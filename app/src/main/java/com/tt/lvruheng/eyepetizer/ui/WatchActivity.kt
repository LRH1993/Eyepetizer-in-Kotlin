package com.tt.lvruheng.eyepetizer.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar
import com.tt.lvruheng.eyepetizer.R
import kotlinx.android.synthetic.main.activity_watch.*

/**
 * Created by lvruheng on 2017/7/11.
 */
class WatchActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init()
        setContentView(R.layout.activity_watch)
        setToolbar()

    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        var bar = supportActionBar
        bar?.title = "观看记录"
        bar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

}