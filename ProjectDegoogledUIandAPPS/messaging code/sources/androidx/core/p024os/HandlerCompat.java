package androidx.core.p024os;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;

/* renamed from: androidx.core.os.HandlerCompat */
public final class HandlerCompat {
    private static final String TAG = "HandlerCompat";

    private HandlerCompat() {
    }

    public static Handler createAsync(Looper looper) {
        int i = Build.VERSION.SDK_INT;
        return Handler.createAsync(looper);
    }

    public static boolean postDelayed(Handler handler, Runnable runnable, Object obj, long j) {
        int i = Build.VERSION.SDK_INT;
        return handler.postDelayed(runnable, obj, j);
    }

    public static Handler createAsync(Looper looper, Handler.Callback callback) {
        int i = Build.VERSION.SDK_INT;
        return Handler.createAsync(looper, callback);
    }
}
