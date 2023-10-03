package com.android.settings.display;

import android.content.Context;
import android.hardware.display.ColorDisplayManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.TogglePreferenceController;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;
import java.time.LocalTime;

public class NightDisplayActivationPreferenceController extends TogglePreferenceController {
    /* access modifiers changed from: private */
    public ColorDisplayManager mColorDisplayManager;
    private final View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View view) {
            NightDisplayActivationPreferenceController.this.mColorDisplayManager.setNightDisplayActivated(!NightDisplayActivationPreferenceController.this.mColorDisplayManager.isNightDisplayActivated());
            NightDisplayActivationPreferenceController.this.updateStateInternal();
        }
    };
    private NightDisplayTimeFormatter mTimeFormatter;
    private Button mTurnOffButton;
    private Button mTurnOnButton;

    public NightDisplayActivationPreferenceController(Context context, String str) {
        super(context, str);
        this.mColorDisplayManager = (ColorDisplayManager) context.getSystemService(ColorDisplayManager.class);
        this.mTimeFormatter = new NightDisplayTimeFormatter(context);
    }

    public int getAvailabilityStatus() {
        return ColorDisplayManager.isNightDisplayAvailable(this.mContext) ? 0 : 3;
    }

    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), "night_display_activated");
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        LayoutPreference layoutPreference = (LayoutPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mTurnOnButton = (Button) layoutPreference.findViewById(C1715R.C1718id.night_display_turn_on_button);
        this.mTurnOnButton.setOnClickListener(this.mListener);
        this.mTurnOffButton = (Button) layoutPreference.findViewById(C1715R.C1718id.night_display_turn_off_button);
        this.mTurnOffButton.setOnClickListener(this.mListener);
    }

    public final void updateState(Preference preference) {
        updateStateInternal();
    }

    public boolean isChecked() {
        return this.mColorDisplayManager.isNightDisplayActivated();
    }

    public boolean setChecked(boolean z) {
        return this.mColorDisplayManager.setNightDisplayActivated(z);
    }

    public CharSequence getSummary() {
        return this.mTimeFormatter.getAutoModeSummary(this.mContext, this.mColorDisplayManager);
    }

    /* access modifiers changed from: private */
    public void updateStateInternal() {
        String str;
        LocalTime localTime;
        if (this.mTurnOnButton != null && this.mTurnOffButton != null) {
            boolean isNightDisplayActivated = this.mColorDisplayManager.isNightDisplayActivated();
            int nightDisplayAutoMode = this.mColorDisplayManager.getNightDisplayAutoMode();
            if (nightDisplayAutoMode == 1) {
                Context context = this.mContext;
                int i = isNightDisplayActivated ? C1715R.string.night_display_activation_off_custom : C1715R.string.night_display_activation_on_custom;
                Object[] objArr = new Object[1];
                NightDisplayTimeFormatter nightDisplayTimeFormatter = this.mTimeFormatter;
                if (isNightDisplayActivated) {
                    localTime = this.mColorDisplayManager.getNightDisplayCustomStartTime();
                } else {
                    localTime = this.mColorDisplayManager.getNightDisplayCustomEndTime();
                }
                objArr[0] = nightDisplayTimeFormatter.getFormattedTimeString(localTime);
                str = context.getString(i, objArr);
            } else if (nightDisplayAutoMode == 2) {
                str = this.mContext.getString(isNightDisplayActivated ? C1715R.string.night_display_activation_off_twilight : C1715R.string.night_display_activation_on_twilight);
            } else {
                str = this.mContext.getString(isNightDisplayActivated ? C1715R.string.night_display_activation_off_manual : C1715R.string.night_display_activation_on_manual);
            }
            if (isNightDisplayActivated) {
                this.mTurnOnButton.setVisibility(8);
                this.mTurnOffButton.setVisibility(0);
                this.mTurnOffButton.setText(str);
                return;
            }
            this.mTurnOnButton.setVisibility(0);
            this.mTurnOffButton.setVisibility(8);
            this.mTurnOnButton.setText(str);
        }
    }
}
