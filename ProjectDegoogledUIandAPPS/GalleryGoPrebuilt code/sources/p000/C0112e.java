package p000;

import java.util.HashMap;

/* renamed from: e */
/* compiled from: PG */
public final class C0112e extends C0303l {

    /* renamed from: a */
    public final HashMap f7757a = new HashMap();

    /* renamed from: c */
    public final boolean mo4624c(Object obj) {
        return this.f7757a.containsKey(obj);
    }

    /* renamed from: a */
    public final C0195h mo4622a(Object obj) {
        return (C0195h) this.f7757a.get(obj);
    }

    /* renamed from: b */
    public final Object mo4623b(Object obj) {
        Object b = super.mo4623b(obj);
        this.f7757a.remove(obj);
        return b;
    }
}
