package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.hardware.display.AmbientDisplayConfiguration;
import android.os.PowerManager;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.MathUtils;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1778R$integer;
import com.android.systemui.doze.AlwaysOnDisplayPolicy;

public class DozeParameters implements com.android.systemui.plugins.statusbar.DozeParameters {
    public static final boolean FORCE_BLANKING = SystemProperties.getBoolean("debug.force_blanking", false);
    public static final boolean FORCE_NO_BLANKING = SystemProperties.getBoolean("debug.force_no_blanking", false);
    private static DozeParameters sInstance;
    private final AlwaysOnDisplayPolicy mAlwaysOnPolicy = new AlwaysOnDisplayPolicy(this.mContext);
    private final AmbientDisplayConfiguration mAmbientDisplayConfiguration = new AmbientDisplayConfiguration(this.mContext);
    private final Context mContext;
    private boolean mControlScreenOffAnimation = (!getDisplayNeedsBlanking());
    private final PowerManager mPowerManager = ((PowerManager) this.mContext.getSystemService(PowerManager.class));

    public static DozeParameters getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DozeParameters(context);
        }
        return sInstance;
    }

    @VisibleForTesting
    protected DozeParameters(Context context) {
        this.mContext = context.getApplicationContext();
        this.mPowerManager.setDozeAfterScreenOff(!this.mControlScreenOffAnimation);
    }

    public boolean getDisplayStateSupported() {
        return getBoolean("doze.display.supported", C1773R$bool.doze_display_state_supported);
    }

    public boolean getDozeSuspendDisplayStateSupported() {
        return this.mContext.getResources().getBoolean(C1773R$bool.doze_suspend_display_state_supported);
    }

    public float getScreenBrightnessDoze() {
        return ((float) this.mContext.getResources().getInteger(17694906)) / 255.0f;
    }

    public int getPulseVisibleDuration() {
        return getInt("doze.pulse.duration.visible", C1778R$integer.doze_pulse_duration_visible);
    }

    public boolean getPulseOnSigMotion() {
        return getBoolean("doze.pulse.sigmotion", C1773R$bool.doze_pulse_on_significant_motion);
    }

    public boolean getProxCheckBeforePulse() {
        return getBoolean("doze.pulse.proxcheck", C1773R$bool.doze_proximity_check_before_pulse);
    }

    public int getPickupVibrationThreshold() {
        return getInt("doze.pickup.vibration.threshold", C1778R$integer.doze_pickup_vibration_threshold);
    }

    public long getWallpaperAodDuration() {
        if (shouldControlScreenOff()) {
            return 2500;
        }
        return this.mAlwaysOnPolicy.wallpaperVisibilityDuration;
    }

    public long getWallpaperFadeOutDuration() {
        return this.mAlwaysOnPolicy.wallpaperFadeOutDuration;
    }

    public boolean getAlwaysOn() {
        return this.mAmbientDisplayConfiguration.alwaysOnEnabled(-2);
    }

    public boolean getDisplayNeedsBlanking() {
        boolean z = Settings.System.getInt(this.mContext.getContentResolver(), "screen_off_fod", 0) != 0;
        if (!FORCE_BLANKING) {
            if (FORCE_NO_BLANKING) {
                return false;
            }
            if (!this.mContext.getResources().getBoolean(17891420) && !z) {
                return false;
            }
        }
        return true;
    }

    public boolean shouldControlScreenOff() {
        return this.mControlScreenOffAnimation;
    }

    public void setControlScreenOffAnimation(boolean z) {
        if (this.mControlScreenOffAnimation != z) {
            this.mControlScreenOffAnimation = z;
            getPowerManager().setDozeAfterScreenOff(!z);
        }
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public PowerManager getPowerManager() {
        return this.mPowerManager;
    }

    private boolean getBoolean(String str, int i) {
        return SystemProperties.getBoolean(str, this.mContext.getResources().getBoolean(i));
    }

    private int getInt(String str, int i) {
        return MathUtils.constrain(SystemProperties.getInt(str, this.mContext.getResources().getInteger(i)), 0, 60000);
    }

    public int getPulseVisibleDurationExtended() {
        return getPulseVisibleDuration() * 2;
    }

    public boolean doubleTapReportsTouchCoordinates() {
        return this.mContext.getResources().getBoolean(C1773R$bool.doze_double_tap_reports_touch_coordinates);
    }

    public AlwaysOnDisplayPolicy getPolicy() {
        return this.mAlwaysOnPolicy;
    }
}
