package com.example.nitrogenium.githubsearch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import fragment.FragmentStartSearchLayout;

/**
 * Класс инициализирует главную активность
 *@author Данилов Владислав
 * */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
        if (savedInstanceState == null) {
            /**
             * объявляю переменную класса Fragment которую в последующем
             * буду использовать для инициализации фрагмента layout result
             * */
            Fragment fragmentStartSearch;
            /**
             * объявляю переменную класса FragmentTransaction позволяющего
             * удалять, добавлять, заменять фрагмент
             * */
            FragmentTransaction transaction;
            /**
             * инициализация фрагмента FragmentStartSearchLayout
             * */
            fragmentStartSearch = new FragmentStartSearchLayout();
            /**
             * вызов фрагмента fragmentStartSearch в layout fragment_layout
             * */
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, fragmentStartSearch);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
