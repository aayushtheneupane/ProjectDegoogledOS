package com.android.messaging.p041ui.p042a;

import android.animation.TypeEvaluator;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.PopupWindow;
import com.android.messaging.R;
import com.android.messaging.util.C1480va;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.a.f */
public class C1079f extends Animation {
    /* access modifiers changed from: private */

    /* renamed from: al */
    public final View f1698al;

    /* renamed from: bl */
    private final Rect f1699bl;
    /* access modifiers changed from: private */

    /* renamed from: cl */
    public Rect f1700cl;
    /* access modifiers changed from: private */

    /* renamed from: dl */
    public final Rect f1701dl;
    /* access modifiers changed from: private */

    /* renamed from: el */
    public final Rect f1702el;
    /* access modifiers changed from: private */

    /* renamed from: fl */
    public final Rect f1703fl;

    /* renamed from: gl */
    private final TypeEvaluator f1704gl;

    /* renamed from: hl */
    private View f1705hl;
    /* access modifiers changed from: private */

    /* renamed from: il */
    public Runnable f1706il;
    /* access modifiers changed from: private */

    /* renamed from: jl */
    public Runnable f1707jl;
    /* access modifiers changed from: private */

    /* renamed from: kl */
    public final Runnable f1708kl = new C1075b(this);
    private final View mActionBarView;
    /* access modifiers changed from: private */
    public final StringBuilder mEvents = new StringBuilder();
    /* access modifiers changed from: private */
    public PopupWindow mPopupWindow;

    public C1079f(Rect rect, View view) {
        this.f1698al = view;
        this.f1699bl = rect;
        this.f1700cl = new Rect(this.f1699bl);
        this.f1701dl = new Rect();
        this.f1702el = new Rect();
        this.f1703fl = new Rect();
        this.mActionBarView = view.getRootView().findViewById(R.id.action_bar);
        this.f1704gl = C1080g.create();
        setDuration((long) C1486ya.f2354YK);
        setInterpolator(C1486ya.f2357aL);
        setAnimationListener(new C1074a(this));
    }

    /* access modifiers changed from: private */
    public void dismiss() {
        this.mEvents.append("d,");
        this.f1698al.setAlpha(1.0f);
        this.f1698al.setVisibility(0);
        C1480va.getMainThreadHandler().post(new C1077d(this));
    }

    /* access modifiers changed from: protected */
    public void applyTransformation(float f, Transformation transformation) {
        if (this.mPopupWindow == null) {
            this.f1705hl = new C1078e(this, this.f1698al.getContext());
            this.mPopupWindow = new PopupWindow(this.f1698al.getContext());
            this.mPopupWindow.setBackgroundDrawable((Drawable) null);
            this.mPopupWindow.setContentView(this.f1705hl);
            this.mPopupWindow.setWidth(-1);
            this.mPopupWindow.setHeight(-1);
            this.mPopupWindow.setTouchable(false);
            this.mPopupWindow.showAtLocation(this.f1698al, 48, 0, 1);
        }
        this.f1702el.set(C1486ya.m3858h(this.f1705hl));
        this.f1703fl.set(C1486ya.m3858h(this.mActionBarView));
        Rect rect = this.f1701dl;
        int i = rect.top;
        int i2 = rect.left;
        int i3 = rect.right;
        int i4 = rect.bottom;
        rect.set(C1486ya.m3858h(this.f1698al));
        if (!(!rect.isEmpty())) {
            Rect rect2 = this.f1701dl;
            rect2.top = i;
            rect2.left = i2;
            rect2.bottom = i4;
            rect2.right = i3;
        }
        this.f1700cl = (Rect) this.f1704gl.evaluate(f, this.f1699bl, this.f1701dl);
        this.f1705hl.invalidate();
        if (((double) f) >= 0.98d) {
            StringBuilder sb = this.mEvents;
            sb.append("aT");
            sb.append(f);
            sb.append(',');
        }
        if (f == 1.0f) {
            dismiss();
        }
    }

    /* renamed from: dc */
    public void mo7127dc() {
        this.f1698al.setVisibility(4);
        this.f1698al.setAlpha(0.0f);
        new C1076c(this).run();
    }

    public boolean willChangeBounds() {
        return false;
    }

    /* renamed from: c */
    public C1079f mo7125c(Runnable runnable) {
        this.f1706il = runnable;
        return this;
    }

    /* renamed from: d */
    public C1079f mo7126d(Runnable runnable) {
        this.f1707jl = runnable;
        return this;
    }
}
