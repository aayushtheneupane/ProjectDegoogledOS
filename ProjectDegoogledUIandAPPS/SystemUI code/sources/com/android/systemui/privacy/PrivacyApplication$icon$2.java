package com.android.systemui.privacy;

import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.util.IconDrawableFactory;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: PrivacyItem.kt */
final class PrivacyApplication$icon$2 extends Lambda implements Function0<Drawable> {
    final /* synthetic */ PrivacyApplication this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PrivacyApplication$icon$2(PrivacyApplication privacyApplication) {
        super(0);
        this.this$0 = privacyApplication;
    }

    public final Drawable invoke() {
        Drawable drawable;
        ApplicationInfo access$getApplicationInfo$p = this.this$0.getApplicationInfo();
        if (access$getApplicationInfo$p != null) {
            try {
                drawable = IconDrawableFactory.newInstance(this.this$0.getContext(), true).getBadgedIcon(access$getApplicationInfo$p, UserHandle.getUserId(this.this$0.getUid()));
            } catch (Exception unused) {
                drawable = null;
            }
            if (drawable != null) {
                return drawable;
            }
        }
        return this.this$0.getContext().getDrawable(17301651);
    }
}
