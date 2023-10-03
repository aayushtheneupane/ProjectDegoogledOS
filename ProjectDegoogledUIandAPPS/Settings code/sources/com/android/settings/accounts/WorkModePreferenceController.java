package com.android.settings.accounts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.TwoStatePreference;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.havoc.config.center.C1715R;

public class WorkModePreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener, LifecycleObserver, OnStart, OnStop {
    private static final String TAG = "WorkModeController";
    private IntentFilter mIntentFilter;
    /* access modifiers changed from: private */
    public UserHandle mManagedUser;
    /* access modifiers changed from: private */
    public Preference mPreference;
    final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                Log.v(WorkModePreferenceController.TAG, "Received broadcast: " + action);
                if (!"android.intent.action.MANAGED_PROFILE_AVAILABLE".equals(action) && !"android.intent.action.MANAGED_PROFILE_UNAVAILABLE".equals(action)) {
                    Log.w(WorkModePreferenceController.TAG, "Cannot handle received broadcast: " + intent.getAction());
                } else if (intent.getIntExtra("android.intent.extra.user_handle", -10000) == WorkModePreferenceController.this.mManagedUser.getIdentifier()) {
                    WorkModePreferenceController workModePreferenceController = WorkModePreferenceController.this;
                    workModePreferenceController.updateState(workModePreferenceController.mPreference);
                }
            }
        }
    };
    private UserManager mUserManager;

    public int getSliceType() {
        return 1;
    }

    public WorkModePreferenceController(Context context, String str) {
        super(context, str);
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mIntentFilter = new IntentFilter();
        this.mIntentFilter.addAction("android.intent.action.MANAGED_PROFILE_AVAILABLE");
        this.mIntentFilter.addAction("android.intent.action.MANAGED_PROFILE_UNAVAILABLE");
    }

    public void setManagedUser(UserHandle userHandle) {
        this.mManagedUser = userHandle;
    }

    public void onStart() {
        this.mContext.registerReceiver(this.mReceiver, this.mIntentFilter);
    }

    public void onStop() {
        this.mContext.unregisterReceiver(this.mReceiver);
    }

    public int getAvailabilityStatus() {
        return this.mManagedUser != null ? 0 : 4;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    public CharSequence getSummary() {
        return this.mContext.getText(isChecked() ? C1715R.string.work_mode_on_summary : C1715R.string.work_mode_off_summary);
    }

    private boolean isChecked() {
        UserHandle userHandle;
        UserManager userManager = this.mUserManager;
        if (userManager == null || (userHandle = this.mManagedUser) == null) {
            return false;
        }
        return !userManager.isQuietModeEnabled(userHandle);
    }

    private boolean setChecked(boolean z) {
        UserHandle userHandle;
        UserManager userManager = this.mUserManager;
        if (!(userManager == null || (userHandle = this.mManagedUser) == null)) {
            userManager.requestQuietModeEnabled(!z, userHandle);
        }
        return true;
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        if (preference instanceof TwoStatePreference) {
            ((TwoStatePreference) preference).setChecked(isChecked());
        }
    }

    public final boolean onPreferenceChange(Preference preference, Object obj) {
        return setChecked(((Boolean) obj).booleanValue());
    }
}
