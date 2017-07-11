package com.tt.lvruheng.eyepetizer.utils

import android.R.id.edit
import android.content.Context
import android.support.annotation.NonNull
import java.util.Collections.emptySet
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import java.util.*


/**
 * Created by lvruheng on 2017/7/11.
 */
class SPUtils private constructor(context: Context,spName: String) {
    private val sp: SharedPreferences

    init {
        sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
    }

    /**
     * SP中写入String

     * @param key   键
     * *
     * @param value 值
     */
    fun put(key: String, value: String) {
        sp.edit().putString(key, value).apply()
    }

    /**
     * SP中读取String

     * @param key          键
     * *
     * @param defaultValue 默认值
     * *
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    @JvmOverloads fun getString(key: String, defaultValue: String = ""): String {
        return sp.getString(key, defaultValue)
    }

    /**
     * SP中写入int

     * @param key   键
     * *
     * @param value 值
     */
    fun put(key: String, value: Int) {
        sp.edit().putInt(key, value).apply()
    }

    /**
     * SP中读取int

     * @param key          键
     * *
     * @param defaultValue 默认值
     * *
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    @JvmOverloads fun getInt(key: String, defaultValue: Int = -1): Int {
        return sp.getInt(key, defaultValue)
    }

    /**
     * SP中写入long

     * @param key   键
     * *
     * @param value 值
     */
    fun put(key: String, value: Long) {
        sp.edit().putLong(key, value).apply()
    }

    /**
     * SP中读取long

     * @param key          键
     * *
     * @param defaultValue 默认值
     * *
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    @JvmOverloads fun getLong(key: String, defaultValue: Long = -1L): Long {
        return sp.getLong(key, defaultValue)
    }

    /**
     * SP中写入float

     * @param key   键
     * *
     * @param value 值
     */
    fun put(key: String, value: Float) {
        sp.edit().putFloat(key, value).apply()
    }

    /**
     * SP中读取float

     * @param key          键
     * *
     * @param defaultValue 默认值
     * *
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    @JvmOverloads fun getFloat(key: String, defaultValue: Float = -1f): Float {
        return sp.getFloat(key, defaultValue)
    }

    /**
     * SP中写入boolean

     * @param key   键
     * *
     * @param value 值
     */
    fun put(key: String, value: Boolean) {
        sp.edit().putBoolean(key, value).apply()
    }

    /**
     * SP中读取boolean

     * @param key          键
     * *
     * @param defaultValue 默认值
     * *
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    @JvmOverloads fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sp.getBoolean(key, defaultValue)
    }

    /**
     * SP中写入String集合

     * @param key    键
     * *
     * @param values 值
     */
    fun put(key: String, values: Set<String>) {
        sp.edit().putStringSet(key, values).apply()
    }

    /**
     * SP中读取StringSet

     * @param key          键
     * *
     * @param defaultValue 默认值
     * *
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    @JvmOverloads fun getStringSet(key: String, defaultValue: Set<String> = Collections.emptySet()): Set<String> {
        return sp.getStringSet(key, defaultValue)
    }

    /**
     * SP中获取所有键值对

     * @return Map对象
     */
    val all: Map<String, *>
        get() = sp.all

    /**
     * SP中移除该key

     * @param key 键
     */
    fun remove(key: String) {
        sp.edit().remove(key).apply()
    }

    /**
     * SP中是否存在该key

     * @param key 键
     * *
     * @return `true`: 存在<br></br>`false`: 不存在
     */
    operator fun contains(key: String): Boolean {
        return sp.contains(key)
    }

    /**
     * SP中清除所有数据
     */
    fun clear() {
        sp.edit().clear().apply()
    }

    companion object {

        private val sSPMap = HashMap<String,SPUtils>()

        /**
         * 获取SP实例

         * @param spName sp名
         * *
         * @return [SPUtils]
         */
        fun getInstance(context: Context,spName: String): SPUtils {
            var spName = spName
            if (isSpace(spName)) spName = "spUtils"
            var sp: SPUtils? = sSPMap[spName]
            if (sp == null) {
                sp = SPUtils(context,spName)
                sSPMap.put(spName, sp)
            }
            return sp
        }

        private fun isSpace(s: String?): Boolean {
            if (s == null) return true
            var i = 0
            val len = s.length
            while (i < len) {
                if (!Character.isWhitespace(s[i])) {
                    return false
                }
                ++i
            }
            return true
        }
    }
}
