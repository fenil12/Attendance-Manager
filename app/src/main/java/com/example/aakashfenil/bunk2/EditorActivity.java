package com.example.aakashfenil.bunk2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aakashfenil.bunk2.data.SubjectContract.SubjectEntry;
import com.example.aakashfenil.bunk2.data.SubjectDbHelper;

public class EditorActivity extends AppCompatActivity {

    private EditText mNameEditText;

    private EditText mAttendedEditText;

    private EditText mBunkedEditText;

    private EditText mFreeEditText;

    private EditText mTotalEditText;

    private EditText mMinPercentageText;

    private int attend=0;
    private int bunk=0;
    private int free=0;
    private int total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mNameEditText = (EditText) findViewById(R.id.edit_Subject_name);
        mAttendedEditText = (EditText) findViewById(R.id.edit_Subject_attended);
        mBunkedEditText = (EditText) findViewById(R.id.edit_Subject_bunked);
        mFreeEditText = (EditText) findViewById(R.id.edit_Subject_free);
        mTotalEditText = (EditText) findViewById(R.id.edit_Subject_total);
        mMinPercentageText =(EditText) findViewById(R.id.edit_Subject_min_percentage);

    }


    private void insertSubject(){
        String nameString= mNameEditText.getText().toString().trim();
        int attend =Integer.parseInt(mAttendedEditText.getText().toString().trim());
        int bunk =Integer.parseInt(mBunkedEditText.getText().toString().trim());
        int free =Integer.parseInt(mFreeEditText.getText().toString().trim());
        int total =Integer.parseInt(mTotalEditText.getText().toString().trim());
        double minpercentage=Double.parseDouble(mMinPercentageText.getText().toString().trim());

        SubjectDbHelper mDbHelper = new SubjectDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values =new ContentValues();

        values.put(SubjectEntry.COLUMN_Subject_NAME,nameString);
        values.put(SubjectEntry.COLUMN_Attended_Lectures,attend);
        values.put(SubjectEntry.COLUMN_Bunked_lectures,bunk);
        values.put(SubjectEntry.COLUMN_Free_Lectures,free);
        values.put(SubjectEntry.COLUMN_Total_Lectures,total);
        values.put(SubjectEntry.COLUMN_MIN_PERCENTAGE,minpercentage);

        long newRowId = db.insert(SubjectEntry.TABLE_NAME,null,values);

        if(newRowId ==-1) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"pet rows:" + newRowId,Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Do nothing for now
                insertSubject();
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
