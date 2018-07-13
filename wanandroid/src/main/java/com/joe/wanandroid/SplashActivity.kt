package com.joe.wanandroid

import android.os.Bundle
import android.app.Activity
import android.os.Handler
import com.common.utils.arouter.RouterCenter

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            RouterCenter.toMain()
        }, 3000)
    }

}
