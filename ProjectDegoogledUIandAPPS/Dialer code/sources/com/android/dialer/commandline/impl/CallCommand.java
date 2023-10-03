package com.android.dialer.commandline.impl;

import android.content.Context;
import android.content.Intent;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import com.android.dialer.buildtype.BuildType;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.commandline.Arguments;
import com.android.dialer.commandline.Command;
import com.android.dialer.precall.PreCall;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class CallCommand implements Command {
    private final Context appContext;

    CallCommand(Context context) {
        this.appContext = context;
    }

    public String getShortDescription() {
        return "make a call";
    }

    public String getUsage() {
        return "call [flags --] number\n\nuse 'voicemail' to call voicemail\n\nflags:\n--direct send intent to telecom instead of pre call";
    }

    public ListenableFuture<String> run(Arguments arguments) throws Command.IllegalCommandLineArgumentException {
        CallIntentBuilder callIntentBuilder;
        if (BuildType.get() != 1) {
            throw new SecurityException("Bugfood only command");
        } else if (arguments.getPositionals().size() > 0) {
            String str = arguments.getPositionals().get(0);
            PhoneAccountHandle defaultOutgoingPhoneAccount = ((TelecomManager) this.appContext.getSystemService(TelecomManager.class)).getDefaultOutgoingPhoneAccount("tel");
            if ("voicemail".equals(str)) {
                callIntentBuilder = CallIntentBuilder.forVoicemail(defaultOutgoingPhoneAccount, CallInitiationType$Type.DIALPAD);
            } else {
                callIntentBuilder = new CallIntentBuilder(str, CallInitiationType$Type.DIALPAD);
            }
            if (arguments.getBoolean("direct", false).booleanValue()) {
                Intent build = callIntentBuilder.build();
                ((TelecomManager) this.appContext.getSystemService(TelecomManager.class)).placeCall(build.getData(), build.getExtras());
            } else {
                Intent intent = PreCall.getIntent(this.appContext, callIntentBuilder);
                intent.setFlags(268435456);
                this.appContext.startActivity(intent);
            }
            return Futures.immediateFuture("Calling " + str);
        } else {
            throw new Command.IllegalCommandLineArgumentException(GeneratedOutlineSupport.outline8("number", " expected"));
        }
    }
}
