package p000;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.List;

/* renamed from: bet */
/* compiled from: PG */
final class bet {

    /* renamed from: d */
    private static Integer f2185d;

    /* renamed from: a */
    public final View f2186a;

    /* renamed from: b */
    public final List f2187b = new ArrayList();

    /* renamed from: c */
    public bes f2188c;

    public bet(View view) {
        this.f2186a = view;
    }

    /* renamed from: a */
    private static final boolean m2389a(int i) {
        return i > 0 || i == Integer.MIN_VALUE;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo1915a() {
        ViewTreeObserver viewTreeObserver = this.f2186a.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnPreDrawListener(this.f2188c);
        }
        this.f2188c = null;
        this.f2187b.clear();
    }

    /* renamed from: a */
    private final int m2388a(int i, int i2, int i3) {
        int i4 = i2 - i3;
        if (i4 > 0) {
            return i4;
        }
        int i5 = i - i3;
        if (i5 > 0) {
            return i5;
        }
        if (this.f2186a.isLayoutRequested() || i2 != -2) {
            return 0;
        }
        Context context = this.f2186a.getContext();
        if (f2185d == null) {
            Display defaultDisplay = ((WindowManager) cns.m4632a((Object) (WindowManager) context.getSystemService("window"))).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            f2185d = Integer.valueOf(Math.max(point.x, point.y));
        }
        return f2185d.intValue();
    }

    /* renamed from: b */
    public final int mo1916b() {
        int i;
        int paddingTop = this.f2186a.getPaddingTop() + this.f2186a.getPaddingBottom();
        ViewGroup.LayoutParams layoutParams = this.f2186a.getLayoutParams();
        if (layoutParams != null) {
            i = layoutParams.height;
        } else {
            i = 0;
        }
        return m2388a(this.f2186a.getHeight(), i, paddingTop);
    }

    /* renamed from: c */
    public final int mo1917c() {
        int i;
        int paddingLeft = this.f2186a.getPaddingLeft() + this.f2186a.getPaddingRight();
        ViewGroup.LayoutParams layoutParams = this.f2186a.getLayoutParams();
        if (layoutParams != null) {
            i = layoutParams.width;
        } else {
            i = 0;
        }
        return m2388a(this.f2186a.getWidth(), i, paddingLeft);
    }

    /* renamed from: a */
    public static final boolean m2390a(int i, int i2) {
        return m2389a(i) && m2389a(i2);
    }
}
