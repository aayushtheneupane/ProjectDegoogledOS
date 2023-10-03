package com.android.systemui.privacy;

import com.android.systemui.privacy.PrivacyItemController;
import java.lang.ref.WeakReference;
import java.util.List;

/* compiled from: PrivacyItemController.kt */
final class PrivacyItemController$notifyChanges$1 implements Runnable {
    final /* synthetic */ PrivacyItemController this$0;

    PrivacyItemController$notifyChanges$1(PrivacyItemController privacyItemController) {
        this.this$0 = privacyItemController;
    }

    public final void run() {
        List<PrivacyItem> privacyList$name = this.this$0.getPrivacyList$name();
        for (WeakReference weakReference : this.this$0.callbacks) {
            PrivacyItemController.Callback callback = (PrivacyItemController.Callback) weakReference.get();
            if (callback != null) {
                callback.privacyChanged(privacyList$name);
            }
        }
    }
}
