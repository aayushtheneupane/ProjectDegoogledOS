package p000;

/* renamed from: fmn */
/* compiled from: PG */
public final class fmn {

    /* renamed from: a */
    public Integer f10057a;

    /* renamed from: b */
    public fmo f10058b;

    /* renamed from: c */
    public Boolean f10059c;

    /* renamed from: d */
    private Boolean f10060d;

    public fmn(byte[] bArr) {
    }

    /* renamed from: a */
    public final fmq mo5972a() {
        String str = this.f10060d == null ? " enabled" : "";
        if (this.f10057a == null) {
            str = str.concat(" sampleRatePerSecond");
        }
        if (this.f10058b == null) {
            str = String.valueOf(str).concat(" dynamicSampler");
        }
        if (this.f10059c == null) {
            str = String.valueOf(str).concat(" recordTimerDuration");
        }
        if (str.isEmpty()) {
            return new fin(this.f10060d.booleanValue(), this.f10057a.intValue(), this.f10058b, this.f10059c.booleanValue());
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }

    /* renamed from: a */
    public final void mo5973a(boolean z) {
        this.f10060d = Boolean.valueOf(z);
    }

    public fmn() {
    }
}
