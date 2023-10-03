package p000;

import android.net.Uri;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.infosheet.InfoSheetListView;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: csu */
/* compiled from: PG */
public final class csu implements cnq {

    /* renamed from: a */
    public final C0147fh f5598a;

    /* renamed from: b */
    public final blu f5599b;

    /* renamed from: c */
    public final cxi f5600c;

    /* renamed from: d */
    public final fee f5601d;

    /* renamed from: e */
    public InfoSheetListView f5602e;

    /* renamed from: f */
    private final cxy f5603f;

    /* renamed from: g */
    private final gvi f5604g;

    /* renamed from: h */
    private final egu f5605h;

    /* renamed from: i */
    private final Optional f5606i;

    public csu(C0147fh fhVar, cxy cxy, gvi gvi, blu blu, egu egu, Optional optional, cxi cxi, fee fee) {
        this.f5598a = fhVar;
        this.f5603f = cxy;
        this.f5604g = gvi;
        this.f5599b = blu;
        this.f5605h = egu;
        this.f5606i = optional;
        this.f5600c = cxi;
        this.f5601d = fee;
    }

    /* renamed from: h */
    public final void mo2815h() {
    }

    /* renamed from: i */
    public final void mo2816i() {
    }

    /* renamed from: e */
    public final void mo2812e() {
        this.f5605h.mo4804c();
    }

    /* renamed from: a */
    public final void mo3806a() {
        gud gud;
        ieh ieh;
        cxy cxy = this.f5603f;
        cxi cxi = this.f5600c;
        Uri parse = Uri.parse(cxi.f5910b);
        String str = cxi.f5914f;
        if ((str == null || !str.startsWith("image/")) && (str == null || !str.startsWith("video/"))) {
            gud = cxy.f5978d.mo7085a(new cxr(parse), cxy.f5980f);
        } else {
            if (fxk.m9827a(parse)) {
                cxp q = cxq.m5606q();
                hnm a = gte.m10769a(cxy.f5977c.mo7042a(cyc.m5642a(parse), cxy.f5974g, (String) null, (String[]) null, (String) null).mo6895a((hpr) new cxv(parse, q), (Executor) cxy.f5979e), cxy.f5979e.mo5933a(hmq.m11749a((Callable) new cxw(cxy, parse, q))));
                q.getClass();
                ieh = a.mo7613a((Callable) new cxt(q), (Executor) cxy.f5980f);
            } else if ("file".equals(parse.getScheme())) {
                cxp q2 = cxq.m5606q();
                ieh = gte.m10770a(cxy.f5979e.mo5933a(hmq.m11749a((Callable) new cxx(cxy, q2, parse))), (hpr) new cxu(q2), (Executor) cxy.f5980f);
            } else {
                ieh = cxy.m5627a(parse);
            }
            guj guj = cxy.f5978d;
            cxs cxs = new cxs(ieh);
            String valueOf = String.valueOf(parse.toString());
            gud = guj.mo7085a(cxs, valueOf.length() == 0 ? new String("MEDIA_METADATA_DATA_SERVICE:") : "MEDIA_METADATA_DATA_SERVICE:".concat(valueOf));
        }
        this.f5604g.mo7113a(gud, guy.DONT_CARE, new cst(this, this.f5602e));
        InfoSheetListView infoSheetListView = this.f5602e;
        if (this.f5606i.isPresent()) {
            ViewGroup viewGroup = (ViewGroup) infoSheetListView.findViewById(R.id.list_view);
            ((cto) this.f5606i.get()).mo3829a();
        }
    }
}
