package p000;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/* renamed from: td */
/* compiled from: PG */
public final class C0523td {

    /* renamed from: a */
    public C0682za f15913a;

    /* renamed from: b */
    private final View f15914b;

    /* renamed from: c */
    private final C0529tj f15915c;

    /* renamed from: d */
    private int f15916d = -1;

    /* renamed from: e */
    private C0682za f15917e;

    /* renamed from: f */
    private C0682za f15918f;

    public C0523td(View view) {
        this.f15914b = view;
        this.f15915c = C0529tj.m15440b();
    }

    /* renamed from: a */
    public final void mo10102a() {
        Drawable background = this.f15914b.getBackground();
        if (background != null) {
            int i = Build.VERSION.SDK_INT;
            if (this.f15917e != null) {
                if (this.f15918f == null) {
                    this.f15918f = new C0682za();
                }
                C0682za zaVar = this.f15918f;
                zaVar.f16431a = null;
                zaVar.f16434d = false;
                zaVar.f16432b = null;
                zaVar.f16433c = false;
                ColorStateList s = C0340mj.m14728s(this.f15914b);
                if (s != null) {
                    zaVar.f16434d = true;
                    zaVar.f16431a = s;
                }
                PorterDuff.Mode t = C0340mj.m14729t(this.f15914b);
                if (t != null) {
                    zaVar.f16433c = true;
                    zaVar.f16432b = t;
                }
                if (zaVar.f16434d || zaVar.f16433c) {
                    C0529tj.m15439a(background, zaVar, this.f15914b.getDrawableState());
                    return;
                }
            }
            C0682za zaVar2 = this.f15913a;
            if (zaVar2 != null) {
                C0529tj.m15439a(background, zaVar2, this.f15914b.getDrawableState());
                return;
            }
            C0682za zaVar3 = this.f15917e;
            if (zaVar3 != null) {
                C0529tj.m15439a(background, zaVar3, this.f15914b.getDrawableState());
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10104a(AttributeSet attributeSet, int i) {
        C0684zc a = C0684zc.m16192a(this.f15914b.getContext(), attributeSet, C0435px.f15598z, i, 0);
        try {
            if (a.mo10735f(0)) {
                this.f15916d = a.mo10734f(0, -1);
                ColorStateList b = this.f15915c.mo10133b(this.f15914b.getContext(), this.f15916d);
                if (b != null) {
                    m15427a(b);
                }
            }
            if (a.mo10735f(1)) {
                C0340mj.m14691a(this.f15914b, a.mo10733e(1));
            }
            if (a.mo10735f(2)) {
                C0340mj.m14692a(this.f15914b, C0579vf.m15603a(a.mo10722a(2, -1), (PorterDuff.Mode) null));
            }
        } finally {
            a.mo10724a();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo10105b() {
        this.f15916d = -1;
        m15427a((ColorStateList) null);
        mo10102a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10103a(int i) {
        ColorStateList colorStateList;
        this.f15916d = i;
        C0529tj tjVar = this.f15915c;
        if (tjVar != null) {
            colorStateList = tjVar.mo10133b(this.f15914b.getContext(), i);
        } else {
            colorStateList = null;
        }
        m15427a(colorStateList);
        mo10102a();
    }

    /* renamed from: a */
    private final void m15427a(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f15917e == null) {
                this.f15917e = new C0682za();
            }
            C0682za zaVar = this.f15917e;
            zaVar.f16431a = colorStateList;
            zaVar.f16434d = true;
        } else {
            this.f15917e = null;
        }
        mo10102a();
    }
}
