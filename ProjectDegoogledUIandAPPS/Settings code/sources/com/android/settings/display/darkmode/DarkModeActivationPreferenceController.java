package com.android.settings.display.darkmode;

import android.app.UiModeManager;
import android.content.Context;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;

public class DarkModeActivationPreferenceController extends BasePreferenceController {
    private final View.OnClickListener mListener = new View.OnClickListener() {
        public void onClick(View view) {
            boolean z = (DarkModeActivationPreferenceController.this.mContext.getResources().getConfiguration().uiMode & 32) != 0;
            DarkModeActivationPreferenceController.this.mUiModeManager.setNightModeActivated(!z);
            DarkModeActivationPreferenceController.this.updateNightMode(!z);
        }
    };
    private PowerManager mPowerManager;
    private Button mTurnOffButton;
    private Button mTurnOnButton;
    /* access modifiers changed from: private */
    public final UiModeManager mUiModeManager;

    public int getAvailabilityStatus() {
        return 0;
    }

    public DarkModeActivationPreferenceController(Context context, String str) {
        super(context, str);
        this.mPowerManager = (PowerManager) context.getSystemService(PowerManager.class);
        this.mUiModeManager = (UiModeManager) context.getSystemService(UiModeManager.class);
    }

    public final void updateState(Preference preference) {
        if (this.mPowerManager.isPowerSaveMode()) {
            this.mTurnOnButton.setVisibility(8);
            this.mTurnOffButton.setVisibility(8);
            return;
        }
        updateNightMode((this.mContext.getResources().getConfiguration().uiMode & 32) != 0);
    }

    /* access modifiers changed from: private */
    public void updateNightMode(boolean z) {
        String str;
        if (this.mUiModeManager.getNightMode() == 0) {
            str = this.mContext.getString(z ? C1715R.string.dark_ui_activation_off_auto : C1715R.string.dark_ui_activation_on_auto);
        } else {
            str = this.mContext.getString(z ? C1715R.string.dark_ui_activation_off_manual : C1715R.string.dark_ui_activation_on_manual);
        }
        if (z) {
            this.mTurnOnButton.setVisibility(8);
            this.mTurnOffButton.setVisibility(0);
            this.mTurnOffButton.setText(str);
            return;
        }
        this.mTurnOnButton.setVisibility(0);
        this.mTurnOffButton.setVisibility(8);
        this.mTurnOnButton.setText(str);
    }

    public CharSequence getSummary() {
        boolean z = (this.mContext.getResources().getConfiguration().uiMode & 32) != 0;
        if (this.mUiModeManager.getNightMode() == 0) {
            return this.mContext.getString(z ? C1715R.string.dark_ui_summary_on_auto_mode_auto : C1715R.string.dark_ui_summary_off_auto_mode_auto);
        }
        return this.mContext.getString(z ? C1715R.string.dark_ui_summary_on_auto_mode_never : C1715R.string.dark_ui_summary_off_auto_mode_never);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        LayoutPreference layoutPreference = (LayoutPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mTurnOnButton = (Button) layoutPreference.findViewById(C1715R.C1718id.dark_ui_turn_on_button);
        this.mTurnOnButton.setOnClickListener(this.mListener);
        this.mTurnOffButton = (Button) layoutPreference.findViewById(C1715R.C1718id.dark_ui_turn_off_button);
        this.mTurnOffButton.setOnClickListener(this.mListener);
    }
}
