package com.android.messaging.p041ui.mediapicker.camerafocus;

import android.graphics.Canvas;

/* renamed from: com.android.messaging.ui.mediapicker.camerafocus.d */
public abstract class C1307d implements C1319p {
    protected int mBottom;
    protected int mLeft;
    protected RenderOverlay mOverlay;
    protected int mRight;
    protected int mTop;
    protected boolean mVisible;

    public abstract void onDraw(Canvas canvas);

    /* access modifiers changed from: protected */
    public void update() {
        RenderOverlay renderOverlay = this.mOverlay;
        if (renderOverlay != null) {
            renderOverlay.update();
        }
    }
}
