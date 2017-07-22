package com.example.joe.widget.refreshlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * 继承viewgroup重写onMeasure和onLayout Created by qiaobing on 2017/4/5.
 */
public class DrawLayout extends ViewGroup {

    public View header;
    public View footer;

    public PullHeader pullHeader;
    public PullFooter pullFooter;

    public int bottomScroll;// 当滚动到内容最底部时Y轴所需要的滑动值
    public int lastChildIndex;// 最后一个childview的index

    public DrawLayout(Context context) {
        super(context);
    }

    public DrawLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setHeader(PullHeader pullHeader) {
        this.pullHeader = pullHeader;
    }

    public void setFooter(PullFooter pullFooter) {
        this.pullFooter = pullFooter;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        lastChildIndex=getChildCount()-1;
    }

    /**
     * 添加上拉刷新布局作为header
     */
    public void addHeader(View header) {
        this.header = header;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(header, layoutParams);
    }

    /**
     * 添加下拉加载布局作为footer
     */
    public void addFooter(View footer) {
        this.footer = footer;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(footer, layoutParams);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //重置（避免重复累加）
        int contentHeight = 0;
        //遍历进行子控件的位置工作
        for (int index = 0; index < getChildCount(); index++) {
            View child = getChildAt(index);
            if (child.getVisibility() == GONE) {
                continue;
            }
            //头视图隐藏在ViewGroup的顶端
            if (child == header) {
                child.layout(0, 0 - child.getMeasuredHeight(), child.getMeasuredWidth(), 0);
            }
            //底部视图隐藏在ViewGroup的所有内容之后
            else if (child == footer) {
                child.layout(0, contentHeight, child.getMeasuredWidth(), contentHeight + child.getMeasuredHeight());
            }
            //内容视图根据定义（插入）顺序，按从上到下的顺序在垂直方向进行排列
            else {
                child.layout(0, contentHeight, child.getMeasuredWidth(), contentHeight + child.getMeasuredHeight());
                if (index <=lastChildIndex){
                    if (child instanceof ScrollView){
                        contentHeight+=getMeasuredHeight();
                        continue;
                    }
                    contentHeight+=child.getMeasuredHeight();
                }
            }
            //计算达到内容最底部时ViewGroup的滑动距离
            bottomScroll=contentHeight-getMeasuredHeight();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //遍历进行子控件的测量
        for (int i = 0; i < getChildCount(); i++) {
            //通知子控件测量
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
    }
}
