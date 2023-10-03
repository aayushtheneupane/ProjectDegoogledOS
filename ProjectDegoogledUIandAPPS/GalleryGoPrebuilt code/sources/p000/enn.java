package p000;

import android.util.Log;
import com.google.android.gms.common.api.Status;

/* renamed from: enn */
/* compiled from: PG */
final class enn implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ejq f8663a;

    /* renamed from: b */
    private final /* synthetic */ eno f8664b;

    public enn(eno eno, ejq ejq) {
        this.f8664b = eno;
        this.f8663a = ejq;
    }

    public final void run() {
        eno eno = this.f8664b;
        enp enp = eno.f8670f;
        Status status = enp.f8671a;
        enl enl = (enl) enp.f8681j.get(eno.f8666b);
        if (enl == null) {
            return;
        }
        if (this.f8663a.mo4895b()) {
            eno eno2 = this.f8664b;
            eno2.f8669e = true;
            if (!eno2.f8665a.mo4934g()) {
                try {
                    ekm ekm = this.f8664b.f8665a;
                    ekm.mo4929a((eqg) null, ekm.mo4936i());
                } catch (SecurityException e) {
                    Log.e("GoogleApiManager", "Failed to get service from broker. ", e);
                    enl.mo4994a(new ejq(10));
                }
            } else {
                this.f8664b.mo5056a();
            }
        } else {
            enl.mo4994a(this.f8663a);
        }
    }
}
