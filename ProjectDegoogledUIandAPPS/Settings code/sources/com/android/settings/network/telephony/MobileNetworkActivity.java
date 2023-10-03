package com.android.settings.network.telephony;

import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.android.internal.util.CollectionUtils;
import com.android.settings.core.SettingsBaseActivity;
import com.android.settings.development.featureflags.FeatureFlagPersistent;
import com.android.settings.network.SubscriptionUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MobileNetworkActivity extends SettingsBaseActivity {
    static final String MOBILE_SETTINGS_TAG = "mobile_settings:";
    static final int SUB_ID_NULL = Integer.MIN_VALUE;
    int mCurSubscriptionId;
    private final SubscriptionManager.OnSubscriptionsChangedListener mOnSubscriptionsChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener() {
        public void onSubscriptionsChanged() {
            MobileNetworkActivity mobileNetworkActivity = MobileNetworkActivity.this;
            if (!Objects.equals(mobileNetworkActivity.mSubscriptionInfos, mobileNetworkActivity.mSubscriptionManager.getActiveSubscriptionInfoList(true))) {
                MobileNetworkActivity.this.updateSubscriptions((Bundle) null);
            }
        }
    };
    private PhoneChangeReceiver mPhoneChangeReceiver;
    List<SubscriptionInfo> mSubscriptionInfos = new ArrayList();
    SubscriptionManager mSubscriptionManager;

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        updateSubscriptions((Bundle) null);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (FeatureFlagPersistent.isEnabled(this, "settings_network_and_internet_v2")) {
            setContentView((int) C1715R.layout.mobile_network_settings_container_v2);
        } else {
            setContentView((int) C1715R.layout.mobile_network_settings_container);
        }
        setActionBar((Toolbar) findViewById(C1715R.C1718id.mobile_action_bar));
        this.mPhoneChangeReceiver = new PhoneChangeReceiver(this, new PhoneChangeReceiver.Client() {
            public void onPhoneChange() {
                MobileNetworkActivity.this.switchFragment(new MobileNetworkSettings(), MobileNetworkActivity.this.mCurSubscriptionId, true);
            }

            public int getSubscriptionId() {
                return MobileNetworkActivity.this.mCurSubscriptionId;
            }
        });
        this.mSubscriptionManager = (SubscriptionManager) getSystemService(SubscriptionManager.class);
        this.mSubscriptionInfos = this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        int i = SUB_ID_NULL;
        if (bundle != null) {
            i = bundle.getInt("android.provider.extra.SUB_ID", SUB_ID_NULL);
        }
        this.mCurSubscriptionId = i;
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        updateSubscriptions(bundle);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.mPhoneChangeReceiver.register();
        this.mSubscriptionManager.addOnSubscriptionsChangedListener(this.mOnSubscriptionsChangeListener);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.mPhoneChangeReceiver.unregister();
        this.mSubscriptionManager.removeOnSubscriptionsChangedListener(this.mOnSubscriptionsChangeListener);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        saveInstanceState(bundle);
    }

    /* access modifiers changed from: package-private */
    public void saveInstanceState(Bundle bundle) {
        bundle.putInt("android.provider.extra.SUB_ID", this.mCurSubscriptionId);
    }

    /* access modifiers changed from: package-private */
    public void updateSubscriptions(Bundle bundle) {
        SubscriptionInfo subscription = getSubscription();
        if (subscription != null) {
            setTitle(subscription.getDisplayName());
        }
        this.mSubscriptionInfos = this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        if (!FeatureFlagPersistent.isEnabled(this, "settings_network_and_internet_v2")) {
            updateBottomNavigationView();
        }
        if (bundle == null) {
            switchFragment(new MobileNetworkSettings(), getSubscriptionId());
        }
    }

    /* access modifiers changed from: package-private */
    public SubscriptionInfo getSubscription() {
        int intExtra;
        Intent intent = getIntent();
        if (!(intent == null || (intExtra = intent.getIntExtra("android.provider.extra.SUB_ID", SUB_ID_NULL)) == SUB_ID_NULL)) {
            for (SubscriptionInfo next : SubscriptionUtil.getAvailableSubscriptions(this)) {
                if (next.getSubscriptionId() == intExtra) {
                    return next;
                }
            }
        }
        if (CollectionUtils.isEmpty(this.mSubscriptionInfos)) {
            return null;
        }
        return this.mSubscriptionInfos.get(0);
    }

    /* access modifiers changed from: package-private */
    public int getSubscriptionId() {
        SubscriptionInfo subscription = getSubscription();
        if (subscription != null) {
            return subscription.getSubscriptionId();
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void updateBottomNavigationView() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(C1715R.C1718id.bottom_nav);
        if (CollectionUtils.size(this.mSubscriptionInfos) <= 1) {
            bottomNavigationView.setVisibility(8);
            return;
        }
        Menu menu = bottomNavigationView.getMenu();
        menu.clear();
        int size = this.mSubscriptionInfos.size();
        for (int i = 0; i < size; i++) {
            SubscriptionInfo subscriptionInfo = this.mSubscriptionInfos.get(i);
            menu.add(0, subscriptionInfo.getSubscriptionId(), i, subscriptionInfo.getDisplayName()).setIcon(C1715R.C1717drawable.ic_settings_sim);
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public final boolean onNavigationItemSelected(MenuItem menuItem) {
                return MobileNetworkActivity.this.lambda$updateBottomNavigationView$0$MobileNetworkActivity(menuItem);
            }
        });
    }

    public /* synthetic */ boolean lambda$updateBottomNavigationView$0$MobileNetworkActivity(MenuItem menuItem) {
        switchFragment(new MobileNetworkSettings(), menuItem.getItemId());
        return true;
    }

    /* access modifiers changed from: package-private */
    public void switchFragment(Fragment fragment, int i) {
        switchFragment(fragment, i, false);
    }

    /* access modifiers changed from: package-private */
    public void switchFragment(Fragment fragment, int i, boolean z) {
        int i2 = this.mCurSubscriptionId;
        if (i2 == SUB_ID_NULL || i != i2 || z) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putInt("android.provider.extra.SUB_ID", i);
            fragment.setArguments(bundle);
            beginTransaction.replace(C1715R.C1718id.main_content, fragment, buildFragmentTag(i));
            beginTransaction.commit();
            this.mCurSubscriptionId = i;
        }
    }

    private String buildFragmentTag(int i) {
        return MOBILE_SETTINGS_TAG + i;
    }

    static class PhoneChangeReceiver extends BroadcastReceiver {
        private Client mClient;
        private Context mContext;

        interface Client {
            int getSubscriptionId();

            void onPhoneChange();
        }

        public PhoneChangeReceiver(Context context, Client client) {
            this.mContext = context;
            this.mClient = client;
        }

        public void register() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.RADIO_TECHNOLOGY");
            intentFilter.addAction("android.telephony.action.CARRIER_CONFIG_CHANGED");
            this.mContext.registerReceiver(this, intentFilter);
        }

        public void unregister() {
            this.mContext.unregisterReceiver(this);
        }

        public void onReceive(Context context, Intent intent) {
            if (!isInitialStickyBroadcast()) {
                if (!intent.getAction().equals("android.telephony.action.CARRIER_CONFIG_CHANGED") || (intent.hasExtra("android.telephony.extra.SUBSCRIPTION_INDEX") && intent.getIntExtra("android.telephony.extra.SUBSCRIPTION_INDEX", -1) == this.mClient.getSubscriptionId())) {
                    this.mClient.onPhoneChange();
                }
            }
        }
    }
}
