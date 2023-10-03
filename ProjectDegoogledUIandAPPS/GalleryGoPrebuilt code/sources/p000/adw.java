package p000;

import android.os.Bundle;
import android.view.View;
import androidx.preference.Preference;

/* renamed from: adw */
/* compiled from: PG */
final class adw extends C0315ll {

    /* renamed from: b */
    private final /* synthetic */ adx f243b;

    public adw(adx adx) {
        this.f243b = adx;
    }

    /* renamed from: a */
    public final void mo232a(View view, C0354mx mxVar) {
        Preference f;
        this.f243b.f245e.mo232a(view, mxVar);
        int childAdapterPosition = this.f243b.f244d.getChildAdapterPosition(view);
        C0634xg adapter = this.f243b.f244d.getAdapter();
        if ((adapter instanceof adr) && (f = ((adr) adapter).mo226f(childAdapterPosition)) != null) {
            f.mo1173a(mxVar);
        }
    }

    /* renamed from: a */
    public final boolean mo233a(View view, int i, Bundle bundle) {
        return this.f243b.f245e.mo233a(view, i, bundle);
    }
}
