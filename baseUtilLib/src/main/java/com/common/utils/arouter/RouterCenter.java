package com.common.utils.arouter;//package com.example.tome.component_base.arouter;

import android.app.Activity;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @Created by TOME .
 * @时间 2018/4/26 10:19
 * @描述 ${路由中心}
 */
//ARouter 提供了大量的参数类型 跳转携带 https://blog.csdn.net/zhaoyanjun6/article/details/76165252
public class RouterCenter {
    /**
     * 测试首页
     */
    public static void toMain() {
        ARouter.getInstance().build(RouterURLS.BASE_MAIN).navigation();
    }
}
