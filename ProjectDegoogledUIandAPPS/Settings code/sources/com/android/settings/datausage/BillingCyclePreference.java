package com.android.settings.datausage;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkTemplate;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import androidx.preference.Preference;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.datausage.CellDataPreference;
import com.android.settings.datausage.TemplatePreference;
import com.havoc.config.center.C1715R;

public class BillingCyclePreference extends Preference implements TemplatePreference {
    private final CellDataPreference.DataStateListener mListener = new CellDataPreference.DataStateListener() {
        public void onChange(boolean z) {
            BillingCyclePreference.this.updateEnabled();
        }
    };
    private TemplatePreference.NetworkServices mServices;
    private int mSubId;
    private NetworkTemplate mTemplate;

    public BillingCyclePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onAttached() {
        super.onAttached();
        this.mListener.setListener(true, this.mSubId, getContext());
    }

    public void onDetached() {
        this.mListener.setListener(false, this.mSubId, getContext());
        super.onDetached();
    }

    public void setTemplate(NetworkTemplate networkTemplate, int i, TemplatePreference.NetworkServices networkServices) {
        this.mTemplate = networkTemplate;
        this.mSubId = i;
        this.mServices = networkServices;
        setSummary((CharSequence) null);
        setIntent(getIntent());
    }

    /* access modifiers changed from: private */
    public void updateEnabled() {
        try {
            setEnabled(this.mServices.mNetworkService.isBandwidthControlEnabled() && this.mServices.mTelephonyManager.getDataEnabled(this.mSubId) && this.mServices.mUserManager.isAdminUser());
        } catch (RemoteException unused) {
            setEnabled(false);
        }
    }

    public Intent getIntent() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("network_template", this.mTemplate);
        return new SubSettingLauncher(getContext()).setDestination(BillingCycleSettings.class.getName()).setArguments(bundle).setTitleRes(C1715R.string.billing_cycle).setSourceMetricsCategory(0).toIntent();
    }
}
