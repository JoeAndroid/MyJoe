package com.example.joe.widget.tabindicatorlayout.listener;

public interface OnTabSelectListener {
    void onTabSelect(int position);

    void onTabReselect(int position);

    void onDoubleTap(int position);
}