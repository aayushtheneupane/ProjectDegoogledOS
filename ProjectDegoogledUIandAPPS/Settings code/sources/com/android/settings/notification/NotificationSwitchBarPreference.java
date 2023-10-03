package com.android.settings.notification;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.widget.ToggleSwitch;
import com.android.settingslib.widget.LayoutPreference;

public class NotificationSwitchBarPreference extends LayoutPreference {
    /* access modifiers changed from: private */
    public boolean mChecked;
    private boolean mEnableSwitch = true;
    /* access modifiers changed from: private */
    public ToggleSwitch mSwitch;

    public NotificationSwitchBarPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        this.mSwitch = (ToggleSwitch) preferenceViewHolder.findViewById(16908352);
        ToggleSwitch toggleSwitch = this.mSwitch;
        if (toggleSwitch != null) {
            toggleSwitch.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (NotificationSwitchBarPreference.this.mSwitch.isEnabled()) {
                        NotificationSwitchBarPreference notificationSwitchBarPreference = NotificationSwitchBarPreference.this;
                        boolean unused = notificationSwitchBarPreference.mChecked = !notificationSwitchBarPreference.mChecked;
                        NotificationSwitchBarPreference notificationSwitchBarPreference2 = NotificationSwitchBarPreference.this;
                        notificationSwitchBarPreference2.setChecked(notificationSwitchBarPreference2.mChecked);
                        NotificationSwitchBarPreference notificationSwitchBarPreference3 = NotificationSwitchBarPreference.this;
                        if (!notificationSwitchBarPreference3.callChangeListener(Boolean.valueOf(notificationSwitchBarPreference3.mChecked))) {
                            NotificationSwitchBarPreference notificationSwitchBarPreference4 = NotificationSwitchBarPreference.this;
                            notificationSwitchBarPreference4.setChecked(!notificationSwitchBarPreference4.mChecked);
                        }
                    }
                }
            });
            this.mSwitch.setChecked(this.mChecked);
            this.mSwitch.setEnabled(this.mEnableSwitch);
        }
    }

    public void setChecked(boolean z) {
        this.mChecked = z;
        ToggleSwitch toggleSwitch = this.mSwitch;
        if (toggleSwitch != null) {
            toggleSwitch.setChecked(z);
        }
    }
}
