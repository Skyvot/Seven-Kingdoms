package com.example.skyvot.sevenkingdoms;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Random;

import static java.lang.Math.abs;

public class StartActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new MySurfaceView(this));
    }

    @Override／
    protected void onResume() {
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((MySurfaceView.gameState == MySurfaceView.GAMEING || MySurfaceView.gameState == MySurfaceView.GAME_INF ||
                MySurfaceView.gameState==MySurfaceView.GAME_WIN || MySurfaceView.gameState==MySurfaceView.GAME_LOST)
                && keyCode == KeyEvent.KEYCODE_BACK) {
            MySurfaceView.gameState = MySurfaceView.GAME_MENU;
            MySurfaceView.resetgameinf = true;
            MySurfaceView.resetgame  = true;
            return true;
        }
        else{
            return super.onKeyDown(keyCode,event);
        }
    }
}
