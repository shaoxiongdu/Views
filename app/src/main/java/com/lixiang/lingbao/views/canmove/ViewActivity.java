package com.lixiang.lingbao.views.canmove;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lixiang.lingbao.views.R;

import java.io.File;

import cn.hutool.core.io.StreamProgress;
import cn.hutool.http.HttpUtil;

public class ViewActivity extends AppCompatActivity {
    
    private static final String TAG = ViewActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_move);
    }
}