package androidx.media;

import android.os.Bundle;

/* renamed from: androidx.media.k */
public final class C0504k {
    /* access modifiers changed from: private */
    public final Bundle mExtras;
    /* access modifiers changed from: private */

    /* renamed from: wq */
    public final String f489wq;

    public C0504k(String str, Bundle bundle) {
        if (str != null) {
            this.f489wq = str;
            this.mExtras = bundle;
            return;
        }
        throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public String getRootId() {
        return this.f489wq;
    }
}
