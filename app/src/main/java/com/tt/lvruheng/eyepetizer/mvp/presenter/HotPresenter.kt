package com.tt.lvruheng.eyepetizer.mvp.presenter

import android.content.Context
import com.tt.lvruheng.eyepetizer.base.BasePresenter
import com.tt.lvruheng.eyepetizer.mvp.contract.FindContract
import com.tt.lvruheng.eyepetizer.mvp.contract.HotContract
import com.tt.lvruheng.eyepetizer.mvp.model.FindModel
import com.tt.lvruheng.eyepetizer.mvp.model.HotModel
import com.tt.lvruheng.eyepetizer.mvp.model.bean.FindBean
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HotBean
import com.tt.lvruheng.eyepetizer.utils.NetworkUtils
import com.tt.lvruheng.eyepetizer.utils.applySchedulers
import io.reactivex.BackpressureOverflowStrategy
import io.reactivex.Observable

/**
 * Created by lvruheng on 2017/7/7.
 */
class HotPresenter(context: Context, view: HotContract.View) : HotContract.Presenter {


    var mContext: Context? = null
    var mView: HotContract.View? = null
    val mModel: HotModel by lazy {
        HotModel()
    }

    init {
        mView = view
        mContext = context
    }

    override fun start() {

    }

    override fun requestData(strategy: String) {
        val observable: Observable<HotBean>? = mContext?.let { mModel.loadData(mContext!!, strategy) }
        observable?.applySchedulers()?.subscribe({ bean: HotBean ->
            mView?.setData(bean)
        }, { error ->
            run {
                error.printStackTrace()
                if (NetworkUtils.isNetworkError(error)) {
                    mView?.unexpectedErrorToast(BasePresenter.UNEXPECTED_ERROR.NETWORK, false)
                }
            }

        })
    }

}