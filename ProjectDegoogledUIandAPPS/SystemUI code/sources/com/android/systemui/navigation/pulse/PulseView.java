package com.android.systemui.navigation.pulse;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.FrameLayout;

public class PulseView extends View {
    private PulseControllerImpl mPulse;

    public PulseView(Context context, PulseControllerImpl pulseControllerImpl) {
        super(context);
        this.mPulse = pulseControllerImpl;
        setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        setWillNotDraw(false);
        setTag("PulseView");
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        this.mPulse.onSizeChanged(i, i2, i3, i4);
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void onDraw(Canvas canvas) {
        this.mPulse.onDraw(canvas);
        super.onDraw(canvas);
    }
}
