package com.example.administrator.androidprogramming_project_smartmenu;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.Log;

/**
 * Created by Administrator on 2015-11-12.
 */
public class brick {

    private RectF block;
    private Paint paint;
    public float rectX;
    public float rectY;
    public int hit_number = 0;

    private Integer[] colorArray = {Color.parseColor("#FFFFE004"), Color.parseColor("#FFFFC800"), Color.parseColor("#FFFFB100"), Color.parseColor("#FFFF8D00"),
            Color.parseColor("#FFFF6F00"), Color.parseColor("#FFFF5400"), Color.parseColor("#FFFF0100"), Color.parseColor("#4A030303")};

    private int colorNumber = 0;

    float width = 180;
    float height = 150;


    public brick(int x, int y) {
        paint = new Paint();
        block = new RectF();
        rectX = x;
        rectY = y;
    }

    public void draw(Canvas canvas) {


        paint.setStyle(Paint.Style.FILL);
        paint.setColor(colorArray[colorNumber]);
        block.set(rectX, rectY, rectX + width, rectY + height);
        canvas.drawRect(block, paint);

        paint.setTextSize(50);
        paint.setColor(Color.BLUE);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText((colorNumber + 1) + "", rectX + 90, rectY + 90, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2.0F);
        paint.setColor(Color.DKGRAY);
        canvas.drawRect(block, paint);

    }

    public void setColor() {
        colorNumber++;
    }

    public void reduceColor() {
        colorNumber--;
        colorNumber--;
        if(colorNumber<0){
            colorNumber=0;
        }
    }

    public int getColorNumber() {
        return colorNumber;
    }

    public float getHeight() {
        return rectY+height;
    }

    public void addHeight() {
        rectY += 150;
    }

}