package p000;

@Deprecated
/* renamed from: fbx */
/* compiled from: PG */
final class fbx implements fbl {

    /* renamed from: a */
    private final eus f9257a;

    public fbx(eus eus) {
        this.f9257a = eus;
    }

    /* renamed from: c */
    public final boolean mo5467c() {
        eus eus = this.f9257a;
        if (eus.f9074g == 2) {
            return eus.f9070c;
        }
        throw new IllegalArgumentException("Not a boolean type");
    }

    /* renamed from: g */
    public final byte[] mo5471g() {
        eus eus = this.f9257a;
        if (eus.f9074g == 5) {
            return eus.f9073f;
        }
        throw new IllegalArgumentException("Not a bytes type");
    }

    /* renamed from: d */
    public final double mo5468d() {
        eus eus = this.f9257a;
        if (eus.f9074g == 3) {
            return eus.f9071d;
        }
        throw new IllegalArgumentException("Not a double type");
    }

    /* renamed from: b */
    public final String mo5466b() {
        return this.f9257a.f9068a;
    }

    /* renamed from: a */
    public final int mo5465a() {
        return this.f9257a.f9074g;
    }

    /* renamed from: f */
    public final long mo5470f() {
        eus eus = this.f9257a;
        if (eus.f9074g == 1) {
            return eus.f9069b;
        }
        throw new IllegalArgumentException("Not a long type");
    }

    /* renamed from: e */
    public final String mo5469e() {
        eus eus = this.f9257a;
        if (eus.f9074g == 4) {
            return eus.f9072e;
        }
        throw new IllegalArgumentException("Not a String type");
    }
}
