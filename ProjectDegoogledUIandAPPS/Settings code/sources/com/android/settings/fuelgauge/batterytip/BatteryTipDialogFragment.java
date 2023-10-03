package com.android.settings.fuelgauge.batterytip;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settings.SettingsActivity;
import com.android.settings.Utils;
import com.android.settings.core.InstrumentedPreferenceFragment;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settings.fuelgauge.batterytip.BatteryTipPreferenceController;
import com.android.settings.fuelgauge.batterytip.actions.BatteryTipAction;
import com.android.settings.fuelgauge.batterytip.tips.BatteryTip;
import com.android.settings.fuelgauge.batterytip.tips.HighUsageTip;
import com.android.settings.fuelgauge.batterytip.tips.RestrictAppTip;
import com.android.settings.fuelgauge.batterytip.tips.UnrestrictAppTip;
import com.havoc.config.center.C1715R;
import java.util.List;

public class BatteryTipDialogFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
    BatteryTip mBatteryTip;
    int mMetricsKey;

    public int getMetricsCategory() {
        return 1323;
    }

    public static BatteryTipDialogFragment newInstance(BatteryTip batteryTip, int i) {
        BatteryTipDialogFragment batteryTipDialogFragment = new BatteryTipDialogFragment();
        Bundle bundle = new Bundle(1);
        bundle.putParcelable(BatteryTipPreferenceController.PREF_NAME, batteryTip);
        bundle.putInt("metrics_key", i);
        batteryTipDialogFragment.setArguments(bundle);
        return batteryTipDialogFragment;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        Context context = getContext();
        this.mBatteryTip = (BatteryTip) arguments.getParcelable(BatteryTipPreferenceController.PREF_NAME);
        this.mMetricsKey = arguments.getInt("metrics_key");
        int type = this.mBatteryTip.getType();
        if (type == 1) {
            RestrictAppTip restrictAppTip = (RestrictAppTip) this.mBatteryTip;
            List<AppInfo> restrictAppList = restrictAppTip.getRestrictAppList();
            int size = restrictAppList.size();
            CharSequence applicationLabel = Utils.getApplicationLabel(context, restrictAppList.get(0).packageName);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle((CharSequence) context.getResources().getQuantityString(C1715R.plurals.battery_tip_restrict_app_dialog_title, size, new Object[]{Integer.valueOf(size)}));
            builder.setPositiveButton((int) C1715R.string.battery_tip_restrict_app_dialog_ok, (DialogInterface.OnClickListener) this);
            builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
            if (size == 1) {
                builder.setMessage((CharSequence) getString(C1715R.string.battery_tip_restrict_app_dialog_message, applicationLabel));
            } else if (size <= 5) {
                builder.setMessage((CharSequence) getString(C1715R.string.battery_tip_restrict_apps_less_than_5_dialog_message));
                RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(context).inflate(C1715R.layout.recycler_view, (ViewGroup) null);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(new HighUsageAdapter(context, restrictAppList));
                builder.setView((View) recyclerView);
            } else {
                builder.setMessage((CharSequence) context.getString(C1715R.string.battery_tip_restrict_apps_more_than_5_dialog_message, new Object[]{restrictAppTip.getRestrictAppsString(context)}));
            }
            return builder.create();
        } else if (type == 2) {
            HighUsageTip highUsageTip = (HighUsageTip) this.mBatteryTip;
            RecyclerView recyclerView2 = (RecyclerView) LayoutInflater.from(context).inflate(C1715R.layout.recycler_view, (ViewGroup) null);
            recyclerView2.setLayoutManager(new LinearLayoutManager(context));
            recyclerView2.setAdapter(new HighUsageAdapter(context, highUsageTip.getHighUsageAppList()));
            AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
            builder2.setMessage((CharSequence) getString(C1715R.string.battery_tip_dialog_message, Integer.valueOf(highUsageTip.getHighUsageAppList().size())));
            builder2.setView((View) recyclerView2);
            builder2.setPositiveButton(17039370, (DialogInterface.OnClickListener) null);
            return builder2.create();
        } else if (type == 6) {
            AlertDialog.Builder builder3 = new AlertDialog.Builder(context);
            builder3.setMessage((int) C1715R.string.battery_tip_dialog_summary_message);
            builder3.setPositiveButton(17039370, (DialogInterface.OnClickListener) null);
            return builder3.create();
        } else if (type == 7) {
            Utils.getApplicationLabel(context, ((UnrestrictAppTip) this.mBatteryTip).getPackageName());
            AlertDialog.Builder builder4 = new AlertDialog.Builder(context);
            builder4.setTitle((CharSequence) getString(C1715R.string.battery_tip_unrestrict_app_dialog_title));
            builder4.setMessage((int) C1715R.string.battery_tip_unrestrict_app_dialog_message);
            builder4.setPositiveButton((int) C1715R.string.battery_tip_unrestrict_app_dialog_ok, (DialogInterface.OnClickListener) this);
            builder4.setNegativeButton((int) C1715R.string.battery_tip_unrestrict_app_dialog_cancel, (DialogInterface.OnClickListener) null);
            return builder4.create();
        } else {
            throw new IllegalArgumentException("unknown type " + this.mBatteryTip.getType());
        }
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        BatteryTipPreferenceController.BatteryTipListener batteryTipListener = (BatteryTipPreferenceController.BatteryTipListener) getTargetFragment();
        if (batteryTipListener != null) {
            BatteryTipAction actionForBatteryTip = BatteryTipUtils.getActionForBatteryTip(this.mBatteryTip, (SettingsActivity) getActivity(), (InstrumentedPreferenceFragment) getTargetFragment());
            if (actionForBatteryTip != null) {
                actionForBatteryTip.handlePositiveAction(this.mMetricsKey);
            }
            batteryTipListener.onBatteryTipHandled(this.mBatteryTip);
        }
    }
}
