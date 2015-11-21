package com.example.administrator.androidprogramming_project_smartmenu;

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
import android.widget.TextView;

/**
 * Created by Administrator on 2015-11-15.
 */
public class GameOver extends FragmentActivity {
        TextView scoreView;
        int BESTscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        scoreDBManager scoredbmanager = new scoreDBManager(getApplicationContext(), "score.db", null, 1);



        Intent intent = getIntent();
        int score = intent.getExtras().getInt("score");

        scoreView = (TextView)findViewById(R.id.scoreView);
        scoreView.setText(score+"");

        SQLiteDatabase db = scoredbmanager.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from SCORE_LIST", null);

        if(cursor.moveToLast()){
            if(cursor.getInt(1)<score) {
                BESTscore=score;
                scoredbmanager.insert("insert into SCORE_LIST values(null, " + BESTscore + ");");
            }
        }

        findViewById(R.id.returnButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Go_chooseActivity = new Intent(getApplicationContext(), GameChoose.class);
                Go_chooseActivity.setFlags(Go_chooseActivity.FLAG_ACTIVITY_SINGLE_TOP | Go_chooseActivity.FLAG_ACTIVITY_CLEAR_TOP);
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
