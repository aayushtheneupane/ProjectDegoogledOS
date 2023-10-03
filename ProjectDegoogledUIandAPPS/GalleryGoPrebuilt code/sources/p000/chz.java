package p000;

import p003j$.util.Optional;

/* renamed from: chz */
/* compiled from: PG */
public final class chz {

    /* renamed from: a */
    public Optional f4440a;

    /* renamed from: b */
    private Long f4441b;

    /* renamed from: c */
    private String f4442c;

    /* renamed from: d */
    private byte[] f4443d;

    /* renamed from: e */
    private Boolean f4444e;

    /* renamed from: f */
    private Boolean f4445f;

    public chz(byte[] bArr) {
        this.f4440a = Optional.empty();
    }

    public /* synthetic */ chz(cia cia) {
        this.f4440a = Optional.empty();
        cfd cfd = (cfd) cia;
        this.f4440a = cfd.f4239a;
        this.f4441b = Long.valueOf(cfd.f4240b);
        this.f4442c = cfd.f4241c;
        this.f4443d = cfd.f4242d;
        this.f4444e = Boolean.valueOf(cfd.f4243e);
        this.f4445f = Boolean.valueOf(cfd.f4244f);
    }

    /* renamed from: a */
    public final cia mo3146a() {
        String str = this.f4441b == null ? " thumbnailFaceId" : "";
        if (this.f4442c == null) {
            str = str.concat(" personClusterKey");
        }
        if (this.f4443d == null) {
            str = String.valueOf(str).concat(" thumbnail");
        }
        if (this.f4444e == null) {
            str = String.valueOf(str).concat(" thumbnailingRun");
        }
        if (this.f4445f == null) {
            str = String.valueOf(str).concat(" hidden");
        }
        if (str.isEmpty()) {
            return new cfd(this.f4440a, this.f4441b.longValue(), this.f4442c, this.f4443d, this.f4444e.booleanValue(), this.f4445f.booleanValue());
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }

    /* renamed from: a */
    public final void mo3149a(boolean z) {
        this.f4445f = Boolean.valueOf(z);
    }

    /* renamed from: a */
    public final void mo3148a(String str) {
        if (str != null) {
            this.f4442c = str;
            return;
        }
        throw new NullPointerException("Null personClusterKey");
    }

    /* renamed from: a */
    public final void mo3150a(byte[] bArr) {
        if (bArr != null) {
            this.f4443d = bArr;
            return;
        }
        throw new NullPointerException("Null thumbnail");
    }

    /* renamed from: a */
    public final void mo3147a(long j) {
        this.f4441b = Long.valueOf(j);
    }

    /* renamed from: b */
    public final void mo3151b(boolean z) {
        this.f4444e = Boolean.valueOf(z);
    }

    chz() {
    }
}
