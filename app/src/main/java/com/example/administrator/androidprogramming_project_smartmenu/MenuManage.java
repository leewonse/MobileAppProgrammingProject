package com.example.administrator.androidprogramming_project_smartmenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-11-15.
 */
public class MenuManage extends Activity {

    RecyclerView recyclerView;
    ArrayList<Recycler_item> items;
    public static Activity MMActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        MMActivity = MenuManage.this;
        storeDBManager storedbmanager = new storeDBManager(getApplicationContext(), "store.db", null, 1);

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Go_enroll = new Intent(getApplicationContext(), MenuEnroll.class);
                startActivity(Go_enroll);
                finish();
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        items=new ArrayList<>();

        SQLiteDatabase db = storedbmanager.getReadableDatabase();
        String str = "";
        Cursor cursor = db.rawQuery("select * from STORE_LIST", null);

        Log.d("id들 : ",storedbmanager.PrintData());
        while(cursor.moveToNext()) {
            if(cursor.getString(1)!="")
            items.add(new Recycler_item(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
        }

        recyclerView.setAdapter(new RecyclerViewAdapter(getApplicationContext(),items,R.layout.activity_manage));
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
