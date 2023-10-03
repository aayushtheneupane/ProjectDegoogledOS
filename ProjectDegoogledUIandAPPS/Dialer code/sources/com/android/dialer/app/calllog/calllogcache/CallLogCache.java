package com.android.dialer.app.calllog.calllogcache;

import android.content.Context;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.text.TextUtils;
import android.util.ArrayMap;
import com.android.dialer.calllogutils.CallLogDates;
import com.android.dialer.telecom.TelecomUtil;
import com.android.dialer.util.CallUtil;
import java.util.Map;

public class CallLogCache {
    protected final Context context;
    private boolean hasCheckedForVideoAvailability;
    private final Map<PhoneAccountHandle, Boolean> phoneAccountCallWithNoteCache = new ArrayMap();
    private final Map<PhoneAccountHandle, Integer> phoneAccountColorCache = new ArrayMap();
    private final Map<PhoneAccountHandle, String> phoneAccountLabelCache = new ArrayMap();
    private int videoAvailability;

    public CallLogCache(Context context2) {
        this.context = context2;
    }

    public boolean canRelyOnVideoPresence() {
        if (!this.hasCheckedForVideoAvailability) {
            this.videoAvailability = CallUtil.getVideoCallingAvailability(this.context);
            this.hasCheckedForVideoAvailability = true;
        }
        if ((this.videoAvailability & 2) != 0) {
            return true;
        }
        return false;
    }

    public synchronized boolean doesAccountSupportCallSubject(PhoneAccountHandle phoneAccountHandle) {
        if (this.phoneAccountCallWithNoteCache.containsKey(phoneAccountHandle)) {
            return this.phoneAccountCallWithNoteCache.get(phoneAccountHandle).booleanValue();
        }
        PhoneAccount phoneAccount = TelecomUtil.getPhoneAccount(this.context, phoneAccountHandle);
        Boolean valueOf = Boolean.valueOf(phoneAccount != null && phoneAccount.hasCapabilities(64));
        this.phoneAccountCallWithNoteCache.put(phoneAccountHandle, valueOf);
        return valueOf.booleanValue();
    }

    public synchronized int getAccountColor(PhoneAccountHandle phoneAccountHandle) {
        int i;
        if (this.phoneAccountColorCache.containsKey(phoneAccountHandle)) {
            return this.phoneAccountColorCache.get(phoneAccountHandle).intValue();
        }
        PhoneAccount phoneAccount = TelecomUtil.getPhoneAccount(this.context, phoneAccountHandle);
        if (phoneAccount == null) {
            i = 0;
        } else {
            i = phoneAccount.getHighlightColor();
        }
        Integer valueOf = Integer.valueOf(i);
        this.phoneAccountColorCache.put(phoneAccountHandle, valueOf);
        return valueOf.intValue();
    }

    public synchronized String getAccountLabel(PhoneAccountHandle phoneAccountHandle) {
        if (this.phoneAccountLabelCache.containsKey(phoneAccountHandle)) {
            return this.phoneAccountLabelCache.get(phoneAccountHandle);
        }
        String accountLabel = CallLogDates.getAccountLabel(this.context, phoneAccountHandle);
        this.phoneAccountLabelCache.put(phoneAccountHandle, accountLabel);
        return accountLabel;
    }

    public synchronized boolean isVoicemailNumber(PhoneAccountHandle phoneAccountHandle, CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return false;
        }
        return TelecomUtil.isVoicemailNumber(this.context, phoneAccountHandle, charSequence.toString());
    }

    public synchronized void reset() {
        this.phoneAccountLabelCache.clear();
        this.phoneAccountColorCache.clear();
        this.phoneAccountCallWithNoteCache.clear();
        this.hasCheckedForVideoAvailability = false;
        this.videoAvailability = 0;
    }
}
