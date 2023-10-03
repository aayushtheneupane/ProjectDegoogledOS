package p000;

import android.content.Context;
import android.os.StrictMode;

/* renamed from: hpc */
/* compiled from: PG */
final /* synthetic */ class hpc implements hbc {

    /* renamed from: a */
    private final Context f13208a;

    public hpc(Context context) {
        this.f13208a = context;
    }

    /* renamed from: a */
    public final void mo7253a() {
        Context context = this.f13208a;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            hpy a = hoy.f13186a.mo7634a(context, iij.m13502b());
            Integer num = null;
            if (a.mo7646a()) {
                hox hox = (hox) a.mo7647b();
                if ((hox.f13181a & 4) != 0) {
                    num = Integer.valueOf(hox.f13184d);
                }
            }
            if (num != null) {
                C0397om.m14923d(num.intValue());
            }
        } catch (Exception e) {
            ((hvv) ((hvv) ((hvv) hpd.f13209a.mo8178a()).mo8202a((Throwable) e)).mo8201a("com/google/apps/tiktok/ui/locale/NightModeModule", "lambda$provideLocaleErrorLoggingListener$0", 43, "NightModeModule.java")).mo8203a("Failed to set default night mode");
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            throw th;
        }
        StrictMode.setThreadPolicy(allowThreadDiskReads);
    }
}
