package p000;

import java.util.concurrent.Callable;

/* renamed from: cty */
/* compiled from: PG */
final /* synthetic */ class cty implements Callable {

    /* renamed from: a */
    private final boolean f5647a;

    public cty(boolean z) {
        this.f5647a = z;
    }

    public final Object call() {
        return Boolean.valueOf(this.f5647a);
    }
}
