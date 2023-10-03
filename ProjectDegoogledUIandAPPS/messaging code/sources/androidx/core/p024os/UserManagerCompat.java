package androidx.core.p024os;

import android.content.Context;
import android.os.Build;
import android.os.UserManager;

/* renamed from: androidx.core.os.UserManagerCompat */
public class UserManagerCompat {
    private UserManagerCompat() {
    }

    public static boolean isUserUnlocked(Context context) {
        int i = Build.VERSION.SDK_INT;
        return ((UserManager) context.getSystemService(UserManager.class)).isUserUnlocked();
    }
}
