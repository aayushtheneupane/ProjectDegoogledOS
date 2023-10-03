package androidx.core.app;

import android.app.Dialog;
import android.os.Build;
import android.view.View;

public class DialogCompat {
    private DialogCompat() {
    }

    public static View requireViewById(Dialog dialog, int i) {
        int i2 = Build.VERSION.SDK_INT;
        return dialog.requireViewById(i);
    }
}
