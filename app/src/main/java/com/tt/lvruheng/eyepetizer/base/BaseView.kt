package com.tt.lvruheng.eyepetizer.base

/**
 * Created by lvruheng on 2017/7/5.
 */
interface BaseView<in T> {
    fun unexpectedErrorToast(errorType:BasePresenter.UNEXPECTED_ERROR, ignoredRepeatedError:Boolean)
}