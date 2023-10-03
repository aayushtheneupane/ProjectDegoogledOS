package p000;

import android.os.Looper;

/* renamed from: aj */
/* compiled from: PG */
public class C0010aj extends C0009ai {
    /* renamed from: b */
    public final void mo535b(Object obj) {
        Object obj2;
        Object obj3;
        synchronized (this.f520a) {
            obj2 = this.f523e;
            obj3 = C0009ai.f519b;
            this.f523e = obj;
        }
        if (obj2 == obj3) {
            C0029b a = C0029b.m2002a();
            Runnable runnable = this.f525g;
            C0321lr lrVar = a.f1926b;
            C0085d dVar = (C0085d) lrVar;
            if (dVar.f6132c == null) {
                synchronized (dVar.f6130a) {
                    if (((C0085d) lrVar).f6132c == null) {
                        ((C0085d) lrVar).f6132c = C0085d.m5793a(Looper.getMainLooper());
                    }
                }
            }
            dVar.f6132c.post(runnable);
        }
    }

    /* renamed from: a */
    public final void mo512a(Object obj) {
        C0009ai.m522a("setValue");
        this.f524f++;
        this.f522d = obj;
        mo510a((C0008ah) null);
    }
}
