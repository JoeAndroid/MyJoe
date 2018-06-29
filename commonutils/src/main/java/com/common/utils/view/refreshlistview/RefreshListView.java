package com.common.utils.view.refreshlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Scroller;
import android.widget.TextView;

import com.common.utils.R;
import com.common.utils.utils.TimeUtils;

/**
 * Created by qiaobing on 2018/5/8.
 */

public class RefreshListView extends ListView implements AbsListView.OnScrollListener {

    private float mLastY = -1;//上一次滑动位置
    private Scroller mScroller;//阻尼滑动效果
    private RefreshListViewListenre listViewListenre;//刷新监听

    private ListViewHeaderView mHeaderView;
    private LinearLayout mHeaderViewContent;
    private TextView mHeaderTimeView;
    private boolean mEnablePullRefresh = true;//设置允许下拉刷新
    private boolean mPullRefreshing = false; //是否正在刷新

    private ListViewFooterView mFooterView;
    private LinearLayout mfooterViewContent;
    private boolean mEnablePullLoad = true;//设置允许上拉加载
    private boolean mPullLoading = false;//是否正在加载更多
    //是否添加了 footer
    private boolean mIsFooterReady = false;

    private int iHeaderHeight;
    private int iFooterHeight;

    public final static int STATE_NORMAL = 0;//初始状态
    public final static int STATE_READY = 1;//下拉状态
    public final static int STATE_LOADING = 2;//刷新状态

    private static final int SCROLL_BACK_HEADER = 0;
    private static final int SCROLL_BACK_FOOTER = 1;
    private int mScrollBack = SCROLL_BACK_HEADER;

    private static final int SCROLL_DURATION = 400;


    public RefreshListView(Context context) {
        super(context);
        init(context);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context, new DecelerateInterpolator());
        super.setOnScrollListener(this);
        initHeaderView(context);
    }

    private void initHeaderView(Context context) {
        mHeaderView = new ListViewHeaderView(context);
        mFooterView = new ListViewFooterView(context);
        mHeaderViewContent = mHeaderView.findViewById(R.id.linear_header_container);
        mHeaderTimeView = mHeaderView.findViewById(R.id.tv_time);
        mfooterViewContent = mFooterView.findViewById(R.id.linear_footer_container);
        mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                iHeaderHeight = mHeaderViewContent.getHeight();
                getViewTreeObserver()
                        .removeOnGlobalLayoutListener(this);
            }
        });
        mFooterView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                iFooterHeight = mfooterViewContent.getHeight();
                getViewTreeObserver()
                        .removeOnGlobalLayoutListener(this);
            }
        });
        addHeaderView(mHeaderView);

    }

    public void setListViewListenre(RefreshListViewListenre listViewListenre) {
        this.listViewListenre = listViewListenre;
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        if (!mIsFooterReady) {
            mIsFooterReady = true;
            addFooterView(mFooterView);
        }
        super.setAdapter(adapter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mLastY == -1) {
            mLastY = ev.getRawY();
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //按下的时候我们需要记住起始点的Y轴的坐标
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - mLastY;
                mLastY = ev.getRawY();
                Log.i("Dy-----", deltaY + "");
                if (!mPullRefreshing && mEnablePullRefresh && getFirstVisiblePosition() == 0 && (mHeaderView.getMeasuredHeight() > 0 || deltaY > 0)) {
                    upDataHeardView(deltaY * 0.7f);
                } else if (!mPullLoading && mEnablePullLoad && getLastVisiblePosition() == getCount() - 1 && (mFooterView.getMeasuredHeight() > 0 || deltaY < 0)) {
                    updateFooterState(-deltaY);
                }
                break;
            default:
                mLastY = -1;
                if (getFirstVisiblePosition() == 0) {
                    if (mEnablePullRefresh && mHeaderView.getHeaderHeight() > iHeaderHeight && !mPullRefreshing) {//下拉刷新状态
                        mPullRefreshing = true;
                        mHeaderView.setState(STATE_LOADING);
                        //刷新
                        if (listViewListenre != null)
                            listViewListenre.onRefresh();
                    }
                    resetHeaderHeight();
                } else if (getLastVisiblePosition() == getCount() - 1) {
                    if (mEnablePullLoad && mFooterView.getFooterHeight() > iFooterHeight && !mPullLoading) {
                        //刷新
                        mPullLoading = true;
                        mFooterView.setState(STATE_LOADING);
                        if (listViewListenre != null) {
                            listViewListenre.onLoadMore();
                        }
                    }
                    resetFooterHeight();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 设置头部的view的高度，来达到下移的效果
     */
    private void upDataHeardView(float dy) {
        //移动距离等于move的距离加上heardview的高度
        mHeaderView.setHeaderHeight((int) (dy + mHeaderView.getHeaderHeight()));
        //如果当前的状态不是正在加载中，就改变状态
        if (mEnablePullRefresh && !mPullRefreshing) {
            //如果当前的heardview的高度大于原始的高度就代表用户下拉刷新了要该表状态，变成下拉刷新的状态
            if (mHeaderView.getHeaderHeight() > iHeaderHeight) {
                mHeaderView.setState(STATE_READY);
            } else {
                mHeaderView.setState(STATE_NORMAL);
            }
        }
        setSelection(0); // scroll to top each time
    }

    /**
     * 更新底部footview的状态
     */
    private void updateFooterState(float dy) {
        //设置footview的高度移动的时候，这个高度是增加每次移动的点相当于上一个点的位置的距离
        mFooterView.setFooterHeight((int) (dy + mFooterView.getFooterHeight()));
        if (null == mFooterView) {
            return;
        }
        if (mEnablePullLoad && !mPullLoading) {
            if (mFooterView.getFooterHeight() > iFooterHeight) {
                mFooterView.setState(STATE_READY);
            } else {
                mFooterView.setState(STATE_NORMAL);
            }
        }
    }

    private void resetHeaderHeight() {
        int height = mHeaderView.getHeaderHeight();
        if (height == 0)
            return;

        if (mPullRefreshing && height < iHeaderHeight) {  //正在刷新并且将header向上拖动隐藏时
            return;
        }

        int finalHeight = 0;

        if (mPullRefreshing && mHeaderView.getHeaderHeight() >= iHeaderHeight) {
            finalHeight = iHeaderHeight;
        }
        mScrollBack = SCROLL_BACK_HEADER;
        mScroller.startScroll(0, height, 0, finalHeight - height, SCROLL_DURATION);
        //激活computeScroll方法
        invalidate();
    }

    private void resetFooterHeight() {
        int height = mFooterView.getFooterHeight();
        if (height == 0)
            return;
        if (mPullLoading && height < iFooterHeight) {  //正在刷新并且将footer向上拖动隐藏时
            return;
        }
        int finalHeight = 0;

        if (mPullLoading && mFooterView.getFooterHeight() >= iFooterHeight) {
            finalHeight = iFooterHeight;
        }
        mScrollBack = SCROLL_BACK_FOOTER;
        mScroller.startScroll(0, height, 0, finalHeight - height, SCROLL_DURATION);
        //激活computeScroll方法
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            if (mScrollBack == SCROLL_BACK_HEADER) {
                mHeaderView.setHeaderHeight(mScroller.getCurrY());
            } else {
                mFooterView.setFooterHeight(mScroller.getCurrY());
            }
            postInvalidate();
        }
        super.computeScroll();
    }

    public void setEnablePullRefresh(boolean enablePullRefresh) {
        this.mEnablePullRefresh = enablePullRefresh;
        if (mEnablePullRefresh) {
            mHeaderViewContent.setVisibility(VISIBLE);
        } else {
            mHeaderViewContent.setVisibility(INVISIBLE);
        }
    }

    public void setEnablePullLoad(boolean enablePullLoad) {
        this.mEnablePullLoad = enablePullLoad;
        if (mEnablePullLoad) {
            mPullLoading = false;
            mFooterView.show();
            mFooterView.setState(STATE_NORMAL);
            setFooterDividersEnabled(true);
        } else {
            mFooterView.hide();
            setFooterDividersEnabled(false);
        }
    }

    //刷新完成
    public void refreshComplete() {
        if (mPullRefreshing) {
            mPullRefreshing = false;
            resetHeaderHeight();
            mHeaderTimeView.setText("最后刷新时间:" + TimeUtils.millis2String(System.currentTimeMillis()));
        }
    }

    public void loadMoreComplete() {
        if (mPullLoading) {
            mPullLoading = false;
            resetFooterHeight();
        }
    }

    /**
     * 开始刷新，通常用于调用者主动刷新，典型的情况是进入界面，开始主动刷新，这个刷新并不是由用户拉动引起的
     *
     * @param delayMillis 延迟时间
     */
    public void doPullRefreshing(long delayMillis) {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!mPullRefreshing) {
                    if (listViewListenre != null) {
                        mPullRefreshing = true;
                        mHeaderView.setState(STATE_LOADING);
                        listViewListenre.onRefresh();
                        setSelection(0); // scroll to top each time
                        mScrollBack = SCROLL_BACK_HEADER;
                        mScroller.startScroll(0, 0, 0, iHeaderHeight, SCROLL_DURATION);
                        invalidate();
                    }
                }
            }
        }, delayMillis);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    public interface RefreshListViewListenre {
        void onRefresh();

        void onLoadMore();
    }
}
