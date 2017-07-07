package com.tt.lvruheng.eyepetizer.ui.fragment

import android.graphics.Typeface
import com.tt.lvruheng.eyepetizer.R
import kotlinx.android.synthetic.main.mine_fragment.*

/**
 * Created by lvruheng on 2017/7/4.
 */
class MineFragment : BaseFragment(){
    override fun getLayoutResources(): Int {
        return R.layout.mine_fragment
    }

    override fun initView() {
        tv_advise.typeface = Typeface.createFromAsset(context?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        tv_watch.typeface = Typeface.createFromAsset(context?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
        tv_save.typeface = Typeface.createFromAsset(context?.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }

}