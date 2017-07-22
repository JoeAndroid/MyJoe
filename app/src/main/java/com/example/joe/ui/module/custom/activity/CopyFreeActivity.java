package com.example.joe.ui.module.custom.activity;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joe.R;

public class CopyFreeActivity extends AppCompatActivity {
    private static final String TAG = "SelectTextToCopyActivity";
    private float mScaleFactor = 1;
    private ScaleGestureDetector mScaleDetector;
    private GestureDetector mGestureDetector;
    private TextView text;
    private EditText edit;

    private static final int ZOOM_IN = 4;
    private static final int ZOOM_OUT = 5;
    private final int MAX_ZOOM_IN_SIZE = 60;
    private final int MAX_ZOOM_OUT_SIZE = 20;
    private final int THE_SIZE_OF_PER_ZOOM = 9;
    private float mTextSize = 27;
    private int mZoomMsg = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_free);
        initUi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void button(View view) {
        if (TextUtils.isEmpty(edit.getText())) {
            Toast.makeText(this, "请输入测试内容！",Toast.LENGTH_LONG).show();
        } else
            text.setText(edit.getText());
    }

    @Override
    public boolean onSearchRequested() {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Exit").setIcon(android.R.drawable.btn_star)
                        .setMessage("立即退出程序？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CopyFreeActivity.this.finish();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void initUi() {
        text = (TextView) findViewById(R.id.text);
        edit = (EditText) findViewById(R.id.edit);
        text.setTransformationMethod(HideReturnsTransformationMethod
                .getInstance());
        text.setTextIsSelectable(true);
        mScaleDetector = new ScaleGestureDetector(this, new MyScaleListener());
        mGestureDetector = new GestureDetector(this,
                new GestureDetector.SimpleOnGestureListener() {
                });
        mGestureDetector.setOnDoubleTapListener(null);
//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private Handler mUiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ZOOM_IN:
                    zoomIn();
                    text.invalidate();
                    break;
                case ZOOM_OUT:
                    zoomOut();
                    text.invalidate();
                    break;
                default:
                    break;
            }
        }
    };

    private void zoomIn() {
        mTextSize = mTextSize + THE_SIZE_OF_PER_ZOOM <= MAX_ZOOM_IN_SIZE ? mTextSize
                + THE_SIZE_OF_PER_ZOOM
                : MAX_ZOOM_IN_SIZE;
        if (mTextSize >= MAX_ZOOM_IN_SIZE) {
            mTextSize = MAX_ZOOM_IN_SIZE;
        }
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
    }

    private void zoomOut() {
        mTextSize = mTextSize - THE_SIZE_OF_PER_ZOOM < MAX_ZOOM_OUT_SIZE ? MAX_ZOOM_OUT_SIZE
                : mTextSize - THE_SIZE_OF_PER_ZOOM;
        if (mTextSize <= MAX_ZOOM_OUT_SIZE) {
            mTextSize = MAX_ZOOM_OUT_SIZE;
        }
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
    }

    private class MyScaleListener extends
            ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scale = detector.getScaleFactor();
            if (scale < 0.999999 || scale > 1.00001) {
                mScaleFactor = scale;
            }
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            float scale = detector.getScaleFactor();
            if (mScaleFactor > 1.0) {
                mZoomMsg = ZOOM_IN;
            } else if (mScaleFactor < 1.0) {
                mZoomMsg = ZOOM_OUT;
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        mScaleDetector.onTouchEvent(ev);
        final int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mGestureDetector.onTouchEvent(ev);
                return false;

            case MotionEvent.ACTION_MOVE:
                mGestureDetector.onTouchEvent(ev);
                return false;

            case MotionEvent.ACTION_UP:
                mGestureDetector.onTouchEvent(ev);
                Message msg = Message.obtain();
                msg.what = mZoomMsg;
                mUiHandler.sendMessage(msg);
                mZoomMsg = -1;
                return false;
        }
        return true;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        mScaleDetector.onTouchEvent(ev);
        final int action = ev.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mGestureDetector.onTouchEvent(ev);
                return true;

            case MotionEvent.ACTION_MOVE:
                mGestureDetector.onTouchEvent(ev);
                return true;

            case MotionEvent.ACTION_UP:
                mGestureDetector.onTouchEvent(ev);
                Message msg = Message.obtain();
                msg.what = mZoomMsg;
                mUiHandler.sendMessage(msg);
                mZoomMsg = -1;
                return true;

            case MotionEvent.ACTION_CANCEL:
                mGestureDetector.onTouchEvent(ev);
                return true;

            default:
                if (mGestureDetector.onTouchEvent(ev)) {
                    return true;
                }

                return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUiHandler.removeCallbacksAndMessages(null);
    }
}
