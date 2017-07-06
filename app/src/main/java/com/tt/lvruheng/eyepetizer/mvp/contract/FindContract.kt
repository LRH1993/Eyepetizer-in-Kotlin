package com.tt.lvruheng.eyepetizer.mvp.contract

import com.tt.lvruheng.eyepetizer.base.BasePresenter
import com.tt.lvruheng.eyepetizer.base.BaseView
import com.tt.lvruheng.eyepetizer.mvp.model.bean.FindBean
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HomeBean

/**
 * Created by lvruheng on 2017/7/6.
 */
interface FindContract{
    interface View : BaseView<Presenter> {
        fun setData(beans : MutableList<FindBean>)
    }
    interface Presenter : BasePresenter {
        fun requestData()
    }
}