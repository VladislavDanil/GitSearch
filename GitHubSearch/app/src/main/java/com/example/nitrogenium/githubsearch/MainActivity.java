package com.example.nitrogenium.githubsearch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import fragment.FragmentStartSearchLayout;

/**
 * the class initializes the main activity
 *
 * @author Данилов Владислав
 */
public class MainActivity extends AppCompatActivity {
    /**
     * method creates the main active,
     * loads the initial fragment FragmentStartSearchLayout
     *
     * @param savedInstanceState if non-null, this fragment is being re-constructed from a previous saved state as given here
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
        if (savedInstanceState == null) {
            Fragment fragmentStartSearch;
            FragmentTransaction transaction;
            fragmentStartSearch = new FragmentStartSearchLayout();
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, fragmentStartSearch);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
