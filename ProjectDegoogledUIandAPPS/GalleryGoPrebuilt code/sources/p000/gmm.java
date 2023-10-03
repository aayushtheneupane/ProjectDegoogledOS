package p000;

import android.util.Pair;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: gmm */
/* compiled from: PG */
public final /* synthetic */ class gmm implements hpr {

    /* renamed from: a */
    private final hpr f11627a;

    /* renamed from: b */
    private final AtomicReference f11628b;

    public gmm(hpr hpr, AtomicReference atomicReference) {
        this.f11627a = hpr;
        this.f11628b = atomicReference;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        hpr hpr = this.f11627a;
        AtomicReference atomicReference = this.f11628b;
        Pair pair = (Pair) hpr.mo1484a((gng) obj);
        atomicReference.set(pair.first);
        return (gng) pair.second;
    }
}
