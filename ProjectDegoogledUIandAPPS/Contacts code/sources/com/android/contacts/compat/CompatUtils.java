package com.android.contacts.compat;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.android.contacts.model.CPOWrapper;
import java.lang.reflect.InvocationTargetException;

public final class CompatUtils {
    private static final String TAG = "CompatUtils";
    public static final int TYPE_ASSERT = 4;
    public static final int TYPE_DELETE = 3;
    public static final int TYPE_INSERT = 1;
    public static final int TYPE_UPDATE = 2;

    public static boolean isInsertCompat(CPOWrapper cPOWrapper) {
        if (SdkVersionOverride.getSdkVersion(23) >= 23) {
            return cPOWrapper.getOperation().isInsert();
        }
        return cPOWrapper.getType() == 1;
    }

    public static boolean isUpdateCompat(CPOWrapper cPOWrapper) {
        if (SdkVersionOverride.getSdkVersion(23) >= 23) {
            return cPOWrapper.getOperation().isUpdate();
        }
        return cPOWrapper.getType() == 2;
    }

    public static boolean isDeleteCompat(CPOWrapper cPOWrapper) {
        if (SdkVersionOverride.getSdkVersion(23) >= 23) {
            return cPOWrapper.getOperation().isDelete();
        }
        return cPOWrapper.getType() == 3;
    }

    public static boolean isAssertQueryCompat(CPOWrapper cPOWrapper) {
        if (SdkVersionOverride.getSdkVersion(23) >= 23) {
            return cPOWrapper.getOperation().isAssertQuery();
        }
        return cPOWrapper.getType() == 4;
    }

    public static boolean hasPrioritizedMimeType() {
        return SdkVersionOverride.getSdkVersion(23) >= 23;
    }

    public static boolean isMSIMCompatible() {
        return SdkVersionOverride.getSdkVersion(21) >= 22;
    }

    public static boolean isVideoCompatible() {
        return SdkVersionOverride.getSdkVersion(21) >= 23;
    }

    public static boolean isVideoPresenceCompatible() {
        return SdkVersionOverride.getSdkVersion(23) > 23;
    }

    public static boolean isCallSubjectCompatible() {
        return SdkVersionOverride.getSdkVersion(21) >= 23;
    }

    public static boolean isDefaultDialerCompatible() {
        return isMarshmallowCompatible();
    }

    public static boolean isLollipopMr1Compatible() {
        return SdkVersionOverride.getSdkVersion(22) >= 22;
    }

    public static boolean isMarshmallowCompatible() {
        return SdkVersionOverride.getSdkVersion(21) >= 23;
    }

    public static boolean isNCompatible() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static boolean isNougatMr1Compatible() {
        return SdkVersionOverride.getSdkVersion(25) >= 25;
    }

    public static boolean isLauncherShortcutCompatible() {
        return isNougatMr1Compatible();
    }

    public static boolean isClassAvailable(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        } catch (Throwable th) {
            String str2 = TAG;
            Log.e(str2, "Unexpected exception when checking if class:" + str + " exists at runtime", th);
            return false;
        }
    }

    public static boolean isMethodAvailable(String str, String str2, Class<?>... clsArr) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                Class.forName(str).getMethod(str2, clsArr);
                return true;
            } catch (ClassNotFoundException | NoSuchMethodException unused) {
                if (Log.isLoggable(TAG, 2)) {
                    String str3 = TAG;
                    Log.v(str3, "Could not find method: " + str + "#" + str2);
                }
            } catch (Throwable th) {
                String str4 = TAG;
                Log.e(str4, "Unexpected exception when checking if method: " + str + "#" + str2 + " exists at runtime", th);
                return false;
            }
        }
        return false;
    }

    public static Object invokeMethod(Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        if (obj != null && !TextUtils.isEmpty(str)) {
            String name = obj.getClass().getName();
            try {
                return Class.forName(name).getMethod(str, clsArr).invoke(obj, objArr);
            } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
                if (Log.isLoggable(TAG, 2)) {
                    String str2 = TAG;
                    Log.v(str2, "Could not invoke method: " + name + "#" + str);
                }
            } catch (Throwable th) {
                String str3 = TAG;
                Log.e(str3, "Unexpected exception when invoking method: " + name + "#" + str + " at runtime", th);
                return null;
            }
        }
        return null;
    }

    public static boolean isLollipopCompatible() {
        return SdkVersionOverride.getSdkVersion(21) >= 21;
    }
}
