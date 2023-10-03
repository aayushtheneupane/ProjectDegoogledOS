package p000;

import android.content.Context;
import android.util.SparseIntArray;

/* renamed from: eqe */
/* compiled from: PG */
public final class eqe {

    /* renamed from: a */
    private final SparseIntArray f8836a;

    /* renamed from: b */
    private ejx f8837b;

    public eqe() {
        this(ejw.f8454a);
    }

    public eqe(ejx ejx) {
        this.f8836a = new SparseIntArray();
        abj.m85a((Object) ejx);
        this.f8837b = ejx;
    }

    /* renamed from: a */
    public final void mo5159a() {
        this.f8836a.clear();
    }

    /* renamed from: a */
    public final int mo5158a(Context context, ekm ekm) {
        abj.m85a((Object) context);
        abj.m85a((Object) ekm);
        ekm.mo4939l();
        int c = ekm.mo4885c();
        int i = this.f8836a.get(c, -1);
        if (i == -1) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.f8836a.size()) {
                    break;
                }
                int keyAt = this.f8836a.keyAt(i2);
                if (keyAt > c && this.f8836a.get(keyAt) == 0) {
                    i = 0;
                    break;
                }
                i2++;
            }
            if (i == -1) {
                i = this.f8837b.mo4919b(context, c);
            }
            this.f8836a.put(c, i);
        }
        return i;
    }
}
