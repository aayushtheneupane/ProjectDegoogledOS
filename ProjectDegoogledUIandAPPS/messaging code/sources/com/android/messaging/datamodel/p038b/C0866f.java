package com.android.messaging.datamodel.p038b;

import android.util.SparseArray;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.C0760A;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.datamodel.b.f */
public class C0866f implements C0760A {

    /* renamed from: sC */
    protected final SparseArray f1117sC = new SparseArray();

    public C0866f() {
        C0967f.get().mo6651Nd().mo5866a(this);
    }

    /* access modifiers changed from: private */
    /* renamed from: Eb */
    public synchronized C0882v mo6144ua(int i) {
        C0882v vVar;
        vVar = (C0882v) this.f1117sC.get(i);
        if (vVar == null && (vVar = mo6143ta(i)) != null) {
            this.f1117sC.put(i, vVar);
        }
        return vVar;
    }

    public static C0866f get() {
        return C0967f.get().mo6648Kd();
    }

    /* renamed from: Fh */
    public void mo6142Fh() {
        int size = this.f1117sC.size();
        for (int i = 0; i < size; i++) {
            ((C0882v) this.f1117sC.valueAt(i)).evictAll();
        }
        this.f1117sC.clear();
    }

    /* access modifiers changed from: protected */
    /* renamed from: ta */
    public C0882v mo6143ta(int i) {
        if (i == 1) {
            return new C0845H(10240, i, "DefaultImageCache");
        }
        if (i == 2) {
            return new C0845H(5120, i, "AvatarImageCache");
        }
        if (i == 3) {
            return new C0882v(5, i, "VCardCache");
        }
        C1424b.fail("BugleMediaCacheManager: unsupported cache id " + i);
        return null;
    }
}
