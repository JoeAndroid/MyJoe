package com.common.utils.view.refreshlistview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.common.utils.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * listview刷线头部
 * Created by qiaobing on 2018/5/7.
 */

public class ListViewHeaderView extends LinearLayout {

    private Context mContext;

    private LinearLayout mHeaderView;
    ImageView iv_arr;
    ProgressBar pbprogress;
    TextView tv_title;
    TextView tv_time;

    //箭头旋转动画
    private RotateAnimation animUp;
    private RotateAnimation animDown;

    private int mState = RefreshListView.STATE_NORMAL;


    public ListViewHeaderView(@NonNull Context context) {
        super(context);
        initViews(context);
        initArrowAnim();
    }

    public ListViewHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
        initArrowAnim();
    }

    private void initViews(Context context) {
        mContext = context;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, 0);
        mHeaderView = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.refreshlistview_header, null);
        addView(mHeaderView, lp);
        setGravity(Gravity.BOTTOM);
        iv_arr = (ImageView) mHeaderView.findViewById(R.id.iv_arr);
        pbprogress = (ProgressBar) mHeaderView.findViewById(R.id.pb_progress);
        tv_title = (TextView) mHeaderView.findViewById(R.id.tv_title);
        tv_time = (TextView) mHeaderView.findViewById(R.id.tv_time);
        tv_time.setText("最后刷新时间" + getCurrentTime());
    }

    /**
     * 初始化箭头动画
     */
    private void initArrowAnim() {
        //箭头向上动画
        animUp = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animUp.setDuration(200);
        animUp.setFillEnabled(true);

        //箭头向下动画
        animDown = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animDown.setDuration(200);
        animDown.setFillEnabled(true);
    }

    /**
     * 设置头部状态
     *
     * @param state
     */
    public void setState(int state) {
        if (state == mState) return;

        if (state == RefreshListView.STATE_LOADING) {
            iv_arr.clearAnimation();
            iv_arr.setVisibility(INVISIBLE);
            pbprogress.setVisibility(VISIBLE);
        } else {
            iv_arr.setVisibility(VISIBLE);
            pbprogress.setVisibility(INVISIBLE);
        }
        switch (state) {
            case RefreshListView.STATE_NORMAL:
                if (mState == RefreshListView.STATE_READY) {
                    iv_arr.startAnimation(animDown);
                }

                if (mState == RefreshListView.STATE_LOADING) {
                    iv_arr.clearAnimation();
                }
                tv_title.setText("下拉刷新");
                break;
            case RefreshListView.STATE_READY:
                if (mState != RefreshListView.STATE_READY) {
                    iv_arr.clearAnimation();
                    iv_arr.startAnimation(animUp);
                }
                tv_title.setText("松开刷新");
                break;
            case RefreshListView.STATE_LOADING:
                tv_title.setText("正在刷新");
                break;
        }
        mState = state;
    }

    public int getCurrentState() {
        return mState;
    }

    /**
     * 获取当前时间
     */
    private String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * 设置头部显示高度
     */
    public void setHeaderHeight(int height) {
        if (height < 0)
            height = 0;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mHeaderView
                .getLayoutParams();
        lp.height = height;
        lp.gravity=Gravity.BOTTOM;
        mHeaderView.setLayoutParams(lp);
    }

    public int getHeaderHeight() {
        return mHeaderView.getLayoutParams().height;
    }


}
