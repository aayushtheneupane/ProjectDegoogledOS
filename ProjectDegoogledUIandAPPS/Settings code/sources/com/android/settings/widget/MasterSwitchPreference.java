package com.android.settings.widget;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Switch;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.TwoTargetPreference;
import com.havoc.config.center.C1715R;

public class MasterSwitchPreference extends TwoTargetPreference {
    /* access modifiers changed from: private */
    public boolean mChecked;
    private final Context mContext;
    private boolean mEnableSwitch;
    /* access modifiers changed from: private */
    public Switch mSwitch;
    private final Vibrator mVibrator;

    /* access modifiers changed from: protected */
    public int getSecondTargetResId() {
        return C1715R.layout.preference_widget_master_switch;
    }

    public MasterSwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mEnableSwitch = true;
        this.mContext = context;
        this.mVibrator = (Vibrator) context.getSystemService("vibrator");
    }

    public MasterSwitchPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public MasterSwitchPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.getAttr(context, C1715R.attr.switchPreferenceStyle, 16843629));
    }

    public MasterSwitchPreference(Context context) {
        this(context, (AttributeSet) null);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(16908312);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (MasterSwitchPreference.this.mSwitch == null || MasterSwitchPreference.this.mSwitch.isEnabled()) {
                        MasterSwitchPreference masterSwitchPreference = MasterSwitchPreference.this;
                        masterSwitchPreference.setChecked(!masterSwitchPreference.mChecked);
                        MasterSwitchPreference masterSwitchPreference2 = MasterSwitchPreference.this;
                        if (!masterSwitchPreference2.callChangeListener(Boolean.valueOf(masterSwitchPreference2.mChecked))) {
                            MasterSwitchPreference masterSwitchPreference3 = MasterSwitchPreference.this;
                            masterSwitchPreference3.setChecked(!masterSwitchPreference3.mChecked);
                        } else {
                            MasterSwitchPreference masterSwitchPreference4 = MasterSwitchPreference.this;
                            boolean unused = masterSwitchPreference4.persistBoolean(masterSwitchPreference4.mChecked);
                        }
                        MasterSwitchPreference.this.doHapticFeedback();
                    }
                }
            });
        }
        this.mSwitch = (Switch) preferenceViewHolder.findViewById(C1715R.C1718id.switchWidget);
        Switch switchR = this.mSwitch;
        if (switchR != null) {
            switchR.setContentDescription(getTitle());
            this.mSwitch.setChecked(this.mChecked);
            this.mSwitch.setEnabled(this.mEnableSwitch);
        }
    }

    public boolean isChecked() {
        return this.mSwitch != null && this.mChecked;
    }

    public void setChecked(boolean z) {
        this.mChecked = z;
        Switch switchR = this.mSwitch;
        if (switchR != null) {
            switchR.setChecked(z);
        }
    }

    public void setSwitchEnabled(boolean z) {
        this.mEnableSwitch = z;
        Switch switchR = this.mSwitch;
        if (switchR != null) {
            switchR.setEnabled(z);
        }
    }

    public void setDisabledByAdmin(RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        setSwitchEnabled(enforcedAdmin == null);
    }

    /* access modifiers changed from: private */
    public void doHapticFeedback() {
        boolean z = true;
        if (Settings.System.getInt(this.mContext.getContentResolver(), "haptic_feedback_enabled", 1) == 0) {
            z = false;
        }
        if (z) {
            this.mVibrator.vibrate(VibrationEffect.get(0));
        }
    }
}
