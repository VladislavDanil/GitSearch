package com.example.nitrogenium.githubsearch;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
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
            transaction.setCustomAnimations(R.anim.animstart,0);
            transaction.replace(R.id.fragment, fragmentStartSearch);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    /**
     * method allows you to go back when you press
     */
    @Override
    public void onBackPressed() {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("Выйти?");

            alertDialog.setMessage("Вы действительно хотите выйти?");

            alertDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });

            alertDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialog.show();
            return;
    }
}
