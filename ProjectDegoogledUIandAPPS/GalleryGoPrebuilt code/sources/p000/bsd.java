package p000;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.devicefolders.PromoView;

/* renamed from: bsd */
/* compiled from: PG */
final class bsd extends gwe {

    /* renamed from: a */
    public brm f3479a = brm.SIZE_AND_NUMBER;

    /* renamed from: b */
    private final hbl f3480b;

    public /* synthetic */ bsd(hbl hbl) {
        this.f3480b = hbl;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2643a(View view, Object obj) {
        Object obj2;
        String str;
        bsb bsb = (bsb) obj;
        bse a = ((bsc) view).mo2635n();
        brm brm = this.f3479a;
        a.f3484d = bsb.mo2689b();
        int b = bsb.mo2689b();
        int i = b - 1;
        if (b != 0) {
            int i2 = 0;
            if (i == 0) {
                a.f3482b.setVisibility(0);
                a.f3481a.setVisibility(8);
                PromoView promoView = a.f3483c;
                if (promoView != null) {
                    promoView.setVisibility(8);
                }
            } else if (i == 1) {
                a.f3481a.setVisibility(0);
                bry c = a.f3481a.mo2635n();
                btl a2 = bsb.mo2688a();
                cxi cxi = (cxi) a2.mo2738b().get(0);
                apj a3 = c.f3467b.mo7307a();
                if (cxi.f5912d) {
                    obj2 = ebh.m7086a(cxi.f5910b);
                } else {
                    obj2 = cxi.f5910b;
                }
                cnx cnx = c.f3468c;
                ((apj) a3.mo1419a(obj2).mo1854a((aqu) new bfa(Long.valueOf(cxi.f5918j)))).mo1426b(cnx.f4748a.f4753b.mo1853a(aqj.PREFER_RGB_565).mo1861a(new azn(), new bas(c.f3475j)).mo1851a(cnx.f4750c)).mo1422a(c.f3470e);
                c.f3473h.setText(a2.mo2737a().f5897c);
                TextView textView = c.f3474i;
                brm brm2 = brm.SIZE_AND_NUMBER;
                int ordinal = ((brm) ife.m12869b((Object) brm, (Object) "folderSummaryType cannot be null")).ordinal();
                if (ordinal == 0) {
                    String a4 = c.mo2713a(a2);
                    String d = a2.mo2740d();
                    StringBuilder sb = new StringBuilder(String.valueOf(a4).length() + 3 + String.valueOf(d).length());
                    sb.append(a4);
                    sb.append(" · ");
                    sb.append(d);
                    str = sb.toString();
                } else if (ordinal == 1) {
                    str = c.mo2713a(a2);
                } else {
                    String valueOf = String.valueOf(brm);
                    StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 30);
                    sb2.append("Unsupported FolderSummaryType ");
                    sb2.append(valueOf);
                    throw new IllegalStateException(sb2.toString());
                }
                textView.setText(str);
                int e = a2.mo2741e();
                if (e != 0) {
                    if (e != 2) {
                        i2 = 8;
                    }
                    c.f3471f.setVisibility(i2);
                    c.f3472g.setVisibility(i2);
                    c.f3469d.mo7633a((View) c.f3466a, (hoi) new brx(a2.mo2737a()));
                    a.f3482b.setVisibility(8);
                    PromoView promoView2 = a.f3483c;
                    if (promoView2 != null) {
                        promoView2.setVisibility(8);
                        return;
                    }
                    return;
                }
                throw null;
            } else if (i == 2) {
                a.mo2718a();
                a.f3483c.setVisibility(0);
                bsl c2 = a.f3483c.mo2635n();
                btn d2 = bsb.mo2697d();
                c2.f3488c.setText(d2.mo2745a());
                c2.f3489d.setText(d2.mo2746b());
                c2.f3491f = d2.mo2747c();
                int d3 = d2.mo2748d();
                if (d3 != 0) {
                    int i3 = d3 == 1 ? 2 : 1;
                    iih iih = bks.f3074a;
                    iir g = bkg.f2999d.mo8793g();
                    iir g2 = bkf.f2995c.mo8793g();
                    if (g2.f14319c) {
                        g2.mo8751b();
                        g2.f14319c = false;
                    }
                    bkf bkf = (bkf) g2.f14318b;
                    bkf.f2998b = i3 - 1;
                    bkf.f2997a |= 1;
                    if (g.f14319c) {
                        g.mo8751b();
                        g.f14319c = false;
                    }
                    bkg bkg = (bkg) g.f14318b;
                    bkf bkf2 = (bkf) g2.mo8770g();
                    bkf2.getClass();
                    bkg.f3003c = bkf2;
                    bkg.f3001a |= 2;
                    ((fea) ((fea) c2.f3490e.f9364c.mo5563a(74892).mo5512a(fdm.m8618a(iih, (bkg) g.mo8770g()))).mo5513a(ffh.f9451a)).mo5560a((View) c2.f3489d);
                    c2.f3486a.mo7633a(c2.f3487b.findViewById(R.id.dismiss_button), (hoi) new brj());
                    a.f3481a.setVisibility(8);
                    a.f3482b.setVisibility(8);
                    return;
                }
                throw null;
            } else {
                String b2 = gbz.m9995b(bsb.mo2689b());
                StringBuilder sb3 = new StringBuilder(b2.length() + 36);
                sb3.append("Kind ");
                sb3.append(b2);
                sb3.append(" isn't a valid FoldersGridItem.");
                throw new IllegalArgumentException(sb3.toString());
            }
        } else {
            throw null;
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ View mo2641a(ViewGroup viewGroup) {
        return new bsc(this.f3480b);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2642a(View view) {
        bse a = ((bsc) view).mo2635n();
        int i = a.f3484d;
        int i2 = i - 1;
        if (i == 0) {
            throw null;
        } else if (i2 == 1) {
            bry c = a.f3481a.mo2635n();
            c.f3467b.mo7311a(c.f3470e);
        } else if (i2 == 2) {
            a.mo2718a();
            fee.m8692a(a.f3483c.mo2635n().f3489d);
        }
    }
}
