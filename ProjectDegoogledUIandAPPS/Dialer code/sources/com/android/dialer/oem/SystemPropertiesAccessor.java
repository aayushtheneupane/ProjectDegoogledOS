package com.android.dialer.oem;

import com.android.dialer.common.LogUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class SystemPropertiesAccessor {
    private Method systemPropertiesGetMethod;

    SystemPropertiesAccessor() {
    }

    public String get(String str) {
        Method method = this.systemPropertiesGetMethod;
        if (method == null) {
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                if (cls == null) {
                    method = null;
                } else {
                    this.systemPropertiesGetMethod = cls.getMethod("get", new Class[]{String.class});
                    method = this.systemPropertiesGetMethod;
                }
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                LogUtil.m7e("SystemPropertiesAccessor.get", "unable to access system class", e);
            }
        }
        if (method == null) {
            return null;
        }
        try {
            return (String) method.invoke((Object) null, new Object[]{str});
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
            LogUtil.m7e("SystemPropertiesAccessor.get", "unable to invoke system method", e2);
            return null;
        }
    }
}
