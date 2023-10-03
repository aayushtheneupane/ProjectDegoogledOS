package com.android.messaging.p041ui.mediapicker;

import android.hardware.Camera;

/* renamed from: com.android.messaging.ui.mediapicker.p */
class C1344p implements Camera.AutoFocusCallback {
    final /* synthetic */ C1352t this$0;

    C1344p(C1352t tVar) {
        this.this$0 = tVar;
    }

    public void onAutoFocus(boolean z, Camera camera) {
        this.this$0.f2164hI.mo7797l(z, false);
    }
}
