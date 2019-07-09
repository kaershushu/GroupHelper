package com.example.lw.grouphelper

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group

class MainActivity : AppCompatActivity() {

    lateinit var mGroupHelper: OccupiedGroupHelper
    lateinit var mGroup1:Group
    lateinit var mGroup2:Group
    lateinit var mButtonGroup: Button
    lateinit var mButtonCustom: Button
    lateinit var mButtonOccupiedGroupHelper: OccupiedGroupHelper
    lateinit var mButtonHide1: Button
    lateinit var mButtonHide2: Button
    lateinit var mButtonShow: Button
    lateinit var mButtonHide: Button

    lateinit var mTvBottom1: TextView
    lateinit var mTvBottom2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        mButtonGroup.setOnClickListener {
            mGroup1.visibility = View.VISIBLE
            mGroup2.visibility = View.GONE
            mGroupHelper.isGroupBehavior = true
        }
        mButtonCustom.setOnClickListener {
            mGroup1.visibility = View.GONE
            mGroup2.visibility = View.VISIBLE
            mGroupHelper.isGroupBehavior = false
        }
        mButtonHide1.setOnClickListener {
            mTvBottom1.visibility = View.GONE
            mTvBottom2.visibility = View.VISIBLE
        }
        mButtonHide2.setOnClickListener {
            mTvBottom1.visibility = View.VISIBLE
            mTvBottom2.visibility = View.GONE
        }
        mButtonShow.setOnClickListener {
            mButtonOccupiedGroupHelper.visibility = View.VISIBLE
        }
        mButtonHide.setOnClickListener {
            mButtonOccupiedGroupHelper.visibility = View.GONE
        }
    }

    private fun initView() {
        mGroup1 = findViewById(R.id.Group_group)
        mGroup2 = findViewById(R.id.Group_grouphelper)
        mGroupHelper = findViewById(R.id.GroupHelper)
        mButtonGroup = findViewById(R.id.Button_group)
        mButtonCustom = findViewById(R.id.Button_occupied_group)
        mButtonOccupiedGroupHelper = findViewById(R.id.GroupHelper)
        mButtonHide1 = findViewById(R.id.Button_hide_1)
        mButtonHide2 = findViewById(R.id.Button_hide_2)
        mButtonShow = findViewById(R.id.Button_show)
        mButtonHide = findViewById(R.id.Button_hide)
        mTvBottom1 = findViewById(R.id.TextView_3)
        mTvBottom2 = findViewById(R.id.TextView_4)
    }
}
