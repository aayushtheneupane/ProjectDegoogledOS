package com.android.systemui.privacy;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: PrivacyItem.kt */
public final class PrivacyItem {
    private final PrivacyApplication application;
    private final PrivacyType privacyType;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PrivacyItem)) {
            return false;
        }
        PrivacyItem privacyItem = (PrivacyItem) obj;
        return Intrinsics.areEqual((Object) this.privacyType, (Object) privacyItem.privacyType) && Intrinsics.areEqual((Object) this.application, (Object) privacyItem.application);
    }

    public int hashCode() {
        PrivacyType privacyType2 = this.privacyType;
        int i = 0;
        int hashCode = (privacyType2 != null ? privacyType2.hashCode() : 0) * 31;
        PrivacyApplication privacyApplication = this.application;
        if (privacyApplication != null) {
            i = privacyApplication.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "PrivacyItem(privacyType=" + this.privacyType + ", application=" + this.application + ")";
    }

    public PrivacyItem(PrivacyType privacyType2, PrivacyApplication privacyApplication) {
        Intrinsics.checkParameterIsNotNull(privacyType2, "privacyType");
        Intrinsics.checkParameterIsNotNull(privacyApplication, "application");
        this.privacyType = privacyType2;
        this.application = privacyApplication;
    }

    public final PrivacyType getPrivacyType() {
        return this.privacyType;
    }

    public final PrivacyApplication getApplication() {
        return this.application;
    }
}
