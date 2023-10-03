package com.android.messaging.datamodel.p038b;

import android.graphics.BitmapFactory;
import com.android.messaging.util.C1424b;
import java.util.List;

/* renamed from: com.android.messaging.datamodel.b.n */
class C0874n implements C0883w {
    final /* synthetic */ C0875o this$0;

    public C0874n(C0875o oVar) {
        this.this$0 = oVar;
        oVar.mo6100Oh();
    }

    /* renamed from: a */
    public C0846I mo6120a(List list) {
        C1424b.m3584Gj();
        this.this$0.acquireLock();
        try {
            return new C0873m(this.this$0.getKey(), BitmapFactory.decodeByteArray(this.this$0.f1121WC, 0, this.this$0.f1121WC.length), this.this$0.mOrientation);
        } finally {
            this.this$0.releaseLock();
            this.this$0.release();
        }
    }

    /* renamed from: fa */
    public C0882v mo6121fa() {
        return null;
    }

    public C0884x getDescriptor() {
        return null;
    }

    public String getKey() {
        return this.this$0.getKey();
    }

    public int getRequestType() {
        return 2;
    }
}
