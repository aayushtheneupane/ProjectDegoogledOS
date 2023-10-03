package com.android.dialer.app.list;

import android.content.Context;
import android.support.p000v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class DialerViewPager extends ViewPager {
    private boolean enableSwipingPages = true;

    public DialerViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.enableSwipingPages) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.enableSwipingPages) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public void setEnableSwipingPages(boolean z) {
        this.enableSwipingPages = z;
    }
}
