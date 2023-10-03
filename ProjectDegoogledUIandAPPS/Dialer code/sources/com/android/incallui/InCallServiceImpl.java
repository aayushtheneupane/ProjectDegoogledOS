package com.android.incallui;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Trace;
import android.telecom.Call;
import android.telecom.CallAudioState;
import android.telecom.InCallService;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.blocking.FilteredNumberAsyncQueryHandler;
import com.android.dialer.inject.HasRootComponent;
import com.android.incallui.audiomode.AudioModeProvider;
import com.android.incallui.call.CallList;
import com.android.incallui.call.CallRecorder;
import com.android.incallui.call.ExternalCallList;
import com.android.incallui.call.TelecomAdapter;
import com.android.incallui.speakeasy.SpeakEasyCallManager;
import com.android.incallui.speakeasy.SpeakEasyCallManagerStub;
import com.android.incallui.speakeasy.SpeakEasyComponent;

public class InCallServiceImpl extends InCallService {
    private CallList.Listener feedbackListener;
    private ReturnToCallController returnToCallController;
    private SpeakEasyCallManager speakEasyCallManager;

    public IBinder onBind(Intent intent) {
        Trace.beginSection("InCallServiceImpl.onBind");
        Context applicationContext = getApplicationContext();
        ContactInfoCache instance = ContactInfoCache.getInstance(applicationContext);
        AudioModeProvider.getInstance().initializeAudioState(this);
        InCallPresenter.getInstance().setUp(applicationContext, CallList.getInstance(), new ExternalCallList(), new StatusBarNotifier(applicationContext, instance), new ExternalCallNotifier(applicationContext, instance), instance, new ProximitySensor(applicationContext, AudioModeProvider.getInstance(), new AccelerometerListener(applicationContext)), new FilteredNumberAsyncQueryHandler(applicationContext), this.speakEasyCallManager);
        InCallPresenter.getInstance().onServiceBind();
        InCallPresenter.getInstance().maybeStartRevealAnimation(intent);
        TelecomAdapter.getInstance().setInCallService(this);
        CallRecorder.getInstance().setUp(applicationContext);
        this.returnToCallController = new ReturnToCallController(this, ContactInfoCache.getInstance(applicationContext));
        this.feedbackListener = ((DaggerAospDialerRootComponent) ((HasRootComponent) applicationContext.getApplicationContext()).component()).feedbackComponent().getCallFeedbackListener();
        CallList.getInstance().addListener(this.feedbackListener);
        IBinder onBind = super.onBind(intent);
        Trace.endSection();
        return onBind;
    }

    public void onBringToForeground(boolean z) {
        Trace.beginSection("InCallServiceImpl.onBringToForeground");
        InCallPresenter.getInstance().onBringToForeground(z);
        Trace.endSection();
    }

    public void onCallAdded(Call call) {
        Trace.beginSection("InCallServiceImpl.onCallAdded");
        InCallPresenter.getInstance().onCallAdded(call);
        Trace.endSection();
    }

    public void onCallAudioStateChanged(CallAudioState callAudioState) {
        Trace.beginSection("InCallServiceImpl.onCallAudioStateChanged");
        AudioModeProvider.getInstance().onAudioStateChanged(callAudioState);
        Trace.endSection();
    }

    public void onCallRemoved(Call call) {
        Trace.beginSection("InCallServiceImpl.onCallRemoved");
        ((SpeakEasyCallManagerStub) this.speakEasyCallManager).onCallRemoved(CallList.getInstance().getDialerCallFromTelecomCall(call));
        InCallPresenter.getInstance().onCallRemoved(call);
        Trace.endSection();
    }

    public void onCanAddCallChanged(boolean z) {
        Trace.beginSection("InCallServiceImpl.onCanAddCallChanged");
        InCallPresenter.getInstance().onCanAddCallChanged(z);
        Trace.endSection();
    }

    public void onCreate() {
        super.onCreate();
        this.speakEasyCallManager = SpeakEasyComponent.get(this).speakEasyCallManager();
    }

    public boolean onUnbind(Intent intent) {
        Trace.beginSection("InCallServiceImpl.onUnbind");
        super.onUnbind(intent);
        InCallPresenter.getInstance().onServiceUnbind();
        Trace.beginSection("InCallServiceImpl.tearDown");
        Bindings.m40v(this, "tearDown");
        InCallPresenter.getInstance().tearDown();
        TelecomAdapter.getInstance().clearInCallService();
        ReturnToCallController returnToCallController2 = this.returnToCallController;
        if (returnToCallController2 != null) {
            returnToCallController2.tearDown();
            this.returnToCallController = null;
        }
        if (this.feedbackListener != null) {
            CallList.getInstance().removeListener(this.feedbackListener);
            this.feedbackListener = null;
        }
        Trace.endSection();
        Trace.endSection();
        return false;
    }
}
