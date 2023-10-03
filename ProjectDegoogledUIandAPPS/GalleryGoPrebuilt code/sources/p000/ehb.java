package p000;

import android.content.Context;
import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: ehb */
/* compiled from: PG */
public final class ehb {

    /* renamed from: a */
    public final Context f8260a;

    /* renamed from: b */
    public final iel f8261b;

    public ehb(Context context, iel iel) {
        this.f8260a = context;
        this.f8261b = iel;
    }

    /* renamed from: a */
    public final ieh mo4809a(Context context) {
        return this.f8261b.mo5933a(hmq.m11749a((Callable) new eha(context)));
    }

    /* renamed from: a */
    public static egx m7465a(File file, String str, String str2, int i, int i2, int i3) {
        File file2 = new File(file, str);
        iir g = egx.f8238g.mo8793g();
        String path = file2.getPath();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        egx egx = (egx) g.f14318b;
        path.getClass();
        int i4 = egx.f8240a | 1;
        egx.f8240a = i4;
        egx.f8241b = path;
        str2.getClass();
        int i5 = i4 | 2;
        egx.f8240a = i5;
        egx.f8242c = str2;
        int i6 = i5 | 4;
        egx.f8240a = i6;
        egx.f8243d = i;
        int i7 = i6 | 8;
        egx.f8240a = i7;
        egx.f8244e = i2;
        egx.f8240a = i7 | 16;
        egx.f8245f = i3;
        return (egx) g.mo8770g();
    }

    /* renamed from: a */
    public final ieh mo4810a(String str, String str2, String str3, int i, int i2, int i3) {
        boolean z = false;
        if (!str2.isEmpty() && str2.charAt(0) == '.') {
            z = true;
        }
        ife.m12890c(z);
        return gte.m10770a(mo4809a(this.f8260a), (hpr) new egy(str, str2, str3, i, i2, i3), (Executor) this.f8261b);
    }
}
