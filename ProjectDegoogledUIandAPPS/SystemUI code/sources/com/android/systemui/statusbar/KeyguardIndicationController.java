package com.android.systemui.statusbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.hardware.biometrics.BiometricSourceType;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.app.IBatteryStats;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.ViewClippingUtil;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.settingslib.Utils;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1778R$integer;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.Interpolators;
import com.android.systemui.dock.DockManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.phone.KeyguardIndicationTextView;
import com.android.systemui.statusbar.phone.LockIcon;
import com.android.systemui.statusbar.phone.LockscreenGestureLogger;
import com.android.systemui.statusbar.phone.ShadeController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.UnlockMethodCache;
import com.android.systemui.statusbar.policy.AccessibilityController;
import com.android.systemui.util.wakelock.SettableWakeLock;
import com.android.systemui.util.wakelock.WakeLock;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.IllegalFormatConversionException;

public class KeyguardIndicationController implements StatusBarStateController.StateListener, UnlockMethodCache.OnUnlockMethodChangedListener {
    private final AccessibilityController mAccessibilityController;
    private String mAlignmentIndication;
    private final IBatteryStats mBatteryInfo;
    /* access modifiers changed from: private */
    public int mBatteryLevel;
    /* access modifiers changed from: private */
    public boolean mBiometricHelpShowOnlyWhenFailed;
    /* access modifiers changed from: private */
    public int mChargingCurrent;
    /* access modifiers changed from: private */
    public int mChargingSpeed;
    /* access modifiers changed from: private */
    public double mChargingVoltage;
    /* access modifiers changed from: private */
    public int mChargingWattage;
    /* access modifiers changed from: private */
    public final ViewClippingUtil.ClippingParameters mClippingParams;
    /* access modifiers changed from: private */
    public final Context mContext;
    private final DevicePolicyManager mDevicePolicyManager;
    private KeyguardIndicationTextView mDisclosure;
    private final DockManager mDockManager;
    /* access modifiers changed from: private */
    public boolean mDozing;
    /* access modifiers changed from: private */
    public final int mFastThreshold;
    /* access modifiers changed from: private */
    public final Handler mHandler;
    private boolean mHideTransientMessageOnScreenOff;
    /* access modifiers changed from: private */
    public ViewGroup mIndicationArea;
    /* access modifiers changed from: private */
    public ColorStateList mInitialTextColorState;
    /* access modifiers changed from: private */
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    /* access modifiers changed from: private */
    public final LockIcon mLockIcon;
    private final LockPatternUtils mLockPatternUtils;
    private LockscreenGestureLogger mLockscreenGestureLogger;
    /* access modifiers changed from: private */
    public String mMessageToShowOnScreenOn;
    /* access modifiers changed from: private */
    public boolean mPowerCharged;
    /* access modifiers changed from: private */
    public boolean mPowerPluggedIn;
    /* access modifiers changed from: private */
    public boolean mPowerPluggedInWired;
    private String mRestingIndication;
    private final ShadeController mShadeController;
    /* access modifiers changed from: private */
    public final int mSlowThreshold;
    /* access modifiers changed from: private */
    public StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    private final StatusBarStateController mStatusBarStateController;
    /* access modifiers changed from: private */
    public float mTemperature;
    private KeyguardIndicationTextView mTextView;
    private final KeyguardUpdateMonitorCallback mTickReceiver;
    private CharSequence mTransientIndication;
    private ColorStateList mTransientTextColorState;
    private final UnlockMethodCache mUnlockMethodCache;
    private KeyguardUpdateMonitorCallback mUpdateMonitorCallback;
    private final UserManager mUserManager;
    /* access modifiers changed from: private */
    public boolean mVisible;
    private final SettableWakeLock mWakeLock;

    private String getTrustManagedIndication() {
        return null;
    }

    public void onStateChanged(int i) {
    }

    public KeyguardIndicationController(Context context, ViewGroup viewGroup, LockIcon lockIcon) {
        this(context, viewGroup, lockIcon, new LockPatternUtils(context), WakeLock.createPartial(context, "Doze:KeyguardIndication"), (ShadeController) Dependency.get(ShadeController.class), (AccessibilityController) Dependency.get(AccessibilityController.class), UnlockMethodCache.getInstance(context), (StatusBarStateController) Dependency.get(StatusBarStateController.class), KeyguardUpdateMonitor.getInstance(context), (DockManager) Dependency.get(DockManager.class));
    }

    @VisibleForTesting
    KeyguardIndicationController(Context context, ViewGroup viewGroup, LockIcon lockIcon, LockPatternUtils lockPatternUtils, WakeLock wakeLock, ShadeController shadeController, AccessibilityController accessibilityController, UnlockMethodCache unlockMethodCache, StatusBarStateController statusBarStateController, KeyguardUpdateMonitor keyguardUpdateMonitor, DockManager dockManager) {
        this.mLockscreenGestureLogger = new LockscreenGestureLogger();
        this.mAlignmentIndication = "";
        this.mBiometricHelpShowOnlyWhenFailed = false;
        this.mClippingParams = new ViewClippingUtil.ClippingParameters() {
            public boolean shouldFinish(View view) {
                return view == KeyguardIndicationController.this.mIndicationArea;
            }
        };
        this.mTickReceiver = new KeyguardUpdateMonitorCallback() {
            public void onTimeChanged() {
                if (KeyguardIndicationController.this.mVisible) {
                    KeyguardIndicationController.this.updateIndication(false);
                }
            }
        };
        this.mHandler = new Handler() {
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    KeyguardIndicationController.this.hideTransientIndication();
                } else if (i == 2) {
                    KeyguardIndicationController.this.mLockIcon.setTransientBiometricsError(false);
                } else if (i == 3) {
                    KeyguardIndicationController.this.showSwipeUpToUnlock();
                }
            }
        };
        this.mContext = context;
        this.mLockIcon = lockIcon;
        this.mShadeController = shadeController;
        this.mAccessibilityController = accessibilityController;
        this.mUnlockMethodCache = unlockMethodCache;
        this.mStatusBarStateController = statusBarStateController;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mDockManager = dockManager;
        this.mDockManager.addAlignmentStateListener(new DockManager.AlignmentStateListener() {
        });
        LockIcon lockIcon2 = this.mLockIcon;
        if (lockIcon2 != null) {
            lockIcon2.setOnLongClickListener(new View.OnLongClickListener() {
                public final boolean onLongClick(View view) {
                    return KeyguardIndicationController.this.handleLockLongClick(view);
                }
            });
            this.mLockIcon.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    KeyguardIndicationController.this.handleLockClick(view);
                }
            });
        }
        this.mWakeLock = new SettableWakeLock(wakeLock, "KeyguardIndication");
        this.mLockPatternUtils = lockPatternUtils;
        Resources resources = context.getResources();
        this.mSlowThreshold = resources.getInteger(C1778R$integer.config_chargingSlowlyThreshold);
        this.mFastThreshold = resources.getInteger(C1778R$integer.config_chargingFastThreshold);
        this.mUserManager = (UserManager) context.getSystemService(UserManager.class);
        this.mBatteryInfo = IBatteryStats.Stub.asInterface(ServiceManager.getService("batterystats"));
        this.mDevicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        setIndicationArea(viewGroup);
        updateDisclosure();
        this.mKeyguardUpdateMonitor.registerCallback(getKeyguardCallback());
        this.mKeyguardUpdateMonitor.registerCallback(this.mTickReceiver);
        this.mStatusBarStateController.addCallback(this);
        this.mUnlockMethodCache.addListener(this);
        this.mBiometricHelpShowOnlyWhenFailed = resources.getBoolean(C1773R$bool.config_biometricHelpShowOnlyWhenFailed);
    }

    public void setIndicationArea(ViewGroup viewGroup) {
        this.mIndicationArea = viewGroup;
        this.mTextView = (KeyguardIndicationTextView) viewGroup.findViewById(C1777R$id.keyguard_indication_text);
        KeyguardIndicationTextView keyguardIndicationTextView = this.mTextView;
        this.mInitialTextColorState = keyguardIndicationTextView != null ? keyguardIndicationTextView.getTextColors() : ColorStateList.valueOf(-1);
        this.mDisclosure = (KeyguardIndicationTextView) viewGroup.findViewById(C1777R$id.keyguard_indication_enterprise_disclosure);
        updateIndication(false);
    }

    /* access modifiers changed from: private */
    public boolean handleLockLongClick(View view) {
        this.mLockscreenGestureLogger.write(191, 0, 0);
        showTransientIndication(C1784R$string.keyguard_indication_trust_disabled);
        this.mKeyguardUpdateMonitor.onLockIconPressed();
        this.mLockPatternUtils.requireCredentialEntry(KeyguardUpdateMonitor.getCurrentUser());
        return true;
    }

    /* access modifiers changed from: private */
    public void handleLockClick(View view) {
        if (this.mAccessibilityController.isAccessibilityEnabled()) {
            this.mShadeController.animateCollapsePanels(0, true);
        }
    }

    /* access modifiers changed from: protected */
    public KeyguardUpdateMonitorCallback getKeyguardCallback() {
        if (this.mUpdateMonitorCallback == null) {
            this.mUpdateMonitorCallback = new BaseKeyguardCallback();
        }
        return this.mUpdateMonitorCallback;
    }

    /* access modifiers changed from: private */
    public void updateDisclosure() {
        DevicePolicyManager devicePolicyManager = this.mDevicePolicyManager;
        if (devicePolicyManager != null) {
            if (this.mDozing || !devicePolicyManager.isDeviceManaged()) {
                this.mDisclosure.setVisibility(8);
                return;
            }
            CharSequence deviceOwnerOrganizationName = this.mDevicePolicyManager.getDeviceOwnerOrganizationName();
            if (deviceOwnerOrganizationName != null) {
                this.mDisclosure.switchIndication((CharSequence) this.mContext.getResources().getString(C1784R$string.do_disclosure_with_name, new Object[]{deviceOwnerOrganizationName}));
            } else {
                this.mDisclosure.switchIndication(C1784R$string.do_disclosure_generic);
            }
            this.mDisclosure.setVisibility(0);
        }
    }

    public void setVisible(boolean z) {
        this.mVisible = z;
        this.mIndicationArea.setVisibility(z ? 0 : 8);
        if (z) {
            if (!this.mHandler.hasMessages(1)) {
                hideTransientIndication();
            }
            updateIndication(false);
            this.mLockIcon.setAlpha(255);
        } else if (!z) {
            hideTransientIndication();
            this.mLockIcon.setAlpha(0);
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public String getTrustGrantedIndication() {
        return this.mContext.getString(C1784R$string.keyguard_indication_trust_unlocked);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setPowerPluggedIn(boolean z) {
        this.mPowerPluggedIn = z;
    }

    public void hideTransientIndicationDelayed(long j) {
        Handler handler = this.mHandler;
        handler.sendMessageDelayed(handler.obtainMessage(1), j);
    }

    public void showTransientIndication(int i) {
        showTransientIndication((CharSequence) this.mContext.getResources().getString(i));
    }

    public void showTransientIndication(CharSequence charSequence) {
        showTransientIndication(charSequence, this.mInitialTextColorState, false);
    }

    /* access modifiers changed from: private */
    public void showTransientIndication(CharSequence charSequence, ColorStateList colorStateList, boolean z) {
        this.mTransientIndication = charSequence;
        this.mHideTransientMessageOnScreenOff = z && charSequence != null;
        this.mTransientTextColorState = colorStateList;
        this.mHandler.removeMessages(1);
        this.mHandler.removeMessages(3);
        if (this.mDozing && !TextUtils.isEmpty(this.mTransientIndication)) {
            this.mWakeLock.setAcquired(true);
            hideTransientIndicationDelayed(5000);
        }
        updateIndication(false);
    }

    public void hideTransientIndication() {
        if (this.mTransientIndication != null) {
            this.mTransientIndication = null;
            this.mHideTransientMessageOnScreenOff = false;
            this.mHandler.removeMessages(1);
            updateIndication(false);
        }
    }

    /* access modifiers changed from: protected */
    public final void updateIndication(boolean z) {
        String str;
        boolean z2 = false;
        if (TextUtils.isEmpty(this.mTransientIndication)) {
            this.mWakeLock.setAcquired(false);
        }
        if (this.mVisible) {
            String str2 = null;
            if (this.mDozing) {
                this.mTextView.setTextColor(-1);
                if (!TextUtils.isEmpty(this.mTransientIndication)) {
                    this.mTextView.switchIndication(this.mTransientIndication);
                } else if (!TextUtils.isEmpty(this.mAlignmentIndication)) {
                    this.mTextView.switchIndication((CharSequence) this.mAlignmentIndication);
                    this.mTextView.setTextColor(Utils.getColorError(this.mContext));
                } else if (this.mPowerPluggedIn) {
                    String computePowerIndication = computePowerIndication();
                    if (z) {
                        animateText(this.mTextView, computePowerIndication);
                    } else {
                        this.mTextView.switchIndication((CharSequence) computePowerIndication);
                    }
                } else {
                    if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "ambient_battery_percent", 0, -2) != 0) {
                        z2 = true;
                    }
                    if (z2) {
                        StringBuilder sb = new StringBuilder();
                        if (this.mPowerPluggedIn) {
                            str = "⚡︎" + " ";
                        } else {
                            str = "";
                        }
                        sb.append(str);
                        sb.append(NumberFormat.getPercentInstance().format((double) (((float) this.mBatteryLevel) / 100.0f)));
                        this.mTextView.switchIndication((CharSequence) sb.toString());
                        return;
                    }
                    this.mTextView.switchIndication((CharSequence) null);
                }
            } else {
                int currentUser = KeyguardUpdateMonitor.getCurrentUser();
                String trustGrantedIndication = getTrustGrantedIndication();
                String trustManagedIndication = getTrustManagedIndication();
                if (this.mPowerPluggedIn) {
                    str2 = computePowerIndication();
                }
                if (!this.mUserManager.isUserUnlocked(currentUser)) {
                    this.mTextView.switchIndication(17040346);
                    this.mTextView.setTextColor(this.mInitialTextColorState);
                } else if (!TextUtils.isEmpty(this.mTransientIndication)) {
                    this.mTextView.switchIndication(this.mTransientIndication);
                    this.mTextView.setTextColor(this.mTransientTextColorState);
                } else if (!TextUtils.isEmpty(trustGrantedIndication) && this.mKeyguardUpdateMonitor.getUserHasTrust(currentUser)) {
                    if (str2 != null) {
                        this.mTextView.switchIndication((CharSequence) this.mContext.getResources().getString(C1784R$string.keyguard_indication_trust_unlocked_plugged_in, new Object[]{trustGrantedIndication, str2}));
                    } else {
                        this.mTextView.switchIndication((CharSequence) trustGrantedIndication);
                    }
                    this.mTextView.setTextColor(this.mInitialTextColorState);
                } else if (!TextUtils.isEmpty(this.mAlignmentIndication)) {
                    this.mTextView.switchIndication((CharSequence) this.mAlignmentIndication);
                    this.mTextView.setTextColor(Utils.getColorError(this.mContext));
                } else if (this.mPowerPluggedIn) {
                    this.mTextView.setTextColor(this.mInitialTextColorState);
                    if (z) {
                        animateText(this.mTextView, str2);
                    } else {
                        this.mTextView.switchIndication((CharSequence) str2);
                    }
                } else if (TextUtils.isEmpty(trustManagedIndication) || !this.mKeyguardUpdateMonitor.getUserTrustIsManaged(currentUser) || this.mKeyguardUpdateMonitor.getUserHasTrust(currentUser)) {
                    this.mTextView.switchIndication((CharSequence) this.mRestingIndication);
                    this.mTextView.setTextColor(this.mInitialTextColorState);
                } else {
                    this.mTextView.switchIndication((CharSequence) trustManagedIndication);
                    this.mTextView.setTextColor(this.mInitialTextColorState);
                }
            }
        }
    }

    private void animateText(final KeyguardIndicationTextView keyguardIndicationTextView, final String str) {
        int integer = this.mContext.getResources().getInteger(C1778R$integer.wired_charging_keyguard_text_animation_distance);
        int integer2 = this.mContext.getResources().getInteger(C1778R$integer.wired_charging_keyguard_text_animation_duration_up);
        final int integer3 = this.mContext.getResources().getInteger(C1778R$integer.wired_charging_keyguard_text_animation_duration_down);
        keyguardIndicationTextView.animate().cancel();
        ViewClippingUtil.setClippingDeactivated(keyguardIndicationTextView, true, this.mClippingParams);
        keyguardIndicationTextView.animate().translationYBy((float) integer).setInterpolator(Interpolators.LINEAR).setDuration((long) integer2).setListener(new AnimatorListenerAdapter() {
            private boolean mCancelled;

            public void onAnimationStart(Animator animator) {
                keyguardIndicationTextView.switchIndication((CharSequence) str);
            }

            public void onAnimationCancel(Animator animator) {
                keyguardIndicationTextView.setTranslationY(0.0f);
                this.mCancelled = true;
            }

            public void onAnimationEnd(Animator animator) {
                if (this.mCancelled) {
                    ViewClippingUtil.setClippingDeactivated(keyguardIndicationTextView, false, KeyguardIndicationController.this.mClippingParams);
                } else {
                    keyguardIndicationTextView.animate().setDuration((long) integer3).setInterpolator(Interpolators.BOUNCE).translationY(0.0f).setListener(new AnimatorListenerAdapter() {
                        public void onAnimationEnd(Animator animator) {
                            keyguardIndicationTextView.setTranslationY(0.0f);
                            C10902 r1 = C10902.this;
                            ViewClippingUtil.setClippingDeactivated(keyguardIndicationTextView, false, KeyguardIndicationController.this.mClippingParams);
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public String computePowerIndication() {
        long j;
        int i;
        String str;
        String str2;
        String str3;
        if (this.mPowerCharged) {
            return this.mContext.getResources().getString(C1784R$string.keyguard_charged);
        }
        try {
            j = this.mBatteryInfo.computeChargeTimeRemaining();
        } catch (RemoteException e) {
            Log.e("KeyguardIndication", "Error calling IBatteryStats: ", e);
            j = 0;
        }
        boolean z = j > 0;
        if (this.mPowerPluggedInWired) {
            int i2 = this.mChargingSpeed;
            if (i2 != 0) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            if (i2 != 5) {
                                if (z) {
                                    i = C1784R$string.keyguard_indication_charging_time;
                                } else {
                                    i = C1784R$string.keyguard_plugged_in;
                                }
                            } else if (z) {
                                i = C1784R$string.keyguard_indication_vooc_charging_time;
                            } else {
                                i = C1784R$string.keyguard_plugged_in_vooc_charging;
                            }
                        } else if (z) {
                            i = C1784R$string.keyguard_indication_warp_charging_time;
                        } else {
                            i = C1784R$string.keyguard_plugged_in_warp_charging;
                        }
                    } else if (z) {
                        i = C1784R$string.keyguard_indication_dash_charging_time;
                    } else {
                        i = C1784R$string.keyguard_plugged_in_dash_charging;
                    }
                } else if (z) {
                    i = C1784R$string.keyguard_indication_charging_time_fast;
                } else {
                    i = C1784R$string.keyguard_plugged_in_charging_fast;
                }
            } else if (z) {
                i = C1784R$string.keyguard_indication_charging_time_slowly;
            } else {
                i = C1784R$string.keyguard_plugged_in_charging_slowly;
            }
        } else if (z) {
            i = C1784R$string.keyguard_indication_charging_time_wireless;
        } else {
            i = C1784R$string.keyguard_plugged_in_wireless;
        }
        String str4 = "";
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "lockscreen_battery_info", 1, -2) == 1) {
            if (this.mChargingCurrent > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(str4);
                int i3 = this.mChargingCurrent;
                if (i3 < 5) {
                    i3 *= 1000;
                } else if (i3 >= 4000) {
                    i3 /= 1000;
                }
                sb.append(i3);
                sb.append("mA");
                str = sb.toString();
            } else {
                str = str4;
            }
            if (this.mChargingVoltage > 0.0d) {
                StringBuilder sb2 = new StringBuilder();
                if (str == str4) {
                    str3 = str4;
                } else {
                    str3 = str + " · ";
                }
                sb2.append(str3);
                sb2.append(String.format("%.1f", new Object[]{Double.valueOf((this.mChargingVoltage / 1000.0d) / 1000.0d)}));
                sb2.append("V");
                str = sb2.toString();
            }
            if (this.mTemperature > 0.0f) {
                StringBuilder sb3 = new StringBuilder();
                if (str == str4) {
                    str2 = str4;
                } else {
                    str2 = str + " · ";
                }
                sb3.append(str2);
                sb3.append(this.mTemperature / 10.0f);
                sb3.append("°C");
                str = sb3.toString();
            }
            if (str != str4) {
                str4 = "\n" + str;
            } else {
                str4 = str;
            }
        }
        String format = NumberFormat.getPercentInstance().format((double) (((float) this.mBatteryLevel) / 100.0f));
        if (z) {
            String formatShortElapsedTimeRoundingUpToMinutes = Formatter.formatShortElapsedTimeRoundingUpToMinutes(this.mContext, j);
            try {
                return this.mContext.getResources().getString(i, new Object[]{formatShortElapsedTimeRoundingUpToMinutes, format}) + str4;
            } catch (IllegalFormatConversionException unused) {
                return this.mContext.getResources().getString(i, new Object[]{formatShortElapsedTimeRoundingUpToMinutes}) + str4;
            }
        } else {
            try {
                return this.mContext.getResources().getString(i, new Object[]{format}) + str4;
            } catch (IllegalFormatConversionException unused2) {
                return this.mContext.getResources().getString(i) + str4;
            }
        }
    }

    public void setStatusBarKeyguardViewManager(StatusBarKeyguardViewManager statusBarKeyguardViewManager) {
        this.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager;
    }

    /* access modifiers changed from: private */
    public void showSwipeUpToUnlock() {
        if (!this.mDozing) {
            if (this.mStatusBarKeyguardViewManager.isBouncerShowing()) {
                this.mStatusBarKeyguardViewManager.showBouncerMessage(this.mContext.getString(C1784R$string.keyguard_retry), this.mInitialTextColorState);
            } else if (this.mKeyguardUpdateMonitor.isScreenOn()) {
                showTransientIndication(this.mContext.getString(C1784R$string.keyguard_unlock), this.mInitialTextColorState, true);
                hideTransientIndicationDelayed(5000);
            }
        }
    }

    public void setDozing(boolean z) {
        if (this.mDozing != z) {
            this.mDozing = z;
            if (!this.mHideTransientMessageOnScreenOff || !this.mDozing) {
                updateIndication(false);
            } else {
                hideTransientIndication();
            }
            updateDisclosure();
        }
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("KeyguardIndicationController:");
        printWriter.println("  mTransientTextColorState: " + this.mTransientTextColorState);
        printWriter.println("  mInitialTextColorState: " + this.mInitialTextColorState);
        printWriter.println("  mPowerPluggedInWired: " + this.mPowerPluggedInWired);
        printWriter.println("  mPowerPluggedIn: " + this.mPowerPluggedIn);
        printWriter.println("  mPowerCharged: " + this.mPowerCharged);
        printWriter.println("  mChargingSpeed: " + this.mChargingSpeed);
        printWriter.println("  mChargingWattage: " + this.mChargingWattage);
        printWriter.println("  mMessageToShowOnScreenOn: " + this.mMessageToShowOnScreenOn);
        printWriter.println("  mDozing: " + this.mDozing);
        printWriter.println("  mBatteryLevel: " + this.mBatteryLevel);
        StringBuilder sb = new StringBuilder();
        sb.append("  mTextView.getText(): ");
        KeyguardIndicationTextView keyguardIndicationTextView = this.mTextView;
        sb.append(keyguardIndicationTextView == null ? null : keyguardIndicationTextView.getText());
        printWriter.println(sb.toString());
        printWriter.println("  computePowerIndication(): " + computePowerIndication());
    }

    public void onDozingChanged(boolean z) {
        setDozing(z);
    }

    public void onUnlockMethodStateChanged() {
        updateIndication(!this.mDozing);
    }

    protected class BaseKeyguardCallback extends KeyguardUpdateMonitorCallback {
        protected BaseKeyguardCallback() {
        }

        public void onRefreshBatteryInfo(KeyguardUpdateMonitor.BatteryStatus batteryStatus) {
            int i = batteryStatus.status;
            boolean z = false;
            boolean z2 = i == 2 || i == 5;
            boolean access$500 = KeyguardIndicationController.this.mPowerPluggedIn;
            boolean unused = KeyguardIndicationController.this.mPowerPluggedInWired = batteryStatus.isPluggedInWired() && z2;
            boolean unused2 = KeyguardIndicationController.this.mPowerPluggedIn = batteryStatus.isPluggedIn() && z2;
            boolean unused3 = KeyguardIndicationController.this.mPowerCharged = batteryStatus.isCharged();
            int unused4 = KeyguardIndicationController.this.mChargingCurrent = batteryStatus.maxChargingCurrent;
            double unused5 = KeyguardIndicationController.this.mChargingVoltage = (double) batteryStatus.maxChargingVoltage;
            int unused6 = KeyguardIndicationController.this.mChargingWattage = batteryStatus.maxChargingWattage;
            float unused7 = KeyguardIndicationController.this.mTemperature = batteryStatus.temperature;
            KeyguardIndicationController keyguardIndicationController = KeyguardIndicationController.this;
            int unused8 = keyguardIndicationController.mChargingSpeed = batteryStatus.getChargingSpeed(keyguardIndicationController.mSlowThreshold, KeyguardIndicationController.this.mFastThreshold);
            int unused9 = KeyguardIndicationController.this.mBatteryLevel = batteryStatus.level;
            KeyguardIndicationController keyguardIndicationController2 = KeyguardIndicationController.this;
            if (!access$500 && keyguardIndicationController2.mPowerPluggedInWired) {
                z = true;
            }
            keyguardIndicationController2.updateIndication(z);
            if (!KeyguardIndicationController.this.mDozing) {
                return;
            }
            if (!access$500 && KeyguardIndicationController.this.mPowerPluggedIn) {
                KeyguardIndicationController keyguardIndicationController3 = KeyguardIndicationController.this;
                keyguardIndicationController3.showTransientIndication((CharSequence) keyguardIndicationController3.computePowerIndication());
                KeyguardIndicationController.this.hideTransientIndicationDelayed(5000);
            } else if (access$500 && !KeyguardIndicationController.this.mPowerPluggedIn) {
                KeyguardIndicationController.this.hideTransientIndication();
            }
        }

        public void onKeyguardVisibilityChanged(boolean z) {
            if (z) {
                KeyguardIndicationController.this.updateDisclosure();
            }
        }

        public void onBiometricHelp(int i, String str, BiometricSourceType biometricSourceType) {
            if ((!KeyguardIndicationController.this.mBiometricHelpShowOnlyWhenFailed || i == -1) && KeyguardIndicationController.this.mKeyguardUpdateMonitor.isUnlockingWithBiometricAllowed()) {
                boolean z = i == -2;
                if (KeyguardIndicationController.this.mStatusBarKeyguardViewManager.isBouncerShowing()) {
                    KeyguardIndicationController.this.mStatusBarKeyguardViewManager.showBouncerMessage(str, KeyguardIndicationController.this.mInitialTextColorState);
                } else if (KeyguardIndicationController.this.mKeyguardUpdateMonitor.isScreenOn()) {
                    KeyguardIndicationController keyguardIndicationController = KeyguardIndicationController.this;
                    keyguardIndicationController.showTransientIndication(str, keyguardIndicationController.mInitialTextColorState, z);
                    if (!z) {
                        KeyguardIndicationController.this.hideTransientIndicationDelayed(1300);
                    }
                }
                if (z) {
                    KeyguardIndicationController.this.mHandler.sendMessageDelayed(KeyguardIndicationController.this.mHandler.obtainMessage(3), 1300);
                }
            }
        }

        public void onBiometricError(int i, String str, BiometricSourceType biometricSourceType) {
            if (!shouldSuppressBiometricError(i, biometricSourceType, KeyguardIndicationController.this.mKeyguardUpdateMonitor)) {
                animatePadlockError();
                if (i == 3) {
                    KeyguardIndicationController.this.showSwipeUpToUnlock();
                } else if (KeyguardIndicationController.this.mStatusBarKeyguardViewManager.isBouncerShowing()) {
                    KeyguardIndicationController.this.mStatusBarKeyguardViewManager.showBouncerMessage(str, KeyguardIndicationController.this.mInitialTextColorState);
                } else if (KeyguardIndicationController.this.mKeyguardUpdateMonitor.isScreenOn()) {
                    KeyguardIndicationController.this.showTransientIndication((CharSequence) str);
                    KeyguardIndicationController.this.hideTransientIndicationDelayed(5000);
                } else {
                    String unused = KeyguardIndicationController.this.mMessageToShowOnScreenOn = str;
                }
            }
        }

        private void animatePadlockError() {
            KeyguardIndicationController.this.mLockIcon.setTransientBiometricsError(true);
            KeyguardIndicationController.this.mHandler.removeMessages(2);
            KeyguardIndicationController.this.mHandler.sendMessageDelayed(KeyguardIndicationController.this.mHandler.obtainMessage(2), 1300);
        }

        private boolean shouldSuppressBiometricError(int i, BiometricSourceType biometricSourceType, KeyguardUpdateMonitor keyguardUpdateMonitor) {
            if (biometricSourceType == BiometricSourceType.FINGERPRINT) {
                return shouldSuppressFingerprintError(i, keyguardUpdateMonitor);
            }
            if (biometricSourceType == BiometricSourceType.FACE) {
                return shouldSuppressFaceError(i, keyguardUpdateMonitor);
            }
            return false;
        }

        private boolean shouldSuppressFingerprintError(int i, KeyguardUpdateMonitor keyguardUpdateMonitor) {
            return (!keyguardUpdateMonitor.isUnlockingWithBiometricAllowed() && i != 9) || i == 5;
        }

        private boolean shouldSuppressFaceError(int i, KeyguardUpdateMonitor keyguardUpdateMonitor) {
            return (!keyguardUpdateMonitor.isUnlockingWithBiometricAllowed() && i != 9) || i == 5;
        }

        public void onTrustAgentErrorMessage(CharSequence charSequence) {
            KeyguardIndicationController keyguardIndicationController = KeyguardIndicationController.this;
            keyguardIndicationController.showTransientIndication(charSequence, Utils.getColorError(keyguardIndicationController.mContext), false);
        }

        public void onScreenTurnedOn() {
            if (KeyguardIndicationController.this.mMessageToShowOnScreenOn != null) {
                KeyguardIndicationController keyguardIndicationController = KeyguardIndicationController.this;
                keyguardIndicationController.showTransientIndication(keyguardIndicationController.mMessageToShowOnScreenOn, Utils.getColorError(KeyguardIndicationController.this.mContext), false);
                KeyguardIndicationController.this.hideTransientIndicationDelayed(5000);
                String unused = KeyguardIndicationController.this.mMessageToShowOnScreenOn = null;
            }
        }

        public void onBiometricRunningStateChanged(boolean z, BiometricSourceType biometricSourceType) {
            if (z) {
                KeyguardIndicationController.this.hideTransientIndication();
                String unused = KeyguardIndicationController.this.mMessageToShowOnScreenOn = null;
            }
        }

        public void onBiometricAuthenticated(int i, BiometricSourceType biometricSourceType) {
            super.onBiometricAuthenticated(i, biometricSourceType);
            KeyguardIndicationController.this.mHandler.sendEmptyMessage(1);
        }

        public void onUserUnlocked() {
            if (KeyguardIndicationController.this.mVisible) {
                KeyguardIndicationController.this.updateIndication(false);
            }
        }
    }
}
