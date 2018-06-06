package com.tt.lvruheng.eyepetizer.ui

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.tt.lvruheng.eyepetizer.base.BasePresenter

open class BaseActivity:AppCompatActivity() {
    open fun unexpectedErrorToast(errorType: BasePresenter.UNEXPECTED_ERROR, ignoredRepeatedError:Boolean){
        var toastMsg:CharSequence?
        when(errorType){
            BasePresenter.UNEXPECTED_ERROR.NETWORK -> toastMsg = "网络异常，请检查网络链接"
            BasePresenter.UNEXPECTED_ERROR.INVALID_JSON -> toastMsg = "服务器数据异常，请重试"
            BasePresenter.UNEXPECTED_ERROR.DATA_FORMAT -> toastMsg = "服务器数据错误，请重试"
            BasePresenter.UNEXPECTED_ERROR.OTHER -> toastMsg = "未知错误，请重试"
        }
        Toast.makeText(this,toastMsg, Toast.LENGTH_SHORT).show()
    }

}