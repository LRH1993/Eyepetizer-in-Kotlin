package com.tt.lvruheng.eyepetizer.base

/**
 * Created by lvruheng on 2017/7/5.
 */
interface BasePresenter {

    enum class UNEXPECTED_ERROR{
        NETWORK,
        INVALID_JSON,
        DATA_FORMAT,
        OTHER
    }

    fun start()
}