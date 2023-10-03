package com.android.dialer.voicemail.listui.error;

import android.content.Context;
import android.os.Build;

public class VoicemailErrorMessageCreator {
    public VoicemailErrorMessage create(Context context, VoicemailStatus voicemailStatus, VoicemailStatusReader voicemailStatusReader) {
        int i = Build.VERSION.SDK_INT;
        String str = voicemailStatus.type;
        char c = 65535;
        if (str.hashCode() == -1478600199 && str.equals("vvm_type_vvm3")) {
            c = 0;
        }
        if (c != 0) {
            return OmtpVoicemailMessageCreator.create(context, voicemailStatus, voicemailStatusReader);
        }
        return OmtpVoicemailMessageCreator.create1(context, voicemailStatus, voicemailStatusReader);
    }

    public boolean isSyncBlockingError(VoicemailStatus voicemailStatus) {
        String str = voicemailStatus.type;
        if (((str.hashCode() == -1478600199 && str.equals("vvm_type_vvm3")) ? (char) 0 : 65535) != 0) {
            if (voicemailStatus.notificationChannelState != 0 || voicemailStatus.dataChannelState != 0) {
                return true;
            }
            int i = voicemailStatus.configurationState;
            if (i == 0 || i == 3) {
                return false;
            }
            return true;
        } else if (voicemailStatus.notificationChannelState != 0 || voicemailStatus.dataChannelState != 0) {
            return true;
        } else {
            int i2 = voicemailStatus.configurationState;
            if (i2 == -100 || i2 == 0 || i2 == 3) {
                return false;
            }
            return true;
        }
    }
}
