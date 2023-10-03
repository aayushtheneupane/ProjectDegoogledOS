package com.android.settings.fuelgauge;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.preference.Preference;
import com.android.settings.SettingsActivity;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.fuelgauge.batterytip.BatteryTipLoader;
import com.android.settings.fuelgauge.batterytip.BatteryTipPreferenceController;
import com.android.settings.fuelgauge.batterytip.tips.BatteryTip;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settingslib.Utils;
import com.android.settingslib.utils.PowerUtil;
import com.android.settingslib.utils.StringUtil;
import com.android.settingslib.widget.LayoutPreference;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SystemSettingMasterSwitchPreference;
import java.util.Collections;
import java.util.List;

public class PowerUsageSummary extends PowerUsageBase implements View.OnLongClickListener, BatteryTipPreferenceController.BatteryTipListener, Preference.OnPreferenceChangeListener {
    static final String ARG_BATTERY_LEVEL = "key_battery_level";
    static final int BATTERY_INFO_LOADER = 1;
    static final int BATTERY_TIP_LOADER = 2;
    static final int MENU_ADVANCED_BATTERY = 2;
    static final int MENU_STATS_TYPE = 1;
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.power_usage_summary;
            return Collections.singletonList(searchIndexableResource);
        }
    };
    BatteryInfo mBatteryInfo;
    LoaderManager.LoaderCallbacks<List<BatteryInfo>> mBatteryInfoDebugLoaderCallbacks = new LoaderManager.LoaderCallbacks<List<BatteryInfo>>() {
        public void onLoaderReset(Loader<List<BatteryInfo>> loader) {
        }

        public Loader<List<BatteryInfo>> onCreateLoader(int i, Bundle bundle) {
            return new DebugEstimatesLoader(PowerUsageSummary.this.getContext(), PowerUsageSummary.this.mStatsHelper);
        }

        public void onLoadFinished(Loader<List<BatteryInfo>> loader, List<BatteryInfo> list) {
            PowerUsageSummary.this.updateViews(list);
        }
    };
    LoaderManager.LoaderCallbacks<BatteryInfo> mBatteryInfoLoaderCallbacks = new LoaderManager.LoaderCallbacks<BatteryInfo>() {
        public void onLoaderReset(Loader<BatteryInfo> loader) {
        }

        public Loader<BatteryInfo> onCreateLoader(int i, Bundle bundle) {
            return new BatteryInfoLoader(PowerUsageSummary.this.getContext(), PowerUsageSummary.this.mStatsHelper);
        }

        public void onLoadFinished(Loader<BatteryInfo> loader, BatteryInfo batteryInfo) {
            PowerUsageSummary.this.updateHeaderPreference(batteryInfo);
            PowerUsageSummary powerUsageSummary = PowerUsageSummary.this;
            powerUsageSummary.mBatteryInfo = batteryInfo;
            powerUsageSummary.updateLastFullChargePreference();
        }
    };
    LayoutPreference mBatteryLayoutPref;
    int mBatteryLevel;
    PowerGaugePreference mBatteryTemp;
    BatteryTipPreferenceController mBatteryTipPreferenceController;
    private LoaderManager.LoaderCallbacks<List<BatteryTip>> mBatteryTipsCallbacks = new LoaderManager.LoaderCallbacks<List<BatteryTip>>() {
        public void onLoaderReset(Loader<List<BatteryTip>> loader) {
        }

        public Loader<List<BatteryTip>> onCreateLoader(int i, Bundle bundle) {
            return new BatteryTipLoader(PowerUsageSummary.this.getContext(), PowerUsageSummary.this.mStatsHelper);
        }

        public void onLoadFinished(Loader<List<BatteryTip>> loader, List<BatteryTip> list) {
            PowerUsageSummary.this.mBatteryTipPreferenceController.updateBatteryTips(list);
        }
    };
    BatteryUtils mBatteryUtils;
    BatteryMeterView mBatteryView;
    PowerGaugePreference mLastFullChargePref;
    boolean mNeedUpdateBatteryTip;
    PowerUsageFeatureProvider mPowerFeatureProvider;
    PowerManager mPowerManager;
    PowerGaugePreference mScreenUsagePref;
    final ContentObserver mSettingsObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean z, Uri uri) {
            PowerUsageSummary.this.restartBatteryInfoLoader();
        }
    };
    private SystemSettingMasterSwitchPreference mSmartCharging;
    private int mStatsType = 0;

    public int getHelpResource() {
        return C1715R.string.help_url_battery;
    }

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "PowerUsageSummary";
    }

    public int getMetricsCategory() {
        return 1263;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.power_usage_summary;
    }

    /* access modifiers changed from: protected */
    public void updateViews(List<BatteryInfo> list) {
        BatteryInfo batteryInfo = list.get(0);
        ((TextView) this.mBatteryLayoutPref.findViewById(C1715R.C1718id.battery_percent)).setText(Utils.formatPercentage(batteryInfo.batteryLevel));
        String oldEstimateDebugString = this.mPowerFeatureProvider.getOldEstimateDebugString(Formatter.formatShortElapsedTime(getContext(), PowerUtil.convertUsToMs(batteryInfo.remainingTimeUs)));
        String enhancedEstimateDebugString = this.mPowerFeatureProvider.getEnhancedEstimateDebugString(Formatter.formatShortElapsedTime(getContext(), PowerUtil.convertUsToMs(list.get(1).remainingTimeUs)));
        ((TextView) this.mBatteryLayoutPref.findViewById(C1715R.C1718id.summary1)).setText(oldEstimateDebugString + "\n" + enhancedEstimateDebugString);
        this.mBatteryView.setBatteryLevel(batteryInfo.batteryLevel);
        this.mBatteryView.setCharging(batteryInfo.discharging ^ true);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mPowerManager = (PowerManager) context.getSystemService(PowerManager.class);
        this.mBatteryTipPreferenceController = (BatteryTipPreferenceController) use(BatteryTipPreferenceController.class);
        this.mBatteryTipPreferenceController.setActivity((SettingsActivity) getActivity());
        this.mBatteryTipPreferenceController.setFragment(this);
        this.mBatteryTipPreferenceController.setBatteryTipListener(new BatteryTipPreferenceController.BatteryTipListener() {
            public final void onBatteryTipHandled(BatteryTip batteryTip) {
                PowerUsageSummary.this.onBatteryTipHandled(batteryTip);
            }
        });
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setAnimationAllowed(true);
        initFeatureProvider();
        this.mBatteryLayoutPref = (LayoutPreference) findPreference("battery_header");
        this.mBatteryView = (BatteryMeterView) this.mBatteryLayoutPref.findViewById(C1715R.C1718id.battery_header_icon);
        this.mBatteryView.setDrawableStyle();
        this.mBatteryLevel = getContext().getResources().getInteger(17694766) + 1;
        this.mScreenUsagePref = (PowerGaugePreference) findPreference("screen_usage");
        this.mLastFullChargePref = (PowerGaugePreference) findPreference("last_full_charge");
        this.mBatteryTemp = (PowerGaugePreference) findPreference("battery_temp");
        this.mFooterPreferenceMixin.createFooterPreference().setTitle((int) C1715R.string.battery_footer_summary);
        this.mBatteryUtils = BatteryUtils.getInstance(getContext());
        restartBatteryInfoLoader();
        this.mBatteryTipPreferenceController.restoreInstanceState(bundle);
        updateBatteryTipFlag(bundle);
        updateMasterPrefs();
    }

    private void updateMasterPrefs() {
        this.mSmartCharging = (SystemSettingMasterSwitchPreference) findPreference("smart_charging");
        SystemSettingMasterSwitchPreference systemSettingMasterSwitchPreference = this.mSmartCharging;
        boolean z = true;
        if (Settings.System.getInt(getActivity().getContentResolver(), "smart_charging", 0) != 1) {
            z = false;
        }
        systemSettingMasterSwitchPreference.setChecked(z);
        this.mSmartCharging.setOnPreferenceChangeListener(this);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference != this.mSmartCharging) {
            return false;
        }
        Settings.System.putInt(getActivity().getContentResolver(), "smart_charging", ((Boolean) obj).booleanValue() ? 1 : 0);
        return true;
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        if (!"battery_header".equals(preference.getKey())) {
            return super.onPreferenceTreeClick(preference);
        }
        new SubSettingLauncher(getContext()).setDestination(PowerUsageAdvanced.class.getName()).setSourceMetricsCategory(getMetricsCategory()).setTitleRes(C1715R.string.advanced_battery_title).launch();
        return true;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.mBatteryLevel = bundle.getInt(ARG_BATTERY_LEVEL);
        }
    }

    public void onResume() {
        super.onResume();
        initHeaderPreference();
        getContentResolver().registerContentObserver(Settings.Global.getUriFor("battery_estimates_last_update_time"), false, this.mSettingsObserver);
        this.mBatteryView.setDrawableStyle();
        updateMasterPrefs();
    }

    public void onPause() {
        getContentResolver().unregisterContentObserver(this.mSettingsObserver);
        super.onPause();
        updateMasterPrefs();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.add(0, 3, 0, C1715R.string.battery_stats_reset).setIcon(C1715R.C1717drawable.ic_delete).setAlphabeticShortcut('d').setShowAsAction(1);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    private void resetStats() {
        new AlertDialog.Builder(getActivity()).setTitle(C1715R.string.battery_stats_reset).setMessage(C1715R.string.battery_stats_message).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                PowerUsageSummary.this.mStatsHelper.resetStatistics();
                PowerUsageSummary.this.refreshUi(0);
            }
        }).setNegativeButton(C1715R.string.cancel, (DialogInterface.OnClickListener) null).create().show();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            if (this.mStatsType == 0) {
                this.mStatsType = 2;
            } else {
                this.mStatsType = 0;
            }
            refreshUi(0);
            return true;
        } else if (itemId == 2) {
            new SubSettingLauncher(getContext()).setDestination(PowerUsageAdvanced.class.getName()).setSourceMetricsCategory(getMetricsCategory()).setTitleRes(C1715R.string.advanced_battery_title).launch();
            return true;
        } else if (itemId != 3) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            resetStats();
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void refreshUi(int i) {
        String str;
        Context context = getContext();
        if (context != null) {
            if (!this.mNeedUpdateBatteryTip || i == 1) {
                this.mNeedUpdateBatteryTip = true;
            } else {
                restartBatteryTipLoader();
            }
            restartBatteryInfoLoader();
            updateLastFullChargePreference();
            this.mScreenUsagePref.setSummary(StringUtil.formatElapsedTime(getContext(), (double) this.mBatteryUtils.calculateScreenUsageTime(this.mStatsHelper), false));
            PowerGaugePreference powerGaugePreference = this.mBatteryTemp;
            if (com.android.internal.util.havoc.Utils.mccCheck(getContext())) {
                str = com.android.internal.util.havoc.Utils.batteryTemperature(getContext(), true) + "°F";
            } else {
                str = com.android.internal.util.havoc.Utils.batteryTemperature(getContext(), false) + "°C";
            }
            powerGaugePreference.setSummary((CharSequence) str);
            updateHeaderPreference(BatteryInfo.getBatteryInfoOld(context, context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED")), this.mStatsHelper.getStats(), 1000 * SystemClock.elapsedRealtime(), false));
        }
    }

    /* access modifiers changed from: package-private */
    public void restartBatteryTipLoader() {
        getLoaderManager().restartLoader(2, Bundle.EMPTY, this.mBatteryTipsCallbacks);
    }

    /* access modifiers changed from: package-private */
    public void setBatteryLayoutPreference(LayoutPreference layoutPreference) {
        this.mBatteryLayoutPref = layoutPreference;
    }

    /* access modifiers changed from: package-private */
    public void updateLastFullChargePreference() {
        BatteryInfo batteryInfo = this.mBatteryInfo;
        if (batteryInfo == null || batteryInfo.averageTimeToDischarge == -1) {
            long calculateLastFullChargeTime = this.mBatteryUtils.calculateLastFullChargeTime(this.mStatsHelper, System.currentTimeMillis());
            this.mLastFullChargePref.setTitle((int) C1715R.string.battery_last_full_charge);
            this.mLastFullChargePref.setSummary(StringUtil.formatRelativeTime(getContext(), (double) calculateLastFullChargeTime, false));
            return;
        }
        this.mLastFullChargePref.setTitle((int) C1715R.string.battery_full_charge_last);
        this.mLastFullChargePref.setSummary(StringUtil.formatElapsedTime(getContext(), (double) this.mBatteryInfo.averageTimeToDischarge, false));
    }

    /* access modifiers changed from: package-private */
    public void showBothEstimates() {
        Context context = getContext();
        if (context != null && this.mPowerFeatureProvider.isEnhancedBatteryPredictionEnabled(context)) {
            getLoaderManager().restartLoader(3, Bundle.EMPTY, this.mBatteryInfoDebugLoaderCallbacks);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateHeaderPreference(BatteryInfo batteryInfo) {
        if (getContext() != null) {
            BatteryMeterView batteryMeterView = (BatteryMeterView) this.mBatteryLayoutPref.findViewById(C1715R.C1718id.battery_header_icon);
            TextView textView = (TextView) this.mBatteryLayoutPref.findViewById(C1715R.C1718id.battery_percent);
            TextView textView2 = (TextView) this.mBatteryLayoutPref.findViewById(C1715R.C1718id.summary1);
            CharSequence charSequence = batteryInfo.remainingLabel;
            if (charSequence == null) {
                textView2.setText(batteryInfo.statusLabel);
            } else {
                textView2.setText(charSequence);
            }
            batteryMeterView.setCharging(!batteryInfo.discharging);
            batteryMeterView.setPowerSave(this.mPowerManager.isPowerSaveMode());
            startBatteryHeaderAnimationIfNecessary(batteryMeterView, textView, this.mBatteryLevel, batteryInfo.batteryLevel);
        }
    }

    /* access modifiers changed from: package-private */
    public void initHeaderPreference() {
        if (getContext() != null) {
            BatteryMeterView batteryMeterView = (BatteryMeterView) this.mBatteryLayoutPref.findViewById(C1715R.C1718id.battery_header_icon);
            batteryMeterView.setBatteryLevel(this.mBatteryLevel);
            batteryMeterView.setPowerSave(this.mPowerManager.isPowerSaveMode());
            ((TextView) this.mBatteryLayoutPref.findViewById(C1715R.C1718id.battery_percent)).setText(formatBatteryPercentageText(this.mBatteryLevel));
        }
    }

    /* access modifiers changed from: package-private */
    public void startBatteryHeaderAnimationIfNecessary(final BatteryMeterView batteryMeterView, final TextView textView, int i, int i2) {
        if (getContext() != null) {
            this.mBatteryLevel = i2;
            int abs = Math.abs(i - i2);
            if (abs != 0) {
                ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i, i2});
                ofInt.setDuration((long) (abs * 30));
                ofInt.setInterpolator(AnimationUtils.loadInterpolator(getContext(), 17563661));
                ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        Integer num = (Integer) valueAnimator.getAnimatedValue();
                        batteryMeterView.setBatteryLevel(num.intValue());
                        batteryMeterView.setPowerSave(PowerUsageSummary.this.mPowerManager.isPowerSaveMode());
                        textView.setText(PowerUsageSummary.this.formatBatteryPercentageText(num.intValue()));
                    }
                });
                ofInt.start();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void initFeatureProvider() {
        Context context = getContext();
        this.mPowerFeatureProvider = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context);
    }

    /* access modifiers changed from: package-private */
    public void restartBatteryInfoLoader() {
        if (getContext() != null) {
            getLoaderManager().restartLoader(1, Bundle.EMPTY, this.mBatteryInfoLoaderCallbacks);
            if (this.mPowerFeatureProvider.isEstimateDebugEnabled()) {
                this.mBatteryLayoutPref.findViewById(C1715R.C1718id.summary1).setOnLongClickListener(this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void updateBatteryTipFlag(Bundle bundle) {
        this.mNeedUpdateBatteryTip = bundle == null || this.mBatteryTipPreferenceController.needUpdate();
    }

    public boolean onLongClick(View view) {
        showBothEstimates();
        view.setOnLongClickListener((View.OnLongClickListener) null);
        return true;
    }

    /* access modifiers changed from: protected */
    public void restartBatteryStatsLoader(int i) {
        super.lambda$onCreate$0$PowerUsageBase(i);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mBatteryTipPreferenceController.saveInstanceState(bundle);
    }

    public void onBatteryTipHandled(BatteryTip batteryTip) {
        restartBatteryTipLoader();
    }

    /* access modifiers changed from: private */
    public CharSequence formatBatteryPercentageText(int i) {
        try {
            return TextUtils.expandTemplate(getContext().getText(C1715R.string.battery_header_title_alternate), new CharSequence[]{NumberFormat.getIntegerInstance().format((long) i)});
        } catch (Exception unused) {
            return null;
        }
    }
}
