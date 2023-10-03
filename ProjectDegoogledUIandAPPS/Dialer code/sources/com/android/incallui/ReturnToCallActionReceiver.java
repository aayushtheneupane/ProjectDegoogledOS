package com.android.incallui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;

public class ReturnToCallActionReceiver extends BroadcastReceiver {
    private DialerCall getCall() {
        CallList callList = InCallPresenter.getInstance().getCallList();
        if (callList == null) {
            return null;
        }
        DialerCall outgoingCall = callList.getOutgoingCall();
        if (outgoingCall == null) {
            outgoingCall = callList.getActiveOrBackgroundCall();
        }
        if (outgoingCall != null) {
            return outgoingCall;
        }
        return null;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r10, android.content.Intent r11) {
        /*
            r9 = this;
            java.lang.String r0 = r11.getAction()
            int r1 = r0.hashCode()
            r2 = 4
            r3 = 3
            r4 = 0
            r5 = 2
            r6 = 1
            switch(r1) {
                case -161527321: goto L_0x0039;
                case 201682379: goto L_0x002f;
                case 858442021: goto L_0x0025;
                case 949883625: goto L_0x001b;
                case 1133414965: goto L_0x0011;
                default: goto L_0x0010;
            }
        L_0x0010:
            goto L_0x0043
        L_0x0011:
            java.lang.String r1 = "endCallV2"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0043
            r0 = r2
            goto L_0x0044
        L_0x001b:
            java.lang.String r1 = "toggleMuteV2"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0043
            r0 = r3
            goto L_0x0044
        L_0x0025:
            java.lang.String r1 = "returnToCallV2"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0043
            r0 = r4
            goto L_0x0044
        L_0x002f:
            java.lang.String r1 = "showAudioRouteSelectorV2"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0043
            r0 = r5
            goto L_0x0044
        L_0x0039:
            java.lang.String r1 = "toggleSpeakerV2"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0043
            r0 = r6
            goto L_0x0044
        L_0x0043:
            r0 = -1
        L_0x0044:
            r7 = 0
            java.lang.String r1 = ""
            if (r0 == 0) goto L_0x0122
            if (r0 == r6) goto L_0x00c6
            if (r0 == r5) goto L_0x00c1
            if (r0 == r3) goto L_0x008b
            if (r0 != r2) goto L_0x0074
            com.android.incallui.call.DialerCall r9 = r9.getCall()
            com.android.dialer.logging.LoggingBindings r10 = com.android.dialer.logging.Logger.get(r10)
            com.android.dialer.logging.DialerImpression$Type r11 = com.android.dialer.logging.DialerImpression$Type.BUBBLE_V2_END_CALL
            if (r9 == 0) goto L_0x0062
            java.lang.String r1 = r9.getUniqueCallId()
        L_0x0062:
            if (r9 == 0) goto L_0x0068
            long r7 = r9.getTimeAddedMs()
        L_0x0068:
            com.android.dialer.logging.LoggingBindingsStub r10 = (com.android.dialer.logging.LoggingBindingsStub) r10
            r10.logCallImpression(r11, r1, r7)
            if (r9 == 0) goto L_0x0149
            r9.disconnect()
            goto L_0x0149
        L_0x0074:
            java.lang.String r9 = "Invalid intent action: "
            java.lang.StringBuilder r9 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r9)
            java.lang.String r10 = r11.getAction()
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            r10.<init>(r9)
            throw r10
        L_0x008b:
            com.android.incallui.call.DialerCall r9 = r9.getCall()
            com.android.incallui.audiomode.AudioModeProvider r11 = com.android.incallui.audiomode.AudioModeProvider.getInstance()
            android.telecom.CallAudioState r11 = r11.getAudioState()
            boolean r11 = r11.isMuted()
            r11 = r11 ^ r6
            com.android.dialer.logging.LoggingBindings r10 = com.android.dialer.logging.Logger.get(r10)
            if (r11 == 0) goto L_0x00a5
            com.android.dialer.logging.DialerImpression$Type r0 = com.android.dialer.logging.DialerImpression$Type.BUBBLE_V2_MUTE_CALL
            goto L_0x00a7
        L_0x00a5:
            com.android.dialer.logging.DialerImpression$Type r0 = com.android.dialer.logging.DialerImpression$Type.BUBBLE_V2_UNMUTE_CALL
        L_0x00a7:
            if (r9 == 0) goto L_0x00ad
            java.lang.String r1 = r9.getUniqueCallId()
        L_0x00ad:
            if (r9 == 0) goto L_0x00b3
            long r7 = r9.getTimeAddedMs()
        L_0x00b3:
            com.android.dialer.logging.LoggingBindingsStub r10 = (com.android.dialer.logging.LoggingBindingsStub) r10
            r10.logCallImpression(r0, r1, r7)
            com.android.incallui.call.TelecomAdapter r9 = com.android.incallui.call.TelecomAdapter.getInstance()
            r9.mute(r11)
            goto L_0x0149
        L_0x00c1:
            r9.showAudioRouteSelector(r10)
            goto L_0x0149
        L_0x00c6:
            com.android.incallui.audiomode.AudioModeProvider r11 = com.android.incallui.audiomode.AudioModeProvider.getInstance()
            android.telecom.CallAudioState r11 = r11.getAudioState()
            int r0 = r11.getSupportedRouteMask()
            r0 = r0 & r5
            if (r0 != r5) goto L_0x00de
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r2 = "ReturnToCallActionReceiver.toggleSpeaker"
            java.lang.String r3 = "toggleSpeaker() called when bluetooth available. Probably should have shown audio route selector"
            com.android.dialer.common.LogUtil.m10w(r2, r3, r0)
        L_0x00de:
            com.android.incallui.call.DialerCall r9 = r9.getCall()
            int r11 = r11.getRoute()
            r0 = 8
            if (r11 != r0) goto L_0x0103
            r0 = 5
            com.android.dialer.logging.LoggingBindings r10 = com.android.dialer.logging.Logger.get(r10)
            com.android.dialer.logging.DialerImpression$Type r11 = com.android.dialer.logging.DialerImpression$Type.BUBBLE_V2_WIRED_OR_EARPIECE
            if (r9 == 0) goto L_0x00f7
            java.lang.String r1 = r9.getUniqueCallId()
        L_0x00f7:
            if (r9 == 0) goto L_0x00fd
            long r7 = r9.getTimeAddedMs()
        L_0x00fd:
            com.android.dialer.logging.LoggingBindingsStub r10 = (com.android.dialer.logging.LoggingBindingsStub) r10
            r10.logCallImpression(r11, r1, r7)
            goto L_0x011a
        L_0x0103:
            com.android.dialer.logging.LoggingBindings r10 = com.android.dialer.logging.Logger.get(r10)
            com.android.dialer.logging.DialerImpression$Type r11 = com.android.dialer.logging.DialerImpression$Type.BUBBLE_V2_SPEAKERPHONE
            if (r9 == 0) goto L_0x010f
            java.lang.String r1 = r9.getUniqueCallId()
        L_0x010f:
            if (r9 == 0) goto L_0x0115
            long r7 = r9.getTimeAddedMs()
        L_0x0115:
            com.android.dialer.logging.LoggingBindingsStub r10 = (com.android.dialer.logging.LoggingBindingsStub) r10
            r10.logCallImpression(r11, r1, r7)
        L_0x011a:
            com.android.incallui.call.TelecomAdapter r9 = com.android.incallui.call.TelecomAdapter.getInstance()
            r9.setAudioRoute(r0)
            goto L_0x0149
        L_0x0122:
            com.android.incallui.call.DialerCall r9 = r9.getCall()
            com.android.dialer.logging.LoggingBindings r11 = com.android.dialer.logging.Logger.get(r10)
            com.android.dialer.logging.DialerImpression$Type r0 = com.android.dialer.logging.DialerImpression$Type.BUBBLE_V2_RETURN_TO_CALL
            if (r9 == 0) goto L_0x0132
            java.lang.String r1 = r9.getUniqueCallId()
        L_0x0132:
            if (r9 == 0) goto L_0x0138
            long r7 = r9.getTimeAddedMs()
        L_0x0138:
            com.android.dialer.logging.LoggingBindingsStub r11 = (com.android.dialer.logging.LoggingBindingsStub) r11
            r11.logCallImpression(r0, r1, r7)
            android.content.Intent r9 = com.android.incallui.InCallActivity.getIntent(r10, r4, r4, r4)
            r11 = 268435456(0x10000000, float:2.5243549E-29)
            r9.addFlags(r11)
            r10.startActivity(r9)
        L_0x0149:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.ReturnToCallActionReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }

    public void showAudioRouteSelector(Context context) {
        Intent intent = new Intent(context, AudioRouteSelectorActivity.class);
        intent.setFlags(402653184);
        context.startActivity(intent);
    }
}
