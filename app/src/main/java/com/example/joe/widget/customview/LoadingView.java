package com.example.joe.widget.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qiaobing on 2017/5/24.
 */
public class LoadingView extends View {

    private Paint mPaint;

    private RectF mRectF;

    private int mWidth, mHeight;

    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mRectF = new RectF(10, 10, 200, 200);
//        canvas.drawArc(mRectF, 90, 180, false, mPaint);

        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心
        canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴

        Path path = new Path();
        path.lineTo(100,100);

        RectF oval = new RectF(0,0,300,300);

        path.arcTo(oval,0,270);
        // path.arcTo(oval,0,270,false);             // <-- 和上面一句作用等价

        canvas.drawPath(path,mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
}
