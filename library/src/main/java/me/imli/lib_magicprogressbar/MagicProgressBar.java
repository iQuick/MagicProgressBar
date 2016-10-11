package me.imli.lib_magicprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Doots on 2016/10/11.
 */
public class MagicProgressBar extends View {

    // 进度
    private int mProgress = 0;

    // ColorInt
    private int mFillColor = Color.GREEN;
    private int mBackgroundColor = Color.TRANSPARENT;

    // Pain
    private Paint mFillPaint;
    private Paint mBackgroundPaint;

    // 是否是圆头
    private boolean isRound = true;
    //
    private final RectF mRectF = new RectF();
    private final RectF mFillRectF = new RectF();

    public MagicProgressBar(Context context) {
        this(context, null);
    }

    public MagicProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MagicProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs == null) {
            mFillColor = mFillColor <= 0 ? Color.GREEN : mFillColor;
            mBackgroundColor = mBackgroundColor <= 0 ? Color.TRANSPARENT : mBackgroundColor;
        } else {
            TypedArray typedArray = null;
            try {
                typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MagicProgressBar);
                mBackgroundColor = typedArray.getColor(R.styleable.MagicProgressBar_mpb_background_color, Color.TRANSPARENT);
                mFillColor = typedArray.getColor(R.styleable.MagicProgressBar_mpb_fill_color, Color.GREEN);
                isRound = typedArray.getBoolean(R.styleable.MagicProgressBar_mpb_is_round, false);
            } finally {
                if (typedArray != null) {
                    typedArray.recycle();
                }
            }
        }

        mFillPaint = new Paint();
        mFillPaint.setColor(mFillColor);
        mFillPaint.setAntiAlias(true);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(mBackgroundColor);
        mBackgroundPaint.setAntiAlias(true);

    }

    /**
     * 设置进度
     * @param progress
     */
    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }


    /**
     * 获取进度
     * @return
     */
    public int getProgress() {
        return mProgress;
    }

    /**
     * 设置背景颜色
     * @param color
     */
    public void setBackgroundColor(final @ColorInt int color) {
        mBackgroundColor = color;
        invalidate();
    }

    /**
     * 设置进度条颜色
     * @param color
     */
    public void setFillColor(final @ColorInt int color) {
        mFillColor = color;
        invalidate();
    }

    /**
     * 设置是否圆头
     * @param round
     */
    public void setRound(final boolean round) {
        isRound = round;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 进度
        float drawProgress = getProgress();

        // 宽高
        final int width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        final int height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        // 绘制的宽高
        final int drawWidth = (int) (drawProgress * width / 100);
        final int drawRadius = height / 2;

        // 绘制背景
        mRectF.left = 0;
        mRectF.top = 0;
        mRectF.right = width;
        mRectF.bottom = height;

        if (mBackgroundColor != 0) {
            if (isRound) {
                canvas.drawRoundRect(mRectF, drawRadius, drawRadius, mBackgroundPaint);
            } else {
                canvas.drawRect(mRectF, mBackgroundPaint);
            }
        }

        // 绘制进度
        int centreWidth = width / 2;
        mFillRectF.left = centreWidth - (drawProgress * width / 2 / 100);
        mFillRectF.right = centreWidth + (drawProgress * width / 2 / 100);
        mFillRectF.top = 0;
        mFillRectF.bottom = height;

        if (isRound) {
            canvas.drawRoundRect(mFillRectF, drawRadius, drawRadius, mFillPaint);
        } else {
            canvas.drawRect(mFillRectF, mFillPaint);
        }
    }


}
