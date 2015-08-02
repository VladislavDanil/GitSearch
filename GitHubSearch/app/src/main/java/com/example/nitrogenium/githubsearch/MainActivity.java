package com.example.nitrogenium.githubsearch;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import android.view.MenuItem;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import fragment.FragmentResultLayout;

/**
 * the class initializes the main activity
 *
 * @author Данилов Владислав
 */
public class MainActivity extends ActionBarActivity implements SearchView.OnQueryTextListener {
    SearchView mSearchView;
    /**
     * ads fragment�layout result for further use in the transition
     */
    Fragment mFragmentResult;
    /**
     * variable declaration for the transition between fragments FragmentTransaction
     */
    FragmentTransaction mTransaction;
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
            mFragmentResult = new FragmentResultLayout();
            mTransaction = getSupportFragmentManager().beginTransaction();
            mTransaction.setCustomAnimations(R.anim.start_app,0);
            mTransaction.replace(R.id.fragment, mFragmentResult);
            mTransaction.addToBackStack(null);
            mTransaction.commit();
        }

    }

    /**
     * method allows you to go back when you press
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
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setQueryHint("Поиск");
        mSearchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    public boolean onQueryTextChange(String text_new) {
        return true;
    }

    public boolean onQueryTextSubmit(String query) {

        if (query == null) {
            Toast.makeText(getApplicationContext(), "Null Query", Toast.LENGTH_SHORT).show();
        } else {
            //forming a query string and transferring it by means of Bundle
            Bundle stringSearch = new Bundle();
            stringSearch.putString("string", mSearchView.getQuery().toString());
            mFragmentResult = new FragmentResultLayout();
            mFragmentResult.setArguments(stringSearch);
            mTransaction = getSupportFragmentManager().beginTransaction();
            mTransaction.setCustomAnimations(R.anim.left, R.anim.right);
            mTransaction.replace(R.id.fragment, mFragmentResult);
            mTransaction.addToBackStack(null);
            mTransaction.commit();
        }
        return true;
    }


}
