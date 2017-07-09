package com.tt.lvruheng.eyepetizer.search

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.animation.DecelerateInterpolator
import android.view.ViewAnimationUtils
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.annotation.SuppressLint
import android.view.View


/**
 * Created by lvruheng on 2017/7/9.
 */
class CircularRevealAnim {
    companion object {

        val DURATION: Long = 200
    }

    private var mListener: AnimListener? = null

    interface AnimListener {

        fun onHideAnimationEnd()

        fun onShowAnimationEnd()
    }

    @SuppressLint("NewApi")
    private fun actionOtherVisible(isShow: Boolean, triggerView: View, animView: View) {

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            if (isShow) {
                animView.visibility = View.VISIBLE
                if (mListener != null) mListener!!.onShowAnimationEnd()
            } else {
                animView.visibility = View.GONE
                if (mListener != null) mListener!!.onHideAnimationEnd()
            }
            return
        }

        /**
         * 计算 triggerView 的中心位置
         */
        val tvLocation = IntArray(2)
        triggerView.getLocationInWindow(tvLocation)
        val tvX = tvLocation[0] + triggerView.width / 2
        val tvY = tvLocation[1] + triggerView.height / 2

        /**
         * 计算 animView 的中心位置
         */
        val avLocation = IntArray(2)
        animView.getLocationInWindow(avLocation)
        val avX = avLocation[0] + animView.width / 2
        val avY = avLocation[1] + animView.height / 2

        val rippleW = if (tvX < avX) animView.width - tvX else tvX - avLocation[0]
        val rippleH = if (tvY < avY) animView.height - tvY else tvY - avLocation[1]

        val maxRadius = Math.sqrt((rippleW * rippleW + rippleH * rippleH).toDouble()).toFloat()
        val startRadius: Float
        val endRadius: Float

        if (isShow) {
            startRadius = 0f
            endRadius = maxRadius
        } else {
            startRadius = maxRadius
            endRadius = 0f
        }

        val anim = ViewAnimationUtils.createCircularReveal(animView, tvX, tvY, startRadius, endRadius)
        animView.visibility = View.VISIBLE
        anim.duration = DURATION
        anim.interpolator = DecelerateInterpolator()

        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                if (isShow) {
                    animView.visibility = View.VISIBLE
                    if (mListener != null) mListener!!.onShowAnimationEnd()
                } else {
                    animView.visibility = View.GONE
                    if (mListener != null) mListener!!.onHideAnimationEnd()
                }
            }
        })

        anim.start()
    }

    fun show(triggerView: View, showView: View) {
        actionOtherVisible(true, triggerView, showView)
    }

    fun hide(triggerView: View, hideView: View) {
        actionOtherVisible(false, triggerView, hideView)
    }

    fun setAnimListener(listener: AnimListener) {
        mListener = listener
    }


}