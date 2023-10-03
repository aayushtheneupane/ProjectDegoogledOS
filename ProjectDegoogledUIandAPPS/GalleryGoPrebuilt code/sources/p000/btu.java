package p000;

import android.view.MenuItem;
import com.google.android.apps.photosgo.R;

/* renamed from: btu */
/* compiled from: PG */
final /* synthetic */ class btu implements C0690zi {

    /* renamed from: a */
    private final btw f3576a;

    public btu(btw btw) {
        this.f3576a = btw;
    }

    /* renamed from: a */
    public final boolean mo2636a(MenuItem menuItem) {
        dwh dwh;
        btw btw = this.f3576a;
        int itemId = menuItem.getItemId();
        if (itemId == R.id.single_device_folder_select) {
            dwh dwh2 = (dwh) btw.f3579a.mo5659r().mo6418a("photo_grid_fragment");
            if (dwh2 == null) {
                return true;
            }
            btw.f3587i.mo5552a(fdu.m8653a(), btw.f3592n);
            dwh2.mo2635n().mo4528c();
            return true;
        } else if (itemId != R.id.single_device_folder_find_large_files || (dwh = (dwh) btw.f3579a.mo5659r().mo6418a("photo_grid_fragment")) == null) {
            return false;
        } else {
            btw.f3587i.mo5552a(fdu.m8653a(), btw.f3593o);
            dwn Q = dwh.mo2635n();
            Q.f7526r = true;
            Q.mo4526a(dxy.DESCENDING_FILE_SIZE_BYTES);
            Q.f7512d.mo3765e();
            return false;
        }
    }
}
