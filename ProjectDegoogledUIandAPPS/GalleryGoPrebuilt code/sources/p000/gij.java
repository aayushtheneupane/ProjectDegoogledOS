package p000;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import com.google.android.apps.photosgo.R;

/* renamed from: gij */
/* compiled from: PG */
public class gij extends FrameLayout {

    /* renamed from: e */
    private static final View.OnTouchListener f11405e = new gii();

    /* renamed from: a */
    public gih f11406a;

    /* renamed from: b */
    public gig f11407b;

    /* renamed from: c */
    public int f11408c;

    /* renamed from: d */
    public final float f11409d;

    /* renamed from: f */
    private final float f11410f;

    /* renamed from: g */
    private ColorStateList f11411g;

    /* renamed from: h */
    private PorterDuff.Mode f11412h;

    protected gij(Context context) {
        this(context, (AttributeSet) null);
    }

    protected gij(Context context, AttributeSet attributeSet) {
        super(gkl.m10444a(context, attributeSet, 0, 0), attributeSet);
        Context context2 = getContext();
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, gim.f11431a);
        if (obtainStyledAttributes.hasValue(6)) {
            C0340mj.m14688a((View) this, (float) obtainStyledAttributes.getDimensionPixelSize(6, 0));
        }
        this.f11408c = obtainStyledAttributes.getInt(2, 0);
        this.f11410f = obtainStyledAttributes.getFloat(3, 1.0f);
        setBackgroundTintList(gqb.m10615a(context2, obtainStyledAttributes, 4));
        setBackgroundTintMode(ggf.m10248a(obtainStyledAttributes.getInt(5, -1), PorterDuff.Mode.SRC_IN));
        this.f11409d = obtainStyledAttributes.getFloat(1, 1.0f);
        obtainStyledAttributes.recycle();
        setOnTouchListener(f11405e);
        setFocusable(true);
        if (getBackground() == null) {
            float dimension = getResources().getDimension(R.dimen.mtrl_snackbar_background_corner_radius);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadius(dimension);
            gradientDrawable.setColor(ggf.m10243a(ggf.m10246a((View) this, (int) R.attr.colorSurface), ggf.m10246a((View) this, (int) R.attr.colorOnSurface), this.f11410f));
            if (this.f11411g != null) {
                int i = Build.VERSION.SDK_INT;
                C0257jh.m14475a((Drawable) gradientDrawable, this.f11411g);
            } else {
                int i2 = Build.VERSION.SDK_INT;
            }
            C0340mj.m14694a((View) this, (Drawable) gradientDrawable);
        }
    }

    /* access modifiers changed from: protected */
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        gig gig = this.f11407b;
        if (gig != null && Build.VERSION.SDK_INT >= 29) {
            gib gib = (gib) gig;
            WindowInsets rootWindowInsets = gib.f11400a.f11418e.getRootWindowInsets();
            if (rootWindowInsets != null) {
                gib.f11400a.f11425l = rootWindowInsets.getMandatorySystemGestureInsets().bottom;
                gib.f11400a.mo6712a();
            }
        }
        C0340mj.m14724o(this);
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        boolean z;
        super.onDetachedFromWindow();
        gig gig = this.f11407b;
        if (gig != null) {
            gib gib = (gib) gig;
            gik gik = gib.f11400a;
            gir a = gir.m10379a();
            gip gip = gik.f11427n;
            synchronized (a.f11439a) {
                z = true;
                if (!a.mo6728c(gip) && !a.mo6729d(gip)) {
                    z = false;
                }
            }
            if (z) {
                gik.f11413a.post(new gia(gib));
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        gih gih = this.f11406a;
        if (gih != null) {
            gik gik = ((gic) gih).f11401a;
            gik.f11418e.f11406a = null;
            gik.mo6716d();
        }
    }

    public final void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public final void setBackgroundDrawable(Drawable drawable) {
        if (!(drawable == null || this.f11411g == null)) {
            drawable = drawable.mutate();
            int i = Build.VERSION.SDK_INT;
            C0257jh.m14475a(drawable, this.f11411g);
            C0257jh.m14476a(drawable, this.f11412h);
        }
        super.setBackgroundDrawable(drawable);
    }

    public final void setBackgroundTintList(ColorStateList colorStateList) {
        this.f11411g = colorStateList;
        if (getBackground() != null) {
            Drawable mutate = getBackground().mutate();
            int i = Build.VERSION.SDK_INT;
            C0257jh.m14475a(mutate, colorStateList);
            C0257jh.m14476a(mutate, this.f11412h);
            if (mutate != getBackground()) {
                super.setBackgroundDrawable(mutate);
            }
        }
    }

    public final void setBackgroundTintMode(PorterDuff.Mode mode) {
        this.f11412h = mode;
        if (getBackground() != null) {
            Drawable mutate = getBackground().mutate();
            int i = Build.VERSION.SDK_INT;
            C0257jh.m14476a(mutate, mode);
            if (mutate != getBackground()) {
                super.setBackgroundDrawable(mutate);
            }
        }
    }

    public final void setOnClickListener(View.OnClickListener onClickListener) {
        setOnTouchListener(onClickListener == null ? f11405e : null);
        super.setOnClickListener(onClickListener);
    }
}
