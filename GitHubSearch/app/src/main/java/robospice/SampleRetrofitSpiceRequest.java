package robospice;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import github.Example;
import github.Gitapi;

/**
 * class contains methods for querying github
 *
 * @author Danilov Vladislav
 */
public class SampleRetrofitSpiceRequest extends RetrofitSpiceRequest<Example, Gitapi> {
    /**
     * string search in request
     */
    private String stringSearch;

    /**
     * challenge class RetrofitSpiceRequest
     * receiving at the input string for the request
     *
     * @param stringSearch string for the request
     */
    public SampleRetrofitSpiceRequest(String stringSearch) {
        super(Example.class, Gitapi.class);
        this.stringSearch = stringSearch;
    }

    /**
     * sending request
     *
     * @return
     */
    @Override
    public Example loadDataFromNetwork() {
        return getService().getFeed((stringSearch + "+in:description+in:name"));
    }
}
