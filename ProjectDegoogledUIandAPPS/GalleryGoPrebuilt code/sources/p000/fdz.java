package p000;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.google.android.apps.photosgo.R;
import java.util.ArrayList;
import java.util.List;

/* renamed from: fdz */
/* compiled from: PG */
public final class fdz implements View.OnAttachStateChangeListener, View.OnLayoutChangeListener, fdq {

    /* renamed from: a */
    public fdr f9345a;

    /* renamed from: b */
    public boolean f9346b = false;

    /* renamed from: c */
    public boolean f9347c = false;

    /* renamed from: d */
    private final View f9348d;

    /* renamed from: e */
    private final fdr f9349e;

    /* renamed from: f */
    private List f9350f;

    /* renamed from: g */
    private boolean f9351g = false;

    /* renamed from: h */
    private ViewGroup f9352h;

    /* renamed from: i */
    private fdr f9353i = null;

    /* renamed from: j */
    private int f9354j = 2;

    public fdz(View view, fdr fdr) {
        this.f9348d = view;
        this.f9349e = fdr;
    }

    /* renamed from: f */
    public final boolean mo5530f() {
        return this.f9351g;
    }

    /* renamed from: b */
    public final void mo5525b(fdr fdr) {
        if (this.f9350f == null) {
            this.f9350f = new ArrayList();
        }
        fdq fdq = fdr.f9326c;
        ife.m12890c(this.f9350f.add(fdr));
        fdq.mo5522a(this.f9349e);
        if (this.f9346b) {
            fdq.mo5529e();
        }
    }

    /* renamed from: c */
    public final void mo5526c() {
        ife.m12876b(this.f9345a != null, (Object) "No parent override to unset");
        this.f9345a = null;
        if (this.f9346b) {
            mo5529e();
        }
    }

    /* renamed from: h */
    public final void mo5532h() {
        if (this.f9349e.mo5539b()) {
            this.f9348d.removeOnAttachStateChangeListener(this);
            if (C0340mj.m14735z(this.f9348d)) {
                onViewDetachedFromWindow(this.f9348d);
            }
        }
        fdr fdr = this.f9345a;
        if (fdr != null) {
            fdr.f9326c.mo5527c(this.f9349e);
        }
        List list = this.f9350f;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                fdr fdr2 = (fdr) list.get(i);
                if (this.f9346b) {
                    fdr2.f9326c.mo5531g();
                }
                fdr2.f9326c.mo5526c();
            }
            this.f9350f.clear();
            this.f9350f = null;
        }
        this.f9353i = null;
        this.f9348d.setTag(R.id.ve_tag, (Object) null);
    }

    /* renamed from: k */
    public final void mo5554k() {
        ViewGroup viewGroup = this.f9352h;
        if (viewGroup != null) {
            viewGroup.removeOnLayoutChangeListener(this);
            this.f9352h = null;
            return;
        }
        this.f9348d.removeOnLayoutChangeListener(this);
    }

    /* renamed from: j */
    public final void mo5553j() {
        ife.m12896d(this.f9346b);
        if (this.f9347c) {
            this.f9352h = (ViewGroup) ife.m12898e((Object) (ViewGroup) this.f9348d.getRootView().findViewById(16908290));
        } else {
            this.f9352h = (ViewGroup) this.f9348d.getParent();
        }
        ViewGroup viewGroup = this.f9352h;
        if (viewGroup == null) {
            this.f9348d.addOnLayoutChangeListener(this);
        } else {
            viewGroup.addOnLayoutChangeListener(this);
        }
    }

    /* renamed from: l */
    private final int m8663l() {
        if (this.f9347c) {
            if (this.f9348d.isShown()) {
                return 1;
            }
            return 2;
        } else if (this.f9348d.getVisibility() == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    /* renamed from: a */
    public static fdr m8660a(View view, ffa ffa) {
        ife.m12898e((Object) ffa);
        return (fdr) view.getTag(R.id.ve_tag);
    }

    /* renamed from: b */
    public final fdr mo5524b() {
        if (mo5523a() || this.f9347c) {
            return null;
        }
        fdr fdr = this.f9345a;
        if (fdr != null || (fdr = this.f9353i) != null) {
            return fdr;
        }
        ViewParent parent = this.f9348d.getParent();
        while (parent != null) {
            ffb.f9435a++;
            if (!(parent instanceof View)) {
                break;
            }
            View view = (View) parent;
            fdr a = m8660a(view, ffa.f9433a);
            if (a == null) {
                if (m8662a(view)) {
                    break;
                }
                parent = parent.getParent();
            } else {
                if (this.f9346b) {
                    this.f9353i = a;
                }
                return a;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static View m8658a(Activity activity) {
        return activity.findViewById(16908290);
    }

    /* renamed from: a */
    public static View m8659a(fdr fdr, ffa ffa) {
        ife.m12898e((Object) ffa);
        fdq fdq = fdr.f9326c;
        if (fdq instanceof fdz) {
            return ((fdz) fdq).f9348d;
        }
        return null;
    }

    /* renamed from: i */
    public final int mo5533i() {
        return m8663l();
    }

    /* renamed from: a */
    public final boolean mo5523a() {
        return (this.f9345a == null && m8662a(this.f9348d)) || this.f9347c;
    }

    /* renamed from: a */
    public static boolean m8662a(View view) {
        return view.getId() == 16908290;
    }

    /* renamed from: e */
    public final void mo5529e() {
        if (this.f9346b && !this.f9351g) {
            this.f9351g = true;
            this.f9354j = m8663l();
            this.f9349e.mo5545f(ffa.f9433a);
            List list = this.f9350f;
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    ((fdr) list.get(i)).f9326c.mo5529e();
                }
            }
        }
    }

    /* renamed from: g */
    public final void mo5531g() {
        if (this.f9351g) {
            this.f9351g = false;
            List list = this.f9350f;
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    ((fdr) list.get(i)).f9326c.mo5531g();
                }
            }
            this.f9349e.mo5546g(ffa.f9433a);
            this.f9353i = null;
        }
    }

    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        boolean z;
        if (view == this.f9348d) {
            if (this.f9352h == null) {
                z = true;
            } else {
                z = false;
            }
            ife.m12896d(z);
            ViewGroup viewGroup = (ViewGroup) this.f9348d.getParent();
            this.f9352h = viewGroup;
            viewGroup.addOnLayoutChangeListener(this);
            this.f9348d.removeOnLayoutChangeListener(this);
        }
        int l = m8663l();
        int i9 = this.f9354j;
        if (l != i9) {
            this.f9354j = l;
            fdr fdr = this.f9349e;
            ife.m12898e((Object) ffa.f9433a);
            fdo fdo = fdr.f9325b;
            if (fdo != null) {
                fdo.mo5517a(fdr, i9, l);
            }
        }
    }

    public final void onViewAttachedToWindow(View view) {
        ife.m12896d(!this.f9346b);
        this.f9346b = true;
        mo5553j();
        mo5529e();
    }

    public final void onViewDetachedFromWindow(View view) {
        ife.m12896d(this.f9346b);
        this.f9346b = false;
        mo5554k();
        fdr fdr = this.f9345a;
        if (fdr != null) {
            fdr.f9326c.mo5527c(this.f9349e);
            ife.m12876b(!this.f9351g, (Object) "View was child of detached parent.");
            return;
        }
        mo5531g();
    }

    /* renamed from: c */
    public final void mo5527c(fdr fdr) {
        ife.m12890c(this.f9350f.remove(fdr));
        fdq fdq = fdr.f9326c;
        if (this.f9346b) {
            fdq.mo5531g();
        }
        fdq.mo5526c();
    }

    /* renamed from: a */
    public final void mo5522a(fdr fdr) {
        boolean z;
        ife.m12898e((Object) fdr);
        if (this.f9345a == null) {
            z = true;
        } else {
            z = false;
        }
        ife.m12876b(z, (Object) "Already has a parent override, swapping prohibited");
        ife.m12876b(!this.f9347c, (Object) "Isolated trees cannot have parents.");
        if (this.f9346b) {
            ife.m12845a(fdr.f9326c.mo5530f(), (Object) "Attached view node cannot be a child of a detached node.");
            mo5531g();
        }
        this.f9345a = fdr;
    }

    /* renamed from: d */
    public final void mo5528d() {
        this.f9348d.setTag(R.id.ve_tag, this.f9349e);
        if (this.f9349e.mo5539b()) {
            this.f9348d.addOnAttachStateChangeListener(this);
            if (C0340mj.m14735z(this.f9348d)) {
                onViewAttachedToWindow(this.f9348d);
            }
        }
    }

    /* renamed from: a */
    public final void mo5521a(fdl fdl) {
        View view = this.f9348d;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                m8661a(viewGroup.getChildAt(i), fdl);
            }
        }
        List list = this.f9350f;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                fdl.mo5515a((fdr) this.f9350f.get(size));
            }
        }
    }

    /* renamed from: a */
    private static void m8661a(View view, fdl fdl) {
        ffb.f9436b++;
        fdr a = m8660a(view, ffa.f9433a);
        if (a != null) {
            fdq fdq = a.f9326c;
            if (fdq instanceof fdz) {
                fdz fdz = (fdz) fdq;
                if (fdz.f9345a != null || fdz.f9347c) {
                    return;
                }
            }
            fdl.mo5515a(a);
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                m8661a(viewGroup.getChildAt(i), fdl);
            }
        }
    }
}
