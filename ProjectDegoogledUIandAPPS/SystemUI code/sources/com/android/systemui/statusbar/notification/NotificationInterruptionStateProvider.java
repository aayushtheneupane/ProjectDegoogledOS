package com.android.systemui.statusbar.notification;

import android.app.Notification;
import android.content.Context;
import android.database.ContentObserver;
import android.hardware.display.AmbientDisplayConfiguration;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.provider.Telephony;
import android.service.dreams.IDreamManager;
import android.service.notification.StatusBarNotification;
import android.telecom.TelecomManager;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.Dependency;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.NotificationPresenter;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.phone.StatusBarNotificationPresenter;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.HeadsUpManager;

public class NotificationInterruptionStateProvider {
    private static final boolean DEBUG_HEADS_UP = Build.IS_ENG;
    private final AmbientDisplayConfiguration mAmbientDisplayConfiguration;
    private final BatteryController mBatteryController;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public boolean mDisableNotificationAlerts;
    private final IDreamManager mDreamManager;
    /* access modifiers changed from: private */
    public HeadsUpManager mHeadsUpManager;
    private ContentObserver mHeadsUpObserver;
    private HeadsUpSuppressor mHeadsUpSuppressor;
    private boolean mLessBoringHeadsUp;
    private final NotificationFilter mNotificationFilter;
    private boolean mPartialScreenshot;
    private final PowerManager mPowerManager;
    private NotificationPresenter mPresenter;
    private boolean mSkipHeadsUp;
    private final StatusBarStateController mStatusBarStateController;
    private TelecomManager mTm;
    @VisibleForTesting
    protected boolean mUseHeadsUp;

    public interface HeadsUpSuppressor {
        boolean canHeadsUp(NotificationEntry notificationEntry, StatusBarNotification statusBarNotification);
    }

    public NotificationInterruptionStateProvider(Context context, NotificationFilter notificationFilter, StatusBarStateController statusBarStateController, BatteryController batteryController) {
        this(context, (PowerManager) context.getSystemService("power"), IDreamManager.Stub.asInterface(ServiceManager.checkService("dreams")), new AmbientDisplayConfiguration(context), notificationFilter, batteryController, statusBarStateController, (TelecomManager) context.getSystemService("telecom"));
    }

    @VisibleForTesting
    protected NotificationInterruptionStateProvider(Context context, PowerManager powerManager, IDreamManager iDreamManager, AmbientDisplayConfiguration ambientDisplayConfiguration, NotificationFilter notificationFilter, BatteryController batteryController, StatusBarStateController statusBarStateController, TelecomManager telecomManager) {
        this.mUseHeadsUp = false;
        this.mContext = context;
        this.mPowerManager = powerManager;
        this.mDreamManager = iDreamManager;
        this.mBatteryController = batteryController;
        this.mAmbientDisplayConfiguration = ambientDisplayConfiguration;
        this.mNotificationFilter = notificationFilter;
        this.mStatusBarStateController = statusBarStateController;
        this.mTm = telecomManager;
    }

    public void setUpWithPresenter(NotificationPresenter notificationPresenter, HeadsUpManager headsUpManager, HeadsUpSuppressor headsUpSuppressor) {
        setUpWithPresenter(notificationPresenter, headsUpManager, headsUpSuppressor, new ContentObserver((Handler) Dependency.get(Dependency.MAIN_HANDLER)) {
            public void onChange(boolean z) {
                NotificationInterruptionStateProvider notificationInterruptionStateProvider = NotificationInterruptionStateProvider.this;
                boolean z2 = notificationInterruptionStateProvider.mUseHeadsUp;
                boolean z3 = false;
                if (!notificationInterruptionStateProvider.mDisableNotificationAlerts && Settings.Global.getInt(NotificationInterruptionStateProvider.this.mContext.getContentResolver(), "heads_up_notifications_enabled", 0) != 0) {
                    z3 = true;
                }
                notificationInterruptionStateProvider.mUseHeadsUp = z3;
                StringBuilder sb = new StringBuilder();
                sb.append("heads up is ");
                sb.append(NotificationInterruptionStateProvider.this.mUseHeadsUp ? "enabled" : "disabled");
                Log.d("InterruptionStateProvider", sb.toString());
                boolean z4 = NotificationInterruptionStateProvider.this.mUseHeadsUp;
                if (z2 != z4 && !z4) {
                    Log.d("InterruptionStateProvider", "dismissing any existing heads up notification on disable event");
                    NotificationInterruptionStateProvider.this.mHeadsUpManager.releaseAllImmediately();
                }
            }
        });
    }

    public void setUpWithPresenter(NotificationPresenter notificationPresenter, HeadsUpManager headsUpManager, HeadsUpSuppressor headsUpSuppressor, ContentObserver contentObserver) {
        this.mPresenter = notificationPresenter;
        this.mHeadsUpManager = headsUpManager;
        this.mHeadsUpSuppressor = headsUpSuppressor;
        this.mHeadsUpObserver = contentObserver;
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("heads_up_notifications_enabled"), true, this.mHeadsUpObserver);
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("ticker_gets_heads_up"), true, this.mHeadsUpObserver);
        this.mHeadsUpObserver.onChange(true);
    }

    public boolean shouldBubbleUp(NotificationEntry notificationEntry) {
        if (notificationEntry.rowExists() && notificationEntry.getRow().isAppLocked()) {
            return false;
        }
        StatusBarNotification statusBarNotification = notificationEntry.notification;
        if (!canAlertCommon(notificationEntry) || !canAlertAwakeCommon(notificationEntry) || !notificationEntry.canBubble || !notificationEntry.isBubble()) {
            return false;
        }
        Notification notification = statusBarNotification.getNotification();
        if (notification.getBubbleMetadata() == null || notification.getBubbleMetadata().getIntent() == null) {
            return false;
        }
        return true;
    }

    public boolean shouldHeadsUp(NotificationEntry notificationEntry) {
        if (this.mStatusBarStateController.isDozing()) {
            return shouldHeadsUpWhenDozing(notificationEntry);
        }
        return shouldHeadsUpWhenAwake(notificationEntry);
    }

    private boolean shouldHeadsUpWhenAwake(NotificationEntry notificationEntry) {
        boolean z;
        if (this.mStatusBarStateController.getState() != 1 && notificationEntry.rowExists() && notificationEntry.getRow().blockHeadsUp()) {
            return false;
        }
        StatusBarNotification statusBarNotification = notificationEntry.notification;
        if (!this.mUseHeadsUp) {
            if (DEBUG_HEADS_UP) {
                Log.d("InterruptionStateProvider", "No heads up: no huns");
            }
            return false;
        } else if (!canAlertCommon(notificationEntry) || !canAlertAwakeCommon(notificationEntry)) {
            return false;
        } else {
            boolean z2 = this.mStatusBarStateController.getState() == 0;
            if (!notificationEntry.isBubble() || !z2) {
                boolean isMediaPlayerNotification = isMediaPlayerNotification(notificationEntry);
                if (notificationEntry.shouldSuppressPeek() || ((shouldSkipHeadsUp(statusBarNotification) && !isMediaPlayerNotification) || this.mPartialScreenshot)) {
                    if (DEBUG_HEADS_UP) {
                        Log.d("InterruptionStateProvider", "No heads up: suppressed by DND: " + statusBarNotification.getKey());
                    }
                    return false;
                } else if (isMediaPlayerNotification || notificationEntry.importance >= 4) {
                    try {
                        z = this.mDreamManager.isDreaming();
                    } catch (RemoteException e) {
                        Log.e("InterruptionStateProvider", "Failed to query dream manager.", e);
                        z = false;
                    }
                    if (!(this.mPowerManager.isScreenOn() && !z)) {
                        if (DEBUG_HEADS_UP) {
                            Log.d("InterruptionStateProvider", "No heads up: not in use: " + statusBarNotification.getKey());
                        }
                        return false;
                    } else if (this.mHeadsUpSuppressor.canHeadsUp(notificationEntry, statusBarNotification)) {
                        return true;
                    } else {
                        if (DEBUG_HEADS_UP) {
                            Log.d("InterruptionStateProvider", "No heads up: aborted by suppressor: " + statusBarNotification.getKey());
                        }
                        return false;
                    }
                } else {
                    if (DEBUG_HEADS_UP) {
                        Log.d("InterruptionStateProvider", "No heads up: unimportant notification: " + statusBarNotification.getKey());
                    }
                    return false;
                }
            } else {
                if (DEBUG_HEADS_UP) {
                    Log.d("InterruptionStateProvider", "No heads up: in unlocked shade where notification is shown as a bubble: " + statusBarNotification.getKey());
                }
                return false;
            }
        }
    }

    public boolean isMediaPlayerNotification(NotificationEntry notificationEntry) {
        return ((StatusBarNotificationPresenter) this.mPresenter).isMediaPlayerNotification(notificationEntry);
    }

    private boolean shouldHeadsUpWhenDozing(NotificationEntry notificationEntry) {
        StatusBarNotification statusBarNotification = notificationEntry.notification;
        if (!this.mAmbientDisplayConfiguration.pulseOnNotificationEnabled(-2)) {
            if (DEBUG_HEADS_UP) {
                Log.d("InterruptionStateProvider", "No pulsing: disabled by setting: " + statusBarNotification.getKey());
            }
            return false;
        } else if (this.mBatteryController.isAodPowerSave()) {
            if (DEBUG_HEADS_UP) {
                Log.d("InterruptionStateProvider", "No pulsing: disabled by battery saver: " + statusBarNotification.getKey());
            }
            return false;
        } else if (!canAlertCommon(notificationEntry)) {
            if (DEBUG_HEADS_UP) {
                Log.d("InterruptionStateProvider", "No pulsing: notification shouldn't alert: " + statusBarNotification.getKey());
            }
            return false;
        } else if (notificationEntry.shouldSuppressAmbient()) {
            if (DEBUG_HEADS_UP) {
                Log.d("InterruptionStateProvider", "No pulsing: ambient effect suppressed: " + statusBarNotification.getKey());
            }
            return false;
        } else if (notificationEntry.importance >= 3) {
            return true;
        } else {
            if (DEBUG_HEADS_UP) {
                Log.d("InterruptionStateProvider", "No pulsing: not important enough: " + statusBarNotification.getKey());
            }
            return false;
        }
    }

    @VisibleForTesting
    public boolean canAlertCommon(NotificationEntry notificationEntry) {
        StatusBarNotification statusBarNotification = notificationEntry.notification;
        if (this.mNotificationFilter.shouldFilterOut(notificationEntry)) {
            if (DEBUG_HEADS_UP) {
                Log.d("InterruptionStateProvider", "No alerting: filtered notification: " + statusBarNotification.getKey());
            }
            return false;
        } else if (!statusBarNotification.isGroup() || !statusBarNotification.getNotification().suppressAlertingDueToGrouping()) {
            return true;
        } else {
            if (DEBUG_HEADS_UP) {
                Log.d("InterruptionStateProvider", "No alerting: suppressed due to group alert behavior");
            }
            return false;
        }
    }

    public void setUseLessBoringHeadsUp(boolean z) {
        this.mLessBoringHeadsUp = z;
    }

    public void setGamingPeekMode(boolean z) {
        this.mSkipHeadsUp = z;
    }

    public void setPartialScreenshot(boolean z) {
        this.mPartialScreenshot = z;
    }

    public boolean shouldSkipHeadsUp(StatusBarNotification statusBarNotification) {
        String packageName = statusBarNotification.getPackageName();
        if (this.mSkipHeadsUp) {
            boolean z = packageName.equals(getDefaultDialerPackage(this.mTm)) || packageName.contains("clock");
            if (this.mStatusBarStateController.isDozing() || !this.mSkipHeadsUp || z) {
                return false;
            }
            return true;
        }
        boolean z2 = packageName.equals(getDefaultDialerPackage(this.mTm)) || packageName.contains("clock") || packageName.equals(getDefaultSmsPackage(this.mContext));
        if (this.mStatusBarStateController.isDozing() || !this.mLessBoringHeadsUp || z2) {
            return false;
        }
        return true;
    }

    private static String getDefaultSmsPackage(Context context) {
        return Telephony.Sms.getDefaultSmsPackage(context);
    }

    private static String getDefaultDialerPackage(TelecomManager telecomManager) {
        return telecomManager != null ? telecomManager.getDefaultDialerPackage() : "";
    }

    @VisibleForTesting
    public boolean canAlertAwakeCommon(NotificationEntry notificationEntry) {
        StatusBarNotification statusBarNotification = notificationEntry.notification;
        if (!this.mUseHeadsUp || this.mPresenter.isDeviceInVrMode() || shouldSkipHeadsUp(statusBarNotification)) {
            if (DEBUG_HEADS_UP) {
                Log.d("InterruptionStateProvider", "No alerting: no huns or vr mode or less boring or gaming mode");
            }
            return false;
        } else if (notificationEntry.shouldSuppressPeek()) {
            if (DEBUG_HEADS_UP) {
                Log.d("InterruptionStateProvider", "No alerting: suppressed by DND: " + statusBarNotification.getKey());
            }
            return false;
        } else if (isSnoozedPackage(statusBarNotification)) {
            if (DEBUG_HEADS_UP) {
                Log.d("InterruptionStateProvider", "No alerting: snoozed package: " + statusBarNotification.getKey());
            }
            return false;
        } else if (!notificationEntry.hasJustLaunchedFullScreenIntent()) {
            return true;
        } else {
            if (DEBUG_HEADS_UP) {
                Log.d("InterruptionStateProvider", "No alerting: recent fullscreen: " + statusBarNotification.getKey());
            }
            return false;
        }
    }

    private boolean isSnoozedPackage(StatusBarNotification statusBarNotification) {
        return this.mHeadsUpManager.isSnoozed(statusBarNotification.getPackageName());
    }

    public void setDisableNotificationAlerts(boolean z) {
        this.mDisableNotificationAlerts = z;
        this.mHeadsUpObserver.onChange(true);
    }

    @VisibleForTesting
    public boolean areNotificationAlertsDisabled() {
        return this.mDisableNotificationAlerts;
    }

    @VisibleForTesting
    public boolean getUseHeadsUp() {
        return this.mUseHeadsUp;
    }

    public boolean shouldLaunchFullScreenIntentWhenAdded(NotificationEntry notificationEntry) {
        if (notificationEntry.notification.getNotification().fullScreenIntent == null || (shouldHeadsUp(notificationEntry) && this.mStatusBarStateController.getState() != 1)) {
            return false;
        }
        return true;
    }
}
