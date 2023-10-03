package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.util.AttributeSet;

public class ClockCenter extends Clock {
    private boolean mClockVisibleByPolicy;
    private boolean mClockVisibleByUser;

    public ClockCenter(Context context) {
        this(context, (AttributeSet) null);
    }

    public ClockCenter(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ClockCenter(Context context, AttributeSet attributeSet, int i) {
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
        boolean z = true;
        if (this.mClockStyle != 1 || !this.mShowClock || !this.mClockVisibleByPolicy || !this.mClockVisibleByUser) {
            z = false;
        }
        if (!z) {
            i = 8;
        }
        setVisibility(i);
    }
}
