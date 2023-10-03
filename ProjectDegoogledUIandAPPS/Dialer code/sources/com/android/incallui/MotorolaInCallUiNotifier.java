package com.android.incallui;

import android.content.Context;
import android.content.Intent;
import com.android.incallui.InCallPresenter;
import com.android.incallui.call.CallList;

public class MotorolaInCallUiNotifier implements InCallPresenter.InCallUiListener, InCallPresenter.InCallStateListener {
    static final String ACTION_INCOMING_CALL_VISIBILITY_CHANGED = "com.motorola.incallui.action.INCOMING_CALL_VISIBILITY_CHANGED";
    static final String EXTRA_VISIBLE_KEY = "visible";
    static final String PERMISSION_INCOMING_CALL_VISIBILITY_CHANGED = "com.motorola.incallui.permission.INCOMING_CALL_VISIBILITY_CHANGED";
    private final Context context;

    MotorolaInCallUiNotifier(Context context2) {
        this.context = context2;
    }

    private void sendInCallUiBroadcast(boolean z) {
        "Send InCallUi Broadcast, visible: " + z;
        Intent intent = new Intent();
        intent.putExtra(EXTRA_VISIBLE_KEY, z);
        intent.setAction(ACTION_INCOMING_CALL_VISIBILITY_CHANGED);
        this.context.sendBroadcast(intent, PERMISSION_INCOMING_CALL_VISIBILITY_CHANGED);
    }

    public void onStateChange(InCallPresenter.InCallState inCallState, InCallPresenter.InCallState inCallState2, CallList callList) {
        if (inCallState != null && inCallState.isConnectingOrConnected() && inCallState2 == InCallPresenter.InCallState.NO_CALLS) {
            sendInCallUiBroadcast(false);
        }
    }

    public void onUiShowing(boolean z) {
        if (z && CallList.getInstance().getIncomingCall() != null) {
            sendInCallUiBroadcast(true);
        }
    }
}
