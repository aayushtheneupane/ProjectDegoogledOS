package p000;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* renamed from: abm */
/* compiled from: PG */
public final class abm extends ViewGroup.MarginLayoutParams {

    /* renamed from: a */
    public abj f80a;

    /* renamed from: b */
    public boolean f81b = false;

    /* renamed from: c */
    public int f82c = 0;

    /* renamed from: d */
    public int f83d = 0;

    /* renamed from: e */
    public int f84e = -1;

    /* renamed from: f */
    public int f85f = -1;

    /* renamed from: g */
    public int f86g = 0;

    /* renamed from: h */
    public int f87h = 0;

    /* renamed from: i */
    public int f88i;

    /* renamed from: j */
    public int f89j;

    /* renamed from: k */
    public View f90k;

    /* renamed from: l */
    public View f91l;

    /* renamed from: m */
    public boolean f92m;

    /* renamed from: n */
    public boolean f93n;

    /* renamed from: o */
    public final Rect f94o = new Rect();

    /* renamed from: p */
    private boolean f95p;

    /* renamed from: q */
    private boolean f96q;

    public abm() {
        super(-2, -2);
    }

    /* renamed from: a */
    public final boolean mo104a(int i) {
        if (i == 0) {
            return this.f95p;
        }
        if (i != 1) {
            return false;
        }
        return this.f96q;
    }

    public abm(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, abg.f77b);
        this.f82c = obtainStyledAttributes.getInteger(0, 0);
        this.f85f = obtainStyledAttributes.getResourceId(1, -1);
        this.f83d = obtainStyledAttributes.getInteger(2, 0);
        this.f84e = obtainStyledAttributes.getInteger(6, -1);
        this.f86g = obtainStyledAttributes.getInt(5, 0);
        this.f87h = obtainStyledAttributes.getInt(4, 0);
        boolean hasValue = obtainStyledAttributes.hasValue(3);
        this.f81b = hasValue;
        if (hasValue) {
            this.f80a = CoordinatorLayout.m958a(context, attributeSet, obtainStyledAttributes.getString(3));
        }
        obtainStyledAttributes.recycle();
        abj abj = this.f80a;
        if (abj != null) {
            abj.mo82a(this);
        }
    }

    public abm(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
    }

    public abm(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
    }

    public abm(abm abm) {
        super(abm);
    }

    /* renamed from: a */
    public final void mo101a() {
        this.f93n = false;
    }

    /* renamed from: a */
    public final void mo103a(abj abj) {
        abj abj2 = this.f80a;
        if (abj2 != abj) {
            if (abj2 != null) {
                abj2.mo81a();
            }
            this.f80a = abj;
            this.f81b = true;
            if (abj != null) {
                abj.mo82a(this);
            }
        }
    }

    /* renamed from: a */
    public final void mo102a(int i, boolean z) {
        if (i == 0) {
            this.f95p = z;
        } else if (i == 1) {
            this.f96q = z;
        }
    }
}
