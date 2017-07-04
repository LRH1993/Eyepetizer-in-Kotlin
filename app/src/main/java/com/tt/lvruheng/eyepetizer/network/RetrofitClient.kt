package com.tt.lvruheng.eyepetizer.network

import android.content.Context
import android.util.Log
import java.io.File
import okhttp3.Cache
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import javax.xml.datatype.DatatypeConstants.SECONDS
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer




/**
 * Created by lvruheng on 2017/7/4.
 */
class RetrofitClient private constructor(context: Context,baseUrl:String){
    var httpCacheDirectory : File? = null
    val mContext : Context = context
    var cache : Cache? = null
    var okHttpClient : OkHttpClient? = null
    var retrofit : Retrofit? = null
    val DEFAULT_TIMEOUT : Long = 20
    val url = baseUrl
    init {
        //缓存地址
        if (httpCacheDirectory == null) {
            httpCacheDirectory = File(mContext.cacheDir, "app_cache")
        }
        try {
            if (cache == null) {
                cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)
            }
        } catch (e: Exception) {
            Log.e("OKHttp", "Could not create http cache", e)
        }
        //okhttp创建了
        okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(cache)
                .addInterceptor(CacheInterceptor(context))
                .addNetworkInterceptor(CacheInterceptor(context))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build()
        //retrofit创建了
        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build()

    }
    companion object{
        @Volatile
        var instance: RetrofitClient? = null

        fun getInstance(context: Context,baseUrl: String) : RetrofitClient {
            if (instance == null) {
                synchronized(RetrofitClient::class) {
                    if (instance == null) {
                        instance = RetrofitClient(context,baseUrl)
                    }
                }
            }
            return instance!!
        }



    }

    fun <T> create(service: Class<T>?): T? {
        if (service == null) {
            throw RuntimeException("Api service is null!")
        }
        return retrofit?.create(service)
    }


}