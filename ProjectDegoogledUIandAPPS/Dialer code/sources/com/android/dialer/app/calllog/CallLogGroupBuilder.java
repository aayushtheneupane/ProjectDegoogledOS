package com.android.dialer.app.calllog;

import android.content.Context;
import android.telephony.PhoneNumberUtils;
import android.text.format.Time;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import java.util.Objects;

public class CallLogGroupBuilder {
    private static final Time TIME = new Time();
    private final Context appContext;
    private final GroupCreator groupCreator;

    public interface GroupCreator {
        void addGroup(int i, int i2);

        void clearDayGroups();

        void setCallbackAction(long j, int i);

        void setDayGroup(long j, int i);
    }

    public CallLogGroupBuilder(Context context, GroupCreator groupCreator2) {
        this.appContext = context;
        this.groupCreator = groupCreator2;
    }

    private int getDayGroup(long j, long j2) {
        Time time = TIME;
        time.set(j);
        int julianDay = Time.getJulianDay(j, time.gmtoff);
        time.set(j2);
        int abs = Math.abs(Time.getJulianDay(j2, time.gmtoff) - julianDay);
        if (abs == 0) {
            return 0;
        }
        return abs == 1 ? 1 : 2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0115, code lost:
        if ((r8 == 6 && r1 == 6) != false) goto L_0x0117;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addGroups(android.database.Cursor r30) {
        /*
            r29 = this;
            r0 = r29
            r1 = r30
            int r2 = r30.getCount()
            if (r2 != 0) goto L_0x000b
            return
        L_0x000b:
            com.android.dialer.app.calllog.CallLogGroupBuilder$GroupCreator r3 = r0.groupCreator
            r3.clearDayGroups()
            long r3 = java.lang.System.currentTimeMillis()
            r30.moveToFirst()
            r5 = 2
            long r6 = r1.getLong(r5)
            r8 = 0
            long r9 = r1.getLong(r8)
            int r6 = r0.getDayGroup(r6, r3)
            com.android.dialer.app.calllog.CallLogGroupBuilder$GroupCreator r7 = r0.groupCreator
            r7.setDayGroup(r9, r6)
            r7 = 1
            java.lang.String r11 = r1.getString(r7)
            r12 = 18
            java.lang.String r13 = r1.getString(r12)
            r14 = 20
            int r15 = r1.getInt(r14)
            android.content.Context r5 = r0.appContext
            com.android.dialer.duo.DuoComponent r5 = com.android.dialer.duo.DuoComponent.get(r5)
            com.android.dialer.duo.Duo r5 = r5.getDuo()
            com.android.dialer.duo.stub.DuoStub r5 = (com.android.dialer.duo.stub.DuoStub) r5
            r5.isDuoAccount((java.lang.String) r13)
            int r5 = com.android.dialer.calllogutils.CallLogDates.getCallbackAction(r11, r15, r8)
            com.android.dialer.app.calllog.CallLogGroupBuilder$GroupCreator r8 = r0.groupCreator
            r8.setCallbackAction(r9, r5)
            r8 = 19
            java.lang.String r9 = r1.getString(r8)
            r10 = 24
            java.lang.String r16 = r1.getString(r10)
            r8 = 25
            java.lang.String r17 = r1.getString(r8)
            r12 = 4
            int r18 = r1.getInt(r12)
            r19 = r5
            r5 = r7
            r20 = r18
            r27 = r16
            r16 = r6
            r6 = r27
            r28 = r15
            r15 = r9
            r9 = r17
            r17 = r28
        L_0x007c:
            boolean r18 = r30.moveToNext()
            if (r18 == 0) goto L_0x0184
            java.lang.String r14 = r1.getString(r7)
            java.lang.String r7 = r1.getString(r10)
            java.lang.String r10 = r1.getString(r8)
            int r8 = r1.getInt(r12)
            r18 = r2
            r12 = 20
            int r2 = r1.getInt(r12)
            r21 = r3
            r12 = 18
            java.lang.String r3 = r1.getString(r12)
            r4 = 19
            java.lang.String r12 = r1.getString(r4)
            android.content.Context r4 = r0.appContext
            com.android.dialer.duo.DuoComponent r4 = com.android.dialer.duo.DuoComponent.get(r4)
            com.android.dialer.duo.Duo r4 = r4.getDuo()
            com.android.dialer.duo.stub.DuoStub r4 = (com.android.dialer.duo.stub.DuoStub) r4
            r4.isDuoAccount((java.lang.String) r3)
            r4 = 0
            int r1 = com.android.dialer.calllogutils.CallLogDates.getCallbackAction(r14, r2, r4)
            boolean r4 = r0.equalNumbers(r11, r14)
            boolean r23 = r6.equals(r7)
            boolean r24 = r9.equals(r10)
            boolean r25 = android.text.TextUtils.equals(r13, r3)
            if (r25 == 0) goto L_0x00db
            boolean r25 = android.text.TextUtils.equals(r15, r12)
            if (r25 == 0) goto L_0x00db
            r25 = r3
            r3 = r19
            r19 = 1
            goto L_0x00e1
        L_0x00db:
            r25 = r3
            r3 = r19
            r19 = 0
        L_0x00e1:
            if (r3 != r1) goto L_0x00e6
            r26 = 1
            goto L_0x00e8
        L_0x00e6:
            r26 = 0
        L_0x00e8:
            if (r4 == 0) goto L_0x013a
            if (r19 == 0) goto L_0x013a
            if (r23 == 0) goto L_0x013a
            if (r24 == 0) goto L_0x013a
            if (r26 == 0) goto L_0x013a
            r4 = 4
            r19 = r1
            r1 = r20
            if (r8 == r4) goto L_0x00fe
            if (r1 == r4) goto L_0x00fe
            r20 = 1
            goto L_0x0100
        L_0x00fe:
            r20 = 0
        L_0x0100:
            if (r20 == 0) goto L_0x013c
            r4 = 6
            if (r8 == r4) goto L_0x010a
            if (r1 == r4) goto L_0x010a
            r20 = 1
            goto L_0x010c
        L_0x010a:
            r20 = 0
        L_0x010c:
            if (r20 != 0) goto L_0x0117
            if (r8 != r4) goto L_0x0114
            if (r1 != r4) goto L_0x0114
            r4 = 1
            goto L_0x0115
        L_0x0114:
            r4 = 0
        L_0x0115:
            if (r4 == 0) goto L_0x013c
        L_0x0117:
            java.lang.Integer r4 = com.android.dialer.compat.telephony.TelephonyManagerCompat.FEATURES_ASSISTED_DIALING
            int r4 = r4.intValue()
            r4 = r4 & r17
            java.lang.Integer r20 = com.android.dialer.compat.telephony.TelephonyManagerCompat.FEATURES_ASSISTED_DIALING
            int r20 = r20.intValue()
            r23 = r1
            r1 = r20 & r2
            if (r4 != r1) goto L_0x012d
            r1 = 1
            goto L_0x012e
        L_0x012d:
            r1 = 0
        L_0x012e:
            if (r1 == 0) goto L_0x013c
            int r5 = r5 + 1
            r1 = r30
            r7 = r5
            r2 = r16
            r20 = r23
            goto L_0x0162
        L_0x013a:
            r19 = r1
        L_0x013c:
            r1 = r30
            r6 = r2
            r4 = 2
            long r2 = r1.getLong(r4)
            r9 = r5
            r4 = r21
            int r2 = r0.getDayGroup(r2, r4)
            com.android.dialer.app.calllog.CallLogGroupBuilder$GroupCreator r3 = r0.groupCreator
            int r11 = r30.getPosition()
            int r11 = r11 - r9
            r3.addGroup(r11, r9)
            r17 = r6
            r6 = r7
            r20 = r8
            r9 = r10
            r15 = r12
            r11 = r14
            r3 = r19
            r13 = r25
            r7 = 1
        L_0x0162:
            r8 = 0
            long r4 = r1.getLong(r8)
            com.android.dialer.app.calllog.CallLogGroupBuilder$GroupCreator r10 = r0.groupCreator
            r10.setCallbackAction(r4, r3)
            com.android.dialer.app.calllog.CallLogGroupBuilder$GroupCreator r10 = r0.groupCreator
            r10.setDayGroup(r4, r2)
            r16 = r2
            r19 = r3
            r5 = r7
            r2 = r18
            r3 = r21
            r7 = 1
            r8 = 25
            r10 = 24
            r12 = 4
            r14 = 20
            goto L_0x007c
        L_0x0184:
            r18 = r2
            r9 = r5
            com.android.dialer.app.calllog.CallLogGroupBuilder$GroupCreator r0 = r0.groupCreator
            int r2 = r18 - r9
            r0.addGroup(r2, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.app.calllog.CallLogGroupBuilder.addGroups(android.database.Cursor):void");
    }

    /* access modifiers changed from: package-private */
    public boolean compareSipAddresses(String str, String str2) {
        String str3;
        String str4;
        if (str == null || str2 == null) {
            return Objects.equals(str, str2);
        }
        int indexOf = str.indexOf(64);
        if (indexOf != -1) {
            String substring = str.substring(0, indexOf);
            str3 = str.substring(indexOf);
            str = substring;
        } else {
            str3 = "";
        }
        int indexOf2 = str2.indexOf(64);
        if (indexOf2 != -1) {
            String substring2 = str2.substring(0, indexOf2);
            str4 = str2.substring(indexOf2);
            str2 = substring2;
        } else {
            str4 = "";
        }
        if (!str.equals(str2) || !str3.equalsIgnoreCase(str4)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean equalNumbers(String str, String str2) {
        if (PhoneNumberHelper.isUriNumber(str) || PhoneNumberHelper.isUriNumber(str2)) {
            return compareSipAddresses(str, str2);
        }
        if (PhoneNumberHelper.numberHasSpecialChars(str) || PhoneNumberHelper.numberHasSpecialChars(str2)) {
            return PhoneNumberHelper.sameRawNumbers(str, str2);
        }
        return PhoneNumberUtils.compare(str, str2);
    }
}
