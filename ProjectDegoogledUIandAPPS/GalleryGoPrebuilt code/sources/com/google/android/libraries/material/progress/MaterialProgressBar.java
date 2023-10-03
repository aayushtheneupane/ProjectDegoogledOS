package com.google.android.libraries.material.progress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.PowerManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.ProgressBar;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public class MaterialProgressBar extends ProgressBar {

    /* renamed from: a */
    private int f5108a;

    /* renamed from: b */
    private int f5109b;

    /* renamed from: c */
    private int f5110c;

    /* renamed from: d */
    private int f5111d;

    /* renamed from: e */
    private int f5112e;

    /* renamed from: f */
    private fhc f5113f;

    /* renamed from: g */
    private Drawable f5114g;

    static {
        MaterialProgressBar.class.getSimpleName();
    }

    private MaterialProgressBar(Context context) {
        super(context, (AttributeSet) null, 16842872);
        super.getProgress();
    }

    public MaterialProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.getProgress();
        m4967a(context, attributeSet, 0);
        m4970d();
    }

    public MaterialProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.getProgress();
        m4967a(context, attributeSet, i);
        m4970d();
    }

    /* renamed from: g */
    private final void m4973g() {
        if (m4972f() == 0 && isIndeterminate()) {
            Context context = getContext();
            int i = Build.VERSION.SDK_INT;
            if (ValueAnimator.areAnimatorsEnabled()) {
                int i2 = Build.VERSION.SDK_INT;
                if (!((PowerManager) context.getSystemService("power")).isPowerSaveMode()) {
                    if (mo3530b() != this.f5113f) {
                        this.f5114g.setVisible(false, false);
                        this.f5114g.setCallback((Drawable.Callback) null);
                        unscheduleDrawable(this.f5114g);
                        setIndeterminateDrawable(this.f5113f);
                        onSizeChanged(getWidth(), getHeight(), getWidth(), getHeight());
                    }
                }
            }
            if (mo3530b() != this.f5114g) {
                this.f5113f.mo5605a();
                this.f5113f.setCallback((Drawable.Callback) null);
                unscheduleDrawable(this.f5113f);
                setIndeterminateDrawable(this.f5114g);
                onSizeChanged(getWidth(), getHeight(), getWidth(), getHeight());
            }
        }
        Drawable b = mo3530b();
        if (b != null) {
            boolean a = mo3529a();
            if ((b instanceof fgv) && !a) {
                ((fgv) b).mo5605a();
            } else {
                b.setVisible(a, true);
            }
        }
    }

    /* renamed from: a */
    private static int m4966a(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 2;
        }
        StringBuilder sb = new StringBuilder(59);
        sb.append("Invalid attribute value for mtrlLinearGrowFrom: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    /* renamed from: f */
    private final int m4972f() {
        return isIndeterminate() ? this.f5108a : this.f5109b;
    }

    /* renamed from: b */
    public final Drawable mo3530b() {
        return isIndeterminate() ? getIndeterminateDrawable() : getProgressDrawable();
    }

    /* renamed from: a */
    private final void m4968a(TypedArray typedArray, boolean z) {
        int[] iArr;
        int i;
        int i2;
        int i3;
        int[] iArr2 = fhl.f9682a;
        int i4 = typedArray.getInt(7, 3);
        if (typedArray.hasValue(1)) {
            iArr = getResources().getIntArray(typedArray.getResourceId(1, -1));
        } else if (typedArray.hasValue(0)) {
            iArr = new int[]{typedArray.getColor(0, 0)};
        } else {
            iArr = null;
        }
        if (iArr == null) {
            iArr = getResources().getIntArray(R.array.material_google_colors);
        }
        if (i4 == 1) {
            i = getResources().getDimensionPixelSize(R.dimen.material_progress_circle_size_small);
        } else if (i4 == 2) {
            i = getResources().getDimensionPixelSize(R.dimen.material_progress_circle_size_medium);
        } else if (i4 == 3) {
            i = getResources().getDimensionPixelSize(R.dimen.material_progress_circle_size_large);
        } else {
            throw new IllegalStateException("Invalid progress circle size");
        }
        this.f5110c = i;
        if (i4 == 1) {
            i2 = getResources().getDimensionPixelSize(R.dimen.material_progress_circle_stroke_width_small);
        } else if (i4 == 2) {
            i2 = getResources().getDimensionPixelSize(R.dimen.material_progress_circle_stroke_width_medium);
        } else if (i4 == 3) {
            i2 = getResources().getDimensionPixelSize(R.dimen.material_progress_circle_stroke_width_large);
        } else {
            throw new IllegalStateException("Invalid progress circle size");
        }
        if (i4 == 1) {
            i3 = getResources().getDimensionPixelSize(R.dimen.material_progress_circle_inset_small);
        } else if (i4 == 2) {
            i3 = getResources().getDimensionPixelSize(R.dimen.material_progress_circle_inset_medium);
        } else if (i4 == 3) {
            i3 = getResources().getDimensionPixelSize(R.dimen.material_progress_circle_inset_large);
        } else {
            throw new IllegalStateException("Invalid progress circle size");
        }
        if (z) {
            this.f5114g = C0071co.m4660a(getContext(), (int) R.drawable.quantum_ic_sync_grey600_24);
            fhc fhc = new fhc(i2, i3, iArr);
            this.f5113f = fhc;
            setIndeterminateDrawable(fhc);
            return;
        }
        setProgressDrawable(new fgy(i2, i3, iArr[0]));
    }

    /* renamed from: b */
    private final void m4969b(TypedArray typedArray, boolean z) {
        int i;
        boolean z2;
        int[] iArr = fhl.f9682a;
        if (typedArray.hasValue(0)) {
            i = typedArray.getColor(0, -1);
        } else {
            i = getResources().getColor(R.color.quantum_googblue);
        }
        int color = typedArray.getColor(8, -1);
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(new int[]{16842803});
        float f = obtainStyledAttributes.getFloat(0, 0.2f);
        obtainStyledAttributes.recycle();
        int i2 = typedArray.getInt(6, 0);
        float f2 = color != -1 ? 1.0f : f;
        if (z) {
            int i3 = this.f5111d;
            if (this.f5108a == 2) {
                z2 = true;
            } else {
                z2 = false;
            }
            setIndeterminateDrawable(new fhj(i3, i, color, f2, z2, m4966a(i2)));
            return;
        }
        setProgressDrawable(new fhf(this.f5111d, i, color, f2, m4966a(i2)));
    }

    /* renamed from: d */
    private final void m4970d() {
        m4971e();
        if (isIndeterminate()) {
            getProgressDrawable().setVisible(false, false);
            getIndeterminateDrawable().setVisible(mo3529a(), true);
            return;
        }
        getProgressDrawable().setVisible(mo3529a(), true);
        getIndeterminateDrawable().setVisible(false, false);
    }

    /* renamed from: a */
    private final void m4967a(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, fhl.f9682a, i, 2131952419);
        this.f5111d = obtainStyledAttributes.getDimensionPixelSize(4, 0);
        this.f5112e = obtainStyledAttributes.getDimensionPixelSize(5, 0);
        int i2 = obtainStyledAttributes.getInt(3, 0);
        this.f5108a = i2;
        if (i2 == 0) {
            m4968a(obtainStyledAttributes, true);
        } else if (i2 == 1 || i2 == 2) {
            m4969b(obtainStyledAttributes, true);
        } else {
            throw new IllegalArgumentException("Invalid determinate progress style");
        }
        int i3 = obtainStyledAttributes.getInt(2, 1);
        this.f5109b = i3;
        if (i3 == 0) {
            m4968a(obtainStyledAttributes, false);
        } else if (i3 == 1) {
            m4969b(obtainStyledAttributes, false);
        } else {
            throw new IllegalArgumentException("Invalid determinate progress style");
        }
        obtainStyledAttributes.recycle();
    }

    /* renamed from: e */
    private final void m4971e() {
        if (!isIndeterminate()) {
            if (this.f5109b != 0) {
                setMinimumHeight(this.f5111d);
            } else {
                setMinimumHeight(0);
            }
        } else if (this.f5108a != 0) {
            setMinimumHeight(this.f5111d);
        } else {
            setMinimumHeight(0);
        }
    }

    /* access modifiers changed from: protected */
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mo3529a()) {
            mo3531c();
        }
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        if (mo3530b() instanceof fgv) {
            ((fgv) mo3530b()).mo5605a();
        }
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public final synchronized void onDraw(Canvas canvas) {
        int save = canvas.save();
        if (!(getPaddingLeft() == 0 && getPaddingTop() == 0)) {
            canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
        }
        if (!(getPaddingRight() == 0 && getPaddingBottom() == 0)) {
            canvas.clipRect(0, 0, getWidth() - (getPaddingLeft() + getPaddingRight()), getHeight() - (getPaddingTop() + getPaddingBottom()));
        }
        mo3530b().draw(canvas);
        canvas.restoreToCount(save);
    }

    /* access modifiers changed from: protected */
    public final synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (m4972f() == 0) {
            setMeasuredDimension(this.f5110c + getPaddingLeft() + getPaddingRight(), this.f5110c + getPaddingTop() + getPaddingBottom());
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int i3 = this.f5111d;
        int i4 = this.f5112e;
        setMeasuredDimension(measuredWidth, resolveSizeAndState(i3 + i4 + i4 + getPaddingTop() + getPaddingBottom(), i2, 0));
    }

    /* access modifiers changed from: protected */
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        if (m4972f() != 0) {
            int paddingLeft = i - (getPaddingLeft() + getPaddingRight());
            int paddingTop = i2 - (getPaddingTop() + getPaddingBottom());
            Drawable indeterminateDrawable = getIndeterminateDrawable();
            if (indeterminateDrawable != null) {
                indeterminateDrawable.setBounds(0, 0, paddingLeft, paddingTop);
            }
            Drawable progressDrawable = getProgressDrawable();
            if (progressDrawable != null) {
                progressDrawable.setBounds(0, 0, paddingLeft, paddingTop);
                return;
            }
            return;
        }
        super.onSizeChanged(i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        m4973g();
    }

    /* access modifiers changed from: protected */
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        m4973g();
    }

    public final synchronized void setIndeterminate(boolean z) {
        super.setIndeterminate(z);
        m4971e();
        Drawable b = mo3530b();
        if (b != null) {
            b.setVisible(mo3529a(), false);
        }
    }

    /* renamed from: a */
    public final boolean mo3529a() {
        if (!C0340mj.m14735z(this) || getWindowVisibility() != 0) {
            return false;
        }
        View view = this;
        while (view.getVisibility() == 0) {
            ViewParent parent = view.getParent();
            if (parent != null) {
                if (!(parent instanceof View)) {
                    return true;
                }
                view = (View) parent;
            } else if (getWindowVisibility() != 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    public final void mo3531c() {
        if (getVisibility() != 0) {
            setVisibility(0);
        } else if (mo3529a()) {
            mo3530b().setVisible(true, false);
        }
    }
}
