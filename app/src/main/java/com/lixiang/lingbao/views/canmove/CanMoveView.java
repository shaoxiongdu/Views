package com.lixiang.lingbao.views.canmove;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

public class CanMoveView extends View {

    private static final String TAG = CanMoveView.class.getName();
    private Handler handler = new Handler();

    private static  int xSpeed = 10, ySpeed = 10;

    private static final int refreshTime = 5;

    private Paint circlePaint;

    private TextPaint textPaint;

    private int xPoint = 0, yPoint = 0;
    private int screenWidth = 0, screenHeight = 0;

    private int red = 0, green = 0, blue = 0;

    public CanMoveView(Context context) {
        super(context);
        start();
    }
    public CanMoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        init();
        start();
    }

    private void init() {
        textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(20);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);

        circlePaint = new Paint();
    }

    private void start(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                move();
                handler.postDelayed(this, refreshTime);
            }
        }, refreshTime);
    }

    private void move(){

        if (getRight() > screenWidth || getLeft() < 0) {
            Log.i(TAG, "x轴反向");
            xSpeed = -xSpeed;
            updateColor();
        }
        if (getBottom() > screenHeight - 100 || getTop() < 0) {
            Log.i(TAG, "y轴反向");
            ySpeed = -ySpeed;
            updateColor();
        }

        xPoint += xSpeed;
        yPoint += ySpeed;
        updateView();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        int radius = Math.min(getWidth(), getHeight()) / 2; // 圆形半径为宽高最小值的一半
        int cx = getWidth() / 2; // 圆心的 xPoint 坐标
        int cy = getHeight() / 2; // 圆心的 yPoint 坐标

        circlePaint.setColor(Color.rgb(red, green, blue));
        circlePaint.setStyle(Paint.Style.FILL);
        setBackgroundColor(Color.TRANSPARENT);

        canvas.drawCircle(cx, cy, radius, circlePaint);

        canvas.drawText(StrUtil.format("rgb({}, {}, {})", red, green, blue), cx, cy, textPaint);
    }

    private void updateColor(){
        red = RandomUtil.randomInt(128, 256);
        green = RandomUtil.randomInt(128, 256);
        blue = RandomUtil.randomInt(128, 256);
    }

    private void updateView(){
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        layoutParams.leftMargin  = xPoint;
        layoutParams.topMargin  = yPoint;
        setLayoutParams(layoutParams);
        invalidate();
    }

}
