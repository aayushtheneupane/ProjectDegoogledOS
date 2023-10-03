package p000;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import com.google.android.apps.photosgo.R;
import p003j$.util.function.Consumer;

/* renamed from: dnc */
/* compiled from: PG */
final /* synthetic */ class dnc implements C0690zi {

    /* renamed from: a */
    private final dnn f6858a;

    public dnc(dnn dnn) {
        this.f6858a = dnn;
    }

    /* renamed from: a */
    public final boolean mo2636a(MenuItem menuItem) {
        dnn dnn = this.f6858a;
        int itemId = menuItem.getItemId();
        if (itemId == R.id.oneup_menu_infosheet) {
            dnn.mo4270a("info", (Consumer) new dmi(dnn));
            return true;
        } else if (itemId == R.id.oneup_menu_set_as_main_photo) {
            dnn.mo4270a("set primary", (Consumer) new dmj(dnn));
            return true;
        } else if (itemId == R.id.oneup_menu_all_photos) {
            C0395ok okVar = dnn.f6896e;
            okVar.startActivity(dvg.m6742a((Context) okVar, new Intent(okVar, dnn.f6911t)));
            dnn.f6896e.finish();
            return true;
        } else if (itemId == R.id.oneup_menu_use_as) {
            dnn.mo4270a("use as", (Consumer) new dmk(dnn));
            return true;
        } else if (itemId != R.id.oneup_menu_edit_in) {
            return false;
        } else {
            dnn.mo4270a("edit in", (Consumer) new dml(dnn));
            return true;
        }
    }
}
