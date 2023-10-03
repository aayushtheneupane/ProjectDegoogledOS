package p000;

/* renamed from: hvf */
/* compiled from: PG */
public final class hvf extends hto {

    /* renamed from: a */
    public static final hvf f13465a = new hvf(new Object[0], 0, (Object[]) null, 0, 0);

    /* renamed from: b */
    public final transient int f13466b;

    /* renamed from: c */
    private final transient Object[] f13467c;

    /* renamed from: d */
    private final transient Object[] f13468d;

    /* renamed from: e */
    private final transient int f13469e;

    /* renamed from: f */
    private final transient int f13470f;

    /* renamed from: b */
    public final Object[] mo7879b() {
        return this.f13467c;
    }

    /* renamed from: c */
    public final int mo7880c() {
        return 0;
    }

    /* renamed from: d */
    public final int mo7883d() {
        return this.f13466b;
    }

    /* renamed from: f */
    public final boolean mo7964f() {
        return true;
    }

    /* renamed from: h */
    public final boolean mo7885h() {
        return false;
    }

    public final int hashCode() {
        return this.f13470f;
    }

    public final int size() {
        return this.f13466b;
    }

    public hvf(Object[] objArr, int i, Object[] objArr2, int i2, int i3) {
        this.f13467c = objArr;
        this.f13468d = objArr2;
        this.f13469e = i2;
        this.f13470f = i;
        this.f13466b = i3;
    }

    public final boolean contains(Object obj) {
        Object[] objArr = this.f13468d;
        if (obj != null && objArr != null) {
            int b = ife.m12863b(obj);
            while (true) {
                int i = b & this.f13469e;
                Object obj2 = objArr[i];
                if (obj2 == null) {
                    break;
                } else if (obj2.equals(obj)) {
                    return true;
                } else {
                    b = i + 1;
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    public final int mo7875a(Object[] objArr, int i) {
        System.arraycopy(this.f13467c, 0, objArr, i, this.f13466b);
        return i + this.f13466b;
    }

    /* renamed from: i */
    public final hso mo7999i() {
        return hso.m12045b(this.f13467c, this.f13466b);
    }

    /* renamed from: a */
    public final hvr iterator() {
        return mo7884g().listIterator();
    }
}
