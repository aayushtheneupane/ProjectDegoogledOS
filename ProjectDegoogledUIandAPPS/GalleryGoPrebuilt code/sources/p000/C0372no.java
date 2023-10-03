package p000;

import android.graphics.Rect;
import android.os.Build;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;
import java.util.List;

/* renamed from: no */
/* compiled from: PG */
public abstract class C0372no extends C0315ll {

    /* renamed from: g */
    private static final Rect f15295g = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, RecyclerView.UNDEFINED_DURATION, RecyclerView.UNDEFINED_DURATION);

    /* renamed from: b */
    public final AccessibilityManager f15296b;

    /* renamed from: c */
    public final View f15297c;

    /* renamed from: d */
    public int f15298d = RecyclerView.UNDEFINED_DURATION;

    /* renamed from: e */
    public int f15299e = RecyclerView.UNDEFINED_DURATION;

    /* renamed from: f */
    public int f15300f = RecyclerView.UNDEFINED_DURATION;

    /* renamed from: h */
    private final Rect f15301h = new Rect();

    /* renamed from: i */
    private final Rect f15302i = new Rect();

    /* renamed from: j */
    private final Rect f15303j = new Rect();

    /* renamed from: k */
    private final int[] f15304k = new int[2];

    /* renamed from: l */
    private C0371nn f15305l;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo2870a(int i, C0354mx mxVar);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo2871a(AccessibilityEvent accessibilityEvent) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo2872a(List list);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract boolean mo2873a();

    public C0372no(View view) {
        if (view != null) {
            this.f15297c = view;
            this.f15296b = (AccessibilityManager) view.getContext().getSystemService("accessibility");
            view.setFocusable(true);
            if (C0340mj.m14712e(view) == 0) {
                C0340mj.m14689a(view, 1);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("View may not be null");
    }

    /* renamed from: c */
    public final boolean mo9465c(int i) {
        if (this.f15298d != i) {
            return false;
        }
        this.f15298d = RecyclerView.UNDEFINED_DURATION;
        this.f15297c.invalidate();
        mo9464b(i, 65536);
        return true;
    }

    /* renamed from: d */
    public final boolean mo9466d(int i) {
        if (this.f15299e != i) {
            return false;
        }
        this.f15299e = RecyclerView.UNDEFINED_DURATION;
        mo9464b(i, 8);
        return true;
    }

    /* renamed from: a */
    public final AccessibilityEvent mo9461a(int i, int i2) {
        if (i != -1) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
            C0354mx b = mo9463b(i);
            obtain.getText().add(b.mo9442h());
            obtain.setContentDescription(b.mo9444i());
            obtain.setScrollable(b.mo9440f());
            obtain.setPassword(b.mo9438e());
            obtain.setEnabled(b.mo9437d());
            obtain.setChecked(b.mo9431b());
            mo2871a(obtain);
            if (!obtain.getText().isEmpty() || obtain.getContentDescription() != null) {
                obtain.setClassName(b.mo9441g());
                View view = this.f15297c;
                int i3 = Build.VERSION.SDK_INT;
                obtain.setSource(view, i);
                obtain.setPackageName(this.f15297c.getContext().getPackageName());
                return obtain;
            }
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        AccessibilityEvent obtain2 = AccessibilityEvent.obtain(i2);
        this.f15297c.onInitializeAccessibilityEvent(obtain2);
        return obtain2;
    }

    /* renamed from: a */
    public final C0358na mo9357a(View view) {
        if (this.f15305l == null) {
            this.f15305l = new C0371nn(this);
        }
        return this.f15305l;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final C0354mx mo9463b(int i) {
        boolean z;
        if (i != -1) {
            C0354mx a = C0354mx.m14777a();
            a.f15257a.setEnabled(true);
            a.f15257a.setFocusable(true);
            a.mo9423a((CharSequence) "android.view.View");
            a.mo9427b(f15295g);
            a.mo9436d(f15295g);
            View view = this.f15297c;
            a.f15258b = -1;
            a.f15257a.setParent(view);
            mo2870a(i, a);
            if (a.mo9442h() == null && a.mo9444i() == null) {
                throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
            }
            a.mo9422a(this.f15302i);
            if (!this.f15302i.equals(f15295g)) {
                int actions = a.f15257a.getActions();
                if ((actions & 64) != 0) {
                    throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
                } else if ((actions & 128) == 0) {
                    a.f15257a.setPackageName(this.f15297c.getContext().getPackageName());
                    View view2 = this.f15297c;
                    a.f15259c = i;
                    int i2 = Build.VERSION.SDK_INT;
                    a.f15257a.setSource(view2, i);
                    if (this.f15298d != i) {
                        a.mo9430b(false);
                        a.mo9420a(64);
                    } else {
                        a.mo9430b(true);
                        a.mo9420a(128);
                    }
                    if (this.f15299e == i) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        a.mo9420a(2);
                    } else if (a.mo9435c()) {
                        a.mo9420a(1);
                    }
                    a.f15257a.setFocused(z);
                    this.f15297c.getLocationOnScreen(this.f15304k);
                    a.mo9432c(this.f15301h);
                    if (this.f15301h.equals(f15295g)) {
                        a.mo9422a(this.f15301h);
                        if (a.f15258b != -1) {
                            C0354mx a2 = C0354mx.m14777a();
                            for (int i3 = a.f15258b; i3 != -1; i3 = a2.f15258b) {
                                View view3 = this.f15297c;
                                a2.f15258b = -1;
                                int i4 = Build.VERSION.SDK_INT;
                                a2.f15257a.setParent(view3, -1);
                                a2.mo9427b(f15295g);
                                mo2870a(0, a2);
                                a2.mo9422a(this.f15302i);
                                this.f15301h.offset(this.f15302i.left, this.f15302i.top);
                            }
                            a2.f15257a.recycle();
                        }
                        this.f15301h.offset(this.f15304k[0] - this.f15297c.getScrollX(), this.f15304k[1] - this.f15297c.getScrollY());
                    }
                    if (this.f15297c.getLocalVisibleRect(this.f15303j)) {
                        this.f15303j.offset(this.f15304k[0] - this.f15297c.getScrollX(), this.f15304k[1] - this.f15297c.getScrollY());
                        if (this.f15301h.intersect(this.f15303j)) {
                            a.mo9436d(this.f15301h);
                            Rect rect = this.f15301h;
                            if (rect != null && !rect.isEmpty() && this.f15297c.getWindowVisibility() == 0) {
                                ViewParent parent = this.f15297c.getParent();
                                while (true) {
                                    if (parent instanceof View) {
                                        View view4 = (View) parent;
                                        if (view4.getAlpha() <= 0.0f || view4.getVisibility() != 0) {
                                            break;
                                        }
                                        parent = view4.getParent();
                                    } else if (parent != null) {
                                        int i5 = Build.VERSION.SDK_INT;
                                        a.f15257a.setVisibleToUser(true);
                                    }
                                }
                            }
                        }
                    }
                    return a;
                } else {
                    throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
                }
            } else {
                throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
            }
        } else {
            C0354mx a3 = C0354mx.m14778a(AccessibilityNodeInfo.obtain(this.f15297c));
            C0340mj.m14701a(this.f15297c, a3);
            ArrayList arrayList = new ArrayList();
            mo2872a((List) arrayList);
            if (a3.f15257a.getChildCount() <= 0 || arrayList.size() <= 0) {
                int size = arrayList.size();
                for (int i6 = 0; i6 < size; i6++) {
                    View view5 = this.f15297c;
                    int intValue = ((Integer) arrayList.get(i6)).intValue();
                    int i7 = Build.VERSION.SDK_INT;
                    a3.f15257a.addChild(view5, intValue);
                }
                return a3;
            }
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
    }

    /* renamed from: b */
    public final void mo9464b(int i, int i2) {
        ViewParent parent;
        if (i != Integer.MIN_VALUE && this.f15296b.isEnabled() && (parent = this.f15297c.getParent()) != null) {
            parent.requestSendAccessibilityEvent(this.f15297c, mo9461a(i, i2));
        }
    }

    /* renamed from: a */
    public final void mo9462a(int i) {
        int i2 = this.f15300f;
        if (i2 != i) {
            this.f15300f = i;
            mo9464b(i, 128);
            mo9464b(i2, 256);
        }
    }
}
