package com.android.settings.datausage;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.NetworkPolicy;
import android.net.NetworkTemplate;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.settings.core.instrumentation.InstrumentedDialogFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.NetworkPolicyEditor;
import com.android.settingslib.net.DataUsageController;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;
import java.util.ArrayList;
import java.util.List;

public class BillingCycleSettings extends DataUsageBaseFragment implements Preference.OnPreferenceChangeListener, DataUsageEditController {
    static final String KEY_SET_DATA_LIMIT = "set_data_limit";
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.billing_cycle;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }

        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            return DataUsageUtils.hasMobileData(context);
        }
    };
    private Preference mBillingCycle;
    private Preference mDataLimit;
    private DataUsageController mDataUsageController;
    private Preference mDataWarning;
    private SwitchPreference mEnableDataLimit;
    private SwitchPreference mEnableDataWarning;
    NetworkTemplate mNetworkTemplate;

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "BillingCycleSettings";
    }

    public int getMetricsCategory() {
        return 342;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.billing_cycle;
    }

    /* access modifiers changed from: package-private */
    public void setUpForTest(NetworkPolicyEditor networkPolicyEditor, Preference preference, Preference preference2, Preference preference3, SwitchPreference switchPreference, SwitchPreference switchPreference2) {
        this.services.mPolicyEditor = networkPolicyEditor;
        this.mBillingCycle = preference;
        this.mDataLimit = preference2;
        this.mDataWarning = preference3;
        this.mEnableDataLimit = switchPreference;
        this.mEnableDataWarning = switchPreference2;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        this.mDataUsageController = new DataUsageController(context);
        this.mNetworkTemplate = getArguments().getParcelable("network_template");
        if (this.mNetworkTemplate == null) {
            this.mNetworkTemplate = DataUsageUtils.getDefaultTemplate(context, DataUsageUtils.getDefaultSubscriptionId(context));
        }
        this.mBillingCycle = findPreference("billing_cycle");
        this.mEnableDataWarning = (SwitchPreference) findPreference("set_data_warning");
        this.mEnableDataWarning.setOnPreferenceChangeListener(this);
        this.mDataWarning = findPreference("data_warning");
        this.mEnableDataLimit = (SwitchPreference) findPreference(KEY_SET_DATA_LIMIT);
        this.mEnableDataLimit.setOnPreferenceChangeListener(this);
        this.mDataLimit = findPreference("data_limit");
        this.mFooterPreferenceMixin.createFooterPreference().setTitle((int) C1715R.string.data_warning_footnote);
    }

    public void onResume() {
        super.onResume();
        updatePrefs();
    }

    /* access modifiers changed from: package-private */
    public void updatePrefs() {
        this.mBillingCycle.setSummary((CharSequence) null);
        long policyWarningBytes = this.services.mPolicyEditor.getPolicyWarningBytes(this.mNetworkTemplate);
        if (policyWarningBytes != -1) {
            this.mDataWarning.setSummary(DataUsageUtils.formatDataUsage(getContext(), policyWarningBytes));
            this.mDataWarning.setEnabled(true);
            this.mEnableDataWarning.setChecked(true);
        } else {
            this.mDataWarning.setSummary((CharSequence) null);
            this.mDataWarning.setEnabled(false);
            this.mEnableDataWarning.setChecked(false);
        }
        long policyLimitBytes = this.services.mPolicyEditor.getPolicyLimitBytes(this.mNetworkTemplate);
        if (policyLimitBytes != -1) {
            this.mDataLimit.setSummary(DataUsageUtils.formatDataUsage(getContext(), policyLimitBytes));
            this.mDataLimit.setEnabled(true);
            this.mEnableDataLimit.setChecked(true);
            return;
        }
        this.mDataLimit.setSummary((CharSequence) null);
        this.mDataLimit.setEnabled(false);
        this.mEnableDataLimit.setChecked(false);
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference == this.mBillingCycle) {
            CycleEditorFragment.show(this);
            return true;
        } else if (preference == this.mDataWarning) {
            BytesEditorFragment.show(this, false);
            return true;
        } else if (preference != this.mDataLimit) {
            return super.onPreferenceTreeClick(preference);
        } else {
            BytesEditorFragment.show(this, true);
            return true;
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (this.mEnableDataLimit == preference) {
            if (!((Boolean) obj).booleanValue()) {
                setPolicyLimitBytes(-1);
                return true;
            }
            ConfirmLimitFragment.show(this);
            return false;
        } else if (this.mEnableDataWarning != preference) {
            return false;
        } else {
            if (((Boolean) obj).booleanValue()) {
                setPolicyWarningBytes(this.mDataUsageController.getDefaultWarningLevel());
            } else {
                setPolicyWarningBytes(-1);
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public void setPolicyLimitBytes(long j) {
        this.services.mPolicyEditor.setPolicyLimitBytes(this.mNetworkTemplate, j);
        updatePrefs();
    }

    private void setPolicyWarningBytes(long j) {
        this.services.mPolicyEditor.setPolicyWarningBytes(this.mNetworkTemplate, j);
        updatePrefs();
    }

    public NetworkPolicyEditor getNetworkPolicyEditor() {
        return this.services.mPolicyEditor;
    }

    public NetworkTemplate getNetworkTemplate() {
        return this.mNetworkTemplate;
    }

    public void updateDataUsage() {
        updatePrefs();
    }

    public static class BytesEditorFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
        private View mView;

        public int getMetricsCategory() {
            return 550;
        }

        public static void show(DataUsageEditController dataUsageEditController, boolean z) {
            if (dataUsageEditController instanceof Fragment) {
                Fragment fragment = (Fragment) dataUsageEditController;
                if (fragment.isAdded()) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("template", dataUsageEditController.getNetworkTemplate());
                    bundle.putBoolean("limit", z);
                    BytesEditorFragment bytesEditorFragment = new BytesEditorFragment();
                    bytesEditorFragment.setArguments(bundle);
                    bytesEditorFragment.setTargetFragment(fragment, 0);
                    bytesEditorFragment.show(fragment.getFragmentManager(), "warningEditor");
                }
            }
        }

        public Dialog onCreateDialog(Bundle bundle) {
            FragmentActivity activity = getActivity();
            LayoutInflater from = LayoutInflater.from(activity);
            boolean z = getArguments().getBoolean("limit");
            this.mView = from.inflate(C1715R.layout.data_usage_bytes_editor, (ViewGroup) null, false);
            setupPicker((EditText) this.mView.findViewById(C1715R.C1718id.bytes), (Spinner) this.mView.findViewById(C1715R.C1718id.size_spinner));
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(z ? C1715R.string.data_usage_limit_editor_title : C1715R.string.data_usage_warning_editor_title);
            builder.setView(this.mView);
            builder.setPositiveButton((int) C1715R.string.data_usage_cycle_editor_positive, (DialogInterface.OnClickListener) this);
            return builder.create();
        }

        private void setupPicker(EditText editText, Spinner spinner) {
            long j;
            NetworkPolicyEditor networkPolicyEditor = ((DataUsageEditController) getTargetFragment()).getNetworkPolicyEditor();
            NetworkTemplate parcelable = getArguments().getParcelable("template");
            if (getArguments().getBoolean("limit")) {
                j = networkPolicyEditor.getPolicyLimitBytes(parcelable);
            } else {
                j = networkPolicyEditor.getPolicyWarningBytes(parcelable);
            }
            float f = (float) j;
            if (f > 1.61061274E9f) {
                String formatText = formatText(f / 1.07374182E9f);
                editText.setText(formatText);
                editText.setSelection(0, formatText.length());
                spinner.setSelection(1);
                return;
            }
            String formatText2 = formatText(f / 1048576.0f);
            editText.setText(formatText2);
            editText.setSelection(0, formatText2.length());
            spinner.setSelection(0);
        }

        private String formatText(float f) {
            return String.valueOf(((float) Math.round(f * 100.0f)) / 100.0f);
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            if (i == -1) {
                DataUsageEditController dataUsageEditController = (DataUsageEditController) getTargetFragment();
                NetworkPolicyEditor networkPolicyEditor = dataUsageEditController.getNetworkPolicyEditor();
                NetworkTemplate parcelable = getArguments().getParcelable("template");
                boolean z = getArguments().getBoolean("limit");
                Spinner spinner = (Spinner) this.mView.findViewById(C1715R.C1718id.size_spinner);
                String obj = ((EditText) this.mView.findViewById(C1715R.C1718id.bytes)).getText().toString();
                if (obj.isEmpty() || obj.equals(".")) {
                    obj = "0";
                }
                long min = Math.min(53687091200000L, (long) (Float.valueOf(obj).floatValue() * ((float) (spinner.getSelectedItemPosition() == 0 ? 1048576 : 1073741824))));
                if (z) {
                    networkPolicyEditor.setPolicyLimitBytes(parcelable, min);
                } else {
                    networkPolicyEditor.setPolicyWarningBytes(parcelable, min);
                }
                dataUsageEditController.updateDataUsage();
            }
        }
    }

    public static class CycleEditorFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
        private NumberPicker mCycleDayPicker;

        public int getMetricsCategory() {
            return 549;
        }

        public static void show(BillingCycleSettings billingCycleSettings) {
            if (billingCycleSettings.isAdded()) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("template", billingCycleSettings.mNetworkTemplate);
                CycleEditorFragment cycleEditorFragment = new CycleEditorFragment();
                cycleEditorFragment.setArguments(bundle);
                cycleEditorFragment.setTargetFragment(billingCycleSettings, 0);
                cycleEditorFragment.show(billingCycleSettings.getFragmentManager(), "cycleEditor");
            }
        }

        public Dialog onCreateDialog(Bundle bundle) {
            FragmentActivity activity = getActivity();
            NetworkPolicyEditor networkPolicyEditor = ((DataUsageEditController) getTargetFragment()).getNetworkPolicyEditor();
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            View inflate = LayoutInflater.from(builder.getContext()).inflate(C1715R.layout.data_usage_cycle_editor, (ViewGroup) null, false);
            this.mCycleDayPicker = (NumberPicker) inflate.findViewById(C1715R.C1718id.cycle_day);
            int policyCycleDay = networkPolicyEditor.getPolicyCycleDay(getArguments().getParcelable("template"));
            this.mCycleDayPicker.setMinValue(1);
            this.mCycleDayPicker.setMaxValue(31);
            this.mCycleDayPicker.setValue(policyCycleDay);
            this.mCycleDayPicker.setWrapSelectorWheel(true);
            builder.setTitle((int) C1715R.string.data_usage_cycle_editor_title);
            builder.setView(inflate);
            builder.setPositiveButton((int) C1715R.string.data_usage_cycle_editor_positive, (DialogInterface.OnClickListener) this);
            return builder.create();
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            DataUsageEditController dataUsageEditController = (DataUsageEditController) getTargetFragment();
            NetworkPolicyEditor networkPolicyEditor = dataUsageEditController.getNetworkPolicyEditor();
            this.mCycleDayPicker.clearFocus();
            networkPolicyEditor.setPolicyCycleDay(getArguments().getParcelable("template"), this.mCycleDayPicker.getValue(), new Time().timezone);
            dataUsageEditController.updateDataUsage();
        }
    }

    public static class ConfirmLimitFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
        static final String EXTRA_LIMIT_BYTES = "limitBytes";

        public int getMetricsCategory() {
            return 551;
        }

        public static void show(BillingCycleSettings billingCycleSettings) {
            NetworkPolicy policy;
            if (billingCycleSettings.isAdded() && (policy = billingCycleSettings.services.mPolicyEditor.getPolicy(billingCycleSettings.mNetworkTemplate)) != null) {
                billingCycleSettings.getResources();
                long max = Math.max(5368709120L, (long) (((float) policy.warningBytes) * 1.2f));
                Bundle bundle = new Bundle();
                bundle.putLong(EXTRA_LIMIT_BYTES, max);
                ConfirmLimitFragment confirmLimitFragment = new ConfirmLimitFragment();
                confirmLimitFragment.setArguments(bundle);
                confirmLimitFragment.setTargetFragment(billingCycleSettings, 0);
                confirmLimitFragment.show(billingCycleSettings.getFragmentManager(), "confirmLimit");
            }
        }

        public Dialog onCreateDialog(Bundle bundle) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle((int) C1715R.string.data_usage_limit_dialog_title);
            builder.setMessage((int) C1715R.string.data_usage_limit_dialog_mobile);
            builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) this);
            builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
            return builder.create();
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            BillingCycleSettings billingCycleSettings = (BillingCycleSettings) getTargetFragment();
            if (i == -1) {
                long j = getArguments().getLong(EXTRA_LIMIT_BYTES);
                if (billingCycleSettings != null) {
                    billingCycleSettings.setPolicyLimitBytes(j);
                }
                billingCycleSettings.getPreferenceManager().getSharedPreferences().edit().putBoolean(BillingCycleSettings.KEY_SET_DATA_LIMIT, true).apply();
            }
        }
    }
}
