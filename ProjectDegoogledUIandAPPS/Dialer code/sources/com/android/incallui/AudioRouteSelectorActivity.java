package com.android.incallui;

import android.os.Bundle;
import android.support.p000v4.app.FragmentActivity;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.incallui.audiomode.AudioModeProvider;
import com.android.incallui.audioroute.AudioRouteSelectorDialogFragment;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import com.android.incallui.call.TelecomAdapter;

public class AudioRouteSelectorActivity extends FragmentActivity implements AudioRouteSelectorDialogFragment.AudioRouteSelectorPresenter, CallList.Listener {
    public void onAudioRouteSelected(int i) {
        DialerImpression$Type dialerImpression$Type;
        TelecomAdapter.getInstance().setAudioRoute(i);
        finish();
        if ((i & 5) != 0) {
            dialerImpression$Type = DialerImpression$Type.BUBBLE_V2_WIRED_OR_EARPIECE;
        } else if (i == 8) {
            dialerImpression$Type = DialerImpression$Type.BUBBLE_V2_SPEAKERPHONE;
        } else {
            dialerImpression$Type = i == 2 ? DialerImpression$Type.BUBBLE_V2_BLUETOOTH : null;
        }
        if (dialerImpression$Type != null) {
            DialerCall outgoingCall = CallList.getInstance().getOutgoingCall();
            if (outgoingCall == null) {
                outgoingCall = CallList.getInstance().getActiveOrBackgroundCall();
            }
            if (outgoingCall != null) {
                ((LoggingBindingsStub) Logger.get(this)).logCallImpression(dialerImpression$Type, outgoingCall.getUniqueCallId(), outgoingCall.getTimeAddedMs());
            } else {
                ((LoggingBindingsStub) Logger.get(this)).logImpression(dialerImpression$Type);
            }
        }
    }

    public void onAudioRouteSelectorDismiss() {
        finish();
    }

    public void onCallListChange(CallList callList) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AudioRouteSelectorDialogFragment.newInstance(AudioModeProvider.getInstance().getAudioState()).show(getSupportFragmentManager(), "AudioRouteSelectorDialogFragment");
        CallList.getInstance().addListener(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        CallList.getInstance().removeListener(this);
        super.onDestroy();
    }

    public void onDisconnect(DialerCall dialerCall) {
        DialerCall outgoingCall = CallList.getInstance().getOutgoingCall();
        if (outgoingCall == null) {
            outgoingCall = CallList.getInstance().getActiveOrBackgroundCall();
        }
        if (outgoingCall == null) {
            finish();
        }
    }

    public void onHandoverToWifiFailed(DialerCall dialerCall) {
    }

    public void onIncomingCall(DialerCall dialerCall) {
    }

    public void onInternationalCallOnWifi(DialerCall dialerCall) {
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        AudioRouteSelectorDialogFragment audioRouteSelectorDialogFragment = (AudioRouteSelectorDialogFragment) getSupportFragmentManager().findFragmentByTag("AudioRouteSelectorDialogFragment");
        if (audioRouteSelectorDialogFragment != null) {
            audioRouteSelectorDialogFragment.dismiss();
        }
        if (!isChangingConfigurations()) {
            finish();
        }
    }

    public void onSessionModificationStateChange(DialerCall dialerCall) {
    }

    public void onUpgradeToVideo(DialerCall dialerCall) {
    }

    public void onWiFiToLteHandover(DialerCall dialerCall) {
    }
}
