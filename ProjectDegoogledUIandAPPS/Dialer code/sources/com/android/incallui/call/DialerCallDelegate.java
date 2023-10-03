package com.android.incallui.call;

import android.telecom.Call;

public interface DialerCallDelegate {
    DialerCall getDialerCallFromTelecomCall(Call call);
}
