package p000;

import android.view.View;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.editor.videotrimming.fragment.VideoTrimView;

/* renamed from: cdh */
/* compiled from: PG */
public final class cdh implements cnq, cnd {

    /* renamed from: a */
    public final cnh f4102a;

    /* renamed from: b */
    public final ccm f4103b;

    /* renamed from: c */
    public final cbx f4104c;

    /* renamed from: d */
    public final hlz f4105d;

    /* renamed from: e */
    public final hnw f4106e;

    /* renamed from: f */
    public final ccy f4107f;

    /* renamed from: g */
    public final cdz f4108g;

    /* renamed from: h */
    public final dtm f4109h;

    /* renamed from: i */
    public final blu f4110i;

    /* renamed from: j */
    public final gvi f4111j;

    /* renamed from: k */
    public final cdm f4112k;

    /* renamed from: l */
    public final cdg f4113l = new cdg(this);

    /* renamed from: m */
    public final fee f4114m;

    /* renamed from: n */
    public dtl f4115n = null;

    /* renamed from: o */
    public cxi f4116o = null;

    /* renamed from: p */
    public final gvc f4117p = new cdf(this);

    /* renamed from: q */
    private final egu f4118q;

    public cdh(cnh cnh, ccm ccm, cbx cbx, hlz hlz, hnw hnw, ccy ccy, cdz cdz, dtm dtm, blu blu, gvi gvi, gus gus, egu egu, fee fee) {
        this.f4102a = cnh;
        this.f4103b = ccm;
        this.f4104c = cbx;
        this.f4105d = hlz;
        this.f4106e = hnw;
        this.f4107f = ccy;
        this.f4108g = cdz;
        this.f4109h = dtm;
        this.f4110i = blu;
        this.f4111j = gvi;
        this.f4118q = egu;
        this.f4114m = fee;
        this.f4112k = new ccz(ccy, gus);
    }

    /* renamed from: a */
    public final int mo3046a() {
        return 1;
    }

    /* renamed from: b */
    public final hom mo3047b() {
        if (this.f4104c.f4035d) {
            ihg.m13041a((hoi) cce.m4042b(), (C0147fh) this.f4103b);
        }
        return hom.f13155a;
    }

    /* renamed from: d */
    public final cef mo3049d() {
        View view = this.f4103b.f9573L;
        if (view != null) {
            return ((VideoTrimView) ife.m12898e((Object) (VideoTrimView) view.findViewById(R.id.video_trim_view))).mo2635n();
        }
        throw new IllegalStateException("VideoEditorFragmentPeer: fragment does not exist");
    }

    /* renamed from: h */
    public final void mo2815h() {
        dtl dtl = this.f4115n;
        if (dtl != null && dtl.mo4413h()) {
            dtl dtl2 = this.f4115n;
            dtl2.mo4403a(dtl2.mo4421p());
        }
    }

    /* renamed from: i */
    public final void mo2816i() {
        dtl dtl = this.f4115n;
        if (dtl != null && dtl.mo4415j()) {
            this.f4115n.mo4416k();
        }
    }

    /* renamed from: e */
    public final void mo2812e() {
        this.f4118q.mo4803b();
    }

    /* renamed from: c */
    public final void mo3048c() {
        if (this.f4103b.mo5659r().mo6418a("VIDEO_TYPE_UNSUPPORTED_DIALOG") == null) {
            cei cei = new cei();
            ftr.m9611b(cei);
            ftr.m9610a((C0147fh) cei);
            cei.mo5429b(this.f4103b.mo5659r(), "VIDEO_TYPE_UNSUPPORTED_DIALOG");
        }
    }
}
