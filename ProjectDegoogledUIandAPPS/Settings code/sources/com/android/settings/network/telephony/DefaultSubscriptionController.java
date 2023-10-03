package com.android.settings.network.telephony;

import android.content.Context;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.network.SubscriptionUtil;
import com.android.settings.network.SubscriptionsChangeListener;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public abstract class DefaultSubscriptionController extends BasePreferenceController implements LifecycleObserver, Preference.OnPreferenceChangeListener, SubscriptionsChangeListener.SubscriptionsChangeListenerClient {
    private static final String TAG = "DefaultSubController";
    protected SubscriptionsChangeListener mChangeListener;
    protected SubscriptionManager mManager;
    protected ListPreference mPreference;

    /* access modifiers changed from: protected */
    public abstract int getDefaultSubscriptionId();

    /* access modifiers changed from: protected */
    public abstract SubscriptionInfo getDefaultSubscriptionInfo();

    public void onAirplaneModeChanged(boolean z) {
    }

    /* access modifiers changed from: protected */
    public abstract void setDefaultSubscription(int i);

    public DefaultSubscriptionController(Context context, String str) {
        super(context, str);
        this.mManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        this.mChangeListener = new SubscriptionsChangeListener(context, this);
    }

    public void init(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    public int getAvailabilityStatus() {
        return SubscriptionUtil.getActiveSubscriptions(this.mManager).size() > 1 ? 0 : 2;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        this.mChangeListener.start();
        updateEntries();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        this.mChangeListener.stop();
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (ListPreference) preferenceScreen.findPreference(getPreferenceKey());
        updateEntries();
    }

    public CharSequence getSummary() {
        SubscriptionInfo defaultSubscriptionInfo = getDefaultSubscriptionInfo();
        if (defaultSubscriptionInfo != null) {
            return defaultSubscriptionInfo.getDisplayName();
        }
        return this.mContext.getString(C1715R.string.calls_and_sms_ask_every_time);
    }

    private void updateEntries() {
        if (this.mPreference != null) {
            if (!isAvailable()) {
                this.mPreference.setVisible(false);
                return;
            }
            this.mPreference.setVisible(true);
            this.mPreference.setOnPreferenceChangeListener(this);
            List<SubscriptionInfo> activeSubscriptions = SubscriptionUtil.getActiveSubscriptions(this.mManager);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int defaultSubscriptionId = getDefaultSubscriptionId();
            boolean z = false;
            for (SubscriptionInfo next : activeSubscriptions) {
                if (!next.isOpportunistic()) {
                    arrayList.add(next.getDisplayName());
                    int subscriptionId = next.getSubscriptionId();
                    arrayList2.add(Integer.toString(subscriptionId));
                    if (subscriptionId == defaultSubscriptionId) {
                        z = true;
                    }
                }
            }
            arrayList.add(this.mContext.getString(C1715R.string.calls_and_sms_ask_every_time));
            arrayList2.add(Integer.toString(-1));
            this.mPreference.setEntries((CharSequence[]) arrayList.toArray(new CharSequence[0]));
            this.mPreference.setEntryValues((CharSequence[]) arrayList2.toArray(new CharSequence[0]));
            if (z) {
                this.mPreference.setValue(Integer.toString(defaultSubscriptionId));
            } else {
                this.mPreference.setValue(Integer.toString(-1));
            }
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        setDefaultSubscription(Integer.parseInt((String) obj));
        refreshSummary(this.mPreference);
        return true;
    }

    public void onSubscriptionsChanged() {
        if (this.mPreference != null) {
            updateEntries();
            refreshSummary(this.mPreference);
        }
    }
}
