package p000;

import android.os.Bundle;
import android.view.View;

/* renamed from: gdm */
/* compiled from: PG */
final class gdm extends C0315ll {

    /* renamed from: b */
    private final /* synthetic */ gdo f11029b;

    public gdm(gdo gdo) {
        this.f11029b = gdo;
    }

    /* renamed from: a */
    public final void mo232a(View view, C0354mx mxVar) {
        super.mo232a(view, mxVar);
        if (this.f11029b.f11030a) {
            mxVar.mo9420a(1048576);
            mxVar.mo9434c(true);
            return;
        }
        mxVar.mo9434c(false);
    }

    /* renamed from: a */
    public final boolean mo233a(View view, int i, Bundle bundle) {
        if (i == 1048576) {
            gdo gdo = this.f11029b;
            if (gdo.f11030a) {
                gdo.cancel();
                return true;
            }
        }
        return super.mo233a(view, i, bundle);
    }
}
