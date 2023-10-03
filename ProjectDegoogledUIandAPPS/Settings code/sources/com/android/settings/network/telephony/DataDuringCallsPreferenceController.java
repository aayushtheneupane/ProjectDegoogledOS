package com.android.settings.network.telephony;

import android.content.Context;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.network.SubscriptionsChangeListener;
import com.havoc.support.preferences.SwitchPreference;

public class DataDuringCallsPreferenceController extends TelephonyTogglePreferenceController implements LifecycleObserver, SubscriptionsChangeListener.SubscriptionsChangeListenerClient {
    private SubscriptionsChangeListener mChangeListener = new SubscriptionsChangeListener(this.mContext, this);
    private TelephonyManager mManager;
    private SwitchPreference mPreference;

    public void onAirplaneModeChanged(boolean z) {
    }

    public DataDuringCallsPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void init(Lifecycle lifecycle, int i) {
        this.mSubId = i;
        this.mManager = ((TelephonyManager) this.mContext.getSystemService(TelephonyManager.class)).createForSubscriptionId(i);
        lifecycle.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        this.mChangeListener.start();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        this.mChangeListener.stop();
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (SwitchPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    public boolean isChecked() {
        return this.mManager.isDataAllowedInVoiceCall();
    }

    public boolean setChecked(boolean z) {
        this.mManager.setDataAllowedDuringVoiceCall(z);
        return true;
    }

    public int getAvailabilityStatus(int i) {
        return (this.mSubId == -1 || SubscriptionManager.getDefaultDataSubscriptionId() == this.mSubId) ? 2 : 0;
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        preference.setVisible(isAvailable());
    }

    public void onSubscriptionsChanged() {
        updateState(this.mPreference);
    }
}
