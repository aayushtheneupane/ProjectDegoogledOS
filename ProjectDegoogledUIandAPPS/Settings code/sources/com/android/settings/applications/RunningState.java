package com.android.settings.applications;

import android.app.ActivityManager;
import android.app.ActivityThread;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.pm.UserInfo;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.format.Formatter;
import android.util.Log;
import android.util.SparseArray;
import com.android.settingslib.Utils;
import com.android.settingslib.applications.InterestingConfigChanges;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class RunningState {
    static Object sGlobalLock = new Object();
    static RunningState sInstance;
    final ArrayList<ProcessItem> mAllProcessItems = new ArrayList<>();
    final ActivityManager mAm;
    final Context mApplicationContext;
    final Comparator<MergedItem> mBackgroundComparator = new Comparator<MergedItem>() {
        public int compare(MergedItem mergedItem, MergedItem mergedItem2) {
            int i = mergedItem.mUserId;
            int i2 = mergedItem2.mUserId;
            if (i != i2) {
                int i3 = RunningState.this.mMyUserId;
                if (i == i3) {
                    return -1;
                }
                if (i2 == i3) {
                    return 1;
                }
                if (i < i2) {
                    return -1;
                }
                return 1;
            }
            ProcessItem processItem = mergedItem.mProcess;
            ProcessItem processItem2 = mergedItem2.mProcess;
            if (processItem == processItem2) {
                String str = mergedItem.mLabel;
                String str2 = mergedItem2.mLabel;
                if (str == str2) {
                    return 0;
                }
                if (str != null) {
                    return str.compareTo(str2);
                }
                return -1;
            } else if (processItem == null) {
                return -1;
            } else {
                if (processItem2 == null) {
                    return 1;
                }
                ActivityManager.RunningAppProcessInfo runningAppProcessInfo = processItem.mRunningProcessInfo;
                ActivityManager.RunningAppProcessInfo runningAppProcessInfo2 = processItem2.mRunningProcessInfo;
                boolean z = runningAppProcessInfo.importance >= 400;
                if (z == (runningAppProcessInfo2.importance >= 400)) {
                    boolean z2 = (runningAppProcessInfo.flags & 4) != 0;
                    if (z2 == ((runningAppProcessInfo2.flags & 4) != 0)) {
                        int i4 = runningAppProcessInfo.lru;
                        int i5 = runningAppProcessInfo2.lru;
                        if (i4 == i5) {
                            String str3 = mergedItem.mProcess.mLabel;
                            String str4 = mergedItem2.mProcess.mLabel;
                            if (str3 == str4) {
                                return 0;
                            }
                            if (str3 == null) {
                                return 1;
                            }
                            if (str4 == null) {
                                return -1;
                            }
                            return str3.compareTo(str4);
                        } else if (i4 < i5) {
                            return -1;
                        } else {
                            return 1;
                        }
                    } else if (z2) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else if (z) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    };
    final BackgroundHandler mBackgroundHandler;
    ArrayList<MergedItem> mBackgroundItems = new ArrayList<>();
    long mBackgroundProcessMemory;
    final HandlerThread mBackgroundThread;
    long mForegroundProcessMemory;
    final Handler mHandler = new Handler() {
        int mNextUpdate = 0;

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0017, code lost:
            removeMessages(4);
            sendMessageDelayed(obtainMessage(4), 1000);
            r3 = r2.this$0.mRefreshUiListener;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
            if (r3 == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
            r3.onRefreshUi(r2.mNextUpdate);
            r2.mNextUpdate = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r3) {
            /*
                r2 = this;
                int r0 = r3.what
                r1 = 3
                if (r0 == r1) goto L_0x0035
                r3 = 4
                if (r0 == r3) goto L_0x0009
                goto L_0x003e
            L_0x0009:
                com.android.settings.applications.RunningState r0 = com.android.settings.applications.RunningState.this
                java.lang.Object r0 = r0.mLock
                monitor-enter(r0)
                com.android.settings.applications.RunningState r1 = com.android.settings.applications.RunningState.this     // Catch:{ all -> 0x0032 }
                boolean r1 = r1.mResumed     // Catch:{ all -> 0x0032 }
                if (r1 != 0) goto L_0x0016
                monitor-exit(r0)     // Catch:{ all -> 0x0032 }
                return
            L_0x0016:
                monitor-exit(r0)     // Catch:{ all -> 0x0032 }
                r2.removeMessages(r3)
                android.os.Message r3 = r2.obtainMessage(r3)
                r0 = 1000(0x3e8, double:4.94E-321)
                r2.sendMessageDelayed(r3, r0)
                com.android.settings.applications.RunningState r3 = com.android.settings.applications.RunningState.this
                com.android.settings.applications.RunningState$OnRefreshUiListener r3 = r3.mRefreshUiListener
                if (r3 == 0) goto L_0x003e
                int r0 = r2.mNextUpdate
                r3.onRefreshUi(r0)
                r3 = 0
                r2.mNextUpdate = r3
                goto L_0x003e
            L_0x0032:
                r2 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0032 }
                throw r2
            L_0x0035:
                int r3 = r3.arg1
                if (r3 == 0) goto L_0x003b
                r3 = 2
                goto L_0x003c
            L_0x003b:
                r3 = 1
            L_0x003c:
                r2.mNextUpdate = r3
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.settings.applications.RunningState.C05502.handleMessage(android.os.Message):void");
        }
    };
    boolean mHaveData;
    final boolean mHideManagedProfiles;
    final InterestingConfigChanges mInterestingConfigChanges = new InterestingConfigChanges();
    final ArrayList<ProcessItem> mInterestingProcesses = new ArrayList<>();
    ArrayList<BaseItem> mItems = new ArrayList<>();
    final Object mLock = new Object();
    ArrayList<MergedItem> mMergedItems = new ArrayList<>();
    final int mMyUserId;
    int mNumBackgroundProcesses;
    int mNumForegroundProcesses;
    int mNumServiceProcesses;
    final SparseArray<MergedItem> mOtherUserBackgroundItems = new SparseArray<>();
    final SparseArray<MergedItem> mOtherUserMergedItems = new SparseArray<>();
    final PackageManager mPm;
    final ArrayList<ProcessItem> mProcessItems = new ArrayList<>();
    OnRefreshUiListener mRefreshUiListener;
    boolean mResumed;
    final SparseArray<ProcessItem> mRunningProcesses = new SparseArray<>();
    int mSequence = 0;
    final ServiceProcessComparator mServiceProcessComparator = new ServiceProcessComparator();
    long mServiceProcessMemory;
    final SparseArray<HashMap<String, ProcessItem>> mServiceProcessesByName = new SparseArray<>();
    final SparseArray<ProcessItem> mServiceProcessesByPid = new SparseArray<>();
    final SparseArray<AppProcessInfo> mTmpAppProcesses = new SparseArray<>();
    final UserManager mUm;
    private final UserManagerBroadcastReceiver mUmBroadcastReceiver = new UserManagerBroadcastReceiver();
    ArrayList<MergedItem> mUserBackgroundItems = new ArrayList<>();
    boolean mWatchingBackgroundItems;

    interface OnRefreshUiListener {
        void onRefreshUi(int i);
    }

    static class AppProcessInfo {
        boolean hasForegroundServices;
        boolean hasServices;
        final ActivityManager.RunningAppProcessInfo info;

        AppProcessInfo(ActivityManager.RunningAppProcessInfo runningAppProcessInfo) {
            this.info = runningAppProcessInfo;
        }
    }

    final class BackgroundHandler extends Handler {
        public BackgroundHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                RunningState.this.reset();
            } else if (i == 2) {
                synchronized (RunningState.this.mLock) {
                    if (RunningState.this.mResumed) {
                        Message obtainMessage = RunningState.this.mHandler.obtainMessage(3);
                        RunningState runningState = RunningState.this;
                        obtainMessage.arg1 = runningState.update(runningState.mApplicationContext, runningState.mAm) ? 1 : 0;
                        RunningState.this.mHandler.sendMessage(obtainMessage);
                        removeMessages(2);
                        sendMessageDelayed(obtainMessage(2), 2000);
                    }
                }
            }
        }
    }

    private final class UserManagerBroadcastReceiver extends BroadcastReceiver {
        private volatile boolean usersChanged;

        private UserManagerBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            synchronized (RunningState.this.mLock) {
                if (RunningState.this.mResumed) {
                    RunningState.this.mHaveData = false;
                    RunningState.this.mBackgroundHandler.removeMessages(1);
                    RunningState.this.mBackgroundHandler.sendEmptyMessage(1);
                    RunningState.this.mBackgroundHandler.removeMessages(2);
                    RunningState.this.mBackgroundHandler.sendEmptyMessage(2);
                } else {
                    this.usersChanged = true;
                }
            }
        }

        public boolean checkUsersChangedLocked() {
            boolean z = this.usersChanged;
            this.usersChanged = false;
            return z;
        }

        /* access modifiers changed from: package-private */
        public void register(Context context) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.USER_STOPPED");
            intentFilter.addAction("android.intent.action.USER_STARTED");
            intentFilter.addAction("android.intent.action.USER_INFO_CHANGED");
            context.registerReceiverAsUser(this, UserHandle.ALL, intentFilter, (String) null, (Handler) null);
        }
    }

    static class UserState {
        Drawable mIcon;
        UserInfo mInfo;
        String mLabel;

        UserState() {
        }
    }

    static class BaseItem {
        long mActiveSince;
        boolean mBackground;
        int mCurSeq;
        String mCurSizeStr;
        String mDescription;
        CharSequence mDisplayLabel;
        final boolean mIsProcess;
        String mLabel;
        boolean mNeedDivider;
        PackageItemInfo mPackageInfo;
        long mSize;
        String mSizeStr;
        final int mUserId;

        public BaseItem(boolean z, int i) {
            this.mIsProcess = z;
            this.mUserId = i;
        }

        public Drawable loadIcon(Context context, RunningState runningState) {
            PackageItemInfo packageItemInfo = this.mPackageInfo;
            if (packageItemInfo == null) {
                return null;
            }
            return runningState.mPm.getUserBadgedIcon(packageItemInfo.loadUnbadgedIcon(runningState.mPm), new UserHandle(this.mUserId));
        }
    }

    static class ServiceItem extends BaseItem {
        MergedItem mMergedItem;
        ActivityManager.RunningServiceInfo mRunningService;
        ServiceInfo mServiceInfo;
        boolean mShownAsStarted;

        public ServiceItem(int i) {
            super(false, i);
        }
    }

    static class ProcessItem extends BaseItem {
        long mActiveSince;
        ProcessItem mClient;
        final SparseArray<ProcessItem> mDependentProcesses = new SparseArray<>();
        boolean mInteresting;
        boolean mIsStarted;
        boolean mIsSystem;
        int mLastNumDependentProcesses;
        MergedItem mMergedItem;
        int mPid;
        final String mProcessName;
        ActivityManager.RunningAppProcessInfo mRunningProcessInfo;
        int mRunningSeq;
        final HashMap<ComponentName, ServiceItem> mServices = new HashMap<>();
        final int mUid;

        public ProcessItem(Context context, int i, String str) {
            super(true, UserHandle.getUserId(i));
            this.mDescription = context.getResources().getString(C1715R.string.service_process_name, new Object[]{str});
            this.mUid = i;
            this.mProcessName = str;
        }

        /* access modifiers changed from: package-private */
        public void ensureLabel(PackageManager packageManager) {
            CharSequence text;
            if (this.mLabel == null) {
                try {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(this.mProcessName, 4194304);
                    if (applicationInfo.uid == this.mUid) {
                        this.mDisplayLabel = applicationInfo.loadLabel(packageManager);
                        this.mLabel = this.mDisplayLabel.toString();
                        this.mPackageInfo = applicationInfo;
                        return;
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
                String[] packagesForUid = packageManager.getPackagesForUid(this.mUid);
                if (packagesForUid.length == 1) {
                    try {
                        ApplicationInfo applicationInfo2 = packageManager.getApplicationInfo(packagesForUid[0], 4194304);
                        this.mDisplayLabel = applicationInfo2.loadLabel(packageManager);
                        this.mLabel = this.mDisplayLabel.toString();
                        this.mPackageInfo = applicationInfo2;
                        return;
                    } catch (PackageManager.NameNotFoundException unused2) {
                    }
                }
                for (String str : packagesForUid) {
                    try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
                        if (!(packageInfo.sharedUserLabel == 0 || (text = packageManager.getText(str, packageInfo.sharedUserLabel, packageInfo.applicationInfo)) == null)) {
                            this.mDisplayLabel = text;
                            this.mLabel = text.toString();
                            this.mPackageInfo = packageInfo.applicationInfo;
                            return;
                        }
                    } catch (PackageManager.NameNotFoundException unused3) {
                    }
                }
                if (this.mServices.size() > 0) {
                    this.mPackageInfo = this.mServices.values().iterator().next().mServiceInfo.applicationInfo;
                    this.mDisplayLabel = this.mPackageInfo.loadLabel(packageManager);
                    this.mLabel = this.mDisplayLabel.toString();
                    return;
                }
                try {
                    ApplicationInfo applicationInfo3 = packageManager.getApplicationInfo(packagesForUid[0], 4194304);
                    this.mDisplayLabel = applicationInfo3.loadLabel(packageManager);
                    this.mLabel = this.mDisplayLabel.toString();
                    this.mPackageInfo = applicationInfo3;
                } catch (PackageManager.NameNotFoundException unused4) {
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean updateService(Context context, ActivityManager.RunningServiceInfo runningServiceInfo) {
            boolean z;
            PackageManager packageManager = context.getPackageManager();
            ServiceItem serviceItem = this.mServices.get(runningServiceInfo.service);
            if (serviceItem == null) {
                serviceItem = new ServiceItem(this.mUserId);
                serviceItem.mRunningService = runningServiceInfo;
                try {
                    serviceItem.mServiceInfo = ActivityThread.getPackageManager().getServiceInfo(runningServiceInfo.service, 4194304, UserHandle.getUserId(runningServiceInfo.uid));
                    if (serviceItem.mServiceInfo == null) {
                        Log.d("RunningService", "getServiceInfo returned null for: " + runningServiceInfo.service);
                        return false;
                    }
                } catch (RemoteException unused) {
                }
                serviceItem.mDisplayLabel = RunningState.makeLabel(packageManager, serviceItem.mRunningService.service.getClassName(), serviceItem.mServiceInfo);
                CharSequence charSequence = this.mDisplayLabel;
                this.mLabel = charSequence != null ? charSequence.toString() : null;
                serviceItem.mPackageInfo = serviceItem.mServiceInfo.applicationInfo;
                this.mServices.put(runningServiceInfo.service, serviceItem);
                z = true;
            } else {
                z = false;
            }
            serviceItem.mCurSeq = this.mCurSeq;
            serviceItem.mRunningService = runningServiceInfo;
            long j = runningServiceInfo.restarting == 0 ? runningServiceInfo.activeSince : -1;
            if (serviceItem.mActiveSince != j) {
                serviceItem.mActiveSince = j;
                z = true;
            }
            if (runningServiceInfo.clientPackage == null || runningServiceInfo.clientLabel == 0) {
                if (!serviceItem.mShownAsStarted) {
                    serviceItem.mShownAsStarted = true;
                    z = true;
                }
                serviceItem.mDescription = context.getResources().getString(C1715R.string.service_started_by_app);
            } else {
                if (serviceItem.mShownAsStarted) {
                    serviceItem.mShownAsStarted = false;
                    z = true;
                }
                try {
                    serviceItem.mDescription = context.getResources().getString(C1715R.string.service_client_name, new Object[]{packageManager.getResourcesForApplication(runningServiceInfo.clientPackage).getString(runningServiceInfo.clientLabel)});
                } catch (PackageManager.NameNotFoundException unused2) {
                    serviceItem.mDescription = null;
                }
            }
            return z;
        }

        /* access modifiers changed from: package-private */
        public boolean updateSize(Context context, long j, int i) {
            this.mSize = j * 1024;
            if (this.mCurSeq == i) {
                String formatShortFileSize = Formatter.formatShortFileSize(context, this.mSize);
                if (!formatShortFileSize.equals(this.mSizeStr)) {
                    this.mSizeStr = formatShortFileSize;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean buildDependencyChain(Context context, PackageManager packageManager, int i) {
            int size = this.mDependentProcesses.size();
            boolean z = false;
            for (int i2 = 0; i2 < size; i2++) {
                ProcessItem valueAt = this.mDependentProcesses.valueAt(i2);
                if (valueAt.mClient != this) {
                    valueAt.mClient = this;
                    z = true;
                }
                valueAt.mCurSeq = i;
                valueAt.ensureLabel(packageManager);
                z |= valueAt.buildDependencyChain(context, packageManager, i);
            }
            if (this.mLastNumDependentProcesses == this.mDependentProcesses.size()) {
                return z;
            }
            this.mLastNumDependentProcesses = this.mDependentProcesses.size();
            return true;
        }

        /* access modifiers changed from: package-private */
        public void addDependentProcesses(ArrayList<BaseItem> arrayList, ArrayList<ProcessItem> arrayList2) {
            int size = this.mDependentProcesses.size();
            for (int i = 0; i < size; i++) {
                ProcessItem valueAt = this.mDependentProcesses.valueAt(i);
                valueAt.addDependentProcesses(arrayList, arrayList2);
                arrayList.add(valueAt);
                if (valueAt.mPid > 0) {
                    arrayList2.add(valueAt);
                }
            }
        }
    }

    static class MergedItem extends BaseItem {
        final ArrayList<MergedItem> mChildren = new ArrayList<>();
        private int mLastNumProcesses = -1;
        private int mLastNumServices = -1;
        final ArrayList<ProcessItem> mOtherProcesses = new ArrayList<>();
        ProcessItem mProcess;
        final ArrayList<ServiceItem> mServices = new ArrayList<>();
        UserState mUser;

        MergedItem(int i) {
            super(false, i);
        }

        private void setDescription(Context context, int i, int i2) {
            if (this.mLastNumProcesses != i || this.mLastNumServices != i2) {
                this.mLastNumProcesses = i;
                this.mLastNumServices = i2;
                int i3 = C1715R.string.running_processes_item_description_s_s;
                if (i != 1) {
                    i3 = i2 != 1 ? C1715R.string.running_processes_item_description_p_p : C1715R.string.running_processes_item_description_p_s;
                } else if (i2 != 1) {
                    i3 = C1715R.string.running_processes_item_description_s_p;
                }
                this.mDescription = context.getResources().getString(i3, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
            }
        }

        /* access modifiers changed from: package-private */
        public boolean update(Context context, boolean z) {
            this.mBackground = z;
            if (this.mUser != null) {
                this.mPackageInfo = this.mChildren.get(0).mProcess.mPackageInfo;
                UserState userState = this.mUser;
                this.mLabel = userState != null ? userState.mLabel : null;
                this.mDisplayLabel = this.mLabel;
                this.mActiveSince = -1;
                int i = 0;
                int i2 = 0;
                for (int i3 = 0; i3 < this.mChildren.size(); i3++) {
                    MergedItem mergedItem = this.mChildren.get(i3);
                    i += mergedItem.mLastNumProcesses;
                    i2 += mergedItem.mLastNumServices;
                    long j = mergedItem.mActiveSince;
                    if (j >= 0 && this.mActiveSince < j) {
                        this.mActiveSince = j;
                    }
                }
                if (!this.mBackground) {
                    setDescription(context, i, i2);
                }
            } else {
                ProcessItem processItem = this.mProcess;
                this.mPackageInfo = processItem.mPackageInfo;
                this.mDisplayLabel = processItem.mDisplayLabel;
                this.mLabel = processItem.mLabel;
                if (!this.mBackground) {
                    setDescription(context, (processItem.mPid > 0 ? 1 : 0) + this.mOtherProcesses.size(), this.mServices.size());
                }
                this.mActiveSince = -1;
                for (int i4 = 0; i4 < this.mServices.size(); i4++) {
                    long j2 = this.mServices.get(i4).mActiveSince;
                    if (j2 >= 0 && this.mActiveSince < j2) {
                        this.mActiveSince = j2;
                    }
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean updateSize(Context context) {
            if (this.mUser != null) {
                this.mSize = 0;
                for (int i = 0; i < this.mChildren.size(); i++) {
                    MergedItem mergedItem = this.mChildren.get(i);
                    mergedItem.updateSize(context);
                    this.mSize += mergedItem.mSize;
                }
            } else {
                this.mSize = this.mProcess.mSize;
                for (int i2 = 0; i2 < this.mOtherProcesses.size(); i2++) {
                    this.mSize += this.mOtherProcesses.get(i2).mSize;
                }
            }
            String formatShortFileSize = Formatter.formatShortFileSize(context, this.mSize);
            if (!formatShortFileSize.equals(this.mSizeStr)) {
                this.mSizeStr = formatShortFileSize;
            }
            return false;
        }

        public Drawable loadIcon(Context context, RunningState runningState) {
            UserState userState = this.mUser;
            if (userState == null) {
                return super.loadIcon(context, runningState);
            }
            Drawable drawable = userState.mIcon;
            if (drawable == null) {
                return context.getDrawable(17302681);
            }
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState == null) {
                return this.mUser.mIcon;
            }
            return constantState.newDrawable();
        }
    }

    class ServiceProcessComparator implements Comparator<ProcessItem> {
        ServiceProcessComparator() {
        }

        public int compare(ProcessItem processItem, ProcessItem processItem2) {
            int i = processItem.mUserId;
            int i2 = processItem2.mUserId;
            if (i != i2) {
                int i3 = RunningState.this.mMyUserId;
                if (i == i3) {
                    return -1;
                }
                if (i2 != i3 && i < i2) {
                    return -1;
                }
                return 1;
            }
            boolean z = processItem.mIsStarted;
            if (z == processItem2.mIsStarted) {
                boolean z2 = processItem.mIsSystem;
                if (z2 == processItem2.mIsSystem) {
                    long j = processItem.mActiveSince;
                    long j2 = processItem2.mActiveSince;
                    if (j == j2) {
                        return 0;
                    }
                    if (j > j2) {
                        return -1;
                    }
                    return 1;
                } else if (z2) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (z) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    static CharSequence makeLabel(PackageManager packageManager, String str, PackageItemInfo packageItemInfo) {
        CharSequence loadLabel;
        if (packageItemInfo != null && ((packageItemInfo.labelRes != 0 || packageItemInfo.nonLocalizedLabel != null) && (loadLabel = packageItemInfo.loadLabel(packageManager)) != null)) {
            return loadLabel;
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf >= 0 ? str.substring(lastIndexOf + 1, str.length()) : str;
    }

    static RunningState getInstance(Context context) {
        RunningState runningState;
        synchronized (sGlobalLock) {
            if (sInstance == null) {
                sInstance = new RunningState(context);
            }
            runningState = sInstance;
        }
        return runningState;
    }

    private RunningState(Context context) {
        this.mApplicationContext = context.getApplicationContext();
        this.mAm = (ActivityManager) this.mApplicationContext.getSystemService("activity");
        this.mPm = this.mApplicationContext.getPackageManager();
        this.mUm = (UserManager) this.mApplicationContext.getSystemService("user");
        this.mMyUserId = UserHandle.myUserId();
        UserInfo userInfo = this.mUm.getUserInfo(this.mMyUserId);
        this.mHideManagedProfiles = userInfo == null || !userInfo.canHaveProfile();
        this.mResumed = false;
        this.mBackgroundThread = new HandlerThread("RunningState:Background");
        this.mBackgroundThread.start();
        this.mBackgroundHandler = new BackgroundHandler(this.mBackgroundThread.getLooper());
        this.mUmBroadcastReceiver.register(this.mApplicationContext);
    }

    /* access modifiers changed from: package-private */
    public void resume(OnRefreshUiListener onRefreshUiListener) {
        synchronized (this.mLock) {
            this.mResumed = true;
            this.mRefreshUiListener = onRefreshUiListener;
            boolean checkUsersChangedLocked = this.mUmBroadcastReceiver.checkUsersChangedLocked();
            boolean applyNewConfig = this.mInterestingConfigChanges.applyNewConfig(this.mApplicationContext.getResources());
            if (checkUsersChangedLocked || applyNewConfig) {
                this.mHaveData = false;
                this.mBackgroundHandler.removeMessages(1);
                this.mBackgroundHandler.removeMessages(2);
                this.mBackgroundHandler.sendEmptyMessage(1);
            }
            if (!this.mBackgroundHandler.hasMessages(2)) {
                this.mBackgroundHandler.sendEmptyMessage(2);
            }
            this.mHandler.sendEmptyMessage(4);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateNow() {
        synchronized (this.mLock) {
            this.mBackgroundHandler.removeMessages(2);
            this.mBackgroundHandler.sendEmptyMessage(2);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasData() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mHaveData;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0003 */
    /* JADX WARNING: Removed duplicated region for block: B:2:0x0003 A[LOOP:0: B:2:0x0003->B:13:0x0003, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void waitForData() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
        L_0x0003:
            boolean r1 = r4.mHaveData     // Catch:{ all -> 0x0011 }
            if (r1 != 0) goto L_0x000f
            java.lang.Object r1 = r4.mLock     // Catch:{ InterruptedException -> 0x0003 }
            r2 = 0
            r1.wait(r2)     // Catch:{ InterruptedException -> 0x0003 }
            goto L_0x0003
        L_0x000f:
            monitor-exit(r0)     // Catch:{ all -> 0x0011 }
            return
        L_0x0011:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0011 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.applications.RunningState.waitForData():void");
    }

    /* access modifiers changed from: package-private */
    public void pause() {
        synchronized (this.mLock) {
            this.mResumed = false;
            this.mRefreshUiListener = null;
            this.mHandler.removeMessages(4);
        }
    }

    private boolean isInterestingProcess(ActivityManager.RunningAppProcessInfo runningAppProcessInfo) {
        int i;
        int i2 = runningAppProcessInfo.flags;
        if ((i2 & 1) != 0) {
            return true;
        }
        if ((i2 & 2) != 0 || (i = runningAppProcessInfo.importance) < 100 || i >= 350 || runningAppProcessInfo.importanceReasonCode != 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void reset() {
        this.mServiceProcessesByName.clear();
        this.mServiceProcessesByPid.clear();
        this.mInterestingProcesses.clear();
        this.mRunningProcesses.clear();
        this.mProcessItems.clear();
        this.mAllProcessItems.clear();
    }

    private void addOtherUserItem(Context context, ArrayList<MergedItem> arrayList, SparseArray<MergedItem> sparseArray, MergedItem mergedItem) {
        MergedItem mergedItem2 = sparseArray.get(mergedItem.mUserId);
        if (mergedItem2 == null || mergedItem2.mCurSeq != this.mSequence) {
            UserInfo userInfo = this.mUm.getUserInfo(mergedItem.mUserId);
            if (userInfo != null) {
                if (!this.mHideManagedProfiles || !userInfo.isManagedProfile()) {
                    if (mergedItem2 == null) {
                        mergedItem2 = new MergedItem(mergedItem.mUserId);
                        sparseArray.put(mergedItem.mUserId, mergedItem2);
                    } else {
                        mergedItem2.mChildren.clear();
                    }
                    mergedItem2.mCurSeq = this.mSequence;
                    mergedItem2.mUser = new UserState();
                    UserState userState = mergedItem2.mUser;
                    userState.mInfo = userInfo;
                    userState.mIcon = Utils.getUserIcon(context, this.mUm, userInfo);
                    mergedItem2.mUser.mLabel = Utils.getUserLabel(context, userInfo);
                    arrayList.add(mergedItem2);
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        mergedItem2.mChildren.add(mergedItem);
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARNING: Removed duplicated region for block: B:338:0x0659  */
    /* JADX WARNING: Removed duplicated region for block: B:346:0x0680  */
    /* JADX WARNING: Removed duplicated region for block: B:348:0x0685  */
    /* JADX WARNING: Removed duplicated region for block: B:363:0x06cf  */
    /* JADX WARNING: Removed duplicated region for block: B:367:0x06d9 A[LOOP:29: B:365:0x06d1->B:367:0x06d9, LOOP_END] */
    public boolean update(android.content.Context r24, android.app.ActivityManager r25) {
        /*
            r23 = this;
            r0 = r23
            r1 = r24
            android.content.pm.PackageManager r2 = r24.getPackageManager()
            int r3 = r0.mSequence
            r4 = 1
            int r3 = r3 + r4
            r0.mSequence = r3
            r3 = 100
            r5 = r25
            java.util.List r3 = r5.getRunningServices(r3)
            if (r3 == 0) goto L_0x001d
            int r7 = r3.size()
            goto L_0x001e
        L_0x001d:
            r7 = 0
        L_0x001e:
            r8 = r7
            r7 = 0
        L_0x0020:
            if (r7 >= r8) goto L_0x0043
            java.lang.Object r9 = r3.get(r7)
            android.app.ActivityManager$RunningServiceInfo r9 = (android.app.ActivityManager.RunningServiceInfo) r9
            boolean r10 = r9.started
            if (r10 != 0) goto L_0x0034
            int r10 = r9.clientLabel
            if (r10 != 0) goto L_0x0034
            r3.remove(r7)
            goto L_0x003d
        L_0x0034:
            int r9 = r9.flags
            r9 = r9 & 8
            if (r9 == 0) goto L_0x0041
            r3.remove(r7)
        L_0x003d:
            int r7 = r7 + -1
            int r8 = r8 + -1
        L_0x0041:
            int r7 = r7 + r4
            goto L_0x0020
        L_0x0043:
            java.util.List r5 = r25.getRunningAppProcesses()
            if (r5 == 0) goto L_0x004e
            int r7 = r5.size()
            goto L_0x004f
        L_0x004e:
            r7 = 0
        L_0x004f:
            android.util.SparseArray<com.android.settings.applications.RunningState$AppProcessInfo> r9 = r0.mTmpAppProcesses
            r9.clear()
            r9 = 0
        L_0x0055:
            if (r9 >= r7) goto L_0x006c
            java.lang.Object r10 = r5.get(r9)
            android.app.ActivityManager$RunningAppProcessInfo r10 = (android.app.ActivityManager.RunningAppProcessInfo) r10
            android.util.SparseArray<com.android.settings.applications.RunningState$AppProcessInfo> r11 = r0.mTmpAppProcesses
            int r12 = r10.pid
            com.android.settings.applications.RunningState$AppProcessInfo r13 = new com.android.settings.applications.RunningState$AppProcessInfo
            r13.<init>(r10)
            r11.put(r12, r13)
            int r9 = r9 + 1
            goto L_0x0055
        L_0x006c:
            r9 = 0
        L_0x006d:
            r10 = 0
            if (r9 >= r8) goto L_0x0096
            java.lang.Object r12 = r3.get(r9)
            android.app.ActivityManager$RunningServiceInfo r12 = (android.app.ActivityManager.RunningServiceInfo) r12
            long r13 = r12.restarting
            int r10 = (r13 > r10 ? 1 : (r13 == r10 ? 0 : -1))
            if (r10 != 0) goto L_0x0093
            int r10 = r12.pid
            if (r10 <= 0) goto L_0x0093
            android.util.SparseArray<com.android.settings.applications.RunningState$AppProcessInfo> r11 = r0.mTmpAppProcesses
            java.lang.Object r10 = r11.get(r10)
            com.android.settings.applications.RunningState$AppProcessInfo r10 = (com.android.settings.applications.RunningState.AppProcessInfo) r10
            if (r10 == 0) goto L_0x0093
            r10.hasServices = r4
            boolean r11 = r12.foreground
            if (r11 == 0) goto L_0x0093
            r10.hasForegroundServices = r4
        L_0x0093:
            int r9 = r9 + 1
            goto L_0x006d
        L_0x0096:
            r9 = 0
            r12 = 0
        L_0x0098:
            if (r9 >= r8) goto L_0x015d
            java.lang.Object r13 = r3.get(r9)
            android.app.ActivityManager$RunningServiceInfo r13 = (android.app.ActivityManager.RunningServiceInfo) r13
            long r14 = r13.restarting
            int r14 = (r14 > r10 ? 1 : (r14 == r10 ? 0 : -1))
            if (r14 != 0) goto L_0x00ee
            int r14 = r13.pid
            if (r14 <= 0) goto L_0x00ee
            android.util.SparseArray<com.android.settings.applications.RunningState$AppProcessInfo> r15 = r0.mTmpAppProcesses
            java.lang.Object r14 = r15.get(r14)
            com.android.settings.applications.RunningState$AppProcessInfo r14 = (com.android.settings.applications.RunningState.AppProcessInfo) r14
            if (r14 == 0) goto L_0x00ee
            boolean r15 = r14.hasForegroundServices
            if (r15 != 0) goto L_0x00ee
            android.app.ActivityManager$RunningAppProcessInfo r14 = r14.info
            int r15 = r14.importance
            r6 = 300(0x12c, float:4.2E-43)
            if (r15 >= r6) goto L_0x00ee
            android.util.SparseArray<com.android.settings.applications.RunningState$AppProcessInfo> r6 = r0.mTmpAppProcesses
            int r14 = r14.importanceReasonPid
            java.lang.Object r6 = r6.get(r14)
            com.android.settings.applications.RunningState$AppProcessInfo r6 = (com.android.settings.applications.RunningState.AppProcessInfo) r6
        L_0x00ca:
            if (r6 == 0) goto L_0x00e8
            boolean r14 = r6.hasServices
            if (r14 != 0) goto L_0x00e6
            android.app.ActivityManager$RunningAppProcessInfo r14 = r6.info
            boolean r14 = r0.isInterestingProcess(r14)
            if (r14 == 0) goto L_0x00d9
            goto L_0x00e6
        L_0x00d9:
            android.util.SparseArray<com.android.settings.applications.RunningState$AppProcessInfo> r14 = r0.mTmpAppProcesses
            android.app.ActivityManager$RunningAppProcessInfo r6 = r6.info
            int r6 = r6.importanceReasonPid
            java.lang.Object r6 = r14.get(r6)
            com.android.settings.applications.RunningState$AppProcessInfo r6 = (com.android.settings.applications.RunningState.AppProcessInfo) r6
            goto L_0x00ca
        L_0x00e6:
            r6 = r4
            goto L_0x00e9
        L_0x00e8:
            r6 = 0
        L_0x00e9:
            if (r6 == 0) goto L_0x00ee
            r25 = r5
            goto L_0x0156
        L_0x00ee:
            android.util.SparseArray<java.util.HashMap<java.lang.String, com.android.settings.applications.RunningState$ProcessItem>> r6 = r0.mServiceProcessesByName
            int r14 = r13.uid
            java.lang.Object r6 = r6.get(r14)
            java.util.HashMap r6 = (java.util.HashMap) r6
            if (r6 != 0) goto L_0x0106
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            android.util.SparseArray<java.util.HashMap<java.lang.String, com.android.settings.applications.RunningState$ProcessItem>> r14 = r0.mServiceProcessesByName
            int r15 = r13.uid
            r14.put(r15, r6)
        L_0x0106:
            java.lang.String r14 = r13.process
            java.lang.Object r14 = r6.get(r14)
            com.android.settings.applications.RunningState$ProcessItem r14 = (com.android.settings.applications.RunningState.ProcessItem) r14
            if (r14 != 0) goto L_0x011f
            com.android.settings.applications.RunningState$ProcessItem r14 = new com.android.settings.applications.RunningState$ProcessItem
            int r12 = r13.uid
            java.lang.String r15 = r13.process
            r14.<init>(r1, r12, r15)
            java.lang.String r12 = r13.process
            r6.put(r12, r14)
            r12 = r4
        L_0x011f:
            int r6 = r14.mCurSeq
            int r15 = r0.mSequence
            r25 = r5
            if (r6 == r15) goto L_0x0151
            long r4 = r13.restarting
            int r4 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r4 != 0) goto L_0x0130
            int r4 = r13.pid
            goto L_0x0131
        L_0x0130:
            r4 = 0
        L_0x0131:
            int r5 = r14.mPid
            if (r4 == r5) goto L_0x0148
            if (r5 == r4) goto L_0x0147
            if (r5 == 0) goto L_0x013e
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r12 = r0.mServiceProcessesByPid
            r12.remove(r5)
        L_0x013e:
            if (r4 == 0) goto L_0x0145
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r5 = r0.mServiceProcessesByPid
            r5.put(r4, r14)
        L_0x0145:
            r14.mPid = r4
        L_0x0147:
            r12 = 1
        L_0x0148:
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r4 = r14.mDependentProcesses
            r4.clear()
            int r4 = r0.mSequence
            r14.mCurSeq = r4
        L_0x0151:
            boolean r4 = r14.updateService(r1, r13)
            r12 = r12 | r4
        L_0x0156:
            int r9 = r9 + 1
            r5 = r25
            r4 = 1
            goto L_0x0098
        L_0x015d:
            r25 = r5
            r4 = r12
            r3 = 0
        L_0x0161:
            if (r3 >= r7) goto L_0x01c9
            r5 = r25
            java.lang.Object r8 = r5.get(r3)
            android.app.ActivityManager$RunningAppProcessInfo r8 = (android.app.ActivityManager.RunningAppProcessInfo) r8
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r9 = r0.mServiceProcessesByPid
            int r12 = r8.pid
            java.lang.Object r9 = r9.get(r12)
            com.android.settings.applications.RunningState$ProcessItem r9 = (com.android.settings.applications.RunningState.ProcessItem) r9
            if (r9 != 0) goto L_0x019c
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r9 = r0.mRunningProcesses
            int r12 = r8.pid
            java.lang.Object r9 = r9.get(r12)
            com.android.settings.applications.RunningState$ProcessItem r9 = (com.android.settings.applications.RunningState.ProcessItem) r9
            if (r9 != 0) goto L_0x0197
            com.android.settings.applications.RunningState$ProcessItem r4 = new com.android.settings.applications.RunningState$ProcessItem
            int r9 = r8.uid
            java.lang.String r12 = r8.processName
            r4.<init>(r1, r9, r12)
            int r9 = r8.pid
            r4.mPid = r9
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r12 = r0.mRunningProcesses
            r12.put(r9, r4)
            r9 = r4
            r4 = 1
        L_0x0197:
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r12 = r9.mDependentProcesses
            r12.clear()
        L_0x019c:
            boolean r12 = r0.isInterestingProcess(r8)
            if (r12 == 0) goto L_0x01bb
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r12 = r0.mInterestingProcesses
            boolean r12 = r12.contains(r9)
            if (r12 != 0) goto L_0x01b0
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r4 = r0.mInterestingProcesses
            r4.add(r9)
            r4 = 1
        L_0x01b0:
            int r12 = r0.mSequence
            r9.mCurSeq = r12
            r6 = 1
            r9.mInteresting = r6
            r9.ensureLabel(r2)
            goto L_0x01be
        L_0x01bb:
            r12 = 0
            r9.mInteresting = r12
        L_0x01be:
            int r12 = r0.mSequence
            r9.mRunningSeq = r12
            r9.mRunningProcessInfo = r8
            int r3 = r3 + 1
            r25 = r5
            goto L_0x0161
        L_0x01c9:
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r3 = r0.mRunningProcesses
            int r3 = r3.size()
            r5 = r4
            r4 = r3
            r3 = 0
        L_0x01d2:
            r7 = 0
            if (r3 >= r4) goto L_0x0217
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r8 = r0.mRunningProcesses
            java.lang.Object r8 = r8.valueAt(r3)
            com.android.settings.applications.RunningState$ProcessItem r8 = (com.android.settings.applications.RunningState.ProcessItem) r8
            int r9 = r8.mRunningSeq
            int r12 = r0.mSequence
            if (r9 != r12) goto L_0x020a
            android.app.ActivityManager$RunningAppProcessInfo r9 = r8.mRunningProcessInfo
            int r9 = r9.importanceReasonPid
            if (r9 == 0) goto L_0x0205
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r7 = r0.mServiceProcessesByPid
            java.lang.Object r7 = r7.get(r9)
            com.android.settings.applications.RunningState$ProcessItem r7 = (com.android.settings.applications.RunningState.ProcessItem) r7
            if (r7 != 0) goto L_0x01fb
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r7 = r0.mRunningProcesses
            java.lang.Object r7 = r7.get(r9)
            com.android.settings.applications.RunningState$ProcessItem r7 = (com.android.settings.applications.RunningState.ProcessItem) r7
        L_0x01fb:
            if (r7 == 0) goto L_0x0207
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r7 = r7.mDependentProcesses
            int r9 = r8.mPid
            r7.put(r9, r8)
            goto L_0x0207
        L_0x0205:
            r8.mClient = r7
        L_0x0207:
            int r3 = r3 + 1
            goto L_0x01d2
        L_0x020a:
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r5 = r0.mRunningProcesses
            int r7 = r5.keyAt(r3)
            r5.remove(r7)
            int r4 = r4 + -1
            r5 = 1
            goto L_0x01d2
        L_0x0217:
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r3 = r0.mInterestingProcesses
            int r3 = r3.size()
            r4 = r3
            r3 = 0
        L_0x021f:
            if (r3 >= r4) goto L_0x0244
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r8 = r0.mInterestingProcesses
            java.lang.Object r8 = r8.get(r3)
            com.android.settings.applications.RunningState$ProcessItem r8 = (com.android.settings.applications.RunningState.ProcessItem) r8
            boolean r9 = r8.mInteresting
            if (r9 == 0) goto L_0x0237
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r9 = r0.mRunningProcesses
            int r8 = r8.mPid
            java.lang.Object r8 = r9.get(r8)
            if (r8 != 0) goto L_0x0241
        L_0x0237:
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r5 = r0.mInterestingProcesses
            r5.remove(r3)
            int r3 = r3 + -1
            int r4 = r4 + -1
            r5 = 1
        L_0x0241:
            r6 = 1
            int r3 = r3 + r6
            goto L_0x021f
        L_0x0244:
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r3 = r0.mServiceProcessesByPid
            int r3 = r3.size()
            r4 = 0
        L_0x024b:
            if (r4 >= r3) goto L_0x0263
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r8 = r0.mServiceProcessesByPid
            java.lang.Object r8 = r8.valueAt(r4)
            com.android.settings.applications.RunningState$ProcessItem r8 = (com.android.settings.applications.RunningState.ProcessItem) r8
            int r9 = r8.mCurSeq
            int r12 = r0.mSequence
            if (r9 != r12) goto L_0x0260
            boolean r8 = r8.buildDependencyChain(r1, r2, r12)
            r5 = r5 | r8
        L_0x0260:
            int r4 = r4 + 1
            goto L_0x024b
        L_0x0263:
            r4 = r7
            r3 = 0
        L_0x0265:
            android.util.SparseArray<java.util.HashMap<java.lang.String, com.android.settings.applications.RunningState$ProcessItem>> r8 = r0.mServiceProcessesByName
            int r8 = r8.size()
            if (r3 >= r8) goto L_0x02e8
            android.util.SparseArray<java.util.HashMap<java.lang.String, com.android.settings.applications.RunningState$ProcessItem>> r8 = r0.mServiceProcessesByName
            java.lang.Object r8 = r8.valueAt(r3)
            java.util.HashMap r8 = (java.util.HashMap) r8
            java.util.Collection r9 = r8.values()
            java.util.Iterator r9 = r9.iterator()
        L_0x027d:
            boolean r12 = r9.hasNext()
            if (r12 == 0) goto L_0x02e4
            java.lang.Object r12 = r9.next()
            com.android.settings.applications.RunningState$ProcessItem r12 = (com.android.settings.applications.RunningState.ProcessItem) r12
            int r13 = r12.mCurSeq
            int r14 = r0.mSequence
            if (r13 != r14) goto L_0x02bc
            r12.ensureLabel(r2)
            int r13 = r12.mPid
            if (r13 != 0) goto L_0x029b
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r13 = r12.mDependentProcesses
            r13.clear()
        L_0x029b:
            java.util.HashMap<android.content.ComponentName, com.android.settings.applications.RunningState$ServiceItem> r12 = r12.mServices
            java.util.Collection r12 = r12.values()
            java.util.Iterator r12 = r12.iterator()
        L_0x02a5:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x027d
            java.lang.Object r13 = r12.next()
            com.android.settings.applications.RunningState$ServiceItem r13 = (com.android.settings.applications.RunningState.ServiceItem) r13
            int r13 = r13.mCurSeq
            int r14 = r0.mSequence
            if (r13 == r14) goto L_0x02a5
            r12.remove()
            r5 = 1
            goto L_0x02a5
        L_0x02bc:
            r9.remove()
            int r5 = r8.size()
            if (r5 != 0) goto L_0x02d9
            if (r4 != 0) goto L_0x02cc
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
        L_0x02cc:
            android.util.SparseArray<java.util.HashMap<java.lang.String, com.android.settings.applications.RunningState$ProcessItem>> r5 = r0.mServiceProcessesByName
            int r5 = r5.keyAt(r3)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r4.add(r5)
        L_0x02d9:
            int r5 = r12.mPid
            if (r5 == 0) goto L_0x02e2
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r12 = r0.mServiceProcessesByPid
            r12.remove(r5)
        L_0x02e2:
            r5 = 1
            goto L_0x027d
        L_0x02e4:
            int r3 = r3 + 1
            goto L_0x0265
        L_0x02e8:
            if (r4 == 0) goto L_0x0303
            r2 = 0
        L_0x02eb:
            int r3 = r4.size()
            if (r2 >= r3) goto L_0x0303
            java.lang.Object r3 = r4.get(r2)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            android.util.SparseArray<java.util.HashMap<java.lang.String, com.android.settings.applications.RunningState$ProcessItem>> r8 = r0.mServiceProcessesByName
            r8.remove(r3)
            int r2 = r2 + 1
            goto L_0x02eb
        L_0x0303:
            if (r5 == 0) goto L_0x04b9
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r3 = 0
        L_0x030b:
            android.util.SparseArray<java.util.HashMap<java.lang.String, com.android.settings.applications.RunningState$ProcessItem>> r4 = r0.mServiceProcessesByName
            int r4 = r4.size()
            if (r3 >= r4) goto L_0x0380
            android.util.SparseArray<java.util.HashMap<java.lang.String, com.android.settings.applications.RunningState$ProcessItem>> r4 = r0.mServiceProcessesByName
            java.lang.Object r4 = r4.valueAt(r3)
            java.util.HashMap r4 = (java.util.HashMap) r4
            java.util.Collection r4 = r4.values()
            java.util.Iterator r4 = r4.iterator()
        L_0x0323:
            boolean r8 = r4.hasNext()
            if (r8 == 0) goto L_0x037c
            java.lang.Object r8 = r4.next()
            com.android.settings.applications.RunningState$ProcessItem r8 = (com.android.settings.applications.RunningState.ProcessItem) r8
            r9 = 0
            r8.mIsSystem = r9
            r6 = 1
            r8.mIsStarted = r6
            r12 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r8.mActiveSince = r12
            java.util.HashMap<android.content.ComponentName, com.android.settings.applications.RunningState$ServiceItem> r9 = r8.mServices
            java.util.Collection r9 = r9.values()
            java.util.Iterator r9 = r9.iterator()
        L_0x0346:
            boolean r12 = r9.hasNext()
            if (r12 == 0) goto L_0x0377
            java.lang.Object r12 = r9.next()
            com.android.settings.applications.RunningState$ServiceItem r12 = (com.android.settings.applications.RunningState.ServiceItem) r12
            android.content.pm.ServiceInfo r13 = r12.mServiceInfo
            if (r13 == 0) goto L_0x0360
            android.content.pm.ApplicationInfo r13 = r13.applicationInfo
            int r13 = r13.flags
            r6 = 1
            r13 = r13 & r6
            if (r13 == 0) goto L_0x0360
            r8.mIsSystem = r6
        L_0x0360:
            android.app.ActivityManager$RunningServiceInfo r12 = r12.mRunningService
            if (r12 == 0) goto L_0x0375
            int r13 = r12.clientLabel
            if (r13 == 0) goto L_0x0375
            r13 = 0
            r8.mIsStarted = r13
            long r13 = r8.mActiveSince
            long r6 = r12.activeSince
            int r12 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1))
            if (r12 <= 0) goto L_0x0375
            r8.mActiveSince = r6
        L_0x0375:
            r7 = 0
            goto L_0x0346
        L_0x0377:
            r2.add(r8)
            r7 = 0
            goto L_0x0323
        L_0x037c:
            int r3 = r3 + 1
            r7 = 0
            goto L_0x030b
        L_0x0380:
            com.android.settings.applications.RunningState$ServiceProcessComparator r3 = r0.mServiceProcessComparator
            java.util.Collections.sort(r2, r3)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r6 = r0.mProcessItems
            r6.clear()
            r7 = 0
        L_0x0395:
            int r6 = r2.size()
            if (r7 >= r6) goto L_0x043f
            java.lang.Object r6 = r2.get(r7)
            com.android.settings.applications.RunningState$ProcessItem r6 = (com.android.settings.applications.RunningState.ProcessItem) r6
            r8 = 0
            r6.mNeedDivider = r8
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r8 = r0.mProcessItems
            int r8 = r8.size()
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r9 = r0.mProcessItems
            r6.addDependentProcesses(r3, r9)
            r3.add(r6)
            int r9 = r6.mPid
            if (r9 <= 0) goto L_0x03bb
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r9 = r0.mProcessItems
            r9.add(r6)
        L_0x03bb:
            java.util.HashMap<android.content.ComponentName, com.android.settings.applications.RunningState$ServiceItem> r9 = r6.mServices
            java.util.Collection r9 = r9.values()
            java.util.Iterator r9 = r9.iterator()
            r12 = 0
            r13 = 0
        L_0x03c7:
            boolean r14 = r9.hasNext()
            if (r14 == 0) goto L_0x03e1
            java.lang.Object r14 = r9.next()
            com.android.settings.applications.RunningState$ServiceItem r14 = (com.android.settings.applications.RunningState.ServiceItem) r14
            r14.mNeedDivider = r12
            r3.add(r14)
            com.android.settings.applications.RunningState$MergedItem r12 = r14.mMergedItem
            if (r12 == 0) goto L_0x03df
            com.android.settings.applications.RunningState$MergedItem r12 = r14.mMergedItem
            r13 = r12
        L_0x03df:
            r12 = 1
            goto L_0x03c7
        L_0x03e1:
            com.android.settings.applications.RunningState$MergedItem r9 = new com.android.settings.applications.RunningState$MergedItem
            int r12 = r6.mUserId
            r9.<init>(r12)
            java.util.HashMap<android.content.ComponentName, com.android.settings.applications.RunningState$ServiceItem> r12 = r6.mServices
            java.util.Collection r12 = r12.values()
            java.util.Iterator r12 = r12.iterator()
        L_0x03f2:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x0406
            java.lang.Object r13 = r12.next()
            com.android.settings.applications.RunningState$ServiceItem r13 = (com.android.settings.applications.RunningState.ServiceItem) r13
            java.util.ArrayList<com.android.settings.applications.RunningState$ServiceItem> r14 = r9.mServices
            r14.add(r13)
            r13.mMergedItem = r9
            goto L_0x03f2
        L_0x0406:
            r9.mProcess = r6
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r6 = r9.mOtherProcesses
            r6.clear()
        L_0x040d:
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r6 = r0.mProcessItems
            int r6 = r6.size()
            r12 = 1
            int r13 = r6 + -1
            if (r8 >= r13) goto L_0x0428
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r12 = r9.mOtherProcesses
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r13 = r0.mProcessItems
            java.lang.Object r13 = r13.get(r8)
            com.android.settings.applications.RunningState$ProcessItem r13 = (com.android.settings.applications.RunningState.ProcessItem) r13
            r12.add(r13)
            int r8 = r8 + 1
            goto L_0x040d
        L_0x0428:
            r8 = 0
            r9.update(r1, r8)
            int r8 = r9.mUserId
            int r12 = r0.mMyUserId
            if (r8 == r12) goto L_0x0438
            android.util.SparseArray<com.android.settings.applications.RunningState$MergedItem> r8 = r0.mOtherUserMergedItems
            r0.addOtherUserItem(r1, r4, r8, r9)
            goto L_0x043b
        L_0x0438:
            r4.add(r9)
        L_0x043b:
            int r7 = r7 + 1
            goto L_0x0395
        L_0x043f:
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r2 = r0.mInterestingProcesses
            int r2 = r2.size()
            r7 = 0
        L_0x0446:
            if (r7 >= r2) goto L_0x048c
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r8 = r0.mInterestingProcesses
            java.lang.Object r8 = r8.get(r7)
            com.android.settings.applications.RunningState$ProcessItem r8 = (com.android.settings.applications.RunningState.ProcessItem) r8
            com.android.settings.applications.RunningState$ProcessItem r9 = r8.mClient
            if (r9 != 0) goto L_0x0489
            java.util.HashMap<android.content.ComponentName, com.android.settings.applications.RunningState$ServiceItem> r9 = r8.mServices
            int r9 = r9.size()
            if (r9 > 0) goto L_0x0489
            com.android.settings.applications.RunningState$MergedItem r9 = r8.mMergedItem
            if (r9 != 0) goto L_0x046d
            com.android.settings.applications.RunningState$MergedItem r9 = new com.android.settings.applications.RunningState$MergedItem
            int r12 = r8.mUserId
            r9.<init>(r12)
            r8.mMergedItem = r9
            com.android.settings.applications.RunningState$MergedItem r9 = r8.mMergedItem
            r9.mProcess = r8
        L_0x046d:
            com.android.settings.applications.RunningState$MergedItem r9 = r8.mMergedItem
            r12 = 0
            r9.update(r1, r12)
            com.android.settings.applications.RunningState$MergedItem r9 = r8.mMergedItem
            int r13 = r9.mUserId
            int r14 = r0.mMyUserId
            if (r13 == r14) goto L_0x0481
            android.util.SparseArray<com.android.settings.applications.RunningState$MergedItem> r13 = r0.mOtherUserMergedItems
            r0.addOtherUserItem(r1, r4, r13, r9)
            goto L_0x0484
        L_0x0481:
            r4.add(r12, r9)
        L_0x0484:
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r9 = r0.mProcessItems
            r9.add(r8)
        L_0x0489:
            int r7 = r7 + 1
            goto L_0x0446
        L_0x048c:
            android.util.SparseArray<com.android.settings.applications.RunningState$MergedItem> r2 = r0.mOtherUserMergedItems
            int r2 = r2.size()
            r7 = 0
        L_0x0493:
            if (r7 >= r2) goto L_0x04ac
            android.util.SparseArray<com.android.settings.applications.RunningState$MergedItem> r8 = r0.mOtherUserMergedItems
            java.lang.Object r8 = r8.valueAt(r7)
            com.android.settings.applications.RunningState$MergedItem r8 = (com.android.settings.applications.RunningState.MergedItem) r8
            int r9 = r8.mCurSeq
            int r12 = r0.mSequence
            if (r9 != r12) goto L_0x04a8
            r9 = 0
            r8.update(r1, r9)
            goto L_0x04a9
        L_0x04a8:
            r9 = 0
        L_0x04a9:
            int r7 = r7 + 1
            goto L_0x0493
        L_0x04ac:
            r9 = 0
            java.lang.Object r2 = r0.mLock
            monitor-enter(r2)
            r0.mItems = r3     // Catch:{ all -> 0x04b6 }
            r0.mMergedItems = r4     // Catch:{ all -> 0x04b6 }
            monitor-exit(r2)     // Catch:{ all -> 0x04b6 }
            goto L_0x04ba
        L_0x04b6:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x04b6 }
            throw r0
        L_0x04b9:
            r9 = 0
        L_0x04ba:
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r2 = r0.mAllProcessItems
            r2.clear()
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r2 = r0.mAllProcessItems
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r3 = r0.mProcessItems
            r2.addAll(r3)
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r2 = r0.mRunningProcesses
            int r2 = r2.size()
            r3 = r9
            r4 = r3
            r7 = r4
            r8 = r7
        L_0x04d0:
            r12 = 200(0xc8, float:2.8E-43)
            r13 = 400(0x190, float:5.6E-43)
            if (r3 >= r2) goto L_0x0524
            android.util.SparseArray<com.android.settings.applications.RunningState$ProcessItem> r14 = r0.mRunningProcesses
            java.lang.Object r14 = r14.valueAt(r3)
            com.android.settings.applications.RunningState$ProcessItem r14 = (com.android.settings.applications.RunningState.ProcessItem) r14
            int r15 = r14.mCurSeq
            int r6 = r0.mSequence
            if (r15 == r6) goto L_0x051f
            android.app.ActivityManager$RunningAppProcessInfo r6 = r14.mRunningProcessInfo
            int r6 = r6.importance
            if (r6 < r13) goto L_0x04f2
            int r7 = r7 + 1
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r6 = r0.mAllProcessItems
            r6.add(r14)
            goto L_0x0521
        L_0x04f2:
            if (r6 > r12) goto L_0x04fc
            int r8 = r8 + 1
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r6 = r0.mAllProcessItems
            r6.add(r14)
            goto L_0x0521
        L_0x04fc:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r12 = "Unknown non-service process: "
            r6.append(r12)
            java.lang.String r12 = r14.mProcessName
            r6.append(r12)
            java.lang.String r12 = " #"
            r6.append(r12)
            int r12 = r14.mPid
            r6.append(r12)
            java.lang.String r6 = r6.toString()
            java.lang.String r12 = "RunningState"
            android.util.Log.i(r12, r6)
            goto L_0x0521
        L_0x051f:
            int r4 = r4 + 1
        L_0x0521:
            int r3 = r3 + 1
            goto L_0x04d0
        L_0x0524:
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r2 = r0.mAllProcessItems     // Catch:{ RemoteException -> 0x0642 }
            int r2 = r2.size()     // Catch:{ RemoteException -> 0x0642 }
            int[] r3 = new int[r2]     // Catch:{ RemoteException -> 0x0642 }
            r6 = r9
        L_0x052d:
            if (r6 >= r2) goto L_0x0549
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r14 = r0.mAllProcessItems     // Catch:{ RemoteException -> 0x053e }
            java.lang.Object r14 = r14.get(r6)     // Catch:{ RemoteException -> 0x053e }
            com.android.settings.applications.RunningState$ProcessItem r14 = (com.android.settings.applications.RunningState.ProcessItem) r14     // Catch:{ RemoteException -> 0x053e }
            int r14 = r14.mPid     // Catch:{ RemoteException -> 0x053e }
            r3[r6] = r14     // Catch:{ RemoteException -> 0x053e }
            int r6 = r6 + 1
            goto L_0x052d
        L_0x053e:
            r20 = r5
            r14 = r10
            r16 = r14
            r18 = r16
            r10 = 0
            r11 = r9
            goto L_0x064b
        L_0x0549:
            android.app.IActivityManager r2 = android.app.ActivityManager.getService()     // Catch:{ RemoteException -> 0x0642 }
            long[] r2 = r2.getProcessPss(r3)     // Catch:{ RemoteException -> 0x0642 }
            r20 = r5
            r5 = r9
            r6 = r5
            r14 = r10
            r16 = r14
            r18 = r16
            r10 = 0
            r11 = r6
        L_0x055c:
            int r9 = r3.length     // Catch:{ RemoteException -> 0x064b }
            if (r5 >= r9) goto L_0x064b
            java.util.ArrayList<com.android.settings.applications.RunningState$ProcessItem> r9 = r0.mAllProcessItems     // Catch:{ RemoteException -> 0x064b }
            java.lang.Object r9 = r9.get(r5)     // Catch:{ RemoteException -> 0x064b }
            com.android.settings.applications.RunningState$ProcessItem r9 = (com.android.settings.applications.RunningState.ProcessItem) r9     // Catch:{ RemoteException -> 0x064b }
            r12 = r2[r5]     // Catch:{ RemoteException -> 0x064b }
            r21 = r2
            int r2 = r0.mSequence     // Catch:{ RemoteException -> 0x064b }
            boolean r2 = r9.updateSize(r1, r12, r2)     // Catch:{ RemoteException -> 0x064b }
            r20 = r20 | r2
            int r2 = r9.mCurSeq     // Catch:{ RemoteException -> 0x064b }
            int r12 = r0.mSequence     // Catch:{ RemoteException -> 0x064b }
            if (r2 != r12) goto L_0x0582
            long r12 = r9.mSize     // Catch:{ RemoteException -> 0x064b }
            long r18 = r18 + r12
            r22 = r3
            r3 = r6
            goto L_0x0635
        L_0x0582:
            android.app.ActivityManager$RunningAppProcessInfo r2 = r9.mRunningProcessInfo     // Catch:{ RemoteException -> 0x064b }
            int r2 = r2.importance     // Catch:{ RemoteException -> 0x064b }
            r12 = 400(0x190, float:5.6E-43)
            if (r2 < r12) goto L_0x0626
            long r12 = r9.mSize     // Catch:{ RemoteException -> 0x064b }
            long r12 = r12 + r14
            if (r10 == 0) goto L_0x05af
            com.android.settings.applications.RunningState$MergedItem r2 = new com.android.settings.applications.RunningState$MergedItem     // Catch:{ RemoteException -> 0x0624 }
            int r14 = r9.mUserId     // Catch:{ RemoteException -> 0x0624 }
            r2.<init>(r14)     // Catch:{ RemoteException -> 0x0624 }
            r9.mMergedItem = r2     // Catch:{ RemoteException -> 0x0624 }
            com.android.settings.applications.RunningState$MergedItem r14 = r9.mMergedItem     // Catch:{ RemoteException -> 0x0624 }
            r14.mProcess = r9     // Catch:{ RemoteException -> 0x0624 }
            int r9 = r2.mUserId     // Catch:{ RemoteException -> 0x0624 }
            int r14 = r0.mMyUserId     // Catch:{ RemoteException -> 0x0624 }
            if (r9 == r14) goto L_0x05a4
            r9 = 1
            goto L_0x05a5
        L_0x05a4:
            r9 = 0
        L_0x05a5:
            r9 = r9 | r11
            r10.add(r2)     // Catch:{ RemoteException -> 0x05ac }
            r22 = r3
            goto L_0x05cf
        L_0x05ac:
            r11 = r9
            goto L_0x0624
        L_0x05af:
            java.util.ArrayList<com.android.settings.applications.RunningState$MergedItem> r2 = r0.mBackgroundItems     // Catch:{ RemoteException -> 0x0624 }
            int r2 = r2.size()     // Catch:{ RemoteException -> 0x0624 }
            if (r6 >= r2) goto L_0x05d1
            java.util.ArrayList<com.android.settings.applications.RunningState$MergedItem> r2 = r0.mBackgroundItems     // Catch:{ RemoteException -> 0x0624 }
            java.lang.Object r2 = r2.get(r6)     // Catch:{ RemoteException -> 0x0624 }
            com.android.settings.applications.RunningState$MergedItem r2 = (com.android.settings.applications.RunningState.MergedItem) r2     // Catch:{ RemoteException -> 0x0624 }
            com.android.settings.applications.RunningState$ProcessItem r2 = r2.mProcess     // Catch:{ RemoteException -> 0x0624 }
            if (r2 == r9) goto L_0x05c4
            goto L_0x05d1
        L_0x05c4:
            java.util.ArrayList<com.android.settings.applications.RunningState$MergedItem> r2 = r0.mBackgroundItems     // Catch:{ RemoteException -> 0x0624 }
            java.lang.Object r2 = r2.get(r6)     // Catch:{ RemoteException -> 0x0624 }
            com.android.settings.applications.RunningState$MergedItem r2 = (com.android.settings.applications.RunningState.MergedItem) r2     // Catch:{ RemoteException -> 0x0624 }
            r22 = r3
            r9 = r11
        L_0x05cf:
            r3 = 1
            goto L_0x0614
        L_0x05d1:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ RemoteException -> 0x0624 }
            r2.<init>(r7)     // Catch:{ RemoteException -> 0x0624 }
            r10 = 0
        L_0x05d7:
            if (r10 >= r6) goto L_0x05f5
            java.util.ArrayList<com.android.settings.applications.RunningState$MergedItem> r14 = r0.mBackgroundItems     // Catch:{ RemoteException -> 0x0623 }
            java.lang.Object r14 = r14.get(r10)     // Catch:{ RemoteException -> 0x0623 }
            com.android.settings.applications.RunningState$MergedItem r14 = (com.android.settings.applications.RunningState.MergedItem) r14     // Catch:{ RemoteException -> 0x0623 }
            int r15 = r14.mUserId     // Catch:{ RemoteException -> 0x0623 }
            r22 = r3
            int r3 = r0.mMyUserId     // Catch:{ RemoteException -> 0x0623 }
            if (r15 == r3) goto L_0x05eb
            r3 = 1
            goto L_0x05ec
        L_0x05eb:
            r3 = 0
        L_0x05ec:
            r11 = r11 | r3
            r2.add(r14)     // Catch:{ RemoteException -> 0x0623 }
            int r10 = r10 + 1
            r3 = r22
            goto L_0x05d7
        L_0x05f5:
            r22 = r3
            com.android.settings.applications.RunningState$MergedItem r3 = new com.android.settings.applications.RunningState$MergedItem     // Catch:{ RemoteException -> 0x0623 }
            int r10 = r9.mUserId     // Catch:{ RemoteException -> 0x0623 }
            r3.<init>(r10)     // Catch:{ RemoteException -> 0x0623 }
            r9.mMergedItem = r3     // Catch:{ RemoteException -> 0x0623 }
            com.android.settings.applications.RunningState$MergedItem r10 = r9.mMergedItem     // Catch:{ RemoteException -> 0x0623 }
            r10.mProcess = r9     // Catch:{ RemoteException -> 0x0623 }
            int r9 = r3.mUserId     // Catch:{ RemoteException -> 0x0623 }
            int r10 = r0.mMyUserId     // Catch:{ RemoteException -> 0x0623 }
            if (r9 == r10) goto L_0x060c
            r9 = 1
            goto L_0x060d
        L_0x060c:
            r9 = 0
        L_0x060d:
            r9 = r9 | r11
            r2.add(r3)     // Catch:{ RemoteException -> 0x0621 }
            r10 = r2
            r2 = r3
            goto L_0x05cf
        L_0x0614:
            r2.update(r1, r3)     // Catch:{ RemoteException -> 0x05ac }
            r3 = r6
            r2.updateSize(r1)     // Catch:{ RemoteException -> 0x05ac }
            int r2 = r3 + 1
            r3 = r2
            r11 = r9
            r14 = r12
            goto L_0x0635
        L_0x0621:
            r10 = r2
            goto L_0x05ac
        L_0x0623:
            r10 = r2
        L_0x0624:
            r14 = r12
            goto L_0x064b
        L_0x0626:
            r22 = r3
            r3 = r6
            android.app.ActivityManager$RunningAppProcessInfo r2 = r9.mRunningProcessInfo     // Catch:{ RemoteException -> 0x064b }
            int r2 = r2.importance     // Catch:{ RemoteException -> 0x064b }
            r12 = 200(0xc8, float:2.8E-43)
            if (r2 > r12) goto L_0x0635
            long r12 = r9.mSize     // Catch:{ RemoteException -> 0x064b }
            long r16 = r16 + r12
        L_0x0635:
            int r5 = r5 + 1
            r6 = r3
            r2 = r21
            r3 = r22
            r12 = 200(0xc8, float:2.8E-43)
            r13 = 400(0x190, float:5.6E-43)
            goto L_0x055c
        L_0x0642:
            r20 = r5
            r14 = r10
            r16 = r14
            r18 = r16
            r10 = 0
            r11 = 0
        L_0x064b:
            r2 = r16
            r12 = r18
            if (r10 != 0) goto L_0x0680
            java.util.ArrayList<com.android.settings.applications.RunningState$MergedItem> r5 = r0.mBackgroundItems
            int r5 = r5.size()
            if (r5 <= r7) goto L_0x0680
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r7)
            r9 = 0
        L_0x065f:
            if (r9 >= r7) goto L_0x067d
            java.util.ArrayList<com.android.settings.applications.RunningState$MergedItem> r10 = r0.mBackgroundItems
            java.lang.Object r10 = r10.get(r9)
            com.android.settings.applications.RunningState$MergedItem r10 = (com.android.settings.applications.RunningState.MergedItem) r10
            int r6 = r10.mUserId
            r16 = r12
            int r12 = r0.mMyUserId
            if (r6 == r12) goto L_0x0673
            r6 = 1
            goto L_0x0674
        L_0x0673:
            r6 = 0
        L_0x0674:
            r11 = r11 | r6
            r5.add(r10)
            int r9 = r9 + 1
            r12 = r16
            goto L_0x065f
        L_0x067d:
            r16 = r12
            goto L_0x0683
        L_0x0680:
            r16 = r12
            r5 = r10
        L_0x0683:
            if (r5 == 0) goto L_0x06cf
            if (r11 != 0) goto L_0x0689
            r9 = r5
            goto L_0x06d0
        L_0x0689:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            int r6 = r5.size()
            r10 = 0
        L_0x0693:
            if (r10 >= r6) goto L_0x06ad
            java.lang.Object r11 = r5.get(r10)
            com.android.settings.applications.RunningState$MergedItem r11 = (com.android.settings.applications.RunningState.MergedItem) r11
            int r12 = r11.mUserId
            int r13 = r0.mMyUserId
            if (r12 == r13) goto L_0x06a7
            android.util.SparseArray<com.android.settings.applications.RunningState$MergedItem> r12 = r0.mOtherUserBackgroundItems
            r0.addOtherUserItem(r1, r9, r12, r11)
            goto L_0x06aa
        L_0x06a7:
            r9.add(r11)
        L_0x06aa:
            int r10 = r10 + 1
            goto L_0x0693
        L_0x06ad:
            android.util.SparseArray<com.android.settings.applications.RunningState$MergedItem> r6 = r0.mOtherUserBackgroundItems
            int r10 = r6.size()
            r11 = 0
        L_0x06b4:
            if (r11 >= r10) goto L_0x06d0
            android.util.SparseArray<com.android.settings.applications.RunningState$MergedItem> r6 = r0.mOtherUserBackgroundItems
            java.lang.Object r6 = r6.valueAt(r11)
            r12 = r6
            com.android.settings.applications.RunningState$MergedItem r12 = (com.android.settings.applications.RunningState.MergedItem) r12
            int r6 = r12.mCurSeq
            int r13 = r0.mSequence
            if (r6 != r13) goto L_0x06cc
            r6 = 1
            r12.update(r1, r6)
            r12.updateSize(r1)
        L_0x06cc:
            int r11 = r11 + 1
            goto L_0x06b4
        L_0x06cf:
            r9 = 0
        L_0x06d0:
            r10 = 0
        L_0x06d1:
            java.util.ArrayList<com.android.settings.applications.RunningState$MergedItem> r11 = r0.mMergedItems
            int r11 = r11.size()
            if (r10 >= r11) goto L_0x06e7
            java.util.ArrayList<com.android.settings.applications.RunningState$MergedItem> r11 = r0.mMergedItems
            java.lang.Object r11 = r11.get(r10)
            com.android.settings.applications.RunningState$MergedItem r11 = (com.android.settings.applications.RunningState.MergedItem) r11
            r11.updateSize(r1)
            int r10 = r10 + 1
            goto L_0x06d1
        L_0x06e7:
            java.lang.Object r1 = r0.mLock
            monitor-enter(r1)
            r0.mNumBackgroundProcesses = r7     // Catch:{ all -> 0x0712 }
            r0.mNumForegroundProcesses = r8     // Catch:{ all -> 0x0712 }
            r0.mNumServiceProcesses = r4     // Catch:{ all -> 0x0712 }
            r0.mBackgroundProcessMemory = r14     // Catch:{ all -> 0x0712 }
            r0.mForegroundProcessMemory = r2     // Catch:{ all -> 0x0712 }
            r2 = r16
            r0.mServiceProcessMemory = r2     // Catch:{ all -> 0x0712 }
            if (r5 == 0) goto L_0x0704
            r0.mBackgroundItems = r5     // Catch:{ all -> 0x0712 }
            r0.mUserBackgroundItems = r9     // Catch:{ all -> 0x0712 }
            boolean r2 = r0.mWatchingBackgroundItems     // Catch:{ all -> 0x0712 }
            if (r2 == 0) goto L_0x0704
            r20 = 1
        L_0x0704:
            boolean r2 = r0.mHaveData     // Catch:{ all -> 0x0712 }
            if (r2 != 0) goto L_0x0710
            r2 = 1
            r0.mHaveData = r2     // Catch:{ all -> 0x0712 }
            java.lang.Object r0 = r0.mLock     // Catch:{ all -> 0x0712 }
            r0.notifyAll()     // Catch:{ all -> 0x0712 }
        L_0x0710:
            monitor-exit(r1)     // Catch:{ all -> 0x0712 }
            return r20
        L_0x0712:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0712 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.applications.RunningState.update(android.content.Context, android.app.ActivityManager):boolean");
    }

    /* access modifiers changed from: package-private */
    public void setWatchingBackgroundItems(boolean z) {
        synchronized (this.mLock) {
            this.mWatchingBackgroundItems = z;
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<MergedItem> getCurrentMergedItems() {
        ArrayList<MergedItem> arrayList;
        synchronized (this.mLock) {
            arrayList = this.mMergedItems;
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<MergedItem> getCurrentBackgroundItems() {
        ArrayList<MergedItem> arrayList;
        synchronized (this.mLock) {
            arrayList = this.mUserBackgroundItems;
        }
        return arrayList;
    }
}
