package com.android.settings.nfc;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.nfc.PaymentBackend;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.havoc.config.center.C1715R;
import java.util.List;

public class NfcForegroundPreferenceController extends BasePreferenceController implements PaymentBackend.Callback, Preference.OnPreferenceChangeListener, LifecycleObserver, OnStart, OnStop {
    private MetricsFeatureProvider mMetricsFeatureProvider;
    private PaymentBackend mPaymentBackend;
    private DropDownPreference mPreference;

    public NfcForegroundPreferenceController(Context context, String str) {
        super(context, str);
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
    }

    public void setPaymentBackend(PaymentBackend paymentBackend) {
        this.mPaymentBackend = paymentBackend;
    }

    public void onStart() {
        PaymentBackend paymentBackend = this.mPaymentBackend;
        if (paymentBackend != null) {
            paymentBackend.registerCallback(this);
        }
    }

    public void onStop() {
        PaymentBackend paymentBackend = this.mPaymentBackend;
        if (paymentBackend != null) {
            paymentBackend.unregisterCallback(this);
        }
    }

    public int getAvailabilityStatus() {
        PaymentBackend paymentBackend;
        List<PaymentBackend.PaymentAppInfo> paymentAppInfos;
        if (this.mContext.getPackageManager().hasSystemFeature("android.hardware.nfc") && (paymentBackend = this.mPaymentBackend) != null && (paymentAppInfos = paymentBackend.getPaymentAppInfos()) != null && !paymentAppInfos.isEmpty()) {
            return 0;
        }
        return 3;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (DropDownPreference) preferenceScreen.findPreference(getPreferenceKey());
        DropDownPreference dropDownPreference = this.mPreference;
        if (dropDownPreference != null) {
            dropDownPreference.setEntries(new CharSequence[]{this.mContext.getText(C1715R.string.nfc_payment_favor_open), this.mContext.getText(C1715R.string.nfc_payment_favor_default)});
            this.mPreference.setEntryValues(new CharSequence[]{"1", "0"});
        }
    }

    public void onPaymentAppsChanged() {
        updateState(this.mPreference);
    }

    public void updateState(Preference preference) {
        if (preference instanceof DropDownPreference) {
            ((DropDownPreference) preference).setValue(this.mPaymentBackend.isForegroundMode() ? "1" : "0");
        }
        super.updateState(preference);
    }

    public CharSequence getSummary() {
        return this.mPreference.getEntry();
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (!(preference instanceof DropDownPreference)) {
            return false;
        }
        DropDownPreference dropDownPreference = (DropDownPreference) preference;
        String str = (String) obj;
        dropDownPreference.setSummary(dropDownPreference.getEntries()[dropDownPreference.findIndexOfValue(str)]);
        boolean z = Integer.parseInt(str) != 0;
        this.mPaymentBackend.setForegroundMode(z);
        this.mMetricsFeatureProvider.action(this.mContext, z ? 1622 : 1623, (Pair<Integer, Object>[]) new Pair[0]);
        return true;
    }

    public void updateNonIndexableKeys(List<String> list) {
        String preferenceKey = getPreferenceKey();
        if (!TextUtils.isEmpty(preferenceKey)) {
            list.add(preferenceKey);
        }
    }
}
