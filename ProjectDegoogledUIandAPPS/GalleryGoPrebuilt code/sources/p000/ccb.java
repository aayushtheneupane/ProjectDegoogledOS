package p000;

import p003j$.util.Optional;

/* renamed from: ccb */
/* compiled from: PG */
public final class ccb {

    /* renamed from: a */
    public Optional f4041a;

    /* renamed from: b */
    private cxi f4042b;

    /* renamed from: c */
    private Boolean f4043c;

    /* renamed from: d */
    private Long f4044d;

    /* renamed from: e */
    private Long f4045e;

    public ccb(byte[] bArr) {
        this.f4041a = Optional.empty();
    }

    public /* synthetic */ ccb(ccc ccc) {
        this.f4041a = Optional.empty();
        cbv cbv = (cbv) ccc;
        this.f4042b = cbv.f4024a;
        this.f4043c = Boolean.valueOf(cbv.f4025b);
        this.f4044d = Long.valueOf(cbv.f4026c);
        this.f4045e = Long.valueOf(cbv.f4027d);
        this.f4041a = cbv.f4028e;
    }

    /* renamed from: a */
    public final ccc mo3017a() {
        String str = this.f4042b == null ? " media" : "";
        if (this.f4043c == null) {
            str = str.concat(" checkRequest");
        }
        if (this.f4044d == null) {
            str = String.valueOf(str).concat(" startMilliseconds");
        }
        if (this.f4045e == null) {
            str = String.valueOf(str).concat(" endMilliseconds");
        }
        if (str.isEmpty()) {
            return new cbv(this.f4042b, this.f4043c.booleanValue(), this.f4044d.longValue(), this.f4045e.longValue(), this.f4041a);
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }

    /* renamed from: a */
    public final void mo3020a(boolean z) {
        this.f4043c = Boolean.valueOf(z);
    }

    /* renamed from: a */
    public final void mo3018a(long j) {
        this.f4045e = Long.valueOf(j);
    }

    /* renamed from: a */
    public final void mo3019a(cxi cxi) {
        if (cxi != null) {
            this.f4042b = cxi;
            return;
        }
        throw new NullPointerException("Null media");
    }

    /* renamed from: b */
    public final void mo3021b(long j) {
        this.f4044d = Long.valueOf(j);
    }

    ccb() {
    }
}
