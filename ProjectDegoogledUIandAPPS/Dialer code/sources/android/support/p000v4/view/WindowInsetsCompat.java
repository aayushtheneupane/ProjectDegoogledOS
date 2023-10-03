package android.support.p000v4.view;

import android.os.Build;
import android.view.WindowInsets;

/* renamed from: android.support.v4.view.WindowInsetsCompat */
public class WindowInsetsCompat {
    private final Object mInsets;

    private WindowInsetsCompat(Object obj) {
        this.mInsets = obj;
    }

    static Object unwrap(WindowInsetsCompat windowInsetsCompat) {
        if (windowInsetsCompat == null) {
            return null;
        }
        return windowInsetsCompat.mInsets;
    }

    static WindowInsetsCompat wrap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new WindowInsetsCompat(obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || WindowInsetsCompat.class != obj.getClass()) {
            return false;
        }
        WindowInsetsCompat windowInsetsCompat = (WindowInsetsCompat) obj;
        Object obj2 = this.mInsets;
        if (obj2 != null) {
            return obj2.equals(windowInsetsCompat.mInsets);
        }
        if (windowInsetsCompat.mInsets == null) {
            return true;
        }
        return false;
    }

    public int getSystemWindowInsetBottom() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).getSystemWindowInsetBottom();
    }

    public int getSystemWindowInsetLeft() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).getSystemWindowInsetLeft();
    }

    public int getSystemWindowInsetRight() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).getSystemWindowInsetRight();
    }

    public int getSystemWindowInsetTop() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).getSystemWindowInsetTop();
    }

    public int hashCode() {
        Object obj = this.mInsets;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public boolean isConsumed() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).isConsumed();
    }

    public WindowInsetsCompat replaceSystemWindowInsets(int i, int i2, int i3, int i4) {
        int i5 = Build.VERSION.SDK_INT;
        return new WindowInsetsCompat(((WindowInsets) this.mInsets).replaceSystemWindowInsets(i, i2, i3, i4));
    }
}
