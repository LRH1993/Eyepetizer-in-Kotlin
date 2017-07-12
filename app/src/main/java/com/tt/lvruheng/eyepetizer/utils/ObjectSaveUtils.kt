package com.tt.lvruheng.eyepetizer.utils

import android.content.Context
import java.io.*

/**
 * Created by lvruheng on 2017/7/11.
 */
object ObjectSaveUtils{
    fun saveObject(context: Context, name: String, value: Any) {
        var fos: FileOutputStream? = null
        var oos: ObjectOutputStream? = null
        try {
            fos = context.openFileOutput(name, Context.MODE_PRIVATE)
            oos = ObjectOutputStream(fos)
            oos.writeObject(value)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (fos != null) {
                fos.close()
            }
            if (oos != null) {
                oos.close()
            }
        }
    }
    fun getValue(context: Context, name: String): Any? {
        var fis: FileInputStream? = null
        var ois: ObjectInputStream? = null
        try {
            fis = context.openFileInput(name)
            if(fis==null){
                return null
            }
            ois = ObjectInputStream(fis)
            return ois.readObject()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (e: IOException) {
                    // fis流关闭异常
                    e.printStackTrace()
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (e: IOException) {
                    // ois流关闭异常
                    e.printStackTrace()
                }
            }
        }
        return null
    }
    fun deleteFile(name: String,context: Context){
        context.deleteFile(name)
    }
}
