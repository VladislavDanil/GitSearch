package fragment;

/**
 * Created by Nitrogenium on 15.07.15.
 */
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nitrogenium.githubsearch.R;

public class FragmentResultLayout extends Fragment {
    private FragmentTransaction transaction;
    private Fragment fragmentStartSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentResult = inflater.inflate(R.layout.result, null);
        fragmentStartSearch = new FragmentStartSearchLayout();
        Button button = (Button) fragmentResult.findViewById(R.id.resultb);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fragmentStartSearch);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return fragmentResult;
    }
}
