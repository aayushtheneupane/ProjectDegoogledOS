package p000;

import android.support.p002v7.widget.RecyclerView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.media.Filter$Category;
import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;

/* renamed from: bnp */
/* compiled from: PG */
final /* synthetic */ class bnp implements hpr {

    /* renamed from: a */
    public static final hpr f3202a = new bnp();

    private bnp() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Filter$Category filter$Category;
        iir iir;
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : ((SortedMap) obj).entrySet()) {
            cxd cxd = (cxd) entry.getKey();
            Filter$Category filter$Category2 = Filter$Category.UNKNOWN_CATEGORY;
            if (cxd.f5887b != 2 || (filter$Category = Filter$Category.forNumber(((Integer) cxd.f5888c).intValue())) == null) {
                filter$Category = Filter$Category.UNKNOWN_CATEGORY;
            }
            switch (filter$Category.ordinal()) {
                case 1:
                    iir = bnc.f3179g.mo8793g();
                    if (iir.f14319c) {
                        iir.mo8751b();
                        iir.f14319c = false;
                    }
                    bnc bnc = (bnc) iir.f14318b;
                    int i = 2 | bnc.f3181a;
                    bnc.f3181a = i;
                    bnc.f3185e = R.string.category_people;
                    int i2 = i | 1;
                    bnc.f3181a = i2;
                    bnc.f3184d = R.drawable.quantum_gm_ic_people_black_24;
                    cxd.getClass();
                    bnc.f3186f = cxd;
                    bnc.f3181a = i2 | 16;
                    break;
                case RecyclerView.SCROLL_STATE_SETTLING:
                    iir = bnc.f3179g.mo8793g();
                    if (iir.f14319c) {
                        iir.mo8751b();
                        iir.f14319c = false;
                    }
                    bnc bnc2 = (bnc) iir.f14318b;
                    int i3 = 2 | bnc2.f3181a;
                    bnc2.f3181a = i3;
                    bnc2.f3185e = R.string.category_selfies;
                    int i4 = i3 | 1;
                    bnc2.f3181a = i4;
                    bnc2.f3184d = R.drawable.quantum_gm_ic_account_box_black_24;
                    cxd.getClass();
                    bnc2.f3186f = cxd;
                    bnc2.f3181a = i4 | 16;
                    break;
                case 3:
                    iir = bnc.f3179g.mo8793g();
                    if (iir.f14319c) {
                        iir.mo8751b();
                        iir.f14319c = false;
                    }
                    bnc bnc3 = (bnc) iir.f14318b;
                    int i5 = 2 | bnc3.f3181a;
                    bnc3.f3181a = i5;
                    bnc3.f3185e = R.string.category_nature;
                    int i6 = i5 | 1;
                    bnc3.f3181a = i6;
                    bnc3.f3184d = R.drawable.quantum_gm_ic_local_florist_black_24;
                    cxd.getClass();
                    bnc3.f3186f = cxd;
                    bnc3.f3181a = i6 | 16;
                    break;
                case 4:
                    iir = bnc.f3179g.mo8793g();
                    if (iir.f14319c) {
                        iir.mo8751b();
                        iir.f14319c = false;
                    }
                    bnc bnc4 = (bnc) iir.f14318b;
                    int i7 = 2 | bnc4.f3181a;
                    bnc4.f3181a = i7;
                    bnc4.f3185e = R.string.category_animals;
                    int i8 = i7 | 1;
                    bnc4.f3181a = i8;
                    bnc4.f3184d = R.drawable.quantum_gm_ic_pets_black_24;
                    cxd.getClass();
                    bnc4.f3186f = cxd;
                    bnc4.f3181a = i8 | 16;
                    break;
                case 5:
                    iir = bnc.f3179g.mo8793g();
                    if (iir.f14319c) {
                        iir.mo8751b();
                        iir.f14319c = false;
                    }
                    bnc bnc5 = (bnc) iir.f14318b;
                    int i9 = 2 | bnc5.f3181a;
                    bnc5.f3181a = i9;
                    bnc5.f3185e = R.string.category_screenshots;
                    int i10 = i9 | 1;
                    bnc5.f3181a = i10;
                    bnc5.f3184d = R.drawable.quantum_gm_ic_screenshot_black_24;
                    cxd.getClass();
                    bnc5.f3186f = cxd;
                    bnc5.f3181a = i10 | 16;
                    break;
                case 6:
                    iir = bnc.f3179g.mo8793g();
                    if (iir.f14319c) {
                        iir.mo8751b();
                        iir.f14319c = false;
                    }
                    bnc bnc6 = (bnc) iir.f14318b;
                    int i11 = 2 | bnc6.f3181a;
                    bnc6.f3181a = i11;
                    bnc6.f3185e = R.string.category_documents;
                    int i12 = i11 | 1;
                    bnc6.f3181a = i12;
                    bnc6.f3184d = R.drawable.quantum_gm_ic_description_black_24;
                    cxd.getClass();
                    bnc6.f3186f = cxd;
                    bnc6.f3181a = i12 | 16;
                    break;
                case 7:
                    iir = bnc.f3179g.mo8793g();
                    if (iir.f14319c) {
                        iir.mo8751b();
                        iir.f14319c = false;
                    }
                    bnc bnc7 = (bnc) iir.f14318b;
                    int i13 = 2 | bnc7.f3181a;
                    bnc7.f3181a = i13;
                    bnc7.f3185e = R.string.category_videos;
                    int i14 = i13 | 1;
                    bnc7.f3181a = i14;
                    bnc7.f3184d = R.drawable.quantum_gm_ic_videocam_black_24;
                    cxd.getClass();
                    bnc7.f3186f = cxd;
                    bnc7.f3181a = i14 | 16;
                    break;
                case 8:
                    iir = bnc.f3179g.mo8793g();
                    if (iir.f14319c) {
                        iir.mo8751b();
                        iir.f14319c = false;
                    }
                    bnc bnc8 = (bnc) iir.f14318b;
                    int i15 = 2 | bnc8.f3181a;
                    bnc8.f3181a = i15;
                    bnc8.f3185e = R.string.category_movies;
                    int i16 = i15 | 1;
                    bnc8.f3181a = i16;
                    bnc8.f3184d = R.drawable.quantum_gm_ic_movie_black_24;
                    cxd.getClass();
                    bnc8.f3186f = cxd;
                    bnc8.f3181a = i16 | 16;
                    break;
                case 9:
                    iir = bnc.f3179g.mo8793g();
                    if (iir.f14319c) {
                        iir.mo8751b();
                        iir.f14319c = false;
                    }
                    bnc bnc9 = (bnc) iir.f14318b;
                    int i17 = 2 | bnc9.f3181a;
                    bnc9.f3181a = i17;
                    bnc9.f3185e = R.string.category_food;
                    int i18 = i17 | 1;
                    bnc9.f3181a = i18;
                    bnc9.f3184d = R.drawable.quantum_gm_ic_fastfood_black_24;
                    cxd.getClass();
                    bnc9.f3186f = cxd;
                    bnc9.f3181a = i18 | 16;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown category when constructing builder");
            }
            String str = (String) entry.getValue();
            if (iir.f14319c) {
                iir.mo8751b();
                iir.f14319c = false;
            }
            bnc bnc10 = (bnc) iir.f14318b;
            str.getClass();
            bnc10.f3182b = 3;
            bnc10.f3183c = str;
            arrayList.add((bnc) iir.mo8770g());
        }
        return arrayList;
    }
}
