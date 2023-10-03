package androidx.core.p024os;

import android.os.Build;
import android.os.Environment;
import java.io.File;

/* renamed from: androidx.core.os.EnvironmentCompat */
public final class EnvironmentCompat {
    public static final String MEDIA_UNKNOWN = "unknown";
    private static final String TAG = "EnvironmentCompat";

    private EnvironmentCompat() {
    }

    public static String getStorageState(File file) {
        int i = Build.VERSION.SDK_INT;
        return Environment.getStorageState(file);
    }
}
