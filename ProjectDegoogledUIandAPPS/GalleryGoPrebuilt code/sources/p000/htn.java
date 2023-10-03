package p000;

import java.io.Serializable;

/* renamed from: htn */
/* compiled from: PG */
final class htn implements Serializable {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    private final Object[] f13386a;

    public htn(Object[] objArr) {
        this.f13386a = objArr;
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return hto.m12126a(this.f13386a);
    }
}
