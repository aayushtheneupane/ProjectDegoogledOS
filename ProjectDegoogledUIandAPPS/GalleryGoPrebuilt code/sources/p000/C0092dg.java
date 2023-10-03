package p000;

import java.io.Serializable;

/* renamed from: dg */
/* compiled from: PG */
final class C0092dg implements Serializable {
    public static final long serialVersionUID = 1;

    /* renamed from: a */
    public final String f6484a;

    /* renamed from: b */
    public final C0086da f6485b;

    /* renamed from: c */
    public final C0089dd f6486c;

    /* renamed from: d */
    public final C0089dd f6487d;

    public C0092dg(String str, C0086da daVar, C0089dd ddVar, C0089dd ddVar2) {
        this.f6484a = str;
        this.f6485b = daVar;
        this.f6486c = ddVar;
        this.f6487d = ddVar2;
    }

    @Deprecated
    public final int hashCode() {
        return this.f6484a.hashCode() ^ this.f6485b.hashCode();
    }

    public final String toString() {
        String str;
        String str2 = this.f6484a;
        String obj = this.f6485b.toString();
        C0089dd ddVar = this.f6486c;
        String str3 = "";
        if (ddVar != null) {
            String valueOf = String.valueOf(ddVar.toString());
            str = valueOf.length() == 0 ? new String(" ") : " ".concat(valueOf);
        } else {
            str = str3;
        }
        C0089dd ddVar2 = this.f6487d;
        if (ddVar2 != null) {
            String valueOf2 = String.valueOf(ddVar2.toString());
            str3 = valueOf2.length() == 0 ? new String(" ") : " ".concat(valueOf2);
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 2 + String.valueOf(obj).length() + String.valueOf(str).length() + String.valueOf(str3).length());
        sb.append(str2);
        sb.append(": ");
        sb.append(obj);
        sb.append(str);
        sb.append(str3);
        return sb.toString();
    }
}
