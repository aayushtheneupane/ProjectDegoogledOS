package p000;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p002v7.widget.ActionMenuView;
import android.support.p002v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;

/* renamed from: zn */
/* compiled from: PG */
public final class C0695zn implements C0566ut {

    /* renamed from: a */
    public final Toolbar f16452a;

    /* renamed from: b */
    public int f16453b;

    /* renamed from: c */
    public CharSequence f16454c;

    /* renamed from: d */
    public Window.Callback f16455d;

    /* renamed from: e */
    public boolean f16456e;

    /* renamed from: f */
    private View f16457f;

    /* renamed from: g */
    private Drawable f16458g;

    /* renamed from: h */
    private Drawable f16459h;

    /* renamed from: i */
    private Drawable f16460i;

    /* renamed from: j */
    private boolean f16461j;

    /* renamed from: k */
    private CharSequence f16462k;

    /* renamed from: l */
    private CharSequence f16463l;

    /* renamed from: m */
    private C0512st f16464m;

    /* renamed from: n */
    private int f16465n = 0;

    /* renamed from: o */
    private Drawable f16466o;

    public C0695zn(Toolbar toolbar, boolean z) {
        Drawable drawable;
        this.f16452a = toolbar;
        this.f16454c = toolbar.f1027n;
        this.f16462k = toolbar.f1028o;
        this.f16461j = this.f16454c != null;
        this.f16460i = toolbar.mo1097e();
        String str = null;
        C0684zc a = C0684zc.m16192a(toolbar.getContext(), (AttributeSet) null, C0435px.f15573a, R.attr.actionBarStyle, 0);
        int i = 15;
        this.f16466o = a.mo10723a(15);
        if (z) {
            CharSequence c = a.mo10729c(27);
            if (!TextUtils.isEmpty(c)) {
                mo10331b(c);
            }
            CharSequence c2 = a.mo10729c(25);
            if (!TextUtils.isEmpty(c2)) {
                this.f16462k = c2;
                if ((this.f16453b & 8) != 0) {
                    this.f16452a.mo1090b(c2);
                }
            }
            Drawable a2 = a.mo10723a(20);
            if (a2 != null) {
                mo10325a(a2);
            }
            Drawable a3 = a.mo10723a(17);
            if (a3 != null) {
                this.f16458g = a3;
                m16222s();
            }
            if (this.f16460i == null && (drawable = this.f16466o) != null) {
                this.f16460i = drawable;
                m16223t();
            }
            mo10324a(a.mo10722a(10, 0));
            int f = a.mo10734f(9, 0);
            if (f != 0) {
                View inflate = LayoutInflater.from(this.f16452a.getContext()).inflate(f, this.f16452a, false);
                View view = this.f16457f;
                if (!(view == null || (this.f16453b & 16) == 0)) {
                    this.f16452a.removeView(view);
                }
                this.f16457f = inflate;
                if (!(inflate == null || (this.f16453b & 16) == 0)) {
                    this.f16452a.addView(inflate);
                }
                mo10324a(this.f16453b | 16);
            }
            int e = a.mo10732e(13, 0);
            if (e > 0) {
                ViewGroup.LayoutParams layoutParams = this.f16452a.getLayoutParams();
                layoutParams.height = e;
                this.f16452a.setLayoutParams(layoutParams);
            }
            int c3 = a.mo10728c(7, -1);
            int c4 = a.mo10728c(3, -1);
            if (c3 >= 0 || c4 >= 0) {
                Toolbar toolbar2 = this.f16452a;
                int max = Math.max(c3, 0);
                int max2 = Math.max(c4, 0);
                toolbar2.mo1105i();
                toolbar2.f1026m.mo10712a(max, max2);
            }
            int f2 = a.mo10734f(28, 0);
            if (f2 != 0) {
                Toolbar toolbar3 = this.f16452a;
                Context context = toolbar3.getContext();
                toolbar3.f1023j = f2;
                TextView textView = toolbar3.f1015b;
                if (textView != null) {
                    textView.setTextAppearance(context, f2);
                }
            }
            int f3 = a.mo10734f(26, 0);
            if (f3 != 0) {
                Toolbar toolbar4 = this.f16452a;
                Context context2 = toolbar4.getContext();
                toolbar4.f1024k = f3;
                TextView textView2 = toolbar4.f1016c;
                if (textView2 != null) {
                    textView2.setTextAppearance(context2, f3);
                }
            }
            int f4 = a.mo10734f(22, 0);
            if (f4 != 0) {
                this.f16452a.mo1082a(f4);
            }
        } else {
            if (this.f16452a.mo1097e() != null) {
                this.f16466o = this.f16452a.mo1097e();
            } else {
                i = 11;
            }
            this.f16453b = i;
        }
        a.mo10724a();
        if (this.f16465n != R.string.abc_action_bar_up_description) {
            this.f16465n = R.string.abc_action_bar_up_description;
            if (TextUtils.isEmpty(this.f16452a.mo1095d())) {
                int i2 = this.f16465n;
                this.f16463l = i2 != 0 ? mo10329b().getString(i2) : str;
                m16224u();
            }
        }
        this.f16463l = this.f16452a.mo1095d();
        this.f16452a.mo1084a((View.OnClickListener) new C0693zl(this));
    }

    /* renamed from: a */
    public final ViewGroup mo10322a() {
        return this.f16452a;
    }

    /* renamed from: c */
    public final boolean mo10332c() {
        C0688zg zgVar = this.f16452a.f1032s;
        return (zgVar == null || zgVar.f16441a == null) ? false : true;
    }

    /* renamed from: e */
    public final void mo10334e() {
    }

    /* renamed from: f */
    public final void mo10335f() {
    }

    /* renamed from: n */
    public final int mo10343n() {
        return this.f16453b;
    }

    /* renamed from: o */
    public final void mo10344o() {
    }

    /* renamed from: p */
    public final void mo10345p() {
    }

    /* renamed from: q */
    public final void mo10346q() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0009, code lost:
        r0 = r0.f1014a;
     */
    /* renamed from: g */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo10336g() {
        /*
            r3 = this;
            android.support.v7.widget.Toolbar r0 = r3.f16452a
            int r1 = r0.getVisibility()
            r2 = 0
            if (r1 != 0) goto L_0x0013
            android.support.v7.widget.ActionMenuView r0 = r0.f1014a
            if (r0 == 0) goto L_0x0013
            boolean r0 = r0.f935b
            if (r0 == 0) goto L_0x0013
            r2 = 1
        L_0x0013:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0695zn.mo10336g():boolean");
    }

    /* renamed from: d */
    public final void mo10333d() {
        this.f16452a.mo1091c();
    }

    /* renamed from: m */
    public final void mo10342m() {
        ActionMenuView actionMenuView = this.f16452a.f1014a;
        if (actionMenuView != null) {
            actionMenuView.mo849b();
        }
    }

    /* renamed from: b */
    public final Context mo10329b() {
        return this.f16452a.getContext();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r0 = r0.f936c;
     */
    /* renamed from: k */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo10340k() {
        /*
            r2 = this;
            android.support.v7.widget.Toolbar r0 = r2.f16452a
            android.support.v7.widget.ActionMenuView r0 = r0.f1014a
            r1 = 0
            if (r0 == 0) goto L_0x0014
            st r0 = r0.f936c
            if (r0 == 0) goto L_0x0014
            boolean r0 = r0.mo10071d()
            if (r0 == 0) goto L_0x0013
            r1 = 1
            goto L_0x0015
        L_0x0013:
            return r1
        L_0x0014:
        L_0x0015:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0695zn.mo10340k():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r0.f936c;
     */
    /* renamed from: i */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo10338i() {
        /*
            r4 = this;
            android.support.v7.widget.Toolbar r0 = r4.f16452a
            android.support.v7.widget.ActionMenuView r0 = r0.f1014a
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0019
            st r0 = r0.f936c
            if (r0 == 0) goto L_0x0019
            so r3 = r0.f15883k
            if (r3 != 0) goto L_0x0018
            boolean r0 = r0.mo10072e()
            if (r0 != 0) goto L_0x0017
            goto L_0x0019
        L_0x0017:
            return r1
        L_0x0018:
            goto L_0x001a
        L_0x0019:
            r1 = 0
        L_0x001a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0695zn.mo10338i():boolean");
    }

    /* renamed from: h */
    public final boolean mo10337h() {
        return this.f16452a.mo1087an();
    }

    /* renamed from: r */
    public final void mo10347r() {
        Toolbar toolbar = this.f16452a;
        toolbar.f1035v = false;
        toolbar.requestLayout();
    }

    /* renamed from: a */
    public final void mo10324a(int i) {
        View view;
        int i2 = this.f16453b ^ i;
        this.f16453b = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    m16224u();
                }
                m16223t();
            }
            if ((i2 & 3) != 0) {
                m16222s();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.f16452a.mo1085a(this.f16454c);
                    this.f16452a.mo1090b(this.f16462k);
                } else {
                    this.f16452a.mo1085a((CharSequence) null);
                    this.f16452a.mo1090b((CharSequence) null);
                }
            }
            if ((i2 & 16) != 0 && (view = this.f16457f) != null) {
                if ((i & 16) == 0) {
                    this.f16452a.removeView(view);
                } else {
                    this.f16452a.addView(view);
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo10325a(Drawable drawable) {
        this.f16459h = drawable;
        m16222s();
    }

    /* renamed from: a */
    public final void mo10326a(Menu menu, C0485rt rtVar) {
        if (this.f16464m == null) {
            this.f16464m = new C0512st(this.f16452a.getContext());
        }
        C0512st stVar = this.f16464m;
        stVar.f15694e = rtVar;
        Toolbar toolbar = this.f16452a;
        if (menu != null || toolbar.f1014a != null) {
            toolbar.mo1100g();
            C0472rg rgVar = toolbar.f1014a.f934a;
            if (rgVar != menu) {
                if (rgVar != null) {
                    rgVar.mo9850b((C0486ru) toolbar.f1031r);
                    rgVar.mo9850b((C0486ru) toolbar.f1032s);
                }
                if (toolbar.f1032s == null) {
                    toolbar.f1032s = new C0688zg(toolbar);
                }
                stVar.mo10074g();
                if (menu != null) {
                    C0472rg rgVar2 = (C0472rg) menu;
                    rgVar2.mo9834a((C0486ru) stVar, toolbar.f1021h);
                    rgVar2.mo9834a((C0486ru) toolbar.f1032s, toolbar.f1021h);
                } else {
                    stVar.mo9785a(toolbar.f1021h, (C0472rg) null);
                    toolbar.f1032s.mo9785a(toolbar.f1021h, (C0472rg) null);
                    stVar.mo9791b();
                    toolbar.f1032s.mo9791b();
                }
                toolbar.f1014a.mo846a(toolbar.f1022i);
                toolbar.f1014a.mo848a(stVar);
                toolbar.f1031r = stVar;
            }
        }
    }

    /* renamed from: l */
    public final void mo10341l() {
        this.f16456e = true;
    }

    /* renamed from: b */
    public final void mo10331b(CharSequence charSequence) {
        this.f16461j = true;
        m16221c(charSequence);
    }

    /* renamed from: c */
    private final void m16221c(CharSequence charSequence) {
        this.f16454c = charSequence;
        if ((this.f16453b & 8) != 0) {
            this.f16452a.mo1085a(charSequence);
        }
    }

    /* renamed from: b */
    public final void mo10330b(int i) {
        this.f16452a.setVisibility(i);
    }

    /* renamed from: a */
    public final void mo10327a(Window.Callback callback) {
        this.f16455d = callback;
    }

    /* renamed from: a */
    public final void mo10328a(CharSequence charSequence) {
        if (!this.f16461j) {
            m16221c(charSequence);
        }
    }

    /* renamed from: a */
    public final C0344mn mo10323a(int i, long j) {
        float f;
        C0344mn k = C0340mj.m14720k(this.f16452a);
        if (i == 0) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        k.mo9400a(f);
        k.mo9401a(j);
        k.mo9402a((C0345mo) new C0694zm(this, i));
        return k;
    }

    /* renamed from: j */
    public final boolean mo10339j() {
        return this.f16452a.mo1086al();
    }

    /* renamed from: u */
    private final void m16224u() {
        if ((this.f16453b & 4) == 0) {
            return;
        }
        if (TextUtils.isEmpty(this.f16463l)) {
            this.f16452a.mo1092c(this.f16465n);
        } else {
            this.f16452a.mo1093c(this.f16463l);
        }
    }

    /* renamed from: t */
    private final void m16223t() {
        if ((this.f16453b & 4) != 0) {
            Toolbar toolbar = this.f16452a;
            Drawable drawable = this.f16460i;
            if (drawable == null) {
                drawable = this.f16466o;
            }
            toolbar.mo1089b(drawable);
            return;
        }
        this.f16452a.mo1089b((Drawable) null);
    }

    /* renamed from: s */
    private final void m16222s() {
        Drawable drawable;
        int i = this.f16453b;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) == 0) {
            drawable = this.f16458g;
        } else {
            drawable = this.f16459h;
            if (drawable == null) {
                drawable = this.f16458g;
            }
        }
        this.f16452a.mo1083a(drawable);
    }
}
