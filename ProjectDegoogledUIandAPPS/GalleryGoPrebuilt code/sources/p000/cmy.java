package p000;

import com.google.android.apps.photosgo.R;
import java.io.File;
import java.util.Collection;
import java.util.List;

/* renamed from: cmy */
/* compiled from: PG */
final class cmy implements hol {

    /* renamed from: a */
    private final /* synthetic */ cmv f4714a;

    public cmy(cmv cmv) {
        this.f4714a = cmv;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ hom mo2639a(hoi hoi) {
        brx brx = (brx) hoi;
        cmv cmv = this.f4714a;
        if (!cmv.f4711l) {
            cmv.f4711l = true;
            if (!brx.f3465a.f5898d.isEmpty()) {
                String str = brx.f3465a.f5898d;
                cmo cmo = cmv.f4700a;
                cmv.f4705f.mo6986a(grw.m10690e(cmv.f4707h.mo4796a((List) cmo.f4692b ? hso.m12041a((Collection) cmo.f4693c) : hso.m12047f(), (List) hso.m12033a((Object) new File(str)))), grt.m10683a((CharSequence) str), cmv.f4709j);
            } else {
                cwn.m5514b("PickFolderToAddItems: Unable to fetch folder path of the chosen folder", new Object[0]);
                cmv.f4703d.mo2572a((int) R.string.pick_folder_folder_unavailable);
            }
        }
        return hom.f13155a;
    }
}
