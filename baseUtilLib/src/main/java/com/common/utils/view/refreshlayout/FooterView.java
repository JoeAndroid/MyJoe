package com.common.utils.view.refreshlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.common.utils.R;

/**
 * Created by qiaobing on 2017/4/5.
 */

public class FooterView extends FrameLayout implements PullFooter {

    public TextView tvPullUp;

    public FooterView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.layout_footer, this, true);
        tvPullUp = (TextView) findViewById(R.id.tv);
    }


    @Override
    public void onUpBefore(int scrollY) {
        tvPullUp.setText("上拉加载更多");
    }

    @Override
    public void onUpAfter(int scrollY) {
        tvPullUp.setText("松开加载更多");
    }

    @Override
    public void onLoadScrolling(int scrollY) {
        tvPullUp.setText("准备加载");
    }

    @Override
    public void onLoadDoing(int scrollY) {
        tvPullUp.setText("正在加载……");
    }

    @Override
    public void onLoadCompleteScrolling(int scrollY, boolean isLoadSuccess) {
        tvPullUp.setText(isLoadSuccess ? "加载成功" : "加载失败");
    }

    @Override
    public void onLoadCancelScrolling(int scrollY) {
        tvPullUp.setText("加载取消");
    }
}
