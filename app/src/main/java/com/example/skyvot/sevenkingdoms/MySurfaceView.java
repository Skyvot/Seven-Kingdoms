package com.example.skyvot.sevenkingdoms;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 * Created by skyvot on 2017/7/7.
 */

public class MySurfaceView extends SurfaceView implements Callback,Runnable{

    public static final int GAME_MENU = 0;
    public static final int GAMEING = 1;
    public static final int GAME_WIN = 2;
    public static final int GAME_LOST = 3;
    public static final int GAME_INF = 4;
    public static int gameState = GAME_MENU;
    public static int gamescore = 0;

    private Paint paint;
    private int paintColor = Color.BLUE;
    private Canvas canvas;
    private SurfaceHolder sfh;
    private Resources res = this.getResources();


    private Bitmap title;
    private Bitmap help;
    private Bitmap start;
    private Bitmap restart;
    private Bitmap youwin;
    private Bitmap youlost;


    public static int screenH,screenW;
    public Thread th;
    private GameMenu gameMenu;
    public GamingView gamingView;
    private WinView winView;
    private LostView lostView;
    private GameInf gameInf;

    public static boolean resetgame = false;
    public static boolean resetgameinf = false;

    public MySurfaceView(Context context){
        super(context);
        initPaint();
        sfh = this.getHolder();
        sfh.addCallback(this);
        th = new Thread(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    private void initPaint(){

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(paintColor);
    }

    public void surfaceCreated(SurfaceHolder holder)
    {
        screenH = getHeight();
        screenW = getWidth();
        initgame();

    }
    private void initgame()
    {
        title = BitmapFactory.decodeResource(res,R.drawable.sevenkingdoms);
        help = BitmapFactory.decodeResource(res,R.drawable.howtoplay);
        start = BitmapFactory.decodeResource(res,R.drawable.startgame);
        restart = BitmapFactory.decodeResource(res,R.drawable.tryagain);
        youwin = BitmapFactory.decodeResource(res,R.drawable.youwin);
        youlost = BitmapFactory.decodeResource(res,R.drawable.youlost);
        gameMenu = new GameMenu(title,start,help);
        gamingView = new GamingView();
        winView = new WinView(restart,youwin);
        lostView = new LostView(restart, youlost);
        gameInf = new GameInf();
        th.start();
    }

    protected void myDraw() {
        try{
            canvas = sfh.lockCanvas();
            if(canvas!=null){
                switch (gameState)
                {
                    case GAME_MENU:
                        gameMenu.draw(canvas, paint);
                        break;
                    case GAMEING:
                        if(resetgame)
                        {
                            gamingView.clear();
                            resetgame = false;
                            gamingView.draw(canvas,paint);
                        }
                        else gamingView.draw(canvas,paint);
                        break;
                    case GAME_WIN:
                        winView.draw(canvas,paint);
                        break;
                    case GAME_LOST:
                        lostView.draw(canvas,paint);
                        break;
                    case GAME_INF:
                        if(resetgameinf)
                        {
                            gameInf.clear();
                            resetgameinf = false;
                            gameInf.draw(canvas,paint);
                        }
                        gameInf.draw(canvas,paint);
                        break;
                }
            }
        }catch (Exception e){

        }finally {
            if(canvas!=null) {
                sfh.unlockCanvasAndPost(canvas);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        switch (gameState)
        {
            case GAME_MENU:
                gameMenu.onTouchEvent(event);
                break;
            case GAMEING:
                boolean flg = gamingView.onTouchEvent(event);
                break;
            case GAME_WIN:
                winView.onTouchEvent(event);
                break;
            case GAME_LOST:
                lostView.onTouchEvent(event);
                break;
            case GAME_INF:
                gameInf.onTouchEvent(event);
                break;
        }
        return true;
    }

    @Override
    public void run() {
        while(true)
        {
            try {
                Thread.sleep(10);
                myDraw();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}