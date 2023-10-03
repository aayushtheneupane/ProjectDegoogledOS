package p000;

import android.net.Uri;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.category.CategoryView;
import com.google.android.apps.photosgo.media.Filter$Category;

/* renamed from: bol */
/* compiled from: PG */
final class bol extends gwe {
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2643a(View view, Object obj) {
        Filter$Category filter$Category;
        int i;
        apj apj;
        ihw ihw;
        String str;
        CategoryView categoryView = (CategoryView) view;
        bnc bnc = (bnc) obj;
        bon a = categoryView.mo2635n();
        iih iih = bks.f3074a;
        iir g = bkg.f2999d.mo8793g();
        iir g2 = bkd.f2990c.mo8793g();
        cxd cxd = bnc.f3186f;
        if (cxd == null) {
            cxd = cxd.f5884h;
        }
        if (cxd.f5887b != 2 || (filter$Category = Filter$Category.forNumber(((Integer) cxd.f5888c).intValue())) == null) {
            filter$Category = Filter$Category.UNKNOWN_CATEGORY;
        }
        switch (filter$Category.ordinal()) {
            case 0:
                i = 1;
                break;
            case 1:
                i = 2;
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                i = 3;
                break;
            case 3:
                i = 4;
                break;
            case 4:
                i = 5;
                break;
            case 5:
                i = 6;
                break;
            case 6:
                i = 7;
                break;
            case 7:
                i = 8;
                break;
            case 8:
                i = 9;
                break;
            case 9:
                i = 10;
                break;
            default:
                i = 1;
                break;
        }
        if (g2.f14319c) {
            g2.mo8751b();
            g2.f14319c = false;
        }
        bkd bkd = (bkd) g2.f14318b;
        bkd.f2993b = i - 1;
        bkd.f2992a |= 1;
        bkd bkd2 = (bkd) g2.mo8770g();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        bkg bkg = (bkg) g.f14318b;
        bkd2.getClass();
        bkg.f3002b = bkd2;
        bkg.f3001a |= 1;
        ((fea) a.f3269k.f9364c.mo5563a(74871).mo5512a(fdm.m8618a(iih, (bkg) g.mo8770g()))).mo5560a((View) a.f3259a);
        a.f3260b.setVisibility(0);
        a.f3261c.setImageResource(bnc.f3184d);
        a.f3261c.setVisibility(0);
        a.f3262d.setVisibility(4);
        a.f3263e.setVisibility(4);
        a.f3263e.setText(bnc.f3185e);
        a.f3263e.setVisibility(8);
        int i2 = bnc.f3182b;
        if (i2 == 3) {
            apj a2 = a.f3265g.mo7307a();
            if (bnc.f3182b != 3) {
                str = "";
            } else {
                str = (String) bnc.f3183c;
            }
            apj = a2.mo1419a((Object) ebh.m7086a(str));
        } else if (i2 != 5) {
            apj = a.f3265g.mo7307a().mo1415a(Uri.EMPTY);
        } else {
            apj a3 = a.f3265g.mo7307a();
            cxd cxd2 = bnc.f3186f;
            if (cxd2 == null) {
                cxd2 = cxd.f5884h;
            }
            if (bnc.f3182b == 5) {
                ihw = (ihw) bnc.f3183c;
            } else {
                ihw = ihw.f14203b;
            }
            apj = a3.mo1419a((Object) cob.m4677a(cxd2, ihw));
        }
        apj.mo1424b((bee) new bom(a, bnc)).mo1426b(a.f3267i.f4753b.mo1861a(new azn(), new bas(a.f3266h)).mo1872k()).mo1422a(a.f3262d);
        a.f3268j.mo3758a((cqg) a);
        a.mo2621a();
        a.f3264f.mo7632a((View) a.f3259a, (View.OnClickListener) new bok(a, bnc));
        categoryView.setContentDescription(categoryView.getContext().getString(bnc.f3185e));
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ View mo2641a(ViewGroup viewGroup) {
        return (CategoryView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_view, viewGroup, false);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2642a(View view) {
        bon a = ((CategoryView) view).mo2635n();
        fee.m8692a(a.f3259a);
        a.f3265g.mo7311a(a.f3262d);
        a.f3268j.mo3761b((cqg) a);
    }
}
