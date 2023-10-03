package com.android.systemui.shared.plugins;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.PluginFragment;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.shared.plugins.VersionInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PluginInstanceManager<T extends Plugin> {
    /* access modifiers changed from: private */
    public final boolean isDebuggable;
    /* access modifiers changed from: private */
    public final String mAction;
    /* access modifiers changed from: private */
    public final boolean mAllowMultiple;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final PluginListener<T> mListener;
    @VisibleForTesting
    final PluginInstanceManager<T>.MainHandler mMainHandler;
    /* access modifiers changed from: private */
    public final PluginManagerImpl mManager;
    @VisibleForTesting
    final PluginInstanceManager<T>.PluginHandler mPluginHandler;
    /* access modifiers changed from: private */
    public final PackageManager mPm;
    /* access modifiers changed from: private */
    public final VersionInfo mVersion;
    private final ArraySet<String> mWhitelistedPlugins;

    PluginInstanceManager(Context context, String str, PluginListener<T> pluginListener, boolean z, Looper looper, VersionInfo versionInfo, PluginManagerImpl pluginManagerImpl) {
        this(context, context.getPackageManager(), str, pluginListener, z, looper, versionInfo, pluginManagerImpl, Build.IS_DEBUGGABLE, pluginManagerImpl.getWhitelistedPlugins());
    }

    @VisibleForTesting
    PluginInstanceManager(Context context, PackageManager packageManager, String str, PluginListener<T> pluginListener, boolean z, Looper looper, VersionInfo versionInfo, PluginManagerImpl pluginManagerImpl, boolean z2, String[] strArr) {
        this.mWhitelistedPlugins = new ArraySet<>();
        this.mMainHandler = new MainHandler(Looper.getMainLooper());
        this.mPluginHandler = new PluginHandler(looper);
        this.mManager = pluginManagerImpl;
        this.mContext = context;
        this.mPm = packageManager;
        this.mAction = str;
        this.mListener = pluginListener;
        this.mAllowMultiple = z;
        this.mVersion = versionInfo;
        this.mWhitelistedPlugins.addAll(Arrays.asList(strArr));
        this.isDebuggable = z2;
    }

    public void loadAll() {
        this.mPluginHandler.sendEmptyMessage(1);
    }

    public void destroy() {
        Iterator it = new ArrayList(this.mPluginHandler.mPlugins).iterator();
        while (it.hasNext()) {
            this.mMainHandler.obtainMessage(2, ((PluginInfo) it.next()).mPlugin).sendToTarget();
        }
    }

    public void onPackageRemoved(String str) {
        this.mPluginHandler.obtainMessage(3, str).sendToTarget();
    }

    public void onPackageChange(String str) {
        this.mPluginHandler.obtainMessage(3, str).sendToTarget();
        this.mPluginHandler.obtainMessage(2, str).sendToTarget();
    }

    public boolean checkAndDisable(String str) {
        Iterator it = new ArrayList(this.mPluginHandler.mPlugins).iterator();
        boolean z = false;
        while (it.hasNext()) {
            PluginInfo pluginInfo = (PluginInfo) it.next();
            if (str.startsWith(pluginInfo.mPackage)) {
                disable(pluginInfo, 2);
                z = true;
            }
        }
        return z;
    }

    public boolean disableAll() {
        ArrayList arrayList = new ArrayList(this.mPluginHandler.mPlugins);
        for (int i = 0; i < arrayList.size(); i++) {
            disable((PluginInfo) arrayList.get(i), 3);
        }
        if (arrayList.size() != 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean isPluginWhitelisted(ComponentName componentName) {
        Iterator<String> it = this.mWhitelistedPlugins.iterator();
        while (it.hasNext()) {
            String next = it.next();
            ComponentName unflattenFromString = ComponentName.unflattenFromString(next);
            if (unflattenFromString == null) {
                if (next.equals(componentName.getPackageName())) {
                    return true;
                }
            } else if (unflattenFromString.equals(componentName)) {
                return true;
            }
        }
        return false;
    }

    private void disable(PluginInfo pluginInfo, int i) {
        ComponentName componentName = new ComponentName(pluginInfo.mPackage, pluginInfo.mClass);
        if (!isPluginWhitelisted(componentName)) {
            Log.w("PluginInstanceManager", "Disabling plugin " + componentName.flattenToShortString());
            this.mManager.getPluginEnabler().setDisabled(componentName, i);
        }
    }

    public <T> boolean dependsOn(Plugin plugin, Class<T> cls) {
        Iterator it = new ArrayList(this.mPluginHandler.mPlugins).iterator();
        while (it.hasNext()) {
            PluginInfo pluginInfo = (PluginInfo) it.next();
            if (pluginInfo.mPlugin.getClass().getName().equals(plugin.getClass().getName())) {
                if (pluginInfo.mVersion == null || !pluginInfo.mVersion.hasClass(cls)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return String.format("%s@%s (action=%s)", new Object[]{PluginInstanceManager.class.getSimpleName(), Integer.valueOf(hashCode()), this.mAction});
    }

    private class MainHandler extends Handler {
        public MainHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                PluginPrefs.setHasPlugins(PluginInstanceManager.this.mContext);
                PluginInfo pluginInfo = (PluginInfo) message.obj;
                PluginInstanceManager.this.mManager.handleWtfs();
                if (!(message.obj instanceof PluginFragment)) {
                    ((Plugin) pluginInfo.mPlugin).onCreate(PluginInstanceManager.this.mContext, pluginInfo.mPluginContext);
                }
                PluginInstanceManager.this.mListener.onPluginConnected((Plugin) pluginInfo.mPlugin, pluginInfo.mPluginContext);
            } else if (i != 2) {
                super.handleMessage(message);
            } else {
                PluginInstanceManager.this.mListener.onPluginDisconnected((Plugin) message.obj);
                Object obj = message.obj;
                if (!(obj instanceof PluginFragment)) {
                    ((Plugin) obj).onDestroy();
                }
            }
        }
    }

    private class PluginHandler extends Handler {
        /* access modifiers changed from: private */
        public final ArrayList<PluginInfo<T>> mPlugins = new ArrayList<>();

        public PluginHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                for (int size = this.mPlugins.size() - 1; size >= 0; size--) {
                    PluginInfo pluginInfo = this.mPlugins.get(size);
                    PluginInstanceManager.this.mListener.onPluginDisconnected((Plugin) pluginInfo.mPlugin);
                    T t = pluginInfo.mPlugin;
                    if (!(t instanceof PluginFragment)) {
                        ((Plugin) t).onDestroy();
                    }
                }
                this.mPlugins.clear();
                handleQueryPlugins((String) null);
            } else if (i == 2) {
                String str = (String) message.obj;
                if (PluginInstanceManager.this.mAllowMultiple || this.mPlugins.size() == 0) {
                    handleQueryPlugins(str);
                }
            } else if (i != 3) {
                super.handleMessage(message);
            } else {
                String str2 = (String) message.obj;
                for (int size2 = this.mPlugins.size() - 1; size2 >= 0; size2--) {
                    PluginInfo pluginInfo2 = this.mPlugins.get(size2);
                    if (pluginInfo2.mPackage.equals(str2)) {
                        PluginInstanceManager.this.mMainHandler.obtainMessage(2, pluginInfo2.mPlugin).sendToTarget();
                        this.mPlugins.remove(size2);
                    }
                }
            }
        }

        private void handleQueryPlugins(String str) {
            Intent intent = new Intent(PluginInstanceManager.this.mAction);
            if (str != null) {
                intent.setPackage(str);
            }
            List<ResolveInfo> queryIntentServices = PluginInstanceManager.this.mPm.queryIntentServices(intent, 0);
            if (queryIntentServices.size() <= 1 || PluginInstanceManager.this.mAllowMultiple) {
                for (ResolveInfo next : queryIntentServices) {
                    PluginInfo handleLoadPlugin = handleLoadPlugin(new ComponentName(next.serviceInfo.packageName, next.serviceInfo.name));
                    if (handleLoadPlugin != null) {
                        this.mPlugins.add(handleLoadPlugin);
                        PluginInstanceManager.this.mMainHandler.obtainMessage(1, handleLoadPlugin).sendToTarget();
                    }
                }
                return;
            }
            Log.w("PluginInstanceManager", "Multiple plugins found for " + PluginInstanceManager.this.mAction);
        }

        /* access modifiers changed from: protected */
        public PluginInfo<T> handleLoadPlugin(ComponentName componentName) {
            Plugin plugin;
            String str;
            if (!PluginInstanceManager.this.isDebuggable && !PluginInstanceManager.this.isPluginWhitelisted(componentName)) {
                Log.w("PluginInstanceManager", "Plugin cannot be loaded on production build: " + componentName);
                return null;
            } else if (!PluginInstanceManager.this.mManager.getPluginEnabler().isEnabled(componentName)) {
                return null;
            } else {
                String packageName = componentName.getPackageName();
                String className = componentName.getClassName();
                try {
                    ApplicationInfo applicationInfo = PluginInstanceManager.this.mPm.getApplicationInfo(packageName, 0);
                    if (PluginInstanceManager.this.mPm.checkPermission("com.android.systemui.permission.PLUGIN", packageName) != 0) {
                        Log.d("PluginInstanceManager", "Plugin doesn't have permission: " + packageName);
                        return null;
                    }
                    ClassLoader classLoader = PluginInstanceManager.this.mManager.getClassLoader(applicationInfo);
                    PluginContextWrapper pluginContextWrapper = new PluginContextWrapper(PluginInstanceManager.this.mContext.createApplicationContext(applicationInfo, 0), classLoader);
                    Class<?> cls = Class.forName(className, true, classLoader);
                    plugin = (Plugin) cls.newInstance();
                    return new PluginInfo(packageName, className, plugin, pluginContextWrapper, checkVersion(cls, plugin, PluginInstanceManager.this.mVersion));
                } catch (VersionInfo.InvalidVersionException e) {
                    Notification.Builder color = new Notification.Builder(PluginInstanceManager.this.mContext, "ALR").setStyle(new Notification.BigTextStyle()).setSmallIcon(PluginInstanceManager.this.mContext.getResources().getIdentifier("tuner", "drawable", PluginInstanceManager.this.mContext.getPackageName())).setWhen(0).setShowWhen(false).setVisibility(1).setColor(PluginInstanceManager.this.mContext.getColor(Resources.getSystem().getIdentifier("system_notification_accent_color", "color", "android")));
                    try {
                        str = PluginInstanceManager.this.mPm.getServiceInfo(componentName, 0).loadLabel(PluginInstanceManager.this.mPm).toString();
                    } catch (PackageManager.NameNotFoundException unused) {
                        str = className;
                    }
                    if (!e.isTooNew()) {
                        Notification.Builder contentTitle = color.setContentTitle("Plugin \"" + str + "\" is too old");
                        StringBuilder sb = new StringBuilder();
                        sb.append("Contact plugin developer to get an updated version.\n");
                        sb.append(e.getMessage());
                        contentTitle.setContentText(sb.toString());
                    } else {
                        Notification.Builder contentTitle2 = color.setContentTitle("Plugin \"" + str + "\" is too new");
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Check to see if an OTA is available.\n");
                        sb2.append(e.getMessage());
                        contentTitle2.setContentText(sb2.toString());
                    }
                    Intent intent = new Intent("com.android.systemui.action.DISABLE_PLUGIN");
                    color.addAction(new Notification.Action.Builder((Icon) null, "Disable plugin", PendingIntent.getBroadcast(PluginInstanceManager.this.mContext, 0, intent.setData(Uri.parse("package://" + componentName.flattenToString())), 0)).build());
                    ((NotificationManager) PluginInstanceManager.this.mContext.getSystemService(NotificationManager.class)).notifyAsUser(className, 6, color.build(), UserHandle.ALL);
                    Log.w("PluginInstanceManager", "Plugin has invalid interface version " + plugin.getVersion() + ", expected " + PluginInstanceManager.this.mVersion);
                    return null;
                } catch (Throwable th) {
                    Log.w("PluginInstanceManager", "Couldn't load plugin: " + packageName, th);
                    return null;
                }
            }
        }

        private VersionInfo checkVersion(Class<?> cls, T t, VersionInfo versionInfo) throws VersionInfo.InvalidVersionException {
            VersionInfo versionInfo2 = new VersionInfo();
            versionInfo2.addClass(cls);
            if (versionInfo2.hasVersionInfo()) {
                versionInfo.checkVersion(versionInfo2);
                return versionInfo2;
            } else if (t.getVersion() == versionInfo.getDefaultVersion()) {
                return null;
            } else {
                throw new VersionInfo.InvalidVersionException("Invalid legacy version", false);
            }
        }
    }

    public static class PluginContextWrapper extends ContextWrapper {
        private final ClassLoader mClassLoader;
        private LayoutInflater mInflater;

        public PluginContextWrapper(Context context, ClassLoader classLoader) {
            super(context);
            this.mClassLoader = classLoader;
        }

        public ClassLoader getClassLoader() {
            return this.mClassLoader;
        }

        public Object getSystemService(String str) {
            if (!"layout_inflater".equals(str)) {
                return getBaseContext().getSystemService(str);
            }
            if (this.mInflater == null) {
                this.mInflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
            }
            return this.mInflater;
        }
    }

    static class PluginInfo<T> {
        /* access modifiers changed from: private */
        public String mClass;
        String mPackage;
        T mPlugin;
        /* access modifiers changed from: private */
        public final Context mPluginContext;
        /* access modifiers changed from: private */
        public final VersionInfo mVersion;

        public PluginInfo(String str, String str2, T t, Context context, VersionInfo versionInfo) {
            this.mPlugin = t;
            this.mClass = str2;
            this.mPackage = str;
            this.mPluginContext = context;
            this.mVersion = versionInfo;
        }
    }
}
