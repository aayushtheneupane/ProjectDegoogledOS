package p000;

import java.io.Serializable;
import java.util.EnumMap;

/* renamed from: hsh */
/* compiled from: PG */
final class hsh implements Serializable {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    private final EnumMap f13344a;

    public hsh(EnumMap enumMap) {
        this.f13344a = enumMap;
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return new hsi(this.f13344a, (byte[]) null);
    }
}
