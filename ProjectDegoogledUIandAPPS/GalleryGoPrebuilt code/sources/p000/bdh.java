package p000;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/* renamed from: bdh */
/* compiled from: PG */
public final class bdh implements bcw {

    /* renamed from: a */
    public final Set f2088a = Collections.newSetFromMap(new WeakHashMap());

    /* renamed from: d */
    public final void mo1444d() {
        List a = bfp.m2428a((Collection) this.f2088a);
        int size = a.size();
        for (int i = 0; i < size; i++) {
            ((ber) a.get(i)).mo1444d();
        }
    }

    /* renamed from: b */
    public final void mo1442b() {
        List a = bfp.m2428a((Collection) this.f2088a);
        int size = a.size();
        for (int i = 0; i < size; i++) {
            ((ber) a.get(i)).mo1442b();
        }
    }

    /* renamed from: c */
    public final void mo1443c() {
        List a = bfp.m2428a((Collection) this.f2088a);
        int size = a.size();
        for (int i = 0; i < size; i++) {
            ((ber) a.get(i)).mo1443c();
        }
    }
}
