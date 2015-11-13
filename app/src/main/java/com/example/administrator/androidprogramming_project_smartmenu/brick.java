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
    private float rectX;
    private float rectY;
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
    }

    public int getColorNumber() {
        return colorNumber;
    }

    public void addHeight() {
        rectY += 150;
    }

    public void moveWithCollisionDetection(float BallX, float BallY) {
        //if ((Math.pow(afP[0] - spur_Radius - rectbug.get(j).x, 2) + Math.pow(afP[1] - rectbug.get(j).y, 2)) < Math.pow(spur_Radius, 2)) {
        //    rectbug.remove(j);
        //    rectNumber--;
        //    if (spur_Radius <= 150) {
        //        spur_Radius += 25;
        //}
        //}

    }
}