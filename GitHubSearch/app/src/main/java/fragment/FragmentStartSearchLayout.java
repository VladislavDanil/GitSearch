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
import github.*;
import com.example.nitrogenium.githubsearch.R;
import java.util.ArrayList;
/**
 * Класс реализует фрагмент из start_search Layout и при нажатии кнопки позволяет переходить
 * к result Layout
 * кроме того реализует интерфейс gitapi тем самым позволяя сделать запрос к серверу и получить результат
 *
 * @author Данилов Владислав
 */
public class FragmentStartSearchLayout extends Fragment {
    /**
     * объявление переменной для перехода между фрагментами FragmentTransaction
     */
    private FragmentTransaction mTransaction;
    /**
     * объявление фрагмента�layout result для дальнейшего использования при переходе
     */
    private Fragment mFragmentResult;
    /**
     * объявление статической переменной содержащей ответ на запрос от сервера github
     */
    public static String mStringSearch;
    public static ArrayList<Item> itemArrayList;
    final private String API = "https://api.github.com";
    /**
     * объявление поля ввода для значения поиска
     */
    EditText mFormSearchText;
    @Override
    /**метод реализует фрагмент из layout start_search*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentStartSearch = inflater.inflate(R.layout.start_search, null);
        /**инициализация кнопки поиска searchb из layout start_search*/
        Button button = (Button) fragmentStartSearch.findViewById(R.id.searchb);
        /**инициализация поля поиска searcht из layout start_search*/
        mFormSearchText = (EditText) fragmentStartSearch.findViewById(R.id.searcht);
        /**метод обрабатывает нажатие на кнопку searchb и инициализацию метода replace()
         который заменяет фрагменты*/

        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mStringSearch = mFormSearchText.getText().toString();

                        mFragmentResult = new FragmentResultLayout();
                        mTransaction = getFragmentManager().beginTransaction();
                        mTransaction.replace(R.id.fragment, mFragmentResult);
                        /**добавление в стек фрагментов для кнопки back*/
                        mTransaction.addToBackStack(null);
                        mTransaction.commit();

            }
        });

        return fragmentStartSearch;
    }
}