package p000;

import com.google.android.apps.photosgo.R;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: ccy */
/* compiled from: PG */
public final class ccy implements gud {

    /* renamed from: a */
    public final Object f4077a = new Object();

    /* renamed from: b */
    public final cby f4078b;

    /* renamed from: c */
    public final ccd f4079c;

    /* renamed from: d */
    public final iek f4080d;

    /* renamed from: e */
    public final Object f4081e;

    /* renamed from: f */
    public final int f4082f;

    /* renamed from: g */
    public int f4083g = 0;

    /* renamed from: h */
    public ccn f4084h = null;

    /* renamed from: i */
    private final cbx f4085i;

    /* renamed from: j */
    private final cfb f4086j;

    /* renamed from: k */
    private final exm f4087k;

    /* renamed from: l */
    private final gus f4088l;

    /* renamed from: m */
    private ieh f4089m;

    public ccy(C0147fh fhVar, iek iek, cbx cbx, cfb cfb, cby cby, ccd ccd, exm exm, gus gus) {
        Object obj;
        this.f4080d = iek;
        this.f4085i = cbx;
        this.f4082f = Math.round(fhVar.mo2634k().getResources().getDimension(R.dimen.photosgo_videotrimming_trimview_thumbnail_height));
        this.f4086j = cfb;
        this.f4078b = cby;
        this.f4079c = ccd;
        this.f4087k = exm;
        this.f4088l = gus;
        this.f4089m = ife.m12820a((Object) null);
        int b = cdu.m4147b(cbx.f4033b);
        int i = b - 1;
        if (b != 0) {
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        String a = cdu.m4144a(cdu.m4147b(cbx.f4033b));
                        StringBuilder sb = new StringBuilder(a.length() + 21);
                        sb.append("Unsupported TypeCase ");
                        sb.append(a);
                        throw new IllegalArgumentException(sb.toString());
                    }
                    throw new IllegalArgumentException("TypeCase not set");
                } else if (cbx.f4033b == 4) {
                    obj = (ceq) cbx.f4034c;
                } else {
                    obj = ceq.f4197g;
                }
            } else if (cbx.f4033b == 1) {
                obj = (cxi) cbx.f4034c;
            } else {
                obj = cxi.f5908x;
            }
            this.f4081e = obj;
            return;
        }
        throw null;
    }

    /* renamed from: c */
    public final Object mo2735c() {
        return this.f4081e;
    }

    /* renamed from: a */
    public final ieh mo2733a() {
        Object obj;
        ieh ieh;
        ieh a;
        ieh a2;
        ieh ieh2;
        ieh a3;
        cxi cxi;
        ceq ceq;
        synchronized (this.f4077a) {
            this.f4080d.mo5933a(hmq.m11749a((Callable) new ccp(this.f4089m)));
            ccn ccn = this.f4084h;
            if (ccn == null) {
                obj = cco.m4083d().mo3036a();
            } else {
                obj = ccn.mo3036a();
            }
            this.f4084h = cco.m4083d();
            ccf ccf = (ccf) obj;
            Optional optional = ((ccf) obj).f4046a;
            if (optional.isPresent()) {
                ieh = ife.m12820a((Object) (iag) optional.get());
            } else {
                int b = cdu.m4147b(this.f4085i.f4033b);
                int i = b - 1;
                if (b == 0) {
                    throw null;
                } else if (i == 0) {
                    cbx cbx = this.f4085i;
                    if (cbx.f4033b == 1) {
                        cxi = (cxi) cbx.f4034c;
                    } else {
                        cxi = cxi.f5908x;
                    }
                    ieh = iag.m12576a(ife.m12820a((Object) cxi));
                } else if (i == 1) {
                    cfb cfb = this.f4086j;
                    cbx cbx2 = this.f4085i;
                    if (cbx2.f4033b == 4) {
                        ceq = (ceq) cbx2.f4034c;
                    } else {
                        ceq = ceq.f4197g;
                    }
                    ieh = iag.m12576a(gte.m10770a(cfb.mo3090a(ceq), cct.f4072a, (Executor) this.f4080d));
                } else if (i != 2) {
                    String a4 = cdu.m4144a(cdu.m4147b(this.f4085i.f4033b));
                    StringBuilder sb = new StringBuilder(a4.length() + 21);
                    sb.append("Unsupported TypeCase ");
                    sb.append(a4);
                    ieh = ife.m12820a((Object) iad.m12566a(new IllegalArgumentException(sb.toString())));
                } else {
                    ieh = ife.m12820a((Object) iad.m12566a(new IllegalArgumentException("TypeCase not set")));
                }
            }
            a = gte.m10770a(ieh, (hpr) new ccq(this), (Executor) this.f4080d);
            a2 = gte.m10770a(this.f4083g != 0 ? gte.m10771a(a, (icf) new ccu(this), (Executor) this.f4080d) : ife.m12820a((Object) new ArrayList()), (hpr) new ccr(this), (Executor) this.f4080d);
            ccf ccf2 = (ccf) obj;
            Optional optional2 = ((ccf) obj).f4047b;
            if (!optional2.isPresent()) {
                ieh2 = gte.m10771a(a, (icf) new ccv(this), (Executor) this.f4080d);
            } else {
                ieh2 = ife.m12820a((Object) (Boolean) optional2.get());
            }
            a3 = gte.m10770a(ieh2, (hpr) new ccs(this), (Executor) this.f4080d);
            this.f4089m = ife.m12823a(a, a2, a3);
        }
        this.f4088l.mo7096a(a, this.f4081e);
        this.f4088l.mo7096a(a2, this.f4081e);
        this.f4088l.mo7096a(a3, this.f4081e);
        cwn.m5509a(a, "VideoEditorFragmentDataSource: Unable to fetch the media.", new Object[0]);
        cwn.m5509a(a3, "VideoEditorFragmentDataSource: Unable to type check the video.", new Object[0]);
        cwn.m5509a(a2, "VideoEditorFragmentDataSource: Unable to get thumbnails of the video.", new Object[0]);
        return ife.m12820a((Object) null);
    }

    /* renamed from: b */
    public final gpc mo2734b() {
        synchronized (this.f4077a) {
            ccn ccn = this.f4084h;
            if (ccn != null) {
                gpc a = gpc.m10579a((Object) guc.m10815a(ccn.mo3036a(), this.f4087k.mo5370a()));
                return a;
            }
            gpc a2 = gpc.m10579a((Object) guc.f12067a);
            return a2;
        }
    }
}
