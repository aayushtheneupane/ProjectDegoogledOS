package p000;

/* renamed from: fkx */
/* compiled from: PG */
public final class fkx {

    /* renamed from: a */
    public fiq f9933a;

    /* renamed from: b */
    private Boolean f9934b;

    public fkx(byte[] bArr) {
    }

    /* renamed from: a */
    public final fky mo5903a() {
        String str = this.f9934b == null ? " enabled" : "";
        if (this.f9933a == null) {
            str = str.concat(" metricExtensionProvider");
        }
        if (str.isEmpty()) {
            return new fig(this.f9934b.booleanValue(), this.f9933a);
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }

    /* renamed from: a */
    public final void mo5904a(boolean z) {
        this.f9934b = Boolean.valueOf(z);
    }

    public fkx() {
    }
}
