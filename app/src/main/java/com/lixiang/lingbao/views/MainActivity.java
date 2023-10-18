package com.lixiang.lingbao.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lixiang.lingbao.views.canmove.ViewActivity;
import com.lixiang.lingbao.views.webview.WebViewActivity;

import java.io.File;
import java.io.IOException;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.StreamProgress;
import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showMoveView(View view) {
        startActivity(new Intent(this, ViewActivity.class));
    }

    public void showWebView(View view) {
        startActivity(new Intent(this, WebViewActivity.class));
    }
}