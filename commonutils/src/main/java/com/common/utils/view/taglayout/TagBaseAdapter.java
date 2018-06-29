package com.common.utils.view.taglayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.common.utils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fyales
 * @since 8/26/15.
 */
public class TagBaseAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mList;
    private List<Boolean> mSelectList;

    public TagBaseAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
        mSelectList = new ArrayList<Boolean>();
        for (int i = 0; i < list.size(); i++) {
            mSelectList.add(i, false);
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.tagview, null);
            holder = new ViewHolder();
            holder.tagBtn = (Button) convertView.findViewById(R.id.tag_btn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final String text = getItem(position);
        holder.tagBtn.setText(text);
        //选择状态
        if (mSelectList.get(position)) {
            holder.tagBtn.setSelected(true);
            holder.tagBtn.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            holder.tagBtn.setSelected(false);
            holder.tagBtn.setTextColor(mContext.getResources().getColor(R.color.color_848484));
        }
        return convertView;
    }

    /**
     * 设置选中状态
     *
     * @param isSelect
     * @param position
     */
    public void setSelect(boolean isSelect, int position) {
        mSelectList.set(position, isSelect);
        this.notifyDataSetChanged();
    }

    static class ViewHolder {
        Button tagBtn;
    }

}
