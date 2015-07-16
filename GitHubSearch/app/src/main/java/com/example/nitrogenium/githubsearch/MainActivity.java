package com.example.nitrogenium.githubsearch;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.Activity;
import android.os.Bundle;

import fragment.FragmentStartSearchLayout;

/**����� �������������� ������� ����������
 *@author ������� ���������*/
class MainActivity extends Activity {
    /**�������� ���������� ������ Fragment ������� � �����������
     * ���� ������������ ��� ������������� ��������� layout result*/
    private Fragment fragmentStartSearch;
    /**�������� ���������� ������ FragmentTransaction ������������
     * �������, ���������, �������� ��������*/
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
        /**������������� ��������� FragmentStartSearchLayout*/
        fragmentStartSearch = new FragmentStartSearchLayout();
        /**����� ��������� fragmentStartSearch � layout fragment_layout*/
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragmentStartSearch);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
