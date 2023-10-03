package p000;

import android.app.Activity;
import java.util.concurrent.TimeUnit;

/* renamed from: fjo */
/* compiled from: PG */
final class fjo implements fia {

    /* renamed from: a */
    public final /* synthetic */ fjq f9825a;

    public fjo(fjq fjq) {
        this.f9825a = fjq;
    }

    /* renamed from: a */
    public final void mo5745a(Activity activity) {
        String simpleName = activity.getClass().getSimpleName();
        this.f9825a.f9827b.mo5882a(4, simpleName);
        this.f9825a.mo5883a();
        fjq fjq = this.f9825a;
        fjq.f9828c = ((iel) fjq.f9830e.mo2652a()).mo5935a((Runnable) new fjn(this, simpleName), 10, TimeUnit.SECONDS);
    }
}
