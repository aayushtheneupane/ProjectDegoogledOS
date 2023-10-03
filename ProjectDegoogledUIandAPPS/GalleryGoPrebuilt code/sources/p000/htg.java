package p000;

import java.util.Iterator;

/* renamed from: htg */
/* compiled from: PG */
final class htg extends hvr {

    /* renamed from: a */
    private int f13374a;

    /* renamed from: b */
    private Object f13375b;

    /* renamed from: c */
    private final /* synthetic */ Iterator f13376c;

    public htg(Iterator it) {
        this.f13376c = it;
    }

    public final boolean hasNext() {
        return this.f13374a > 0 || this.f13376c.hasNext();
    }

    public final Object next() {
        int i = this.f13374a;
        if (i <= 0) {
            hun hun = (hun) this.f13376c.next();
            this.f13375b = hun.mo8079a();
            i = hun.mo8080b();
        }
        this.f13374a = i - 1;
        return this.f13375b;
    }
}
