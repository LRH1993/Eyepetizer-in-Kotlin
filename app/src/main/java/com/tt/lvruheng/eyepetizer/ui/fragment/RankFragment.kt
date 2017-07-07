package com.tt.lvruheng.eyepetizer.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.tt.lvruheng.eyepetizer.R
import com.tt.lvruheng.eyepetizer.adapter.RankAdapter
import com.tt.lvruheng.eyepetizer.mvp.contract.HotContract
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HotBean
import com.tt.lvruheng.eyepetizer.mvp.presenter.HotPresenter
import kotlinx.android.synthetic.main.home_fragment.*

/**
 * Created by lvruheng on 2017/7/6.
 */
class RankFragment : BaseFragment(), HotContract.View {
    lateinit var mPresenter: HotPresenter
    lateinit var mStrategy: String
    lateinit var mAdapter: RankAdapter
    var mList: ArrayList<HotBean.ItemListBean.DataBean> = ArrayList()
    override fun getLayoutResources(): Int {
        return R.layout.rank_fragment
    }

    override fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = RankAdapter(context, mList)
        recyclerView.adapter = mAdapter
        if (arguments != null) {
            mStrategy = arguments.getString("strategy")
            mPresenter = HotPresenter(context, this)
            mPresenter.requestData(mStrategy)
        }

    }

    override fun setData(bean: HotBean) {
        Log.e("rank", bean.toString())
        if(mList.size>0){
            mList.clear()
        }
        bean.itemList?.forEach {
            it.data?.let { it1 -> mList.add(it1) }
        }
        mAdapter.notifyDataSetChanged()
    }

}