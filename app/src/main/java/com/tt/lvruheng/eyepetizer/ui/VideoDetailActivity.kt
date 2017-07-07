package com.tt.lvruheng.eyepetizer.ui

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar
import com.tt.lvruheng.eyepetizer.R
import com.tt.lvruheng.eyepetizer.mvp.model.bean.VideoBean
import com.tt.lvruheng.eyepetizer.utils.ImageLoadUtils
import kotlinx.android.synthetic.main.activity_video_detail.*

/**
 * Created by lvruheng on 2017/7/7.
 */
class VideoDetailActivity : AppCompatActivity() {
    lateinit var bean: VideoBean
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).statusBarColor(R.color.black).fitsSystemWindows(true).init()
        setContentView(R.layout.activity_video_detail)
        bean = intent.getParcelableExtra<VideoBean>("data")
        initView()
    }

    private fun initView() {
        var bgUrl = bean.blurred
        bgUrl?.let { ImageLoadUtils.display(this, iv_bottom_bg, bgUrl) }
        tv_video_desc.text = bean.description
        tv_video_title.text = bean.title
        tv_video_title.typeface =  Typeface.createFromAsset(this.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
        var category = bean.category
        var duration = bean.duration
        var minute = duration?.div(60)
        var second = duration?.minus((minute?.times(60)) as Long )
        var realMinute : String
        var realSecond : String
        if(minute!!<10){
            realMinute = "0"+minute
        }else{
            realMinute = minute.toString()
        }
        if(second!!<10){
            realSecond = "0"+second
        }else{
            realSecond = second.toString()
        }
        tv_video_time.text = "$category / $realMinute'$realSecond''"
        tv_video_favor.text = bean.collect.toString()
        tv_video_share.text = bean.share.toString()
        tv_video_reply.text = bean.share.toString()

    }
}
