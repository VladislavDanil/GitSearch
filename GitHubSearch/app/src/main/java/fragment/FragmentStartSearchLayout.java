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
import android.widget.ProgressBar;

import github.*;

import com.example.nitrogenium.githubsearch.R;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
    public static ArrayList<Item> itemArrayList;
    final private String API = "https://api.github.com";
    /**
     * объявление поля ввода для значения поиска
     */
    EditText mFormSearchText;
    ProgressBar mPbar;
    @Override
    /**метод реализует фрагмент из layout start_search*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentStartSearch = inflater.inflate(R.layout.start_search, null);
        /**инициализация кнопки поиска searchb из layout start_search*/
        Button button = (Button) fragmentStartSearch.findViewById(R.id.searchb);
        /**инициализация поля поиска searcht из layout start_search*/
        mFormSearchText = (EditText) fragmentStartSearch.findViewById(R.id.searcht);
        mPbar = (ProgressBar) fragmentStartSearch.findViewById(R.id.pbar);
        mPbar.setVisibility(View.INVISIBLE);
        /**метод обрабатывает нажатие на кнопку searchb и инициализацию метода replace()
         который заменяет фрагменты*/

        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                /**делает видимым ProgressBar в начале запроса*/
                mPbar.setVisibility(View.VISIBLE);
                /**инициализирует переменную для доступа к серверу*/
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint(API).build();
                /**подключает к адаптеру интерфейс*/
                Gitapi git = restAdapter.create(Gitapi.class);
                /**преобразует значение поля ввода в строку для использования в запросе*/
                String stringSearch = mFormSearchText.getText().toString();
                /**инициализация интерфейса и метода для запроса*/
                git.getFeed((stringSearch + "+in:description+in:name"), new Callback<Example>() {
                    @Override
                    public void success(Example gitmodel, Response response) {
                        /** преобразование json в объектную модель*/
                        itemArrayList = gitmodel.getItems();
                        /** делает невидимым ProgressBar при окончании запроса*/
                        mPbar.setVisibility(View.INVISIBLE);
                        /**инициализация объекта FragmentResultLayout для перехода между фрагментами*/
                        mFragmentResult = new FragmentResultLayout();
                        mTransaction = getFragmentManager().beginTransaction();
                        mTransaction.replace(R.id.fragment, mFragmentResult);
                        /**добавление в стек фрагментов для кнопки back*/
                        mTransaction.addToBackStack(null);
                        mTransaction.commit();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        android.util.Log.v("tag", error.toString());
                        /** делает невидимым ProgressBar при выводе ошибки*/
                        mPbar.setVisibility(View.INVISIBLE);
                    }
                });

            }
        });

        return fragmentStartSearch;
    }
}