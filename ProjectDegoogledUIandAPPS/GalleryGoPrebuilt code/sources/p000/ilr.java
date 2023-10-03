package p000;

import sun.misc.Unsafe;

/* renamed from: ilr */
/* compiled from: PG */
final class ilr extends ilu {
    public ilr(Unsafe unsafe) {
        super(unsafe);
    }

    /* renamed from: b */
    public final boolean mo8976b(Object obj, long j) {
        return ilv.f14464c ? ilv.m14051i(obj, j) : ilv.m14052j(obj, j);
    }

    /* renamed from: a */
    public final byte mo8971a(Object obj, long j) {
        return ilv.f14464c ? ilv.m14049g(obj, j) : ilv.m14050h(obj, j);
    }

    /* renamed from: d */
    public final double mo8978d(Object obj, long j) {
        return Double.longBitsToDouble(mo8986f(obj, j));
    }

    /* renamed from: c */
    public final float mo8977c(Object obj, long j) {
        return Float.intBitsToFloat(mo8985e(obj, j));
    }

    /* renamed from: a */
    public final void mo8975a(Object obj, long j, boolean z) {
        if (ilv.f14464c) {
            ilv.m14031a(obj, j, (byte) z);
        } else {
            ilv.m14042b(obj, j, z ? (byte) 1 : 0);
        }
    }

    /* renamed from: a */
    public final void mo8972a(Object obj, long j, byte b) {
        if (ilv.f14464c) {
            ilv.m14031a(obj, j, b);
        } else {
            ilv.m14042b(obj, j, b);
        }
    }

    /* renamed from: a */
    public final void mo8973a(Object obj, long j, double d) {
        mo8981a(obj, j, Double.doubleToLongBits(d));
    }

    /* renamed from: a */
    public final void mo8974a(Object obj, long j, float f) {
        mo8980a(obj, j, Float.floatToIntBits(f));
    }
}
