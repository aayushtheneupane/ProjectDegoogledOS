package androidx.core.view;

import android.graphics.Rect;
import android.os.Build;
import android.view.WindowInsets;
import androidx.core.graphics.Insets;
import androidx.core.util.ObjectsCompat;
import java.util.Objects;

public class WindowInsetsCompat {
    private final Object mInsets;

    WindowInsetsCompat(Object obj) {
        this.mInsets = obj;
    }

    public static WindowInsets unwrap(WindowInsetsCompat windowInsetsCompat) {
        return (WindowInsets) windowInsetsCompat.mInsets;
    }

    public static WindowInsetsCompat wrap(WindowInsets windowInsets) {
        return new WindowInsetsCompat(Objects.requireNonNull(windowInsets));
    }

    public WindowInsetsCompat consumeDisplayCutout() {
        int i = Build.VERSION.SDK_INT;
        return new WindowInsetsCompat((Object) ((WindowInsets) this.mInsets).consumeDisplayCutout());
    }

    public WindowInsetsCompat consumeStableInsets() {
        int i = Build.VERSION.SDK_INT;
        return new WindowInsetsCompat((Object) ((WindowInsets) this.mInsets).consumeStableInsets());
    }

    public WindowInsetsCompat consumeSystemWindowInsets() {
        int i = Build.VERSION.SDK_INT;
        return new WindowInsetsCompat((Object) ((WindowInsets) this.mInsets).consumeSystemWindowInsets());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || WindowInsetsCompat.class != obj.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(this.mInsets, ((WindowInsetsCompat) obj).mInsets);
    }

    public DisplayCutoutCompat getDisplayCutout() {
        int i = Build.VERSION.SDK_INT;
        return DisplayCutoutCompat.wrap(((WindowInsets) this.mInsets).getDisplayCutout());
    }

    public Insets getMandatorySystemGestureInsets() {
        if (Build.VERSION.SDK_INT >= 29) {
            return Insets.wrap(((WindowInsets) this.mInsets).getMandatorySystemGestureInsets());
        }
        return getSystemWindowInsets();
    }

    public int getStableInsetBottom() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).getStableInsetBottom();
    }

    public int getStableInsetLeft() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).getStableInsetLeft();
    }

    public int getStableInsetRight() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).getStableInsetRight();
    }

    public int getStableInsetTop() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).getStableInsetTop();
    }

    public Insets getStableInsets() {
        if (Build.VERSION.SDK_INT >= 29) {
            return Insets.wrap(((WindowInsets) this.mInsets).getStableInsets());
        }
        return Insets.m254of(getStableInsetLeft(), getStableInsetTop(), getStableInsetRight(), getStableInsetBottom());
    }

    public Insets getSystemGestureInsets() {
        if (Build.VERSION.SDK_INT >= 29) {
            return Insets.wrap(((WindowInsets) this.mInsets).getSystemGestureInsets());
        }
        return getSystemWindowInsets();
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

    public Insets getSystemWindowInsets() {
        if (Build.VERSION.SDK_INT >= 29) {
            return Insets.wrap(((WindowInsets) this.mInsets).getSystemWindowInsets());
        }
        return Insets.m254of(getSystemWindowInsetLeft(), getSystemWindowInsetTop(), getSystemWindowInsetRight(), getSystemWindowInsetBottom());
    }

    public Insets getTappableElementInsets() {
        if (Build.VERSION.SDK_INT >= 29) {
            return Insets.wrap(((WindowInsets) this.mInsets).getTappableElementInsets());
        }
        return getSystemWindowInsets();
    }

    public boolean hasInsets() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).hasInsets();
    }

    public boolean hasStableInsets() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).hasStableInsets();
    }

    public boolean hasSystemWindowInsets() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).hasSystemWindowInsets();
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

    public boolean isRound() {
        int i = Build.VERSION.SDK_INT;
        return ((WindowInsets) this.mInsets).isRound();
    }

    public WindowInsetsCompat replaceSystemWindowInsets(int i, int i2, int i3, int i4) {
        int i5 = Build.VERSION.SDK_INT;
        return new WindowInsetsCompat((Object) ((WindowInsets) this.mInsets).replaceSystemWindowInsets(i, i2, i3, i4));
    }

    public WindowInsetsCompat(WindowInsetsCompat windowInsetsCompat) {
        WindowInsets windowInsets;
        int i = Build.VERSION.SDK_INT;
        if (windowInsetsCompat == null) {
            windowInsets = null;
        } else {
            windowInsets = new WindowInsets((WindowInsets) windowInsetsCompat.mInsets);
        }
        this.mInsets = windowInsets;
    }

    public WindowInsetsCompat replaceSystemWindowInsets(Rect rect) {
        int i = Build.VERSION.SDK_INT;
        return new WindowInsetsCompat((Object) ((WindowInsets) this.mInsets).replaceSystemWindowInsets(rect));
    }
}
