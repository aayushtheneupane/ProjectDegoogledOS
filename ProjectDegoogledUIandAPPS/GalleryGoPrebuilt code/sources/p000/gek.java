package p000;

import android.view.View;
import com.google.android.apps.photosgo.R;

/* renamed from: gek */
/* compiled from: PG */
final class gek extends C0315ll {

    /* renamed from: b */
    private final /* synthetic */ geq f11110b;

    public gek(geq geq) {
        this.f11110b = geq;
    }

    /* renamed from: a */
    public final void mo232a(View view, C0354mx mxVar) {
        String str;
        super.mo232a(view, mxVar);
        if (this.f11110b.f11122ab.getVisibility() == 0) {
            str = this.f11110b.mo5635a((int) R.string.mtrl_picker_toggle_to_year_selection);
        } else {
            str = this.f11110b.mo5635a((int) R.string.mtrl_picker_toggle_to_day_selection);
        }
        mxVar.mo9433c((CharSequence) str);
    }
}
