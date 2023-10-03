package com.android.systemui.appops;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.ArraySet;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.Dumpable;
import com.android.systemui.appops.AppOpsController;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AppOpsControllerImpl implements AppOpsController, AppOpsManager.OnOpActiveChangedListener, AppOpsManager.OnOpNotedListener, Dumpable {
    protected static final int[] OPS = {26, 24, 27, 0, 1};
    @GuardedBy({"mActiveItems"})
    private final List<AppOpItem> mActiveItems;
    private final AppOpsManager mAppOps;
    private C0626H mBGHandler;
    private final List<AppOpsController.Callback> mCallbacks;
    private final ArrayMap<Integer, Set<AppOpsController.Callback>> mCallbacksByCode;
    private final Context mContext;
    private final PermissionFlagsCache mFlagsCache;
    private boolean mListening;
    @GuardedBy({"mNotedItems"})
    private final List<AppOpItem> mNotedItems;

    public AppOpsControllerImpl(Context context, Looper looper) {
        this(context, looper, new PermissionFlagsCache(context));
    }

    @VisibleForTesting
    protected AppOpsControllerImpl(Context context, Looper looper, PermissionFlagsCache permissionFlagsCache) {
        this.mCallbacks = new ArrayList();
        this.mCallbacksByCode = new ArrayMap<>();
        this.mActiveItems = new ArrayList();
        this.mNotedItems = new ArrayList();
        this.mContext = context;
        this.mAppOps = (AppOpsManager) context.getSystemService("appops");
        this.mFlagsCache = permissionFlagsCache;
        this.mBGHandler = new C0626H(looper);
        for (int valueOf : OPS) {
            this.mCallbacksByCode.put(Integer.valueOf(valueOf), new ArraySet());
        }
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public void setBGHandler(C0626H h) {
        this.mBGHandler = h;
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public void setListening(boolean z) {
        this.mListening = z;
        if (z) {
            this.mAppOps.startWatchingActive(OPS, this);
            this.mAppOps.startWatchingNoted(OPS, this);
            return;
        }
        this.mAppOps.stopWatchingActive(this);
        this.mAppOps.stopWatchingNoted(this);
        this.mBGHandler.removeCallbacksAndMessages((Object) null);
        synchronized (this.mActiveItems) {
            this.mActiveItems.clear();
        }
        synchronized (this.mNotedItems) {
            this.mNotedItems.clear();
        }
    }

    public void addCallback(int[] iArr, AppOpsController.Callback callback) {
        int length = iArr.length;
        boolean z = false;
        for (int i = 0; i < length; i++) {
            if (this.mCallbacksByCode.containsKey(Integer.valueOf(iArr[i]))) {
                this.mCallbacksByCode.get(Integer.valueOf(iArr[i])).add(callback);
                z = true;
            }
        }
        if (z) {
            this.mCallbacks.add(callback);
        }
        if (!this.mCallbacks.isEmpty()) {
            setListening(true);
        }
    }

    public void removeCallback(int[] iArr, AppOpsController.Callback callback) {
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            if (this.mCallbacksByCode.containsKey(Integer.valueOf(iArr[i]))) {
                this.mCallbacksByCode.get(Integer.valueOf(iArr[i])).remove(callback);
            }
        }
        this.mCallbacks.remove(callback);
        if (this.mCallbacks.isEmpty()) {
            setListening(false);
        }
    }

    private AppOpItem getAppOpItem(List<AppOpItem> list, int i, int i2, String str) {
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            AppOpItem appOpItem = list.get(i3);
            if (appOpItem.getCode() == i && appOpItem.getUid() == i2 && appOpItem.getPackageName().equals(str)) {
                return appOpItem;
            }
        }
        return null;
    }

    private boolean updateActives(int i, int i2, String str, boolean z) {
        synchronized (this.mActiveItems) {
            AppOpItem appOpItem = getAppOpItem(this.mActiveItems, i, i2, str);
            if (appOpItem == null && z) {
                this.mActiveItems.add(new AppOpItem(i, i2, str, System.currentTimeMillis()));
                return true;
            } else if (appOpItem == null || z) {
                return false;
            } else {
                this.mActiveItems.remove(appOpItem);
                return true;
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0015, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        if (getAppOpItem(r3.mActiveItems, r4, r5, r6) == null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0022, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0023, code lost:
        if (r0 != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0025, code lost:
        notifySuscribers(r4, r5, r6, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        r1 = r3.mActiveItems;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeNoted(int r4, int r5, java.lang.String r6) {
        /*
            r3 = this;
            java.util.List<com.android.systemui.appops.AppOpItem> r0 = r3.mNotedItems
            monitor-enter(r0)
            java.util.List<com.android.systemui.appops.AppOpItem> r1 = r3.mNotedItems     // Catch:{ all -> 0x002c }
            com.android.systemui.appops.AppOpItem r1 = r3.getAppOpItem(r1, r4, r5, r6)     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x000d
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x000d:
            java.util.List<com.android.systemui.appops.AppOpItem> r2 = r3.mNotedItems     // Catch:{ all -> 0x002c }
            r2.remove(r1)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            java.util.List<com.android.systemui.appops.AppOpItem> r1 = r3.mActiveItems
            monitor-enter(r1)
            java.util.List<com.android.systemui.appops.AppOpItem> r0 = r3.mActiveItems     // Catch:{ all -> 0x0029 }
            com.android.systemui.appops.AppOpItem r0 = r3.getAppOpItem(r0, r4, r5, r6)     // Catch:{ all -> 0x0029 }
            r2 = 0
            if (r0 == 0) goto L_0x0021
            r0 = 1
            goto L_0x0022
        L_0x0021:
            r0 = r2
        L_0x0022:
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            if (r0 != 0) goto L_0x0028
            r3.lambda$onOpActiveChanged$0$AppOpsControllerImpl(r4, r5, r6, r2)
        L_0x0028:
            return
        L_0x0029:
            r3 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            throw r3
        L_0x002c:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.appops.AppOpsControllerImpl.removeNoted(int, int, java.lang.String):void");
    }

    private boolean addNoted(int i, int i2, String str) {
        AppOpItem appOpItem;
        boolean z;
        synchronized (this.mNotedItems) {
            appOpItem = getAppOpItem(this.mNotedItems, i, i2, str);
            if (appOpItem == null) {
                appOpItem = new AppOpItem(i, i2, str, System.currentTimeMillis());
                this.mNotedItems.add(appOpItem);
                z = true;
            } else {
                z = false;
            }
        }
        this.mBGHandler.removeCallbacksAndMessages(appOpItem);
        this.mBGHandler.scheduleRemoval(appOpItem, 5000);
        return z;
    }

    private boolean isUserSensitive(int i, int i2, String str) {
        String opToPermission = AppOpsManager.opToPermission(i);
        if (opToPermission == null || (this.mFlagsCache.getPermissionFlags(opToPermission, str, UserHandle.getUserHandleForUid(i2)) & 256) == 0) {
            return false;
        }
        return true;
    }

    private boolean isUserVisible(AppOpItem appOpItem) {
        return isUserVisible(appOpItem.getCode(), appOpItem.getUid(), appOpItem.getPackageName());
    }

    private boolean isUserVisible(int i, int i2, String str) {
        if (i == 24) {
            return true;
        }
        return isUserSensitive(i, i2, str);
    }

    public List<AppOpItem> getActiveAppOpsForUser(int i) {
        int i2;
        ArrayList arrayList = new ArrayList();
        synchronized (this.mActiveItems) {
            int size = this.mActiveItems.size();
            for (int i3 = 0; i3 < size; i3++) {
                AppOpItem appOpItem = this.mActiveItems.get(i3);
                if ((i == -1 || UserHandle.getUserId(appOpItem.getUid()) == i) && isUserVisible(appOpItem)) {
                    arrayList.add(appOpItem);
                }
            }
        }
        synchronized (this.mNotedItems) {
            int size2 = this.mNotedItems.size();
            for (i2 = 0; i2 < size2; i2++) {
                AppOpItem appOpItem2 = this.mNotedItems.get(i2);
                if ((i == -1 || UserHandle.getUserId(appOpItem2.getUid()) == i) && isUserVisible(appOpItem2)) {
                    arrayList.add(appOpItem2);
                }
            }
        }
        return arrayList;
    }

    public void onOpActiveChanged(int i, int i2, String str, boolean z) {
        boolean z2;
        if (updateActives(i, i2, str, z)) {
            synchronized (this.mNotedItems) {
                z2 = getAppOpItem(this.mNotedItems, i, i2, str) != null;
            }
            if (!z2) {
                this.mBGHandler.post(new Runnable(i, i2, str, z) {
                    private final /* synthetic */ int f$1;
                    private final /* synthetic */ int f$2;
                    private final /* synthetic */ String f$3;
                    private final /* synthetic */ boolean f$4;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                        this.f$4 = r5;
                    }

                    public final void run() {
                        AppOpsControllerImpl.this.lambda$onOpActiveChanged$0$AppOpsControllerImpl(this.f$1, this.f$2, this.f$3, this.f$4);
                    }
                });
            }
        }
    }

    public void onOpNoted(int i, int i2, String str, int i3) {
        boolean z;
        if (i3 == 0 && addNoted(i, i2, str)) {
            synchronized (this.mActiveItems) {
                z = getAppOpItem(this.mActiveItems, i, i2, str) != null;
            }
            if (!z) {
                this.mBGHandler.post(new Runnable(i, i2, str) {
                    private final /* synthetic */ int f$1;
                    private final /* synthetic */ int f$2;
                    private final /* synthetic */ String f$3;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    public final void run() {
                        AppOpsControllerImpl.this.lambda$onOpNoted$1$AppOpsControllerImpl(this.f$1, this.f$2, this.f$3);
                    }
                });
            }
        }
    }

    public /* synthetic */ void lambda$onOpNoted$1$AppOpsControllerImpl(int i, int i2, String str) {
        lambda$onOpActiveChanged$0$AppOpsControllerImpl(i, i2, str, true);
    }

    /* access modifiers changed from: private */
    /* renamed from: notifySuscribers */
    public void lambda$onOpActiveChanged$0$AppOpsControllerImpl(int i, int i2, String str, boolean z) {
        if (this.mCallbacksByCode.containsKey(Integer.valueOf(i)) && isUserVisible(i, i2, str)) {
            for (AppOpsController.Callback onActiveStateChanged : this.mCallbacksByCode.get(Integer.valueOf(i))) {
                onActiveStateChanged.onActiveStateChanged(i, i2, str, z);
            }
        }
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("AppOpsController state:");
        printWriter.println("  Listening: " + this.mListening);
        printWriter.println("  Active Items:");
        for (int i = 0; i < this.mActiveItems.size(); i++) {
            printWriter.print("    ");
            printWriter.println(this.mActiveItems.get(i).toString());
        }
        printWriter.println("  Noted Items:");
        for (int i2 = 0; i2 < this.mNotedItems.size(); i2++) {
            printWriter.print("    ");
            printWriter.println(this.mNotedItems.get(i2).toString());
        }
    }

    /* renamed from: com.android.systemui.appops.AppOpsControllerImpl$H */
    protected class C0626H extends Handler {
        C0626H(Looper looper) {
            super(looper);
        }

        public void scheduleRemoval(final AppOpItem appOpItem, long j) {
            removeCallbacksAndMessages(appOpItem);
            postDelayed(new Runnable() {
                public void run() {
                    AppOpsControllerImpl.this.removeNoted(appOpItem.getCode(), appOpItem.getUid(), appOpItem.getPackageName());
                }
            }, appOpItem, j);
        }
    }
}
