package com.tt.lvruheng.eyepetizer.ui.fragment

import android.util.Log
import com.tt.lvruheng.eyepetizer.R
import com.tt.lvruheng.eyepetizer.mvp.contract.FindContract
import com.tt.lvruheng.eyepetizer.mvp.model.bean.FindBean
import com.tt.lvruheng.eyepetizer.mvp.presenter.FindPresenter
import com.tt.lvruheng.eyepetizer.mvp.presenter.HomePresenter

/**
 * Created by lvruheng on 2017/7/4.
 */
class FindFragment : BaseFragment(),FindContract.View {
    var mPresenter: FindPresenter? = null
    override fun setData(beans: MutableList<FindBean>) {
        Log.e("find", ""+beans?.size)
    }

    override fun getLayoutResources(): Int {
        return R.layout.find_fragment
    }

    override fun initView() {
        mPresenter = FindPresenter(context,this)
        mPresenter?.start()
    }

}