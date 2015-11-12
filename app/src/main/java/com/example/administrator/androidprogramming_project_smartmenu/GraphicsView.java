package com.example.administrator.androidprogramming_project_smartmenu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

public class GraphicsView extends View{
        private int xMin=0;
        private int xMax;
        private int yMin =0;
        private int yMax;
        private float ballRadius = 30;
        private float ballX=539;
        private float ballY=1314;
        private float ballSpeedX = 0;
        private float ballSpeedY = 0;
        private RectF ballBounds;
        private Paint circlePaint;
        private Paint blockPaint;
        private Paint startline;
        Context context;

        ArrayList<brick> block = new ArrayList<brick>();


        static private GraphicsView singleton = null;

        boolean updown=false;
        boolean shoot_Ready=false;
        float upPositionX;
        float upPositionY;
        float downPositionX;
        float downPositionY;
        float movePositionX;
        float movePositionY;

        int height=150;

    private Integer[] colorArray={Color.parseColor("#FFFFE004"), Color.parseColor("#FFFFC800"), Color.parseColor("#FFFFB100"), Color.parseColor("#FFFF8D00"),
                                    Color.parseColor("#FFFF6F00"), Color.parseColor("#FFFF5400"), Color.parseColor("#FFFF0100")};

        public GraphicsView(Context context){
            super(context);
            this.context = context;
            ballBounds = new RectF();
            circlePaint = new Paint();
            startline = new Paint();
            blockPaint = new Paint();
            circlePaint.setTypeface(Typeface.MONOSPACE);
            circlePaint.setTextSize(10);
            this.setFocusableInTouchMode(true);
        }

        public static GraphicsView getGraphicsView(){return singleton;}
        public GraphicsView(Context context, AttributeSet attrs) {
        this(context);
    }
        public GraphicsView(Context context,AttributeSet attrs, int defStyle) {
        super(context);
    }

        @Override
        protected void onDraw(Canvas canvas){
            canvas.drawLine(0, 1344, 1079, 1344, startline);
            ballBounds.set(ballX - ballRadius, ballY - ballRadius, ballX + ballRadius, ballY + ballRadius);
            circlePaint.setColor(Color.BLACK);
            canvas.drawOval(ballBounds, circlePaint);

            if(ballSpeedX==0 && ballSpeedY==0){

                addBlock();
                for(int i=0; i<block.size();i++){
                    block.get(i).draw(canvas);
                }

                if(ballY>=1314){
                    ballY=1314;
                }
                if(updown==true){
                    Paint dotLine = new Paint();
                    DashPathEffect dashPath = new DashPathEffect(new float[]{8,3}, 2);

                    dotLine.setStyle( Paint.Style.STROKE );
                    dotLine.setPathEffect(dashPath);
                    dotLine.setStrokeWidth(3);
                    canvas.drawLine(downPositionX,downPositionY,movePositionX,movePositionY,dotLine);
                }
                if(shoot_Ready==true){
                    ballSpeedX = (float)((upPositionX-downPositionX)/Math.sqrt(Math.pow((Math.pow(upPositionX,2)-Math.pow(downPositionX,2)),2)+Math.pow((Math.pow(upPositionY,2)-Math.pow(downPositionY,2)),2)))*70000;
                    ballSpeedY = (float)((upPositionY-downPositionY)/Math.sqrt(Math.pow((Math.pow(upPositionX,2)-Math.pow(downPositionX,2)),2)+Math.pow((Math.pow(upPositionY,2)-Math.pow(downPositionY,2)),2)))*70000;
                    shoot_Ready=false;
                }
            }

            update();
            invalidate();
        }

        public void addBlock(){
            int random = (int)(Math.random()*4+2);
            int r_remove;
            ArrayList<Integer> location = new ArrayList<Integer>();
            location.add(0);
            location.add(180);
            location.add(360);
            location.add(540);
            location.add(720);
            location.add(900);

            for(int i=0; i<6-random; i++) {
                r_remove=(int)(Math.random()*(6-i));
                location.remove(r_remove);
            }

            pushBlock();


            for(int i=0; i<random; i++) {
                block.add(new brick(colorArray[0], location.get(i), height));
            }
        }

        public void pushBlock(){
            for(int i=0; i<block.size();i++){
            block.get(i).addHeight();
            }
        }

        public void onSizeChanged(int w, int h, int oldW, int oldH){
            xMax = w-1;
            yMax = h-1;
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
                ballY = yMax-ballRadius;
            }else if(ballY-ballRadius < yMin){
                ballSpeedY = -ballSpeedY;
                ballY = yMin+ballRadius;
            }

            if(ballY+530>=yMax){
                ballSpeedX=0;
                ballSpeedY=0;

            }
        }

    public boolean onTouchEvent(MotionEvent event){


        switch(event.getAction()){
            case MotionEvent.ACTION_MOVE:
                movePositionX=event.getX();
                movePositionY=event.getY();
                break;
            case MotionEvent.ACTION_DOWN:
                downPositionX=event.getX();
                downPositionY=event.getY();
                updown=true;

                break;
            case MotionEvent.ACTION_UP:
                upPositionX=event.getX();
                upPositionY=event.getY();
                updown=false;
                shoot_Ready=true;
                break;
        }
        return true;
    }
}