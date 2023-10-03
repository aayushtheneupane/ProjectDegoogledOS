package p000;

/* renamed from: hwb */
/* compiled from: PG */
final class hwb extends hxd {

    /* renamed from: a */
    public Object[] f13503a = new Object[8];

    /* renamed from: b */
    public int f13504b = 0;

    /* renamed from: a */
    public final int mo8191a() {
        return this.f13504b;
    }

    /* renamed from: b */
    public final Object mo8195b(hwn hwn) {
        int a = mo8192a(hwn);
        if (a != -1) {
            return hwn.mo8230a(this.f13503a[a + a + 1]);
        }
        return null;
    }

    /* renamed from: a */
    public final hwn mo8193a(int i) {
        if (i < this.f13504b) {
            return (hwn) this.f13503a[i + i];
        }
        throw new IndexOutOfBoundsException();
    }

    /* renamed from: b */
    public final Object mo8194b(int i) {
        if (i < this.f13504b) {
            return this.f13503a[i + i + 1];
        }
        throw new IndexOutOfBoundsException();
    }

    /* renamed from: a */
    public final int mo8192a(hwn hwn) {
        for (int i = 0; i < this.f13504b; i++) {
            if (this.f13503a[i + i].equals(hwn)) {
                return i;
            }
        }
        return -1;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Metadata{");
        for (int i = 0; i < this.f13504b; i++) {
            sb.append(" '");
            sb.append(mo8193a(i));
            sb.append("': ");
            sb.append(mo8194b(i));
        }
        sb.append(" }");
        return sb.toString();
    }
}
