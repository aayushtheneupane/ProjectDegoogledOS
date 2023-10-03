package p000;

/* renamed from: gxc */
/* compiled from: PG */
public abstract class gxc {
    /* renamed from: a */
    public abstract Object mo7164a();

    /* renamed from: b */
    public abstract int mo7165b();

    /* renamed from: a */
    public static gxc m10995a(boolean z) {
        return new gwv(Boolean.valueOf(z), 2);
    }

    /* renamed from: a */
    public static gxc m10993a(long j) {
        return new gwv(Long.valueOf(j), 1);
    }

    /* renamed from: a */
    public static gxc m10994a(String str) {
        return new gwv(str, 4);
    }

    /* renamed from: d */
    public final boolean mo7172d() {
        return ((Boolean) mo7164a()).booleanValue();
    }

    /* renamed from: c */
    public final String mo7171c() {
        return (String) mo7164a();
    }
}
