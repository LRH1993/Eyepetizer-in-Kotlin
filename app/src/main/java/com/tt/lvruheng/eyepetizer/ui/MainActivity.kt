package com.tt.lvruheng.eyepetizer.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tt.lvruheng.eyepetizer.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRadioButton()
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
        when(v?.id){
            R.id.rb_find ->{
                println("点击find")
                rb_find.isChecked = true
                rb_find.setTextColor(resources.getColor(R.color.black))
            }
            R.id.rb_home ->{
                rb_home.isChecked = true
                rb_home.setTextColor(resources.getColor(R.color.black))
            }
            R.id.rb_hot ->{
                rb_hot.isChecked = true
                rb_hot.setTextColor(resources.getColor(R.color.black))
            }
            R.id.rb_mine ->{
                rb_mine.isChecked = true
                rb_mine.setTextColor(resources.getColor(R.color.black))
            }
        }

    }
    private fun clearState(){
        rg_root.clearCheck()
        rb_home.setTextColor(resources.getColor(R.color.gray))
        rb_mine.setTextColor(resources.getColor(R.color.gray))
        rb_hot.setTextColor(resources.getColor(R.color.gray))
        rb_find.setTextColor(resources.getColor(R.color.gray))
    }
}
