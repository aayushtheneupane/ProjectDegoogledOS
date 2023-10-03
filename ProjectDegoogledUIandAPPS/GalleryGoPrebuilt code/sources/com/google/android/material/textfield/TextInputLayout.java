package com.google.android.material.textfield;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import com.google.android.material.internal.CheckableImageButton;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* compiled from: PG */
public class TextInputLayout extends LinearLayout {

    /* renamed from: A */
    private final TextView f5231A;

    /* renamed from: B */
    private boolean f5232B;

    /* renamed from: C */
    private CharSequence f5233C;

    /* renamed from: D */
    private ggu f5234D;

    /* renamed from: E */
    private gha f5235E;

    /* renamed from: F */
    private final int f5236F;

    /* renamed from: G */
    private final int f5237G;

    /* renamed from: H */
    private int f5238H;

    /* renamed from: I */
    private final int f5239I;

    /* renamed from: J */
    private final int f5240J;

    /* renamed from: K */
    private int f5241K;

    /* renamed from: L */
    private final Rect f5242L;

    /* renamed from: M */
    private final Rect f5243M;

    /* renamed from: N */
    private final RectF f5244N;

    /* renamed from: O */
    private final CheckableImageButton f5245O;

    /* renamed from: P */
    private ColorStateList f5246P;

    /* renamed from: Q */
    private boolean f5247Q;

    /* renamed from: R */
    private PorterDuff.Mode f5248R;

    /* renamed from: S */
    private boolean f5249S;

    /* renamed from: T */
    private Drawable f5250T;

    /* renamed from: U */
    private int f5251U;

    /* renamed from: V */
    private final LinkedHashSet f5252V;

    /* renamed from: W */
    private int f5253W;

    /* renamed from: a */
    public EditText f5254a;

    /* renamed from: aa */
    private final SparseArray f5255aa;

    /* renamed from: ab */
    private ColorStateList f5256ab;

    /* renamed from: ac */
    private boolean f5257ac;

    /* renamed from: ad */
    private PorterDuff.Mode f5258ad;

    /* renamed from: ae */
    private boolean f5259ae;

    /* renamed from: af */
    private Drawable f5260af;

    /* renamed from: ag */
    private int f5261ag;

    /* renamed from: ah */
    private Drawable f5262ah;

    /* renamed from: ai */
    private final CheckableImageButton f5263ai;

    /* renamed from: aj */
    private ColorStateList f5264aj;

    /* renamed from: ak */
    private ColorStateList f5265ak;

    /* renamed from: al */
    private ColorStateList f5266al;

    /* renamed from: am */
    private int f5267am;

    /* renamed from: an */
    private int f5268an;

    /* renamed from: ao */
    private int f5269ao;

    /* renamed from: ap */
    private ColorStateList f5270ap;

    /* renamed from: aq */
    private int f5271aq;

    /* renamed from: ar */
    private final int f5272ar;

    /* renamed from: as */
    private final int f5273as;

    /* renamed from: at */
    private final int f5274at;

    /* renamed from: au */
    private int f5275au;

    /* renamed from: av */
    private boolean f5276av;

    /* renamed from: aw */
    private boolean f5277aw;

    /* renamed from: ax */
    private ValueAnimator f5278ax;

    /* renamed from: ay */
    private boolean f5279ay;

    /* renamed from: b */
    public final gjq f5280b;

    /* renamed from: c */
    public boolean f5281c;

    /* renamed from: d */
    public int f5282d;

    /* renamed from: e */
    public boolean f5283e;

    /* renamed from: f */
    public TextView f5284f;

    /* renamed from: g */
    public CharSequence f5285g;

    /* renamed from: h */
    public boolean f5286h;

    /* renamed from: i */
    public ggu f5287i;

    /* renamed from: j */
    public int f5288j;

    /* renamed from: k */
    public int f5289k;

    /* renamed from: l */
    public final CheckableImageButton f5290l;

    /* renamed from: m */
    public final LinkedHashSet f5291m;

    /* renamed from: n */
    public final gfv f5292n;

    /* renamed from: o */
    public boolean f5293o;

    /* renamed from: p */
    private final FrameLayout f5294p;

    /* renamed from: q */
    private final LinearLayout f5295q;

    /* renamed from: r */
    private final LinearLayout f5296r;

    /* renamed from: s */
    private final FrameLayout f5297s;

    /* renamed from: t */
    private CharSequence f5298t;

    /* renamed from: u */
    private int f5299u;

    /* renamed from: v */
    private int f5300v;

    /* renamed from: w */
    private ColorStateList f5301w;

    /* renamed from: x */
    private ColorStateList f5302x;

    /* renamed from: y */
    private CharSequence f5303y;

    /* renamed from: z */
    private final TextView f5304z;

    public TextInputLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    /* renamed from: h */
    private final boolean m5133h() {
        return this.f5280b.f11515m;
    }

    /* renamed from: p */
    private final boolean m5141p() {
        return this.f5238H >= 0 && this.f5241K != 0;
    }

    /* renamed from: u */
    private final boolean m5146u() {
        return this.f5253W != 0;
    }

    /* renamed from: a */
    public final CharSequence mo3680a() {
        if (!this.f5232B) {
            return null;
        }
        return this.f5233C;
    }

    /* renamed from: c */
    public final CharSequence mo3694c() {
        gjq gjq = this.f5280b;
        if (!gjq.f11509g) {
            return null;
        }
        return gjq.f11508f;
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textInputStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TextInputLayout(android.content.Context r21, android.util.AttributeSet r22, int r23) {
        /*
            r20 = this;
            r0 = r20
            r7 = r22
            r8 = r23
            r9 = 2131952412(0x7f13031c, float:1.9541266E38)
            r1 = r21
            android.content.Context r1 = p000.gkl.m10444a(r1, r7, r8, r9)
            r0.<init>(r1, r7, r8)
            gjq r1 = new gjq
            r1.<init>(r0)
            r0.f5280b = r1
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            r0.f5242L = r1
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            r0.f5243M = r1
            android.graphics.RectF r1 = new android.graphics.RectF
            r1.<init>()
            r0.f5244N = r1
            java.util.LinkedHashSet r1 = new java.util.LinkedHashSet
            r1.<init>()
            r0.f5252V = r1
            r10 = 0
            r0.f5253W = r10
            android.util.SparseArray r1 = new android.util.SparseArray
            r1.<init>()
            r0.f5255aa = r1
            java.util.LinkedHashSet r1 = new java.util.LinkedHashSet
            r1.<init>()
            r0.f5291m = r1
            gfv r1 = new gfv
            r1.<init>(r0)
            r0.f5292n = r1
            android.content.Context r11 = r20.getContext()
            r12 = 1
            r0.setOrientation(r12)
            r0.setWillNotDraw(r10)
            r0.setAddStatesFromChildren(r12)
            android.widget.FrameLayout r1 = new android.widget.FrameLayout
            r1.<init>(r11)
            r0.f5294p = r1
            r1.setAddStatesFromChildren(r12)
            android.widget.FrameLayout r1 = r0.f5294p
            r0.addView(r1)
            android.widget.LinearLayout r1 = new android.widget.LinearLayout
            r1.<init>(r11)
            r0.f5295q = r1
            r1.setOrientation(r10)
            android.widget.LinearLayout r1 = r0.f5295q
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams
            r13 = -2
            r14 = -1
            r3 = 8388611(0x800003, float:1.1754948E-38)
            r2.<init>(r13, r14, r3)
            r1.setLayoutParams(r2)
            android.widget.FrameLayout r1 = r0.f5294p
            android.widget.LinearLayout r2 = r0.f5295q
            r1.addView(r2)
            android.widget.LinearLayout r1 = new android.widget.LinearLayout
            r1.<init>(r11)
            r0.f5296r = r1
            r1.setOrientation(r10)
            android.widget.LinearLayout r1 = r0.f5296r
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams
            r3 = 8388613(0x800005, float:1.175495E-38)
            r2.<init>(r13, r14, r3)
            r1.setLayoutParams(r2)
            android.widget.FrameLayout r1 = r0.f5294p
            android.widget.LinearLayout r2 = r0.f5296r
            r1.addView(r2)
            android.widget.FrameLayout r1 = new android.widget.FrameLayout
            r1.<init>(r11)
            r0.f5297s = r1
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams
            r2.<init>(r13, r14)
            r1.setLayoutParams(r2)
            gfv r1 = r0.f5292n
            android.animation.TimeInterpolator r2 = p000.gci.f10936a
            r1.f11215w = r2
            r1.mo6601d()
            gfv r1 = r0.f5292n
            android.animation.TimeInterpolator r2 = p000.gci.f10936a
            r1.f11214v = r2
            r1.mo6601d()
            gfv r1 = r0.f5292n
            r2 = 8388659(0x800033, float:1.1755015E-38)
            r1.mo6598b((int) r2)
            int[] r15 = p000.gjx.f11530a
            r6 = 5
            int[] r5 = new int[r6]
            r5 = {18, 16, 31, 35, 39} // fill-array
            p000.gga.m10240a(r11, r7, r8, r9)
            r16 = 2131952412(0x7f13031c, float:1.9541266E38)
            r1 = r11
            r2 = r22
            r3 = r15
            r4 = r23
            r17 = r5
            r5 = r16
            r13 = 5
            r6 = r17
            p000.gga.m10242b(r1, r2, r3, r4, r5, r6)
            zc r1 = p000.C0684zc.m16192a(r11, r7, r15, r8, r9)
            r2 = 38
            boolean r2 = r1.mo10725a((int) r2, (boolean) r12)
            r0.f5232B = r2
            r2 = 2
            java.lang.CharSequence r3 = r1.mo10729c(r2)
            r0.m5126c((java.lang.CharSequence) r3)
            r3 = 37
            boolean r3 = r1.mo10725a((int) r3, (boolean) r12)
            r0.f5277aw = r3
            ggy r3 = p000.gha.m10331a((android.content.Context) r11, (android.util.AttributeSet) r7, (int) r8, (int) r9)
            gha r3 = r3.mo6660a()
            r0.f5235E = r3
            android.content.res.Resources r3 = r11.getResources()
            r4 = 2131165629(0x7f0701bd, float:1.794548E38)
            int r3 = r3.getDimensionPixelOffset(r4)
            r0.f5236F = r3
            int r3 = r1.mo10728c(r13, r10)
            r0.f5237G = r3
            android.content.res.Resources r3 = r11.getResources()
            r4 = 2131165630(0x7f0701be, float:1.7945483E38)
            int r3 = r3.getDimensionPixelSize(r4)
            r4 = 12
            int r3 = r1.mo10730d(r4, r3)
            r0.f5239I = r3
            android.content.res.Resources r3 = r11.getResources()
            r4 = 2131165631(0x7f0701bf, float:1.7945485E38)
            int r3 = r3.getDimensionPixelSize(r4)
            r4 = 13
            int r3 = r1.mo10730d(r4, r3)
            r0.f5240J = r3
            int r3 = r0.f5239I
            r0.f5238H = r3
            r3 = 9
            float r3 = r1.mo10736g(r3)
            r4 = 8
            float r5 = r1.mo10736g(r4)
            r6 = 6
            float r6 = r1.mo10736g(r6)
            r7 = 7
            float r7 = r1.mo10736g(r7)
            gha r8 = r0.f5235E
            ggy r8 = r8.mo6672b()
            r9 = 0
            int r13 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r13 >= 0) goto L_0x0174
            goto L_0x0177
        L_0x0174:
            r8.mo6663c(r3)
        L_0x0177:
            int r3 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r3 >= 0) goto L_0x017c
            goto L_0x017f
        L_0x017c:
            r8.mo6664d(r5)
        L_0x017f:
            int r3 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r3 >= 0) goto L_0x0184
            goto L_0x0187
        L_0x0184:
            r8.mo6662b(r6)
        L_0x0187:
            int r3 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r3 < 0) goto L_0x018e
            r8.mo6661a((float) r7)
        L_0x018e:
            gha r3 = r8.mo6660a()
            r0.f5235E = r3
            r3 = 3
            android.content.res.ColorStateList r5 = p000.gqb.m10616a((android.content.Context) r11, (p000.C0684zc) r1, (int) r3)
            r6 = -16842910(0xfffffffffefeff62, float:-1.6947497E38)
            if (r5 == 0) goto L_0x01f0
            int r7 = r5.getDefaultColor()
            r0.f5271aq = r7
            r0.f5289k = r7
            boolean r7 = r5.isStateful()
            if (r7 == 0) goto L_0x01cd
            int[] r7 = new int[r12]
            r7[r10] = r6
            int r7 = r5.getColorForState(r7, r14)
            r0.f5272ar = r7
            int[] r7 = new int[r2]
            r7 = {16842908, 16842910} // fill-array
            int r7 = r5.getColorForState(r7, r14)
            r0.f5273as = r7
            int[] r7 = new int[r2]
            r7 = {16843623, 16842910} // fill-array
            int r5 = r5.getColorForState(r7, r14)
            r0.f5274at = r5
            goto L_0x01fb
        L_0x01cd:
            int r5 = r0.f5271aq
            r0.f5273as = r5
            r5 = 2131100078(0x7f0601ae, float:1.7812527E38)
            android.content.res.ColorStateList r5 = p000.C0436py.m15104a(r11, r5)
            int[] r7 = new int[r12]
            r7[r10] = r6
            int r7 = r5.getColorForState(r7, r14)
            r0.f5272ar = r7
            int[] r7 = new int[r12]
            r8 = 16843623(0x1010367, float:2.3696E-38)
            r7[r10] = r8
            int r5 = r5.getColorForState(r7, r14)
            r0.f5274at = r5
            goto L_0x01fb
        L_0x01f0:
            r0.f5289k = r10
            r0.f5271aq = r10
            r0.f5272ar = r10
            r0.f5273as = r10
            r0.f5274at = r10
        L_0x01fb:
            boolean r5 = r1.mo10735f(r12)
            if (r5 == 0) goto L_0x020a
            android.content.res.ColorStateList r5 = r1.mo10733e(r12)
            r0.f5266al = r5
            r0.f5265ak = r5
        L_0x020a:
            r5 = 10
            android.content.res.ColorStateList r7 = p000.gqb.m10616a((android.content.Context) r11, (p000.C0684zc) r1, (int) r5)
            int r5 = r1.mo10737h(r5)
            r0.f5269ao = r5
            r5 = 2131100096(0x7f0601c0, float:1.7812564E38)
            int r5 = p000.C0071co.m4669b(r11, r5)
            r0.f5267am = r5
            r5 = 2131100097(0x7f0601c1, float:1.7812566E38)
            int r5 = p000.C0071co.m4669b(r11, r5)
            r0.f5275au = r5
            r5 = 2131100100(0x7f0601c4, float:1.7812572E38)
            int r5 = p000.C0071co.m4669b(r11, r5)
            r0.f5268an = r5
            if (r7 == 0) goto L_0x0272
            boolean r5 = r7.isStateful()
            if (r5 == 0) goto L_0x0261
            int r5 = r7.getDefaultColor()
            r0.f5267am = r5
            int[] r5 = new int[r12]
            r5[r10] = r6
            int r5 = r7.getColorForState(r5, r14)
            r0.f5275au = r5
            int[] r5 = new int[r2]
            r5 = {16843623, 16842910} // fill-array
            int r5 = r7.getColorForState(r5, r14)
            r0.f5268an = r5
            int[] r5 = new int[r2]
            r5 = {16842908, 16842910} // fill-array
            int r5 = r7.getColorForState(r5, r14)
            r0.f5269ao = r5
            goto L_0x026f
        L_0x0261:
            int r5 = r0.f5269ao
            int r6 = r7.getDefaultColor()
            if (r5 == r6) goto L_0x026f
            int r5 = r7.getDefaultColor()
            r0.f5269ao = r5
        L_0x026f:
            r20.mo3702e()
        L_0x0272:
            r5 = 11
            boolean r6 = r1.mo10735f(r5)
            if (r6 == 0) goto L_0x0288
            android.content.res.ColorStateList r5 = p000.gqb.m10616a((android.content.Context) r11, (p000.C0684zc) r1, (int) r5)
            android.content.res.ColorStateList r6 = r0.f5270ap
            if (r6 == r5) goto L_0x0288
            r0.f5270ap = r5
            r20.mo3702e()
        L_0x0288:
            r5 = 39
            int r6 = r1.mo10734f(r5, r14)
            if (r6 == r14) goto L_0x02fc
            int r5 = r1.mo10734f(r5, r10)
            gfv r6 = r0.f5292n
            ggi r7 = new ggi
            android.view.View r8 = r6.f11193a
            android.content.Context r8 = r8.getContext()
            r7.<init>(r8, r5)
            android.content.res.ColorStateList r5 = r7.f11240b
            if (r5 != 0) goto L_0x02a7
            goto L_0x02a9
        L_0x02a7:
            r6.f11201i = r5
        L_0x02a9:
            float r5 = r7.f11239a
            int r8 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r8 != 0) goto L_0x02b0
            goto L_0x02b2
        L_0x02b0:
            r6.f11199g = r5
        L_0x02b2:
            android.content.res.ColorStateList r5 = r7.f11242d
            if (r5 != 0) goto L_0x02b7
            goto L_0x02b9
        L_0x02b7:
            r6.f11181A = r5
        L_0x02b9:
            float r5 = r7.f11243e
            r6.f11217y = r5
            float r5 = r7.f11244f
            r6.f11218z = r5
            float r5 = r7.f11245g
            r6.f11216x = r5
            ggj r5 = r6.f11182B
            if (r5 != 0) goto L_0x02ca
            goto L_0x02cd
        L_0x02ca:
            r5.mo6617a()
        L_0x02cd:
            ggj r5 = new ggj
            gfu r8 = new gfu
            r8.<init>(r6)
            r7.mo6615a()
            android.graphics.Typeface r9 = r7.f11246h
            r5.<init>(r8, r9)
            r6.f11182B = r5
            android.view.View r5 = r6.f11193a
            android.content.Context r5 = r5.getContext()
            ggj r8 = r6.f11182B
            r7.mo6616a(r5, r8)
            r6.mo6601d()
            gfv r5 = r0.f5292n
            android.content.res.ColorStateList r5 = r5.f11201i
            r0.f5266al = r5
            android.widget.EditText r5 = r0.f5254a
            if (r5 == 0) goto L_0x02fc
            r0.mo3688a((boolean) r10)
            r20.m5131g()
        L_0x02fc:
            r5 = 31
            int r5 = r1.mo10734f(r5, r10)
            r6 = 26
            java.lang.CharSequence r6 = r1.mo10729c(r6)
            r7 = 27
            boolean r7 = r1.mo10725a((int) r7, (boolean) r10)
            android.content.Context r8 = r20.getContext()
            android.view.LayoutInflater r8 = android.view.LayoutInflater.from(r8)
            android.widget.LinearLayout r9 = r0.f5296r
            r13 = 2131558456(0x7f0d0038, float:1.8742228E38)
            android.view.View r8 = r8.inflate(r13, r9, r10)
            com.google.android.material.internal.CheckableImageButton r8 = (com.google.android.material.internal.CheckableImageButton) r8
            r0.f5263ai = r8
            r8.setVisibility(r4)
            r8 = 28
            boolean r9 = r1.mo10735f(r8)
            if (r9 == 0) goto L_0x0335
            android.graphics.drawable.Drawable r8 = r1.mo10723a(r8)
            r0.mo3682a((android.graphics.drawable.Drawable) r8)
        L_0x0335:
            r8 = 29
            boolean r9 = r1.mo10735f(r8)
            if (r9 == 0) goto L_0x0364
            android.content.res.ColorStateList r8 = p000.gqb.m10616a((android.content.Context) r11, (p000.C0684zc) r1, (int) r8)
            r0.f5264aj = r8
            com.google.android.material.internal.CheckableImageButton r9 = r0.f5263ai
            android.graphics.drawable.Drawable r9 = r9.getDrawable()
            if (r9 == 0) goto L_0x0356
            int r15 = android.os.Build.VERSION.SDK_INT
            android.graphics.drawable.Drawable r9 = r9.mutate()
            p000.C0257jh.m14475a((android.graphics.drawable.Drawable) r9, (android.content.res.ColorStateList) r8)
            goto L_0x0357
        L_0x0356:
        L_0x0357:
            com.google.android.material.internal.CheckableImageButton r8 = r0.f5263ai
            android.graphics.drawable.Drawable r8 = r8.getDrawable()
            if (r8 == r9) goto L_0x0364
            com.google.android.material.internal.CheckableImageButton r8 = r0.f5263ai
            r8.setImageDrawable(r9)
        L_0x0364:
            r8 = 30
            boolean r9 = r1.mo10735f(r8)
            r15 = 0
            if (r9 == 0) goto L_0x0396
            int r8 = r1.mo10722a((int) r8, (int) r14)
            android.graphics.PorterDuff$Mode r8 = p000.ggf.m10248a((int) r8, (android.graphics.PorterDuff.Mode) r15)
            com.google.android.material.internal.CheckableImageButton r9 = r0.f5263ai
            android.graphics.drawable.Drawable r9 = r9.getDrawable()
            if (r9 == 0) goto L_0x0388
            int r16 = android.os.Build.VERSION.SDK_INT
            android.graphics.drawable.Drawable r9 = r9.mutate()
            p000.C0257jh.m14476a((android.graphics.drawable.Drawable) r9, (android.graphics.PorterDuff.Mode) r8)
            goto L_0x0389
        L_0x0388:
        L_0x0389:
            com.google.android.material.internal.CheckableImageButton r8 = r0.f5263ai
            android.graphics.drawable.Drawable r8 = r8.getDrawable()
            if (r8 == r9) goto L_0x0396
            com.google.android.material.internal.CheckableImageButton r8 = r0.f5263ai
            r8.setImageDrawable(r9)
        L_0x0396:
            com.google.android.material.internal.CheckableImageButton r8 = r0.f5263ai
            android.content.res.Resources r9 = r20.getResources()
            r3 = 2131886219(0x7f12008b, float:1.940701E38)
            java.lang.CharSequence r3 = r9.getText(r3)
            r8.setContentDescription(r3)
            com.google.android.material.internal.CheckableImageButton r3 = r0.f5263ai
            p000.C0340mj.m14689a((android.view.View) r3, (int) r2)
            com.google.android.material.internal.CheckableImageButton r3 = r0.f5263ai
            r3.setClickable(r10)
            com.google.android.material.internal.CheckableImageButton r3 = r0.f5263ai
            r3.f5218c = r10
            r3.setFocusable(r10)
            r3 = 35
            int r3 = r1.mo10734f(r3, r10)
            r8 = 34
            boolean r8 = r1.mo10725a((int) r8, (boolean) r10)
            r9 = 33
            java.lang.CharSequence r9 = r1.mo10729c(r9)
            r2 = 47
            int r2 = r1.mo10734f(r2, r10)
            r13 = 46
            java.lang.CharSequence r13 = r1.mo10729c(r13)
            r15 = 57
            int r15 = r1.mo10734f(r15, r10)
            r12 = 56
            java.lang.CharSequence r12 = r1.mo10729c(r12)
            r4 = 14
            boolean r4 = r1.mo10725a((int) r4, (boolean) r10)
            r10 = 15
            int r10 = r1.mo10722a((int) r10, (int) r14)
            int r14 = r0.f5282d
            if (r14 == r10) goto L_0x0400
            if (r10 > 0) goto L_0x03f7
            r10 = -1
            r0.f5282d = r10
            goto L_0x03f9
        L_0x03f7:
            r0.f5282d = r10
        L_0x03f9:
            boolean r10 = r0.f5281c
            if (r10 == 0) goto L_0x0400
            r20.m5134i()
        L_0x0400:
            r10 = 18
            r14 = 0
            int r10 = r1.mo10734f(r10, r14)
            r0.f5300v = r10
            r10 = 16
            int r10 = r1.mo10734f(r10, r14)
            r0.f5299u = r10
            android.content.Context r10 = r20.getContext()
            android.view.LayoutInflater r10 = android.view.LayoutInflater.from(r10)
            r14 = 2131558457(0x7f0d0039, float:1.874223E38)
            r18 = r4
            android.widget.LinearLayout r4 = r0.f5295q
            r19 = r15
            r15 = 0
            android.view.View r4 = r10.inflate(r14, r4, r15)
            com.google.android.material.internal.CheckableImageButton r4 = (com.google.android.material.internal.CheckableImageButton) r4
            r0.f5245O = r4
            r10 = 8
            r4.setVisibility(r10)
            r20.m5151z()
            r20.m5113A()
            r4 = 53
            boolean r10 = r1.mo10735f(r4)
            if (r10 == 0) goto L_0x047d
            android.graphics.drawable.Drawable r4 = r1.mo10723a(r4)
            com.google.android.material.internal.CheckableImageButton r10 = r0.f5245O
            r10.setImageDrawable(r4)
            if (r4 == 0) goto L_0x0451
            r4 = 1
            r0.m5130f(r4)
            r20.m5145t()
            goto L_0x0460
        L_0x0451:
            r4 = 0
            r0.m5130f(r4)
            r20.m5151z()
            r20.m5113A()
            r4 = 0
            r0.m5127d((java.lang.CharSequence) r4)
        L_0x0460:
            r4 = 52
            boolean r4 = r1.mo10735f(r4)
            if (r4 == 0) goto L_0x0471
            r4 = 52
            java.lang.CharSequence r4 = r1.mo10729c(r4)
            r0.m5127d((java.lang.CharSequence) r4)
        L_0x0471:
            r4 = 51
            r10 = 1
            boolean r4 = r1.mo10725a((int) r4, (boolean) r10)
            com.google.android.material.internal.CheckableImageButton r10 = r0.f5245O
            r10.mo3663a(r4)
        L_0x047d:
            r4 = 54
            boolean r10 = r1.mo10735f(r4)
            if (r10 == 0) goto L_0x0496
            android.content.res.ColorStateList r4 = p000.gqb.m10616a((android.content.Context) r11, (p000.C0684zc) r1, (int) r4)
            android.content.res.ColorStateList r10 = r0.f5246P
            if (r10 == r4) goto L_0x0496
            r0.f5246P = r4
            r4 = 1
            r0.f5247Q = r4
            r20.m5145t()
        L_0x0496:
            r4 = 55
            boolean r4 = r1.mo10735f(r4)
            if (r4 == 0) goto L_0x04b6
            r4 = 55
            r10 = -1
            int r4 = r1.mo10722a((int) r4, (int) r10)
            r10 = 0
            android.graphics.PorterDuff$Mode r4 = p000.ggf.m10248a((int) r4, (android.graphics.PorterDuff.Mode) r10)
            android.graphics.PorterDuff$Mode r10 = r0.f5248R
            if (r10 == r4) goto L_0x04b6
            r0.f5248R = r4
            r4 = 1
            r0.f5249S = r4
            r20.m5145t()
        L_0x04b6:
            r4 = 4
            r10 = 0
            int r4 = r1.mo10722a((int) r4, (int) r10)
            int r10 = r0.f5288j
            if (r4 == r10) goto L_0x04c9
            r0.f5288j = r4
            android.widget.EditText r4 = r0.f5254a
            if (r4 == 0) goto L_0x04c9
            r20.m5129f()
        L_0x04c9:
            android.content.Context r4 = r20.getContext()
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            android.widget.FrameLayout r10 = r0.f5297s
            r14 = 2131558456(0x7f0d0038, float:1.8742228E38)
            r15 = 0
            android.view.View r4 = r4.inflate(r14, r10, r15)
            com.google.android.material.internal.CheckableImageButton r4 = (com.google.android.material.internal.CheckableImageButton) r4
            r0.f5290l = r4
            android.widget.FrameLayout r10 = r0.f5297s
            r10.addView(r4)
            com.google.android.material.internal.CheckableImageButton r4 = r0.f5290l
            r10 = 8
            r4.setVisibility(r10)
            android.util.SparseArray r4 = r0.f5255aa
            gjb r10 = new gjb
            r10.<init>(r0)
            r14 = -1
            r4.append(r14, r10)
            android.util.SparseArray r4 = r0.f5255aa
            gjr r10 = new gjr
            r10.<init>(r0)
            r14 = 0
            r4.append(r14, r10)
            android.util.SparseArray r4 = r0.f5255aa
            gjw r10 = new gjw
            r10.<init>(r0)
            r14 = 1
            r4.append(r14, r10)
            android.util.SparseArray r4 = r0.f5255aa
            gja r10 = new gja
            r10.<init>(r0)
            r14 = 2
            r4.append(r14, r10)
            android.util.SparseArray r4 = r0.f5255aa
            gjn r10 = new gjn
            r10.<init>(r0)
            r14 = 3
            r4.append(r14, r10)
            r4 = 23
            boolean r4 = r1.mo10735f(r4)
            r10 = 43
            if (r4 == 0) goto L_0x0563
            r4 = 23
            r14 = 0
            int r4 = r1.mo10722a((int) r4, (int) r14)
            r0.m5123b((int) r4)
            r4 = 22
            boolean r4 = r1.mo10735f(r4)
            if (r4 == 0) goto L_0x0547
            r4 = 22
            android.graphics.drawable.Drawable r4 = r1.mo10723a(r4)
            r0.mo3691b((android.graphics.drawable.Drawable) r4)
        L_0x0547:
            r4 = 21
            boolean r4 = r1.mo10735f(r4)
            if (r4 == 0) goto L_0x0558
            r4 = 21
            java.lang.CharSequence r4 = r1.mo10729c(r4)
            r0.mo3692b((java.lang.CharSequence) r4)
        L_0x0558:
            r4 = 20
            r14 = 1
            boolean r4 = r1.mo10725a((int) r4, (boolean) r14)
            r0.mo3696d((boolean) r4)
            goto L_0x05ac
        L_0x0563:
            boolean r4 = r1.mo10735f(r10)
            if (r4 == 0) goto L_0x05ac
            r4 = 0
            boolean r14 = r1.mo10725a((int) r10, (boolean) r4)
            r0.m5123b((int) r14)
            r4 = 42
            android.graphics.drawable.Drawable r4 = r1.mo10723a(r4)
            r0.mo3691b((android.graphics.drawable.Drawable) r4)
            r4 = 41
            java.lang.CharSequence r4 = r1.mo10729c(r4)
            r0.mo3692b((java.lang.CharSequence) r4)
            r4 = 44
            boolean r4 = r1.mo10735f(r4)
            if (r4 == 0) goto L_0x0595
            r4 = 44
            android.content.res.ColorStateList r4 = p000.gqb.m10616a((android.content.Context) r11, (p000.C0684zc) r1, (int) r4)
            r0.m5115a((android.content.res.ColorStateList) r4)
        L_0x0595:
            r4 = 45
            boolean r4 = r1.mo10735f(r4)
            if (r4 == 0) goto L_0x05ac
            r4 = 45
            r14 = -1
            int r4 = r1.mo10722a((int) r4, (int) r14)
            r14 = 0
            android.graphics.PorterDuff$Mode r4 = p000.ggf.m10248a((int) r4, (android.graphics.PorterDuff.Mode) r14)
            r0.m5116a((android.graphics.PorterDuff.Mode) r4)
        L_0x05ac:
            boolean r4 = r1.mo10735f(r10)
            if (r4 != 0) goto L_0x05db
            r4 = 24
            boolean r4 = r1.mo10735f(r4)
            if (r4 == 0) goto L_0x05c4
            r4 = 24
            android.content.res.ColorStateList r4 = p000.gqb.m10616a((android.content.Context) r11, (p000.C0684zc) r1, (int) r4)
            r0.m5115a((android.content.res.ColorStateList) r4)
        L_0x05c4:
            r4 = 25
            boolean r4 = r1.mo10735f(r4)
            if (r4 == 0) goto L_0x05db
            r4 = 25
            r10 = -1
            int r4 = r1.mo10722a((int) r4, (int) r10)
            r10 = 0
            android.graphics.PorterDuff$Mode r4 = p000.ggf.m10248a((int) r4, (android.graphics.PorterDuff.Mode) r10)
            r0.m5116a((android.graphics.PorterDuff.Mode) r4)
        L_0x05db:
            ul r4 = new ul
            r4.<init>(r11)
            r0.f5304z = r4
            r10 = 2131362297(0x7f0a01f9, float:1.834437E38)
            r4.setId(r10)
            android.widget.TextView r4 = r0.f5304z
            android.widget.FrameLayout$LayoutParams r10 = new android.widget.FrameLayout$LayoutParams
            r14 = -2
            r10.<init>(r14, r14)
            r4.setLayoutParams(r10)
            android.widget.TextView r4 = r0.f5304z
            p000.C0340mj.m14679F(r4)
            android.widget.LinearLayout r4 = r0.f5295q
            com.google.android.material.internal.CheckableImageButton r10 = r0.f5245O
            r4.addView(r10)
            android.widget.LinearLayout r4 = r0.f5295q
            android.widget.TextView r10 = r0.f5304z
            r4.addView(r10)
            ul r4 = new ul
            r4.<init>(r11)
            r0.f5231A = r4
            r10 = 2131362298(0x7f0a01fa, float:1.8344373E38)
            r4.setId(r10)
            android.widget.TextView r4 = r0.f5231A
            android.widget.FrameLayout$LayoutParams r10 = new android.widget.FrameLayout$LayoutParams
            r11 = 80
            r14 = -2
            r10.<init>(r14, r14, r11)
            r4.setLayoutParams(r10)
            android.widget.TextView r4 = r0.f5231A
            p000.C0340mj.m14679F(r4)
            android.widget.LinearLayout r4 = r0.f5296r
            android.widget.TextView r10 = r0.f5231A
            r4.addView(r10)
            android.widget.LinearLayout r4 = r0.f5296r
            com.google.android.material.internal.CheckableImageButton r10 = r0.f5263ai
            r4.addView(r10)
            android.widget.LinearLayout r4 = r0.f5296r
            android.widget.FrameLayout r10 = r0.f5297s
            r4.addView(r10)
            r0.m5128e(r8)
            boolean r4 = android.text.TextUtils.isEmpty(r9)
            if (r4 == 0) goto L_0x0650
            boolean r4 = r20.m5133h()
            if (r4 != 0) goto L_0x064a
            goto L_0x067c
        L_0x064a:
            r4 = 0
            r0.m5128e(r4)
            goto L_0x067c
        L_0x0650:
            boolean r4 = r20.m5133h()
            if (r4 == 0) goto L_0x0657
            goto L_0x065c
        L_0x0657:
            r4 = 1
            r0.m5128e(r4)
        L_0x065c:
            gjq r4 = r0.f5280b
            r4.mo6773b()
            r4.f11514l = r9
            android.widget.TextView r8 = r4.f11516n
            r8.setText(r9)
            int r8 = r4.f11506d
            r10 = 2
            if (r8 != r10) goto L_0x066e
            goto L_0x0671
        L_0x066e:
            r4.f11507e = r10
        L_0x0671:
            int r10 = r4.f11507e
            android.widget.TextView r11 = r4.f11516n
            boolean r9 = r4.mo6772a((android.widget.TextView) r11, (java.lang.CharSequence) r9)
            r4.mo6768a(r8, r10, r9)
        L_0x067c:
            gjq r4 = r0.f5280b
            r4.mo6774b((int) r3)
            r0.mo3693b((boolean) r7)
            gjq r3 = r0.f5280b
            r3.mo6767a((int) r5)
            gjq r3 = r0.f5280b
            r3.mo6771a((java.lang.CharSequence) r6)
            boolean r3 = android.text.TextUtils.isEmpty(r13)
            if (r3 != 0) goto L_0x0696
            r4 = r13
            goto L_0x0698
        L_0x0696:
            r4 = 0
        L_0x0698:
            r0.f5303y = r4
            android.widget.TextView r3 = r0.f5304z
            r3.setText(r13)
            r20.m5135j()
            android.widget.TextView r3 = r0.f5304z
            p000.dcm.m5901a((android.widget.TextView) r3, (int) r2)
            boolean r2 = android.text.TextUtils.isEmpty(r12)
            if (r2 != 0) goto L_0x06af
            r4 = r12
            goto L_0x06b1
        L_0x06af:
            r4 = 0
        L_0x06b1:
            r0.f5285g = r4
            android.widget.TextView r2 = r0.f5231A
            r2.setText(r12)
            r20.m5137l()
            android.widget.TextView r2 = r0.f5231A
            r3 = r19
            p000.dcm.m5901a((android.widget.TextView) r2, (int) r3)
            r2 = 32
            boolean r2 = r1.mo10735f(r2)
            if (r2 == 0) goto L_0x06d5
            r2 = 32
            android.content.res.ColorStateList r2 = r1.mo10733e(r2)
            gjq r3 = r0.f5280b
            r3.mo6769a((android.content.res.ColorStateList) r2)
        L_0x06d5:
            r2 = 36
            boolean r2 = r1.mo10735f(r2)
            if (r2 == 0) goto L_0x06e8
            r2 = 36
            android.content.res.ColorStateList r2 = r1.mo10733e(r2)
            gjq r3 = r0.f5280b
            r3.mo6775b((android.content.res.ColorStateList) r2)
        L_0x06e8:
            r2 = 40
            boolean r2 = r1.mo10735f(r2)
            if (r2 == 0) goto L_0x070d
            r2 = 40
            android.content.res.ColorStateList r2 = r1.mo10733e(r2)
            android.content.res.ColorStateList r3 = r0.f5266al
            if (r3 == r2) goto L_0x070d
            android.content.res.ColorStateList r3 = r0.f5265ak
            if (r3 != 0) goto L_0x0703
            gfv r3 = r0.f5292n
            r3.mo6594a((android.content.res.ColorStateList) r2)
        L_0x0703:
            r0.f5266al = r2
            android.widget.EditText r2 = r0.f5254a
            if (r2 == 0) goto L_0x070d
            r2 = 0
            r0.mo3688a((boolean) r2)
        L_0x070d:
            r2 = 19
            boolean r2 = r1.mo10735f(r2)
            if (r2 == 0) goto L_0x0724
            r2 = 19
            android.content.res.ColorStateList r2 = r1.mo10733e(r2)
            android.content.res.ColorStateList r3 = r0.f5301w
            if (r3 == r2) goto L_0x0724
            r0.f5301w = r2
            r20.m5139n()
        L_0x0724:
            r2 = 17
            boolean r2 = r1.mo10735f(r2)
            if (r2 == 0) goto L_0x073b
            r2 = 17
            android.content.res.ColorStateList r2 = r1.mo10733e(r2)
            android.content.res.ColorStateList r3 = r0.f5302x
            if (r3 == r2) goto L_0x073b
            r0.f5302x = r2
            r20.m5139n()
        L_0x073b:
            r2 = 48
            boolean r2 = r1.mo10735f(r2)
            if (r2 == 0) goto L_0x074e
            r2 = 48
            android.content.res.ColorStateList r2 = r1.mo10733e(r2)
            android.widget.TextView r3 = r0.f5304z
            r3.setTextColor(r2)
        L_0x074e:
            r2 = 58
            boolean r2 = r1.mo10735f(r2)
            if (r2 == 0) goto L_0x0761
            r2 = 58
            android.content.res.ColorStateList r2 = r1.mo10733e(r2)
            android.widget.TextView r3 = r0.f5231A
            r3.setTextColor(r2)
        L_0x0761:
            boolean r2 = r0.f5281c
            r3 = r18
            if (r2 == r3) goto L_0x079c
            if (r3 == 0) goto L_0x078f
            ul r2 = new ul
            android.content.Context r4 = r20.getContext()
            r2.<init>(r4)
            r0.f5284f = r2
            r4 = 2131362294(0x7f0a01f6, float:1.8344365E38)
            r2.setId(r4)
            android.widget.TextView r2 = r0.f5284f
            r4 = 1
            r2.setMaxLines(r4)
            gjq r2 = r0.f5280b
            android.widget.TextView r4 = r0.f5284f
            r5 = 2
            r2.mo6770a((android.widget.TextView) r4, (int) r5)
            r20.m5139n()
            r20.m5134i()
            goto L_0x079a
        L_0x078f:
            r5 = 2
            gjq r2 = r0.f5280b
            android.widget.TextView r4 = r0.f5284f
            r2.mo6776b(r4, r5)
            r2 = 0
            r0.f5284f = r2
        L_0x079a:
            r0.f5281c = r3
        L_0x079c:
            r2 = 1
            r3 = 0
            boolean r2 = r1.mo10725a((int) r3, (boolean) r2)
            r0.setEnabled(r2)
            r1.mo10724a()
            r1 = 2
            p000.C0340mj.m14689a((android.view.View) r0, (int) r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* renamed from: a */
    public final void mo3686a(gkd gkd) {
        this.f5252V.add(gkd);
        if (this.f5254a != null) {
            gkd.mo6734a(this);
        }
    }

    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        boolean z;
        if (view instanceof EditText) {
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
            layoutParams2.gravity = (layoutParams2.gravity & -113) | 16;
            this.f5294p.addView(view, layoutParams2);
            this.f5294p.setLayoutParams(layoutParams);
            m5131g();
            EditText editText = (EditText) view;
            if (this.f5254a == null) {
                this.f5254a = editText;
                m5129f();
                mo3685a(new gkc(this));
                gfv gfv = this.f5292n;
                Typeface typeface = this.f5254a.getTypeface();
                boolean a = gfv.mo6595a(typeface);
                if (gfv.f11204l != typeface) {
                    gfv.f11204l = typeface;
                    z = true;
                } else {
                    z = false;
                }
                if (a || z) {
                    gfv.mo6601d();
                }
                gfv gfv2 = this.f5292n;
                float textSize = this.f5254a.getTextSize();
                if (gfv2.f11198f != textSize) {
                    gfv2.f11198f = textSize;
                    gfv2.mo6601d();
                }
                int gravity = this.f5254a.getGravity();
                this.f5292n.mo6598b((gravity & -113) | 48);
                this.f5292n.mo6593a(gravity);
                this.f5254a.addTextChangedListener(new gjy(this));
                if (this.f5265ak == null) {
                    this.f5265ak = this.f5254a.getHintTextColors();
                }
                if (this.f5232B) {
                    if (TextUtils.isEmpty(this.f5233C)) {
                        CharSequence hint = this.f5254a.getHint();
                        this.f5298t = hint;
                        m5126c(hint);
                        this.f5254a.setHint((CharSequence) null);
                    }
                    this.f5286h = true;
                }
                if (this.f5284f != null) {
                    mo3681a(this.f5254a.getText().length());
                }
                mo3690b();
                this.f5280b.mo6777c();
                this.f5295q.bringToFront();
                this.f5296r.bringToFront();
                this.f5297s.bringToFront();
                this.f5263ai.bringToFront();
                Iterator it = this.f5252V.iterator();
                while (it.hasNext()) {
                    ((gkd) it.next()).mo6734a(this);
                }
                m5136k();
                m5138m();
                if (!isEnabled()) {
                    editText.setEnabled(false);
                }
                m5122a(false, true);
                return;
            }
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        super.addView(view, i, layoutParams);
    }

    /* renamed from: a */
    private final void m5114a(float f) {
        if (this.f5292n.f11195c != f) {
            if (this.f5278ax == null) {
                ValueAnimator valueAnimator = new ValueAnimator();
                this.f5278ax = valueAnimator;
                valueAnimator.setInterpolator(gci.f10937b);
                this.f5278ax.setDuration(167);
                this.f5278ax.addUpdateListener(new gkb(this));
            }
            this.f5278ax.setFloatValues(new float[]{this.f5292n.f11195c, f});
            this.f5278ax.start();
        }
    }

    /* renamed from: v */
    private final void m5147v() {
        m5121a(this.f5290l, this.f5257ac, this.f5256ab, this.f5259ae, this.f5258ad);
    }

    /* renamed from: a */
    private static final void m5121a(CheckableImageButton checkableImageButton, boolean z, ColorStateList colorStateList, boolean z2, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null && (z || z2)) {
            int i = Build.VERSION.SDK_INT;
            drawable = drawable.mutate();
            if (z) {
                C0257jh.m14475a(drawable, colorStateList);
            }
            if (z2) {
                C0257jh.m14476a(drawable, mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    /* renamed from: t */
    private final void m5145t() {
        m5121a(this.f5245O, this.f5247Q, this.f5246P, this.f5249S, this.f5248R);
    }

    /* renamed from: o */
    private final int m5140o() {
        if (!this.f5232B) {
            return 0;
        }
        int i = this.f5288j;
        if (i == 0 || i == 1) {
            return (int) this.f5292n.mo6597b();
        }
        if (i != 2) {
            return 0;
        }
        return (int) (this.f5292n.mo6597b() / 2.0f);
    }

    /* renamed from: x */
    private final boolean m5149x() {
        return this.f5232B && !TextUtils.isEmpty(this.f5233C) && (this.f5287i instanceof gjc);
    }

    public final void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i) {
        EditText editText;
        if (this.f5298t == null || (editText = this.f5254a) == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i);
            return;
        }
        boolean z = this.f5286h;
        this.f5286h = false;
        CharSequence hint = editText.getHint();
        this.f5254a.setHint(this.f5298t);
        try {
            super.dispatchProvideAutofillStructure(viewStructure, i);
        } finally {
            this.f5254a.setHint(hint);
            this.f5286h = z;
        }
    }

    /* access modifiers changed from: protected */
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        this.f5293o = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.f5293o = false;
    }

    public final void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f5232B) {
            gfv gfv = this.f5292n;
            int save = canvas.save();
            if (gfv.f11206n != null && gfv.f11194b) {
                float f = gfv.f11202j;
                float f2 = gfv.f11203k;
                boolean z = gfv.f11208p;
                gfv.f11212t.ascent();
                float f3 = gfv.f11209q;
                gfv.f11212t.descent();
                float f4 = gfv.f11209q;
                if (f4 != 1.0f) {
                    canvas.scale(f4, f4, f, f2);
                }
                CharSequence charSequence = gfv.f11206n;
                canvas.drawText(charSequence, 0, charSequence.length(), f, f2, gfv.f11212t);
            }
            canvas.restoreToCount(save);
        }
        ggu ggu = this.f5234D;
        if (ggu != null) {
            Rect bounds = ggu.getBounds();
            bounds.top = bounds.bottom - this.f5238H;
            this.f5234D.draw(canvas);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void drawableStateChanged() {
        /*
            r4 = this;
            boolean r0 = r4.f5279ay
            if (r0 != 0) goto L_0x0059
            r0 = 1
            r4.f5279ay = r0
            super.drawableStateChanged()
            int[] r1 = r4.getDrawableState()
            gfv r2 = r4.f5292n
            r3 = 0
            if (r2 == 0) goto L_0x0031
            r2.f11210r = r1
            android.content.res.ColorStateList r1 = r2.f11201i
            if (r1 != 0) goto L_0x001a
            goto L_0x0020
        L_0x001a:
            boolean r1 = r1.isStateful()
            if (r1 != 0) goto L_0x002c
        L_0x0020:
            android.content.res.ColorStateList r1 = r2.f11200h
            if (r1 == 0) goto L_0x0032
            boolean r1 = r1.isStateful()
            if (r1 == 0) goto L_0x002b
            goto L_0x002c
        L_0x002b:
            goto L_0x0032
        L_0x002c:
            r2.mo6601d()
            r1 = 1
            goto L_0x0033
        L_0x0031:
        L_0x0032:
            r1 = 0
        L_0x0033:
            android.widget.EditText r2 = r4.f5254a
            if (r2 == 0) goto L_0x004a
            boolean r2 = p000.C0340mj.m14732w(r4)
            if (r2 == 0) goto L_0x0046
            boolean r2 = r4.isEnabled()
            if (r2 == 0) goto L_0x0044
            goto L_0x0047
        L_0x0044:
        L_0x0046:
            r0 = 0
        L_0x0047:
            r4.mo3688a((boolean) r0)
        L_0x004a:
            r4.mo3690b()
            r4.mo3702e()
            if (r1 == 0) goto L_0x0055
            r4.invalidate()
        L_0x0055:
            r4.f5279ay = r3
            return
        L_0x0059:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.drawableStateChanged():void");
    }

    public final int getBaseline() {
        EditText editText = this.f5254a;
        if (editText != null) {
            return editText.getBaseline() + getPaddingTop() + m5140o();
        }
        return super.getBaseline();
    }

    /* renamed from: s */
    private final gjo m5144s() {
        gjo gjo = (gjo) this.f5255aa.get(this.f5253W);
        return gjo == null ? (gjo) this.f5255aa.get(0) : gjo;
    }

    /* renamed from: r */
    private final Drawable m5143r() {
        return this.f5290l.getDrawable();
    }

    /* renamed from: d */
    public final boolean mo3697d() {
        return this.f5297s.getVisibility() == 0 && this.f5290l.getVisibility() == 0;
    }

    /* renamed from: q */
    private final boolean m5142q() {
        return this.f5245O.getVisibility() == 0;
    }

    /* renamed from: f */
    private final void m5129f() {
        int i = this.f5288j;
        if (i == 0) {
            this.f5287i = null;
            this.f5234D = null;
        } else if (i == 1) {
            this.f5287i = new ggu(this.f5235E);
            this.f5234D = new ggu();
        } else if (i == 2) {
            if (this.f5232B && !(this.f5287i instanceof gjc)) {
                this.f5287i = new gjc(this.f5235E);
            } else {
                this.f5287i = new ggu(this.f5235E);
            }
            this.f5234D = null;
        } else {
            StringBuilder sb = new StringBuilder(72);
            sb.append(i);
            sb.append(" is illegal; only @BoxBackgroundMode constants are supported.");
            throw new IllegalArgumentException(sb.toString());
        }
        EditText editText = this.f5254a;
        if (!(editText == null || this.f5287i == null || editText.getBackground() != null || this.f5288j == 0)) {
            C0340mj.m14694a((View) this.f5254a, (Drawable) this.f5287i);
        }
        mo3702e();
        if (this.f5288j != 0) {
            m5131g();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x014f  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0184  */
    /* JADX WARNING: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
        /*
            r3 = this;
            super.onLayout(r4, r5, r6, r7, r8)
            android.widget.EditText r4 = r3.f5254a
            if (r4 == 0) goto L_0x0194
            android.graphics.Rect r5 = r3.f5242L
            p000.gfw.m10234a((android.view.ViewGroup) r3, (android.view.View) r4, (android.graphics.Rect) r5)
            ggu r4 = r3.f5234D
            if (r4 == 0) goto L_0x0020
            int r4 = r5.bottom
            int r6 = r3.f5240J
            ggu r7 = r3.f5234D
            int r8 = r5.left
            int r4 = r4 - r6
            int r6 = r5.right
            int r0 = r5.bottom
            r7.setBounds(r8, r4, r6, r0)
        L_0x0020:
            boolean r4 = r3.f5232B
            if (r4 == 0) goto L_0x0194
            android.widget.EditText r4 = r3.f5254a
            int r4 = r4.getGravity()
            r4 = r4 & -113(0xffffffffffffff8f, float:NaN)
            gfv r6 = r3.f5292n
            r7 = r4 | 48
            r6.mo6598b((int) r7)
            gfv r6 = r3.f5292n
            r6.mo6593a((int) r4)
            gfv r4 = r3.f5292n
            android.widget.EditText r6 = r3.f5254a
            if (r6 == 0) goto L_0x018e
            android.graphics.Rect r6 = r3.f5243M
            int r7 = p000.C0340mj.m14714f(r3)
            int r8 = r5.bottom
            r6.bottom = r8
            int r8 = r3.f5288j
            r0 = 1
            if (r8 == r0) goto L_0x008d
            r7 = 2
            if (r8 == r7) goto L_0x006d
            int r7 = r5.left
            android.widget.EditText r8 = r3.f5254a
            int r8 = r8.getCompoundPaddingLeft()
            int r7 = r7 + r8
            r6.left = r7
            int r7 = r3.getPaddingTop()
            r6.top = r7
            int r7 = r5.right
            android.widget.EditText r8 = r3.f5254a
            int r8 = r8.getCompoundPaddingRight()
            int r7 = r7 - r8
            r6.right = r7
            goto L_0x00d4
        L_0x006d:
            int r7 = r5.left
            android.widget.EditText r8 = r3.f5254a
            int r8 = r8.getPaddingLeft()
            int r7 = r7 + r8
            r6.left = r7
            int r7 = r5.top
            int r8 = r3.m5140o()
            int r7 = r7 - r8
            r6.top = r7
            int r7 = r5.right
            android.widget.EditText r8 = r3.f5254a
            int r8 = r8.getPaddingRight()
            int r7 = r7 - r8
            r6.right = r7
            goto L_0x00d4
        L_0x008d:
            int r8 = r5.left
            android.widget.EditText r1 = r3.f5254a
            int r1 = r1.getCompoundPaddingLeft()
            int r8 = r8 + r1
            java.lang.CharSequence r1 = r3.f5303y
            if (r1 != 0) goto L_0x009b
            goto L_0x00ab
        L_0x009b:
            if (r7 == r0) goto L_0x00ab
            android.widget.TextView r1 = r3.f5304z
            int r1 = r1.getMeasuredWidth()
            int r8 = r8 - r1
            android.widget.TextView r1 = r3.f5304z
            int r1 = r1.getPaddingLeft()
            int r8 = r8 + r1
        L_0x00ab:
            r6.left = r8
            int r8 = r5.top
            int r1 = r3.f5237G
            int r8 = r8 + r1
            r6.top = r8
            int r8 = r5.right
            android.widget.EditText r1 = r3.f5254a
            int r1 = r1.getCompoundPaddingRight()
            int r8 = r8 - r1
            java.lang.CharSequence r1 = r3.f5303y
            if (r1 != 0) goto L_0x00c2
            goto L_0x00d2
        L_0x00c2:
            if (r7 != r0) goto L_0x00d2
            android.widget.TextView r7 = r3.f5304z
            int r7 = r7.getMeasuredWidth()
            int r8 = r8 + r7
            android.widget.TextView r7 = r3.f5304z
            int r7 = r7.getPaddingRight()
            int r8 = r8 + r7
        L_0x00d2:
            r6.right = r8
        L_0x00d4:
            int r7 = r6.left
            int r8 = r6.top
            int r1 = r6.right
            int r6 = r6.bottom
            android.graphics.Rect r2 = r4.f11197e
            boolean r2 = p000.gfv.m10217a(r2, r7, r8, r1, r6)
            if (r2 != 0) goto L_0x00ee
            android.graphics.Rect r2 = r4.f11197e
            r2.set(r7, r8, r1, r6)
            r4.f11211s = r0
            r4.mo6600c()
        L_0x00ee:
            gfv r4 = r3.f5292n
            android.widget.EditText r6 = r3.f5254a
            if (r6 == 0) goto L_0x0188
            android.graphics.Rect r6 = r3.f5243M
            android.text.TextPaint r7 = r4.f11213u
            float r8 = r4.f11198f
            r7.setTextSize(r8)
            android.graphics.Typeface r8 = r4.f11204l
            r7.setTypeface(r8)
            android.text.TextPaint r7 = r4.f11213u
            float r7 = r7.ascent()
            float r7 = -r7
            int r8 = r5.left
            android.widget.EditText r1 = r3.f5254a
            int r1 = r1.getCompoundPaddingLeft()
            int r8 = r8 + r1
            r6.left = r8
            int r8 = r3.f5288j
            if (r8 != r0) goto L_0x012f
            int r8 = android.os.Build.VERSION.SDK_INT
            android.widget.EditText r8 = r3.f5254a
            int r8 = r8.getMinLines()
            if (r8 <= r0) goto L_0x0123
            goto L_0x012f
        L_0x0123:
            int r8 = r5.centerY()
            float r8 = (float) r8
            r1 = 1073741824(0x40000000, float:2.0)
            float r1 = r7 / r1
            float r8 = r8 - r1
            int r8 = (int) r8
            goto L_0x0138
        L_0x012f:
            int r8 = r5.top
            android.widget.EditText r1 = r3.f5254a
            int r1 = r1.getCompoundPaddingTop()
            int r8 = r8 + r1
        L_0x0138:
            r6.top = r8
            int r8 = r5.right
            android.widget.EditText r1 = r3.f5254a
            int r1 = r1.getCompoundPaddingRight()
            int r8 = r8 - r1
            r6.right = r8
            int r8 = r3.f5288j
            if (r8 != r0) goto L_0x014f
            int r5 = r6.top
            float r5 = (float) r5
            float r5 = r5 + r7
            int r5 = (int) r5
            goto L_0x0158
        L_0x014f:
            int r5 = r5.bottom
            android.widget.EditText r7 = r3.f5254a
            int r7 = r7.getCompoundPaddingBottom()
            int r5 = r5 - r7
        L_0x0158:
            r6.bottom = r5
            int r5 = r6.left
            int r7 = r6.top
            int r8 = r6.right
            int r6 = r6.bottom
            android.graphics.Rect r1 = r4.f11196d
            boolean r1 = p000.gfv.m10217a(r1, r5, r7, r8, r6)
            if (r1 != 0) goto L_0x0174
            android.graphics.Rect r1 = r4.f11196d
            r1.set(r5, r7, r8, r6)
            r4.f11211s = r0
            r4.mo6600c()
        L_0x0174:
            gfv r4 = r3.f5292n
            r4.mo6601d()
            boolean r4 = r3.m5149x()
            if (r4 == 0) goto L_0x0194
            boolean r4 = r3.f5276av
            if (r4 == 0) goto L_0x0184
            goto L_0x0194
        L_0x0184:
            r3.m5150y()
            return
        L_0x0188:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            r4.<init>()
            throw r4
        L_0x018e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            r4.<init>()
            throw r4
        L_0x0194:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.onLayout(boolean, int, int, int, int):void");
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        int max;
        super.onMeasure(i, i2);
        boolean z = false;
        if (this.f5254a != null && this.f5254a.getMeasuredHeight() < (max = Math.max(this.f5296r.getMeasuredHeight(), this.f5295q.getMeasuredHeight()))) {
            this.f5254a.setMinimumHeight(max);
            z = true;
        }
        boolean w = m5148w();
        if (z || w) {
            this.f5254a.post(new gka(this));
        }
    }

    /* access modifiers changed from: protected */
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof gkg)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        gkg gkg = (gkg) parcelable;
        super.onRestoreInstanceState(gkg.f15201b);
        mo3687a(gkg.f11538c);
        if (gkg.f11539d) {
            this.f5290l.post(new gjz(this));
        }
        requestLayout();
    }

    public final Parcelable onSaveInstanceState() {
        gkg gkg = new gkg(super.onSaveInstanceState());
        if (this.f5280b.mo6778d()) {
            gkg.f11538c = mo3694c();
        }
        boolean z = false;
        if (m5146u() && this.f5290l.f5216a) {
            z = true;
        }
        gkg.f11539d = z;
        return gkg;
    }

    /* renamed from: y */
    private final void m5150y() {
        float f;
        float f2;
        if (m5149x()) {
            RectF rectF = this.f5244N;
            gfv gfv = this.f5292n;
            int width = this.f5254a.getWidth();
            int gravity = this.f5254a.getGravity();
            boolean a = gfv.mo6596a(gfv.f11205m);
            gfv.f11207o = a;
            int i = gravity & 8388613;
            if (i == 8388613 || (gravity & 5) == 5) {
                f = !a ? ((float) gfv.f11197e.right) - gfv.mo6591a() : (float) gfv.f11197e.left;
            } else if (gravity == 17) {
                f = (((float) width) / 2.0f) - (gfv.mo6591a() / 2.0f);
            } else {
                f = a ? ((float) gfv.f11197e.right) - gfv.mo6591a() : (float) gfv.f11197e.left;
            }
            rectF.left = f;
            rectF.top = (float) gfv.f11197e.top;
            if (i == 8388613 || (gravity & 5) == 5) {
                f2 = !gfv.f11207o ? (float) gfv.f11197e.right : rectF.left + gfv.mo6591a();
            } else if (gravity == 17) {
                f2 = (((float) width) / 2.0f) + (gfv.mo6591a() / 2.0f);
            } else {
                f2 = gfv.f11207o ? (float) gfv.f11197e.right : rectF.left + gfv.mo6591a();
            }
            rectF.right = f2;
            rectF.bottom = ((float) gfv.f11197e.top) + gfv.mo6597b();
            rectF.left -= (float) this.f5236F;
            rectF.top -= (float) this.f5236F;
            rectF.right += (float) this.f5236F;
            rectF.bottom += (float) this.f5236F;
            rectF.offset((float) (-getPaddingLeft()), 0.0f);
            ((gjc) this.f5287i).mo6746a(rectF.left, rectF.top, rectF.right, rectF.bottom);
        }
    }

    /* renamed from: a */
    private static void m5117a(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                m5117a((ViewGroup) childAt, z);
            }
        }
    }

    public final void setEnabled(boolean z) {
        m5117a((ViewGroup) this, z);
        super.setEnabled(z);
    }

    /* renamed from: d */
    public final void mo3696d(boolean z) {
        this.f5290l.mo3663a(z);
    }

    /* renamed from: b */
    public final void mo3692b(CharSequence charSequence) {
        if (this.f5290l.getContentDescription() != charSequence) {
            this.f5290l.setContentDescription(charSequence);
        }
    }

    /* renamed from: b */
    public final void mo3691b(Drawable drawable) {
        this.f5290l.setImageDrawable(drawable);
    }

    /* renamed from: b */
    private final void m5123b(int i) {
        boolean z;
        int i2 = this.f5253W;
        this.f5253W = i;
        if (i != 0) {
            z = true;
        } else {
            z = false;
        }
        mo3695c(z);
        if (m5144s().mo6760a(this.f5288j)) {
            m5144s().mo6743a();
            m5147v();
            Iterator it = this.f5291m.iterator();
            while (it.hasNext()) {
                ((gke) it.next()).mo6783a(this, i2);
            }
            return;
        }
        int i3 = this.f5288j;
        StringBuilder sb = new StringBuilder(93);
        sb.append("The current box background mode ");
        sb.append(i3);
        sb.append(" is not supported by the end icon mode ");
        sb.append(i);
        throw new IllegalStateException(sb.toString());
    }

    /* renamed from: a */
    public final void mo3683a(View.OnClickListener onClickListener) {
        m5120a(this.f5290l, onClickListener);
    }

    /* renamed from: a */
    private final void m5115a(ColorStateList colorStateList) {
        if (this.f5256ab != colorStateList) {
            this.f5256ab = colorStateList;
            this.f5257ac = true;
            m5147v();
        }
    }

    /* renamed from: a */
    private final void m5116a(PorterDuff.Mode mode) {
        if (this.f5258ad != mode) {
            this.f5258ad = mode;
            this.f5259ae = true;
            m5147v();
        }
    }

    /* renamed from: c */
    public final void mo3695c(boolean z) {
        int i;
        if (mo3697d() != z) {
            CheckableImageButton checkableImageButton = this.f5290l;
            if (!z) {
                i = 8;
            } else {
                i = 0;
            }
            checkableImageButton.setVisibility(i);
            m5138m();
            m5148w();
        }
    }

    /* renamed from: a */
    public final void mo3687a(CharSequence charSequence) {
        if (!this.f5280b.f11509g) {
            if (!TextUtils.isEmpty(charSequence)) {
                mo3693b(true);
            } else {
                return;
            }
        }
        if (!TextUtils.isEmpty(charSequence)) {
            gjq gjq = this.f5280b;
            gjq.mo6773b();
            gjq.f11508f = charSequence;
            gjq.f11510h.setText(charSequence);
            int i = gjq.f11506d;
            if (i != 1) {
                gjq.f11507e = 1;
            }
            gjq.mo6768a(i, gjq.f11507e, gjq.mo6772a(gjq.f11510h, charSequence));
            return;
        }
        this.f5280b.mo6766a();
    }

    /* renamed from: b */
    public final void mo3693b(boolean z) {
        gjq gjq = this.f5280b;
        if (gjq.f11509g != z) {
            gjq.mo6773b();
            if (z) {
                gjq.f11510h = new C0558ul(gjq.f11503a);
                gjq.f11510h.setId(R.id.textinput_error);
                gjq.mo6767a(gjq.f11512j);
                gjq.mo6769a(gjq.f11513k);
                gjq.mo6771a(gjq.f11511i);
                gjq.f11510h.setVisibility(4);
                C0340mj.m14679F(gjq.f11510h);
                gjq.mo6770a(gjq.f11510h, 0);
            } else {
                gjq.mo6766a();
                gjq.mo6776b(gjq.f11510h, 0);
                gjq.f11510h = null;
                gjq.f11504b.mo3690b();
                gjq.f11504b.mo3702e();
            }
            gjq.f11509g = z;
        }
    }

    /* renamed from: a */
    public final void mo3682a(Drawable drawable) {
        this.f5263ai.setImageDrawable(drawable);
        boolean z = false;
        if (drawable != null && this.f5280b.f11509g) {
            z = true;
        }
        m5132g(z);
    }

    /* renamed from: g */
    private final void m5132g(boolean z) {
        int i = 8;
        this.f5263ai.setVisibility(!z ? 8 : 0);
        FrameLayout frameLayout = this.f5297s;
        if (!z) {
            i = 0;
        }
        frameLayout.setVisibility(i);
        m5138m();
        if (!m5146u()) {
            m5148w();
        }
    }

    /* renamed from: e */
    private final void m5128e(boolean z) {
        gjq gjq = this.f5280b;
        if (gjq.f11515m != z) {
            gjq.mo6773b();
            if (z) {
                gjq.f11516n = new C0558ul(gjq.f11503a);
                gjq.f11516n.setId(R.id.textinput_helper_text);
                gjq.f11516n.setVisibility(4);
                C0340mj.m14679F(gjq.f11516n);
                gjq.mo6774b(gjq.f11517o);
                gjq.mo6775b(gjq.f11518p);
                gjq.mo6770a(gjq.f11516n, 1);
            } else {
                gjq.mo6773b();
                int i = gjq.f11506d;
                if (i == 2) {
                    gjq.f11507e = 0;
                }
                gjq.mo6768a(i, gjq.f11507e, gjq.mo6772a(gjq.f11516n, (CharSequence) null));
                gjq.mo6776b(gjq.f11516n, 1);
                gjq.f11516n = null;
                gjq.f11504b.mo3690b();
                gjq.f11504b.mo3702e();
            }
            gjq.f11515m = z;
        }
    }

    /* renamed from: c */
    private final void m5126c(CharSequence charSequence) {
        if (this.f5232B) {
            if (!TextUtils.equals(charSequence, this.f5233C)) {
                this.f5233C = charSequence;
                gfv gfv = this.f5292n;
                if (charSequence == null || !TextUtils.equals(gfv.f11205m, charSequence)) {
                    gfv.f11205m = charSequence;
                    gfv.f11206n = null;
                    gfv.mo6601d();
                }
                if (!this.f5276av) {
                    m5150y();
                }
            }
            sendAccessibilityEvent(2048);
        }
    }

    /* renamed from: b */
    private static void m5124b(CheckableImageButton checkableImageButton) {
        int i;
        boolean A = C0340mj.m14674A(checkableImageButton);
        checkableImageButton.setFocusable(A);
        checkableImageButton.setClickable(A);
        checkableImageButton.f5218c = A;
        checkableImageButton.setLongClickable(false);
        if (!A) {
            i = 2;
        } else {
            i = 1;
        }
        C0340mj.m14689a((View) checkableImageButton, i);
    }

    /* renamed from: a */
    private static void m5120a(CheckableImageButton checkableImageButton, View.OnClickListener onClickListener) {
        checkableImageButton.setOnClickListener(onClickListener);
        m5124b(checkableImageButton);
    }

    /* renamed from: a */
    public static void m5118a(CheckableImageButton checkableImageButton) {
        checkableImageButton.setOnLongClickListener((View.OnLongClickListener) null);
        m5124b(checkableImageButton);
    }

    /* renamed from: d */
    private final void m5127d(CharSequence charSequence) {
        if (this.f5245O.getContentDescription() != charSequence) {
            this.f5245O.setContentDescription(charSequence);
        }
    }

    /* renamed from: z */
    private final void m5151z() {
        m5120a(this.f5245O, (View.OnClickListener) null);
    }

    /* renamed from: A */
    private final void m5113A() {
        m5118a(this.f5245O);
    }

    /* renamed from: f */
    private final void m5130f(boolean z) {
        int i;
        if (m5142q() != z) {
            CheckableImageButton checkableImageButton = this.f5245O;
            if (!z) {
                i = 8;
            } else {
                i = 0;
            }
            checkableImageButton.setVisibility(i);
            m5136k();
            m5148w();
        }
    }

    /* renamed from: a */
    public final void mo3684a(TextView textView, int i) {
        try {
            dcm.m5901a(textView, i);
            int i2 = Build.VERSION.SDK_INT;
            if (textView.getTextColors().getDefaultColor() != -65281) {
                return;
            }
        } catch (Exception e) {
        }
        dcm.m5901a(textView, 2131952023);
        textView.setTextColor(C0071co.m4669b(getContext(), R.color.design_error));
    }

    /* renamed from: a */
    public final void mo3685a(gkc gkc) {
        EditText editText = this.f5254a;
        if (editText != null) {
            C0340mj.m14698a((View) editText, (C0315ll) gkc);
        }
    }

    /* renamed from: i */
    private final void m5134i() {
        if (this.f5284f != null) {
            EditText editText = this.f5254a;
            mo3681a(editText != null ? editText.getText().length() : 0);
        }
    }

    /* renamed from: a */
    public final void mo3681a(int i) {
        boolean z = this.f5283e;
        int i2 = this.f5282d;
        if (i2 == -1) {
            this.f5284f.setText(String.valueOf(i));
            this.f5284f.setContentDescription((CharSequence) null);
            this.f5283e = false;
        } else {
            this.f5283e = i > i2;
            Context context = getContext();
            TextView textView = this.f5284f;
            int i3 = this.f5282d;
            int i4 = !this.f5283e ? R.string.character_counter_content_description : R.string.character_counter_overflowed_content_description;
            Integer valueOf = Integer.valueOf(i);
            textView.setContentDescription(context.getString(i4, new Object[]{valueOf, Integer.valueOf(i3)}));
            if (z != this.f5283e) {
                m5139n();
            }
            this.f5284f.setText(getContext().getString(R.string.character_counter_pattern, new Object[]{valueOf, Integer.valueOf(this.f5282d)}));
        }
        if (this.f5254a != null && z != this.f5283e) {
            mo3688a(false);
            mo3702e();
            mo3690b();
        }
    }

    /* renamed from: n */
    private final void m5139n() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        TextView textView = this.f5284f;
        if (textView != null) {
            mo3684a(textView, this.f5283e ? this.f5299u : this.f5300v);
            if (!this.f5283e && (colorStateList2 = this.f5301w) != null) {
                this.f5284f.setTextColor(colorStateList2);
            }
            if (this.f5283e && (colorStateList = this.f5302x) != null) {
                this.f5284f.setTextColor(colorStateList);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0119  */
    /* renamed from: w */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean m5148w() {
        /*
            r10 = this;
            android.widget.EditText r0 = r10.f5254a
            r1 = 0
            if (r0 == 0) goto L_0x013f
            com.google.android.material.internal.CheckableImageButton r0 = r10.f5245O
            android.graphics.drawable.Drawable r0 = r0.getDrawable()
            r2 = 2
            r3 = 3
            r4 = 0
            r5 = 1
            if (r0 == 0) goto L_0x0012
            goto L_0x0016
        L_0x0012:
            java.lang.CharSequence r0 = r10.f5303y
            if (r0 == 0) goto L_0x005a
        L_0x0016:
            android.widget.LinearLayout r0 = r10.f5295q
            int r0 = r0.getMeasuredWidth()
            if (r0 <= 0) goto L_0x005a
            android.widget.LinearLayout r0 = r10.f5295q
            int r0 = r0.getMeasuredWidth()
            android.widget.EditText r6 = r10.f5254a
            int r6 = r6.getPaddingLeft()
            int r0 = r0 - r6
            android.graphics.drawable.Drawable r6 = r10.f5250T
            if (r6 != 0) goto L_0x0030
            goto L_0x0034
        L_0x0030:
            int r6 = r10.f5251U
            if (r6 == r0) goto L_0x0040
        L_0x0034:
            android.graphics.drawable.ColorDrawable r6 = new android.graphics.drawable.ColorDrawable
            r6.<init>()
            r10.f5250T = r6
            r10.f5251U = r0
            r6.setBounds(r1, r1, r0, r5)
        L_0x0040:
            android.widget.EditText r0 = r10.f5254a
            android.graphics.drawable.Drawable[] r0 = p000.dcm.m5903a((android.widget.TextView) r0)
            r6 = r0[r1]
            android.graphics.drawable.Drawable r7 = r10.f5250T
            if (r6 == r7) goto L_0x0059
            android.widget.EditText r6 = r10.f5254a
            r8 = r0[r5]
            r9 = r0[r2]
            r0 = r0[r3]
            p000.dcm.m5902a(r6, r7, r8, r9, r0)
            r0 = 1
            goto L_0x0076
        L_0x0059:
            goto L_0x0075
        L_0x005a:
            android.graphics.drawable.Drawable r0 = r10.f5250T
            if (r0 == 0) goto L_0x0074
            android.widget.EditText r0 = r10.f5254a
            android.graphics.drawable.Drawable[] r0 = p000.dcm.m5903a((android.widget.TextView) r0)
            android.widget.EditText r6 = r10.f5254a
            r7 = r0[r5]
            r8 = r0[r2]
            r0 = r0[r3]
            p000.dcm.m5902a(r6, r4, r7, r8, r0)
            r10.f5250T = r4
            r0 = 1
            goto L_0x0076
        L_0x0074:
        L_0x0075:
            r0 = 0
        L_0x0076:
            com.google.android.material.internal.CheckableImageButton r6 = r10.f5263ai
            int r6 = r6.getVisibility()
            if (r6 != 0) goto L_0x007f
            goto L_0x008f
        L_0x007f:
            boolean r6 = r10.m5146u()
            if (r6 == 0) goto L_0x008b
            boolean r6 = r10.mo3697d()
            if (r6 != 0) goto L_0x008f
        L_0x008b:
            java.lang.CharSequence r6 = r10.f5285g
            if (r6 == 0) goto L_0x0119
        L_0x008f:
            android.widget.LinearLayout r6 = r10.f5296r
            int r6 = r6.getMeasuredWidth()
            if (r6 <= 0) goto L_0x0119
            android.widget.TextView r6 = r10.f5231A
            int r6 = r6.getMeasuredWidth()
            android.widget.EditText r7 = r10.f5254a
            int r7 = r7.getPaddingRight()
            int r6 = r6 - r7
            com.google.android.material.internal.CheckableImageButton r7 = r10.f5263ai
            int r7 = r7.getVisibility()
            if (r7 != 0) goto L_0x00af
            com.google.android.material.internal.CheckableImageButton r4 = r10.f5263ai
            goto L_0x00bf
        L_0x00af:
            boolean r7 = r10.m5146u()
            if (r7 == 0) goto L_0x00be
            boolean r7 = r10.mo3697d()
            if (r7 == 0) goto L_0x00be
            com.google.android.material.internal.CheckableImageButton r4 = r10.f5290l
            goto L_0x00bf
        L_0x00be:
        L_0x00bf:
            if (r4 == 0) goto L_0x00d2
            int r7 = r4.getMeasuredWidth()
            int r6 = r6 + r7
            android.view.ViewGroup$LayoutParams r4 = r4.getLayoutParams()
            android.view.ViewGroup$MarginLayoutParams r4 = (android.view.ViewGroup.MarginLayoutParams) r4
            int r4 = p000.C0350mt.m14758a((android.view.ViewGroup.MarginLayoutParams) r4)
            int r6 = r6 + r4
            goto L_0x00d3
        L_0x00d2:
        L_0x00d3:
            android.widget.EditText r4 = r10.f5254a
            android.graphics.drawable.Drawable[] r4 = p000.dcm.m5903a((android.widget.TextView) r4)
            android.graphics.drawable.Drawable r7 = r10.f5260af
            if (r7 == 0) goto L_0x00f5
            int r8 = r10.f5261ag
            if (r8 != r6) goto L_0x00e2
            goto L_0x00f5
        L_0x00e2:
            r10.f5261ag = r6
            r7.setBounds(r1, r1, r6, r5)
            android.widget.EditText r0 = r10.f5254a
            r1 = r4[r1]
            r2 = r4[r5]
            android.graphics.drawable.Drawable r6 = r10.f5260af
            r3 = r4[r3]
            p000.dcm.m5902a(r0, r1, r2, r6, r3)
            goto L_0x013e
        L_0x00f5:
            if (r7 != 0) goto L_0x0103
            android.graphics.drawable.ColorDrawable r7 = new android.graphics.drawable.ColorDrawable
            r7.<init>()
            r10.f5260af = r7
            r10.f5261ag = r6
            r7.setBounds(r1, r1, r6, r5)
        L_0x0103:
            r2 = r4[r2]
            android.graphics.drawable.Drawable r6 = r10.f5260af
            if (r2 != r6) goto L_0x010b
            goto L_0x013d
        L_0x010b:
            r10.f5262ah = r2
            android.widget.EditText r0 = r10.f5254a
            r1 = r4[r1]
            r2 = r4[r5]
            r3 = r4[r3]
            p000.dcm.m5902a(r0, r1, r2, r6, r3)
            return r5
        L_0x0119:
            android.graphics.drawable.Drawable r6 = r10.f5260af
            if (r6 == 0) goto L_0x013d
            android.widget.EditText r6 = r10.f5254a
            android.graphics.drawable.Drawable[] r6 = p000.dcm.m5903a((android.widget.TextView) r6)
            r2 = r6[r2]
            android.graphics.drawable.Drawable r7 = r10.f5260af
            if (r2 != r7) goto L_0x0137
            android.widget.EditText r0 = r10.f5254a
            r1 = r6[r1]
            r2 = r6[r5]
            android.graphics.drawable.Drawable r7 = r10.f5262ah
            r3 = r6[r3]
            p000.dcm.m5902a(r0, r1, r2, r7, r3)
            goto L_0x0138
        L_0x0137:
            r5 = r0
        L_0x0138:
            r10.f5260af = r4
            goto L_0x013e
        L_0x013d:
            r5 = r0
        L_0x013e:
            return r5
        L_0x013f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.m5148w():boolean");
    }

    /* renamed from: b */
    public final void mo3690b() {
        Drawable background;
        TextView textView;
        EditText editText = this.f5254a;
        if (editText != null && this.f5288j == 0 && (background = editText.getBackground()) != null) {
            if (C0579vf.m15606b(background)) {
                background = background.mutate();
            }
            if (this.f5280b.mo6778d()) {
                background.setColorFilter(C0529tj.m15437a(this.f5280b.mo6779e(), PorterDuff.Mode.SRC_IN));
            } else if (!this.f5283e || (textView = this.f5284f) == null) {
                int i = Build.VERSION.SDK_INT;
                background.clearColorFilter();
                this.f5254a.refreshDrawableState();
            } else {
                background.setColorFilter(C0529tj.m15437a(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    /* renamed from: a */
    private final void m5119a(CheckableImageButton checkableImageButton, ColorStateList colorStateList) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (checkableImageButton.getDrawable() != null && colorStateList != null && colorStateList.isStateful()) {
            int colorForState = colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
            int i = Build.VERSION.SDK_INT;
            Drawable mutate = drawable.mutate();
            C0257jh.m14475a(mutate, ColorStateList.valueOf(colorForState));
            checkableImageButton.setImageDrawable(mutate);
        }
    }

    /* renamed from: g */
    private final void m5131g() {
        if (this.f5288j != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f5294p.getLayoutParams();
            int o = m5140o();
            if (o != layoutParams.topMargin) {
                layoutParams.topMargin = o;
                this.f5294p.requestLayout();
            }
        }
    }

    /* renamed from: a */
    public final void mo3688a(boolean z) {
        m5122a(z, false);
    }

    /* renamed from: a */
    private final void m5122a(boolean z, boolean z2) {
        ColorStateList colorStateList;
        TextView textView;
        ColorStateList colorStateList2;
        boolean isEnabled = isEnabled();
        EditText editText = this.f5254a;
        boolean z3 = editText != null && !TextUtils.isEmpty(editText.getText());
        EditText editText2 = this.f5254a;
        boolean z4 = editText2 != null && editText2.hasFocus();
        boolean d = this.f5280b.mo6778d();
        ColorStateList colorStateList3 = this.f5265ak;
        if (colorStateList3 != null) {
            this.f5292n.mo6594a(colorStateList3);
            this.f5292n.mo6599b(this.f5265ak);
        }
        if (!isEnabled) {
            this.f5292n.mo6594a(ColorStateList.valueOf(this.f5275au));
            this.f5292n.mo6599b(ColorStateList.valueOf(this.f5275au));
        } else if (d) {
            gfv gfv = this.f5292n;
            TextView textView2 = this.f5280b.f11510h;
            if (textView2 != null) {
                colorStateList2 = textView2.getTextColors();
            } else {
                colorStateList2 = null;
            }
            gfv.mo6594a(colorStateList2);
        } else if (this.f5283e && (textView = this.f5284f) != null) {
            this.f5292n.mo6594a(textView.getTextColors());
        } else if (z4 && (colorStateList = this.f5266al) != null) {
            this.f5292n.mo6594a(colorStateList);
        }
        if (z3 || (isEnabled() && (z4 || d))) {
            if (z2 || this.f5276av) {
                ValueAnimator valueAnimator = this.f5278ax;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.f5278ax.cancel();
                }
                if (z && this.f5277aw) {
                    m5114a(1.0f);
                } else {
                    this.f5292n.mo6592a(1.0f);
                }
                this.f5276av = false;
                if (m5149x()) {
                    m5150y();
                }
                m5135j();
                m5137l();
            }
        } else if (z2 || !this.f5276av) {
            ValueAnimator valueAnimator2 = this.f5278ax;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.f5278ax.cancel();
            }
            if (z && this.f5277aw) {
                m5114a(0.0f);
            } else {
                this.f5292n.mo6592a(0.0f);
            }
            if (m5149x() && (!((gjc) this.f5287i).f11468g.isEmpty()) && m5149x()) {
                ((gjc) this.f5287i).mo6746a(0.0f, 0.0f, 0.0f, 0.0f);
            }
            this.f5276av = true;
            m5135j();
            m5137l();
        }
    }

    /* renamed from: k */
    private final void m5136k() {
        int i;
        if (this.f5254a != null) {
            TextView textView = this.f5304z;
            if (!m5142q()) {
                i = this.f5254a.getPaddingLeft();
            } else {
                i = 0;
            }
            textView.setPadding(i, this.f5254a.getCompoundPaddingTop(), this.f5304z.getCompoundPaddingRight(), this.f5254a.getCompoundPaddingBottom());
        }
    }

    /* renamed from: j */
    private final void m5135j() {
        TextView textView = this.f5304z;
        int i = 8;
        if (this.f5303y != null && !this.f5276av) {
            i = 0;
        }
        textView.setVisibility(i);
        m5148w();
    }

    /* renamed from: b */
    private final void m5125b(boolean z, boolean z2) {
        int defaultColor = this.f5270ap.getDefaultColor();
        int colorForState = this.f5270ap.getColorForState(new int[]{16843623, 16842910}, defaultColor);
        int colorForState2 = this.f5270ap.getColorForState(new int[]{16843518, 16842910}, defaultColor);
        if (z) {
            this.f5241K = colorForState2;
        } else if (!z2) {
            this.f5241K = defaultColor;
        } else {
            this.f5241K = colorForState;
        }
    }

    /* renamed from: m */
    private final void m5138m() {
        if (this.f5254a != null) {
            TextView textView = this.f5231A;
            int paddingLeft = textView.getPaddingLeft();
            int paddingTop = this.f5254a.getPaddingTop();
            int i = 0;
            if (!mo3697d() && this.f5263ai.getVisibility() != 0) {
                i = this.f5254a.getPaddingRight();
            }
            textView.setPadding(paddingLeft, paddingTop, i, this.f5254a.getPaddingBottom());
        }
    }

    /* renamed from: l */
    private final void m5137l() {
        int visibility = this.f5231A.getVisibility();
        int i = 0;
        boolean z = this.f5285g != null && !this.f5276av;
        TextView textView = this.f5231A;
        if (!z) {
            i = 8;
        }
        textView.setVisibility(i);
        if (visibility != this.f5231A.getVisibility()) {
            m5144s().mo6744a(z);
        }
        m5148w();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0010, code lost:
        r0 = r6.f5254a;
     */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo3702e() {
        /*
            r6 = this;
            ggu r0 = r6.f5287i
            if (r0 == 0) goto L_0x018a
            int r0 = r6.f5288j
            if (r0 == 0) goto L_0x018a
            boolean r0 = r6.isFocused()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x001f
            android.widget.EditText r0 = r6.f5254a
            if (r0 == 0) goto L_0x001d
            boolean r0 = r0.hasFocus()
            if (r0 != 0) goto L_0x001b
            goto L_0x001d
        L_0x001b:
            goto L_0x001f
        L_0x001d:
            r0 = 0
            goto L_0x0020
        L_0x001f:
            r0 = 1
        L_0x0020:
            boolean r3 = r6.isHovered()
            if (r3 != 0) goto L_0x0035
            android.widget.EditText r3 = r6.f5254a
            if (r3 == 0) goto L_0x0033
            boolean r3 = r3.isHovered()
            if (r3 != 0) goto L_0x0031
            goto L_0x0033
        L_0x0031:
            goto L_0x0035
        L_0x0033:
            r3 = 0
            goto L_0x0036
        L_0x0035:
            r3 = 1
        L_0x0036:
            boolean r4 = r6.isEnabled()
            if (r4 != 0) goto L_0x0041
            int r4 = r6.f5275au
            r6.f5241K = r4
            goto L_0x0083
        L_0x0041:
            gjq r4 = r6.f5280b
            boolean r4 = r4.mo6778d()
            if (r4 != 0) goto L_0x0073
            boolean r4 = r6.f5283e
            if (r4 == 0) goto L_0x0060
            android.widget.TextView r4 = r6.f5284f
            if (r4 == 0) goto L_0x0060
            android.content.res.ColorStateList r5 = r6.f5270ap
            if (r5 == 0) goto L_0x0059
            r6.m5125b(r0, r3)
            goto L_0x0083
        L_0x0059:
            int r4 = r4.getCurrentTextColor()
            r6.f5241K = r4
            goto L_0x0083
        L_0x0060:
            if (r0 == 0) goto L_0x0067
            int r4 = r6.f5269ao
            r6.f5241K = r4
            goto L_0x0083
        L_0x0067:
            if (r3 != 0) goto L_0x006e
            int r4 = r6.f5267am
            r6.f5241K = r4
            goto L_0x0083
        L_0x006e:
            int r4 = r6.f5268an
            r6.f5241K = r4
            goto L_0x0083
        L_0x0073:
            android.content.res.ColorStateList r4 = r6.f5270ap
            if (r4 != 0) goto L_0x0080
            gjq r4 = r6.f5280b
            int r4 = r4.mo6779e()
            r6.f5241K = r4
            goto L_0x0083
        L_0x0080:
            r6.m5125b(r0, r3)
        L_0x0083:
            com.google.android.material.internal.CheckableImageButton r4 = r6.f5263ai
            android.graphics.drawable.Drawable r4 = r4.getDrawable()
            if (r4 == 0) goto L_0x009a
            gjq r4 = r6.f5280b
            boolean r5 = r4.f11509g
            if (r5 == 0) goto L_0x009a
            boolean r4 = r4.mo6778d()
            if (r4 == 0) goto L_0x0099
            r1 = 1
            goto L_0x009b
        L_0x0099:
        L_0x009a:
        L_0x009b:
            r6.m5132g(r1)
            com.google.android.material.internal.CheckableImageButton r1 = r6.f5263ai
            android.content.res.ColorStateList r4 = r6.f5264aj
            r6.m5119a((com.google.android.material.internal.CheckableImageButton) r1, (android.content.res.ColorStateList) r4)
            com.google.android.material.internal.CheckableImageButton r1 = r6.f5245O
            android.content.res.ColorStateList r4 = r6.f5246P
            r6.m5119a((com.google.android.material.internal.CheckableImageButton) r1, (android.content.res.ColorStateList) r4)
            com.google.android.material.internal.CheckableImageButton r1 = r6.f5290l
            android.content.res.ColorStateList r4 = r6.f5256ab
            r6.m5119a((com.google.android.material.internal.CheckableImageButton) r1, (android.content.res.ColorStateList) r4)
            gjo r1 = r6.m5144s()
            boolean r1 = r1.mo6762b()
            if (r1 == 0) goto L_0x00e8
            gjq r1 = r6.f5280b
            boolean r1 = r1.mo6778d()
            if (r1 == 0) goto L_0x00e5
            android.graphics.drawable.Drawable r1 = r6.m5143r()
            if (r1 != 0) goto L_0x00cc
            goto L_0x00e5
        L_0x00cc:
            android.graphics.drawable.Drawable r1 = r6.m5143r()
            int r4 = android.os.Build.VERSION.SDK_INT
            android.graphics.drawable.Drawable r1 = r1.mutate()
            gjq r4 = r6.f5280b
            int r4 = r4.mo6779e()
            p000.C0257jh.m14473a((android.graphics.drawable.Drawable) r1, (int) r4)
            com.google.android.material.internal.CheckableImageButton r4 = r6.f5290l
            r4.setImageDrawable(r1)
            goto L_0x00e8
        L_0x00e5:
            r6.m5147v()
        L_0x00e8:
            if (r0 != 0) goto L_0x00eb
            goto L_0x00f6
        L_0x00eb:
            boolean r1 = r6.isEnabled()
            if (r1 == 0) goto L_0x00f6
            int r1 = r6.f5240J
            r6.f5238H = r1
            goto L_0x00fa
        L_0x00f6:
            int r1 = r6.f5239I
            r6.f5238H = r1
        L_0x00fa:
            int r1 = r6.f5288j
            if (r1 == r2) goto L_0x00ff
            goto L_0x011e
        L_0x00ff:
            boolean r1 = r6.isEnabled()
            if (r1 != 0) goto L_0x010a
            int r0 = r6.f5272ar
            r6.f5289k = r0
            goto L_0x011e
        L_0x010a:
            if (r3 == 0) goto L_0x0113
            if (r0 != 0) goto L_0x0113
            int r0 = r6.f5274at
            r6.f5289k = r0
            goto L_0x011e
        L_0x0113:
            if (r0 != 0) goto L_0x011a
            int r0 = r6.f5271aq
            r6.f5289k = r0
            goto L_0x011e
        L_0x011a:
            int r0 = r6.f5273as
            r6.f5289k = r0
        L_0x011e:
            ggu r0 = r6.f5287i
            if (r0 == 0) goto L_0x0189
            gha r1 = r6.f5235E
            r0.mo3619a((p000.gha) r1)
            int r0 = r6.f5288j
            r1 = 2
            if (r0 == r1) goto L_0x012d
            goto L_0x013d
        L_0x012d:
            boolean r0 = r6.m5141p()
            if (r0 == 0) goto L_0x013d
            ggu r0 = r6.f5287i
            int r1 = r6.f5238H
            float r1 = (float) r1
            int r3 = r6.f5241K
            r0.mo6632a((float) r1, (int) r3)
        L_0x013d:
            int r0 = r6.f5289k
            int r1 = r6.f5288j
            if (r1 != r2) goto L_0x0154
            android.content.Context r0 = r6.getContext()
            r1 = 2130968783(0x7f0400cf, float:1.754623E38)
            int r0 = p000.ggf.m10255b(r0, r1)
            int r1 = r6.f5289k
            int r0 = p000.C0238ip.m14265a(r1, r0)
        L_0x0154:
            r6.f5289k = r0
            ggu r1 = r6.f5287i
            android.content.res.ColorStateList r0 = android.content.res.ColorStateList.valueOf(r0)
            r1.mo6635a((android.content.res.ColorStateList) r0)
            int r0 = r6.f5253W
            r1 = 3
            if (r0 != r1) goto L_0x016d
            android.widget.EditText r0 = r6.f5254a
            android.graphics.drawable.Drawable r0 = r0.getBackground()
            r0.invalidateSelf()
        L_0x016d:
            ggu r0 = r6.f5234D
            if (r0 == 0) goto L_0x0185
            boolean r0 = r6.m5141p()
            if (r0 == 0) goto L_0x0182
            ggu r0 = r6.f5234D
            int r1 = r6.f5241K
            android.content.res.ColorStateList r1 = android.content.res.ColorStateList.valueOf(r1)
            r0.mo6635a((android.content.res.ColorStateList) r1)
        L_0x0182:
            r6.invalidate()
        L_0x0185:
            r6.invalidate()
            return
        L_0x0189:
            return
        L_0x018a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.mo3702e():void");
    }
}
