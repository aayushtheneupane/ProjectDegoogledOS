package com.android.dialer.voicemail.listui.error;

import android.content.ComponentName;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telecom.PhoneAccountHandle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.android.dialer.common.LogUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.VoicemailClient;
import com.android.voicemail.VoicemailComponent;

public class VoicemailStatus {
    public final int configurationState;
    public final int dataChannelState;
    public final boolean isAirplaneMode;
    public final int notificationChannelState;
    public final String phoneAccountComponentName;
    public final String phoneAccountId;
    public final int quotaOccupied;
    public final int quotaTotal;
    public final Uri settingsUri;
    public final String sourcePackage;
    public final String type;
    public final Uri voicemailAccessUri;

    public VoicemailStatus(Context context, Cursor cursor) {
        String str;
        String str2;
        String str3;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        boolean z = false;
        String str4 = "";
        if (cursor.isNull(0)) {
            str = str4;
        } else {
            str = cursor.getString(0);
        }
        this.sourcePackage = str;
        Uri uri = null;
        this.settingsUri = cursor.getString(1) != null ? Uri.parse(cursor.getString(1)) : null;
        this.voicemailAccessUri = cursor.getString(2) != null ? Uri.parse(cursor.getString(2)) : uri;
        int i6 = Build.VERSION.SDK_INT;
        if (cursor.isNull(10)) {
            str2 = "vvm_type_omtp";
        } else {
            str2 = cursor.getString(10);
        }
        this.type = str2;
        if (cursor.isNull(8)) {
            str3 = str4;
        } else {
            str3 = cursor.getString(8);
        }
        this.phoneAccountComponentName = str3;
        this.phoneAccountId = !cursor.isNull(9) ? cursor.getString(9) : str4;
        if (cursor.isNull(3)) {
            i = 1;
        } else {
            i = cursor.getInt(3);
        }
        this.configurationState = i;
        if (cursor.isNull(4)) {
            i2 = 1;
        } else {
            i2 = cursor.getInt(4);
        }
        this.dataChannelState = i2;
        int i7 = Build.VERSION.SDK_INT;
        if (this.sourcePackage.equals(context.getPackageName())) {
            TelephonyManager createForPhoneAccountHandle = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForPhoneAccountHandle(getPhoneAccountHandle());
            if (createForPhoneAccountHandle == null) {
                LogUtil.m8e("VoicemailStatus.constructor", "invalid PhoneAccountHandle", new Object[0]);
            } else if (createForPhoneAccountHandle.getServiceState().getState() == 0) {
                i5 = 0;
                this.notificationChannelState = i5;
            }
            i5 = 1;
            this.notificationChannelState = i5;
        } else {
            if (cursor.isNull(5)) {
                i4 = 1;
            } else {
                i4 = cursor.getInt(5);
            }
            this.notificationChannelState = i4;
        }
        this.isAirplaneMode = Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0) != 0 ? true : z;
        int i8 = -1;
        if (cursor.isNull(6)) {
            i3 = -1;
        } else {
            i3 = cursor.getInt(6);
        }
        this.quotaOccupied = i3;
        this.quotaTotal = !cursor.isNull(7) ? cursor.getInt(7) : i8;
    }

    public PhoneAccountHandle getPhoneAccountHandle() {
        ComponentName unflattenFromString;
        if (TextUtils.isEmpty(this.phoneAccountComponentName) || TextUtils.isEmpty(this.phoneAccountId) || (unflattenFromString = ComponentName.unflattenFromString(this.phoneAccountComponentName)) == null) {
            return null;
        }
        return new PhoneAccountHandle(unflattenFromString, this.phoneAccountId);
    }

    public boolean isActive(Context context) {
        VoicemailClient voicemailClient = VoicemailComponent.get(context).getVoicemailClient();
        if (context.getPackageName().equals(this.sourcePackage)) {
            if (!voicemailClient.isVoicemailModuleEnabled()) {
                LogUtil.m9i("VoicemailStatus.isActive", "module disabled", new Object[0]);
                return false;
            } else if (!voicemailClient.hasCarrierSupport(context, getPhoneAccountHandle())) {
                LogUtil.m9i("VoicemailStatus.isActive", "carrier not supported", new Object[0]);
                return false;
            } else if (!voicemailClient.isVoicemailEnabled(context, getPhoneAccountHandle())) {
                LogUtil.m9i("VoicemailStatus.isActive", "VVM disabled", new Object[0]);
                return false;
            }
        }
        int i = this.configurationState;
        if (i == 1 || i == 5) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("VoicemailStatus[sourcePackage: ");
        outline13.append(this.sourcePackage);
        outline13.append(", type:");
        outline13.append(this.type);
        outline13.append(", settingsUri: ");
        outline13.append(this.settingsUri);
        outline13.append(", voicemailAccessUri: ");
        outline13.append(this.voicemailAccessUri);
        outline13.append(", configurationState: ");
        outline13.append(this.configurationState);
        outline13.append(", dataChannelState: ");
        outline13.append(this.dataChannelState);
        outline13.append(", notificationChannelState: ");
        outline13.append(this.notificationChannelState);
        outline13.append(", quotaOccupied: ");
        outline13.append(this.quotaOccupied);
        outline13.append(", quotaTotal: ");
        outline13.append(this.quotaTotal);
        outline13.append(", isAirplaneMode: ");
        outline13.append(this.isAirplaneMode);
        outline13.append("]");
        return outline13.toString();
    }
}
