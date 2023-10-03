package android.support.design.circularreveal;

import android.os.Build;

public class CircularRevealHelper {
    public static final int STRATEGY = 2;

    interface Delegate {
    }

    static {
        int i = Build.VERSION.SDK_INT;
    }
}
