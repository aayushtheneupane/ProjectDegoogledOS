package com.android.settings.network.telephony;

import android.content.Context;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.widget.Switch;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.development.featureflags.FeatureFlagPersistent;
import com.android.settings.network.SubscriptionUtil;
import com.android.settings.network.SubscriptionsChangeListener;
import com.android.settings.widget.SwitchBar;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;
import java.util.List;

public class MobileNetworkSwitchController extends BasePreferenceController implements SubscriptionsChangeListener.SubscriptionsChangeListenerClient, LifecycleObserver {
    private static final String TAG = "MobileNetworkSwitchCtrl";
    private SubscriptionsChangeListener mChangeListener;
    private int mSubId = -1;
    private SubscriptionManager mSubscriptionManager = ((SubscriptionManager) this.mContext.getSystemService(SubscriptionManager.class));
    private SwitchBar mSwitchBar;

    public void onAirplaneModeChanged(boolean z) {
    }

    public MobileNetworkSwitchController(Context context, String str) {
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
        this.mSwitchBar = (SwitchBar) ((LayoutPreference) preferenceScreen.findPreference(this.mPreferenceKey)).findViewById(C1715R.C1718id.switch_bar);
        this.mSwitchBar.setSwitchBarText(C1715R.string.mobile_network_use_sim_on, C1715R.string.mobile_network_use_sim_off);
        this.mSwitchBar.addOnSwitchChangeListener(new SwitchBar.OnSwitchChangeListener() {
            public final void onSwitchChanged(Switch switchR, boolean z) {
                MobileNetworkSwitchController.this.lambda$displayPreference$0$MobileNetworkSwitchController(switchR, z);
            }
        });
        update();
    }

    public /* synthetic */ void lambda$displayPreference$0$MobileNetworkSwitchController(Switch switchR, boolean z) {
        if (this.mSubscriptionManager.isSubscriptionEnabled(this.mSubId) != z && !this.mSubscriptionManager.setSubscriptionEnabled(this.mSubId, z)) {
            this.mSwitchBar.setChecked(!z);
        }
    }

    private void update() {
        if (this.mSwitchBar != null) {
            List<SubscriptionInfo> availableSubscriptions = SubscriptionUtil.getAvailableSubscriptions(this.mContext);
            int i = this.mSubId;
            if (i == -1 || (this.mSubscriptionManager.isSubscriptionEnabled(i) && availableSubscriptions.size() < 2)) {
                this.mSwitchBar.hide();
                return;
            }
            for (SubscriptionInfo subscriptionId : availableSubscriptions) {
                if (subscriptionId.getSubscriptionId() == this.mSubId) {
                    this.mSwitchBar.show();
                    this.mSwitchBar.setChecked(this.mSubscriptionManager.isSubscriptionEnabled(this.mSubId));
                    return;
                }
            }
            this.mSwitchBar.hide();
        }
    }

    public int getAvailabilityStatus() {
        return FeatureFlagPersistent.isEnabled(this.mContext, "settings_network_and_internet_v2") ? 1 : 2;
    }

    public void onSubscriptionsChanged() {
        update();
    }
}
