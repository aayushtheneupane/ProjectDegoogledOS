package p000;

import android.app.Activity;
import java.util.concurrent.TimeUnit;

/* renamed from: fjm */
/* compiled from: PG */
final class fjm implements fhz {

    /* renamed from: a */
    public final /* synthetic */ fjq f9822a;

    public fjm(fjq fjq) {
        this.f9822a = fjq;
    }

    /* renamed from: b */
    public final void mo5742b(Activity activity) {
        String simpleName = activity.getClass().getSimpleName();
        this.f9822a.f9827b.mo5882a(3, simpleName);
        this.f9822a.mo5883a();
        fjq fjq = this.f9822a;
        fjq.f9829d = ((iel) fjq.f9830e.mo2652a()).mo5935a((Runnable) new fjl(this, simpleName), 10, TimeUnit.SECONDS);
    }
}
