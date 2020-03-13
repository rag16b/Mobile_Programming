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
    public final static String DBNAME = "EmployeeTable";
    public final static String TABLE_NAMESTABLE = "EmployeeTable";
    public final static String COLUMN_EMPID = "Empid";
    public final static String COLUMN_NAME = "Name";
    public final static String COLUMN_EMAIL = "Email";
    public final static String COLUMN_GENDER = "Gender";
    public final static String COLUMN_ACCESS_CODE = "AccessCode";
    public final static String COLUMN_DEPARTMENT = "Department";

    private static final String SQL_CREATE_MAIN =
            "CREATE TABLE EmployeeTable ( " +
                    " _ID INTEGER PRIMARY KEY, " +
                    "Empid TEXT, " +
                    "Name TEXT, " +
                    "Email TEXT, " +
                    "Gender TEXT, " +
                    "AccessCode TEXT," +
                    "Department TEXT" +
                    ")";
    public static final Uri CONTENT_URI = Uri.parse("content://edu.fsu.cs.hw5.provider/" + TABLE_NAMESTABLE);

    protected static final class MainDatabaseHelper
            extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context) {
            super(context, DBNAME, null, DBVERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_MAIN);
        }
        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            // NOT SURE WHAT WOULD GO HERE
        }
    }

    MainDatabaseHelper mOpenHelper;

    @Override
    public boolean onCreate() {
        mOpenHelper = new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String empid = values.getAsString("Empid");
        String name = values.getAsString("Name");
        String email = values.getAsString("Email");
        String gender = values.getAsString("Gender");
        String aCode = values.getAsString("AccessCode");
        String department = values.getAsString("Department");

        long id = mOpenHelper
                .getWritableDatabase()
                .insert("EmployeeTable", null, values);
        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().update(TABLE_NAMESTABLE, values, selection, selectionArgs);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().delete(TABLE_NAMESTABLE, selection, selectionArgs);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return mOpenHelper.getReadableDatabase().query(TABLE_NAMESTABLE, projection, selection, selectionArgs,
                null, null, sortOrder);
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }
}
