package com.example.joe.ui.module.custom.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.joe.R;
import com.example.joe.widget.AlignTextView;
import com.example.joe.widget.ElasticListView;

import java.util.ArrayList;
import java.util.List;

public class SwipeListActivity extends AppCompatActivity {

    private ElasticListView elasticListView;
    private Toolbar toolbar;

    private AlignTextView textView;

    private List<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_list);
        textView= (AlignTextView) findViewById(R.id.textview);
        elasticListView = (ElasticListView) findViewById(R.id.elastic_listview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.xsearch_loading);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("JOE");
        toolbar.setSubtitle("joe");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
        elasticListView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList));
    }


    private void initData() {
        arrayList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            arrayList.add("item" + i);
        }
    }


}

