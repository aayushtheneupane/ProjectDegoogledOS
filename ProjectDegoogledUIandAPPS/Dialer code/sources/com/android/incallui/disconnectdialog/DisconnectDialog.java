package com.android.incallui.disconnectdialog;

import android.app.Dialog;
import android.content.Context;
import android.telecom.DisconnectCause;
import android.util.Pair;
import com.android.incallui.call.DialerCall;

public interface DisconnectDialog {
    Pair<Dialog, CharSequence> createDialog(Context context, DialerCall dialerCall);

    boolean shouldShow(DisconnectCause disconnectCause);
}
