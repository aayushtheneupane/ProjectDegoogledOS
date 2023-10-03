package com.android.incallui;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.Trace;
import android.support.design.R$dimen;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.incallui.answer.impl.AnswerFragment;
import com.android.incallui.answer.protocol.AnswerScreen;
import com.android.incallui.answer.protocol.AnswerScreenDelegate;
import com.android.incallui.answerproximitysensor.AnswerProximitySensor;
import com.android.incallui.answerproximitysensor.PseudoScreenState;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import com.android.incallui.call.DialerCallListener;
import com.android.incallui.incalluilock.InCallUiLock;
import com.android.incallui.speakeasy.SpeakEasyCallManagerStub;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class AnswerScreenPresenter implements AnswerScreenDelegate, DialerCall.CannedTextResponsesLoadedListener {
    private long actionPerformedTimeMillis;
    private final AnswerScreen answerScreen;
    /* access modifiers changed from: private */
    public final DialerCall call;
    private final Context context;

    private class AnswerOnDisconnected implements DialerCallListener {
        private final DialerCall disconnectingCall;

        AnswerOnDisconnected(DialerCall dialerCall) {
            this.disconnectingCall = dialerCall;
        }

        public void onDialerCallChildNumberChange() {
        }

        public void onDialerCallDisconnect() {
            LogUtil.m9i("AnswerScreenPresenter.AnswerOnDisconnected", "call disconnected, answering new call", new Object[0]);
            AnswerScreenPresenter.this.call.answer();
            this.disconnectingCall.removeListener(this);
        }

        public void onDialerCallLastForwardedNumberChange() {
        }

        public void onDialerCallSessionModificationStateChange() {
        }

        public void onDialerCallUpdate() {
        }

        public void onDialerCallUpgradeToVideo() {
        }

        public void onHandoverToWifiFailure() {
        }

        public void onInternationalCallOnWifi() {
        }

        public void onWiFiToLteHandover() {
        }
    }

    AnswerScreenPresenter(Context context2, AnswerScreen answerScreen2, DialerCall dialerCall) {
        boolean z = false;
        LogUtil.m9i("AnswerScreenPresenter.constructor", (String) null, new Object[0]);
        Assert.isNotNull(context2);
        this.context = context2;
        Assert.isNotNull(answerScreen2);
        this.answerScreen = answerScreen2;
        Assert.isNotNull(dialerCall);
        this.call = dialerCall;
        if (isSmsResponseAllowed(dialerCall)) {
            ((AnswerFragment) answerScreen2).setTextResponses(dialerCall.getCannedSmsResponses());
        }
        dialerCall.addCannedTextResponsesLoadedListener(this);
        PseudoScreenState pseudoScreenState = InCallPresenter.getInstance().getPseudoScreenState();
        Trace.beginSection("AnswerProximitySensor.shouldUse");
        if (dialerCall.getState() != 4) {
            LogUtil.m9i("AnswerProximitySensor.shouldUse", "call state is not incoming", new Object[0]);
            Trace.endSection();
        } else if (!((SharedPrefConfigProvider) ConfigProviderComponent.get(context2).getConfigProvider()).getBoolean("answer_proximity_sensor_enabled", true)) {
            LogUtil.m9i("AnswerProximitySensor.shouldUse", "disabled by config", new Object[0]);
            Trace.endSection();
        } else if (!context2.getResources().getBoolean(R.bool.config_answer_proximity_sensor_enabled)) {
            LogUtil.m9i("AnswerProximitySensor.shouldUse", "disabled by overlay", new Object[0]);
        } else if (!((PowerManager) context2.getSystemService(PowerManager.class)).isWakeLockLevelSupported(32)) {
            LogUtil.m9i("AnswerProximitySensor.shouldUse", "wake lock level not supported", new Object[0]);
            Trace.endSection();
        } else {
            if (((DisplayManager) context2.getSystemService(DisplayManager.class)).getDisplay(0).getState() == 2) {
                LogUtil.m9i("AnswerProximitySensor.shouldUse", "display is already on", new Object[0]);
                Trace.endSection();
            } else {
                Trace.endSection();
                z = true;
            }
        }
        if (z) {
            new AnswerProximitySensor(context2, dialerCall, pseudoScreenState);
        } else {
            pseudoScreenState.setOn(true);
        }
    }

    static /* synthetic */ void access$000(AnswerScreenPresenter answerScreenPresenter, boolean z) {
        if (((AnswerFragment) answerScreenPresenter.answerScreen).isVideoUpgradeRequest()) {
            if (z) {
                ((LoggingBindingsStub) Logger.get(answerScreenPresenter.context)).logCallImpression(DialerImpression$Type.VIDEO_CALL_REQUEST_ACCEPTED_AS_AUDIO, answerScreenPresenter.call.getUniqueCallId(), answerScreenPresenter.call.getTimeAddedMs());
                answerScreenPresenter.call.getVideoTech().acceptVideoRequestAsAudio();
                return;
            }
            ((LoggingBindingsStub) Logger.get(answerScreenPresenter.context)).logCallImpression(DialerImpression$Type.VIDEO_CALL_REQUEST_ACCEPTED, answerScreenPresenter.call.getUniqueCallId(), answerScreenPresenter.call.getTimeAddedMs());
            answerScreenPresenter.call.getVideoTech().acceptVideoRequest(answerScreenPresenter.context);
        } else if (z) {
            answerScreenPresenter.call.answer(0);
        } else {
            answerScreenPresenter.call.answer();
        }
    }

    private void addTimeoutCheck() {
        this.actionPerformedTimeMillis = SystemClock.elapsedRealtime();
        AnswerFragment answerFragment = (AnswerFragment) this.answerScreen;
        answerFragment.getAnswerScreenFragment();
        if (answerFragment.isVisible()) {
            DialerExecutorModule.postDelayedOnUiThread(new Runnable() {
                public final void run() {
                    AnswerScreenPresenter.this.lambda$addTimeoutCheck$0$AnswerScreenPresenter();
                }
            }, 5000);
        }
    }

    private boolean isSmsResponseAllowed(DialerCall dialerCall) {
        return R$dimen.isUserUnlocked(this.context) && dialerCall.can(32);
    }

    public InCallUiLock acquireInCallUiLock(String str) {
        return InCallPresenter.getInstance().acquireInCallUiLock(str);
    }

    public boolean isActionTimeout() {
        return this.actionPerformedTimeMillis != 0 && SystemClock.elapsedRealtime() - this.actionPerformedTimeMillis >= 5000;
    }

    public /* synthetic */ void lambda$addTimeoutCheck$0$AnswerScreenPresenter() {
        AnswerFragment answerFragment = (AnswerFragment) this.answerScreen;
        answerFragment.getAnswerScreenFragment();
        if (answerFragment.isVisible()) {
            LogUtil.m9i("AnswerScreenPresenter.addTimeoutCheck", "accept/reject call timed out", new Object[0]);
            InCallPresenter.getInstance().refreshUi();
        }
    }

    public void onAnswer(final boolean z) {
        ListenableFuture<Void> listenableFuture;
        DialerCall incomingCall = CallList.getInstance().getIncomingCall();
        AnswerFragment answerFragment = (AnswerFragment) this.answerScreen;
        answerFragment.getAnswerScreenFragment();
        InCallActivity inCallActivity = (InCallActivity) answerFragment.getActivity();
        if (incomingCall == null || inCallActivity == null) {
            listenableFuture = Futures.immediateFuture(null);
        } else {
            listenableFuture = ((SpeakEasyCallManagerStub) inCallActivity.getSpeakEasyCallManager()).onNewIncomingCall(incomingCall);
        }
        Futures.addCallback(listenableFuture, new FutureCallback<Void>() {
            public void onFailure(Throwable th) {
                AnswerScreenPresenter.access$000(AnswerScreenPresenter.this, false);
                throw new RuntimeException("Failed to successfully complete pre call tasks.", th);
            }

            public void onSuccess(Object obj) {
                Void voidR = (Void) obj;
                AnswerScreenPresenter.access$000(AnswerScreenPresenter.this, false);
            }
        }, DialerExecutorComponent.get(this.context).uiExecutor());
        addTimeoutCheck();
    }

    public void onAnswerAndReleaseButtonDisabled() {
        DialerCall activeCall = CallList.getInstance().getActiveCall();
        if (activeCall != null) {
            activeCall.increaseSecondCallWithoutAnswerAndReleasedButtonTimes();
        }
    }

    public void onAnswerAndReleaseButtonEnabled() {
        DialerCall activeCall = CallList.getInstance().getActiveCall();
        if (activeCall != null) {
            activeCall.increaseAnswerAndReleaseButtonDisplayedTimes();
        }
    }

    public void onAnswerAndReleaseCall() {
        ListenableFuture<Void> listenableFuture;
        LogUtil.enterBlock("AnswerScreenPresenter.onAnswerAndReleaseCall");
        DialerCall activeCall = CallList.getInstance().getActiveCall();
        if (activeCall == null) {
            LogUtil.m9i("AnswerScreenPresenter.onAnswerAndReleaseCall", "activeCall == null", new Object[0]);
            DialerCall incomingCall = CallList.getInstance().getIncomingCall();
            AnswerFragment answerFragment = (AnswerFragment) this.answerScreen;
            answerFragment.getAnswerScreenFragment();
            InCallActivity inCallActivity = (InCallActivity) answerFragment.getActivity();
            if (incomingCall == null || inCallActivity == null) {
                listenableFuture = Futures.immediateFuture(null);
            } else {
                listenableFuture = ((SpeakEasyCallManagerStub) inCallActivity.getSpeakEasyCallManager()).onNewIncomingCall(incomingCall);
            }
            Futures.addCallback(listenableFuture, new FutureCallback<Void>(false) {
                public void onFailure(Throwable th) {
                    AnswerScreenPresenter.access$000(AnswerScreenPresenter.this, false);
                    throw new RuntimeException("Failed to successfully complete pre call tasks.", th);
                }

                public void onSuccess(Object obj) {
                    Void voidR = (Void) obj;
                    AnswerScreenPresenter.access$000(AnswerScreenPresenter.this, false);
                }
            }, DialerExecutorComponent.get(this.context).uiExecutor());
            addTimeoutCheck();
        } else {
            activeCall.setReleasedByAnsweringSecondCall(true);
            activeCall.addListener(new AnswerOnDisconnected(activeCall));
            activeCall.disconnect();
        }
        addTimeoutCheck();
    }

    public void onAnswerScreenUnready() {
        this.call.removeCannedTextResponsesLoadedListener(this);
    }

    public void onCannedTextResponsesLoaded(DialerCall dialerCall) {
        if (isSmsResponseAllowed(dialerCall)) {
            ((AnswerFragment) this.answerScreen).setTextResponses(dialerCall.getCannedSmsResponses());
        }
    }

    public void onReject() {
        if (((AnswerFragment) this.answerScreen).isVideoUpgradeRequest()) {
            ((LoggingBindingsStub) Logger.get(this.context)).logCallImpression(DialerImpression$Type.VIDEO_CALL_REQUEST_DECLINED, this.call.getUniqueCallId(), this.call.getTimeAddedMs());
            this.call.getVideoTech().declineVideoRequest();
        } else {
            this.call.reject(false, (String) null);
        }
        addTimeoutCheck();
    }

    public void onRejectCallWithMessage(String str) {
        this.call.reject(true, str);
        addTimeoutCheck();
    }

    public void onSpeakEasyCall() {
        LogUtil.enterBlock("AnswerScreenPresenter.onSpeakEasyCall");
        DialerCall incomingCall = CallList.getInstance().getIncomingCall();
        if (incomingCall == null) {
            LogUtil.m9i("AnswerScreenPresenter.onSpeakEasyCall", "incomingCall == null", new Object[0]);
        } else {
            incomingCall.setIsSpeakEasyCall(true);
        }
    }

    public void updateWindowBackgroundColor(float f) {
        AnswerFragment answerFragment = (AnswerFragment) this.answerScreen;
        answerFragment.getAnswerScreenFragment();
        InCallActivity inCallActivity = (InCallActivity) answerFragment.getActivity();
        if (inCallActivity != null) {
            inCallActivity.updateWindowBackgroundColor(f);
        }
    }
}
