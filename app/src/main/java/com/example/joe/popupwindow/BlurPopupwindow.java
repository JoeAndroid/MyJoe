package com.example.joe.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.joe.R;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

/**
 * Created by qiaobing on 2016/6/21.
 */
public class BlurPopupwindow extends PopupWindow {

    private View view;

    private Context mContext;

    private BlurView blurView;

    private TextView textview;

    private View decorView;

    public BlurPopupwindow(Context mContext,View decorView){
        this.mContext=mContext;
        this.decorView=decorView;
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
//        view= LayoutInflater.from(mContext).inflate(R.layout.layout_popup_blur,null);
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(lp);
        setContentView(view);
        WindowManager wm= (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        //设置焦点
        setFocusable(true);
        //设置点击外部消失
        setOutsideTouchable(true);
        //设置无背景
        setBackgroundDrawable(new ColorDrawable(0x88000000));
        blurView= (BlurView) view.findViewById(R.id.blurView);
        textview= (TextView) view.findViewById(R.id.textview);
//        TextView tv_close = (TextView) view.findViewById(R.id.tv_close);
//        tv_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BlurPopupwindow.this.dismiss();
//            }
//        });
        textview.getLayoutParams().width=wm.getDefaultDisplay().getWidth();
        textview.getLayoutParams().height=wm.getDefaultDisplay().getHeight();
        setupBlurView();
    }

    private void setupBlurView() {
        final float radius = 16f;
        //Activity's root View. Can also be root View of your layout
        final View rootView = decorView.findViewById(android.R.id.content);
        //set background, if your root layout doesn't have one
        final Drawable windowBackground = decorView.getBackground();

        blurView.setupWith(rootView)
                .windowBackground(windowBackground)
                .blurAlgorithm(new RenderScriptBlur(mContext, true)) //Preferable algorithm, needs RenderScript support mode enabled
                .blurRadius(radius);
    }
}
