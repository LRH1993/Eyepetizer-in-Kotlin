package com.tt.lvruheng.eyepetizer.ui

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar
import com.tt.lvruheng.eyepetizer.R
import com.tt.lvruheng.eyepetizer.mvp.contract.FindDetailContract
import com.tt.lvruheng.eyepetizer.mvp.model.bean.HotBean
import com.tt.lvruheng.eyepetizer.mvp.presenter.FindDetailPresenter
import kotlinx.android.synthetic.main.activity_find_detail.*

/**
 * Created by lvruheng on 2017/7/8.
 */
class FindDetailActivity : AppCompatActivity(),FindDetailContract.View {
    lateinit var mPresenter : FindDetailPresenter
    override fun setData(bean: HotBean) {

    }

    lateinit var name: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_detail)
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init()
        setToolbar()
    }

    private fun setToolbar() {
        intent.getStringExtra("name")?.let {
            name = intent.getStringExtra("name")
            tv_bar_title.typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
            tv_bar_title.text = name
        }
    }
}