package com.android.systemui.privacy;

/* compiled from: PrivacyItemController.kt */
final class PrivacyItemController$updateListAndNotifyChanges$1 implements Runnable {
    final /* synthetic */ PrivacyItemController this$0;

    PrivacyItemController$updateListAndNotifyChanges$1(PrivacyItemController privacyItemController) {
        this.this$0 = privacyItemController;
    }

    public final void run() {
        this.this$0.updatePrivacyList();
        this.this$0.uiHandler.post(this.this$0.notifyChanges);
    }
}
