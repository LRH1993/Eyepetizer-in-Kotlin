package com.tt.lvruheng.eyepetizer.mvp.contract

import com.tt.lvruheng.eyepetizer.base.BasePresenter
import com.tt.lvruheng.eyepetizer.base.BaseView
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HotBean

/**
 * Created by lvruheng on 2017/7/11.
 */
interface ResultContract {
    interface View : BaseView<Presenter> {
        fun setData(bean: HotBean)
    }

    interface Presenter : BasePresenter {
        fun requestData(query: String, start: Int)
    }
}