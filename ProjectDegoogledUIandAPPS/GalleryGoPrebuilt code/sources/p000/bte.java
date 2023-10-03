package p000;

import p003j$.util.Optional;

/* renamed from: bte */
/* compiled from: PG */
final /* synthetic */ class bte implements hpr {

    /* renamed from: a */
    private final btf f3524a;

    /* renamed from: b */
    private final bta f3525b;

    public bte(btf btf, bta bta) {
        this.f3524a = btf;
        this.f3525b = bta;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        btf btf = this.f3524a;
        bta bta = this.f3525b;
        Optional optional = (Optional) obj;
        synchronized (btf.f3526a) {
            bta.f3520b = Optional.m16285of(optional);
        }
        return null;
    }
}
