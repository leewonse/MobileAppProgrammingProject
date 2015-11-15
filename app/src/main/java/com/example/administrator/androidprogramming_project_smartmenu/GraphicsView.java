package com.example.administrator.androidprogramming_project_smartmenu;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.MainThread;
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
        private Paint startline;
        private Paint scorePaint;
        Context mcontext;

        ArrayList<brick> block = new ArrayList<brick>();

        private int score=0;
        static private GraphicsView singleton = null;

        boolean updown=false;
        boolean shoot_Ready=false;
        boolean addBlock=true;

        float upPositionX;
        float upPositionY;
        float downPositionX;
        float downPositionY;
        float movePositionX;
        float movePositionY;

        int height=150;

        public GraphicsView(Context context){
            super(context);
            this.mcontext = context;
            ballBounds = new RectF();
            circlePaint = new Paint();
            startline = new Paint();
            scorePaint = new Paint();

            circlePaint.setTypeface(Typeface.MONOSPACE);
            circlePaint.setTextSize(10);
            this.setFocusableInTouchMode(true);
        }

        public static GraphicsView getGraphicsView(){return singleton;}
        public GraphicsView(Context context, AttributeSet attrs) {this(context);}
        public GraphicsView(Context context,AttributeSet attrs, int defStyle) {super(context);}

        @Override
        protected void onDraw(Canvas canvas){
            scorePaint.setTextSize(70);
            canvas.drawText("최고점수",150,1470,scorePaint);
            canvas.drawText("점    수",710,1470,scorePaint);
            scorePaint.setTextSize(70);
            if(score>=10){
            canvas.drawText(score+"",780,1670,scorePaint);}
            else{canvas.drawText(score+"",790,1670,scorePaint);}
            canvas.drawLine(0, 1344, 1079, 1344, startline);
            ballBounds.set(ballX - ballRadius, ballY - ballRadius, ballX + ballRadius, ballY + ballRadius);
            circlePaint.setColor(Color.BLACK);
            canvas.drawOval(ballBounds, circlePaint);

            if(ballSpeedX==0 && ballSpeedY==0){
                if(addBlock==true) {
                    addBlock();
                }

                if(ballY>=1314){
                    ballY=1314;
                }
                if(updown==true){
                    Paint dotLine = new Paint();
                    DashPathEffect dashPath = new DashPathEffect(new float[]{20,10}, 1);

                    dotLine.setStyle( Paint.Style.STROKE );
                    dotLine.setPathEffect(dashPath);
                    dotLine.setStrokeWidth(5);
                    canvas.drawLine(downPositionX,downPositionY,movePositionX,movePositionY,dotLine);
                }
                if(shoot_Ready==true){
                    ballSpeedX = (float)((upPositionX-downPositionX)/Math.sqrt(Math.pow((Math.pow(upPositionX,2)-Math.pow(downPositionX,2)),2)+Math.pow((Math.pow(upPositionY,2)-Math.pow(downPositionY,2)),2)))*30000;
                    ballSpeedY = (float)((upPositionY-downPositionY)/Math.sqrt(Math.pow((Math.pow(upPositionX,2)-Math.pow(downPositionX,2)),2)+Math.pow((Math.pow(upPositionY,2)-Math.pow(downPositionY,2)),2)))*30000;
                    shoot_Ready=false;
                }
            }

            for(int i=0; i<block.size();i++){
                block.get(i).draw(canvas);
            }

            update();
            collisionDetection();
            invalidate();
        }

    private void collisionDetection(){
        for(int i=0;i<block.size();i++){
            if((ballX+ballRadius<=block.get(i).rectX+20) && (ballY>=block.get(i).rectY && ballY<=block.get(i).rectY+block.get(i).height)){
                if((ballX+ballRadius >= block.get(i).rectX && ballX+ballRadius<=block.get(i).rectX+20) && (ballY>=block.get(i).rectY && ballY<=block.get(i).rectY+block.get(i).height)) {       //왼쪽 충돌
                    ballSpeedX = -ballSpeedX;
                    ballX = block.get(i).rectX-ballRadius;
                    afterCollision(i);
                }
            }
            else if((ballX-ballRadius >= block.get(i).rectX + block.get(i).width-20) && (ballY>=block.get(i).rectY && ballY<=block.get(i).rectY+block.get(i).height)){
                if((ballX-ballRadius >= block.get(i).rectX + block.get(i).width-20 && ballX-ballRadius <= block.get(i).rectX + block.get(i).width) && (ballY>=block.get(i).rectY && ballY<=block.get(i).rectY+block.get(i).height)){    //오른쪽 충돌
                    ballSpeedX = -ballSpeedX;
                    ballX = block.get(i).rectX + block.get(i).width + ballRadius;
                    afterCollision(i);
                }
            }
            else if((ballX >= block.get(i).rectX && ballX <=block.get(i).rectX+block.get(i).width) && (ballY+ballRadius <= block.get(i).rectY+20)){
                if((ballX >= block.get(i).rectX && ballX <=block.get(i).rectX+block.get(i).width) && (ballY+ballRadius <= block.get(i).rectY+20 && ballY+ballRadius >= block.get(i).rectY)){     //윗쪽 충돌
                    ballSpeedY = -ballSpeedY;
                    ballY = block.get(i).rectY - ballRadius;
                    afterCollision(i);
                }
            }
            else if((ballX >= block.get(i).rectX && ballX <=block.get(i).rectX+block.get(i).width) && (ballY-ballRadius >= block.get(i).rectY + block.get(i).height - 20)){
                if((ballX >= block.get(i).rectX && ballX <=block.get(i).rectX+block.get(i).width) && (ballY-ballRadius>=block.get(i).rectY+block.get(i).height-20 && ballY-ballRadius<=block.get(i).rectY+block.get(i).height)){     //아래쪽 충돌
                    ballSpeedY = -ballSpeedY;
                    ballY = block.get(i).rectY+block.get(i).height + ballRadius;
                    afterCollision(i);
                }
            }
            else if(ballX<block.get(i).rectX && ballY<block.get(i).rectY){
                if(Math.pow(block.get(i).rectX-ballX,2)+Math.pow(block.get(i).rectY-ballY,2)<=900){        //2사분면
                ballSpeedY = -ballSpeedY;
                ballSpeedX = -ballSpeedX;
                afterCollision(i);
            }}
            else if(ballX>block.get(i).rectX + block.get(i).width && ballY<block.get(i).rectY){
                if(Math.pow(block.get(i).rectX + block.get(i).width - ballX,2)+Math.pow(block.get(i).rectY - ballY,2)<=900){       //1사분면
                ballSpeedY = -ballSpeedY;
                ballSpeedX = -ballSpeedX;
                afterCollision(i);
            }}
            else if(ballX<block.get(i).rectX && ballY>block.get(i).rectY+block.get(i).height){
                if(Math.pow(block.get(i).rectX-ballX,2)+Math.pow(block.get(i).rectY+block.get(i).height-ballY,2)<=900){          //3사분면
                ballSpeedY = -ballSpeedY;
                ballSpeedX = -ballSpeedX;
                afterCollision(i);
            }}
            else if(ballX>block.get(i).rectX + block.get(i).width && ballY>block.get(i).rectY+block.get(i).height){
                if(Math.pow(block.get(i).rectX + block.get(i).width - ballX,2)+Math.pow(block.get(i).rectY + block.get(i).height-ballY,2)<=900){       //4사분면
                ballSpeedY = -ballSpeedY;
                ballSpeedX = -ballSpeedX;
                afterCollision(i);
            }}
        }
    }

    public void afterCollision(int number){
        if(block.get(number).getColorNumber()==0 || block.get(number).getColorNumber()==1){
            block.remove(number);
            score++;
        }
        else{
            block.get(number).reduceColor();
        }
    }

    public void addBlock(){
            int random = (int)(Math.random()*3+1);
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
                block.add(new brick(location.get(i), height));
            }
            addBlock=false;
        }

        public void pushBlock(){
            for(int i=0; i<block.size();i++){
            block.get(i).addHeight();
            block.get(i).setColor();

                if(block.get(i).getHeight()>=1344){

                    Intent intent = new Intent(mcontext, GameOver.class);
                    if (mcontext instanceof Activity) {
                        intent.putExtra("score",score);
                        mcontext.startActivity(intent);
                        ((MainActivity) mcontext).finish();
                    }
                }
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
            if(ballY+530>yMax){
                addBlock=true;
                if(ballY+530>=yMax){
                    ballSpeedX=0;
                    ballSpeedY=0;
                }
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