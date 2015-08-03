package robospice;


import android.app.Application;

import com.octo.android.robospice.SpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.ormlite.InDatabaseObjectPersisterFactory;
import com.octo.android.robospice.persistence.ormlite.RoboSpiceDatabaseHelper;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

import java.util.ArrayList;
import java.util.List;

import github.Example;
import github.Gitapi;
import github.Item;
import github.Owner;

/**
 * Service for inquiries.
 * @author Danilov Vladislav
 */
public class SampleRetrofitSpiceService extends RetrofitGsonSpiceService {

    private final static String BASE_URL = "https://api.github.com";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(Gitapi.class);
    }
    @Override
    public CacheManager createCacheManager(Application application) {
        CacheManager cacheManager = new CacheManager();
        List< Class< ? >> classCollection = new ArrayList< Class< ? >>();

        // add persisted classes to class collection
        classCollection.add( Example.class );
        classCollection.add(Item.class);
        classCollection.add(Owner.class);

        // init
        RoboSpiceDatabaseHelper databaseHelper = new RoboSpiceDatabaseHelper( application, "sample_database.db", 1 );
        InDatabaseObjectPersisterFactory inDatabaseObjectPersisterFactory = new InDatabaseObjectPersisterFactory( application, databaseHelper, classCollection );
        cacheManager.addPersister(inDatabaseObjectPersisterFactory);

        return cacheManager;
    }
    @Override
    protected String getServerUrl() {
        return BASE_URL;
    };
}