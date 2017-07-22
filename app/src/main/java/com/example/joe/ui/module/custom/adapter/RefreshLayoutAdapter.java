package com.example.joe.ui.module.custom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.joe.R;

import java.util.ArrayList;

/**
 * Created by qiaobing on 2016/6/14.
 */
public class RefreshLayoutAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<String> list;

    public RefreshLayoutAdapter(Context context){
        this.context=context;
    }

    public void addList(ArrayList<String> list){
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (null==convertView){
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_main_list_item,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position));

        return convertView;
    }

    class ViewHolder{
        View view;

        TextView textView;

        public ViewHolder(View view){
            this.view=view;
            textView= (TextView) view.findViewById(R.id.textview_name);
        }
    }
}
