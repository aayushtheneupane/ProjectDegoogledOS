package p000;

import android.graphics.RectF;
import p003j$.util.Optional;

/* renamed from: cfe */
/* compiled from: PG */
public final class cfe {

    /* renamed from: a */
    public Optional f4245a;

    /* renamed from: b */
    public RectF f4246b;

    /* renamed from: c */
    private Long f4247c;

    /* renamed from: d */
    private Integer f4248d;

    /* renamed from: e */
    private Integer f4249e;

    /* renamed from: f */
    private byte[] f4250f;

    /* renamed from: g */
    private byte[] f4251g;

    /* renamed from: h */
    private Optional f4252h;

    /* renamed from: i */
    private Optional f4253i;

    /* renamed from: j */
    private Boolean f4254j;

    /* renamed from: k */
    private Boolean f4255k;

    /* renamed from: l */
    private Boolean f4256l;

    public cfe(byte[] bArr) {
        this.f4245a = Optional.empty();
        this.f4252h = Optional.empty();
        this.f4253i = Optional.empty();
    }

    public /* synthetic */ cfe(cff cff) {
        this.f4245a = Optional.empty();
        this.f4252h = Optional.empty();
        this.f4253i = Optional.empty();
        cfc cfc = (cfc) cff;
        this.f4245a = cfc.f4227a;
        this.f4246b = cfc.f4228b;
        this.f4247c = Long.valueOf(cfc.f4229c);
        this.f4248d = Integer.valueOf(cfc.f4230d);
        this.f4249e = Integer.valueOf(cfc.f4231e);
        this.f4250f = cfc.f4232f;
        this.f4251g = cfc.f4233g;
        this.f4252h = cfc.f4234h;
        this.f4253i = cfc.f4235i;
        this.f4254j = Boolean.valueOf(cfc.f4236j);
        this.f4255k = Boolean.valueOf(cfc.f4237k);
        this.f4256l = Boolean.valueOf(cfc.f4238l);
    }

    /* renamed from: a */
    public final cff mo3117a() {
        String str = this.f4246b == null ? " boundingBox" : "";
        if (this.f4247c == null) {
            str = str.concat(" sourceMediaId");
        }
        if (this.f4248d == null) {
            str = String.valueOf(str).concat(" scaledSourceWidth");
        }
        if (this.f4249e == null) {
            str = String.valueOf(str).concat(" scaledSourceHeight");
        }
        if (this.f4250f == null) {
            str = String.valueOf(str).concat(" metadata");
        }
        if (this.f4251g == null) {
            str = String.valueOf(str).concat(" template");
        }
        if (this.f4254j == null) {
            str = String.valueOf(str).concat(" clusteringRun");
        }
        if (this.f4255k == null) {
            str = String.valueOf(str).concat(" embeddingRun");
        }
        if (this.f4256l == null) {
            str = String.valueOf(str).concat(" provisional");
        }
        if (str.isEmpty()) {
            return new cfc(this.f4245a, this.f4246b, this.f4247c.longValue(), this.f4248d.intValue(), this.f4249e.intValue(), this.f4250f, this.f4251g, this.f4252h, this.f4253i, this.f4254j.booleanValue(), this.f4255k.booleanValue(), this.f4256l.booleanValue());
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }

    /* renamed from: a */
    public final void mo3122a(boolean z) {
        this.f4254j = Boolean.valueOf(z);
    }

    /* renamed from: b */
    public final void mo3125b(boolean z) {
        this.f4255k = Boolean.valueOf(z);
    }

    /* renamed from: a */
    public final void mo3118a(double d) {
        this.f4253i = Optional.m16285of(Double.valueOf(d));
    }

    /* renamed from: a */
    public final void mo3123a(byte[] bArr) {
        if (bArr != null) {
            this.f4250f = bArr;
            return;
        }
        throw new NullPointerException("Null metadata");
    }

    /* renamed from: a */
    public final void mo3121a(String str) {
        this.f4252h = Optional.m16285of(str);
    }

    /* renamed from: c */
    public final void mo3127c(boolean z) {
        this.f4256l = Boolean.valueOf(z);
    }

    /* renamed from: a */
    public final void mo3119a(int i) {
        this.f4249e = Integer.valueOf(i);
    }

    /* renamed from: b */
    public final void mo3124b(int i) {
        this.f4248d = Integer.valueOf(i);
    }

    /* renamed from: a */
    public final void mo3120a(long j) {
        this.f4247c = Long.valueOf(j);
    }

    /* renamed from: b */
    public final void mo3126b(byte[] bArr) {
        if (bArr != null) {
            this.f4251g = bArr;
            return;
        }
        throw new NullPointerException("Null template");
    }

    cfe() {
    }
}
