package p000;

/* renamed from: hup */
/* compiled from: PG */
final class hup implements hun {

    /* renamed from: a */
    private final Object f13418a;

    /* renamed from: b */
    private int f13419b;

    /* renamed from: c */
    private final /* synthetic */ hut f13420c;

    hup() {
    }

    /* renamed from: a */
    public final Object mo8079a() {
        return this.f13418a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof hun) {
            hun hun = (hun) obj;
            if (mo8080b() != hun.mo8080b() || !ife.m12891c(this.f13418a, hun.mo8079a())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        Object obj = this.f13418a;
        return (obj != null ? obj.hashCode() : 0) ^ mo8080b();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f13418a);
        int b = mo8080b();
        if (b == 1) {
            return valueOf;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14);
        sb.append(valueOf);
        sb.append(" x ");
        sb.append(b);
        return sb.toString();
    }

    public hup(hut hut, int i) {
        this.f13420c = hut;
        this.f13418a = hut.f13429a[i];
        this.f13419b = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0016, code lost:
        if (p000.ife.m12891c(r4.f13418a, r2.f13429a[r0]) != false) goto L_0x0022;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int mo8080b() {
        /*
            r4 = this;
            int r0 = r4.f13419b
            r1 = -1
            if (r0 != r1) goto L_0x0006
            goto L_0x0018
        L_0x0006:
            hut r2 = r4.f13420c
            int r3 = r2.f13431c
            if (r0 >= r3) goto L_0x0018
            java.lang.Object r3 = r4.f13418a
            java.lang.Object[] r2 = r2.f13429a
            r0 = r2[r0]
            boolean r0 = p000.ife.m12891c((java.lang.Object) r3, (java.lang.Object) r0)
            if (r0 != 0) goto L_0x0022
        L_0x0018:
            hut r0 = r4.f13420c
            java.lang.Object r2 = r4.f13418a
            int r0 = r0.mo8102a((java.lang.Object) r2)
            r4.f13419b = r0
        L_0x0022:
            int r0 = r4.f13419b
            if (r0 == r1) goto L_0x002d
            hut r1 = r4.f13420c
            int[] r1 = r1.f13430b
            r0 = r1[r0]
            return r0
        L_0x002d:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.hup.mo8080b():int");
    }
}
