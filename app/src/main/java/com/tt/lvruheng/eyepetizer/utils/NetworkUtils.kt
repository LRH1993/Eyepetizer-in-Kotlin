package com.tt.lvruheng.eyepetizer.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import java.io.IOException

/**
 * Created by lvruheng on 2017/7/2.
 */
object NetworkUtils{

    fun isNetConneted(context: Context):Boolean{
        val connectManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo : NetworkInfo?= connectManager.activeNetworkInfo
        if(networkInfo==null){
            return  false
        }else{
            return networkInfo.isAvailable&& networkInfo.isConnected
        }

    }

    fun isNetworkConnected(context: Context,typeMoblie : Int): Boolean{
        if(
                !isNetConneted(context)){
            return false
        }
        val connectManager  = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo : NetworkInfo = connectManager.getNetworkInfo(typeMoblie)
        if(networkInfo==null){
            return false;
        }else{
            return  networkInfo.isConnected && networkInfo.isAvailable
        }
    }

    fun isPhoneNetConnected(context: Context): Boolean {
        val typeMobile = ConnectivityManager.TYPE_MOBILE
        return isNetworkConnected(context,typeMobile)
    }

    fun isWifiNetConnected(context: Context) : Boolean{
        val typeMobile = ConnectivityManager.TYPE_WIFI
        return  isNetworkConnected(context,typeMobile)
    }

    fun isNetworkError(error:Throwable):Boolean{
        return IOException::class.java.isAssignableFrom(error::class.java) || HttpException::class.java.isAssignableFrom(error::class.java)
    }



}