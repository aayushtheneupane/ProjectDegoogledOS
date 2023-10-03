package p000;

import com.google.android.apps.photosgo.media.Filter$Category;

/* renamed from: bno */
/* compiled from: PG */
final /* synthetic */ class bno implements icf {

    /* renamed from: a */
    private final bnq f3201a;

    public bno(bnq bnq) {
        this.f3201a = bnq;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        bnq bnq = this.f3201a;
        cjm cjm = (cjm) obj;
        cjm cjm2 = cjm.UNKNOWN;
        Filter$Category filter$Category = Filter$Category.UNKNOWN_CATEGORY;
        int ordinal = cjm.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return ife.m12820a((Object) Boolean.valueOf(bnq.f3206c.mo3175a()));
            }
            if (!(ordinal == 2 || ordinal == 3 || ordinal == 4)) {
                String valueOf = String.valueOf(cjm.name());
                throw new IllegalStateException(valueOf.length() == 0 ? new String("CategoryDataService: unknown consent state: ") : "CategoryDataService: unknown consent state: ".concat(valueOf));
            }
        }
        return ife.m12820a((Object) false);
    }
}
