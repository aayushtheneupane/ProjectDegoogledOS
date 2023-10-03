package com.android.settings.display;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.hardware.display.DcDimmingManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.display.BrightnessUtils;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class DcDimmingSettings extends DashboardFragment implements Preference.OnPreferenceChangeListener, SeekBar.OnSeekBarChangeListener {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.dc_dimming_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }

        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            DcDimmingManager dcDimmingManager = (DcDimmingManager) context.getSystemService("dc_dim_service");
            return dcDimmingManager != null && dcDimmingManager.isAvailable();
        }

        public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
            return new ArrayList(1);
        }
    };
    private TextView mBrightnessSumm;
    private TextView mBrightnessTitle;
    private TextView mBrightnessValue;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public DcDimmingManager mDcDimmingManager;
    private DropDownPreference mDropPreference;
    private final View.OnClickListener mForceListener = new View.OnClickListener() {
        public void onClick(View view) {
            DcDimmingSettings.this.mDcDimmingManager.isDcDimmingOn();
            DcDimmingSettings.this.mDcDimmingManager.setDcDimming(!DcDimmingSettings.this.mDcDimmingManager.isDcDimmingOn());
        }
    };
    private int mMaximumBacklight;
    private int mMinimumBacklight;
    private LayoutPreference mRestoreAuto;
    private final View.OnClickListener mRestoreListener = new View.OnClickListener() {
        public void onClick(View view) {
            DcDimmingSettings.this.mDcDimmingManager.restoreAutoMode();
            DcDimmingSettings.this.updateStateInternal();
        }
    };
    private SeekBar mSeekBar;
    private Button mTurnOffButton;
    private Button mTurnOnButton;

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "DcDimmingSettings";
    }

    public int getMetricsCategory() {
        return -1;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.dc_dimming_settings;
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getContext();
        this.mDcDimmingManager = (DcDimmingManager) getSystemService("dc_dim_service");
        DcDimmingManager dcDimmingManager = this.mDcDimmingManager;
        if (dcDimmingManager != null && dcDimmingManager.isAvailable()) {
            PowerManager powerManager = (PowerManager) getSystemService("power");
            this.mMinimumBacklight = powerManager.getMinimumScreenBrightnessSetting();
            this.mMaximumBacklight = powerManager.getMaximumScreenBrightnessSetting();
            new SettingsObserver(new Handler()).observe();
            LayoutPreference layoutPreference = (LayoutPreference) findPreference("dc_dimming_brightness");
            this.mSeekBar = (SeekBar) layoutPreference.findViewById(C1715R.C1718id.seekbar);
            this.mBrightnessValue = (TextView) layoutPreference.findViewById(C1715R.C1718id.value);
            this.mBrightnessTitle = (TextView) layoutPreference.findViewById(C1715R.C1718id.title);
            this.mBrightnessSumm = (TextView) layoutPreference.findViewById(C1715R.C1718id.summary);
            this.mSeekBar.setOnSeekBarChangeListener(this);
            this.mSeekBar.setMax(511);
            LayoutPreference layoutPreference2 = (LayoutPreference) findPreference("dc_dimming_button");
            this.mTurnOnButton = (Button) layoutPreference2.findViewById(C1715R.C1718id.dc_dimming_on_button);
            this.mTurnOnButton.setOnClickListener(this.mForceListener);
            this.mTurnOffButton = (Button) layoutPreference2.findViewById(C1715R.C1718id.dc_dimming_off_button);
            this.mTurnOffButton.setOnClickListener(this.mForceListener);
            this.mRestoreAuto = (LayoutPreference) findPreference("dc_dimming_restore_button");
            ((Button) this.mRestoreAuto.findViewById(C1715R.C1718id.dc_dimming_restore_active)).setOnClickListener(this.mRestoreListener);
            this.mDropPreference = (DropDownPreference) findPreference("dc_dimming_auto_mode");
            this.mDropPreference.setEntries(new CharSequence[]{this.mContext.getString(C1715R.string.dark_ui_auto_mode_never), this.mContext.getString(C1715R.string.dark_ui_auto_mode_auto), this.mContext.getString(C1715R.string.dc_dimming_mode_brightness), this.mContext.getString(C1715R.string.dc_dimming_mode_full)});
            this.mDropPreference.setEntryValues(new CharSequence[]{String.valueOf(0), String.valueOf(1), String.valueOf(2), String.valueOf(3)});
            this.mDropPreference.setValue(String.valueOf(this.mDcDimmingManager.getAutoMode()));
            this.mDropPreference.setOnPreferenceChangeListener(this);
            findPreference("footer_preference").setTitle((CharSequence) this.mContext.getResources().getString(C1715R.string.dc_dimming_info));
            this.mSeekBar.setProgress(BrightnessUtils.convertLinearToGamma(this.mDcDimmingManager.getBrightnessThreshold(), this.mMinimumBacklight, this.mMaximumBacklight));
            updateStateInternal();
        }
    }

    private void updateBrightnessValue(int i) {
        int i2 = (int) ((((float) i) * 100.0f) / 1023.0f);
        if (i2 == 0) {
            this.mBrightnessValue.setText(C1715R.string.dc_dimming_brightness_disabled);
            return;
        }
        TextView textView = this.mBrightnessValue;
        textView.setText(String.valueOf(i2) + "%");
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (!(preference instanceof DropDownPreference)) {
            return true;
        }
        this.mDcDimmingManager.setAutoMode(Integer.parseInt((String) obj));
        return true;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            int convertGammaToLinear = BrightnessUtils.convertGammaToLinear(i, this.mMinimumBacklight, this.mMaximumBacklight);
            int min = Math.min((int) ((((float) i) * 100.0f) / 1023.0f), convertGammaToLinear);
            int autoMode = this.mDcDimmingManager.getAutoMode();
            if (min == 0 && autoMode >= 2) {
                int i2 = autoMode == 2 ? 0 : 1;
                this.mDropPreference.setValue(String.valueOf(i2));
                this.mDcDimmingManager.setAutoMode(i2);
                this.mSeekBar.setProgress(255, true);
                this.mDcDimmingManager.setBrightnessThreshold(BrightnessUtils.convertGammaToLinear(255, this.mMinimumBacklight, this.mMaximumBacklight));
            } else if (convertGammaToLinear <= this.mMinimumBacklight) {
                this.mDcDimmingManager.setBrightnessThreshold(min);
            } else {
                this.mDcDimmingManager.setBrightnessThreshold(convertGammaToLinear);
            }
        }
        updateStateInternal();
    }

    /* access modifiers changed from: private */
    public void updateStateInternal() {
        if (this.mTurnOnButton != null && this.mTurnOffButton != null) {
            int autoMode = this.mDcDimmingManager.getAutoMode();
            boolean isDcDimmingOn = this.mDcDimmingManager.isDcDimmingOn();
            boolean isForcing = this.mDcDimmingManager.isForcing();
            boolean z = true;
            String string = this.mContext.getString(autoMode != 1 ? isDcDimmingOn ? C1715R.string.dark_ui_activation_off_manual : C1715R.string.dark_ui_activation_on_manual : isDcDimmingOn ? C1715R.string.dark_ui_activation_off_auto : C1715R.string.dark_ui_activation_on_auto);
            int i = 0;
            if (isDcDimmingOn) {
                this.mTurnOnButton.setVisibility(8);
                this.mTurnOffButton.setVisibility(0);
                this.mTurnOffButton.setText(string);
            } else {
                this.mTurnOnButton.setVisibility(0);
                this.mTurnOffButton.setVisibility(8);
                this.mTurnOnButton.setText(string);
            }
            this.mRestoreAuto.setVisible(isForcing);
            if (autoMode < 2 || isForcing) {
                z = false;
            }
            updateBrightnessState(z);
            if (autoMode >= 2) {
                i = this.mSeekBar.getProgress();
            }
            updateBrightnessValue(i);
        }
    }

    private void updateBrightnessState(boolean z) {
        this.mBrightnessValue.setEnabled(z);
        this.mBrightnessTitle.setEnabled(z);
        this.mBrightnessSumm.setEnabled(z);
        this.mSeekBar.setEnabled(z);
    }

    private class SettingsObserver extends ContentObserver {
        SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            ContentResolver contentResolver = DcDimmingSettings.this.mContext.getContentResolver();
            contentResolver.registerContentObserver(Settings.System.getUriFor("dc_dimming_auto_mode"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("dc_dimming_state"), false, this, -1);
        }

        public void onChange(boolean z) {
            DcDimmingSettings.this.updateStateInternal();
        }
    }
}
