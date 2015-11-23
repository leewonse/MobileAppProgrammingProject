package com.example.administrator.androidprogramming_project_smartmenu;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2015-11-15.
 */
public class MenuEnroll extends Activity {

    String BD_store;
    String BD_menu;
    String BD_lower;
    String BD_higher;
    String BD_location;

    EditText editstorename;
    EditText editmenu;
    EditText editlower;
    EditText edithigher;
    EditText editlocation;

    Button savebutton;

    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        final storeDBManager storedbmanager = new storeDBManager(getApplicationContext(), "store.db", null, 1);

        editstorename = (EditText)findViewById(R.id.editstorename);
        editmenu = (EditText)findViewById(R.id.editmenu);
        editlower = (EditText)findViewById(R.id.editlowerprice);
        edithigher = (EditText)findViewById(R.id.edithigherprice);
        editlocation = (EditText)findViewById(R.id.get_location);

        savebutton = (Button)findViewById(R.id.saveButton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BD_store = editstorename.getText().toString();
                BD_menu = editmenu.getText().toString();
                BD_lower = editlower.getText().toString();
                BD_higher = edithigher.getText().toString();
                BD_location = editlocation.getText().toString();
                if(BD_store!="") {
                    storedbmanager.insert("insert into STORE_LIST values(null, '" + BD_store + "', '" + BD_menu + "',  '" + BD_lower + "',  '" + BD_higher + "','" + BD_location + "');");
                }
                finish();
                //Intent go_menumanage = new Intent();
                //startActivity(go_menumanage);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
