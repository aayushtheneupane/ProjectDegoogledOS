package com.android.incallui;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.telecom.Call;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.rtt.RttTranscript;
import com.android.incallui.InCallPresenter;
import com.android.incallui.RttCallPresenter;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import com.android.incallui.rtt.impl.RttChatFragment;
import com.android.incallui.rtt.protocol.RttCallScreen;
import com.android.incallui.rtt.protocol.RttCallScreenDelegate;
import java.io.IOException;

@TargetApi(28)
public class RttCallPresenter implements RttCallScreenDelegate, InCallPresenter.InCallStateListener {
    private HandlerThread handlerThread;
    private RemoteMessageHandler remoteMessageHandler;
    private Call.RttCall rttCall;
    private RttCallScreen rttCallScreen;

    private static class RemoteMessageHandler extends Handler {
        private final Call.RttCall rttCall;
        private final RttCallScreen rttCallScreen;

        RemoteMessageHandler(Looper looper, Call.RttCall rttCall2, RttCallScreen rttCallScreen2) {
            super(looper);
            this.rttCall = rttCall2;
            this.rttCallScreen = rttCallScreen2;
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                sendEmptyMessage(2);
            } else if (i == 2) {
                try {
                    String readImmediately = this.rttCall.readImmediately();
                    if (readImmediately != null) {
                        DialerExecutorModule.postOnUiThread(new Runnable(readImmediately) {
                            private final /* synthetic */ String f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void run() {
                                RttCallPresenter.RemoteMessageHandler.this.lambda$handleMessage$0$RttCallPresenter$RemoteMessageHandler(this.f$1);
                            }
                        });
                    }
                } catch (IOException e) {
                    LogUtil.m7e("RttCallPresenter.RemoteMessageHandler.handleMessage", "read message", (Throwable) e);
                }
                sendEmptyMessageDelayed(2, 200);
            } else if (i == 3) {
                try {
                    this.rttCall.write((String) message.obj);
                } catch (IOException e2) {
                    LogUtil.m7e("RttCallPresenter.RemoteMessageHandler.handleMessage", "write message", (Throwable) e2);
                }
            }
        }

        public /* synthetic */ void lambda$handleMessage$0$RttCallPresenter$RemoteMessageHandler(String str) {
            ((RttChatFragment) this.rttCallScreen).onRemoteMessage(str);
        }
    }

    private void startListenOnRemoteMessage() {
        DialerCall callById = CallList.getInstance().getCallById(((RttChatFragment) this.rttCallScreen).getCallId());
        if (callById == null) {
            LogUtil.m9i("RttCallPresenter.startListenOnRemoteMessage", "call does not exist", new Object[0]);
            return;
        }
        this.rttCall = callById.getRttCall();
        if (this.rttCall == null) {
            LogUtil.m9i("RttCallPresenter.startListenOnRemoteMessage", "RTT Call is not started yet", new Object[0]);
            return;
        }
        HandlerThread handlerThread2 = this.handlerThread;
        if (handlerThread2 == null || !handlerThread2.isAlive()) {
            this.handlerThread = new HandlerThread("RttCallRemoteMessageHandler");
            this.handlerThread.start();
            this.remoteMessageHandler = new RemoteMessageHandler(this.handlerThread.getLooper(), this.rttCall, this.rttCallScreen);
            this.remoteMessageHandler.sendEmptyMessage(1);
            return;
        }
        LogUtil.m9i("RttCallPresenter.startListenOnRemoteMessage", "already running", new Object[0]);
    }

    public void initRttCallScreenDelegate(RttCallScreen rttCallScreen2) {
        this.rttCallScreen = rttCallScreen2;
    }

    public void onLocalMessage(String str) {
        if (this.rttCall == null) {
            LogUtil.m10w("RttCallPresenter.onLocalMessage", "Rtt Call is not started yet", new Object[0]);
            return;
        }
        RemoteMessageHandler remoteMessageHandler2 = this.remoteMessageHandler;
        remoteMessageHandler2.sendMessage(remoteMessageHandler2.obtainMessage(3, str));
    }

    public void onRttCallScreenUiReady() {
        LogUtil.enterBlock("RttCallPresenter.onRttCallScreenUiReady");
        InCallPresenter.getInstance().addListener(this);
        startListenOnRemoteMessage();
        DialerCall callById = CallList.getInstance().getCallById(((RttChatFragment) this.rttCallScreen).getCallId());
        if (callById != null) {
            ((RttChatFragment) this.rttCallScreen).onRestoreRttChat(callById.getRttTranscript());
        }
    }

    public void onRttCallScreenUiUnready() {
        LogUtil.enterBlock("RttCallPresenter.onRttCallScreenUiUnready");
        InCallPresenter.getInstance().removeListener(this);
        HandlerThread handlerThread2 = this.handlerThread;
        if (handlerThread2 != null && handlerThread2.isAlive()) {
            this.handlerThread.quit();
        }
        onSaveRttTranscript();
    }

    public void onSaveRttTranscript() {
        LogUtil.enterBlock("RttCallPresenter.onSaveRttTranscript");
        DialerCall callById = CallList.getInstance().getCallById(((RttChatFragment) this.rttCallScreen).getCallId());
        if (callById != null) {
            LogUtil.enterBlock("RttCallPresenter.saveTranscript");
            RttTranscript.Builder newBuilder = RttTranscript.newBuilder();
            newBuilder.setId(String.valueOf(callById.getCreationTimeMillis()));
            newBuilder.setTimestamp(callById.getCreationTimeMillis());
            newBuilder.setNumber(callById.getNumber());
            newBuilder.addAllMessages(((RttChatFragment) this.rttCallScreen).getRttTranscriptMessageList());
            callById.setRttTranscript((RttTranscript) newBuilder.build());
        }
    }

    public void onStateChange(InCallPresenter.InCallState inCallState, InCallPresenter.InCallState inCallState2, CallList callList) {
        LogUtil.enterBlock("RttCallPresenter.onStateChange");
        if (inCallState2 == InCallPresenter.InCallState.INCALL) {
            startListenOnRemoteMessage();
        }
    }
}
