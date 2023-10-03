package p000;

/* renamed from: gwj */
/* compiled from: PG */
public final class gwj {

    /* renamed from: a */
    public ihw f12196a;

    /* renamed from: b */
    private gwi f12197b;

    /* renamed from: c */
    private Boolean f12198c;

    public gwj(byte[] bArr) {
    }

    /* renamed from: a */
    public final gwk mo7144a() {
        String str = this.f12196a == null ? " data" : "";
        if (this.f12197b == null) {
            str = str.concat(" type");
        }
        if (this.f12198c == null) {
            str = String.valueOf(str).concat(" containsPii");
        }
        if (str.isEmpty()) {
            return new gwg(this.f12196a, this.f12197b, this.f12198c.booleanValue());
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }

    /* renamed from: a */
    public final void mo7146a(boolean z) {
        this.f12198c = Boolean.valueOf(z);
    }

    /* renamed from: a */
    public final void mo7145a(gwi gwi) {
        if (gwi != null) {
            this.f12197b = gwi;
            return;
        }
        throw new NullPointerException("Null type");
    }

    gwj() {
    }
}
