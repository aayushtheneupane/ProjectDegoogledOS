package p000;

import android.content.Intent;
import p003j$.util.function.Supplier;

/* renamed from: dzx */
/* compiled from: PG */
public final /* synthetic */ class dzx implements Supplier {

    /* renamed from: a */
    private final Intent f7751a;

    public dzx(Intent intent) {
        this.f7751a = intent;
    }

    public final Object get() {
        Intent intent = this.f7751a;
        String[] strArr = dzy.f7752a;
        iir g = dzr.f7736d.mo8793g();
        boolean booleanExtra = intent.getBooleanExtra("android.intent.extra.ALLOW_MULTIPLE", false);
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        dzr dzr = (dzr) g.f14318b;
        dzr.f7738a |= 1;
        dzr.f7739b = booleanExtra;
        String type = intent.getType();
        if (!hpz.m11899a(type)) {
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            dzr dzr2 = (dzr) g.f14318b;
            type.getClass();
            dzr2.f7738a |= 2;
            dzr2.f7740c = type;
        }
        dzq dzq = new dzq();
        ftr.m9611b(dzq);
        ftr.m9610a((C0147fh) dzq);
        hcl.m11210a(dzq, (dzr) g.mo8770g());
        return dzq;
    }
}
