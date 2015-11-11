package com.example.administrator.androidprogramming_project_smartmenu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class GraphicsView extends View{
        private int xMin=0;
        private int xMax;
        private int yMin =0;
        private int yMax;
        private float ballRadius = 30;
        private float ballX=xMax/2;
        private float ballY=yMax-30;
        private float ballSpeedX = 0;
        private float ballSpeedY = 0;
        private RectF ballBounds;
        private Paint paint;
        Context context;


        public GraphicsView(Context context){
            super(context);
            this.context = context;
            ballBounds = new RectF();
            paint = new Paint();

            paint.setTypeface(Typeface.MONOSPACE);
            paint.setTextSize(10);

            this.setFocusableInTouchMode(true);
        }

        @Override
        protected void onDraw(Canvas canvas){
            ballBounds.set(ballX - ballRadius, ballY - ballRadius, ballX + ballRadius, ballY + ballRadius);
            paint.setColor(Color.BLACK);
            canvas.drawOval(ballBounds, paint);

            update();

            invalidate();
        }

        private void update() {
            ballX+=ballSpeedX;
            ballY+=ballSpeedY;

            if(ballX+ballRadius > xMax){
                ballSpeedX = -ballSpeedX;
                ballX = xMax -ballRadius;
            }else if(ballX-ballRadius < xMin){
                ballSpeedX = -ballSpeedX;
                ballX = xMin + ballRadius;
            }
            if(ballY+ballRadius > yMax){
                ballSpeedY = -ballSpeedY;
                ballY = yMax -ballRadius;
            }else if(ballY-ballRadius < yMin){
                ballSpeedY = -ballSpeedY;
                ballY = yMin + ballRadius;
            }

            if(ballY+30==yMax){
                ballSpeedX=0;
                ballSpeedY=0;
            }
        }

    public boolean onTouchEvent(MotionEvent event){
        float currentX;
        float currentY;
        float previousX;
        float previousY;

        switch(event.getAction()){
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_DOWN:
                previousX=event.getX();
                previousY=event.getY();
                break;
            case MotionEvent.ACTION_UP:
                currentX=event.getX();
                currentY=event.getY();
                break;
        }
        return true;
    }
}