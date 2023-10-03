package com.android.systemui.privacy;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: PrivacyItem.kt */
final class PrivacyApplication$applicationInfo$2 extends Lambda implements Function0<ApplicationInfo> {
    final /* synthetic */ PrivacyApplication this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PrivacyApplication$applicationInfo$2(PrivacyApplication privacyApplication) {
        super(0);
        this.this$0 = privacyApplication;
    }

    public final ApplicationInfo invoke() {
        try {
            return this.this$0.getContext().createPackageContextAsUser(this.this$0.getPackageName(), 0, UserHandle.getUserHandleForUid(this.this$0.getUid())).getPackageManager().getApplicationInfo(this.this$0.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }
}
