package com.android.systemui.globalactions;

import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.ActivityManagerNative;
import android.app.Dialog;
import android.app.IActivityManager;
import android.app.IStopUserCallback;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.app.trust.TrustManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.UserInfo;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.Vibrator;
import android.provider.Settings;
import android.service.dreams.IDreamManager;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.internal.colorextraction.ColorExtractor;
import com.android.internal.colorextraction.drawable.ScrimDrawable;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.util.EmergencyAffordanceManager;
import com.android.internal.util.ScreenRecordHelper;
import com.android.internal.util.ScreenshotHelper;
import com.android.internal.view.RotationPolicy;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.C1774R$color;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1784R$string;
import com.android.systemui.C1785R$style;
import com.android.systemui.Dependency;
import com.android.systemui.Interpolators;
import com.android.systemui.MultiListLayout;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.globalactions.GlobalActionsDialog;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.GlobalActions;
import com.android.systemui.plugins.GlobalActionsPanelPlugin;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.phone.StatusBarWindowController;
import com.android.systemui.statusbar.phone.UnlockMethodCache;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.leak.RotationUtils;
import com.android.systemui.volume.SystemUIInterpolators$LogAccelerateInterpolator;
import java.util.ArrayList;
import java.util.function.Consumer;

public class GlobalActionsDialog implements DialogInterface.OnDismissListener, DialogInterface.OnShowListener, ConfigurationController.ConfigurationListener {
    /* access modifiers changed from: private */
    public final ActivityStarter mActivityStarter;
    /* access modifiers changed from: private */
    public MyAdapter mAdapter;
    private ContentObserver mAirplaneModeObserver;
    /* access modifiers changed from: private */
    public ToggleAction mAirplaneModeOn;
    /* access modifiers changed from: private */
    public ToggleAction.State mAirplaneState = ToggleAction.State.Off;
    /* access modifiers changed from: private */
    public final AudioManager mAudioManager;
    private BroadcastReceiver mBroadcastReceiver;
    /* access modifiers changed from: private */
    public final Context mContext;
    private final DevicePolicyManager mDevicePolicyManager;
    /* access modifiers changed from: private */
    public boolean mDeviceProvisioned;
    /* access modifiers changed from: private */
    public ActionsDialog mDialog;
    private final IDreamManager mDreamManager;
    /* access modifiers changed from: private */
    public final EmergencyAffordanceManager mEmergencyAffordanceManager;
    /* access modifiers changed from: private */
    public Handler mHandler;
    private boolean mHasLockdownButton;
    private boolean mHasLogoutButton;
    /* access modifiers changed from: private */
    public boolean mHasTelephony;
    private boolean mHasVibrator;
    /* access modifiers changed from: private */
    public boolean mIsWaitingForEcmExit;
    /* access modifiers changed from: private */
    public ArrayList<Action> mItems;
    private final KeyguardManager mKeyguardManager;
    /* access modifiers changed from: private */
    public boolean mKeyguardShowing;
    private final LockPatternUtils mLockPatternUtils;
    private GlobalActionsPanelPlugin mPanelPlugin;
    PhoneStateListener mPhoneStateListener;
    private AdvancedAction mRestartBootloader;
    private AdvancedAction mRestartHot;
    private AdvancedAction mRestartRecovery;
    private AdvancedAction mRestartSystemUI;
    private BroadcastReceiver mRingerModeReceiver;
    /* access modifiers changed from: private */
    public final ScreenRecordHelper mScreenRecordHelper;
    /* access modifiers changed from: private */
    public final ScreenshotHelper mScreenshotHelper;
    private AdvancedAction mShowAdvancedToggles;
    private final boolean mShowSilentToggle;
    private Action mSilentModeAction;
    /* access modifiers changed from: private */
    public boolean mTorchEnabled;
    /* access modifiers changed from: private */
    public final GlobalActions.GlobalActionsManager mWindowManagerFuncs;

    public interface Action {
        View create(Context context, View view, ViewGroup viewGroup, LayoutInflater layoutInflater);

        boolean isEnabled();

        void onPress();

        boolean shouldBeSeparated() {
            return false;
        }

        boolean showBeforeProvisioning();

        boolean showDuringKeyguard();
    }

    private interface LongPressAction extends Action {
        boolean onLongPress();
    }

    /* access modifiers changed from: private */
    public static boolean shouldUseSeparatedView() {
        return true;
    }

    public GlobalActionsDialog(Context context, GlobalActions.GlobalActionsManager globalActionsManager) {
        boolean z = false;
        this.mKeyguardShowing = false;
        this.mDeviceProvisioned = false;
        this.mIsWaitingForEcmExit = false;
        this.mTorchEnabled = false;
        this.mBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(action) || "android.intent.action.SCREEN_OFF".equals(action)) {
                    String stringExtra = intent.getStringExtra("reason");
                    if (!"globalactions".equals(stringExtra)) {
                        GlobalActionsDialog.this.mHandler.sendMessage(GlobalActionsDialog.this.mHandler.obtainMessage(0, stringExtra));
                    }
                } else if ("android.intent.action.EMERGENCY_CALLBACK_MODE_CHANGED".equals(action) && !intent.getBooleanExtra("PHONE_IN_ECM_STATE", false) && GlobalActionsDialog.this.mIsWaitingForEcmExit) {
                    boolean unused = GlobalActionsDialog.this.mIsWaitingForEcmExit = false;
                    GlobalActionsDialog.this.changeAirplaneModeSystemSetting(true);
                }
            }
        };
        this.mPhoneStateListener = new PhoneStateListener() {
            public void onServiceStateChanged(ServiceState serviceState) {
                if (GlobalActionsDialog.this.mHasTelephony) {
                    ToggleAction.State unused = GlobalActionsDialog.this.mAirplaneState = serviceState.getState() == 3 ? ToggleAction.State.On : ToggleAction.State.Off;
                    GlobalActionsDialog.this.mAirplaneModeOn.updateState(GlobalActionsDialog.this.mAirplaneState);
                    GlobalActionsDialog.this.mAdapter.notifyDataSetChanged();
                }
            }
        };
        this.mRingerModeReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("android.media.RINGER_MODE_CHANGED")) {
                    GlobalActionsDialog.this.mHandler.sendEmptyMessage(1);
                }
            }
        };
        this.mAirplaneModeObserver = new ContentObserver(new Handler()) {
            public void onChange(boolean z) {
                GlobalActionsDialog.this.onAirplaneModeChanged();
            }
        };
        this.mHandler = new Handler() {
            public void handleMessage(Message message) {
                int i = message.what;
                if (i != 0) {
                    if (i == 1) {
                        GlobalActionsDialog.this.refreshSilentMode();
                        GlobalActionsDialog.this.mAdapter.notifyDataSetChanged();
                    } else if (i == 2) {
                        GlobalActionsDialog.this.handleShow();
                    } else if (i == 3) {
                        GlobalActionsDialog.this.mDialog.dismiss();
                        GlobalActionsDialog.this.mHandler.sendEmptyMessageDelayed(6, 200);
                    } else if (i == 6) {
                        GlobalActionsDialog.this.mAdapter.notifyDataSetChanged();
                        GlobalActionsDialog.this.addNewItems();
                        GlobalActionsDialog.this.mDialog.refreshList();
                        GlobalActionsDialog.this.mDialog.show();
                    }
                } else if (GlobalActionsDialog.this.mDialog != null) {
                    if ("dream".equals(message.obj)) {
                        GlobalActionsDialog.this.mDialog.dismissImmediately();
                    } else {
                        GlobalActionsDialog.this.mDialog.dismiss();
                    }
                    ActionsDialog unused = GlobalActionsDialog.this.mDialog = null;
                }
            }
        };
        this.mContext = new ContextThemeWrapper(context, C1785R$style.qs_theme);
        this.mWindowManagerFuncs = globalActionsManager;
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        this.mDreamManager = IDreamManager.Stub.asInterface(ServiceManager.getService("dreams"));
        this.mDevicePolicyManager = (DevicePolicyManager) this.mContext.getSystemService("device_policy");
        this.mLockPatternUtils = new LockPatternUtils(this.mContext);
        this.mKeyguardManager = (KeyguardManager) this.mContext.getSystemService("keyguard");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.EMERGENCY_CALLBACK_MODE_CHANGED");
        context.registerReceiver(this.mBroadcastReceiver, intentFilter);
        this.mHasTelephony = ((ConnectivityManager) context.getSystemService("connectivity")).isNetworkSupported(0);
        ((TelephonyManager) context.getSystemService("phone")).listen(this.mPhoneStateListener, 1);
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("airplane_mode_on"), true, this.mAirplaneModeObserver);
        Vibrator vibrator = (Vibrator) this.mContext.getSystemService("vibrator");
        if (vibrator != null && vibrator.hasVibrator()) {
            z = true;
        }
        this.mHasVibrator = z;
        this.mShowSilentToggle = !this.mContext.getResources().getBoolean(17891593);
        this.mEmergencyAffordanceManager = new EmergencyAffordanceManager(context);
        this.mScreenshotHelper = new ScreenshotHelper(context);
        this.mScreenRecordHelper = new ScreenRecordHelper(context);
        ((ConfigurationController) Dependency.get(ConfigurationController.class)).addCallback(this);
        this.mActivityStarter = (ActivityStarter) Dependency.get(ActivityStarter.class);
        KeyguardUpdateMonitor instance = KeyguardUpdateMonitor.getInstance(context);
        UnlockMethodCache instance2 = UnlockMethodCache.getInstance(context);
        instance2.addListener(new UnlockMethodCache.OnUnlockMethodChangedListener(instance2, instance) {
            private final /* synthetic */ UnlockMethodCache f$1;
            private final /* synthetic */ KeyguardUpdateMonitor f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onUnlockMethodStateChanged() {
                GlobalActionsDialog.this.lambda$new$0$GlobalActionsDialog(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$new$0$GlobalActionsDialog(UnlockMethodCache unlockMethodCache, KeyguardUpdateMonitor keyguardUpdateMonitor) {
        ActionsDialog actionsDialog = this.mDialog;
        if (actionsDialog != null && actionsDialog.mPanelController != null) {
            this.mDialog.mPanelController.onDeviceLockStateChanged(!unlockMethodCache.canSkipBouncer() && keyguardUpdateMonitor.isKeyguardVisible());
        }
    }

    public void showDialog(boolean z, boolean z2, GlobalActionsPanelPlugin globalActionsPanelPlugin) {
        this.mKeyguardShowing = z;
        this.mDeviceProvisioned = z2;
        this.mPanelPlugin = globalActionsPanelPlugin;
        ActionsDialog actionsDialog = this.mDialog;
        if (actionsDialog != null) {
            actionsDialog.dismiss();
            this.mDialog = null;
            this.mHandler.sendEmptyMessage(2);
            return;
        }
        handleShow();
    }

    public void dismissDialog() {
        this.mHandler.removeMessages(0);
        this.mHandler.sendEmptyMessage(0);
    }

    private void awakenIfNecessary() {
        IDreamManager iDreamManager = this.mDreamManager;
        if (iDreamManager != null) {
            try {
                if (iDreamManager.isDreaming()) {
                    this.mDreamManager.awaken();
                }
            } catch (RemoteException unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void handleShow() {
        awakenIfNecessary();
        this.mDialog = createDialog();
        prepareDialog();
        if (this.mAdapter.getCount() != 1 || !(this.mAdapter.getItem(0) instanceof SinglePressAction) || (this.mAdapter.getItem(0) instanceof LongPressAction)) {
            WindowManager.LayoutParams attributes = this.mDialog.getWindow().getAttributes();
            attributes.setTitle("ActionsDialog");
            attributes.layoutInDisplayCutoutMode = 1;
            this.mDialog.getWindow().setAttributes(attributes);
            this.mDialog.show();
            this.mWindowManagerFuncs.onGlobalActionsShown();
            return;
        }
        ((SinglePressAction) this.mAdapter.getItem(0)).onPress();
    }

    private ActionsDialog createDialog() {
        GlobalActionsPanelPlugin.PanelViewController panelViewController;
        if (!this.mHasVibrator) {
            this.mSilentModeAction = new SilentModeToggleAction();
        } else {
            this.mSilentModeAction = new SilentModeTriStateAction(this.mAudioManager, this.mHandler);
        }
        this.mAirplaneModeOn = new ToggleAction(17302458, 17302460, 17040118, 17040117, 17040116) {
            public boolean showBeforeProvisioning() {
                return false;
            }

            public boolean showDuringKeyguard() {
                return true;
            }

            /* access modifiers changed from: package-private */
            public void onToggle(boolean z) {
                if (!GlobalActionsDialog.this.mHasTelephony || !Boolean.parseBoolean(SystemProperties.get("ril.cdma.inecmmode"))) {
                    GlobalActionsDialog.this.changeAirplaneModeSystemSetting(z);
                    return;
                }
                boolean unused = GlobalActionsDialog.this.mIsWaitingForEcmExit = true;
                Intent intent = new Intent("com.android.internal.intent.action.ACTION_SHOW_NOTICE_ECM_BLOCK_OTHERS", (Uri) null);
                intent.addFlags(268435456);
                GlobalActionsDialog.this.mContext.startActivity(intent);
            }

            /* access modifiers changed from: protected */
            public void changeStateFromPress(boolean z) {
                if (GlobalActionsDialog.this.mHasTelephony && !Boolean.parseBoolean(SystemProperties.get("ril.cdma.inecmmode"))) {
                    this.mState = z ? ToggleAction.State.TurningOn : ToggleAction.State.TurningOff;
                    ToggleAction.State unused = GlobalActionsDialog.this.mAirplaneState = this.mState;
                }
            }
        };
        onAirplaneModeChanged();
        this.mShowAdvancedToggles = new AdvancedAction(1, C1776R$drawable.ic_restart_advanced, C1784R$string.global_action_restart_advanced, this.mWindowManagerFuncs, this.mHandler) {
            public boolean showBeforeProvisioning() {
                return true;
            }

            public boolean showDuringKeyguard() {
                return Settings.System.getInt(GlobalActionsDialog.this.mContext.getContentResolver(), "powermenu_ls_advanced_reboot", 0) == 1;
            }
        };
        this.mRestartHot = new AdvancedAction(2, C1776R$drawable.ic_restart_hot, C1784R$string.global_action_restart_hot, this.mWindowManagerFuncs, this.mHandler) {
            public boolean showBeforeProvisioning() {
                return true;
            }

            public boolean showDuringKeyguard() {
                return true;
            }
        };
        this.mRestartRecovery = new AdvancedAction(3, C1776R$drawable.ic_restart_recovery, C1784R$string.global_action_restart_recovery, this.mWindowManagerFuncs, this.mHandler) {
            public boolean showBeforeProvisioning() {
                return true;
            }

            public boolean showDuringKeyguard() {
                return true;
            }
        };
        this.mRestartBootloader = new AdvancedAction(4, C1776R$drawable.ic_restart_bootloader, C1784R$string.global_action_restart_bootloader, this.mWindowManagerFuncs, this.mHandler) {
            public boolean showBeforeProvisioning() {
                return true;
            }

            public boolean showDuringKeyguard() {
                return true;
            }
        };
        this.mRestartSystemUI = new AdvancedAction(5, C1776R$drawable.ic_restart_ui, C1784R$string.global_action_restart_ui, this.mWindowManagerFuncs, this.mHandler) {
            public boolean showBeforeProvisioning() {
                return true;
            }

            public boolean showDuringKeyguard() {
                return true;
            }
        };
        this.mItems = new ArrayList<>();
        String[] stringArray = this.mContext.getResources().getStringArray(17236006);
        ArraySet arraySet = new ArraySet();
        this.mHasLogoutButton = false;
        this.mHasLockdownButton = false;
        int i = 0;
        while (true) {
            panelViewController = null;
            if (i >= stringArray.length) {
                break;
            }
            String str = stringArray[i];
            if (!arraySet.contains(str)) {
                if ("power".equals(str)) {
                    this.mItems.add(new PowerAction());
                } else if (!"airplane".equals(str) && !"bugreport".equals(str) && !"silent".equals(str) && !"users".equals(str) && !"settings".equals(str)) {
                    if ("lockdown".equals(str)) {
                        if (Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "lockdown_in_power_menu", 0, getCurrentUser().id) != 0 && shouldDisplayLockdown()) {
                            this.mItems.add(getLockdownAction());
                            this.mHasLockdownButton = true;
                        }
                    } else if ("torch".equals(str)) {
                        if (Settings.System.getInt(this.mContext.getContentResolver(), "powermenu_torch", 0) != 0) {
                            this.mItems.add(getTorchToggleAction());
                        }
                    } else if (!"voiceassist".equals(str) && !"assist".equals(str)) {
                        if ("restart".equals(str)) {
                            if (Settings.System.getInt(this.mContext.getContentResolver(), "powermenu_reboot", 1) == 1) {
                                this.mItems.add(new RestartAction());
                            }
                        } else if (StatusBar.SYSTEM_DIALOG_REASON_SCREENSHOT.equals(str)) {
                            if (Settings.System.getInt(this.mContext.getContentResolver(), "powermenu_screenshot", 0) != 0) {
                                this.mItems.add(new ScreenshotAction());
                            }
                        } else if ("screenrecord".equals(str)) {
                            if (Settings.System.getInt(this.mContext.getContentResolver(), "powermenu_screenrecord", 0) != 0) {
                                this.mItems.add(new ScreenrecordAction());
                            }
                        } else if ("logout".equals(str)) {
                            if (this.mDevicePolicyManager.isLogoutEnabled() && getCurrentUser().id != 0) {
                                this.mItems.add(new LogoutAction());
                                this.mHasLogoutButton = true;
                            }
                        } else if ("emergency".equals(str)) {
                            if (!this.mEmergencyAffordanceManager.needsEmergencyAffordance()) {
                                this.mItems.add(new EmergencyDialerAction());
                            }
                        } else if (!"advanced".equals(str)) {
                            Log.e("GlobalActionsDialog", "Invalid global action key " + str);
                        } else if (Settings.System.getInt(this.mContext.getContentResolver(), "powermenu_reboot", 1) == 1 && Settings.System.getInt(this.mContext.getContentResolver(), "powermenu_advanced_reboot", 0) != 0) {
                            this.mItems.add(this.mShowAdvancedToggles);
                        }
                    }
                }
                arraySet.add(str);
            }
            i++;
        }
        if (this.mEmergencyAffordanceManager.needsEmergencyAffordance() && this.mContext.getResources().getBoolean(17891541)) {
            this.mItems.add(new EmergencyAffordanceAction());
        }
        this.mAdapter = new MyAdapter();
        GlobalActionsPanelPlugin globalActionsPanelPlugin = this.mPanelPlugin;
        if (globalActionsPanelPlugin != null) {
            panelViewController = globalActionsPanelPlugin.onPanelShown(new GlobalActionsPanelPlugin.Callbacks() {
                public void dismissGlobalActionsMenu() {
                    if (GlobalActionsDialog.this.mDialog != null) {
                        GlobalActionsDialog.this.mDialog.dismiss();
                    }
                }

                public void startPendingIntentDismissingKeyguard(PendingIntent pendingIntent) {
                    GlobalActionsDialog.this.mActivityStarter.startPendingIntentDismissingKeyguard(pendingIntent);
                }
            }, this.mKeyguardManager.isDeviceLocked());
        }
        ActionsDialog actionsDialog = new ActionsDialog(this.mContext, this.mAdapter, panelViewController);
        actionsDialog.setCanceledOnTouchOutside(false);
        actionsDialog.setKeyguardShowing(this.mKeyguardShowing);
        actionsDialog.setOnDismissListener(this);
        actionsDialog.setOnShowListener(this);
        return actionsDialog;
    }

    private boolean shouldDisplayLockdown() {
        int i = getCurrentUser().id;
        if (!this.mKeyguardManager.isDeviceSecure(i)) {
            return false;
        }
        int strongAuthForUser = this.mLockPatternUtils.getStrongAuthForUser(i);
        if (strongAuthForUser == 0 || strongAuthForUser == 4) {
            return true;
        }
        return false;
    }

    public void onUiModeChanged() {
        this.mContext.getTheme().applyStyle(this.mContext.getThemeResId(), true);
        ActionsDialog actionsDialog = this.mDialog;
        if (actionsDialog != null && actionsDialog.isShowing()) {
            this.mDialog.refreshDialog();
        }
    }

    public void destroy() {
        ((ConfigurationController) Dependency.get(ConfigurationController.class)).removeCallback(this);
    }

    private final class PowerAction extends SinglePressAction implements LongPressAction {
        public boolean onLongPress() {
            return false;
        }

        public boolean showBeforeProvisioning() {
            return true;
        }

        public boolean showDuringKeyguard() {
            return true;
        }

        private PowerAction() {
            super(C1776R$drawable.ic_power_off, 17040107);
        }

        public void onPress() {
            GlobalActionsDialog.this.mWindowManagerFuncs.shutdown();
        }
    }

    private abstract class EmergencyAction extends SinglePressAction {
        public boolean showBeforeProvisioning() {
            return true;
        }

        public boolean showDuringKeyguard() {
            return true;
        }

        EmergencyAction(int i, int i2) {
            super(i, i2);
        }

        public boolean shouldBeSeparated() {
            return GlobalActionsDialog.shouldUseSeparatedView();
        }

        public View create(Context context, View view, ViewGroup viewGroup, LayoutInflater layoutInflater) {
            int i;
            View create = super.create(context, view, viewGroup, layoutInflater);
            if (shouldBeSeparated()) {
                i = create.getResources().getColor(C1774R$color.global_actions_alert_text);
            } else {
                i = create.getResources().getColor(C1774R$color.global_actions_text);
            }
            TextView textView = (TextView) create.findViewById(16908299);
            textView.setTextColor(i);
            textView.setSelected(true);
            ((ImageView) create.findViewById(16908294)).getDrawable().setTint(i);
            return create;
        }
    }

    private class EmergencyAffordanceAction extends EmergencyAction {
        EmergencyAffordanceAction() {
            super(17302203, 17040103);
        }

        public void onPress() {
            GlobalActionsDialog.this.mEmergencyAffordanceManager.performEmergencyCall();
        }
    }

    private class EmergencyDialerAction extends EmergencyAction {
        private EmergencyDialerAction() {
            super(C1776R$drawable.ic_emergency_star, 17040103);
        }

        public void onPress() {
            MetricsLogger.action(GlobalActionsDialog.this.mContext, 1569);
            Intent intent = new Intent("com.android.phone.EmergencyDialer.DIAL");
            intent.addFlags(343932928);
            intent.putExtra("com.android.phone.EmergencyDialer.extra.ENTRY_TYPE", 2);
            GlobalActionsDialog.this.mContext.startActivityAsUser(intent, UserHandle.CURRENT);
        }
    }

    private final class RestartAction extends SinglePressAction implements LongPressAction {
        public boolean showBeforeProvisioning() {
            return true;
        }

        private RestartAction() {
            super(C1776R$drawable.ic_restart, C1784R$string.global_action_restart_system);
        }

        public boolean onLongPress() {
            if (((UserManager) GlobalActionsDialog.this.mContext.getSystemService("user")).hasUserRestriction("no_safe_boot")) {
                return false;
            }
            GlobalActionsDialog.this.mDialog.dismiss();
            GlobalActionsDialog.this.mWindowManagerFuncs.reboot(true);
            return true;
        }

        public boolean showDuringKeyguard() {
            return Settings.System.getInt(GlobalActionsDialog.this.mContext.getContentResolver(), "powermenu_ls_reboot", 1) == 1;
        }

        public void onPress() {
            GlobalActionsDialog.this.mWindowManagerFuncs.reboot(false);
        }
    }

    private class ScreenshotAction extends SinglePressAction implements LongPressAction {
        public boolean showBeforeProvisioning() {
            return true;
        }

        public ScreenshotAction() {
            super(C1776R$drawable.ic_screenshot, C1784R$string.global_action_screenshot);
        }

        public void onPress() {
            GlobalActionsDialog.this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    try {
                        WindowManagerGlobal.getWindowManagerService().takeOPScreenshot(1);
                    } catch (RemoteException e) {
                        Log.e("GlobalActionsDialog", "Error while trying to take screenshot.", e);
                    }
                    MetricsLogger.action(GlobalActionsDialog.this.mContext, 1282);
                }
            }, 500);
        }

        public boolean showDuringKeyguard() {
            return Settings.System.getInt(GlobalActionsDialog.this.mContext.getContentResolver(), "powermenu_ls_screenshot", 0) == 1;
        }

        public boolean onLongPress() {
            GlobalActionsDialog.this.mDialog.dismiss();
            GlobalActionsDialog.this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    GlobalActionsDialog.this.mScreenshotHelper.takeScreenshot(2, true, true, GlobalActionsDialog.this.mHandler, (Consumer) null);
                    MetricsLogger.action(GlobalActionsDialog.this.mContext, 1282);
                }
            }, 500);
            return true;
        }
    }

    private Action getTorchToggleAction() {
        return new SinglePressAction(C1776R$drawable.ic_torch, C1784R$string.quick_settings_flashlight_label) {
            public boolean showBeforeProvisioning() {
                return false;
            }

            public void onPress() {
                try {
                    CameraManager cameraManager = (CameraManager) GlobalActionsDialog.this.mContext.getSystemService("camera");
                    for (String str : cameraManager.getCameraIdList()) {
                        boolean z = true;
                        if (((Integer) cameraManager.getCameraCharacteristics(str).get(CameraCharacteristics.LENS_FACING)).intValue() == 1) {
                            cameraManager.setTorchMode(str, !GlobalActionsDialog.this.mTorchEnabled);
                            GlobalActionsDialog globalActionsDialog = GlobalActionsDialog.this;
                            if (GlobalActionsDialog.this.mTorchEnabled) {
                                z = false;
                            }
                            boolean unused = globalActionsDialog.mTorchEnabled = z;
                        }
                    }
                } catch (CameraAccessException unused2) {
                }
            }

            public boolean showDuringKeyguard() {
                return Settings.System.getInt(GlobalActionsDialog.this.mContext.getContentResolver(), "powermenu_ls_torch", 0) == 1;
            }
        };
    }

    private class ScreenrecordAction extends SinglePressAction implements LongPressAction {
        public boolean onLongPress() {
            return false;
        }

        public boolean showBeforeProvisioning() {
            return false;
        }

        public ScreenrecordAction() {
            super(C1776R$drawable.ic_screenrecord, C1784R$string.global_action_screenrecord);
        }

        public void onPress() {
            GlobalActionsDialog.this.mScreenRecordHelper.launchRecordPrompt();
        }

        public boolean showDuringKeyguard() {
            return Settings.System.getInt(GlobalActionsDialog.this.mContext.getContentResolver(), "powermenu_ls_screenrecord", 0) == 1;
        }
    }

    private final class LogoutAction extends SinglePressAction {
        public boolean showBeforeProvisioning() {
            return false;
        }

        public boolean showDuringKeyguard() {
            return true;
        }

        private LogoutAction() {
            super(17302512, 17040106);
        }

        public void onPress() {
            GlobalActionsDialog.this.mHandler.postDelayed(new Runnable() {
                public final void run() {
                    GlobalActionsDialog.LogoutAction.this.lambda$onPress$0$GlobalActionsDialog$LogoutAction();
                }
            }, 500);
        }

        public /* synthetic */ void lambda$onPress$0$GlobalActionsDialog$LogoutAction() {
            try {
                int i = GlobalActionsDialog.this.getCurrentUser().id;
                ActivityManager.getService().switchUser(0);
                ActivityManager.getService().stopUser(i, true, (IStopUserCallback) null);
            } catch (RemoteException e) {
                Log.e("GlobalActionsDialog", "Couldn't logout user " + e);
            }
        }
    }

    private Action getLockdownAction() {
        return new SinglePressAction(C1776R$drawable.ic_lockdown, 17040105) {
            public boolean showBeforeProvisioning() {
                return false;
            }

            public boolean showDuringKeyguard() {
                return true;
            }

            public void onPress() {
                new LockPatternUtils(GlobalActionsDialog.this.mContext).requireStrongAuth(32, -1);
                try {
                    WindowManagerGlobal.getWindowManagerService().lockNow((Bundle) null);
                    new Handler((Looper) Dependency.get(Dependency.BG_LOOPER)).post(new Runnable() {
                        public final void run() {
                            GlobalActionsDialog.C075512.this.lambda$onPress$0$GlobalActionsDialog$12();
                        }
                    });
                } catch (RemoteException e) {
                    Log.e("GlobalActionsDialog", "Error while trying to lock device.", e);
                }
            }

            public /* synthetic */ void lambda$onPress$0$GlobalActionsDialog$12() {
                GlobalActionsDialog.this.lockProfiles();
            }
        };
    }

    /* access modifiers changed from: private */
    public void lockProfiles() {
        TrustManager trustManager = (TrustManager) this.mContext.getSystemService("trust");
        int i = getCurrentUser().id;
        for (int i2 : ((UserManager) this.mContext.getSystemService("user")).getEnabledProfileIds(i)) {
            if (i2 != i) {
                trustManager.setDeviceLockedForUser(i2, true);
            }
        }
    }

    /* access modifiers changed from: private */
    public UserInfo getCurrentUser() {
        try {
            return ActivityManager.getService().getCurrentUser();
        } catch (RemoteException unused) {
            return null;
        }
    }

    private void prepareDialog() {
        refreshSilentMode();
        this.mAirplaneModeOn.updateState(this.mAirplaneState);
        this.mAdapter.notifyDataSetChanged();
        if (this.mShowSilentToggle) {
            this.mContext.registerReceiver(this.mRingerModeReceiver, new IntentFilter("android.media.RINGER_MODE_CHANGED"));
        }
    }

    /* access modifiers changed from: private */
    public void refreshSilentMode() {
        if (!this.mHasVibrator) {
            ((ToggleAction) this.mSilentModeAction).updateState(this.mAudioManager.getRingerMode() != 2 ? ToggleAction.State.On : ToggleAction.State.Off);
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.mWindowManagerFuncs.onGlobalActionsHidden();
        if (this.mShowSilentToggle) {
            try {
                this.mContext.unregisterReceiver(this.mRingerModeReceiver);
            } catch (IllegalArgumentException e) {
                Log.w("GlobalActionsDialog", e);
            }
        }
    }

    public void onShow(DialogInterface dialogInterface) {
        MetricsLogger.visible(this.mContext, 1568);
    }

    public class MyAdapter extends MultiListLayout.MultiListAdapter {
        public boolean areAllItemsEnabled() {
            return false;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public MyAdapter() {
        }

        private int countItems(boolean z) {
            int i = 0;
            for (int i2 = 0; i2 < GlobalActionsDialog.this.mItems.size(); i2++) {
                Action action = (Action) GlobalActionsDialog.this.mItems.get(i2);
                if (shouldBeShown(action) && action.shouldBeSeparated() == z) {
                    i++;
                }
            }
            return i;
        }

        private boolean shouldBeShown(Action action) {
            if (GlobalActionsDialog.this.mKeyguardShowing && !action.showDuringKeyguard()) {
                return false;
            }
            if (GlobalActionsDialog.this.mDeviceProvisioned || action.showBeforeProvisioning()) {
                return true;
            }
            return false;
        }

        public int countSeparatedItems() {
            return countItems(true);
        }

        public int countListItems() {
            return countItems(false);
        }

        public int getCount() {
            return countSeparatedItems() + countListItems();
        }

        public boolean isEnabled(int i) {
            return getItem(i).isEnabled();
        }

        public Action getItem(int i) {
            int i2 = 0;
            for (int i3 = 0; i3 < GlobalActionsDialog.this.mItems.size(); i3++) {
                Action action = (Action) GlobalActionsDialog.this.mItems.get(i3);
                if (shouldBeShown(action)) {
                    if (i2 == i) {
                        return action;
                    }
                    i2++;
                }
            }
            throw new IllegalArgumentException("position " + i + " out of range of showable actions, filtered count=" + getCount() + ", keyguardshowing=" + GlobalActionsDialog.this.mKeyguardShowing + ", provisioned=" + GlobalActionsDialog.this.mDeviceProvisioned);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View create = getItem(i).create(GlobalActionsDialog.this.mContext, view, viewGroup, LayoutInflater.from(GlobalActionsDialog.this.mContext));
            create.setOnClickListener(new View.OnClickListener(i) {
                private final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    GlobalActionsDialog.MyAdapter.this.lambda$getView$0$GlobalActionsDialog$MyAdapter(this.f$1, view);
                }
            });
            create.setOnLongClickListener(new View.OnLongClickListener(i) {
                private final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final boolean onLongClick(View view) {
                    return GlobalActionsDialog.MyAdapter.this.lambda$getView$1$GlobalActionsDialog$MyAdapter(this.f$1, view);
                }
            });
            return create;
        }

        public /* synthetic */ void lambda$getView$0$GlobalActionsDialog$MyAdapter(int i, View view) {
            onClickItem(i);
        }

        public /* synthetic */ boolean lambda$getView$1$GlobalActionsDialog$MyAdapter(int i, View view) {
            return onLongClickItem(i);
        }

        public boolean onLongClickItem(int i) {
            Action item = GlobalActionsDialog.this.mAdapter.getItem(i);
            if (item instanceof LongPressAction) {
                return ((LongPressAction) item).onLongPress();
            }
            return false;
        }

        public void onClickItem(int i) {
            Action item = GlobalActionsDialog.this.mAdapter.getItem(i);
            if (!(item instanceof SilentModeTriStateAction) && !(item instanceof AdvancedAction)) {
                GlobalActionsDialog.this.mDialog.dismiss();
            }
            item.onPress();
        }

        public boolean shouldBeSeparated(int i) {
            return getItem(i).shouldBeSeparated();
        }
    }

    private static abstract class SinglePressAction implements Action {
        private final Drawable mIcon = null;
        private final int mIconResId;
        private final CharSequence mMessage = null;
        private final int mMessageResId;

        public String getStatus() {
            return null;
        }

        public boolean isEnabled() {
            return true;
        }

        public abstract void onPress();

        protected SinglePressAction(int i, int i2) {
            this.mIconResId = i;
            this.mMessageResId = i2;
        }

        /* access modifiers changed from: protected */
        public int getActionLayoutId(Context context) {
            return C1779R$layout.global_actions_grid_item;
        }

        public View create(Context context, View view, ViewGroup viewGroup, LayoutInflater layoutInflater) {
            View inflate = layoutInflater.inflate(getActionLayoutId(context), viewGroup, false);
            ImageView imageView = (ImageView) inflate.findViewById(16908294);
            TextView textView = (TextView) inflate.findViewById(16908299);
            textView.setSelected(true);
            TextView textView2 = (TextView) inflate.findViewById(16909409);
            String status = getStatus();
            if (!TextUtils.isEmpty(status)) {
                textView2.setText(status);
            } else {
                textView2.setVisibility(8);
            }
            Drawable drawable = this.mIcon;
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                int i = this.mIconResId;
                if (i != 0) {
                    imageView.setImageDrawable(context.getDrawable(i));
                }
            }
            CharSequence charSequence = this.mMessage;
            if (charSequence != null) {
                textView.setText(charSequence);
            } else {
                textView.setText(this.mMessageResId);
            }
            return inflate;
        }
    }

    private static abstract class ToggleAction implements Action {
        protected int mDisabledIconResid;
        protected int mDisabledStatusMessageResId;
        protected int mEnabledIconResId;
        protected int mEnabledStatusMessageResId;
        protected int mMessageResId;
        protected State mState = State.Off;

        /* access modifiers changed from: package-private */
        public abstract void onToggle(boolean z);

        /* access modifiers changed from: package-private */
        public void willCreate() {
        }

        enum State {
            Off(false),
            TurningOn(true),
            TurningOff(true),
            On(false);
            
            private final boolean inTransition;

            private State(boolean z) {
                this.inTransition = z;
            }

            public boolean inTransition() {
                return this.inTransition;
            }
        }

        public ToggleAction(int i, int i2, int i3, int i4, int i5) {
            this.mEnabledIconResId = i;
            this.mDisabledIconResid = i2;
            this.mMessageResId = i3;
            this.mEnabledStatusMessageResId = i4;
            this.mDisabledStatusMessageResId = i5;
        }

        public View create(Context context, View view, ViewGroup viewGroup, LayoutInflater layoutInflater) {
            willCreate();
            View inflate = layoutInflater.inflate(17367160, viewGroup, false);
            ImageView imageView = (ImageView) inflate.findViewById(16908294);
            TextView textView = (TextView) inflate.findViewById(16908299);
            TextView textView2 = (TextView) inflate.findViewById(16909409);
            boolean isEnabled = isEnabled();
            boolean z = true;
            if (textView != null) {
                textView.setText(this.mMessageResId);
                textView.setEnabled(isEnabled);
                textView.setSelected(true);
            }
            State state = this.mState;
            if (!(state == State.On || state == State.TurningOn)) {
                z = false;
            }
            if (imageView != null) {
                imageView.setImageDrawable(context.getDrawable(z ? this.mEnabledIconResId : this.mDisabledIconResid));
                imageView.setEnabled(isEnabled);
            }
            if (textView2 != null) {
                textView2.setText(z ? this.mEnabledStatusMessageResId : this.mDisabledStatusMessageResId);
                textView2.setVisibility(0);
                textView2.setEnabled(isEnabled);
            }
            inflate.setEnabled(isEnabled);
            return inflate;
        }

        public final void onPress() {
            if (this.mState.inTransition()) {
                Log.w("GlobalActionsDialog", "shouldn't be able to toggle when in transition");
                return;
            }
            boolean z = this.mState != State.On;
            onToggle(z);
            changeStateFromPress(z);
        }

        public boolean isEnabled() {
            return !this.mState.inTransition();
        }

        /* access modifiers changed from: protected */
        public void changeStateFromPress(boolean z) {
            this.mState = z ? State.On : State.Off;
        }

        public void updateState(State state) {
            this.mState = state;
        }
    }

    private static abstract class AdvancedAction implements Action, LongPressAction {
        protected int mActionType;
        private Context mContext;
        protected int mIconResid;
        protected int mMessageResid;
        protected Handler mRefresh;
        protected GlobalActions.GlobalActionsManager mWmFuncs;

        public String getStatus() {
            return null;
        }

        public boolean isEnabled() {
            return true;
        }

        public boolean onLongPress() {
            return false;
        }

        public AdvancedAction(int i, int i2, int i3, GlobalActions.GlobalActionsManager globalActionsManager, Handler handler) {
            this.mActionType = i;
            this.mIconResid = i2;
            this.mMessageResid = i3;
            this.mRefresh = handler;
            this.mWmFuncs = globalActionsManager;
        }

        public View create(Context context, View view, ViewGroup viewGroup, LayoutInflater layoutInflater) {
            this.mContext = context;
            View inflate = layoutInflater.inflate(C1779R$layout.global_actions_grid_item, viewGroup, false);
            TextView textView = (TextView) inflate.findViewById(16909409);
            String status = getStatus();
            if (!TextUtils.isEmpty(status)) {
                textView.setText(status);
            } else {
                textView.setVisibility(8);
            }
            TextView textView2 = (TextView) inflate.findViewById(16908299);
            if (textView2 != null) {
                textView2.setText(this.mMessageResid);
                textView2.setSelected(true);
            }
            ImageView imageView = (ImageView) inflate.findViewById(16908294);
            if (imageView != null) {
                imageView.setImageDrawable(this.mContext.getDrawable(this.mIconResid));
            }
            return inflate;
        }

        public final void onPress() {
            int i = this.mActionType;
            if (i == 1) {
                this.mRefresh.sendEmptyMessage(3);
            } else {
                GlobalActionsDialog.triggerAction(i, this.mRefresh, this.mWmFuncs, this.mContext);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void triggerAction(int i, Handler handler, GlobalActions.GlobalActionsManager globalActionsManager, Context context) {
        if (i == 2) {
            handler.sendEmptyMessage(0);
            doHotReboot();
        } else if (i == 3) {
            handler.sendEmptyMessage(0);
            globalActionsManager.advancedReboot("recovery");
        } else if (i == 4) {
            handler.sendEmptyMessage(0);
            globalActionsManager.advancedReboot("bootloader");
        } else if (i == 5) {
            globalActionsManager.onGlobalActionsHidden();
            restartSystemUI(context);
        }
    }

    private class SilentModeToggleAction extends ToggleAction {
        public boolean showBeforeProvisioning() {
            return false;
        }

        public boolean showDuringKeyguard() {
            return true;
        }

        public SilentModeToggleAction() {
            super(17302302, 17302301, 17040113, 17040112, 17040111);
        }

        /* access modifiers changed from: package-private */
        public void onToggle(boolean z) {
            if (z) {
                GlobalActionsDialog.this.mAudioManager.setRingerMode(0);
            } else {
                GlobalActionsDialog.this.mAudioManager.setRingerMode(2);
            }
        }
    }

    private static class SilentModeTriStateAction implements Action, View.OnClickListener {
        private final int[] ITEM_IDS = {16909197, 16909198, 16909199};
        private final AudioManager mAudioManager;
        private final Handler mHandler;

        private int indexToRingerMode(int i) {
            return i;
        }

        private int ringerModeToIndex(int i) {
            return i;
        }

        public boolean isEnabled() {
            return true;
        }

        public void onPress() {
        }

        public boolean showBeforeProvisioning() {
            return false;
        }

        public boolean showDuringKeyguard() {
            return true;
        }

        SilentModeTriStateAction(AudioManager audioManager, Handler handler) {
            this.mAudioManager = audioManager;
            this.mHandler = handler;
        }

        public View create(Context context, View view, ViewGroup viewGroup, LayoutInflater layoutInflater) {
            View inflate = layoutInflater.inflate(17367161, viewGroup, false);
            int ringerMode = this.mAudioManager.getRingerMode();
            ringerModeToIndex(ringerMode);
            int i = 0;
            while (i < 3) {
                View findViewById = inflate.findViewById(this.ITEM_IDS[i]);
                findViewById.setSelected(ringerMode == i);
                findViewById.setTag(Integer.valueOf(i));
                findViewById.setOnClickListener(this);
                i++;
            }
            return inflate;
        }

        public void onClick(View view) {
            if (view.getTag() instanceof Integer) {
                int intValue = ((Integer) view.getTag()).intValue();
                AudioManager audioManager = this.mAudioManager;
                indexToRingerMode(intValue);
                audioManager.setRingerMode(intValue);
                this.mHandler.sendEmptyMessageDelayed(0, 200);
            }
        }
    }

    /* access modifiers changed from: private */
    public void addNewItems() {
        this.mItems.clear();
        this.mItems.add(this.mRestartHot);
        this.mItems.add(this.mRestartRecovery);
        this.mItems.add(this.mRestartBootloader);
        this.mItems.add(this.mRestartSystemUI);
    }

    /* access modifiers changed from: private */
    public void onAirplaneModeChanged() {
        if (!this.mHasTelephony) {
            boolean z = false;
            if (Settings.Global.getInt(this.mContext.getContentResolver(), "airplane_mode_on", 0) == 1) {
                z = true;
            }
            this.mAirplaneState = z ? ToggleAction.State.On : ToggleAction.State.Off;
            this.mAirplaneModeOn.updateState(this.mAirplaneState);
        }
    }

    /* access modifiers changed from: private */
    public void changeAirplaneModeSystemSetting(boolean z) {
        Settings.Global.putInt(this.mContext.getContentResolver(), "airplane_mode_on", z ? 1 : 0);
        Intent intent = new Intent("android.intent.action.AIRPLANE_MODE");
        intent.addFlags(536870912);
        intent.putExtra("state", z);
        this.mContext.sendBroadcastAsUser(intent, UserHandle.ALL);
        if (!this.mHasTelephony) {
            this.mAirplaneState = z ? ToggleAction.State.On : ToggleAction.State.Off;
        }
    }

    private static final class ActionsDialog extends Dialog implements DialogInterface, ColorExtractor.OnColorsChangedListener {
        private final MyAdapter mAdapter;
        private Drawable mBackgroundDrawable;
        private final SysuiColorExtractor mColorExtractor;
        /* access modifiers changed from: private */
        public final Context mContext;
        private MultiListLayout mGlobalActionsLayout;
        private boolean mHadTopUi;
        private boolean mKeyguardShowing;
        /* access modifiers changed from: private */
        public final GlobalActionsPanelPlugin.PanelViewController mPanelController;
        private ResetOrientationData mResetOrientationData;
        private float mScrimAlpha;
        private boolean mShowing;
        private final IStatusBarService mStatusBarService;
        private final StatusBarWindowController mStatusBarWindowController;
        private final IBinder mToken = new Binder();

        ActionsDialog(Context context, MyAdapter myAdapter, GlobalActionsPanelPlugin.PanelViewController panelViewController) {
            super(context, C1785R$style.Theme_SystemUI_Dialog_GlobalActions);
            this.mContext = context;
            this.mAdapter = myAdapter;
            this.mColorExtractor = (SysuiColorExtractor) Dependency.get(SysuiColorExtractor.class);
            this.mStatusBarService = (IStatusBarService) Dependency.get(IStatusBarService.class);
            this.mStatusBarWindowController = (StatusBarWindowController) Dependency.get(StatusBarWindowController.class);
            Window window = getWindow();
            window.requestFeature(1);
            window.getDecorView();
            window.getAttributes().systemUiVisibility |= 1792;
            window.setLayout(-1, -1);
            window.clearFlags(2);
            window.addFlags(17629472);
            window.setType(2020);
            setTitle(17040115);
            this.mPanelController = panelViewController;
            initializeLayout();
        }

        private boolean shouldUsePanel() {
            GlobalActionsPanelPlugin.PanelViewController panelViewController = this.mPanelController;
            return (panelViewController == null || panelViewController.getPanelContent() == null) ? false : true;
        }

        private void initializePanel() {
            int rotation = RotationUtils.getRotation(this.mContext);
            boolean isRotationLocked = RotationPolicy.isRotationLocked(this.mContext);
            if (rotation == 0) {
                if (!isRotationLocked) {
                    if (this.mResetOrientationData == null) {
                        this.mResetOrientationData = new ResetOrientationData();
                        this.mResetOrientationData.locked = false;
                    }
                    this.mGlobalActionsLayout.post(new Runnable() {
                        public final void run() {
                            GlobalActionsDialog.ActionsDialog.this.lambda$initializePanel$1$GlobalActionsDialog$ActionsDialog();
                        }
                    });
                }
                setRotationSuggestionsEnabled(false);
                ((FrameLayout) findViewById(C1777R$id.global_actions_panel_container)).addView(this.mPanelController.getPanelContent(), new FrameLayout.LayoutParams(-1, -1));
                this.mBackgroundDrawable = this.mPanelController.getBackgroundDrawable();
                this.mScrimAlpha = 1.0f;
            } else if (isRotationLocked) {
                if (this.mResetOrientationData == null) {
                    this.mResetOrientationData = new ResetOrientationData();
                    ResetOrientationData resetOrientationData = this.mResetOrientationData;
                    resetOrientationData.locked = true;
                    resetOrientationData.rotation = rotation;
                }
                this.mGlobalActionsLayout.post(new Runnable() {
                    public final void run() {
                        GlobalActionsDialog.ActionsDialog.this.lambda$initializePanel$0$GlobalActionsDialog$ActionsDialog();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$initializePanel$0$GlobalActionsDialog$ActionsDialog() {
            RotationPolicy.setRotationLockAtAngle(this.mContext, false, 0);
        }

        public /* synthetic */ void lambda$initializePanel$1$GlobalActionsDialog$ActionsDialog() {
            RotationPolicy.setRotationLockAtAngle(this.mContext, true, 0);
        }

        private void initializeLayout() {
            setContentView(getGlobalActionsLayoutId(this.mContext));
            fixNavBarClipping();
            this.mGlobalActionsLayout = (MultiListLayout) findViewById(C1777R$id.global_actions_view);
            this.mGlobalActionsLayout.setOutsideTouchListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    GlobalActionsDialog.ActionsDialog.this.lambda$initializeLayout$2$GlobalActionsDialog$ActionsDialog(view);
                }
            });
            ((View) this.mGlobalActionsLayout.getParent()).setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    GlobalActionsDialog.ActionsDialog.this.lambda$initializeLayout$3$GlobalActionsDialog$ActionsDialog(view);
                }
            });
            this.mGlobalActionsLayout.setListViewAccessibilityDelegate(new View.AccessibilityDelegate() {
                public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                    accessibilityEvent.getText().add(ActionsDialog.this.mContext.getString(17040115));
                    return true;
                }
            });
            this.mGlobalActionsLayout.setRotationListener(new MultiListLayout.RotationListener() {
                public final void onRotate(int i, int i2) {
                    GlobalActionsDialog.ActionsDialog.this.onRotate(i, i2);
                }
            });
            this.mGlobalActionsLayout.setAdapter(this.mAdapter);
            if (shouldUsePanel()) {
                initializePanel();
            }
            if (this.mBackgroundDrawable == null) {
                this.mBackgroundDrawable = new ScrimDrawable();
                this.mScrimAlpha = 0.2f;
            }
            getWindow().setBackgroundDrawable(this.mBackgroundDrawable);
        }

        public /* synthetic */ void lambda$initializeLayout$2$GlobalActionsDialog$ActionsDialog(View view) {
            dismiss();
        }

        public /* synthetic */ void lambda$initializeLayout$3$GlobalActionsDialog$ActionsDialog(View view) {
            dismiss();
        }

        private void fixNavBarClipping() {
            ViewGroup viewGroup = (ViewGroup) findViewById(16908290);
            viewGroup.setClipChildren(false);
            viewGroup.setClipToPadding(false);
            ViewGroup viewGroup2 = (ViewGroup) viewGroup.getParent();
            viewGroup2.setClipChildren(false);
            viewGroup2.setClipToPadding(false);
        }

        private int getGlobalActionsLayoutId(Context context) {
            int rotation = RotationUtils.getRotation(context);
            boolean z = GlobalActionsDialog.isForceGridEnabled(context) || (shouldUsePanel() && rotation == 0);
            if (rotation == 2) {
                if (z) {
                    return C1779R$layout.global_actions_grid_seascape;
                }
                return C1779R$layout.global_actions_column_seascape;
            } else if (z) {
                return C1779R$layout.global_actions_grid;
            } else {
                return C1779R$layout.global_actions_column;
            }
        }

        public void refreshList() {
            this.mGlobalActionsLayout.updateList();
        }

        /* access modifiers changed from: protected */
        public void onStart() {
            super.setCanceledOnTouchOutside(true);
            super.onStart();
            this.mGlobalActionsLayout.updateList();
            if (this.mBackgroundDrawable instanceof ScrimDrawable) {
                this.mColorExtractor.addOnColorsChangedListener(this);
                updateColors(this.mColorExtractor.getNeutralColors(), false);
            }
        }

        private void updateColors(ColorExtractor.GradientColors gradientColors, boolean z) {
            ScrimDrawable scrimDrawable = this.mBackgroundDrawable;
            if (scrimDrawable instanceof ScrimDrawable) {
                scrimDrawable.setColor(gradientColors.getMainColor(), z);
                View decorView = getWindow().getDecorView();
                if (gradientColors.supportsDarkText()) {
                    decorView.setSystemUiVisibility(8208);
                } else {
                    decorView.setSystemUiVisibility(0);
                }
            }
        }

        /* access modifiers changed from: protected */
        public void onStop() {
            super.onStop();
            this.mColorExtractor.removeOnColorsChangedListener(this);
        }

        public void show() {
            super.show();
            this.mShowing = true;
            this.mHadTopUi = this.mStatusBarWindowController.getForceHasTopUi();
            this.mStatusBarWindowController.setForceHasTopUi(true);
            this.mBackgroundDrawable.setAlpha(0);
            MultiListLayout multiListLayout = this.mGlobalActionsLayout;
            multiListLayout.setTranslationX(multiListLayout.getAnimationOffsetX());
            MultiListLayout multiListLayout2 = this.mGlobalActionsLayout;
            multiListLayout2.setTranslationY(multiListLayout2.getAnimationOffsetY());
            this.mGlobalActionsLayout.setAlpha(0.0f);
            this.mGlobalActionsLayout.animate().alpha(1.0f).translationX(0.0f).translationY(0.0f).setDuration(200).setInterpolator(Interpolators.FAST_OUT_SLOW_IN).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    GlobalActionsDialog.ActionsDialog.this.lambda$show$4$GlobalActionsDialog$ActionsDialog(valueAnimator);
                }
            }).start();
        }

        public /* synthetic */ void lambda$show$4$GlobalActionsDialog$ActionsDialog(ValueAnimator valueAnimator) {
            this.mBackgroundDrawable.setAlpha((int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * this.mScrimAlpha * 255.0f));
        }

        public void dismiss() {
            if (this.mShowing) {
                this.mShowing = false;
                this.mGlobalActionsLayout.setTranslationX(0.0f);
                this.mGlobalActionsLayout.setTranslationY(0.0f);
                this.mGlobalActionsLayout.setAlpha(1.0f);
                this.mGlobalActionsLayout.animate().alpha(0.0f).translationX(this.mGlobalActionsLayout.getAnimationOffsetX()).translationY(this.mGlobalActionsLayout.getAnimationOffsetY()).setDuration(200).withEndAction(new Runnable() {
                    public final void run() {
                        GlobalActionsDialog.ActionsDialog.this.completeDismiss();
                    }
                }).setInterpolator(new SystemUIInterpolators$LogAccelerateInterpolator()).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        GlobalActionsDialog.ActionsDialog.this.lambda$dismiss$5$GlobalActionsDialog$ActionsDialog(valueAnimator);
                    }
                }).start();
                dismissPanel();
                resetOrientation();
            }
        }

        public /* synthetic */ void lambda$dismiss$5$GlobalActionsDialog$ActionsDialog(ValueAnimator valueAnimator) {
            this.mBackgroundDrawable.setAlpha((int) ((1.0f - ((Float) valueAnimator.getAnimatedValue()).floatValue()) * this.mScrimAlpha * 255.0f));
        }

        /* access modifiers changed from: package-private */
        public void dismissImmediately() {
            this.mShowing = false;
            dismissPanel();
            resetOrientation();
            completeDismiss();
        }

        /* access modifiers changed from: private */
        public void completeDismiss() {
            this.mStatusBarWindowController.setForceHasTopUi(this.mHadTopUi);
            super.dismiss();
        }

        private void dismissPanel() {
            GlobalActionsPanelPlugin.PanelViewController panelViewController = this.mPanelController;
            if (panelViewController != null) {
                panelViewController.onDismissed();
            }
        }

        private void setRotationSuggestionsEnabled(boolean z) {
            try {
                this.mStatusBarService.disable2ForUser(z ? 0 : 16, this.mToken, this.mContext.getPackageName(), Binder.getCallingUserHandle().getIdentifier());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        private void resetOrientation() {
            ResetOrientationData resetOrientationData = this.mResetOrientationData;
            if (resetOrientationData != null) {
                RotationPolicy.setRotationLockAtAngle(this.mContext, resetOrientationData.locked, resetOrientationData.rotation);
            }
            setRotationSuggestionsEnabled(true);
        }

        public void onColorsChanged(ColorExtractor colorExtractor, int i) {
            if (this.mKeyguardShowing) {
                if ((i & 2) != 0) {
                    updateColors(colorExtractor.getColors(2), true);
                }
            } else if ((i & 1) != 0) {
                updateColors(colorExtractor.getColors(1), true);
            }
        }

        public void setKeyguardShowing(boolean z) {
            this.mKeyguardShowing = z;
        }

        public void refreshDialog() {
            initializeLayout();
            this.mGlobalActionsLayout.updateList();
        }

        public void onRotate(int i, int i2) {
            if (this.mShowing) {
                refreshDialog();
            }
        }

        private static class ResetOrientationData {
            public boolean locked;
            public int rotation;

            private ResetOrientationData() {
            }
        }
    }

    private static boolean isPanelDebugModeEnabled(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "global_actions_panel_debug_enabled", 0) == 1;
    }

    /* access modifiers changed from: private */
    public static boolean isForceGridEnabled(Context context) {
        return isPanelDebugModeEnabled(context);
    }

    public static void restartSystemUI(Context context) {
        Process.killProcess(Process.myPid());
    }

    private static void doHotReboot() {
        try {
            IActivityManager asInterface = ActivityManagerNative.asInterface(ServiceManager.checkService("activity"));
            if (asInterface != null) {
                asInterface.restart();
            }
        } catch (RemoteException e) {
            Log.e("GlobalActionsDialog", "failure trying to perform hot reboot", e);
        }
    }
}
