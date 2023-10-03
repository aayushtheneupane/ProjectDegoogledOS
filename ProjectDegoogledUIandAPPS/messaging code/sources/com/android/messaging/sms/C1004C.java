package com.android.messaging.sms;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.receiver.SendStatusReceiver;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1474sa;
import com.android.messaging.util.C1486ya;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.android.messaging.sms.C */
public class C1004C {

    /* renamed from: gF */
    private static ConcurrentHashMap f1460gF = new ConcurrentHashMap();

    /* renamed from: hF */
    private static Boolean f1461hF = null;

    static {
        new Random();
    }

    /* renamed from: a */
    public static void m2332a(Uri uri, int i, int i2, int i3, int i4) {
        String str;
        if (i != -1) {
            C1430e.m3622e("MessagingApp", "SmsSender: failure in sending message part.  requestId=" + uri + " partId=" + i3 + " resultCode=" + i + " errorCode=" + i2);
            if (i2 != -1) {
                Context applicationContext = C0967f.get().getApplicationContext();
                String carrierName = C1474sa.get(i4).getCarrierName();
                if (TextUtils.isEmpty(carrierName)) {
                    str = applicationContext.getString(R.string.carrier_send_error_unknown_carrier, new Object[]{Integer.valueOf(i2)});
                } else {
                    str = applicationContext.getString(R.string.carrier_send_error, new Object[]{carrierName, Integer.valueOf(i2)});
                }
                C1486ya.m3846Ma(str);
            }
        } else if (Log.isLoggable("MessagingApp", 2)) {
            C1430e.m3628v("MessagingApp", "SmsSender: received sent result.  requestId=" + uri + " partId=" + i3 + " resultCode=" + i);
        }
        if (uri != null) {
            C1003B b = (C1003B) f1460gF.get(uri);
            if (b != null) {
                synchronized (b) {
                    b.mo6782Ha(i);
                    if (!b.mo6784Oi()) {
                        b.notifyAll();
                    }
                }
                return;
            }
            C1430e.m3622e("MessagingApp", "SmsSender: ignoring sent result.  requestId=" + uri + " partId=" + i3 + " resultCode=" + i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b7, code lost:
        if (r6 != false) goto L_0x00b9;
     */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0220  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.android.messaging.sms.C1003B m2331a(android.content.Context r19, int r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, boolean r24, android.net.Uri r25) {
        /*
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            r4 = r24
            r5 = r25
            r6 = 2
            java.lang.String r7 = "MessagingApp"
            boolean r6 = android.util.Log.isLoggable(r7, r6)
            if (r6 == 0) goto L_0x004e
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "SmsSender: sending message. dest="
            r6.append(r7)
            r6.append(r2)
            java.lang.String r7 = " message="
            r6.append(r7)
            r6.append(r3)
            java.lang.String r7 = " serviceCenter="
            r6.append(r7)
            r7 = r23
            r6.append(r7)
            java.lang.String r8 = " requireDeliveryReport="
            r6.append(r8)
            r6.append(r4)
            java.lang.String r8 = " requestId="
            r6.append(r8)
            r6.append(r5)
            java.lang.String r6 = r6.toString()
            java.lang.String r8 = "MessagingApp"
            com.android.messaging.util.C1430e.m3628v(r8, r6)
            goto L_0x0050
        L_0x004e:
            r7 = r23
        L_0x0050:
            boolean r6 = android.text.TextUtils.isEmpty(r22)
            if (r6 != 0) goto L_0x0228
            com.android.messaging.sms.t r6 = com.android.messaging.sms.C1024t.get(r20)
            java.lang.String r6 = r6.mo6840ki()
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            r8 = 0
            r9 = 1
            if (r6 != 0) goto L_0x00cd
            boolean r6 = com.android.messaging.sms.C1027w.isEmailAddress(r21)
            if (r6 != 0) goto L_0x00b9
            com.android.messaging.sms.t r6 = com.android.messaging.sms.C1024t.get(r20)
            boolean r6 = r6.mo6833Bi()
            if (r6 != 0) goto L_0x0077
            goto L_0x00b6
        L_0x0077:
            if (r2 != 0) goto L_0x007b
            r6 = r8
            goto L_0x007f
        L_0x007b:
            int r6 = r21.length()
        L_0x007f:
            com.android.messaging.sms.t r10 = com.android.messaging.sms.C1024t.get(r20)
            int r10 = r10.mo6838ji()
            if (r6 < r10) goto L_0x00b6
            com.android.messaging.sms.t r10 = com.android.messaging.sms.C1024t.get(r20)
            int r10 = r10.mo6837ii()
            if (r6 <= r10) goto L_0x0094
            goto L_0x00b6
        L_0x0094:
            char r10 = r2.charAt(r8)
            boolean r10 = java.lang.Character.isLetter(r10)
            if (r10 != 0) goto L_0x009f
            goto L_0x00b6
        L_0x009f:
            r10 = r9
        L_0x00a0:
            if (r10 >= r6) goto L_0x00b4
            char r11 = r2.charAt(r10)
            boolean r12 = java.lang.Character.isLetterOrDigit(r11)
            if (r12 != 0) goto L_0x00b1
            r12 = 46
            if (r11 == r12) goto L_0x00b1
            goto L_0x00b6
        L_0x00b1:
            int r10 = r10 + 1
            goto L_0x00a0
        L_0x00b4:
            r6 = r9
            goto L_0x00b7
        L_0x00b6:
            r6 = r8
        L_0x00b7:
            if (r6 == 0) goto L_0x00cd
        L_0x00b9:
            java.lang.String r6 = " "
            java.lang.String r2 = p026b.p027a.p030b.p031a.C0632a.m1023d(r2, r6, r3)
            com.android.messaging.sms.t r3 = com.android.messaging.sms.C1024t.get(r20)
            java.lang.String r3 = r3.mo6840ki()
            r18 = r3
            r3 = r2
            r2 = r18
            goto L_0x00d1
        L_0x00cd:
            java.lang.String r2 = android.telephony.PhoneNumberUtils.stripSeparators(r21)
        L_0x00d1:
            boolean r6 = android.text.TextUtils.isEmpty(r2)
            if (r6 != 0) goto L_0x0220
            com.android.messaging.util.sa r6 = com.android.messaging.util.C1474sa.get(r20)
            android.telephony.SmsManager r6 = r6.mo8214ik()
            java.util.ArrayList r6 = r6.divideMessage(r3)
            if (r6 == 0) goto L_0x0218
            int r10 = r6.size()
            if (r10 < r9) goto L_0x0218
            com.android.messaging.sms.B r14 = new com.android.messaging.sms.B
            int r10 = r6.size()
            r14.<init>(r10)
            java.util.concurrent.ConcurrentHashMap r10 = f1460gF
            r10.put(r5, r14)
            com.android.messaging.util.C1424b.m3594t(r19)
            com.android.messaging.util.sa r10 = com.android.messaging.util.C1474sa.get(r20)
            android.telephony.SmsManager r15 = r10.mo8214ik()
            int r13 = r6.size()
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>(r13)
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>(r13)
            r10 = r9
            r9 = r8
        L_0x0114:
            if (r9 >= r13) goto L_0x014c
            if (r13 > r10) goto L_0x011a
            r10 = r8
            goto L_0x011c
        L_0x011a:
            int r10 = r9 + 1
        L_0x011c:
            if (r4 == 0) goto L_0x0133
            int r8 = r13 + -1
            if (r9 != r8) goto L_0x0131
            java.lang.String r8 = "com.android.messaging.receiver.SendStatusReceiver.MESSAGE_DELIVERED"
            android.content.Intent r8 = m2330a((android.content.Context) r0, (java.lang.String) r8, (android.net.Uri) r5, (int) r10, (int) r1)
            r4 = 0
            android.app.PendingIntent r8 = android.app.PendingIntent.getBroadcast(r0, r10, r8, r4)
            r12.add(r8)
            goto L_0x0138
        L_0x0131:
            r4 = 0
            goto L_0x0134
        L_0x0133:
            r4 = r8
        L_0x0134:
            r8 = 0
            r12.add(r8)
        L_0x0138:
            java.lang.String r8 = "com.android.messaging.receiver.SendStatusReceiver.MESSAGE_SENT"
            android.content.Intent r8 = m2330a((android.content.Context) r0, (java.lang.String) r8, (android.net.Uri) r5, (int) r10, (int) r1)
            android.app.PendingIntent r8 = android.app.PendingIntent.getBroadcast(r0, r10, r8, r4)
            r11.add(r8)
            int r9 = r9 + 1
            r10 = 1
            r8 = r4
            r4 = r24
            goto L_0x0114
        L_0x014c:
            r4 = r8
            java.lang.Boolean r0 = f1461hF
            if (r0 != 0) goto L_0x015f
            com.android.messaging.sms.t r0 = com.android.messaging.sms.C1024t.get(r20)
            boolean r0 = r0.mo6850vi()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            f1461hF = r0
        L_0x015f:
            java.lang.Boolean r0 = f1461hF     // Catch:{ Exception -> 0x020b }
            boolean r0 = r0.booleanValue()     // Catch:{ Exception -> 0x020b }
            if (r0 == 0) goto L_0x0193
        L_0x0167:
            if (r4 >= r13) goto L_0x01a1
            java.lang.Object r0 = r6.get(r4)     // Catch:{ Exception -> 0x020b }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x020b }
            java.lang.Object r1 = r11.get(r4)     // Catch:{ Exception -> 0x020b }
            android.app.PendingIntent r1 = (android.app.PendingIntent) r1     // Catch:{ Exception -> 0x020b }
            java.lang.Object r8 = r12.get(r4)     // Catch:{ Exception -> 0x020b }
            r16 = r8
            android.app.PendingIntent r16 = (android.app.PendingIntent) r16     // Catch:{ Exception -> 0x020b }
            r8 = r15
            r9 = r2
            r10 = r23
            r17 = r11
            r11 = r0
            r0 = r12
            r12 = r1
            r1 = r13
            r13 = r16
            r8.sendTextMessage(r9, r10, r11, r12, r13)     // Catch:{ Exception -> 0x020b }
            int r4 = r4 + 1
            r12 = r0
            r13 = r1
            r11 = r17
            goto L_0x0167
        L_0x0193:
            r17 = r11
            r0 = r12
            r8 = r15
            r9 = r2
            r10 = r23
            r11 = r6
            r12 = r17
            r13 = r0
            r8.sendMultipartTextMessage(r9, r10, r11, r12, r13)     // Catch:{ Exception -> 0x020b }
        L_0x01a1:
            monitor-enter(r14)
            com.android.messaging.util.g r0 = com.android.messaging.util.C1449g.get()     // Catch:{ all -> 0x0208 }
            java.lang.String r1 = "bugle_sms_send_timeout"
            r6 = 300000(0x493e0, double:1.482197E-318)
            r0.getLong(r1, r6)     // Catch:{ all -> 0x0208 }
            long r0 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x0208 }
            r8 = r6
        L_0x01b3:
            boolean r4 = r14.mo6784Oi()     // Catch:{ all -> 0x0208 }
            if (r4 == 0) goto L_0x01d2
            r10 = 0
            int r4 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r4 <= 0) goto L_0x01d2
            r14.wait(r8)     // Catch:{ InterruptedException -> 0x01c3 }
            goto L_0x01ca
        L_0x01c3:
            java.lang.String r4 = "MessagingApp"
            java.lang.String r8 = "SmsSender: sending wait interrupted"
            com.android.messaging.util.C1430e.m3622e(r4, r8)     // Catch:{ all -> 0x0208 }
        L_0x01ca:
            long r8 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x0208 }
            long r8 = r8 - r0
            long r8 = r6 - r8
            goto L_0x01b3
        L_0x01d2:
            monitor-exit(r14)     // Catch:{ all -> 0x0208 }
            java.util.concurrent.ConcurrentHashMap r0 = f1460gF
            r0.remove(r5)
            java.lang.String r0 = "MessagingApp"
            r1 = 2
            boolean r0 = android.util.Log.isLoggable(r0, r1)
            if (r0 == 0) goto L_0x0207
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "SmsSender: sending completed. dest="
            r0.append(r1)
            r0.append(r2)
            java.lang.String r1 = " message="
            r0.append(r1)
            r0.append(r3)
            java.lang.String r1 = " result="
            r0.append(r1)
            r0.append(r14)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "MessagingApp"
            com.android.messaging.util.C1430e.m3628v(r1, r0)
        L_0x0207:
            return r14
        L_0x0208:
            r0 = move-exception
            monitor-exit(r14)     // Catch:{ all -> 0x0208 }
            throw r0
        L_0x020b:
            r0 = move-exception
            com.android.messaging.sms.SmsException r1 = new com.android.messaging.sms.SmsException
            java.lang.String r2 = "SmsSender: caught exception in sending "
            java.lang.String r0 = p026b.p027a.p030b.p031a.C0632a.m1014a(r2, r0)
            r1.<init>(r0)
            throw r1
        L_0x0218:
            com.android.messaging.sms.SmsException r0 = new com.android.messaging.sms.SmsException
            java.lang.String r1 = "SmsSender: fails to divide message"
            r0.<init>(r1)
            throw r0
        L_0x0220:
            com.android.messaging.sms.SmsException r0 = new com.android.messaging.sms.SmsException
            java.lang.String r1 = "SmsSender: empty destination address"
            r0.<init>(r1)
            throw r0
        L_0x0228:
            com.android.messaging.sms.SmsException r0 = new com.android.messaging.sms.SmsException
            java.lang.String r1 = "SmsSender: empty text message"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.sms.C1004C.m2331a(android.content.Context, int, java.lang.String, java.lang.String, java.lang.String, boolean, android.net.Uri):com.android.messaging.sms.B");
    }

    /* renamed from: a */
    private static Intent m2330a(Context context, String str, Uri uri, int i, int i2) {
        Intent intent = new Intent(str, uri, context, SendStatusReceiver.class);
        intent.putExtra("partId", i);
        intent.putExtra("subId", i2);
        return intent;
    }
}
