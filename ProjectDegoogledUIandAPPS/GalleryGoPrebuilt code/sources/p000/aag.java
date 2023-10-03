package p000;

import java.util.ArrayDeque;
import java.util.Iterator;

/* renamed from: aag */
/* compiled from: PG */
public final class aag {

    /* renamed from: a */
    public final ArrayDeque f17a;

    /* renamed from: b */
    private final Runnable f18b;

    public aag() {
        this((Runnable) null);
    }

    public aag(Runnable runnable) {
        this.f17a = new ArrayDeque();
        this.f18b = runnable;
    }

    /* renamed from: a */
    public final void mo15a() {
        Iterator descendingIterator = this.f17a.descendingIterator();
        while (descendingIterator.hasNext()) {
            aad aad = (aad) descendingIterator.next();
            if (aad.f8a) {
                C0171gd gdVar = aad.f10c;
                gdVar.mo6442c(true);
                if (!gdVar.f10986e.f8a) {
                    gdVar.f10985d.mo15a();
                    return;
                } else {
                    gdVar.mo6438b();
                    return;
                }
            }
        }
        Runnable runnable = this.f18b;
        if (runnable != null) {
            runnable.run();
        }
    }
}
