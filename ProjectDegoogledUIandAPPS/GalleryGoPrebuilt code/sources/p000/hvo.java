package p000;

/* renamed from: hvo */
/* compiled from: PG */
public final class hvo extends hto {

    /* renamed from: a */
    private final transient Object f13482a;

    /* renamed from: b */
    private transient int f13483b;

    public hvo(Object obj) {
        this.f13482a = ife.m12898e(obj);
    }

    /* renamed from: f */
    public final boolean mo7964f() {
        return this.f13483b != 0;
    }

    /* renamed from: h */
    public final boolean mo7885h() {
        return false;
    }

    public final int size() {
        return 1;
    }

    public hvo(Object obj, int i) {
        this.f13482a = obj;
        this.f13483b = i;
    }

    public final boolean contains(Object obj) {
        return this.f13482a.equals(obj);
    }

    /* renamed from: a */
    public final int mo7875a(Object[] objArr, int i) {
        objArr[i] = this.f13482a;
        return i + 1;
    }

    /* renamed from: i */
    public final hso mo7999i() {
        return hso.m12033a(this.f13482a);
    }

    public final int hashCode() {
        int i = this.f13483b;
        if (i != 0) {
            return i;
        }
        int hashCode = this.f13482a.hashCode();
        this.f13483b = hashCode;
        return hashCode;
    }

    /* renamed from: a */
    public final hvr iterator() {
        return new htw(this.f13482a);
    }

    public final String toString() {
        String obj = this.f13482a.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 2);
        sb.append('[');
        sb.append(obj);
        sb.append(']');
        return sb.toString();
    }
}
