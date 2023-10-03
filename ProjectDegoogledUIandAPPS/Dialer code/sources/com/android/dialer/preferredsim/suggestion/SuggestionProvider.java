package com.android.dialer.preferredsim.suggestion;

import android.content.Context;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;

public interface SuggestionProvider {

    public enum Reason {
        UNKNOWN,
        INTRA_CARRIER,
        FREQUENT,
        USER_SET,
        ACCOUNT,
        OTHER
    }

    public static class Suggestion {
        public final PhoneAccountHandle phoneAccountHandle;
        public final Reason reason;
        public final boolean shouldAutoSelect;
    }

    static Optional<String> getHint(Context context, PhoneAccountHandle phoneAccountHandle, Suggestion suggestion) {
        if (suggestion == null) {
            return Optional.absent();
        }
        if (!phoneAccountHandle.equals(suggestion.phoneAccountHandle)) {
            return Optional.absent();
        }
        int ordinal = suggestion.reason.ordinal();
        if (ordinal == 1) {
            return Optional.m67of(context.getString(R.string.pre_call_select_phone_account_hint_intra_carrier));
        }
        if (ordinal == 2) {
            return Optional.m67of(context.getString(R.string.pre_call_select_phone_account_hint_frequent));
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("unhandled reason ");
        outline13.append(suggestion.reason);
        LogUtil.m10w("CallingAccountSelector.getHint", outline13.toString(), new Object[0]);
        return Optional.absent();
    }
}
