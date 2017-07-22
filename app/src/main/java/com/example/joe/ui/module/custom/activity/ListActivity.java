package com.example.joe.ui.module.custom.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.joe.R;
import com.example.joe.ui.module.custom.activity.RefreshVerticalViewPagerActivity;
import com.example.joe.ui.module.custom.activity.SwipeListActivity;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private List<String> stringList;

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initData();
        listview= (ListView) findViewById(R.id.listview);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,stringList);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(this);
    }

    private void initData() {
        stringList=new ArrayList<>();
        stringList.add("ListView侧滑删除");
        stringList.add("RefreshVerticalViewPager");
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0://侧滑删除
                startActivity(new Intent(this, SwipeListActivity.class));
            break;
            case 1://RefreshVerticalViewPager
                startActivity(new Intent(this, RefreshVerticalViewPagerActivity.class));
            break;
        }
    }
}
