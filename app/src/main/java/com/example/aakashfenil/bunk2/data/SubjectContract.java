package com.example.aakashfenil.bunk2.data;

import android.provider.BaseColumns;

/**
 * Created by Aakash&Fenil on 4/2/2017.
 */




public final class SubjectContract {


    private SubjectContract() {}


    public static final class SubjectEntry implements BaseColumns {

        public final static String TABLE_NAME = "Subject";

        public final static String Subject_ID = BaseColumns._ID;
        public final static String COLUMN_Subject_NAME = "name";
        public final static String COLUMN_Attended_Lectures = "attended";
        public final static String COLUMN_Bunked_lectures = "bunked";
        public final static String COLUMN_Free_Lectures = "free";
        public final static String COLUMN_Total_Lectures ="total";
        public final static String COLUMN_MIN_PERCENTAGE ="min_percentage";
    }
}