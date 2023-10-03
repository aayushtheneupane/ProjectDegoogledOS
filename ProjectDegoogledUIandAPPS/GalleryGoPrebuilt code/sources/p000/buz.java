package p000;

import android.view.MenuItem;
import com.google.android.apps.photosgo.R;

/* renamed from: buz */
/* compiled from: PG */
final /* synthetic */ class buz implements C0690zi {

    /* renamed from: a */
    private final bvv f3652a;

    public buz(bvv bvv) {
        this.f3652a = bvv;
    }

    /* renamed from: a */
    public final boolean mo2636a(MenuItem menuItem) {
        bvv bvv = this.f3652a;
        if (menuItem.getItemId() != R.id.editor_top_bar_save) {
            return false;
        }
        bvv.f3742x.mo5552a(fdu.m8653a(), bvv.f3712Y);
        bvv.f3696I = true;
        bvv.mo2806a(false);
        if (!dvg.m6745a(bvv.f3722d.mo2634k(), new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"})) {
            bvv.f3722d.mo5640a(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"});
            return true;
        }
        bvv.mo2803a();
        return true;
    }
}
