package com.example.skyvot.sevenkingdoms;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

/**
 * Created by skyvot on 2017/7/11.
 */

public class WinView {

    private Bitmap restart;
    private Bitmap youwin;
    private int btnRx,btnRy;

    public WinView(Bitmap restart, Bitmap youwin) {
        this.restart = restart;
        this.youwin = youwin;
        btnRx = (MySurfaceView.screenW-restart.getWidth())/2;
        btnRy = (MySurfaceView.screenH/2+restart.getHeight());
    }
    public void draw(Canvas canvas , Paint paint) {
        canvas.drawColor(Color.rgb(53,59,64));
        paint.setTextSize(60);
        String score = "Your Score is ";
        String cont = "Winning Award : score + 200 !";
        String scores = "" + MySurfaceView.gamescore;
        score += scores;
        canvas.drawText(cont, 100 , 500, paint);
        canvas.drawText(score, 100, 700, paint);
        canvas.drawText("Congratulations! You Win!", 100 , 300, paint);
        paint.setTextSize(100);
        canvas.drawText("Play Again!", (MySurfaceView.screenW-restart.getWidth())/2, 1200, paint);
    }
    public void onTouchEvent(MotionEvent event)
    {
        int pointX = (int) event.getX();
        int pointY = (int) event.getY();

        if(event.getAction() == MotionEvent.ACTION_UP)
        {
            if(pointX< btnRx+restart.getWidth() && pointX>btnRx)
                if (pointY < btnRy + restart.getHeight() && pointY > btnRy) {
                    MySurfaceView.resetgame = true;
                    MySurfaceView.resetgameinf = true;
                    MySurfaceView.gameState = MySurfaceView.GAME_MENU;
                }
        }
    }
}
