package com.tt.lvruheng.eyepetizer.ui

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.gyf.barlibrary.ImmersionBar
import com.tt.lvruheng.eyepetizer.R
import com.tt.lvruheng.eyepetizer.mvp.contract.ResultContract
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HotBean
import com.tt.lvruheng.eyepetizer.mvp.presenter.ResultPresenter

/**
 * Created by lvruheng on 2017/7/11.
 */
class ResultActivity : AppCompatActivity(),ResultContract.View {
    lateinit var keyWord: String
    lateinit var mPresenter: ResultPresenter
    var start : Int = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init()
        setContentView(R.layout.activity_result)
        keyWord = intent.getStringExtra("keyWord")
        mPresenter = ResultPresenter(this,this)
        mPresenter.requestData(keyWord,start)
    }
    override fun setData(bean: HotBean) {
    }
}