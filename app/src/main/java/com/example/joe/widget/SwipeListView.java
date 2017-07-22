package com.example.joe.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by qiaobing on 2016/8/24.
 */
public class SwipeListView extends ListView {
    private int mScreenWith;//屏幕宽
    //按下点
    private int mDownX;
    private int mDownY;
    //删除按钮宽度
    private int mDeleteBtnWith;
    //是否显示删除按钮
    private boolean isDeleteShown;
    //当前处理的item
    private ViewGroup mPointChild;
    // 当前处理的item的LayoutParams
    private LinearLayout.LayoutParams mLayoutParams;


    public SwipeListView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public SwipeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
