package com.example.skyvot.sevenkingdoms;

/**
 * Created by skyvot on 2017/7/7.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
public class GameMenu {
    private Bitmap title;
    private Bitmap start;
    private Bitmap help;
    private int btnHx,btnHy,btnSx,btnSy,btnTx,btnTy;
    public GameMenu(Bitmap title, Bitmap start, Bitmap help)
    {
        this.help = help;
        this.start = start;
        this.title = title;
        btnSx = MySurfaceView.screenW/2 - (start.getWidth()/2);
        btnSy = MySurfaceView.screenH/2 + start.getHeight();
        btnHx = MySurfaceView.screenW/2 - help.getWidth()/2;
        btnHy = MySurfaceView.screenH/2 + 3*help.getHeight();
        btnTx = MySurfaceView.screenW/2 - title.getWidth()/2;
        btnTy = MySurfaceView.screenH/2 - title.getHeight();
  /*      btnSx = 200;
        btnSy = 800;
        btnHx = 200;
        btnHy = 1200;
        btnTx = 100;
        btnTy = 400;*/
    }
    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawColor(Color.rgb(53,59,64));
        paint.setColor(Color.GRAY);
        paint.setTextSize(130);
        canvas.drawText("SevenKingdoms",btnTx,btnTy+100,paint);
        paint.setTextSize(100);
        canvas.drawText("Infinite mod",btnHx-100,btnHy+50,paint);
        canvas.drawText("Start game",btnSx-100,btnSy+50,paint);
    }
    public void onTouchEvent(MotionEvent event)
    {
        int pointX = (int) event.getX();
        int pointY = (int) event.getY();
        if(event.getAction() == MotionEvent.ACTION_UP)
        {
            if(pointX<btnSx + 400 && pointX>btnSx - 100)
            {
                if(pointY<btnSy + 100 && pointY>btnSy - 50)
                {
                       MySurfaceView.gameState = MySurfaceView.GAMEING;
                }
            }
            if(pointX<btnHx+help.getWidth()+200 && pointX>btnHx)
            {
                if(pointY<btnHy+help.getHeight() && pointY>btnHy)
                {
                       MySurfaceView.gameState = MySurfaceView.GAME_INF;
                }
            }

        }

    }
}