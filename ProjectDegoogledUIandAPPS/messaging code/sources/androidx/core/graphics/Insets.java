package androidx.core.graphics;

import android.graphics.Rect;
import p026b.p027a.p030b.p031a.C0632a;

public final class Insets {
    public static final Insets NONE = new Insets(0, 0, 0, 0);
    public final int bottom;
    public final int left;
    public final int right;
    public final int top;

    private Insets(int i, int i2, int i3, int i4) {
        this.left = i;
        this.top = i2;
        this.right = i3;
        this.bottom = i4;
    }

    /* renamed from: of */
    public static Insets m254of(int i, int i2, int i3, int i4) {
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            return NONE;
        }
        return new Insets(i, i2, i3, i4);
    }

    public static Insets wrap(android.graphics.Insets insets) {
        return m254of(insets.left, insets.top, insets.right, insets.bottom);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Insets.class != obj.getClass()) {
            return false;
        }
        Insets insets = (Insets) obj;
        return this.bottom == insets.bottom && this.left == insets.left && this.right == insets.right && this.top == insets.top;
    }

    public int hashCode() {
        return (((((this.left * 31) + this.top) * 31) + this.right) * 31) + this.bottom;
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("Insets{left=");
        Pa.append(this.left);
        Pa.append(", top=");
        Pa.append(this.top);
        Pa.append(", right=");
        Pa.append(this.right);
        Pa.append(", bottom=");
        Pa.append(this.bottom);
        Pa.append('}');
        return Pa.toString();
    }

    /* renamed from: of */
    public static Insets m255of(Rect rect) {
        return m254of(rect.left, rect.top, rect.right, rect.bottom);
    }
}
