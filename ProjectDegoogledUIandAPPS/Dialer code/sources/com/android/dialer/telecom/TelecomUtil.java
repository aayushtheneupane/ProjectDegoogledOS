package com.android.dialer.telecom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.CallLog;
import android.support.p000v4.content.ContextCompat;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import android.util.Pair;
import com.android.dialer.common.LogUtil;
import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class TelecomUtil {
    private static TelecomUtilImpl instance = new TelecomUtilImpl();
    private static final Map<Pair<PhoneAccountHandle, String>, Boolean> isVoicemailNumberCache = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public static boolean warningLogged = false;

    public static class TelecomUtilImpl {
        public boolean hasPermission(Context context, String str) {
            return ContextCompat.checkSelfPermission(context, str) == 0;
        }

        public boolean isDefaultDialer(Context context) {
            boolean equals = TextUtils.equals(context.getPackageName(), TelecomUtil.access$000(context).getDefaultDialerPackage());
            if (equals) {
                boolean unused = TelecomUtil.warningLogged = false;
            } else if (!TelecomUtil.warningLogged) {
                LogUtil.m10w("TelecomUtil", "Dialer is not currently set to be default dialer", new Object[0]);
                boolean unused2 = TelecomUtil.warningLogged = true;
            }
            return equals;
        }

        public boolean isInManagedCall(Context context) {
            if (!TelecomUtil.hasReadPhoneStatePermission(context)) {
                return false;
            }
            int i = Build.VERSION.SDK_INT;
            return ((TelecomManager) context.getSystemService("telecom")).isInManagedCall();
        }
    }

    static /* synthetic */ TelecomManager access$000(Context context) {
        return (TelecomManager) context.getSystemService("telecom");
    }

    public static void cancelMissedCallsNotification(Context context) {
        if (hasModifyPhoneStatePermission(context)) {
            try {
                ((TelecomManager) context.getSystemService("telecom")).cancelMissedCallsNotification();
            } catch (SecurityException unused) {
                LogUtil.m10w("TelecomUtil", "TelecomManager.cancelMissedCalls called without permission.", new Object[0]);
            }
        }
    }

    public static PhoneAccountHandle composePhoneAccountHandle(String str, String str2) {
        ComponentName unflattenFromString;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (unflattenFromString = ComponentName.unflattenFromString(str)) == null) {
            return null;
        }
        return new PhoneAccountHandle(unflattenFromString, str2);
    }

    public static Uri getAdnUriForPhoneAccount(Context context, PhoneAccountHandle phoneAccountHandle) {
        if (!hasModifyPhoneStatePermission(context)) {
            return null;
        }
        try {
            return ((TelecomManager) context.getSystemService("telecom")).getAdnUriForPhoneAccount(phoneAccountHandle);
        } catch (SecurityException unused) {
            LogUtil.m10w("TelecomUtil", "TelecomManager.getAdnUriForPhoneAccount called without permission.", new Object[0]);
            return null;
        }
    }

    public static List<PhoneAccountHandle> getCallCapablePhoneAccounts(Context context) {
        if (hasReadPhoneStatePermission(context)) {
            return (List) Optional.fromNullable(((TelecomManager) context.getSystemService("telecom")).getCallCapablePhoneAccounts()).mo10247or(new ArrayList());
        }
        return new ArrayList();
    }

    public static Uri getCallLogUri(Context context) {
        if (hasReadWriteVoicemailPermissions(context)) {
            return CallLog.Calls.CONTENT_URI_WITH_VOICEMAIL;
        }
        return CallLog.Calls.CONTENT_URI;
    }

    public static PhoneAccountHandle getDefaultOutgoingPhoneAccount(Context context, String str) {
        if (hasReadPhoneStatePermission(context)) {
            return ((TelecomManager) context.getSystemService("telecom")).getDefaultOutgoingPhoneAccount(str);
        }
        return null;
    }

    public static PhoneAccountHandle getOtherAccount(Context context, PhoneAccountHandle phoneAccountHandle) {
        if (phoneAccountHandle == null) {
            return null;
        }
        TelecomManager telecomManager = (TelecomManager) context.getSystemService(TelecomManager.class);
        for (PhoneAccountHandle next : telecomManager.getCallCapablePhoneAccounts()) {
            PhoneAccount phoneAccount = telecomManager.getPhoneAccount(next);
            if (phoneAccount != null && phoneAccount.hasCapabilities(4) && !next.equals(phoneAccountHandle)) {
                return next;
            }
        }
        return null;
    }

    public static PhoneAccount getPhoneAccount(Context context, PhoneAccountHandle phoneAccountHandle) {
        return ((TelecomManager) context.getSystemService("telecom")).getPhoneAccount(phoneAccountHandle);
    }

    public static Optional<SubscriptionInfo> getSubscriptionInfo(Context context, PhoneAccountHandle phoneAccountHandle) {
        if (TextUtils.isEmpty(phoneAccountHandle.getId())) {
            return Optional.absent();
        }
        if (!hasPermission(context, "android.permission.READ_PHONE_STATE")) {
            return Optional.absent();
        }
        List<SubscriptionInfo> activeSubscriptionInfoList = ((SubscriptionManager) context.getSystemService(SubscriptionManager.class)).getActiveSubscriptionInfoList();
        if (activeSubscriptionInfoList == null) {
            return Optional.absent();
        }
        for (SubscriptionInfo next : activeSubscriptionInfoList) {
            if (phoneAccountHandle.getId().startsWith(next.getIccId())) {
                return Optional.m67of(next);
            }
        }
        return Optional.absent();
    }

    public static List<PhoneAccountHandle> getSubscriptionPhoneAccounts(Context context) {
        ArrayList arrayList = new ArrayList();
        for (PhoneAccountHandle next : getCallCapablePhoneAccounts(context)) {
            if (((TelecomManager) context.getSystemService("telecom")).getPhoneAccount(next).hasCapabilities(4)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public static String getVoicemailNumber(Context context, PhoneAccountHandle phoneAccountHandle) {
        if (hasReadPhoneStatePermission(context)) {
            return ((TelecomManager) context.getSystemService("telecom")).getVoiceMailNumber(phoneAccountHandle);
        }
        return null;
    }

    public static boolean handleMmi(Context context, String str, PhoneAccountHandle phoneAccountHandle) {
        if (hasModifyPhoneStatePermission(context)) {
            if (phoneAccountHandle != null) {
                return ((TelecomManager) context.getSystemService("telecom")).handleMmi(str, phoneAccountHandle);
            }
            try {
                return ((TelecomManager) context.getSystemService("telecom")).handleMmi(str);
            } catch (SecurityException unused) {
                LogUtil.m10w("TelecomUtil", "TelecomManager.handleMmi called without permission.", new Object[0]);
            }
        }
        return false;
    }

    @Deprecated
    public static boolean hasCallPhonePermission(Context context) {
        return isDefaultDialer(context) || hasPermission(context, "android.permission.CALL_PHONE");
    }

    @Deprecated
    public static boolean hasModifyPhoneStatePermission(Context context) {
        return isDefaultDialer(context) || hasPermission(context, "android.permission.MODIFY_PHONE_STATE");
    }

    private static boolean hasPermission(Context context, String str) {
        return instance.hasPermission(context, str);
    }

    @Deprecated
    public static boolean hasReadPhoneStatePermission(Context context) {
        return isDefaultDialer(context) || hasPermission(context, "android.permission.READ_PHONE_STATE");
    }

    public static boolean hasReadWriteVoicemailPermissions(Context context) {
        return isDefaultDialer(context) || (hasPermission(context, "com.android.voicemail.permission.READ_VOICEMAIL") && hasPermission(context, "com.android.voicemail.permission.WRITE_VOICEMAIL"));
    }

    public static boolean isDefaultDialer(Context context) {
        return instance.isDefaultDialer(context);
    }

    public static boolean isInManagedCall(Context context) {
        return instance.isInManagedCall(context);
    }

    public static boolean isVoicemailNumber(Context context, PhoneAccountHandle phoneAccountHandle, String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Pair pair = new Pair(phoneAccountHandle, str);
        if (isVoicemailNumberCache.containsKey(pair)) {
            return isVoicemailNumberCache.get(pair).booleanValue();
        }
        if (hasReadPhoneStatePermission(context)) {
            z = ((TelecomManager) context.getSystemService("telecom")).isVoiceMailNumber(phoneAccountHandle, str);
        }
        isVoicemailNumberCache.put(pair, Boolean.valueOf(z));
        return z;
    }

    public static boolean placeCall(Context context, Intent intent) {
        if (!hasCallPhonePermission(context)) {
            return false;
        }
        ((TelecomManager) context.getSystemService("telecom")).placeCall(intent.getData(), intent.getExtras());
        return true;
    }

    public static void setInstanceForTesting(TelecomUtilImpl telecomUtilImpl) {
        instance = telecomUtilImpl;
    }

    public static void showInCallScreen(Context context, boolean z) {
        if (hasReadPhoneStatePermission(context)) {
            try {
                ((TelecomManager) context.getSystemService("telecom")).showInCallScreen(z);
            } catch (SecurityException unused) {
                LogUtil.m10w("TelecomUtil", "TelecomManager.showInCallScreen called without permission.", new Object[0]);
            }
        }
    }

    public static void silenceRinger(Context context) {
        if (hasModifyPhoneStatePermission(context)) {
            try {
                ((TelecomManager) context.getSystemService("telecom")).silenceRinger();
            } catch (SecurityException unused) {
                LogUtil.m10w("TelecomUtil", "TelecomManager.silenceRinger called without permission.", new Object[0]);
            }
        }
    }
}
