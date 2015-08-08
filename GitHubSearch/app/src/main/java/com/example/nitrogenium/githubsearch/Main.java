package com.example.nitrogenium.githubsearch;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import fragment.FragmentResultLayout;

/**
 * the class initializes the main activity
 *
 * @author Данилов Владислав
 */
public class Main extends ActionBarActivity implements SearchView.OnQueryTextListener {
    private RecordsDbHelper mDbHelper;
    public static final String STRING_BUNDLE_INDEX = "string";
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
     * implements requests suggestion search view
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
            mTransaction.setCustomAnimations(R.anim.start_app, 0);
            mTransaction.replace(R.id.fragment, mFragmentResult);
            mTransaction.addToBackStack(null);
            mTransaction.commit();

        }
        mDbHelper = new RecordsDbHelper(this);

        //Получаем Intent
        Intent intent = getIntent();
        //Проверяем тип Intent
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            saveTask(query);
            Bundle stringSearch = new Bundle();
            stringSearch.putString(STRING_BUNDLE_INDEX, query);
            mFragmentResult = new FragmentResultLayout();
            mFragmentResult.setArguments(stringSearch);
            mTransaction = getSupportFragmentManager().beginTransaction();
            mTransaction.setCustomAnimations(R.anim.left, R.anim.right);
            mTransaction.replace(R.id.fragment, mFragmentResult);
            mTransaction.addToBackStack(null);
            mTransaction.commit();
        } else if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri uri = intent.getData();
            Cursor cursor = managedQuery(uri, null, null, null, null);
            cursor.moveToFirst();
            int rIndex = cursor.getColumnIndexOrThrow(RecordsDbHelper.KEY_DATA);
            Bundle stringSearch = new Bundle();
            stringSearch.putString(STRING_BUNDLE_INDEX, cursor.getString(rIndex));
            mFragmentResult = new FragmentResultLayout();
            mFragmentResult.setArguments(stringSearch);
            mTransaction = getSupportFragmentManager().beginTransaction();
            mTransaction.setCustomAnimations(R.anim.left, R.anim.right);
            mTransaction.replace(R.id.fragment, mFragmentResult);
            mTransaction.addToBackStack(null);
            mTransaction.commit();
        }

        /**
         * method allows you to go back when you press
         @Override public void onBackPressed() {
         final Dialog alertDialog = new Dialog(Main.this);
         alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
         final LinearLayout alertDialogCust = (LinearLayout) getLayoutInflater()
         .inflate(R.layout.alert_dialog_main, null);
         alertDialog.setContentView(alertDialogCust);
         Button yes = (Button) alertDialogCust.findViewById(R.id.yes);
         Button not = (Button) alertDialogCust.findViewById(R.id.not);
         yes.setOnClickListener(new View.OnClickListener() {
         @Override public void onClick(View v){
         Main.this.finish();
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


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onQueryTextChange(String text_new) {
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        if (query == null) {
            Toast.makeText(getApplicationContext(), "Null Query", Toast.LENGTH_SHORT).show();
        } else {
            saveTask(mSearchView.getQuery().toString());
            //forming a query string and transferring it by means of Bundle
            //Создаем экземпляр SearchRecentSuggestions
            Bundle stringSearch = new Bundle();
            stringSearch.putString(STRING_BUNDLE_INDEX, mSearchView.getQuery().toString());
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

    private void saveTask(String data) {
        mDbHelper.createRecord(data);
    }

    /**
     * method calls the search string
     *
     * @param item it contains elements of the current menu
     * @return recursive call
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                onSearchRequested();

                return true;
            case R.id.delete_search:
                mDbHelper.dropBase();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

