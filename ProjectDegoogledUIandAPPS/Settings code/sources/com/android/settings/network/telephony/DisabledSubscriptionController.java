package com.android.settings.network.telephony;

import android.content.Context;
import android.telephony.SubscriptionManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.network.SubscriptionsChangeListener;

public class DisabledSubscriptionController extends BasePreferenceController implements SubscriptionsChangeListener.SubscriptionsChangeListenerClient, LifecycleObserver {
    private PreferenceCategory mCategory;
    private SubscriptionsChangeListener mChangeListener;
    private int mSubId = -1;
    private SubscriptionManager mSubscriptionManager = ((SubscriptionManager) this.mContext.getSystemService(SubscriptionManager.class));

    public int getAvailabilityStatus() {
        return 1;
    }

    public void onAirplaneModeChanged(boolean z) {
    }

    public DisabledSubscriptionController(Context context, String str) {
        super(context, str);
        this.mChangeListener = new SubscriptionsChangeListener(context, this);
    }

    public void init(Lifecycle lifecycle, int i) {
        lifecycle.addObserver(this);
        this.mSubId = i;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        this.mChangeListener.start();
        update();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        this.mChangeListener.stop();
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mCategory = (PreferenceCategory) preferenceScreen.findPreference(getPreferenceKey());
        update();
    }

    private void update() {
        int i;
        PreferenceCategory preferenceCategory = this.mCategory;
        if (preferenceCategory != null && (i = this.mSubId) != -1) {
            preferenceCategory.setVisible(this.mSubscriptionManager.isSubscriptionEnabled(i));
        }
    }

    public void onSubscriptionsChanged() {
        update();
    }
}
