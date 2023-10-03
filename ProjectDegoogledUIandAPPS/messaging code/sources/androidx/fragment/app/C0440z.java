package androidx.fragment.app;

import android.os.Bundle;

/* renamed from: androidx.fragment.app.z */
class C0440z extends C0428n {
    final /* synthetic */ C0389H this$0;

    C0440z(C0389H h) {
        this.this$0 = h;
    }

    /* renamed from: a */
    public C0424j mo4424a(ClassLoader classLoader, String str) {
        C0429o oVar = this.this$0.mHost;
        return oVar.instantiate(oVar.getContext(), str, (Bundle) null);
    }
}
