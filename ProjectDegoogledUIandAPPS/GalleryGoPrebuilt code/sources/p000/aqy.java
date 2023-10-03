package p000;

/* renamed from: aqy */
/* compiled from: PG */
public final class aqy {

    /* renamed from: a */
    public static final aqx f1468a = new aqw();

    /* renamed from: b */
    public final Object f1469b;

    /* renamed from: c */
    public final aqx f1470c;

    /* renamed from: d */
    public final String f1471d;

    /* renamed from: e */
    public volatile byte[] f1472e;

    public aqy(String str, Object obj, aqx aqx) {
        this.f1471d = cns.m4634a(str);
        this.f1469b = obj;
        this.f1470c = (aqx) cns.m4632a((Object) aqx);
    }

    /* renamed from: a */
    public static aqy m1472a(String str, Object obj, aqx aqx) {
        return new aqy(str, obj, aqx);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof aqy) {
            return this.f1471d.equals(((aqy) obj).f1471d);
        }
        return false;
    }

    public final int hashCode() {
        return this.f1471d.hashCode();
    }

    /* renamed from: a */
    public static aqy m1471a(String str, Object obj) {
        return new aqy(str, obj, f1468a);
    }

    public final String toString() {
        String str = this.f1471d;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 14);
        sb.append("Option{key='");
        sb.append(str);
        sb.append("'}");
        return sb.toString();
    }
}
