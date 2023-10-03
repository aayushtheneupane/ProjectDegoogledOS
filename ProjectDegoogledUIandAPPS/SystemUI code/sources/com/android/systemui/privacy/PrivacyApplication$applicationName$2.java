package com.android.systemui.privacy;

import android.content.pm.ApplicationInfo;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: PrivacyItem.kt */
final class PrivacyApplication$applicationName$2 extends Lambda implements Function0<String> {
    final /* synthetic */ PrivacyApplication this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PrivacyApplication$applicationName$2(PrivacyApplication privacyApplication) {
        super(0);
        this.this$0 = privacyApplication;
    }

    public final String invoke() {
        ApplicationInfo access$getApplicationInfo$p = this.this$0.getApplicationInfo();
        if (access$getApplicationInfo$p != null) {
            CharSequence applicationLabel = this.this$0.getContext().getPackageManager().getApplicationLabel(access$getApplicationInfo$p);
            if (applicationLabel != null) {
                String str = (String) applicationLabel;
                if (str != null) {
                    return str;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            }
        }
        return this.this$0.getPackageName();
    }
}
