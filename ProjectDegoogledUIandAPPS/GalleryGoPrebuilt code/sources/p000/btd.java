package p000;

import java.util.List;
import p003j$.util.Optional;

/* renamed from: btd */
/* compiled from: PG */
final /* synthetic */ class btd implements hpr {

    /* renamed from: a */
    private final btf f3522a;

    /* renamed from: b */
    private final bta f3523b;

    public btd(btf btf, bta bta) {
        this.f3522a = btf;
        this.f3523b = bta;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        btf btf = this.f3522a;
        bta bta = this.f3523b;
        List list = (List) obj;
        synchronized (btf.f3526a) {
            bta.f3519a = Optional.m16285of(list);
        }
        return null;
    }
}
