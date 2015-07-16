package com.example.nitrogenium.githubsearch;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.Activity;
import android.os.Bundle;

import fragment.FragmentStartSearchLayout;

/** ласс инициализирует главную активность
 *@author ƒанилов ¬ладислав*/
class MainActivity extends Activity {
    /**объ€вл€ю переменную класса Fragment которую в последующем
     * буду использовать дл€ инициализации фрагмента layout result*/
    private Fragment fragmentStartSearch;
    /**объ€вл€ю переменную класса FragmentTransaction позвол€ющего
     * удал€ть, добавл€ть, замен€ть фрагмент*/
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
        /**инициализаци€ фрагмента FragmentStartSearchLayout*/
        fragmentStartSearch = new FragmentStartSearchLayout();
        /**вызов фрагмента fragmentStartSearch в layout fragment_layout*/
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragmentStartSearch);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
