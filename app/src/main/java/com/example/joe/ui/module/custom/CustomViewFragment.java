package com.example.joe.ui.module.custom;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.joe.R;
import com.example.joe.ui.base.BaseFragment;
import com.example.joe.ui.base.BasePresenter;
import com.example.joe.ui.presenter.HomePresenter;
import com.example.joe.ui.view.HomeView;
import com.example.joe.widget.customview.MultiShapeView;
import com.example.joe.widget.customview.PieData;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by qiaobing on 2017/4/7.
 */
public class CustomViewFragment extends BaseFragment<HomeView,HomePresenter> implements View.OnClickListener {
    @BindView(R.id.multiShapeView)
    MultiShapeView multiShapeView;
    @BindView(R.id.btnUnCheck)
    Button btnUnCheck;

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
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this.getContext());
    }

    @OnClick(value = {R.id.btnCheck, R.id.btnUnCheck})
    @Override
    public void onClick(View v) {
    }
}
