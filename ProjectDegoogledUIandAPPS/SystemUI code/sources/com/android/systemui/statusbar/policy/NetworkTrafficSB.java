package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.Dependency;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.statusbar.StatusIconDisplayable;

public class NetworkTrafficSB extends NetworkTraffic implements DarkIconDispatcher.DarkReceiver, StatusIconDisplayable {
    private boolean mKeyguardShowing;
    private boolean mSystemIconVisible;
    private boolean mTrafficVisible;
    private int mVisibleState;

    public String getSlot() {
        return "networktraffic";
    }

    public void setDecorColor(int i) {
    }

    public NetworkTrafficSB(Context context) {
        this(context, (AttributeSet) null);
    }

    public NetworkTrafficSB(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NetworkTrafficSB(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mVisibleState = -1;
        this.mTrafficVisible = false;
        this.mSystemIconVisible = true;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((DarkIconDispatcher) Dependency.get(DarkIconDispatcher.class)).addDarkReceiver((DarkIconDispatcher.DarkReceiver) this);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ((DarkIconDispatcher) Dependency.get(DarkIconDispatcher.class)).removeDarkReceiver((DarkIconDispatcher.DarkReceiver) this);
    }

    public void onDarkChanged(Rect rect, float f, int i) {
        if (this.mIsEnabled && this.mLocation != 1) {
            this.mTintColor = DarkIconDispatcher.getTint(rect, this, i);
            setTextColor(this.mTintColor);
            updateTrafficDrawable();
        }
    }

    public boolean isIconVisible() {
        return this.mIsEnabled && this.mLocation == 0;
    }

    public int getVisibleState() {
        return this.mVisibleState;
    }

    public void setVisibleState(int i, boolean z) {
        if (i != this.mVisibleState) {
            this.mVisibleState = i;
            if (i != 0) {
                this.mSystemIconVisible = false;
            } else {
                this.mSystemIconVisible = true;
            }
            update();
        }
    }

    /* access modifiers changed from: protected */
    public void makeVisible() {
        int i = 0;
        boolean z = this.mSystemIconVisible && !this.mKeyguardShowing && this.mLocation == 0;
        if (!z) {
            i = 8;
        }
        setVisibility(i);
        this.mVisible = z;
    }

    public void setStaticDrawableColor(int i) {
        this.mTintColor = i;
        setTextColor(this.mTintColor);
        updateTrafficDrawable();
    }

    /* access modifiers changed from: protected */
    public void updateTrafficDrawable() {
        setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, getContext().getDrawable(C1776R$drawable.stat_sys_network_traffic_spacer), (Drawable) null);
        setTextColor(this.mTintColor);
    }

    public void setKeyguardShowing(boolean z) {
        this.mKeyguardShowing = z;
        if (z) {
            setVisibility(8);
            this.mVisible = false;
            return;
        }
        maybeRestoreVisibility();
    }

    private void maybeRestoreVisibility() {
        if (!this.mVisible && this.mIsEnabled && this.mLocation == 0 && !this.mKeyguardShowing && this.mSystemIconVisible && restoreViewQuickly()) {
            setVisibility(0);
            this.mVisible = true;
            update();
        }
    }
}
