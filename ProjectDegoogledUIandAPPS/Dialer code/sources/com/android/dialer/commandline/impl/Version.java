package com.android.dialer.commandline.impl;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.android.dialer.commandline.Arguments;
import com.android.dialer.commandline.Command;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Locale;

public class Version implements Command {
    private final Context appContext;

    Version(Context context) {
        this.appContext = context;
    }

    public String getShortDescription() {
        return "Print dialer version";
    }

    public String getUsage() {
        return "version";
    }

    public ListenableFuture<String> run(Arguments arguments) throws Command.IllegalCommandLineArgumentException {
        try {
            PackageInfo packageInfo = this.appContext.getPackageManager().getPackageInfo(this.appContext.getPackageName(), 0);
            return Futures.immediateFuture(String.format(Locale.US, "%s(%d)", new Object[]{packageInfo.versionName, Integer.valueOf(packageInfo.versionCode)}));
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
