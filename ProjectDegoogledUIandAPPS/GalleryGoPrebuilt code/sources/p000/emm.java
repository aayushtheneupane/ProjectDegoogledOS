package p000;

import android.os.Looper;
import java.lang.ref.WeakReference;

/* renamed from: emm */
/* compiled from: PG */
final class emm implements epc {

    /* renamed from: a */
    public final boolean f8559a;

    /* renamed from: b */
    private final WeakReference f8560b;

    /* renamed from: c */
    private final ekn f8561c;

    public emm(emv emv, ekn ekn, boolean z) {
        this.f8560b = new WeakReference(emv);
        this.f8561c = ekn;
        this.f8559a = z;
    }

    /* renamed from: a */
    public final void mo5018a(ejq ejq) {
        emv emv = (emv) this.f8560b.get();
        if (emv != null) {
            abj.m108a(Looper.myLooper() == emv.f8574a.f8639l.f8607g, (Object) "onReportServiceBinding must be called on the GoogleApiClient handler thread");
            emv.f8575b.lock();
            try {
                if (emv.mo5025b(0)) {
                    if (!ejq.mo4895b()) {
                        emv.mo5024b(ejq, this.f8561c, this.f8559a);
                    }
                    if (emv.mo5026d()) {
                        emv.mo5027e();
                    }
                }
            } finally {
                emv.f8575b.unlock();
            }
        }
    }
}
