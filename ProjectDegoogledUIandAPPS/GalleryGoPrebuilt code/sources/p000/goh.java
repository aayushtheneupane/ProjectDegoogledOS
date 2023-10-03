package p000;

import java.util.concurrent.ThreadFactory;

/* renamed from: goh */
/* compiled from: PG */
final /* synthetic */ class goh implements ThreadFactory {

    /* renamed from: a */
    private final hpy f11733a;

    /* renamed from: b */
    private final int f11734b;

    public goh(hpy hpy, int i) {
        this.f11733a = hpy;
        this.f11734b = i;
    }

    public final Thread newThread(Runnable runnable) {
        return new Thread(new goi(this.f11733a, this.f11734b, runnable));
    }
}
