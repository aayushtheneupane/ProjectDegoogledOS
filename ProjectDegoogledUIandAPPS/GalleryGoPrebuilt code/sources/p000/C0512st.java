package p000;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.p002v7.view.menu.ActionMenuItemView;
import android.support.p002v7.widget.ActionMenuView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;
import java.util.ArrayList;

/* renamed from: st */
/* compiled from: PG */
public final class C0512st extends C0458qt {

    /* renamed from: g */
    public C0509sq f15879g;

    /* renamed from: h */
    public int f15880h;

    /* renamed from: i */
    public C0510sr f15881i;

    /* renamed from: j */
    public C0506sn f15882j;

    /* renamed from: k */
    public C0507so f15883k;

    /* renamed from: l */
    public final C0511ss f15884l = new C0511ss(this);

    /* renamed from: m */
    public int f15885m;

    /* renamed from: n */
    private boolean f15886n;

    /* renamed from: o */
    private boolean f15887o;

    /* renamed from: p */
    private int f15888p;

    /* renamed from: q */
    private int f15889q;

    /* renamed from: r */
    private boolean f15890r;

    /* renamed from: s */
    private final SparseBooleanArray f15891s = new SparseBooleanArray();

    /* renamed from: t */
    private C0457qs f15892t;

    public C0512st(Context context) {
        super(context);
    }

    /* renamed from: f */
    public final void mo10073f() {
        mo10071d();
        mo10076i();
    }

    /* renamed from: a */
    public final boolean mo9788a() {
        int i;
        ArrayList arrayList;
        boolean z;
        C0472rg rgVar = this.f15692c;
        View view = null;
        if (rgVar != null) {
            arrayList = rgVar.mo9862g();
            i = arrayList.size();
        } else {
            arrayList = null;
            i = 0;
        }
        int i2 = this.f15880h;
        int i3 = this.f15889q;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) this.f15695f;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        boolean z2 = false;
        while (true) {
            z = true;
            if (i4 >= i) {
                break;
            }
            C0475rj rjVar = (C0475rj) arrayList.get(i4);
            if (rjVar.mo9909h()) {
                i5++;
            } else if (rjVar.mo9896g()) {
                i6++;
            } else {
                z2 = true;
            }
            if (this.f15890r && rjVar.f15795p) {
                i2 = 0;
            }
            i4++;
            view = null;
        }
        if (this.f15886n && (z2 || i6 + i5 > i2)) {
            i2--;
        }
        int i7 = i2 - i5;
        SparseBooleanArray sparseBooleanArray = this.f15891s;
        sparseBooleanArray.clear();
        int i8 = 0;
        int i9 = 0;
        while (i8 < i) {
            C0475rj rjVar2 = (C0475rj) arrayList.get(i8);
            if (rjVar2.mo9909h()) {
                View a = mo9784a(rjVar2, view, viewGroup);
                a.measure(makeMeasureSpec, makeMeasureSpec);
                int measuredWidth = a.getMeasuredWidth();
                i3 -= measuredWidth;
                if (i9 == 0) {
                    i9 = measuredWidth;
                }
                int i10 = rjVar2.f15781b;
                if (i10 != 0) {
                    sparseBooleanArray.put(i10, z);
                }
                rjVar2.mo9891c(z);
            } else if (rjVar2.mo9896g()) {
                int i11 = rjVar2.f15781b;
                boolean z3 = sparseBooleanArray.get(i11);
                boolean z4 = (i7 > 0 || z3) && i3 > 0;
                if (z4) {
                    View a2 = mo9784a(rjVar2, view, viewGroup);
                    a2.measure(makeMeasureSpec, makeMeasureSpec);
                    int measuredWidth2 = a2.getMeasuredWidth();
                    i3 -= measuredWidth2;
                    if (i9 == 0) {
                        i9 = measuredWidth2;
                    }
                    z4 = i3 + i9 > 0;
                }
                boolean z5 = z4;
                if (z5 && i11 != 0) {
                    sparseBooleanArray.put(i11, z);
                } else if (z3) {
                    sparseBooleanArray.put(i11, false);
                    for (int i12 = 0; i12 < i8; i12++) {
                        C0475rj rjVar3 = (C0475rj) arrayList.get(i12);
                        if (rjVar3.f15781b == i11) {
                            if (rjVar3.mo9895f()) {
                                i7++;
                            }
                            rjVar3.mo9891c(false);
                        }
                    }
                }
                if (z5) {
                    i7--;
                }
                rjVar2.mo9891c(z5);
            } else {
                rjVar2.mo9891c(false);
            }
            i8++;
            view = null;
            z = true;
        }
        return true;
    }

    /* renamed from: a */
    public final View mo9784a(C0475rj rjVar, View view, ViewGroup viewGroup) {
        C0487rv rvVar;
        View actionView = rjVar.getActionView();
        int i = 0;
        if (actionView == null || rjVar.mo9911i()) {
            if (view instanceof C0487rv) {
                rvVar = (C0487rv) view;
            } else {
                rvVar = (C0487rv) this.f15693d.inflate(R.layout.abc_action_menu_item_layout, viewGroup, false);
            }
            rvVar.mo763a(rjVar);
            ActionMenuItemView actionMenuItemView = (ActionMenuItemView) rvVar;
            actionMenuItemView.f859c = (ActionMenuView) this.f15695f;
            if (this.f15892t == null) {
                this.f15892t = new C0457qs(this);
            }
            actionMenuItemView.f860d = this.f15892t;
            actionView = (View) rvVar;
        }
        if (rjVar.f15795p) {
            i = 8;
        }
        actionView.setVisibility(i);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!(layoutParams instanceof C0515sw)) {
            actionView.setLayoutParams(ActionMenuView.m894b(layoutParams));
        }
        return actionView;
    }

    /* renamed from: d */
    public final boolean mo10071d() {
        C0488rw rwVar;
        C0507so soVar = this.f15883k;
        if (soVar == null || (rwVar = this.f15695f) == null) {
            C0510sr srVar = this.f15881i;
            if (srVar == null) {
                return false;
            }
            srVar.mo10000c();
            return true;
        }
        ((View) rwVar).removeCallbacks(soVar);
        this.f15883k = null;
        return true;
    }

    /* renamed from: i */
    public final void mo10076i() {
        C0506sn snVar = this.f15882j;
        if (snVar != null) {
            snVar.mo10000c();
        }
    }

    /* renamed from: a */
    public final void mo9785a(Context context, C0472rg rgVar) {
        this.f15691b = context;
        LayoutInflater.from(this.f15691b);
        this.f15692c = rgVar;
        Resources resources = context.getResources();
        C0441qc a = C0441qc.m15109a(context);
        if (!this.f15887o) {
            int i = Build.VERSION.SDK_INT;
            this.f15886n = true;
        }
        this.f15888p = a.f15602a.getResources().getDisplayMetrics().widthPixels / 2;
        this.f15880h = a.mo9692a();
        int i2 = this.f15888p;
        if (!this.f15886n) {
            this.f15879g = null;
        } else {
            if (this.f15879g == null) {
                this.f15879g = new C0509sq(this, this.f15690a);
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.f15879g.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i2 -= this.f15879g.getMeasuredWidth();
        }
        this.f15889q = i2;
        float f = resources.getDisplayMetrics().density;
    }

    /* renamed from: e */
    public final boolean mo10072e() {
        C0510sr srVar = this.f15881i;
        return srVar != null && srVar.mo10002e();
    }

    /* renamed from: a */
    public final void mo9786a(C0472rg rgVar, boolean z) {
        mo10073f();
        C0485rt rtVar = this.f15694e;
        if (rtVar != null) {
            rtVar.mo9574a(rgVar, z);
        }
    }

    /* renamed from: a */
    public final boolean mo9790a(C0495sc scVar) {
        boolean z = false;
        if (!scVar.hasVisibleItems()) {
            return false;
        }
        C0495sc scVar2 = scVar;
        while (true) {
            C0472rg rgVar = scVar2.f15853j;
            if (rgVar == this.f15692c) {
                break;
            }
            scVar2 = rgVar;
        }
        C0475rj rjVar = scVar2.f15854k;
        ViewGroup viewGroup = (ViewGroup) this.f15695f;
        View view = null;
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    break;
                }
                View childAt = viewGroup.getChildAt(i);
                if ((childAt instanceof C0487rv) && ((C0487rv) childAt).mo762a() == rjVar) {
                    view = childAt;
                    break;
                }
                i++;
            }
        }
        if (view == null) {
            return false;
        }
        this.f15885m = scVar.f15854k.f15780a;
        int size = scVar.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            MenuItem item = scVar.getItem(i2);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
            i2++;
        }
        C0506sn snVar = new C0506sn(this, this.f15691b, scVar, view);
        this.f15882j = snVar;
        snVar.mo9998a(z);
        if (this.f15882j.mo9999b()) {
            super.mo9790a(scVar);
            return true;
        }
        throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
    }

    /* renamed from: g */
    public final void mo10074g() {
        this.f15890r = true;
    }

    /* renamed from: a */
    public final void mo10069a(ActionMenuView actionMenuView) {
        this.f15695f = actionMenuView;
        actionMenuView.f934a = this.f15692c;
    }

    /* renamed from: h */
    public final void mo10075h() {
        this.f15886n = true;
        this.f15887o = true;
    }

    /* renamed from: c */
    public final boolean mo10070c() {
        C0472rg rgVar;
        if (!this.f15886n || mo10072e() || (rgVar = this.f15692c) == null || this.f15695f == null || this.f15883k != null || rgVar.mo9866i().isEmpty()) {
            return false;
        }
        this.f15883k = new C0507so(this, new C0510sr(this, this.f15691b, this.f15692c, this.f15879g));
        ((View) this.f15695f).post(this.f15883k);
        super.mo9790a((C0495sc) null);
        return true;
    }

    /* renamed from: b */
    public final void mo9791b() {
        C0488rw rwVar;
        int size;
        int i;
        ViewGroup viewGroup = (ViewGroup) this.f15695f;
        ArrayList arrayList = null;
        if (viewGroup != null) {
            C0472rg rgVar = this.f15692c;
            if (rgVar != null) {
                rgVar.mo9864h();
                ArrayList g = this.f15692c.mo9862g();
                int size2 = g.size();
                i = 0;
                for (int i2 = 0; i2 < size2; i2++) {
                    C0475rj rjVar = (C0475rj) g.get(i2);
                    if (rjVar.mo9895f()) {
                        View childAt = viewGroup.getChildAt(i);
                        C0475rj a = childAt instanceof C0487rv ? ((C0487rv) childAt).mo762a() : null;
                        View a2 = mo9784a(rjVar, childAt, viewGroup);
                        if (rjVar != a) {
                            a2.setPressed(false);
                            a2.jumpDrawablesToCurrentState();
                        }
                        if (a2 != childAt) {
                            ViewGroup viewGroup2 = (ViewGroup) a2.getParent();
                            if (viewGroup2 != null) {
                                viewGroup2.removeView(a2);
                            }
                            ((ViewGroup) this.f15695f).addView(a2, i);
                        }
                        i++;
                    }
                }
            } else {
                i = 0;
            }
            while (i < viewGroup.getChildCount()) {
                if (viewGroup.getChildAt(i) == this.f15879g) {
                    i++;
                } else {
                    viewGroup.removeViewAt(i);
                }
            }
        }
        ((View) this.f15695f).requestLayout();
        C0472rg rgVar2 = this.f15692c;
        if (rgVar2 != null) {
            rgVar2.mo9864h();
            ArrayList arrayList2 = rgVar2.f15752d;
            int size3 = arrayList2.size();
            for (int i3 = 0; i3 < size3; i3++) {
                C0475rj rjVar2 = (C0475rj) arrayList2.get(i3);
            }
        }
        C0472rg rgVar3 = this.f15692c;
        if (rgVar3 != null) {
            arrayList = rgVar3.mo9866i();
        }
        if (this.f15886n && arrayList != null && ((size = arrayList.size()) != 1 ? size > 0 : (!((C0475rj) arrayList.get(0)).f15795p))) {
            if (this.f15879g == null) {
                this.f15879g = new C0509sq(this, this.f15690a);
            }
            ViewGroup viewGroup3 = (ViewGroup) this.f15879g.getParent();
            if (viewGroup3 != this.f15695f) {
                if (viewGroup3 != null) {
                    viewGroup3.removeView(this.f15879g);
                }
                C0509sq sqVar = this.f15879g;
                C0515sw d = ActionMenuView.m895d();
                d.f15893a = true;
                ((ActionMenuView) this.f15695f).addView(sqVar, d);
            }
        } else {
            C0509sq sqVar2 = this.f15879g;
            if (sqVar2 != null && sqVar2.getParent() == (rwVar = this.f15695f)) {
                ((ViewGroup) rwVar).removeView(this.f15879g);
            }
        }
        ((ActionMenuView) this.f15695f).f935b = this.f15886n;
    }
}
