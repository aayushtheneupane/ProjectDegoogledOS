package p000;

/* renamed from: drn */
/* compiled from: PG */
class drn extends drq {

    /* renamed from: a */
    public final cxi f7236a;

    /* renamed from: b */
    public final int f7237b;

    /* renamed from: c */
    public final boolean f7238c;

    /* renamed from: d */
    public final boolean f7239d;

    public drn(cxi cxi, int i, boolean z, boolean z2) {
        if (cxi != null) {
            this.f7236a = cxi;
            this.f7237b = i;
            this.f7238c = z;
            this.f7239d = z2;
            return;
        }
        throw new NullPointerException("Null media");
    }

    /* renamed from: a */
    public final cxi mo4372a() {
        return this.f7236a;
    }

    /* renamed from: b */
    public final int mo4373b() {
        return this.f7237b;
    }

    /* renamed from: c */
    public final boolean mo4374c() {
        return this.f7238c;
    }

    /* renamed from: d */
    public final boolean mo4375d() {
        return this.f7239d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof drq) {
            drq drq = (drq) obj;
            return this.f7236a.equals(drq.mo4372a()) && this.f7237b == drq.mo4373b() && this.f7238c == drq.mo4374c() && this.f7239d == drq.mo4375d();
        }
    }

    public int hashCode() {
        cxi cxi = this.f7236a;
        int i = cxi.f14173y;
        if (i == 0) {
            i = ikp.f14397a.mo8876a((Object) cxi).mo8862a(cxi);
            cxi.f14173y = i;
        }
        int i2 = 1237;
        int i3 = (((((i ^ 1000003) * 1000003) ^ this.f7237b) * 1000003) ^ (!this.f7238c ? 1237 : 1231)) * 1000003;
        if (this.f7239d) {
            i2 = 1231;
        }
        return i3 ^ i2;
    }

    /* renamed from: e */
    public final drp mo4376e() {
        return new drp((drq) this);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f7236a);
        int i = this.f7237b;
        boolean z = this.f7238c;
        boolean z2 = this.f7239d;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 70);
        sb.append("CarouselItem{media=");
        sb.append(valueOf);
        sb.append(", index=");
        sb.append(i);
        sb.append(", primary=");
        sb.append(z);
        sb.append(", selected=");
        sb.append(z2);
        sb.append("}");
        return sb.toString();
    }
}
