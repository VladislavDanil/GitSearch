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
import android.widget.ProgressBar;
import android.widget.Toast;

import adapter.AdapterRepositories;

import com.example.nitrogenium.githubsearch.R;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;

import adapter.RepositoriesElement;
import github.Example;
import github.Item;
import robospice.SampleRetrofitSpiceRequest;
import robospice.SampleRetrofitSpiceService;

/**
 * Класс реализует фрагмент из result Layout и при нажатии кнопки позволяет переходить
 * к strart_searh Layout
 * кроме того реализует вывод данных в список на экран
 *
 * @author Данилов Владислав
 */
public class FragmentResultLayout extends Fragment {
    private SpiceManager spiceManager = new SpiceManager(SampleRetrofitSpiceService.class);
    private SampleRetrofitSpiceRequest githubRequest;
    private ProgressBar resultProgressBar;
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
        resultProgressBar = (ProgressBar) fragmentResult.findViewById(R.id.ResultProgressBar);
        resultProgressBar.setVisibility(View.INVISIBLE);
        mFragmentStartSearch = new FragmentStartSearchLayout();
        /**инициализация кнопки для нового поиска resultb �� layout result*/
        Button button = (Button) fragmentResult.findViewById(R.id.resultb);
        /**обработчик нажатия кнопки для перехода между фрагментами*/
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mTransaction = getFragmentManager().beginTransaction();
                mTransaction.replace(R.id.fragment, mFragmentStartSearch);
                mTransaction.addToBackStack(null);
                mTransaction.commit();
            }
        });
        githubRequest = new SampleRetrofitSpiceRequest(FragmentStartSearchLayout.mStringSearch);
        return fragmentResult;
    }

    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onStart() {
        resultProgressBar.setVisibility(View.VISIBLE);
        spiceManager.start(getActivity());
        super.onStart();
        getSpiceManager().execute(githubRequest, "github", DurationInMillis.ONE_MINUTE, new ListContributorRequestListener());

    }

    @Override
    public void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    private void updateContributors(Example result) {
        /**получаем экземпляр элемента ListView*/
        ListView listView = (ListView) getActivity().findViewById(R.id.listView);
        /**запись результатов из статической переменной в массив*/
        for (Item item : result.getItems()) {
            repositoriesElements.add(new RepositoriesElement((item.stargazersCount).toString(), item.owner.avatarUrl, item.name, item.owner.login));
        }

        /**вывод данных сформированных в адаптере на экран*/
        AdapterRepositories adapterRepositories;
        adapterRepositories = new AdapterRepositories(getActivity(),repositoriesElements);
        listView.setAdapter(adapterRepositories);
    }

    public final class ListContributorRequestListener implements RequestListener<Example> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            resultProgressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(Example result) {
            resultProgressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
            updateContributors(result);
        }
    }
}
