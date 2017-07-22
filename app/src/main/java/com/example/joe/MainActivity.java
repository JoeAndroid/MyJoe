package com.example.joe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joe.ui.module.custom.activity.BlurActivity;
import com.example.joe.ui.module.custom.activity.PeriscopeLayoutActivity;
import com.example.joe.ui.module.custom.activity.SwipeListActivity;

import java.util.ArrayList;

import eightbitlab.com.blurview.BlurView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    /**
     * 水平方向模糊度
     */
    private static float hRadius = 10;
    /**
     * 竖直方向模糊度
     */
    private static float vRadius = 10;
    /**
     * 模糊迭代度
     */
    private static int iterations = 7;

//    private ImageView image_xuhua;

    private TextView tv;

    private ListView listview;

    private MainAdapter mainAdapter;

    private ArrayList<String> arrayList;
    private BlurView blurView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniview();
        addListener();
        initData();
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.header);
//        image_xuhua.setImageBitmap(bitmap);
//        image_xuhua.setImageBitmap(BlurBitmap.centerImage(bitmap,this));
//        image_xuhua.setImageBitmap(drawableToBitmap(BlurBitmap.BlurImages(bitmap, this)));
//        image_xuhua.setImageBitmap(drawableToBitmap(BoxBlurFilter(bitmap)));
        tv.setText(getClickableSpan());
        tv.setMovementMethod(LinkMovementMethod.getInstance());
//        addUnderLineSpan();
//        addForeColorSpan();
        avoidHintColor(tv);//方法重新设置文字背景为透明色。
    }


    public void iniview() {
//        image_xuhua = (ImageView) findViewById(R.id.image_xuhua);
        tv = (TextView) findViewById(R.id.textview_content);
        listview = (ListView) findViewById(R.id.listview);
    }

    private void addListener() {
        listview.setOnItemClickListener(this);
    }

    private void initData() {
        mainAdapter = new MainAdapter(this);
        mainAdapter.addList(getData());
        listview.setAdapter(mainAdapter);
    }

    private ArrayList<String> getData() {
        arrayList = new ArrayList<>();
        arrayList.add("RefreshLayout上拉刷新");
        arrayList.add("BlurActivity");
        arrayList.add("自定义上拉刷新listview");
        arrayList.add("自定义上拉刷新ViewGroup");
        arrayList.add("PeriscopeLayoutActivity");
        return arrayList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 1) {
            startActivity(new Intent(MainActivity.this, BlurActivity.class));
            /*final Dialog exitDialog = new Dialog(this, R.style.FullScreenDialog);
            LinearLayout ll = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(
                    R.layout.layout_diaolog_blur, null);
            blurView= (BlurView)ll. findViewById(R.id.blurView);
            TextView textview = (TextView) ll.findViewById(R.id.textview);
            WindowManager wm = getWindowManager();
            Display d = wm.getDefaultDisplay();
            textview.getLayoutParams().height=d.getHeight();
            textview.getLayoutParams().width=d.getWidth();
//            blurView.setLayoutParams(p);
            setupBlurView();
           *//* Window w = exitDialog.getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                    WindowManager.LayoutParams.FLAG_BLUR_BEHIND);*//*
           *//* WindowManager.LayoutParams lp = exitDialog.getWindow().getAttributes();
            lp.dimAmount = 0.55f;
            exitDialog.getWindow().setAttributes(lp);
            exitDialog.getWindow() .addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);*//*
            exitDialog.setContentView(ll);
            exitDialog.show();*/
        }
        if (position==2){
            startActivity(new Intent(MainActivity.this, SwipeListActivity.class));
        }
        if (position==4){
            startActivity(new Intent(MainActivity.this, PeriscopeLayoutActivity.class));
        }
    }


    @Override
    public void onClick(View v) {
    }


    private class MainAdapter extends BaseAdapter {

        private Context context;

        private ArrayList<String> list;

        public MainAdapter(Context context) {
            this.context = context;
        }

        public void addList(ArrayList<String> list) {
            this.list = list;
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
            ViewHolder viewHolder = null;
            if (null == convertView) {
                convertView = LayoutInflater.from(context).inflate(R.layout.layout_main_list_item, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(list.get(position));

            return convertView;
        }

        class ViewHolder {
            View view;

            TextView textView;

            public ViewHolder(View view) {
                this.view = view;
                textView = (TextView) view.findViewById(R.id.textview_name);
            }
        }
    }


    /**
     * 可点击的富文本
     *
     * @return
     */
    private SpannableString getClickableSpan() {
        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click Success",
                        Toast.LENGTH_SHORT).show();
            }
        };

        SpannableString spanableInfo = new SpannableString(
                tv.getText().toString());
        int start = 16;
        int end = spanableInfo.length();
        ForegroundColorSpan span = new ForegroundColorSpan(Color.BLUE);
        spanableInfo.setSpan(span, 7, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        UnderlineSpan span2 = new UnderlineSpan();
        spanableInfo.setSpan(span2, 7, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanableInfo.setSpan(new Clickable(l), 7, 11,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanableInfo;
    }

    /**
     * 内部类，用于截获点击富文本后的事件
     *
     * @author pro
     */
    class Clickable extends ClickableSpan implements View.OnClickListener {
        private final View.OnClickListener mListener;

        public Clickable(View.OnClickListener l) {
            mListener = l;
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
//            ds.setColor(Color.argb(255, 54, 92, 124));
            ds.setUnderlineText(true);    //去除超链接的下划线
            ds.clearShadowLayer();
        }
    }

    /**
     * 文字背景颜色
     */
    private void addBackColorSpan() {
        SpannableString spanString = new SpannableString(tv.getText().toString());
        BackgroundColorSpan span = new BackgroundColorSpan(Color.YELLOW);
        spanString.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spanString);
    }

    /**
     * 文字颜色
     */
    private void addForeColorSpan() {
        SpannableString spanString = new SpannableString(tv.getText().toString());
        ForegroundColorSpan span = new ForegroundColorSpan(Color.BLUE);
        spanString.setSpan(span, 7, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spanString);

    }


    /**
     * 字体大小
     */
    private void addFontSpan() {
        SpannableString spanString = new SpannableString(tv.getText().toString());
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(36);
        spanString.setSpan(span, 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spanString);
    }

    /**
     * 下划线
     */
    private void addUnderLineSpan() {
        SpannableString spanString = new SpannableString(tv.getText().toString());
        UnderlineSpan span = new UnderlineSpan();
        spanString.setSpan(span, 7, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spanString);
    }

    /**
     * 会出现文字选中出现淡绿色的背景色现象。如下图1.1。ds.setColor()设定的是span超链接的文本颜色，而不是点击后的颜色，点击后的背景颜色(HighLightColor)属于TextView的属性，Android4.0以上默认是淡绿色，低版本的是黄色。
     *
     * @param view
     */
    private void avoidHintColor(View view) {
        if (view instanceof TextView)
            ((TextView) view).setHighlightColor(getResources().getColor(android.R.color.transparent));
    }

    /**
     * 高斯模糊
     */
    public Drawable BoxBlurFilter(Bitmap bmp) {
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] inPixels = new int[width * height];
        int[] outPixels = new int[width * height];
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bmp.getPixels(inPixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < iterations; i++) {
            blur(inPixels, outPixels, width, height, hRadius);
            blur(outPixels, inPixels, height, width, vRadius);
        }
        blurFractional(inPixels, outPixels, width, height, hRadius);
        blurFractional(outPixels, inPixels, height, width, vRadius);
        bitmap.setPixels(inPixels, 0, width, 0, 0, width, height);
        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
        return drawable;
    }


    public static void blur(int[]
                                    in, int[]
                                    out, int width,
                            int height,
                            float radius) {
        int widthMinus1
                = width - 1;
        int r
                = (int)
                radius;
        int tableSize
                = 2 *
                r + 1;
        int divide[]
                = new int[256 *
                tableSize];

        for (int i
             = 0;
             i < 256 *
                     tableSize; i++)
            divide[i]
                    = i / tableSize;

        int inIndex
                = 0;

        for (int y
             = 0;
             y < height; y++) {
            int outIndex
                    = y;
            int ta
                    = 0,
                    tr = 0,
                    tg = 0,
                    tb = 0;

            for (int i
                 = -r; i <= r; i++) {
                int rgb
                        = in[inIndex + clamp(i, 0,
                        width - 1)];
                ta
                        += (rgb >> 24)
                        & 0xff;
                tr
                        += (rgb >> 16)
                        & 0xff;
                tg
                        += (rgb >> 8)
                        & 0xff;
                tb
                        += rgb & 0xff;
            }

            for (int x
                 = 0;
                 x < width; x++) {
                out[outIndex]
                        = (divide[ta] << 24)
                        | (divide[tr] << 16)
                        |
                        (divide[tg] << 8)
                        | divide[tb];

                int i1
                        = x + r + 1;
                if (i1
                        > widthMinus1)
                    i1
                            = widthMinus1;
                int i2
                        = x - r;
                if (i2
                        < 0)
                    i2
                            = 0;
                int rgb1
                        = in[inIndex + i1];
                int rgb2
                        = in[inIndex + i2];

                ta
                        += ((rgb1 >> 24)
                        & 0xff)
                        - ((rgb2 >> 24)
                        & 0xff);
                tr
                        += ((rgb1 & 0xff0000)
                        - (rgb2 & 0xff0000))
                        >> 16;
                tg
                        += ((rgb1 & 0xff00)
                        - (rgb2 & 0xff00))
                        >> 8;
                tb
                        += (rgb1 & 0xff)
                        - (rgb2 & 0xff);
                outIndex
                        += height;
            }
            inIndex
                    += width;
        }
    }

    public static void blurFractional(int[]
                                              in, int[]
                                              out, int width,
                                      int height,
                                      float radius) {
        radius
                -= (int)
                radius;
        float f
                = 1.0f
                / (1 +
                2 *
                        radius);
        int inIndex
                = 0;

        for (int y
             = 0;
             y < height; y++) {
            int outIndex
                    = y;

            out[outIndex]
                    = in[0];
            outIndex
                    += height;
            for (int x
                 = 1;
                 x < width - 1;
                 x++) {
                int i
                        = inIndex + x;
                int rgb1
                        = in[i - 1];
                int rgb2
                        = in[i];
                int rgb3
                        = in[i + 1];

                int a1
                        = (rgb1 >> 24)
                        & 0xff;
                int r1
                        = (rgb1 >> 16)
                        & 0xff;
                int g1
                        = (rgb1 >> 8)
                        & 0xff;
                int b1
                        = rgb1 & 0xff;
                int a2
                        = (rgb2 >> 24)
                        & 0xff;
                int r2
                        = (rgb2 >> 16)
                        & 0xff;
                int g2
                        = (rgb2 >> 8)
                        & 0xff;
                int b2
                        = rgb2 & 0xff;
                int a3
                        = (rgb3 >> 24)
                        & 0xff;
                int r3
                        = (rgb3 >> 16)
                        & 0xff;
                int g3
                        = (rgb3 >> 8)
                        & 0xff;
                int b3
                        = rgb3 & 0xff;
                a1
                        = a2 + (int)
                        ((a1 + a3) * radius);
                r1
                        = r2 + (int)
                        ((r1 + r3) * radius);
                g1
                        = g2 + (int)
                        ((g1 + g3) * radius);
                b1
                        = b2 + (int)
                        ((b1 + b3) * radius);
                a1
                        *= f;
                r1
                        *= f;
                g1
                        *= f;
                b1
                        *= f;
                out[outIndex]
                        = (a1 << 24)
                        | (r1 << 16)
                        | (g1 << 8)
                        | b1;
                outIndex
                        += height;
            }
            out[outIndex]
                    = in[width - 1];
            inIndex
                    += width;
        }
    }

    public static int clamp(int x,
                            int a,
                            int b) {
        return (x
                < a) ? a : (x > b) ? b : x;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {


        Bitmap bitmap = Bitmap.createBitmap(

                drawable.getIntrinsicWidth(),

                drawable.getIntrinsicHeight(),

                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

                        : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        //canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;

    }
}
