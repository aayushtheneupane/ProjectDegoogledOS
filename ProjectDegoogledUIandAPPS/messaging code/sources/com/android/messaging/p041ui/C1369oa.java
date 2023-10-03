package com.android.messaging.p041ui;

import com.android.messaging.C0967f;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.oa */
public class C1369oa {

    /* renamed from: hG */
    private final Runnable f2182hG;

    /* renamed from: iG */
    private final String f2183iG;

    private C1369oa(Runnable runnable, String str) {
        this.f2182hG = runnable;
        this.f2183iG = str;
    }

    /* renamed from: a */
    public static C1369oa m3482a(Runnable runnable, String str) {
        return new C1369oa(runnable, str);
    }

    /* renamed from: g */
    public static C1369oa m3483g(Runnable runnable) {
        return new C1369oa(runnable, C0967f.get().getApplicationContext().getString(R.string.snack_bar_retry));
    }

    /* renamed from: h */
    public static C1369oa m3484h(Runnable runnable) {
        return new C1369oa(runnable, C0967f.get().getApplicationContext().getString(R.string.snack_bar_undo));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Xi */
    public String mo7984Xi() {
        return this.f2183iG;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Yi */
    public Runnable mo7985Yi() {
        return this.f2182hG;
    }
}
