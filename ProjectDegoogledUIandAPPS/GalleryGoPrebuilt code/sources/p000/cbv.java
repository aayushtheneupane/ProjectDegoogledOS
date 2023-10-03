package p000;

import p003j$.util.Optional;

/* renamed from: cbv */
/* compiled from: PG */
public final class cbv extends ccc {

    /* renamed from: a */
    public final cxi f4024a;

    /* renamed from: b */
    public final boolean f4025b;

    /* renamed from: c */
    public final long f4026c;

    /* renamed from: d */
    public final long f4027d;

    /* renamed from: e */
    public final Optional f4028e;

    public /* synthetic */ cbv(cxi cxi, boolean z, long j, long j2, Optional optional) {
        this.f4024a = cxi;
        this.f4025b = z;
        this.f4026c = j;
        this.f4027d = j2;
        this.f4028e = optional;
    }

    /* renamed from: a */
    public final cxi mo3002a() {
        return this.f4024a;
    }

    /* renamed from: b */
    public final boolean mo3003b() {
        return this.f4025b;
    }

    /* renamed from: c */
    public final long mo3004c() {
        return this.f4026c;
    }

    /* renamed from: d */
    public final long mo3005d() {
        return this.f4027d;
    }

    /* renamed from: e */
    public final Optional mo3006e() {
        return this.f4028e;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ccc) {
            ccc ccc = (ccc) obj;
            return this.f4024a.equals(ccc.mo3002a()) && this.f4025b == ccc.mo3003b() && this.f4026c == ccc.mo3004c() && this.f4027d == ccc.mo3005d() && this.f4028e.equals(ccc.mo3006e());
        }
    }

    public final int hashCode() {
        int i;
        cxi cxi = this.f4024a;
        int i2 = cxi.f14173y;
        if (i2 == 0) {
            i2 = ikp.f14397a.mo8876a((Object) cxi).mo8862a(cxi);
            cxi.f14173y = i2;
        }
        int i3 = (i2 ^ 1000003) * 1000003;
        if (!this.f4025b) {
            i = 1237;
        } else {
            i = 1231;
        }
        long j = this.f4026c;
        long j2 = this.f4027d;
        return this.f4028e.hashCode() ^ ((((((i3 ^ i) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003);
    }

    /* renamed from: f */
    public final ccb mo3008f() {
        return new ccb((ccc) this);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f4024a);
        boolean z = this.f4025b;
        long j = this.f4026c;
        long j2 = this.f4027d;
        String valueOf2 = String.valueOf(this.f4028e);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 128 + String.valueOf(valueOf2).length());
        sb.append("Arguments{media=");
        sb.append(valueOf);
        sb.append(", checkRequest=");
        sb.append(z);
        sb.append(", startMilliseconds=");
        sb.append(j);
        sb.append(", endMilliseconds=");
        sb.append(j2);
        sb.append(", outputPath=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
