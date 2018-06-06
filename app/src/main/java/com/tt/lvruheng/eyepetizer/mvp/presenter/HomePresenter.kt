package com.tt.lvruheng.eyepetizer.mvp.presenter

import android.content.Context
import android.util.Log
import com.tt.lvruheng.eyepetizer.base.BasePresenter
import com.tt.lvruheng.eyepetizer.mvp.contract.HomeContract
import com.tt.lvruheng.eyepetizer.mvp.model.HomeModel
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HomeBean
import com.tt.lvruheng.eyepetizer.utils.NetworkUtils
import com.tt.lvruheng.eyepetizer.utils.applySchedulers
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber


/**
 * Created by lvruheng on 2017/7/5.
 */
class HomePresenter(context: Context, view: HomeContract.View) : HomeContract.Presenter {
    var mContext: Context? = null
    var mView: HomeContract.View? = null
    val mModel: HomeModel by lazy {
        HomeModel()
    }

    init {
        mView = view
        mContext = context
    }

    override fun start() {
        requestData()
    }

    override fun requestData() {
        val observable: Observable<HomeBean>? = mContext?.let { mModel.loadData(it, true, "0") }
        observable?.applySchedulers()?.subscribe({ homeBean: HomeBean ->
            mView?.setData(homeBean)
        }, { error ->
            run {
                error.printStackTrace()
                if (NetworkUtils.isNetworkError(error)) {
                    mView?.unexpectedErrorToast(BasePresenter.UNEXPECTED_ERROR.NETWORK, false)
                }
            }
        })
    }

    fun moreData(data: String?) {
        val observable: Observable<HomeBean>? = mContext?.let { mModel.loadData(it, false, data) }
        observable?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())?.subscribe({ homeBean: HomeBean ->
                    mView?.setData(homeBean)
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




