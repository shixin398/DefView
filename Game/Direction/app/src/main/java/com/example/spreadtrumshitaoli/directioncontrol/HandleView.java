package com.example.spreadtrumshitaoli.directioncontrol;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by SPREADTRUM\shitao.li on 17-6-6.
 */

public class HandleView extends View {

    private String TAG = "HandleView";

    private float[] mTouchPosition;
    private Paint mCirclePaint;

    public HandleView(Context context) {
        super(context, null);
    }
    public HandleView(Context context, AttributeSet attrs){
        super(context, attrs);
    }
    public HandleView(Context context, AttributeSet attrs, int defStyleRes) {
        super(context, attrs, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSizeDef(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSizeDef(getSuggestedMinimumHeight(), heightMeasureSpec));
        int childWidthSize = getMeasuredWidth();
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        doDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                if(null == mTouchPosition) {
                    mTouchPosition = new float[2];
                }
                mTouchPosition[0] = event.getX();
                mTouchPosition[1] = event.getY();
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                mTouchPosition = null;
                invalidate();
                return true;
        }
        return super.onTouchEvent(event);
    }

    private void doDraw(Canvas canvas) {
        int radiusOuter = getWidth()/2;
        int radiusInner = getWidth()/4;
        int cxOuter = getWidth()/2;
        int cyOuter = getWidth()/2;

        //For first time, new paint and lastPoint.
        if (null == mCirclePaint) {
            mCirclePaint = new Paint();
        }

        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.argb(0x7f, 0x11, 0x11, 0x11));
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawCircle(cxOuter, cyOuter, radiusOuter, mCirclePaint);

        //For first time, no Touch event
        if (null == mTouchPosition) {
            mCirclePaint.setColor(Color.GREEN);
            canvas.drawCircle(cxOuter, cyOuter, radiusInner, mCirclePaint);
            canvas.save();
            return;
        }

        float cxInner = mTouchPosition[0];
        float cyInner = mTouchPosition[1];
        boolean outOfBound = Math.sqrt(Math.pow(cxInner-cxOuter, 2) + Math.pow(cyInner-cyOuter, 2)) + radiusInner > radiusOuter;
        Log.d(TAG,"Out of Boun? "+outOfBound);
        if(outOfBound) {
            double ratio = (radiusOuter - radiusInner)/
                    Math.sqrt(Math.pow((mTouchPosition[0] - cxOuter), 2) + Math.pow((mTouchPosition[1] - cyOuter), 2));
            cxInner = (float) (ratio * (mTouchPosition[0] - cxOuter) + cxOuter);
            cyInner = (float) (ratio * (mTouchPosition[1] - cyOuter) + cyOuter);
        }

        mCirclePaint.setColor(Color.BLUE);
        canvas.drawCircle(cxInner, cyInner, radiusInner, mCirclePaint);
        canvas.save();
    }
    /**
     * Utility to return a default size. Uses the supplied size if the
     * MeasureSpec imposed no constraints. Will get larger if allowed
     * by the MeasureSpec.
     *
     * @param size Default size for this view
     * @param measureSpec Constraints imposed by the parent
     * @return The size this view should be.
     */
    private int getDefaultSizeDef(int size, int measureSpec){
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(size, specSize);
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
        }
        return result;
    }

}
