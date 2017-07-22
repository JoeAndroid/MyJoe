package com.example.joe.widget.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * 饼图 Created by qiaobing on 2017/5/19.
 */
public class PieView extends View {
    // 颜色表(注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    //饼状图初始绘制角度
    private float mStartAngle = 0;
    //数据
    private List<PieData> mData;
    //宽高
    private int mWidth, mHeight;
    //画笔
    private Paint mPaint = new Paint();

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (null == mData)
            return;
        float currentAngle = mStartAngle;//启始角度
        canvas.translate(mWidth / 2, mHeight / 2);//移动画布坐标原点到中心位置
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);

        RectF rectF = new RectF(-r, -r, r, r);

        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            mPaint.setColor(pie.getColor());
            canvas.drawArc(rectF, currentAngle, pie.getAngle(), true, mPaint);
            currentAngle += pie.getAngle();
        }

    }

    /**
     * 设置启始角度
     */
    public void setStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();//刷新
    }

    /**
     * 设置数据
     */
    public void setData(List<PieData> mData) {
        this.mData = mData;
        initData(mData);
        invalidate();
    }

    //初始化数据
    private void initData(List<PieData> mData) {
        if (null == mData || mData.size() == 0)
            return;
        float sumValue = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);

            sumValue += pie.getValue();

            int j = i % mColors.length;

            pie.setColor(mColors[j]);
        }

        float sumAngle = 0;

        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);

            float percentage = pie.getValue() / sumValue;//百分比

            float angle = percentage * 360;//角度

            pie.setPercentage(percentage);
            pie.setAngle(angle);

            sumAngle += angle;

            Log.i("angle", "" + pie.getAngle());
        }
    }


}
