package com.android.voicemail.impl.sync;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.UserManager;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.util.ArraySet;
import com.android.dialer.common.Assert;
import com.android.dialer.common.PerAccountSharedPreferences;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.storage.StorageComponent;
import com.android.dialer.voicemail.settings.VoicemailSettingsFragment;
import com.android.voicemail.VoicemailClient;
import com.android.voicemail.impl.VisualVoicemailPreferences;
import com.android.voicemail.impl.VoicemailStatus$Editor;
import com.android.voicemail.impl.sms.StatusMessage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@TargetApi(26)
public class VvmAccountManager {
    static final String IS_ACCOUNT_ACTIVATED = "is_account_activated";
    private static final Set<VoicemailClient.ActivationStateListener> listeners = new ArraySet();

    public static void addAccount(Context context, PhoneAccountHandle phoneAccountHandle, StatusMessage statusMessage) {
        PerAccountSharedPreferences.Editor edit = new VisualVoicemailPreferences(context, phoneAccountHandle).edit();
        statusMessage.putStatus(edit);
        edit.apply();
        setAccountActivated(context, phoneAccountHandle, true);
        DialerExecutorModule.postOnUiThread(new Runnable(phoneAccountHandle) {
            private final /* synthetic */ PhoneAccountHandle f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                VvmAccountManager.lambda$addAccount$0(this.f$0);
            }
        });
    }

    public static void addListener(VoicemailClient.ActivationStateListener activationStateListener) {
        Assert.isMainThread();
        listeners.add(activationStateListener);
    }

    public static List<PhoneAccountHandle> getActiveAccounts(Context context) {
        ArrayList arrayList = new ArrayList();
        for (PhoneAccountHandle next : ((TelecomManager) context.getSystemService(TelecomManager.class)).getCallCapablePhoneAccounts()) {
            if (isAccountActivated(context, next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public static boolean isAccountActivated(Context context, PhoneAccountHandle phoneAccountHandle) {
        Assert.isNotNull(phoneAccountHandle);
        PerAccountSharedPreferences perAccountSharedPreferences = new PerAccountSharedPreferences(phoneAccountHandle, StorageComponent.get(context).unencryptedSharedPrefs());
        if (((UserManager) context.getSystemService(UserManager.class)).isUserUnlocked() && !perAccountSharedPreferences.contains(IS_ACCOUNT_ACTIVATED)) {
            VisualVoicemailPreferences visualVoicemailPreferences = new VisualVoicemailPreferences(context, phoneAccountHandle);
            PerAccountSharedPreferences.Editor edit = perAccountSharedPreferences.edit();
            edit.putBoolean(IS_ACCOUNT_ACTIVATED, visualVoicemailPreferences.getBoolean(IS_ACCOUNT_ACTIVATED, false));
            edit.apply();
        }
        return perAccountSharedPreferences.getBoolean(IS_ACCOUNT_ACTIVATED, false);
    }

    static /* synthetic */ void lambda$addAccount$0(PhoneAccountHandle phoneAccountHandle) {
        Iterator<VoicemailClient.ActivationStateListener> it = listeners.iterator();
        while (it.hasNext()) {
            ((VoicemailSettingsFragment) it.next()).onActivationStateChanged(phoneAccountHandle, true);
        }
    }

    static /* synthetic */ void lambda$removeAccount$1(PhoneAccountHandle phoneAccountHandle) {
        Iterator<VoicemailClient.ActivationStateListener> it = listeners.iterator();
        while (it.hasNext()) {
            ((VoicemailSettingsFragment) it.next()).onActivationStateChanged(phoneAccountHandle, false);
        }
    }

    public static void removeAccount(Context context, PhoneAccountHandle phoneAccountHandle) {
        VoicemailStatus$Editor edit = com.android.voicemail.impl.Assert.edit(context, phoneAccountHandle);
        edit.setConfigurationState(1);
        edit.setDataChannelState(1);
        edit.setNotificationChannelState(1);
        edit.apply();
        setAccountActivated(context, phoneAccountHandle, false);
        PerAccountSharedPreferences.Editor edit2 = new VisualVoicemailPreferences(context, phoneAccountHandle).edit();
        edit2.putString("u", (String) null);
        edit2.putString("pw", (String) null);
        edit2.apply();
        DialerExecutorModule.postOnUiThread(new Runnable(phoneAccountHandle) {
            private final /* synthetic */ PhoneAccountHandle f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                VvmAccountManager.lambda$removeAccount$1(this.f$0);
            }
        });
    }

    public static void removeListener(VoicemailClient.ActivationStateListener activationStateListener) {
        Assert.isMainThread();
        listeners.remove(activationStateListener);
    }

    private static void setAccountActivated(Context context, PhoneAccountHandle phoneAccountHandle, boolean z) {
        Assert.isNotNull(phoneAccountHandle);
        PerAccountSharedPreferences.Editor edit = new PerAccountSharedPreferences(phoneAccountHandle, StorageComponent.get(context).unencryptedSharedPrefs()).edit();
        edit.putBoolean(IS_ACCOUNT_ACTIVATED, z);
        edit.apply();
    }
}
