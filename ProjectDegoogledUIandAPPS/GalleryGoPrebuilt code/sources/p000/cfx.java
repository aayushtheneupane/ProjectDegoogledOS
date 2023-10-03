package p000;

import java.util.HashSet;

/* renamed from: cfx */
/* compiled from: PG */
public final class cfx extends iox {

    /* renamed from: b */
    private final ioq f4296b;

    public cfx(iqk iqk, iqk iqk2, ioq ioq) {
        super(iqk2, iph.m14288a(cfx.class), iqk);
        this.f4296b = ipd.m14274a(ioq);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        HashSet hashSet = new HashSet();
        hvs i = ((cfi) obj).f4262a.listIterator();
        while (i.hasNext()) {
            cff cff = (cff) i.next();
            if (cff.mo3099h().isPresent()) {
                hashSet.add((String) cff.mo3099h().get());
            }
        }
        return ife.m12820a((Object) hashSet);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return this.f4296b.mo9044b();
    }
}
