package com.android.settings.network.telephony;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.preference.PreferenceScreen;
import com.android.settings.network.MobileDataContentObserver;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

public class MmsMessagePreferenceController extends TelephonyTogglePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private MobileDataContentObserver mMobileDataContentObserver = new MobileDataContentObserver(new Handler(Looper.getMainLooper()));
    private PreferenceScreen mScreen;
    private SubscriptionManager mSubscriptionManager;
    private TelephonyManager mTelephonyManager;

    public MmsMessagePreferenceController(Context context, String str) {
        super(context, str);
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        this.mMobileDataContentObserver.setOnMobileDataChangedListener(new MobileDataContentObserver.OnMobileDataChangedListener() {
            public final void onMobileDataChanged() {
                MmsMessagePreferenceController.this.lambda$new$0$MmsMessagePreferenceController();
            }
        });
    }

    public int getAvailabilityStatus(int i) {
        TelephonyManager createForSubscriptionId = TelephonyManager.from(this.mContext).createForSubscriptionId(i);
        if (i == -1 || createForSubscriptionId.isDataEnabled() || !createForSubscriptionId.isApnMetered(2)) {
            return 2;
        }
        return 0;
    }

    public void onStart() {
        int i = this.mSubId;
        if (i != -1) {
            this.mMobileDataContentObserver.register(this.mContext, i);
        }
    }

    public void onStop() {
        if (this.mSubId != -1) {
            this.mMobileDataContentObserver.unRegister(this.mContext);
        }
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mScreen = preferenceScreen;
    }

    public void init(int i) {
        this.mSubId = i;
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
    }

    public boolean setChecked(boolean z) {
        return this.mSubscriptionManager.setAlwaysAllowMmsData(this.mSubId, z);
    }

    public boolean isChecked() {
        return this.mTelephonyManager.isDataEnabledForApn(2);
    }

    /* access modifiers changed from: private */
    /* renamed from: refreshPreference */
    public void lambda$new$0$MmsMessagePreferenceController() {
        PreferenceScreen preferenceScreen = this.mScreen;
        if (preferenceScreen != null) {
            super.displayPreference(preferenceScreen);
        }
    }
}
