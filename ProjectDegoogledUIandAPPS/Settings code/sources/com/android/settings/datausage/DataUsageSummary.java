package com.android.settings.datausage;

import android.app.Activity;
import android.content.Context;
import android.net.NetworkTemplate;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.SubscriptionPlan;
import android.text.BidiFormatter;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.text.style.RelativeSizeSpan;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.dashboard.SummaryLoader;
import com.android.settings.datausage.BillingCycleSettings;
import com.android.settingslib.NetworkPolicyEditor;
import com.android.settingslib.Utils;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.net.DataUsageController;
import com.android.settingslib.net.DataUsageUtils;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class DataUsageSummary extends DataUsageBaseFragment implements DataUsageEditController {
    public static final SummaryLoader.SummaryProviderFactory SUMMARY_PROVIDER_FACTORY = $$Lambda$YwlDbChrdnT61OBL_A63UT4To.INSTANCE;
    /* access modifiers changed from: private */
    public static boolean showDailyDataUsage;
    private NetworkTemplate mDefaultTemplate;
    private DataUsageSummaryPreferenceController mSummaryController;
    private DataUsageSummaryPreference mSummaryPreference;

    public int getHelpResource() {
        return C1715R.string.help_url_data_usage;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "DataUsageSummary";
    }

    public int getMetricsCategory() {
        return 37;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.data_usage;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        showDailyDataUsage = Settings.System.getInt(context.getContentResolver(), "data_usage_period", 1) == 0;
        boolean hasMobileData = DataUsageUtils.hasMobileData(context);
        int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
        if (defaultDataSubscriptionId == -1) {
            hasMobileData = false;
        }
        this.mDefaultTemplate = DataUsageUtils.getDefaultTemplate(context, defaultDataSubscriptionId);
        this.mSummaryPreference = (DataUsageSummaryPreference) findPreference("status_header");
        if (!hasMobileData || !isAdmin()) {
            removePreference("restrict_background");
        }
        boolean hasWifiRadio = DataUsageUtils.hasWifiRadio(context);
        if (hasMobileData) {
            addMobileSection(defaultDataSubscriptionId);
            if (DataUsageUtils.hasSim(context) && hasWifiRadio) {
                addWifiSection();
            }
        } else if (hasWifiRadio) {
            addWifiSection();
        }
        if (DataUsageUtils.hasEthernet(context)) {
            addEthernetSection();
        }
        setHasOptionsMenu(true);
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference != findPreference("status_header")) {
            return super.onPreferenceTreeClick(preference);
        }
        BillingCycleSettings.BytesEditorFragment.show(this, false);
        return false;
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        FragmentActivity activity = getActivity();
        ArrayList arrayList = new ArrayList();
        this.mSummaryController = new DataUsageSummaryPreferenceController(activity, getSettingsLifecycle(), this, DataUsageUtils.getDefaultSubscriptionId(activity));
        arrayList.add(this.mSummaryController);
        getSettingsLifecycle().addObserver(this.mSummaryController);
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public void addMobileSection(int i) {
        addMobileSection(i, (SubscriptionInfo) null);
    }

    private void addMobileSection(int i, SubscriptionInfo subscriptionInfo) {
        TemplatePreferenceCategory templatePreferenceCategory = (TemplatePreferenceCategory) inflatePreferences(C1715R.xml.data_usage_cellular);
        templatePreferenceCategory.setTemplate(DataUsageUtils.getMobileTemplate(getContext(), i), i, this.services);
        templatePreferenceCategory.pushTemplates(this.services);
        if (subscriptionInfo != null && !TextUtils.isEmpty(subscriptionInfo.getDisplayName())) {
            templatePreferenceCategory.findPreference("mobile_category").setTitle(subscriptionInfo.getDisplayName());
        }
    }

    /* access modifiers changed from: package-private */
    public void addWifiSection() {
        ((TemplatePreferenceCategory) inflatePreferences(C1715R.xml.data_usage_wifi)).setTemplate(NetworkTemplate.buildTemplateWifiWildcard(), 0, this.services);
    }

    private void addEthernetSection() {
        ((TemplatePreferenceCategory) inflatePreferences(C1715R.xml.data_usage_ethernet)).setTemplate(NetworkTemplate.buildTemplateEthernet(), 0, this.services);
    }

    private Preference inflatePreferences(int i) {
        PreferenceScreen inflateFromResource = getPreferenceManager().inflateFromResource(getPrefContext(), i, (PreferenceScreen) null);
        Preference preference = inflateFromResource.getPreference(0);
        inflateFromResource.removeAll();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preference.setOrder(preferenceScreen.getPreferenceCount());
        preferenceScreen.addPreference(preference);
        return preference;
    }

    public void onResume() {
        super.onResume();
        updateState();
    }

    static CharSequence formatUsage(Context context, String str, long j) {
        return formatUsage(context, str, j, 1.5625f, 0.64f);
    }

    static CharSequence formatUsage(Context context, String str, long j, float f, float f2) {
        Formatter.BytesResult formatBytes = Formatter.formatBytes(context.getResources(), j, 10);
        SpannableString spannableString = new SpannableString(formatBytes.value);
        spannableString.setSpan(new RelativeSizeSpan(f), 0, spannableString.length(), 18);
        CharSequence expandTemplate = TextUtils.expandTemplate(new SpannableString(context.getString(17040051).replace("%1$s", "^1").replace("%2$s", "^2")), new CharSequence[]{spannableString, formatBytes.units});
        SpannableString spannableString2 = new SpannableString(str);
        spannableString2.setSpan(new RelativeSizeSpan(f2), 0, spannableString2.length(), 18);
        return TextUtils.expandTemplate(spannableString2, new CharSequence[]{BidiFormatter.getInstance().unicodeWrap(expandTemplate.toString())});
    }

    private void updateState() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        for (int i = 1; i < preferenceScreen.getPreferenceCount(); i++) {
            Preference preference = preferenceScreen.getPreference(i);
            if (preference instanceof TemplatePreferenceCategory) {
                ((TemplatePreferenceCategory) preference).pushTemplates(this.services);
            }
        }
    }

    public NetworkPolicyEditor getNetworkPolicyEditor() {
        return this.services.mPolicyEditor;
    }

    public NetworkTemplate getNetworkTemplate() {
        return this.mDefaultTemplate;
    }

    public void updateDataUsage() {
        updateState();
        this.mSummaryController.updateState(this.mSummaryPreference);
    }

    private static class SummaryProvider implements SummaryLoader.SummaryProvider {
        private final Activity mActivity;
        private final DataUsageController mDataController;
        private final SummaryLoader mSummaryLoader;

        public SummaryProvider(Activity activity, SummaryLoader summaryLoader) {
            this.mActivity = activity;
            this.mSummaryLoader = summaryLoader;
            this.mDataController = new DataUsageController(activity);
        }

        public void setListening(boolean z) {
            if (!z) {
                return;
            }
            if (DataUsageUtils.hasSim(this.mActivity)) {
                this.mSummaryLoader.setSummary(this, this.mActivity.getString(C1715R.string.data_usage_summary_format, new Object[]{formatUsedData()}));
                return;
            }
            DataUsageController.DataUsageInfo wifiDataUsageInfo = this.mDataController.getWifiDataUsageInfo();
            if (wifiDataUsageInfo == null) {
                this.mSummaryLoader.setSummary(this, (CharSequence) null);
                return;
            }
            CharSequence text = this.mActivity.getText(C1715R.string.data_usage_wifi_format);
            CharSequence formatDataUsage = DataUsageUtils.formatDataUsage(this.mActivity, wifiDataUsageInfo.usageLevel);
            this.mSummaryLoader.setSummary(this, TextUtils.expandTemplate(text, new CharSequence[]{formatDataUsage}));
        }

        private CharSequence formatUsedData() {
            SubscriptionManager subscriptionManager = (SubscriptionManager) this.mActivity.getSystemService("telephony_subscription_service");
            int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
            if (defaultDataSubscriptionId == -1) {
                return formatFallbackData();
            }
            SubscriptionPlan primaryPlan = DataUsageSummaryPreferenceController.getPrimaryPlan(subscriptionManager, defaultDataSubscriptionId);
            if (primaryPlan == null) {
                return formatFallbackData();
            }
            if (DataUsageSummaryPreferenceController.unlimited(primaryPlan.getDataLimitBytes())) {
                return DataUsageUtils.formatDataUsage(this.mActivity, primaryPlan.getDataUsageBytes());
            }
            return Utils.formatPercentage(primaryPlan.getDataUsageBytes(), primaryPlan.getDataLimitBytes());
        }

        private CharSequence formatFallbackData() {
            DataUsageController.DataUsageInfo dataUsageInfo;
            if (DataUsageSummary.showDailyDataUsage) {
                dataUsageInfo = this.mDataController.getDailyDataUsageInfo();
            } else {
                dataUsageInfo = this.mDataController.getDataUsageInfo();
            }
            if (dataUsageInfo == null) {
                return DataUsageUtils.formatDataUsage(this.mActivity, 0);
            }
            long j = dataUsageInfo.limitLevel;
            if (j <= 0) {
                return DataUsageUtils.formatDataUsage(this.mActivity, dataUsageInfo.usageLevel);
            }
            return Utils.formatPercentage(dataUsageInfo.usageLevel, j);
        }
    }
}
