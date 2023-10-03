package p000;

import android.view.MenuItem;
import com.google.android.apps.photosgo.R;

/* renamed from: dud */
/* compiled from: PG */
final /* synthetic */ class dud implements C0690zi {

    /* renamed from: a */
    private final dug f7387a;

    public dud(dug dug) {
        this.f7387a = dug;
    }

    /* renamed from: a */
    public final boolean mo2636a(MenuItem menuItem) {
        dug dug = this.f7387a;
        if (menuItem.getItemId() != R.id.people_grid_show_hide) {
            return false;
        }
        dug.f7401k.mo3765e();
        dug.mo4457c();
        return true;
    }
}
