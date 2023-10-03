package com.android.systemui.statusbar.policy;

import android.app.ActivityManager;
import android.app.ActivityManagerNative;
import android.app.ActivityTaskManager;
import android.app.IActivityManager;
import android.app.IActivityTaskManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.internal.os.BackgroundThread;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.PackageManagerWrapper;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskHelper implements CommandQueue.Callbacks, KeyguardMonitor.Callback {
    private static final String[] DEFAULT_HOME_CHANGE_ACTIONS = {"android.intent.action.ACTION_PREFERRED_ACTIVITY_CHANGED", "android.intent.action.BOOT_COMPLETED", "android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_CHANGED", "android.intent.action.PACKAGE_REMOVED"};
    /* access modifiers changed from: private */
    public IActivityTaskManager mActivityTaskManager;
    private Context mContext;
    /* access modifiers changed from: private */
    public ComponentName mDefaultHome;
    private final BroadcastReceiver mDefaultHomeBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            TaskHelper taskHelper = TaskHelper.this;
            ComponentName unused = taskHelper.mDefaultHome = taskHelper.getCurrentDefaultHome();
        }
    };
    /* access modifiers changed from: private */
    public String mForegroundAppPackageName;
    /* access modifiers changed from: private */
    public TaskHelperHandler mHandler;
    private final Injector mInjector;
    private KeyguardMonitor mKeyguardMonitor;
    private boolean mKeyguardShowing;
    private PackageManager mPm;
    private final ComponentName mRecentsComponentName;
    /* access modifiers changed from: private */
    public int mRunningTaskId;
    /* access modifiers changed from: private */
    public ComponentName mTaskComponentName;
    private final TaskStackChangeListener mTaskStackChangeListener = new TaskStackChangeListener() {
        public void onTaskStackChanged() {
            TaskHelper.this.mHandler.sendEmptyMessage(0);
        }
    };

    private final class TaskHelperHandler extends Handler {
        public TaskHelperHandler(Looper looper) {
            super(looper, (Handler.Callback) null, true);
        }

        public void handleMessage(Message message) {
            if (message.what == 0) {
                TaskHelper.this.updateForegroundApp();
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateForegroundApp() {
        this.mInjector.getBackgroundThreadHandler().post(new Runnable() {
            public void run() {
                try {
                    ActivityManager.StackInfo focusedStackInfo = TaskHelper.this.mActivityTaskManager.getFocusedStackInfo();
                    ComponentName unused = TaskHelper.this.mTaskComponentName = focusedStackInfo != null ? focusedStackInfo.topActivity : null;
                    if (TaskHelper.this.mTaskComponentName != null) {
                        String unused2 = TaskHelper.this.mForegroundAppPackageName = TaskHelper.this.mTaskComponentName.getPackageName();
                        int unused3 = TaskHelper.this.mRunningTaskId = focusedStackInfo.taskIds[focusedStackInfo.taskIds.length - 1];
                    }
                } catch (RemoteException unused4) {
                }
            }
        });
    }

    public static class Injector {
        public Handler getBackgroundThreadHandler() {
            return BackgroundThread.getHandler();
        }
    }

    public TaskHelper(Context context, Handler handler) {
        this.mContext = context;
        this.mActivityTaskManager = ActivityTaskManager.getService();
        this.mInjector = new Injector();
        this.mHandler = new TaskHelperHandler(Looper.getMainLooper());
        IntentFilter intentFilter = new IntentFilter();
        for (String addAction : DEFAULT_HOME_CHANGE_ACTIONS) {
            intentFilter.addAction(addAction);
        }
        this.mDefaultHome = getCurrentDefaultHome();
        this.mRecentsComponentName = ComponentName.unflattenFromString(context.getString(17039814));
        context.registerReceiver(this.mDefaultHomeBroadcastReceiver, intentFilter);
        ActivityManagerWrapper.getInstance().registerTaskStackListener(this.mTaskStackChangeListener);
        ((CommandQueue) SysUiServiceProvider.getComponent(context, CommandQueue.class)).addCallback((CommandQueue.Callbacks) this);
        this.mKeyguardMonitor = (KeyguardMonitor) Dependency.get(KeyguardMonitor.class);
        this.mKeyguardMonitor.addCallback(this);
        this.mPm = context.getPackageManager();
        updateForegroundApp();
    }

    /* access modifiers changed from: private */
    public ComponentName getCurrentDefaultHome() {
        ArrayList arrayList = new ArrayList();
        ComponentName homeActivities = PackageManagerWrapper.getInstance().getHomeActivities(arrayList);
        if (homeActivities != null) {
            return homeActivities;
        }
        Iterator it = arrayList.iterator();
        int i = Integer.MIN_VALUE;
        while (true) {
            ComponentName componentName = null;
            while (true) {
                if (!it.hasNext()) {
                    return componentName;
                }
                ResolveInfo resolveInfo = (ResolveInfo) it.next();
                int i2 = resolveInfo.priority;
                if (i2 > i) {
                    componentName = resolveInfo.activityInfo.getComponentName();
                    i = resolveInfo.priority;
                } else if (i2 == i) {
                }
            }
        }
    }

    public void killForegroundApp() {
        ComponentName componentName;
        boolean z;
        if (!isLauncherShowing() && this.mContext.checkCallingOrSelfPermission("android.permission.FORCE_STOP_PACKAGES") == 0 && !isLockTaskOn() && !this.mKeyguardShowing && (componentName = this.mTaskComponentName) != null && !componentName.equals(this.mRecentsComponentName) && !this.mForegroundAppPackageName.equals("com.android.systemui") && !isPackageLiveWalls(this.mForegroundAppPackageName)) {
            IActivityManager iActivityManager = ActivityManagerNative.getDefault();
            try {
                iActivityManager.forceStopPackage(this.mForegroundAppPackageName, -2);
                iActivityManager.removeTask(this.mRunningTaskId);
                z = true;
            } catch (RemoteException unused) {
                z = false;
            }
            if (z) {
                String str = null;
                try {
                    str = this.mPm.getActivityInfo(this.mTaskComponentName, 0).applicationInfo.loadLabel(this.mPm).toString();
                } catch (Exception unused2) {
                }
                if (str == null || str.length() == 0) {
                    str = this.mContext.getString(C1784R$string.empty_app_killed);
                }
                Toast.makeText(this.mContext, this.mContext.getString(C1784R$string.task_helper_app_killed, new Object[]{str}), 0).show();
                this.mHandler.sendEmptyMessage(0);
            }
        }
    }

    public void onKeyguardShowingChanged() {
        this.mKeyguardShowing = this.mKeyguardMonitor.isShowing();
    }

    public String getForegroundApp() {
        String str = this.mForegroundAppPackageName;
        return str == null ? "" : str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
        r2 = r2.mDefaultHome;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isLauncherShowing() {
        /*
            r2 = this;
            android.content.ComponentName r0 = r2.mTaskComponentName
            if (r0 == 0) goto L_0x000c
            android.content.ComponentName r1 = r2.mDefaultHome
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x001c
        L_0x000c:
            android.content.ComponentName r2 = r2.mDefaultHome
            if (r2 == 0) goto L_0x001e
            java.lang.String r2 = r2.getPackageName()
            java.lang.String r0 = "com.android.settings"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x001e
        L_0x001c:
            r2 = 1
            goto L_0x001f
        L_0x001e:
            r2 = 0
        L_0x001f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.TaskHelper.isLauncherShowing():boolean");
    }

    private boolean isPackageLiveWalls(String str) {
        List<ResolveInfo> queryIntentServices;
        if (str == null || (queryIntentServices = this.mPm.queryIntentServices(new Intent("android.service.wallpaper.WallpaperService"), 128)) == null) {
            return false;
        }
        for (ResolveInfo resolveInfo : queryIntentServices) {
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            if (serviceInfo != null && TextUtils.equals(str, serviceInfo.packageName)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isLockTaskOn() {
        try {
            return ActivityManager.getService().isInLockTaskMode();
        } catch (Exception unused) {
            return false;
        }
    }
}
