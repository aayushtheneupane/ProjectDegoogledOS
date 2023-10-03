package p000;

import android.content.Context;
import android.support.p002v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.google.android.apps.photosgo.R;

/* renamed from: zg */
/* compiled from: PG */
public final class C0688zg implements C0486ru {

    /* renamed from: a */
    public C0475rj f16441a;

    /* renamed from: b */
    private C0472rg f16442b;

    /* renamed from: c */
    private final /* synthetic */ Toolbar f16443c;

    public C0688zg(Toolbar toolbar) {
        this.f16443c = toolbar;
    }

    /* renamed from: a */
    public final void mo9786a(C0472rg rgVar, boolean z) {
    }

    /* renamed from: a */
    public final void mo9787a(C0485rt rtVar) {
    }

    /* renamed from: a */
    public final boolean mo9788a() {
        return false;
    }

    /* renamed from: a */
    public final boolean mo9790a(C0495sc scVar) {
        return false;
    }

    /* renamed from: a */
    public final boolean mo9789a(C0475rj rjVar) {
        View view = this.f16443c.f1020g;
        if (view instanceof C0444qf) {
            ((C0444qf) view).mo9695b();
        }
        Toolbar toolbar = this.f16443c;
        toolbar.removeView(toolbar.f1020g);
        Toolbar toolbar2 = this.f16443c;
        toolbar2.removeView(toolbar2.f1019f);
        Toolbar toolbar3 = this.f16443c;
        toolbar3.f1020g = null;
        for (int size = toolbar3.f1029p.size() - 1; size >= 0; size--) {
            toolbar3.addView((View) toolbar3.f1029p.get(size));
        }
        toolbar3.f1029p.clear();
        this.f16441a = null;
        this.f16443c.requestLayout();
        rjVar.mo9892d(false);
        return true;
    }

    /* renamed from: b */
    public final boolean mo9792b(C0475rj rjVar) {
        Toolbar toolbar = this.f16443c;
        if (toolbar.f1019f == null) {
            toolbar.f1019f = new C0531tl(toolbar.getContext(), (AttributeSet) null, R.attr.toolbarNavigationButtonStyle);
            toolbar.f1019f.setImageDrawable(toolbar.f1017d);
            toolbar.f1019f.setContentDescription(toolbar.f1018e);
            C0689zh j = Toolbar.m930j();
            j.f15336a = (toolbar.f1025l & 112) | 8388611;
            j.f16444b = 2;
            toolbar.f1019f.setLayoutParams(j);
            toolbar.f1019f.setOnClickListener(new C0687zf(toolbar));
        }
        ViewParent parent = this.f16443c.f1019f.getParent();
        Toolbar toolbar2 = this.f16443c;
        if (parent != toolbar2) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(toolbar2.f1019f);
            }
            Toolbar toolbar3 = this.f16443c;
            toolbar3.addView(toolbar3.f1019f);
        }
        this.f16443c.f1020g = rjVar.getActionView();
        this.f16441a = rjVar;
        ViewParent parent2 = this.f16443c.f1020g.getParent();
        Toolbar toolbar4 = this.f16443c;
        if (parent2 != toolbar4) {
            if (parent2 instanceof ViewGroup) {
                ((ViewGroup) parent2).removeView(toolbar4.f1020g);
            }
            C0689zh j2 = Toolbar.m930j();
            Toolbar toolbar5 = this.f16443c;
            j2.f15336a = 8388611 | (toolbar5.f1025l & 112);
            j2.f16444b = 2;
            toolbar5.f1020g.setLayoutParams(j2);
            Toolbar toolbar6 = this.f16443c;
            toolbar6.addView(toolbar6.f1020g);
        }
        Toolbar toolbar7 = this.f16443c;
        for (int childCount = toolbar7.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = toolbar7.getChildAt(childCount);
            if (!(((C0689zh) childAt.getLayoutParams()).f16444b == 2 || childAt == toolbar7.f1014a)) {
                toolbar7.removeViewAt(childCount);
                toolbar7.f1029p.add(childAt);
            }
        }
        this.f16443c.requestLayout();
        rjVar.mo9892d(true);
        View view = this.f16443c.f1020g;
        if (view instanceof C0444qf) {
            ((C0444qf) view).mo9694a();
        }
        return true;
    }

    /* renamed from: a */
    public final void mo9785a(Context context, C0472rg rgVar) {
        C0475rj rjVar;
        C0472rg rgVar2 = this.f16442b;
        if (!(rgVar2 == null || (rjVar = this.f16441a) == null)) {
            rgVar2.mo9853b(rjVar);
        }
        this.f16442b = rgVar;
    }

    /* renamed from: b */
    public final void mo9791b() {
        if (this.f16441a != null) {
            C0472rg rgVar = this.f16442b;
            if (rgVar != null) {
                int size = rgVar.size();
                int i = 0;
                while (i < size) {
                    if (this.f16442b.getItem(i) != this.f16441a) {
                        i++;
                    } else {
                        return;
                    }
                }
            }
            mo9789a(this.f16441a);
        }
    }
}
