package com.android.systemui;

import android.app.Notification;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Map;
import java.util.function.Function;

public abstract class SystemUI implements SysUiServiceProvider {
    public Map<Class<?>, Object> mComponents;
    public Context mContext;

    public interface Injector extends Function<Context, SystemUI> {
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    /* access modifiers changed from: protected */
    public void onBootCompleted() {
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
    }

    public abstract void start();

    public <T> T getComponent(Class<T> cls) {
        Map<Class<?>, Object> map = this.mComponents;
        if (map != null) {
            return map.get(cls);
        }
        return null;
    }

    public <T, C extends T> void putComponent(Class<T> cls, C c) {
        Map<Class<?>, Object> map = this.mComponents;
        if (map != null) {
            map.put(cls, c);
        }
    }

    public static void overrideNotificationAppName(Context context, Notification.Builder builder, boolean z) {
        String str;
        Bundle bundle = new Bundle();
        if (z) {
            str = context.getString(17040542);
        } else {
            str = context.getString(17040541);
        }
        bundle.putString("android.substName", str);
        builder.addExtras(bundle);
    }
}
