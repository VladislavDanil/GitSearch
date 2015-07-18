package fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import github.*;
import com.example.nitrogenium.githubsearch.R;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**����� ��� �������� ��������� �� start_search Layout
 *@author ������� ���������*/
public class FragmentStartSearchLayout extends Fragment {
    /**�������� ���������� ������ FragmentTransaction ������������
     * �������, ���������, �������� ��������*/
    private FragmentTransaction transaction;
    /**�������� ���������� ������ Fragment ������� � �����������
     * ���� ������������ ��� ������������� ��������� layout result*/
    private Fragment fragmentResult;
    /**���������� ������ �������� ����������� ������� � ����������������� ����*/
    public static String searchString;
    String API = "https://api.github.com";
    EditText searchText;
    ProgressBar pbar;
    @Override
    /**�������� ��������� �� layout start_search*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentStartSearch = inflater.inflate(R.layout.start_search, null);
        /**������������� ������ searchb �� layout start_search*/
        Button button = (Button) fragmentStartSearch.findViewById(R.id.searchb);
        /**������������� ���� ����� searcht �� layout start_search*/
        searchText = (EditText) fragmentStartSearch.findViewById(R.id.searcht);
        pbar = (ProgressBar) fragmentStartSearch.findViewById(R.id.pbar);
        pbar.setVisibility(View.INVISIBLE);
        /**��������� ������� ������ search ����� � replace()
         �������� ���� �������� �� ������*/

        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                final String user = searchText.getText().toString();
                pbar.setVisibility(View.VISIBLE);
                /**���������� ������ � retrofit*/
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint(API).build();
                /**�������� �������� ��� ������ � �����*/
                final Gitapi git = restAdapter.create(Gitapi.class);
                Thread t = new Thread(new Runnable(){
                    public void run(){
                        Gitmodel r = git.getFeed(user);
                        searchString = ("Github Name :" + r.getName() + "\nWebsite :" + r.getBlog() + "\nCompany Name :" + r.getCompany());
                    }
                });
                t.start();
                //Now ,we need to call for response
                //Retrofit using gson for JSON-POJO conversion

                /*git.getFeed(user, new Callback<Gitmodel>() {
                    @Override
                    public void success(Gitmodel gitmodel, Response response) {
                        //we get json object from github server to our POJO or model class

                       searchString = ("Github Name :" + gitmodel.getName() + "\nWebsite :" + gitmodel.getBlog() + "\nCompany Name :" + gitmodel.getCompany());

                        pbar.setVisibility(View.INVISIBLE);                               //disable progressbar
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        searchString ="Результатов нет!";
                        pbar.setVisibility(View.INVISIBLE);                               //disable progressbar
                    }
                });*/
                // получаем экземпляр элемента ListView

                /**������������� ��������� FragmentResultLayout*/
                fragmentResult = new FragmentResultLayout();
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fragmentResult);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return fragmentStartSearch;
    }
}