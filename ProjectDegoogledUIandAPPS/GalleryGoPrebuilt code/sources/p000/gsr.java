package p000;

import java.util.Collection;
import java.util.Set;

/* renamed from: gsr */
/* compiled from: PG */
public final class gsr {

    /* renamed from: a */
    public Class f11980a;

    /* renamed from: b */
    public gst f11981b;

    /* renamed from: c */
    public ahf f11982c;

    /* renamed from: d */
    public hpy f11983d;

    /* renamed from: e */
    private ahb f11984e;

    /* renamed from: f */
    private hpy f11985f;

    /* renamed from: g */
    private hto f11986g;

    public gsr(byte[] bArr) {
        this.f11983d = hph.f13219a;
        this.f11985f = hph.f13219a;
    }

    public /* synthetic */ gsr(gsv gsv) {
        this.f11983d = hph.f13219a;
        this.f11985f = hph.f13219a;
        gsm gsm = (gsm) gsv;
        this.f11980a = gsm.f11968a;
        this.f11984e = gsm.f11969b;
        this.f11981b = gsm.f11970c;
        this.f11982c = gsm.f11971d;
        this.f11983d = gsm.f11972e;
        this.f11985f = gsm.f11973f;
        this.f11986g = gsm.f11974g;
    }

    /* renamed from: a */
    public final gsv mo7028a() {
        String str = this.f11980a == null ? " workerClass" : "";
        if (this.f11984e == null) {
            str = str.concat(" constraints");
        }
        if (this.f11981b == null) {
            str = String.valueOf(str).concat(" initialDelay");
        }
        if (this.f11982c == null) {
            str = String.valueOf(str).concat(" inputData");
        }
        if (this.f11986g == null) {
            str = String.valueOf(str).concat(" tags");
        }
        if (str.isEmpty()) {
            return new gsm(this.f11980a, this.f11984e, this.f11981b, this.f11982c, this.f11983d, this.f11985f, this.f11986g);
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }

    /* renamed from: a */
    public final void mo7029a(ahb ahb) {
        if (ahb != null) {
            this.f11984e = ahb;
            return;
        }
        throw new NullPointerException("Null constraints");
    }

    /* renamed from: a */
    public final void mo7031a(Set set) {
        this.f11986g = hto.m12125a((Collection) set);
    }

    /* renamed from: a */
    public final void mo7030a(gsu gsu) {
        this.f11985f = hpy.m11893b(gsu);
    }

    gsr() {
    }
}
