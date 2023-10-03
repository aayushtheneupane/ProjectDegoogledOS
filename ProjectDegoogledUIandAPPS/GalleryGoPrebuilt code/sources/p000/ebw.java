package p000;

import androidx.preference.Preference;
import java.util.concurrent.Callable;

/* renamed from: ebw */
/* compiled from: PG */
final /* synthetic */ class ebw implements acz {

    /* renamed from: a */
    private final eby f7871a;

    public ebw(eby eby) {
        this.f7871a = eby;
    }

    /* renamed from: a */
    public final boolean mo184a(Preference preference, Object obj) {
        int i;
        fdr fdr;
        eby eby = this.f7871a;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        hpb hpb = eby.f7896v;
        if (!booleanValue) {
            i = -1;
        } else {
            i = 2;
        }
        cwn.m5509a(gte.m10770a(hpb.f13206b.mo5933a((Callable) new hoz(hpb, i)), (hpr) new hpa(i), hpb.f13207c), "SettingsFragmentPeer:Failed to set dark mode", new Object[0]);
        if (!booleanValue) {
            fdr = eby.f7900z;
        } else {
            fdr = eby.f7899y;
        }
        if (fdr == null) {
            return true;
        }
        eby.f7886l.mo5552a(fdu.m8653a(), fdr);
        return true;
    }
}
