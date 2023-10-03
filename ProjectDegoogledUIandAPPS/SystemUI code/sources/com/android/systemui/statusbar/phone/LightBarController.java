package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import com.android.internal.colorextraction.ColorExtractor;
import com.android.systemui.C1774R$color;
import com.android.systemui.Dependency;
import com.android.systemui.Dumpable;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class LightBarController implements BatteryController.BatteryStateChangeCallback, Dumpable, ConfigurationController.ConfigurationListener {
    private final BatteryController mBatteryController;
    private BiometricUnlockController mBiometricUnlockController;
    /* access modifiers changed from: private */
    public Context mContext;
    private CustomSettingsObserver mCustomSettingsObserver = new CustomSettingsObserver(this.mHandler);
    private final Color mDarkModeColor;
    private boolean mDirectReplying;
    private boolean mDockedLight;
    private int mDockedStackVisibility;
    private boolean mForceDarkForScrim;
    private boolean mForceDarkIcons = false;
    private boolean mFullscreenLight;
    private int mFullscreenStackVisibility;
    private Handler mHandler = new Handler();
    private boolean mHasLightNavigationBar;
    private final Rect mLastDockedBounds = new Rect();
    private final Rect mLastFullscreenBounds = new Rect();
    private int mLastNavigationBarMode;
    private int mLastStatusBarMode;
    private boolean mNavbarColorManagedByIme;
    private LightBarTransitionsController mNavigationBarController;
    private boolean mNavigationLight;
    private boolean mQsCustomizing;
    private final SysuiDarkIconDispatcher mStatusBarIconController;
    private int mSystemUiVisibility;

    private boolean isLight(int i, int i2, int i3) {
        return (i2 == 4 || i2 == 6) && ((i & i3) != 0);
    }

    public void onBatteryLevelChanged(int i, boolean z, boolean z2) {
    }

    public void onDensityOrFontScaleChanged() {
    }

    public void onLocaleListChanged() {
    }

    public void onOverlayChanged() {
    }

    public LightBarController(Context context, DarkIconDispatcher darkIconDispatcher, BatteryController batteryController) {
        this.mDarkModeColor = Color.valueOf(context.getColor(C1774R$color.dark_mode_icon_color_single_tone));
        this.mStatusBarIconController = (SysuiDarkIconDispatcher) darkIconDispatcher;
        this.mBatteryController = batteryController;
        this.mBatteryController.addCallback(this);
        this.mContext = context;
        this.mCustomSettingsObserver.observe();
        updateCutout((Configuration) null);
        ((ConfigurationController) Dependency.get(ConfigurationController.class)).addCallback(this);
    }

    public void setNavigationBar(LightBarTransitionsController lightBarTransitionsController) {
        this.mNavigationBarController = lightBarTransitionsController;
        updateNavigation();
    }

    public void setBiometricUnlockController(BiometricUnlockController biometricUnlockController) {
        this.mBiometricUnlockController = biometricUnlockController;
    }

    public void onSystemUiVisibilityChanged(int i, int i2, int i3, Rect rect, Rect rect2, boolean z, int i4, boolean z2) {
        int i5 = this.mFullscreenStackVisibility;
        int i6 = ~i3;
        int i7 = (i & i3) | (i5 & i6);
        int i8 = this.mDockedStackVisibility;
        int i9 = (i2 & i3) | (i6 & i8);
        int i10 = i9 ^ i8;
        if (((i5 ^ i7) & 8192) != 0 || (i10 & 8192) != 0 || z || !this.mLastFullscreenBounds.equals(rect) || !this.mLastDockedBounds.equals(rect2)) {
            this.mFullscreenLight = isLight(i7, i4, 8192);
            this.mDockedLight = isLight(i9, i4, 8192);
            updateStatus(rect, rect2);
        }
        this.mFullscreenStackVisibility = i7;
        this.mDockedStackVisibility = i9;
        this.mLastStatusBarMode = i4;
        this.mNavbarColorManagedByIme = z2;
        this.mLastFullscreenBounds.set(rect);
        this.mLastDockedBounds.set(rect2);
    }

    public void onNavigationVisibilityChanged(int i, int i2, boolean z, int i3, boolean z2) {
        int i4 = this.mSystemUiVisibility;
        int i5 = (i2 & i) | ((~i2) & i4);
        if (((i4 ^ i5) & 16) != 0 || z) {
            boolean z3 = this.mNavigationLight;
            this.mHasLightNavigationBar = isLight(i, i3, 16);
            this.mNavigationLight = this.mHasLightNavigationBar && ((this.mDirectReplying && this.mNavbarColorManagedByIme) || !this.mForceDarkForScrim) && !this.mQsCustomizing;
            if (this.mNavigationLight != z3) {
                updateNavigation();
            }
        }
        this.mSystemUiVisibility = i5;
        this.mLastNavigationBarMode = i3;
        this.mNavbarColorManagedByIme = z2;
    }

    private void reevaluate() {
        onSystemUiVisibilityChanged(this.mFullscreenStackVisibility, this.mDockedStackVisibility, 0, this.mLastFullscreenBounds, this.mLastDockedBounds, true, this.mLastStatusBarMode, this.mNavbarColorManagedByIme);
        onNavigationVisibilityChanged(this.mSystemUiVisibility, 0, true, this.mLastNavigationBarMode, this.mNavbarColorManagedByIme);
    }

    public void setQsCustomizing(boolean z) {
        if (this.mQsCustomizing != z) {
            this.mQsCustomizing = z;
            reevaluate();
        }
    }

    public void setDirectReplying(boolean z) {
        if (this.mDirectReplying != z) {
            this.mDirectReplying = z;
            reevaluate();
        }
    }

    public void setScrimState(ScrimState scrimState, float f, ColorExtractor.GradientColors gradientColors) {
        boolean z = this.mForceDarkForScrim;
        this.mForceDarkForScrim = scrimState != ScrimState.BOUNCER && scrimState != ScrimState.BOUNCER_SCRIMMED && f >= 0.1f && !gradientColors.supportsDarkText();
        if (this.mHasLightNavigationBar && this.mForceDarkForScrim != z) {
            reevaluate();
        }
    }

    private boolean animateChange() {
        int mode;
        BiometricUnlockController biometricUnlockController = this.mBiometricUnlockController;
        if (biometricUnlockController == null || (mode = biometricUnlockController.getMode()) == 2 || mode == 1) {
            return false;
        }
        return true;
    }

    private void updateStatus(Rect rect, Rect rect2) {
        boolean z = !rect2.isEmpty();
        boolean z2 = !this.mForceDarkIcons;
        if ((this.mFullscreenLight && this.mDockedLight) || (this.mFullscreenLight && !z)) {
            this.mStatusBarIconController.setIconsDarkArea((Rect) null);
            this.mStatusBarIconController.getTransitionsController().setIconsDark(z2, animateChange());
        } else if ((this.mFullscreenLight || this.mDockedLight) && (this.mFullscreenLight || z)) {
            if (!this.mFullscreenLight) {
                rect = rect2;
            }
            if (rect.isEmpty()) {
                this.mStatusBarIconController.setIconsDarkArea((Rect) null);
            } else {
                this.mStatusBarIconController.setIconsDarkArea(rect);
            }
            this.mStatusBarIconController.getTransitionsController().setIconsDark(z2, animateChange());
        } else {
            this.mStatusBarIconController.getTransitionsController().setIconsDark(false, animateChange());
        }
    }

    private void updateNavigation() {
        LightBarTransitionsController lightBarTransitionsController = this.mNavigationBarController;
        if (lightBarTransitionsController != null) {
            lightBarTransitionsController.setIconsDark(this.mNavigationLight, animateChange());
        }
    }

    public void onPowerSaveChanged(boolean z) {
        reevaluate();
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("LightBarController: ");
        printWriter.print(" mSystemUiVisibility=0x");
        printWriter.print(Integer.toHexString(this.mSystemUiVisibility));
        printWriter.print(" mFullscreenStackVisibility=0x");
        printWriter.print(Integer.toHexString(this.mFullscreenStackVisibility));
        printWriter.print(" mDockedStackVisibility=0x");
        printWriter.println(Integer.toHexString(this.mDockedStackVisibility));
        printWriter.print(" mFullscreenLight=");
        printWriter.print(this.mFullscreenLight);
        printWriter.print(" mDockedLight=");
        printWriter.println(this.mDockedLight);
        printWriter.print(" mLastFullscreenBounds=");
        printWriter.print(this.mLastFullscreenBounds);
        printWriter.print(" mLastDockedBounds=");
        printWriter.println(this.mLastDockedBounds);
        printWriter.print(" mNavigationLight=");
        printWriter.print(this.mNavigationLight);
        printWriter.print(" mHasLightNavigationBar=");
        printWriter.println(this.mHasLightNavigationBar);
        printWriter.print(" mLastStatusBarMode=");
        printWriter.print(this.mLastStatusBarMode);
        printWriter.print(" mLastNavigationBarMode=");
        printWriter.println(this.mLastNavigationBarMode);
        printWriter.print(" mForceDarkForScrim=");
        printWriter.print(this.mForceDarkForScrim);
        printWriter.print(" mQsCustomizing=");
        printWriter.print(this.mQsCustomizing);
        printWriter.print(" mDirectReplying=");
        printWriter.println(this.mDirectReplying);
        printWriter.print(" mNavbarColorManagedByIme=");
        printWriter.println(this.mNavbarColorManagedByIme);
        printWriter.println();
        LightBarTransitionsController transitionsController = this.mStatusBarIconController.getTransitionsController();
        if (transitionsController != null) {
            printWriter.println(" StatusBarTransitionsController:");
            transitionsController.dump(fileDescriptor, printWriter, strArr);
            printWriter.println();
        }
        if (this.mNavigationBarController != null) {
            printWriter.println(" NavigationBarTransitionsController:");
            this.mNavigationBarController.dump(fileDescriptor, printWriter, strArr);
            printWriter.println();
        }
    }

    private class CustomSettingsObserver extends ContentObserver {
        CustomSettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            LightBarController.this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("display_cutout_mode"), false, this);
        }

        public void onChange(boolean z, Uri uri) {
            if (uri.equals(Settings.System.getUriFor("display_cutout_mode"))) {
                LightBarController.this.updateCutout((Configuration) null);
            }
        }

        public void onChange(boolean z) {
            LightBarController.this.updateCutout((Configuration) null);
        }
    }

    public void onConfigChanged(Configuration configuration) {
        updateCutout(configuration);
    }

    /* access modifiers changed from: private */
    public void updateCutout(Configuration configuration) {
        boolean z = true;
        if (configuration == null || configuration.orientation == 1) {
            if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "display_cutout_mode", 0, -2) != 1) {
                z = false;
            }
            this.mForceDarkIcons = z;
            return;
        }
        this.mForceDarkIcons = false;
    }
}
