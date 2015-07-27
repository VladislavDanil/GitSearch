package fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

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

/**
 * It creates a fragment from result Layout
 * and when you pass to strart_searh Layout.
 * It makes a request for github using the libraries robospice.
 *
 * @author Данилов Владислав
 */
public class FragmentResultLayout extends Fragment {
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
     * API for performing a set of Fragment operations
     */
    private FragmentTransaction mTransaction;
    /**
     * ads fragment�layout start_search for further use in the transition
     */
    private Fragment mFragmentStartSearch;
    ArrayList<RepositoriesElement> mRepositoriesElements = new ArrayList<RepositoriesElement>();

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
        View fragmentResult = inflater.inflate(R.layout.result, null);
        mResultProgressBar = (ProgressBar) fragmentResult.findViewById(R.id.result_progress_bar);
        mResultProgressBar.setVisibility(View.INVISIBLE);
        mFragmentStartSearch = new FragmentStartSearchLayout();
        Button resultb = (Button) fragmentResult.findViewById(R.id.resultb);
        resultb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mTransaction = getFragmentManager().beginTransaction();
                mTransaction.replace(R.id.fragment, mFragmentStartSearch);
                mTransaction.addToBackStack(null);
                mTransaction.commit();
            }
        });
        mGithubRequest = new SampleRetrofitSpiceRequest(FragmentStartSearchLayout.mStringSearch);
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
        mResultProgressBar.setVisibility(View.VISIBLE);
        mSpiceManager.start(getActivity());
        super.onStart();
        getmSpiceManager().execute(mGithubRequest, "github", DurationInMillis.ONE_MINUTE, new ListContributorRequestListener());

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
        ListView listView = (ListView) getActivity().findViewById(R.id.listView);
        for (Item item : result.getItems()) {
            mRepositoriesElements.add(new RepositoriesElement((item.stargazersCount).toString(), item.owner.avatarUrl, item.name, item.owner.login));
        }
        AdapterRepositories adapterRepositories;
        adapterRepositories = new AdapterRepositories(getActivity(), mRepositoriesElements);
        listView.setAdapter(adapterRepositories);
    }

    /**
     * Processing successful and unsuccessful request.
     */
    public final class ListContributorRequestListener implements RequestListener<Example> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            mResultProgressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(Example result) {
            mResultProgressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
            updateContributors(result);
        }
    }
}
