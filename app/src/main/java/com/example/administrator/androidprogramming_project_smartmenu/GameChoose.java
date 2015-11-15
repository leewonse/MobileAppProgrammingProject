package com.example.administrator.androidprogramming_project_smartmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Administrator on 2015-11-15.
 */
public class GameChoose extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main);

        findViewById(R.id.goblockgame).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Go_blockgame = new Intent(getApplicationContext(), GameStart.class);
                startActivity(Go_blockgame);
            }
        });

        findViewById(R.id.golottogame).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Go_lottogame = new Intent(getApplicationContext(),GameLotto.class);
                startActivity(Go_lottogame);
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
