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
        import android.view.View.OnClickListener;
        import com.example.nitrogenium.githubsearch.R;

public class FragmentStartSearchLayout extends Fragment {
    private FragmentTransaction transaction;
    private Fragment fragmentResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentStartSearch = inflater.inflate(R.layout.start_search, null);
        fragmentResult = new FragmentResultLayout();
        Button button = (Button) fragmentStartSearch.findViewById(R.id.searchb);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fragmentResult);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return fragmentStartSearch;
    }
}