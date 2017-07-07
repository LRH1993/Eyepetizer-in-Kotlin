package com.tt.lvruheng.eyepetizer.ui.fragment

import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.tt.lvruheng.eyepetizer.R
import com.tt.lvruheng.eyepetizer.adapter.FindAdapter
import com.tt.lvruheng.eyepetizer.mvp.contract.FindContract
import com.tt.lvruheng.eyepetizer.mvp.model.bean.FindBean
import com.tt.lvruheng.eyepetizer.mvp.presenter.FindPresenter
import kotlinx.android.synthetic.main.find_fragment.*

/**
 * Created by lvruheng on 2017/7/4.
 */
class FindFragment : BaseFragment(),FindContract.View {
    var mPresenter: FindPresenter? = null
    var mAdapter : FindAdapter? = null
    var mList : MutableList<FindBean>? = null
    override fun setData(beans: MutableList<FindBean>) {
        mAdapter?.mList = beans
        mAdapter?.notifyDataSetChanged()
    }

    override fun getLayoutResources(): Int {
        return R.layout.find_fragment
    }

    override fun initView() {
        mPresenter = FindPresenter(context,this)
        mPresenter?.start()
        mAdapter = FindAdapter(context,mList)
        gv_find.adapter = mAdapter
        gv_find.setOnItemClickListener { parent, view, position, id ->
            var bean = mList?.get(position)

        }
    }

}