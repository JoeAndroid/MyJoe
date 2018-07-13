package com.example.joe.ui.module.custom;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.common.utils.base.BaseFragment;
import com.common.utils.view.RecordProgressView;
import com.example.joe.R;
import com.example.joe.bean.HomeBean;
import com.example.joe.ui.presenter.HomePresenter;
import com.example.joe.ui.view.HomeView;
import com.example.joe.widget.customview.MultiShapeView;
import com.example.joe.widget.customview.PieData;
import com.facebook.stetho.common.LogUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by qiaobing on 2017/4/7.
 */
public class CustomViewFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView, View.OnClickListener {
    @BindView(R.id.multiShapeView)
    MultiShapeView multiShapeView;
    @BindView(R.id.btnUnCheck)
    Button btnUnCheck;
    @BindView(R.id.start)
    TextView start;
    @BindView(R.id.pause)
    TextView pause;
    @BindView(R.id.reback)
    TextView reback;
    @BindView(R.id.delete)
    TextView delete;
    @BindView(R.id.recordProgress)
    RecordProgressView recordProgress;


    private List<PieData> mDatas;

    @Override
    public int bindRootView() {
        return R.layout.fragment_customview;
    }

    @Override
    public void initBundle(Bundle bundle) {

    }

    @Override
    public void addListener() {
    }

    @Override
    public void initData() {
        int a = 0;

       /* mDatas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            a += 10;
            PieData pie = new PieData("新能源" + i, a);
            mDatas.add(pie);
        }
        pieView.setStartAngle(90);
        pieView.setData(mDatas);*/

        multiShapeView.setImageResource(R.mipmap.ic_launcher);

        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogUtil.i("Rxjava_timer", "----timer----");
                    }
                });
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this.getContext());
    }

    @OnClick(value = {R.id.start, R.id.pause, R.id.reback, R.id.delete})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                recordProgress.changeRecordState(RecordProgressView.RecordState.START);
                break;
            case R.id.pause:
                recordProgress.changeRecordState(RecordProgressView.RecordState.PAUSE);
                break;
            case R.id.reback:
                recordProgress.changeRecordState(RecordProgressView.RecordState.ROLLBACK);
                break;
            case R.id.delete:
                recordProgress.changeRecordState(RecordProgressView.RecordState.DELETE);
                break;
        }
    }

    @Override
    public void getDataListSuccess(HomeBean value) {

    }
}
