package p000;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: ifa */
/* compiled from: PG */
final class ifa extends idp {

    /* renamed from: a */
    public ieh f13988a;

    /* renamed from: f */
    public ScheduledFuture f13989f;

    public ifa(ieh ieh) {
        this.f13988a = (ieh) ife.m12898e((Object) ieh);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo6947b() {
        mo8345a((Future) this.f13988a);
        ScheduledFuture scheduledFuture = this.f13989f;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.f13988a = null;
        this.f13989f = null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo6386a() {
        ieh ieh = this.f13988a;
        ScheduledFuture scheduledFuture = this.f13989f;
        if (ieh == null) {
            return null;
        }
        String valueOf = String.valueOf(ieh);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14);
        sb.append("inputFuture=[");
        sb.append(valueOf);
        sb.append("]");
        String sb2 = sb.toString();
        if (scheduledFuture == null) {
            return sb2;
        }
        long delay = scheduledFuture.getDelay(TimeUnit.MILLISECONDS);
        if (delay <= 0) {
            return sb2;
        }
        String valueOf2 = String.valueOf(sb2);
        StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf2).length() + 43);
        sb3.append(valueOf2);
        sb3.append(", remaining delay=[");
        sb3.append(delay);
        sb3.append(" ms]");
        return sb3.toString();
    }
}
