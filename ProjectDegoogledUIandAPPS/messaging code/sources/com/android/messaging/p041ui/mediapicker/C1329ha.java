package com.android.messaging.p041ui.mediapicker;

/* renamed from: com.android.messaging.ui.mediapicker.ha */
class C1329ha implements Runnable {
    final /* synthetic */ C1345pa this$0;

    C1329ha(C1345pa paVar) {
        this.this$0 = paVar;
    }

    public void run() {
        this.this$0.mListener.onDismissed();
    }
}
