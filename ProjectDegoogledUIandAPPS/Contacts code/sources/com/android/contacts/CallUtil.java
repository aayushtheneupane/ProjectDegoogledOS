package com.android.contacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import com.android.contacts.compat.CompatUtils;
import com.android.contacts.util.PermissionsUtil;
import com.android.contacts.util.PhoneNumberHelper;
import com.android.contactsbind.FeedbackHelper;
import com.android.contactsbind.experiments.Flags;
import java.util.ArrayList;
import java.util.List;

public class CallUtil {
    public static Intent getCallWithSubjectIntent(String str, PhoneAccountHandle phoneAccountHandle, String str2) {
        Intent callIntent = getCallIntent(getCallUri(str));
        callIntent.putExtra("android.telecom.extra.CALL_SUBJECT", str2);
        if (phoneAccountHandle != null) {
            callIntent.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandle);
        }
        return callIntent;
    }

    public static Intent getCallIntent(String str) {
        Uri callUri = getCallUri(str);
        return PhoneNumberUtils.isEmergencyNumber(str) ? getCallIntentForEmergencyNumber(callUri) : getCallIntent(callUri);
    }

    private static Intent getCallIntentForEmergencyNumber(Uri uri) {
        return new Intent("android.intent.action.DIAL", uri);
    }

    public static Intent getCallIntent(Uri uri) {
        return new Intent("android.intent.action.CALL", uri);
    }

    public static Intent getVideoCallIntent(String str, String str2) {
        Intent intent = new Intent("android.intent.action.CALL", getCallUri(str));
        intent.putExtra("android.telecom.extra.START_CALL_WITH_VIDEO_STATE", 3);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("com.android.phone.CALL_ORIGIN", str2);
        }
        return intent;
    }

    public static Uri getCallUri(String str) {
        if (PhoneNumberHelper.isUriNumber(str)) {
            return Uri.fromParts("sip", str, (String) null);
        }
        return Uri.fromParts("tel", str, (String) null);
    }

    public static int getVideoCallingAvailability(Context context) {
        TelecomManager telecomManager;
        if (!PermissionsUtil.hasPermission(context, "android.permission.READ_PHONE_STATE") || !CompatUtils.isVideoCompatible() || (telecomManager = (TelecomManager) context.getSystemService("telecom")) == null) {
            return 0;
        }
        try {
            for (PhoneAccountHandle phoneAccount : telecomManager.getCallCapablePhoneAccounts()) {
                PhoneAccount phoneAccount2 = telecomManager.getPhoneAccount(phoneAccount);
                if (phoneAccount2 != null && phoneAccount2.hasCapabilities(8)) {
                    if (CompatUtils.isVideoPresenceCompatible() && phoneAccount2.hasCapabilities(256)) {
                        return 3;
                    }
                    return 1;
                }
            }
            return 0;
        } catch (SecurityException e) {
            FeedbackHelper.sendFeedback(context, "CallUtil", "Security exception when getting call capable phone accounts", e);
            return 0;
        }
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

    public static boolean isCallWithSubjectSupported(Context context) {
        TelecomManager telecomManager;
        if (!PermissionsUtil.hasPermission(context, "android.permission.READ_PHONE_STATE") || !CompatUtils.isCallSubjectCompatible() || (telecomManager = (TelecomManager) context.getSystemService("telecom")) == null) {
            return false;
        }
        try {
            for (PhoneAccountHandle phoneAccount : telecomManager.getCallCapablePhoneAccounts()) {
                PhoneAccount phoneAccount2 = telecomManager.getPhoneAccount(phoneAccount);
                if (phoneAccount2 != null && phoneAccount2.hasCapabilities(64)) {
                    return true;
                }
            }
            return false;
        } catch (SecurityException e) {
            FeedbackHelper.sendFeedback(context, "CallUtil", "Security exception when getting call capable phone accounts", e);
            return false;
        }
    }

    public static boolean isTachyonEnabled(Context context) {
        TelecomManager telecomManager;
        if (!PermissionsUtil.hasPermission(context, "android.permission.READ_PHONE_STATE") || !CompatUtils.isNCompatible() || (telecomManager = (TelecomManager) context.getSystemService("telecom")) == null) {
            return false;
        }
        try {
            for (PhoneAccountHandle phoneAccount : telecomManager.getCallCapablePhoneAccounts()) {
                PhoneAccount phoneAccount2 = telecomManager.getPhoneAccount(phoneAccount);
                if (phoneAccount2 != null) {
                    Bundle extras = phoneAccount2.getExtras();
                    boolean z = extras != null && extras.getBoolean("android.telecom.extra.SUPPORTS_VIDEO_CALLING_FALLBACK");
                    if (Log.isLoggable("CallUtil", 3)) {
                        Log.d("CallUtil", "Device video fallback config: " + z);
                    }
                    PersistableBundle config = ((CarrierConfigManager) context.getSystemService(CarrierConfigManager.class)).getConfig();
                    boolean z2 = config != null && config.getBoolean("allow_video_calling_fallback_bool");
                    if (Log.isLoggable("CallUtil", 3)) {
                        Log.d("CallUtil", "Carrier video fallback config: " + z2);
                    }
                    boolean z3 = Flags.getInstance().getBoolean("QuickContact__video_call_integration");
                    if (Log.isLoggable("CallUtil", 3)) {
                        Log.d("CallUtil", "Experiment video fallback config: " + z3);
                    }
                    if (!z || !z2 || !z3) {
                        return false;
                    }
                    return true;
                }
            }
            return false;
        } catch (SecurityException e) {
            FeedbackHelper.sendFeedback(context, "CallUtil", "Security exception when getting call capable phone accounts", e);
            return false;
        }
    }
}
