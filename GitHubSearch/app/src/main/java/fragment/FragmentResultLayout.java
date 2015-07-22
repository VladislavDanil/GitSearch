package fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import adapter.AdapterRepositories;

import com.example.nitrogenium.githubsearch.R;

import java.util.ArrayList;

import adapter.RepositoriesElement;
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
    private FragmentTransaction mTransaction;
    /**
     * объявление фрагмента�layout start_search для дальнейшего использования при переходе
     */
    private Fragment mFragmentStartSearch;
    ArrayList<RepositoriesElement> repositoriesElements = new ArrayList<RepositoriesElement>();
    @Override
    /**метод реализует фрагмент из layout result*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentResult = inflater.inflate(R.layout.result, null);

        mFragmentStartSearch = new FragmentStartSearchLayout();
        /**инициализация кнопки для нового поиска resultb �� layout result*/
        Button button = (Button) fragmentResult.findViewById(R.id.resultb);
        /**получаем экземпляр элемента ListView*/
        ListView listView = (ListView) fragmentResult.findViewById(R.id.listView);
        /**запись результатов из статической переменной в массив*/
        for (Item item : FragmentStartSearchLayout.itemArrayList) {
            repositoriesElements.add(new RepositoriesElement((item.stargazersCount).toString(), item.owner.avatarUrl, item.name, item.owner.login));
        }

        /**вывод данных сформированных в адаптере на экран*/
        AdapterRepositories adapterRepositories;
        adapterRepositories = new AdapterRepositories(getActivity(),repositoriesElements);
        listView.setAdapter(adapterRepositories);
        /**обработчик нажатия кнопки для перехода между фрагментами*/
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mTransaction = getFragmentManager().beginTransaction();
                mTransaction.replace(R.id.fragment, mFragmentStartSearch);
                mTransaction.addToBackStack(null);
                mTransaction.commit();
            }
        });
        return fragmentResult;
    }
}
