package com.tt.lvruheng.eyepetizer.mvp.presenter

import android.content.Context
import com.tt.lvruheng.eyepetizer.mvp.contract.HomeContract
import com.tt.lvruheng.eyepetizer.mvp.model.HomeModel
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HomeBean
import com.tt.lvruheng.eyepetizer.utils.applySchedulers
import io.reactivex.Observable




/**
 * Created by lvruheng on 2017/7/5.
 */
class HomePresenter(context: Context,view : HomeContract.View) : HomeContract.Presenter{
    var mContext : Context? = null
    var mView : HomeContract.View? = null
    val mModel : HomeModel by lazy {
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
       val observable : Observable<HomeBean>? = mContext?.let { mModel.loadData(it,true,"0") }
        observable?.applySchedulers()?.subscribe { homeBean : HomeBean ->
            mView?.setData(homeBean)
        }
    }
    fun moreData(data: String?){
        val observable : Observable<HomeBean>? = mContext?.let { mModel.loadData(it,false,data) }
        observable?.applySchedulers()?.subscribe { homeBean : HomeBean ->
            mView?.setData(homeBean)
        }
    }


}




