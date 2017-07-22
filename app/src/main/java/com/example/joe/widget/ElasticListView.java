package com.example.joe.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

/**
 * 具有弹性的listview
 * Created by qiaobing on 2016/9/30.
 */

public class ElasticListView extends ListView {
    private Context mContext;
    private int mMaxOverScrollY=200;
    public ElasticListView(Context context) {
        super(context,null);
    }


    public ElasticListView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public ElasticListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        DisplayMetrics metrics=mContext.getResources().getDisplayMetrics();
        float density=metrics.density;
        mMaxOverScrollY= (int) (density*mMaxOverScrollY);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxOverScrollY, isTouchEvent);
    }
}
