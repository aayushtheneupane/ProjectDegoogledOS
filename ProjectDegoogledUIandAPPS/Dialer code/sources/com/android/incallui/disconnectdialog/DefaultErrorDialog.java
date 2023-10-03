package com.android.incallui.disconnectdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.telecom.DisconnectCause;
import android.text.TextUtils;
import android.util.Pair;
import com.android.incallui.call.DialerCall;

public class DefaultErrorDialog implements DisconnectDialog {
    public Pair<Dialog, CharSequence> createDialog(Context context, DialerCall dialerCall) {
        CharSequence description = dialerCall.getDisconnectCause().getDescription();
        return new Pair<>(new AlertDialog.Builder(context).setMessage(description).setPositiveButton(17039360, (DialogInterface.OnClickListener) null).create(), description);
    }

    public boolean shouldShow(DisconnectCause disconnectCause) {
        if (TextUtils.isEmpty(disconnectCause.getDescription()) || (disconnectCause.getCode() != 1 && disconnectCause.getCode() != 8)) {
            return false;
        }
        return true;
    }
}
