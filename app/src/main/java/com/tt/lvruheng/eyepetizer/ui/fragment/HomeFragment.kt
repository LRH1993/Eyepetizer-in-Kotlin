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
import com.tt.lvruheng.eyepetizer.mvp.presenter.HomePresenter
import kotlinx.android.synthetic.main.home_fragment.*

/**
 * Created by lvruheng on 2017/7/4.
 */
class HomeFragment : BaseFragment(),HomeContract.View{
    var mPresenter : HomePresenter? = null
    var mList : MutableList<HomeBean.IssueListBean.ItemListBean>? = null
    var mAdapter : HomeAdatper? = null
    override fun setData(bean: HomeBean) {

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