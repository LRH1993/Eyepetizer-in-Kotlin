package com.tt.lvruheng.eyepetizer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.tt.lvruheng.eyepetizer.mvp.model.bean.FindBean

/**
 * Created by lvruheng on 2017/7/6.
 */
class FindAdapter(context: Context,list: MutableList<FindBean>) : BaseAdapter(){
    var mContext : Context? = null
    var mList : MutableList<FindBean>? = null
    var mInflater : LayoutInflater? = null
    init {
        mContext = context
        mList = list
        mInflater = LayoutInflater.from(context)
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view : View

        return  view
    }

    override fun getItem(position: Int): FindBean? {
        return mList?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return  position.toLong()
    }

    override fun getCount(): Int {
        if(mList!=null){
            return mList!!.size
        }else{
            return 0
        }
    }
    class FindViewHolder(itemView : View){

    }

}