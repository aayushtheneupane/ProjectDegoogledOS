package p000;

import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckBox;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.category.CategoryListView;
import com.google.android.apps.photosgo.photogrid.PhotosPromoView;
import java.util.List;
import java.util.Map;
import p003j$.util.function.Function;

/* renamed from: dwg */
/* compiled from: PG */
final class dwg extends C0634xg {

    /* renamed from: c */
    public final hpq f7482c;

    /* renamed from: d */
    public final Function f7483d;

    /* renamed from: e */
    public dxy f7484e;

    /* renamed from: f */
    public Map f7485f = hvb.f13454a;

    /* renamed from: g */
    public List f7486g;

    /* renamed from: h */
    private final dvz f7487h;

    public dwg(hpq hpq, dvz dvz, Function function) {
        this.f7482c = hpq;
        this.f7487h = dvz;
        this.f7483d = function;
    }

    /* renamed from: a */
    public final int mo221a(int i) {
        return 1;
    }

    /* renamed from: a */
    public final void mo4519a(RecyclerView recyclerView) {
    }

    /* renamed from: a */
    public final int mo220a() {
        List list = this.f7486g;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo4521a(C0667ym ymVar, int i, List list) {
        dwf dwf = (dwf) ymVar;
        for (Object next : list) {
            if (next instanceof dik) {
                int i2 = dwf.f7480q;
                dvz.m6779a(dwf.f7481p, (dik) next);
                return;
            }
        }
        mo223a(dwf, i);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public final void mo223a(dwf dwf, int i) {
        dik dik;
        dwv dwv = (dwv) this.f7486g.get(i);
        dvz dvz = this.f7487h;
        int i2 = dwf.f7480q;
        dvy dvy = dwf.f7481p;
        dvy.mo2635n().mo4518a(dvz.f7468b);
        dwa a = dvy.mo2635n();
        a.f7472a = dwv.mo4478b();
        dwu dwu = dwu.MEDIA;
        int ordinal = dwv.mo4478b().ordinal();
        if (ordinal == 0) {
            a.f7475d.setVisibility(0);
            dxu a2 = a.f7475d.mo2635n();
            a2.f7597l = dwv.mo4490e();
            a2.f7595j.f7839a.add(a2.f7603r);
            if (a2.f7595j.f7841c.equals(ebd.FAST)) {
                a2.f7601p = true;
                a2.mo4549c().mo1422a(a2.f7593h);
            } else {
                a2.f7601p = false;
                a2.mo4550d().mo1422a(a2.f7593h);
            }
            a2.mo4548b();
            a2.f7587b.mo3758a((cqg) a2.f7591f.mo2635n());
            if (a2.f7587b.mo3764d()) {
                a2.mo4547a(false);
            }
            a2.mo4551e();
            a2.f7588c.f5410b.add(a2.f7591f.mo2635n());
            a2.mo3751a(a2.f7588c.f5409a);
        } else if (ordinal == 1) {
            a.f7475d.setVisibility(0);
            dxu a3 = a.f7475d.mo2635n();
            a3.f7593h.setImageDrawable(a3.f7589d.getDrawable(R.color.photosgo_placeholder_background_color));
        } else if (ordinal != 2) {
            int i3 = 3;
            if (ordinal == 3) {
                a.f7477f.setVisibility(0);
            } else if (ordinal == 4) {
                if (a.f7473b == null) {
                    a.f7473b = (PhotosPromoView) ((ViewStub) a.f7478g.findViewById(R.id.promoStub)).inflate();
                }
                a.f7473b.setVisibility(0);
                dxk c = a.f7473b.mo2635n();
                dzf g = dwv.mo4499g();
                c.f7569a.setText(g.mo4605a());
                c.f7570b.setText(g.mo4606b());
                c.f7571c.setText(g.mo4607c());
                c.f7573e = g.mo4608d();
                int e = g.mo4609e();
                if (e != 0) {
                    if (e != 1) {
                        i3 = 1;
                    }
                    iih iih = bks.f3074a;
                    iir g2 = bkg.f2999d.mo8793g();
                    iir g3 = bkf.f2995c.mo8793g();
                    if (g3.f14319c) {
                        g3.mo8751b();
                        g3.f14319c = false;
                    }
                    bkf bkf = (bkf) g3.f14318b;
                    bkf.f2998b = i3 - 1;
                    bkf.f2997a = 1 | bkf.f2997a;
                    if (g2.f14319c) {
                        g2.mo8751b();
                        g2.f14319c = false;
                    }
                    bkg bkg = (bkg) g2.f14318b;
                    bkf bkf2 = (bkf) g3.mo8770g();
                    bkf2.getClass();
                    bkg.f3003c = bkf2;
                    bkg.f3001a |= 2;
                    ((fea) ((fea) c.f7572d.f9364c.mo5563a(75041).mo5512a(fdm.m8618a(iih, (bkg) g2.mo8770g()))).mo5513a(ffh.f9451a)).mo5560a((View) c.f7571c);
                } else {
                    throw null;
                }
            } else if (ordinal == 5) {
                if (a.f7474c == null) {
                    a.f7474c = (CategoryListView) ((ViewStub) a.f7478g.findViewById(R.id.category_list_stub)).inflate();
                }
                a.f7474c.setVisibility(0);
                bnu a4 = a.f7474c.mo2635n();
                List a5 = dwv.mo4477a();
                List list = a4.f3232f.f3225b;
                if (list != null) {
                    a5 = list;
                }
                a4.mo2622a(a5);
                Parcelable parcelable = a4.f3232f.f3226c;
                if (parcelable != null) {
                    a4.f3231e.mo10463a(parcelable);
                }
            } else {
                String valueOf = String.valueOf(dwv.mo4478b());
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 34);
                sb.append("Kind ");
                sb.append(valueOf);
                sb.append(" isn't a valid PhotoGridItem.");
                throw new IllegalArgumentException(sb.toString());
            }
        } else {
            a.f7476e.setVisibility(0);
            a.f7476e.mo2635n().f7464a.setText(dwv.mo4482c().f7463b);
        }
        if (dwv.mo4529h() && (dwv.mo4490e().f5909a & 65536) != 0 && (dik = (dik) this.f7485f.get(dwv.mo4490e().f5926r)) != null) {
            dvz.m6779a(dwf.f7481p, dik);
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ C0667ym mo222a(ViewGroup viewGroup, int i) {
        return new dwf(new dvy(this.f7487h.f7467a));
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo4520a(C0667ym ymVar) {
        CategoryListView categoryListView;
        int i = dwf.f7480q;
        dwa a = ((dwf) ymVar).f7481p.mo2635n();
        dwu dwu = dwu.MEDIA;
        int ordinal = a.f7472a.ordinal();
        if (ordinal == 0) {
            dxu a2 = a.f7475d.mo2635n();
            a2.f7592g.mo7311a(a2.f7593h);
            ebf ebf = a2.f7595j;
            ebf.f7839a.remove(a2.f7603r);
            a2.f7587b.mo3761b((cqg) a2.f7591f.mo2635n());
            CheckBox checkBox = a2.f7599n;
            if (checkBox != null) {
                checkBox.setVisibility(8);
            }
            a2.f7594i.setScaleX(1.0f);
            a2.f7594i.setScaleY(1.0f);
            CheckBox checkBox2 = a2.f7599n;
            if (checkBox2 != null) {
                checkBox2.setChecked(false);
            }
            cqc cqc = a2.f7588c;
            cqc.f5410b.remove(a2.f7591f.mo2635n());
            TextView textView = a2.f7600o;
            if (textView != null) {
                textView.setVisibility(8);
            }
            a2.f7601p = false;
            a2.f7597l = null;
            a2.f7598m = null;
            a.f7475d.setVisibility(8);
        } else if (ordinal == 1) {
            a.f7475d.setVisibility(8);
        } else if (ordinal == 2) {
            a.f7476e.setVisibility(8);
        } else if (ordinal == 3) {
            a.f7477f.setVisibility(8);
        } else if (ordinal == 4) {
            PhotosPromoView photosPromoView = a.f7473b;
            if (photosPromoView != null) {
                fee.m8692a(photosPromoView.mo2635n().f7571c);
                photosPromoView.setVisibility(8);
            }
        } else if (ordinal == 5 && (categoryListView = a.f7474c) != null) {
            bnu a3 = categoryListView.mo2635n();
            a3.f3232f.f3226c = a3.f3231e.mo10476h();
            a3.f3228b.mo7129a((List) null);
            a3.f3230d.mo3761b((cqg) a3);
            a3.f3232f.f3224a = null;
            categoryListView.setVisibility(8);
        }
    }
}
