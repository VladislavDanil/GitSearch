package fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import com.example.nitrogenium.githubsearch.R;
/**����� ��� �������� ��������� �� start_search Layout
 *@author ������� ���������*/
public class FragmentStartSearchLayout extends Fragment {
    /**�������� ���������� ������ FragmentTransaction ������������
     * �������, ���������, �������� ��������*/
    private FragmentTransaction transaction;
    /**�������� ���������� ������ Fragment ������� � �����������
     * ���� ������������ ��� ������������� ��������� layout result*/
    private Fragment fragmentResult;

    @Override
    /**�������� ��������� �� layout start_search*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentStartSearch = inflater.inflate(R.layout.start_search, null);
        /**������������� ��������� FragmentResultLayout*/
        fragmentResult = new FragmentResultLayout();
        /**������������� ������ searchb �� layout start_search*/
        Button button = (Button) fragmentStartSearch.findViewById(R.id.searchb);
        /**��������� ������� ������ searchb ����� � replace()
         �������� ���� �������� �� ������*/
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fragmentResult);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return fragmentStartSearch;
    }
}