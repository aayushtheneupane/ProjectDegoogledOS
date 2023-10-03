package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/* compiled from: PG */
public class RescheduleReceiver extends BroadcastReceiver {

    /* renamed from: a */
    private static final String f1179a = iol.m14236b("RescheduleReceiver");

    public final void onReceive(Context context, Intent intent) {
        iol.m14231a();
        String.format("Received intent %s", new Object[]{intent});
        int i = Build.VERSION.SDK_INT;
        try {
            aip a = aip.m549a(context);
            BroadcastReceiver.PendingResult goAsync = goAsync();
            synchronized (aip.f549j) {
                a.f560i = goAsync;
                if (a.f559h) {
                    a.f560i.finish();
                    a.f560i = null;
                }
            }
        } catch (IllegalStateException e) {
            iol.m14231a();
            iol.m14234a(f1179a, "Cannot reschedule jobs. WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().", new Throwable[0]);
        }
    }
}
