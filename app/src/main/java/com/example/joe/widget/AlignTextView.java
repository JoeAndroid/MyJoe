package com.example.joe.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * 可设置对齐方式的textview
 * Created by qiaobing on 2016/10/31.
 */
public class AlignTextView extends View {
    //画笔
    private Paint mPaint;

    public AlignTextView(Context context) {
        super(context, null);
    }

    public AlignTextView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public AlignTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setTextSize(50);
        mPaint.setColor(Color.BLUE);
        String[] strings = new String[]{"Idtk", "是", "一", "个", "小", "学", "生"};
        Point point = new Point(0, 0);
        textCenter(strings, mPaint, canvas, point, Paint.Align.CENTER);
    }


    private void textCenter(String[] strings, Paint paint, Canvas canvas, Point point, Paint.Align align) {
        mPaint.setTextAlign(align);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;
        int length = strings.length;
        float total = (length - 1) * (-top + bottom) + (-fontMetrics.ascent + fontMetrics.descent);
        float offset = total / 2 - bottom;
        for (int i = 0; i < length; i++) {
            float yAxis = -(length - i - 1) * (-top + bottom) + offset;
            canvas.drawText(strings[i], point.x, point.y + yAxis, mPaint);
        }
    }
}
