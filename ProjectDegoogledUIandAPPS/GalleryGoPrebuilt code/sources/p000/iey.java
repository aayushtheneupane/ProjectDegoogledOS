package p000;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: iey */
/* compiled from: PG */
final class iey implements Runnable {

    /* renamed from: a */
    private ifa f13982a;

    public iey(ifa ifa) {
        this.f13982a = ifa;
    }

    public final void run() {
        ieh ieh;
        ifa ifa = this.f13982a;
        if (ifa != null && (ieh = ifa.f13988a) != null) {
            this.f13982a = null;
            if (!ieh.isDone()) {
                try {
                    ScheduledFuture scheduledFuture = ifa.f13989f;
                    ifa.f13989f = null;
                    String str = "Timed out";
                    if (scheduledFuture != null) {
                        try {
                            long abs = Math.abs(scheduledFuture.getDelay(TimeUnit.MILLISECONDS));
                            if (abs > 10) {
                                StringBuilder sb = new StringBuilder(75);
                                sb.append(str);
                                sb.append(" (timeout delayed by ");
                                sb.append(abs);
                                sb.append(" ms after scheduled time)");
                                str = sb.toString();
                            }
                        } catch (Throwable th) {
                            th = th;
                            ifa.mo6952a((Throwable) new iez(str));
                            throw th;
                        }
                    }
                    try {
                        String valueOf = String.valueOf(str);
                        String valueOf2 = String.valueOf(ieh);
                        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 2 + String.valueOf(valueOf2).length());
                        sb2.append(valueOf);
                        sb2.append(": ");
                        sb2.append(valueOf2);
                        ifa.mo6952a((Throwable) new iez(sb2.toString()));
                    } catch (Throwable th2) {
                        th = th2;
                        ifa.mo6952a((Throwable) new iez(str));
                        throw th;
                    }
                } finally {
                    ieh.cancel(true);
                }
            } else {
                ifa.mo6946a(ieh);
            }
        }
    }
}
