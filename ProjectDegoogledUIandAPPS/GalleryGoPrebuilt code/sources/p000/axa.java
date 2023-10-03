package p000;

import java.net.URL;
import java.security.MessageDigest;

/* renamed from: axa */
/* compiled from: PG */
public final class axa implements aqu {

    /* renamed from: b */
    public final axb f1807b;

    /* renamed from: c */
    public final URL f1808c;

    /* renamed from: d */
    public final String f1809d;

    /* renamed from: e */
    public String f1810e;

    /* renamed from: f */
    public URL f1811f;

    /* renamed from: g */
    private volatile byte[] f1812g;

    /* renamed from: h */
    private int f1813h;

    public axa(String str) {
        axb axb = axb.f1814a;
        this.f1808c = null;
        this.f1809d = cns.m4634a(str);
        this.f1807b = (axb) cns.m4632a((Object) axb);
    }

    public axa(URL url) {
        axb axb = axb.f1814a;
        this.f1808c = (URL) cns.m4632a((Object) url);
        this.f1809d = null;
        this.f1807b = (axb) cns.m4632a((Object) axb);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof axa) {
            axa axa = (axa) obj;
            if (!m1837a().equals(axa.m1837a()) || !this.f1807b.equals(axa.f1807b)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private final String m1837a() {
        String str = this.f1809d;
        return str == null ? ((URL) cns.m4632a((Object) this.f1808c)).toString() : str;
    }

    public final int hashCode() {
        int i = this.f1813h;
        if (i != 0) {
            return i;
        }
        int hashCode = m1837a().hashCode();
        this.f1813h = hashCode;
        int hashCode2 = (hashCode * 31) + this.f1807b.hashCode();
        this.f1813h = hashCode2;
        return hashCode2;
    }

    public final String toString() {
        return m1837a();
    }

    /* renamed from: a */
    public final void mo1494a(MessageDigest messageDigest) {
        if (this.f1812g == null) {
            this.f1812g = m1837a().getBytes(f1466a);
        }
        messageDigest.update(this.f1812g);
    }
}
