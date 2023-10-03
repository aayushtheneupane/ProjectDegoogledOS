package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: gvs */
/* compiled from: PG */
final /* synthetic */ class gvs implements Runnable {

    /* renamed from: a */
    private final gvt f12153a;

    public gvs(gvt gvt) {
        this.f12153a = gvt;
    }

    public final void run() {
        boolean z;
        gvt gvt = this.f12153a;
        guu guu = (guu) gvt.f12154a.f12159e.getAndSet((Object) null);
        gvu gvu = gvt.f12154a;
        guz guz = gvu.f12161g;
        long a = gvu.f12155a.mo5370a();
        gtx gtx = (gtx) guz;
        if (gtx.f12050c != RecyclerView.FOREVER_NS) {
            z = true;
        } else {
            z = false;
        }
        ife.m12876b(z, (Object) "You've just overflowed a long. Consider upgrading to a BigDecimal, if this happens more than once.");
        gud gud = gtx.f12048a;
        gvu.f12161g = new gtx(gud, gtx.f12049b, gtx.f12050c, gtx.f12052e, gtx.f12051d.mo7105a(gud, a));
        if (guu.LOCAL_STATE_CHANGE.equals(guu)) {
            gvu gvu2 = gvt.f12154a;
            gvu2.mo7125b(((gtx) gvu2.f12161g).f12051d);
        } else if (guu.REMOTE_STATE_CHANGE.equals(guu)) {
            gvu gvu3 = gvt.f12154a;
            gvu3.mo7121a(((gtx) gvu3.f12161g).f12051d);
        } else {
            String valueOf = String.valueOf(guu);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 17);
            sb.append("Invalidation was ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }
}
