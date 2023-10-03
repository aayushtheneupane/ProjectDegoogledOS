package com.android.messaging.p041ui.mediapicker;

import android.media.MediaRecorder;

/* renamed from: com.android.messaging.ui.mediapicker.o */
class C1342o implements MediaRecorder.OnInfoListener {
    final /* synthetic */ C1352t this$0;

    C1342o(C1352t tVar) {
        this.this$0 = tVar;
    }

    public void onInfo(MediaRecorder mediaRecorder, int i, int i2) {
        if (i == 800 || i == 801) {
            this.this$0.mo7951tj();
        }
    }
}
