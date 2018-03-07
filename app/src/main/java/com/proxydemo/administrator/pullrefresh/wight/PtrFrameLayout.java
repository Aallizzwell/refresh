package com.proxydemo.administrator.pullrefresh.wight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/3/5 0005.
 */

public class PtrFrameLayout extends ViewGroup {

    private View mHeaderView;
    private int mHeaderId=0;
    protected View mContent;
    private int mContainerId = 0;
    private int mHeaderHeight;
    private float mLastX;
    private float mLastY;
    private MotionEvent mLastMoveEvent;

    public PtrFrameLayout(Context context) {
        this(context,null);
    }

    public PtrFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public PtrFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mHeaderView != null) {
            measureChild(mHeaderView,widthMeasureSpec,heightMeasureSpec);
            mHeaderHeight = mHeaderView.getMeasuredHeight();
        }

        if (mContent != null) {
            measureChild(mContent,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean flag, int i, int j, int k, int l) {
        layoutChildren();
    }

    private void layoutChildren() {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        if (mHeaderView != null) {
            LayoutParams layoutParams = mHeaderView.getLayoutParams();
            int left = paddingLeft;
            int top = -(mHeaderHeight - paddingTop);
            int right = left + mHeaderView.getMeasuredWidth();
            int bottom = top + mHeaderView.getMeasuredHeight();
            mHeaderView.layout(left, top, right, bottom);
        }
        if (mContent != null) {
             int contentLeft = paddingLeft;
             int contentTop = paddingTop;
             int contentRight = contentLeft+mContent.getMeasuredWidth();
             int contentBottom = contentTop+mContent.getMeasuredHeight();
            mContent.layout(contentLeft,contentTop,contentRight,contentBottom);
        }
    }

    @Override
    protected void onFinishInflate() {
        int childCount = getChildCount();
        if (childCount > 2) {
            throw new IllegalStateException(" can only contains 2 children");
        } else if (childCount == 2) {
            if (mHeaderId != 0 && mHeaderView == null) {
                mHeaderView = findViewById(mHeaderId);
            }
            if (mContainerId != 0 && mContent == null) {
                mContent = findViewById(mContainerId);
            }
            if (mContent == null || mHeaderView == null) {
                View child1 = getChildAt(0);
                View child2 = getChildAt(1);
                if (child1 instanceof PtrUIHandler) {
                    mHeaderView = child1;
                    mContent = child2;
                } else if (child2 instanceof PtrUIHandler) {
                    mHeaderView = child2;
                    mContent = child1;
                } else {
                    if (mContent == null && mHeaderView == null) {
                        mHeaderView = child1;
                        mContent = child2;
                    } else {
                        if (mHeaderView == null) {
                            mHeaderView = mContent == child1 ? child2 : child1;
                        } else {
                            mContent = mHeaderView == child1 ? child2 : child1;
                        }
                    }
                }
            }
        } else if (childCount == 1) {
            mContent = getChildAt(0);
        } else {
            TextView errorView = new TextView(getContext());
            errorView.setClickable(true);
            errorView.setTextColor(0xffff6600);
            errorView.setGravity(Gravity.CENTER);
            errorView.setTextSize(20);
            errorView.setText("The content view in PtrFrameLayout is empty. Do you forget to specify its id in xml layout file?");
            mContent = errorView;
            addView(mContent);
        }
        if (mHeaderView != null) {
            mHeaderView.bringToFront();
        }
        super.onFinishInflate();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mHeaderView == null || mContent == null) {
            return false;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = ev.getX();
                mLastY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                mLastMoveEvent = ev;
                float offsetX = ev.getX() - mLastX;
                float offsetY = ev.getY() - mLastY;
                mLastX = ev.getX();
                mLastY = ev.getY();
                mHeaderView.offsetTopAndBottom((int) offsetY);
                mContent.offsetTopAndBottom((int) offsetY);
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setHeaderView(View view) {
        if (mHeaderView != null && view != null && mHeaderView != view) {
            removeView(mHeaderView);
        }
        mHeaderView = view;
        LayoutParams layoutParams = mHeaderView.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            mHeaderView.setLayoutParams(layoutParams);
        }
        addView(mHeaderView);
        //TODO 不需要重新绘制？
    }
}
