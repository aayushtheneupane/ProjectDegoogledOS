package p000;

import android.os.Build;
import java.util.concurrent.Callable;

/* renamed from: hgb */
/* compiled from: PG */
public final class hgb {

    /* renamed from: a */
    public final gag f12688a;

    public hgb(gag gag) {
        this.f12688a = gag;
    }

    /* renamed from: a */
    public final ieh mo7393a(hga hga) {
        gag gag = this.f12688a;
        gaa gaa = new gaa(new hfy(hga));
        gag.mo6371a();
        gbr gbr = new gbr(gag.f10783a);
        hlj a = hnb.m11765a("Transaction");
        try {
            iei a2 = iei.m12761a(hmq.m11749a((Callable) new gad(gag, gaa, gbr)));
            gag.f10785c.execute(a2);
            int i = Build.VERSION.SDK_INT;
            a2.mo53a(new gab(a2, gbr), idh.f13918a);
            ieh a3 = a.mo7548a(a2);
            if (a != null) {
                a.close();
            }
            return a3;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
