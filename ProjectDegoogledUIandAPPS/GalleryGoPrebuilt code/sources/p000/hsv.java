package p000;

import java.io.Serializable;

/* renamed from: hsv */
/* compiled from: PG */
final class hsv implements Serializable {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    private final hsu f13361a;

    public hsv(hsu hsu) {
        this.f13361a = hsu;
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return this.f13361a.entrySet();
    }
}
