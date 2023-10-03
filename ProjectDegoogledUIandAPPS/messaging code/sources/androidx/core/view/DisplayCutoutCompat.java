package androidx.core.view;

import android.graphics.Rect;
import android.os.Build;
import android.view.DisplayCutout;
import java.util.List;
import p026b.p027a.p030b.p031a.C0632a;

public final class DisplayCutoutCompat {
    private final Object mDisplayCutout;

    public DisplayCutoutCompat(Rect rect, List list) {
        int i = Build.VERSION.SDK_INT;
        this.mDisplayCutout = new DisplayCutout(rect, list);
    }

    static DisplayCutoutCompat wrap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new DisplayCutoutCompat(obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DisplayCutoutCompat.class != obj.getClass()) {
            return false;
        }
        DisplayCutoutCompat displayCutoutCompat = (DisplayCutoutCompat) obj;
        Object obj2 = this.mDisplayCutout;
        if (obj2 != null) {
            return obj2.equals(displayCutoutCompat.mDisplayCutout);
        }
        if (displayCutoutCompat.mDisplayCutout == null) {
            return true;
        }
        return false;
    }

    public List getBoundingRects() {
        int i = Build.VERSION.SDK_INT;
        return ((DisplayCutout) this.mDisplayCutout).getBoundingRects();
    }

    public int getSafeInsetBottom() {
        int i = Build.VERSION.SDK_INT;
        return ((DisplayCutout) this.mDisplayCutout).getSafeInsetBottom();
    }

    public int getSafeInsetLeft() {
        int i = Build.VERSION.SDK_INT;
        return ((DisplayCutout) this.mDisplayCutout).getSafeInsetLeft();
    }

    public int getSafeInsetRight() {
        int i = Build.VERSION.SDK_INT;
        return ((DisplayCutout) this.mDisplayCutout).getSafeInsetRight();
    }

    public int getSafeInsetTop() {
        int i = Build.VERSION.SDK_INT;
        return ((DisplayCutout) this.mDisplayCutout).getSafeInsetTop();
    }

    public int hashCode() {
        Object obj = this.mDisplayCutout;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("DisplayCutoutCompat{");
        Pa.append(this.mDisplayCutout);
        Pa.append("}");
        return Pa.toString();
    }

    private DisplayCutoutCompat(Object obj) {
        this.mDisplayCutout = obj;
    }
}
