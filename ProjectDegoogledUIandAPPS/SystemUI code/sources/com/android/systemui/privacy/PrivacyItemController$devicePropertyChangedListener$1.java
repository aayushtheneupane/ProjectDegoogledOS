package com.android.systemui.privacy;

import android.provider.DeviceConfig;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PrivacyItemController.kt */
public final class PrivacyItemController$devicePropertyChangedListener$1 implements DeviceConfig.OnPropertyChangedListener {
    final /* synthetic */ PrivacyItemController this$0;

    PrivacyItemController$devicePropertyChangedListener$1(PrivacyItemController privacyItemController) {
        this.this$0 = privacyItemController;
    }

    public void onPropertyChanged(String str, String str2, String str3) {
        Intrinsics.checkParameterIsNotNull(str, "namespace");
        Intrinsics.checkParameterIsNotNull(str2, "name");
        if ("privacy".equals(str) && "permissions_hub_enabled".equals(str2)) {
            this.this$0.indicatorsAvailable = Boolean.parseBoolean(str3);
            this.this$0.messageHandler.removeMessages(2);
            this.this$0.messageHandler.sendEmptyMessage(2);
        }
    }
}
