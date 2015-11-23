package com.example.administrator.androidprogramming_project_smartmenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2015-11-16.
 */
public class MenuChoose extends Activity {

    Button choosebutton;
    TextView view_location;
    String location;
    TextView moneyView;

    boolean cango=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        moneyDBManager moneydbmanager = new moneyDBManager(getApplicationContext(), "money.db", null, 1);
        SQLiteDatabase moneydb = moneydbmanager.getReadableDatabase();
        Cursor moneycursor = moneydb.rawQuery("select * from MONEY_LIST", null);

        if(moneycursor.moveToFirst()==false){
            moneydbmanager.insert("insert into MONEY_LIST values(null, " + 0 + ");");
        }


        if(moneycursor.moveToFirst()) {
            int money = moneycursor.getInt(1);

            moneyView = (TextView) findViewById(R.id.moneyView);
            moneyView.setText(money+"");
        }

        choosebutton = (Button)findViewById(R.id.chooseButton);
        choosebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneyDBManager moneydbmanager = new moneyDBManager(v.getContext(), "money.db", null, 1);
                SQLiteDatabase moneydb = moneydbmanager.getReadableDatabase();
                Cursor moneycursor = moneydb.rawQuery("select * from MONEY_LIST", null);

                moneyDBManager storedbmanager = new moneyDBManager(v.getContext(), "store.db", null, 1);
                SQLiteDatabase storedb = storedbmanager.getReadableDatabase();
                Cursor storecursor = storedb.rawQuery("select * from STORE_LIST", null);

                if(moneycursor.moveToFirst()) {
                    int old_money = moneycursor.getInt(1);

                    if(old_money>=20) {
                        int subtract_money= old_money;
                        moneydbmanager.update("update MONEY_LIST set money = " + subtract_money + " where money = " + old_money + ";");

                        view_location = (TextView)findViewById(R.id.get_location);
                        location = view_location.getText().toString();

                        while(storecursor.moveToNext()){
                            if(location.equals(storecursor.getString(5).toString())){
                                cango=true;
                                Intent Go_Choice = new Intent(getApplicationContext(), TodayMenu.class);
                                Go_Choice.putExtra("location", location);
                                Go_Choice.setFlags(Go_Choice.FLAG_ACTIVITY_SINGLE_TOP | Go_Choice.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(Go_Choice);
                                finish();
                            }
                        }
                        if(cango==false){
                        moneydbmanager.update("update MONEY_LIST set money = " + old_money + " where money = " + subtract_money + ";");
                        AlertDialog dialog = createBox();
                        dialog.show();
                        }
                    }
                    else{
                        AlertDialog cancle_dialog = createCancelBox();
                        cancle_dialog.show();
                    }
                }
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

    private AlertDialog createBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Button Click Result");
        builder.setMessage("등록된 지역이 아니에요..." + "\n" + "가게를 등록해주세요!!");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();

        return dialog;
    }

    private AlertDialog createCancelBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Button Click Result");
        builder.setMessage("화폐 20이 없어요."+"\n"+"게임을 통해 벌어보세요!");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

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
