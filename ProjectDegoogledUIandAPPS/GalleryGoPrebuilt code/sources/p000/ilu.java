package p000;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/* renamed from: ilu */
/* compiled from: PG */
abstract class ilu {

    /* renamed from: a */
    public final Unsafe f14461a;

    public ilu(Unsafe unsafe) {
        this.f14461a = unsafe;
    }

    /* renamed from: a */
    public abstract byte mo8971a(Object obj, long j);

    /* renamed from: a */
    public abstract void mo8972a(Object obj, long j, byte b);

    /* renamed from: a */
    public abstract void mo8973a(Object obj, long j, double d);

    /* renamed from: a */
    public abstract void mo8974a(Object obj, long j, float f);

    /* renamed from: a */
    public abstract void mo8975a(Object obj, long j, boolean z);

    /* renamed from: b */
    public abstract boolean mo8976b(Object obj, long j);

    /* renamed from: c */
    public abstract float mo8977c(Object obj, long j);

    /* renamed from: d */
    public abstract double mo8978d(Object obj, long j);

    /* renamed from: a */
    public final int mo8979a(Class cls) {
        return this.f14461a.arrayBaseOffset(cls);
    }

    /* renamed from: b */
    public final void mo8984b(Class cls) {
        this.f14461a.arrayIndexScale(cls);
    }

    /* renamed from: e */
    public final int mo8985e(Object obj, long j) {
        return this.f14461a.getInt(obj, j);
    }

    /* renamed from: f */
    public final long mo8986f(Object obj, long j) {
        return this.f14461a.getLong(obj, j);
    }

    /* renamed from: g */
    public final Object mo8987g(Object obj, long j) {
        return this.f14461a.getObject(obj, j);
    }

    /* renamed from: a */
    public final void mo8983a(Field field) {
        this.f14461a.objectFieldOffset(field);
    }

    /* renamed from: a */
    public final void mo8980a(Object obj, long j, int i) {
        this.f14461a.putInt(obj, j, i);
    }

    /* renamed from: a */
    public final void mo8981a(Object obj, long j, long j2) {
        this.f14461a.putLong(obj, j, j2);
    }

    /* renamed from: a */
    public final void mo8982a(Object obj, long j, Object obj2) {
        this.f14461a.putObject(obj, j, obj2);
    }
}
