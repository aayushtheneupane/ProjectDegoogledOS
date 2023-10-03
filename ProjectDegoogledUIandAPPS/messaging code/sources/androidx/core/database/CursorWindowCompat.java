package androidx.core.database;

import android.database.CursorWindow;
import android.os.Build;

public final class CursorWindowCompat {
    private CursorWindowCompat() {
    }

    public static CursorWindow create(String str, long j) {
        int i = Build.VERSION.SDK_INT;
        return new CursorWindow(str, j);
    }
}
