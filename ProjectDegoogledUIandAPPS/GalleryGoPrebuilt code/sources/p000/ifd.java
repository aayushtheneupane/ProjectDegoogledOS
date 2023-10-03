package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;

/* renamed from: ifd */
/* compiled from: PG */
final class ifd extends idp implements RunnableFuture {

    /* renamed from: a */
    private volatile ieg f13994a;

    private ifd(ice ice) {
        this.f13994a = new ifb(this, ice);
    }

    private ifd(Callable callable) {
        this.f13994a = new ifc(this, callable);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo6947b() {
        ieg ieg;
        if (mo8348d() && (ieg = this.f13994a) != null) {
            ieg.mo8452e();
        }
        this.f13994a = null;
    }

    /* renamed from: a */
    static ifd m12797a(ice ice) {
        return new ifd(ice);
    }

    /* renamed from: a */
    static ifd m12798a(Runnable runnable, Object obj) {
        return new ifd(Executors.callable(runnable, obj));
    }

    /* renamed from: a */
    static ifd m12799a(Callable callable) {
        return new ifd(callable);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo6386a() {
        ieg ieg = this.f13994a;
        if (ieg == null) {
            return super.mo6386a();
        }
        String valueOf = String.valueOf(ieg);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 7);
        sb.append("task=[");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    public final void run() {
        ieg ieg = this.f13994a;
        if (ieg != null) {
            ieg.run();
        }
        this.f13994a = null;
    }
}
