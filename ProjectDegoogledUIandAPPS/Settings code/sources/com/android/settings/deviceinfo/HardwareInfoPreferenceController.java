package com.android.settings.deviceinfo;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class HardwareInfoPreferenceController extends BasePreferenceController {
    private static final String TAG = "DeviceModelPrefCtrl";

    public HardwareInfoPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_device_model) ? 1 : 3;
    }

    public CharSequence getSummary() {
        return this.mContext.getResources().getString(C1715R.string.model_summary, new Object[]{getDeviceModel()});
    }

    public static String getDeviceModel() {
        FutureTask futureTask = new FutureTask(C0746x92b57fc2.INSTANCE);
        futureTask.run();
        try {
            return Build.MODEL + ((String) futureTask.get());
        } catch (ExecutionException unused) {
            Log.e(TAG, "Execution error, so we only show model name");
            return Build.MODEL;
        } catch (InterruptedException unused2) {
            Log.e(TAG, "Interruption error, so we only show model name");
            return Build.MODEL;
        }
    }
}
