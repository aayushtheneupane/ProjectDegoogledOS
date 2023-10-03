package p000;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.api.Status;

@Deprecated
/* renamed from: fam */
/* compiled from: PG */
public final class fam implements ezy {

    /* renamed from: a */
    private static final Status f9242a = new Status(0);

    /* renamed from: b */
    private final ekr f9243b;

    public /* synthetic */ fam(Context context) {
        this.f9243b = new ekr(context);
    }

    /* renamed from: a */
    public final eyn mo5422a(fae fae) {
        ekr ekr = this.f9243b;
        esi c = m8469c(fae);
        try {
            if (((Boolean) esx.f8962a.mo5369a()).booleanValue()) {
                ekr.mo4946b(new esf(c));
                return ezb.m8403a(eov.m7948a(f9242a));
            }
        } catch (SecurityException e) {
            Log.e("fb_FeedbackClient", e.getMessage());
        }
        C0652xy.m16066a(esd.m8092b(ekr.f8489f, c));
        return ezb.m8403a(eov.m7948a(f9242a));
    }

    /* renamed from: b */
    public final eyn mo5423b(fae fae) {
        ekr ekr = this.f9243b;
        esi c = m8469c(fae);
        try {
            if (!((Boolean) esx.f8963b.mo5369a()).booleanValue()) {
                C0652xy.m16066a(esd.m8090a(ekr.f8489f, c));
                return ezb.m8403a(eov.m7948a(f9242a));
            }
            long nanoTime = System.nanoTime();
            Context a = ekr.f8489f.mo4947a();
            eop b = eoq.m7942b();
            b.f8722a = new ese(c, nanoTime, a);
            b.f8723b = new ejt[]{erw.f8884b};
            ekr.mo4946b(b.mo5090a());
            return ezb.m8403a(eov.m7948a(f9242a));
        } catch (SecurityException e) {
            Log.e("fb_FeedbackClient", e.getMessage());
        }
    }

    /* renamed from: c */
    private static final esi m8469c(fae fae) {
        return ((far) fae).f9247a;
    }
}
