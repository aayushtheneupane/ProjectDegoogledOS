package p000;

import android.content.Intent;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/* renamed from: gxu */
/* compiled from: PG */
public final class gxu implements hfl {

    /* renamed from: a */
    private static final hvy f12275a = hvy.m12261a("com/google/apps/tiktok/experiments/phenotype/ConfigurationUpdatedReceiver");

    /* renamed from: b */
    private final gxw f12276b;

    /* renamed from: c */
    private final Set f12277c;

    public gxu(Map map, gxw gxw) {
        this.f12276b = gxw;
        this.f12277c = map.keySet();
    }

    /* renamed from: a */
    public final ieh mo2555a(Intent intent) {
        ieh ieh;
        String stringExtra = intent.getStringExtra("com.google.android.gms.phenotype.PACKAGE_NAME");
        hlj a = hnb.m11765a("Updating experiments");
        if (stringExtra == null) {
            try {
                ieh = this.f12276b.mo7198a();
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        } else if (!this.f12277c.contains(stringExtra)) {
            ((hvv) ((hvv) f12275a.mo8180b()).mo8201a("com/google/apps/tiktok/experiments/phenotype/ConfigurationUpdatedReceiver", "onReceive", 75, "ConfigurationUpdatedReceiver.java")).mo8206a("Received update for unknown package %s", (Object) stringExtra);
            ieh a2 = ife.m12820a((Object) null);
            if (a != null) {
                a.close();
            }
            return a2;
        } else {
            ieh = this.f12276b.mo7201b(stringExtra);
        }
        goo.m10562a(ieh, "Failed updating experiments for package %s", stringExtra);
        ieh a3 = a.mo7548a(ibd.m12603a(ieh, Exception.class, gxt.f12274a, (Executor) idh.f13918a));
        if (a != null) {
            a.close();
        }
        return a3;
        throw th;
    }
}
