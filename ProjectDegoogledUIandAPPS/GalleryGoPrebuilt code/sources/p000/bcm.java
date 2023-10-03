package p000;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/* renamed from: bcm */
/* compiled from: PG */
final class bcm implements bcv {

    /* renamed from: a */
    private final Set f2057a = Collections.newSetFromMap(new WeakHashMap());

    /* renamed from: b */
    private boolean f2058b;

    /* renamed from: c */
    private boolean f2059c;

    /* renamed from: a */
    public final void mo1812a(bcw bcw) {
        this.f2057a.add(bcw);
        if (this.f2059c) {
            bcw.mo1444d();
        } else if (!this.f2058b) {
            bcw.mo1443c();
        } else {
            bcw.mo1442b();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final void mo1815c() {
        this.f2059c = true;
        List a = bfp.m2428a((Collection) this.f2057a);
        int size = a.size();
        for (int i = 0; i < size; i++) {
            ((bcw) a.get(i)).mo1444d();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo1811a() {
        this.f2058b = true;
        List a = bfp.m2428a((Collection) this.f2057a);
        int size = a.size();
        for (int i = 0; i < size; i++) {
            ((bcw) a.get(i)).mo1442b();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo1813b() {
        this.f2058b = false;
        List a = bfp.m2428a((Collection) this.f2057a);
        int size = a.size();
        for (int i = 0; i < size; i++) {
            ((bcw) a.get(i)).mo1443c();
        }
    }

    /* renamed from: b */
    public final void mo1814b(bcw bcw) {
        this.f2057a.remove(bcw);
    }
}
