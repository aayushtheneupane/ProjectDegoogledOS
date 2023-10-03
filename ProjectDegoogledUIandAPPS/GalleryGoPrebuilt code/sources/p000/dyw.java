package p000;

import java.util.List;
import p003j$.util.Optional;

/* renamed from: dyw */
/* compiled from: PG */
final /* synthetic */ class dyw implements hpr {

    /* renamed from: a */
    private final dza f7677a;

    /* renamed from: b */
    private final dyt f7678b;

    public dyw(dza dza, dyt dyt) {
        this.f7677a = dza;
        this.f7678b = dyt;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        dza dza = this.f7677a;
        dyt dyt = this.f7678b;
        List list = (List) obj;
        synchronized (dza.f7684a) {
            dyt.f7670b = Optional.m16285of(list);
        }
        return null;
    }
}
