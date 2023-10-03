package com.android.dialer.widget;

import android.content.Context;
import android.support.p000v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class LockableViewPager extends ViewPager {
    private boolean swipingLocked;

    public LockableViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return !this.swipingLocked && super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !this.swipingLocked && super.onTouchEvent(motionEvent);
    }

    public void setSwipingLocked(boolean z) {
        this.swipingLocked = z;
    }
}
