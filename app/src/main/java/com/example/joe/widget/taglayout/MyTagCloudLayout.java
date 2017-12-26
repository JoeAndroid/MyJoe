package com.example.joe.widget.taglayout;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 自定义居中标签布局
 * Created by shenxiaolei on 17/3/31.
 */

public class MyTagCloudLayout extends ViewGroup {

    private BaseAdapter mAdapter;
    private DataChangeObserver mObserver;

    private int tagIndex;

    private int parentWidth;
    private int totaleft = 0;
    private int totalTop = 0;
    private int margin = 20;//每个view的间隔
    private int maxChildHeight = 0;
    private int totalRight = 0;
    private int mLineSpacing = 20;


    public MyTagCloudLayout(Context context) {
        super(context);
    }

    public MyTagCloudLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTagCloudLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyTagCloudLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private void drawLayout() {
        if (mAdapter == null || mAdapter.getCount() == 0) {
            return;
        }
        this.removeAllViews();
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View view = mAdapter.getView(i, null, null);
            this.addView(view);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /* 测量父布局 */
        parentWidth = resolveSize(0, widthMeasureSpec);
        int count = getChildCount();
        int tempMaxChildHeight = 0;
        int tempTotalHeight = 0;
        int tempTotalRight = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                /* 测量子布局 */
                if (child.getMeasuredWidth() > parentWidth) {
                    child.measure(parentWidth, child.getMeasuredHeight());
                } else
                    child.measure(child.getMeasuredWidth(), child.getMeasuredHeight());
                tempMaxChildHeight = Math.max(tempMaxChildHeight, child.getMeasuredHeight());
                tempTotalRight += child.getMeasuredWidth();
                if (tempTotalRight > parentWidth) {
                    tempTotalHeight += tempMaxChildHeight + mLineSpacing;
                    tempMaxChildHeight = child.getMeasuredHeight();
                    tempTotalRight = child.getMeasuredWidth();
                }
            }
        }
        /* 获取适配子布局后的高度 */
        int parentHeight = tempTotalHeight + tempMaxChildHeight + mLineSpacing * 5;
        //判断是否超出两行
        if (parentHeight > tempMaxChildHeight * 2 + mLineSpacing * 3) {
            parentHeight = tempMaxChildHeight * 2 + mLineSpacing * 3;
        }
        setMeasuredDimension(parentWidth, parentHeight);
    }

    private int measureSize(int measureSpec, int defaultSize) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int result = defaultSize;

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            result = Math.max(size, result);
        }

        return result;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int count = getChildCount();
        int lineViewCount = 0;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                if (i != 0) {
                    /* child 的 left 是上个子 view 的宽加上 margin */
                    totaleft += getChildAt(i - 1).getMeasuredWidth() + margin;
                } else {
                    totaleft = 0;
                    totalTop = 0;
                    maxChildHeight = child.getMeasuredHeight();
                }
                /* child 的 right */
                totalRight = totaleft + child.getMeasuredWidth();
                /* 如果 rigth 大于 父布局的宽， 则换行 */
                if (totalRight > parentWidth) {
                    adjustLine(lineViewCount, i); // 调整这一行的子布局的位置
                    lineViewCount = 0;  // 这一行的子 child 的数量充值
                    totalTop += maxChildHeight + mLineSpacing;
                    totaleft = 0;
                    maxChildHeight = child.getMeasuredHeight();
                    totalRight = child.getMeasuredWidth();
                } else {
                    maxChildHeight = Math.max(maxChildHeight, child.getMeasuredHeight());
                }

                child.layout(
                        totaleft,
                        totalTop,
                        totalRight,
                        totalTop + child.getMeasuredHeight()
                );

                /* 统计这一行的子view的数量 */
                lineViewCount++;
            }
        }

        /* 调整最后一行子布局的位置 */
        totaleft = totalRight + margin;
        adjustLine(lineViewCount, count);

    }

    /* 调整一行，让这一行的子布局水平居中 */
    private void adjustLine(int lineViewCount, int i) {
        totaleft = (parentWidth - totaleft) / 2;
        int marginTop;
        for (int lineViewNumber = lineViewCount; lineViewNumber > 0; lineViewNumber--) {
            View lineViewChild = getChildAt(i - lineViewNumber);
            totalRight = totaleft + lineViewChild.getMeasuredWidth();
            if (lineViewChild.getMeasuredHeight() != maxChildHeight) {
                marginTop = (maxChildHeight - lineViewChild.getMeasuredHeight()) / 2;
            } else {
                marginTop = 0;
            }
            lineViewChild.layout(totaleft, totalTop + marginTop, totalRight, totalTop + marginTop + lineViewChild.getMeasuredHeight());
            totaleft += lineViewChild.getMeasuredWidth() + margin;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(this.getContext(), attrs);
    }

    public void setAdapter(BaseAdapter adapter) {
        if (mAdapter == null) {
            mAdapter = adapter;
            if (mObserver == null) {
                mObserver = new DataChangeObserver();
                mAdapter.registerDataSetObserver(mObserver);
            }
            drawLayout();
        }
    }

    class DataChangeObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            MyTagCloudLayout.this.drawLayout();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    }
}
