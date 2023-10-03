package android.support.p002v7.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;

/* renamed from: android.support.v7.widget.SwitchCompat */
/* compiled from: PG */
public class SwitchCompat extends CompoundButton {

    /* renamed from: O */
    private static final int[] f958O = {16842912};

    /* renamed from: d */
    private static final Property f959d = new C0677yw(Float.class, "thumbPos");

    /* renamed from: A */
    private int f960A;

    /* renamed from: B */
    private int f961B;

    /* renamed from: C */
    private int f962C;

    /* renamed from: D */
    private int f963D;

    /* renamed from: E */
    private int f964E;

    /* renamed from: F */
    private int f965F;

    /* renamed from: G */
    private final TextPaint f966G;

    /* renamed from: H */
    private ColorStateList f967H;

    /* renamed from: I */
    private Layout f968I;

    /* renamed from: J */
    private Layout f969J;

    /* renamed from: K */
    private TransformationMethod f970K;

    /* renamed from: L */
    private ObjectAnimator f971L;

    /* renamed from: M */
    private final C0557uk f972M;

    /* renamed from: N */
    private final Rect f973N;

    /* renamed from: a */
    public CharSequence f974a;

    /* renamed from: b */
    public CharSequence f975b;

    /* renamed from: c */
    public float f976c;

    /* renamed from: e */
    private Drawable f977e;

    /* renamed from: f */
    private ColorStateList f978f;

    /* renamed from: g */
    private PorterDuff.Mode f979g;

    /* renamed from: h */
    private boolean f980h;

    /* renamed from: i */
    private boolean f981i;

    /* renamed from: j */
    private Drawable f982j;

    /* renamed from: k */
    private ColorStateList f983k;

    /* renamed from: l */
    private PorterDuff.Mode f984l;

    /* renamed from: m */
    private boolean f985m;

    /* renamed from: n */
    private boolean f986n;

    /* renamed from: o */
    private int f987o;

    /* renamed from: p */
    private int f988p;

    /* renamed from: q */
    private int f989q;

    /* renamed from: r */
    private boolean f990r;

    /* renamed from: s */
    private boolean f991s;

    /* renamed from: t */
    private int f992t;

    /* renamed from: u */
    private int f993u;

    /* renamed from: v */
    private float f994v;

    /* renamed from: w */
    private float f995w;

    /* renamed from: x */
    private VelocityTracker f996x;

    /* renamed from: y */
    private int f997y;

    /* renamed from: z */
    private int f998z;

    /* renamed from: a */
    private final boolean m913a() {
        return this.f976c > 0.5f;
    }

    public SwitchCompat(Context context) {
        this(context, (AttributeSet) null);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.switchStyle);
    }

    public SwitchCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Typeface typeface;
        Typeface typeface2;
        int i2;
        Drawable drawable;
        Drawable drawable2;
        this.f978f = null;
        this.f979g = null;
        this.f980h = false;
        this.f981i = false;
        this.f983k = null;
        this.f984l = null;
        this.f985m = false;
        this.f986n = false;
        this.f996x = VelocityTracker.obtain();
        this.f973N = new Rect();
        boolean z = true;
        this.f966G = new TextPaint(1);
        this.f966G.density = getResources().getDisplayMetrics().density;
        C0684zc a = C0684zc.m16192a(context, attributeSet, C0435px.f15594v, i, 0);
        Drawable a2 = a.mo10723a(2);
        this.f977e = a2;
        if (a2 != null) {
            a2.setCallback(this);
        }
        Drawable a3 = a.mo10723a(11);
        this.f982j = a3;
        if (a3 != null) {
            a3.setCallback(this);
        }
        this.f974a = a.mo10729c(0);
        this.f975b = a.mo10729c(1);
        this.f991s = a.mo10725a(3, true);
        this.f987o = a.mo10730d(8, 0);
        this.f988p = a.mo10730d(5, 0);
        this.f989q = a.mo10730d(6, 0);
        this.f990r = a.mo10725a(4, false);
        ColorStateList e = a.mo10733e(9);
        if (e != null) {
            this.f978f = e;
            this.f980h = true;
        }
        PorterDuff.Mode a4 = C0579vf.m15603a(a.mo10722a(10, -1), (PorterDuff.Mode) null);
        if (this.f979g != a4) {
            this.f979g = a4;
            this.f981i = true;
        }
        boolean z2 = this.f980h;
        if ((z2 || this.f981i) && (drawable2 = this.f977e) != null && (z2 || this.f981i)) {
            int i3 = Build.VERSION.SDK_INT;
            Drawable mutate = drawable2.mutate();
            this.f977e = mutate;
            if (this.f980h) {
                C0257jh.m14475a(mutate, this.f978f);
            }
            if (this.f981i) {
                C0257jh.m14476a(this.f977e, this.f979g);
            }
            if (this.f977e.isStateful()) {
                this.f977e.setState(getDrawableState());
            }
        }
        ColorStateList e2 = a.mo10733e(12);
        if (e2 != null) {
            this.f983k = e2;
            this.f985m = true;
        }
        PorterDuff.Mode a5 = C0579vf.m15603a(a.mo10722a(13, -1), (PorterDuff.Mode) null);
        if (this.f984l != a5) {
            this.f984l = a5;
            this.f986n = true;
        }
        boolean z3 = this.f985m;
        if ((z3 || this.f986n) && (drawable = this.f982j) != null && (z3 || this.f986n)) {
            int i4 = Build.VERSION.SDK_INT;
            Drawable mutate2 = drawable.mutate();
            this.f982j = mutate2;
            if (this.f985m) {
                C0257jh.m14475a(mutate2, this.f983k);
            }
            if (this.f986n) {
                C0257jh.m14476a(this.f982j, this.f984l);
            }
            if (this.f982j.isStateful()) {
                this.f982j.setState(getDrawableState());
            }
        }
        int f = a.mo10734f(7, 0);
        if (f != 0) {
            C0684zc a6 = C0684zc.m16190a(context, f, C0435px.f15595w);
            ColorStateList e3 = a6.mo10733e(3);
            if (e3 != null) {
                this.f967H = e3;
            } else {
                this.f967H = getTextColors();
            }
            int d = a6.mo10730d(0, 0);
            if (d != 0) {
                float f2 = (float) d;
                if (f2 != this.f966G.getTextSize()) {
                    this.f966G.setTextSize(f2);
                    requestLayout();
                }
            }
            int a7 = a6.mo10722a(1, -1);
            int a8 = a6.mo10722a(2, -1);
            if (a7 == 1) {
                typeface = Typeface.SANS_SERIF;
            } else if (a7 != 2) {
                typeface = a7 != 3 ? null : Typeface.MONOSPACE;
            } else {
                typeface = Typeface.SERIF;
            }
            float f3 = 0.0f;
            if (a8 <= 0) {
                this.f966G.setFakeBoldText(false);
                this.f966G.setTextSkewX(0.0f);
                m912a(typeface);
            } else {
                if (typeface != null) {
                    typeface2 = Typeface.create(typeface, a8);
                } else {
                    typeface2 = Typeface.defaultFromStyle(a8);
                }
                m912a(typeface2);
                if (typeface2 != null) {
                    i2 = typeface2.getStyle();
                } else {
                    i2 = 0;
                }
                int i5 = (i2 ^ -1) & a8;
                this.f966G.setFakeBoldText((i5 & 1) == 0 ? false : z);
                this.f966G.setTextSkewX((2 & i5) != 0 ? -0.25f : f3);
            }
            if (a6.mo10725a(14, false)) {
                this.f970K = new C0440qb(getContext());
            } else {
                this.f970K = null;
            }
            a6.mo10724a();
        }
        C0557uk ukVar = new C0557uk(this);
        this.f972M = ukVar;
        ukVar.mo10246a(attributeSet, i);
        a.mo10724a();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f993u = viewConfiguration.getScaledTouchSlop();
        this.f997y = viewConfiguration.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
    }

    public final void draw(Canvas canvas) {
        Rect rect;
        int i;
        int i2;
        Rect rect2 = this.f973N;
        int i3 = this.f962C;
        int i4 = this.f963D;
        int i5 = this.f964E;
        int i6 = this.f965F;
        int b = m914b() + i3;
        Drawable drawable = this.f977e;
        if (drawable == null) {
            rect = C0579vf.f16087a;
        } else {
            rect = C0579vf.m15604a(drawable);
        }
        Drawable drawable2 = this.f982j;
        if (drawable2 != null) {
            drawable2.getPadding(rect2);
            b += rect2.left;
            if (rect == null) {
                i = i4;
            } else {
                if (rect.left > rect2.left) {
                    i3 += rect.left - rect2.left;
                }
                i = rect.top > rect2.top ? (rect.top - rect2.top) + i4 : i4;
                if (rect.right > rect2.right) {
                    i5 -= rect.right - rect2.right;
                }
                if (rect.bottom > rect2.bottom) {
                    i2 = i6 - (rect.bottom - rect2.bottom);
                    this.f982j.setBounds(i3, i, i5, i2);
                }
            }
            i2 = i6;
            this.f982j.setBounds(i3, i, i5, i2);
        }
        Drawable drawable3 = this.f977e;
        if (drawable3 != null) {
            drawable3.getPadding(rect2);
            int i7 = b - rect2.left;
            int i8 = b + this.f961B + rect2.right;
            this.f977e.setBounds(i7, i4, i8, i6);
            Drawable background = getBackground();
            if (background != null) {
                C0257jh.m14474a(background, i7, i4, i8, i6);
            }
        }
        super.draw(canvas);
    }

    public final void drawableHotspotChanged(float f, float f2) {
        int i = Build.VERSION.SDK_INT;
        super.drawableHotspotChanged(f, f2);
        Drawable drawable = this.f977e;
        if (drawable != null) {
            C0257jh.m14472a(drawable, f, f2);
        }
        Drawable drawable2 = this.f982j;
        if (drawable2 != null) {
            C0257jh.m14472a(drawable2, f, f2);
        }
    }

    /* access modifiers changed from: protected */
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f977e;
        boolean z = false;
        if (drawable != null && drawable.isStateful()) {
            z = drawable.setState(drawableState);
        }
        Drawable drawable2 = this.f982j;
        if (drawable2 != null && drawable2.isStateful()) {
            z |= drawable2.setState(drawableState);
        }
        if (z) {
            invalidate();
        }
    }

    public final int getCompoundPaddingLeft() {
        if (!C0703zv.m16280a(this)) {
            return super.getCompoundPaddingLeft();
        }
        int compoundPaddingLeft = super.getCompoundPaddingLeft() + this.f998z;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingLeft + this.f989q : compoundPaddingLeft;
    }

    public final int getCompoundPaddingRight() {
        if (C0703zv.m16280a(this)) {
            return super.getCompoundPaddingRight();
        }
        int compoundPaddingRight = super.getCompoundPaddingRight() + this.f998z;
        return !TextUtils.isEmpty(getText()) ? compoundPaddingRight + this.f989q : compoundPaddingRight;
    }

    /* renamed from: b */
    private final int m914b() {
        float f;
        if (C0703zv.m16280a(this)) {
            f = 1.0f - this.f976c;
        } else {
            f = this.f976c;
        }
        return (int) ((f * ((float) m915c())) + 0.5f);
    }

    /* renamed from: c */
    private final int m915c() {
        Rect rect;
        Drawable drawable = this.f982j;
        if (drawable == null) {
            return 0;
        }
        Rect rect2 = this.f973N;
        drawable.getPadding(rect2);
        Drawable drawable2 = this.f977e;
        if (drawable2 == null) {
            rect = C0579vf.f16087a;
        } else {
            rect = C0579vf.m15604a(drawable2);
        }
        return ((((this.f998z - this.f961B) - rect2.left) - rect2.right) - rect.left) - rect.right;
    }

    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f977e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f982j;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        ObjectAnimator objectAnimator = this.f971L;
        if (objectAnimator != null && objectAnimator.isStarted()) {
            this.f971L.end();
            this.f971L = null;
        }
    }

    /* renamed from: a */
    private final Layout m911a(CharSequence charSequence) {
        TransformationMethod transformationMethod = this.f970K;
        CharSequence transformation = transformationMethod == null ? charSequence : transformationMethod.getTransformation(charSequence, this);
        TextPaint textPaint = this.f966G;
        return new StaticLayout(transformation, textPaint, transformation != null ? (int) Math.ceil((double) Layout.getDesiredWidth(transformation, textPaint)) : 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    /* access modifiers changed from: protected */
    public final int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (isChecked()) {
            mergeDrawableStates(onCreateDrawableState, f958O);
        }
        return onCreateDrawableState;
    }

    /* access modifiers changed from: protected */
    public final void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        Rect rect = this.f973N;
        Drawable drawable = this.f982j;
        if (drawable != null) {
            drawable.getPadding(rect);
        } else {
            rect.setEmpty();
        }
        int i2 = this.f963D;
        int i3 = this.f965F;
        int i4 = i2 + rect.top;
        int i5 = i3 - rect.bottom;
        Drawable drawable2 = this.f977e;
        if (drawable != null) {
            if (this.f990r && drawable2 != null) {
                Rect a = C0579vf.m15604a(drawable2);
                drawable2.copyBounds(rect);
                rect.left += a.left;
                rect.right -= a.right;
                int save = canvas.save();
                canvas.clipRect(rect, Region.Op.DIFFERENCE);
                drawable.draw(canvas);
                canvas.restoreToCount(save);
            } else {
                drawable.draw(canvas);
            }
        }
        int save2 = canvas.save();
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
        Layout layout = !m913a() ? this.f969J : this.f968I;
        if (layout != null) {
            int[] drawableState = getDrawableState();
            ColorStateList colorStateList = this.f967H;
            if (colorStateList != null) {
                this.f966G.setColor(colorStateList.getColorForState(drawableState, 0));
            }
            this.f966G.drawableState = drawableState;
            if (drawable2 != null) {
                Rect bounds = drawable2.getBounds();
                i = bounds.left + bounds.right;
            } else {
                i = getWidth();
            }
            canvas.translate((float) ((i / 2) - (layout.getWidth() / 2)), (float) (((i4 + i5) / 2) - (layout.getHeight() / 2)));
            layout.draw(canvas);
        }
        canvas.restoreToCount(save2);
    }

    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("android.widget.Switch");
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        CharSequence charSequence;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("android.widget.Switch");
        if (!isChecked()) {
            charSequence = this.f975b;
        } else {
            charSequence = this.f974a;
        }
        if (!TextUtils.isEmpty(charSequence)) {
            CharSequence text = accessibilityNodeInfo.getText();
            if (!TextUtils.isEmpty(text)) {
                StringBuilder sb = new StringBuilder();
                sb.append(text);
                sb.append(' ');
                sb.append(charSequence);
                accessibilityNodeInfo.setText(sb);
                return;
            }
            accessibilityNodeInfo.setText(charSequence);
        }
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        super.onLayout(z, i, i2, i3, i4);
        int i10 = 0;
        if (this.f977e != null) {
            Rect rect = this.f973N;
            Drawable drawable = this.f982j;
            if (drawable == null) {
                rect.setEmpty();
            } else {
                drawable.getPadding(rect);
            }
            Rect a = C0579vf.m15604a(this.f977e);
            i5 = Math.max(0, a.left - rect.left);
            i10 = Math.max(0, a.right - rect.right);
        } else {
            i5 = 0;
        }
        if (C0703zv.m16280a(this)) {
            i7 = getPaddingLeft() + i5;
            i6 = ((this.f998z + i7) - i5) - i10;
        } else {
            i6 = (getWidth() - getPaddingRight()) - i10;
            i7 = (i6 - this.f998z) + i5 + i10;
        }
        int gravity = getGravity() & 112;
        if (gravity == 16) {
            int paddingTop = getPaddingTop();
            int height = getHeight();
            int paddingBottom = getPaddingBottom();
            int i11 = this.f960A;
            i9 = (((paddingTop + height) - paddingBottom) / 2) - (i11 / 2);
            i8 = i9 + i11;
        } else if (gravity != 80) {
            i9 = getPaddingTop();
            i8 = this.f960A + i9;
        } else {
            i8 = getHeight() - getPaddingBottom();
            i9 = i8 - this.f960A;
        }
        this.f962C = i7;
        this.f963D = i9;
        this.f965F = i8;
        this.f964E = i6;
    }

    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        if (this.f991s) {
            if (this.f968I == null) {
                this.f968I = m911a(this.f974a);
            }
            if (this.f969J == null) {
                this.f969J = m911a(this.f975b);
            }
        }
        Rect rect = this.f973N;
        Drawable drawable = this.f977e;
        int i6 = 0;
        if (drawable != null) {
            drawable.getPadding(rect);
            i4 = (this.f977e.getIntrinsicWidth() - rect.left) - rect.right;
            i3 = this.f977e.getIntrinsicHeight();
        } else {
            i4 = 0;
            i3 = 0;
        }
        if (this.f991s) {
            int max = Math.max(this.f968I.getWidth(), this.f969J.getWidth());
            int i7 = this.f987o;
            i5 = max + i7 + i7;
        } else {
            i5 = 0;
        }
        this.f961B = Math.max(i5, i4);
        Drawable drawable2 = this.f982j;
        if (drawable2 != null) {
            drawable2.getPadding(rect);
            i6 = this.f982j.getIntrinsicHeight();
        } else {
            rect.setEmpty();
        }
        int i8 = rect.left;
        int i9 = rect.right;
        Drawable drawable3 = this.f977e;
        if (drawable3 != null) {
            Rect a = C0579vf.m15604a(drawable3);
            i8 = Math.max(i8, a.left);
            i9 = Math.max(i9, a.right);
        }
        int i10 = this.f988p;
        int i11 = this.f961B;
        int max2 = Math.max(i10, i11 + i11 + i8 + i9);
        int max3 = Math.max(i6, i3);
        this.f998z = max2;
        this.f960A = max3;
        super.onMeasure(i, i2);
        if (getMeasuredHeight() < max3) {
            setMeasuredDimension(getMeasuredWidthAndState(), max3);
        }
    }

    public final void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        CharSequence charSequence;
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        if (!isChecked()) {
            charSequence = this.f975b;
        } else {
            charSequence = this.f974a;
        }
        if (charSequence != null) {
            accessibilityEvent.getText().add(charSequence);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        if (r0 != 3) goto L_0x0158;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouchEvent(android.view.MotionEvent r10) {
        /*
            r9 = this;
            android.view.VelocityTracker r0 = r9.f996x
            r0.addMovement(r10)
            int r0 = r10.getActionMasked()
            r1 = 1
            if (r0 == 0) goto L_0x0105
            r2 = 3
            r3 = 2
            r4 = 0
            if (r0 == r1) goto L_0x0092
            if (r0 == r3) goto L_0x0017
            if (r0 == r2) goto L_0x0092
            goto L_0x0158
        L_0x0017:
            int r0 = r9.f992t
            if (r0 == r1) goto L_0x005d
            if (r0 == r3) goto L_0x001f
            goto L_0x0158
        L_0x001f:
            float r10 = r10.getX()
            int r0 = r9.m915c()
            float r2 = r9.f994v
            float r2 = r10 - r2
            r3 = 1065353216(0x3f800000, float:1.0)
            if (r0 == 0) goto L_0x0032
            float r0 = (float) r0
            float r2 = r2 / r0
            goto L_0x003b
        L_0x0032:
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 > 0) goto L_0x0039
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            goto L_0x003b
        L_0x0039:
            r2 = 1065353216(0x3f800000, float:1.0)
        L_0x003b:
            boolean r0 = p000.C0703zv.m16280a(r9)
            if (r0 != 0) goto L_0x0042
            goto L_0x0043
        L_0x0042:
            float r2 = -r2
        L_0x0043:
            float r0 = r9.f976c
            float r2 = r2 + r0
            int r5 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r5 < 0) goto L_0x0053
            int r4 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r4 > 0) goto L_0x0050
            r4 = r2
            goto L_0x0053
        L_0x0050:
            r4 = 1065353216(0x3f800000, float:1.0)
        L_0x0053:
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 == 0) goto L_0x005c
            r9.f994v = r10
            r9.mo1063a((float) r4)
        L_0x005c:
            return r1
        L_0x005d:
            float r0 = r10.getX()
            float r2 = r10.getY()
            float r4 = r9.f994v
            float r4 = r0 - r4
            float r4 = java.lang.Math.abs(r4)
            int r5 = r9.f993u
            float r5 = (float) r5
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 > 0) goto L_0x0083
            float r4 = r9.f995w
            float r4 = r2 - r4
            float r4 = java.lang.Math.abs(r4)
            int r5 = r9.f993u
            float r5 = (float) r5
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L_0x0158
        L_0x0083:
            r9.f992t = r3
            android.view.ViewParent r10 = r9.getParent()
            r10.requestDisallowInterceptTouchEvent(r1)
            r9.f994v = r0
            r9.f995w = r2
            return r1
        L_0x0092:
            int r0 = r9.f992t
            r5 = 0
            if (r0 == r3) goto L_0x00a0
            r9.f992t = r5
            android.view.VelocityTracker r0 = r9.f996x
            r0.clear()
            goto L_0x0158
        L_0x00a0:
            r9.f992t = r5
            int r0 = r10.getAction()
            if (r0 != r1) goto L_0x00b3
            boolean r0 = r9.isEnabled()
            if (r0 == 0) goto L_0x00b1
            r0 = 1
            goto L_0x00b4
        L_0x00b1:
        L_0x00b3:
            r0 = 0
        L_0x00b4:
            boolean r3 = r9.isChecked()
            if (r0 == 0) goto L_0x00eb
            android.view.VelocityTracker r0 = r9.f996x
            r6 = 1000(0x3e8, float:1.401E-42)
            r0.computeCurrentVelocity(r6)
            android.view.VelocityTracker r0 = r9.f996x
            float r0 = r0.getXVelocity()
            float r6 = java.lang.Math.abs(r0)
            int r7 = r9.f997y
            float r7 = (float) r7
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 <= 0) goto L_0x00e6
            boolean r6 = p000.C0703zv.m16280a(r9)
            if (r6 != 0) goto L_0x00de
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x00dd
            goto L_0x00e2
        L_0x00dd:
            goto L_0x00e4
        L_0x00de:
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 >= 0) goto L_0x00e4
        L_0x00e2:
            r0 = 1
            goto L_0x00ec
        L_0x00e4:
            r0 = 0
            goto L_0x00ec
        L_0x00e6:
            boolean r0 = r9.m913a()
            goto L_0x00ec
        L_0x00eb:
            r0 = r3
        L_0x00ec:
            if (r0 == r3) goto L_0x00f1
            r9.playSoundEffect(r5)
        L_0x00f1:
            r9.setChecked(r0)
            android.view.MotionEvent r0 = android.view.MotionEvent.obtain(r10)
            r0.setAction(r2)
            super.onTouchEvent(r0)
            r0.recycle()
            super.onTouchEvent(r10)
            return r1
        L_0x0105:
            float r0 = r10.getX()
            float r2 = r10.getY()
            boolean r3 = r9.isEnabled()
            if (r3 == 0) goto L_0x0158
            android.graphics.drawable.Drawable r3 = r9.f977e
            if (r3 == 0) goto L_0x0158
            int r3 = r9.m914b()
            android.graphics.drawable.Drawable r4 = r9.f977e
            android.graphics.Rect r5 = r9.f973N
            r4.getPadding(r5)
            int r4 = r9.f963D
            int r5 = r9.f993u
            int r4 = r4 - r5
            int r6 = r9.f962C
            int r6 = r6 + r3
            int r6 = r6 - r5
            int r3 = r9.f961B
            android.graphics.Rect r5 = r9.f973N
            int r5 = r5.left
            android.graphics.Rect r7 = r9.f973N
            int r7 = r7.right
            int r8 = r9.f993u
            int r3 = r3 + r6
            int r3 = r3 + r5
            int r3 = r3 + r7
            int r3 = r3 + r8
            int r5 = r9.f965F
            int r5 = r5 + r8
            float r6 = (float) r6
            int r6 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x0158
            float r3 = (float) r3
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x0158
            float r3 = (float) r4
            int r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r3 <= 0) goto L_0x0158
            float r3 = (float) r5
            int r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x0158
            r9.f992t = r1
            r9.f994v = r0
            r9.f995w = r2
        L_0x0158:
            boolean r10 = super.onTouchEvent(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.SwitchCompat.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void setChecked(boolean z) {
        super.setChecked(z);
        boolean isChecked = isChecked();
        float f = 0.0f;
        if (getWindowToken() != null && C0340mj.m14732w(this)) {
            if (isChecked) {
                f = 1.0f;
            }
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, f959d, new float[]{f});
            this.f971L = ofFloat;
            ofFloat.setDuration(250);
            int i = Build.VERSION.SDK_INT;
            this.f971L.setAutoCancel(true);
            this.f971L.start();
            return;
        }
        ObjectAnimator objectAnimator = this.f971L;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        if (isChecked) {
            f = 1.0f;
        }
        mo1063a(f);
    }

    public final void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(dcm.m5891a((TextView) this, callback));
    }

    /* renamed from: a */
    private final void m912a(Typeface typeface) {
        if ((this.f966G.getTypeface() != null && !this.f966G.getTypeface().equals(typeface)) || (this.f966G.getTypeface() == null && typeface != null)) {
            this.f966G.setTypeface(typeface);
            requestLayout();
            invalidate();
        }
    }

    /* renamed from: a */
    public final void mo1063a(float f) {
        this.f976c = f;
        invalidate();
    }

    public final void toggle() {
        setChecked(!isChecked());
    }

    /* access modifiers changed from: protected */
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f977e || drawable == this.f982j;
    }
}
