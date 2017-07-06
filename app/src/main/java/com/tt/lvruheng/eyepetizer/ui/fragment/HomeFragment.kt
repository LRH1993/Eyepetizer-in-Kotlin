package com.tt.lvruheng.eyepetizer.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tt.lvruheng.eyepetizer.R
import com.tt.lvruheng.eyepetizer.adapter.HomeAdatper
import com.tt.lvruheng.eyepetizer.mvp.contract.HomeContract
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HomeBean
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HomeBean.IssueListBean.ItemListBean
import com.tt.lvruheng.eyepetizer.mvp.presenter.HomePresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_fragment.*

/**
 * Created by lvruheng on 2017/7/4.
 */
class HomeFragment : BaseFragment(),HomeContract.View{
    var mPresenter : HomePresenter? = null
    var mList = ArrayList<ItemListBean>()
    var mAdapter : HomeAdatper? = null
    override fun setData(bean: HomeBean) {
        bean.issueList!!
                .flatMap { it.itemList!! }
                .filter { it.type.equals("video") }
                .forEach { mList.add(it) }
    }


    override fun getLayoutResources(): Int {
        return R.layout.home_fragment
    }

    override fun initView() {
        mPresenter = HomePresenter(context,0,this)
        mPresenter?.start()
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = HomeAdatper(context,mList)
        recyclerView.adapter = mAdapter
    }
}