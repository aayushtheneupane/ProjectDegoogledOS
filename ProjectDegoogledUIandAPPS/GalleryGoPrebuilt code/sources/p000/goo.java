package p000;

import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.PowerManager;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* renamed from: goo */
/* compiled from: PG */
public final class goo {

    /* renamed from: a */
    public static final hvy f11748a = hvy.m12261a("com/google/apps/tiktok/concurrent/AndroidFutures");

    /* renamed from: b */
    public final iek f11749b;

    /* renamed from: c */
    public final iel f11750c;

    /* renamed from: d */
    public final gqw f11751d;

    /* renamed from: e */
    public final NotificationManager f11752e;

    /* renamed from: f */
    private final Context f11753f;

    /* renamed from: g */
    private final PowerManager f11754g;

    /* renamed from: h */
    private final iel f11755h;

    /* renamed from: i */
    private boolean f11756i = false;

    public goo(Context context, PowerManager powerManager, NotificationManager notificationManager, iek iek, gqw gqw, iel iel, iel iel2) {
        this.f11753f = context;
        this.f11754g = powerManager;
        this.f11752e = notificationManager;
        this.f11749b = iek;
        this.f11750c = iel;
        this.f11755h = iel2;
        this.f11751d = gqw;
    }

    /* renamed from: a */
    public final ieh mo6881a(ieh ieh) {
        String str;
        hlp a = hnb.m11769a();
        if (a != null) {
            str = hnb.m11780c(a);
        } else {
            str = "<no trace>";
        }
        if (!ieh.isDone()) {
            try {
                PowerManager.WakeLock newWakeLock = this.f11754g.newWakeLock(1, str);
                newWakeLock.acquire();
                TimeUnit timeUnit = TimeUnit.SECONDS;
                ieh a2 = ife.m12817a(ieh);
                ife.m12841a(ife.m12818a(a2, 45, timeUnit, (ScheduledExecutorService) this.f11750c), hmq.m11746a((idw) new gon(a2, str)), (Executor) idh.f13918a);
                ieh a3 = ife.m12818a(ife.m12817a(ieh), 3600, TimeUnit.SECONDS, (ScheduledExecutorService) this.f11755h);
                newWakeLock.getClass();
                a3.mo53a(new goj(newWakeLock), idh.f13918a);
            } catch (SecurityException e) {
                if (!this.f11756i) {
                    try {
                        PackageInfo packageInfo = this.f11753f.getPackageManager().getPackageInfo(this.f11753f.getPackageName(), 4096);
                        if (packageInfo.requestedPermissions != null) {
                            String[] strArr = packageInfo.requestedPermissions;
                            int length = strArr.length;
                            int i = 0;
                            while (i < length) {
                                if (!"android.permission.WAKE_LOCK".equals(strArr[i])) {
                                    i++;
                                } else {
                                    this.f11756i = true;
                                    ((hvv) ((hvv) ((hvv) f11748a.mo8178a()).mo8202a((Throwable) e)).mo8201a("com/google/apps/tiktok/concurrent/AndroidFutures", "checkPermissionRequested", 153, "AndroidFutures.java")).mo8203a("Failed to acquire wakelock");
                                }
                            }
                        }
                    } catch (PackageManager.NameNotFoundException e2) {
                        int i2 = Build.VERSION.SDK_INT;
                        ifn.m12935a((Throwable) e, (Throwable) e2);
                    }
                    throw e;
                }
            }
        }
        return ieh;
    }

    /* renamed from: b */
    static final /* synthetic */ void m10563b(ieh ieh, String str, Object[] objArr) {
        try {
            ife.m12871b((Future) ieh);
        } catch (ExecutionException e) {
            ((hvv) ((hvv) ((hvv) f11748a.mo8178a()).mo8202a(e.getCause())).mo8201a("com/google/apps/tiktok/concurrent/AndroidFutures", "lambda$logOnFailure$3", 358, "AndroidFutures.java")).mo8208a(str, objArr);
        } catch (CancellationException e2) {
        }
    }

    /* renamed from: a */
    public static void m10562a(ieh ieh, String str, Object... objArr) {
        ieh.mo53a(hmq.m11748a((Runnable) new gom(ieh, str, objArr)), idh.f13918a);
    }
}
