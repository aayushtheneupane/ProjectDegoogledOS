package android.support.design.button;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;

class MaterialButtonHelper {
    private static final boolean IS_LOLLIPOP = true;
    private GradientDrawable backgroundDrawableLollipop;
    private boolean backgroundOverwritten = false;
    private ColorStateList backgroundTint;
    private PorterDuff.Mode backgroundTintMode;
    private final Rect bounds = new Rect();
    private final Paint buttonStrokePaint = new Paint(1);
    private GradientDrawable colorableBackgroundDrawableCompat;
    private int cornerRadius;
    private int insetBottom;
    private int insetLeft;
    private int insetRight;
    private int insetTop;
    private GradientDrawable maskDrawableLollipop;
    private final MaterialButton materialButton;
    private final RectF rectF = new RectF();
    private ColorStateList rippleColor;
    private GradientDrawable rippleDrawableCompat;
    private ColorStateList strokeColor;
    private GradientDrawable strokeDrawableLollipop;
    private int strokeWidth;
    private Drawable tintableBackgroundDrawableCompat;
    private Drawable tintableRippleDrawableCompat;

    static {
        int i = Build.VERSION.SDK_INT;
    }

    public MaterialButtonHelper(MaterialButton materialButton2) {
        this.materialButton = materialButton2;
    }

    private void updateTintAndTintModeLollipop() {
        GradientDrawable gradientDrawable = this.backgroundDrawableLollipop;
        if (gradientDrawable != null) {
            ColorStateList colorStateList = this.backgroundTint;
            int i = Build.VERSION.SDK_INT;
            gradientDrawable.setTintList(colorStateList);
            PorterDuff.Mode mode = this.backgroundTintMode;
            if (mode != null) {
                GradientDrawable gradientDrawable2 = this.backgroundDrawableLollipop;
                int i2 = Build.VERSION.SDK_INT;
                gradientDrawable2.setTintMode(mode);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ColorStateList getSupportBackgroundTintList() {
        return this.backgroundTint;
    }

    /* access modifiers changed from: package-private */
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return this.backgroundTintMode;
    }

    /* access modifiers changed from: package-private */
    public boolean isBackgroundOverwritten() {
        return this.backgroundOverwritten;
    }

    /* JADX WARNING: type inference failed for: r3v11, types: [android.support.design.button.MaterialButtonBackgroundDrawable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadFromAttributes(android.content.res.TypedArray r21) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            int r2 = android.support.design.R$styleable.MaterialButton_android_insetLeft
            r3 = 0
            int r2 = r1.getDimensionPixelOffset(r2, r3)
            r0.insetLeft = r2
            r2 = 1
            int r4 = r1.getDimensionPixelOffset(r2, r3)
            r0.insetRight = r4
            r4 = 2
            int r5 = r1.getDimensionPixelOffset(r4, r3)
            r0.insetTop = r5
            r5 = 3
            int r5 = r1.getDimensionPixelOffset(r5, r3)
            r0.insetBottom = r5
            r5 = 6
            int r5 = r1.getDimensionPixelSize(r5, r3)
            r0.cornerRadius = r5
            r5 = 15
            int r5 = r1.getDimensionPixelSize(r5, r3)
            r0.strokeWidth = r5
            r5 = -1
            r6 = 5
            int r6 = r1.getInt(r6, r5)
            android.graphics.PorterDuff$Mode r7 = android.graphics.PorterDuff.Mode.SRC_IN
            android.graphics.PorterDuff$Mode r6 = android.support.design.R$dimen.parseTintMode(r6, r7)
            r0.backgroundTintMode = r6
            android.support.design.button.MaterialButton r6 = r0.materialButton
            android.content.Context r6 = r6.getContext()
            r7 = 4
            android.content.res.ColorStateList r6 = android.support.design.R$dimen.getColorStateList(r6, r1, r7)
            r0.backgroundTint = r6
            android.support.design.button.MaterialButton r6 = r0.materialButton
            android.content.Context r6 = r6.getContext()
            r7 = 14
            android.content.res.ColorStateList r6 = android.support.design.R$dimen.getColorStateList(r6, r1, r7)
            r0.strokeColor = r6
            android.support.design.button.MaterialButton r6 = r0.materialButton
            android.content.Context r6 = r6.getContext()
            r7 = 13
            android.content.res.ColorStateList r1 = android.support.design.R$dimen.getColorStateList(r6, r1, r7)
            r0.rippleColor = r1
            android.graphics.Paint r1 = r0.buttonStrokePaint
            android.graphics.Paint$Style r6 = android.graphics.Paint.Style.STROKE
            r1.setStyle(r6)
            android.graphics.Paint r1 = r0.buttonStrokePaint
            int r6 = r0.strokeWidth
            float r6 = (float) r6
            r1.setStrokeWidth(r6)
            android.graphics.Paint r1 = r0.buttonStrokePaint
            android.content.res.ColorStateList r6 = r0.strokeColor
            if (r6 == 0) goto L_0x0088
            android.support.design.button.MaterialButton r7 = r0.materialButton
            int[] r7 = r7.getDrawableState()
            int r6 = r6.getColorForState(r7, r3)
            goto L_0x0089
        L_0x0088:
            r6 = r3
        L_0x0089:
            r1.setColor(r6)
            android.support.design.button.MaterialButton r1 = r0.materialButton
            int r1 = android.support.p000v4.view.ViewCompat.getPaddingStart(r1)
            android.support.design.button.MaterialButton r6 = r0.materialButton
            int r6 = r6.getPaddingTop()
            android.support.design.button.MaterialButton r7 = r0.materialButton
            int r7 = android.support.p000v4.view.ViewCompat.getPaddingEnd(r7)
            android.support.design.button.MaterialButton r8 = r0.materialButton
            int r8 = r8.getPaddingBottom()
            android.support.design.button.MaterialButton r9 = r0.materialButton
            boolean r10 = IS_LOLLIPOP
            r11 = 925353388(0x3727c5ac, float:1.0E-5)
            if (r10 == 0) goto L_0x012c
            android.graphics.drawable.GradientDrawable r10 = new android.graphics.drawable.GradientDrawable
            r10.<init>()
            r0.backgroundDrawableLollipop = r10
            android.graphics.drawable.GradientDrawable r10 = r0.backgroundDrawableLollipop
            int r12 = r0.cornerRadius
            float r12 = (float) r12
            float r12 = r12 + r11
            r10.setCornerRadius(r12)
            android.graphics.drawable.GradientDrawable r10 = r0.backgroundDrawableLollipop
            r10.setColor(r5)
            r20.updateTintAndTintModeLollipop()
            android.graphics.drawable.GradientDrawable r10 = new android.graphics.drawable.GradientDrawable
            r10.<init>()
            r0.strokeDrawableLollipop = r10
            android.graphics.drawable.GradientDrawable r10 = r0.strokeDrawableLollipop
            int r12 = r0.cornerRadius
            float r12 = (float) r12
            float r12 = r12 + r11
            r10.setCornerRadius(r12)
            android.graphics.drawable.GradientDrawable r10 = r0.strokeDrawableLollipop
            r10.setColor(r3)
            android.graphics.drawable.GradientDrawable r10 = r0.strokeDrawableLollipop
            int r12 = r0.strokeWidth
            android.content.res.ColorStateList r13 = r0.strokeColor
            r10.setStroke(r12, r13)
            android.graphics.drawable.LayerDrawable r15 = new android.graphics.drawable.LayerDrawable
            android.graphics.drawable.Drawable[] r4 = new android.graphics.drawable.Drawable[r4]
            android.graphics.drawable.GradientDrawable r10 = r0.backgroundDrawableLollipop
            r4[r3] = r10
            android.graphics.drawable.GradientDrawable r3 = r0.strokeDrawableLollipop
            r4[r2] = r3
            r15.<init>(r4)
            android.graphics.drawable.InsetDrawable r2 = new android.graphics.drawable.InsetDrawable
            int r3 = r0.insetLeft
            int r4 = r0.insetTop
            int r10 = r0.insetRight
            int r12 = r0.insetBottom
            r14 = r2
            r16 = r3
            r17 = r4
            r18 = r10
            r19 = r12
            r14.<init>(r15, r16, r17, r18, r19)
            android.graphics.drawable.GradientDrawable r3 = new android.graphics.drawable.GradientDrawable
            r3.<init>()
            r0.maskDrawableLollipop = r3
            android.graphics.drawable.GradientDrawable r3 = r0.maskDrawableLollipop
            int r4 = r0.cornerRadius
            float r4 = (float) r4
            float r4 = r4 + r11
            r3.setCornerRadius(r4)
            android.graphics.drawable.GradientDrawable r3 = r0.maskDrawableLollipop
            r3.setColor(r5)
            android.support.design.button.MaterialButtonBackgroundDrawable r3 = new android.support.design.button.MaterialButtonBackgroundDrawable
            android.content.res.ColorStateList r4 = r0.rippleColor
            android.content.res.ColorStateList r4 = android.support.design.ripple.RippleUtils.convertToRippleDrawableColor(r4)
            android.graphics.drawable.GradientDrawable r5 = r0.maskDrawableLollipop
            r3.<init>(r4, r2, r5)
            r2 = r3
            goto L_0x019a
        L_0x012c:
            android.graphics.drawable.GradientDrawable r10 = new android.graphics.drawable.GradientDrawable
            r10.<init>()
            r0.colorableBackgroundDrawableCompat = r10
            android.graphics.drawable.GradientDrawable r10 = r0.colorableBackgroundDrawableCompat
            int r12 = r0.cornerRadius
            float r12 = (float) r12
            float r12 = r12 + r11
            r10.setCornerRadius(r12)
            android.graphics.drawable.GradientDrawable r10 = r0.colorableBackgroundDrawableCompat
            r10.setColor(r5)
            android.graphics.drawable.GradientDrawable r10 = r0.colorableBackgroundDrawableCompat
            int r12 = android.os.Build.VERSION.SDK_INT
            r0.tintableBackgroundDrawableCompat = r10
            android.graphics.drawable.Drawable r10 = r0.tintableBackgroundDrawableCompat
            android.content.res.ColorStateList r12 = r0.backgroundTint
            r10.setTintList(r12)
            android.graphics.PorterDuff$Mode r10 = r0.backgroundTintMode
            if (r10 == 0) goto L_0x0159
            android.graphics.drawable.Drawable r12 = r0.tintableBackgroundDrawableCompat
            int r13 = android.os.Build.VERSION.SDK_INT
            r12.setTintMode(r10)
        L_0x0159:
            android.graphics.drawable.GradientDrawable r10 = new android.graphics.drawable.GradientDrawable
            r10.<init>()
            r0.rippleDrawableCompat = r10
            android.graphics.drawable.GradientDrawable r10 = r0.rippleDrawableCompat
            int r12 = r0.cornerRadius
            float r12 = (float) r12
            float r12 = r12 + r11
            r10.setCornerRadius(r12)
            android.graphics.drawable.GradientDrawable r10 = r0.rippleDrawableCompat
            r10.setColor(r5)
            android.graphics.drawable.GradientDrawable r5 = r0.rippleDrawableCompat
            int r10 = android.os.Build.VERSION.SDK_INT
            r0.tintableRippleDrawableCompat = r5
            android.graphics.drawable.Drawable r5 = r0.tintableRippleDrawableCompat
            android.content.res.ColorStateList r10 = r0.rippleColor
            r5.setTintList(r10)
            android.graphics.drawable.LayerDrawable r12 = new android.graphics.drawable.LayerDrawable
            android.graphics.drawable.Drawable[] r4 = new android.graphics.drawable.Drawable[r4]
            android.graphics.drawable.Drawable r5 = r0.tintableBackgroundDrawableCompat
            r4[r3] = r5
            android.graphics.drawable.Drawable r3 = r0.tintableRippleDrawableCompat
            r4[r2] = r3
            r12.<init>(r4)
            android.graphics.drawable.InsetDrawable r2 = new android.graphics.drawable.InsetDrawable
            int r13 = r0.insetLeft
            int r14 = r0.insetTop
            int r15 = r0.insetRight
            int r3 = r0.insetBottom
            r11 = r2
            r16 = r3
            r11.<init>(r12, r13, r14, r15, r16)
        L_0x019a:
            r9.setInternalBackground(r2)
            android.support.design.button.MaterialButton r2 = r0.materialButton
            int r3 = r0.insetLeft
            int r1 = r1 + r3
            int r3 = r0.insetTop
            int r6 = r6 + r3
            int r3 = r0.insetRight
            int r7 = r7 + r3
            int r0 = r0.insetBottom
            int r8 = r8 + r0
            int r0 = android.os.Build.VERSION.SDK_INT
            r2.setPaddingRelative(r1, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.button.MaterialButtonHelper.loadFromAttributes(android.content.res.TypedArray):void");
    }

    /* access modifiers changed from: package-private */
    public void setBackgroundColor(int i) {
        GradientDrawable gradientDrawable;
        GradientDrawable gradientDrawable2;
        if (IS_LOLLIPOP && (gradientDrawable2 = this.backgroundDrawableLollipop) != null) {
            gradientDrawable2.setColor(i);
        } else if (!IS_LOLLIPOP && (gradientDrawable = this.colorableBackgroundDrawableCompat) != null) {
            gradientDrawable.setColor(i);
        }
    }

    /* access modifiers changed from: package-private */
    public void setBackgroundOverwritten() {
        this.backgroundOverwritten = true;
        this.materialButton.setSupportBackgroundTintList(this.backgroundTint);
        this.materialButton.setSupportBackgroundTintMode(this.backgroundTintMode);
    }

    /* access modifiers changed from: package-private */
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (this.backgroundTint != colorStateList) {
            this.backgroundTint = colorStateList;
            if (IS_LOLLIPOP) {
                updateTintAndTintModeLollipop();
                return;
            }
            Drawable drawable = this.tintableBackgroundDrawableCompat;
            if (drawable != null) {
                ColorStateList colorStateList2 = this.backgroundTint;
                int i = Build.VERSION.SDK_INT;
                drawable.setTintList(colorStateList2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        PorterDuff.Mode mode2;
        if (this.backgroundTintMode != mode) {
            this.backgroundTintMode = mode;
            if (IS_LOLLIPOP) {
                updateTintAndTintModeLollipop();
                return;
            }
            Drawable drawable = this.tintableBackgroundDrawableCompat;
            if (drawable != null && (mode2 = this.backgroundTintMode) != null) {
                int i = Build.VERSION.SDK_INT;
                drawable.setTintMode(mode2);
            }
        }
    }
}
