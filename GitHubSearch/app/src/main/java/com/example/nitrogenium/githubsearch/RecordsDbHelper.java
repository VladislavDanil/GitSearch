package com.example.nitrogenium.githubsearch;

import java.util.HashMap;

import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * class writes and reads from the database
 */
public class RecordsDbHelper {

    //������������ ������� � ������� - ������
    public static final String KEY_DATA = SearchManager.SUGGEST_COLUMN_TEXT_1;

    private static final String TAG = "RecordsDbHelper";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "datas";
    private static final String DATABASE_TABLE = "records";
    private static final int DATABASE_VERSION = 2;

    //�������� �������� ��
    private static final String DATABASE_CREATE =
            "CREATE VIRTUAL TABLE " + DATABASE_TABLE +
                    " USING fts3 (" + KEY_DATA + ");";

    private static final HashMap<String, String> mColumnMap = buildColumnMap();

    /**
     * Returns the cursor that points to a record rowId
     *
     * @param rowId   id the returned record
     * @param columns columns returned records; if null, the all
     * @return cursor pointing to a specific entry, null - if no entry is found
     */
    public Cursor getRecord(String rowId, String[] columns) {
        String selection = "rowid = ?";
        String[] selectionArgs = new String[]{rowId};

        return query(selection, selectionArgs, columns);
    }

    /**
     * returns the cursor that points to all the records that match the query
     *
     * @param query   text search query
     * @param columns columns returned records; if null, the all
     * @return cursor indicating the records that match the query, null - if no record is found
     */
    public Cursor getRecordMatches(String query, String[] columns) {
        String selection = KEY_DATA + " MATCH ?";
        String[] selectionArgs = new String[]{query + "*"};

        return query(selection, selectionArgs, columns);
    }

    /**
     * It creates a display of all kinds of requested columns.
     * To be set as a projection in SQLiteQueryBuilder.
     * It is necessary to assign each entry a unique value SUGGEST_COLUMN_INTENT_DATA_ID
     * Are used to obtain specific recording URI.
     */
    private static HashMap<String, String> buildColumnMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(KEY_DATA, KEY_DATA);
        map.put(BaseColumns._ID, "rowid AS " +
                BaseColumns._ID);
        map.put(SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID, "rowid AS " +
                SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID);
        return map;
    }

    /**
     * @param selection     operator sampling
     * @param selectionArgs arguments replace "?" the query to the database
     * @param columns       columns returned record
     * @return cursor pointing to all records that match the search query
     */
    private Cursor query(String selection, String[] selectionArgs, String[] columns) {
        /* SQLiteBuilder provides an opportunity to create a mapping for all
          * Required database column that allows you to not report a content provider
          * Real names columns.
         */

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(DATABASE_TABLE);
        builder.setProjectionMap(mColumnMap);

        Cursor cursor = builder.query(mDbHelper.getReadableDatabase(),
                columns, selection, selectionArgs, null, null, null);
        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }

    /**
     * Create / open database
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS records");
            onCreate(db);
        }
    }

    public void dropBase(){
        mDb = mDbHelper.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        mDb.execSQL("DROP TABLE IF EXISTS records");
        mDb.execSQL(DATABASE_CREATE);
    }
    public RecordsDbHelper(Context context) {
        mDbHelper = new DatabaseHelper(context);
    }

    /**
     * Adds an entry to the table
     *
     * @param data data stored in the table
     * @return id entry, or -1 if the addition failed
     */
    public long createRecord(String data) {
        mDb = mDbHelper.getWritableDatabase();
        mDb = mDbHelper.getReadableDatabase();
        ContentValues initialValues = new ContentValues();
        String selection = "suggest_text_1 LIKE ?";
        String[] selectionArgs = new String[] { data };
        Cursor cursor = mDb.query(DATABASE_TABLE, null, selection, selectionArgs, null, null, null );
        if(cursor.getCount()==0) {
            initialValues.put(KEY_DATA, data);
            return mDb.insertWithOnConflict(DATABASE_TABLE, null, initialValues, SQLiteDatabase.CONFLICT_REPLACE);
        }
        return 0;
    }

}