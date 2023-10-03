package p000;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.utils.ForceStopRunnable$BroadcastReceiver;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* renamed from: ama */
/* compiled from: PG */
public final class ama implements Runnable {

    /* renamed from: a */
    private static final long f751a = TimeUnit.DAYS.toMillis(3650);

    /* renamed from: b */
    private final Context f752b;

    /* renamed from: c */
    private final aip f753c;

    static {
        iol.m14236b("ForceStopRunnable");
    }

    public ama(Context context, aip aip) {
        this.f752b = context.getApplicationContext();
        this.f753c = aip;
    }

    /* renamed from: a */
    private static PendingIntent m756a(Context context, int i) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, ForceStopRunnable$BroadcastReceiver.class));
        intent.setAction("ACTION_FORCE_STOP_RESCHEDULE");
        return PendingIntent.getBroadcast(context, -1, intent, i);
    }

    /* JADX INFO: finally extract failed */
    public final void run() {
        Context context = this.f752b;
        File a = ain.m545a(context);
        int i = Build.VERSION.SDK_INT;
        if (a.exists()) {
            iol.m14231a();
            HashMap hashMap = new HashMap();
            int i2 = Build.VERSION.SDK_INT;
            File a2 = ain.m545a(context);
            File b = ain.m546b(context);
            hashMap.put(a2, b);
            for (String str : ain.f547b) {
                hashMap.put(new File(a2.getPath() + str), new File(b.getPath() + str));
            }
            for (File file : hashMap.keySet()) {
                File file2 = (File) hashMap.get(file);
                if (file.exists() && file2 != null) {
                    if (file2.exists()) {
                        String format = String.format("Over-writing contents of %s", new Object[]{file2});
                        iol.m14231a();
                        Log.w(ain.f546a, format);
                    }
                    if (file.renameTo(file2)) {
                        String.format("Migrated %s to %s", new Object[]{file, file2});
                    } else {
                        String.format("Renaming %s to %s failed", new Object[]{file, file2});
                    }
                    iol.m14231a();
                }
            }
        }
        iol.m14231a();
        int i3 = Build.VERSION.SDK_INT;
        ajh.m601b(this.f752b);
        WorkDatabase workDatabase = this.f753c.f554c;
        alh j = workDatabase.mo1226j();
        ala o = workDatabase.mo1231o();
        workDatabase.mo2845e();
        try {
            List<alg> c = j.mo611c();
            boolean z = !c.isEmpty();
            if (z) {
                for (alg alg : c) {
                    j.mo603a(1, alg.f713b);
                    j.mo610b(alg.f713b, -1);
                }
            }
            o.mo590a();
            workDatabase.mo2847g();
            workDatabase.mo2846f();
            Long a3 = this.f753c.f558g.f755a.mo1232p().mo582a("reschedule_needed");
            if (a3 != null && a3.longValue() == 1) {
                iol.m14231a();
                this.f753c.mo522a();
                this.f753c.f558g.f755a.mo1232p().mo583a(new akn("reschedule_needed"));
            } else if (m756a(this.f752b, 536870912) == null) {
                m757a(this.f752b);
                iol.m14231a();
                this.f753c.mo522a();
            } else if (z) {
                iol.m14231a();
                aip aip = this.f753c;
                aib.m533a(aip.f554c, aip.f556e);
            }
            aip aip2 = this.f753c;
            synchronized (aip.f549j) {
                aip2.f559h = true;
                BroadcastReceiver.PendingResult pendingResult = aip2.f560i;
                if (pendingResult != null) {
                    pendingResult.finish();
                    aip2.f560i = null;
                }
            }
        } catch (Throwable th) {
            workDatabase.mo2846f();
            throw th;
        }
    }

    /* renamed from: a */
    public static void m757a(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent a = m756a(context, 134217728);
        long currentTimeMillis = System.currentTimeMillis() + f751a;
        if (alarmManager != null) {
            int i = Build.VERSION.SDK_INT;
            alarmManager.setExact(0, currentTimeMillis, a);
        }
    }
}
