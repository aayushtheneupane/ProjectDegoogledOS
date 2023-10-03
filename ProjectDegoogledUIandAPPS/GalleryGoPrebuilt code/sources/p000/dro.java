package p000;

/* renamed from: dro */
/* compiled from: PG */
final class dro extends drn {

    /* renamed from: e */
    private volatile transient int f7240e;

    /* renamed from: f */
    private volatile transient boolean f7241f;

    public dro(cxi cxi, int i, boolean z, boolean z2) {
        super(cxi, i, z, z2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof dro) || hashCode() != obj.hashCode()) {
            return false;
        }
        if (this != obj) {
            if (!(obj instanceof drq)) {
                return false;
            }
            drq drq = (drq) obj;
            if (!(this.f7236a.equals(drq.mo4372a()) && this.f7237b == drq.mo4373b() && this.f7238c == drq.mo4374c() && this.f7239d == drq.mo4375d())) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        if (!this.f7241f) {
            synchronized (this) {
                if (!this.f7241f) {
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
                    this.f7240e = i3 ^ i2;
                    this.f7241f = true;
                }
            }
        }
        return this.f7240e;
    }
}
