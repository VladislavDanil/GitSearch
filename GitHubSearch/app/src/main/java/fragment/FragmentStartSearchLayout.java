package fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import com.example.nitrogenium.githubsearch.R;
/** ласс дл€ создани€ фрагмента из start_search Layout
 *@author ƒанилов ¬ладислав*/
public class FragmentStartSearchLayout extends Fragment {
    /**объ€вл€ю переменную класса FragmentTransaction позвол€ющего
     * удал€ть, добавл€ть, замен€ть фрагмент*/
    private FragmentTransaction transaction;
    /**объ€вл€ю переменную класса Fragment которую в последующем
     * буду использовать дл€ инициализации фрагмента layout result*/
    private Fragment fragmentResult;

    @Override
    /**создание фрагмента из layout start_search*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentStartSearch = inflater.inflate(R.layout.start_search, null);
        /**инициализаци€ фрагмента FragmentResultLayout*/
        fragmentResult = new FragmentResultLayout();
        /**инициализаци€ кнопки searchb из layout start_search*/
        Button button = (Button) fragmentStartSearch.findViewById(R.id.searchb);
        /**обработка нажати€ кнопки searchb ведет к replace()
         «амен€ет один фрагмент на другой*/
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