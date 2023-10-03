package com.android.settings.datausage;

import android.app.Activity;
import android.content.Intent;
import android.net.NetworkPolicyManager;
import android.net.NetworkTemplate;
import android.provider.Settings;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.SubscriptionPlan;
import android.text.TextUtils;
import android.util.Log;
import android.util.RecurrenceRule;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.android.internal.util.CollectionUtils;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.widget.EntityHeaderController;
import com.android.settingslib.NetworkPolicyEditor;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.net.DataUsageController;
import com.havoc.config.center.C1715R;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Predicate;

public class DataUsageSummaryPreferenceController extends BasePreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnStart {
    private static final String KEY = "status_header";
    private static final long PETA = 1000000000000000L;
    private static final float RELATIVE_SIZE_LARGE = 1.5625f;
    private static final float RELATIVE_SIZE_SMALL = 0.64f;
    private static final String TAG = "DataUsageController";
    private final Activity mActivity;
    private CharSequence mCarrierName;
    private long mCycleEnd;
    private long mCycleStart;
    private long mDataBarSize;
    protected final DataUsageInfoController mDataInfoController;
    protected final DataUsageController mDataUsageController;
    private final int mDataUsageTemplate;
    private int mDataplanCount;
    private long mDataplanSize;
    private long mDataplanUse;
    private final NetworkTemplate mDefaultTemplate;
    private final EntityHeaderController mEntityHeaderController;
    private final PreferenceFragmentCompat mFragment;
    private final boolean mHasMobileData;
    private final Lifecycle mLifecycle;
    private Intent mManageSubscriptionIntent;
    protected final NetworkPolicyEditor mPolicyEditor;
    private long mSnapshotTime;
    private int mSubscriptionId;
    private final SubscriptionManager mSubscriptionManager;

    private static boolean saneSize(long j) {
        return j >= 0 && j < PETA;
    }

    public static boolean unlimited(long j) {
        return j == Long.MAX_VALUE;
    }

    public DataUsageSummaryPreferenceController(Activity activity, Lifecycle lifecycle, PreferenceFragmentCompat preferenceFragmentCompat, int i) {
        super(activity, KEY);
        this.mActivity = activity;
        this.mEntityHeaderController = EntityHeaderController.newInstance(activity, preferenceFragmentCompat, (View) null);
        this.mLifecycle = lifecycle;
        this.mFragment = preferenceFragmentCompat;
        this.mSubscriptionId = i;
        this.mDefaultTemplate = DataUsageUtils.getDefaultTemplate(activity, this.mSubscriptionId);
        this.mPolicyEditor = new NetworkPolicyEditor((NetworkPolicyManager) activity.getSystemService(NetworkPolicyManager.class));
        this.mHasMobileData = DataUsageUtils.hasMobileData(activity) && this.mSubscriptionId != -1;
        this.mDataUsageController = new DataUsageController(activity);
        this.mDataUsageController.setSubscriptionId(this.mSubscriptionId);
        this.mDataInfoController = new DataUsageInfoController();
        if (this.mHasMobileData) {
            this.mDataUsageTemplate = C1715R.string.cell_data_template;
        } else if (DataUsageUtils.hasWifiRadio(activity)) {
            this.mDataUsageTemplate = C1715R.string.wifi_data_template;
        } else {
            this.mDataUsageTemplate = C1715R.string.ethernet_data_template;
        }
        this.mSubscriptionManager = (SubscriptionManager) this.mContext.getSystemService("telephony_subscription_service");
    }

    DataUsageSummaryPreferenceController(DataUsageController dataUsageController, DataUsageInfoController dataUsageInfoController, NetworkTemplate networkTemplate, NetworkPolicyEditor networkPolicyEditor, int i, boolean z, SubscriptionManager subscriptionManager, Activity activity, Lifecycle lifecycle, EntityHeaderController entityHeaderController, PreferenceFragmentCompat preferenceFragmentCompat, int i2) {
        super(activity, KEY);
        this.mDataUsageController = dataUsageController;
        this.mDataInfoController = dataUsageInfoController;
        this.mDefaultTemplate = networkTemplate;
        this.mPolicyEditor = networkPolicyEditor;
        this.mDataUsageTemplate = i;
        this.mHasMobileData = z;
        this.mSubscriptionManager = subscriptionManager;
        this.mActivity = activity;
        this.mLifecycle = lifecycle;
        this.mEntityHeaderController = entityHeaderController;
        this.mFragment = preferenceFragmentCompat;
        this.mSubscriptionId = i2;
    }

    public void onStart() {
        this.mEntityHeaderController.setRecyclerView(this.mFragment.getListView(), this.mLifecycle);
        this.mEntityHeaderController.styleActionBar(this.mActivity);
    }

    /* access modifiers changed from: package-private */
    public void setPlanValues(int i, long j, long j2) {
        this.mDataplanCount = i;
        this.mDataplanSize = j;
        this.mDataBarSize = j;
        this.mDataplanUse = j2;
    }

    /* access modifiers changed from: package-private */
    public void setCarrierValues(String str, long j, long j2, Intent intent) {
        this.mCarrierName = str;
        this.mSnapshotTime = j;
        this.mCycleEnd = j2;
        this.mManageSubscriptionIntent = intent;
    }

    public int getAvailabilityStatus() {
        return (DataUsageUtils.hasSim(this.mActivity) || DataUsageUtils.hasWifiRadio(this.mContext)) ? 0 : 2;
    }

    public void updateState(Preference preference) {
        DataUsageController.DataUsageInfo dataUsageInfo;
        DataUsageSummaryPreference dataUsageSummaryPreference = (DataUsageSummaryPreference) preference;
        if (DataUsageUtils.hasSim(this.mActivity)) {
            if (Settings.System.getInt(this.mContext.getContentResolver(), "data_usage_period", 1) == 0) {
                dataUsageInfo = this.mDataUsageController.getDailyDataUsageInfo(this.mDefaultTemplate);
            } else {
                dataUsageInfo = this.mDataUsageController.getDataUsageInfo(this.mDefaultTemplate);
            }
            this.mDataInfoController.updateDataLimit(dataUsageInfo, this.mPolicyEditor.getPolicy(this.mDefaultTemplate));
            dataUsageSummaryPreference.setWifiMode(false, (String) null, false);
            if (this.mSubscriptionManager != null) {
                refreshDataplanInfo(dataUsageInfo);
            }
            if (dataUsageInfo.warningLevel > 0 && dataUsageInfo.limitLevel > 0) {
                dataUsageSummaryPreference.setLimitInfo(TextUtils.expandTemplate(this.mContext.getText(C1715R.string.cell_data_warning_and_limit), new CharSequence[]{DataUsageUtils.formatDataUsage(this.mContext, dataUsageInfo.warningLevel), DataUsageUtils.formatDataUsage(this.mContext, dataUsageInfo.limitLevel)}));
            } else if (dataUsageInfo.warningLevel > 0) {
                dataUsageSummaryPreference.setLimitInfo(TextUtils.expandTemplate(this.mContext.getText(C1715R.string.cell_data_warning), new CharSequence[]{DataUsageUtils.formatDataUsage(this.mContext, dataUsageInfo.warningLevel)}));
            } else if (dataUsageInfo.limitLevel > 0) {
                dataUsageSummaryPreference.setLimitInfo(TextUtils.expandTemplate(this.mContext.getText(C1715R.string.cell_data_limit), new CharSequence[]{DataUsageUtils.formatDataUsage(this.mContext, dataUsageInfo.limitLevel)}));
            } else {
                dataUsageSummaryPreference.setLimitInfo((CharSequence) null);
            }
            dataUsageSummaryPreference.setUsageNumbers(this.mDataplanUse, this.mDataplanSize, this.mHasMobileData);
            if (this.mDataBarSize <= 0) {
                dataUsageSummaryPreference.setChartEnabled(false);
            } else {
                dataUsageSummaryPreference.setChartEnabled(true);
                dataUsageSummaryPreference.setLabels(DataUsageUtils.formatDataUsage(this.mContext, 0), DataUsageUtils.formatDataUsage(this.mContext, this.mDataBarSize));
                dataUsageSummaryPreference.setProgress(((float) this.mDataplanUse) / ((float) this.mDataBarSize));
            }
            dataUsageSummaryPreference.setUsageInfo(this.mCycleEnd, this.mSnapshotTime, this.mCarrierName, this.mDataplanCount, this.mManageSubscriptionIntent);
            return;
        }
        DataUsageController.DataUsageInfo dataUsageInfo2 = this.mDataUsageController.getDataUsageInfo(NetworkTemplate.buildTemplateWifiWildcard());
        dataUsageSummaryPreference.setWifiMode(true, dataUsageInfo2.period, false);
        dataUsageSummaryPreference.setLimitInfo((CharSequence) null);
        DataUsageSummaryPreference dataUsageSummaryPreference2 = dataUsageSummaryPreference;
        dataUsageSummaryPreference2.setUsageNumbers(dataUsageInfo2.usageLevel, -1, true);
        dataUsageSummaryPreference.setChartEnabled(false);
        dataUsageSummaryPreference2.setUsageInfo(dataUsageInfo2.cycleEnd, -1, (CharSequence) null, 0, (Intent) null);
    }

    private void refreshDataplanInfo(DataUsageController.DataUsageInfo dataUsageInfo) {
        ZonedDateTime zonedDateTime;
        this.mCarrierName = null;
        this.mDataplanCount = 0;
        this.mDataplanSize = -1;
        this.mDataBarSize = this.mDataInfoController.getSummaryLimit(dataUsageInfo);
        this.mDataplanUse = dataUsageInfo.usageLevel;
        this.mCycleStart = dataUsageInfo.cycleStart;
        this.mCycleEnd = dataUsageInfo.cycleEnd;
        this.mSnapshotTime = -1;
        SubscriptionInfo activeSubscriptionInfo = this.mSubscriptionManager.getActiveSubscriptionInfo(this.mSubscriptionId);
        if (activeSubscriptionInfo == null) {
            activeSubscriptionInfo = (SubscriptionInfo) this.mSubscriptionManager.getAllSubscriptionInfoList().stream().filter(new Predicate() {
                public final boolean test(Object obj) {
                    return DataUsageSummaryPreferenceController.this.mo9186x9071561f((SubscriptionInfo) obj);
                }
            }).findFirst().orElse((Object) null);
        }
        if (activeSubscriptionInfo != null && this.mHasMobileData) {
            this.mCarrierName = activeSubscriptionInfo.getCarrierName();
            List<SubscriptionPlan> subscriptionPlans = this.mSubscriptionManager.getSubscriptionPlans(this.mSubscriptionId);
            SubscriptionPlan primaryPlan = getPrimaryPlan(this.mSubscriptionManager, this.mSubscriptionId);
            if (primaryPlan != null) {
                this.mDataplanCount = subscriptionPlans.size();
                this.mDataplanSize = primaryPlan.getDataLimitBytes();
                if (unlimited(this.mDataplanSize)) {
                    this.mDataplanSize = -1;
                }
                this.mDataBarSize = this.mDataplanSize;
                this.mDataplanUse = primaryPlan.getDataUsageBytes();
                RecurrenceRule cycleRule = primaryPlan.getCycleRule();
                if (!(cycleRule == null || (zonedDateTime = cycleRule.start) == null || cycleRule.end == null)) {
                    this.mCycleStart = zonedDateTime.toEpochSecond() * 1000;
                    this.mCycleEnd = cycleRule.end.toEpochSecond() * 1000;
                }
                this.mSnapshotTime = primaryPlan.getDataUsageTime();
            }
        }
        this.mManageSubscriptionIntent = this.mSubscriptionManager.createManageSubscriptionIntent(this.mSubscriptionId);
        Log.i(TAG, "Have " + this.mDataplanCount + " plans, dflt sub-id " + this.mSubscriptionId + ", intent " + this.mManageSubscriptionIntent);
    }

    /* renamed from: lambda$refreshDataplanInfo$0$DataUsageSummaryPreferenceController */
    public /* synthetic */ boolean mo9186x9071561f(SubscriptionInfo subscriptionInfo) {
        return subscriptionInfo.getSubscriptionId() == this.mSubscriptionId;
    }

    public static SubscriptionPlan getPrimaryPlan(SubscriptionManager subscriptionManager, int i) {
        List<SubscriptionPlan> subscriptionPlans = subscriptionManager.getSubscriptionPlans(i);
        if (CollectionUtils.isEmpty(subscriptionPlans)) {
            return null;
        }
        SubscriptionPlan subscriptionPlan = subscriptionPlans.get(0);
        if (subscriptionPlan.getDataLimitBytes() <= 0 || !saneSize(subscriptionPlan.getDataUsageBytes()) || subscriptionPlan.getCycleRule() == null) {
            return null;
        }
        return subscriptionPlan;
    }
}
