package p000;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.List;

/* renamed from: gcr */
/* compiled from: PG */
public abstract class gcr extends gct {

    /* renamed from: a */
    public final Rect f10961a = new Rect();

    /* renamed from: b */
    public int f10962b = 0;

    /* renamed from: c */
    public int f10963c;

    /* renamed from: d */
    private final Rect f10964d = new Rect();

    public gcr() {
    }

    /* renamed from: a */
    public abstract View mo3606a(List list);

    /* renamed from: e */
    public float mo3607e(View view) {
        throw null;
    }

    /* renamed from: f */
    public int mo3608f(View view) {
        throw null;
    }

    public gcr(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: g */
    public final int mo6402g(View view) {
        if (this.f10963c == 0) {
            return 0;
        }
        float e = mo3607e(view);
        int i = this.f10963c;
        return C0257jh.m14468a((int) (e * ((float) i)), 0, i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final void mo6401c(CoordinatorLayout coordinatorLayout, View view, int i) {
        View a = mo3606a(coordinatorLayout.mo1119a(view));
        if (a != null) {
            abm abm = (abm) view.getLayoutParams();
            Rect rect = this.f10961a;
            rect.set(coordinatorLayout.getPaddingLeft() + abm.leftMargin, a.getBottom() + abm.topMargin, (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - abm.rightMargin, ((coordinatorLayout.getHeight() + a.getBottom()) - coordinatorLayout.getPaddingBottom()) - abm.bottomMargin);
            C0348mr mrVar = coordinatorLayout.f1051c;
            if (mrVar != null && C0340mj.m14725p(coordinatorLayout) && !C0340mj.m14725p(view)) {
                rect.left += mrVar.mo9408a();
                rect.right -= mrVar.mo9410c();
            }
            Rect rect2 = this.f10964d;
            int i2 = abm.f82c;
            C0321lr.m14627a(i2 == 0 ? 8388659 : i2, view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i);
            int g = mo6402g(a);
            view.layout(rect2.left, rect2.top - g, rect2.right, rect2.bottom - g);
            this.f10962b = rect2.top - a.getBottom();
            return;
        }
        coordinatorLayout.mo1123b(view, i);
        this.f10962b = 0;
    }
}
