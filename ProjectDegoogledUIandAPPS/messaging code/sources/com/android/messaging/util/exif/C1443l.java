package com.android.messaging.util.exif;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.android.messaging.util.exif.l */
class C1443l {

    /* renamed from: eM */
    private static final int[] f2302eM = {0, 1, 2, 3, 4};

    /* renamed from: bM */
    private final int f2303bM;

    /* renamed from: cM */
    private final Map f2304cM = new HashMap();

    /* renamed from: dM */
    private int f2305dM = 0;

    C1443l(int i) {
        this.f2303bM = i;
    }

    /* renamed from: Jk */
    protected static int[] m3709Jk() {
        return f2302eM;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Kk */
    public int mo8147Kk() {
        return this.f2305dM;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Lk */
    public int mo8148Lk() {
        return this.f2304cM.size();
    }

    /* access modifiers changed from: protected */
    /* renamed from: ab */
    public void mo8149ab(int i) {
        this.f2305dM = i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public C1442k mo8150b(C1442k kVar) {
        kVar.mo8129_a(this.f2303bM);
        return (C1442k) this.f2304cM.put(Short.valueOf(kVar.mo8123Gk()), kVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public C1442k mo8151c(short s) {
        return (C1442k) this.f2304cM.get(Short.valueOf(s));
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo8152d(short s) {
        this.f2304cM.remove(Short.valueOf(s));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof C1443l)) {
            C1443l lVar = (C1443l) obj;
            if (lVar.getId() == this.f2303bM && lVar.mo8148Lk() == mo8148Lk()) {
                for (C1442k kVar : lVar.mo8155rk()) {
                    if (!C1435d.m3658a(kVar.mo8123Gk()) && !kVar.equals((C1442k) this.f2304cM.get(Short.valueOf(kVar.mo8123Gk())))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public int getId() {
        return this.f2303bM;
    }

    /* access modifiers changed from: protected */
    /* renamed from: rk */
    public C1442k[] mo8155rk() {
        return (C1442k[]) this.f2304cM.values().toArray(new C1442k[this.f2304cM.size()]);
    }
}
