package com.android.messaging.datamodel.p038b;

import android.content.Intent;
import com.android.messaging.datamodel.data.C0909V;

/* renamed from: com.android.messaging.datamodel.b.U */
public class C0857U {
    /* access modifiers changed from: private */

    /* renamed from: Av */
    public final String f1108Av;
    /* access modifiers changed from: private */

    /* renamed from: YB */
    public final String f1109YB;
    /* access modifiers changed from: private */

    /* renamed from: _C */
    public final Intent f1110_C;

    public C0857U(String str, String str2, Intent intent) {
        this.f1109YB = str;
        this.f1108Av = str2;
        this.f1110_C = intent;
    }

    /* renamed from: Xh */
    public C0909V mo6134Xh() {
        return new C0856T(this);
    }
}
