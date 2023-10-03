package p000;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import sun.misc.Unsafe;

/* renamed from: ibp */
/* compiled from: PG */
final class ibp extends ibe {

    /* renamed from: a */
    public static final Unsafe f13845a;

    /* renamed from: b */
    public static final long f13846b;

    /* renamed from: c */
    public static final long f13847c;

    /* renamed from: d */
    public static final long f13848d;

    /* renamed from: e */
    public static final long f13849e;

    /* renamed from: f */
    public static final long f13850f;

    static {
        Unsafe unsafe;
        try {
            unsafe = Unsafe.getUnsafe();
        } catch (SecurityException e) {
            try {
                unsafe = (Unsafe) AccessController.doPrivileged(new ibo());
            } catch (PrivilegedActionException e2) {
                throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
            }
        }
        Class<ibr> cls = ibr.class;
        try {
            f13847c = unsafe.objectFieldOffset(cls.getDeclaredField("waiters"));
            f13846b = unsafe.objectFieldOffset(cls.getDeclaredField("listeners"));
            f13848d = unsafe.objectFieldOffset(cls.getDeclaredField("value"));
            f13849e = unsafe.objectFieldOffset(ibq.class.getDeclaredField("thread"));
            f13850f = unsafe.objectFieldOffset(ibq.class.getDeclaredField("next"));
            f13845a = unsafe;
        } catch (Exception e3) {
            hqo.m11925a(e3);
            throw new RuntimeException(e3);
        }
    }

    private ibp() {
    }

    public /* synthetic */ ibp(byte[] bArr) {
    }

    /* renamed from: a */
    public final boolean mo8338a(ibr ibr, ibi ibi, ibi ibi2) {
        return f13845a.compareAndSwapObject(ibr, f13846b, ibi, ibi2);
    }

    /* renamed from: a */
    public final boolean mo8340a(ibr ibr, Object obj, Object obj2) {
        return f13845a.compareAndSwapObject(ibr, f13848d, obj, obj2);
    }

    /* renamed from: a */
    public final boolean mo8339a(ibr ibr, ibq ibq, ibq ibq2) {
        return f13845a.compareAndSwapObject(ibr, f13847c, ibq, ibq2);
    }

    /* renamed from: a */
    public final void mo8336a(ibq ibq, ibq ibq2) {
        f13845a.putObject(ibq, f13850f, ibq2);
    }

    /* renamed from: a */
    public final void mo8337a(ibq ibq, Thread thread) {
        f13845a.putObject(ibq, f13849e, thread);
    }
}
