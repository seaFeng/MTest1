package com.haige.mtest1.mtest1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.haige.mtest1.mtest1.R;

/**
 * Created by Administrator on 2017/4/7 0007.
 */

public class PercentageRing extends View {
    private Paint mCirclePaint;
    private Paint mTextPaint;
    private Paint mArcPaint;
    private int mCircleX;
    private int mCircleY;
    private float mCurrentAngle;
    private RectF mArcRectF;
    private float mStartSweepValue;
    private float mTargetPercent;
    private float mCurrentPercent;

    private int mRadius;
    private int mCircleBackground;
    private int mRingColor;
    private int mTextSize;
    private int mTextColor;

    public PercentageRing(Context context) {
        super(context);
        init(context);
    }

    public PercentageRing(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PercentageRing);
    }

    public PercentageRing(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mStartSweepValue = -90;     // 圆环开始角度-90度 正北方向。
        mCurrentAngle = 0;          // 当前角度。
        mCurrentPercent = 0;        // 当前百分比。
        // 设置中心圆的画笔。
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mCircleBackground);
    }
}
