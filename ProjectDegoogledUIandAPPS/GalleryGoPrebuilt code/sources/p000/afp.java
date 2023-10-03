package p000;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* renamed from: afp */
/* compiled from: PG */
public final class afp {

    /* renamed from: a */
    public static final ArrayList f345a = new ArrayList();

    /* renamed from: b */
    private static final ThreadLocal f346b = new ThreadLocal();

    static {
        new afs((byte[]) null);
    }

    /* renamed from: a */
    static C0290kn m380a() {
        C0290kn knVar;
        WeakReference weakReference = (WeakReference) f346b.get();
        if (weakReference != null && (knVar = (C0290kn) weakReference.get()) != null) {
            return knVar;
        }
        C0290kn knVar2 = new C0290kn();
        f346b.set(new WeakReference(knVar2));
        return knVar2;
    }
}
