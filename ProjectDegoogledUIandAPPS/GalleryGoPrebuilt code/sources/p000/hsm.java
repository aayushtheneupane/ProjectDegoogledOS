package p000;

import java.io.Serializable;

/* renamed from: hsm */
/* compiled from: PG */
final class hsm implements Serializable {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    private final Object[] f13348a;

    public hsm(Object[] objArr) {
        this.f13348a = objArr;
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return hso.m12042a(this.f13348a);
    }
}
