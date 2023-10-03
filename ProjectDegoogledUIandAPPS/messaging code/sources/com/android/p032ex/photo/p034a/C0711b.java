package com.android.p032ex.photo.p034a;

import androidx.fragment.app.C0424j;
import p000a.p005b.C0020g;

/* renamed from: com.android.ex.photo.a.b */
class C0711b extends C0020g {
    final /* synthetic */ C0710a this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0711b(C0710a aVar, int i) {
        super(i);
        this.this$0 = aVar;
    }

    /* access modifiers changed from: protected */
    public void entryRemoved(boolean z, Object obj, Object obj2, Object obj3) {
        String str = (String) obj;
        C0424j jVar = (C0424j) obj2;
        C0424j jVar2 = (C0424j) obj3;
        if (z || !(jVar2 == null || jVar == jVar2)) {
            this.this$0.f834Lu.mo4184q(jVar);
        }
    }
}
