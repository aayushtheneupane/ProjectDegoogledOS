package p000;

import java.util.concurrent.Executor;

/* renamed from: b */
/* compiled from: PG */
public final class C0029b extends C0321lr {

    /* renamed from: a */
    public static final Executor f1924a = new C0000a();

    /* renamed from: c */
    private static volatile C0029b f1925c;

    /* renamed from: b */
    public final C0321lr f1926b;

    /* renamed from: d */
    private final C0321lr f1927d;

    private C0029b() {
        C0085d dVar = new C0085d();
        this.f1927d = dVar;
        this.f1926b = dVar;
    }

    /* renamed from: a */
    public static C0029b m2002a() {
        if (f1925c == null) {
            synchronized (C0029b.class) {
                if (f1925c == null) {
                    f1925c = new C0029b();
                }
            }
        }
        return f1925c;
    }
}
