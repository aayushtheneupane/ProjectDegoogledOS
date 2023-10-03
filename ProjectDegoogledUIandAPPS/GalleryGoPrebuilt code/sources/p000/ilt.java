package p000;

import sun.misc.Unsafe;

/* renamed from: ilt */
/* compiled from: PG */
final class ilt extends ilu {
    public ilt(Unsafe unsafe) {
        super(unsafe);
    }

    /* renamed from: b */
    public final boolean mo8976b(Object obj, long j) {
        return this.f14461a.getBoolean(obj, j);
    }

    /* renamed from: a */
    public final byte mo8971a(Object obj, long j) {
        return this.f14461a.getByte(obj, j);
    }

    /* renamed from: d */
    public final double mo8978d(Object obj, long j) {
        return this.f14461a.getDouble(obj, j);
    }

    /* renamed from: c */
    public final float mo8977c(Object obj, long j) {
        return this.f14461a.getFloat(obj, j);
    }

    /* renamed from: a */
    public final void mo8975a(Object obj, long j, boolean z) {
        this.f14461a.putBoolean(obj, j, z);
    }

    /* renamed from: a */
    public final void mo8972a(Object obj, long j, byte b) {
        this.f14461a.putByte(obj, j, b);
    }

    /* renamed from: a */
    public final void mo8973a(Object obj, long j, double d) {
        this.f14461a.putDouble(obj, j, d);
    }

    /* renamed from: a */
    public final void mo8974a(Object obj, long j, float f) {
        this.f14461a.putFloat(obj, j, f);
    }
}
