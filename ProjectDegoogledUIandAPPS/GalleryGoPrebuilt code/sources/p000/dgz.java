package p000;

import p003j$.util.Optional;

/* renamed from: dgz */
/* compiled from: PG */
public final /* synthetic */ class dgz implements hpr {

    /* renamed from: a */
    private final String f6528a;

    public dgz(String str) {
        this.f6528a = str;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        String str = this.f6528a;
        if (((Integer) obj).intValue() < 3) {
            return Optional.empty();
        }
        return Optional.m16285of(str);
    }
}
