package com.android.messaging.p041ui.mediapicker;

import android.media.MediaRecorder;

/* renamed from: com.android.messaging.ui.mediapicker.n */
class C1340n implements MediaRecorder.OnErrorListener {
    final /* synthetic */ C1352t this$0;

    C1340n(C1352t tVar) {
        this.this$0 = tVar;
    }

    public void onError(MediaRecorder mediaRecorder, int i, int i2) {
        if (this.this$0.mListener != null) {
            this.this$0.mListener.mo7677a(5, (Exception) null);
        }
        this.this$0.m3426Co();
    }
}
