package com.android.dialer.app.calllog;

import android.content.res.Resources;
import android.text.TextUtils;
import com.android.dialer.R;
import com.android.dialer.app.calllog.calllogcache.CallLogCache;
import com.android.dialer.calllogutils.PhoneCallDetails;
import com.android.dialer.common.LogUtil;

class CallLogListItemHelper {
    private final CallLogCache callLogCache;
    private final PhoneCallDetailsHelper phoneCallDetailsHelper;
    private final Resources resources;

    public CallLogListItemHelper(PhoneCallDetailsHelper phoneCallDetailsHelper2, Resources resources2, CallLogCache callLogCache2) {
        this.phoneCallDetailsHelper = phoneCallDetailsHelper2;
        this.resources = resources2;
        this.callLogCache = callLogCache2;
    }

    private CharSequence getNameOrNumber(PhoneCallDetails phoneCallDetails) {
        if (!TextUtils.isEmpty(phoneCallDetails.getPreferredName())) {
            return phoneCallDetails.getPreferredName();
        }
        return phoneCallDetails.displayNumber;
    }

    public void setActionContentDescriptions(CallLogListItemViewHolder callLogListItemViewHolder) {
        if (callLogListItemViewHolder.nameOrNumber == null) {
            LogUtil.m8e("CallLogListItemHelper.setActionContentDescriptions", "setActionContentDescriptions; name or number is null.", new Object[0]);
        }
        CharSequence charSequence = callLogListItemViewHolder.nameOrNumber;
        if (charSequence == null) {
            charSequence = "";
        }
        callLogListItemViewHolder.videoCallButtonView.setContentDescription(TextUtils.expandTemplate(this.resources.getString(R.string.description_video_call_action), new CharSequence[]{charSequence}));
        callLogListItemViewHolder.createNewContactButtonView.setContentDescription(TextUtils.expandTemplate(this.resources.getString(R.string.description_create_new_contact_action), new CharSequence[]{charSequence}));
        callLogListItemViewHolder.addToExistingContactButtonView.setContentDescription(TextUtils.expandTemplate(this.resources.getString(R.string.description_add_to_existing_contact_action), new CharSequence[]{charSequence}));
        callLogListItemViewHolder.detailsButtonView.setContentDescription(TextUtils.expandTemplate(this.resources.getString(R.string.description_details_action), new CharSequence[]{charSequence}));
    }

    public void setPhoneCallDetails(CallLogListItemViewHolder callLogListItemViewHolder, PhoneCallDetails phoneCallDetails) {
        String str;
        this.phoneCallDetailsHelper.setPhoneCallDetails(callLogListItemViewHolder.phoneCallDetailsViews, phoneCallDetails);
        DialerQuickContactBadge dialerQuickContactBadge = callLogListItemViewHolder.quickContactView;
        if (phoneCallDetails.isSpam) {
            str = this.resources.getString(R.string.description_spam_contact_details, new Object[]{getNameOrNumber(phoneCallDetails)});
        } else {
            str = this.resources.getString(R.string.description_contact_details, new Object[]{getNameOrNumber(phoneCallDetails)});
        }
        dialerQuickContactBadge.setContentDescription(str);
        callLogListItemViewHolder.primaryActionView.setContentDescription(phoneCallDetails.callDescription);
        callLogListItemViewHolder.nameOrNumber = getNameOrNumber(phoneCallDetails);
        callLogListItemViewHolder.callTypeOrLocation = this.phoneCallDetailsHelper.getCallTypeOrLocation(phoneCallDetails);
        callLogListItemViewHolder.countryIso = phoneCallDetails.countryIso;
        callLogListItemViewHolder.updatePhoto();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0079, code lost:
        if (r8 == null) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0089, code lost:
        if (r4 == null) goto L_0x00a5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updatePhoneCallDetails(com.android.dialer.calllogutils.PhoneCallDetails r14) {
        /*
            r13 = this;
            com.android.dialer.common.Assert.isWorkerThread()
            com.android.dialer.app.calllog.PhoneCallDetailsHelper r0 = r13.phoneCallDetailsHelper
            java.lang.CharSequence r0 = r0.getCallLocationAndDate(r14)
            r14.callLocationAndDate = r0
            java.lang.CharSequence r0 = r13.getNameOrNumber(r14)
            com.android.dialer.app.calllog.PhoneCallDetailsHelper r1 = r13.phoneCallDetailsHelper
            java.lang.CharSequence r1 = r1.getCallTypeOrLocation(r14)
            com.android.dialer.app.calllog.PhoneCallDetailsHelper r2 = r13.phoneCallDetailsHelper
            java.lang.CharSequence r2 = r2.getCallDate(r14)
            android.text.SpannableStringBuilder r3 = new android.text.SpannableStringBuilder
            r3.<init>()
            int[] r4 = r14.callTypes
            int r5 = r4.length
            r6 = 0
            r7 = 1
            if (r5 <= r7) goto L_0x003c
            android.content.res.Resources r5 = r13.resources
            r8 = 2131820868(0x7f110144, float:1.9274463E38)
            java.lang.Object[] r9 = new java.lang.Object[r7]
            int r4 = r4.length
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r9[r6] = r4
            java.lang.String r4 = r5.getString(r8, r9)
            r3.append(r4)
        L_0x003c:
            int r4 = r14.features
            r4 = r4 & r7
            if (r4 != r7) goto L_0x004d
            android.content.res.Resources r4 = r13.resources
            r5 = 2131820887(0x7f110157, float:1.9274502E38)
            java.lang.String r4 = r4.getString(r5)
            r3.append(r4)
        L_0x004d:
            com.android.dialer.app.calllog.calllogcache.CallLogCache r4 = r13.callLogCache
            android.telecom.PhoneAccountHandle r5 = r14.accountHandle
            java.lang.String r4 = r4.getAccountLabel(r5)
            android.content.res.Resources r5 = r13.resources
            java.lang.String r8 = r14.viaNumber
            boolean r9 = android.text.TextUtils.isEmpty(r8)
            java.lang.String r10 = ""
            r11 = 2
            if (r9 != 0) goto L_0x007c
            boolean r9 = android.text.TextUtils.isEmpty(r4)
            if (r9 != 0) goto L_0x007c
            r9 = 2131820886(0x7f110156, float:1.92745E38)
            java.lang.Object[] r12 = new java.lang.Object[r11]
            r12[r6] = r4
            r12[r7] = r8
            java.lang.String r4 = r5.getString(r9, r12)
            android.text.Spannable r8 = android.support.p002v7.appcompat.R$style.getTelephoneTtsSpannable(r4, r8)
            if (r8 != 0) goto L_0x00a5
            goto L_0x008c
        L_0x007c:
            boolean r9 = android.text.TextUtils.isEmpty(r8)
            if (r9 != 0) goto L_0x008e
            r4 = 2131820885(0x7f110155, float:1.9274498E38)
            java.lang.CharSequence r4 = android.support.p002v7.appcompat.R$style.getTtsSpannedPhoneNumber(r5, r4, r8)
            if (r4 != 0) goto L_0x008c
            goto L_0x00a5
        L_0x008c:
            r8 = r4
            goto L_0x00a5
        L_0x008e:
            boolean r8 = android.text.TextUtils.isEmpty(r4)
            if (r8 != 0) goto L_0x00a4
            r8 = 2131820870(0x7f110146, float:1.9274467E38)
            java.lang.String r5 = r5.getString(r8)
            java.lang.CharSequence[] r8 = new java.lang.CharSequence[r7]
            r8[r6] = r4
            java.lang.CharSequence r8 = android.text.TextUtils.expandTemplate(r5, r8)
            goto L_0x00a5
        L_0x00a4:
            r8 = r10
        L_0x00a5:
            int[] r4 = r14.callTypes
            boolean r5 = r14.isRead
            int r9 = r4.length
            r12 = 3
            if (r9 <= 0) goto L_0x00b0
            r4 = r4[r6]
            goto L_0x00b1
        L_0x00b0:
            r4 = r12
        L_0x00b1:
            r9 = 4
            if (r4 != r12) goto L_0x00b8
            r4 = 2131820866(0x7f110142, float:1.927446E38)
            goto L_0x00cd
        L_0x00b8:
            if (r4 != r7) goto L_0x00be
            r4 = 2131820865(0x7f110141, float:1.9274457E38)
            goto L_0x00cd
        L_0x00be:
            if (r4 != r9) goto L_0x00ca
            if (r5 == 0) goto L_0x00c6
            r4 = 2131820876(0x7f11014c, float:1.927448E38)
            goto L_0x00cd
        L_0x00c6:
            r4 = 2131820883(0x7f110153, float:1.9274494E38)
            goto L_0x00cd
        L_0x00ca:
            r4 = 2131820869(0x7f110145, float:1.9274465E38)
        L_0x00cd:
            android.content.res.Resources r13 = r13.resources
            java.lang.String r13 = r13.getString(r4)
            java.lang.CharSequence[] r4 = new java.lang.CharSequence[r9]
            r4[r6] = r0
            if (r1 != 0) goto L_0x00da
            r1 = r10
        L_0x00da:
            r4[r7] = r1
            r4[r11] = r2
            r4[r12] = r8
            java.lang.CharSequence r13 = android.text.TextUtils.expandTemplate(r13, r4)
            r3.append(r13)
            r14.callDescription = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.app.calllog.CallLogListItemHelper.updatePhoneCallDetails(com.android.dialer.calllogutils.PhoneCallDetails):void");
    }
}
