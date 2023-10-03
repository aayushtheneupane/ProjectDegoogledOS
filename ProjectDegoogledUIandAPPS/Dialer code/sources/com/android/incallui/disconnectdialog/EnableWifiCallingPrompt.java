package com.android.incallui.disconnectdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.telecom.DisconnectCause;
import android.util.Pair;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.incallui.call.DialerCall;

public class EnableWifiCallingPrompt implements DisconnectDialog {
    static /* synthetic */ void lambda$createDialog$0(Context context, DialogInterface dialogInterface, int i) {
        LogUtil.m9i("EnableWifiCallingPrompt.openWifiCallingSettings", "opening settings", new Object[0]);
        context.startActivity(new Intent("android.settings.WIFI_CALLING_SETTINGS").setPackage("com.android.settings"));
    }

    public Pair<Dialog, CharSequence> createDialog(Context context, DialerCall dialerCall) {
        Assert.isNotNull(context);
        CharSequence description = dialerCall.getDisconnectCause().getDescription();
        return new Pair<>(new AlertDialog.Builder(context).setMessage(description).setPositiveButton(R.string.incall_enable_wifi_calling_button, new DialogInterface.OnClickListener(context) {
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                EnableWifiCallingPrompt.lambda$createDialog$0(this.f$0, dialogInterface, i);
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create(), description);
    }

    public boolean shouldShow(DisconnectCause disconnectCause) {
        String reason = disconnectCause.getReason();
        if (reason == null || !reason.startsWith("REASON_WIFI_ON_BUT_WFC_OFF")) {
            return false;
        }
        LogUtil.m9i("EnableWifiCallingPrompt.shouldShowPrompt", "showing prompt for disconnect cause: %s", reason);
        return true;
    }
}
