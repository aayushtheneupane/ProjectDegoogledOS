package p000;

import java.io.File;

/* renamed from: egy */
/* compiled from: PG */
final /* synthetic */ class egy implements hpr {

    /* renamed from: a */
    private final String f8246a;

    /* renamed from: b */
    private final String f8247b;

    /* renamed from: c */
    private final String f8248c;

    /* renamed from: d */
    private final int f8249d;

    /* renamed from: e */
    private final int f8250e;

    /* renamed from: f */
    private final int f8251f;

    public egy(String str, String str2, String str3, int i, int i2, int i3) {
        this.f8246a = str;
        this.f8247b = str2;
        this.f8248c = str3;
        this.f8249d = i;
        this.f8250e = i2;
        this.f8251f = i3;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        String str = this.f8246a;
        String str2 = this.f8247b;
        egw egw = new egw(ehb.m7465a((File) obj, str2.length() == 0 ? new String(str) : str.concat(str2), this.f8248c, this.f8249d, this.f8250e, this.f8251f));
        egw.mo4807g();
        return egw;
    }
}
