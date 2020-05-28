package com.example.myapplication.ui

import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
