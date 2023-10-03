package com.android.settings.gestures;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Switch;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.widget.SwitchBar;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;

public class PreventRingingSwitchPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, SwitchBar.OnSwitchChangeListener {
    private final Context mContext;
    private SettingObserver mSettingObserver;
    SwitchBar mSwitch;

    public String getPreferenceKey() {
        return "gesture_prevent_ringing_switch";
    }

    public PreventRingingSwitchPreferenceController(Context context) {
        super(context);
        this.mContext = context;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        LayoutPreference layoutPreference;
        super.displayPreference(preferenceScreen);
        if (isAvailable() && (layoutPreference = (LayoutPreference) preferenceScreen.findPreference(getPreferenceKey())) != null) {
            this.mSettingObserver = new SettingObserver(layoutPreference);
            layoutPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public final boolean onPreferenceClick(Preference preference) {
                    return PreventRingingSwitchPreferenceController.this.mo10136x376a281d(preference);
                }
            });
            this.mSwitch = (SwitchBar) layoutPreference.findViewById(C1715R.C1718id.switch_bar);
            SwitchBar switchBar = this.mSwitch;
            if (switchBar != null) {
                switchBar.addOnSwitchChangeListener(this);
                this.mSwitch.show();
            }
        }
    }

    /* renamed from: lambda$displayPreference$0$PreventRingingSwitchPreferenceController */
    public /* synthetic */ boolean mo10136x376a281d(Preference preference) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "volume_hush_gesture", (Settings.Secure.getInt(this.mContext.getContentResolver(), "volume_hush_gesture", 1) != 0 ? 1 : 0) ^ 1);
        return true;
    }

    public void setChecked(boolean z) {
        SwitchBar switchBar = this.mSwitch;
        if (switchBar != null) {
            switchBar.setChecked(z);
        }
    }

    public void updateState(Preference preference) {
        boolean z = true;
        if (Settings.Secure.getInt(this.mContext.getContentResolver(), "volume_hush_gesture", 1) == 0) {
            z = false;
        }
        setChecked(z);
    }

    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(17891608);
    }

    public void onSwitchChanged(Switch switchR, boolean z) {
        int i = Settings.Secure.getInt(this.mContext.getContentResolver(), "volume_hush_gesture", 1);
        if (i == 0) {
            i = 1;
        }
        ContentResolver contentResolver = this.mContext.getContentResolver();
        if (!z) {
            i = 0;
        }
        Settings.Secure.putInt(contentResolver, "volume_hush_gesture", i);
    }

    private class SettingObserver extends ContentObserver {
        private final Uri VOLUME_HUSH_GESTURE = Settings.Secure.getUriFor("volume_hush_gesture");
        private final Preference mPreference;

        public SettingObserver(Preference preference) {
            super(new Handler());
            this.mPreference = preference;
        }

        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            if (uri == null || this.VOLUME_HUSH_GESTURE.equals(uri)) {
                PreventRingingSwitchPreferenceController.this.updateState(this.mPreference);
            }
        }
    }
}
