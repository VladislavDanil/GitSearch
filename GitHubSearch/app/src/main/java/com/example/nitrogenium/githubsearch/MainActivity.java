package com.example.nitrogenium.githubsearch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import fragment.FragmentStartSearchLayout;

/**
 * ����� �������������� ������� ����������
 *@author ������� ���������
 * */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
        /**
         * �������� ���������� ������ Fragment ������� � �����������
         * ���� ������������ ��� ������������� ��������� layout result
         * */
        Fragment fragmentStartSearch;
        /**
         * �������� ���������� ������ FragmentTransaction ������������
         * �������, ���������, �������� ��������
         * */
        FragmentTransaction transaction;
        /**
         * ������������� ��������� FragmentStartSearchLayout
         * */
        fragmentStartSearch = new FragmentStartSearchLayout();
        /**
         * ����� ��������� fragmentStartSearch � layout fragment_layout
         * */
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragmentStartSearch);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
