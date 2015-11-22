package com.example.administrator.androidprogramming_project_smartmenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

        moneyDBManager moneydbmanager = new moneyDBManager(getApplicationContext(), "money.db", null, 1);
        SQLiteDatabase moneydb = moneydbmanager.getReadableDatabase();
        Cursor moneycursor = moneydb.rawQuery("select * from MONEY_LIST", null);

        if(moneycursor.moveToFirst()==false){
            moneydbmanager.insert("insert into MONEY_LIST values(null, " + 0 + ");");
        }

        lottobutton = (Button)findViewById(R.id.lottobutton);
        lottobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                random = (float) Math.random() * 100;

                moneyDBManager moneydbmanager = new moneyDBManager(v.getContext(), "money.db", null, 1);
                SQLiteDatabase moneydb = moneydbmanager.getReadableDatabase();
                Cursor moneycursor = moneydb.rawQuery("select * from MONEY_LIST", null);

                if(moneycursor.moveToFirst()){
                    int old_money = moneycursor.getInt(1);

                    if(old_money>=20){
                        int subtract_money= old_money-20;
                        moneydbmanager.update("update MONEY_LIST set money = " + subtract_money + " where money = " + old_money + ";");
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
                    else if(old_money<20){
                        AlertDialog cancle_dialog = createCancelBox();
                            cancle_dialog.show();
                    }
                }
            }
        });

        findViewById(R.id.returnfromlottotochoose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Go_chooseActivity = new Intent(getApplicationContext(), GameChoose.class);
                Go_chooseActivity.setFlags(Go_chooseActivity.FLAG_ACTIVITY_SINGLE_TOP | Go_chooseActivity.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(Go_chooseActivity);
                finish();
            }
        });
    }

    private AlertDialog createDialogBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        moneyDBManager moneydbmanager = new moneyDBManager(getApplicationContext(), "money.db", null, 1);
        SQLiteDatabase moneydb = moneydbmanager.getReadableDatabase();
        Cursor moneycursor = moneydb.rawQuery("select * from MONEY_LIST", null);

        builder.setTitle("Lotto Result");
        if (random < 3) {
            int new_money=0;
            if(moneycursor.moveToFirst()) {
                int old_money = moneycursor.getInt(1);
                new_money= 200 + old_money;
                moneydbmanager.update("update MONEY_LIST set money = " + new_money + " where money = " + old_money + ";");
            }
            builder.setMessage("1등 화폐 +200!! 축하합니다!"+"\n"+"현재 화폐 : " + new_money);
        } else if (random >= 3 && random < 10) {
            int new_money = 0;
            if(moneycursor.moveToFirst()) {
                int old_money = moneycursor.getInt(1);
                new_money= 100 + old_money;
                moneydbmanager.update("update MONEY_LIST set money = " + new_money + " where money = " + old_money + ";");
            }
            builder.setMessage("2등 화폐 +100!! 축하합니다!"+"\n"+"현재 화폐 : " + new_money);
        } else if (random >= 10 && random < 25) {
                int new_money=0;
            if(moneycursor.moveToFirst()) {
                int old_money = moneycursor.getInt(1);
                new_money= 50 + old_money;
                moneydbmanager.update("update MONEY_LIST set money = " + new_money + " where money = " + old_money + ";");
            }
            builder.setMessage("3등 화폐 +50!!" + "\n" + "현재 화폐 : " + new_money);
        } else if (random >= 25 && random < 50) {
            int new_money=0;
            if(moneycursor.moveToFirst()) {
                int old_money = moneycursor.getInt(1);
                new_money= 20 + old_money;
                moneydbmanager.update("update MONEY_LIST set money = " + new_money + " where money = " + old_money + ";");
            }
            builder.setMessage("4등 화폐 +20"+"\n"+"현재 화폐 : " + new_money);

        } else {
            int new_money=0;
            if(moneycursor.moveToFirst()) {
                int old_money = moneycursor.getInt(1);
                new_money=  old_money;
                moneydbmanager.update("update MONEY_LIST set money = " + new_money + " where money = " + old_money + ";");
            }
            builder.setMessage("꽝... 아쉽네요.. 다시 도전하세요!"+"\n"+"현재 화폐 : " + new_money);
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

    private AlertDialog createCancelBox(){
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);

        builder2.setTitle("Button Click Result");
        builder2.setMessage("화폐 20이 없어요."+"\n"+"게임을 통해 벌어보세요!");
        builder2.setIcon(android.R.drawable.ic_dialog_alert);

        builder2.setNegativeButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        AlertDialog dialog2 = builder2.create();

        return dialog2;
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
