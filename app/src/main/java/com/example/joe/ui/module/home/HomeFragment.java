package com.example.joe.ui.module.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.common.utils.base.BaseFragment;
import com.example.joe.R;
import com.example.joe.ui.presenter.HomePresenter;
import com.example.joe.ui.view.HomeView;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by qiaobing on 2017/4/7.
 */

public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView, View.OnClickListener {
    @BindView(R.id.btnVideo)
    Button btnVideo;

    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard videoplayer;

    /**
     * 实现无限自动左右滑动，点击item 事件
     * 1：getCount()方法返回值设置Integer.MAX_VALUE（可以"无限"左右滑动）
     * 2：自动滑动可以使用 handler发送延迟消息实现
     * 3：点击事件：item 添加点击事件。
     *      点击事件的 bug,点击按下会自动滑动。
     *      解决办法：item添加onTouch触摸事件，监听手指：按下，移动，取消，抬起。
     *      1.手指按下是移除handler 消息，手指抬起发送 handler消息。
     *      2.手指滑动后不能自动滑动了，因为手指拖拽后执行了按下，移动和取消，没有执行抬起。
     *      3.在执行取消的位置执行发送消息。扔有问题，拖拽没有结束执行到了取消。
     *      4.监听 viewpager的滑动状态，拖拽，移动，停止。viewpager刚显示和滚动过程中会执行移动和静止两个状态。
     *          解决拖拽问题：拖拽状态取消 handler消息。停止时执行 hanlder消息。定义状态是否拖拽，只在拖拽时执行，防止多次放松 handler.
     */
    @BindView(R.id.viewpager)
    ViewPager viewpager;


    @Override
    public int bindRootView() {
        return R.layout.fragment_network;
    }

    @Override
    public void initBundle(Bundle bundle) {

    }

    @Override
    public void addListener() {
        btnVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("yanzhi://yanzhi.cn/open"));
        intent.setData(Uri.parse("yanzhi://yanzhi.cn/video?videoId=43376&albumId=0&topicId=0"));
        startActivity(intent);
    }

    @Override
    public void initData() {
        mPresenter.getHomeDataList(new ArrayMap<>());
        videoplayer.setUp("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "饺子闭眼睛");
        videoplayer.thumbImageView.setImageURI(Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {//拖拽

                } else if (state == ViewPager.SCROLL_STATE_SETTLING) {//滚动

                } else if (state == ViewPager.SCROLL_STATE_IDLE) {//静止

                }
            }
        });
    }


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this.getActivity());
    }

    @Override
    public void getDataListSuccess() {

    }

    private static class MyPagerAdapter extends PagerAdapter {

        /**
         * 实例化 item
         * (初始位置是0：实例化0和1，初始位置是1：初始化0和2)
         *
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        /**
         * 释放 item
         * (初始位置是0：默认可以缓存两个，初始位置是1：默认缓存三个)
         *
         * @param container
         * @param position
         * @param object
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        /**
         * 比较 view与 object是否是同一个
         *
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }
    }

}
