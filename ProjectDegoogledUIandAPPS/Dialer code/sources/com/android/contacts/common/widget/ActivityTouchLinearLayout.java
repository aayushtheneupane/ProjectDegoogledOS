package com.android.contacts.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import com.android.dialer.util.TouchPointManager;

public class ActivityTouchLinearLayout extends LinearLayout {
    public ActivityTouchLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        TouchPointManager.getInstance().setPoint((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
        return false;
    }
}
