package com.example.administrator.androidprogramming_project_smartmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2015-11-16.
 */
public class MenuChoose extends Activity {

    Button choosebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        choosebutton = (Button)findViewById(R.id.chooseButton);
        choosebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Go_add = new Intent(getApplicationContext(), MenuEnroll.class);
                startActivity(Go_add);
            }
        });
        choosebutton.setOnTouchListener(new View.OnTouchListener() { //버튼 터치시 이벤트
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) // 버튼을 누르고 있을 때
                    choosebutton.setBackgroundResource(R.drawable.choosemenudown);
                if (event.getAction() == MotionEvent.ACTION_UP) { //버튼에서 손을 떼었을 때
                    choosebutton.setBackgroundResource(R.drawable.choosemenuup);
                }
                return false;
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
