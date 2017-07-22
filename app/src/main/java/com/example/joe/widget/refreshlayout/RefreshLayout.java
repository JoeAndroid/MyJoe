package com.example.joe.widget.refreshlayout;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by qiaobing on 2017/4/5.
 */

public class RefreshLayout extends PullLayout {
    public RefreshLayout(Context context) {
        super(context);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    public void init() {
        HeaderView header = new HeaderView(getContext());
        FooterView footer = new FooterView(getContext());

        addHeader(header);
        addFooter(footer);

        setHeader(header);
        setFooter(footer);
    }

}
