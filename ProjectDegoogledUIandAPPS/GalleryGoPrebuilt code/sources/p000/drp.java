package p000;

/* renamed from: drp */
/* compiled from: PG */
public final class drp {

    /* renamed from: a */
    public cxi f7242a;

    /* renamed from: b */
    public Integer f7243b;

    /* renamed from: c */
    public Boolean f7244c;

    /* renamed from: d */
    private Boolean f7245d;

    public drp(byte[] bArr) {
    }

    public /* synthetic */ drp(drq drq) {
        this.f7242a = drq.mo4372a();
        this.f7243b = Integer.valueOf(drq.mo4373b());
        this.f7244c = Boolean.valueOf(drq.mo4374c());
        this.f7245d = Boolean.valueOf(drq.mo4375d());
    }

    /* renamed from: a */
    public final drq mo4380a() {
        String str = this.f7242a == null ? " media" : "";
        if (this.f7243b == null) {
            str = str.concat(" index");
        }
        if (this.f7244c == null) {
            str = String.valueOf(str).concat(" primary");
        }
        if (this.f7245d == null) {
            str = String.valueOf(str).concat(" selected");
        }
        if (str.isEmpty()) {
            return new dro(this.f7242a, this.f7243b.intValue(), this.f7244c.booleanValue(), this.f7245d.booleanValue());
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }

    /* renamed from: a */
    public final void mo4381a(boolean z) {
        this.f7245d = Boolean.valueOf(z);
    }

    drp() {
    }
}
