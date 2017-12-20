//package com.example.joe.ui.module.custom.activity;
//
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.drawable.ColorDrawable;
//import android.net.Uri;
//import android.support.v4.util.ArrayMap;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.view.Gravity;
//import android.view.KeyEvent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.AbsListView;
//import android.widget.AdapterView;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.PopupWindow;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.cloudvideo.CloudVideoApplication;
//import com.example.cloudvideo.MainActivity;
//import com.example.cloudvideo.R;
//import com.example.cloudvideo.base.BaseActivity;
//import com.example.cloudvideo.bean.JsonBean;
//import com.example.cloudvideo.bean.PingLunListBean;
//import com.example.cloudvideo.bean.SquareMoreInfoBean;
//import com.example.cloudvideo.contants.Contants;
//import com.example.cloudvideo.contants.ShareTypeCode;
//import com.example.cloudvideo.contants.TongJiTypeCode;
//import com.example.cloudvideo.db.AiTeUserInfoDB;
//import com.example.cloudvideo.module.album.view.activity.AlbumDetailListActivity;
//import com.example.cloudvideo.module.arena.iview.BaseZanPeiView;
//import com.example.cloudvideo.module.arena.presenter.ZanPeiPresenter;
//import com.example.cloudvideo.module.arena.view.activity.TopicDetailActivity;
//import com.example.cloudvideo.module.login.view.activity.LoginActivity;
//import com.example.cloudvideo.module.my.view.activity.UserHomeActivity;
//import com.example.cloudvideo.module.my.view.activity.XiajiaActivity;
//import com.example.cloudvideo.module.share.ShareWenAn;
//import com.example.cloudvideo.module.square.iview.BaseGuanZhuView;
//import com.example.cloudvideo.module.square.iview.BasePingLunView;
//import com.example.cloudvideo.module.square.iview.BaseShouCangView;
//import com.example.cloudvideo.module.square.presenter.GuanZhuPresenter;
//import com.example.cloudvideo.module.square.presenter.PingLunPresenter;
//import com.example.cloudvideo.module.square.presenter.ShouCangPresenter;
//import com.example.cloudvideo.module.square.view.adapter.PingLunAdapter;
//import com.example.cloudvideo.module.square.view.adapter.VideoCloumnsAdapter;
//import com.example.cloudvideo.network.NetWorkConfig;
//import com.example.cloudvideo.network.NetWorkUtil;
//import com.example.cloudvideo.poupwindow.SeeMoreVideoPopupWindow;
//import com.example.cloudvideo.util.JsonBeanUtil;
//import com.example.cloudvideo.util.SPUtils;
//import com.example.cloudvideo.util.ToastAlone;
//import com.example.cloudvideo.util.Utils;
//import com.example.cloudvideo.view.CircleImageView;
//import com.example.cloudvideo.view.myrefreshview.MyRefreshListView;
//import com.example.cloudvideo.view.video.KMediaPlayer;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//import com.lidroid.xutils.exception.HttpException;
//import com.lidroid.xutils.http.ResponseInfo;
//import com.lidroid.xutils.http.callback.RequestCallBack;
//import com.lidroid.xutils.util.LogUtils;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.assist.ImageScaleType;
//import com.umeng.analytics.MobclickAgent;
//
//import org.json.JSONObject;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 视频播放界面 Created by wangxuemei on 16/3/16.
// */
//public class SquareDetailActivity extends BaseActivity implements BaseGuanZhuView, BasePingLunView,
//        BaseShouCangView, BaseZanPeiView, TextWatcher,
//        View.OnKeyListener, View.OnFocusChangeListener, KMediaPlayer.OnScreenChangerListener, AdapterView.OnItemClickListener, KMediaPlayer.OnNextVideoPlayListener {
////    public final static int GET_REQUSET_CODE = 0x33;
//    /**
//     * 登录返回
//     */
//    private final static int GUANZHU_LOGIN_CODE = 0x44;
//    // 头像默认图
//    private static DisplayImageOptions headDisplayImageOptions = new DisplayImageOptions.Builder()
//            // 设置图片可以不放在内存中
//            .cacheInMemory(true)
//            // 设置图片可以放在本地缓存中
//            .cacheOnDisk(true)
//            // 设置图片缩放到目标大小
//            .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
//            // 设置图片以rgb565形式显示
//            .bitmapConfig(Bitmap.Config.RGB_565)
//            // 设置默认加载图
//            .showImageOnLoading(R.drawable.icon_reqiwang_head_moren)
//            // 设置加载失败图
//            .showImageOnFail(R.drawable.icon_reqiwang_head_moren)
//            // 设置URI为空 或URI显示错误图
//            .showImageForEmptyUri(R.drawable.icon_reqiwang_head_moren)
//            // 设置图片下载前可以被重置
//            .resetViewBeforeLoading(true).build();
//    /**
//     * 评论@请求编码
//     */
//    private final int PINGLUN_AITE_CODE = 0x11;
//    private final int LOGIN_CODE = 0x22;
//    private View topView;
//    private View view;
//    //listView头部个人信息
//    private View headerView;
//    //视频描述
//    private TextView textview_describe;
//    //视频名称
//    private TextView textview_videoName;
//    //用户头像
//    private CircleImageView cImageView_head;
//    //是否加v
//    private ImageView imageView_v;
//    //用户名称
//    private TextView textView_userName;
//    //用户职业
////    private TagCloudLayout tagCloudLayout_profession;
//    //关注按钮
//    private ImageView iv_guanzhu;
//    //评论个数
//    private TextView tvPinglunNum;
//    //无评论默认图
//    private TextView tvNoneComment;
//    //返回按钮
//    private ImageButton imageButton_back;
//    //评论布局
//    private LinearLayout linear_bottom;
//    //提示评论输入框
//    private TextView tvPingLun;
//    //发送评论按钮
//    private TextView textview_pinglun_fasong;
//    //评论相对布局
//    private RelativeLayout rLayoutComment;
//    //评论输入
//    private EditText edit_pinglun;
//    private ImageButton imBtnSendCommet;
//    //评论最大数量
//    private TextView tvPinglunMaxNum;
//    //显示的评论数量
//    private TextView tvVideoComment;
//    //视频是否暂停
//    private boolean isPause = false;
//    //是否点击了其它视频item
//    private boolean isClick = false;
//    //屏幕宽度,最后记录宽度
//    private int screenWidth, lastWidth;
//    //标示视频高度是否为屏幕宽度
//    private boolean isScreen = true;
//    //标示视频是否全屏播放
//    private boolean isFullScreen = false;
//    //关注
//    private GuanZhuPresenter guanZhuPresenter;
//    //评论info
//    private SquareMoreInfoBean.CommentInfo commentInfo;
//    //评论adapter
//    private PingLunAdapter pingLunAdapter;
//    private PopupWindow morePopupWindow;
//    //举报
//    private TextView tvJuBao;
//    //回复
//    private TextView tvHuiFu;
//    //删除
//    private TextView tvDel;
//    //取消
//    private TextView tvCancel;
//    private String userId;
//    private int page = 1;
//    //上一个视频id
//    private int lastVideoId = 0;
//    private int videoId = 0;
//    private int competitionId = 0;
//    //擂台名称
//    private String competitionName;
//    //是擂台结束的视频
//    private boolean isFinish;
//    //true为评论 false为回复
//    private boolean isPinglun;
//    //回复评论的位置
//    private int selectPosition;
//    /**
//     * 纪录开始坐标点
//     */
//    private int beforeIndex, oldIndex;
//    /**
//     * 标记是否在删除状态
//     */
//    private boolean isDelete = false, isAitAdd = false;
//    /**
//     * 评论@Map
//     */
//    private ArrayMap<String, int[]> aiTeMap;
//    /**
//     * 删除标示Map,结束的字符串标示，开始的位置
//     */
//    private ArrayMap<String, Integer> deleteMap;
//    /**
//     * aite选择用户列表
//     */
//    private List<ArrayMap<String, String>> aiTeListMaps;
//    // 评论列表
//    private List<SquareMoreInfoBean.CommentInfo> listPingLuns;
//    //视频 返回结果
//    private SquareMoreInfoBean.MoreBean moreBean;
//    //视频详情信息
//    private SquareMoreInfoBean.VideoInfo videoInfo;
//    //其它视频
//    private SquareMoreInfoBean.AlbumOtherVideo albumOtherVideo;
//    //下个视频
//    private SquareMoreInfoBean.VideoInfo nextVideoInfo;
//    //视频用户详情信息
//    private SquareMoreInfoBean.UserInfo userInfo;
//    private RelativeLayout relative_layout;
//    //赞
//    private TextView tvZan;
//    //职业
//    private TextView tv_zhiye;
//    //收藏
//    private ImageView imageShouCang;
//    //举报
//    private ImageView imageShare;
//    //分享
//    private ImageView imageJubaoVideo;
//    //相关视频
//    private LinearLayout lLayoutCloumns;
//    //专辑名称
//    private TextView tvZhuanjiName;
//    //相关视频上面的线条
//    private View view_line;
//    //查看视频上面的横线
//    private View viewLineSeeMore;
//    //查看更多
//    private TextView tvSeeMore;
//    //相关视频adapter
//    private VideoCloumnsAdapter videoCloumnsAdapter;
//    //相关视频 临时数据list
//    private List<SquareMoreInfoBean.AlbumOtherVideoInfo> listCloumns;
//    private Map<String, String> params;
//    private PingLunPresenter pingLunPresenter;
//    private ShouCangPresenter shouCangPresenter;
//    private ZanPeiPresenter zanPeiPresenter;
//    //表示是否有更多评论
//    private boolean isPingLunMore = true;
//    private int intAddPingLunNum = 0;
//    private KMediaPlayer kMediaPlayer;
//    private MyRefreshListView myRefreshListView;
//    //评论数量
//    private int pingLunNumber;
//    //标示视频自动播放
//    private boolean isVideoAtuoPlay = false;
//    private boolean scrollFlag = false;// 标记是否滑动
//    private int lastVisibleItemPosition = 0;// 标记上次滑动位置
//    private int lastVisibleItemCount = 0;//最后的可见数量
//    private int albumId = 0;//专辑ID
//    private int topicId = 0;//专题ID
//    private SeeMoreVideoPopupWindow seeMoreVideoPopupWindow;
//    private RelativeLayout rlayoutDetails;
//    private int videoHeight;
//    //是否是H5吊起的。
//    private boolean isH5Skip;
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (isPause) {
//            isPause = false;
//            kMediaPlayer.onResume();
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        // 暂停
//        isPause = true;
//        kMediaPlayer.onPause();
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        kMediaPlayer.onDestory();
//    }
//
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//    }
//
//    @Override
//    public void initViews() {
//        if (null != this.getIntent().getExtras()) {
//            videoId = this.getIntent().getExtras().getInt("videoId", 0);
//            competitionId = this.getIntent().getExtras().getInt("competitionId", 0);
//            competitionName = this.getIntent().getExtras().getString("competitionName", null);
//            isFinish = this.getIntent().getExtras().getBoolean("isFinish", false);
//            albumId = this.getIntent().getExtras().getInt("albumId", 0);
//            topicId = this.getIntent().getExtras().getInt("topicId", 0);
//        }
//        Intent intent = getIntent();
//        if (null != intent) {
//            String scheme = intent.getScheme();
//            Uri uri = intent.getData();
//            if (uri != null) {
//                isH5Skip = true;
//                String host = uri.getHost();
//                String dataString = intent.getDataString();
//                videoId = Integer.valueOf(uri.getQueryParameter("videoId"));
//                String albumId = uri.getQueryParameter("albumId");
//                String topicId = uri.getQueryParameter("topicId");
//                String queryString = uri.getQuery();
//                String path = uri.getPath();
//                ((CloudVideoApplication) this.getApplicationContext()).initImageLoader();
//            }
//        }
//        //页面来源参数
//        sourceId = videoId;
//        // 设置屏幕常亮
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
//                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        view = LayoutInflater.from(this).inflate(R.layout.activity_square_detail, null);
//        view.setFitsSystemWindows(true);
//        setContentView(view);
//        initMyVideoView();
//        rlayoutDetails = (RelativeLayout) findViewById(R.id.realyout_square_deatil);
//        myRefreshListView = (MyRefreshListView) findViewById(R.id.myRefreshListView_play);
//        //不显示头部
//        myRefreshListView.setisShowHeaderer(false);
//        screenWidth = Utils.getScreenWithAndHeight(this)[0];
//        lastWidth = screenWidth;
//        imageButton_back = (ImageButton) findViewById(R.id.imageButton_back);
//        //赞，收藏，举报，分享
//        relative_layout = (RelativeLayout) findViewById(R.id.relative_layout);
//        tvZan = (TextView) findViewById(R.id.tv_zan);
//        imageShouCang = (ImageView) findViewById(R.id.image_shoucang);
//        imageShare = (ImageView) findViewById(R.id.image_share);
//        imageJubaoVideo = (ImageView) findViewById(R.id.image_jubao_video);
//        //评论相关
//        linear_bottom = (LinearLayout) findViewById(R.id.linear_bottom);
//        tvPingLun = (TextView) findViewById(R.id.tvPinglun);
//        textview_pinglun_fasong = (TextView) findViewById(R.id.textview_pinglun_fasong);
//        rLayoutComment = (RelativeLayout) findViewById(R.id.rLayoutPingLun);
//        edit_pinglun = (EditText) findViewById(R.id.etPinglun);
//        tvPinglunMaxNum = (TextView) findViewById(R.id.tvPinglunMaxNum);
//        imBtnSendCommet = (ImageButton) findViewById(R.id.imBtnSendComment);
//        tvVideoComment = (TextView) findViewById(R.id.tvVideoComment);
//        tvVideoComment.setText("" + 0 + "");
//        initHeadView();
//    }
//
//    /**
//     * 初始化播放管理
//     */
//    public void initMyVideoView() {
//        videoHeight = this.getResources().getDisplayMetrics().widthPixels / 16 * 9;
//        kMediaPlayer = (KMediaPlayer) findViewById(R.id.kMediaPlayer);
//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) kMediaPlayer.getLayoutParams();
//        layoutParams.height = videoHeight;
//        kMediaPlayer.setLayoutParams(layoutParams);
//        kMediaPlayer.setActivity(SquareDetailActivity.this);
//        kMediaPlayer.setOnScreenChangerListener(this);
//        kMediaPlayer.setFullScreenIsShow(true);
//        kMediaPlayer.setOnNextVideoPlayListener(this);
//        //设置播放时长记录参数
//        Map<String, String> params = new HashMap<>();
//        if (albumId != 0) {
//            params.put("type", "2");
//            params.put("sourceId", albumId + "");
//        } else if (topicId != 0) {
//            params.put("type", "3");
//            params.put("sourceId", +topicId + "");
//        } else {
//            if (isClick && lastVideoId != 0) {
//                params.put("type", "1");
//                params.put("sourceId", +lastVideoId + "");
//            } else {
//                params.put("type", "0");
//                params.put("sourceId", +videoId + "");
//            }
//        }
//        kMediaPlayer.setPlayRecordParams(params);
//    }
//
//
//    /**
//     * 初始化头部数据
//     */
//    public void initHeadView() {
//        headerView = LayoutInflater.from(this).inflate(R.layout.layout_square_details_header, null);
//        //用户相关
//        cImageView_head = (CircleImageView) headerView.findViewById(R.id.cImageView_head);
//        imageView_v = (ImageView) headerView.findViewById(R.id.imageView_v);
//        textView_userName = (TextView) headerView.findViewById(R.id.textView_userName);
//        tv_zhiye = (TextView) headerView.findViewById(R.id.tv_zhiye);
////        tagCloudLayout_profession = (TagCloudLayout) headerView.findViewById(R.id.tagCloudLayout_profession);
//        iv_guanzhu = (ImageView) headerView.findViewById(R.id.iv_guanzhu);
//        iv_guanzhu.setImageResource(R.drawable.icon_guanzhu_cansai_video_play);
//        //视频相关
//        textview_describe = (TextView) headerView.findViewById(R.id.textview_describe);
//        textview_videoName = (TextView) headerView.findViewById(R.id.textview_videoName);
//        //评论相关
//        tvPinglunNum = (TextView) headerView.findViewById(R.id.textview_pinglun_number);
//        tvNoneComment = (TextView) headerView.findViewById(R.id.tvNoneComment);
//        tvPinglunNum.setText("(" + 0 + ")");
//        //相关视频
//        lLayoutCloumns = (LinearLayout) headerView.findViewById(R.id.lLayoutCloumns);
//        tvZhuanjiName = (TextView) headerView.findViewById(R.id.tvZhuanjiName);
//        view_line = headerView.findViewById(R.id.view_line);
//        viewLineSeeMore = headerView.findViewById(R.id.view_line_seemore);
//        tvSeeMore = (TextView) headerView.findViewById(R.id.tvSeeMore);
//        myRefreshListView.addHeaderView(headerView);
//        //设置显示头部
//        listPingLuns = new ArrayList<SquareMoreInfoBean.CommentInfo>();
//        pingLunAdapter = new PingLunAdapter(SquareDetailActivity.this, listPingLuns, true);
//        myRefreshListView.setAdapter(pingLunAdapter);
//    }
//
//
//    @Override
//    public void initData() {
//
//        guanZhuPresenter = new GuanZhuPresenter(this, this);
//        aiTeMap = new ArrayMap<String, int[]>();
//        deleteMap = new ArrayMap<String, Integer>();
//        aiTeListMaps = new ArrayList<ArrayMap<String, String>>();
//        listCloumns = new ArrayList<SquareMoreInfoBean.AlbumOtherVideoInfo>();
//        commentInfo = new SquareMoreInfoBean.CommentInfo();
//        pingLunPresenter = new PingLunPresenter(this, this);
//        shouCangPresenter = new ShouCangPresenter(this, this);
//        zanPeiPresenter = new ZanPeiPresenter(this, this);
//        intAddPingLunNum = 0;
//        getVideoDetailByServer();
//
//    }
//
//    @Override
//    public void addLisenter() {
//        //查看更多
//        rLayoutComment.setOnClickListener(this);
//        rlayoutDetails.setOnClickListener(this);
//        tvSeeMore.setOnClickListener(this);
//        imageButton_back.setOnClickListener(this);
//        iv_guanzhu.setOnClickListener(this);
//        cImageView_head.setOnClickListener(this);
//        tvZhuanjiName.setOnClickListener(this);
//        textview_pinglun_fasong.setOnClickListener(this);
//        tvPingLun.setOnClickListener(this);
//        imBtnSendCommet.setOnClickListener(this);
//        //增加输入监听
//        edit_pinglun.addTextChangedListener(this);
//        //增加删除监听
//        edit_pinglun.setOnKeyListener(this);
//        edit_pinglun.setOnFocusChangeListener(this);
//        tvZan.setOnClickListener(this);
//        tvVideoComment.setOnClickListener(this);
//        imageShouCang.setOnClickListener(this);
//        imageShare.setOnClickListener(this);
//        imageJubaoVideo.setOnClickListener(this);
//        myRefreshListView.setOnItemClickListener(this);
//        myRefreshListView.setOnRefreshListenter(new MyRefreshListView.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//
//            @Override
//            public void onLoadeMore() {
//                page++;
//                getPunLunListByServer();
//            }
//        });
////        //设置监听
////        myRefreshListView.setOnMyScrollChangeListener(new MyRefreshListView.OnMyScrollChangeListener() {
////            @Override
////            public void onUpScroll() {
////                colosePingLun();
////                if (linear_bottom.getVisibility() == View.VISIBLE) {
////                    linear_bottom.setVisibility(View.GONE);
////                }
////            }
////
////            @Override
////            public void onDownScroll() {
////                colosePingLun();
////                //显示评论获取焦点
////                if (linear_bottom.getVisibility() == View.GONE) {
////                    linear_bottom.setVisibility(View.VISIBLE);
////                }
////            }
////        });
//        myRefreshListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                // TODO Auto-generated method stub
//                switch (scrollState) {
//                    // 当不滚动时
//                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:// 是当屏幕停止滚动时
//                        scrollFlag = false;
//                        break;
//                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:// 滚动时
//                        scrollFlag = true;
//                        colosePingLun();
//                        if (linear_bottom.getVisibility() == View.GONE) {
//                            linear_bottom.setVisibility(View.VISIBLE);
//                        }
//                        break;
//                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:// 是当用户由于之前划动屏幕并抬起手指，屏幕产生惯性滑动时
//                        scrollFlag = false;
//                        colosePingLun();
//                        if (linear_bottom.getVisibility() == View.GONE) {
//                            linear_bottom.setVisibility(View.VISIBLE);
//                        }
//                        break;
//                }
//            }
//
//            /**
//             * firstVisibleItem：当前能看见的第一个列表项ID（从0开始）
//             * visibleItemCount：当前能看见的列表项个数（小半个也算） totalItemCount：列表项共数
//             */
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem,
//                                 int visibleItemCount, int totalItemCount) {
//                // 当开始滑动且ListView底部的Y轴点超出屏幕最大范围时，显示或隐藏顶部按
////                if (scrollFlag) {
////                    if (firstVisibleItem > lastVisibleItemPosition) {// 上滑
////                        colosePingLun();
////                        if (linear_bottom.getVisibility() == View.VISIBLE) {
////                            linear_bottom.setVisibility(View.GONE);
////                        }
////                    }
////                    if (firstVisibleItem < lastVisibleItemPosition) {// 下滑
////                        colosePingLun();
////                        //显示评论获取焦点
////                        if (linear_bottom.getVisibility() == View.GONE) {
////                            linear_bottom.setVisibility(View.VISIBLE);
////                        }
////                    }
////                    if (firstVisibleItem == lastVisibleItemPosition) {
////                        return;
////                    }
////                    lastVisibleItemCount = visibleItemCount;
////                    lastVisibleItemPosition = firstVisibleItem;
////                }
//            }
//        });
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        //选择 弹出更多选择
//
//        if (null != listPingLuns && listPingLuns.size() > position - 1) {
//            selectPosition = position - 1;
//            initPopupWindow();
//            morePopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.imageButton_back://返回
//                if (isFullScreen) {
//                    //调整为非全屏
//                    kMediaPlayer.setOnNoFullScreen();
//                } else {
//                    if (isH5Skip) {
//                        startActivity(new Intent(this, MainActivity.class));
//                    }
//                    SquareDetailActivity.this.finish();
//                }
//                break;
//            case R.id.image_share://分享
//                showSharePopupWindow();
//                break;
//            case R.id.cImageView_head://头像跳转到用户
//                if (null == userInfo) {
//                    return;
//                }
//                startActivity(new Intent(SquareDetailActivity.this, UserHomeActivity.class)
//                        .putExtra("userInfo", userInfo));
//                break;
//            case R.id.tvZhuanjiName:
//                if (null != albumOtherVideo && albumOtherVideo.getType() == 1)
//                    startActivity(new Intent(SquareDetailActivity.this, AlbumDetailListActivity.class)
//                            .putExtra("albumId", albumOtherVideo.getAlbumId()));
//                break;
//            case R.id.iv_guanzhu://关注
//                userId = SPUtils.getInstance(this).getData(Contants.LOGIN_USER, null);
//                if (null != userId && !TextUtils.isEmpty(userId.trim())) {
//                    if (null != userInfo) {
//                        if (!userInfo.isFocus()) {//true已关注 false未关注
//                            guanZhuToServer();
//                        } else {
//                            showSureDialog();
//                        }
//                    }
//                } else {
//                    startActivityForResult(new Intent(SquareDetailActivity.this, LoginActivity.class), GUANZHU_LOGIN_CODE);
//                }
//                break;
//            case R.id.tvPinglun://显示评论
//                MobclickAgent.onEvent(SquareDetailActivity.this, TongJiTypeCode.CLICK_COMMENTS_VIDEO);
//                NetWorkUtil.getInitialize().getLogEvent(SquareDetailActivity.this, TongJiTypeCode.CLICK_COMMENTS_VIDEO);
//                isPinglun = true;
//                rLayoutComment.setVisibility(View.VISIBLE);
//                tvPinglunMaxNum.setText("0/150");
//                edit_pinglun.setHint("我来写点评论...");
//                edit_pinglun.setFocusable(true);
//                edit_pinglun.requestFocus();
//                //显示键盘
//                Utils.showJianPan(SquareDetailActivity.this, edit_pinglun);
//                break;
//            case R.id.imBtnSendComment://发送评论
//                // 评论按钮
//                userId = SPUtils.getInstance(this).getData(Contants.LOGIN_USER, null);
//                if (null != userId && !TextUtils.isEmpty(userId.trim())) {
//                    addPingLunToServer();
//                } else {
//                    startActivityForResult(new Intent(this, LoginActivity.class), GUANZHU_LOGIN_CODE);
//                }
//                break;
//            case R.id.textview_pinglun_fasong:
//                MobclickAgent.onEvent(SquareDetailActivity.this, TongJiTypeCode.CLICK_COMMENTS_VIDEO);
//                NetWorkUtil.getInitialize().getLogEvent(SquareDetailActivity.this, TongJiTypeCode.CLICK_COMMENTS_VIDEO);
//                isPinglun = true;
//                rLayoutComment.setVisibility(View.VISIBLE);
//                tvPinglunMaxNum.setText("0/150");
//                edit_pinglun.setHint("我来写点评论...");
//                edit_pinglun.setFocusable(true);
//                edit_pinglun.requestFocus();
//                //显示键盘
//                Utils.showJianPan(SquareDetailActivity.this, edit_pinglun);
//                break;
//            case R.id.tv_zan://点赞
//                userId = SPUtils.getInstance(SquareDetailActivity.this).getData(Contants.LOGIN_USER,
//                        null);
//                // 判断用户
//                if (null != userId && !TextUtils.isEmpty(userId.trim())) {
//                    tvZan.setEnabled(false);
//                    if (tvZan.isSelected()) {
//                        cancleZanToServer();
//                    } else {
//                        zanToServer();
//                    }
//                } else {
//                    startActivityForResult(new Intent(SquareDetailActivity.this, LoginActivity.class), GUANZHU_LOGIN_CODE);
//                }
//                break;
//            case R.id.image_shoucang:
//                //收藏
//                userId = SPUtils.getInstance(SquareDetailActivity.this).getData(Contants.LOGIN_USER,
//                        null);
//                // 判断用户
//                if (null != userId && !TextUtils.isEmpty(userId.trim())) {
//                    if (imageShouCang.isSelected()) {
//                        quXiaoShouCangToServer();
//                    } else {
//                        shouCangToServer();
//                    }
//                } else {
//                    startActivityForResult(new Intent(SquareDetailActivity.this, LoginActivity.class), GUANZHU_LOGIN_CODE);
//                }
//                break;
//            case R.id.image_jubao_video:
//                //举报视频
//                userId = SPUtils.getInstance(SquareDetailActivity.this).getData(Contants.LOGIN_USER,
//                        null);
//                // 判断用户
//                if (null != userId && !TextUtils.isEmpty(userId.trim())) {
//                    showJubaoDialog();
//                } else {
//                    startActivityForResult(new Intent(SquareDetailActivity.this, LoginActivity.class), GUANZHU_LOGIN_CODE);
//                }
//                break;
//            case R.id.tvSeeMore:
//                if (null != albumOtherVideo) {
//                    if (1 == albumOtherVideo.getType()) {
//                        //专辑查看更多
//                        if (0 != albumOtherVideo.getAlbumId()) {
//                            seeMoreVideoPopupWindow = new SeeMoreVideoPopupWindow(SquareDetailActivity.this, albumOtherVideo.getAlbumId(), videoInfo.getUserId(), videoId);
//                            seeMoreVideoPopupWindow.setOnVideoSelectedListener(new SeeMoreVideoPopupWindow.OnVideoSelectedListener() {
//                                @Override
//                                public void onVideoSelected(int albumId, int videoId) {
//                                    isClick = true;
//                                    isPause = false;
//                                    page = 1;
//                                    lastVideoId = SquareDetailActivity.this.videoId;
//                                    SquareDetailActivity.this.albumId = albumId;
//                                    SquareDetailActivity.this.videoId = videoId;
//                                    kMediaPlayer.stop();
//                                    if (null != listPingLuns) {
//                                        listPingLuns.clear();
//                                        pingLunAdapter.notifyDataSetChanged();
//                                    }
//                                    getVideoDetailByServer();
//                                }
//                            });
//                            seeMoreVideoPopupWindow.showAsDropDown(kMediaPlayer, 0, 0);
//                        } else {
//                            ToastAlone.showToast(this, "获取更多失败", Toast.LENGTH_LONG);
//                        }
//                    } else {
//                        //进入话题
//                        Intent intent = new Intent(SquareDetailActivity.this, TopicDetailActivity.class);
//                        intent.putExtra("topicName", albumOtherVideo.getTopicName());
//                        intent.putExtra("topicId", albumOtherVideo.getTopicId());
//                        startActivity(intent);
//                    }
//                }
//                break;
//            case R.id.tvVideoComment:
//                myRefreshListView.setSelection(1);
//                //显示评论
//                if (rLayoutComment.getVisibility() != View.VISIBLE) {
//                    if (linear_bottom.getVisibility() == View.GONE)
//                        linear_bottom.setVisibility(View.VISIBLE);
//                }
//                break;
//        }
//
//    }
//
//    /**
//     * 举报提示
//     */
//
//    public void showSureDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("提示");
//        builder.setMessage("确认要取消关注“" + userInfo.getNickName() + "”");
//        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                quXiaoGuanZhuToServer();
//            }
//
//        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//
//        });
//        builder.create().show();
//    }
//
//    /**
//     * 分享
//     */
//    private void showSharePopupWindow() {
//        //分享
//        if (null != kMediaPlayer) {
//            kMediaPlayer.setComeBackFromShare(true);
//        }
//        if (-1 != competitionId) {
//            if (videoInfo.isWinnerVideo() && userId.equals(String.valueOf(userInfo.getId()))) {
//                // 分享弹出框 擂主分享自己的擂主视频
//                ShareWenAn.getInstance()
//                        .setData(SquareDetailActivity.this, view, ShareTypeCode.ZIJI_LEIZHU_VIDEO, userId, videoInfo.getId() + "", competitionId, null)
//                        .getShareText();
//            } else if (videoInfo.isWinnerVideo()) {
//                //分享擂主视频
//                ShareWenAn.getInstance()
//                        .setData(SquareDetailActivity.this, view, ShareTypeCode.TA_LEIZHU_VIDEO, userId, videoInfo.getId() + "", competitionId, null)
//                        .getShareText();
//            } else {
//                //不是擂主视频
//                if (null != competitionName && !TextUtils.isEmpty(competitionName.trim())) {
//                    ShareWenAn.getInstance()
//                            .setData(SquareDetailActivity.this, view, ShareTypeCode.COMPETITION_VIDEO, userId, videoInfo.getId() + "", competitionId, null)
//                            .getShareText();
//                } else {
//                    Map<String, String> stringMap = new ArrayMap<>();
//                    stringMap.put("type", ShareTypeCode.SQUARE_VIDEO + "");
//                    if (0 != videoInfo.getId()) {
//                        stringMap.put("videoId", videoInfo.getId() + "");
//                    }
//                    if (0 != topicId) {
//                        stringMap.put("topicId", topicId + "");
//                    }
//                    if (0 != albumId) {
//                        stringMap.put("albumId", albumId + "");
//                    }
//                    ShareWenAn.getInstance()
//                            .setData(this, view, stringMap)
//                            .getShareText();
//                }
//            }
//        } else {
//            ShareWenAn.getInstance()
//                    .setData(SquareDetailActivity.this, view, ShareTypeCode.SQUARE_VIDEO, null, videoId + "", 0, null)
//                    .getShareText();
//        }
//    }
//
//    /**
//     * 初始化pop 删除  举报   回复
//     */
//    private void initPopupWindow() {
//
//        View popView = LayoutInflater.from(this).inflate(R.layout.layout_detail_huifu_popupwindow, null);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT);
//        popView.setLayoutParams(lp);
//        popView.setFitsSystemWindows(true);
//        morePopupWindow = new PopupWindow(popView,
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT, true);
//        // 设置焦点
//        morePopupWindow.setFocusable(true);
//        // 设置显示动画效果
//        morePopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
//        // 设置点击外部消失
//        morePopupWindow.setOutsideTouchable(false);
//        // 设置无背景
//        morePopupWindow.setBackgroundDrawable(new ColorDrawable(0x88000000));
//        // 举报
//        tvJuBao = (TextView) popView.findViewById(R.id.tvJuBao);
//        // 收藏按钮
//        tvHuiFu = (TextView) popView.findViewById(R.id.tvHuiFu);
//        tvJuBao = (TextView) popView.findViewById(R.id.tvJuBao);
//        tvDel = (TextView) popView.findViewById(R.id.tvDel);
//        tvCancel = (TextView) popView.findViewById(R.id.tvCancel);
//        userId = SPUtils.getInstance(SquareDetailActivity.this).getData(Contants.LOGIN_USER, null);
//        if (null != listPingLuns && listPingLuns.size() > 0) {
//            if (!TextUtils.isEmpty(userId) && null != listPingLuns.get(selectPosition).getUserId()) {
//                if (userId.equals(String.valueOf(listPingLuns.get(selectPosition).getUserId()))) {
//                    tvDel.setVisibility(View.VISIBLE);
//                    tvHuiFu.setVisibility(View.GONE);
//                    tvJuBao.setVisibility(View.GONE);
//                }
//            }
//        }
//        tvCancel.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                morePopupWindow.dismiss();
//            }
//        });
//        //回复
//        tvHuiFu.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                MobclickAgent.onEvent(SquareDetailActivity.this, TongJiTypeCode.CLICK_PUSH);
//                NetWorkUtil.getInitialize().getLogEvent(SquareDetailActivity.this, TongJiTypeCode.CLICK_PUSH);
//                //回复
//                userId = SPUtils.getInstance(SquareDetailActivity.this).getData(Contants.LOGIN_USER,
//                        null);
//                // 判断用户
//                if (null != userId && !TextUtils.isEmpty(userId.trim())) {
//                    morePopupWindow.dismiss();
////                    iv_pinglun.setVisibility(View.GONE);
//                    rLayoutComment.setVisibility(View.VISIBLE);
//                    isPinglun = false;
//                    if (null != listPingLuns.get(selectPosition).getUserName() && !TextUtils.isEmpty(listPingLuns.get(selectPosition).getUserName())) {
//                        edit_pinglun.setHint("回复：" + listPingLuns.get(selectPosition).getUserName());
//                    }
//                    // 弹出键盘
//                    edit_pinglun.setFocusable(true);
//                    edit_pinglun.requestFocus();
//                    edit_pinglun.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            //弹出键盘
//                            Utils.showJianPan(SquareDetailActivity.this, edit_pinglun);
//                        }
//                    });
//                } else {
//                    startActivityForResult(new Intent(SquareDetailActivity.this, LoginActivity.class), GUANZHU_LOGIN_CODE);
//                }
//            }
//        });
//        //举报
//        tvJuBao.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                //举报
//                userId = SPUtils.getInstance(SquareDetailActivity.this).getData(Contants.LOGIN_USER,
//                        null);
//                // 判断用户
//                if (null != userId && !TextUtils.isEmpty(userId.trim())) {
//                    // 举报人
//                    morePopupWindow.dismiss();
//                    juBaoPersonToServer();
//                } else {
//                    startActivityForResult(new Intent(SquareDetailActivity.this, LoginActivity.class), GUANZHU_LOGIN_CODE);
//                }
//            }
//        });
//        //删除
//        tvDel.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                morePopupWindow.dismiss();
//                //删除
//                deletePingLunToServer();
//            }
//        });
//    }
//
//    @Override
//    public void onPlayVideo(int videoId) {
//        isVideoAtuoPlay = true;
//        isClick = true;
//        isPause = false;
//        page = 1;
//        lastVideoId = SquareDetailActivity.this.videoId;
//        SquareDetailActivity.this.videoId = videoId;
//        kMediaPlayer.stop();
//        if (null != listPingLuns) {
//            listPingLuns.clear();
//            pingLunAdapter.notifyDataSetChanged();
//        }
//        getVideoDetailByServer();
//    }
//
//    @Override
//    public void onVideoFullScreen() {
//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) kMediaPlayer.getLayoutParams();
//        layoutParams.height = RelativeLayout.LayoutParams.MATCH_PARENT;
//        kMediaPlayer.setLayoutParams(layoutParams);
//        isFullScreen = true;
//        linear_bottom.setVisibility(View.GONE);
//        relative_layout.setVisibility(View.GONE);
//        colosePingLun();
//    }
//
//    @Override
//    public void onVideoNoFullScreen() {
//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) kMediaPlayer.getLayoutParams();
//        layoutParams.height = videoHeight;
//        kMediaPlayer.setLayoutParams(layoutParams);
//        isFullScreen = false;
//        linear_bottom.setVisibility(View.VISIBLE);
//        relative_layout.setVisibility(View.VISIBLE);
//    }
//
//    /**
//     * 提交评论接口
//     */
//    public void addPingLunToServer() {
//        // 判断是否有网络链接
//        if (Contants.NETWORK_NONE == Utils.getNetWorkStatus(this)) {
//            ToastAlone.showToast(this, "无网络链接", Toast.LENGTH_LONG);
//            return;
//        }
//        if (null == edit_pinglun.getText()) {
//            ToastAlone.showToast(this, "评论内容不能为空", Toast.LENGTH_LONG);
//            return;
//        }
//        String content = edit_pinglun.getText().toString();
//        if (null == content || TextUtils.isEmpty(content.trim())) {
//            ToastAlone.showToast(this, "评论内容不能为空", Toast.LENGTH_LONG);
//            return;
//        }
//        // 设置请求参数
//        params = new HashMap<String, String>();
//        //获取@列表json
//        String json = getAiteJson();
//        //判断是否为空
//        if (!TextUtils.isEmpty(json)) {
//            params.put("atUserData", json);
//        }
//        // videoId=视频Id&userId=当前登陆用户Id&content=测试回复内容&replyUserId=回复用户Id&replyCommId=回复评论Id
//        // 视频Id
//        params.put("videoId", videoId + "");
//        // 当前登录用户id
//        String userId = SPUtils.getInstance(this).getData(Contants.LOGIN_USER, null);
//        if (null != userId && !TextUtils.isEmpty(userId.trim())) {
//            params.put("userId", userId);
//        }
//        // 回复内容
//        params.put("content", content);
//        if (!isPinglun) {//回复加上这两个参数
//            if (null != listPingLuns || listPingLuns.size() != 0) {
//                params.put("replyCommId", listPingLuns.get(selectPosition).getId() + "");
//                params.put("replyUserId", listPingLuns.get(selectPosition).getUserId() + "");
//            }
//        }
//        pingLunPresenter.addPingLunToServer(params);
//    }
//
//    /**
//     * 删除评论
//     */
//    public void deletePingLunToServer() {
//        // 判断是否有网络链接
//        if (Contants.NETWORK_NONE == Utils.getNetWorkStatus(this)) {
//            ToastAlone.showToast(this, "无网络链接", Toast.LENGTH_LONG);
//            return;
//        }
//        // 设置请求参数
//        params = new HashMap<String, String>();
//        //评论id
//        params.put("commentId", listPingLuns.get(selectPosition).getId() + "");
//        // 回复内容
//        pingLunPresenter.deletePingLunToServer(params);
//    }
//
//    /**
//     * 编辑组织@用户列表json
//     */
//    public String getAiteJson() {
//        String json = null;
//        if (null != aiTeListMaps && aiTeListMaps.size() > 0) {
//            List<ArrayMap<String, String>> aiteMaps = new ArrayList<ArrayMap<String, String>>();
//            for (int i = 0; i < aiTeListMaps.size(); i++) {
//                String nichName = "@" + aiTeListMaps.get(i).get("nickName");
//                String pingLunText = edit_pinglun.getText().toString().trim();
//                //判断是否@用户有编辑情况
//                if (pingLunText.contains(nichName)) {
//                    //如果用户有编辑，则除去该用户
//                    aiteMaps.add(aiTeListMaps.get(i));
//                }
//            }
//            //转换为json
//            if (null != aiteMaps && aiteMaps.size() > 0) {
//                json = new GsonBuilder().serializeNulls().create().toJson(aiteMaps);
//            }
//        }
//        return json;
//    }
//
//    /**
//     * 获取评论列表信息
//     */
//    public void getPunLunListByServer() {
//        if (Contants.NETWORK_NONE == Utils.getNetWorkStatus(this)) {
//            ToastAlone.showToast(this, "无网络链接", Toast.LENGTH_LONG);
//            if (myRefreshListView.isRefreshing()) {
//                myRefreshListView.onRefreshComplete();
//            }
//            if (page > 1) {
//                page--;
//            }
//            return;
//
//        }
//        // 设置请求参数
//        params = new HashMap<String, String>();
//        // banner
//        params.put("videoId", videoId + "");
//        //页数
//        params.put("page", page + "");
//        //每页显示多少条Contants.PAGE_NUMBER
//        params.put("pageSize", Contants.PAGE_NUMBER + "");
//        pingLunPresenter.getPingLunListByServer(params);
//
//    }
//
//    /**
//     * 向服务器发送赞信息
//     */
//    private void zanToServer() {
//        // 如果无网络链接
//        if (Contants.NETWORK_NONE == Utils.getNetWorkStatus(SquareDetailActivity.this)) {
//            ToastAlone.showToast(SquareDetailActivity.this, "无网络链接", Toast.LENGTH_LONG);
//            return;
//        }
//        // 设置请求参数
//        params = new HashMap<String, String>();
//        // 视频id
//        params.put("videoId", videoId + "");
//        zanPeiPresenter.zanToServer(params);
//    }
//
//    /**
//     * 取消赞接口
//     */
//    private void cancleZanToServer() {
//        // 如果无网络链接
//        if (Contants.NETWORK_NONE == Utils.getNetWorkStatus(SquareDetailActivity.this)) {
//            ToastAlone.showToast(SquareDetailActivity.this, "无网络链接", Toast.LENGTH_LONG);
//            return;
//        }
//        // 设置请求参数
//        params = new HashMap<String, String>();
//
//        // 用户id
//        userId = SPUtils.getInstance(SquareDetailActivity.this).getData(Contants.LOGIN_USER, null);
//        if (null != userId && !TextUtils.isEmpty(userId)) {
//            params.put("userId", userId);
//        }
//        // 视频id
//        params.put("videoId", videoId + "");
//        zanPeiPresenter.cancelZanToServer(params);
//    }
//
//    /**
//     * 收藏
//     */
//    private void shouCangToServer() {
//        // 如果无网络链接
//        if (Contants.NETWORK_NONE == Utils.getNetWorkStatus(SquareDetailActivity.this)) {
//            ToastAlone.showToast(SquareDetailActivity.this, "无网络链接", Toast.LENGTH_LONG);
//            return;
//        }
//        // 设置请求参数
//        params = new HashMap<String, String>();
//        // 用户id
//        userId = SPUtils.getInstance(SquareDetailActivity.this).getData(Contants.LOGIN_USER, null);
//        if (null != userId && !TextUtils.isEmpty(userId.trim())) {
//            params.put("userId", userId);
//        }
//        // 视频id
//        params.put("videoId", videoId + "");
//        shouCangPresenter.shouCangToServer(params);
//    }
//
//    /**
//     * 取消收藏
//     */
//    private void quXiaoShouCangToServer() {
//        // TODO Auto-generated method stub
//        // 如果无网络链接
//        if (Contants.NETWORK_NONE == Utils.getNetWorkStatus(SquareDetailActivity.this)) {
//            ToastAlone.showToast(SquareDetailActivity.this, "无网络链接", Toast.LENGTH_LONG);
//            return;
//        }
//        // 设置请求参数
//        params = new HashMap<String, String>();
//        // 用户id
//        userId = SPUtils.getInstance(SquareDetailActivity.this).getData(Contants.LOGIN_USER, null);
//        if (null != userId && !TextUtils.isEmpty(userId.trim())) {
//            params.put("userId", userId);
//        }
//        // 视频id
//        params.put("videoId", videoId + "");
//        shouCangPresenter.cancleShouCangToServer(params);
//    }
//
//    /**
//     * 举报
//     */
//    private void juBaoToServer() {
//        // 如果无网络链接
//        if (Contants.NETWORK_NONE == Utils.getNetWorkStatus(SquareDetailActivity.this)) {
//            ToastAlone.showToast(SquareDetailActivity.this, "无网络链接", Toast.LENGTH_LONG);
//            return;
//        }
//        progressDialog = ProgressDialog.show(SquareDetailActivity.this, "稍后", "正在提交举报信息,请稍后...");
//        progressDialog.setOnDismissListener(this);
//        progressDialog.show();
//        // 设置请求参数
//        params = new HashMap<String, String>();
//        // 用户id
//        userId = SPUtils.getInstance(this).getData(Contants.LOGIN_USER, null);
//        if (null != userId && !TextUtils.isEmpty(userId.trim())) {
//            params.put("userId", userId);
//        }
//        if (null == videoInfo) {
//            return;
//        }
//        // 视频id
//        params.put("videoId", videoInfo.getId() + "");
//        try {
//            NetWorkUtil.getInitialize().sendPostRequest(SquareDetailActivity.this, NetWorkConfig.JUBAO_VIDEO_TO_SERVER, params, new RequestCallBack<String>() {
//                @Override
//                public void onSuccess(ResponseInfo<String> responseInfo) {
//
//                    // TODO Auto-generated method stub
//                    progressDialog.cancel();
//                    // 判断返回参数是否为空
//                    if (null != responseInfo) {
//                        String json = responseInfo.result;
//                        LogUtils.e("json-->" + json);
//                        if (null != json && !TextUtils.isEmpty(json.trim())) {
//                            try {
//                                JSONObject jsonObject = new JSONObject(json);
//                                String code = jsonObject.optString("code");
//                                String msg = jsonObject.optString("msg");
//                                String result = jsonObject.optString("result");
//                                // 判断是否请求成功
//                                if (null != code && "0".equals(code.trim())) {
//                                    // 举报成功
//                                    ToastAlone.showToast(SquareDetailActivity.this,
//                                            "举报成功", Toast.LENGTH_LONG);
//                                } else {
//                                    // 请求失败,显示错误信息
//                                    if (null != msg && !TextUtils.isEmpty(msg.trim())) {
//                                        ToastAlone.showToast(
//                                                SquareDetailActivity.this, msg,
//                                                Toast.LENGTH_LONG);
//                                    } else {
//                                        ToastAlone.showToast(
//                                                SquareDetailActivity.this, "请求失败",
//                                                Toast.LENGTH_LONG);
//                                    }
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                ToastAlone.showToast(SquareDetailActivity.this,
//                                        "请求失败", Toast.LENGTH_LONG);
//                            }
//                        } else {
//                            ToastAlone.showToast(SquareDetailActivity.this, "请求失败",
//                                    Toast.LENGTH_LONG);
//                        }
//                    } else {
//                        ToastAlone.showToast(SquareDetailActivity.this, "请求失败",
//                                Toast.LENGTH_LONG);
//                    }
//
//                }
//
//                @Override
//                public void onFailure(HttpException e, String s) {
//                    // TODO Auto-generated method stub
//                    progressDialog.cancel();
//                    ToastAlone.showToast(SquareDetailActivity.this, "请求失败",
//                            Toast.LENGTH_LONG);
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            progressDialog.cancel();
//            ToastAlone.showToast(SquareDetailActivity.this, "请求失败", Toast.LENGTH_LONG);
//        }
//    }
//
//    /**
//     * 举报人
//     */
//    private void juBaoPersonToServer() {
//        // 如果无网络链接
//        if (Contants.NETWORK_NONE == Utils.getNetWorkStatus(SquareDetailActivity.this)) {
//            ToastAlone.showToast(SquareDetailActivity.this, "无网络链接", Toast.LENGTH_LONG);
//            return;
//        }
//        progressDialog = ProgressDialog.show(SquareDetailActivity.this, "稍后", "正在提交举报信息,请稍后...");
//        progressDialog.setOnDismissListener(this);
//        progressDialog.show();
//
//        // 设置请求参数
//        params = new HashMap<String, String>();
//        // 用户id
//        userId = SPUtils.getInstance(this).getData(Contants.LOGIN_USER, null);
//        if (null != userId && !TextUtils.isEmpty(userId.trim())) {
//            params.put("userId", userId);
//        }
//        //评论id
//        params.put("commentId", listPingLuns.get(selectPosition).getId() + "");
//        // 1:视频  2：banner 3:举报头条评论
//        params.put("type", 1 + "");
//        try {
//            NetWorkUtil.getInitialize().sendPostRequest(SquareDetailActivity.this, NetWorkConfig.JUBAO_PERSON_TO_SERVER, params, new RequestCallBack<String>() {
//                @Override
//                public void onSuccess(ResponseInfo<String> responseInfo) {
//                    // TODO Auto-generated method stub
//                    progressDialog.cancel();
//                    // 判断返回参数是否为空
//                    if (null != responseInfo) {
//                        String json = responseInfo.result;
//                        LogUtils.e("json-->" + json);
//                        if (null != json && !TextUtils.isEmpty(json.trim())) {
//                            try {
//                                JSONObject jsonObject = new JSONObject(json);
//                                String code = jsonObject.optString("code");
//                                String msg = jsonObject.optString("msg");
//                                String result = jsonObject.optString("result");
//                                // 判断是否请求成功
//                                if (null != code && "0".equals(code.trim())) {
//                                    // 举报成功
//                                    ToastAlone.showToast(SquareDetailActivity.this,
//                                            "举报成功", Toast.LENGTH_LONG);
//                                } else {
//                                    // 请求失败,显示错误信息
//                                    if (null != msg && !TextUtils.isEmpty(msg.trim())) {
//                                        ToastAlone.showToast(
//                                                SquareDetailActivity.this, msg,
//                                                Toast.LENGTH_LONG);
//                                    } else {
//                                        ToastAlone.showToast(
//                                                SquareDetailActivity.this, "请求失败",
//                                                Toast.LENGTH_LONG);
//                                    }
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                ToastAlone.showToast(SquareDetailActivity.this,
//                                        "请求失败", Toast.LENGTH_LONG);
//                            }
//                        } else {
//                            ToastAlone.showToast(SquareDetailActivity.this, "请求失败",
//                                    Toast.LENGTH_LONG);
//                        }
//                    } else {
//                        ToastAlone.showToast(SquareDetailActivity.this, "请求失败",
//                                Toast.LENGTH_LONG);
//                    }
//
//                }
//
//                @Override
//                public void onFailure(HttpException e, String s) {
//                    // TODO Auto-generated method stub
//                    progressDialog.cancel();
//                    ToastAlone.showToast(SquareDetailActivity.this, "请求失败",
//                            Toast.LENGTH_LONG);
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            progressDialog.cancel();
//            ToastAlone.showToast(SquareDetailActivity.this, "请求失败", Toast.LENGTH_LONG);
//        }
//    }
//
//
//    /**
//     * 关闭评论
//     */
//    public void colosePingLun() {
//        if (rLayoutComment.getVisibility() == View.VISIBLE) {
//            edit_pinglun.setText("");
//            edit_pinglun.setHint("我来写点评论...");
//            edit_pinglun.clearFocus();
//            tvPinglunMaxNum.setText("0/150");
//            Utils.ColoseJianPan(this, edit_pinglun);
//            rLayoutComment.setVisibility(View.GONE);
//            view.requestFocus();
//            view.setFocusable(true);
////            iv_pinglun.setVisibility(View.VISIBLE);
//        }
//    }
//
//    /**
//     * 是否举报
//     */
//    public void showJubaoDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("提示");
//        builder.setMessage("确定要举报该视频吗？");
//        builder.setPositiveButton("举报", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                juBaoToServer();
//            }
//
//        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//
//        });
//        builder.create().show();
//    }
//
//    /**
//     * 关注用户
//     */
//    public void guanZhuToServer() {
//        // 如果无网络链接
//        if (Contants.NETWORK_NONE == Utils.getNetWorkStatus(SquareDetailActivity.this)) {
//            ToastAlone.showToast(SquareDetailActivity.this, "无网络链接", Toast.LENGTH_LONG);
//            return;
//        }
//        // 设置请求参数
//        params = new HashMap<String, String>();
//        // 用户id
//        userId = SPUtils.getInstance(this).getData(Contants.LOGIN_USER, null);
//        if (null != userId && !TextUtils.isEmpty(userId.trim())) {
//            params.put("userId", userId);
//        }
//        // 关注用户id
//        params.put("focusId", videoInfo.getUserId() + "");
//        guanZhuPresenter.guanZhuToServer(params);
//    }
//
//    /**
//     * 取消关注用户
//     */
//    private void quXiaoGuanZhuToServer() {
//        // TODO Auto-generated method stub
//        // 如果无网络链接
//        if (Contants.NETWORK_NONE == Utils.getNetWorkStatus(SquareDetailActivity.this)) {
//            ToastAlone.showToast(SquareDetailActivity.this, "无网络链接", Toast.LENGTH_LONG);
//            return;
//        }
//        // 设置请求参数
//        params = new HashMap<String, String>();
//        // 用户id
//        userId = SPUtils.getInstance(this).getData(Contants.LOGIN_USER, null);
//        if (null != userId && !TextUtils.isEmpty(userId.trim())) {
//            params.put("userId", userId);
//        }
//        // 关注用户id
//        params.put("focusId", videoInfo.getUserId() + "");
//        guanZhuPresenter.cancleGuanZhuServer(params);
//    }
//
//    /**
//     * 关注成功
//     */
//    @Override
//    public void guanZhuSuccess() {
//        ToastAlone.showToast(SquareDetailActivity.this, "关注成功", Toast.LENGTH_LONG);
//        userInfo.setFocus(true);
//        iv_guanzhu.setImageResource(R.drawable.icon_yiguanzhu);
//    }
//
//    /**
//     * 取消关注  成功
//     */
//    @Override
//    public void cancleGuanZhuSuccess() {
//        // 取消成功
//        ToastAlone.showToast(SquareDetailActivity.this, "取消成功", Toast.LENGTH_LONG);
//        userInfo.setFocus(false);
//        iv_guanzhu.setImageResource(R.drawable.icon_guanzhu_cansai_video_play);
//    }
//
//    @Override
//    public void shouCangSuccess() {
//        // 收藏成功
//        ToastAlone.showToast(SquareDetailActivity.this, "收藏成功", Toast.LENGTH_LONG);
//        imageShouCang.setSelected(true);
//    }
//
//    @Override
//    public void cancleShouCangSuccess() {
//        // 取消收藏成功
//        ToastAlone.showToast(SquareDetailActivity.this, "取消成功", Toast.LENGTH_LONG);
//        imageShouCang.setSelected(false);
//    }
//
//    @Override
//    public void zanToServerSuccess(String result) {
//        try {
//            if (null != result && !TextUtils.isEmpty(result.trim())) {
//                JSONObject jsonObject1 = new JSONObject(result);
//                String code1 = jsonObject1.optString("code");
//                String msg1 = jsonObject1.optString("msg");
//                String result1 = jsonObject1.optString("result");
//                tvZan.setEnabled(true);
//                tvZan.setSelected(true);
//                videoInfo.setPraiseNum(videoInfo.getPraiseNum() + 1);
//                tvZan.setText("" + videoInfo.getPraiseNum() + "");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void cancelZanServerSuccess() {
//        tvZan.setSelected(false);
//        tvZan.setEnabled(true);
//        videoInfo.setPraiseNum(videoInfo.getPraiseNum() - 1);
//        tvZan.setText("" + videoInfo.getPraiseNum() + "");
//    }
//
//    @Override
//    public void peiToServerSuccess(String result) {
//
//    }
//
//    @Override
//    public void cancelPeiServerSuccess() {
//
//    }
//
//    @Override
//    public void showProgressDialog(String message) {
//        if (!myRefreshListView.isRefreshing()) {
//            super.showProgressDialog(message);
//        }
//    }
//
//    @Override
//    public void canleProgressDialog() {
//        if (!myRefreshListView.isRefreshing()) {
//            super.canleProgressDialog();
//        } else {
//            if (myRefreshListView.isRefreshing()) {
//                myRefreshListView.onRefreshComplete();
//            }
//        }
//    }
//
//
//    /**
//     * 发表评论失败
//     */
//    @Override
//    public void onPingLunFailure() {
//
//    }
//
//    /**
//     * 发表评论成功
//     */
//    @Override
//    public void onPingLunSuccess(SquareMoreInfoBean.CommentInfo commentInfo) {
//        colosePingLun();
//        tvNoneComment.setVisibility(View.GONE);
//        if (null == listPingLuns) {
//            listPingLuns = new ArrayList<>();
//            pingLunAdapter = new PingLunAdapter(
//                    SquareDetailActivity.this, listPingLuns, true);
//            myRefreshListView.setAdapter(pingLunAdapter);
//        }
//        listPingLuns.add(0, commentInfo);
//        pingLunAdapter.notifyDataSetChanged();
//        pingLunNumber++;
//        //设置评论数量
//        tvPinglunNum.setText("(" + pingLunNumber + ")");
//        tvVideoComment.setText("" + pingLunNumber + "");
//        if (isPinglun) {
//            ToastAlone.showToast(SquareDetailActivity.this,
//                    "评论成功", Toast.LENGTH_LONG);
//        } else {
//            ToastAlone.showToast(SquareDetailActivity.this,
//                    "回复成功", Toast.LENGTH_LONG);
//        }
//    }
//
//    @Override
//    public void onDelPingLunSuccess(String message) {
//        ToastAlone.showToast(this, "删除成功", Toast.LENGTH_LONG);
//        listPingLuns.remove(selectPosition);
//        pingLunAdapter.notifyDataSetChanged();
//        if (pingLunNumber > 0)
//            pingLunNumber--;
//        else
//            pingLunNumber = 0;
//        tvPinglunNum.setText("(" + pingLunNumber + ")");
//        tvVideoComment.setText("" + pingLunNumber + "");
//        if (null != listPingLuns && listPingLuns.size() <= 0) {
//            tvNoneComment.setVisibility(View.VISIBLE);
//        }
//    }
//
//    /**
//     * 获取评论列表成功
//     */
//    @Override
//    public void onGetPingLunListSuccess(final PingLunListBean.PingLunListResultBean
//                                                pingLunListResultBean) {
//        if (null != pingLunListResultBean.getList() && pingLunListResultBean.getList().size() > 0) {
////            tvPingLun.setVisibility(View.VISIBLE);
////            tvPinglunNum.setVisibility(View.VISIBLE);
//            tvNoneComment.setVisibility(View.GONE);
//            pingLunNumber = pingLunListResultBean.getTotal();
//            tvPinglunNum.setText("(" + pingLunListResultBean.getTotal() + "" + ")");
//            tvVideoComment.setText("" + pingLunListResultBean.getTotal() + "");
//            if (isClick && page == 1) {
//                if (null != listPingLuns) {
//                    listPingLuns.clear();
//                    listPingLuns = null;
//                }
//            } else if (page == 1) {
//                if (null != listPingLuns) {
//                    listPingLuns.clear();
//                    listPingLuns = null;
//                }
//            }
//            if (page > 1) {
//                listPingLuns.addAll(pingLunListResultBean.getList());
//                pingLunAdapter.notifyDataSetChanged();
//            } else {
//                listPingLuns = pingLunListResultBean.getList();
//                pingLunAdapter = new PingLunAdapter(SquareDetailActivity.this, listPingLuns, true);
//                myRefreshListView.setAdapter(pingLunAdapter);
//                if (listPingLuns.size() > 5 && listPingLuns.size() < 10) {
//                    myRefreshListView.setNoMoreData();
//                } else if (listPingLuns.size() <= 5) {
//                    myRefreshListView.hideFooterView();
//                    myRefreshListView.setIsMore(true);
//                }
//            }
//        } else {
//            if (page > 1) {
//                page--;
//                isPingLunMore = false;
//                myRefreshListView.setNoMoreData();
//            } else {
//                tvPinglunNum.setText("(" + "0" + ")");
//                tvVideoComment.setText("" + 0 + "");
//                pingLunNumber = 0;
//                tvNoneComment.setVisibility(View.VISIBLE);
//                myRefreshListView.hideFooterView();
//                myRefreshListView.setIsMore(true);
//            }
//        }
//
//    }
//
//    /**
//     * 获取评论列表失败
//     */
//    @Override
//    public void onGetPingLunListFailure() {
//        if (page > 1) {
//            page--;
//            isPingLunMore = false;
//            myRefreshListView.setNoMoreData();
//        } else if (page == 1) {
//            tvNoneComment.setVisibility(View.VISIBLE);
//            pingLunNumber = 0;
//            tvPinglunNum.setText("(" + "0" + ")");
//            tvVideoComment.setText("" + 0 + "");
//            myRefreshListView.setIsMore(true);
//            myRefreshListView.hideFooterView();
//        }
//
//    }
//
//    @Override
//    public void onGetAiTeUserInfoListSuccess(List<AiTeUserInfoDB> userInfosList) {
//
//    }
//
//    /**
//     * 获取视频详情
//     */
//
//    public void getVideoDetailByServer() {
//        showProgressDialog("正在加载,请稍后...");
//        // 设置请求参数
//        Map<String, String> params = new HashMap<String, String>();
//        // 视频id
//        if (0 != videoId) {
//            params.put("videoId", videoId + "");
//        } else {
//            canleProgressDialog();
//            ToastAlone.showToast(this, "视频ID有误!", Toast.LENGTH_LONG);
//            return;
//        }
//        // 擂台id
//        if (0 != competitionId) {
//            params.put("competitionId", competitionId + "");
//        }
//        if (0 != albumId) {
//            params.put("albumId", albumId + "");
//        }
//        if (0 != topicId) {
//            params.put("topicId", topicId + "");
//        }
//        // 用户id
//        userId = SPUtils.getInstance(SquareDetailActivity.this).getData(Contants.LOGIN_USER, null);
//        if (null != userId && !TextUtils.isEmpty(userId)) {
//            params.put("userId", userId);
//        }
//        try {
//            NetWorkUtil.getInitialize().sendPostRequest(SquareDetailActivity.this, NetWorkConfig.GET_VIDEO_DETAIL, params, new RequestCallBack<String>() {
//                @Override
//                public void onSuccess(ResponseInfo<String> responseInfo) {
//                    // TODO Auto-generated method stub
//                    // 判断返回参数是否为空
//                    if (null != responseInfo) {
//                        String json = responseInfo.result;
//                        LogUtils.e("json-->" + json);
//                        if (null != json && !TextUtils.isEmpty(json.trim())) {
//                            try {
//                                JsonBean jsonBean = JsonBeanUtil.getJsonBean(json);
//                                if (null != jsonBean && "0".equals(jsonBean.getCode())) {
//                                    // 解析数据
//                                    Gson gson = new GsonBuilder().serializeNulls().create();
//                                    Type type = new TypeToken<SquareMoreInfoBean.MoreBean>() {
//                                    }.getType();
//                                    moreBean = gson.fromJson(jsonBean.getResult(), type);
//                                    // 设置显示信息
//                                    setViewInfos();
//                                } else if (null != jsonBean) {
//                                    canleProgressDialog();
//                                    if (null != jsonBean.getMsg() && !TextUtils
//                                            .isEmpty(jsonBean.getMsg().trim())) {
//                                        ToastAlone.showToast(SquareDetailActivity.this,
//                                                jsonBean.getMsg(),
//                                                Toast.LENGTH_LONG);
//                                    } else {
//                                        ToastAlone.showToast(SquareDetailActivity.this,
//                                                "请求失败", Toast.LENGTH_LONG);
//                                    }
//                                } else {
//                                    canleProgressDialog();
//                                    ToastAlone.showToast(SquareDetailActivity.this,
//                                            "请求失败", Toast.LENGTH_LONG);
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                canleProgressDialog();
//                                ToastAlone.showToast(SquareDetailActivity.this, "请求失败",
//                                        Toast.LENGTH_LONG);
//                            }
//                        } else {
//                            canleProgressDialog();
//                            ToastAlone.showToast(SquareDetailActivity.this, "请求失败",
//                                    Toast.LENGTH_LONG);
//                        }
//                    } else {
//                        canleProgressDialog();
//                        ToastAlone.showToast(SquareDetailActivity.this, "请求失败",
//                                Toast.LENGTH_LONG);
//                    }
//                }
//
//                @Override
//                public void onFailure(HttpException e, String s) {
//                    canleProgressDialog();
//                    ToastAlone.showToast(SquareDetailActivity.this, "请求失败", Toast.LENGTH_LONG);
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//            canleProgressDialog();
//            ToastAlone.showToast(SquareDetailActivity.this, "请求失败", Toast.LENGTH_LONG);
//        }
//    }
//
//    /**
//     * 展示界面信息
//     */
//    private void setViewInfos() {
//        if (null != moreBean) {
//            // 用户信息
//            userInfo = moreBean.getUserInfo();
//            // 视频信息
//            videoInfo = moreBean.getVideoInfo();
//            //是否有下个视频播放
//            nextVideoInfo = moreBean.getNextVideoInfo();
//            //其它视频
//            albumOtherVideo = moreBean.getAlbumOtherVideo();
//            tvVideoComment.setText("(0)");
//            if (null != videoInfo) {
//                if (videoInfo.getStatus() == 0) {
//                    //视频已下架
//                    canleProgressDialog();
//                    startActivity(new Intent(this, XiajiaActivity.class));
//                    finish();
//                } else {
//                    //未下架
//                    try {
//                        //设置视频名称
//                        textview_videoName.setText(videoInfo.getName());
//                        if (!TextUtils.isEmpty(videoInfo.getDescript())) {
//                            //设置视频描述
//                            textview_describe.setVisibility(View.VISIBLE);
//                            textview_describe.setText(videoInfo.getDescript());
//                        } else {
//                            textview_describe.setVisibility(View.GONE);
//                        }
//                        // 设置用户头像
//                        ImageLoader.getInstance().displayImage(userInfo.getImg(), cImageView_head,
//                                headDisplayImageOptions);
//                        //设置用户名
//                        textView_userName.setText(userInfo.getNickName());
//                        userId = SPUtils.getInstance(SquareDetailActivity.this).getData(Contants.LOGIN_USER,
//                                null);
//                        if (null != userId && !TextUtils.isEmpty(userId.trim())) {
//                            //关注状态
//                            if (userId.equals(userInfo.getId() + "")) {
//                                iv_guanzhu.setVisibility(View.GONE);
//                            } else {
//                                iv_guanzhu.setVisibility(View.VISIBLE);
//                            }
//                        } else {
//                            iv_guanzhu.setVisibility(View.VISIBLE);
//                        }
//                        if (userInfo.isFocus()) {
//                            iv_guanzhu.setImageResource(R.drawable.icon_yiguanzhu);
//                        } else {
//                            iv_guanzhu.setImageResource(R.drawable.icon_guanzhu);
//                        }
//                        if (videoInfo.isPraise()) {
//                            tvZan.setSelected(true);
//                        } else {
//                            tvZan.setSelected(false);
//                        }
//                        if (videoInfo.isCollect()) {
//                            imageShouCang.setSelected(true);
//                        } else {
//                            imageShouCang.setSelected(false);
//                        }
//                        //赞数
//                        tvZan.setText("" + videoInfo.getPraiseNum() + "");
//                        //显示用户职业标签
//                        if (null != userInfo && !TextUtils.isEmpty(userInfo.getCareer())) {
//                            tv_zhiye.setVisibility(View.VISIBLE);
//                            tv_zhiye.setText(userInfo.getCareer());
//                        } else {
//                            tv_zhiye.setVisibility(View.GONE);
//                        }
//                        //设置加v信息(0未认证；1，个人；2企业，3，职业；)
//                        if (3 == userInfo.getUserAuthType()) {
//                            //职业
//                            imageView_v.setVisibility(View.VISIBLE);
//                            imageView_v.setImageResource(R.drawable.zhiye_auth_small);
//                        } else if (2 == userInfo.getUserAuthType()) {
//                            //企业
//                            imageView_v.setVisibility(View.VISIBLE);
//                            imageView_v.setImageResource(R.drawable.business_auth_small);
//                        } else if (1 == userInfo.getUserAuthType()) {
//                            //个人
//                            imageView_v.setVisibility(View.VISIBLE);
//                            imageView_v.setImageResource(R.drawable.personal_auth_small);
//                        } else if (0 == userInfo.getUserAuthType()) {
//                            imageView_v.setVisibility(View.GONE);
//                        }
//                        //设置其它视频数据
//                        if (null != albumOtherVideo && null != albumOtherVideo.getVideoList() && albumOtherVideo.getVideoList().size() > 0) {
//                            if (1 == albumOtherVideo.getType()) {
//                                tvZhuanjiName.setText("《" + albumOtherVideo.getAlbumName() + "》的专辑视频");
//                                if (1 == moreBean.getIsMore()) {
//                                    tvSeeMore.setText("查看更多");
//                                    tvSeeMore.setVisibility(View.VISIBLE);
//                                    viewLineSeeMore.setVisibility(View.VISIBLE);
//                                }
//                            } else {
//                                tvZhuanjiName.setText("《" + albumOtherVideo.getTopicName() + "》专题视频");
//                                tvSeeMore.setVisibility(View.VISIBLE);
//                                viewLineSeeMore.setVisibility(View.VISIBLE);
//                                tvSeeMore.setText("进入专题");
//                            }
//                            listCloumns = albumOtherVideo.getVideoList();
//                            if (null != listCloumns && listCloumns.size() > 0) {
//                                tvZhuanjiName.setVisibility(View.VISIBLE);
//                                view_line.setVisibility(View.VISIBLE);
//                                lLayoutCloumns.setVisibility(View.VISIBLE);
//                                videoCloumnsAdapter = new VideoCloumnsAdapter(SquareDetailActivity.this, listCloumns, albumOtherVideo.getType());
//                                videoCloumnsAdapter.setPlayVideoId(videoId);
//                                lLayoutCloumns.removeAllViews();
//                                int number = listCloumns.size();
//                                if (listCloumns.size() > 3) {
//                                    number = 3;
//                                }
//                                for (int i = 0; i < number; i++) {
//                                    View view = videoCloumnsAdapter.getView(i, null, null);
//                                    final int position = i;
//                                    view.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            isClick = true;
//                                            isPause = false;
//                                            page = 1;
//                                            lastVideoId = SquareDetailActivity.this.videoId;
//                                            videoId = listCloumns.get(position).getId();
//                                            kMediaPlayer.stop();
//                                            isVideoAtuoPlay = true;
//                                            if (null != listPingLuns) {
//                                                listPingLuns.clear();
//                                                pingLunAdapter.notifyDataSetChanged();
//                                            }
//                                            getVideoDetailByServer();
//
//                                        }
//                                    });
//                                    lLayoutCloumns.addView(view);
//                                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
//                                    layoutParams.setMargins(0, 0, 10, 0);
//                                    view.setLayoutParams(layoutParams);
//                                }
//                            }
//                        }
//                        //设置视频信息
//                        kMediaPlayer.setVideoCover(videoInfo.getImg());
//                        kMediaPlayer.setVideoId(videoId + "");
//                        kMediaPlayer.setVideoUrl(videoInfo.getUrl());
//                        //设置下一个要播放的视频
//                        if (null != nextVideoInfo) {
//                            kMediaPlayer.setNextVideoInfo(nextVideoInfo);
//                        } else {
//                            kMediaPlayer.setNextVideoInfo(null);
//                        }
//                        //是否自动播放
//                        if (isVideoAtuoPlay) {
//                            isVideoAtuoPlay = false;
//                            kMediaPlayer.startPlay();
//                            if (null != seeMoreVideoPopupWindow && seeMoreVideoPopupWindow.isShowing()) {
//                                seeMoreVideoPopupWindow.setVideoPlayingId(videoId);
//                            }
//                        }
//                        myRefreshListView.hideFooterView();
//                        myRefreshListView.setIsMore(false);
//                        listPingLuns = new ArrayList<>();
//                        pingLunAdapter = new PingLunAdapter(SquareDetailActivity.this, listPingLuns, true);
//                        myRefreshListView.setAdapter(pingLunAdapter);
//                        getPunLunListByServer();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        canleProgressDialog();
//                        ToastAlone.showToast(this, "视频加载失败", Toast.LENGTH_LONG);
//                    }
//                }
//            }
//        } else {
//            canleProgressDialog();
//            ToastAlone.showToast(this, "视频加载失败", Toast.LENGTH_LONG);
//        }
//    }
//
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        //返回按钮
//        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
//            //如果处于全屏模式则取消全屏
//            if (isFullScreen) {
//                //调整为非全屏
//                kMediaPlayer.setOnNoFullScreen();
//                return true;
//            }
//            if (null != seeMoreVideoPopupWindow && seeMoreVideoPopupWindow.isShowing()) {
//                return true;
//            }
//            //取消评论
//            if (null != rLayoutComment && rLayoutComment.getVisibility() == View.VISIBLE) {
//                colosePingLun();
//                return true;
//            }
//            //H5唤起的返回主界面
//            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//                if (isH5Skip) {
//                    startActivity(new Intent(this, MainActivity.class));
//                    finish();
//                    return true;
//                }
//            }
//
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    /**
//     * 编辑框获取焦点监听
//     */
//    @Override
//    public void onFocusChange(View v, boolean hasFocus) {
//        if (v == edit_pinglun && hasFocus) {
//            //获取焦点
//            //获取光标位置
//            isDelete = false;
//        }
//
//    }
//
//    /**
//     * 监听编辑框edit
//     */
//    @Override
//    public boolean onKey(View v, int keyCode, KeyEvent event) {
//        //如果是删除按键
//        if (keyCode == KeyEvent.KEYCODE_DEL) {
//            //设置删除状态为true
//            isDelete = true;
//        }
//        return false;
//    }
//
//    /**
//     * 编辑输入变化前
//     */
//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//        beforeIndex = start;
//
//    }
//
//    /**
//     * 编辑输入变化中
//     */
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//    }
//
//    /**
//     * 编辑输入变化结束
//     */
//    @Override
//    public void afterTextChanged(Editable s) {
//        //消除删除状态
//        oldIndex = beforeIndex;
//        if (null != s && !TextUtils.isEmpty(s.toString())) {
//            tvPinglunMaxNum.setText(s.toString().length() + "/150");
//            if (!isAitAdd) {
//                if (String.valueOf("@").equals(s.toString())) {
//                    SquareDetailActivity.this.startActivityForResult(new Intent(SquareDetailActivity.this, PingLunAiteActivity.class), PINGLUN_AITE_CODE);
//                } else {
//                    //获取光标位置
//                    if (s.length() > 1) {
//                        int start = edit_pinglun.getSelectionStart();
//                        //获取@符号前面字符
//                        if (start > 0) {
//                            char beforeChar = s.toString().charAt(start - 1);
//                            //判断@符号前面是否为字母和数字,如果为数字或字母则不进行@判断
//                            if ('\0' == beforeChar || Utils.checkType(beforeChar) == Utils.CharType.NUM ||
//                                    Utils.checkType(beforeChar) == Utils.CharType.LETTER) {
//                                return;
//                            }
//                            //判断是否为@
//                            if (String.valueOf("@").equals(String.valueOf(s.toString().charAt(start - 1)))) {
//                                if (TextUtils.isEmpty(userId)) {
//                                    Intent intent = new Intent(SquareDetailActivity.this, LoginActivity.class);
//                                    startActivityForResult(intent, LOGIN_CODE);
//                                } else {
//                                    SquareDetailActivity.this.startActivityForResult(new Intent(SquareDetailActivity.this, PingLunAiteActivity.class), PINGLUN_AITE_CODE);
//                                }
//                            }
//                        }
//                    } else {
//                        if (String.valueOf("@").equals(s.toString())) {
//                            SquareDetailActivity.this.startActivityForResult(new Intent(SquareDetailActivity.this, PingLunAiteActivity.class), PINGLUN_AITE_CODE);
//                        }
//                    }
//                }
//            }
//        }
//        isDelete = false;
//    }
//
//    /**
//     * activity请求返回
//     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //@请求返回
//        if (requestCode == PINGLUN_AITE_CODE && resultCode == RESULT_OK && null != data) {
//            AiTeUserInfoDB aiTeUserInfoDB = (AiTeUserInfoDB) data.getExtras().getSerializable("names");
//            //设置获取的@人 用户
//            if (null != aiTeUserInfoDB) {
//                isAitAdd = true;
//                beforeIndex = edit_pinglun.getSelectionStart();
//                Editable editable = edit_pinglun.getText();
//                //加空格
//                editable.insert(beforeIndex, aiTeUserInfoDB.getNickName() + " ");
//                //把@的用户放入
//                ArrayMap<String, String> map = new ArrayMap<String, String>();
//                map.put("userId", String.valueOf(aiTeUserInfoDB.getUserId()));
//                map.put("nickName", aiTeUserInfoDB.getNickName());
//                map.put("index", String.valueOf(beforeIndex));
//                aiTeListMaps.add(map);
//                isAitAdd = false;
////                //保存对应用户昵称的开始和结束位置
////                aiTeMap.put(names, new int[]{beforeIndex, beforeIndex + names.length() + 1});
////                //保存最位置的对应的字符串
////                LogUtils.e("text---" + names.substring(names.length() - 1) + " ");
////                deleteMap.put(names.substring(names.length() - 1) + " ", beforeIndex);
////                //保存位置纪录
////                locationInt[localIndex] = beforeIndex;
////                localIndex++;
////                locationInt[localIndex] = beforeIndex + names.length() + 1;
////                localIndex++;
////                pingLunEdit.setSelection(beforeIndex + names.length() + 1);
//
//            }
//        }
//
//        //登录返回
//        if (requestCode == LOGIN_CODE) {
//            userId = SPUtils.getInstance(this).getData(Contants.LOGIN_USER, null);
//            if (TextUtils.isEmpty(userId)) {
//
//            } else {
//                SquareDetailActivity.this.startActivityForResult(new Intent(SquareDetailActivity.this, PingLunAiteActivity.class), PINGLUN_AITE_CODE);
//            }
//        }
//        //关注登录返回
//        if (requestCode == GUANZHU_LOGIN_CODE) {
//            userId = SPUtils.getInstance(this).getData(Contants.LOGIN_USER, null);
//            if (!TextUtils.isEmpty(userId)) {
//                getVideoDetailByServer();
//            }
//        }
//    }
//
//}
