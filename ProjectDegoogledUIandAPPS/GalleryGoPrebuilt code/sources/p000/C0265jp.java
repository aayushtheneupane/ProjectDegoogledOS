package p000;

import android.util.Base64;
import java.util.List;

/* renamed from: jp */
/* compiled from: PG */
public final class C0265jp {

    /* renamed from: a */
    public final String f15084a;

    /* renamed from: b */
    public final String f15085b;

    /* renamed from: c */
    public final String f15086c;

    /* renamed from: d */
    public final List f15087d;

    /* renamed from: e */
    public final String f15088e = (this.f15084a + "-" + this.f15085b + "-" + this.f15086c);

    public C0265jp(String str, String str2, String str3, List list) {
        this.f15084a = (String) C0321lr.m14624a((Object) str);
        this.f15085b = (String) C0321lr.m14624a((Object) str2);
        this.f15086c = (String) C0321lr.m14624a((Object) str3);
        this.f15087d = (List) C0321lr.m14624a((Object) list);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f15084a + ", mProviderPackage: " + this.f15085b + ", mQuery: " + this.f15086c + ", mCertificates:");
        for (int i = 0; i < this.f15087d.size(); i++) {
            sb.append(" [");
            List list = (List) this.f15087d.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString((byte[]) list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: 0");
        return sb.toString();
    }
}
