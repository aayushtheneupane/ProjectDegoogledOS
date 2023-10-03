package com.android.dialer.feedback.stub;

import android.content.Context;
import com.android.dialer.common.Assert;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;

public class CallFeedbackListenerStub implements CallList.Listener {
    public CallFeedbackListenerStub(Context context) {
        Assert.isNotNull(context);
        Context context2 = context;
    }

    public void onCallListChange(CallList callList) {
    }

    public void onDisconnect(DialerCall dialerCall) {
    }

    public void onHandoverToWifiFailed(DialerCall dialerCall) {
    }

    public void onIncomingCall(DialerCall dialerCall) {
    }

    public void onInternationalCallOnWifi(DialerCall dialerCall) {
    }

    public void onSessionModificationStateChange(DialerCall dialerCall) {
    }

    public void onUpgradeToVideo(DialerCall dialerCall) {
    }

    public void onWiFiToLteHandover(DialerCall dialerCall) {
    }
}
