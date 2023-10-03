package p000;

import android.os.IBinder;

/* renamed from: eql */
/* compiled from: PG */
final class eql implements eqm {

    /* renamed from: a */
    private final IBinder f8840a;

    public eql(IBinder iBinder) {
        this.f8840a = iBinder;
    }

    public final IBinder asBinder() {
        return this.f8840a;
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [eqk, android.os.IBinder] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo5161a(p000.eqk r4, p000.epr r5) {
        /*
            r3 = this;
            android.os.Parcel r0 = android.os.Parcel.obtain()
            android.os.Parcel r1 = android.os.Parcel.obtain()
            java.lang.String r2 = "com.google.android.gms.common.internal.IGmsServiceBroker"
            r0.writeInterfaceToken(r2)     // Catch:{ all -> 0x0029 }
            r0.writeStrongBinder(r4)     // Catch:{ all -> 0x0029 }
            r4 = 1
            r0.writeInt(r4)     // Catch:{ all -> 0x0029 }
            r4 = 0
            p000.eps.m8010a(r5, r0, r4)     // Catch:{ all -> 0x0029 }
            android.os.IBinder r5 = r3.f8840a     // Catch:{ all -> 0x0029 }
            r2 = 46
            r5.transact(r2, r0, r1, r4)     // Catch:{ all -> 0x0029 }
            r1.readException()     // Catch:{ all -> 0x0029 }
            r1.recycle()
            r0.recycle()
            return
        L_0x0029:
            r4 = move-exception
            r1.recycle()
            r0.recycle()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.eql.mo5161a(eqk, epr):void");
    }
}
