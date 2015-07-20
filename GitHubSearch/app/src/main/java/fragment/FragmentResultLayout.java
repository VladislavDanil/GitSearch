package fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.nitrogenium.githubsearch.R;

import java.util.ArrayList;

import github.Item;

/**
 * Класс реализует фрагмент из result Layout и при нажатии кнопки позволяет переходить
 * к strart_searh Layout
 * кроме того реализует вывод данныхв список на экран
 *
 * @author Данилов Владислав
 */
public class FragmentResultLayout extends Fragment {
    /**
     * объявление переменной для перехода между фрагментами FragmentTransaction
     */
    private FragmentTransaction transaction;
    /**
     * объявление фрагмента�layout start_search для дальнейшего использования при переходе
     */
    private Fragment fragmentStartSearch;

    @Override
    /**метод реализует фрагмент из layout result*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentResult = inflater.inflate(R.layout.result, null);
        fragmentStartSearch = new FragmentStartSearchLayout();
        /**инициализация кнопки для нового поиска resultb �� layout result*/
        Button button = (Button) fragmentResult.findViewById(R.id.resultb);
        /**получаем экземпляр элемента ListView*/
        ListView listView = (ListView) fragmentResult.findViewById(R.id.listView);
        /**запись результатов из статической переменной в массив*/
        ArrayList<String> resultGit=new ArrayList<String>();
        for (Item item : FragmentStartSearchLayout.itemArrayList) {
                resultGit.add("Name: " + item.getName() + "\n" + "Description: " + item.getDescription());
        }
        ArrayAdapter<String> adapter;
        /**вывод данных в список во встроенный адаптер*/
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, resultGit);
        /**вывод данных сформированных в адаптере на экран*/
        listView.setAdapter(adapter);
        /**обработчик нажатия кнопки для перехода между фрагментами*/
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
