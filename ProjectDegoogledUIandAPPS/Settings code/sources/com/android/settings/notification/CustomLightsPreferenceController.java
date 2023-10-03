package com.android.settings.notification;

import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.havoc.config.center.C1715R;
import com.havoc.support.colorpicker.ColorPickerPreference;

public class CustomLightsPreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    private ColorPickerPreference mCustomLight;
    private int mLedColor = 0;

    public String getPreferenceKey() {
        return "custom_light";
    }

    public CustomLightsPreferenceController(Context context, NotificationBackend notificationBackend) {
        super(context, notificationBackend);
    }

    public boolean isAvailable() {
        if (this.mContext.getResources().getBoolean(17891481) && super.isAvailable() && this.mChannel != null && checkCanBeVisible(3) && canPulseLight() && isMultiColorLed()) {
            return true;
        }
        return false;
    }

    public void updateState(Preference preference) {
        if (this.mChannel != null) {
            this.mCustomLight = (ColorPickerPreference) preference;
            int color = this.mContext.getResources().getColor(17170725);
            this.mCustomLight.setDefaultValue(color);
            if (this.mChannel.getLightColor() != 0) {
                color = this.mChannel.getLightColor();
            }
            this.mLedColor = color;
            this.mCustomLight.setAlphaSliderEnabled(false);
            this.mCustomLight.setNewPreviewColor(this.mLedColor);
            updateSummary();
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (this.mChannel == null) {
            return true;
        }
        this.mLedColor = ((Integer) obj).intValue();
        this.mChannel.setLightColor(this.mLedColor);
        saveChannel();
        showLedPreview();
        updateSummary();
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean canPulseLight() {
        if (!this.mContext.getResources().getBoolean(17891494)) {
            return false;
        }
        return Settings.System.getInt(this.mContext.getContentResolver(), "notification_light_pulse", 1) == 1;
    }

    /* access modifiers changed from: package-private */
    public boolean isMultiColorLed() {
        return this.mContext.getResources().getBoolean(17891507);
    }

    private void showLedPreview() {
        if (this.mChannel.shouldShowLights()) {
            if (this.mLedColor == -1) {
                this.mLedColor = 16777215;
            }
            this.mNm.forcePulseLedLight(this.mLedColor, this.mChannel.getLightOnTime(), this.mChannel.getLightOffTime());
        }
    }

    private void updateSummary() {
        String format = String.format("#%06x", new Object[]{Integer.valueOf(this.mLedColor & 16777215)});
        if (format.equals("#ffffff")) {
            this.mCustomLight.setSummary((int) C1715R.string.default_string);
        } else {
            this.mCustomLight.setSummary((CharSequence) format);
        }
    }
}
