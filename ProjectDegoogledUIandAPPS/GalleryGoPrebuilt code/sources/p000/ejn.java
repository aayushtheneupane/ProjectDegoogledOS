package p000;

import android.content.Context;
import java.nio.charset.Charset;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: ejn */
/* compiled from: PG */
public final class ejn implements eiy {

    /* renamed from: a */
    public static final Charset f8426a = Charset.forName("UTF-8");

    /* renamed from: b */
    public static final fqf f8427b;

    /* renamed from: d */
    public static final ConcurrentHashMap f8428d = new ConcurrentHashMap();

    /* renamed from: e */
    public static Boolean f8429e = null;

    /* renamed from: f */
    public static Long f8430f = null;

    /* renamed from: c */
    public final Context f8431c;

    static {
        fqf fqf = new fqf(evb.m8187a("com.google.android.gms.clearcut.public"));
        if (!fqf.f10247d) {
            fqf fqf2 = new fqf(fqf.f10244a, "gms:playlog:service:samplingrules_", fqf.f10246c, false);
            f8427b = new fqf(fqf2.f10244a, fqf2.f10245b, "LogSamplingRules__", fqf2.f10247d);
            return;
        }
        throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
    }

    public ejn(Context context) {
        this.f8431c = context;
        if (context != null) {
            synchronized (fqg.f10248a) {
                if (fqg.f10249b == null) {
                    synchronized (fqg.f10248a) {
                        Context applicationContext = context.getApplicationContext();
                        if (applicationContext != null) {
                            context = applicationContext;
                        }
                        if (fqg.f10249b != context) {
                            fpr.m9370a();
                            fqh.m9407a();
                            fpy.m9390a();
                            fqg.m9403a();
                            fqg.f10249b = context;
                            fqg.f10250c = ife.m12811a(fqc.f10243a);
                        }
                    }
                }
            }
        }
    }
}
