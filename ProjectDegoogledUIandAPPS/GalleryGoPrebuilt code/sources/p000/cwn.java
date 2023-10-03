package p000;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: cwn */
/* compiled from: PG */
public final class cwn {

    /* renamed from: a */
    private static final hwt f5808a = hwt.m12335a("PhotosGo");

    /* renamed from: b */
    public static void m5514b(String str, Object... objArr) {
        ((hwp) ((hwp) f5808a.mo8178a()).mo8201a("com/google/android/apps/photosgo/log/Phlog", "e", 71, "Phlog.java")).mo8208a(str, objArr);
    }

    /* renamed from: b */
    public static void m5515b(Throwable th, String str, Object... objArr) {
        ((hwp) ((hwp) ((hwp) f5808a.mo8178a()).mo8202a(th)).mo8201a("com/google/android/apps/photosgo/log/Phlog", "e", 77, "Phlog.java")).mo8208a(str, objArr);
    }

    /* renamed from: b */
    static final /* synthetic */ void m5513b(ieh ieh, String str, Object[] objArr) {
        try {
            ife.m12871b((Future) ieh);
        } catch (ExecutionException e) {
            ((hwp) ((hwp) ((hwp) f5808a.mo8178a()).mo8202a(e.getCause())).mo8201a("com/google/android/apps/photosgo/log/Phlog", "lambda$onFailure$0", 90, "Phlog.java")).mo8208a(str, objArr);
        } catch (CancellationException e2) {
        }
    }

    /* renamed from: a */
    public static void m5509a(ieh ieh, String str, Object... objArr) {
        ieh.mo53a(hmq.m11748a((Runnable) new cwm(ieh, str, objArr)), idh.f13918a);
    }

    /* renamed from: a */
    public static void m5510a(String str, Object... objArr) {
        ((hwp) ((hwp) f5808a.mo8180b()).mo8201a("com/google/android/apps/photosgo/log/Phlog", "w", 59, "Phlog.java")).mo8208a(str, objArr);
    }

    /* renamed from: a */
    public static void m5511a(Throwable th, String str, Object... objArr) {
        ((hwp) ((hwp) ((hwp) f5808a.mo8180b()).mo8202a(th)).mo8201a("com/google/android/apps/photosgo/log/Phlog", "w", 65, "Phlog.java")).mo8208a(str, objArr);
    }

    /* renamed from: a */
    public static void m5512a(boolean z, String str, Object... objArr) {
        if (!z) {
            m5510a(str, objArr);
        }
    }
}
