package com.tt.lvruheng.eyepetizer.ui

import android.content.Context
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.gyf.barlibrary.ImmersionBar
import com.tt.lvruheng.eyepetizer.R
import com.tt.lvruheng.eyepetizer.mvp.model.bean.VideoBean
import com.tt.lvruheng.eyepetizer.utils.ObjectSaveUtils
import com.tt.lvruheng.eyepetizer.utils.SPUtils
import kotlinx.android.synthetic.main.activity_watch.*
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.concurrent.ExecutionException

/**
 * Created by lvruheng on 2017/7/11.
 */
class WatchActivity : AppCompatActivity() {
    var mList = ArrayList<VideoBean>()
    var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init()
        setContentView(R.layout.activity_watch)
        setToolbar()
        DataAsyncTask(mHandler,this).execute()
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

    private class DataAsyncTask(handler: Handler, activity: WatchActivity) : AsyncTask<Void, Void, ArrayList<VideoBean>>() {
        var activity: WatchActivity = activity
        var handler = handler
        override fun doInBackground(vararg params: Void?): ArrayList<VideoBean>? {
            var list = ArrayList<VideoBean>()
            var count: Int = SPUtils.getInstance(activity, "beans").getInt("count")
            var i = 1
            while (i.compareTo(count) < 0) {
                var bean :VideoBean= ObjectSaveUtils.getValue(activity, "bean$i") as VideoBean
                list.add(bean)
                i++
            }
            return list
        }

        override fun onPostExecute(result: ArrayList<VideoBean>?) {
            super.onPostExecute(result)
            Log.e("watch",result?.size.toString())
        }

    }
}