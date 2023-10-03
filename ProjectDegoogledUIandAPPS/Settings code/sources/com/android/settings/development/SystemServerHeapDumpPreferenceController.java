package com.android.settings.development;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.os.UserManager;
import android.util.Log;
import android.widget.Toast;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import com.havoc.config.center.C1715R;

public class SystemServerHeapDumpPreferenceController extends DeveloperOptionsPreferenceController implements PreferenceControllerMixin {
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private final UserManager mUserManager;

    public String getPreferenceKey() {
        return "system_server_heap_dump";
    }

    public SystemServerHeapDumpPreferenceController(Context context) {
        super(context);
        this.mUserManager = (UserManager) context.getSystemService(UserManager.class);
    }

    public boolean isAvailable() {
        return Build.IS_ENG && !this.mUserManager.hasUserRestriction("no_debugging_features");
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!"system_server_heap_dump".equals(preference.getKey())) {
            return false;
        }
        try {
            preference.setEnabled(false);
            Toast.makeText(this.mContext, C1715R.string.capturing_system_heap_dump_message, 0).show();
            ActivityManager.getService().requestSystemServerHeapDump();
            this.mHandler.postDelayed(new Runnable() {
                public final void run() {
                    Preference.this.setEnabled(true);
                }
            }, 5000);
            return true;
        } catch (RemoteException e) {
            Log.e("PrefControllerMixin", "error taking system heap dump", e);
            Toast.makeText(this.mContext, C1715R.string.error_capturing_system_heap_dump_message, 0).show();
            return false;
        }
    }
}
