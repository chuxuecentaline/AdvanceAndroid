/*
package com.example.cherish.salehouse_kotlin.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint


import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.cherish.salehouse_kotlin.bean.Point

*/
/** 九宫格解锁
 * Created by cherish
 *//*

class LockPatternView : View {
    // 二维数组初始化，int[3][3]
    private var mPoints: Array<Array<Point?>> = Array(3) { Array<Point?>(3, { null }) }
    // 是否初始化
    private var mIsInit = false
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    // 外圆的半径
    private var mDotRadius: Int = 0
    // 画笔
    private var mLinePaint: Paint? = null
    private var mPressedPaint: Paint? = null
    private var mErrorPaint: Paint? = null
    private var mNormalPaint: Paint? = null
    private var mArrowPaint: Paint? = null
    // 颜色
    private val mOuterPressedColor = 0xff8cbad8.toInt()
    private val mInnerPressedColor = 0xff0596f6.toInt()
    private val mOuterNormalColor = 0xffd9d9d9.toInt()
    private val mInnerNormalColor = 0xff929292.toInt()
    private val mOuterErrorColor = 0xff901032.toInt()
    private val mInnerErrorColor = 0xffea0945.toInt()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas) {
        if (!mIsInit) {
            initPoints()
        }

        drawToCanvas(canvas)
    }

    private fun drawToCanvas(canvas: Canvas) {
        for (i in mPoints.indices) {
            for (j in 0..mPoints[i].size - 1) {
                val point = mPoints[i][j]
                if (point != null) {
                    // 循环绘制默认圆
                    mNormalPaint!!.color = mOuterNormalColor
                    canvas.drawCircle(point.centerX.toFloat(), point.centerY.toFloat(), mDotRadius.toFloat(), mNormalPaint!!)
                    mNormalPaint!!.color = mInnerNormalColor
                    canvas.drawCircle(point.centerX.toFloat(), point.centerY.toFloat(), (mDotRadius / 6).toFloat(), mNormalPaint!!
                }
            }
        }

        drawLineToCanvas(canvas)
    }

    */
/**
     * 初始化点
     *//*

    private fun initPoints() {
        mWidth = width
        mHeight = height

        var offsetX = 0
        var offsetY = 0

        if (mWidth > mHeight) {
            offsetX = (mWidth - mHeight) / 2
            mWidth = mHeight
        } else {
            offsetY = (mHeight - mWidth) / 2
            mHeight = mWidth
        }

        mDotRadius = mWidth / 12

        val padding = mDotRadius / 2
        val sideSize = (mWidth - 2 * padding) / 3
        offsetX += padding
        offsetY += padding

        for (i in mPoints.indices) {
            for (j in mPoints.indices) {
                // 循环初始化九个点
                mPoints[i][j] = Point(offsetX + sideSize * (i * 2 + 1) / 2,
                        offsetY + sideSize * (j * 2 + 1) / 2, i * mPoints.size + j)
            }
        }

        initPaint()

        mIsInit = true
    }


    private fun initPaint() {
        // 线的画笔
        mLinePaint = Paint()
        mLinePaint!!.color = mInnerPressedColor
        mLinePaint!!.style = Paint.Style.STROKE
        mLinePaint!!.isAntiAlias = true
        mLinePaint!!.strokeWidth = (mDotRadius / 9).toFloat()
        // 按下的画笔
        mPressedPaint = Paint()
        mPressedPaint!!.style = Paint.Style.STROKE
        mPressedPaint!!.isAntiAlias = true
        mPressedPaint!!.strokeWidth = (mDotRadius / 6).toFloat()
        // 错误的画笔
        mErrorPaint = Paint()
        mErrorPaint!!.style = Paint.Style.STROKE
        mErrorPaint!!.isAntiAlias = true
        mErrorPaint!!.strokeWidth = (mDotRadius / 6).toFloat()
        // 默认的画笔
        mNormalPaint = Paint()
        mNormalPaint!!.style = Paint.Style.STROKE
        mNormalPaint!!.isAntiAlias = true
        mNormalPaint!!.strokeWidth = (mDotRadius / 9).toFloat()
        // 箭头的画笔
        mArrowPaint = Paint()
        mArrowPaint!!.color = mInnerPressedColor
        mArrowPaint!!.style = Paint.Style.FILL
        mArrowPaint!!.isAntiAlias = true
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        mMovingX = event.x
        mMovingY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val firstPoint = point
                if (firstPoint != null) {
                    // 已经开始选点了
                    mSelectPoints.add(firstPoint)
                    // 点设置为已经选中
                    firstPoint.setStatusPressed()
                    // 开始绘制
                    mSelectBegin = true
                }
            }
        }

        invalidate()
        return true
    }

    */
/**
     * 获取按下的点
     * @return 当前按下的点
     *//*

    private val point: Point?
        get() {
            for (i in mPoints.indices) {
                for (j in 0..mPoints[i].size - 1) {
                    val point = mPoints[i][j]
                    if (point != null) {
                        if (MathUtil.checkInRound(point.centerX.toFloat(), point.centerY.toFloat(), mDotRadius.toFloat(), mMovingX, mMovingY)) {
                            return point
                        }
                    }
                }
            }
            return null
        }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mMovingX = event.x
        mMovingY = event.y

        when (event.action) {
            MotionEvent.ACTION_MOVE -> if (mSelectBegin) {
                val selectPoint = point
                if (selectPoint != null) {
                    selectPoint.setStatusPressed()
                    if (!mSelectPoints.contains(selectPoint)) {
                        // 把选中的点添加到集合
                        mSelectPoints.add(selectPoint)
                    }
                }
            }
            MotionEvent.ACTION_UP -> if (mSelectBegin) {
                if (mSelectPoints.size == 1) {
                    // 清空选择
                    clearSelectPoints()
                } else if (mSelectPoints.size <= 4) {
                    // 太短显示错误
                    showSelectError()
                } else {
                    // 成功回调
                    if (mListener != null) {
                        lockCallBack()
                    }
                }
                mSelectBegin = false
            }
        }

        invalidate()
        return true
    }

    */
/**
     * 画线
     * @param canvas
     *//*

    private fun drawLineToCanvas(canvas: Canvas) {
        if (mSelectPoints.size >= 1) {
            if (mIsErrorStatus) {
                mLinePaint!!.color = mInnerErrorColor
                mArrowPaint!!.color = mInnerErrorColor
            } else {
                mLinePaint!!.color = mInnerPressedColor
                mArrowPaint!!.color = mInnerPressedColor
            }

            var lastPoint = mSelectPoints[0]
            for (i in 1..mSelectPoints.size - 1) {
                val point = mSelectPoints[i]
                // 不断的画线
                drawLine(lastPoint, point, canvas, mLinePaint!!)
                drawArrow(canvas, mArrowPaint!!, lastPoint, point, (mDotRadius / 4).toFloat(), 38)
                lastPoint = point
            }

            val isInnerPoint = MathUtil.checkInRound(lastPoint.centerX.toFloat(), lastPoint.centerY.toFloat(), mDotRadius.toFloat(), mMovingX, mMovingY)
            if (mSelectBegin && !isInnerPoint) {
                drawLine(lastPoint, Point(mMovingX.toInt(), mMovingY.toInt(), -1), canvas, mLinePaint!!)
            }
        }
    }

    */
/**
     * 画线
     *//*

    private fun drawLine(start: Point, end: Point, canvas: Canvas, paint: Paint) {
        val d = MathUtil.distance(start.centerX.toDouble(), start.centerY.toDouble(), end.centerX.toDouble(), end.centerY.toDouble())
        val rx = (((end.centerX - start.centerX) * mDotRadius).toDouble() / 5.0 / d).toFloat()
        val ry = (((end.centerY - start.centerY) * mDotRadius).toDouble() / 5.0 / d).toFloat()
        canvas.drawLine(start.centerX + rx, start.centerY + ry, end.centerX - rx, end.centerY - ry, paint)
    }

    */
/**
     * 画箭头
     *//*

    private fun drawArrow(canvas: Canvas, paint: Paint, start: Point, end: Point, arrowHeight: Float, angle: Int) {
        val d = MathUtil.distance(start.centerX.toDouble(), start.centerY.toDouble(), end.centerX.toDouble(), end.centerY.toDouble())
        val sin_B = ((end.centerX - start.centerX) / d).toFloat()
        val cos_B = ((end.centerY - start.centerY) / d).toFloat()
        val tan_A = Math.tan(Math.toRadians(angle.toDouble())).toFloat()
        val h = (d - arrowHeight.toDouble() - mDotRadius * 1.1).toFloat()
        val l = arrowHeight * tan_A
        val a = l * sin_B
        val b = l * cos_B
        val x0 = h * sin_B
        val y0 = h * cos_B
        val x1 = start.centerX + (h + arrowHeight) * sin_B
        val y1 = start.centerY + (h + arrowHeight) * cos_B
        val x2 = start.centerX + x0 - b
        val y2 = start.centerY.toFloat() + y0 + a
        val x3 = start.centerX.toFloat() + x0 + b
        val y3 = start.centerY + y0 - a
        val path = Path()
        path.moveTo(x1, y1)
        path.lineTo(x2, y2)
        path.lineTo(x3, y3)
        path.close()
        canvas.drawPath(path, paint)
    }
    */
/**
     * 回调
     *//*

    private fun lockCallBack() {
        var password = ""
        for (selectPoint in mSelectPoints) {
            password += selectPoint.index
        }
        mListener!!.lock(password)
    }

    */
/**
     * 显示错误
     *//*

    fun showSelectError() {
        for (selectPoint in mSelectPoints) {
            selectPoint.setStatusError()
            mIsErrorStatus = true
        }

        postDelayed({
            clearSelectPoints()
            mIsErrorStatus = false
            invalidate()
        }, 1000)
    }

    */
/**
     * 清空所有的点
     *//*

    private fun clearSelectPoints() {
        for (selectPoint in mSelectPoints) {
            selectPoint.setStatusNormal()
        }
        mSelectPoints.clear()
    }

    */
/**
     * 清空所有的点
     *//*

    fun clearSelect() {
        for (selectPoint in mSelectPoints) {
            selectPoint.setStatusNormal()
        }
        mSelectPoints.clear()
        invalidate()
    }

    private var mListener: LockPatternListener? = null
    fun setLockPatternListener(listener: LockPatternListener) {
        this.mListener = listener
    }

    interface LockPatternListener {
        fun lock(password: String)
    }


}*/
