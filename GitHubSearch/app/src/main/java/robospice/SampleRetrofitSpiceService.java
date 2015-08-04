package robospice;


import android.app.Application;


import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.ormlite.InDatabaseObjectPersisterFactory;
import com.octo.android.robospice.persistence.ormlite.RoboSpiceDatabaseHelper;
import com.octo.android.robospice.persistence.retrofit.RetrofitObjectPersisterFactory;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import github.Example;
import github.Gitapi;
import github.Item;
import github.Owner;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

/**
 * Service for inquiries.
 * @author Danilov Vladislav
 */

public class SampleRetrofitSpiceService extends RetrofitGsonSpiceService {
    private Converter converter = new GsonConverter(new Gson());
    private final static String BASE_URL = "https://api.github.com";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(Gitapi.class);
    }

   @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException{
        CacheManager cacheManager = new CacheManager();
        List< Class< ? >> classCollection = new ArrayList< Class< ? >>();

        // add persisted classes to class collection
        classCollection.add( Example.class );
        classCollection.add(Item.class);
        classCollection.add(Owner.class);

        // init
        RoboSpiceDatabaseHelper databaseHelper = new RoboSpiceDatabaseHelper( application, "sample_database.db", 1 );
        InDatabaseObjectPersisterFactory inDatabaseObjectPersisterFactory = new InDatabaseObjectPersisterFactory( application, databaseHelper, classCollection );
        cacheManager.addPersister(new RetrofitObjectPersisterFactory(application, converter, getCacheFolder()));

        return cacheManager;
    }
    @Override
    protected String getServerUrl() {
        return BASE_URL;
    };
}