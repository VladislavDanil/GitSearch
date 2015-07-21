package com.example.nitrogenium.githubsearch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import fragment.FragmentStartSearchLayout;

/**
 *  ласс инициализирует главную активность
 *@author ƒанилов ¬ладислав
 * */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
        /**
         * объ€вл€ю переменную класса Fragment которую в последующем
         * буду использовать дл€ инициализации фрагмента layout result
         * */
        Fragment fragmentStartSearch;
        /**
         * объ€вл€ю переменную класса FragmentTransaction позвол€ющего
         * удал€ть, добавл€ть, замен€ть фрагмент
         * */
        FragmentTransaction transaction;
        /**
         * инициализаци€ фрагмента FragmentStartSearchLayout
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
