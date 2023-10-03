package com.android.settings.network;

import android.content.Context;
import android.content.Intent;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.util.ArrayMap;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.network.SubscriptionsChangeListener;
import com.android.settings.network.telephony.MobileNetworkActivity;
import com.android.settings.network.telephony.MobileNetworkUtils;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.Map;

public class MobileNetworkListController extends AbstractPreferenceController implements LifecycleObserver, SubscriptionsChangeListener.SubscriptionsChangeListenerClient {
    @VisibleForTesting
    static final String KEY_ADD_MORE = "add_more";
    private SubscriptionsChangeListener mChangeListener;
    private PreferenceScreen mPreferenceScreen;
    private Map<Integer, Preference> mPreferences = new ArrayMap();
    private SubscriptionManager mSubscriptionManager;

    public String getPreferenceKey() {
        return null;
    }

    public boolean isAvailable() {
        return true;
    }

    public void onAirplaneModeChanged(boolean z) {
    }

    public MobileNetworkListController(Context context, Lifecycle lifecycle) {
        super(context);
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        this.mChangeListener = new SubscriptionsChangeListener(context, this);
        lifecycle.addObserver(this);
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
        this.mPreferenceScreen = preferenceScreen;
        this.mPreferenceScreen.findPreference(KEY_ADD_MORE).setVisible(MobileNetworkUtils.showEuiccSettings(this.mContext));
        update();
    }

    private void update() {
        if (this.mPreferenceScreen != null) {
            Map<Integer, Preference> map = this.mPreferences;
            this.mPreferences = new ArrayMap();
            for (SubscriptionInfo next : SubscriptionUtil.getAvailableSubscriptions(this.mContext)) {
                int subscriptionId = next.getSubscriptionId();
                Preference remove = map.remove(Integer.valueOf(subscriptionId));
                if (remove == null) {
                    remove = new Preference(this.mPreferenceScreen.getContext());
                    this.mPreferenceScreen.addPreference(remove);
                }
                remove.setTitle(next.getDisplayName());
                if (next.isEmbedded()) {
                    if (this.mSubscriptionManager.isActiveSubscriptionId(subscriptionId)) {
                        remove.setSummary((int) C1715R.string.mobile_network_active_esim);
                    } else {
                        remove.setSummary((int) C1715R.string.mobile_network_inactive_esim);
                    }
                } else if (this.mSubscriptionManager.isActiveSubscriptionId(subscriptionId)) {
                    remove.setSummary((int) C1715R.string.mobile_network_active_sim);
                } else {
                    remove.setSummary((int) C1715R.string.mobile_network_inactive_sim);
                }
                remove.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(next) {
                    private final /* synthetic */ SubscriptionInfo f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final boolean onPreferenceClick(Preference preference) {
                        return MobileNetworkListController.this.lambda$update$0$MobileNetworkListController(this.f$1, preference);
                    }
                });
                this.mPreferences.put(Integer.valueOf(subscriptionId), remove);
            }
            for (Preference removePreference : map.values()) {
                this.mPreferenceScreen.removePreference(removePreference);
            }
        }
    }

    public /* synthetic */ boolean lambda$update$0$MobileNetworkListController(SubscriptionInfo subscriptionInfo, Preference preference) {
        Intent intent = new Intent(this.mContext, MobileNetworkActivity.class);
        intent.putExtra("android.provider.extra.SUB_ID", subscriptionInfo.getSubscriptionId());
        this.mContext.startActivity(intent);
        return true;
    }

    public void onSubscriptionsChanged() {
        update();
    }
}
