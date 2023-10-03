package p000;

import com.google.android.apps.photosgo.media.Filter$Category;

/* renamed from: dwo */
/* compiled from: PG */
final class dwo implements hol {

    /* renamed from: a */
    private final /* synthetic */ dwn f7534a;

    public dwo(dwn dwn) {
        this.f7534a = dwn;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ hom mo2639a(hoi hoi) {
        Filter$Category filter$Category;
        bnd bnd = (bnd) hoi;
        dwn dwn = this.f7534a;
        dwn.f7512d.mo3755a();
        dwn.f7511c.mo3732b();
        cxd cxd = bnd.f3187a.f3186f;
        if (cxd == null) {
            cxd = cxd.f5884h;
        }
        if (cxd.f5887b == 2) {
            filter$Category = Filter$Category.forNumber(((Integer) cxd.f5888c).intValue());
            if (filter$Category == null) {
                filter$Category = Filter$Category.UNKNOWN_CATEGORY;
            }
        } else {
            filter$Category = Filter$Category.UNKNOWN_CATEGORY;
        }
        if (filter$Category != Filter$Category.PEOPLE) {
            dwn.f7518j.mo3272a(new dwl(dwn, bnd), "category_photo_grid_fragment", cnf.SLIDE_UP);
        } else {
            dwn.f7518j.mo3272a(new dwk(bnd), "people_grid_fragment", cnf.SLIDE_UP);
        }
        return hom.f13155a;
    }
}
