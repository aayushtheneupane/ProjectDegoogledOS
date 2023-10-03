package com.android.settings.accessibility;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.text.TextUtils;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;
import java.util.List;

public class RTTSettingPreferenceController extends BasePreferenceController {
    private static final String DIALER_RTT_CONFIGURATION = "dialer_rtt_configuration";
    private final Context mContext;
    private final String mDialerPackage = this.mContext.getString(C1715R.string.config_rtt_setting_package_name);
    private final CharSequence[] mModes = this.mContext.getResources().getTextArray(C1715R.array.rtt_setting_mode);
    private final PackageManager mPackageManager;
    Intent mRTTIntent;
    private final TelecomManager mTelecomManager;

    public RTTSettingPreferenceController(Context context, String str) {
        super(context, str);
        this.mContext = context;
        this.mPackageManager = context.getPackageManager();
        this.mTelecomManager = (TelecomManager) context.getSystemService(TelecomManager.class);
        this.mRTTIntent = new Intent(context.getString(C1715R.string.config_rtt_setting_intent_action));
    }

    public int getAvailabilityStatus() {
        List<ResolveInfo> queryIntentActivities = this.mPackageManager.queryIntentActivities(this.mRTTIntent, 0);
        if (queryIntentActivities == null || queryIntentActivities.isEmpty() || !isDialerSupportRTTSetting()) {
            return 3;
        }
        return 0;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        preferenceScreen.findPreference(getPreferenceKey()).setIntent(this.mRTTIntent);
    }

    public CharSequence getSummary() {
        return this.mModes[Settings.Secure.getInt(this.mContext.getContentResolver(), DIALER_RTT_CONFIGURATION, 1)];
    }

    /* access modifiers changed from: package-private */
    public boolean isDialerSupportRTTSetting() {
        return TextUtils.equals(this.mTelecomManager.getDefaultDialerPackage(), this.mDialerPackage);
    }
}
