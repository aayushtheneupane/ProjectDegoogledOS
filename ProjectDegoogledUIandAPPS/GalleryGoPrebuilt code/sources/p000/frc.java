package p000;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.List;

/* renamed from: frc */
/* compiled from: PG */
public final class frc {

    /* renamed from: a */
    private static final Object f10305a = new Object();

    /* renamed from: b */
    private static List f10306b;

    /* renamed from: a */
    public static void m9450a(Context context, Intent intent, BroadcastReceiver broadcastReceiver) {
        List<ResolveInfo> list;
        PackageManager packageManager = context.getPackageManager();
        Class<?> cls = broadcastReceiver.getClass();
        List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(new Intent(context, cls), 0);
        ResolveInfo resolveInfo = !queryBroadcastReceivers.isEmpty() ? queryBroadcastReceivers.get(0) : null;
        if (resolveInfo != null && resolveInfo.activityInfo.exported) {
            String name = cls.getName();
            Intent cloneFilter = intent.setComponent((ComponentName) null).cloneFilter();
            cloneFilter.setSelector((Intent) null);
            cloneFilter.setPackage(context.getPackageName());
            for (ResolveInfo next : packageManager.queryBroadcastReceivers(cloneFilter, 64)) {
                if (name.equals(next.activityInfo.name)) {
                    IntentFilter intentFilter = next.filter;
                    ife.m12898e((Object) intentFilter);
                    if (!intentFilter.matchAction(intent.getAction())) {
                        throw new frb(intent);
                    } else if (intentFilter.matchCategories(intent.getCategories()) == null) {
                        int matchData = intentFilter.matchData(intent.getType(), intent.getScheme(), intent.getData());
                        if (matchData == -1 || matchData == -2) {
                            throw new frb(intent);
                        }
                        return;
                    } else {
                        throw new frb(intent);
                    }
                }
            }
            synchronized (f10305a) {
                if (f10306b == null) {
                    Intent intent2 = new Intent();
                    intent2.setPackage(context.getPackageName());
                    f10306b = packageManager.queryBroadcastReceivers(intent2, 0);
                }
                list = f10306b;
            }
            for (ResolveInfo resolveInfo2 : list) {
                if (name.equals(resolveInfo2.activityInfo.name)) {
                    throw new frb(intent);
                }
            }
        }
    }
}
