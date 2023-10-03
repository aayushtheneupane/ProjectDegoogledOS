package p000;

import android.content.Context;
import java.io.InputStream;

/* renamed from: crz */
/* compiled from: PG */
public final class crz {

    /* renamed from: a */
    public final Context f5531a;

    /* renamed from: b */
    public final String f5532b;

    /* renamed from: c */
    public final String f5533c;

    /* renamed from: d */
    public final Float f5534d;

    public crz(Context context, String str, String str2, Float f, cwf cwf) {
        cwf.mo3858a();
        this.f5531a = context;
        this.f5532b = str;
        this.f5533c = str2;
        this.f5534d = f;
    }

    /* renamed from: a */
    public static iir m5340a(Context context, String str) {
        InputStream open;
        hlj a = hnb.m11765a("Load resource from assets");
        try {
            open = context.getAssets().open(str);
            iir g = gcf.f10920c.mo8793g();
            ihw a2 = ihw.m13159a(open);
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            gcf gcf = (gcf) g.f14318b;
            a2.getClass();
            gcf.f10922a = 2;
            gcf.f10923b = a2;
            if (open != null) {
                open.close();
            }
            if (a != null) {
                a.close();
            }
            return g;
        } catch (Throwable th) {
            if (a != null) {
                try {
                    a.close();
                } catch (Throwable th2) {
                    ifn.m12935a(th, th2);
                }
            }
            throw th;
        }
        throw th;
    }
}
