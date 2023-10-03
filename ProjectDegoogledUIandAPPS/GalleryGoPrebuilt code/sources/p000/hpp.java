package p000;

import java.io.Serializable;

/* renamed from: hpp */
/* compiled from: PG */
public final class hpp extends hpq implements Serializable {

    /* renamed from: a */
    public static final hpp f13230a = new hpp();
    public static final long serialVersionUID = 1;

    private Object readResolve() {
        return f13230a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo4294a(Object obj, Object obj2) {
        return obj.equals(obj2);
    }
}
