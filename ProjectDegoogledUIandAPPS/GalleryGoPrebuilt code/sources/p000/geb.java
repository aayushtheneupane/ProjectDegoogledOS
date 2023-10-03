package p000;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

/* renamed from: geb */
/* compiled from: PG */
final class geb {

    /* renamed from: a */
    private final Rect f11087a;

    /* renamed from: b */
    private final ColorStateList f11088b;

    /* renamed from: c */
    private final ColorStateList f11089c;

    /* renamed from: d */
    private final ColorStateList f11090d;

    /* renamed from: e */
    private final int f11091e;

    /* renamed from: f */
    private final gha f11092f;

    private geb(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, gha gha, Rect rect) {
        C0321lr.m14626a(rect.left);
        C0321lr.m14626a(rect.top);
        C0321lr.m14626a(rect.right);
        C0321lr.m14626a(rect.bottom);
        this.f11087a = rect;
        this.f11088b = colorStateList2;
        this.f11089c = colorStateList;
        this.f11090d = colorStateList3;
        this.f11091e = i;
        this.f11092f = gha;
    }

    /* renamed from: a */
    static geb m10148a(Context context, int i) {
        if (i != 0) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, gfb.f11155b);
            Rect rect = new Rect(obtainStyledAttributes.getDimensionPixelOffset(0, 0), obtainStyledAttributes.getDimensionPixelOffset(2, 0), obtainStyledAttributes.getDimensionPixelOffset(1, 0), obtainStyledAttributes.getDimensionPixelOffset(3, 0));
            ColorStateList a = gqb.m10615a(context, obtainStyledAttributes, 4);
            ColorStateList a2 = gqb.m10615a(context, obtainStyledAttributes, 9);
            ColorStateList a3 = gqb.m10615a(context, obtainStyledAttributes, 7);
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(8, 0);
            gha a4 = gha.m10329a(context, obtainStyledAttributes.getResourceId(5, 0), obtainStyledAttributes.getResourceId(6, 0)).mo6660a();
            obtainStyledAttributes.recycle();
            return new geb(a, a2, a3, dimensionPixelSize, a4, rect);
        }
        throw new IllegalArgumentException("Cannot create a CalendarItemStyle with a styleResId of 0");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo6503a(TextView textView) {
        ggu ggu = new ggu();
        ggu ggu2 = new ggu();
        ggu.mo3619a(this.f11092f);
        ggu2.mo3619a(this.f11092f);
        ggu.mo6635a(this.f11089c);
        ggu.mo6633a((float) this.f11091e, this.f11090d);
        textView.setTextColor(this.f11088b);
        int i = Build.VERSION.SDK_INT;
        C0340mj.m14694a((View) textView, (Drawable) new InsetDrawable(new RippleDrawable(this.f11088b.withAlpha(30), ggu, ggu2), this.f11087a.left, this.f11087a.top, this.f11087a.right, this.f11087a.bottom));
    }
}
