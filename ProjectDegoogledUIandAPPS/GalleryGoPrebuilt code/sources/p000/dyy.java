package p000;

import p003j$.util.Optional;

/* renamed from: dyy */
/* compiled from: PG */
final /* synthetic */ class dyy implements hpr {

    /* renamed from: a */
    private final dza f7680a;

    /* renamed from: b */
    private final dyt f7681b;

    public dyy(dza dza, dyt dyt) {
        this.f7680a = dza;
        this.f7681b = dyt;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        dza dza = this.f7680a;
        dyt dyt = this.f7681b;
        Optional optional = (Optional) obj;
        if (optional == null) {
            return null;
        }
        synchronized (dza.f7684a) {
            dyt.f7671c = optional;
        }
        return null;
    }
}
