package com.havoc.support.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Switch;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.R$attr;
import com.havoc.support.R$id;
import com.havoc.support.R$layout;
import com.havoc.support.util.VibrationUtils;

public class MasterSwitchPreference extends TwoTargetPreference {
    /* access modifiers changed from: private */
    public boolean mChecked;
    /* access modifiers changed from: private */
    public final Context mContext;
    private boolean mEnableSwitch;
    /* access modifiers changed from: private */
    public Switch mSwitch;

    public MasterSwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mEnableSwitch = true;
        this.mContext = context;
    }

    public MasterSwitchPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public MasterSwitchPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.getAttr(context, R$attr.switchPreferenceStyle, 16843629));
    }

    public MasterSwitchPreference(Context context) {
        this(context, (AttributeSet) null);
    }

    /* access modifiers changed from: protected */
    public int getSecondTargetResId() {
        return R$layout.preference_widget_master_switch;
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
                            masterSwitchPreference4.persistBoolean(masterSwitchPreference4.mChecked);
                        }
                        VibrationUtils.doHapticFeedback(MasterSwitchPreference.this.mContext, 0);
                    }
                }
            });
        }
        this.mSwitch = (Switch) preferenceViewHolder.findViewById(R$id.switchWidget);
        Switch switchR = this.mSwitch;
        if (switchR != null) {
            switchR.setContentDescription(getTitle());
            this.mSwitch.setChecked(this.mChecked);
            this.mSwitch.setEnabled(this.mEnableSwitch);
        }
    }

    public void setChecked(boolean z) {
        this.mChecked = z;
        Switch switchR = this.mSwitch;
        if (switchR != null) {
            switchR.setChecked(z);
        }
    }

    /* access modifiers changed from: protected */
    public Object onGetDefaultValue(TypedArray typedArray, int i) {
        return Boolean.valueOf(typedArray.getBoolean(i, false));
    }

    /* access modifiers changed from: protected */
    public boolean persistBoolean(boolean z) {
        if (!shouldPersist()) {
            return false;
        }
        if (z == getPersistedBoolean(!z)) {
            return true;
        }
        Settings.System.putInt(getContext().getContentResolver(), getKey(), z ? 1 : 0);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean getPersistedBoolean(boolean z) {
        if (!shouldPersist()) {
            return z;
        }
        return Settings.System.getInt(getContext().getContentResolver(), getKey(), z ? 1 : 0) != 0;
    }
}
