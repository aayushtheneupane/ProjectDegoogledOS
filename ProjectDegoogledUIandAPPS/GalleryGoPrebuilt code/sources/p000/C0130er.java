package p000;

import android.util.Log;

/* renamed from: er */
/* compiled from: PG */
final class C0130er implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ Object f8862a;

    /* renamed from: b */
    private final /* synthetic */ Object f8863b;

    public C0130er(Object obj, Object obj2) {
        this.f8862a = obj;
        this.f8863b = obj2;
    }

    public final void run() {
        try {
            if (C0132et.f8967a != null) {
                C0132et.f8967a.invoke(this.f8862a, new Object[]{this.f8863b, false, "AppCompat recreation"});
                return;
            }
            C0132et.f8968b.invoke(this.f8862a, new Object[]{this.f8863b, false});
        } catch (RuntimeException e) {
            if (e.getClass() == RuntimeException.class && e.getMessage() != null && e.getMessage().startsWith("Unable to stop")) {
                throw e;
            }
        } catch (Throwable th) {
            Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
        }
    }
}
