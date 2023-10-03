package com.android.incallui;

import android.telephony.PhoneNumberUtils;
import com.android.incallui.InCallPresenter;
import com.android.incallui.baseui.C0701Ui;
import com.android.incallui.baseui.Presenter;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import com.android.incallui.call.TelecomAdapter;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class DialpadPresenter extends Presenter<DialpadUi> implements InCallPresenter.InCallStateListener {
    private DialerCall call;

    public interface DialpadUi extends C0701Ui {
    }

    public void onStateChange(InCallPresenter.InCallState inCallState, InCallPresenter.InCallState inCallState2, CallList callList) {
        this.call = callList.getOutgoingOrActive();
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("DialpadPresenter mCall = ");
        outline13.append(this.call);
        Bindings.m34d(this, outline13.toString());
    }

    public void onUiReady(C0701Ui ui) {
        super.onUiReady((DialpadUi) ui);
        InCallPresenter.getInstance().addListener(this);
        this.call = CallList.getInstance().getOutgoingOrActive();
    }

    public void onUiUnready(C0701Ui ui) {
        super.onUiUnready((DialpadUi) ui);
        InCallPresenter.getInstance().removeListener(this);
    }

    public final void processDtmf(char c) {
        Bindings.m34d(this, "Processing dtmf key " + c);
        if (!PhoneNumberUtils.is12Key(c) || this.call == null) {
            Bindings.m34d(this, "ignoring dtmf request for '" + c + "'");
            return;
        }
        Bindings.m34d(this, "updating display and sending dtmf tone for '" + c + "'");
        DialpadUi dialpadUi = (DialpadUi) getUi();
        if (dialpadUi != null) {
            ((DialpadFragment) dialpadUi).appendDigitsToField(c);
        }
        TelecomAdapter.getInstance().playDtmfTone(this.call.getId(), c);
    }

    public void stopDtmf() {
        if (this.call != null) {
            Bindings.m34d(this, "stopping remote tone");
            TelecomAdapter.getInstance().stopDtmfTone(this.call.getId());
        }
    }
}
