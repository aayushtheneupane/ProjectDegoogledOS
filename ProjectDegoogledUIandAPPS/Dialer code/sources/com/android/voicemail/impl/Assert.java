package com.android.voicemail.impl;

import android.content.Context;
import android.os.Looper;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class Assert {
    private static Boolean isMainThreadForTest;

    public static VoicemailStatus$DeferredEditor deferredEdit(Context context, PhoneAccountHandle phoneAccountHandle) {
        return new VoicemailStatus$DeferredEditor(context, phoneAccountHandle, (VoicemailStatus$1) null);
    }

    public static VoicemailStatus$Editor edit(Context context, PhoneAccountHandle phoneAccountHandle) {
        return new VoicemailStatus$Editor(context, phoneAccountHandle, (VoicemailStatus$1) null);
    }

    public static void handleEvent(Context context, OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper, VoicemailStatus$Editor voicemailStatus$Editor, OmtpEvents omtpEvents) {
        int type = omtpEvents.getType();
        if (type == 1) {
            switch (omtpEvents.ordinal()) {
                case 0:
                case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE:
                case 2:
                    voicemailStatus$Editor.setConfigurationState(0);
                    voicemailStatus$Editor.setNotificationChannelState(0);
                    voicemailStatus$Editor.apply();
                    return;
                case 3:
                    voicemailStatus$Editor.setConfigurationState(3);
                    voicemailStatus$Editor.setNotificationChannelState(0);
                    voicemailStatus$Editor.setDataChannelState(0);
                    voicemailStatus$Editor.apply();
                    return;
                case 4:
                    voicemailStatus$Editor.setConfigurationState(0);
                    voicemailStatus$Editor.setNotificationChannelState(0);
                    voicemailStatus$Editor.setDataChannelState(0);
                    voicemailStatus$Editor.apply();
                    return;
                case 5:
                    voicemailStatus$Editor.setConfigurationState(4);
                    voicemailStatus$Editor.apply();
                    return;
                case 6:
                    voicemailStatus$Editor.setConfigurationState(4);
                    voicemailStatus$Editor.apply();
                    return;
                default:
                    VvmLog.wtf("DefErrorCodeHandler", "invalid configuration event " + omtpEvents);
                    return;
            }
        } else if (type == 2) {
            switch (omtpEvents.ordinal()) {
                case 7:
                case 8:
                    voicemailStatus$Editor.setDataChannelState(0);
                    voicemailStatus$Editor.apply();
                    return;
                case 9:
                    voicemailStatus$Editor.setDataChannelState(3);
                    voicemailStatus$Editor.apply();
                    return;
                case 10:
                    voicemailStatus$Editor.setDataChannelState(2);
                    voicemailStatus$Editor.apply();
                    return;
                case 11:
                    voicemailStatus$Editor.setDataChannelState(1);
                    voicemailStatus$Editor.apply();
                    return;
                case 12:
                    voicemailStatus$Editor.setDataChannelState(6);
                    voicemailStatus$Editor.apply();
                    return;
                case 13:
                case 24:
                case 25:
                case 27:
                case 29:
                    voicemailStatus$Editor.setDataChannelState(5);
                    voicemailStatus$Editor.apply();
                    return;
                case 14:
                case 15:
                case 26:
                case 28:
                    voicemailStatus$Editor.setDataChannelState(4);
                    voicemailStatus$Editor.apply();
                    return;
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                    voicemailStatus$Editor.setDataChannelState(3);
                    voicemailStatus$Editor.apply();
                    return;
                default:
                    VvmLog.wtf("DefErrorCodeHandler", "invalid data channel event " + omtpEvents);
                    return;
            }
        } else if (type == 3) {
            int ordinal = omtpEvents.ordinal();
            if (ordinal == 30) {
                voicemailStatus$Editor.setNotificationChannelState(0);
                voicemailStatus$Editor.setDataChannelState(0);
                voicemailStatus$Editor.apply();
            } else if (ordinal != 31) {
                VvmLog.wtf("DefErrorCodeHandler", "invalid notification channel event " + omtpEvents);
            } else {
                voicemailStatus$Editor.setNotificationChannelState(1);
                if (omtpVvmCarrierConfigHelper.isCellularDataRequired()) {
                    voicemailStatus$Editor.setDataChannelState(2);
                }
                voicemailStatus$Editor.apply();
            }
        } else if (type != 4) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("invalid event type ");
            outline13.append(omtpEvents.getType());
            outline13.append(" for ");
            outline13.append(omtpEvents);
            VvmLog.wtf("DefErrorCodeHandler", outline13.toString());
        } else if (omtpEvents.ordinal() != 32) {
            VvmLog.wtf("DefErrorCodeHandler", "invalid other event " + omtpEvents);
        } else {
            voicemailStatus$Editor.setConfigurationState(1);
            voicemailStatus$Editor.setNotificationChannelState(1);
            voicemailStatus$Editor.setDataChannelState(1);
            voicemailStatus$Editor.apply();
        }
    }

    public static void isMainThread() {
        Boolean bool = isMainThreadForTest;
        if (bool != null) {
            isTrue(bool.booleanValue());
        } else {
            isTrue(Looper.getMainLooper().equals(Looper.myLooper()));
        }
    }

    public static void isNotMainThread() {
        Boolean bool = isMainThreadForTest;
        if (bool != null) {
            isTrue(!bool.booleanValue());
        } else {
            isTrue(!Looper.getMainLooper().equals(Looper.myLooper()));
        }
    }

    public static void isTrue(boolean z) {
        if (!z) {
            throw new AssertionError("Expected condition to be true");
        }
    }
}
