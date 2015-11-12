package com.example.administrator.androidprogramming_project_smartmenu;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;

/**
 * Created by Administrator on 2015-11-12.
 */
public class brick {

    private RectF block;
    private Paint paint;
    private float rectX;
    private float rectY;
    public int hit_number=0;

    float width=180;
    float height=150;


    public brick(int color, int x, int y){
        paint = new Paint();
        paint.setColor(color);

        block = new RectF();
        rectX=x;
        rectY=y;
    }

    public void draw(Canvas canvas){
        block.set(rectX, rectY, rectX+height, rectY+width);
        canvas.drawRect(block,paint);
    }

    public void addHeight(){
        rectY+=150;
    }

}
