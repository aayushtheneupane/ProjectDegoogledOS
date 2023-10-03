package p000;

/* renamed from: hfu */
/* compiled from: PG */
public final class hfu {

    /* renamed from: a */
    public String f12674a;

    /* renamed from: b */
    public hfs f12675b;

    /* renamed from: c */
    public hsj f12676c;

    /* renamed from: d */
    public fyx f12677d;

    /* renamed from: e */
    public iij f12678e;

    /* renamed from: f */
    private ikf f12679f;

    /* renamed from: g */
    private hso f12680g;

    public hfu(byte[] bArr) {
    }

    /* renamed from: a */
    public final hfv mo7382a() {
        hsj hsj = this.f12676c;
        if (hsj != null) {
            this.f12680g = hsj.mo7905a();
        } else if (this.f12680g == null) {
            this.f12680g = hso.m12047f();
        }
        String str = this.f12674a == null ? " name" : "";
        if (this.f12679f == null) {
            str = str.concat(" schema");
        }
        if (this.f12675b == null) {
            str = String.valueOf(str).concat(" storage");
        }
        if (this.f12677d == null) {
            str = String.valueOf(str).concat(" handler");
        }
        if (this.f12678e == null) {
            str = String.valueOf(str).concat(" extensionRegistry");
        }
        if (str.isEmpty()) {
            return new hft(this.f12674a, this.f12679f, this.f12675b, this.f12680g, this.f12677d, this.f12678e);
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }

    /* renamed from: a */
    public final void mo7383a(ikf ikf) {
        if (ikf != null) {
            this.f12679f = ikf;
            return;
        }
        throw new NullPointerException("Null schema");
    }

    hfu() {
    }
}
