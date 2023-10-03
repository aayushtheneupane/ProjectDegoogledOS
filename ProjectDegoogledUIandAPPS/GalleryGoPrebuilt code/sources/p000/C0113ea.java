package p000;

/* renamed from: ea */
/* compiled from: PG */
final class C0113ea implements C0111dz {

    /* renamed from: a */
    public final Object[] f7758a = new Object[256];

    /* renamed from: b */
    public int f7759b;

    /* renamed from: a */
    public final Object mo4602a() {
        int i = this.f7759b;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.f7758a;
        Object obj = objArr[i2];
        objArr[i2] = null;
        this.f7759b = i2;
        return obj;
    }

    /* renamed from: a */
    public final void mo4603a(Object obj) {
        int i = this.f7759b;
        Object[] objArr = this.f7758a;
        if (i < 256) {
            objArr[i] = obj;
            this.f7759b = i + 1;
        }
    }
}
