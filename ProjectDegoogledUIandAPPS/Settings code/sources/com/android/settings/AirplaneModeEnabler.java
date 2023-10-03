package com.android.settings;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.provider.Settings;
import com.android.internal.telephony.PhoneStateIntentReceiver;
import com.android.settingslib.WirelessUtils;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;

public class AirplaneModeEnabler {
    private ContentObserver mAirplaneModeObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
        public void onChange(boolean z) {
            AirplaneModeEnabler.this.onAirplaneModeChanged();
        }
    };
    private final Context mContext;
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 3) {
                AirplaneModeEnabler.this.onAirplaneModeChanged();
            }
        }
    };
    private final MetricsFeatureProvider mMetricsFeatureProvider;
    private OnAirplaneModeChangedListener mOnAirplaneModeChangedListener;
    private PhoneStateIntentReceiver mPhoneStateReceiver;

    public interface OnAirplaneModeChangedListener {
        void onAirplaneModeChanged(boolean z);
    }

    public AirplaneModeEnabler(Context context, MetricsFeatureProvider metricsFeatureProvider, OnAirplaneModeChangedListener onAirplaneModeChangedListener) {
        this.mContext = context;
        this.mMetricsFeatureProvider = metricsFeatureProvider;
        this.mOnAirplaneModeChangedListener = onAirplaneModeChangedListener;
        this.mPhoneStateReceiver = new PhoneStateIntentReceiver(this.mContext, this.mHandler);
        this.mPhoneStateReceiver.notifyServiceState(3);
    }

    public void resume() {
        this.mPhoneStateReceiver.registerIntent();
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("airplane_mode_on"), true, this.mAirplaneModeObserver);
    }

    public void pause() {
        this.mPhoneStateReceiver.unregisterIntent();
        this.mContext.getContentResolver().unregisterContentObserver(this.mAirplaneModeObserver);
    }

    private void setAirplaneModeOn(boolean z) {
        Settings.Global.putInt(this.mContext.getContentResolver(), "airplane_mode_on", z ? 1 : 0);
        OnAirplaneModeChangedListener onAirplaneModeChangedListener = this.mOnAirplaneModeChangedListener;
        if (onAirplaneModeChangedListener != null) {
            onAirplaneModeChangedListener.onAirplaneModeChanged(z);
        }
        Intent intent = new Intent("android.intent.action.AIRPLANE_MODE");
        intent.putExtra("state", z);
        this.mContext.sendBroadcastAsUser(intent, UserHandle.ALL);
    }

    /* access modifiers changed from: private */
    public void onAirplaneModeChanged() {
        OnAirplaneModeChangedListener onAirplaneModeChangedListener = this.mOnAirplaneModeChangedListener;
        if (onAirplaneModeChangedListener != null) {
            onAirplaneModeChangedListener.onAirplaneModeChanged(isAirplaneModeOn());
        }
    }

    public void setAirplaneMode(boolean z) {
        if (!Boolean.parseBoolean(SystemProperties.get("ril.cdma.inecmmode"))) {
            this.mMetricsFeatureProvider.action(this.mContext, 177, z);
            setAirplaneModeOn(z);
        }
    }

    public void setAirplaneModeInECM(boolean z, boolean z2) {
        if (z) {
            setAirplaneModeOn(z2);
        } else {
            onAirplaneModeChanged();
        }
    }

    public boolean isAirplaneModeOn() {
        return WirelessUtils.isAirplaneModeOn(this.mContext);
    }
}
