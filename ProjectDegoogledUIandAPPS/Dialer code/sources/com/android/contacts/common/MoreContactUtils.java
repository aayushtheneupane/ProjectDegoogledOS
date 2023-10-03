package com.android.contacts.common;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.android.contacts.common.model.account.AccountType;

public class MoreContactUtils {
    private static final String WAIT_SYMBOL_AS_STRING = String.valueOf(';');

    public static Intent getInvitableIntent(AccountType accountType, Uri uri) {
        String str = accountType.syncAdapterPackageName;
        String inviteContactActivityClassName = accountType.getInviteContactActivityClassName();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(inviteContactActivityClassName)) {
            return null;
        }
        Intent intent = new Intent();
        intent.setClassName(str, inviteContactActivityClassName);
        intent.setAction("com.android.contacts.action.INVITE_CONTACT");
        intent.setData(uri);
        return intent;
    }

    public static Rect getTargetRectFromView(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = view.getWidth() + iArr[0];
        rect.bottom = view.getHeight() + iArr[1];
        return rect;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r8.parse(r3, (java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        continue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        return false;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x009f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean shouldCollapse(java.lang.CharSequence r6, java.lang.CharSequence r7, java.lang.CharSequence r8, java.lang.CharSequence r9) {
        /*
            boolean r8 = android.text.TextUtils.equals(r6, r8)
            r0 = 0
            if (r8 != 0) goto L_0x0008
            return r0
        L_0x0008:
            boolean r8 = android.text.TextUtils.equals(r7, r9)
            r1 = 1
            if (r8 == 0) goto L_0x0010
            return r1
        L_0x0010:
            if (r7 == 0) goto L_0x00a7
            if (r9 != 0) goto L_0x0016
            goto L_0x00a7
        L_0x0016:
            java.lang.String r8 = "vnd.android.cursor.item/phone_v2"
            boolean r6 = android.text.TextUtils.equals(r8, r6)
            if (r6 != 0) goto L_0x001f
            return r0
        L_0x001f:
            java.lang.String r6 = r7.toString()
            java.lang.String r7 = r9.toString()
            java.lang.String r8 = "#"
            boolean r9 = r6.contains(r8)
            boolean r8 = r7.contains(r8)
            if (r9 != r8) goto L_0x00a7
            java.lang.String r8 = "*"
            boolean r9 = r6.contains(r8)
            boolean r8 = r7.contains(r8)
            if (r9 == r8) goto L_0x0041
            goto L_0x00a7
        L_0x0041:
            java.lang.String r8 = WAIT_SYMBOL_AS_STRING
            java.lang.String[] r6 = r6.split(r8)
            java.lang.String r8 = WAIT_SYMBOL_AS_STRING
            java.lang.String[] r7 = r7.split(r8)
            int r8 = r6.length
            int r9 = r7.length
            if (r8 == r9) goto L_0x0052
            goto L_0x00a7
        L_0x0052:
            com.google.i18n.phonenumbers.PhoneNumberUtil r8 = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance()
            r9 = r0
        L_0x0057:
            int r2 = r6.length
            if (r9 >= r2) goto L_0x00a6
            r2 = r6[r9]
            java.lang.String r2 = android.telephony.PhoneNumberUtils.convertKeypadLettersToDigits(r2)
            r3 = r7[r9]
            boolean r4 = android.text.TextUtils.equals(r2, r3)
            if (r4 == 0) goto L_0x0069
            goto L_0x00a3
        L_0x0069:
            com.google.i18n.phonenumbers.PhoneNumberUtil$MatchType r4 = r8.isNumberMatch((java.lang.CharSequence) r2, (java.lang.CharSequence) r3)
            int r4 = r4.ordinal()
            if (r4 == 0) goto L_0x00a7
            if (r4 == r1) goto L_0x00a7
            r5 = 2
            if (r4 == r5) goto L_0x00a7
            r5 = 3
            if (r4 == r5) goto L_0x0087
            r2 = 4
            if (r4 != r2) goto L_0x007f
            goto L_0x00a3
        L_0x007f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "Unknown result value from phone number library"
            r6.<init>(r7)
            throw r6
        L_0x0087:
            r4 = 0
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber r2 = r8.parse(r2, r4)     // Catch:{ NumberParseException -> 0x009f }
            int r2 = r2.getCountryCode()     // Catch:{ NumberParseException -> 0x009f }
            if (r2 != r1) goto L_0x00a7
            java.lang.String r2 = r3.trim()     // Catch:{ NumberParseException -> 0x009f }
            char r2 = r2.charAt(r0)     // Catch:{ NumberParseException -> 0x009f }
            r3 = 49
            if (r2 != r3) goto L_0x00a3
            goto L_0x00a7
        L_0x009f:
            r8.parse(r3, r4)     // Catch:{ NumberParseException -> 0x00a3 }
            goto L_0x00a7
        L_0x00a3:
            int r9 = r9 + 1
            goto L_0x0057
        L_0x00a6:
            r0 = r1
        L_0x00a7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.common.MoreContactUtils.shouldCollapse(java.lang.CharSequence, java.lang.CharSequence, java.lang.CharSequence, java.lang.CharSequence):boolean");
    }
}
