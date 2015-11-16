package com.example.administrator.androidprogramming_project_smartmenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Administrator on 2015-11-15.
 */
public class GameLotto extends Activity {


    Button lottobutton;
    float random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto);
        lottobutton = (Button)findViewById(R.id.lottobutton);
        lottobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                random = (float) Math.random() * 100;
                AlertDialog dialog = createDialogBox();
                if (random < 3) {
                    dialog.show();
                } else if (random >= 3 && random < 10) {
                    dialog.show();
                } else if (random >= 10 && random < 25) {
                    dialog.show();
                } else if (random >= 25 && random < 50) {
                    dialog.show();
                } else {
                    dialog.show();
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

    private AlertDialog createDialogBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Lotto Result");
        if (random < 3) {
            builder.setMessage("1등 화폐 +200!! 축하합니다!");
        } else if (random >= 3 && random < 10) {
            builder.setMessage("2등 화폐 +100!! 축하합니다!");
        } else if (random >= 10 && random < 25) {
            builder.setMessage("3등 화폐 +50!!");
        } else if (random >= 25 && random < 50) {
            builder.setMessage("4등 화폐 +20");
        } else {
            builder.setMessage("꽝... 아쉽네요.. 다시 도전하세요!");
        }
        builder.setIcon(android.R.drawable.btn_star);

        builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();

        return dialog;
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
