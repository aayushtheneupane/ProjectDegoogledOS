package p000;

import java.util.concurrent.Callable;

/* renamed from: ccp */
/* compiled from: PG */
final /* synthetic */ class ccp implements Callable {

    /* renamed from: a */
    private final ieh f4068a;

    public ccp(ieh ieh) {
        this.f4068a = ieh;
    }

    public final Object call() {
        return Boolean.valueOf(this.f4068a.cancel(true));
    }
}
