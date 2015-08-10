package com.example.nitrogenium.githubsearch;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
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
public class Main extends ActionBarActivity implements SearchView.OnQueryTextListener, SearchView.OnSuggestionListener {
    private RecordsDbHelper mDbHelper;
    private Intent intent;
    public static final String STRING_BUNDLE_INDEX = "string";
    SearchView mSearchView;
    /**
     * ads fragment�layout result for further use in the transition
     */
    Fragment mFragmentResult;
    // At the top of your class
    private Menu mMenu;
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
        mDbHelper = new RecordsDbHelper(this);
        intent = getIntent();
        intent.getAction();
        if (savedInstanceState == null) {
            mFragmentResult = new FragmentResultLayout();
            mTransaction = getSupportFragmentManager().beginTransaction();
            mTransaction.setCustomAnimations(R.anim.start_app, 0);
            mTransaction.replace(R.id.fragment, mFragmentResult);
            mTransaction.addToBackStack(null);
            mTransaction.commit();

        }


        /*
        //Проверяем тип Intent
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
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
        }*/

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
        this.mMenu = menu;
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setQueryHint("Поиск");
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setOnSuggestionListener(this);
        mSearchView.setIconifiedByDefault(false);
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
            Bundle stringSearch = new Bundle();
            stringSearch.putString(STRING_BUNDLE_INDEX, mSearchView.getQuery().toString());
            mFragmentResult = new FragmentResultLayout();
            mFragmentResult.setArguments(stringSearch);
            mTransaction = getSupportFragmentManager().beginTransaction();
            mTransaction.setCustomAnimations(R.anim.left, R.anim.right);
            mTransaction.replace(R.id.fragment, mFragmentResult);
            mTransaction.addToBackStack(null);
            mTransaction.commit();
            mSearchView.clearFocus();
            mSearchView.setFocusable(false);
            mSearchView.onActionViewCollapsed();
        }

        return true;
    }

    private void saveTask(String data) {
        mDbHelper.createRecord(data);
    }

    /**
     * click on the menu item clears the cache
     *
     * @param item it contains elements of the current menu
     * @return recursive call
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_search:
                mDbHelper.dropBase();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSuggestionSelect(int position) {
        return false;
    }


    @Override
    public boolean onSuggestionClick(int position) {
        Uri CONTENT_URI = Uri.parse("content://com.example.nitrogenium.githubsearch.SuggestionProvider/records/"+(position+1));
            Cursor cursor = managedQuery(CONTENT_URI, null, null, null,
                    null);
            if (cursor == null) {
                finish();
            } else {
                int rIndex = cursor.getColumnIndexOrThrow(RecordsDbHelper.KEY_DATA);
                Bundle stringSearch = new Bundle();
                stringSearch.putString(STRING_BUNDLE_INDEX, cursor.getString(rIndex));
                cursor.close();
                mFragmentResult = new FragmentResultLayout();
                mFragmentResult.setArguments(stringSearch);
                mTransaction = getSupportFragmentManager().beginTransaction();
                mTransaction.setCustomAnimations(R.anim.left, R.anim.right);
                mTransaction.replace(R.id.fragment, mFragmentResult);
                mTransaction.addToBackStack(null);
                mTransaction.commit();
                return true;

            }
        return false;
    }
}

