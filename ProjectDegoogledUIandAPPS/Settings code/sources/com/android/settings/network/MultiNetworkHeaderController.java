package com.android.settings.network;

import android.content.Context;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.network.SubscriptionsPreferenceController;
import com.android.settings.wifi.WifiConnectionPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;

public class MultiNetworkHeaderController extends BasePreferenceController implements WifiConnectionPreferenceController.UpdateListener, SubscriptionsPreferenceController.UpdateListener {
    public static final String TAG = "MultiNetworkHdrCtrl";
    private int mOriginalExpandedChildrenCount;
    private PreferenceCategory mPreferenceCategory;
    private PreferenceScreen mPreferenceScreen;
    private SubscriptionsPreferenceController mSubscriptionsController;
    private WifiConnectionPreferenceController mWifiController;

    public MultiNetworkHeaderController(Context context, String str) {
        super(context, str);
    }

    public void init(Lifecycle lifecycle) {
        this.mWifiController = createWifiController(lifecycle);
        this.mSubscriptionsController = createSubscriptionsController(lifecycle);
    }

    /* access modifiers changed from: package-private */
    public WifiConnectionPreferenceController createWifiController(Lifecycle lifecycle) {
        return new WifiConnectionPreferenceController(this.mContext, lifecycle, this, this.mPreferenceKey, 0, 746);
    }

    /* access modifiers changed from: package-private */
    public SubscriptionsPreferenceController createSubscriptionsController(Lifecycle lifecycle) {
        return new SubscriptionsPreferenceController(this.mContext, lifecycle, this, this.mPreferenceKey, 10);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreferenceScreen = preferenceScreen;
        this.mOriginalExpandedChildrenCount = this.mPreferenceScreen.getInitialExpandedChildrenCount();
        this.mPreferenceCategory = (PreferenceCategory) preferenceScreen.findPreference(this.mPreferenceKey);
        this.mPreferenceCategory.setVisible(isAvailable());
        this.mWifiController.displayPreference(preferenceScreen);
        this.mSubscriptionsController.displayPreference(preferenceScreen);
    }

    public int getAvailabilityStatus() {
        SubscriptionsPreferenceController subscriptionsPreferenceController = this.mSubscriptionsController;
        return (subscriptionsPreferenceController == null || !subscriptionsPreferenceController.isAvailable()) ? 2 : 0;
    }

    public void onChildrenUpdated() {
        boolean isAvailable = isAvailable();
        int i = this.mOriginalExpandedChildrenCount;
        if (i != Integer.MAX_VALUE) {
            if (isAvailable) {
                this.mPreferenceScreen.setInitialExpandedChildrenCount(i + this.mPreferenceCategory.getPreferenceCount());
            } else {
                this.mPreferenceScreen.setInitialExpandedChildrenCount(i);
            }
        }
        this.mPreferenceCategory.setVisible(isAvailable);
    }
}
