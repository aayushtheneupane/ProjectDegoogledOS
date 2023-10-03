package com.android.incallui;

import android.support.p002v7.appcompat.R$style;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.incallui.InCallPresenter;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import java.util.Objects;

class VideoPauseController implements InCallPresenter.InCallStateListener, InCallPresenter.IncomingCallListener {
    private static VideoPauseController videoPauseController;
    private InCallPresenter inCallPresenter;
    private boolean isInBackground = false;
    private int prevCallState = 0;
    private DialerCall primaryCall = null;
    private boolean wasVideoCall = false;

    VideoPauseController() {
    }

    private void bringToForeground() {
        LogUtil.enterBlock("VideoPauseController.bringToForeground");
        InCallPresenter inCallPresenter2 = this.inCallPresenter;
        if (inCallPresenter2 != null) {
            inCallPresenter2.bringToForeground(false);
        } else {
            LogUtil.m8e("VideoPauseController.bringToForeground", "InCallPresenter is null. Cannot bring UI to foreground", new Object[0]);
        }
    }

    static synchronized VideoPauseController getInstance() {
        VideoPauseController videoPauseController2;
        synchronized (VideoPauseController.class) {
            if (videoPauseController == null) {
                videoPauseController = new VideoPauseController();
            }
            videoPauseController2 = videoPauseController;
        }
        return videoPauseController2;
    }

    private void onPrimaryCallChanged(DialerCall dialerCall) {
        boolean z = true;
        LogUtil.m9i("VideoPauseController.onPrimaryCallChanged", "new call: %s, old call: %s, mIsInBackground: %b", dialerCall, this.primaryCall, Boolean.valueOf(this.isInBackground));
        if (!Objects.equals(dialerCall, this.primaryCall)) {
            if (!videoCanPause(dialerCall) || this.isInBackground) {
                if (dialerCall == null || !(dialerCall.getState() == 5 || dialerCall.getState() == 4)) {
                    z = false;
                }
                if (z && videoCanPause(this.primaryCall)) {
                    sendRequest(this.primaryCall, false);
                }
            } else {
                sendRequest(dialerCall, true);
            }
            updatePrimaryCallContext(dialerCall);
            return;
        }
        throw new IllegalStateException();
    }

    private void sendRequest(DialerCall dialerCall, boolean z) {
        if (dialerCall != null) {
            if (z) {
                dialerCall.getVideoTech().unpause();
            } else {
                dialerCall.getVideoTech().pause();
            }
        }
    }

    private void updatePrimaryCallContext(DialerCall dialerCall) {
        if (dialerCall == null) {
            this.primaryCall = null;
            this.prevCallState = 0;
            this.wasVideoCall = false;
            return;
        }
        this.primaryCall = dialerCall;
        this.prevCallState = dialerCall.getState();
        this.wasVideoCall = dialerCall.isVideoCall();
    }

    private static boolean videoCanPause(DialerCall dialerCall) {
        return dialerCall != null && dialerCall.isVideoCall() && dialerCall.getState() == 3;
    }

    public void onIncomingCall(InCallPresenter.InCallState inCallState, InCallPresenter.InCallState inCallState2, DialerCall dialerCall) {
        LogUtil.m9i("VideoPauseController.onIncomingCall", "oldState: %s, newState: %s, call: %s", inCallState, inCallState2, dialerCall);
        if (!Objects.equals(dialerCall, this.primaryCall)) {
            onPrimaryCallChanged(dialerCall);
        }
    }

    public void onStateChange(InCallPresenter.InCallState inCallState, InCallPresenter.InCallState inCallState2, CallList callList) {
        DialerCall dialerCall;
        if (inCallState2 == InCallPresenter.InCallState.INCOMING) {
            dialerCall = callList.getIncomingCall();
        } else if (inCallState2 == InCallPresenter.InCallState.WAITING_FOR_ACCOUNT) {
            dialerCall = callList.getWaitingForAccountCall();
        } else if (inCallState2 == InCallPresenter.InCallState.PENDING_OUTGOING) {
            dialerCall = callList.getPendingOutgoingCall();
        } else if (inCallState2 == InCallPresenter.InCallState.OUTGOING) {
            dialerCall = callList.getOutgoingCall();
        } else {
            dialerCall = callList.getActiveCall();
        }
        boolean z = !Objects.equals(dialerCall, this.primaryCall);
        boolean videoCanPause = videoCanPause(dialerCall);
        LogUtil.m9i("VideoPauseController.onStateChange", "hasPrimaryCallChanged: %b, videoCanPause: %b, isInBackground: %b", Boolean.valueOf(z), Boolean.valueOf(videoCanPause), Boolean.valueOf(this.isInBackground));
        if (z) {
            onPrimaryCallChanged(dialerCall);
            return;
        }
        if (R$style.isDialing(this.prevCallState) && videoCanPause && this.isInBackground) {
            bringToForeground();
        } else if (!this.wasVideoCall && videoCanPause && this.isInBackground) {
            bringToForeground();
        }
        updatePrimaryCallContext(dialerCall);
    }

    public void onUiShowing(boolean z) {
        InCallPresenter inCallPresenter2 = this.inCallPresenter;
        if (inCallPresenter2 != null) {
            boolean z2 = inCallPresenter2.getInCallState() == InCallPresenter.InCallState.INCALL;
            if (z) {
                this.isInBackground = false;
                if (z2) {
                    sendRequest(this.primaryCall, true);
                    return;
                }
                return;
            }
            this.isInBackground = true;
            if (z2) {
                sendRequest(this.primaryCall, false);
            }
        }
    }

    public void setUp(InCallPresenter inCallPresenter2) {
        LogUtil.enterBlock("VideoPauseController.setUp");
        Assert.isNotNull(inCallPresenter2);
        this.inCallPresenter = inCallPresenter2;
        this.inCallPresenter.addListener(this);
        this.inCallPresenter.addIncomingCallListener(this);
    }

    public void tearDown() {
        LogUtil.enterBlock("VideoPauseController.tearDown");
        this.inCallPresenter.removeListener(this);
        this.inCallPresenter.removeIncomingCallListener(this);
        this.inCallPresenter = null;
        this.primaryCall = null;
        this.prevCallState = 0;
        this.wasVideoCall = false;
        this.isInBackground = false;
    }
}
