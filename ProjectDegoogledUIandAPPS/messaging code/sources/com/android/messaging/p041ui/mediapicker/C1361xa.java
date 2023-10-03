package com.android.messaging.p041ui.mediapicker;

import android.view.SurfaceHolder;

/* renamed from: com.android.messaging.ui.mediapicker.xa */
class C1361xa implements SurfaceHolder.Callback {
    final /* synthetic */ C1363ya this$0;

    C1361xa(C1363ya yaVar) {
        this.this$0 = yaVar;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        C1352t.get().mo7935a(this.this$0.f2181We);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        C1352t.get().mo7935a(this.this$0.f2181We);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        C1352t.get().mo7935a((C1278F) null);
    }
}
