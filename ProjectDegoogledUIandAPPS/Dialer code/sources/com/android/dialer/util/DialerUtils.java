package com.android.dialer.util;

import android.content.Context;
import android.content.Intent;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.android.dialer.telecom.TelecomUtil;
import java.io.File;
import java.util.Random;

public class DialerUtils {
    private static final Random RANDOM = new Random();

    static /* synthetic */ void access$000(Context context, Intent intent) {
        if (!TelecomUtil.placeCall(context, intent)) {
            Toast.makeText(context, "Cannot place call without Phone permission", 0).show();
        }
    }

    public static File createShareableFile(Context context) {
        long abs = Math.abs(RANDOM.nextLong());
        File file = new File(context.getCacheDir(), "my_cache");
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, String.valueOf(abs));
    }

    public static void hideInputMethod(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static CharSequence join(Iterable<CharSequence> iterable) {
        StringBuilder sb = new StringBuilder();
        BidiFormatter instance = BidiFormatter.getInstance();
        boolean z = true;
        for (CharSequence charSequence : iterable) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(instance.unicodeWrap(charSequence.toString(), TextDirectionHeuristics.FIRSTSTRONG_LTR));
        }
        return instance.unicodeWrap(sb.toString());
    }

    public static void showInputMethod(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(view, 0);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0071 A[Catch:{ ActivityNotFoundException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009e A[Catch:{ ActivityNotFoundException -> 0x00b2 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void startActivityWithErrorToast(final android.content.Context r5, final android.content.Intent r6, int r7) {
        /*
            java.lang.String r0 = "android.telecom.extra.OUTGOING_CALL_EXTRAS"
            r1 = 0
            java.lang.String r2 = "android.intent.action.CALL"
            java.lang.String r3 = r6.getAction()     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            boolean r2 = r2.equals(r3)     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            if (r2 == 0) goto L_0x00ae
            com.android.dialer.util.TouchPointManager r2 = com.android.dialer.util.TouchPointManager.getInstance()     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            android.graphics.Point r2 = r2.getPoint()     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            int r3 = r2.x     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            if (r3 != 0) goto L_0x001f
            int r3 = r2.y     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            if (r3 == 0) goto L_0x0039
        L_0x001f:
            boolean r3 = r6.hasExtra(r0)     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            if (r3 == 0) goto L_0x002c
            android.os.Parcelable r3 = r6.getParcelableExtra(r0)     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            android.os.Bundle r3 = (android.os.Bundle) r3     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            goto L_0x0031
        L_0x002c:
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            r3.<init>()     // Catch:{ ActivityNotFoundException -> 0x00b2 }
        L_0x0031:
            java.lang.String r4 = "touchPoint"
            r3.putParcelable(r4, r2)     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            r6.putExtra(r0, r3)     // Catch:{ ActivityNotFoundException -> 0x00b2 }
        L_0x0039:
            android.net.Uri r0 = r6.getData()     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            java.lang.String r0 = r0.getSchemeSpecificPart()     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            if (r0 == 0) goto L_0x006e
            java.lang.String r2 = "*272"
            boolean r0 = r0.startsWith(r2)     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            if (r0 == 0) goto L_0x006e
            java.lang.Class<android.telephony.TelephonyManager> r0 = android.telephony.TelephonyManager.class
            java.lang.Object r0 = r5.getSystemService(r0)     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            int r2 = r0.getVoiceNetworkType()     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            r3 = 13
            r4 = 1
            if (r2 != r3) goto L_0x005e
            r2 = r4
            goto L_0x005f
        L_0x005e:
            r2 = r1
        L_0x005f:
            int r0 = r0.getCallState()     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            r3 = 2
            if (r0 != r3) goto L_0x0068
            r0 = r4
            goto L_0x0069
        L_0x0068:
            r0 = r1
        L_0x0069:
            if (r2 == 0) goto L_0x006e
            if (r0 == 0) goto L_0x006e
            goto L_0x006f
        L_0x006e:
            r4 = r1
        L_0x006f:
            if (r4 == 0) goto L_0x009e
            java.lang.String r0 = "DialUtils.startActivityWithErrorToast"
            java.lang.String r2 = "showing outgoing WPS dialog before placing call"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            com.android.dialer.common.LogUtil.m9i(r0, r2, r3)     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            android.app.AlertDialog$Builder r0 = new android.app.AlertDialog$Builder     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            r0.<init>(r5)     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            r2 = 2131821201(0x7f110291, float:1.9275138E38)
            r0.setMessage(r2)     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            r2 = 2131820919(0x7f110177, float:1.9274567E38)
            com.android.dialer.util.DialerUtils$1 r3 = new com.android.dialer.util.DialerUtils$1     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            r3.<init>(r5, r6)     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            r0.setPositiveButton(r2, r3)     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            r6 = 17039360(0x1040000, float:2.424457E-38)
            r2 = 0
            r0.setNegativeButton(r6, r2)     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            android.app.AlertDialog r6 = r0.create()     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            r6.show()     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            goto L_0x00b9
        L_0x009e:
            boolean r6 = com.android.dialer.telecom.TelecomUtil.placeCall(r5, r6)     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            if (r6 != 0) goto L_0x00b9
            java.lang.String r6 = "Cannot place call without Phone permission"
            android.widget.Toast r6 = android.widget.Toast.makeText(r5, r6, r1)     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            r6.show()     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            goto L_0x00b9
        L_0x00ae:
            r5.startActivity(r6)     // Catch:{ ActivityNotFoundException -> 0x00b2 }
            goto L_0x00b9
        L_0x00b2:
            android.widget.Toast r5 = android.widget.Toast.makeText(r5, r7, r1)
            r5.show()
        L_0x00b9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.util.DialerUtils.startActivityWithErrorToast(android.content.Context, android.content.Intent, int):void");
    }
}
