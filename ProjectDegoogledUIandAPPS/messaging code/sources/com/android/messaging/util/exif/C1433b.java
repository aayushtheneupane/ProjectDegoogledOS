package com.android.messaging.util.exif;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.android.messaging.util.exif.b */
class C1433b {

    /* renamed from: hL */
    private final C1443l[] f2247hL = new C1443l[5];

    /* renamed from: iL */
    private byte[] f2248iL;

    /* renamed from: jL */
    private final ArrayList f2249jL = new ArrayList();

    /* renamed from: kL */
    private final ByteOrder f2250kL;

    C1433b(ByteOrder byteOrder) {
        this.f2250kL = byteOrder;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Qa */
    public C1443l mo8078Qa(int i) {
        if (C1442k.m3692Za(i)) {
            return this.f2247hL[i];
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ra */
    public byte[] mo8079Ra(int i) {
        return (byte[]) this.f2249jL.get(i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8082a(C1443l lVar) {
        this.f2247hL[lVar.getId()] = lVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo8083b(int i, byte[] bArr) {
        if (i < this.f2249jL.size()) {
            this.f2249jL.set(i, bArr);
            return;
        }
        for (int size = this.f2249jL.size(); size < i; size++) {
            this.f2249jL.add((Object) null);
        }
        this.f2249jL.add(bArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof C1433b)) {
            C1433b bVar = (C1433b) obj;
            if (bVar.f2250kL == this.f2250kL && bVar.f2249jL.size() == this.f2249jL.size() && Arrays.equals(bVar.f2248iL, this.f2248iL)) {
                for (int i = 0; i < this.f2249jL.size(); i++) {
                    if (!Arrays.equals((byte[]) bVar.f2249jL.get(i), (byte[]) this.f2249jL.get(i))) {
                        return false;
                    }
                }
                for (int i2 = 0; i2 < 5; i2++) {
                    C1443l Qa = bVar.mo8078Qa(i2);
                    C1443l Qa2 = mo8078Qa(i2);
                    if (Qa != Qa2 && Qa != null && !Qa.equals(Qa2)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: p */
    public void mo8086p(byte[] bArr) {
        this.f2248iL = bArr;
    }

    /* access modifiers changed from: protected */
    /* renamed from: qk */
    public void mo8087qk() {
        this.f2248iL = null;
        this.f2249jL.clear();
    }

    /* access modifiers changed from: protected */
    /* renamed from: rk */
    public List mo8088rk() {
        C1442k[] rk;
        ArrayList arrayList = new ArrayList();
        for (C1443l lVar : this.f2247hL) {
            if (!(lVar == null || (rk = lVar.mo8155rk()) == null)) {
                for (C1442k add : rk) {
                    arrayList.add(add);
                }
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    /* renamed from: sk */
    public ByteOrder mo8089sk() {
        return this.f2250kL;
    }

    /* access modifiers changed from: protected */
    /* renamed from: tk */
    public byte[] mo8090tk() {
        return this.f2248iL;
    }

    /* access modifiers changed from: protected */
    /* renamed from: uk */
    public int mo8091uk() {
        return this.f2249jL.size();
    }

    /* access modifiers changed from: protected */
    /* renamed from: vk */
    public boolean mo8092vk() {
        return this.f2248iL != null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: wk */
    public boolean mo8093wk() {
        return this.f2249jL.size() != 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C1442k mo8081a(short s, int i) {
        C1443l lVar = this.f2247hL[i];
        if (lVar == null) {
            return null;
        }
        return lVar.mo8151c(s);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C1442k mo8080a(C1442k kVar) {
        if (kVar == null) {
            return null;
        }
        int Ek = kVar.mo8121Ek();
        if (!C1442k.m3692Za(Ek)) {
            return null;
        }
        C1443l lVar = this.f2247hL[Ek];
        if (lVar == null) {
            lVar = new C1443l(Ek);
            this.f2247hL[Ek] = lVar;
        }
        return lVar.mo8150b(kVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo8084b(short s, int i) {
        C1443l lVar = this.f2247hL[i];
        if (lVar != null) {
            lVar.mo8152d(s);
        }
    }
}
