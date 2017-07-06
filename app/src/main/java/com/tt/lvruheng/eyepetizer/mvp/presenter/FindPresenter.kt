package com.tt.lvruheng.eyepetizer.mvp.presenter

import android.content.Context
import android.util.Log
import com.tt.lvruheng.eyepetizer.mvp.contract.FindContract
import com.tt.lvruheng.eyepetizer.mvp.contract.HomeContract
import com.tt.lvruheng.eyepetizer.mvp.model.FindModel
import com.tt.lvruheng.eyepetizer.mvp.model.HomeModel
import com.tt.lvruheng.eyepetizer.mvp.model.bean.FindBean
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HomeBean
import com.tt.lvruheng.eyepetizer.utils.applySchedulers
import io.reactivex.Observable

/**
 * Created by lvruheng on 2017/7/6.
 */
class FindPresenter(context: Context, view : FindContract.View) : FindContract.Presenter{
    var mContext : Context? = null
    var mView : FindContract.View? = null
    val mModel : FindModel by lazy {
        FindModel()
    }
    init {
        mView = view
        mContext = context
    }
    override fun start() {
        requestData()
    }

    override fun requestData() {
        val observable : Observable<MutableList<FindBean>>? = mContext?.let { mModel.loadData(mContext!!) }
        observable?.applySchedulers()?.subscribe { beans : MutableList<FindBean> ->
            mView?.setData(beans)
        }
    }



}