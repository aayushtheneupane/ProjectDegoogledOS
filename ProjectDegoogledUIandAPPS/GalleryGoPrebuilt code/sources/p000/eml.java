package p000;

import android.app.NotificationManager;
import android.content.Context;

/* renamed from: eml */
/* compiled from: PG */
final class eml implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ emv f8558a;

    public eml(emv emv) {
        this.f8558a = emv;
    }

    public final void run() {
        Context context = this.f8558a.f8576c;
        if (!ekh.f8470b.getAndSet(true)) {
            try {
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                if (notificationManager != null) {
                    notificationManager.cancel(10436);
                }
            } catch (SecurityException e) {
            }
        }
    }
}
