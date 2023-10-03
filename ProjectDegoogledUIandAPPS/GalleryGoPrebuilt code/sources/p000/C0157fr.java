package p000;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import java.util.ArrayList;

/* renamed from: fr */
/* compiled from: PG */
public final class C0157fr extends FrameLayout {

    /* renamed from: a */
    public boolean f10299a = true;

    /* renamed from: b */
    private ArrayList f10300b;

    /* renamed from: c */
    private ArrayList f10301c;

    /* JADX WARNING: type inference failed for: r6v4, types: [gn, gb] */
    public C0157fr(Context context, AttributeSet attributeSet, C0171gd gdVar) {
        super(context, attributeSet);
        String str;
        String classAttribute = attributeSet.getClassAttribute();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0122ej.f8394b);
        classAttribute = classAttribute == null ? obtainStyledAttributes.getString(0) : classAttribute;
        String string = obtainStyledAttributes.getString(1);
        obtainStyledAttributes.recycle();
        int id = getId();
        C0147fh b = gdVar.mo6432b(id);
        if (classAttribute != null && b == null) {
            if (id <= 0) {
                if (string != null) {
                    str = " with tag " + string;
                } else {
                    str = "";
                }
                throw new IllegalStateException("FragmentContainerView must have an android:id to add Fragment " + classAttribute + str);
            }
            C0147fh c = gdVar.mo6455m().mo6175c(context.getClassLoader(), classAttribute);
            c.mo5628I();
            ? a = gdVar.mo6419a();
            a.f11659r = true;
            c.f9572K = this;
            a.mo6853b(getId(), c, string);
            a.mo6854d();
            a.f9018a.mo6436b(a, true);
        }
    }

    /* renamed from: a */
    private final void m9435a(View view) {
        ArrayList arrayList;
        if (view.getAnimation() != null || ((arrayList = this.f10301c) != null && arrayList.contains(view))) {
            if (this.f10300b == null) {
                this.f10300b = new ArrayList();
            }
            this.f10300b.add(view);
        }
    }

    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (C0171gd.m10049a(view) != null) {
            super.addView(view, i, layoutParams);
            return;
        }
        throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + view + " is not associated with a Fragment.");
    }

    /* access modifiers changed from: protected */
    public final boolean addViewInLayout(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        if (C0171gd.m10049a(view) != null) {
            return super.addViewInLayout(view, i, layoutParams, z);
        }
        throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + view + " is not associated with a Fragment.");
    }

    /* access modifiers changed from: protected */
    public final void dispatchDraw(Canvas canvas) {
        if (this.f10299a && this.f10300b != null) {
            for (int i = 0; i < this.f10300b.size(); i++) {
                super.drawChild(canvas, (View) this.f10300b.get(i), getDrawingTime());
            }
        }
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public final boolean drawChild(Canvas canvas, View view, long j) {
        ArrayList arrayList;
        if (!this.f10299a || (arrayList = this.f10300b) == null || arrayList.size() <= 0 || !this.f10300b.contains(view)) {
            return super.drawChild(canvas, view, j);
        }
        return false;
    }

    public final void endViewTransition(View view) {
        ArrayList arrayList = this.f10301c;
        if (arrayList != null) {
            arrayList.remove(view);
            ArrayList arrayList2 = this.f10300b;
            if (arrayList2 != null && arrayList2.remove(view)) {
                this.f10299a = true;
            }
        }
        super.endViewTransition(view);
    }

    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).dispatchApplyWindowInsets(new WindowInsets(windowInsets));
        }
        return windowInsets;
    }

    public final void removeAllViewsInLayout() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            m9435a(getChildAt(childCount));
        }
        super.removeAllViewsInLayout();
    }

    /* access modifiers changed from: protected */
    public final void removeDetachedView(View view, boolean z) {
        if (z) {
            m9435a(view);
        }
        super.removeDetachedView(view, z);
    }

    public final void removeView(View view) {
        m9435a(view);
        super.removeView(view);
    }

    public final void removeViewAt(int i) {
        m9435a(getChildAt(i));
        super.removeViewAt(i);
    }

    public final void removeViewInLayout(View view) {
        m9435a(view);
        super.removeViewInLayout(view);
    }

    public final void removeViews(int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            m9435a(getChildAt(i3));
        }
        super.removeViews(i, i2);
    }

    public final void removeViewsInLayout(int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            m9435a(getChildAt(i3));
        }
        super.removeViewsInLayout(i, i2);
    }

    public final void setLayoutTransition(LayoutTransition layoutTransition) {
        int i = Build.VERSION.SDK_INT;
        throw new UnsupportedOperationException("FragmentContainerView does not support Layout Transitions or animateLayoutChanges=\"true\".");
    }

    public final void startViewTransition(View view) {
        if (view.getParent() == this) {
            if (this.f10301c == null) {
                this.f10301c = new ArrayList();
            }
            this.f10301c.add(view);
        }
        super.startViewTransition(view);
    }
}
