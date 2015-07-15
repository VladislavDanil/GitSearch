package fragment;
/**
 * Created by Nitrogenium on 15.07.15.
 */
        import android.app.Fragment;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import com.example.nitrogenium.githubsearch.R;

public class FragmentStartSearchLayout extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.start_search, null);
    }
}