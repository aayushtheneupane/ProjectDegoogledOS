package com.android.voicemail.impl.protocol;

import android.content.Context;
import android.telecom.PhoneAccountHandle;
import com.android.voicemail.VoicemailComponent;
import com.android.voicemail.impl.VvmLog;

public class Vvm3EventHandler {
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void handleEvent(android.content.Context r7, com.android.voicemail.impl.OmtpVvmCarrierConfigHelper r8, com.android.voicemail.impl.VoicemailStatus$Editor r9, com.android.voicemail.impl.OmtpEvents r10) {
        /*
            int r0 = r10.getType()
            r1 = 4
            r2 = 2
            r3 = 1
            r4 = 0
            if (r0 == r3) goto L_0x00ce
            r5 = -9996(0xffffffffffffd8f4, float:NaN)
            r6 = -9994(0xffffffffffffd8f6, float:NaN)
            if (r0 == r2) goto L_0x0079
            r2 = 3
            if (r0 == r2) goto L_0x010d
            if (r0 == r1) goto L_0x0035
            java.lang.String r0 = "invalid event type "
            java.lang.StringBuilder r0 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r0)
            int r1 = r10.getType()
            r0.append(r1)
            java.lang.String r1 = " for "
            r0.append(r1)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "Vvm3EventHandler"
            com.android.voicemail.impl.VvmLog.wtf(r1, r0)
            goto L_0x010d
        L_0x0035:
            int r0 = r10.ordinal()
            switch(r0) {
                case 33: goto L_0x0074;
                case 34: goto L_0x006d;
                case 35: goto L_0x0066;
                case 36: goto L_0x005f;
                case 37: goto L_0x0058;
                case 38: goto L_0x0051;
                case 39: goto L_0x003c;
                case 40: goto L_0x004c;
                case 41: goto L_0x0045;
                case 42: goto L_0x003e;
                default: goto L_0x003c;
            }
        L_0x003c:
            goto L_0x010d
        L_0x003e:
            r0 = -99
            postError(r9, r0)
            goto L_0x0112
        L_0x0045:
            r0 = -9990(0xffffffffffffd8fa, float:NaN)
            postError(r9, r0)
            goto L_0x0112
        L_0x004c:
            postError(r9, r5)
            goto L_0x0112
        L_0x0051:
            r0 = -9008(0xffffffffffffdcd0, float:NaN)
            postError(r9, r0)
            goto L_0x0112
        L_0x0058:
            r0 = -9006(0xffffffffffffdcd2, float:NaN)
            postError(r9, r0)
            goto L_0x0112
        L_0x005f:
            r0 = -9005(0xffffffffffffdcd3, float:NaN)
            postError(r9, r0)
            goto L_0x0112
        L_0x0066:
            r0 = -9003(0xffffffffffffdcd5, float:NaN)
            postError(r9, r0)
            goto L_0x0112
        L_0x006d:
            r0 = -9002(0xffffffffffffdcd6, float:NaN)
            postError(r9, r0)
            goto L_0x0112
        L_0x0074:
            postError(r9, r6)
            goto L_0x0112
        L_0x0079:
            int r0 = r10.ordinal()
            r1 = 29
            r2 = -9999(0xffffffffffffd8f1, float:NaN)
            if (r0 == r1) goto L_0x00ca
            switch(r0) {
                case 10: goto L_0x00c4;
                case 11: goto L_0x00c4;
                case 12: goto L_0x00be;
                case 13: goto L_0x00c4;
                case 14: goto L_0x00b8;
                case 15: goto L_0x00b8;
                case 16: goto L_0x00b4;
                case 17: goto L_0x00ae;
                case 18: goto L_0x00a7;
                case 19: goto L_0x00a0;
                case 20: goto L_0x009b;
                case 21: goto L_0x0094;
                case 22: goto L_0x008f;
                case 23: goto L_0x0088;
                case 24: goto L_0x00ca;
                case 25: goto L_0x00ca;
                case 26: goto L_0x00b8;
                default: goto L_0x0086;
            }
        L_0x0086:
            goto L_0x010d
        L_0x0088:
            r0 = -9998(0xffffffffffffd8f2, float:NaN)
            postError(r9, r0)
            goto L_0x0112
        L_0x008f:
            postError(r9, r5)
            goto L_0x0112
        L_0x0094:
            r0 = -9995(0xffffffffffffd8f5, float:NaN)
            postError(r9, r0)
            goto L_0x0112
        L_0x009b:
            postError(r9, r6)
            goto L_0x0112
        L_0x00a0:
            r0 = -9993(0xffffffffffffd8f7, float:NaN)
            postError(r9, r0)
            goto L_0x0112
        L_0x00a7:
            r0 = -9992(0xffffffffffffd8f8, float:NaN)
            postError(r9, r0)
            goto L_0x0112
        L_0x00ae:
            r0 = -9991(0xffffffffffffd8f9, float:NaN)
            postError(r9, r0)
            goto L_0x0112
        L_0x00b4:
            postError(r9, r2)
            goto L_0x0112
        L_0x00b8:
            r0 = -9007(0xffffffffffffdcd1, float:NaN)
            postError(r9, r0)
            goto L_0x0112
        L_0x00be:
            r0 = -9001(0xffffffffffffdcd7, float:NaN)
            postError(r9, r0)
            goto L_0x0112
        L_0x00c4:
            r0 = -9004(0xffffffffffffdcd4, float:NaN)
            postError(r9, r0)
            goto L_0x0112
        L_0x00ca:
            postError(r9, r2)
            goto L_0x0112
        L_0x00ce:
            int r0 = r10.ordinal()
            r5 = -100
            if (r0 == 0) goto L_0x0103
            if (r0 == r2) goto L_0x00ff
            if (r0 == r1) goto L_0x00e4
            r1 = 5
            if (r0 == r1) goto L_0x00de
            goto L_0x010d
        L_0x00de:
            r0 = -9009(0xffffffffffffdccf, float:NaN)
            postError(r9, r0)
            goto L_0x0112
        L_0x00e4:
            android.telecom.PhoneAccountHandle r0 = r9.getPhoneAccountHandle()
            boolean r0 = isPinRandomized(r7, r0)
            if (r0 == 0) goto L_0x00f2
            r9.setConfigurationState(r5)
            goto L_0x00f5
        L_0x00f2:
            r9.setConfigurationState(r4)
        L_0x00f5:
            r9.setNotificationChannelState(r4)
            r9.setDataChannelState(r4)
            r9.apply()
            goto L_0x0112
        L_0x00ff:
            postError(r9, r5)
            goto L_0x0112
        L_0x0103:
            android.telecom.PhoneAccountHandle r0 = r9.getPhoneAccountHandle()
            boolean r0 = isPinRandomized(r7, r0)
            if (r0 != 0) goto L_0x010f
        L_0x010d:
            r3 = r4
            goto L_0x0112
        L_0x010f:
            postError(r9, r5)
        L_0x0112:
            if (r3 != 0) goto L_0x0117
            com.android.voicemail.impl.Assert.handleEvent(r7, r8, r9, r10)
        L_0x0117:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.protocol.Vvm3EventHandler.handleEvent(android.content.Context, com.android.voicemail.impl.OmtpVvmCarrierConfigHelper, com.android.voicemail.impl.VoicemailStatus$Editor, com.android.voicemail.impl.OmtpEvents):void");
    }

    private static boolean isPinRandomized(Context context, PhoneAccountHandle phoneAccountHandle) {
        if (phoneAccountHandle == null) {
            VvmLog.m43e("Vvm3EventHandler", "status editor has null phone account handle");
            return false;
        } else if (VoicemailComponent.get(context).getVoicemailClient().createPinChanger(context, phoneAccountHandle).getScrambledPin() != null) {
            return true;
        } else {
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
        r2.setDataChannelState(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void postError(com.android.voicemail.impl.VoicemailStatus$Editor r2, int r3) {
        /*
            r0 = -301(0xfffffffffffffed3, float:NaN)
            if (r3 == r0) goto L_0x002f
            r0 = -1
            if (r3 == r0) goto L_0x002f
            switch(r3) {
                case -9999: goto L_0x002b;
                case -9998: goto L_0x002f;
                case -9997: goto L_0x002b;
                case -9996: goto L_0x002f;
                case -9995: goto L_0x002f;
                case -9994: goto L_0x002f;
                case -9993: goto L_0x002f;
                case -9992: goto L_0x002f;
                case -9991: goto L_0x002f;
                case -9990: goto L_0x002f;
                case -9989: goto L_0x002b;
                default: goto L_0x000a;
            }
        L_0x000a:
            switch(r3) {
                case -9009: goto L_0x0027;
                case -9008: goto L_0x002f;
                case -9007: goto L_0x002b;
                case -9006: goto L_0x002f;
                case -9005: goto L_0x002f;
                case -9004: goto L_0x002b;
                case -9003: goto L_0x002f;
                case -9002: goto L_0x002f;
                case -9001: goto L_0x002b;
                default: goto L_0x000d;
            }
        L_0x000d:
            switch(r3) {
                case -103: goto L_0x002f;
                case -102: goto L_0x002f;
                case -101: goto L_0x002f;
                case -100: goto L_0x002f;
                case -99: goto L_0x002f;
                default: goto L_0x0010;
            }
        L_0x0010:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "unknown error code: "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            java.lang.String r0 = "Vvm3EventHandler"
            com.android.voicemail.impl.VvmLog.wtf(r0, r3)
            goto L_0x0032
        L_0x0027:
            r2.setNotificationChannelState(r3)
            goto L_0x0032
        L_0x002b:
            r2.setDataChannelState(r3)
            goto L_0x0032
        L_0x002f:
            r2.setConfigurationState(r3)
        L_0x0032:
            r2.apply()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.protocol.Vvm3EventHandler.postError(com.android.voicemail.impl.VoicemailStatus$Editor, int):void");
    }
}
