package com.example.joe.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

public class CountdownUtil extends CountDownTimer {

    /**
     * 倒计时显示
     */
    private TextView textView;

    /**
     * 倒计时显示
     *
     * @param millisInFuture    总时间 毫秒单位
     * @param countDownInterval 间隔时间 毫秒单位
     * @param textView          显示textView
     */
    public CountdownUtil(long millisInFuture, long countDownInterval, TextView textView) {
        this(millisInFuture, countDownInterval);
        this.textView = textView;

    }

    public CountdownUtil(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onTick(long millisUntilFinished) {
        // TODO Auto-generated method stub
        textView.setText(millisUntilFinished / 1000 + "s后重发");
    }

    @Override
    public void onFinish() {
        // TODO Auto-generated method stub
//        textView.setText("获取验证码(60)");
        textView.setText("获取验证码");
        textView.setEnabled(true);
    }
}
