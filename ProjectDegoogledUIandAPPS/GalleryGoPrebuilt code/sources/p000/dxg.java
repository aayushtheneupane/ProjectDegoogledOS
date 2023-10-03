package p000;

import android.support.p002v7.widget.RecyclerView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.photogrid.PhotoGridView;
import java.util.List;
import java.util.Map;

/* renamed from: dxg */
/* compiled from: PG */
public final class dxg {

    /* renamed from: a */
    public final cqd f7556a;

    /* renamed from: b */
    public final RecyclerView f7557b;

    /* renamed from: c */
    public final C0598vy f7558c;

    /* renamed from: d */
    public final dxf f7559d;

    /* renamed from: e */
    public List f7560e = hso.m12047f();

    /* renamed from: f */
    public int f7561f;

    /* renamed from: g */
    private final dwg f7562g;

    /* renamed from: h */
    private final hbl f7563h;

    /* renamed from: i */
    private final cps f7564i;

    /* renamed from: j */
    private final dxj f7565j;

    /* renamed from: k */
    private final dvz f7566k;

    public dxg(C0147fh fhVar, PhotoGridView photoGridView, hbl hbl, cps cps, ebf ebf, cjr cjr) {
        this.f7564i = cps;
        this.f7563h = hbl;
        dxa dxa = new dxa(cjr);
        cqd cqd = new cqd(hbl);
        this.f7556a = cqd;
        photoGridView.addView(cqd);
        this.f7557b = this.f7556a.f5411a;
        this.f7566k = new dvz(hbl);
        this.f7562g = new dwg(hpp.f13230a.mo7660a(dxb.f7551a), this.f7566k, dxa);
        int a = mo4539a();
        this.f7561f = a;
        dxf dxf = new dxf(this, a);
        this.f7559d = dxf;
        dxf.mo10420c();
        dwx dwx = new dwx(fhVar.mo2634k(), this.f7561f, this.f7566k, hbl);
        this.f7558c = dwx;
        dwx.f16174b = this.f7559d;
        this.f7557b.setHasFixedSize(true);
        dxj dxj = new dxj(hbl.getResources().getDimensionPixelSize(R.dimen.photogrid_item_margin), this.f7559d);
        this.f7565j = dxj;
        this.f7557b.addItemDecoration(dxj);
        this.f7557b.setItemAnimator(new dxe());
        this.f7557b.setItemViewCacheSize(this.f7561f);
        this.f7557b.setOnScrollListener(ebf);
        this.f7557b.setLayoutManager(this.f7558c);
        this.f7557b.setAdapter(this.f7562g);
    }

    /* renamed from: a */
    public final int mo4539a() {
        return this.f7564i.mo3740a(this.f7563h.getResources().getInteger(R.integer.photos_photogrid_default_column_count));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo4540a(List list, Map map, dxy dxy) {
        this.f7560e = list;
        dwg dwg = this.f7562g;
        fxk.m9830b();
        dxy dxy2 = dwg.f7484e;
        dwg.f7484e = dxy;
        List list2 = dwg.f7486g;
        Map map2 = dwg.f7485f;
        dwg.f7486g = list;
        dwg.f7485f = map;
        if (list2 != null) {
            dxy dxy3 = dwg.f7484e;
            if (dxy3 != dxy2) {
                hpq hpq = dwg.f7482c;
                hpq.getClass();
                gwa.m10928a(new dwe(hpq)).mo2661a(list2, list, dwg.f7482c, dwg);
            } else {
                ((gvx) dwg.f7483d.apply(dxy3)).mo2661a(list2, list, dwg.f7482c, dwg);
            }
            if (!ife.m12891c((Object) map2, (Object) dwg.f7485f) && !dwg.f7485f.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    dwv dwv = (dwv) list.get(i);
                    if (dwv.mo4529h()) {
                        cxi e = dwv.mo4490e();
                        if ((e.f5909a & 65536) != 0) {
                            String str = e.f5926r;
                            dik dik = (dik) dwg.f7485f.get(str);
                            if (!ife.m12891c(map2.get(str), (Object) dik)) {
                                dwg.mo10535a(i, (Object) dik);
                            }
                        }
                    }
                }
            }
        } else {
            dwg.mo10537b(0, list.size());
        }
        this.f7556a.mo3752a();
    }
}
