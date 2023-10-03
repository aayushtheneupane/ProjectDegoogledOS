package com.android.settings.network;

import android.content.Context;
import android.content.Intent;
import android.os.UserManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.network.SubscriptionsChangeListener;
import com.android.settings.network.telephony.MobileNetworkActivity;
import com.android.settings.network.telephony.MobileNetworkUtils;
import com.android.settings.widget.AddPreference;
import com.android.settingslib.Utils;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.List;

public class MobileNetworkSummaryController extends AbstractPreferenceController implements SubscriptionsChangeListener.SubscriptionsChangeListenerClient, LifecycleObserver, PreferenceControllerMixin {
    private SubscriptionsChangeListener mChangeListener;
    private AddPreference mPreference;
    private SubscriptionManager mSubscriptionManager;
    private UserManager mUserManager;

    public String getPreferenceKey() {
        return "mobile_network_list";
    }

    public MobileNetworkSummaryController(Context context, Lifecycle lifecycle) {
        super(context);
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        this.mUserManager = (UserManager) context.getSystemService(UserManager.class);
        if (lifecycle != null) {
            this.mChangeListener = new SubscriptionsChangeListener(context, this);
            lifecycle.addObserver(this);
        }
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
        this.mPreference = (AddPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    public CharSequence getSummary() {
        List<SubscriptionInfo> availableSubscriptions = SubscriptionUtil.getAvailableSubscriptions(this.mContext);
        if (availableSubscriptions.isEmpty()) {
            if (MobileNetworkUtils.showEuiccSettings(this.mContext)) {
                return this.mContext.getResources().getString(C1715R.string.mobile_network_summary_add_a_network);
            }
            return null;
        } else if (availableSubscriptions.size() == 1) {
            return availableSubscriptions.get(0).getDisplayName();
        } else {
            int size = availableSubscriptions.size();
            return this.mContext.getResources().getQuantityString(C1715R.plurals.mobile_network_summary_count, size, new Object[]{Integer.valueOf(size)});
        }
    }

    private void startAddSimFlow() {
        Intent intent = new Intent("android.telephony.euicc.action.PROVISION_EMBEDDED_SUBSCRIPTION");
        intent.putExtra("android.telephony.euicc.extra.FORCE_PROVISION", true);
        this.mContext.startActivity(intent);
    }

    private void update() {
        AddPreference addPreference = this.mPreference;
        if (addPreference != null && !addPreference.isDisabledByAdmin()) {
            refreshSummary(this.mPreference);
            this.mPreference.setOnPreferenceClickListener((Preference.OnPreferenceClickListener) null);
            this.mPreference.setOnAddClickListener((AddPreference.OnAddClickListener) null);
            this.mPreference.setFragment((String) null);
            this.mPreference.setEnabled(!this.mChangeListener.isAirplaneModeOn());
            List<SubscriptionInfo> availableSubscriptions = SubscriptionUtil.getAvailableSubscriptions(this.mContext);
            if (!availableSubscriptions.isEmpty()) {
                if (MobileNetworkUtils.showEuiccSettings(this.mContext)) {
                    this.mPreference.setAddWidgetEnabled(!this.mChangeListener.isAirplaneModeOn());
                    this.mPreference.setOnAddClickListener(new AddPreference.OnAddClickListener() {
                        public final void onAddClick(AddPreference addPreference) {
                            MobileNetworkSummaryController.this.lambda$update$1$MobileNetworkSummaryController(addPreference);
                        }
                    });
                }
                if (availableSubscriptions.size() == 1) {
                    this.mPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(availableSubscriptions) {
                        private final /* synthetic */ List f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final boolean onPreferenceClick(Preference preference) {
                            return MobileNetworkSummaryController.this.lambda$update$2$MobileNetworkSummaryController(this.f$1, preference);
                        }
                    });
                } else {
                    this.mPreference.setFragment(MobileNetworkListFragment.class.getCanonicalName());
                }
            } else if (MobileNetworkUtils.showEuiccSettings(this.mContext)) {
                this.mPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    public final boolean onPreferenceClick(Preference preference) {
                        return MobileNetworkSummaryController.this.lambda$update$0$MobileNetworkSummaryController(preference);
                    }
                });
            } else {
                this.mPreference.setEnabled(false);
            }
        }
    }

    public /* synthetic */ boolean lambda$update$0$MobileNetworkSummaryController(Preference preference) {
        startAddSimFlow();
        return true;
    }

    public /* synthetic */ void lambda$update$1$MobileNetworkSummaryController(AddPreference addPreference) {
        startAddSimFlow();
    }

    public /* synthetic */ boolean lambda$update$2$MobileNetworkSummaryController(List list, Preference preference) {
        Intent intent = new Intent(this.mContext, MobileNetworkActivity.class);
        intent.putExtra("android.provider.extra.SUB_ID", ((SubscriptionInfo) list.get(0)).getSubscriptionId());
        this.mContext.startActivity(intent);
        return true;
    }

    public boolean isAvailable() {
        return !Utils.isWifiOnly(this.mContext) && this.mUserManager.isAdminUser();
    }

    public void onAirplaneModeChanged(boolean z) {
        update();
    }

    public void onSubscriptionsChanged() {
        refreshSummary(this.mPreference);
        update();
    }
}
