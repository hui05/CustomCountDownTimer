package com.enci.customcountdowntimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView mTvCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvCountDown = (TextView) findViewById(R.id.tvCountDown);

        mTvCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CustomCountDownTimer.Build(MainActivity.this)
                        .setView(mTvCountDown)    // 设置目标 View
                        .setMillisInFuture(6000)    // 设置倒计时 开始时间  单位 毫秒
                        .setCountDownInterval(1000)  // 设置倒计时  的时间间隔  单位 毫秒
                        .setEndTextSize(12)     // 设置 倒计时中 字体大小
                        .setTickTextSize(12)    // 设置 结束后  字体大小
                        .setEndTextColor(R.color.c_177be6)  // 设置 结束后 字体颜色
                        .setTickTextColor(R.color.c_51)   // 设置 倒计时中 字体颜色
                        .setEndToZero(true)     // 设置 是否从零结束
                        .setEndText("倒计时")    // 设置结束时的 文字
                        .setTickPrefix("")  // 设置倒计时 的前缀
                        .setTickSuffix(" S")  // 设置倒计时 的后缀
                        .build()
                        .start();
            }
        });
    }
}
