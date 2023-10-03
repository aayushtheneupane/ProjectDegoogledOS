package com.android.settings.fuelgauge;

import android.app.Activity;
import android.os.Bundle;
import android.os.UserManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.android.internal.os.BatteryStatsHelper;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.fuelgauge.BatteryBroadcastReceiver;

public abstract class PowerUsageBase extends DashboardFragment {
    static final int MENU_STATS_REFRESH = 2;
    private BatteryBroadcastReceiver mBatteryBroadcastReceiver;
    protected BatteryStatsHelper mStatsHelper;
    protected UserManager mUm;

    /* access modifiers changed from: protected */
    public abstract void refreshUi(int i);

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mUm = (UserManager) activity.getSystemService("user");
        this.mStatsHelper = new BatteryStatsHelper(activity, true);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mStatsHelper.create(bundle);
        setHasOptionsMenu(true);
        this.mBatteryBroadcastReceiver = new BatteryBroadcastReceiver(getContext());
        this.mBatteryBroadcastReceiver.setBatteryChangedListener(new BatteryBroadcastReceiver.OnBatteryChangedListener() {
            public final void onBatteryChanged(int i) {
                PowerUsageBase.this.lambda$onCreate$0$PowerUsageBase(i);
            }
        });
    }

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
        this.mBatteryBroadcastReceiver.register();
    }

    public void onPause() {
        super.onPause();
        this.mBatteryBroadcastReceiver.unRegister();
    }

    /* access modifiers changed from: protected */
    /* renamed from: restartBatteryStatsLoader */
    public void lambda$onCreate$0$PowerUsageBase(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("refresh_type", i);
        getLoaderManager().restartLoader(0, bundle, new PowerLoaderCallback());
    }

    /* access modifiers changed from: protected */
    public void updatePreference(BatteryHistoryPreference batteryHistoryPreference) {
        long currentTimeMillis = System.currentTimeMillis();
        batteryHistoryPreference.setStats(this.mStatsHelper);
        BatteryUtils.logRuntime("PowerUsageBase", "updatePreference", currentTimeMillis);
    }

    public class PowerLoaderCallback implements LoaderManager.LoaderCallbacks<BatteryStatsHelper> {
        private int mRefreshType;

        public void onLoaderReset(Loader<BatteryStatsHelper> loader) {
        }

        public PowerLoaderCallback() {
        }

        public Loader<BatteryStatsHelper> onCreateLoader(int i, Bundle bundle) {
            this.mRefreshType = bundle.getInt("refresh_type");
            return new BatteryStatsHelperLoader(PowerUsageBase.this.getContext());
        }

        public void onLoadFinished(Loader<BatteryStatsHelper> loader, BatteryStatsHelper batteryStatsHelper) {
            PowerUsageBase powerUsageBase = PowerUsageBase.this;
            powerUsageBase.mStatsHelper = batteryStatsHelper;
            powerUsageBase.refreshUi(this.mRefreshType);
        }
    }
}
