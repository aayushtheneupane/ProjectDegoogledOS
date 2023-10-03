package com.google.android.systemui.smartspace;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.KeyValueListParser;
import android.util.Log;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.Dumpable;
import com.android.systemui.util.Assert;
import com.google.android.systemui.smartspace.nano.SmartspaceProto;
import com.google.protobuf.nano.MessageNano;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SmartSpaceController implements Dumpable {
    static final boolean DEBUG = Log.isLoggable("SmartSpaceController", 3);
    private static SmartSpaceController sInstance;
    private final AlarmManager mAlarmManager;
    private boolean mAlarmRegistered;
    private final Context mAppContext;
    private final Handler mBackgroundHandler;
    /* access modifiers changed from: private */
    public final Context mContext;
    public int mCurrentUserId;
    public final SmartSpaceData mData;
    private final AlarmManager.OnAlarmListener mExpireAlarmAction = new AlarmManager.OnAlarmListener() {
        public final void onAlarm() {
            SmartSpaceController.this.onExpire(false);
        }
    };
    /* access modifiers changed from: private */
    public boolean mHidePrivateData;
    private final KeyguardUpdateMonitorCallback mKeyguardMonitorCallback = new KeyguardUpdateMonitorCallback() {
        public void onTimeChanged() {
            SmartSpaceData smartSpaceData = SmartSpaceController.this.mData;
            if (smartSpaceData != null && smartSpaceData.hasCurrent() && SmartSpaceController.this.mData.getExpirationRemainingMillis() > 0) {
                SmartSpaceController.this.update();
            }
        }
    };
    private final ArrayList<SmartSpaceUpdateListener> mListeners = new ArrayList<>();
    private boolean mSmartSpaceEnabledBroadcastSent;
    /* access modifiers changed from: private */
    public final ProtoStore mStore;
    /* access modifiers changed from: private */
    public final Handler mUiHandler;

    private class UserSwitchReceiver extends BroadcastReceiver {
        private UserSwitchReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (SmartSpaceController.DEBUG) {
                Log.d("SmartSpaceController", "Switching user: " + intent.getAction() + " uid: " + UserHandle.myUserId());
            }
            if (intent.getAction().equals("android.intent.action.USER_SWITCHED")) {
                SmartSpaceController.this.mCurrentUserId = intent.getIntExtra("android.intent.extra.user_handle", -1);
                SmartSpaceController.this.mData.clear();
                SmartSpaceController.this.onExpire(true);
            }
            SmartSpaceController.this.onExpire(true);
        }
    }

    public static SmartSpaceController get(Context context) {
        if (sInstance == null) {
            if (DEBUG) {
                Log.d("SmartSpaceController", "controller created");
            }
            sInstance = new SmartSpaceController(context.getApplicationContext());
        }
        return sInstance;
    }

    private SmartSpaceController(Context context) {
        this.mContext = context;
        this.mUiHandler = new Handler(Looper.getMainLooper());
        this.mStore = new ProtoStore(this.mContext);
        HandlerThread handlerThread = new HandlerThread("smartspace-background");
        handlerThread.start();
        this.mBackgroundHandler = new Handler(handlerThread.getLooper());
        this.mCurrentUserId = UserHandle.myUserId();
        this.mAppContext = context;
        this.mAlarmManager = (AlarmManager) context.getSystemService(AlarmManager.class);
        this.mData = new SmartSpaceData();
        if (!isSmartSpaceDisabledByExperiments()) {
            KeyguardUpdateMonitor.getInstance(this.mContext).registerCallback(this.mKeyguardMonitorCallback);
            reloadData();
            onGsaChanged();
            context.registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    SmartSpaceController.this.onGsaChanged();
                }
            }, GSAIntents.getGsaPackageFilter("android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_CHANGED", "android.intent.action.PACKAGE_REMOVED", "android.intent.action.PACKAGE_DATA_CLEARED"));
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.USER_SWITCHED");
            intentFilter.addAction("android.intent.action.USER_UNLOCKED");
            context.registerReceiver(new UserSwitchReceiver(), intentFilter);
            context.registerReceiver(new SmartSpaceBroadcastReceiver(this), new IntentFilter("com.google.android.apps.nexuslauncher.UPDATE_SMARTSPACE"));
        }
    }

    private SmartSpaceCard loadSmartSpaceData(boolean z) {
        SmartspaceProto.CardWrapper cardWrapper = new SmartspaceProto.CardWrapper();
        ProtoStore protoStore = this.mStore;
        if (protoStore.load("smartspace_" + this.mCurrentUserId + "_" + z, cardWrapper)) {
            return SmartSpaceCard.fromWrapper(this.mContext, cardWrapper, !z);
        }
        return null;
    }

    public void onNewCard(final NewCardInfo newCardInfo) {
        if (DEBUG) {
            Log.d("SmartSpaceController", "onNewCard: " + newCardInfo);
        }
        if (newCardInfo == null) {
            return;
        }
        if (newCardInfo.getUserId() == this.mCurrentUserId) {
            this.mBackgroundHandler.post(new Runnable() {
                public final void run() {
                    final SmartspaceProto.CardWrapper wrapper = newCardInfo.toWrapper(SmartSpaceController.this.mContext);
                    if (!SmartSpaceController.this.mHidePrivateData) {
                        ProtoStore access$300 = SmartSpaceController.this.mStore;
                        access$300.store(wrapper, "smartspace_" + SmartSpaceController.this.mCurrentUserId + "_" + newCardInfo.isPrimary());
                    }
                    SmartSpaceController.this.mUiHandler.post(new Runnable() {
                        public final void run() {
                            SmartSpaceCard smartSpaceCard;
                            if (newCardInfo.shouldDiscard()) {
                                smartSpaceCard = null;
                            } else {
                                smartSpaceCard = SmartSpaceCard.fromWrapper(SmartSpaceController.this.mContext, wrapper, newCardInfo.isPrimary());
                            }
                            if (newCardInfo.isPrimary()) {
                                SmartSpaceController.this.mData.mCurrentCard = smartSpaceCard;
                            } else {
                                SmartSpaceController.this.mData.mWeatherCard = smartSpaceCard;
                            }
                            SmartSpaceController.this.mData.handleExpire();
                            SmartSpaceController.this.update();
                        }
                    });
                }
            });
        } else if (DEBUG) {
            Log.d("SmartSpaceController", "Ignore card that belongs to another user target: " + this.mCurrentUserId + " current: " + this.mCurrentUserId);
        }
    }

    private void clearStore() {
        ProtoStore protoStore = this.mStore;
        protoStore.store((MessageNano) null, "smartspace_" + this.mCurrentUserId + "_true");
        ProtoStore protoStore2 = this.mStore;
        protoStore2.store((MessageNano) null, "smartspace_" + this.mCurrentUserId + "_false");
    }

    public void update() {
        Assert.isMainThread();
        if (DEBUG) {
            Log.d("SmartSpaceController", "update");
        }
        if (this.mAlarmRegistered) {
            this.mAlarmManager.cancel(this.mExpireAlarmAction);
            this.mAlarmRegistered = false;
        }
        long expiresAtMillis = this.mData.getExpiresAtMillis();
        if (expiresAtMillis > 0) {
            this.mAlarmManager.set(0, expiresAtMillis, "SmartSpace", this.mExpireAlarmAction, this.mUiHandler);
            this.mAlarmRegistered = true;
        }
        if (this.mListeners != null) {
            if (DEBUG) {
                Log.d("SmartSpaceController", "notifying listeners data=" + this.mData);
            }
            ArrayList arrayList = new ArrayList(this.mListeners);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((SmartSpaceUpdateListener) arrayList.get(i)).onSmartSpaceUpdated(this.mData);
            }
        }
    }

    public void onExpire(boolean z) {
        Assert.isMainThread();
        this.mAlarmRegistered = false;
        if (this.mData.handleExpire() || z) {
            update();
            if (UserHandle.myUserId() == 0) {
                if (DEBUG) {
                    Log.d("SmartSpaceController", "onExpire - sent");
                }
                this.mAppContext.sendBroadcast(new Intent("com.google.android.systemui.smartspace.EXPIRE_EVENT").setPackage("com.google.android.googlequicksearchbox").addFlags(268435456));
            }
        } else if (DEBUG) {
            Log.d("SmartSpaceController", "onExpire - cancelled");
        }
    }

    public void setHideSensitiveData(boolean z) {
        this.mHidePrivateData = z;
        ArrayList arrayList = new ArrayList(this.mListeners);
        for (int i = 0; i < arrayList.size(); i++) {
            ((SmartSpaceUpdateListener) arrayList.get(i)).onSensitiveModeChanged(z);
        }
        if (this.mHidePrivateData) {
            clearStore();
        }
    }

    public void onGsaChanged() {
        if (DEBUG) {
            Log.d("SmartSpaceController", "onGsaChanged");
        }
        if (UserHandle.myUserId() == 0) {
            this.mAppContext.sendBroadcast(new Intent("com.google.android.systemui.smartspace.ENABLE_UPDATE").setPackage("com.google.android.googlequicksearchbox").addFlags(268435456));
            this.mSmartSpaceEnabledBroadcastSent = true;
        }
        ArrayList arrayList = new ArrayList(this.mListeners);
        for (int i = 0; i < arrayList.size(); i++) {
            ((SmartSpaceUpdateListener) arrayList.get(i)).onGsaChanged();
        }
    }

    public void reloadData() {
        this.mData.mCurrentCard = loadSmartSpaceData(true);
        this.mData.mWeatherCard = loadSmartSpaceData(false);
        update();
    }

    private boolean isSmartSpaceDisabledByExperiments() {
        boolean z;
        String string = Settings.Global.getString(this.mContext.getContentResolver(), "always_on_display_constants");
        KeyValueListParser keyValueListParser = new KeyValueListParser(',');
        try {
            keyValueListParser.setString(string);
            z = keyValueListParser.getBoolean("smart_space_enabled", true);
        } catch (IllegalArgumentException unused) {
            Log.e("SmartSpaceController", "Bad AOD constants");
            z = true;
        }
        if (!z) {
            return true;
        }
        return false;
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println();
        printWriter.println("SmartspaceController");
        printWriter.println("  initial broadcast: " + this.mSmartSpaceEnabledBroadcastSent);
        printWriter.println("  weather " + this.mData.mWeatherCard);
        printWriter.println("  current " + this.mData.mCurrentCard);
        printWriter.println("serialized:");
        printWriter.println("  weather " + loadSmartSpaceData(false));
        printWriter.println("  current " + loadSmartSpaceData(true));
        printWriter.println("disabled by experiment: " + isSmartSpaceDisabledByExperiments());
    }

    public void addListener(SmartSpaceUpdateListener smartSpaceUpdateListener) {
        Assert.isMainThread();
        this.mListeners.add(smartSpaceUpdateListener);
        SmartSpaceData smartSpaceData = this.mData;
        if (smartSpaceData != null && smartSpaceUpdateListener != null) {
            smartSpaceUpdateListener.onSmartSpaceUpdated(smartSpaceData);
        }
    }

    public void removeListener(SmartSpaceUpdateListener smartSpaceUpdateListener) {
        Assert.isMainThread();
        this.mListeners.remove(smartSpaceUpdateListener);
    }
}
