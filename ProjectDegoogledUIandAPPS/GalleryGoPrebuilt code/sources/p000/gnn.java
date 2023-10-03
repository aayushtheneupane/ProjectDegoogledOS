package p000;

import java.io.File;

/* renamed from: gnn */
/* compiled from: PG */
public final class gnn {

    /* renamed from: a */
    public final hfq f11694a;

    /* renamed from: b */
    public final gkn f11695b;

    /* renamed from: c */
    public final iek f11696c;

    public gnn(hfq hfq, gkn gkn, iek iek) {
        this.f11694a = hfq;
        this.f11695b = gkn;
        this.f11696c = iek;
        ife.m12876b(gkn.mo6807a() != -1, (Object) "Account Id is invalid");
    }

    /* renamed from: a */
    public final gnl mo6863a(String str) {
        hfs a = hfs.m11385a(1);
        hfq hfq = this.f11694a;
        String a2 = m10546a(this.f11695b);
        String str2 = File.separator;
        StringBuilder sb = new StringBuilder(String.valueOf(a2).length() + String.valueOf(str2).length() + String.valueOf(str).length());
        sb.append(a2);
        sb.append(str2);
        sb.append(str);
        return new gnl(new hfr(a, hfq, sb.toString()), this.f11696c);
    }

    /* renamed from: a */
    static String m10546a(gkn gkn) {
        String str = File.separator;
        int a = gkn.mo6807a();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 19);
        sb.append("accounts");
        sb.append(str);
        sb.append(a);
        return sb.toString();
    }
}
