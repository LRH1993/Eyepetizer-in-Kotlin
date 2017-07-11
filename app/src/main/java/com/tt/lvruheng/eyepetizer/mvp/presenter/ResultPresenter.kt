package com.tt.lvruheng.eyepetizer.mvp.presenter

import android.content.Context
import com.tt.lvruheng.eyepetizer.mvp.contract.FindContract
import com.tt.lvruheng.eyepetizer.mvp.contract.HotContract
import com.tt.lvruheng.eyepetizer.mvp.contract.ResultContract
import com.tt.lvruheng.eyepetizer.mvp.model.FindModel
import com.tt.lvruheng.eyepetizer.mvp.model.HotModel
import com.tt.lvruheng.eyepetizer.mvp.model.ResultModel
import com.tt.lvruheng.eyepetizer.mvp.model.bean.FindBean
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HotBean
import com.tt.lvruheng.eyepetizer.utils.applySchedulers
import io.reactivex.BackpressureOverflowStrategy
import io.reactivex.Observable

/**
 * Created by lvruheng on 2017/7/7.
 */
class ResultPresenter(context: Context, view: ResultContract.View) : ResultContract.Presenter {


    var mContext: Context? = null
    var mView: ResultContract.View? = null
    val mModel: ResultModel by lazy {
        ResultModel()
    }

    init {
        mView = view
        mContext = context
    }

    override fun start() {

    }

    override fun requestData(query: String, start: Int) {
        val observable: Observable<HotBean>? = mContext?.let { mModel.loadData(mContext!!, query, start) }
        observable?.applySchedulers()?.subscribe { bean: HotBean ->
            mView?.setData(bean)
        }
    }

}