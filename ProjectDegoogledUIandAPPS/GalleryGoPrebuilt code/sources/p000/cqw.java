package p000;

import android.content.Intent;
import android.view.MenuItem;
import com.google.android.apps.photosgo.R;
import p003j$.util.Optional;

/* renamed from: cqw */
/* compiled from: PG */
final /* synthetic */ class cqw implements C0690zi {

    /* renamed from: a */
    private final crd f5445a;

    public cqw(crd crd) {
        this.f5445a = crd;
    }

    /* renamed from: a */
    public final boolean mo2636a(MenuItem menuItem) {
        crd crd = this.f5445a;
        int itemId = menuItem.getItemId();
        if (itemId == R.id.home_settings) {
            crd.f5472m.mo5552a(fdu.m8653a(), crd.f5484y);
            C0395ok okVar = crd.f5466g;
            okVar.startActivity(new Intent(okVar, crd.f5467h));
        } else {
            if (itemId == R.id.home_select) {
                int i = crd.f5457C;
                if (i == 0) {
                    throw null;
                } else if (i == 2) {
                    dwh dwh = (dwh) crd.f5460a.mo5659r().mo6432b((int) R.id.main_content);
                    if (dwh != null) {
                        crd.f5472m.mo5552a(fdu.m8653a(), crd.f5483x);
                        dwh.mo2635n().mo4528c();
                        return true;
                    }
                }
            }
            if (itemId == R.id.home_trash) {
                crd.f5463d.mo3271a(cqy.f5447a, "trash");
                return false;
            } else if (itemId == R.id.home_face_clustering_and_image_labeling) {
                crd.f5472m.mo5552a(fdu.m8653a(), crd.f5485z);
                if (((Optional) crd.f5470k.mo9034a()).isPresent()) {
                    ((cvy) ((Optional) crd.f5470k.mo9034a()).get()).mo3851a();
                    return true;
                }
                cwn.m5514b("Flag supports execution of jobs, but optional binding is missing!", new Object[0]);
                return true;
            } else if (itemId != R.id.home_new_folder) {
                return false;
            } else {
                crd.f5463d.mo3271a(cqz.f5448a, "folder_creation");
                return false;
            }
        }
        return true;
    }
}
