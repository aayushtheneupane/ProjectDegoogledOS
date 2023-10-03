package com.android.incallui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import com.android.incallui.call.TelecomAdapter;
import com.android.incallui.speakeasy.SpeakEasyCallManager;
import com.android.incallui.speakeasy.SpeakEasyCallManagerStub;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class NotificationBroadcastReceiver extends BroadcastReceiver {
    private void answerIncomingCall(final int i, Context context) {
        ListenableFuture<Void> listenableFuture;
        CallList callList = InCallPresenter.getInstance().getCallList();
        if (callList == null) {
            StatusBarNotifier.clearAllCallNotifications();
            LogUtil.m8e("NotificationBroadcastReceiver.answerIncomingCall", "call list is empty", new Object[0]);
            return;
        }
        final DialerCall incomingCall = callList.getIncomingCall();
        if (incomingCall != null) {
            SpeakEasyCallManager speakEasyCallManager = InCallPresenter.getInstance().getSpeakEasyCallManager();
            if (speakEasyCallManager != null) {
                listenableFuture = ((SpeakEasyCallManagerStub) speakEasyCallManager).onNewIncomingCall(incomingCall);
            } else {
                listenableFuture = Futures.immediateFuture(null);
            }
            Futures.addCallback(listenableFuture, new FutureCallback<Void>() {
                public void onFailure(Throwable th) {
                    NotificationBroadcastReceiver.this.answerIncomingCallCallback(incomingCall, i);
                    throw new RuntimeException("Failed to successfully complete pre call tasks.", th);
                }

                public void onSuccess(Object obj) {
                    Void voidR = (Void) obj;
                    NotificationBroadcastReceiver.this.answerIncomingCallCallback(incomingCall, i);
                }
            }, DialerExecutorComponent.get(context).uiExecutor());
        }
    }

    /* access modifiers changed from: private */
    public void answerIncomingCallCallback(DialerCall dialerCall, int i) {
        dialerCall.answer(i);
        InCallPresenter.getInstance().showInCall(false, false);
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        LogUtil.m9i("NotificationBroadcastReceiver.onReceive", GeneratedOutlineSupport.outline8("Broadcast from Notification: ", action), new Object[0]);
        if (action.equals("com.android.incallui.ACTION_ANSWER_VIDEO_INCOMING_CALL")) {
            answerIncomingCall(3, context);
        } else if (action.equals("com.android.incallui.ACTION_ANSWER_VOICE_INCOMING_CALL")) {
            answerIncomingCall(0, context);
        } else if (action.equals("com.android.incallui.ACTION_ANSWER_SPEAKEASY_CALL")) {
            CallList callList = InCallPresenter.getInstance().getCallList();
            if (callList == null) {
                LogUtil.m8e("NotificationBroadcastReceiver.markIncomingCallAsSpeakeasyCall", "call list is empty", new Object[0]);
            } else {
                DialerCall incomingCall = callList.getIncomingCall();
                if (incomingCall != null) {
                    incomingCall.setIsSpeakEasyCall(true);
                }
            }
            answerIncomingCall(0, context);
        } else if (action.equals("com.android.incallui.ACTION_DECLINE_INCOMING_CALL")) {
            ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.REJECT_INCOMING_CALL_FROM_NOTIFICATION);
            CallList callList2 = InCallPresenter.getInstance().getCallList();
            if (callList2 == null) {
                StatusBarNotifier.clearAllCallNotifications();
                LogUtil.m8e("NotificationBroadcastReceiver.declineIncomingCall", "call list is empty", new Object[0]);
                return;
            }
            DialerCall incomingCall2 = callList2.getIncomingCall();
            if (incomingCall2 != null) {
                incomingCall2.reject(false, (String) null);
            }
        } else if (action.equals("com.android.incallui.ACTION_HANG_UP_ONGOING_CALL")) {
            CallList callList3 = InCallPresenter.getInstance().getCallList();
            if (callList3 == null) {
                StatusBarNotifier.clearAllCallNotifications();
                LogUtil.m8e("NotificationBroadcastReceiver.hangUpOngoingCall", "call list is empty", new Object[0]);
                return;
            }
            DialerCall outgoingCall = callList3.getOutgoingCall();
            if (outgoingCall == null) {
                outgoingCall = callList3.getActiveOrBackgroundCall();
            }
            LogUtil.m9i("NotificationBroadcastReceiver.hangUpOngoingCall", GeneratedOutlineSupport.outline6("disconnecting call, call: ", outgoingCall), new Object[0]);
            if (outgoingCall != null) {
                outgoingCall.disconnect();
            }
        } else if (action.equals("com.android.incallui.ACTION_ACCEPT_VIDEO_UPGRADE_REQUEST")) {
            CallList callList4 = InCallPresenter.getInstance().getCallList();
            if (callList4 == null) {
                StatusBarNotifier.clearAllCallNotifications();
                LogUtil.m8e("NotificationBroadcastReceiver.acceptUpgradeRequest", "call list is empty", new Object[0]);
                return;
            }
            DialerCall videoUpgradeRequestCall = callList4.getVideoUpgradeRequestCall();
            if (videoUpgradeRequestCall != null) {
                videoUpgradeRequestCall.getVideoTech().acceptVideoRequest(context);
            }
        } else if (action.equals("com.android.incallui.ACTION_DECLINE_VIDEO_UPGRADE_REQUEST")) {
            CallList callList5 = InCallPresenter.getInstance().getCallList();
            if (callList5 == null) {
                StatusBarNotifier.clearAllCallNotifications();
                LogUtil.m8e("NotificationBroadcastReceiver.declineUpgradeRequest", "call list is empty", new Object[0]);
                return;
            }
            DialerCall videoUpgradeRequestCall2 = callList5.getVideoUpgradeRequestCall();
            if (videoUpgradeRequestCall2 != null) {
                videoUpgradeRequestCall2.getVideoTech().declineVideoRequest();
            }
        } else if (action.equals("com.android.incallui.ACTION_PULL_EXTERNAL_CALL")) {
            context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
            InCallPresenter.getInstance().getExternalCallNotifier().pullExternalCall(intent.getIntExtra("com.android.incallui.extra.EXTRA_NOTIFICATION_ID", -1));
        } else if (action.equals("com.android.incallui.ACTION_TURN_ON_SPEAKER")) {
            TelecomAdapter.getInstance().setAudioRoute(8);
        } else if (action.equals("com.android.incallui.ACTION_TURN_OFF_SPEAKER")) {
            TelecomAdapter.getInstance().setAudioRoute(5);
        }
    }
}
