package p000;

/* renamed from: hlk */
/* compiled from: PG */
public final class hlk {

    /* renamed from: d */
    private static final hlk f12982d = new hlk(3, (Object) null, true);

    /* renamed from: e */
    private static final hlk f12983e = new hlk(2, (Object) null, true);

    /* renamed from: a */
    public boolean f12984a;

    /* renamed from: b */
    public final int f12985b;

    /* renamed from: c */
    private final Object f12986c;

    /* renamed from: a */
    static hlk m11693a(int i) {
        return i + -1 != 1 ? f12982d : f12983e;
    }

    public hlk(int i, Object obj, boolean z) {
        this.f12985b = i;
        this.f12986c = obj;
        this.f12984a = z;
    }

    /* renamed from: a */
    public final Object mo7550a() {
        if (this.f12984a && this.f12985b == 1) {
            return this.f12986c;
        }
        throw new IllegalStateException("Calling get() without checking if the extra is present is an error");
    }

    /* renamed from: b */
    public final boolean mo7551b() {
        this.f12984a = true;
        return this.f12985b == 1;
    }
}
