package com.android.incallui;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.p002v7.appcompat.R$style;
import com.android.incallui.InCallPresenter;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class InCallDndHandler implements InCallPresenter.InCallStateListener {
    private DialerCall activeCall;
    private NotificationManager notificationManager;
    private SharedPreferences prefs;
    private int userSelectedDndMode = this.notificationManager.getCurrentInterruptionFilter();

    public InCallDndHandler(Context context) {
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
    }

    private void handleDndState(DialerCall dialerCall) {
        if (this.prefs.getBoolean("incall_enable_dnd", false)) {
            if (R$style.isConnectingOrConnected(dialerCall.getState())) {
                Bindings.m34d(this, "Enabling Do Not Disturb mode");
                setDoNotDisturbMode(3);
                return;
            }
            Bindings.m34d(this, "Restoring previous Do Not Disturb mode");
            setDoNotDisturbMode(this.userSelectedDndMode);
        }
    }

    private void setDoNotDisturbMode(int i) {
        if (this.notificationManager.isNotificationPolicyAccessGranted()) {
            this.notificationManager.setInterruptionFilter(i);
            return;
        }
        Bindings.m36e(this, "Failed to set Do Not Disturb mode " + i + " due to lack of permissions");
    }

    public void onStateChange(InCallPresenter.InCallState inCallState, InCallPresenter.InCallState inCallState2, CallList callList) {
        DialerCall activeCall2 = callList.getActiveCall();
        if (activeCall2 != null && this.activeCall == null) {
            Bindings.m34d(this, "Transition to active call " + activeCall2);
            handleDndState(activeCall2);
            this.activeCall = activeCall2;
        } else if (activeCall2 == null && this.activeCall != null) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Transition from active call ");
            outline13.append(this.activeCall);
            Bindings.m34d(this, outline13.toString());
            handleDndState(this.activeCall);
            this.activeCall = null;
        }
    }
}
