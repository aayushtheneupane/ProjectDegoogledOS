package com.android.settings.notification;

import android.content.Context;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.preference.PreferenceScreen;
import com.android.settings.DefaultRingtonePreference;
import com.android.settings.Utils;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class PhoneRingtonePreferenceController extends RingtonePreferenceControllerBase {
    private TelecomManager mTelecomManager = ((TelecomManager) this.mContext.getSystemService("telecom"));

    public int getIdForPhoneRingtonePreference() {
        return 0;
    }

    public String getPreferenceKey() {
        return "ringtone";
    }

    public int getRingtoneType() {
        return 1;
    }

    public PhoneRingtonePreferenceController(Context context) {
        super(context);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        CharSequence displayNameForRingtonePreference;
        super.displayPreference(preferenceScreen);
        DefaultRingtonePreference defaultRingtonePreference = (DefaultRingtonePreference) preferenceScreen.findPreference(getPreferenceKey());
        PhoneAccountHandle currentPhoneAccountHandle = getCurrentPhoneAccountHandle();
        defaultRingtonePreference.setPhoneAccountHandle(currentPhoneAccountHandle);
        if (hasMultiPhoneAccountHandle() && (displayNameForRingtonePreference = getDisplayNameForRingtonePreference(currentPhoneAccountHandle)) != null) {
            defaultRingtonePreference.setTitle((CharSequence) this.mContext.getString(C1715R.string.ringtone_title) + " - " + displayNameForRingtonePreference);
        }
    }

    public boolean isAvailable() {
        return Utils.isVoiceCapable(this.mContext);
    }

    public boolean hasMultiPhoneAccountHandle() {
        return getPhoneAccountHandles().size() > 1;
    }

    private PhoneAccountHandle getCurrentPhoneAccountHandle() {
        List<PhoneAccountHandle> phoneAccountHandles = getPhoneAccountHandles();
        int idForPhoneRingtonePreference = getIdForPhoneRingtonePreference();
        if (phoneAccountHandles == null || phoneAccountHandles.size() <= idForPhoneRingtonePreference) {
            return null;
        }
        return phoneAccountHandles.get(idForPhoneRingtonePreference);
    }

    private List<PhoneAccountHandle> getPhoneAccountHandles() {
        ArrayList arrayList = new ArrayList();
        for (PhoneAccountHandle phoneAccountHandle : this.mTelecomManager.getCallCapablePhoneAccounts(true)) {
            if (this.mTelecomManager.getPhoneAccount(phoneAccountHandle).hasCapabilities(4) && !"E".equals(phoneAccountHandle.getId())) {
                arrayList.add(phoneAccountHandle);
            }
        }
        return arrayList;
    }

    private CharSequence getDisplayNameForRingtonePreference(PhoneAccountHandle phoneAccountHandle) {
        SubscriptionInfo activeSubscriptionInfo = ((SubscriptionManager) this.mContext.getSystemService("telephony_subscription_service")).getActiveSubscriptionInfo(((TelephonyManager) this.mContext.getSystemService("phone")).getSubIdForPhoneAccount(this.mTelecomManager.getPhoneAccount(phoneAccountHandle)));
        if (activeSubscriptionInfo != null) {
            return activeSubscriptionInfo.getDisplayName();
        }
        return null;
    }
}
