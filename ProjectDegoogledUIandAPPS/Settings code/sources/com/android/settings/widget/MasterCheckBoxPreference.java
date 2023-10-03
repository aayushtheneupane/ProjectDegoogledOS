package com.android.settings.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.TwoTargetPreference;
import com.havoc.config.center.C1715R;

public class MasterCheckBoxPreference extends TwoTargetPreference {
    /* access modifiers changed from: private */
    public CheckBox mCheckBox;
    /* access modifiers changed from: private */
    public boolean mChecked;
    private boolean mEnableCheckBox = true;

    /* access modifiers changed from: protected */
    public int getSecondTargetResId() {
        return C1715R.layout.preference_widget_master_checkbox;
    }

    public MasterCheckBoxPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public MasterCheckBoxPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public MasterCheckBoxPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(16908312);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (MasterCheckBoxPreference.this.mCheckBox == null || MasterCheckBoxPreference.this.mCheckBox.isEnabled()) {
                        MasterCheckBoxPreference masterCheckBoxPreference = MasterCheckBoxPreference.this;
                        masterCheckBoxPreference.setChecked(!masterCheckBoxPreference.mChecked);
                        MasterCheckBoxPreference masterCheckBoxPreference2 = MasterCheckBoxPreference.this;
                        if (!masterCheckBoxPreference2.callChangeListener(Boolean.valueOf(masterCheckBoxPreference2.mChecked))) {
                            MasterCheckBoxPreference masterCheckBoxPreference3 = MasterCheckBoxPreference.this;
                            masterCheckBoxPreference3.setChecked(!masterCheckBoxPreference3.mChecked);
                            return;
                        }
                        MasterCheckBoxPreference masterCheckBoxPreference4 = MasterCheckBoxPreference.this;
                        boolean unused = masterCheckBoxPreference4.persistBoolean(masterCheckBoxPreference4.mChecked);
                    }
                }
            });
        }
        this.mCheckBox = (CheckBox) preferenceViewHolder.findViewById(C1715R.C1718id.checkboxWidget);
        CheckBox checkBox = this.mCheckBox;
        if (checkBox != null) {
            checkBox.setContentDescription(getTitle());
            this.mCheckBox.setChecked(this.mChecked);
            this.mCheckBox.setEnabled(this.mEnableCheckBox);
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setCheckBoxEnabled(z);
    }

    public void setChecked(boolean z) {
        this.mChecked = z;
        CheckBox checkBox = this.mCheckBox;
        if (checkBox != null) {
            checkBox.setChecked(z);
        }
    }

    public void setCheckBoxEnabled(boolean z) {
        this.mEnableCheckBox = z;
        CheckBox checkBox = this.mCheckBox;
        if (checkBox != null) {
            checkBox.setEnabled(z);
        }
    }
}
