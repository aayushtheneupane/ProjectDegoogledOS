package p000;

import java.io.Serializable;

/* renamed from: hsx */
/* compiled from: PG */
final class hsx implements Serializable {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    private final hsu f13362a;

    public hsx(hsu hsu) {
        this.f13362a = hsu;
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return this.f13362a.keySet();
    }
}
