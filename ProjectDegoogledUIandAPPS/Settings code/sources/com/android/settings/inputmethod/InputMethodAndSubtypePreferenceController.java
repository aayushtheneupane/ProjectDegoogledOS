package com.android.settings.inputmethod;

import android.content.Context;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.inputmethod.InputMethodAndSubtypeEnablerManagerCompat;

public class InputMethodAndSubtypePreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private PreferenceFragmentCompat mFragment;
    private InputMethodAndSubtypeEnablerManagerCompat mManager;
    private String mTargetImi;

    public int getAvailabilityStatus() {
        return 0;
    }

    public InputMethodAndSubtypePreferenceController(Context context, String str) {
        super(context, str);
    }

    public void initialize(PreferenceFragmentCompat preferenceFragmentCompat, String str) {
        this.mFragment = preferenceFragmentCompat;
        this.mTargetImi = str;
        this.mManager = new InputMethodAndSubtypeEnablerManagerCompat(this.mFragment);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mManager.init(this.mFragment, this.mTargetImi, preferenceScreen);
    }

    public void onStart() {
        this.mManager.refresh(this.mContext, this.mFragment);
    }

    public void onStop() {
        this.mManager.save(this.mContext, this.mFragment);
    }
}
