package androidx.core.p024os;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Message;

/* renamed from: androidx.core.os.MessageCompat */
public final class MessageCompat {
    private static boolean sTryIsAsynchronous = true;
    private static boolean sTrySetAsynchronous = true;

    private MessageCompat() {
    }

    @SuppressLint({"NewApi"})
    public static boolean isAsynchronous(Message message) {
        int i = Build.VERSION.SDK_INT;
        return message.isAsynchronous();
    }

    @SuppressLint({"NewApi"})
    public static void setAsynchronous(Message message, boolean z) {
        int i = Build.VERSION.SDK_INT;
        message.setAsynchronous(z);
    }
}
