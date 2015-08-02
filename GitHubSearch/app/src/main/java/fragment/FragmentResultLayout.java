package fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import ScansInternet.InternetDetecter;
import adapter.AdapterRepositories;

import com.example.nitrogenium.githubsearch.R;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;

import adapter.RepositoriesElement;
import github.Example;
import github.Item;
import robospice.SampleRetrofitSpiceRequest;
import robospice.SampleRetrofitSpiceService;

import static com.example.nitrogenium.githubsearch.R.mipmap.gh_search;

/**
 * It creates a fragment from result Layout
 * and when you pass to strart_searh Layout.
 * It makes a request for github using the libraries robospice.
 *
 * @author Danilov Vladislav
 */
public class FragmentResultLayout extends Fragment {
    Bundle mBundleStringSearch;
    /**
     * to execute requests, clear cache, prevent listeners from being called and so on,
     * basically, all features of the SpiceService are accessible from the SpiceManager,
     * inicialisation using class SampleRetrofitSpiceService
     */
    private SpiceManager mSpiceManager = new SpiceManager(SampleRetrofitSpiceService.class);
    /**
     * it is used to create queries
     */
    private SampleRetrofitSpiceRequest mGithubRequest;
    /**
     * visual indicator of progress in some operation
     */
    private ProgressBar mResultProgressBar;
    /**
     * the query string
     */
    private String mStringSearch;
    /**
     * a container for storing the results of the query
     */
    private ArrayList<RepositoriesElement> mRepositoriesElements = new ArrayList<RepositoriesElement>();

    /**
     * method implements a fragment of layout result,
     * realizes transition by pressing the button resultb
     *
     * @param inflater           the LayoutInflater object that can be used to inflate any views in the fragment
     * @param container          if non-null, this is the parent view that the fragment's UI should be attached to.
     *                           the fragment should not add the view itself, but this can be used to generate the
     *                           LayoutParams of the view
     * @param savedInstanceState if non-null, this fragment is being re-constructed from a previous saved state as given here
     * @return return the View for the fragment's UI, or null
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentResult = inflater.inflate(R.layout.result, container, false);
        InternetDetecter validateInternet = new InternetDetecter();
        validateInternet.hasConnection(getActivity());
        mResultProgressBar = (ProgressBar) fragmentResult.findViewById(R.id.result_progress_bar);
        mResultProgressBar.setVisibility(View.INVISIBLE);
        mBundleStringSearch = getArguments();
        if (mBundleStringSearch != null) {
            mStringSearch = mBundleStringSearch.getString("string");
            mGithubRequest = new SampleRetrofitSpiceRequest(mStringSearch);
        } else {
            ImageView startImage =(ImageView)fragmentResult.findViewById(R.id.startImage);
            startImage.setImageResource(R.mipmap.gh_search);

        }
        return fragmentResult;
    }

    /**
     * constructor SpiceManager
     *
     * @return the instances of this class allow to acces the SpiceService
     */
    protected SpiceManager getmSpiceManager() {
        return mSpiceManager;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // returns the Transition that will be used to move Views out of the scene when the Fragment
        // is preparing to be removed, hidden, or detached because of popping the back stack
        setRetainInstance(true);
    }

    @Override
    public void onStart() {

        mSpiceManager.start(getActivity());
        super.onStart();
        if (mBundleStringSearch != null) {
            mResultProgressBar.setVisibility(View.VISIBLE);
            getmSpiceManager().execute(mGithubRequest, mStringSearch, DurationInMillis.ONE_DAY, new ListContributorRequestListener());

        } else {
            mResultProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onStop() {
        mSpiceManager.shouldStop();
        super.onStop();
    }

    /**
     * creates ListView,
     * record the results of a static variable in an array,
     * the output data generated in the adapter to the screen
     *
     * @param result array json serialization object
     */
    private void updateContributors(Example result) {
        if (mBundleStringSearch != null) {
            for (Item item : result.getItems()) {
                mRepositoriesElements.add(new RepositoriesElement((item.stargazersCount).toString(), item.owner.avatarUrl, item.name, item.owner.login, item.htmlUrl));
            }
            RecyclerView recList = (RecyclerView) getActivity().findViewById(R.id.cardList);
            recList.setHasFixedSize(true);
            if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                recList.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            }
            else{
                recList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            }

            AdapterRepositories adapterRepositories = new AdapterRepositories(mRepositoriesElements);
            recList.setAdapter(adapterRepositories);


        }


    }





    /**
     * Processing successful and unsuccessful request.
     */
    public final class ListContributorRequestListener implements RequestListener<Example> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            mResultProgressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onRequestSuccess(Example result) {
            mResultProgressBar.setVisibility(View.INVISIBLE);
            updateContributors(result);
        }
    }
}
