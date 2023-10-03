package p000;

import android.os.Bundle;

/* renamed from: eox */
/* compiled from: PG */
abstract class eox extends epb {

    /* renamed from: c */
    private final int f8731c;

    /* renamed from: d */
    private final Bundle f8732d;

    /* renamed from: e */
    private final /* synthetic */ epi f8733e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected eox(epi epi, int i, Bundle bundle) {
        super(epi, true);
        this.f8733e = epi;
        this.f8731c = i;
        this.f8732d = bundle;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo5098a(ejq ejq);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract boolean mo5099a();

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo5100b() {
    }

    /* JADX WARNING: type inference failed for: r0v9, types: [android.os.Parcelable] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ void mo5101c() {
        /*
            r5 = this;
            int r0 = r5.f8731c
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x005a
            r3 = 10
            if (r0 == r3) goto L_0x002a
            epi r0 = r5.f8733e
            r0.m7969a((int) r2, (android.os.IInterface) null)
            android.os.Bundle r0 = r5.f8732d
            if (r0 == 0) goto L_0x001d
            java.lang.String r1 = "pendingIntent"
            android.os.Parcelable r0 = r0.getParcelable(r1)
            r1 = r0
            android.app.PendingIntent r1 = (android.app.PendingIntent) r1
            goto L_0x001f
        L_0x001d:
        L_0x001f:
            ejq r0 = new ejq
            int r2 = r5.f8731c
            r0.<init>(r2, r1)
            r5.mo5098a(r0)
            return
        L_0x002a:
            epi r0 = r5.f8733e
            r0.m7969a((int) r2, (android.os.IInterface) null)
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = 3
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r3 = 0
            java.lang.Class r4 = r5.getClass()
            java.lang.String r4 = r4.getSimpleName()
            r1[r3] = r4
            epi r3 = r5.f8733e
            java.lang.String r3 = r3.mo4883a()
            r1[r2] = r3
            r2 = 2
            epi r3 = r5.f8733e
            java.lang.String r3 = r3.mo4884b()
            r1[r2] = r3
            java.lang.String r2 = "A fatal developer error has occurred. Class name: %s. Start service action: %s. Service Descriptor: %s. "
            java.lang.String r1 = java.lang.String.format(r2, r1)
            r0.<init>(r1)
            throw r0
        L_0x005a:
            boolean r0 = r5.mo5099a()
            if (r0 != 0) goto L_0x0070
            epi r0 = r5.f8733e
            r0.m7969a((int) r2, (android.os.IInterface) null)
            ejq r0 = new ejq
            r2 = 8
            r0.<init>(r2, r1)
            r5.mo5098a(r0)
            return
        L_0x0070:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.eox.mo5101c():void");
    }
}
