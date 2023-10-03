package com.android.messaging.p041ui.conversation;

/* renamed from: com.android.messaging.ui.conversation.Aa */
class C1130Aa implements Runnable {
    final /* synthetic */ SimSelectorView this$0;

    C1130Aa(SimSelectorView simSelectorView) {
        this.this$0 = simSelectorView;
    }

    public void run() {
        this.this$0.setAlpha(1.0f);
        SimSelectorView simSelectorView = this.this$0;
        simSelectorView.setVisibility(simSelectorView.f1848fg ? 0 : 8);
    }
}
