package com.example.administrator.androidprogramming_project_smartmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by Administrator on 2015-11-15.
 */
public class GameLotto extends Activity {

    float random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto);

        findViewById(R.id.lottobutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                random = (float) Math.random() * 100;
                if (random < 5) {
                    Toast.makeText(getApplicationContext(), "1등 화폐 +200!! 축하합니다!", Toast.LENGTH_LONG).show();
                } else if (random >= 5 && random < 20) {
                    Toast.makeText(getApplicationContext(), "2등 화폐 +100!! 축하합니다!", Toast.LENGTH_LONG).show();
                } else if (random >= 20 && random < 40) {
                    Toast.makeText(getApplicationContext(), "3등 화폐 +50!!", Toast.LENGTH_LONG).show();
                } else if (random >= 40 && random < 70) {
                    Toast.makeText(getApplicationContext(), "4등 화폐 +20", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "꽝... 아쉽네요.. 다시 도전하세요!", Toast.LENGTH_LONG).show();
                }
            }
        });
        findViewById(R.id.returnfromlottotochoose).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent Go_chooseActivity = new Intent(getApplicationContext(),GameChoose.class);
                Go_chooseActivity.setFlags(Go_chooseActivity.FLAG_ACTIVITY_SINGLE_TOP|Go_chooseActivity.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(Go_chooseActivity);
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
