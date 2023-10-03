package p000;

import java.security.MessageDigest;
import java.util.Map;

/* renamed from: atq */
/* compiled from: PG */
public final class atq implements aqu {

    /* renamed from: b */
    private final Object f1651b;

    /* renamed from: c */
    private final int f1652c;

    /* renamed from: d */
    private final int f1653d;

    /* renamed from: e */
    private final Class f1654e;

    /* renamed from: f */
    private final Class f1655f;

    /* renamed from: g */
    private final aqu f1656g;

    /* renamed from: h */
    private final Map f1657h;

    /* renamed from: i */
    private final aqz f1658i;

    /* renamed from: j */
    private int f1659j;

    public atq(Object obj, aqu aqu, int i, int i2, Map map, Class cls, Class cls2, aqz aqz) {
        this.f1651b = cns.m4632a(obj);
        this.f1656g = (aqu) cns.m4633a((Object) aqu, "Signature must not be null");
        this.f1652c = i;
        this.f1653d = i2;
        this.f1657h = (Map) cns.m4632a((Object) map);
        this.f1654e = (Class) cns.m4633a((Object) cls, "Resource class must not be null");
        this.f1655f = (Class) cns.m4633a((Object) cls2, "Transcode class must not be null");
        this.f1658i = (aqz) cns.m4632a((Object) aqz);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof atq) {
            atq atq = (atq) obj;
            if (!this.f1651b.equals(atq.f1651b) || !this.f1656g.equals(atq.f1656g) || this.f1653d != atq.f1653d || this.f1652c != atq.f1652c || !this.f1657h.equals(atq.f1657h) || !this.f1654e.equals(atq.f1654e) || !this.f1655f.equals(atq.f1655f) || !this.f1658i.equals(atq.f1658i)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.f1659j;
        if (i != 0) {
            return i;
        }
        int hashCode = this.f1651b.hashCode();
        this.f1659j = hashCode;
        int hashCode2 = (((((hashCode * 31) + this.f1656g.hashCode()) * 31) + this.f1652c) * 31) + this.f1653d;
        this.f1659j = hashCode2;
        int hashCode3 = (hashCode2 * 31) + this.f1657h.hashCode();
        this.f1659j = hashCode3;
        int hashCode4 = (hashCode3 * 31) + this.f1654e.hashCode();
        this.f1659j = hashCode4;
        int hashCode5 = (hashCode4 * 31) + this.f1655f.hashCode();
        this.f1659j = hashCode5;
        int hashCode6 = (hashCode5 * 31) + this.f1658i.hashCode();
        this.f1659j = hashCode6;
        return hashCode6;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f1651b);
        int i = this.f1652c;
        int i2 = this.f1653d;
        String valueOf2 = String.valueOf(this.f1654e);
        String valueOf3 = String.valueOf(this.f1655f);
        String valueOf4 = String.valueOf(this.f1656g);
        int i3 = this.f1659j;
        String valueOf5 = String.valueOf(this.f1657h);
        String valueOf6 = String.valueOf(this.f1658i);
        int length = String.valueOf(valueOf).length();
        int length2 = String.valueOf(valueOf2).length();
        int length3 = String.valueOf(valueOf3).length();
        int length4 = String.valueOf(valueOf4).length();
        StringBuilder sb = new StringBuilder(length + 151 + length2 + length3 + length4 + String.valueOf(valueOf5).length() + String.valueOf(valueOf6).length());
        sb.append("EngineKey{model=");
        sb.append(valueOf);
        sb.append(", width=");
        sb.append(i);
        sb.append(", height=");
        sb.append(i2);
        sb.append(", resourceClass=");
        sb.append(valueOf2);
        sb.append(", transcodeClass=");
        sb.append(valueOf3);
        sb.append(", signature=");
        sb.append(valueOf4);
        sb.append(", hashCode=");
        sb.append(i3);
        sb.append(", transformations=");
        sb.append(valueOf5);
        sb.append(", options=");
        sb.append(valueOf6);
        sb.append('}');
        return sb.toString();
    }

    /* renamed from: a */
    public final void mo1494a(MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }
}
