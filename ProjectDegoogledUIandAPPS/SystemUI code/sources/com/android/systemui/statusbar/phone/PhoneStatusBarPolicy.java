package com.android.systemui.statusbar.phone;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.AlarmManager;
import android.app.SynchronousUserSwitchObserver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.nfc.NfcAdapter;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserManager;
import android.provider.Settings;
import android.service.notification.ZenModeConfig;
import android.telecom.TelecomManager;
import android.text.format.DateFormat;
import android.util.Log;
import com.android.internal.telephony.IccCardConstants;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.UiOffloadThread;
import com.android.systemui.p006qs.tiles.RotationLockTile;
import com.android.systemui.privacy.PrivacyItem;
import com.android.systemui.privacy.PrivacyItemController;
import com.android.systemui.privacy.PrivacyType;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.phone.PhoneStatusBarPolicy;
import com.android.systemui.statusbar.policy.BluetoothController;
import com.android.systemui.statusbar.policy.CastController;
import com.android.systemui.statusbar.policy.DataSaverController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.HotspotController;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import com.android.systemui.statusbar.policy.LocationController;
import com.android.systemui.statusbar.policy.NextAlarmController;
import com.android.systemui.statusbar.policy.RotationLockController;
import com.android.systemui.statusbar.policy.SensorPrivacyController;
import com.android.systemui.statusbar.policy.UserInfoController;
import com.android.systemui.statusbar.policy.ZenModeController;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class PhoneStatusBarPolicy implements BluetoothController.Callback, CommandQueue.Callbacks, RotationLockController.RotationLockControllerCallback, DataSaverController.Listener, ZenModeController.Callback, DeviceProvisionedController.DeviceProvisionedListener, KeyguardMonitor.Callback, PrivacyItemController.Callback, LocationController.LocationChangeCallback {
    /* access modifiers changed from: private */
    public static final boolean DEBUG = Log.isLoggable("PhoneStatusBarPolicy", 3);
    public static final int LOCATION_STATUS_ICON_ID = C1776R$drawable.stat_sys_location;
    private NfcAdapter mAdapter;
    private final AlarmManager mAlarmManager;
    private BluetoothController mBluetooth;
    private final CastController mCast;
    private final CastController.Callback mCastCallback = new CastController.Callback() {
        public void onCastDevicesChanged() {
            PhoneStatusBarPolicy.this.updateCast();
        }
    };
    /* access modifiers changed from: private */
    public final Context mContext;
    private boolean mCurrentUserSetup;
    private final DataSaverController mDataSaver;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler();
    private final HotspotController mHotspot;
    private final HotspotController.Callback mHotspotCallback = new HotspotController.Callback() {
        public void onHotspotChanged(boolean z, int i) {
            PhoneStatusBarPolicy.this.mIconController.setIconVisibility(PhoneStatusBarPolicy.this.mSlotHotspot, z);
        }
    };
    /* access modifiers changed from: private */
    public final StatusBarIconController mIconController;
    private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onReceive(android.content.Context r3, android.content.Intent r4) {
            /*
                r2 = this;
                java.lang.String r3 = r4.getAction()
                int r0 = r3.hashCode()
                r1 = 0
                switch(r0) {
                    case -1676458352: goto L_0x006a;
                    case -1238404651: goto L_0x0060;
                    case -864107122: goto L_0x0056;
                    case -229777127: goto L_0x004c;
                    case 100931828: goto L_0x0042;
                    case 579327048: goto L_0x0037;
                    case 1051344550: goto L_0x002d;
                    case 1051477093: goto L_0x0023;
                    case 1943044864: goto L_0x0018;
                    case 2070024785: goto L_0x000e;
                    default: goto L_0x000c;
                }
            L_0x000c:
                goto L_0x0074
            L_0x000e:
                java.lang.String r0 = "android.media.RINGER_MODE_CHANGED"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0074
                r3 = r1
                goto L_0x0075
            L_0x0018:
                java.lang.String r0 = "android.nfc.action.ADAPTER_STATE_CHANGED"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0074
                r3 = 8
                goto L_0x0075
            L_0x0023:
                java.lang.String r0 = "android.intent.action.MANAGED_PROFILE_REMOVED"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0074
                r3 = 6
                goto L_0x0075
            L_0x002d:
                java.lang.String r0 = "android.telecom.action.CURRENT_TTY_MODE_CHANGED"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0074
                r3 = 3
                goto L_0x0075
            L_0x0037:
                java.lang.String r0 = "android.bluetooth.device.action.BATTERY_LEVEL_CHANGED"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0074
                r3 = 9
                goto L_0x0075
            L_0x0042:
                java.lang.String r0 = "android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0074
                r3 = 1
                goto L_0x0075
            L_0x004c:
                java.lang.String r0 = "android.intent.action.SIM_STATE_CHANGED"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0074
                r3 = 2
                goto L_0x0075
            L_0x0056:
                java.lang.String r0 = "android.intent.action.MANAGED_PROFILE_AVAILABLE"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0074
                r3 = 4
                goto L_0x0075
            L_0x0060:
                java.lang.String r0 = "android.intent.action.MANAGED_PROFILE_UNAVAILABLE"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0074
                r3 = 5
                goto L_0x0075
            L_0x006a:
                java.lang.String r0 = "android.intent.action.HEADSET_PLUG"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0074
                r3 = 7
                goto L_0x0075
            L_0x0074:
                r3 = -1
            L_0x0075:
                switch(r3) {
                    case 0: goto L_0x00ac;
                    case 1: goto L_0x00ac;
                    case 2: goto L_0x009d;
                    case 3: goto L_0x0091;
                    case 4: goto L_0x008b;
                    case 5: goto L_0x008b;
                    case 6: goto L_0x008b;
                    case 7: goto L_0x0085;
                    case 8: goto L_0x007f;
                    case 9: goto L_0x0079;
                    default: goto L_0x0078;
                }
            L_0x0078:
                goto L_0x00b1
            L_0x0079:
                com.android.systemui.statusbar.phone.PhoneStatusBarPolicy r2 = com.android.systemui.statusbar.phone.PhoneStatusBarPolicy.this
                r2.updateSettings()
                goto L_0x00b1
            L_0x007f:
                com.android.systemui.statusbar.phone.PhoneStatusBarPolicy r2 = com.android.systemui.statusbar.phone.PhoneStatusBarPolicy.this
                r2.updateNfc()
                goto L_0x00b1
            L_0x0085:
                com.android.systemui.statusbar.phone.PhoneStatusBarPolicy r2 = com.android.systemui.statusbar.phone.PhoneStatusBarPolicy.this
                r2.updateHeadsetPlug(r4)
                goto L_0x00b1
            L_0x008b:
                com.android.systemui.statusbar.phone.PhoneStatusBarPolicy r2 = com.android.systemui.statusbar.phone.PhoneStatusBarPolicy.this
                r2.updateManagedProfile()
                goto L_0x00b1
            L_0x0091:
                com.android.systemui.statusbar.phone.PhoneStatusBarPolicy r2 = com.android.systemui.statusbar.phone.PhoneStatusBarPolicy.this
                java.lang.String r3 = "android.telecom.intent.extra.CURRENT_TTY_MODE"
                int r3 = r4.getIntExtra(r3, r1)
                r2.updateTTY(r3)
                goto L_0x00b1
            L_0x009d:
                java.lang.String r3 = "rebroadcastOnUnlock"
                boolean r3 = r4.getBooleanExtra(r3, r1)
                if (r3 == 0) goto L_0x00a6
                goto L_0x00b1
            L_0x00a6:
                com.android.systemui.statusbar.phone.PhoneStatusBarPolicy r2 = com.android.systemui.statusbar.phone.PhoneStatusBarPolicy.this
                r2.updateSimState(r4)
                goto L_0x00b1
            L_0x00ac:
                com.android.systemui.statusbar.phone.PhoneStatusBarPolicy r2 = com.android.systemui.statusbar.phone.PhoneStatusBarPolicy.this
                r2.updateVolumeZen()
            L_0x00b1:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.PhoneStatusBarPolicy.C14366.onReceive(android.content.Context, android.content.Intent):void");
        }
    };
    private final KeyguardMonitor mKeyguardMonitor;
    private final LocationController mLocationController;
    private boolean mManagedProfileIconVisible = false;
    /* access modifiers changed from: private */
    public AlarmManager.AlarmClockInfo mNextAlarm;
    private final NextAlarmController.NextAlarmChangeCallback mNextAlarmCallback = new NextAlarmController.NextAlarmChangeCallback() {
        public void onNextAlarmChanged(AlarmManager.AlarmClockInfo alarmClockInfo) {
            AlarmManager.AlarmClockInfo unused = PhoneStatusBarPolicy.this.mNextAlarm = alarmClockInfo;
            PhoneStatusBarPolicy.this.updateAlarm();
        }
    };
    private final NextAlarmController mNextAlarmController;
    private boolean mNfcVisible;
    private boolean mPermissionsHubEnabled;
    private final PrivacyItemController mPrivacyItemController;
    private final DeviceProvisionedController mProvisionedController;
    private Runnable mRemoveCastIconRunnable = new Runnable() {
        public void run() {
            if (PhoneStatusBarPolicy.DEBUG) {
                Log.v("PhoneStatusBarPolicy", "updateCast: hiding icon NOW");
            }
            PhoneStatusBarPolicy.this.mIconController.setIconVisibility(PhoneStatusBarPolicy.this.mSlotCast, false);
        }
    };
    private final RotationLockController mRotationLockController;
    private final SensorPrivacyController mSensorPrivacyController;
    private final SensorPrivacyController.OnSensorPrivacyChangedListener mSensorPrivacyListener = new SensorPrivacyController.OnSensorPrivacyChangedListener() {
        public void onSensorPrivacyChanged(boolean z) {
            PhoneStatusBarPolicy.this.mHandler.post(new Runnable(z) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    PhoneStatusBarPolicy.C14355.this.lambda$onSensorPrivacyChanged$0$PhoneStatusBarPolicy$5(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$onSensorPrivacyChanged$0$PhoneStatusBarPolicy$5(boolean z) {
            PhoneStatusBarPolicy.this.mIconController.setIconVisibility(PhoneStatusBarPolicy.this.mSlotSensorsOff, z);
        }
    };
    private boolean mShowBluetoothBattery;
    IccCardConstants.State mSimState = IccCardConstants.State.READY;
    private final String mSlotAlarmClock;
    private final String mSlotBluetooth;
    private final String mSlotCamera;
    /* access modifiers changed from: private */
    public final String mSlotCast;
    private final String mSlotDataSaver;
    private final String mSlotHeadset;
    /* access modifiers changed from: private */
    public final String mSlotHotspot;
    private final String mSlotLocation;
    private final String mSlotManagedProfile;
    private final String mSlotMicrophone;
    private final String mSlotNfc;
    private final String mSlotRotate;
    /* access modifiers changed from: private */
    public final String mSlotSensorsOff;
    private final String mSlotTty;
    private final String mSlotVolume;
    private final String mSlotZen;
    private final UiOffloadThread mUiOffloadThread = ((UiOffloadThread) Dependency.get(UiOffloadThread.class));
    /* access modifiers changed from: private */
    public final UserInfoController mUserInfoController;
    private final UserManager mUserManager;
    private final SynchronousUserSwitchObserver mUserSwitchListener = new SynchronousUserSwitchObserver() {
        public /* synthetic */ void lambda$onUserSwitching$0$PhoneStatusBarPolicy$1() {
            PhoneStatusBarPolicy.this.mUserInfoController.reloadUserInfo();
        }

        public void onUserSwitching(int i) throws RemoteException {
            PhoneStatusBarPolicy.this.mHandler.post(new Runnable() {
                public final void run() {
                    PhoneStatusBarPolicy.C14311.this.lambda$onUserSwitching$0$PhoneStatusBarPolicy$1();
                }
            });
        }

        public void onUserSwitchComplete(int i) throws RemoteException {
            PhoneStatusBarPolicy.this.mHandler.post(new Runnable() {
                public final void run() {
                    PhoneStatusBarPolicy.C14311.this.lambda$onUserSwitchComplete$1$PhoneStatusBarPolicy$1();
                }
            });
        }

        public /* synthetic */ void lambda$onUserSwitchComplete$1$PhoneStatusBarPolicy$1() {
            PhoneStatusBarPolicy.this.updateAlarm();
            PhoneStatusBarPolicy.this.updateManagedProfile();
        }
    };
    private boolean mVolumeVisible;
    private final ZenModeController mZenController;
    private boolean mZenVisible;

    public PhoneStatusBarPolicy(Context context, StatusBarIconController statusBarIconController) {
        this.mContext = context;
        this.mIconController = statusBarIconController;
        this.mCast = (CastController) Dependency.get(CastController.class);
        this.mHotspot = (HotspotController) Dependency.get(HotspotController.class);
        this.mBluetooth = (BluetoothController) Dependency.get(BluetoothController.class);
        this.mNextAlarmController = (NextAlarmController) Dependency.get(NextAlarmController.class);
        this.mAlarmManager = (AlarmManager) context.getSystemService("alarm");
        this.mUserInfoController = (UserInfoController) Dependency.get(UserInfoController.class);
        this.mUserManager = (UserManager) this.mContext.getSystemService("user");
        this.mRotationLockController = (RotationLockController) Dependency.get(RotationLockController.class);
        this.mDataSaver = (DataSaverController) Dependency.get(DataSaverController.class);
        this.mZenController = (ZenModeController) Dependency.get(ZenModeController.class);
        this.mProvisionedController = (DeviceProvisionedController) Dependency.get(DeviceProvisionedController.class);
        this.mKeyguardMonitor = (KeyguardMonitor) Dependency.get(KeyguardMonitor.class);
        this.mLocationController = (LocationController) Dependency.get(LocationController.class);
        this.mPrivacyItemController = (PrivacyItemController) Dependency.get(PrivacyItemController.class);
        this.mSensorPrivacyController = (SensorPrivacyController) Dependency.get(SensorPrivacyController.class);
        this.mSlotCast = context.getString(17041148);
        this.mSlotHotspot = context.getString(17041156);
        this.mSlotBluetooth = context.getString(17041146);
        this.mSlotTty = context.getString(17041174);
        this.mSlotZen = context.getString(17041178);
        this.mSlotVolume = context.getString(17041175);
        this.mSlotAlarmClock = context.getString(17041144);
        this.mSlotManagedProfile = context.getString(17041159);
        this.mSlotRotate = context.getString(17041168);
        this.mSlotHeadset = context.getString(17041155);
        this.mSlotDataSaver = context.getString(17041153);
        this.mSlotLocation = context.getString(17041158);
        this.mSlotMicrophone = context.getString(17041160);
        this.mSlotCamera = context.getString(17041147);
        this.mSlotSensorsOff = context.getString(17041170);
        this.mSlotNfc = context.getString(17041163);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.media.RINGER_MODE_CHANGED");
        intentFilter.addAction("android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION");
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction("android.bluetooth.device.action.BATTERY_LEVEL_CHANGED");
        intentFilter.addAction("android.intent.action.SIM_STATE_CHANGED");
        intentFilter.addAction("android.telecom.action.CURRENT_TTY_MODE_CHANGED");
        intentFilter.addAction("android.intent.action.MANAGED_PROFILE_AVAILABLE");
        intentFilter.addAction("android.intent.action.MANAGED_PROFILE_UNAVAILABLE");
        intentFilter.addAction("android.intent.action.MANAGED_PROFILE_REMOVED");
        intentFilter.addAction("android.nfc.action.ADAPTER_STATE_CHANGED");
        this.mContext.registerReceiver(this.mIntentReceiver, intentFilter, (String) null, this.mHandler);
        try {
            ActivityManager.getService().registerUserSwitchObserver(this.mUserSwitchListener, "PhoneStatusBarPolicy");
        } catch (RemoteException unused) {
        }
        updateTTY();
        updateSettings();
        this.mIconController.setIcon(this.mSlotAlarmClock, C1776R$drawable.stat_sys_alarm, (CharSequence) null);
        this.mIconController.setIconVisibility(this.mSlotAlarmClock, false);
        this.mIconController.setIcon(this.mSlotZen, C1776R$drawable.stat_sys_dnd, (CharSequence) null);
        this.mIconController.setIconVisibility(this.mSlotZen, false);
        this.mIconController.setIcon(this.mSlotVolume, C1776R$drawable.stat_sys_ringer_vibrate, (CharSequence) null);
        this.mIconController.setIconVisibility(this.mSlotVolume, false);
        updateVolumeZen();
        this.mIconController.setIcon(this.mSlotCast, C1776R$drawable.stat_sys_cast, (CharSequence) null);
        this.mIconController.setIconVisibility(this.mSlotCast, false);
        this.mIconController.setIcon(this.mSlotHotspot, C1776R$drawable.stat_sys_hotspot, this.mContext.getString(C1784R$string.accessibility_status_bar_hotspot));
        this.mIconController.setIconVisibility(this.mSlotHotspot, this.mHotspot.isHotspotEnabled());
        this.mIconController.setIcon(this.mSlotManagedProfile, C1776R$drawable.stat_sys_managed_profile_status, this.mContext.getString(C1784R$string.accessibility_managed_profile));
        this.mIconController.setIconVisibility(this.mSlotManagedProfile, this.mManagedProfileIconVisible);
        this.mIconController.setIcon(this.mSlotDataSaver, C1776R$drawable.stat_sys_data_saver, context.getString(C1784R$string.accessibility_data_saver_on));
        this.mIconController.setIconVisibility(this.mSlotDataSaver, false);
        this.mIconController.setIcon(this.mSlotMicrophone, C1776R$drawable.stat_sys_mic_none, PrivacyType.TYPE_MICROPHONE.getName(this.mContext));
        this.mIconController.setIconVisibility(this.mSlotMicrophone, false);
        this.mIconController.setIcon(this.mSlotCamera, C1776R$drawable.stat_sys_camera, PrivacyType.TYPE_CAMERA.getName(this.mContext));
        this.mIconController.setIconVisibility(this.mSlotCamera, false);
        this.mIconController.setIcon(this.mSlotLocation, LOCATION_STATUS_ICON_ID, this.mContext.getString(C1784R$string.accessibility_location_active));
        this.mIconController.setIconVisibility(this.mSlotLocation, false);
        this.mIconController.setIcon(this.mSlotSensorsOff, C1776R$drawable.stat_sys_sensors_off, this.mContext.getString(C1784R$string.accessibility_sensors_off_active));
        this.mIconController.setIconVisibility(this.mSlotSensorsOff, this.mSensorPrivacyController.isSensorPrivacyEnabled());
        this.mIconController.setIcon(this.mSlotNfc, C1776R$drawable.stat_sys_nfc, this.mContext.getString(C1784R$string.accessibility_status_bar_nfc));
        this.mIconController.setIconVisibility(this.mSlotNfc, false);
        updateNfc();
        this.mRotationLockController.addCallback(this);
        this.mBluetooth.addCallback(this);
        this.mProvisionedController.addCallback(this);
        this.mZenController.addCallback(this);
        this.mCast.addCallback(this.mCastCallback);
        this.mHotspot.addCallback(this.mHotspotCallback);
        this.mNextAlarmController.addCallback(this.mNextAlarmCallback);
        this.mDataSaver.addCallback(this);
        this.mKeyguardMonitor.addCallback(this);
        this.mPrivacyItemController.addCallback((PrivacyItemController.Callback) this);
        this.mSensorPrivacyController.addCallback(this.mSensorPrivacyListener);
        this.mLocationController.addCallback(this);
        ((CommandQueue) SysUiServiceProvider.getComponent(this.mContext, CommandQueue.class)).addCallback((CommandQueue.Callbacks) this);
        new SettingsObserver(new Handler()).observe();
    }

    private final class SettingsObserver extends ContentObserver {
        SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            PhoneStatusBarPolicy.this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("bluetooth_show_battery"), false, this, -1);
            PhoneStatusBarPolicy.this.updateSettings();
        }

        public void onChange(boolean z) {
            PhoneStatusBarPolicy.this.updateSettings();
        }
    }

    /* access modifiers changed from: private */
    public void updateSettings() {
        boolean z = true;
        this.mShowBluetoothBattery = Settings.System.getIntForUser(this.mContext.getContentResolver(), "bluetooth_show_battery", 1, -2) == 1;
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "permissions_hub_enabled", 1, -2) != 1) {
            z = false;
        }
        this.mPermissionsHubEnabled = z;
        updateBluetooth();
    }

    public void onZenChanged(int i) {
        updateVolumeZen();
    }

    public void onConfigChanged(ZenModeConfig zenModeConfig) {
        updateVolumeZen();
    }

    /* access modifiers changed from: private */
    public void updateAlarm() {
        int i;
        AlarmManager.AlarmClockInfo nextAlarmClock = this.mAlarmManager.getNextAlarmClock(-2);
        boolean z = true;
        boolean z2 = nextAlarmClock != null && nextAlarmClock.getTriggerTime() > 0;
        boolean z3 = this.mZenController.getZen() == 2;
        StatusBarIconController statusBarIconController = this.mIconController;
        String str = this.mSlotAlarmClock;
        if (z3) {
            i = C1776R$drawable.stat_sys_alarm_dim;
        } else {
            i = C1776R$drawable.stat_sys_alarm;
        }
        statusBarIconController.setIcon(str, i, buildAlarmContentDescription());
        StatusBarIconController statusBarIconController2 = this.mIconController;
        String str2 = this.mSlotAlarmClock;
        if (!this.mCurrentUserSetup || !z2) {
            z = false;
        }
        statusBarIconController2.setIconVisibility(str2, z);
    }

    private String buildAlarmContentDescription() {
        AlarmManager.AlarmClockInfo alarmClockInfo = this.mNextAlarm;
        if (alarmClockInfo == null) {
            return this.mContext.getString(C1784R$string.status_bar_alarm);
        }
        return formatNextAlarm(alarmClockInfo, this.mContext);
    }

    private static String formatNextAlarm(AlarmManager.AlarmClockInfo alarmClockInfo, Context context) {
        if (alarmClockInfo == null) {
            return "";
        }
        return context.getString(C1784R$string.accessibility_quick_settings_alarm, new Object[]{DateFormat.format(DateFormat.getBestDateTimePattern(Locale.getDefault(), DateFormat.is24HourFormat(context, ActivityManager.getCurrentUser()) ? "EHm" : "Ehma"), alarmClockInfo.getTriggerTime()).toString()});
    }

    /* access modifiers changed from: private */
    public final void updateSimState(Intent intent) {
        String stringExtra = intent.getStringExtra("ss");
        if ("ABSENT".equals(stringExtra)) {
            this.mSimState = IccCardConstants.State.ABSENT;
        } else if ("CARD_IO_ERROR".equals(stringExtra)) {
            this.mSimState = IccCardConstants.State.CARD_IO_ERROR;
        } else if ("CARD_RESTRICTED".equals(stringExtra)) {
            this.mSimState = IccCardConstants.State.CARD_RESTRICTED;
        } else if ("READY".equals(stringExtra)) {
            this.mSimState = IccCardConstants.State.READY;
        } else if ("LOCKED".equals(stringExtra)) {
            String stringExtra2 = intent.getStringExtra("reason");
            if ("PIN".equals(stringExtra2)) {
                this.mSimState = IccCardConstants.State.PIN_REQUIRED;
            } else if ("PUK".equals(stringExtra2)) {
                this.mSimState = IccCardConstants.State.PUK_REQUIRED;
            } else {
                this.mSimState = IccCardConstants.State.NETWORK_LOCKED;
            }
        } else {
            this.mSimState = IccCardConstants.State.UNKNOWN;
        }
    }

    private NfcAdapter getAdapter() {
        if (this.mAdapter == null) {
            try {
                this.mAdapter = NfcAdapter.getNfcAdapter(this.mContext);
            } catch (UnsupportedOperationException unused) {
                this.mAdapter = null;
            }
        }
        return this.mAdapter;
    }

    /* access modifiers changed from: private */
    public final void updateNfc() {
        this.mNfcVisible = getAdapter() != null && getAdapter().isEnabled();
        if (this.mNfcVisible) {
            this.mIconController.setIconVisibility(this.mSlotNfc, true);
        } else {
            this.mIconController.setIconVisibility(this.mSlotNfc, false);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void updateVolumeZen() {
        /*
            r10 = this;
            android.content.Context r0 = r10.mContext
            java.lang.String r1 = "audio"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.media.AudioManager r0 = (android.media.AudioManager) r0
            com.android.systemui.statusbar.policy.ZenModeController r1 = r10.mZenController
            int r1 = r1.getZen()
            android.content.Context r2 = r10.mContext
            boolean r2 = com.android.systemui.p006qs.tiles.DndTile.isVisible(r2)
            r3 = 0
            r4 = 0
            r5 = 1
            if (r2 != 0) goto L_0x0045
            android.content.Context r2 = r10.mContext
            boolean r2 = com.android.systemui.p006qs.tiles.DndTile.isCombinedIcon(r2)
            if (r2 == 0) goto L_0x0024
            goto L_0x0045
        L_0x0024:
            r2 = 2
            if (r1 != r2) goto L_0x0034
            int r2 = com.android.systemui.C1776R$drawable.stat_sys_dnd
            android.content.Context r6 = r10.mContext
            int r7 = com.android.systemui.C1784R$string.interruption_level_none
            java.lang.String r6 = r6.getString(r7)
        L_0x0031:
            r7 = r2
            r2 = r5
            goto L_0x0057
        L_0x0034:
            if (r1 != r5) goto L_0x0041
            int r2 = com.android.systemui.C1776R$drawable.stat_sys_dnd
            android.content.Context r6 = r10.mContext
            int r7 = com.android.systemui.C1784R$string.interruption_level_priority
            java.lang.String r6 = r6.getString(r7)
            goto L_0x0031
        L_0x0041:
            r6 = r3
            r2 = r4
            r7 = r2
            goto L_0x0057
        L_0x0045:
            if (r1 == 0) goto L_0x0049
            r2 = r5
            goto L_0x004a
        L_0x0049:
            r2 = r4
        L_0x004a:
            int r6 = com.android.systemui.C1776R$drawable.stat_sys_dnd
            android.content.Context r7 = r10.mContext
            int r8 = com.android.systemui.C1784R$string.quick_settings_dnd_label
            java.lang.String r7 = r7.getString(r8)
            r9 = r7
            r7 = r6
            r6 = r9
        L_0x0057:
            com.android.systemui.statusbar.policy.ZenModeController r8 = r10.mZenController
            android.app.NotificationManager$Policy r8 = r8.getConsolidatedPolicy()
            boolean r1 = android.service.notification.ZenModeConfig.isZenOverridingRinger(r1, r8)
            if (r1 != 0) goto L_0x0085
            int r1 = r0.getRingerModeInternal()
            if (r1 != r5) goto L_0x0074
            int r4 = com.android.systemui.C1776R$drawable.stat_sys_ringer_vibrate
            android.content.Context r0 = r10.mContext
            int r1 = com.android.systemui.C1784R$string.accessibility_ringer_vibrate
            java.lang.String r3 = r0.getString(r1)
            goto L_0x0086
        L_0x0074:
            int r0 = r0.getRingerModeInternal()
            if (r0 != 0) goto L_0x0085
            int r4 = com.android.systemui.C1776R$drawable.stat_sys_ringer_silent
            android.content.Context r0 = r10.mContext
            int r1 = com.android.systemui.C1784R$string.accessibility_ringer_silent
            java.lang.String r3 = r0.getString(r1)
            goto L_0x0086
        L_0x0085:
            r5 = r4
        L_0x0086:
            if (r2 == 0) goto L_0x008f
            com.android.systemui.statusbar.phone.StatusBarIconController r0 = r10.mIconController
            java.lang.String r1 = r10.mSlotZen
            r0.setIcon(r1, r7, r6)
        L_0x008f:
            boolean r0 = r10.mZenVisible
            if (r2 == r0) goto L_0x009c
            com.android.systemui.statusbar.phone.StatusBarIconController r0 = r10.mIconController
            java.lang.String r1 = r10.mSlotZen
            r0.setIconVisibility(r1, r2)
            r10.mZenVisible = r2
        L_0x009c:
            if (r5 == 0) goto L_0x00a5
            com.android.systemui.statusbar.phone.StatusBarIconController r0 = r10.mIconController
            java.lang.String r1 = r10.mSlotVolume
            r0.setIcon(r1, r4, r3)
        L_0x00a5:
            boolean r0 = r10.mVolumeVisible
            if (r5 == r0) goto L_0x00b2
            com.android.systemui.statusbar.phone.StatusBarIconController r0 = r10.mIconController
            java.lang.String r1 = r10.mSlotVolume
            r0.setIconVisibility(r1, r5)
            r10.mVolumeVisible = r5
        L_0x00b2:
            r10.updateAlarm()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.PhoneStatusBarPolicy.updateVolumeZen():void");
    }

    public void onBluetoothDevicesChanged() {
        updateSettings();
    }

    public void onBluetoothStateChange(boolean z) {
        updateSettings();
    }

    private final void updateBluetooth() {
        boolean z;
        int i = C1776R$drawable.stat_sys_data_bluetooth_connected;
        String string = this.mContext.getString(C1784R$string.accessibility_quick_settings_bluetooth_on);
        BluetoothController bluetoothController = this.mBluetooth;
        if (bluetoothController == null || !bluetoothController.isBluetoothConnected() || (!this.mBluetooth.isBluetoothAudioActive() && this.mBluetooth.isBluetoothAudioProfileOnly())) {
            z = false;
        } else {
            string = this.mContext.getString(C1784R$string.accessibility_bluetooth_connected);
            Collection<CachedBluetoothDevice> devices = this.mBluetooth.getDevices();
            if (devices != null) {
                Iterator<CachedBluetoothDevice> it = devices.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    CachedBluetoothDevice next = it.next();
                    if (this.mBluetooth.getBondState(next) != 10 && next.getMaxConnectionState() == 2) {
                        int batteryLevel = next.getBatteryLevel();
                        next.getBtClass();
                        if (batteryLevel == -1 || !this.mShowBluetoothBattery) {
                            i = C1776R$drawable.stat_sys_data_bluetooth_connected;
                        } else {
                            i = getBtLevelIconRes(batteryLevel);
                        }
                        string = this.mContext.getString(C1784R$string.accessibility_bluetooth_connected);
                    }
                }
            }
            z = this.mBluetooth.isBluetoothEnabled();
        }
        this.mIconController.setIcon(this.mSlotBluetooth, i, string);
        this.mIconController.setIconVisibility(this.mSlotBluetooth, z);
    }

    private int getBtLevelIconRes(int i) {
        if (i == 100) {
            return C1776R$drawable.stat_sys_data_bluetooth_connected_battery_9;
        }
        if (i >= 90) {
            return C1776R$drawable.stat_sys_data_bluetooth_connected_battery_8;
        }
        if (i >= 80) {
            return C1776R$drawable.stat_sys_data_bluetooth_connected_battery_7;
        }
        if (i >= 70) {
            return C1776R$drawable.stat_sys_data_bluetooth_connected_battery_6;
        }
        if (i >= 60) {
            return C1776R$drawable.stat_sys_data_bluetooth_connected_battery_5;
        }
        if (i >= 50) {
            return C1776R$drawable.stat_sys_data_bluetooth_connected_battery_4;
        }
        if (i >= 40) {
            return C1776R$drawable.stat_sys_data_bluetooth_connected_battery_3;
        }
        if (i >= 30) {
            return C1776R$drawable.stat_sys_data_bluetooth_connected_battery_2;
        }
        if (i >= 20) {
            return C1776R$drawable.stat_sys_data_bluetooth_connected_battery_1;
        }
        if (i >= 10) {
            return C1776R$drawable.stat_sys_data_bluetooth_connected_battery_0;
        }
        return C1776R$drawable.stat_sys_data_bluetooth_connected;
    }

    private final void updateTTY() {
        TelecomManager telecomManager = (TelecomManager) this.mContext.getSystemService("telecom");
        if (telecomManager == null) {
            updateTTY(0);
        } else {
            updateTTY(telecomManager.getCurrentTtyMode());
        }
    }

    /* access modifiers changed from: private */
    public final void updateTTY(int i) {
        boolean z = i != 0;
        if (DEBUG) {
            Log.v("PhoneStatusBarPolicy", "updateTTY: enabled: " + z);
        }
        if (z) {
            if (DEBUG) {
                Log.v("PhoneStatusBarPolicy", "updateTTY: set TTY on");
            }
            this.mIconController.setIcon(this.mSlotTty, C1776R$drawable.stat_sys_tty_mode, this.mContext.getString(C1784R$string.accessibility_tty_enabled));
            this.mIconController.setIconVisibility(this.mSlotTty, true);
            return;
        }
        if (DEBUG) {
            Log.v("PhoneStatusBarPolicy", "updateTTY: set TTY off");
        }
        this.mIconController.setIconVisibility(this.mSlotTty, false);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0020 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0011  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateCast() {
        /*
            r6 = this;
            com.android.systemui.statusbar.policy.CastController r0 = r6.mCast
            java.util.List r0 = r0.getCastDevices()
            java.util.Iterator r0 = r0.iterator()
        L_0x000a:
            boolean r1 = r0.hasNext()
            r2 = 1
            if (r1 == 0) goto L_0x0020
            java.lang.Object r1 = r0.next()
            com.android.systemui.statusbar.policy.CastController$CastDevice r1 = (com.android.systemui.statusbar.policy.CastController.CastDevice) r1
            int r1 = r1.state
            if (r1 == r2) goto L_0x001e
            r3 = 2
            if (r1 != r3) goto L_0x000a
        L_0x001e:
            r0 = r2
            goto L_0x0021
        L_0x0020:
            r0 = 0
        L_0x0021:
            boolean r1 = DEBUG
            java.lang.String r3 = "PhoneStatusBarPolicy"
            if (r1 == 0) goto L_0x003b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "updateCast: isCasting: "
            r1.append(r4)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            android.util.Log.v(r3, r1)
        L_0x003b:
            android.os.Handler r1 = r6.mHandler
            java.lang.Runnable r4 = r6.mRemoveCastIconRunnable
            r1.removeCallbacks(r4)
            if (r0 == 0) goto L_0x005d
            com.android.systemui.statusbar.phone.StatusBarIconController r0 = r6.mIconController
            java.lang.String r1 = r6.mSlotCast
            int r3 = com.android.systemui.C1776R$drawable.stat_sys_cast
            android.content.Context r4 = r6.mContext
            int r5 = com.android.systemui.C1784R$string.accessibility_casting
            java.lang.String r4 = r4.getString(r5)
            r0.setIcon(r1, r3, r4)
            com.android.systemui.statusbar.phone.StatusBarIconController r0 = r6.mIconController
            java.lang.String r6 = r6.mSlotCast
            r0.setIconVisibility(r6, r2)
            goto L_0x006f
        L_0x005d:
            boolean r0 = DEBUG
            if (r0 == 0) goto L_0x0066
            java.lang.String r0 = "updateCast: hiding icon in 3 sec..."
            android.util.Log.v(r3, r0)
        L_0x0066:
            android.os.Handler r0 = r6.mHandler
            java.lang.Runnable r6 = r6.mRemoveCastIconRunnable
            r1 = 3000(0xbb8, double:1.482E-320)
            r0.postDelayed(r6, r1)
        L_0x006f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.PhoneStatusBarPolicy.updateCast():void");
    }

    /* access modifiers changed from: private */
    public void updateManagedProfile() {
        this.mUiOffloadThread.submit(new Runnable() {
            public final void run() {
                PhoneStatusBarPolicy.this.lambda$updateManagedProfile$1$PhoneStatusBarPolicy();
            }
        });
    }

    public /* synthetic */ void lambda$updateManagedProfile$1$PhoneStatusBarPolicy() {
        try {
            this.mHandler.post(new Runnable(this.mUserManager.isManagedProfile(ActivityTaskManager.getService().getLastResumedActivityUserId())) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    PhoneStatusBarPolicy.this.lambda$updateManagedProfile$0$PhoneStatusBarPolicy(this.f$1);
                }
            });
        } catch (RemoteException e) {
            Log.w("PhoneStatusBarPolicy", "updateManagedProfile: ", e);
        }
    }

    public /* synthetic */ void lambda$updateManagedProfile$0$PhoneStatusBarPolicy(boolean z) {
        boolean z2;
        if (!z || (this.mKeyguardMonitor.isShowing() && !this.mKeyguardMonitor.isOccluded())) {
            z2 = false;
        } else {
            z2 = true;
            this.mIconController.setIcon(this.mSlotManagedProfile, C1776R$drawable.stat_sys_managed_profile_status, this.mContext.getString(C1784R$string.accessibility_managed_profile));
        }
        if (this.mManagedProfileIconVisible != z2) {
            this.mIconController.setIconVisibility(this.mSlotManagedProfile, z2);
            this.mManagedProfileIconVisible = z2;
        }
    }

    public void appTransitionStarting(int i, long j, long j2, boolean z) {
        if (this.mContext.getDisplayId() == i) {
            updateManagedProfile();
        }
    }

    public void onKeyguardShowingChanged() {
        updateManagedProfile();
    }

    public void onUserSetupChanged() {
        DeviceProvisionedController deviceProvisionedController = this.mProvisionedController;
        boolean isUserSetup = deviceProvisionedController.isUserSetup(deviceProvisionedController.getCurrentUser());
        if (this.mCurrentUserSetup != isUserSetup) {
            this.mCurrentUserSetup = isUserSetup;
            updateAlarm();
        }
    }

    public void onRotationLockStateChanged(boolean z, boolean z2) {
        boolean isCurrentOrientationLockPortrait = RotationLockTile.isCurrentOrientationLockPortrait(this.mRotationLockController, this.mContext);
        if (z) {
            if (isCurrentOrientationLockPortrait) {
                this.mIconController.setIcon(this.mSlotRotate, C1776R$drawable.stat_sys_rotate_portrait, this.mContext.getString(C1784R$string.accessibility_rotation_lock_on_portrait));
            } else {
                this.mIconController.setIcon(this.mSlotRotate, C1776R$drawable.stat_sys_rotate_landscape, this.mContext.getString(C1784R$string.accessibility_rotation_lock_on_landscape));
            }
            this.mIconController.setIconVisibility(this.mSlotRotate, true);
            return;
        }
        this.mIconController.setIconVisibility(this.mSlotRotate, false);
    }

    /* access modifiers changed from: private */
    public void updateHeadsetPlug(Intent intent) {
        int i;
        int i2;
        boolean z = intent.getIntExtra("state", 0) != 0;
        boolean z2 = intent.getIntExtra("microphone", 0) != 0;
        if (z) {
            Context context = this.mContext;
            if (z2) {
                i = C1784R$string.accessibility_status_bar_headset;
            } else {
                i = C1784R$string.accessibility_status_bar_headphones;
            }
            String string = context.getString(i);
            StatusBarIconController statusBarIconController = this.mIconController;
            String str = this.mSlotHeadset;
            if (z2) {
                i2 = C1776R$drawable.stat_sys_headset_mic;
            } else {
                i2 = C1776R$drawable.stat_sys_headset;
            }
            statusBarIconController.setIcon(str, i2, string);
            this.mIconController.setIconVisibility(this.mSlotHeadset, true);
            return;
        }
        this.mIconController.setIconVisibility(this.mSlotHeadset, false);
    }

    public void onDataSaverChanged(boolean z) {
        this.mIconController.setIconVisibility(this.mSlotDataSaver, z);
    }

    public void privacyChanged(List<PrivacyItem> list) {
        updatePrivacyItems(list);
    }

    private void updatePrivacyItems(List<PrivacyItem> list) {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (PrivacyItem next : list) {
            if (next != null) {
                int i = C14388.$SwitchMap$com$android$systemui$privacy$PrivacyType[next.getPrivacyType().ordinal()];
                if (i == 1) {
                    z = true;
                } else if (i == 2) {
                    z3 = true;
                } else if (i == 3) {
                    z2 = true;
                }
            } else if (DEBUG) {
                Log.e("PhoneStatusBarPolicy", "updatePrivacyItems - null item found");
                StringWriter stringWriter = new StringWriter();
                this.mPrivacyItemController.dump((FileDescriptor) null, new PrintWriter(stringWriter), (String[]) null);
                Log.e("PhoneStatusBarPolicy", stringWriter.toString());
            }
        }
        this.mIconController.setIconVisibility(this.mSlotCamera, z);
        this.mIconController.setIconVisibility(this.mSlotMicrophone, z2);
        this.mIconController.setIconVisibility(this.mSlotLocation, z3);
    }

    /* renamed from: com.android.systemui.statusbar.phone.PhoneStatusBarPolicy$8 */
    static /* synthetic */ class C14388 {
        static final /* synthetic */ int[] $SwitchMap$com$android$systemui$privacy$PrivacyType = new int[PrivacyType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.android.systemui.privacy.PrivacyType[] r0 = com.android.systemui.privacy.PrivacyType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$android$systemui$privacy$PrivacyType = r0
                int[] r0 = $SwitchMap$com$android$systemui$privacy$PrivacyType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.android.systemui.privacy.PrivacyType r1 = com.android.systemui.privacy.PrivacyType.TYPE_CAMERA     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$android$systemui$privacy$PrivacyType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.android.systemui.privacy.PrivacyType r1 = com.android.systemui.privacy.PrivacyType.TYPE_LOCATION     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$android$systemui$privacy$PrivacyType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.android.systemui.privacy.PrivacyType r1 = com.android.systemui.privacy.PrivacyType.TYPE_MICROPHONE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.PhoneStatusBarPolicy.C14388.<clinit>():void");
        }
    }

    public void onLocationActiveChanged(boolean z) {
        if (!this.mPermissionsHubEnabled) {
            updateLocation();
        }
    }

    private void updateLocation() {
        if (this.mLocationController.isLocationActive()) {
            this.mIconController.setIconVisibility(this.mSlotLocation, true);
        } else {
            this.mIconController.setIconVisibility(this.mSlotLocation, false);
        }
    }
}
