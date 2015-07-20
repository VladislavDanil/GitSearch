package fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
    private FragmentTransaction transaction;
    /**
     * объявление фрагмента�layout result для дальнейшего использования при переходе
     */
    private Fragment fragmentResult;
    /**
     * объявление статической переменной содержащей ответ на запрос от сервера github
     */
    public static String searchString = "";
    String API = "https://api.github.com";
    /**
     * объявление поля ввода для значения поиска
     */
    EditText searchText;
    ProgressBar pbar;

    @Override
    /**метод реализует фрагмент из layout start_search*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentStartSearch = inflater.inflate(R.layout.start_search, null);
        /**инициализация кнопки поиска searchb из layout start_search*/
        Button button = (Button) fragmentStartSearch.findViewById(R.id.searchb);
        /**инициализация поля поиска searcht из layout start_search*/
        searchText = (EditText) fragmentStartSearch.findViewById(R.id.searcht);
        pbar = (ProgressBar) fragmentStartSearch.findViewById(R.id.pbar);
        pbar.setVisibility(View.INVISIBLE);
        /**метод обрабатывает нажатие на кнопку searchb и инициализацию метода replace()
         который заменяет фрагменты*/

        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
/**делает видимым ProgressBar в начале запроса*/
                pbar.setVisibility(View.VISIBLE);
                /**инициализирует переменную для доступа к серверу*/
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint(API).build();
                /**подключает к адаптеру интерфейс*/
                Gitapi git = restAdapter.create(Gitapi.class);
                /**преобразует значение поля ввода в строку для использования в запросе*/
                String stringSearch = searchText.getText().toString();
                /**инициализация интерфейса и метода для запроса*/
                git.getFeed((stringSearch + "+in:description+in:name"), new Callback<Gitmodel>() {
                    @Override
                    public void success(Gitmodel gitmodel, Response response) {
                        /** преобразование json в объектную модель*/
                        searchString = ("Github Name :" + gitmodel.getName() + gitmodel.getDescription());
                        /** делает невидимым ProgressBar при окончании запроса*/
                        pbar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        /**присвоение строке данных ошибки запроса*/
                        searchString = error.toString();
                        /** делает невидимым ProgressBar при выводе ошибки*/
                        pbar.setVisibility(View.INVISIBLE);
                    }
                });

                /**инициализация объекта FragmentResultLayout для перехода между фрагментами*/
                fragmentResult = new FragmentResultLayout();
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fragmentResult);
                /**добавление в стек фрагментов для кнопки back*/
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return fragmentStartSearch;
    }
}