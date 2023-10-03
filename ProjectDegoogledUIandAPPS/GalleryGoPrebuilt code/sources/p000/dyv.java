package p000;

import java.util.List;
import p003j$.util.Optional;

/* renamed from: dyv */
/* compiled from: PG */
final /* synthetic */ class dyv implements hpr {

    /* renamed from: a */
    private final dza f7675a;

    /* renamed from: b */
    private final dyt f7676b;

    public dyv(dza dza, dyt dyt) {
        this.f7675a = dza;
        this.f7676b = dyt;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        dza dza = this.f7675a;
        dyt dyt = this.f7676b;
        List list = (List) obj;
        synchronized (dza.f7684a) {
            dyt.f7669a = Optional.m16285of(list);
        }
        return null;
    }
}
