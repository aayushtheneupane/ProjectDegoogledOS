package com.android.incallui.disconnectdialog;

import android.app.Dialog;
import android.content.Context;
import android.telecom.DisconnectCause;
import android.util.Pair;
import com.android.incallui.call.DialerCall;
import java.util.Locale;

public class DisconnectMessage {
    private static final DisconnectDialog[] DISCONNECT_DIALOGS = {new EnableWifiCallingPrompt(), new VideoCallNotAvailablePrompt(), new DefaultErrorDialog()};
    private final DisconnectCause cause;
    public final Dialog dialog;
    public final CharSequence toastMessage;

    public DisconnectMessage(Context context, DialerCall dialerCall) {
        this.cause = dialerCall.getDisconnectCause();
        for (DisconnectDialog disconnectDialog : DISCONNECT_DIALOGS) {
            if (disconnectDialog.shouldShow(this.cause)) {
                Pair<Dialog, CharSequence> createDialog = disconnectDialog.createDialog(context, dialerCall);
                this.dialog = (Dialog) createDialog.first;
                this.toastMessage = (CharSequence) createDialog.second;
                return;
            }
        }
        this.dialog = null;
        this.toastMessage = null;
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "DisconnectMessage {code: %d, description: %s, reason: %s, message: %s}", new Object[]{Integer.valueOf(this.cause.getCode()), this.cause.getDescription(), this.cause.getReason(), this.toastMessage});
    }
}
