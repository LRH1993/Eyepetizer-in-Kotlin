package com.tt.lvruheng.eyepetizer.search

import android.app.DialogFragment
import com.tt.lvruheng.eyepetizer.R
import com.tt.lvruheng.eyepetizer.ui.fragment.BaseFragment
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Gravity
import android.view.WindowManager
import android.util.DisplayMetrics




/**
 * Created by lvruheng on 2017/7/9.
 */
const val SEARCH_TAG = "SearchFragment"
class SearchFragment : DialogFragment() {
    lateinit var mCircularRevealAnim : CircularRevealAnim
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogStyle);

    }

    override fun onStart() {
        super.onStart()
        initDialog()
    }

    private fun initDialog() {
        val window = dialog.window
        val metrics = resources.displayMetrics
        val width = (metrics.widthPixels * 0.98).toInt() //DialogSearch的宽
        window!!.setLayout(width, WindowManager.LayoutParams.MATCH_PARENT)
        window.setGravity(Gravity.TOP)
        window.setWindowAnimations(R.style.DialogEmptyAnimation)//取消过渡动画 , 使DialogSearch的出现更加平滑
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater?.inflate(R.layout.search_fragment,container,false)!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}