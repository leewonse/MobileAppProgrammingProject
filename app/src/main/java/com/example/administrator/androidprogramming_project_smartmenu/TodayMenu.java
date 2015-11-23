package com.example.administrator.androidprogramming_project_smartmenu;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015-11-23.
 */
public class TodayMenu extends Activity {

    TextView viewstore;
    TextView viewmenu;
    TextView viewlower;
    TextView viewhigher;

    Button success;
    int choice;

    class data{
        String a;
        String b;
        String c;
        String d;
        data(String a, String b, String c, String d){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todaymenu);


        Intent intent = getIntent();
        String location = intent.getExtras().getString("location");

        viewstore = (TextView)findViewById(R.id.viewstore);
        viewmenu = (TextView)findViewById(R.id.viewmenu);
        viewlower = (TextView)findViewById(R.id.viewlower);
        viewhigher = (TextView)findViewById(R.id.viewhigher);

        storeDBManager storedbmanager = new storeDBManager(getApplicationContext(), "store.db", null, 1);
        SQLiteDatabase db = storedbmanager.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from STORE_LIST", null);
        ArrayList<data> arraylist = new ArrayList<>();

        arraylist.clear();

        while(cursor.moveToNext()){
            if(location.equals(cursor.getString(5))){
                arraylist.add(new data(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }
        }
        Log.d("111111111111",arraylist.size()+"");
        choice = (int)(Math.random()*arraylist.size());
        Log.d("222222222222",choice+"");
        viewstore.setText(arraylist.get(choice).a);
        viewmenu.setText(arraylist.get(choice).b);
        viewlower.setText(arraylist.get(choice).c);
        viewhigher.setText(arraylist.get(choice).d);

        success = (Button)findViewById(R.id.sss);
        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
