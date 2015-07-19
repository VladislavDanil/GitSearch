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
    public static String searchString = "start var";
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

                pbar.setVisibility(View.VISIBLE);
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint(API).build();
                Gitapi git = restAdapter.create(Gitapi.class);
                String stringSearch = searchText.getText().toString();
               /* Thread t = new Thread(new Runnable(){
                    public void run(){
                        String user = searchText.getText().toString();
                        *//**���������� ������ � retrofit*//*
                        RestAdapter restAdapter = new RestAdapter.Builder()
                                .setEndpoint(API).build();
                        *//**�������� �������� ��� ������ � �����*//*
                        Gitapi git = restAdapter.create(Gitapi.class);
                        Gitmodel r = null;
                        try {
                            r = git.getFeed(user);
                            searchString = ("Github Name :" + r.getName());
                        }catch (RetrofitError error){
                            searchString ="Ошибка 404";
                            pbar.setVisibility(View.INVISIBLE);
                        }
                        pbar.setVisibility(View.INVISIBLE);
                    }
                });
                t.start();*/

                git.getFeed((stringSearch+"+in:description+in:name"), new Callback<Gitmodel>() {
                    @Override
                    public void success(Gitmodel gitmodel, Response response) {
                        //we get json object from github server to our POJO or model class

                        searchString = ("Github Name :" + gitmodel.getName()+ gitmodel.getDescription());

                        pbar.setVisibility(View.INVISIBLE);                               //disable progressbar
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        searchString = error.toString();
                        pbar.setVisibility(View.INVISIBLE);                               //disable progressbar
                    }
                });

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