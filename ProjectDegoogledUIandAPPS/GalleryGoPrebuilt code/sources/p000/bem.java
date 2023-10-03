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

/* renamed from: bem */
/* compiled from: PG */
final class bem {

    /* renamed from: d */
    private static Integer f2177d;

    /* renamed from: a */
    public final View f2178a;

    /* renamed from: b */
    public final List f2179b = new ArrayList();

    /* renamed from: c */
    public bel f2180c;

    public bem(View view) {
        this.f2178a = view;
    }

    /* renamed from: a */
    private static final boolean m2354a(int i) {
        return i > 0 || i == Integer.MIN_VALUE;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo1910a() {
        ViewTreeObserver viewTreeObserver = this.f2178a.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnPreDrawListener(this.f2180c);
        }
        this.f2180c = null;
        this.f2179b.clear();
    }

    /* renamed from: a */
    private final int m2353a(int i, int i2, int i3) {
        int i4 = i2 - i3;
        if (i4 > 0) {
            return i4;
        }
        int i5 = i - i3;
        if (i5 > 0) {
            return i5;
        }
        if (this.f2178a.isLayoutRequested() || i2 != -2) {
            return 0;
        }
        Context context = this.f2178a.getContext();
        if (f2177d == null) {
            Display defaultDisplay = ((WindowManager) cns.m4632a((Object) (WindowManager) context.getSystemService("window"))).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            f2177d = Integer.valueOf(Math.max(point.x, point.y));
        }
        return f2177d.intValue();
    }

    /* renamed from: b */
    public final int mo1911b() {
        int i;
        int paddingTop = this.f2178a.getPaddingTop() + this.f2178a.getPaddingBottom();
        ViewGroup.LayoutParams layoutParams = this.f2178a.getLayoutParams();
        if (layoutParams != null) {
            i = layoutParams.height;
        } else {
            i = 0;
        }
        return m2353a(this.f2178a.getHeight(), i, paddingTop);
    }

    /* renamed from: c */
    public final int mo1912c() {
        int i;
        int paddingLeft = this.f2178a.getPaddingLeft() + this.f2178a.getPaddingRight();
        ViewGroup.LayoutParams layoutParams = this.f2178a.getLayoutParams();
        if (layoutParams != null) {
            i = layoutParams.width;
        } else {
            i = 0;
        }
        return m2353a(this.f2178a.getWidth(), i, paddingLeft);
    }

    /* renamed from: a */
    public static final boolean m2355a(int i, int i2) {
        return m2354a(i) && m2354a(i2);
    }
}
