package com.android.settings.display;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.hardware.display.DcDimmingManager;
import android.os.Handler;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Switch;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.TwoTargetPreference;
import com.havoc.config.center.C1715R;

public class DcDimmingPreference extends TwoTargetPreference {
    /* access modifiers changed from: private */
    public boolean mChecked;
    /* access modifiers changed from: private */
    public final DcDimmingManager mDcDimmingManager;
    /* access modifiers changed from: private */
    public Switch mSwitch;

    /* access modifiers changed from: protected */
    public int getSecondTargetResId() {
        return C1715R.layout.preference_widget_master_switch;
    }

    public DcDimmingPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDcDimmingManager = (DcDimmingManager) context.getSystemService("dc_dim_service");
        DcDimmingManager dcDimmingManager = this.mDcDimmingManager;
        if (dcDimmingManager != null && dcDimmingManager.isAvailable()) {
            new SettingsObserver(new Handler()).observe();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToHierarchy(PreferenceManager preferenceManager) {
        super.onAttachedToHierarchy(preferenceManager);
        DcDimmingManager dcDimmingManager = this.mDcDimmingManager;
        if (dcDimmingManager == null || !dcDimmingManager.isAvailable()) {
            setVisible(false);
            return;
        }
        boolean isDcDimmingOn = this.mDcDimmingManager.isDcDimmingOn();
        setChecked(isDcDimmingOn);
        updateSummary(isDcDimmingOn);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(16908312);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (DcDimmingPreference.this.mSwitch == null || DcDimmingPreference.this.mSwitch.isEnabled()) {
                        DcDimmingPreference dcDimmingPreference = DcDimmingPreference.this;
                        dcDimmingPreference.setUserChecked(!dcDimmingPreference.mChecked);
                        DcDimmingPreference dcDimmingPreference2 = DcDimmingPreference.this;
                        if (!dcDimmingPreference2.callChangeListener(Boolean.valueOf(dcDimmingPreference2.mChecked))) {
                            DcDimmingPreference dcDimmingPreference3 = DcDimmingPreference.this;
                            dcDimmingPreference3.setUserChecked(!dcDimmingPreference3.mChecked);
                            return;
                        }
                        DcDimmingPreference dcDimmingPreference4 = DcDimmingPreference.this;
                        boolean unused = dcDimmingPreference4.persistBoolean(dcDimmingPreference4.mChecked);
                    }
                }
            });
        }
        this.mSwitch = (Switch) preferenceViewHolder.findViewById(C1715R.C1718id.switchWidget);
        Switch switchR = this.mSwitch;
        if (switchR != null) {
            switchR.setContentDescription(getTitle());
            this.mSwitch.setChecked(this.mChecked);
        }
    }

    public void setUserChecked(boolean z) {
        setChecked(z);
        this.mDcDimmingManager.setDcDimming(z);
        updateSummary(this.mDcDimmingManager.isDcDimmingOn());
    }

    /* access modifiers changed from: private */
    public void updateSummary(boolean z) {
        String str;
        boolean z2 = this.mDcDimmingManager.getAutoMode() != 0;
        if (z) {
            str = getContext().getString(z2 ? C1715R.string.dc_dimming_on_auto_mode_auto : C1715R.string.dark_ui_summary_on_auto_mode_never);
        } else {
            str = getContext().getString(z2 ? C1715R.string.dc_dimming_off_auto_mode_auto : C1715R.string.dark_ui_summary_off_auto_mode_never);
        }
        setSummary((CharSequence) getContext().getString(z ? C1715R.string.dark_ui_summary_on : C1715R.string.dark_ui_summary_off, new Object[]{str}));
    }

    public void setChecked(boolean z) {
        this.mChecked = z;
        Switch switchR = this.mSwitch;
        if (switchR != null) {
            switchR.setChecked(z);
        }
    }

    private class SettingsObserver extends ContentObserver {
        SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            ContentResolver contentResolver = DcDimmingPreference.this.getContext().getContentResolver();
            contentResolver.registerContentObserver(Settings.System.getUriFor("dc_dimming_auto_mode"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("dc_dimming_state"), false, this, -1);
        }

        public void onChange(boolean z) {
            DcDimmingPreference dcDimmingPreference = DcDimmingPreference.this;
            dcDimmingPreference.updateSummary(dcDimmingPreference.mDcDimmingManager.isDcDimmingOn());
            DcDimmingPreference dcDimmingPreference2 = DcDimmingPreference.this;
            dcDimmingPreference2.setChecked(dcDimmingPreference2.mDcDimmingManager.isDcDimmingOn());
        }
    }
}
