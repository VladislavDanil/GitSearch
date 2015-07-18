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
/**����� ��� �������� ��������� �� result Layout
 *@author ������� ���������*/
public class FragmentResultLayout extends Fragment {
    /**�������� ���������� ������ FragmentTransaction ������������
     * �������, ���������, �������� ��������*/
    private FragmentTransaction transaction;
    /**�������� ���������� ������ Fragment ������� � �����������
     * ���� ������������ ��� ������������� ��������� layout start_search*/
    private Fragment fragmentStartSearch;

    @Override
    /**�������� ��������� �� layout result*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentResult = inflater.inflate(R.layout.result, null);
        /**������������� ��������� FragmentStartSearchLayout*/
        fragmentStartSearch = new FragmentStartSearchLayout();
        /**������������� ������ resultb �� layout result*/
        Button button = (Button) fragmentResult.findViewById(R.id.resultb);
        /**��������� ������� ������ resultb ����� � replace()
         �������� ���� �������� �� ������*/
        // получаем экземпляр элемента ListView
        ListView listView = (ListView) fragmentResult.findViewById(R.id.listView);
        String[] resultGit ={FragmentStartSearchLayout.searchString};
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, resultGit);
        listView.setAdapter(adapter);
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
