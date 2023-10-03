package com.android.dialer.notification;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioAttributes;
import android.os.Build;
import android.provider.Settings;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.ArraySet;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.util.PermissionsUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@TargetApi(26)
final class VoicemailChannelUtils {
    static final String GLOBAL_VOICEMAIL_CHANNEL_ID = "phone_voicemail";

    static void createAllChannels(Context context) {
        int i = Build.VERSION.SDK_INT;
        Assert.checkArgument(true);
        Assert.isNotNull(context);
        if (isSingleSimDevice(context)) {
            NotificationChannel newChannel = newChannel(context, GLOBAL_VOICEMAIL_CHANNEL_ID, (CharSequence) null);
            if (!PermissionsUtil.hasReadPhoneStatePermissions(context)) {
                LogUtil.m9i("VoicemailChannelUtils.migrateGlobalVoicemailSoundSettings", "missing phone permission, not migrating sound settings", new Object[0]);
            } else {
                PhoneAccountHandle defaultOutgoingPhoneAccount = ((TelecomManager) context.getSystemService(TelecomManager.class)).getDefaultOutgoingPhoneAccount("tel");
                if (defaultOutgoingPhoneAccount == null) {
                    LogUtil.m9i("VoicemailChannelUtils.migrateGlobalVoicemailSoundSettings", "phone account is null, not migrating sound settings", new Object[0]);
                } else if (!isChannelAllowedForAccount(context, defaultOutgoingPhoneAccount)) {
                    LogUtil.m9i("VoicemailChannelUtils.migrateGlobalVoicemailSoundSettings", "phone account is not eligable, not migrating sound settings", new Object[0]);
                } else {
                    migrateVoicemailSoundSettings(context, newChannel, defaultOutgoingPhoneAccount);
                }
            }
            ((NotificationManager) context.getSystemService(NotificationManager.class)).createNotificationChannel(newChannel);
            return;
        }
        for (PhoneAccountHandle createVoicemailChannelForAccount : getAllEligableAccounts(context)) {
            createVoicemailChannelForAccount(context, createVoicemailChannelForAccount);
        }
    }

    private static void createVoicemailChannelForAccount(Context context, PhoneAccountHandle phoneAccountHandle) {
        PhoneAccount phoneAccount = ((TelecomManager) context.getSystemService(TelecomManager.class)).getPhoneAccount(phoneAccountHandle);
        if (phoneAccount != null) {
            NotificationChannel newChannel = newChannel(context, getChannelIdForAccount(phoneAccountHandle), phoneAccount.getLabel());
            migrateVoicemailSoundSettings(context, newChannel, phoneAccountHandle);
            ((NotificationManager) context.getSystemService(NotificationManager.class)).createNotificationChannel(newChannel);
        }
    }

    static Set<String> getAllChannelIds(Context context) {
        int i = Build.VERSION.SDK_INT;
        Assert.checkArgument(true);
        Assert.isNotNull(context);
        ArraySet arraySet = new ArraySet();
        if (isSingleSimDevice(context)) {
            arraySet.add(GLOBAL_VOICEMAIL_CHANNEL_ID);
        } else {
            for (PhoneAccountHandle channelIdForAccount : getAllEligableAccounts(context)) {
                arraySet.add(getChannelIdForAccount(channelIdForAccount));
            }
        }
        return arraySet;
    }

    private static List<PhoneAccountHandle> getAllEligableAccounts(Context context) {
        ArrayList arrayList = new ArrayList();
        for (PhoneAccountHandle next : ((TelecomManager) context.getSystemService(TelecomManager.class)).getCallCapablePhoneAccounts()) {
            if (isChannelAllowedForAccount(context, next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    static String getChannelId(Context context, PhoneAccountHandle phoneAccountHandle) {
        int i = Build.VERSION.SDK_INT;
        boolean z = true;
        Assert.checkArgument(true);
        Assert.isNotNull(context);
        if (isSingleSimDevice(context)) {
            return GLOBAL_VOICEMAIL_CHANNEL_ID;
        }
        if (phoneAccountHandle == null) {
            LogUtil.m9i("VoicemailChannelUtils.getChannelId", "no phone account on a multi-SIM device, using default channel", new Object[0]);
            return "phone_default";
        } else if (!isChannelAllowedForAccount(context, phoneAccountHandle)) {
            LogUtil.m9i("VoicemailChannelUtils.getChannelId", "phone account is not for a SIM, using default channel", new Object[0]);
            return "phone_default";
        } else {
            String channelIdForAccount = getChannelIdForAccount(phoneAccountHandle);
            if (((NotificationManager) context.getSystemService(NotificationManager.class)).getNotificationChannel(channelIdForAccount) == null) {
                z = false;
            }
            if (!z) {
                LogUtil.m9i("VoicemailChannelUtils.getChannelId", "voicemail channel not found for phone account (possible SIM swap?), creating a new one", new Object[0]);
                createVoicemailChannelForAccount(context, phoneAccountHandle);
            }
            return channelIdForAccount;
        }
    }

    private static String getChannelIdForAccount(PhoneAccountHandle phoneAccountHandle) {
        Assert.isNotNull(phoneAccountHandle);
        return "phone_voicemail_account_:" + phoneAccountHandle.getId();
    }

    private static boolean isChannelAllowedForAccount(Context context, PhoneAccountHandle phoneAccountHandle) {
        PhoneAccount phoneAccount = ((TelecomManager) context.getSystemService(TelecomManager.class)).getPhoneAccount(phoneAccountHandle);
        if (phoneAccount != null && phoneAccount.hasCapabilities(4)) {
            return true;
        }
        return false;
    }

    private static boolean isSingleSimDevice(Context context) {
        if (PermissionsUtil.hasReadPhoneStatePermissions(context) && ((TelephonyManager) context.getSystemService(TelephonyManager.class)).getPhoneCount() > 1) {
            return false;
        }
        return true;
    }

    private static void migrateVoicemailSoundSettings(Context context, NotificationChannel notificationChannel, PhoneAccountHandle phoneAccountHandle) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        notificationChannel.enableVibration(telephonyManager.isVoicemailVibrationEnabled(phoneAccountHandle));
        notificationChannel.setSound(telephonyManager.getVoicemailRingtoneUri(phoneAccountHandle), new AudioAttributes.Builder().setUsage(5).build());
    }

    private static NotificationChannel newChannel(Context context, String str, CharSequence charSequence) {
        CharSequence text = context.getText(R.string.notification_channel_voicemail);
        if (!TextUtils.isEmpty(charSequence)) {
            text = TextUtils.concat(new CharSequence[]{text, ": ", charSequence});
        }
        NotificationChannel notificationChannel = new NotificationChannel(str, text, 3);
        notificationChannel.setShowBadge(true);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setSound(Settings.System.DEFAULT_NOTIFICATION_URI, new AudioAttributes.Builder().setUsage(5).build());
        return notificationChannel;
    }
}
