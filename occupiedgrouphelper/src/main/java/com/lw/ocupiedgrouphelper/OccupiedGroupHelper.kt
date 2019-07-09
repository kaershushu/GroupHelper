package com.lw.ocupiedgrouphelper

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.solver.widgets.ConstraintWidget
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group

/**
 * Created on 2019-07-03.
 * @author Alan
 */
class OccupiedGroupHelper constructor(context: Context?, attrs: AttributeSet?) : Group(context, attrs) {
    var isGroupBehavior: Boolean = true
        set(value) {
            field = value
            invalidate()
        }

    private var isGroupOccupied: Boolean = false

    init {
        val attrsArray = context!!.obtainStyledAttributes(attrs, R.styleable.OccupiedGroupHelper)
        isGroupBehavior = attrsArray.getBoolean(R.styleable.OccupiedGroupHelper_isGroupBehaviour, true).apply {
            mUseViewMeasure = this
        }
        attrsArray.recycle()
    }

    override fun init(attrs: AttributeSet?) {
        super.init(attrs)
        val attrsArray = context!!.obtainStyledAttributes(attrs, R.styleable.OccupiedGroupHelper)
        isGroupBehavior = attrsArray.getBoolean(R.styleable.OccupiedGroupHelper_isGroupBehaviour, true).apply {
            mUseViewMeasure = this
        }
        attrsArray.recycle()
    }

    override fun updatePreLayout(container: ConstraintLayout?) {
        val visibility = visibility
        for (i in 0 until mCount) {
            val id = mIds[i]
            val view = container!!.getViewById(id)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                view.elevation = elevation
            }
            // 和Group行为一致
            if (isGroupBehavior) {
                view.visibility = visibility
                continue
            }

            // 根据ChildView判断是否占用显示区域，一旦有子View是可见的，那么GroupHelper就需要占用空间
            // 没有子View是可见的，那么GroupHelper不占用空间
            isGroupOccupied = view.visibility != View.GONE
            if (isGroupOccupied) {
                break
            }
        }
    }

    override fun updatePostMeasure(container: ConstraintLayout?) {
        val widget: ConstraintWidget = container!!.getViewWidget(this)
        // 和Group行为一致，宽高为0
        if (isGroupBehavior) {
            widget.width = 0
            widget.height = 0
            return
        }

        // 子View不可见，宽高是0
        if (!isGroupOccupied) {
            widget.width = 0
            widget.height = 0
        }
    }
}