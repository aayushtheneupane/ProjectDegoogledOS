package com.android.systemui.statusbar.phone;

import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.om.IOverlayManager;
import android.content.pm.PackageManager;
import android.content.res.ApkAssets;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.util.Log;
import android.util.SparseBooleanArray;
import com.android.systemui.Dumpable;
import com.android.systemui.UiOffloadThread;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class NavigationModeController implements Dumpable {
    private static final String TAG = "NavigationModeController";
    private final Context mContext;
    private Context mCurrentUserContext;
    private final DeviceProvisionedController.DeviceProvisionedListener mDeviceProvisionedCallback = new DeviceProvisionedController.DeviceProvisionedListener() {
        public void onDeviceProvisionedChanged() {
            NavigationModeController.this.restoreGesturalNavOverlayIfNecessary();
        }

        public void onUserSetupChanged() {
            NavigationModeController.this.restoreGesturalNavOverlayIfNecessary();
        }

        public void onUserSwitched() {
            NavigationModeController.this.updateCurrentInteractionMode(true);
            NavigationModeController.this.deferGesturalNavOverlayIfNecessary();
        }
    };
    private final DeviceProvisionedController mDeviceProvisionedController;
    private BroadcastReceiver mEnableGestureNavReceiver;
    /* access modifiers changed from: private */
    public ArrayList<ModeChangedListener> mListeners = new ArrayList<>();
    private int mMode = 0;
    private final IOverlayManager mOverlayManager;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (((action.hashCode() == -1946981856 && action.equals("android.intent.action.OVERLAY_CHANGED")) ? (char) 0 : 65535) == 0) {
                NavigationModeController.this.updateCurrentInteractionMode(true);
            }
        }
    };
    private SparseBooleanArray mRestoreGesturalNavBarMode = new SparseBooleanArray();
    private SettingsObserver mSettingsObserver;
    private final UiOffloadThread mUiOffloadThread;

    public interface ModeChangedListener {
        void onNavigationModeChanged(int i);

        void onSettingsChanged() {
        }
    }

    private final class SettingsObserver extends ContentObserver {
        public SettingsObserver(Handler handler) {
            super(handler);
        }

        public void onChange(boolean z, Uri uri) {
            for (int i = 0; i < NavigationModeController.this.mListeners.size(); i++) {
                ((ModeChangedListener) NavigationModeController.this.mListeners.get(i)).onSettingsChanged();
            }
        }
    }

    public NavigationModeController(Context context, DeviceProvisionedController deviceProvisionedController, UiOffloadThread uiOffloadThread) {
        this.mContext = context;
        this.mCurrentUserContext = context;
        this.mOverlayManager = IOverlayManager.Stub.asInterface(ServiceManager.getService("overlay"));
        this.mUiOffloadThread = uiOffloadThread;
        this.mDeviceProvisionedController = deviceProvisionedController;
        this.mDeviceProvisionedController.addCallback(this.mDeviceProvisionedCallback);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.OVERLAY_CHANGED");
        intentFilter.addDataScheme("package");
        intentFilter.addDataSchemeSpecificPart("android", 0);
        this.mContext.registerReceiverAsUser(this.mReceiver, UserHandle.ALL, intentFilter, (String) null, (Handler) null);
        this.mSettingsObserver = new SettingsObserver(new Handler());
        this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("back_gesture_height"), false, this.mSettingsObserver, -1);
        this.mContext.registerReceiverAsUser(this.mReceiver, UserHandle.ALL, new IntentFilter("android.intent.action.ACTION_PREFERRED_ACTIVITY_CHANGED"), (String) null, (Handler) null);
        updateCurrentInteractionMode(false);
        deferGesturalNavOverlayIfNecessary();
    }

    private void removeEnableGestureNavListener() {
        BroadcastReceiver broadcastReceiver = this.mEnableGestureNavReceiver;
        if (broadcastReceiver != null) {
            this.mContext.unregisterReceiver(broadcastReceiver);
            this.mEnableGestureNavReceiver = null;
        }
    }

    private boolean setGestureModeOverlayForMainLauncher() {
        removeEnableGestureNavListener();
        if (getCurrentInteractionMode(this.mCurrentUserContext) == 2) {
            return true;
        }
        String str = TAG;
        Log.d(str, "Switching system navigation to full-gesture mode: contextUser=" + this.mCurrentUserContext.getUserId());
        setModeOverlay("com.android.internal.systemui.navbar.gestural", -2);
        return true;
    }

    /* access modifiers changed from: private */
    public boolean enableGestureNav(Intent intent) {
        if (!(intent.getParcelableExtra("com.android.systemui.EXTRA_RESULT_INTENT") instanceof PendingIntent)) {
            Log.e(TAG, "No callback pending intent was attached");
            return false;
        }
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("com.android.systemui.EXTRA_RESULT_INTENT");
        Intent intent2 = pendingIntent.getIntent();
        if (intent2 == null || !"com.android.systemui.action.ENABLE_GESTURE_NAV_RESULT".equals(intent2.getAction())) {
            Log.e(TAG, "Invalid callback intent");
            return false;
        }
        String creatorPackage = pendingIntent.getCreatorPackage();
        UserHandle creatorUserHandle = pendingIntent.getCreatorUserHandle();
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) this.mCurrentUserContext.getSystemService(DevicePolicyManager.class);
        ComponentName deviceOwnerComponentOnCallingUser = devicePolicyManager.getDeviceOwnerComponentOnCallingUser();
        if (deviceOwnerComponentOnCallingUser == null) {
            UserHandle profileParent = ((UserManager) this.mCurrentUserContext.getSystemService(UserManager.class)).getProfileParent(creatorUserHandle);
            if (profileParent == null || !profileParent.equals(this.mCurrentUserContext.getUser())) {
                Log.e(TAG, "Callback must be from a managed user");
                return false;
            }
            ComponentName profileOwnerAsUser = devicePolicyManager.getProfileOwnerAsUser(creatorUserHandle);
            if (profileOwnerAsUser == null || !profileOwnerAsUser.getPackageName().equals(creatorPackage)) {
                Log.e(TAG, "Callback must be from the profile owner");
                return false;
            }
        } else if (!deviceOwnerComponentOnCallingUser.getPackageName().equals(creatorPackage) || !this.mCurrentUserContext.getUser().equals(creatorUserHandle)) {
            Log.e(TAG, "Callback must be from the device owner");
            return false;
        }
        return setGestureModeOverlayForMainLauncher();
    }

    public void updateCurrentInteractionMode(boolean z) {
        this.mCurrentUserContext = getCurrentUserContext();
        int currentInteractionMode = getCurrentInteractionMode(this.mCurrentUserContext);
        this.mMode = currentInteractionMode;
        this.mUiOffloadThread.submit(new Runnable(currentInteractionMode) {
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                NavigationModeController.this.lambda$updateCurrentInteractionMode$0$NavigationModeController(this.f$1);
            }
        });
        if (z) {
            for (int i = 0; i < this.mListeners.size(); i++) {
                this.mListeners.get(i).onNavigationModeChanged(currentInteractionMode);
            }
        }
    }

    public /* synthetic */ void lambda$updateCurrentInteractionMode$0$NavigationModeController(int i) {
        Settings.Secure.putString(this.mCurrentUserContext.getContentResolver(), "navigation_mode", String.valueOf(i));
    }

    public int addListener(ModeChangedListener modeChangedListener) {
        this.mListeners.add(modeChangedListener);
        return getCurrentInteractionMode(this.mCurrentUserContext);
    }

    public void removeListener(ModeChangedListener modeChangedListener) {
        this.mListeners.remove(modeChangedListener);
    }

    private int getCurrentInteractionMode(Context context) {
        return context.getResources().getInteger(17694869);
    }

    public Context getCurrentUserContext() {
        int currentUserId = ActivityManagerWrapper.getInstance().getCurrentUserId();
        if (this.mContext.getUserId() == currentUserId) {
            return this.mContext;
        }
        try {
            return this.mContext.createPackageContextAsUser(this.mContext.getPackageName(), 0, UserHandle.of(currentUserId));
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private boolean supportsDeviceAdmin() {
        return this.mContext.getPackageManager().hasSystemFeature("android.software.device_admin");
    }

    /* access modifiers changed from: private */
    public void deferGesturalNavOverlayIfNecessary() {
        int currentUser = this.mDeviceProvisionedController.getCurrentUser();
        this.mRestoreGesturalNavBarMode.put(currentUser, false);
        if (!this.mDeviceProvisionedController.isDeviceProvisioned() || !this.mDeviceProvisionedController.isCurrentUserSetup()) {
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.addAll(Arrays.asList(this.mOverlayManager.getDefaultOverlayPackages()));
            } catch (RemoteException unused) {
                Log.e(TAG, "deferGesturalNavOverlayIfNecessary: failed to fetch default overlays");
            }
            if (!arrayList.contains("com.android.internal.systemui.navbar.gestural")) {
                removeEnableGestureNavListener();
                return;
            }
            setModeOverlay("com.android.internal.systemui.navbar.threebutton", -2);
            this.mRestoreGesturalNavBarMode.put(currentUser, true);
            if (supportsDeviceAdmin() && this.mEnableGestureNavReceiver == null) {
                this.mEnableGestureNavReceiver = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        setResultCode(NavigationModeController.this.enableGestureNav(intent) ? -1 : 0);
                    }
                };
                this.mContext.registerReceiverAsUser(this.mEnableGestureNavReceiver, UserHandle.ALL, new IntentFilter("com.android.systemui.ENABLE_GESTURE_NAV"), (String) null, (Handler) null);
                return;
            }
            return;
        }
        removeEnableGestureNavListener();
    }

    /* access modifiers changed from: private */
    public void restoreGesturalNavOverlayIfNecessary() {
        int currentUser = this.mDeviceProvisionedController.getCurrentUser();
        if (this.mRestoreGesturalNavBarMode.get(currentUser)) {
            if (!supportsDeviceAdmin() || ((DevicePolicyManager) this.mCurrentUserContext.getSystemService(DevicePolicyManager.class)).getUserProvisioningState() == 0) {
                setGestureModeOverlayForMainLauncher();
            }
            this.mRestoreGesturalNavBarMode.put(currentUser, false);
        }
    }

    public void setModeOverlay(String str, int i) {
        this.mUiOffloadThread.submit(new Runnable(str, i) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                NavigationModeController.this.lambda$setModeOverlay$1$NavigationModeController(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$setModeOverlay$1$NavigationModeController(String str, int i) {
        try {
            this.mOverlayManager.setEnabledExclusiveInCategory(str, i);
        } catch (RemoteException unused) {
            String str2 = TAG;
            Log.e(str2, "Failed to enable overlay " + str + " for user " + i);
        }
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str;
        printWriter.println("NavigationModeController:");
        printWriter.println("  mode=" + this.mMode);
        try {
            str = String.join(", ", this.mOverlayManager.getDefaultOverlayPackages());
        } catch (RemoteException unused) {
            str = "failed_to_fetch";
        }
        printWriter.println("  defaultOverlays=" + str);
        dumpAssetPaths(this.mCurrentUserContext);
    }

    private void dumpAssetPaths(Context context) {
        Log.d(TAG, "assetPaths=");
        for (ApkAssets apkAssets : context.getResources().getAssets().getApkAssets()) {
            Log.d(TAG, "    " + apkAssets.getAssetPath());
        }
    }
}
