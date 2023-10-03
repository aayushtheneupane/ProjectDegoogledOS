package p000;

import java.io.Serializable;

/* renamed from: htb */
/* compiled from: PG */
final class htb implements Serializable {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    private final hsu f13368a;

    public htb(hsu hsu) {
        this.f13368a = hsu;
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return this.f13368a.values();
    }
}
