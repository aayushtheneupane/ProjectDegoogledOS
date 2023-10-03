package com.android.contacts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.contacts.model.account.AccountType;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

public class MoreContactUtils {
    private static final String WAIT_SYMBOL_AS_STRING = String.valueOf(';');

    public static boolean shouldCollapse(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4) {
        if (!TextUtils.equals(charSequence, charSequence3)) {
            return false;
        }
        if (TextUtils.equals(charSequence2, charSequence4)) {
            return true;
        }
        if (charSequence2 == null || charSequence4 == null || !TextUtils.equals("vnd.android.cursor.item/phone_v2", charSequence)) {
            return false;
        }
        return shouldCollapsePhoneNumbers(charSequence2.toString(), charSequence4.toString());
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:33|34|41|35) */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r0.parse(r5, (java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0081, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        continue;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x007e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean shouldCollapsePhoneNumbers(java.lang.String r8, java.lang.String r9) {
        /*
            java.lang.String r0 = "#"
            boolean r1 = r8.contains(r0)
            boolean r0 = r9.contains(r0)
            r2 = 0
            if (r1 != r0) goto L_0x0087
            java.lang.String r0 = "*"
            boolean r1 = r8.contains(r0)
            boolean r0 = r9.contains(r0)
            if (r1 == r0) goto L_0x001a
            goto L_0x0087
        L_0x001a:
            java.lang.String r0 = WAIT_SYMBOL_AS_STRING
            java.lang.String[] r8 = r8.split(r0)
            java.lang.String r0 = WAIT_SYMBOL_AS_STRING
            java.lang.String[] r9 = r9.split(r0)
            int r0 = r8.length
            int r1 = r9.length
            if (r0 == r1) goto L_0x002b
            return r2
        L_0x002b:
            com.google.i18n.phonenumbers.PhoneNumberUtil r0 = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance()
            r1 = 0
        L_0x0030:
            int r3 = r8.length
            r4 = 1
            if (r1 >= r3) goto L_0x0086
            r3 = r8[r1]
            java.lang.String r3 = android.telephony.PhoneNumberUtils.convertKeypadLettersToDigits(r3)
            r5 = r9[r1]
            boolean r6 = android.text.TextUtils.equals(r3, r5)
            if (r6 == 0) goto L_0x0043
            goto L_0x0082
        L_0x0043:
            com.google.i18n.phonenumbers.PhoneNumberUtil$MatchType r6 = r0.isNumberMatch((java.lang.CharSequence) r3, (java.lang.CharSequence) r5)
            int[] r7 = com.android.contacts.MoreContactUtils.C02441.f8xdbccbb2a
            int r6 = r6.ordinal()
            r6 = r7[r6]
            if (r6 == r4) goto L_0x0085
            r7 = 2
            if (r6 == r7) goto L_0x0085
            r7 = 3
            if (r6 == r7) goto L_0x0082
            r7 = 4
            if (r6 == r7) goto L_0x0066
            r8 = 5
            if (r6 != r8) goto L_0x005e
            return r2
        L_0x005e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "Unknown result value from phone number library"
            r8.<init>(r9)
            throw r8
        L_0x0066:
            r6 = 0
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber r3 = r0.parse(r3, r6)     // Catch:{ NumberParseException -> 0x007e }
            int r3 = r3.getCountryCode()     // Catch:{ NumberParseException -> 0x007e }
            if (r3 != r4) goto L_0x0081
            java.lang.String r3 = r5.trim()     // Catch:{ NumberParseException -> 0x007e }
            char r3 = r3.charAt(r2)     // Catch:{ NumberParseException -> 0x007e }
            r4 = 49
            if (r3 != r4) goto L_0x0082
            return r2
        L_0x007e:
            r0.parse(r5, r6)     // Catch:{ NumberParseException -> 0x0082 }
        L_0x0081:
            return r2
        L_0x0082:
            int r1 = r1 + 1
            goto L_0x0030
        L_0x0085:
            return r2
        L_0x0086:
            return r4
        L_0x0087:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.MoreContactUtils.shouldCollapsePhoneNumbers(java.lang.String, java.lang.String):boolean");
    }

    /* renamed from: com.android.contacts.MoreContactUtils$1 */
    static /* synthetic */ class C02441 {

        /* renamed from: $SwitchMap$com$google$i18n$phonenumbers$PhoneNumberUtil$MatchType */
        static final /* synthetic */ int[] f8xdbccbb2a = new int[PhoneNumberUtil.MatchType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.google.i18n.phonenumbers.PhoneNumberUtil$MatchType[] r0 = com.google.i18n.phonenumbers.PhoneNumberUtil.MatchType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f8xdbccbb2a = r0
                int[] r0 = f8xdbccbb2a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.i18n.phonenumbers.PhoneNumberUtil$MatchType r1 = com.google.i18n.phonenumbers.PhoneNumberUtil.MatchType.NOT_A_NUMBER     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f8xdbccbb2a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.i18n.phonenumbers.PhoneNumberUtil$MatchType r1 = com.google.i18n.phonenumbers.PhoneNumberUtil.MatchType.NO_MATCH     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f8xdbccbb2a     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.i18n.phonenumbers.PhoneNumberUtil$MatchType r1 = com.google.i18n.phonenumbers.PhoneNumberUtil.MatchType.EXACT_MATCH     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f8xdbccbb2a     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.i18n.phonenumbers.PhoneNumberUtil$MatchType r1 = com.google.i18n.phonenumbers.PhoneNumberUtil.MatchType.NSN_MATCH     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f8xdbccbb2a     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.i18n.phonenumbers.PhoneNumberUtil$MatchType r1 = com.google.i18n.phonenumbers.PhoneNumberUtil.MatchType.SHORT_NSN_MATCH     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.MoreContactUtils.C02441.<clinit>():void");
        }
    }

    public static Rect getTargetRectFromView(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = iArr[0] + view.getWidth();
        rect.bottom = iArr[1] + view.getHeight();
        return rect;
    }

    public static TextView createHeaderView(Context context, int i) {
        TextView textView = (TextView) View.inflate(context, R.layout.list_separator, (ViewGroup) null);
        textView.setText(context.getString(i));
        return textView;
    }

    public static void setHeaderViewBottomPadding(Context context, TextView textView, boolean z) {
        float f;
        if (z) {
            f = context.getResources().getDimension(R.dimen.frequently_contacted_title_top_margin_when_first_row);
        } else {
            f = context.getResources().getDimension(R.dimen.frequently_contacted_title_top_margin);
        }
        textView.setPaddingRelative(textView.getPaddingStart(), (int) f, textView.getPaddingEnd(), textView.getPaddingBottom());
    }

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
}
