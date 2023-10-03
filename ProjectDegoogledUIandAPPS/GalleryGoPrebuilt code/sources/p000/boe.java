package p000;

import android.view.MenuItem;
import com.google.android.apps.photosgo.R;

/* renamed from: boe */
/* compiled from: PG */
final /* synthetic */ class boe implements C0690zi {

    /* renamed from: a */
    private final bof f3242a;

    public boe(bof bof) {
        this.f3242a = bof;
    }

    /* renamed from: a */
    public final boolean mo2636a(MenuItem menuItem) {
        bof bof = this.f3242a;
        if (menuItem.getItemId() != R.id.category_select) {
            return false;
        }
        dwh dwh = (dwh) bof.f3244b.mo5659r().mo6432b((int) R.id.content);
        if (dwh == null) {
            return true;
        }
        bof.f3250h.mo5552a(fdu.m8653a(), bof.f3251i);
        dwh.mo2635n().mo4528c();
        return true;
    }
}
