package com.example.aakashfenil.bunk2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aakashfenil.bunk2.data.SubjectContract.SubjectEntry;

import javax.security.auth.Subject;

/**
 * Created by Aakash&Fenil on 4/2/2017.
 */

public class SubjectDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = SubjectDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "shelter.db";


    public static final int DATABASE_VERSION = 2;

    public SubjectDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_Subject_TABLE = "CREATE TABLE" + SubjectEntry.TABLE_NAME + "("
                + SubjectEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT"
                + SubjectEntry.COLUMN_Subject_NAME + "TEXT NOT NULL"
                + SubjectEntry.COLUMN_Attended_Lectures + "INTEGER  DEFAULT 0"
                + SubjectEntry.COLUMN_Bunked_lectures + "INTEGER  DEFAULT 0"
                + SubjectEntry.COLUMN_Free_Lectures + "INTEGER  DEFAULT 0"
                + SubjectEntry.COLUMN_Total_Lectures + "INTEGER  DEFAULT 0"
                + SubjectEntry.COLUMN_MIN_PERCENTAGE + "REAL DEFAULT 0";

        db.execSQL(SQL_CREATE_Subject_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
