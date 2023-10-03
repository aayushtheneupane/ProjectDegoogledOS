package com.android.settings;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.euicc.EuiccManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;
import com.android.settings.core.InstrumentedFragment;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.enterprise.ActionDisabledByAdminDialogHelper;
import com.android.settings.password.ChooseLockSettingsHelper;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class ResetNetwork extends InstrumentedFragment {
    private View mContentView;
    CheckBox mEsimCheckbox;
    View mEsimContainer;
    private Button mInitiateButton;
    private final View.OnClickListener mInitiateListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (!ResetNetwork.this.runKeyguardConfirmation(55)) {
                ResetNetwork.this.showFinalConfirmation();
            }
        }
    };
    private Spinner mSubscriptionSpinner;
    private List<SubscriptionInfo> mSubscriptions;

    public int getMetricsCategory() {
        return 83;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivity().setTitle(C1715R.string.reset_network_title);
    }

    /* access modifiers changed from: private */
    public boolean runKeyguardConfirmation(int i) {
        return new ChooseLockSettingsHelper(getActivity(), this).launchConfirmationActivity(i, getActivity().getResources().getText(C1715R.string.reset_network_title));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 55) {
            if (i2 == -1) {
                showFinalConfirmation();
            } else {
                establishInitialState();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void showFinalConfirmation() {
        Bundle bundle = new Bundle();
        List<SubscriptionInfo> list = this.mSubscriptions;
        if (list != null && list.size() > 0) {
            bundle.putInt("subscription", this.mSubscriptions.get(this.mSubscriptionSpinner.getSelectedItemPosition()).getSubscriptionId());
        }
        bundle.putBoolean("erase_esim", this.mEsimContainer.getVisibility() == 0 && this.mEsimCheckbox.isChecked());
        new SubSettingLauncher(getContext()).setDestination(ResetNetworkConfirm.class.getName()).setArguments(bundle).setTitleRes(C1715R.string.reset_network_confirm_title).setSourceMetricsCategory(getMetricsCategory()).launch();
    }

    private void establishInitialState() {
        this.mSubscriptionSpinner = (Spinner) this.mContentView.findViewById(C1715R.C1718id.reset_network_subscription);
        this.mEsimContainer = this.mContentView.findViewById(C1715R.C1718id.erase_esim_container);
        this.mEsimCheckbox = (CheckBox) this.mContentView.findViewById(C1715R.C1718id.erase_esim);
        this.mSubscriptions = SubscriptionManager.from(getActivity()).getActiveSubscriptionInfoList(true);
        List<SubscriptionInfo> list = this.mSubscriptions;
        if (list == null || list.size() <= 0) {
            this.mSubscriptionSpinner.setVisibility(4);
        } else {
            int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
            if (!SubscriptionManager.isUsableSubIdValue(defaultDataSubscriptionId)) {
                defaultDataSubscriptionId = SubscriptionManager.getDefaultVoiceSubscriptionId();
            }
            if (!SubscriptionManager.isUsableSubIdValue(defaultDataSubscriptionId)) {
                defaultDataSubscriptionId = SubscriptionManager.getDefaultSmsSubscriptionId();
            }
            if (!SubscriptionManager.isUsableSubIdValue(defaultDataSubscriptionId)) {
                defaultDataSubscriptionId = SubscriptionManager.getDefaultSubscriptionId();
            }
            this.mSubscriptions.size();
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (SubscriptionInfo next : this.mSubscriptions) {
                if (next.getSubscriptionId() == defaultDataSubscriptionId) {
                    i = arrayList.size();
                }
                String charSequence = next.getDisplayName().toString();
                if (TextUtils.isEmpty(charSequence)) {
                    charSequence = next.getNumber();
                }
                if (TextUtils.isEmpty(charSequence)) {
                    charSequence = next.getCarrierName().toString();
                }
                if (TextUtils.isEmpty(charSequence)) {
                    charSequence = String.format("MCC:%s MNC:%s Slot:%s Id:%s", new Object[]{Integer.valueOf(next.getMcc()), Integer.valueOf(next.getMnc()), Integer.valueOf(next.getSimSlotIndex()), Integer.valueOf(next.getSubscriptionId())});
                }
                arrayList.add(charSequence);
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), 17367048, arrayList);
            arrayAdapter.setDropDownViewResource(17367049);
            this.mSubscriptionSpinner.setAdapter(arrayAdapter);
            this.mSubscriptionSpinner.setSelection(i);
            if (this.mSubscriptions.size() > 1) {
                this.mSubscriptionSpinner.setVisibility(0);
            } else {
                this.mSubscriptionSpinner.setVisibility(4);
            }
        }
        this.mInitiateButton = (Button) this.mContentView.findViewById(C1715R.C1718id.initiate_reset_network);
        this.mInitiateButton.setOnClickListener(this.mInitiateListener);
        if (showEuiccSettings(getContext())) {
            this.mEsimContainer.setVisibility(0);
            this.mEsimContainer.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ResetNetwork.this.mEsimCheckbox.toggle();
                }
            });
            return;
        }
        this.mEsimCheckbox.setChecked(false);
    }

    private boolean showEuiccSettings(Context context) {
        if (!((EuiccManager) context.getSystemService("euicc")).isEnabled()) {
            return false;
        }
        ContentResolver contentResolver = context.getContentResolver();
        if (Settings.Global.getInt(contentResolver, "euicc_provisioned", 0) == 0 && Settings.Global.getInt(contentResolver, "development_settings_enabled", 0) == 0) {
            return false;
        }
        return true;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        UserManager userManager = UserManager.get(getActivity());
        RestrictedLockUtils.EnforcedAdmin checkIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(getActivity(), "no_network_reset", UserHandle.myUserId());
        if (!userManager.isAdminUser() || RestrictedLockUtilsInternal.hasBaseUserRestriction(getActivity(), "no_network_reset", UserHandle.myUserId())) {
            return layoutInflater.inflate(C1715R.layout.network_reset_disallowed_screen, (ViewGroup) null);
        }
        if (checkIfRestrictionEnforced != null) {
            AlertDialog.Builder prepareDialogBuilder = new ActionDisabledByAdminDialogHelper(getActivity()).prepareDialogBuilder("no_network_reset", checkIfRestrictionEnforced);
            prepareDialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public final void onDismiss(DialogInterface dialogInterface) {
                    ResetNetwork.this.lambda$onCreateView$0$ResetNetwork(dialogInterface);
                }
            });
            prepareDialogBuilder.show();
            return new View(getContext());
        }
        this.mContentView = layoutInflater.inflate(C1715R.layout.reset_network, (ViewGroup) null);
        establishInitialState();
        return this.mContentView;
    }

    public /* synthetic */ void lambda$onCreateView$0$ResetNetwork(DialogInterface dialogInterface) {
        getActivity().finish();
    }
}
