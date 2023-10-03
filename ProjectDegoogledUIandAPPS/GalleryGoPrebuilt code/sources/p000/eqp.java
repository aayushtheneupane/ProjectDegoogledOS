package p000;

import com.google.android.gms.common.api.Status;
import java.util.concurrent.TimeUnit;

/* renamed from: eqp */
/* compiled from: PG */
public final class eqp implements ekw {

    /* renamed from: a */
    private final /* synthetic */ ekx f8846a;

    /* renamed from: b */
    private final /* synthetic */ exe f8847b;

    public eqp(ekx ekx, exe exe) {
        this.f8846a = ekx;
        this.f8847b = exe;
    }

    /* renamed from: a */
    public final void mo4951a(Status status) {
        if (status.mo3499b()) {
            this.f8846a.mo3510a(TimeUnit.MILLISECONDS);
            this.f8847b.mo5365a((Object) null);
            return;
        }
        this.f8847b.mo5364a((Exception) C0652xy.m16062a(status));
    }
}
