package androidx.core.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Build;

public final class AlarmManagerCompat {
    private AlarmManagerCompat() {
    }

    public static void setAlarmClock(AlarmManager alarmManager, long j, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        int i = Build.VERSION.SDK_INT;
        alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(j, pendingIntent), pendingIntent2);
    }

    public static void setAndAllowWhileIdle(AlarmManager alarmManager, int i, long j, PendingIntent pendingIntent) {
        int i2 = Build.VERSION.SDK_INT;
        alarmManager.setAndAllowWhileIdle(i, j, pendingIntent);
    }

    public static void setExact(AlarmManager alarmManager, int i, long j, PendingIntent pendingIntent) {
        int i2 = Build.VERSION.SDK_INT;
        alarmManager.setExact(i, j, pendingIntent);
    }

    public static void setExactAndAllowWhileIdle(AlarmManager alarmManager, int i, long j, PendingIntent pendingIntent) {
        int i2 = Build.VERSION.SDK_INT;
        alarmManager.setExactAndAllowWhileIdle(i, j, pendingIntent);
    }
}
