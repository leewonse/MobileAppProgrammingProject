package com.example.administrator.androidprogramming_project_smartmenu;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015-11-23.
 */
public class TodayMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todaymenu);

        Intent intent = getIntent();
        String location = intent.getExtras().getString("location");

        storeDBManager storedbmanager = new storeDBManager(getApplicationContext(), "store.db", null, 1);
        SQLiteDatabase db = storedbmanager.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from STORE_LIST", null);
        ArrayList<SQLiteDatabase> data = new ArrayList<SQLiteDatabase>();
        while(cursor.moveToNext()){
            if(location == cursor.getString(5)){
                
            }


        }

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
