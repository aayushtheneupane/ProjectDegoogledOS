package com.android.messaging.p041ui.mediapicker;

import android.hardware.Camera;

/* renamed from: com.android.messaging.ui.mediapicker.m */
class C1338m implements Camera.AutoFocusMoveCallback {
    final /* synthetic */ C1352t this$0;

    C1338m(C1352t tVar) {
        this.this$0 = tVar;
    }

    public void onAutoFocusMoving(boolean z, Camera camera) {
        this.this$0.f2164hI.mo7792da(z);
    }
}
