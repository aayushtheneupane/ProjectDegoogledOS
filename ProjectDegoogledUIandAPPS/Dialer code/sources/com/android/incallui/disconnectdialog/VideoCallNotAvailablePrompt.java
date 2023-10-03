package com.android.incallui.disconnectdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.telecom.DisconnectCause;
import android.telecom.PhoneAccountHandle;
import android.util.Pair;
import com.android.dialer.R;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.common.LogUtil;
import com.android.dialer.precall.PreCall;
import com.android.incallui.call.DialerCall;

public class VideoCallNotAvailablePrompt implements DisconnectDialog {
    public Pair<Dialog, CharSequence> createDialog(Context context, DialerCall dialerCall) {
        String string = context.getString(R.string.video_call_not_available_title);
        return new Pair<>(new AlertDialog.Builder(context).setTitle(string).setMessage(context.getString(R.string.video_call_not_available_message)).setPositiveButton(R.string.voice_call, new DialogInterface.OnClickListener(context, dialerCall) {
            private final /* synthetic */ Context f$1;
            private final /* synthetic */ DialerCall f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                VideoCallNotAvailablePrompt.this.lambda$createDialog$0$VideoCallNotAvailablePrompt(this.f$1, this.f$2, dialogInterface, i);
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create(), string);
    }

    public /* synthetic */ void lambda$createDialog$0$VideoCallNotAvailablePrompt(Context context, DialerCall dialerCall, DialogInterface dialogInterface, int i) {
        String number = dialerCall.getNumber();
        PhoneAccountHandle accountHandle = dialerCall.getAccountHandle();
        LogUtil.enterBlock("VideoCallNotAvailablePrompt.makeVoiceCall");
        PreCall.start(context, new CallIntentBuilder(number, CallInitiationType$Type.IMS_VIDEO_BLOCKED_FALLBACK_TO_VOICE).setPhoneAccountHandle(accountHandle));
    }

    public boolean shouldShow(DisconnectCause disconnectCause) {
        if (disconnectCause.getCode() != 1 || !"REASON_IMS_ACCESS_BLOCKED".equals(disconnectCause.getReason())) {
            return false;
        }
        LogUtil.m9i("VideoCallNotAvailablePrompt.shouldShowPrompt", "showing prompt for disconnect cause: %s", disconnectCause.getReason());
        return true;
    }
}
