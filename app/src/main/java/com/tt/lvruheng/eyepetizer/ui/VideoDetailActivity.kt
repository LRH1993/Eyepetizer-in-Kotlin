package com.tt.lvruheng.eyepetizer.ui

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar
import com.tt.lvruheng.eyepetizer.R
import com.tt.lvruheng.eyepetizer.mvp.model.bean.VideoBean
import com.tt.lvruheng.eyepetizer.utils.ImageLoadUtils
import kotlinx.android.synthetic.main.activity_video_detail.*
import android.graphics.BitmapFactory
import com.bumptech.glide.Glide
import android.os.AsyncTask
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.ImageView
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.concurrent.ExecutionException


/**
 * Created by lvruheng on 2017/7/7.
 */
class VideoDetailActivity : AppCompatActivity() {
    companion object {
        var MSG_IMAGE_LOADED = 101
    }

    lateinit var imageView: ImageView
    lateinit var bean: VideoBean
    var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                MSG_IMAGE_LOADED ->{
                    Log.e("video","setImage")
                    gsy_player.setThumbImageView(imageView)
                }


            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).statusBarColor(R.color.black).fitsSystemWindows(true).init()
        setContentView(R.layout.activity_video_detail)
        bean = intent.getParcelableExtra<VideoBean>("data")
        initView()
        prepareVideo()
    }

    private fun initView() {
        var bgUrl = bean.blurred
        bgUrl?.let { ImageLoadUtils.display(this, iv_bottom_bg, bgUrl) }
        tv_video_desc.text = bean.description
        tv_video_title.text = bean.title
        tv_video_title.typeface = Typeface.createFromAsset(this.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
        var category = bean.category
        var duration = bean.duration
        var minute = duration?.div(60)
        var second = duration?.minus((minute?.times(60)) as Long)
        var realMinute: String
        var realSecond: String
        if (minute!! < 10) {
            realMinute = "0" + minute
        } else {
            realMinute = minute.toString()
        }
        if (second!! < 10) {
            realSecond = "0" + second
        } else {
            realSecond = second.toString()
        }
        tv_video_time.text = "$category / $realMinute'$realSecond''"
        tv_video_favor.text = bean.collect.toString()
        tv_video_share.text = bean.share.toString()
        tv_video_reply.text = bean.share.toString()

    }

    private fun prepareVideo() {
        gsy_player.setUp(bean.playUrl,false,null, null)
        //增加封面
        imageView = ImageView(this)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        ImageViewAsyncTask(mHandler, this, imageView).execute(bean.feed)
        gsy_player.titleTextView.visibility = View.GONE
        gsy_player.backButton.visibility = View.VISIBLE
    }

    private class ImageViewAsyncTask(handler: Handler, activity: VideoDetailActivity, private val mImageView: ImageView) : AsyncTask<String, Void, String>() {
        private var handler = handler
        private var mPath: String? = null
        private var mIs: FileInputStream? = null
        private var mActivity: VideoDetailActivity = activity
        override fun doInBackground(vararg params: String): String? {
            val future = Glide.with(mActivity)
                    .load(params[0])
                    .downloadOnly(100, 100)
            try {
                val cacheFile = future.get()
                mPath = cacheFile.absolutePath
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: ExecutionException) {
                e.printStackTrace()
            }

            return mPath
        }

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
            try {
                mIs = FileInputStream(s)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
            val bitmap = BitmapFactory.decodeStream(mIs)
            mImageView.setImageBitmap(bitmap)
            var message = handler.obtainMessage()
            message.what = MSG_IMAGE_LOADED
            handler.sendMessage(message)
        }
    }
}
