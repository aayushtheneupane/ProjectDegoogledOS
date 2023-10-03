package com.android.incallui;

import android.telecom.Call;
import com.android.incallui.InCallPresenter;
import com.android.incallui.baseui.C0701Ui;
import com.android.incallui.baseui.Presenter;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;

public class ConferenceManagerPresenter extends Presenter<ConferenceManagerUi> implements InCallPresenter.InCallStateListener, InCallPresenter.InCallDetailsListener, InCallPresenter.IncomingCallListener {

    public interface ConferenceManagerUi extends C0701Ui {
    }

    private void update(CallList callList) {
        DialerCall activeOrBackgroundCall = callList.getActiveOrBackgroundCall();
        if (activeOrBackgroundCall != null) {
            ArrayList arrayList = new ArrayList(activeOrBackgroundCall.getChildCallIds().size());
            for (String callById : activeOrBackgroundCall.getChildCallIds()) {
                arrayList.add(callList.getCallById(callById));
            }
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Number of calls is ");
            outline13.append(String.valueOf(arrayList.size()));
            Bindings.m34d(this, outline13.toString());
            boolean z = true;
            boolean z2 = callList.getActiveCall() != null;
            boolean z3 = callList.getBackgroundCall() != null;
            if (z2 && z3) {
                z = false;
            }
            ((ConferenceManagerFragment) getUi()).update(arrayList, z);
        }
    }

    public void init(CallList callList) {
        update(callList);
    }

    public void onDetailsChanged(DialerCall dialerCall, Call.Details details) {
        boolean can = details.can(8192);
        boolean can2 = details.can(4096);
        if (!(dialerCall.can(8192) == can && dialerCall.can(4096) == can2)) {
            ((ConferenceManagerFragment) getUi()).refreshCall(dialerCall);
        }
        if (!details.can(128)) {
            InCallPresenter.getInstance().showConferenceCallManager(false);
        }
    }

    public void onIncomingCall(InCallPresenter.InCallState inCallState, InCallPresenter.InCallState inCallState2, DialerCall dialerCall) {
        if (((ConferenceManagerFragment) getUi()).isVisible()) {
            Bindings.m34d(this, "onIncomingCall()... Conference ui is showing, hide it.");
            InCallPresenter.getInstance().showConferenceCallManager(false);
        }
    }

    public void onStateChange(InCallPresenter.InCallState inCallState, InCallPresenter.InCallState inCallState2, CallList callList) {
        if (((ConferenceManagerFragment) getUi()).isVisible()) {
            Bindings.m40v(this, "onStateChange" + inCallState2);
            if (inCallState2 == InCallPresenter.InCallState.INCALL) {
                DialerCall activeOrBackgroundCall = callList.getActiveOrBackgroundCall();
                if (activeOrBackgroundCall == null || !activeOrBackgroundCall.isConferenceCall()) {
                    InCallPresenter.getInstance().showConferenceCallManager(false);
                    return;
                }
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Number of existing calls is ");
                outline13.append(String.valueOf(activeOrBackgroundCall.getChildCallIds().size()));
                Bindings.m40v(this, outline13.toString());
                update(callList);
                return;
            }
            InCallPresenter.getInstance().showConferenceCallManager(false);
        }
    }

    public void onUiReady(C0701Ui ui) {
        super.onUiReady((ConferenceManagerUi) ui);
        InCallPresenter.getInstance().addListener(this);
        InCallPresenter.getInstance().addIncomingCallListener(this);
    }

    public void onUiUnready(C0701Ui ui) {
        super.onUiUnready((ConferenceManagerUi) ui);
        InCallPresenter.getInstance().removeListener(this);
        InCallPresenter.getInstance().removeIncomingCallListener(this);
    }
}
