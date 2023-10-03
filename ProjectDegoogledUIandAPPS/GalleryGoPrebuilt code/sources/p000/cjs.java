package p000;

/* renamed from: cjs */
/* compiled from: PG */
final class cjs extends cjv {

    /* renamed from: a */
    public final int f4515a;

    /* renamed from: b */
    public final String f4516b;

    /* renamed from: c */
    public final String f4517c;

    /* renamed from: d */
    public final String f4518d;

    /* renamed from: e */
    public final String f4519e;

    /* renamed from: f */
    public final int f4520f;

    public /* synthetic */ cjs(int i, int i2, String str, String str2, String str3, String str4) {
        this.f4520f = i;
        this.f4515a = i2;
        this.f4516b = str;
        this.f4517c = str2;
        this.f4518d = str3;
        this.f4519e = str4;
    }

    /* renamed from: a */
    public final int mo3176a() {
        return this.f4515a;
    }

    /* renamed from: b */
    public final String mo3177b() {
        return this.f4516b;
    }

    /* renamed from: c */
    public final String mo3178c() {
        return this.f4517c;
    }

    /* renamed from: d */
    public final String mo3179d() {
        return this.f4518d;
    }

    /* renamed from: e */
    public final String mo3180e() {
        return this.f4519e;
    }

    /* renamed from: g */
    public final int mo3183g() {
        return this.f4520f;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof cjv)) {
            return false;
        }
        cjv cjv = (cjv) obj;
        int i = this.f4520f;
        int g = cjv.mo3183g();
        if (i != 0) {
            return g == 1 && this.f4515a == cjv.mo3176a() && this.f4516b.equals(cjv.mo3177b()) && this.f4517c.equals(cjv.mo3178c()) && this.f4518d.equals(cjv.mo3179d()) && this.f4519e.equals(cjv.mo3180e());
        }
        throw null;
    }

    public final int hashCode() {
        if (this.f4520f != 0) {
            return ((((((((this.f4515a ^ -722379962) * 1000003) ^ this.f4516b.hashCode()) * 1000003) ^ this.f4517c.hashCode()) * 1000003) ^ this.f4518d.hashCode()) * 1000003) ^ this.f4519e.hashCode();
        }
        throw null;
    }

    /* renamed from: f */
    public final cju mo3182f() {
        return new cju((cjv) this);
    }

    public final String toString() {
        String str = this.f4520f != 1 ? "null" : "VOLUME";
        int i = this.f4515a;
        String str2 = this.f4516b;
        String str3 = this.f4517c;
        String str4 = this.f4518d;
        String str5 = this.f4519e;
        int length = str.length();
        int length2 = String.valueOf(str2).length();
        StringBuilder sb = new StringBuilder(length + 68 + length2 + String.valueOf(str3).length() + String.valueOf(str4).length() + String.valueOf(str5).length());
        sb.append("FolderItem{type=");
        sb.append(str);
        sb.append(", iconRes=");
        sb.append(i);
        sb.append(", key=");
        sb.append(str2);
        sb.append(", path=");
        sb.append(str3);
        sb.append(", name=");
        sb.append(str4);
        sb.append(", summary=");
        sb.append(str5);
        sb.append("}");
        return sb.toString();
    }
}
