package androidx.media.session;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.p016v4.media.C0085o;
import android.util.Log;
import java.util.List;

public class MediaButtonReceiver extends BroadcastReceiver {
    /* renamed from: o */
    private static ComponentName m538o(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices.size() == 1) {
            ResolveInfo resolveInfo = queryIntentServices.get(0);
            return new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
        } else if (queryIntentServices.isEmpty()) {
            return null;
        } else {
            throw new IllegalStateException("Expected 1 service that handles " + str + ", found " + queryIntentServices.size());
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (intent == null || !"android.intent.action.MEDIA_BUTTON".equals(intent.getAction()) || !intent.hasExtra("android.intent.extra.KEY_EVENT")) {
            Log.d("MediaButtonReceiver", "Ignore unsupported intent: " + intent);
            return;
        }
        ComponentName o = m538o(context, "android.intent.action.MEDIA_BUTTON");
        if (o != null) {
            intent.setComponent(o);
            int i = Build.VERSION.SDK_INT;
            context.startForegroundService(intent);
            return;
        }
        ComponentName o2 = m538o(context, "android.media.browse.MediaBrowserService");
        if (o2 != null) {
            BroadcastReceiver.PendingResult goAsync = goAsync();
            Context applicationContext = context.getApplicationContext();
            C0513a aVar = new C0513a(applicationContext, intent, goAsync);
            C0085o oVar = new C0085o(applicationContext, o2, aVar, (Bundle) null);
            aVar.mo4601a(oVar);
            oVar.connect();
            return;
        }
        throw new IllegalStateException("Could not find any Service that handles android.intent.action.MEDIA_BUTTON or implements a media browser service.");
    }
}
