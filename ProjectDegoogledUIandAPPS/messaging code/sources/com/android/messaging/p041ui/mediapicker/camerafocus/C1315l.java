package com.android.messaging.p041ui.mediapicker.camerafocus;

import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/* renamed from: com.android.messaging.ui.mediapicker.camerafocus.l */
class C1315l extends Animation {
    private float mFrom;
    private float mTo;
    private float mValue;

    public C1315l(C1317n nVar, float f, float f2) {
        setFillAfter(true);
        setInterpolator(new LinearInterpolator());
        this.mFrom = f;
        this.mTo = f2;
    }

    /* access modifiers changed from: protected */
    public void applyTransformation(float f, Transformation transformation) {
        float f2 = this.mFrom;
        this.mValue = ((this.mTo - f2) * f) + f2;
    }

    public float getValue() {
        return this.mValue;
    }
}
