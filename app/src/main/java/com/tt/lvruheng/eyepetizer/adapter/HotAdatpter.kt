package com.tt.lvruheng.eyepetizer.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.View

/**
 * Created by lvruheng on 2017/7/6.
 */
class HotAdatpter(fm: FragmentManager,list: ArrayList<Fragment>,titles : MutableList<String>) : FragmentPagerAdapter(fm) {
    var mFm : FragmentManager = fm!!
    var mList : ArrayList<Fragment> = list
    var mTitles : MutableList<String> = titles
    override fun getItem(position: Int): Fragment {
        return mList[position]

    }
    override fun getCount(): Int {
        return mList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTitles[position]
    }

}