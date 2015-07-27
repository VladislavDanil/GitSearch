package robospice;

import android.app.Application;

import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.string.InFileStringObjectPersister;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

import java.io.File;

import github.Gitapi;

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
    protected String getServerUrl() {
        return BASE_URL;
    };
}