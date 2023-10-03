package p000;

import java.util.logging.Level;

/* renamed from: hvy */
/* compiled from: PG */
public final class hvy extends hvt {

    /* renamed from: b */
    private static final hvx f13494b = new hvx((byte[]) null);

    private hvy(hxa hxa) {
        super(hxa);
    }

    /* renamed from: c */
    public final hvv mo8179a(Level level) {
        boolean b = mo8181b(level);
        boolean a = hxg.m12380a(this.f13492a.mo8243a(), level, b);
        return (!b && !a) ? f13494b : new hvw(this, level, a);
    }

    @Deprecated
    /* renamed from: a */
    public static hvy m12261a(String str) {
        if (!str.isEmpty()) {
            return new hvy(hxg.m12378a(str.replace('/', '.')));
        }
        throw new IllegalArgumentException("injected class name is empty");
    }
}
