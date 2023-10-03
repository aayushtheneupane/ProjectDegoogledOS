package com.android.messaging.p041ui.mediapicker;

/* renamed from: com.android.messaging.ui.mediapicker.ia */
class C1331ia implements Runnable {

    /* renamed from: qI */
    final /* synthetic */ boolean f2121qI;
    final /* synthetic */ C1345pa this$0;

    C1331ia(C1345pa paVar, boolean z) {
        this.this$0 = paVar;
        this.f2121qI = z;
    }

    public void run() {
        this.this$0.mListener.mo7454b(this.f2121qI);
    }
}
