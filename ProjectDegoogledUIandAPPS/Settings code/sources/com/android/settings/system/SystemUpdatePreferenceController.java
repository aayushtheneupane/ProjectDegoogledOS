package com.android.settings.system;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemUpdateManager;
import android.os.UserManager;
import android.telephony.CarrierConfigManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.Utils;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class SystemUpdatePreferenceController extends BasePreferenceController {
    private static final String KEY_SYSTEM_UPDATE_SETTINGS = "system_update_settings";
    private static final String TAG = "SysUpdatePrefContr";
    private final UserManager mUm;
    private final SystemUpdateManager mUpdateManager;

    public SystemUpdatePreferenceController(Context context) {
        super(context, KEY_SYSTEM_UPDATE_SETTINGS);
        this.mUm = UserManager.get(context);
        this.mUpdateManager = (SystemUpdateManager) context.getSystemService("system_update");
    }

    public int getAvailabilityStatus() {
        return (!this.mContext.getResources().getBoolean(C1715R.bool.config_show_system_update_settings) || !this.mUm.isAdminUser()) ? 3 : 0;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (isAvailable()) {
            Utils.updatePreferenceToSpecificActivityOrRemove(this.mContext, preferenceScreen, getPreferenceKey(), 1);
        }
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        PersistableBundle config;
        if (!TextUtils.equals(getPreferenceKey(), preference.getKey()) || (config = ((CarrierConfigManager) this.mContext.getSystemService("carrier_config")).getConfig()) == null || !config.getBoolean("ci_action_on_sys_update_bool")) {
            return false;
        }
        ciActionOnSysUpdate(config);
        return false;
    }

    public CharSequence getSummary() {
        String string = this.mContext.getString(C1715R.string.android_version_summary, new Object[]{Build.VERSION.RELEASE});
        FutureTask futureTask = new FutureTask(new Callable() {
            public final Object call() {
                return SystemUpdatePreferenceController.this.lambda$getSummary$0$SystemUpdatePreferenceController();
            }
        });
        try {
            futureTask.run();
            Bundle bundle = (Bundle) futureTask.get();
            int i = bundle.getInt("status");
            if (i == 0) {
                Log.d(TAG, "Update statue unknown");
            } else if (i != 1) {
                if (i == 2 || i == 3 || i == 4 || i == 5) {
                    return this.mContext.getText(C1715R.string.android_version_pending_update_summary);
                }
                return string;
            }
            String string2 = bundle.getString("title");
            if (TextUtils.isEmpty(string2)) {
                return string;
            }
            return this.mContext.getString(C1715R.string.android_version_summary, new Object[]{string2});
        } catch (InterruptedException | ExecutionException unused) {
            Log.w(TAG, "Error getting system update info.");
            return string;
        }
    }

    public /* synthetic */ Bundle lambda$getSummary$0$SystemUpdatePreferenceController() throws Exception {
        return this.mUpdateManager.retrieveSystemUpdateInfo();
    }

    private void ciActionOnSysUpdate(PersistableBundle persistableBundle) {
        String string = persistableBundle.getString("ci_action_on_sys_update_intent_string");
        if (!TextUtils.isEmpty(string)) {
            String string2 = persistableBundle.getString("ci_action_on_sys_update_extra_string");
            String string3 = persistableBundle.getString("ci_action_on_sys_update_extra_val_string");
            Intent intent = new Intent(string);
            if (!TextUtils.isEmpty(string2)) {
                intent.putExtra(string2, string3);
            }
            Log.d(TAG, "ciActionOnSysUpdate: broadcasting intent " + string + " with extra " + string2 + ", " + string3);
            intent.addFlags(16777216);
            this.mContext.getApplicationContext().sendBroadcast(intent);
        }
    }
}
