package com.android.incallui;

import android.content.Context;
import com.android.dialer.activecalls.ActiveCallInfo;
import com.android.dialer.activecalls.ActiveCallsComponent;
import com.android.dialer.activecalls.impl.ActiveCallsImpl;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class ActiveCallsCallListListener implements CallList.Listener {
    private final Context appContext;

    ActiveCallsCallListListener(Context context) {
        this.appContext = context;
    }

    public void onCallListChange(CallList callList) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (DialerCall next : callList.getAllCalls()) {
            if (!(next.getState() == 10 || next.getAccountHandle() == null)) {
                ActiveCallInfo.Builder builder2 = ActiveCallInfo.builder();
                builder2.setPhoneAccountHandle(Optional.m67of(next.getAccountHandle()));
                builder.add((Object) builder2.build());
            }
        }
        ((ActiveCallsImpl) ActiveCallsComponent.get(this.appContext).activeCalls()).setActiveCalls(builder.build());
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
