package com.tt.lvruheng.eyepetizer.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tt.lvruheng.eyepetizer.R
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HomeBean

/**
 * Created by lvruheng on 2017/7/5.
 */
class HomeAdatper(context: Context,list: MutableList<HomeBean.IssueListBean.ItemListBean>?) : RecyclerView.Adapter<HomeAdatper.HomeViewHolder>() {
    var context : Context? = null;
    var list : MutableList<HomeBean.IssueListBean.ItemListBean>? = null
    var inflater : LayoutInflater? = null
    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }
    override fun getItemCount(): Int {
       return list?.size ?:0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeViewHolder {
        return HomeViewHolder(inflater?.inflate(R.layout.item_home,parent,false))
    }

    override fun onBindViewHolder(holder: HomeViewHolder?, position: Int) {
    }


    class HomeViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {}

}
