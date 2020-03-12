package edu.fsu.cs.hw5;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    public static final int DBVERSION = 1;
    public final static String DBNAME = "HW5DB";
    public final static String TABLE_NAMESTABLE = "EmployeeTable";

    @Override
    public boolean onCreate() {
        // TODO: Create Database
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: insert Employee information
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: update Employee information
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO: delete Employee information
        return 0;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: query Employee by selection
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }
}
