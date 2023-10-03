package com.android.incallui.contactgrid;

import android.content.Context;
import android.text.TextUtils;
import com.android.dialer.R;
import com.android.incallui.incall.protocol.PrimaryCallState;
import com.android.incallui.incall.protocol.PrimaryInfo;

public class BottomRow {

    public static class Info {
        public final boolean isForwardIconVisible;
        public final boolean isHdAttemptingIconVisible;
        public final boolean isHdIconVisible;
        public final boolean isSpamIconVisible;
        public final boolean isTimerVisible;
        public final boolean isWorkIconVisible;
        public final CharSequence label;
        public final boolean shouldPopulateAccessibilityEvent;

        public Info(CharSequence charSequence, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
            this.label = charSequence;
            this.isTimerVisible = z;
            this.isWorkIconVisible = z2;
            this.isHdAttemptingIconVisible = z3;
            this.isHdIconVisible = z4;
            this.isForwardIconVisible = z5;
            this.isSpamIconVisible = z6;
            this.shouldPopulateAccessibilityEvent = z7;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:63:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0177  */
    /* renamed from: getInfo  reason: collision with other method in class */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.android.incallui.contactgrid.TopRow$Info m122getInfo(android.content.Context r8, com.android.incallui.incall.protocol.PrimaryCallState r9, com.android.incallui.incall.protocol.PrimaryInfo r10) {
        /*
            android.graphics.drawable.Drawable r0 = r9.connectionIcon()
            boolean r1 = r9.isWifi()
            if (r1 == 0) goto L_0x0013
            if (r0 != 0) goto L_0x0013
            r0 = 2131231033(0x7f080139, float:1.8078136E38)
            android.graphics.drawable.Drawable r0 = r8.getDrawable(r0)
        L_0x0013:
            int r1 = r9.state()
            r2 = 4
            r3 = 2
            r4 = 3
            r5 = 0
            r6 = 1
            if (r1 == r2) goto L_0x0215
            int r1 = r9.state()
            r2 = 5
            if (r1 != r2) goto L_0x0027
            goto L_0x0215
        L_0x0027:
            int r1 = r9.sessionModificationState()
            boolean r1 = android.support.p002v7.appcompat.R$style.hasSentVideoUpgradeRequest(r1)
            r2 = 0
            if (r1 != 0) goto L_0x01d7
            int r1 = r9.sessionModificationState()
            boolean r1 = android.support.p002v7.appcompat.R$style.hasReceivedVideoUpgradeRequest(r1)
            if (r1 == 0) goto L_0x003e
            goto L_0x01d7
        L_0x003e:
            int r1 = r9.sessionModificationState()
            if (r1 != r3) goto L_0x004d
            r9 = 2131821053(0x7f1101fd, float:1.9274838E38)
            java.lang.String r8 = r8.getString(r9)
            goto L_0x02c7
        L_0x004d:
            int r1 = r9.state()
            r7 = 15
            if (r1 != r7) goto L_0x005e
            r9 = 2131821043(0x7f1101f3, float:1.9274818E38)
            java.lang.String r8 = r8.getString(r9)
            goto L_0x02c7
        L_0x005e:
            int r1 = r9.state()
            r7 = 6
            if (r1 == r7) goto L_0x00df
            int r1 = r9.state()
            r7 = 13
            if (r1 != r7) goto L_0x006f
            goto L_0x00df
        L_0x006f:
            int r1 = r9.state()
            if (r1 != r4) goto L_0x0084
            boolean r1 = r9.isRemotelyHeld()
            if (r1 == 0) goto L_0x0084
            r9 = 2131821040(0x7f1101f0, float:1.9274812E38)
            java.lang.String r8 = r8.getString(r9)
            goto L_0x02c7
        L_0x0084:
            int r8 = r9.state()
            if (r8 != r4) goto L_0x00a4
            boolean r8 = shouldShowNumber(r10, r5)
            if (r8 == 0) goto L_0x00a4
            java.lang.String r8 = r10.number()
            android.text.BidiFormatter r9 = android.text.BidiFormatter.getInstance()
            android.text.TextDirectionHeuristic r10 = android.text.TextDirectionHeuristics.LTR
            java.lang.String r8 = r9.unicodeWrap(r8, r10)
            java.lang.CharSequence r8 = android.telephony.PhoneNumberUtils.createTtsSpannable(r8)
            goto L_0x02c7
        L_0x00a4:
            int r8 = r9.state()
            r10 = 16
            if (r8 != r10) goto L_0x00bc
            java.lang.String r8 = r9.customLabel()
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 != 0) goto L_0x00bc
            java.lang.String r8 = r9.customLabel()
            goto L_0x02c7
        L_0x00bc:
            java.lang.String r8 = r9.connectionLabel()
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 != 0) goto L_0x00dc
            boolean r8 = isAccount(r9)
            if (r8 != 0) goto L_0x00d8
            boolean r8 = r9.isWifi()
            if (r8 != 0) goto L_0x00d8
            boolean r8 = r9.isConference()
            if (r8 == 0) goto L_0x00dc
        L_0x00d8:
            java.lang.String r2 = r9.connectionLabel()
        L_0x00dc:
            r8 = r2
            goto L_0x02c7
        L_0x00df:
            java.lang.String r10 = r9.connectionLabel()
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            java.lang.String r1 = "TopRow.getLabelForDialing"
            if (r10 != 0) goto L_0x017a
            boolean r10 = r9.isWifi()
            if (r10 != 0) goto L_0x017a
            com.android.dialer.preferredsim.suggestion.SuggestionProvider$Reason r10 = r9.simSuggestionReason()
            if (r10 == 0) goto L_0x0124
            com.android.dialer.preferredsim.suggestion.SuggestionProvider$Reason r10 = r9.simSuggestionReason()
            int r10 = r10.ordinal()
            if (r10 == r6) goto L_0x0114
            if (r10 == r3) goto L_0x0104
            goto L_0x0124
        L_0x0104:
            r10 = 2131820997(0x7f1101c5, float:1.9274725E38)
            java.lang.Object[] r2 = new java.lang.Object[r6]
            java.lang.String r7 = r9.connectionLabel()
            r2[r5] = r7
            java.lang.String r10 = r8.getString(r10, r2)
            goto L_0x0133
        L_0x0114:
            r10 = 2131820998(0x7f1101c6, float:1.9274727E38)
            java.lang.Object[] r2 = new java.lang.Object[r6]
            java.lang.String r7 = r9.connectionLabel()
            r2[r5] = r7
            java.lang.String r10 = r8.getString(r10, r2)
            goto L_0x0133
        L_0x0124:
            r10 = 2131820999(0x7f1101c7, float:1.9274729E38)
            java.lang.Object[] r2 = new java.lang.Object[r6]
            java.lang.String r7 = r9.connectionLabel()
            r2[r5] = r7
            java.lang.String r10 = r8.getString(r10, r2)
        L_0x0133:
            boolean r2 = r9.isAssistedDialed()
            if (r2 == 0) goto L_0x0177
            com.android.dialer.assisteddialing.TransformationInfo r2 = r9.assistedDialingExtras()
            if (r2 == 0) goto L_0x0177
            java.lang.Object[] r2 = new java.lang.Object[r5]
            java.lang.String r7 = "using assisted dialing with via label."
            com.android.dialer.common.LogUtil.m9i(r1, r7, r2)
            com.android.dialer.assisteddialing.TransformationInfo r1 = r9.assistedDialingExtras()
            int r1 = r1.transformedNumberCountryCallingCode()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.CharSequence[] r2 = new java.lang.CharSequence[r4]
            r2[r5] = r10
            java.lang.String r10 = " • "
            r2[r6] = r10
            r10 = 2131821004(0x7f1101cc, float:1.9274739E38)
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r4[r5] = r1
            com.android.dialer.assisteddialing.TransformationInfo r9 = r9.assistedDialingExtras()
            java.lang.String r9 = r9.userHomeCountryCode()
            r4[r6] = r9
            java.lang.String r8 = r8.getString(r10, r4)
            r2[r3] = r8
            java.lang.CharSequence r8 = android.text.TextUtils.concat(r2)
            goto L_0x02c7
        L_0x0177:
            r8 = r10
            goto L_0x02c7
        L_0x017a:
            boolean r10 = r9.isVideoCall()
            if (r10 == 0) goto L_0x0198
            boolean r9 = r9.isWifi()
            if (r9 == 0) goto L_0x018f
            r9 = 2131821059(0x7f110203, float:1.927485E38)
            java.lang.String r8 = r8.getString(r9)
            goto L_0x02c7
        L_0x018f:
            r9 = 2131821057(0x7f110201, float:1.9274846E38)
            java.lang.String r8 = r8.getString(r9)
            goto L_0x02c7
        L_0x0198:
            boolean r10 = r9.isAssistedDialed()
            if (r10 == 0) goto L_0x01ce
            com.android.dialer.assisteddialing.TransformationInfo r10 = r9.assistedDialingExtras()
            if (r10 == 0) goto L_0x01ce
            java.lang.Object[] r10 = new java.lang.Object[r5]
            java.lang.String r2 = "using assisted dialing label."
            com.android.dialer.common.LogUtil.m9i(r1, r2, r10)
            com.android.dialer.assisteddialing.TransformationInfo r10 = r9.assistedDialingExtras()
            int r10 = r10.transformedNumberCountryCallingCode()
            java.lang.String r10 = java.lang.String.valueOf(r10)
            r1 = 2131821003(0x7f1101cb, float:1.9274737E38)
            java.lang.Object[] r2 = new java.lang.Object[r3]
            r2[r5] = r10
            com.android.dialer.assisteddialing.TransformationInfo r9 = r9.assistedDialingExtras()
            java.lang.String r9 = r9.userHomeCountryCode()
            r2[r6] = r9
            java.lang.String r8 = r8.getString(r1, r2)
            goto L_0x02c7
        L_0x01ce:
            r9 = 2131821002(0x7f1101ca, float:1.9274735E38)
            java.lang.String r8 = r8.getString(r9)
            goto L_0x02c7
        L_0x01d7:
            int r10 = r9.sessionModificationState()
            switch(r10) {
                case 1: goto L_0x020b;
                case 2: goto L_0x0202;
                case 3: goto L_0x01f4;
                case 4: goto L_0x01eb;
                case 5: goto L_0x0202;
                case 6: goto L_0x01e2;
                default: goto L_0x01de;
            }
        L_0x01de:
            com.android.dialer.common.Assert.fail()
            goto L_0x0214
        L_0x01e2:
            r9 = 2131821055(0x7f1101ff, float:1.9274842E38)
            java.lang.String r8 = r8.getString(r9)
            goto L_0x02c7
        L_0x01eb:
            r9 = 2131821056(0x7f110200, float:1.9274844E38)
            java.lang.String r8 = r8.getString(r9)
            goto L_0x02c7
        L_0x01f4:
            int r10 = r9.sessionModificationState()
            boolean r9 = r9.isWifi()
            java.lang.CharSequence r8 = getLabelForIncomingVideo(r8, r10, r9)
            goto L_0x02c7
        L_0x0202:
            r9 = 2131821054(0x7f1101fe, float:1.927484E38)
            java.lang.String r8 = r8.getString(r9)
            goto L_0x02c7
        L_0x020b:
            r9 = 2131821058(0x7f110202, float:1.9274848E38)
            java.lang.String r8 = r8.getString(r9)
            goto L_0x02c7
        L_0x0214:
            throw r2
        L_0x0215:
            java.lang.String r1 = r9.callSubject()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0226
            java.lang.String r8 = r9.callSubject()
            r6 = r5
            goto L_0x02c7
        L_0x0226:
            boolean r1 = r9.isVideoCall()
            if (r1 == 0) goto L_0x0239
            int r1 = r9.sessionModificationState()
            boolean r9 = r9.isWifi()
            java.lang.CharSequence r8 = getLabelForIncomingVideo(r8, r1, r9)
            goto L_0x02a1
        L_0x0239:
            boolean r1 = r9.isWifi()
            if (r1 == 0) goto L_0x024e
            java.lang.String r1 = r9.connectionLabel()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x024e
            java.lang.String r8 = r9.connectionLabel()
            goto L_0x02a1
        L_0x024e:
            boolean r1 = isAccount(r9)
            if (r1 == 0) goto L_0x028c
            java.lang.String r1 = r9.connectionLabel()
            com.android.dialer.common.Assert.isNotNull(r1)
            java.lang.Object[] r1 = new java.lang.Object[r6]
            java.lang.String r2 = r9.connectionLabel()
            r1[r5] = r2
            r2 = 2131820816(0x7f110110, float:1.9274358E38)
            java.lang.String r8 = r8.getString(r2, r1)
            android.text.SpannableString r1 = new android.text.SpannableString
            r1.<init>(r8)
            java.lang.String r2 = r9.connectionLabel()
            int r8 = r8.indexOf(r2)
            java.lang.String r9 = r9.connectionLabel()
            int r9 = r9.length()
            int r9 = r9 + r8
            android.text.style.StyleSpan r2 = new android.text.style.StyleSpan
            r2.<init>(r6)
            r7 = 17
            r1.setSpan(r2, r8, r9, r7)
            r8 = r1
            goto L_0x02a1
        L_0x028c:
            boolean r9 = r9.isWorkCall()
            if (r9 == 0) goto L_0x029a
            r9 = 2131820820(0x7f110114, float:1.9274366E38)
            java.lang.String r8 = r8.getString(r9)
            goto L_0x02a1
        L_0x029a:
            r9 = 2131820818(0x7f110112, float:1.9274362E38)
            java.lang.String r8 = r8.getString(r9)
        L_0x02a1:
            boolean r9 = shouldShowNumber(r10, r6)
            if (r9 == 0) goto L_0x02c7
            java.lang.CharSequence[] r9 = new java.lang.CharSequence[r4]
            r9[r5] = r8
            java.lang.String r8 = " "
            r9[r6] = r8
            java.lang.String r8 = r10.number()
            android.text.BidiFormatter r10 = android.text.BidiFormatter.getInstance()
            android.text.TextDirectionHeuristic r1 = android.text.TextDirectionHeuristics.LTR
            java.lang.String r8 = r10.unicodeWrap(r8, r1)
            java.lang.CharSequence r8 = android.telephony.PhoneNumberUtils.createTtsSpannable(r8)
            r9[r3] = r8
            java.lang.CharSequence r8 = android.text.TextUtils.concat(r9)
        L_0x02c7:
            com.android.incallui.contactgrid.TopRow$Info r9 = new com.android.incallui.contactgrid.TopRow$Info
            r9.<init>(r8, r0, r6)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.contactgrid.BottomRow.m122getInfo(android.content.Context, com.android.incallui.incall.protocol.PrimaryCallState, com.android.incallui.incall.protocol.PrimaryInfo):com.android.incallui.contactgrid.TopRow$Info");
    }

    private static CharSequence getLabelForIncomingVideo(Context context, int i, boolean z) {
        if (i == 3) {
            if (z) {
                return context.getString(R.string.contact_grid_incoming_wifi_video_call);
            }
            return context.getString(R.string.contact_grid_incoming_video_call);
        } else if (z) {
            return context.getString(R.string.contact_grid_incoming_wifi_video_call);
        } else {
            return context.getString(R.string.contact_grid_incoming_video_call);
        }
    }

    private static boolean isAccount(PrimaryCallState primaryCallState) {
        return !TextUtils.isEmpty(primaryCallState.connectionLabel()) && TextUtils.isEmpty(primaryCallState.gatewayNumber());
    }

    private static boolean shouldShowNumber(PrimaryInfo primaryInfo, boolean z) {
        if (primaryInfo.nameIsNumber()) {
            return false;
        }
        if (primaryInfo.location() == null && z) {
            return false;
        }
        if ((!primaryInfo.isLocalContact() || z) && !TextUtils.isEmpty(primaryInfo.number())) {
            return true;
        }
        return false;
    }

    public static Info getInfo(Context context, PrimaryCallState primaryCallState, PrimaryInfo primaryInfo) {
        boolean z;
        boolean z2;
        boolean z3;
        String str;
        CharSequence charSequence;
        boolean z4 = true;
        boolean z5 = primaryCallState.state() == 3;
        boolean isForwardedNumber = primaryCallState.isForwardedNumber();
        boolean isWorkCall = primaryCallState.isWorkCall();
        boolean z6 = primaryCallState.isHdAudioCall() && !isForwardedNumber;
        boolean isHdAttempting = primaryCallState.isHdAttempting();
        if (!(primaryCallState.state() == 4 || primaryCallState.state() == 5) || !primaryInfo.isSpam()) {
            if (primaryCallState.state() == 9) {
                charSequence = context.getString(R.string.incall_hanging_up);
            } else if (primaryCallState.state() == 10) {
                CharSequence label = primaryCallState.disconnectCause().getLabel();
                if (TextUtils.isEmpty(label)) {
                    charSequence = context.getString(R.string.incall_call_ended);
                } else {
                    str = label;
                    z3 = z6;
                    z = z4;
                    z2 = false;
                }
            } else {
                if (primaryInfo.location() != null) {
                    charSequence = primaryInfo.location();
                } else if (primaryInfo.nameIsNumber() || TextUtils.isEmpty(primaryInfo.number())) {
                    charSequence = null;
                } else {
                    charSequence = primaryInfo.label() == null ? primaryInfo.number() : TextUtils.concat(new CharSequence[]{primaryInfo.label(), " ", primaryInfo.number()});
                }
                z4 = primaryInfo.nameIsNumber();
            }
            str = charSequence;
            z3 = z6;
            z = z4;
            z2 = false;
        } else {
            str = context.getString(R.string.contact_grid_incoming_suspected_spam);
            z2 = true;
            z = true;
            z3 = false;
        }
        return new Info(str, z5, isWorkCall, isHdAttempting, z3, isForwardedNumber, z2, z);
    }
}
