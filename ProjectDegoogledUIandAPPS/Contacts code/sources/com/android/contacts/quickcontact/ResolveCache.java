package com.android.contacts.quickcontact;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import com.android.contacts.util.PhoneCapabilityTester;
import com.google.common.collect.Sets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ResolveCache {
    private static ResolveCache sInstance;
    private static final HashSet<String> sPreferResolve = Sets.newHashSet((E[]) new String[]{"com.android.email", "com.google.android.email", "com.android.phone", "com.google.android.apps.maps", "com.android.chrome", "org.chromium.webview_shell", "com.google.android.browser", "com.android.browser"});
    private HashMap<String, Entry> mCache = new HashMap<>();
    private final Context mContext;
    private BroadcastReceiver mPackageIntentReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            ResolveCache.flush();
        }
    };
    private final PackageManager mPackageManager;

    public static synchronized ResolveCache getInstance(Context context) {
        ResolveCache resolveCache;
        synchronized (ResolveCache.class) {
            if (sInstance == null) {
                Context applicationContext = context.getApplicationContext();
                sInstance = new ResolveCache(applicationContext);
                IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
                intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
                intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
                intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
                intentFilter.addDataScheme("package");
                applicationContext.registerReceiver(sInstance.mPackageIntentReceiver, intentFilter);
            }
            resolveCache = sInstance;
        }
        return resolveCache;
    }

    /* access modifiers changed from: private */
    public static synchronized void flush() {
        synchronized (ResolveCache.class) {
            sInstance = null;
        }
    }

    private static class Entry {
        public ResolveInfo bestResolve;
        public Drawable icon;

        private Entry() {
        }
    }

    private ResolveCache(Context context) {
        this.mContext = context;
        this.mPackageManager = context.getPackageManager();
    }

    /* access modifiers changed from: protected */
    public Entry getEntry(String str, Intent intent) {
        Entry entry = this.mCache.get(str);
        if (entry != null) {
            return entry;
        }
        ResolveInfo resolveInfo = null;
        Entry entry2 = new Entry();
        if ("vnd.android.cursor.item/sip_address".equals(str) && !PhoneCapabilityTester.isSipPhone(this.mContext)) {
            intent = null;
        }
        if (intent != null) {
            List<ResolveInfo> queryIntentActivities = this.mPackageManager.queryIntentActivities(intent, 65536);
            int size = queryIntentActivities.size();
            if (size == 1) {
                resolveInfo = queryIntentActivities.get(0);
            } else if (size > 1) {
                resolveInfo = getBestResolve(intent, queryIntentActivities);
            }
            if (resolveInfo != null) {
                Drawable loadIcon = resolveInfo.loadIcon(this.mPackageManager);
                entry2.bestResolve = resolveInfo;
                entry2.icon = loadIcon;
            }
        }
        this.mCache.put(str, entry2);
        return entry2;
    }

    /* access modifiers changed from: protected */
    public ResolveInfo getBestResolve(Intent intent, List<ResolveInfo> list) {
        ResolveInfo resolveActivity = this.mPackageManager.resolveActivity(intent, 65536);
        if (!((resolveActivity.match & 268369920) == 0)) {
            return resolveActivity;
        }
        ResolveInfo resolveInfo = null;
        for (ResolveInfo next : list) {
            boolean z = (next.activityInfo.applicationInfo.flags & 1) != 0;
            if (sPreferResolve.contains(next.activityInfo.applicationInfo.packageName)) {
                return next;
            }
            if (z && resolveInfo == null) {
                resolveInfo = next;
            }
        }
        return resolveInfo != null ? resolveInfo : list.get(0);
    }

    public Drawable getIcon(String str, Intent intent) {
        return getEntry(str, intent).icon;
    }
}
