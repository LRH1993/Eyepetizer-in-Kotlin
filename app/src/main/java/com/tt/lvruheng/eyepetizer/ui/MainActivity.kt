package com.tt.lvruheng.eyepetizer.ui

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar
import com.tt.lvruheng.eyepetizer.R
import com.tt.lvruheng.eyepetizer.search.SEARCH_TAG
import com.tt.lvruheng.eyepetizer.search.SearchFragment
import com.tt.lvruheng.eyepetizer.ui.fragment.FindFragment
import com.tt.lvruheng.eyepetizer.ui.fragment.HomeFragment
import com.tt.lvruheng.eyepetizer.ui.fragment.HotFragment
import com.tt.lvruheng.eyepetizer.ui.fragment.MineFragment
import com.tt.lvruheng.eyepetizer.utils.showToast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.view.WindowManager



class MainActivity : AppCompatActivity(), View.OnClickListener {
    var homeFragment: HomeFragment? = null
    var findFragment: FindFragment? = null
    var hotFragemnt: HotFragment? = null
    var mineFragment: MineFragment? = null
    var mExitTime: Long = 0
    var toast: Toast? = null
    lateinit var searchFragment: SearchFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init()
        val window = window
        val params = window.attributes
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        window.attributes = params
        setRadioButton()
        initToolbar()
        initFragment(savedInstanceState)
    }

    private fun initToolbar() {
        var today = getToday()
        tv_bar_title.text = today
        tv_bar_title.typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        iv_search.setOnClickListener {
            if (rb_mine.isChecked) {
                //todo 点击设置
            } else {
                //todo 点击搜索
                searchFragment = SearchFragment()
                searchFragment.show(fragmentManager, SEARCH_TAG)
            }

        }
    }

    private fun getToday(): String {
        var list = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
        var data: Date = Date()
        var calendar: Calendar = Calendar.getInstance()
        calendar.time = data
        var index: Int = calendar.get(Calendar.DAY_OF_WEEK) - 1
        if (index < 0) {
            index = 0
        }
        return list[index]
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            //异常情况
            val mFragments: List<Fragment> = supportFragmentManager.fragments
            for (item in mFragments) {
                if (item is HomeFragment) {
                    homeFragment = item
                }
                if (item is FindFragment) {
                    findFragment = item
                }
                if (item is HotFragment) {
                    hotFragemnt = item
                }
                if (item is MineFragment) {
                    mineFragment = item
                }
            }
        } else {
            homeFragment = HomeFragment()
            findFragment = FindFragment()
            mineFragment = MineFragment()
            hotFragemnt = HotFragment()
            val fragmentTrans = supportFragmentManager.beginTransaction()
            fragmentTrans.add(R.id.fl_content, homeFragment)
            fragmentTrans.add(R.id.fl_content, findFragment)
            fragmentTrans.add(R.id.fl_content, mineFragment)
            fragmentTrans.add(R.id.fl_content, hotFragemnt)
            fragmentTrans.commit()
        }
        supportFragmentManager.beginTransaction().show(homeFragment)
                .hide(findFragment)
                .hide(mineFragment)
                .hide(hotFragemnt)
                .commit()
    }

    private fun setRadioButton() {
        rb_home.isChecked = true
        rb_home.setTextColor(resources.getColor(R.color.black))
        rb_home.setOnClickListener(this)
        rb_find.setOnClickListener(this)
        rb_hot.setOnClickListener(this)
        rb_mine.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        clearState()
        when (v?.id) {
            R.id.rb_find -> {
                rb_find.isChecked = true
                rb_find.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(findFragment)
                        .hide(homeFragment)
                        .hide(mineFragment)
                        .hide(hotFragemnt)
                        .commit()
                tv_bar_title.text = "Discover"
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.drawable.icon_search)
            }
            R.id.rb_home -> {
                rb_home.isChecked = true
                rb_home.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(homeFragment)
                        .hide(findFragment)
                        .hide(mineFragment)
                        .hide(hotFragemnt)
                        .commit()
                tv_bar_title.text = getToday()
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.drawable.icon_search)
            }
            R.id.rb_hot -> {
                rb_hot.isChecked = true
                rb_hot.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(hotFragemnt)
                        .hide(findFragment)
                        .hide(mineFragment)
                        .hide(homeFragment)
                        .commit()
                tv_bar_title.text = "Ranking"
                tv_bar_title.visibility = View.VISIBLE
                iv_search.setImageResource(R.drawable.icon_search)
            }
            R.id.rb_mine -> {
                rb_mine.isChecked = true
                rb_mine.setTextColor(resources.getColor(R.color.black))
                supportFragmentManager.beginTransaction().show(mineFragment)
                        .hide(findFragment)
                        .hide(homeFragment)
                        .hide(hotFragemnt)
                        .commit()
                tv_bar_title.visibility = View.INVISIBLE
                iv_search.setImageResource(R.drawable.icon_setting)
            }
        }

    }

    private fun clearState() {
        rg_root.clearCheck()
        rb_home.setTextColor(resources.getColor(R.color.gray))
        rb_mine.setTextColor(resources.getColor(R.color.gray))
        rb_hot.setTextColor(resources.getColor(R.color.gray))
        rb_find.setTextColor(resources.getColor(R.color.gray))
    }

    override fun onPause() {
        super.onPause()
        toast?.let { toast!!.cancel() }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 3000) {
                finish()
                toast!!.cancel()
            } else {
                mExitTime = System.currentTimeMillis()
                toast = showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
