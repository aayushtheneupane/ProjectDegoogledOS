package p000;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;
import java.util.HashMap;
import java.util.WeakHashMap;

/* renamed from: ami */
/* compiled from: PG */
public final class ami {

    /* renamed from: a */
    private static final String f768a = iol.m14236b("WakeLocks");

    /* renamed from: b */
    private static final WeakHashMap f769b = new WeakHashMap();

    /* renamed from: a */
    public static void m763a() {
        HashMap hashMap = new HashMap();
        synchronized (f769b) {
            hashMap.putAll(f769b);
        }
        for (PowerManager.WakeLock wakeLock : hashMap.keySet()) {
            if (wakeLock != null && wakeLock.isHeld()) {
                String format = String.format("WakeLock held for %s", new Object[]{hashMap.get(wakeLock)});
                iol.m14231a();
                Log.w(f768a, format);
            }
        }
    }

    /* renamed from: a */
    public static PowerManager.WakeLock m762a(Context context, String str) {
        String str2 = "WorkManager: " + str;
        PowerManager.WakeLock newWakeLock = ((PowerManager) context.getApplicationContext().getSystemService("power")).newWakeLock(1, str2);
        synchronized (f769b) {
            f769b.put(newWakeLock, str2);
        }
        return newWakeLock;
    }
}
