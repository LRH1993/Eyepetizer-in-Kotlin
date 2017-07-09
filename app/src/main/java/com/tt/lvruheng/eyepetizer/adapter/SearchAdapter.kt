package com.tt.lvruheng.eyepetizer.adapter

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.tt.lvruheng.eyepetizer.R
import com.google.android.flexbox.FlexboxLayoutManager



/**
 * Created by lvruheng on 2017/7/9.
 */
class SearchAdapter(context: Context, list: ArrayList<String>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    var context: Context? = null;
    var list: ArrayList<String>? = null
    var inflater: LayoutInflater? = null

    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SearchViewHolder {
        return SearchViewHolder(inflater?.inflate(R.layout.item_search, parent, false), context!!)
    }

    override fun onBindViewHolder(holder: SearchViewHolder?, position: Int) {
        holder?.tv_title?.text = list!![position]
        val params = holder?.tv_title?.layoutParams
        if (params is FlexboxLayoutManager.LayoutParams) {
            (holder?.tv_title?.layoutParams as FlexboxLayoutManager.LayoutParams).flexGrow = 1.0f
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }


    class SearchViewHolder(itemView: View?, context: Context) : RecyclerView.ViewHolder(itemView) {
        var tv_title: TextView = itemView?.findViewById(R.id.tv_title) as TextView
        
    }
}