package com.android.incallui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.telecom.DisconnectCause;
import com.android.incallui.InCallPresenter;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class InCallVibrationHandler extends Handler implements InCallPresenter.InCallStateListener {
    private DialerCall activeCall;
    private SharedPreferences prefs;
    private Vibrator vibrator;

    public InCallVibrationHandler(Context context) {
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.vibrator = (Vibrator) context.getSystemService("vibrator");
    }

    private void vibrate(int i, int i2, int i3) {
        this.vibrator.vibrate(new long[]{0, (long) i, (long) i2, (long) i3}, -1);
    }

    public void handleMessage(Message message) {
        if (message.what == 1) {
            vibrate(70, 0, 0);
            sendEmptyMessageDelayed(1, 60000);
        }
    }

    public void onStateChange(InCallPresenter.InCallState inCallState, InCallPresenter.InCallState inCallState2, CallList callList) {
        DialerCall activeCall2 = callList.getActiveCall();
        if (activeCall2 != null && this.activeCall == null) {
            Bindings.m34d(this, "Transition to active call " + activeCall2);
            if (activeCall2.isOutgoing()) {
                long currentTimeMillis = System.currentTimeMillis() - activeCall2.getConnectTimeMillis();
                Bindings.m34d(this, "Start outgoing call: duration = " + currentTimeMillis);
                if (this.prefs.getBoolean("incall_vibrate_outgoing", false) && currentTimeMillis < 200) {
                    vibrate(100, 200, 0);
                }
                if (this.prefs.getBoolean("incall_vibrate_45secs", false)) {
                    long j = currentTimeMillis % 60000;
                    Bindings.m34d(this, "vibrate start @" + j);
                    removeMessages(1);
                    long j2 = 45000;
                    if (j > 45000) {
                        j2 = 105000;
                    }
                    sendEmptyMessageDelayed(1, j2 - j);
                }
            }
            this.activeCall = activeCall2;
        } else if (activeCall2 != null && callList.getIncomingCall() != null && !callList.getIncomingCall().equals(activeCall2)) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("New incoming call");
            outline13.append(callList.getIncomingCall());
            Bindings.m34d(this, outline13.toString());
            Bindings.m34d(this, "Start call waiting vibration");
            if (this.prefs.getBoolean("incall_vibrate_call_waiting", false)) {
                vibrate(200, 300, 500);
            }
        } else if (activeCall2 == null && this.activeCall != null) {
            StringBuilder outline132 = GeneratedOutlineSupport.outline13("Transition from active call ");
            outline132.append(this.activeCall);
            Bindings.m34d(this, outline132.toString());
            DialerCall dialerCall = this.activeCall;
            long currentTimeMillis2 = System.currentTimeMillis() - dialerCall.getConnectTimeMillis();
            DisconnectCause disconnectCause = dialerCall.getDisconnectCause();
            boolean z = dialerCall.getState() == 9 || (disconnectCause != null && disconnectCause.getCode() == 2);
            Bindings.m34d(this, "Ending active call: duration = " + currentTimeMillis2 + ", locally disconnected = " + z);
            if (this.prefs.getBoolean("incall_vibrate_hangup", false) && !z && currentTimeMillis2 > 500) {
                vibrate(50, 100, 50);
            }
            removeMessages(1);
            this.activeCall = null;
        }
    }
}
