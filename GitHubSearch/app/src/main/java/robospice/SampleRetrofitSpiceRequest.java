package robospice;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import github.Example;
import github.Gitapi;
import roboguice.util.temp.Ln;

/**
 * Created by Nitrogenium on 23.07.15.
 */
public class SampleRetrofitSpiceRequest extends RetrofitSpiceRequest<Example, Gitapi> {
    private String stringSearch;

    public SampleRetrofitSpiceRequest(String stringSearch) {
        super(Example.class, Gitapi.class);
        this.stringSearch = stringSearch;
    }

    @Override
    public Example loadDataFromNetwork() {
        Ln.d("Call web service ");
        return getService().getFeed((stringSearch + "+in:description+in:name"));
    }
}
