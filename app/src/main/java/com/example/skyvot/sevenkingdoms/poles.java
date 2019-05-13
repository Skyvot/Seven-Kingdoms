package com.example.skyvot.sevenkingdoms;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by skyvot on 2017/7/7.
 */

public class poles {
    public boolean layer1,layer2,layer3;
    public int positionx,positiony;

    public poles(){
        layer1=false;
        layer2=false;
        layer3=false;
    }
    public poles(int x,int y)
    {
        positionx=x;
        positiony=y;
        layer1=false;
        layer2=false;
        layer3=false;
    }
    public void draw(Canvas canvas, Paint paint)
    {
        if(layer3 == true)
        {
            paint.setARGB(255, 243 ,204, 19);
            canvas.drawCircle(positionx,positiony, 161, paint);
            paint.setARGB(255, 53 , 59 , 64);
            canvas.drawCircle(positionx,positiony, 136, paint);
        }
        if(layer2 == true)
        {
            paint.setARGB(255, 165 , 221, 248);
            canvas.drawCircle(positionx,positiony, 111, paint);
            paint.setARGB(255, 53 , 59, 64);
            canvas.drawCircle(positionx,positiony,86, paint);
        }
        if(layer1 == true)
        {
            paint.setARGB(255,225, 71, 70);
            canvas.drawCircle(positionx,positiony, 61, paint);
            paint.setARGB(255, 53 ,59, 64);
            canvas.drawCircle(positionx,positiony, 41, paint);
        }


    }
    public void drawdark(Canvas canvas, Paint paint)
    {
        if(layer3 == true)
        {
            paint.setARGB(255, 243 ,204, 19);
            canvas.drawCircle(positionx,positiony, 161, paint);
            paint.setARGB(255, 10 , 10 , 10);
            canvas.drawCircle(positionx,positiony, 136, paint);
        }
        if(layer2 == true)
        {
            paint.setARGB(255, 165 , 221, 248);
            canvas.drawCircle(positionx,positiony, 111, paint);
            paint.setARGB(255, 10 , 10 , 10);
            canvas.drawCircle(positionx,positiony,86, paint);
        }
        if(layer1 == true)
        {
            paint.setARGB(255, 225, 71, 70);
            canvas.drawCircle(positionx,positiony, 61, paint);
            paint.setARGB(255, 10 , 10 , 10);
            canvas.drawCircle(positionx,positiony, 41, paint);
        }


    }
    public void drawpole(Canvas canvas,Paint paint)
    {
        //绘制柱子
        paint.setARGB(255, 188, 171, 171);
        canvas.drawCircle( positionx, positiony, 18, paint);
    }
}
