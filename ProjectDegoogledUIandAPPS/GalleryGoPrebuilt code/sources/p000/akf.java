package p000;

import androidx.work.impl.WorkDatabase;

/* renamed from: akf */
/* compiled from: PG */
public final class akf implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ WorkDatabase f672a;

    /* renamed from: b */
    private final /* synthetic */ String f673b;

    /* renamed from: c */
    private final /* synthetic */ akh f674c;

    public akf(akh akh, WorkDatabase workDatabase, String str) {
        this.f674c = akh;
        this.f672a = workDatabase;
        this.f673b = str;
    }

    public final void run() {
        alg b = this.f672a.mo1226j().mo608b(this.f673b);
        if (b != null && b.mo598d()) {
            synchronized (this.f674c.f678d) {
                this.f674c.f679e.put(this.f673b, b);
                this.f674c.f680f.add(b);
            }
            akh akh = this.f674c;
            akh.f681g.mo552a((Iterable) akh.f680f);
        }
    }
}
