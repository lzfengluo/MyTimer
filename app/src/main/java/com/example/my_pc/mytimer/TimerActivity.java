package com.example.my_pc.mytimer;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {

    private TextView tvTime;
    private Chronometer ch;
    private int recLen = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        ch = (Chronometer) findViewById(R.id.chronometer);
        tvTime = (TextView) findViewById(R.id.tv_time);

//        开始计时，1秒后调用Runnable对象
        handler.postDelayed(runnable, 1000);

        ch.setBase(SystemClock.elapsedRealtime());
        ch.setFormat("已用时间：%s");
        ch.start();
        ch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (SystemClock.elapsedRealtime() - ch.getBase() >= 10000) {
                    ch.stop();
//                    关闭计时器
                    handler.removeCallbacks(runnable);
                }
            }
        });
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            recLen--;
            tvTime.setText("倒计时："+recLen);
//            每隔1秒调用一次Runnable对象
            handler.postDelayed(this,1000);
        }
    };

}
