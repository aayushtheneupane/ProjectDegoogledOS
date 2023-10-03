package com.android.messaging.p041ui.mediapicker;

/* renamed from: com.android.messaging.ui.mediapicker.na */
class C1341na implements Runnable {
    final /* synthetic */ C1345pa this$0;

    /* renamed from: uI */
    final /* synthetic */ int f2128uI;

    C1341na(C1345pa paVar, int i) {
        this.this$0 = paVar;
        this.f2128uI = i;
    }

    public void run() {
        this.this$0.mListener.mo7456d(this.f2128uI);
    }
}
