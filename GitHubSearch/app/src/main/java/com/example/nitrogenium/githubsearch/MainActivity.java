package com.example.nitrogenium.githubsearch;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
            final Dialog alertDialog = new Dialog(MainActivity.this);
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            final LinearLayout alertDialogCust = (LinearLayout) getLayoutInflater()
                .inflate(R.layout.alert_dialog_main, null);
            alertDialog.setContentView(alertDialogCust);
            Button yes = (Button) alertDialogCust.findViewById(R.id.yes);
            Button not = (Button) alertDialogCust.findViewById(R.id.not);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                MainActivity.this.finish();
            }
        });
        not.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        alertDialog.show();
        return;
    }
}
