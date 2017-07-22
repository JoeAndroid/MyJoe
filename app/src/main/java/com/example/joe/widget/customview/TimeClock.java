package com.example.joe.widget.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义时钟 Created by qiaobing on 2017/5/26.
 */

public class TimeClock extends View {

    /**
     * 定义画笔
     *
     * @param context
     */
    //外圆和刻度
    private Paint mPaint;
    //文字
    private Paint mPaintNum;
    //秒针
    private Paint secondPaint;
    //分针
    private Paint minutePaint;
    //时针
    private Paint hourPaint;
    //圆心
    private float x, y;
    //半径
    private int r;

    public TimeClock(Context context) {
        super(context);
        initPaint();
    }

    public TimeClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public TimeClock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaintNum = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintNum.setColor(Color.BLACK);
        mPaintNum.setTextSize(35);
        mPaintNum.setStyle(Paint.Style.STROKE);
        mPaintNum.setTextAlign(Paint.Align.CENTER);

        secondPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        secondPaint.setColor(Color.RED);
        secondPaint.setStrokeWidth(5);
        secondPaint.setStyle(Paint.Style.FILL);

        minutePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        minutePaint.setColor(Color.BLACK);
        minutePaint.setStrokeWidth(8);
        minutePaint.setStyle(Paint.Style.FILL);

        hourPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hourPaint.setColor(Color.BLACK);
        hourPaint.setStrokeWidth(13);
        hourPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        x = width / 2;
        y = height / 2;

        r = (int) (x - 5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画外圆
        canvas.drawCircle(x, y, r, mPaint);

        //圆心
        canvas.drawCircle(x, y, 15, minutePaint);
        //画刻度
        drawLines(canvas);
        //画文字
        drawText(canvas);

        //画时分秒
        initCurrentTime(canvas);
        //1秒绘制一次
        postInvalidateDelayed(1000);
    }

    private void drawLines(Canvas canvas) {
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                mPaint.setStrokeWidth(8);
                canvas.drawLine(x, y - r, x, y - r + 40, mPaint);
            } else {
                mPaint.setStrokeWidth(3);
                canvas.drawLine(x, y - r, x, y - r + 30, mPaint);
            }
            canvas.rotate(6, x, y);
        }
    }

    private void drawText(Canvas canvas) {
        //文字高度
        float textSize = mPaintNum.getFontMetrics().bottom - mPaintNum.getFontMetrics().top;
        // 数字离圆心的距离,40为刻度的长度,20文字大小
        int distance = r - 40 - 20;
        // 数字的坐标(a,b)
        float a, b;
        // 每30°写一个数字
        for (int i = 0; i < 12; i++) {
            a = (float) (distance * Math.sin(i * 30 * Math.PI / 180) + x);
            b = (float) (y - distance * Math.cos(i * 30 * Math.PI / 180));
            if (i == 0) {
                canvas.drawText("12", a, b + textSize / 3, mPaintNum);
            } else {
                canvas.drawText(String.valueOf(i), a, b + textSize / 3, mPaintNum);
            }
        }
    }

    private void initCurrentTime(Canvas canvas) {
        //获取当前时间
        SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
        String time = format.format(new Date(System.currentTimeMillis()));
        String[] split = time.split("-");
        int hour = Integer.valueOf(split[0]);
        int minute = Integer.valueOf(split[1]);
        int second = Integer.valueOf(split[2]);
        //时针走过的角度
        int hourAngle = hour * 30 + minute / 2;
        //分针的角度
        int minuteAngle = minute * 6 + second / 10;
        //秒针的角度
        int secondAngle = second * 6;

        //绘制时钟
        canvas.rotate(hourAngle,x,y);
        canvas.drawLine(x,y,x,y-r+150,hourPaint);
        canvas.save();
        canvas.restore();

        //这里画好了时钟，我们需要再将画布转回来,继续以12整点为0°参照点
        canvas.rotate(-hourAngle,x,y);

        //绘制分针
        canvas.rotate(minuteAngle,x,y);
        canvas.drawLine(x,y,x,y-r+60,minutePaint);
        canvas.save();
        canvas.restore();

        canvas.rotate(-minuteAngle,x,y);

        canvas.rotate(secondAngle,x,y);
        canvas.drawLine(x,y,x,y-r+20,secondPaint);
        canvas.save();
        canvas.restore();
    }

}
