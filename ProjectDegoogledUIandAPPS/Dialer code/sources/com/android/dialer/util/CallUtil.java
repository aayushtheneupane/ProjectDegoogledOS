package com.android.dialer.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PowerManager;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.android.dialer.common.LogUtil;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CallUtil {
    private static boolean cachedIsVideoEnabledState;
    private static boolean hasInitializedIsVideoEnabledState;

    public static boolean areAnimationsDisabled(Context context) {
        return Settings.Global.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f) == 0.0f || ((PowerManager) context.getSystemService(PowerManager.class)).isPowerSaveMode();
    }

    public static void doOnGlobalLayout(View view, ViewUtil$ViewRunnable viewUtil$ViewRunnable) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewUtil$3(view, viewUtil$ViewRunnable));
    }

    public static void doOnPreDraw(View view, boolean z, Runnable runnable) {
        view.getViewTreeObserver().addOnPreDrawListener(new ViewUtil$1(view, runnable, z));
    }

    public static Intent getAddToExistingContactIntent(CharSequence charSequence, CharSequence charSequence2, int i) {
        Intent intent = new Intent("android.intent.action.INSERT_OR_EDIT");
        intent.setType("vnd.android.cursor.item/contact");
        populateContactIntent(intent, charSequence, charSequence2, i);
        return intent;
    }

    public static List<PhoneAccount> getCallCapablePhoneAccounts(Context context, String str) {
        if (!PermissionsUtil.hasPermission(context, "android.permission.READ_PHONE_STATE")) {
            return null;
        }
        TelecomManager telecomManager = (TelecomManager) context.getSystemService("telecom");
        ArrayList arrayList = new ArrayList();
        for (PhoneAccountHandle phoneAccount : telecomManager.getCallCapablePhoneAccounts()) {
            PhoneAccount phoneAccount2 = telecomManager.getPhoneAccount(phoneAccount);
            if (phoneAccount2 != null && phoneAccount2.supportsUriScheme(str)) {
                arrayList.add(phoneAccount2);
            }
        }
        return arrayList;
    }

    public static Uri getCallUri(String str) {
        if (PhoneNumberHelper.isUriNumber(str)) {
            return Uri.fromParts("sip", str, (String) null);
        }
        return Uri.fromParts("tel", str, (String) null);
    }

    public static Intent getNewContactIntent() {
        return new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI).addFlags(1);
    }

    public static Intent getSendSmsIntent(CharSequence charSequence) {
        return new Intent("android.intent.action.SENDTO", Uri.parse("sms:" + charSequence));
    }

    @SuppressLint({"MissingPermission"})
    public static int getVideoCallingAvailability(Context context) {
        TelecomManager telecomManager;
        if (!PermissionsUtil.hasPermission(context, "android.permission.READ_PHONE_STATE") || (telecomManager = (TelecomManager) context.getSystemService("telecom")) == null) {
            return 0;
        }
        for (PhoneAccountHandle phoneAccount : telecomManager.getCallCapablePhoneAccounts()) {
            PhoneAccount phoneAccount2 = telecomManager.getPhoneAccount(phoneAccount);
            if (phoneAccount2 != null && phoneAccount2.hasCapabilities(8)) {
                if (phoneAccount2.hasCapabilities(256)) {
                    return 3;
                }
                return 1;
            }
        }
        return 0;
    }

    public static boolean isRtl() {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
    }

    public static boolean isVideoEnabled(Context context) {
        boolean z = (getVideoCallingAvailability(context) & 1) != 0;
        if (!hasInitializedIsVideoEnabledState) {
            LogUtil.m9i("CallUtil.isVideoEnabled", GeneratedOutlineSupport.outline10("isVideoEnabled: ", z), new Object[0]);
            hasInitializedIsVideoEnabledState = true;
            cachedIsVideoEnabledState = z;
        } else {
            boolean z2 = cachedIsVideoEnabledState;
            if (z2 != z) {
                LogUtil.m9i("CallUtil.isVideoEnabled", "isVideoEnabled changed from %b to %b", Boolean.valueOf(z2), Boolean.valueOf(z));
                cachedIsVideoEnabledState = z;
            }
        }
        return z;
    }

    private static void populateContactIntent(Intent intent, CharSequence charSequence, CharSequence charSequence2, int i) {
        if (charSequence2 != null) {
            intent.putExtra("phone", charSequence2);
        }
        if (charSequence != null) {
            intent.putExtra("name", charSequence);
        }
        if (i != -1) {
            intent.putExtra("phone_type", i);
        }
    }

    public static void resizeText(TextView textView, int i, int i2) {
        TextPaint paint = textView.getPaint();
        int width = textView.getWidth();
        if (width != 0) {
            float f = (float) i;
            textView.setTextSize(0, f);
            float measureText = ((float) width) / paint.measureText(textView.getText().toString());
            if (measureText <= 1.0f) {
                textView.setTextSize(0, Math.max((float) i2, f * measureText));
            }
        }
    }

    public static Intent getNewContactIntent(CharSequence charSequence, CharSequence charSequence2, int i) {
        Intent newContactIntent = getNewContactIntent();
        populateContactIntent(newContactIntent, charSequence, charSequence2, i);
        return newContactIntent;
    }
}
