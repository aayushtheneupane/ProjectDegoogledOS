package com.android.settings.network.telephony.cdma;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;
import com.android.settings.network.telephony.MobileNetworkUtils;
import com.android.settings.network.telephony.TelephonyBasePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

public abstract class CdmaBasePreferenceController extends TelephonyBasePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private DataContentObserver mDataContentObserver = new DataContentObserver(new Handler(Looper.getMainLooper()));
    protected Preference mPreference;
    protected PreferenceManager mPreferenceManager;
    protected int mSubId = -1;
    protected TelephonyManager mTelephonyManager;

    public CdmaBasePreferenceController(Context context, String str) {
        super(context, str);
    }

    public void onStart() {
        this.mDataContentObserver.register(this.mContext, this.mSubId);
    }

    public void onStop() {
        this.mDataContentObserver.unRegister(this.mContext);
    }

    public int getAvailabilityStatus(int i) {
        return MobileNetworkUtils.isCdmaOptions(this.mContext, i) ? 0 : 2;
    }

    public void init(PreferenceManager preferenceManager, int i) {
        this.mPreferenceManager = preferenceManager;
        this.mSubId = i;
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
    }

    public void init(int i) {
        init((PreferenceManager) null, i);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
        Preference preference = this.mPreference;
        if (preference instanceof CdmaListPreference) {
            ((CdmaListPreference) preference).setSubId(this.mSubId);
        }
    }

    public class DataContentObserver extends ContentObserver {
        public DataContentObserver(Handler handler) {
            super(handler);
        }

        public void onChange(boolean z) {
            super.onChange(z);
            CdmaBasePreferenceController cdmaBasePreferenceController = CdmaBasePreferenceController.this;
            cdmaBasePreferenceController.updateState(cdmaBasePreferenceController.mPreference);
        }

        public void register(Context context, int i) {
            context.getContentResolver().registerContentObserver(Settings.Global.getUriFor("preferred_network_mode" + i), false, this);
        }

        public void unRegister(Context context) {
            context.getContentResolver().unregisterContentObserver(this);
        }
    }
}
