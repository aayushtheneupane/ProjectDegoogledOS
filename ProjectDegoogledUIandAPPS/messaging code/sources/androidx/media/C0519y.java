package androidx.media;

import android.os.Bundle;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.media.y */
public class C0519y {

    /* renamed from: Aq */
    private boolean f498Aq;

    /* renamed from: Bq */
    private boolean f499Bq;

    /* renamed from: Cq */
    private boolean f500Cq;
    private int mFlags;

    /* renamed from: zq */
    private final Object f501zq;

    C0519y(Object obj) {
        this.f501zq = obj;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo4588d(Bundle bundle) {
        StringBuilder Pa = C0632a.m1011Pa("It is not supported to send an error for ");
        Pa.append(this.f501zq);
        throw new UnsupportedOperationException(Pa.toString());
    }

    /* renamed from: e */
    public void mo4605e(Bundle bundle) {
        if (this.f499Bq || this.f500Cq) {
            StringBuilder Pa = C0632a.m1011Pa("sendError() called when either sendResult() or sendError() had already been called for: ");
            Pa.append(this.f501zq);
            throw new IllegalStateException(Pa.toString());
        }
        this.f500Cq = true;
        mo4588d(bundle);
    }

    /* access modifiers changed from: package-private */
    public int getFlags() {
        return this.mFlags;
    }

    /* access modifiers changed from: package-private */
    public boolean isDone() {
        return this.f498Aq || this.f499Bq || this.f500Cq;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: o */
    public void mo4587o(Object obj) {
        throw null;
    }

    public void sendResult(Object obj) {
        if (this.f499Bq || this.f500Cq) {
            StringBuilder Pa = C0632a.m1011Pa("sendResult() called when either sendResult() or sendError() had already been called for: ");
            Pa.append(this.f501zq);
            throw new IllegalStateException(Pa.toString());
        }
        this.f499Bq = true;
        mo4587o(obj);
    }

    /* access modifiers changed from: package-private */
    public void setFlags(int i) {
        this.mFlags = i;
    }
}
