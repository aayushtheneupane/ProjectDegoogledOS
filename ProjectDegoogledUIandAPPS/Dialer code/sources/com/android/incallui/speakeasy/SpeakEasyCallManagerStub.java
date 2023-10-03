package com.android.incallui.speakeasy;

import android.content.Context;
import android.support.p000v4.app.Fragment;
import com.android.incallui.call.DialerCall;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Optional;

public class SpeakEasyCallManagerStub implements SpeakEasyCallManager {
    public Optional<Fragment> getSpeakEasyFragment(DialerCall dialerCall) {
        return Optional.empty();
    }

    public boolean isAvailable(Context context) {
        return false;
    }

    public void onCallRemoved(DialerCall dialerCall) {
    }

    public ListenableFuture<Void> onNewIncomingCall(DialerCall dialerCall) {
        return Futures.immediateFuture(null);
    }
}
