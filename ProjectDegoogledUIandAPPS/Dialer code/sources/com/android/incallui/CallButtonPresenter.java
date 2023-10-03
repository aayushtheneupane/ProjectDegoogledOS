package com.android.incallui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Trace;
import android.preference.PreferenceManager;
import android.support.p000v4.app.Fragment;
import android.telecom.Call;
import android.telecom.CallAudioState;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindings;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.incallui.InCallCameraManager;
import com.android.incallui.InCallPresenter;
import com.android.incallui.audiomode.AudioModeProvider;
import com.android.incallui.call.CallList;
import com.android.incallui.call.CallRecorder;
import com.android.incallui.call.DialerCall;
import com.android.incallui.call.DialerCallListener;
import com.android.incallui.call.TelecomAdapter;
import com.android.incallui.incall.protocol.InCallButtonUi;
import com.android.incallui.incall.protocol.InCallButtonUiDelegate;
import com.android.incallui.multisim.SwapSimWorker;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class CallButtonPresenter implements InCallPresenter.InCallStateListener, AudioModeProvider.AudioModeListener, InCallPresenter.IncomingCallListener, InCallPresenter.InCallDetailsListener, InCallPresenter.CanAddCallListener, InCallCameraManager.Listener, InCallButtonUiDelegate, DialerCallListener {
    private DialerCall call;
    private final Context context;
    /* access modifiers changed from: private */
    public InCallButtonUi inCallButtonUi;
    private boolean isInCallButtonUiReady;
    private boolean isRecording = false;
    private PhoneAccountHandle otherAccount;
    private CallRecorder.RecordingProgressListener recordingProgressListener = new CallRecorder.RecordingProgressListener() {
        public void onRecordingTimeProgress(long j) {
            CallButtonPresenter.this.inCallButtonUi.setCallRecordingDuration(j);
        }

        public void onStartRecording() {
            CallButtonPresenter.this.inCallButtonUi.setCallRecordingState(true);
            CallButtonPresenter.this.inCallButtonUi.setCallRecordingDuration(0);
        }

        public void onStopRecording() {
            CallButtonPresenter.this.inCallButtonUi.setCallRecordingState(false);
        }
    };

    public CallButtonPresenter(Context context2) {
        this.context = context2.getApplicationContext();
    }

    private InCallActivity getActivity() {
        Fragment inCallButtonUiFragment;
        InCallButtonUi inCallButtonUi2 = this.inCallButtonUi;
        if (inCallButtonUi2 == null || (inCallButtonUiFragment = inCallButtonUi2.getInCallButtonUiFragment()) == null) {
            return null;
        }
        return (InCallActivity) inCallButtonUiFragment.getActivity();
    }

    static /* synthetic */ boolean lambda$updateButtonsState$0(DialerCall dialerCall) {
        return dialerCall != null && dialerCall.isSpeakEasyCall();
    }

    /* JADX WARNING: Removed duplicated region for block: B:82:0x0179  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateButtonsState(com.android.incallui.call.DialerCall r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            boolean r2 = r20.isVideoCall()
            r3 = 8
            boolean r4 = r1.can(r3)
            r5 = 2
            r7 = 1
            if (r4 != 0) goto L_0x0020
            boolean r8 = r1.can(r5)
            if (r8 == 0) goto L_0x0020
            boolean r8 = r1.can(r7)
            if (r8 == 0) goto L_0x0020
            r8 = r7
            goto L_0x0021
        L_0x0020:
            r8 = 0
        L_0x0021:
            int r9 = r20.getState()
            if (r9 != r3) goto L_0x0029
            r9 = r7
            goto L_0x002a
        L_0x0029:
            r9 = 0
        L_0x002a:
            com.android.incallui.call.TelecomAdapter r10 = com.android.incallui.call.TelecomAdapter.getInstance()
            boolean r10 = r10.canAddCall()
            if (r10 == 0) goto L_0x003e
            android.content.Context r10 = r0.context
            boolean r10 = android.support.design.R$dimen.isUserUnlocked(r10)
            if (r10 == 0) goto L_0x003e
            r10 = r7
            goto L_0x003f
        L_0x003e:
            r10 = 0
        L_0x003f:
            com.android.incallui.InCallPresenter r11 = com.android.incallui.InCallPresenter.getInstance()
            com.android.incallui.call.CallList r11 = r11.getCallList()
            java.util.Collection r11 = r11.getAllCalls()
            java.util.stream.Stream r11 = r11.stream()
            com.android.incallui.-$$Lambda$CallButtonPresenter$pVPs5_coYg_dZOy12pC-jXX6CHo r12 = com.android.incallui.$$Lambda$CallButtonPresenter$pVPs5_coYg_dZOy12pCjXX6CHo.INSTANCE
            boolean r11 = r11.noneMatch(r12)
            r12 = 4
            if (r11 == 0) goto L_0x0060
            boolean r11 = r1.can(r12)
            if (r11 == 0) goto L_0x0060
            r11 = r7
            goto L_0x0061
        L_0x0060:
            r11 = 0
        L_0x0061:
            if (r2 != 0) goto L_0x0075
            com.android.incallui.videotech.VideoTech r13 = r20.getVideoTech()
            android.content.Context r14 = r0.context
            android.telecom.PhoneAccountHandle r15 = r20.getAccountHandle()
            boolean r13 = r13.isAvailable(r14, r15)
            if (r13 == 0) goto L_0x0075
            r13 = r7
            goto L_0x0076
        L_0x0075:
            r13 = 0
        L_0x0076:
            if (r2 == 0) goto L_0x0083
            r14 = 4194304(0x400000, float:5.877472E-39)
            boolean r14 = r1.can(r14)
            r14 = r14 ^ r7
            if (r14 == 0) goto L_0x0083
            r14 = r7
            goto L_0x0084
        L_0x0083:
            r14 = 0
        L_0x0084:
            r15 = 64
            boolean r15 = r1.can(r15)
            if (r2 == 0) goto L_0x0096
            android.content.Context r5 = r0.context
            boolean r5 = android.support.p002v7.appcompat.R$style.hasCameraPermissionAndShownPrivacyToast(r5)
            if (r5 == 0) goto L_0x0096
            r5 = r7
            goto L_0x0097
        L_0x0096:
            r5 = 0
        L_0x0097:
            r3 = 6
            if (r2 == 0) goto L_0x00aa
            int r12 = r20.getState()
            if (r12 == r3) goto L_0x00aa
            int r12 = r20.getState()
            r3 = 13
            if (r12 == r3) goto L_0x00aa
            r3 = r7
            goto L_0x00ab
        L_0x00aa:
            r3 = 0
        L_0x00ab:
            com.android.incallui.call.CallRecorder.getInstance()
            r12 = 3
            if (r2 != 0) goto L_0x00b9
            int r6 = r20.getState()
            if (r6 != r12) goto L_0x00b9
            r6 = r7
            goto L_0x00ba
        L_0x00b9:
            r6 = 0
        L_0x00ba:
            android.content.Context r12 = r0.context
            android.telecom.PhoneAccountHandle r7 = r20.getAccountHandle()
            android.telecom.PhoneAccountHandle r7 = com.android.dialer.telecom.TelecomUtil.getOtherAccount(r12, r7)
            r0.otherAccount = r7
            boolean r7 = r20.isEmergencyCall()
            if (r7 != 0) goto L_0x00f5
            android.telecom.PhoneAccountHandle r7 = r0.otherAccount
            if (r7 == 0) goto L_0x00f5
            boolean r7 = r20.isVoiceMailNumber()
            if (r7 != 0) goto L_0x00f5
            int r7 = r20.getState()
            boolean r7 = android.support.p002v7.appcompat.R$style.isDialing(r7)
            if (r7 == 0) goto L_0x00f5
            com.android.incallui.InCallPresenter r7 = com.android.incallui.InCallPresenter.getInstance()
            com.android.incallui.call.CallList r7 = r7.getCallList()
            java.util.Collection r7 = r7.getAllCalls()
            int r7 = r7.size()
            r12 = 1
            if (r7 != r12) goto L_0x00f5
            r7 = 1
            goto L_0x00f6
        L_0x00f5:
            r7 = 0
        L_0x00f6:
            boolean r12 = r20.canUpgradeToRttCall()
            if (r12 == 0) goto L_0x0107
            int r1 = r20.getState()
            r17 = r6
            r6 = 3
            if (r1 != r6) goto L_0x010a
            r1 = 1
            goto L_0x010b
        L_0x0107:
            r17 = r6
            r6 = 3
        L_0x010a:
            r1 = 0
        L_0x010b:
            com.android.incallui.incall.protocol.InCallButtonUi r6 = r0.inCallButtonUi
            r16 = r3
            r18 = r11
            r3 = 1
            r11 = 0
            r6.showButton(r11, r3)
            com.android.incallui.incall.protocol.InCallButtonUi r6 = r0.inCallButtonUi
            r11 = 4
            r6.showButton(r11, r4)
            com.android.incallui.incall.protocol.InCallButtonUi r4 = r0.inCallButtonUi
            r6 = 3
            r4.showButton(r6, r8)
            com.android.incallui.incall.protocol.InCallButtonUi r4 = r0.inCallButtonUi
            r4.setHold(r9)
            com.android.incallui.incall.protocol.InCallButtonUi r4 = r0.inCallButtonUi
            r4.showButton(r3, r15)
            com.android.incallui.incall.protocol.InCallButtonUi r4 = r0.inCallButtonUi
            r6 = 14
            r4.showButton(r6, r7)
            com.android.incallui.incall.protocol.InCallButtonUi r4 = r0.inCallButtonUi
            r6 = 8
            r4.showButton(r6, r3)
            com.android.incallui.incall.protocol.InCallButtonUi r3 = r0.inCallButtonUi
            r3.enableButton(r6, r10)
            com.android.incallui.incall.protocol.InCallButtonUi r3 = r0.inCallButtonUi
            r4 = 5
            r3.showButton(r4, r13)
            com.android.incallui.incall.protocol.InCallButtonUi r3 = r0.inCallButtonUi
            r4 = 16
            r3.showButton(r4, r12)
            com.android.incallui.incall.protocol.InCallButtonUi r3 = r0.inCallButtonUi
            r3.enableButton(r4, r1)
            com.android.incallui.incall.protocol.InCallButtonUi r1 = r0.inCallButtonUi
            r3 = 7
            r1.showButton(r3, r14)
            com.android.incallui.incall.protocol.InCallButtonUi r1 = r0.inCallButtonUi
            if (r2 == 0) goto L_0x0169
            if (r5 == 0) goto L_0x0169
            com.android.incallui.videotech.VideoTech r3 = r20.getVideoTech()
            boolean r3 = r3.isTransmitting()
            if (r3 == 0) goto L_0x0169
            r3 = 1
            goto L_0x016a
        L_0x0169:
            r3 = 0
        L_0x016a:
            r4 = 6
            r1.showButton(r4, r3)
            com.android.incallui.incall.protocol.InCallButtonUi r1 = r0.inCallButtonUi
            r3 = 10
            r6 = r16
            r1.showButton(r3, r6)
            if (r2 == 0) goto L_0x018e
            com.android.incallui.incall.protocol.InCallButtonUi r1 = r0.inCallButtonUi
            com.android.incallui.videotech.VideoTech r2 = r20.getVideoTech()
            boolean r2 = r2.isTransmitting()
            if (r2 == 0) goto L_0x018a
            if (r5 != 0) goto L_0x0188
            goto L_0x018a
        L_0x0188:
            r2 = 0
            goto L_0x018b
        L_0x018a:
            r2 = 1
        L_0x018b:
            r1.setVideoPaused(r2)
        L_0x018e:
            com.android.incallui.incall.protocol.InCallButtonUi r1 = r0.inCallButtonUi
            r2 = 2
            r3 = 1
            r1.showButton(r2, r3)
            com.android.incallui.incall.protocol.InCallButtonUi r1 = r0.inCallButtonUi
            r2 = 9
            r6 = r18
            r1.showButton(r2, r6)
            com.android.incallui.incall.protocol.InCallButtonUi r1 = r0.inCallButtonUi
            r2 = 15
            r7 = r17
            r1.showButton(r2, r7)
            com.android.incallui.incall.protocol.InCallButtonUi r0 = r0.inCallButtonUi
            r0.updateButtonStates()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.CallButtonPresenter.updateButtonsState(com.android.incallui.call.DialerCall):void");
    }

    private void updateCamera(boolean z) {
        InCallCameraManager inCallCameraManager = InCallPresenter.getInstance().getInCallCameraManager();
        inCallCameraManager.setUseFrontFacingCamera(z);
        String activeCameraId = inCallCameraManager.getActiveCameraId();
        if (activeCameraId != null) {
            this.call.setCameraDir(inCallCameraManager.isUsingFrontFacingCamera() ^ true ? 1 : 0);
            this.call.getVideoTech().setCamera(activeCameraId);
        }
    }

    public void addCallClicked() {
        ((LoggingBindingsStub) Logger.get(this.context)).logCallImpression(DialerImpression$Type.IN_CALL_ADD_CALL_BUTTON_PRESSED, this.call.getUniqueCallId(), this.call.getTimeAddedMs());
        InCallPresenter.getInstance().addCallClicked();
    }

    public void callRecordClicked(boolean z) {
        CallRecorder instance = CallRecorder.getInstance();
        if (z) {
            if (!instance.isRecording()) {
                String[] strArr = CallRecorder.REQUIRED_PERMISSIONS;
                int length = strArr.length;
                boolean z2 = false;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        z2 = true;
                        break;
                    }
                    if (this.context.checkSelfPermission(strArr[i]) != 0) {
                        break;
                    }
                    i++;
                }
                if (z2) {
                    CallRecorder.getInstance().startRecording(this.call.getNumber(), this.call.getCreationTimeMillis());
                } else {
                    this.inCallButtonUi.requestCallRecordingPermissions(CallRecorder.REQUIRED_PERMISSIONS);
                }
            }
        } else if (instance.isRecording()) {
            instance.finishRecording();
        }
    }

    public void changeToRttClicked() {
        LogUtil.enterBlock("CallButtonPresenter.changeToRttClicked");
        this.call.sendRttUpgradeRequest();
    }

    public void changeToVideoClicked() {
        LogUtil.enterBlock("CallButtonPresenter.changeToVideoClicked");
        ((LoggingBindingsStub) Logger.get(this.context)).logCallImpression(DialerImpression$Type.VIDEO_CALL_UPGRADE_REQUESTED, this.call.getUniqueCallId(), this.call.getTimeAddedMs());
        this.call.getVideoTech().upgradeToVideo(this.context);
    }

    public Context getContext() {
        return this.context;
    }

    public CallAudioState getCurrentAudioState() {
        return AudioModeProvider.getInstance().getAudioState();
    }

    public void holdClicked(boolean z) {
        if (this.call != null) {
            if (z) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("putting the call on hold: ");
                outline13.append(this.call);
                LogUtil.m9i("CallButtonPresenter", outline13.toString(), new Object[0]);
                this.call.hold();
                return;
            }
            StringBuilder outline132 = GeneratedOutlineSupport.outline13("removing the call from hold: ");
            outline132.append(this.call);
            LogUtil.m9i("CallButtonPresenter", outline132.toString(), new Object[0]);
            this.call.unhold();
        }
    }

    public void mergeClicked() {
        ((LoggingBindingsStub) Logger.get(this.context)).logCallImpression(DialerImpression$Type.IN_CALL_MERGE_BUTTON_PRESSED, this.call.getUniqueCallId(), this.call.getTimeAddedMs());
        TelecomAdapter.getInstance().merge(this.call.getId());
    }

    public void muteClicked(boolean z, boolean z2) {
        DialerImpression$Type dialerImpression$Type;
        LogUtil.m9i("CallButtonPresenter", "turning on mute: %s, clicked by user: %s", Boolean.valueOf(z), Boolean.valueOf(z2));
        if (z2) {
            LoggingBindings loggingBindings = Logger.get(this.context);
            if (z) {
                dialerImpression$Type = DialerImpression$Type.IN_CALL_SCREEN_TURN_ON_MUTE;
            } else {
                dialerImpression$Type = DialerImpression$Type.IN_CALL_SCREEN_TURN_OFF_MUTE;
            }
            ((LoggingBindingsStub) loggingBindings).logCallImpression(dialerImpression$Type, this.call.getUniqueCallId(), this.call.getTimeAddedMs());
        }
        TelecomAdapter.getInstance().mute(z);
    }

    public void onActiveCameraSelectionChanged(boolean z) {
        InCallButtonUi inCallButtonUi2 = this.inCallButtonUi;
        if (inCallButtonUi2 != null) {
            inCallButtonUi2.setCameraSwitched(!z);
        }
    }

    public void onAudioStateChanged(CallAudioState callAudioState) {
        InCallButtonUi inCallButtonUi2 = this.inCallButtonUi;
        if (inCallButtonUi2 != null) {
            inCallButtonUi2.setAudioState(callAudioState);
        }
    }

    public void onCameraPermissionGranted() {
        DialerCall dialerCall = this.call;
        if (dialerCall != null) {
            updateButtonsState(dialerCall);
        }
    }

    public void onCanAddCallChanged(boolean z) {
        DialerCall dialerCall;
        if (this.inCallButtonUi != null && (dialerCall = this.call) != null) {
            updateButtonsState(dialerCall);
        }
    }

    public void onDetailsChanged(DialerCall dialerCall, Call.Details details) {
        if (this.inCallButtonUi != null && dialerCall != null && dialerCall.equals(this.call)) {
            updateButtonsState(dialerCall);
        }
    }

    public void onDialerCallChildNumberChange() {
    }

    public void onDialerCallDisconnect() {
    }

    public void onDialerCallLastForwardedNumberChange() {
    }

    public void onDialerCallSessionModificationStateChange() {
        InCallButtonUi inCallButtonUi2 = this.inCallButtonUi;
        if (inCallButtonUi2 != null && this.call != null) {
            inCallButtonUi2.enableButton(10, true);
            updateButtonsState(this.call);
        }
    }

    public void onDialerCallUpdate() {
    }

    public void onDialerCallUpgradeToVideo() {
    }

    public void onEndCallClicked() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("call: ");
        outline13.append(this.call);
        LogUtil.m9i("CallButtonPresenter.onEndCallClicked", outline13.toString(), new Object[0]);
        DialerCall dialerCall = this.call;
        if (dialerCall != null) {
            dialerCall.disconnect();
        }
    }

    public void onHandoverToWifiFailure() {
    }

    public void onInCallButtonUiReady(InCallButtonUi inCallButtonUi2) {
        Assert.checkState(!this.isInCallButtonUiReady);
        this.inCallButtonUi = inCallButtonUi2;
        AudioModeProvider.getInstance().addListener(this);
        InCallPresenter instance = InCallPresenter.getInstance();
        instance.addListener(this);
        instance.addIncomingCallListener(this);
        instance.addDetailsListener(this);
        instance.addCanAddCallListener(this);
        instance.getInCallCameraManager().addCameraSelectionListener(this);
        CallRecorder instance2 = CallRecorder.getInstance();
        instance2.addRecordingProgressListener(this.recordingProgressListener);
        if (instance2.isRecording()) {
            this.inCallButtonUi.setCallRecordingState(true);
        } else {
            this.inCallButtonUi.setCallRecordingState(false);
        }
        onStateChange(InCallPresenter.InCallState.NO_CALLS, instance.getInCallState(), CallList.getInstance());
        this.isInCallButtonUiReady = true;
    }

    public void onInCallButtonUiUnready() {
        Assert.checkState(this.isInCallButtonUiReady);
        this.inCallButtonUi = null;
        InCallPresenter.getInstance().removeListener(this);
        AudioModeProvider.getInstance().removeListener(this);
        InCallPresenter.getInstance().removeIncomingCallListener(this);
        InCallPresenter.getInstance().removeDetailsListener(this);
        InCallPresenter.getInstance().getInCallCameraManager().removeCameraSelectionListener(this);
        InCallPresenter.getInstance().removeCanAddCallListener(this);
        CallRecorder.getInstance().removeRecordingProgressListener(this.recordingProgressListener);
        this.isInCallButtonUiReady = false;
        DialerCall dialerCall = this.call;
        if (dialerCall != null) {
            dialerCall.removeListener(this);
        }
    }

    public void onIncomingCall(InCallPresenter.InCallState inCallState, InCallPresenter.InCallState inCallState2, DialerCall dialerCall) {
        onStateChange(inCallState, inCallState2, CallList.getInstance());
    }

    public void onInternationalCallOnWifi() {
    }

    public void onRestoreInstanceState(Bundle bundle) {
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onStateChange(InCallPresenter.InCallState inCallState, InCallPresenter.InCallState inCallState2, CallList callList) {
        DialerCall dialerCall;
        Trace.beginSection("CallButtonPresenter.onStateChange");
        CallRecorder instance = CallRecorder.getInstance();
        boolean z = false;
        boolean z2 = PreferenceManager.getDefaultSharedPreferences(this.context).getBoolean(this.context.getString(R.string.auto_call_recording_key), false);
        DialerCall dialerCall2 = this.call;
        if (dialerCall2 != null) {
            dialerCall2.removeListener(this);
        }
        if (inCallState2 == InCallPresenter.InCallState.OUTGOING) {
            this.call = callList.getOutgoingCall();
        } else if (inCallState2 == InCallPresenter.InCallState.INCALL) {
            this.call = callList.getActiveOrBackgroundCall();
            if (!this.isRecording && z2 && this.call != null) {
                this.isRecording = true;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        CallButtonPresenter.this.callRecordClicked(true);
                    }
                }, 500);
            }
            if (inCallState == InCallPresenter.InCallState.OUTGOING && (dialerCall = this.call) != null && dialerCall.isVoiceMailNumber() && getActivity() != null) {
                getActivity().showDialpadFragment(true, true);
            }
        } else if (inCallState2 == InCallPresenter.InCallState.INCOMING) {
            if (getActivity() != null) {
                getActivity().showDialpadFragment(false, true);
            }
            this.call = callList.getIncomingCall();
        } else {
            if (z2 && instance.isRecording()) {
                instance.finishRecording();
            }
            this.call = null;
        }
        DialerCall dialerCall3 = this.call;
        if (dialerCall3 != null) {
            dialerCall3.addListener(this);
        }
        DialerCall dialerCall4 = this.call;
        new Object[1][0] = dialerCall4;
        InCallButtonUi inCallButtonUi2 = this.inCallButtonUi;
        if (inCallButtonUi2 != null) {
            if (dialerCall4 != null) {
                inCallButtonUi2.updateInCallButtonUiColors(InCallPresenter.getInstance().getThemeColorManager().getSecondaryColor());
            }
            if (inCallState2.isConnectingOrConnected()) {
                if (!(inCallState2 == InCallPresenter.InCallState.INCOMING) && dialerCall4 != null) {
                    z = true;
                }
            }
            this.inCallButtonUi.setEnabled(z);
            if (dialerCall4 != null) {
                updateButtonsState(dialerCall4);
            }
        }
        Trace.endSection();
    }

    public void onWiFiToLteHandover() {
    }

    public void pauseVideoClicked(boolean z) {
        DialerImpression$Type dialerImpression$Type;
        Object[] objArr = new Object[1];
        objArr[0] = z ? "pause" : "unpause";
        LogUtil.m9i("CallButtonPresenter.pauseVideoClicked", "%s", objArr);
        LoggingBindings loggingBindings = Logger.get(this.context);
        if (z) {
            dialerImpression$Type = DialerImpression$Type.IN_CALL_SCREEN_TURN_OFF_VIDEO;
        } else {
            dialerImpression$Type = DialerImpression$Type.IN_CALL_SCREEN_TURN_ON_VIDEO;
        }
        ((LoggingBindingsStub) loggingBindings).logCallImpression(dialerImpression$Type, this.call.getUniqueCallId(), this.call.getTimeAddedMs());
        if (z) {
            this.call.getVideoTech().stopTransmission();
        } else {
            updateCamera(InCallPresenter.getInstance().getInCallCameraManager().isUsingFrontFacingCamera());
            this.call.getVideoTech().resumeTransmission(this.context);
        }
        this.inCallButtonUi.setVideoPaused(z);
        this.inCallButtonUi.enableButton(10, false);
    }

    public void setAudioRoute(int i) {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("sending new audio route: ");
        outline13.append(CallAudioState.audioRouteToString(i));
        LogUtil.m9i("CallButtonPresenter.setAudioRoute", outline13.toString(), new Object[0]);
        TelecomAdapter.getInstance().setAudioRoute(i);
    }

    public void showAudioRouteSelector() {
        this.inCallButtonUi.showAudioRouteSelector();
    }

    public void showDialpadClicked(boolean z) {
        ((LoggingBindingsStub) Logger.get(this.context)).logCallImpression(DialerImpression$Type.IN_CALL_SHOW_DIALPAD_BUTTON_PRESSED, this.call.getUniqueCallId(), this.call.getTimeAddedMs());
        "show dialpad " + String.valueOf(z);
        getActivity().showDialpadFragment(z, true);
    }

    public void swapClicked() {
        if (this.call != null) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("swapping the call: ");
            outline13.append(this.call);
            LogUtil.m9i("CallButtonPresenter", outline13.toString(), new Object[0]);
            TelecomAdapter.getInstance().swap(this.call.getId());
        }
    }

    public void swapSimClicked() {
        LogUtil.enterBlock("CallButtonPresenter.swapSimClicked");
        ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.DUAL_SIM_CHANGE_SIM_PRESSED);
        ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(this.context).dialerExecutorFactory()).createNonUiTaskBuilder(new SwapSimWorker(this.context, this.call, InCallPresenter.getInstance().getCallList(), this.otherAccount, InCallPresenter.getInstance().acquireInCallUiLock("swapSim"))).build().executeParallel(null);
    }

    public void toggleCameraClicked() {
        LogUtil.m9i("CallButtonPresenter.toggleCameraClicked", "", new Object[0]);
        if (this.call != null) {
            ((LoggingBindingsStub) Logger.get(this.context)).logCallImpression(DialerImpression$Type.IN_CALL_SCREEN_SWAP_CAMERA, this.call.getUniqueCallId(), this.call.getTimeAddedMs());
            updateCamera(!InCallPresenter.getInstance().getInCallCameraManager().isUsingFrontFacingCamera());
        }
    }

    public void toggleSpeakerphone() {
        CallAudioState audioState = AudioModeProvider.getInstance().getAudioState();
        if ((audioState.getSupportedRouteMask() & 2) != 0) {
            LogUtil.m8e("CallButtonPresenter", "toggling speakerphone not allowed when bluetooth supported.", new Object[0]);
            this.inCallButtonUi.setAudioState(audioState);
            return;
        }
        int i = 8;
        if (audioState.getRoute() == 8) {
            i = 5;
            ((LoggingBindingsStub) Logger.get(this.context)).logCallImpression(DialerImpression$Type.IN_CALL_SCREEN_TURN_ON_WIRED_OR_EARPIECE, this.call.getUniqueCallId(), this.call.getTimeAddedMs());
        } else {
            ((LoggingBindingsStub) Logger.get(this.context)).logCallImpression(DialerImpression$Type.IN_CALL_SCREEN_TURN_ON_SPEAKERPHONE, this.call.getUniqueCallId(), this.call.getTimeAddedMs());
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("sending new audio route: ");
        outline13.append(CallAudioState.audioRouteToString(i));
        LogUtil.m9i("CallButtonPresenter.setAudioRoute", outline13.toString(), new Object[0]);
        TelecomAdapter.getInstance().setAudioRoute(i);
    }
}
