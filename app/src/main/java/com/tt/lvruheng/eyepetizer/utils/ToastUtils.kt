package com.tt.lvruheng.eyepetizer.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by lvruheng on 2017/7/2.
 */
fun Context.showToast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}
