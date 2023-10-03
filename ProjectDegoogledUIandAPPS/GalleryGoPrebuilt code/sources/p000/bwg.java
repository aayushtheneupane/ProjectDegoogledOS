package p000;

import android.content.Intent;
import p003j$.util.function.Supplier;

/* renamed from: bwg */
/* compiled from: PG */
public final /* synthetic */ class bwg implements Supplier {

    /* renamed from: a */
    private final Intent f3764a;

    public bwg(Intent intent) {
        this.f3764a = intent;
    }

    public final Object get() {
        Intent intent = this.f3764a;
        String[] strArr = bwh.f3765a;
        iir g = ceq.f4197g.mo8793g();
        String str = (String) ife.m12898e((Object) intent.getDataString());
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        ceq ceq = (ceq) g.f14318b;
        str.getClass();
        ceq.f4199a |= 1;
        ceq.f4200b = str;
        if (intent.getType() != null) {
            String str2 = (String) ife.m12898e((Object) intent.getType());
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            ceq ceq2 = (ceq) g.f14318b;
            str2.getClass();
            ceq2.f4199a |= 2;
            ceq2.f4201c = str2;
        }
        iir g2 = cbx.f4030f.mo8793g();
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        cbx cbx = (cbx) g2.f14318b;
        ceq ceq3 = (ceq) g.mo8770g();
        ceq3.getClass();
        cbx.f4034c = ceq3;
        cbx.f4033b = 4;
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        cbx cbx2 = (cbx) g2.f14318b;
        cbx2.f4032a = 4 | cbx2.f4032a;
        cbx2.f4035d = true;
        String a = bwh.m3685a(intent);
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        cbx cbx3 = (cbx) g2.f14318b;
        a.getClass();
        cbx3.f4032a |= 8;
        cbx3.f4036e = a;
        return ccm.m4069a((cbx) g2.mo8770g());
    }
}
