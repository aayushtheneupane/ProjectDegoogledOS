package com.android.settings.network.telephony;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.network.MobileDataContentObserver;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;

public class MobileDataPreferenceController extends TelephonyTogglePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private static final String DIALOG_TAG = "MobileDataDialog";
    private MobileDataContentObserver mDataContentObserver = new MobileDataContentObserver(new Handler(Looper.getMainLooper()));
    int mDialogType;
    private FragmentManager mFragmentManager;
    boolean mNeedDialog;
    private SwitchPreference mPreference;
    private SubscriptionManager mSubscriptionManager;
    private TelephonyManager mTelephonyManager;

    public int getAvailabilityStatus(int i) {
        return i != -1 ? 0 : 5;
    }

    public MobileDataPreferenceController(Context context, String str) {
        super(context, str);
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        this.mDataContentObserver.setOnMobileDataChangedListener(new MobileDataContentObserver.OnMobileDataChangedListener() {
            public final void onMobileDataChanged() {
                MobileDataPreferenceController.this.lambda$new$0$MobileDataPreferenceController();
            }
        });
    }

    public /* synthetic */ void lambda$new$0$MobileDataPreferenceController() {
        updateState(this.mPreference);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (SwitchPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    public void onStart() {
        int i = this.mSubId;
        if (i != -1) {
            this.mDataContentObserver.register(this.mContext, i);
        }
    }

    public void onStop() {
        if (this.mSubId != -1) {
            this.mDataContentObserver.unRegister(this.mContext);
        }
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            return false;
        }
        if (!this.mNeedDialog) {
            return true;
        }
        showDialog(this.mDialogType);
        return true;
    }

    public boolean setChecked(boolean z) {
        this.mNeedDialog = isDialogNeeded();
        if (this.mNeedDialog) {
            return false;
        }
        MobileNetworkUtils.setMobileDataEnabled(this.mContext, this.mSubId, z, false);
        return true;
    }

    public boolean isChecked() {
        return this.mTelephonyManager.isDataEnabled();
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        if (isOpportunistic()) {
            preference.setEnabled(false);
            preference.setSummary((int) C1715R.string.mobile_data_settings_summary_auto_switch);
            return;
        }
        preference.setEnabled(true);
        preference.setSummary((int) C1715R.string.mobile_data_settings_summary);
    }

    private boolean isOpportunistic() {
        SubscriptionInfo activeSubscriptionInfo = this.mSubscriptionManager.getActiveSubscriptionInfo(this.mSubId);
        return activeSubscriptionInfo != null && activeSubscriptionInfo.isOpportunistic();
    }

    public void init(FragmentManager fragmentManager, int i) {
        this.mFragmentManager = fragmentManager;
        this.mSubId = i;
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
    }

    /* access modifiers changed from: package-private */
    public boolean isDialogNeeded() {
        boolean z = !isChecked();
        boolean z2 = this.mTelephonyManager.getSimCount() > 1;
        int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
        boolean z3 = this.mSubscriptionManager.isActiveSubscriptionId(defaultDataSubscriptionId) && defaultDataSubscriptionId != this.mSubId;
        if (!z || !z2 || !z3) {
            return false;
        }
        this.mDialogType = 1;
        return true;
    }

    private void showDialog(int i) {
        MobileDataDialogFragment.newInstance(i, this.mSubId).show(this.mFragmentManager, DIALOG_TAG);
    }
}
