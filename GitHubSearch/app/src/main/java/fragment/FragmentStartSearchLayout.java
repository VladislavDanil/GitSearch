package fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.example.nitrogenium.githubsearch.R;
/**
 * The class implements a fragment of start_search Layout and press the button allows you to jump
 * to result Layout
 * implements the interface gitapi thereby enabling to make a request to the server and get results
 *
 * @author Danilov Vladislav
 */
public class FragmentStartSearchLayout extends Fragment {
    /**
     * variable declaration for the transition between fragments FragmentTransaction
     */
    private FragmentTransaction mTransaction;
    /**
     * ads fragment�layout result for further use in the transition
     */
    private Fragment mFragmentResult;
    /**
     * ads containing static variable response to a request from the server github
     */
    public static String mStringSearch;
    /**
     * ads input field for the value of search
     */
    EditText mFormSearchText;

    /**
     * method implements a fragment of the layout start_search
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
        View fragmentStartSearch = inflater.inflate(R.layout.start_search, null);
        Button button = (Button) fragmentStartSearch.findViewById(R.id.searchb);
        mFormSearchText = (EditText) fragmentStartSearch.findViewById(R.id.searcht);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mStringSearch = mFormSearchText.getText().toString();
                mFragmentResult = new FragmentResultLayout();
                mTransaction = getFragmentManager().beginTransaction();
                mTransaction.replace(R.id.fragment, mFragmentResult);
                mTransaction.addToBackStack(null);
                mTransaction.commit();

            }
        });

        return fragmentStartSearch;
    }
}