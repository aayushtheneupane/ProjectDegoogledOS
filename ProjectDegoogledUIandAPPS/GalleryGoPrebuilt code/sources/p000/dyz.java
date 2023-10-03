package p000;

import java.util.List;
import p003j$.util.Optional;

/* renamed from: dyz */
/* compiled from: PG */
final /* synthetic */ class dyz implements hpr {

    /* renamed from: a */
    private final dza f7682a;

    /* renamed from: b */
    private final dyt f7683b;

    public dyz(dza dza, dyt dyt) {
        this.f7682a = dza;
        this.f7683b = dyt;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        dza dza = this.f7682a;
        dyt dyt = this.f7683b;
        List list = (List) obj;
        synchronized (dza.f7684a) {
            dyt.f7673e = Optional.m16285of(list);
        }
        return null;
    }
}
