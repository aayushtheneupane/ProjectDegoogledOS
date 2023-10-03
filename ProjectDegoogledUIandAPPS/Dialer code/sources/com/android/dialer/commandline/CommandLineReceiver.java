package com.android.dialer.commandline;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.buildtype.BuildType;
import com.android.dialer.commandline.Command;
import com.android.dialer.common.LogUtil;
import com.android.dialer.inject.HasRootComponent;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;

public class CommandLineReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        final String stringExtra = intent.getStringExtra("tag");
        if (stringExtra == null) {
            LogUtil.m8e("CommandLineReceiver", "missing tag", new Object[0]);
        } else if (LogUtil.isDebugEnabled() || BuildType.get() == 1) {
            final Command command = (Command) ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).commandLineComponent().commandSupplier().get().get(intent.getStringExtra("command"));
            if (command == null) {
                try {
                    LogUtil.m9i(stringExtra, "unknown command " + intent.getStringExtra("command"), new Object[0]);
                } catch (Command.IllegalCommandLineArgumentException e) {
                    LogUtil.m8e(stringExtra, e.getMessage() + "\n\nusage:\n" + command.getUsage(), new Object[0]);
                } catch (Throwable th) {
                    LogUtil.m7e(stringExtra, "error running command", th);
                }
            } else {
                Arguments parse = Arguments.parse(intent.getStringArrayExtra("args"));
                if (parse.getBoolean("help", false).booleanValue()) {
                    LogUtil.m9i(stringExtra, "usage:\n" + command.getUsage(), new Object[0]);
                    return;
                }
                Futures.addCallback(command.run(parse), new FutureCallback<String>(this) {
                    public void onFailure(Throwable th) {
                        if (th instanceof Command.IllegalCommandLineArgumentException) {
                            String str = stringExtra;
                            LogUtil.m8e(str, th.getMessage() + "\n\nusage:\n" + command.getUsage(), new Object[0]);
                        }
                        LogUtil.m7e(stringExtra, "error running command future", th);
                    }

                    public void onSuccess(Object obj) {
                        String str = (String) obj;
                        if (TextUtils.isEmpty(str)) {
                            LogUtil.m9i(stringExtra, "EMPTY", new Object[0]);
                        } else {
                            LogUtil.m9i(stringExtra, str, new Object[0]);
                        }
                    }
                }, MoreExecutors.directExecutor());
            }
        } else {
            LogUtil.m9i(stringExtra, "DISABLED", new Object[0]);
        }
    }
}
