package p000;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import androidx.work.impl.WorkDatabase;

/* renamed from: aiv */
/* compiled from: PG */
final class aiv {
    static {
        iol.m14236b("Alarms");
    }

    /* renamed from: a */
    public static void m571a(Context context, aip aip, String str) {
        aks m = aip.f554c.mo1229m();
        akr a = m.mo586a(str);
        if (a != null) {
            m573a(context, str, a.f697b);
            iol.m14231a();
            String.format("Removing SystemIdInfo for workSpecId (%s)", new Object[]{str});
            m.mo588b(str);
        }
    }

    /* renamed from: a */
    private static void m573a(Context context, String str, int i) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent service = PendingIntent.getService(context, i, aiw.m578b(context, str), 536870912);
        if (service != null && alarmManager != null) {
            iol.m14231a();
            String.format("Cancelling existing alarm with (workSpecId, systemId) (%s, %s)", new Object[]{str, Integer.valueOf(i)});
            alarmManager.cancel(service);
        }
    }

    /* renamed from: a */
    public static void m572a(Context context, aip aip, String str, long j) {
        int a;
        WorkDatabase workDatabase = aip.f554c;
        aks m = workDatabase.mo1229m();
        akr a2 = m.mo586a(str);
        if (a2 != null) {
            m573a(context, str, a2.f697b);
            m574a(context, str, a2.f697b, j);
            return;
        }
        amb amb = new amb(workDatabase);
        synchronized (amb.class) {
            a = amb.mo639a("next_alarm_manager_id");
        }
        m.mo587a(new akr(str, a));
        m574a(context, str, a, j);
    }

    /* renamed from: a */
    private static void m574a(Context context, String str, int i, long j) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent service = PendingIntent.getService(context, i, aiw.m578b(context, str), 134217728);
        if (alarmManager != null) {
            int i2 = Build.VERSION.SDK_INT;
            alarmManager.setExact(0, j, service);
        }
    }
}
