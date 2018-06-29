package com.common.utils.view.refreshlistview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.common.utils.R;

/**
 * listview刷线头部
 * Created by qiaobing on 2018/5/7.
 */

public class ListViewFooterView extends LinearLayout {

    private Context mContext;

    private View mFooterView;
    TextView footer_title;

    ProgressBar progressBar;

    private int mState = RefreshListView.STATE_NORMAL;

    public ListViewFooterView(@NonNull Context context) {
        super(context);
        initViews(context);
    }

    public ListViewFooterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    private void initViews(Context context) {
        mContext = context;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, 0);
        mFooterView = LayoutInflater.from(getContext()).inflate(R.layout.refreshlistview_footer, null);
        addView(mFooterView, lp);
        setGravity(Gravity.TOP);
        footer_title = mFooterView.findViewById(R.id.tv_pull_list_header_title);
        progressBar = mFooterView.findViewById(R.id.pb_pull_list_header);
    }


    public void setState(int state) {
        progressBar.setVisibility(INVISIBLE);
        if (state == RefreshListView.STATE_READY) {
            footer_title.setText("松开加载更多");
        } else if (state == RefreshListView.STATE_LOADING) {
            progressBar.setVisibility(VISIBLE);
            footer_title.setText("正在加载");
        } else {
            footer_title.setText("上拉加载");
        }
        mState = state;
    }

    public int getCurrentState() {
        return mState;
    }

    public void setFooterHeight(int height) {
        if (height <= 0) {
            height = 0;
        }
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mFooterView.getLayoutParams();
        lp.height = height;
        mFooterView.setLayoutParams(lp);
    }

    public int getFooterHeight() {
        return mFooterView.getLayoutParams().height;
    }

    /**
     * 50      * normal status
     * 51
     */
    public void normal() {
        footer_title.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }


    /**
     * loading status
     */
    public void loading() {
        footer_title.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * hide footer when disable pull load more
     */
    public void hide() {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mFooterView.getLayoutParams();
        lp.height = 0;
        mFooterView.setLayoutParams(lp);
    }

    /**
     * show footer
     */
    public void show() {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mFooterView.getLayoutParams();
        lp.height = LayoutParams.WRAP_CONTENT;
        mFooterView.setLayoutParams(lp);
    }
}
