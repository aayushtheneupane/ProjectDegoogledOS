package p000;

import android.os.Bundle;

/* renamed from: fwa */
/* compiled from: PG */
final class fwa implements fwb {

    /* renamed from: a */
    private final /* synthetic */ Bundle f10637a;

    public fwa(Bundle bundle) {
        this.f10637a = bundle;
    }

    /* renamed from: a */
    public final void mo6211a(fwt fwt) {
        if (fwt instanceof fwq) {
            int i = fwv.f10647a;
            Bundle bundle = new Bundle();
            ((fwq) fwt).mo6073b(bundle);
            this.f10637a.putBundle((String) fxk.m9821a((Object) fwc.m9705b(fwt)), bundle);
        }
    }
}
