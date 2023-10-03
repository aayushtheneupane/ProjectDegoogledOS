package p000;

import java.util.HashSet;
import java.util.Set;
import p003j$.util.Optional;

/* renamed from: cmg */
/* compiled from: PG */
public final class cmg implements ckz {

    /* renamed from: a */
    public final Set f4670a = new HashSet();

    /* renamed from: b */
    public String f4671b = cml.f4678a;

    /* renamed from: c */
    public hsu f4672c = hvb.f13454a;

    /* renamed from: d */
    private Optional f4673d = Optional.empty();

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final String mo3255b() {
        return this.f4672c.containsKey(this.f4671b) ? this.f4671b : cml.f4678a;
    }

    /* renamed from: a */
    public final void mo3253a() {
        String b = mo3255b();
        if (!this.f4673d.isPresent() || !((String) this.f4673d.get()).equals(b)) {
            for (cmf a : this.f4670a) {
                a.mo3251a(b);
            }
            this.f4673d = Optional.m16285of(b);
        }
    }

    /* renamed from: a */
    public final void mo3227a(boolean z) {
        for (cmf a : this.f4670a) {
            a.mo3227a(z);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo3254a(String str) {
        this.f4671b = str;
        mo3253a();
    }
}
