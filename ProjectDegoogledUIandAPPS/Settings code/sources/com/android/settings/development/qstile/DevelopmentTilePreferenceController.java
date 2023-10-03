package com.android.settings.development.qstile;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.ServiceManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.internal.statusbar.IStatusBarService;
import com.android.settings.core.BasePreferenceController;
import com.havoc.support.preferences.SwitchPreference;

public class DevelopmentTilePreferenceController extends BasePreferenceController {
    private static final String TAG = "DevTilePrefController";
    private final OnChangeHandler mOnChangeHandler;
    private final PackageManager mPackageManager;

    public int getAvailabilityStatus() {
        return 0;
    }

    public DevelopmentTilePreferenceController(Context context, String str) {
        super(context, str);
        this.mOnChangeHandler = new OnChangeHandler(context);
        this.mPackageManager = context.getPackageManager();
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Context context = preferenceScreen.getContext();
        for (ResolveInfo resolveInfo : this.mPackageManager.queryIntentServices(new Intent("android.service.quicksettings.action.QS_TILE").setPackage(context.getPackageName()), 512)) {
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            int componentEnabledSetting = this.mPackageManager.getComponentEnabledSetting(new ComponentName(serviceInfo.packageName, serviceInfo.name));
            boolean z = true;
            if (componentEnabledSetting != 1 && (componentEnabledSetting != 0 || !serviceInfo.enabled)) {
                z = false;
            }
            SwitchPreference switchPreference = new SwitchPreference(context);
            switchPreference.setTitle(serviceInfo.loadLabel(this.mPackageManager));
            switchPreference.setIcon(serviceInfo.icon);
            switchPreference.setKey(serviceInfo.name);
            switchPreference.setChecked(z);
            switchPreference.setOnPreferenceChangeListener(this.mOnChangeHandler);
            preferenceScreen.addPreference(switchPreference);
        }
    }

    static class OnChangeHandler implements Preference.OnPreferenceChangeListener {
        private final Context mContext;
        private final PackageManager mPackageManager;
        private IStatusBarService mStatusBarService = IStatusBarService.Stub.asInterface(ServiceManager.checkService("statusbar"));

        public OnChangeHandler(Context context) {
            this.mContext = context;
            this.mPackageManager = context.getPackageManager();
        }

        public boolean onPreferenceChange(Preference preference, Object obj) {
            this.mPackageManager.setComponentEnabledSetting(new ComponentName(this.mContext.getPackageName(), preference.getKey()), ((Boolean) obj).booleanValue() ? 1 : 2, 1);
            return true;
        }
    }
}
