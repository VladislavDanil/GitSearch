package robospice;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

import github.Gitapi;

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
    }

}
