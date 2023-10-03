package p000;

import android.graphics.Bitmap;

/* renamed from: cpb */
/* compiled from: PG */
final /* synthetic */ class cpb implements icf {

    /* renamed from: a */
    private final cpd f5340a;

    public cpb(cpd cpd) {
        this.f5340a = cpd;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        Bitmap bitmap = (Bitmap) obj;
        coq coq = this.f5340a.f5345d;
        icf icf = cpc.f5341a;
        hlj a = hnb.m11765a("Process FAST_GRID_THUMBNAIL");
        try {
            cnr cnr = coq.f5321b;
            bdx o = coq.f5320a.mo3295a().mo1876o();
            hdt hdt = cnr.f4740a;
            ieh a2 = a.mo7548a(cof.m4688a(hdt, hdt.mo7307a().mo1414a(bitmap).mo1426b(o), icf, cnr.f4741b));
            if (a != null) {
                a.close();
            }
            return a2;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
