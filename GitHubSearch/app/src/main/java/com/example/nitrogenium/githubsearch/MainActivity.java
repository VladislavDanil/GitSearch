package com.example.nitrogenium.githubsearch;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.Activity;
import android.os.Bundle;

import fragment.FragmentResultLayout;
import fragment.FragmentStartSearchLayout;


public class MainActivity extends Activity{
    private Fragment fragmentStartSearch;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
        fragmentStartSearch = new FragmentStartSearchLayout();
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragmentStartSearch);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
