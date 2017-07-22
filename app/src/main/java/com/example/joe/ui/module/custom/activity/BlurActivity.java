package com.example.joe.ui.module.custom.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.joe.R;
import com.example.joe.popupwindow.BlurPopupwindow;

public class BlurActivity extends Activity implements View.OnClickListener {


    private View view;

    private TextView tv_blurpopupwindow;

    private BlurPopupwindow blurPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur);
        initView();
        initData();
        addListener();

    }


    private void initView() {
        view= LayoutInflater.from(this).inflate(R.layout.activity_blur,null);
        setContentView(view);
        tv_blurpopupwindow = (TextView) view.findViewById(R.id.tv_blurpopupwindow);
    }

    private void initData() {
//        blurPop = new BlurPopupwindow(this,getWindow().getDecorView());
    }


    private void addListener() {
        tv_blurpopupwindow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tv_blurpopupwindow) {
            full(true);
            blurPop = new BlurPopupwindow(this,getWindow().getDecorView());
            blurPop.showAtLocation(getWindow().getDecorView().getRootView(), Gravity.CENTER, 0, 0);
            blurPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    full(false);
                    blurPop=null;
                }
            });
        }
    }

    private void full(boolean enable) {
        if (enable) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(lp);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            WindowManager.LayoutParams attr = getWindow().getAttributes();
            attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attr);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
}
