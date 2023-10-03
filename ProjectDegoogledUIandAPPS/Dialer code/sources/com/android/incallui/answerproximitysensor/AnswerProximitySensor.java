package com.android.incallui.answerproximitysensor;

import android.content.Context;
import android.os.Trace;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.incallui.answerproximitysensor.AnswerProximityWakeLock;
import com.android.incallui.call.DialerCall;
import com.android.incallui.call.DialerCallListener;

public class AnswerProximitySensor implements DialerCallListener, AnswerProximityWakeLock.ScreenOnListener {
    private final AnswerProximityWakeLock answerProximityWakeLock;
    private final DialerCall call;

    public AnswerProximitySensor(Context context, DialerCall dialerCall, PseudoScreenState pseudoScreenState) {
        Trace.beginSection("AnswerProximitySensor Constructor");
        this.call = dialerCall;
        LogUtil.m9i("AnswerProximitySensor.constructor", "acquiring lock", new Object[0]);
        if (((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getBoolean("answer_pseudo_proximity_wake_lock_enabled", true)) {
            this.answerProximityWakeLock = new PseudoProximityWakeLock(context, pseudoScreenState);
        } else {
            this.answerProximityWakeLock = new SystemProximityWakeLock(context);
        }
        this.answerProximityWakeLock.setScreenOnListener(this);
        this.answerProximityWakeLock.acquire();
        dialerCall.addListener(this);
        Trace.endSection();
    }

    private void cleanup() {
        Trace.beginSection("AnswerProximitySensor.Cleanup");
        this.call.removeListener(this);
        if (this.answerProximityWakeLock.isHeld()) {
            LogUtil.m9i("AnswerProximitySensor.releaseProximityWakeLock", "releasing lock", new Object[0]);
            this.answerProximityWakeLock.release();
        }
        Trace.endSection();
    }

    public void onDialerCallChildNumberChange() {
    }

    public void onDialerCallDisconnect() {
        LogUtil.m9i("AnswerProximitySensor.onDialerCallDisconnect", (String) null, new Object[0]);
        cleanup();
    }

    public void onDialerCallLastForwardedNumberChange() {
    }

    public void onDialerCallSessionModificationStateChange() {
    }

    public void onDialerCallUpdate() {
        if (this.call.getState() != 4) {
            LogUtil.m9i("AnswerProximitySensor.onDialerCallUpdate", "no longer incoming, cleaning up", new Object[0]);
            cleanup();
        }
    }

    public void onDialerCallUpgradeToVideo() {
    }

    public void onHandoverToWifiFailure() {
    }

    public void onInternationalCallOnWifi() {
    }

    public void onScreenOn() {
        cleanup();
    }

    public void onWiFiToLteHandover() {
    }
}
