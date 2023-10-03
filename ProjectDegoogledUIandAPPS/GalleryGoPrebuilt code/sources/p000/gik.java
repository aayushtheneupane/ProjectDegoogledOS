package p000;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import com.google.android.apps.photosgo.R;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.snackbar.SnackbarContentLayout;
import java.util.List;

/* renamed from: gik */
/* compiled from: PG */
public class gik {

    /* renamed from: a */
    public static final Handler f11413a = new Handler(Looper.getMainLooper(), new ghv());

    /* renamed from: b */
    public static final String f11414b = gik.class.getSimpleName();

    /* renamed from: o */
    private static final int[] f11415o = {R.attr.snackbarStyle};

    /* renamed from: c */
    public final ViewGroup f11416c;

    /* renamed from: d */
    public final Context f11417d;

    /* renamed from: e */
    public final gij f11418e;

    /* renamed from: f */
    public final gil f11419f;

    /* renamed from: g */
    public int f11420g;

    /* renamed from: h */
    public View f11421h;

    /* renamed from: i */
    public int f11422i;

    /* renamed from: j */
    public int f11423j;

    /* renamed from: k */
    public int f11424k;

    /* renamed from: l */
    public int f11425l;

    /* renamed from: m */
    public int f11426m;

    /* renamed from: n */
    public final gip f11427n = new ghz(this);

    /* renamed from: p */
    private final Runnable f11428p = new ghw(this);

    /* renamed from: q */
    private Rect f11429q;

    /* renamed from: r */
    private final AccessibilityManager f11430r;

    static {
        int i = Build.VERSION.SDK_INT;
        int i2 = Build.VERSION.SDK_INT;
    }

    /* renamed from: b */
    public int mo6714b() {
        throw null;
    }

    protected gik(ViewGroup viewGroup, View view, gil gil) {
        int i;
        if (view == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null content");
        } else if (gil != null) {
            this.f11416c = viewGroup;
            this.f11419f = gil;
            Context context = viewGroup.getContext();
            this.f11417d = context;
            gga.m10239a(context);
            LayoutInflater from = LayoutInflater.from(this.f11417d);
            TypedArray obtainStyledAttributes = this.f11417d.obtainStyledAttributes(f11415o);
            int resourceId = obtainStyledAttributes.getResourceId(0, -1);
            obtainStyledAttributes.recycle();
            if (resourceId != -1) {
                i = R.layout.mtrl_layout_snackbar;
            } else {
                i = R.layout.design_layout_snackbar;
            }
            gij gij = (gij) from.inflate(i, this.f11416c, false);
            this.f11418e = gij;
            float f = gij.f11409d;
            if (f != 1.0f) {
                SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) view;
                snackbarContentLayout.f5228b.setTextColor(ggf.m10243a(ggf.m10246a(view, (int) R.attr.colorSurface), snackbarContentLayout.f5228b.getCurrentTextColor(), f));
            }
            this.f11418e.addView(view);
            ViewGroup.LayoutParams layoutParams = this.f11418e.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                this.f11429q = new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
            }
            C0340mj.m14679F(this.f11418e);
            C0340mj.m14689a((View) this.f11418e, 1);
            C0340mj.m14680G(this.f11418e);
            C0340mj.m14699a((View) this.f11418e, (C0329lz) new ghx(this));
            C0340mj.m14698a((View) this.f11418e, (C0315ll) new ghy(this));
            this.f11430r = (AccessibilityManager) this.f11417d.getSystemService("accessibility");
        } else {
            throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo6713a(int i) {
        gir a = gir.m10379a();
        gip gip = this.f11427n;
        synchronized (a.f11439a) {
            if (a.mo6728c(gip)) {
                a.mo6725a(a.f11441c, i);
            } else if (a.mo6729d(gip)) {
                a.mo6725a(a.f11442d, i);
            }
        }
    }

    /* renamed from: a */
    public final ValueAnimator mo6711a(float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(gci.f10936a);
        ofFloat.addUpdateListener(new ghp(this));
        return ofFloat;
    }

    /* renamed from: e */
    public final int mo6717e() {
        int height = this.f11418e.getHeight();
        ViewGroup.LayoutParams layoutParams = this.f11418e.getLayoutParams();
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? height + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin : height;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public final void mo6720h() {
        gir a = gir.m10379a();
        gip gip = this.f11427n;
        synchronized (a.f11439a) {
            if (a.mo6728c(gip)) {
                a.f11441c = null;
                if (a.f11442d != null) {
                    a.mo6726b();
                }
            }
        }
        ViewParent parent = this.f11418e.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.f11418e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public final void mo6718f() {
        gir a = gir.m10379a();
        gip gip = this.f11427n;
        synchronized (a.f11439a) {
            if (a.mo6728c(gip)) {
                a.mo6724a(a.f11441c);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public final boolean mo6719g() {
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = this.f11430r.getEnabledAccessibilityServiceList(1);
        if (enabledAccessibilityServiceList == null || !enabledAccessibilityServiceList.isEmpty()) {
            return false;
        }
        return true;
    }

    /* renamed from: c */
    public final void mo6715c() {
        gir a = gir.m10379a();
        int b = mo6714b();
        gip gip = this.f11427n;
        synchronized (a.f11439a) {
            if (a.mo6728c(gip)) {
                giq giq = a.f11441c;
                giq.f11436b = b;
                a.f11440b.removeCallbacksAndMessages(giq);
                a.mo6724a(a.f11441c);
                return;
            }
            if (!a.mo6729d(gip)) {
                a.f11442d = new giq(b, gip);
            } else {
                a.f11442d.f11436b = b;
            }
            giq giq2 = a.f11441c;
            if (giq2 != null) {
                if (a.mo6725a(giq2, 4)) {
                    return;
                }
            }
            a.f11441c = null;
            a.mo6726b();
        }
    }

    /* renamed from: d */
    public final void mo6716d() {
        if (!mo6719g()) {
            this.f11418e.setVisibility(0);
            mo6718f();
            return;
        }
        this.f11418e.post(new gie(this));
    }

    /* renamed from: a */
    public final void mo6712a() {
        Rect rect;
        int i;
        ViewGroup.LayoutParams layoutParams = this.f11418e.getLayoutParams();
        if ((layoutParams instanceof ViewGroup.MarginLayoutParams) && (rect = this.f11429q) != null) {
            if (this.f11421h == null) {
                i = this.f11422i;
            } else {
                i = this.f11426m;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.bottomMargin = rect.bottom + i;
            marginLayoutParams.leftMargin = this.f11429q.left + this.f11423j;
            marginLayoutParams.rightMargin = this.f11429q.right + this.f11424k;
            this.f11418e.requestLayout();
            if (Build.VERSION.SDK_INT >= 29 && this.f11425l > 0) {
                ViewGroup.LayoutParams layoutParams2 = this.f11418e.getLayoutParams();
                if ((layoutParams2 instanceof abm) && (((abm) layoutParams2).f80a instanceof SwipeDismissBehavior)) {
                    this.f11418e.removeCallbacks(this.f11428p);
                    this.f11418e.post(this.f11428p);
                    return;
                }
                return;
            }
            return;
        }
        Log.w(f11414b, "Unable to update margins because layout params are not MarginLayoutParams");
    }
}
