package androidx.mediarouter.media;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Collections;

final class RegisteredMediaRouteProviderWatcher {
    private final Callback mCallback;
    private final Context mContext;
    private final Handler mHandler;
    private final PackageManager mPackageManager;
    private final ArrayList<RegisteredMediaRouteProvider> mProviders = new ArrayList<>();
    private boolean mRunning;
    private final BroadcastReceiver mScanPackagesReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            RegisteredMediaRouteProviderWatcher.this.scanPackages();
        }
    };
    private final Runnable mScanPackagesRunnable = new Runnable() {
        public void run() {
            RegisteredMediaRouteProviderWatcher.this.scanPackages();
        }
    };

    public interface Callback {
        void addProvider(MediaRouteProvider mediaRouteProvider);

        void removeProvider(MediaRouteProvider mediaRouteProvider);
    }

    public RegisteredMediaRouteProviderWatcher(Context context, Callback callback) {
        this.mContext = context;
        this.mCallback = callback;
        this.mHandler = new Handler();
        this.mPackageManager = context.getPackageManager();
    }

    public void start() {
        if (!this.mRunning) {
            this.mRunning = true;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
            intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
            intentFilter.addAction("android.intent.action.PACKAGE_RESTARTED");
            intentFilter.addDataScheme("package");
            this.mContext.registerReceiver(this.mScanPackagesReceiver, intentFilter, (String) null, this.mHandler);
            this.mHandler.post(this.mScanPackagesRunnable);
        }
    }

    /* access modifiers changed from: package-private */
    public void scanPackages() {
        int i;
        if (this.mRunning) {
            int i2 = 0;
            for (ResolveInfo resolveInfo : this.mPackageManager.queryIntentServices(new Intent("android.media.MediaRouteProviderService"), 0)) {
                ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                if (serviceInfo != null) {
                    int findProvider = findProvider(serviceInfo.packageName, serviceInfo.name);
                    if (findProvider < 0) {
                        RegisteredMediaRouteProvider registeredMediaRouteProvider = new RegisteredMediaRouteProvider(this.mContext, new ComponentName(serviceInfo.packageName, serviceInfo.name));
                        registeredMediaRouteProvider.start();
                        i = i2 + 1;
                        this.mProviders.add(i2, registeredMediaRouteProvider);
                        this.mCallback.addProvider(registeredMediaRouteProvider);
                    } else if (findProvider >= i2) {
                        RegisteredMediaRouteProvider registeredMediaRouteProvider2 = this.mProviders.get(findProvider);
                        registeredMediaRouteProvider2.start();
                        registeredMediaRouteProvider2.rebindIfDisconnected();
                        i = i2 + 1;
                        Collections.swap(this.mProviders, findProvider, i2);
                    }
                    i2 = i;
                }
            }
            if (i2 < this.mProviders.size()) {
                for (int size = this.mProviders.size() - 1; size >= i2; size--) {
                    RegisteredMediaRouteProvider registeredMediaRouteProvider3 = this.mProviders.get(size);
                    this.mCallback.removeProvider(registeredMediaRouteProvider3);
                    this.mProviders.remove(registeredMediaRouteProvider3);
                    registeredMediaRouteProvider3.stop();
                }
            }
        }
    }

    private int findProvider(String str, String str2) {
        int size = this.mProviders.size();
        for (int i = 0; i < size; i++) {
            if (this.mProviders.get(i).hasComponentName(str, str2)) {
                return i;
            }
        }
        return -1;
    }
}
