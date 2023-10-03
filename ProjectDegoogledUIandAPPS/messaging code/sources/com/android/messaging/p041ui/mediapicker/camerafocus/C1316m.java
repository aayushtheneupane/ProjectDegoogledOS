package com.android.messaging.p041ui.mediapicker.camerafocus;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/* renamed from: com.android.messaging.ui.mediapicker.camerafocus.m */
class C1316m extends Animation {
    private float mFrom = 1.0f;
    private float mTo = 1.0f;
    final /* synthetic */ C1317n this$0;

    public C1316m(C1317n nVar) {
        this.this$0 = nVar;
        setFillAfter(true);
    }

    /* access modifiers changed from: protected */
    public void applyTransformation(float f, Transformation transformation) {
        C1317n nVar = this.this$0;
        float f2 = this.mFrom;
        int unused = nVar.f2099iJ = (int) (((this.mTo - f2) * f) + f2);
    }

    public void setScale(float f, float f2) {
        this.mFrom = f;
        this.mTo = f2;
    }
}
