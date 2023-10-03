package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.util.AttributeSet;

public class ClockRight extends Clock {
    private boolean mClockVisibleByPolicy;
    private boolean mClockVisibleByUser;

    public ClockRight(Context context) {
        this(context, (AttributeSet) null);
    }

    public ClockRight(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ClockRight(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mClockVisibleByPolicy = true;
        this.mClockVisibleByUser = true;
    }

    public void setClockVisibilityByPolicy(boolean z) {
        this.mClockVisibleByPolicy = z;
        updateClockVisibility();
    }

    /* access modifiers changed from: protected */
    public void updateClockVisibility() {
        int i = 0;
        if (!(this.mClockStyle == 2 && this.mShowClock && this.mClockVisibleByPolicy && this.mClockVisibleByUser)) {
            i = 8;
        }
        setVisibility(i);
    }
}
