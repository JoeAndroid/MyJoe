package com.example.joe.ui.module.home.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.joe.R
import com.example.joe.api.ApiHomeFactory

class CustomViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)
        var b = Array(3, { i -> (i * 2) })
    }
}
