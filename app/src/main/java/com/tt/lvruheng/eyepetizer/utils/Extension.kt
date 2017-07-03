package com.tt.lvruheng.eyepetizer.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * Created by lvruheng on 2017/7/2.
 */
fun Context.showToast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}

inline fun <reified T: Activity> Activity.newIntent() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}




