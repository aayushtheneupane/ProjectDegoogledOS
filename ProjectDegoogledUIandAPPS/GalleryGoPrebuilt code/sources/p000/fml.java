package p000;

/* renamed from: fml */
/* compiled from: PG */
public final class fml {

    /* renamed from: a */
    public iel f10052a;

    /* renamed from: b */
    public Integer f10053b;

    /* renamed from: c */
    public Integer f10054c;

    /* renamed from: d */
    public Integer f10055d;

    /* renamed from: e */
    public Boolean f10056e;

    public fml(byte[] bArr) {
    }

    public fml() {
    }

    /* renamed from: a */
    public final fmm mo5971a() {
        String str = this.f10053b == null ? " primesInitializationPriority" : "";
        if (this.f10054c == null) {
            str = str.concat(" primesMetricExecutorPriority");
        }
        if (this.f10055d == null) {
            str = String.valueOf(str).concat(" primesMetricExecutorPoolSize");
        }
        if (this.f10056e == null) {
            str = String.valueOf(str).concat(" enableEarlyTimers");
        }
        if (!str.isEmpty()) {
            String valueOf = String.valueOf(str);
            throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
        }
        fim fim = new fim(this.f10052a, this.f10053b.intValue(), this.f10054c.intValue(), this.f10055d.intValue(), this.f10056e.booleanValue());
        int i = fim.f9733a;
        boolean z = false;
        if (i > 0 && i <= 2) {
            z = true;
        }
        ife.m12877b(z, "Thread pool size must be less than or equal to %s", 2);
        return fim;
    }
}
