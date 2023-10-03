package com.android.settings.network.telephony.gsm;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.telephony.CarrierConfigManager;
import android.telephony.TelephonyManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.network.telephony.MobileNetworkUtils;
import com.android.settings.network.telephony.NetworkSelectSettings;
import com.android.settings.network.telephony.TelephonyTogglePreferenceController;
import com.android.settingslib.utils.ThreadUtils;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AutoSelectPreferenceController extends TelephonyTogglePreferenceController {
    private static final long MINIMUM_DIALOG_TIME_MILLIS = TimeUnit.SECONDS.toMillis(1);
    private List<OnNetworkSelectModeListener> mListeners = new ArrayList();
    private boolean mOnlyAutoSelectInHome;
    ProgressDialog mProgressDialog;
    SwitchPreference mSwitchPreference;
    private TelephonyManager mTelephonyManager;
    private final Handler mUiHandler = new Handler(Looper.getMainLooper());

    public interface OnNetworkSelectModeListener {
        void onNetworkSelectModeChanged();
    }

    public AutoSelectPreferenceController(Context context, String str) {
        super(context, str);
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        this.mSubId = -1;
    }

    public int getAvailabilityStatus(int i) {
        return MobileNetworkUtils.shouldDisplayNetworkSelectOptions(this.mContext, i) ? 0 : 2;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mSwitchPreference = (SwitchPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    public boolean isChecked() {
        return this.mTelephonyManager.getNetworkSelectionMode() == 1;
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        preference.setSummary((CharSequence) null);
        if (this.mTelephonyManager.getServiceState().getRoaming()) {
            preference.setEnabled(true);
            return;
        }
        preference.setEnabled(!this.mOnlyAutoSelectInHome);
        if (this.mOnlyAutoSelectInHome) {
            preference.setSummary((CharSequence) this.mContext.getString(C1715R.string.manual_mode_disallowed_summary, new Object[]{this.mTelephonyManager.getSimOperatorName()}));
        }
    }

    public boolean setChecked(boolean z) {
        if (z) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            showAutoSelectProgressBar();
            this.mSwitchPreference.setEnabled(false);
            ThreadUtils.postOnBackgroundThread((Runnable) new Runnable(elapsedRealtime) {
                private final /* synthetic */ long f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    AutoSelectPreferenceController.this.lambda$setChecked$1$AutoSelectPreferenceController(this.f$1);
                }
            });
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("android.provider.extra.SUB_ID", this.mSubId);
        new SubSettingLauncher(this.mContext).setDestination(NetworkSelectSettings.class.getName()).setSourceMetricsCategory(1581).setTitleRes(C1715R.string.choose_network_title).setArguments(bundle).launch();
        return false;
    }

    public /* synthetic */ void lambda$setChecked$1$AutoSelectPreferenceController(long j) {
        this.mTelephonyManager.setNetworkSelectionModeAutomatic();
        this.mUiHandler.postDelayed(new Runnable(this.mTelephonyManager.getNetworkSelectionMode()) {
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                AutoSelectPreferenceController.this.lambda$setChecked$0$AutoSelectPreferenceController(this.f$1);
            }
        }, Math.max(MINIMUM_DIALOG_TIME_MILLIS - (SystemClock.elapsedRealtime() - j), 0));
    }

    public /* synthetic */ void lambda$setChecked$0$AutoSelectPreferenceController(int i) {
        boolean z = true;
        this.mSwitchPreference.setEnabled(true);
        SwitchPreference switchPreference = this.mSwitchPreference;
        if (i != 1) {
            z = false;
        }
        switchPreference.setChecked(z);
        for (OnNetworkSelectModeListener onNetworkSelectModeChanged : this.mListeners) {
            onNetworkSelectModeChanged.onNetworkSelectModeChanged();
        }
        dismissProgressBar();
    }

    public AutoSelectPreferenceController init(int i) {
        this.mSubId = i;
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
        PersistableBundle configForSubId = ((CarrierConfigManager) this.mContext.getSystemService(CarrierConfigManager.class)).getConfigForSubId(this.mSubId);
        this.mOnlyAutoSelectInHome = configForSubId != null ? configForSubId.getBoolean("only_auto_select_in_home_network") : false;
        return this;
    }

    public AutoSelectPreferenceController addListener(OnNetworkSelectModeListener onNetworkSelectModeListener) {
        this.mListeners.add(onNetworkSelectModeListener);
        return this;
    }

    private void showAutoSelectProgressBar() {
        if (this.mProgressDialog == null) {
            this.mProgressDialog = new ProgressDialog(this.mContext);
            this.mProgressDialog.setMessage(this.mContext.getResources().getString(C1715R.string.register_automatically));
            this.mProgressDialog.setCanceledOnTouchOutside(false);
            this.mProgressDialog.setCancelable(false);
            this.mProgressDialog.setIndeterminate(true);
        }
        this.mProgressDialog.show();
    }

    private void dismissProgressBar() {
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.mProgressDialog.dismiss();
        }
    }
}
