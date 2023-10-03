package com.android.messaging.mmslib.p039a;

import android.util.Log;
import com.android.messaging.mmslib.InvalidHeaderValueException;

/* renamed from: com.android.messaging.mmslib.a.y */
public class C0995y extends C0977g {
    public C0995y() {
        try {
            setMessageType(128);
            mo6673wa(18);
            this.f1404Zl.mo6719b("application/vnd.wap.multipart.related".getBytes(), 132);
            this.f1404Zl.mo6718b(new C0975e(106, "insert-address-token".getBytes()), 137);
            this.f1404Zl.mo6719b(("T" + Long.toHexString(System.currentTimeMillis())).getBytes(), 152);
        } catch (InvalidHeaderValueException e) {
            Log.e("SendReq", "Unexpected InvalidHeaderValueException.", e);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public void mo6752a(C0975e[] eVarArr) {
        this.f1404Zl.mo6717a(eVarArr, 151);
    }

    /* renamed from: l */
    public void mo6753l(byte[] bArr) {
        this.f1404Zl.mo6719b(bArr, 138);
    }

    /* renamed from: t */
    public void mo6754t(long j) {
        this.f1404Zl.mo6715a(j, 136);
    }

    /* renamed from: u */
    public void mo6755u(long j) {
        this.f1404Zl.mo6715a(j, 142);
    }

    /* renamed from: xa */
    public void mo6756xa(int i) {
        this.f1404Zl.mo6720e(i, 134);
    }

    /* renamed from: ya */
    public void mo6757ya(int i) {
        this.f1404Zl.mo6720e(i, 144);
    }

    C0995y(C0987q qVar, C0980j jVar) {
        super(qVar, jVar);
    }
}
