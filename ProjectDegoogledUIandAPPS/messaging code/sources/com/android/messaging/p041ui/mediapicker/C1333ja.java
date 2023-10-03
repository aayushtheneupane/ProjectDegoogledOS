package com.android.messaging.p041ui.mediapicker;

import java.util.Collection;

/* renamed from: com.android.messaging.ui.mediapicker.ja */
class C1333ja implements Runnable {

    /* renamed from: rI */
    final /* synthetic */ Collection f2122rI;

    /* renamed from: sI */
    final /* synthetic */ boolean f2123sI;
    final /* synthetic */ C1345pa this$0;

    C1333ja(C1345pa paVar, Collection collection, boolean z) {
        this.this$0 = paVar;
        this.f2122rI = collection;
        this.f2123sI = z;
    }

    public void run() {
        this.this$0.mListener.mo7453a(this.f2122rI, this.f2123sI);
    }
}
