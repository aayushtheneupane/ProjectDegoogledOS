package com.android.systemui;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.os.SystemProperties;
import android.util.Slog;
import com.android.internal.os.BinderInternal;
import com.android.systemui.shared.plugins.PluginManager;
import com.android.systemui.shared.plugins.PluginManagerImpl;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class SystemUIService extends Service {
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        ((SystemUIApplication) getApplication()).startServicesIfNeeded();
        if (Build.IS_DEBUGGABLE && SystemProperties.getBoolean("debug.crash_sysui", false)) {
            throw new RuntimeException();
        } else if (Build.IS_ENG) {
            BinderInternal.nSetBinderProxyCountEnabled(true);
            BinderInternal.nSetBinderProxyCountWatermarks(1000, 900);
            BinderInternal.setBinderProxyCountCallback(new BinderInternal.BinderProxyLimitListener() {
                public void onLimitReached(int i) {
                    Slog.w("SystemUIService", "uid " + i + " sent too many Binder proxies to uid " + Process.myUid());
                }
            }, (Handler) Dependency.get(Dependency.MAIN_HANDLER));
        }
    }

    /* access modifiers changed from: protected */
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (strArr == null || strArr.length <= 0 || !strArr[0].equals("--config")) {
            dumpServices(((SystemUIApplication) getApplication()).getServices(), fileDescriptor, printWriter, strArr);
            dumpConfig(printWriter);
            return;
        }
        dumpConfig(printWriter);
    }

    static void dumpServices(SystemUI[] systemUIArr, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i = 0;
        if (strArr == null || strArr.length == 0) {
            printWriter.println("dumping service: " + Dependency.class.getName());
            Dependency.staticDump(fileDescriptor, printWriter, strArr);
            int length = systemUIArr.length;
            while (i < length) {
                SystemUI systemUI = systemUIArr[i];
                printWriter.println("dumping service: " + systemUI.getClass().getName());
                systemUI.dump(fileDescriptor, printWriter, strArr);
                i++;
            }
            if (Build.IS_DEBUGGABLE) {
                printWriter.println("dumping plugins:");
                ((PluginManagerImpl) Dependency.get(PluginManager.class)).dump(fileDescriptor, printWriter, strArr);
                return;
            }
            return;
        }
        String lowerCase = strArr[0].toLowerCase();
        if (Dependency.class.getName().endsWith(lowerCase)) {
            Dependency.staticDump(fileDescriptor, printWriter, strArr);
        }
        int length2 = systemUIArr.length;
        while (i < length2) {
            SystemUI systemUI2 = systemUIArr[i];
            if (systemUI2.getClass().getName().toLowerCase().endsWith(lowerCase)) {
                systemUI2.dump(fileDescriptor, printWriter, strArr);
            }
            i++;
        }
    }

    private void dumpConfig(PrintWriter printWriter) {
        printWriter.println("SystemUiServiceComponents configuration:");
        printWriter.print("vendor component: ");
        printWriter.println(getResources().getString(C1784R$string.config_systemUIVendorServiceComponent));
        dumpConfig(printWriter, "global", C1771R$array.config_systemUIServiceComponents);
        dumpConfig(printWriter, "per-user", C1771R$array.config_systemUIServiceComponentsPerUser);
    }

    private void dumpConfig(PrintWriter printWriter, String str, int i) {
        String[] stringArray = getResources().getStringArray(i);
        printWriter.print(str);
        printWriter.print(": ");
        if (stringArray == null) {
            printWriter.println("N/A");
            return;
        }
        printWriter.print(stringArray.length);
        printWriter.println(" services");
        for (int i2 = 0; i2 < stringArray.length; i2++) {
            printWriter.print("  ");
            printWriter.print(i2);
            printWriter.print(": ");
            printWriter.println(stringArray[i2]);
        }
    }
}
