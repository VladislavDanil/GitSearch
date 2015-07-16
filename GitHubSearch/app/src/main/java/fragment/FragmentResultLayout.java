package fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.nitrogenium.githubsearch.R;
/** ласс дл€ создани€ фрагмента из result Layout
 *@author ƒанилов ¬ладислав*/
public class FragmentResultLayout extends Fragment {
    /**объ€вл€ю переменную класса FragmentTransaction позвол€ющего
     * удал€ть, добавл€ть, замен€ть фрагмент*/
    private FragmentTransaction transaction;
    /**объ€вл€ю переменную класса Fragment которую в последующем
     * буду использовать дл€ инициализации фрагмента layout start_search*/
    private Fragment fragmentStartSearch;

    @Override
    /**создание фрагмента из layout result*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentResult = inflater.inflate(R.layout.result, null);
        /**инициализаци€ фрагмента FragmentStartSearchLayout*/
        fragmentStartSearch = new FragmentStartSearchLayout();
        /**инициализаци€ кнопки resultb из layout result*/
        Button button = (Button) fragmentResult.findViewById(R.id.resultb);
        /**обработка нажати€ кнопки resultb ведет к replace()
         «амен€ет один фрагмент на другой*/
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
