package com.example.aakashfenil.bunk2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.aakashfenil.bunk2.data.SubjectContract.SubjectEntry;
import com.example.aakashfenil.bunk2.data.SubjectDbHelper;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    private SubjectDbHelper mDbHelper;
    BarChart barChart;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GraphActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });


        mDbHelper =new SubjectDbHelper(this);

       // displayDatabaseInfo();


        barChart = (BarChart) findViewById(R.id.bargraph);


        ArrayList<BarEntry> barEntries = new ArrayList<>();
        /*
        barEntries.add(new BarEntry(R.id.mc,0));
        barEntries.add(new BarEntry(R.id.dd,1));
        barEntries.add(new BarEntry(R.id.se,2));
        */
        barEntries.add(new BarEntry(80, 0));
        barEntries.add(new BarEntry(90, 1));
        barEntries.add(new BarEntry(72, 2));
        barEntries.add(new BarEntry(75, 3));
        barEntries.add(new BarEntry(75, 4));
        BarDataSet barDataSet = new BarDataSet(barEntries,"Subjects");

        ArrayList<String> subject = new ArrayList<>();

        subject.add("mcc");
        subject.add("dd");
        subject.add("se");
        subject.add("npl");
        subject.add("spcc");

        BarData theData = new BarData(subject,barDataSet);
        barChart.setData(theData);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);


    }

    protected void onStart(){
        super.onStart();
        displayDatabaseInfo();
    }
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        SubjectDbHelper mDbHelper = new SubjectDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
/*
        // Perform this raw SQL query "SELECT * FROM Subjects"
        // to get a Cursor that contains all rows from the Subjects table.
        Cursor cursor = db.rawQuery("SELECT * FROM " + SubjectEntry.TABLE_NAME, null);
*/
        String[] projection = {
                SubjectEntry.COLUMN_Subject_NAME,
                SubjectEntry.COLUMN_Attended_Lectures,
                SubjectEntry.COLUMN_Bunked_lectures,
                SubjectEntry.COLUMN_Free_Lectures,
                SubjectEntry.COLUMN_Total_Lectures,
                SubjectEntry.COLUMN_MIN_PERCENTAGE,
        };

        Cursor cursor= db.query(
                SubjectEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.text_view_subject);


        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // Subjects table in the database).
            displayView.setText("The subject table contains " + cursor.getCount() + " subject\n\n");
            displayView.append(SubjectEntry._ID + " - " + SubjectEntry.COLUMN_Subject_NAME + " - " + SubjectEntry.COLUMN_Attended_Lectures + " - " + SubjectEntry.COLUMN_Bunked_lectures + " - " + SubjectEntry.COLUMN_Free_Lectures + "-" + SubjectEntry.COLUMN_Total_Lectures + "-" + SubjectEntry.COLUMN_MIN_PERCENTAGE + "\n");

            int idColumnIndex = cursor.getColumnIndex(SubjectEntry._ID);
            int nameColumnIndex  = cursor.getColumnIndex(SubjectEntry.COLUMN_Subject_NAME);
            int attendColumnIndex = cursor.getColumnIndex(SubjectEntry.COLUMN_Attended_Lectures);
            int bunkColumnIndex = cursor.getColumnIndex(SubjectEntry.COLUMN_Bunked_lectures);
            int freeColumnIndex = cursor.getColumnIndex((SubjectEntry.COLUMN_Free_Lectures));
            int totalColumnIndex = cursor.getColumnIndex((SubjectEntry.COLUMN_Total_Lectures));
            int minpercentageColumnIndex = cursor.getColumnIndex((SubjectEntry.COLUMN_MIN_PERCENTAGE));

            while(cursor.moveToNext()){
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentAttend = cursor.getInt(attendColumnIndex);
                int currentBunk = cursor.getInt(bunkColumnIndex);
                int currentFree = cursor.getInt(freeColumnIndex);
                int currentTotal = cursor.getInt(totalColumnIndex);
                int currentMinPercentage= cursor.getInt(minpercentageColumnIndex);


                displayView.append(("\n" + currentID + " - " + currentName + " - " + currentAttend + " - " + currentBunk + " - " + currentFree+" - " + currentTotal+" - " + currentMinPercentage));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }










    private void insertSubject(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_graph, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertSubject();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
