package com.android.systemui.privacy;

import android.os.UserHandle;
import com.android.systemui.appops.AppOpsController;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PrivacyItemController.kt */
public final class PrivacyItemController$cb$1 implements AppOpsController.Callback {
    final /* synthetic */ PrivacyItemController this$0;

    PrivacyItemController$cb$1(PrivacyItemController privacyItemController) {
        this.this$0 = privacyItemController;
    }

    public void onActiveStateChanged(int i, int i2, String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, "packageName");
        if (this.this$0.currentUserIds.contains(Integer.valueOf(UserHandle.getUserId(i2)))) {
            this.this$0.update(false);
        }
    }
}
