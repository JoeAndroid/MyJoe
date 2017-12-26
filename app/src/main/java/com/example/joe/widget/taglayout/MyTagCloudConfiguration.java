package com.example.joe.widget.taglayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.example.joe.R;

/**
 * @author fyales
 * @since date 11/3/15
 */
public class MyTagCloudConfiguration {

    private static final int DEFAULT_LINE_SPACING = 5;
    private static final int DEFAULT_TAG_SPACING = 10;//默认标签距离
    private static final int DEFAULT_FIXED_COLUMN_SIZE = 3; //默认列数
    private static final int DEFAULT_LINE_SIZE = 2;//默认行数
    private int lineSpacing;
    private int tagSpacing;
    private int columnSize;
    private int lineSize;

    public MyTagCloudConfiguration(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TagCloudLayout);
        try {
            lineSpacing = a.getDimensionPixelSize(R.styleable.TagCloudLayout_lineSpacing, DEFAULT_LINE_SPACING);
            tagSpacing = a.getDimensionPixelSize(R.styleable.TagCloudLayout_tagSpacing, DEFAULT_TAG_SPACING);
            columnSize = a.getInteger(R.styleable.TagCloudLayout_columnSize, DEFAULT_FIXED_COLUMN_SIZE);
            lineSize = a.getInteger(R.styleable.TagCloudLayout_lineSize, DEFAULT_LINE_SIZE);
        } finally {
            a.recycle();
        }
    }

    public int getLineSpacing() {
        return lineSpacing;
    }

    public void setLineSpacing(int lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public int getTagSpacing() {
        return tagSpacing;
    }

    public void setTagSpacing(int tagSpacing) {
        this.tagSpacing = tagSpacing;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public int getLineSize() {
        return lineSize;
    }

    public void setLineSize(int lineSize) {
        this.lineSize = lineSize;
    }
}
