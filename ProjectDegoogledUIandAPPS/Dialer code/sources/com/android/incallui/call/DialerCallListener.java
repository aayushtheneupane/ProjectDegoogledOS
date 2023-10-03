package com.android.incallui.call;

public interface DialerCallListener {
    void onDialerCallChildNumberChange();

    void onDialerCallDisconnect();

    void onDialerCallLastForwardedNumberChange();

    void onDialerCallSessionModificationStateChange();

    void onDialerCallSpeakEasyStateChange() {
    }

    void onDialerCallUpdate();

    void onDialerCallUpgradeToRtt(int i) {
    }

    void onDialerCallUpgradeToVideo();

    void onHandoverToWifiFailure();

    void onInternationalCallOnWifi();

    void onWiFiToLteHandover();
}
