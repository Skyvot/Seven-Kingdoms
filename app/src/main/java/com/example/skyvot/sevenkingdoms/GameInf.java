package com.example.skyvot.sevenkingdoms;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.Random;

import static java.lang.Math.abs;

/**
 * Created by skyvot on 2017/7/11.
 */

public class GameInf {
    public int config = -1;
    public int origX = 0 ,origY = 0;
    private poles[] kings = new poles[7];
    public int points = 0;
    private int combo = 0;
    private String score = "Your Score is  ";

    public GameInf() {
        kings[0]=new poles(MySurfaceView.screenW/2,MySurfaceView.screenH/2 - 100);
        kings[1]=new poles(MySurfaceView.screenW/2+346,MySurfaceView.screenH/2+100);
        kings[2]=new poles(MySurfaceView.screenW/2+346,MySurfaceView.screenH/2+500);
        kings[3]=new poles(MySurfaceView.screenW/2,MySurfaceView.screenH/2+700);
        kings[4]=new poles(MySurfaceView.screenW/2-346,MySurfaceView.screenH/2+500);
        kings[5]=new poles(MySurfaceView.screenW/2-346,MySurfaceView.screenH/2+100);
        kings[6]=new poles(MySurfaceView.screenW/2,MySurfaceView.screenH/2+300);
        addcir();
        addcir();
        addcir();
    }
    public void clear() {

        for(int i=0;i<=6;i++)
        {
            kings[i].layer1 = false;
            kings[i].layer2 = false;
            kings[i].layer3 = false;
        }
        points = 0;
        combo = 0;
        addcir();
        addcir();
        addcir();
    }
    public void draw(Canvas canvas, Paint paint) {

        canvas.drawColor(Color.rgb(10,10,10));
        paint.setARGB(255, 188, 171, 171);
        canvas.drawCircle( MySurfaceView.screenW/2,MySurfaceView.screenH/2 - 100, 18, paint);
        canvas.drawCircle( MySurfaceView.screenW/2+346,MySurfaceView.screenH/2+100, 18, paint);
        canvas.drawCircle( MySurfaceView.screenW/2+346,MySurfaceView.screenH/2+500, 18, paint);
        canvas.drawCircle( MySurfaceView.screenW/2 ,MySurfaceView.screenH/2+700,18, paint);
        canvas.drawCircle( MySurfaceView.screenW/2-346,MySurfaceView.screenH/2+500, 18, paint);
        canvas.drawCircle( MySurfaceView.screenW/2-346,MySurfaceView.screenH/2+100, 18, paint);
        canvas.drawCircle( MySurfaceView.screenW/2,MySurfaceView.screenH/2+300, 18, paint);

        paint.setTextSize(100);
        String cntpoints = "" + points;
        String outs = score + cntpoints;
        canvas.drawText(outs,100,300,paint);
        for(int i=0;i<=6;i++)
        {
            kings[i].drawdark(canvas,paint);
            kings[i].drawpole(canvas,paint);
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int nx = (int) event.getX();
                int ny = (int) event.getY();
                for(int i = 0;i <= 6;i++)
                {
                    if(abs(nx-kings[i].positionx)<100 && abs(ny-kings[i].positiony)<100)
                    {
                        origX = kings[i].positionx;
                        origY = kings[i].positiony;
                        kings[i].positionx = (int) event.getX();
                        kings[i].positiony = (int) event.getY();
                        config = i;
                        return true;
                    }
                }
                return false;


            case MotionEvent.ACTION_MOVE:
                if(config == -1)return false;
                kings[config].positionx = (int) event.getX();
                kings[config].positiony = (int) event.getY();
                return true;

            case MotionEvent.ACTION_UP:
                nx = (int) event.getX();
                ny = (int) event.getY();
                if(config == -1)return false;
                int lastp = -1;
                for(int i = 0;i <= 6 ;i++) {
                    if (abs(nx - kings[i].positionx) < 100 && abs(ny - kings[i].positiony) < 100 && i!=config) {
                        lastp = i;
                    }
                }
                if(lastp == -1)
                {
                    kings[config].positionx = origX;
                    kings[config].positiony = origY;
                    return true;
                }
                else
                {
                    if(isbeside(config,lastp))
                    {
                        if(match(config,lastp)) {
                            transcir(config,lastp);
                            kings[config].layer1 = false;
                            kings[config].layer2 = false;
                            kings[config].layer3 = false;
                            kings[config].positionx = origX;
                            kings[config].positiony = origY;
                            if(win())MySurfaceView.gameState = MySurfaceView.GAME_WIN;
                            else
                            {
                                addcir();
                                if(lost())MySurfaceView.gameState = MySurfaceView.GAME_LOST;
                            }
                            return true;
                        }
                        else
                        {
                            kings[config].positionx = origX;
                            kings[config].positiony = origY;
                            return true;
                        }
                    }
                    else
                    {
                        kings[config].positionx = origX;
                        kings[config].positiony = origY;
                        return true;
                    }
                }
        }
        return true;
    }
    public void transcir(int f,int l){
        if(kings[f].layer1) kings[l].layer1 = true;
        if(kings[f].layer2) kings[l].layer2 = true;
        if(kings[f].layer3) kings[l].layer3 = true;
        if(kings[l].layer1 == true && kings[l].layer2 == true && kings[l].layer3 == true)
        {
            kings[l].layer1 = false;
            kings[l].layer2 = false;
            kings[l].layer3 = false;
            combo++;
            points += 5;
            if(combo == 2){
                points += 10;
            }
            else if(combo == 3)
            {
                points += 50;
            }
            else if(combo == 4){
                points += 100;
            }
            else if(combo == 5)
            {
                points += 200;
            }
            else if(combo == 6)
            {
                points += 500;
            }
        }
        else{
            combo = 0;
            points += 1;
        }
    }
    public boolean isbeside(int f, int l) {
        if(f==6 || l == 6)return true;
        if(abs(f - l) == 1)return true;
        if((f == 5 && l == 0) || ( f == 0 && l == 5 ))return true;
        return false;
    }
    public boolean match(int f, int l) {
        if(kings[f].layer1 == false && kings[f].layer2 == false && kings[f].layer3 == false)return false;
        if(kings[f].layer1 == true && kings[l].layer1 == true)return false;
        if(kings[f].layer2 == true && kings[l].layer2 == true)return false;
        if(kings[f].layer3 == true && kings[l].layer3 == true)return false;
        return true;
    }
    public boolean win() {
       for(int i = 0; i <= 6; i++)
        {
            if(kings[i].layer1 == true || kings[i].layer2 == true || kings[i].layer3 == true) return false;
        }
        points += 200;
        return false;
    }
    public boolean lost() {
        for(int i = 0; i <= 6; i++)
        {
            for(int j = 0; j<=6 ; j++)
            {
                if(isbeside(i,j) && match(i,j)) return false;
            }
        }
        MySurfaceView.gamescore = points;
        return true;
    }
    public void addcir() {
        int addpole = -1;
        Random r;
        while(true) {
            r = new Random();
            int randnum = r.nextInt();
            randnum %= 7;
            randnum = abs(randnum);
            int cnt = 0;
            if (kings[randnum].layer1) cnt++;
            if (kings[randnum].layer2) cnt++;
            if (kings[randnum].layer3) cnt++;
            if (cnt < 2) {
                addpole = randnum;
                break;
            }
        }
        if(addpole == -1) MySurfaceView.gameState = MySurfaceView.GAME_LOST;
        while(true)
        {
            r = new Random();
            int randlayer = r.nextInt();
            randlayer %= 3;
            randlayer = abs(randlayer);
            if(randlayer == 0){
                if(!kings[addpole].layer1) {
                    kings[addpole].layer1 = true;
                    break;
                }
            }
            if(randlayer == 1){
                if(!kings[addpole].layer2) {
                    kings[addpole].layer2 = true;
                    break;
                }
            }
            if(randlayer == 2){
                if(!kings[addpole].layer3) {
                    kings[addpole].layer3 = true;
                    break;
                }
            }
        }
    }
}
