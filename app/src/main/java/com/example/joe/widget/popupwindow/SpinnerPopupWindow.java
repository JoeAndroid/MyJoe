package com.example.joe.widget.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.joe.utils.ScreenUtils;
import com.example.joe.utils.Utils;
import com.example.joe.widget.MyListView;

/**
 * Created by qiaobing on 2016/11/19.
 */

public class SpinnerPopupWindow extends PopupWindow {

    private Context mContext;

    private View view;

    private MyListView listview;

    private String[] arrays = {"c语言", "java", "php", "python"};

    public SpinnerPopupWindow(Context mContext) {
        this.mContext = mContext;
        initPop();
    }

    private void initPop() {
//        view = LayoutInflater.from(mContext).inflate(R.layout.spinner_popupwindow, null);
        setContentView(view);
        this.setWidth(ScreenUtils.getScreenWidth()/2);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        this.setTouchable(true);
        setOutsideTouchable(true);
//        listview = (MyListView) view.findViewById(R.id.listview);
        listview.setListViewHeight(ScreenUtils.getScreenHeight()/2);
        listview.setAdapter(new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, arrays));
    }


}
