package com.android.systemui.navigation.pulse;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class VisualizerView extends FrameLayout {
    private boolean mAttached;

    public VisualizerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public VisualizerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VisualizerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttached = true;
    }

    public void onDetachedFromWindow() {
        this.mAttached = false;
        super.onDetachedFromWindow();
    }

    public boolean isAttached() {
        return this.mAttached;
    }
}
