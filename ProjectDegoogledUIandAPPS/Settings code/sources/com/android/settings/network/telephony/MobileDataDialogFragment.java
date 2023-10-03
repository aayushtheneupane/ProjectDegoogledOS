package com.android.settings.network.telephony;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import androidx.appcompat.app.AlertDialog;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settings.wifi.tether.TetherService;
import com.havoc.config.center.C1715R;

public class MobileDataDialogFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
    private int mSubId;
    private SubscriptionManager mSubscriptionManager;
    private int mType;

    public int getMetricsCategory() {
        return 1582;
    }

    public static MobileDataDialogFragment newInstance(int i, int i2) {
        MobileDataDialogFragment mobileDataDialogFragment = new MobileDataDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("dialog_type", i);
        bundle.putInt(TetherService.EXTRA_SUBID, i2);
        mobileDataDialogFragment.setArguments(bundle);
        return mobileDataDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSubscriptionManager = (SubscriptionManager) getContext().getSystemService(SubscriptionManager.class);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        String str;
        String str2;
        Bundle arguments = getArguments();
        Context context = getContext();
        this.mType = arguments.getInt("dialog_type");
        this.mSubId = arguments.getInt(TetherService.EXTRA_SUBID);
        int i = this.mType;
        if (i == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage((int) C1715R.string.data_usage_disable_mobile);
            builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) this);
            builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
            return builder.create();
        } else if (i == 1) {
            SubscriptionInfo activeSubscriptionInfo = this.mSubscriptionManager.getActiveSubscriptionInfo(this.mSubId);
            SubscriptionInfo defaultDataSubscriptionInfo = this.mSubscriptionManager.getDefaultDataSubscriptionInfo();
            if (defaultDataSubscriptionInfo == null) {
                str = getContext().getResources().getString(C1715R.string.sim_selection_required_pref);
            } else {
                str = defaultDataSubscriptionInfo.getDisplayName().toString();
            }
            if (activeSubscriptionInfo == null) {
                str2 = getContext().getResources().getString(C1715R.string.sim_selection_required_pref);
            } else {
                str2 = activeSubscriptionInfo.getDisplayName().toString();
            }
            AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
            builder2.setTitle((CharSequence) context.getString(C1715R.string.sim_change_data_title, new Object[]{str2}));
            builder2.setMessage((CharSequence) context.getString(C1715R.string.sim_change_data_message, new Object[]{str2, str}));
            builder2.setPositiveButton((CharSequence) context.getString(C1715R.string.sim_change_data_ok, new Object[]{str2}), (DialogInterface.OnClickListener) this);
            builder2.setNegativeButton((int) C1715R.string.cancel, (DialogInterface.OnClickListener) null);
            return builder2.create();
        } else {
            throw new IllegalArgumentException("unknown type " + this.mType);
        }
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.mType;
        if (i2 == 0) {
            MobileNetworkUtils.setMobileDataEnabled(getContext(), this.mSubId, false, false);
        } else if (i2 == 1) {
            this.mSubscriptionManager.setDefaultDataSubId(this.mSubId);
            MobileNetworkUtils.setMobileDataEnabled(getContext(), this.mSubId, true, true);
        } else {
            throw new IllegalArgumentException("unknown type " + this.mType);
        }
    }
}
