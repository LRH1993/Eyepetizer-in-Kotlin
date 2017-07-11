package com.tt.lvruheng.eyepetizer.ui

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.gyf.barlibrary.ImmersionBar
import com.tt.lvruheng.eyepetizer.R
import com.tt.lvruheng.eyepetizer.adapter.FeedAdapter
import com.tt.lvruheng.eyepetizer.mvp.contract.ResultContract
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HotBean
import com.tt.lvruheng.eyepetizer.mvp.presenter.ResultPresenter
import kotlinx.android.synthetic.main.activity_find_detail.*
import kotlin.collections.ArrayList

/**
 * Created by lvruheng on 2017/7/11.
 */
class ResultActivity : AppCompatActivity(), ResultContract.View, SwipeRefreshLayout.OnRefreshListener {
    lateinit var keyWord: String
    lateinit var mPresenter: ResultPresenter
    lateinit var mAdapter: FeedAdapter
    var mIsRefresh: Boolean = false
    var mList = ArrayList<HotBean.ItemListBean.DataBean>()
    var start: Int = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init()
        setContentView(R.layout.activity_result)
        keyWord = intent.getStringExtra("keyWord")
        mPresenter = ResultPresenter(this, this)
        mPresenter.requestData(keyWord, start)
        setToolbar()
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = FeedAdapter(this, mList)
        recyclerView.adapter = mAdapter
        refreshLayout.setOnRefreshListener(this)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var layoutManager: LinearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                var lastPositon = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPositon == mList.size - 1) {
                    start = start.plus(10)
                    mPresenter.requestData(keyWord, start)
                }
            }
        })
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        var bar = supportActionBar
        bar?.title = "'$keyWord' 相关"
        bar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun setData(bean: HotBean) {
        if (mIsRefresh) {
            mIsRefresh = false
            refreshLayout.isRefreshing = false
            if (mList.size > 0) {
                mList.clear()
            }

        }
        bean.itemList?.forEach {
            it.data?.let { it1 -> mList.add(it1) }
        }
        mAdapter.notifyDataSetChanged()
    }

    override fun onRefresh() {
        if (!mIsRefresh) {
            mIsRefresh = true
            start = 10
            mPresenter.requestData(keyWord, start)
        }
    }
}