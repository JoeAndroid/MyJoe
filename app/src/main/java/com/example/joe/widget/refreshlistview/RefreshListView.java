package com.example.joe.widget.refreshlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.joe.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 上拉加载下拉刷新 Created by qiaobing on 2017/6/15.
 */
public class RefreshListView extends ListView implements AbsListView.OnScrollListener {

    private static final int PULL_TO_REFRESH = 0;//下拉刷新
    private static final int REFRESH_TO_REFRESH = 1;//松开刷新
    private static final int REFRESHING = 2;
    //当前状态
    private int mCurrrentState = PULL_TO_REFRESH;

    private Scroller mScroller;

    //滑动起点坐标
    private int startY = -1;

    //头部布局
    private View mHeaderView;
    ImageView iv_arr;
    ProgressBar pbprogress;
    TextView tv_title;
    TextView tv_time;

    //底部布局
    private View mFooterView;
    TextView footer_title;

    //头部尾部高度
    private int mHeaderViewHeight;
    private int mFooterViewHeight;
    //箭头旋转动画
    private RotateAnimation animUp;
    private RotateAnimation animDown;
    //是否可以加载更多
    private boolean isLoadingMore;


    //刷新监听器
    private OnRefreshListener onRefreshListener;

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    public interface OnRefreshListener {
        void onRefresh();

        void onLoadMore();
    }


    public RefreshListView(Context context) {
        super(context);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeaderView();
        initFooterView();

        initArrowAnim();
        this.setOnScrollListener(this);
    }


    /**
     * 初始化头部
     */
    private void initHeaderView() {
        mHeaderView = LayoutInflater.from(getContext()).inflate(R.layout.refreshlistview_header, null);
        this.addHeaderView(mHeaderView);
        iv_arr = (ImageView) mHeaderView.findViewById(R.id.iv_arr);
        pbprogress = (ProgressBar) mHeaderView.findViewById(R.id.pb_progress);
        tv_title = (TextView) mHeaderView.findViewById(R.id.tv_title);
        tv_time = (TextView) mHeaderView.findViewById(R.id.tv_time);

        mHeaderView.measure(0, 0);
        mHeaderViewHeight = mHeaderView.getMeasuredHeight();
        //隐藏头部布局
        mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);

        tv_time.setText("最后刷新时间" + getCurrentTime());
    }

    /**
     * 初始化底部
     */
    private void initFooterView() {
        mFooterView = LayoutInflater.from(getContext()).inflate(R.layout.refreshlistview_footer, null);
        this.addFooterView(mFooterView);
        footer_title = (TextView) mFooterView.findViewById(R.id.tv_pull_list_header_title);

        mFooterView.measure(0, 0);
        mFooterViewHeight = mFooterView.getMeasuredHeight();
        //隐藏底部
        mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);

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
     * 获取当前时间
     */
    private String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_FLING) {
            if (getLastVisiblePosition() == getCount() - 1 && !isLoadingMore) {
                System.out.println("到底了...");
                mFooterView.setPadding(0, 0, 0, 0);
                this.setSelection(getCount() - 1);

                isLoadingMore = true;

                if (null != onRefreshListener) {
                    onRefreshListener.onLoadMore();
                }
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    /**
     * 下拉头部状态
     */
    private void refreshState() {
        switch (mCurrrentState) {
            case PULL_TO_REFRESH://下拉刷新
                tv_title.setText("下拉刷新");
                iv_arr.setVisibility(VISIBLE);
                pbprogress.setVisibility(INVISIBLE);
                iv_arr.setAnimation(animDown);
                break;
            case REFRESH_TO_REFRESH://松开刷新
                tv_title.setText("松开刷新");
                iv_arr.setVisibility(VISIBLE);
                pbprogress.setVisibility(INVISIBLE);
                iv_arr.setAnimation(animUp);
                break;
            case REFRESHING://正在刷新
                tv_title.setText("正在刷新");
                iv_arr.clearAnimation();// 必须先清除动画,才能隐藏
                iv_arr.setVisibility(INVISIBLE);
                pbprogress.setVisibility(VISIBLE);
                iv_arr.setAnimation(animDown);

                if (null != onRefreshListener) {
                    onRefreshListener.onRefresh();//   下拉刷新
                }
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (getFirstVisiblePosition()==0){
                    startY = (int) ev.getRawY();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (startY == -1&&getFirstVisiblePosition()==0) {
                    startY = (int) ev.getRawY();
                }
                if (mCurrrentState == REFRESHING) {//正在刷新
                    break;
                }
                int endY = (int) ev.getRawY();
                int dy = endY - startY;//偏移量
                if (dy > 0 && getFirstVisiblePosition() == 0) {// 只有下拉并且当前是第一个item,才允许下拉
                    int padding = dy - mHeaderViewHeight;//计算padding
                    if (padding < 15) {
                        mHeaderView.setPadding(0, padding, 0, 0);// 设置当前padding
                    }
                    if (padding > 0 && mCurrrentState != REFRESH_TO_REFRESH) {// 状态改为松开刷新
                        mCurrrentState = REFRESH_TO_REFRESH;
                        refreshState();
                    } else if (padding < 0 && mCurrrentState != PULL_TO_REFRESH) {//下拉刷新
                        mCurrrentState = PULL_TO_REFRESH;
                        refreshState();
                    }
                    return true;// ==================> 消耗此事件，防止 super.onTouchEvent(ev)造成 2倍滑动
                }
                break;
            case MotionEvent.ACTION_UP:
                startY = -1;
                if (mCurrrentState == REFRESH_TO_REFRESH) {
                    mCurrrentState = REFRESHING;
                    mHeaderView.setPadding(0, 0, 0, 0);
                    refreshState();
                } else if (mCurrrentState == PULL_TO_REFRESH) {
                    mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
                }
                break;
        }

        return super.onTouchEvent(ev);
    }

    /**
     * 加载更多完成
     */
    public void onLoadComplete() {
        if (isLoadingMore) {
            isLoadingMore = false;
            mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);
        }
    }

    /**
     * 下拉刷新完成
     */
    public void onRefreshComplete() {
        mCurrrentState = PULL_TO_REFRESH;
        tv_title.setText("下拉刷新");
        iv_arr.setVisibility(VISIBLE);
        pbprogress.setVisibility(INVISIBLE);

        mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);

        tv_time.setText("最后刷新时间:" + getCurrentTime());
    }
}
