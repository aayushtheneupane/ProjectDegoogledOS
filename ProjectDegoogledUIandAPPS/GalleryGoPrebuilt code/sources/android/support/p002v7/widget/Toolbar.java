package android.support.p002v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v7.widget.Toolbar */
/* compiled from: PG */
public class Toolbar extends ViewGroup {

    /* renamed from: A */
    private int f999A;

    /* renamed from: B */
    private int f1000B;

    /* renamed from: C */
    private int f1001C;

    /* renamed from: D */
    private int f1002D;

    /* renamed from: E */
    private int f1003E;

    /* renamed from: F */
    private int f1004F;

    /* renamed from: G */
    private ColorStateList f1005G;

    /* renamed from: H */
    private ColorStateList f1006H;

    /* renamed from: I */
    private boolean f1007I;

    /* renamed from: J */
    private boolean f1008J;

    /* renamed from: K */
    private final ArrayList f1009K;

    /* renamed from: L */
    private final int[] f1010L;

    /* renamed from: M */
    private final C0517sy f1011M;

    /* renamed from: N */
    private C0695zn f1012N;

    /* renamed from: O */
    private final Runnable f1013O;

    /* renamed from: a */
    public ActionMenuView f1014a;

    /* renamed from: b */
    public TextView f1015b;

    /* renamed from: c */
    public TextView f1016c;

    /* renamed from: d */
    public Drawable f1017d;

    /* renamed from: e */
    public CharSequence f1018e;

    /* renamed from: f */
    public ImageButton f1019f;

    /* renamed from: g */
    public View f1020g;

    /* renamed from: h */
    public Context f1021h;

    /* renamed from: i */
    public int f1022i;

    /* renamed from: j */
    public int f1023j;

    /* renamed from: k */
    public int f1024k;

    /* renamed from: l */
    public int f1025l;

    /* renamed from: m */
    public C0673ys f1026m;

    /* renamed from: n */
    public CharSequence f1027n;

    /* renamed from: o */
    public CharSequence f1028o;

    /* renamed from: p */
    public final ArrayList f1029p;

    /* renamed from: q */
    public C0690zi f1030q;

    /* renamed from: r */
    public C0512st f1031r;

    /* renamed from: s */
    public C0688zg f1032s;

    /* renamed from: t */
    public C0485rt f1033t;

    /* renamed from: u */
    public C0470re f1034u;

    /* renamed from: v */
    public boolean f1035v;

    /* renamed from: w */
    private ImageButton f1036w;

    /* renamed from: x */
    private ImageView f1037x;

    /* renamed from: y */
    private int f1038y;

    /* renamed from: z */
    private int f1039z;

    public Toolbar(Context context) {
        this(context, (AttributeSet) null);
    }

    /* renamed from: l */
    private final int m932l() {
        C0673ys ysVar = this.f1026m;
        if (ysVar != null) {
            return ysVar.f16418g ? ysVar.f16413b : ysVar.f16412a;
        }
        return 0;
    }

    /* renamed from: m */
    private final int m933m() {
        C0673ys ysVar = this.f1026m;
        if (ysVar != null) {
            return ysVar.f16418g ? ysVar.f16412a : ysVar.f16413b;
        }
        return 0;
    }

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.toolbarStyle);
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1004F = 8388627;
        this.f1009K = new ArrayList();
        this.f1029p = new ArrayList();
        this.f1010L = new int[2];
        this.f1011M = new C0685zd(this);
        this.f1013O = new C0686ze(this);
        C0684zc a = C0684zc.m16192a(getContext(), attributeSet, C0435px.f15596x, i, 0);
        this.f1023j = a.mo10734f(28, 0);
        this.f1024k = a.mo10734f(19, 0);
        this.f1004F = a.mo10726b(0, this.f1004F);
        this.f1025l = a.mo10726b(2, 48);
        int c = a.mo10728c(22, 0);
        c = a.mo10735f(27) ? a.mo10728c(27, c) : c;
        this.f1001C = c;
        this.f1000B = c;
        this.f999A = c;
        this.f1039z = c;
        int c2 = a.mo10728c(25, -1);
        if (c2 >= 0) {
            this.f1039z = c2;
        }
        int c3 = a.mo10728c(24, -1);
        if (c3 >= 0) {
            this.f999A = c3;
        }
        int c4 = a.mo10728c(26, -1);
        if (c4 >= 0) {
            this.f1000B = c4;
        }
        int c5 = a.mo10728c(23, -1);
        if (c5 >= 0) {
            this.f1001C = c5;
        }
        this.f1038y = a.mo10730d(13, -1);
        int c6 = a.mo10728c(9, RecyclerView.UNDEFINED_DURATION);
        int c7 = a.mo10728c(5, RecyclerView.UNDEFINED_DURATION);
        int d = a.mo10730d(7, 0);
        int d2 = a.mo10730d(8, 0);
        mo1105i();
        C0673ys ysVar = this.f1026m;
        ysVar.f16419h = false;
        if (d != Integer.MIN_VALUE) {
            ysVar.f16416e = d;
            ysVar.f16412a = d;
        }
        if (d2 != Integer.MIN_VALUE) {
            ysVar.f16417f = d2;
            ysVar.f16413b = d2;
        }
        if (!(c6 == Integer.MIN_VALUE && c7 == Integer.MIN_VALUE)) {
            ysVar.mo10712a(c6, c7);
        }
        this.f1002D = a.mo10728c(10, RecyclerView.UNDEFINED_DURATION);
        this.f1003E = a.mo10728c(6, RecyclerView.UNDEFINED_DURATION);
        this.f1017d = a.mo10723a(4);
        this.f1018e = a.mo10729c(3);
        CharSequence c8 = a.mo10729c(21);
        if (!TextUtils.isEmpty(c8)) {
            mo1085a(c8);
        }
        CharSequence c9 = a.mo10729c(18);
        if (!TextUtils.isEmpty(c9)) {
            mo1090b(c9);
        }
        this.f1021h = getContext();
        mo1082a(a.mo10734f(17, 0));
        Drawable a2 = a.mo10723a(16);
        if (a2 != null) {
            mo1089b(a2);
        }
        CharSequence c10 = a.mo10729c(15);
        if (!TextUtils.isEmpty(c10)) {
            mo1093c(c10);
        }
        Drawable a3 = a.mo10723a(11);
        if (a3 != null) {
            mo1083a(a3);
        }
        CharSequence c11 = a.mo10729c(12);
        if (!TextUtils.isEmpty(c11)) {
            if (!TextUtils.isEmpty(c11)) {
                m931k();
            }
            ImageView imageView = this.f1037x;
            if (imageView != null) {
                imageView.setContentDescription(c11);
            }
        }
        if (a.mo10735f(29)) {
            ColorStateList e = a.mo10733e(29);
            this.f1005G = e;
            TextView textView = this.f1015b;
            if (textView != null) {
                textView.setTextColor(e);
            }
        }
        if (a.mo10735f(20)) {
            ColorStateList e2 = a.mo10733e(20);
            this.f1006H = e2;
            TextView textView2 = this.f1016c;
            if (textView2 != null) {
                textView2.setTextColor(e2);
            }
        }
        if (a.mo10735f(14)) {
            mo1098e(a.mo10734f(14, 0));
        }
        a.mo10724a();
    }

    /* renamed from: a */
    private final void m923a(List list, int i) {
        int f = C0340mj.m14714f(this);
        int childCount = getChildCount();
        int a = C0321lr.m14621a(i, C0340mj.m14714f(this));
        list.clear();
        if (f == 1) {
            for (int i2 = childCount - 1; i2 >= 0; i2--) {
                View childAt = getChildAt(i2);
                C0689zh zhVar = (C0689zh) childAt.getLayoutParams();
                if (zhVar.f16444b == 0 && m924a(childAt) && m929f(zhVar.f15336a) == a) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt2 = getChildAt(i3);
            C0689zh zhVar2 = (C0689zh) childAt2.getLayoutParams();
            if (zhVar2.f16444b == 0 && m924a(childAt2) && m929f(zhVar2.f15336a) == a) {
                list.add(childAt2);
            }
        }
    }

    /* renamed from: a */
    private final void m922a(View view, boolean z) {
        C0689zh zhVar;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            zhVar = m930j();
        } else if (!checkLayoutParams(layoutParams)) {
            zhVar = m920a(layoutParams);
        } else {
            zhVar = (C0689zh) layoutParams;
        }
        zhVar.f16444b = 1;
        if (!z || this.f1020g == null) {
            addView(view, zhVar);
            return;
        }
        view.setLayoutParams(zhVar);
        this.f1029p.add(view);
    }

    /* access modifiers changed from: protected */
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof C0689zh);
    }

    /* renamed from: c */
    public final void mo1091c() {
        C0688zg zgVar = this.f1032s;
        C0475rj rjVar = zgVar != null ? zgVar.f16441a : null;
        if (rjVar != null) {
            rjVar.collapseActionView();
        }
    }

    /* renamed from: i */
    public final void mo1105i() {
        if (this.f1026m == null) {
            this.f1026m = new C0673ys();
        }
    }

    /* renamed from: k */
    private final void m931k() {
        if (this.f1037x == null) {
            this.f1037x = new C0533tn(getContext());
        }
    }

    /* renamed from: g */
    public final void mo1100g() {
        if (this.f1014a == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext());
            this.f1014a = actionMenuView;
            actionMenuView.mo846a(this.f1022i);
            ActionMenuView actionMenuView2 = this.f1014a;
            actionMenuView2.f938e = this.f1011M;
            actionMenuView2.mo847a(this.f1033t, this.f1034u);
            C0689zh j = m930j();
            j.f15336a = (this.f1025l & 112) | 8388613;
            this.f1014a.setLayoutParams(j);
            m922a((View) this.f1014a, false);
        }
    }

    /* renamed from: p */
    private final void m936p() {
        if (this.f1036w == null) {
            this.f1036w = new C0531tl(getContext(), (AttributeSet) null, R.attr.toolbarNavigationButtonStyle);
            C0689zh j = m930j();
            j.f15336a = (this.f1025l & 112) | 8388611;
            this.f1036w.setLayoutParams(j);
        }
    }

    /* renamed from: j */
    public static C0689zh m930j() {
        return new C0689zh();
    }

    /* access modifiers changed from: protected */
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return m930j();
    }

    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C0689zh(getContext(), attributeSet);
    }

    /* renamed from: a */
    protected static C0689zh m920a(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof C0689zh) {
            return new C0689zh((C0689zh) layoutParams);
        }
        if (layoutParams instanceof C0381nx) {
            return new C0689zh((C0381nx) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new C0689zh((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new C0689zh(layoutParams);
    }

    /* access modifiers changed from: protected */
    public /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return m920a(layoutParams);
    }

    /* renamed from: f */
    private final int m929f(int i) {
        int f = C0340mj.m14714f(this);
        int a = C0321lr.m14621a(i, f) & 7;
        if (a == 1 || a == 3 || a == 5) {
            return a;
        }
        return f != 1 ? 3 : 5;
    }

    /* renamed from: a */
    private final int m917a(View view, int i) {
        C0689zh zhVar = (C0689zh) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        int i3 = zhVar.f15336a & 112;
        if (!(i3 == 16 || i3 == 48 || i3 == 80)) {
            i3 = this.f1004F & 112;
        }
        if (i3 == 48) {
            return getPaddingTop() - i2;
        }
        if (i3 == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - zhVar.bottomMargin) - i2;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int i4 = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        if (i4 >= zhVar.topMargin) {
            int i5 = (((height - paddingBottom) - measuredHeight) - i4) - paddingTop;
            if (i5 < zhVar.bottomMargin) {
                i4 = Math.max(0, i4 - (zhVar.bottomMargin - i5));
            }
        } else {
            i4 = zhVar.topMargin;
        }
        return paddingTop + i4;
    }

    /* renamed from: o */
    private final int m935o() {
        C0472rg rgVar;
        ActionMenuView actionMenuView = this.f1014a;
        if (actionMenuView == null || (rgVar = actionMenuView.f934a) == null || !rgVar.hasVisibleItems()) {
            return m933m();
        }
        return Math.max(m933m(), Math.max(this.f1003E, 0));
    }

    /* renamed from: n */
    private final int mo2635n() {
        if (mo1097e() != null) {
            return Math.max(m932l(), Math.max(this.f1002D, 0));
        }
        return m932l();
    }

    /* renamed from: c */
    private static final int m927c(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return C0350mt.m14758a(marginLayoutParams) + C0350mt.m14769b(marginLayoutParams);
    }

    /* renamed from: f */
    public final Menu mo1099f() {
        mo1100g();
        ActionMenuView actionMenuView = this.f1014a;
        if (actionMenuView.f934a == null) {
            Menu a = actionMenuView.mo843a();
            if (this.f1032s == null) {
                this.f1032s = new C0688zg(this);
            }
            this.f1014a.f936c.mo10074g();
            ((C0472rg) a).mo9834a((C0486ru) this.f1032s, this.f1021h);
        }
        return this.f1014a.mo843a();
    }

    /* renamed from: d */
    public final CharSequence mo1095d() {
        ImageButton imageButton = this.f1036w;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    /* renamed from: e */
    public final Drawable mo1097e() {
        ImageButton imageButton = this.f1036w;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    /* renamed from: d */
    private static final int m928d(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    /* renamed from: h */
    public final C0566ut mo1104h() {
        if (this.f1012N == null) {
            this.f1012N = new C0695zn(this, true);
        }
        return this.f1012N;
    }

    /* renamed from: e */
    public final void mo1098e(int i) {
        new C0451qm(getContext()).inflate(i, mo1099f());
    }

    /* renamed from: b */
    private final boolean m926b(View view) {
        return view.getParent() == this || this.f1029p.contains(view);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r0.f936c;
     */
    /* renamed from: an */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo1087an() {
        /*
            r1 = this;
            android.support.v7.widget.ActionMenuView r0 = r1.f1014a
            if (r0 == 0) goto L_0x0010
            st r0 = r0.f936c
            if (r0 == 0) goto L_0x0010
            boolean r0 = r0.mo10072e()
            if (r0 == 0) goto L_0x0010
            r0 = 1
            return r0
        L_0x0010:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.Toolbar.mo1087an():boolean");
    }

    /* renamed from: a */
    private final int m919a(View view, int i, int[] iArr, int i2) {
        C0689zh zhVar = (C0689zh) view.getLayoutParams();
        int i3 = zhVar.leftMargin - iArr[0];
        int max = i + Math.max(0, i3);
        iArr[0] = Math.max(0, -i3);
        int a = m917a(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max, a, max + measuredWidth, view.getMeasuredHeight() + a);
        return max + measuredWidth + zhVar.rightMargin;
    }

    /* renamed from: b */
    private final int m925b(View view, int i, int[] iArr, int i2) {
        C0689zh zhVar = (C0689zh) view.getLayoutParams();
        int i3 = zhVar.rightMargin - iArr[1];
        int max = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        int a = m917a(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max - measuredWidth, a, max, view.getMeasuredHeight() + a);
        return max - (measuredWidth + zhVar.leftMargin);
    }

    /* renamed from: a */
    private final int m918a(View view, int i, int i2, int i3, int i4, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i5) + Math.max(0, i6);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + max + i2, marginLayoutParams.width), getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    /* renamed from: a */
    private final void m921a(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width);
        int childMeasureSpec2 = getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i4 >= 0) {
            if (mode != 0) {
                i4 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i4);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i4, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.f1013O);
    }

    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f1008J = false;
        }
        if (!this.f1008J) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.f1008J = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f1008J = false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        boolean z2;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int f = C0340mj.m14714f(this);
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i19 = width - paddingRight;
        int[] iArr = this.f1010L;
        iArr[1] = 0;
        iArr[0] = 0;
        int j = C0340mj.m14719j(this);
        int min = j >= 0 ? Math.min(j, i4 - i2) : 0;
        if (!m924a((View) this.f1036w)) {
            i5 = paddingLeft;
            i6 = i19;
        } else if (f != 1) {
            i5 = m919a(this.f1036w, paddingLeft, iArr, min);
            i6 = i19;
        } else {
            i6 = m925b(this.f1036w, i19, iArr, min);
            i5 = paddingLeft;
        }
        if (m924a((View) this.f1019f)) {
            if (f != 1) {
                i5 = m919a(this.f1019f, i5, iArr, min);
            } else {
                i6 = m925b(this.f1019f, i6, iArr, min);
            }
        }
        if (m924a((View) this.f1014a)) {
            if (f != 1) {
                i6 = m925b(this.f1014a, i6, iArr, min);
            } else {
                i5 = m919a(this.f1014a, i5, iArr, min);
            }
        }
        if (C0340mj.m14714f(this) == 1) {
            i7 = m935o();
        } else {
            i7 = mo2635n();
        }
        if (C0340mj.m14714f(this) == 1) {
            i8 = mo2635n();
        } else {
            i8 = m935o();
        }
        int i20 = paddingRight;
        iArr[0] = Math.max(0, i7 - i5);
        iArr[1] = Math.max(0, i8 - (i19 - i6));
        int max = Math.max(i5, i7);
        int min2 = Math.min(i6, i19 - i8);
        if (m924a(this.f1020g)) {
            if (f != 1) {
                max = m919a(this.f1020g, max, iArr, min);
            } else {
                min2 = m925b(this.f1020g, min2, iArr, min);
            }
        }
        if (m924a((View) this.f1037x)) {
            if (f != 1) {
                max = m919a(this.f1037x, max, iArr, min);
            } else {
                min2 = m925b(this.f1037x, min2, iArr, min);
            }
        }
        boolean a = m924a((View) this.f1015b);
        boolean a2 = m924a((View) this.f1016c);
        if (a) {
            C0689zh zhVar = (C0689zh) this.f1015b.getLayoutParams();
            i9 = zhVar.bottomMargin + zhVar.topMargin + this.f1015b.getMeasuredHeight();
        } else {
            i9 = 0;
        }
        if (a2) {
            C0689zh zhVar2 = (C0689zh) this.f1016c.getLayoutParams();
            i10 = width;
            i9 += zhVar2.topMargin + this.f1016c.getMeasuredHeight() + zhVar2.bottomMargin;
        } else {
            i10 = width;
        }
        if (a || a2) {
            TextView textView = !a ? this.f1016c : this.f1015b;
            TextView textView2 = !a2 ? this.f1015b : this.f1016c;
            C0689zh zhVar3 = (C0689zh) textView.getLayoutParams();
            C0689zh zhVar4 = (C0689zh) textView2.getLayoutParams();
            if ((!a || this.f1015b.getMeasuredWidth() <= 0) && (!a2 || this.f1016c.getMeasuredWidth() <= 0)) {
                z2 = false;
            } else {
                z2 = true;
            }
            i12 = paddingLeft;
            int i21 = this.f1004F & 112;
            i11 = min;
            if (i21 == 48) {
                i13 = min2;
                i14 = getPaddingTop() + zhVar3.topMargin + this.f1000B;
            } else if (i21 != 80) {
                int i22 = (((height - paddingTop) - paddingBottom) - i9) / 2;
                i13 = min2;
                if (i22 < zhVar3.topMargin + this.f1000B) {
                    i22 = zhVar3.topMargin + this.f1000B;
                } else {
                    int i23 = (((height - paddingBottom) - i9) - i22) - paddingTop;
                    if (i23 < zhVar3.bottomMargin + this.f1001C) {
                        i22 = Math.max(0, i22 - ((zhVar4.bottomMargin + this.f1001C) - i23));
                    }
                }
                i14 = paddingTop + i22;
            } else {
                i13 = min2;
                i14 = (((height - paddingBottom) - zhVar4.bottomMargin) - this.f1001C) - i9;
            }
            if (f != 1) {
                int i24 = (z2 ? this.f1039z : 0) - iArr[0];
                max += Math.max(0, i24);
                iArr[0] = Math.max(0, -i24);
                if (a) {
                    int measuredWidth = this.f1015b.getMeasuredWidth() + max;
                    int measuredHeight = this.f1015b.getMeasuredHeight() + i14;
                    this.f1015b.layout(max, i14, measuredWidth, measuredHeight);
                    i17 = measuredWidth + this.f999A;
                    i14 = measuredHeight + ((C0689zh) this.f1015b.getLayoutParams()).bottomMargin;
                } else {
                    i17 = max;
                }
                if (a2) {
                    C0689zh zhVar5 = (C0689zh) this.f1016c.getLayoutParams();
                    int i25 = i14 + zhVar5.topMargin;
                    int measuredWidth2 = this.f1016c.getMeasuredWidth() + max;
                    this.f1016c.layout(max, i25, measuredWidth2, this.f1016c.getMeasuredHeight() + i25);
                    i18 = measuredWidth2 + this.f999A;
                    int i26 = zhVar5.bottomMargin;
                } else {
                    i18 = max;
                }
                if (z2) {
                    max = Math.max(i17, i18);
                    min2 = i13;
                } else {
                    min2 = i13;
                }
            } else {
                int i27 = (z2 ? this.f1039z : 0) - iArr[1];
                min2 = i13 - Math.max(0, i27);
                iArr[1] = Math.max(0, -i27);
                if (a) {
                    int measuredWidth3 = min2 - this.f1015b.getMeasuredWidth();
                    int measuredHeight2 = this.f1015b.getMeasuredHeight() + i14;
                    this.f1015b.layout(measuredWidth3, i14, min2, measuredHeight2);
                    i15 = measuredWidth3 - this.f999A;
                    i14 = measuredHeight2 + ((C0689zh) this.f1015b.getLayoutParams()).bottomMargin;
                } else {
                    i15 = min2;
                }
                if (a2) {
                    C0689zh zhVar6 = (C0689zh) this.f1016c.getLayoutParams();
                    int i28 = i14 + zhVar6.topMargin;
                    this.f1016c.layout(min2 - this.f1016c.getMeasuredWidth(), i28, min2, this.f1016c.getMeasuredHeight() + i28);
                    i16 = min2 - this.f999A;
                    int i29 = zhVar6.bottomMargin;
                } else {
                    i16 = min2;
                }
                if (z2) {
                    min2 = Math.min(i15, i16);
                }
            }
        } else {
            i12 = paddingLeft;
            i11 = min;
        }
        m923a((List) this.f1009K, 3);
        int size = this.f1009K.size();
        for (int i30 = 0; i30 < size; i30++) {
            max = m919a((View) this.f1009K.get(i30), max, iArr, i11);
        }
        int i31 = i11;
        m923a((List) this.f1009K, 5);
        int size2 = this.f1009K.size();
        for (int i32 = 0; i32 < size2; i32++) {
            min2 = m925b((View) this.f1009K.get(i32), min2, iArr, i31);
        }
        m923a((List) this.f1009K, 1);
        ArrayList arrayList = this.f1009K;
        int i33 = iArr[0];
        int i34 = iArr[1];
        int size3 = arrayList.size();
        int i35 = 0;
        int i36 = 0;
        while (i35 < size3) {
            View view = (View) arrayList.get(i35);
            C0689zh zhVar7 = (C0689zh) view.getLayoutParams();
            int i37 = zhVar7.leftMargin - i33;
            int i38 = zhVar7.rightMargin - i34;
            int max2 = Math.max(0, i37);
            int max3 = Math.max(0, i38);
            int max4 = Math.max(0, -i37);
            int max5 = Math.max(0, -i38);
            i36 += max2 + view.getMeasuredWidth() + max3;
            i35++;
            i34 = max5;
            i33 = max4;
        }
        int i39 = (i12 + (((i10 - i12) - i20) / 2)) - (i36 / 2);
        int i40 = i36 + i39;
        if (i39 >= max) {
            max = i40 > min2 ? i39 - (i40 - min2) : i39;
        }
        int size4 = this.f1009K.size();
        for (int i41 = 0; i41 < size4; i41++) {
            max = m919a((View) this.f1009K.get(i41), max, iArr, i31);
        }
        this.f1009K.clear();
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int[] iArr = this.f1010L;
        char a = C0703zv.m16280a(this);
        char c = a ^ 1;
        int i9 = 0;
        if (m924a((View) this.f1036w)) {
            m921a(this.f1036w, i, 0, i2, this.f1038y);
            i5 = this.f1036w.getMeasuredWidth() + m927c((View) this.f1036w);
            i4 = Math.max(0, this.f1036w.getMeasuredHeight() + m928d((View) this.f1036w));
            i3 = View.combineMeasuredStates(0, this.f1036w.getMeasuredState());
        } else {
            i5 = 0;
            i4 = 0;
            i3 = 0;
        }
        if (m924a((View) this.f1019f)) {
            m921a(this.f1019f, i, 0, i2, this.f1038y);
            i5 = this.f1019f.getMeasuredWidth() + m927c((View) this.f1019f);
            i4 = Math.max(i4, this.f1019f.getMeasuredHeight() + m928d((View) this.f1019f));
            i3 = View.combineMeasuredStates(i3, this.f1019f.getMeasuredState());
        }
        int n = mo2635n();
        int max = Math.max(n, i5);
        iArr[a] = Math.max(0, n - i5);
        if (m924a((View) this.f1014a)) {
            m921a(this.f1014a, i, max, i2, this.f1038y);
            i6 = this.f1014a.getMeasuredWidth() + m927c((View) this.f1014a);
            i4 = Math.max(i4, this.f1014a.getMeasuredHeight() + m928d((View) this.f1014a));
            i3 = View.combineMeasuredStates(i3, this.f1014a.getMeasuredState());
        } else {
            i6 = 0;
        }
        int o = m935o();
        int max2 = max + Math.max(o, i6);
        iArr[c] = Math.max(0, o - i6);
        if (m924a(this.f1020g)) {
            max2 += m918a(this.f1020g, i, max2, i2, 0, iArr);
            i4 = Math.max(i4, this.f1020g.getMeasuredHeight() + m928d(this.f1020g));
            i3 = View.combineMeasuredStates(i3, this.f1020g.getMeasuredState());
        }
        if (m924a((View) this.f1037x)) {
            max2 += m918a(this.f1037x, i, max2, i2, 0, iArr);
            i4 = Math.max(i4, this.f1037x.getMeasuredHeight() + m928d((View) this.f1037x));
            i3 = View.combineMeasuredStates(i3, this.f1037x.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            int i11 = i;
            int i12 = i2;
            View childAt = getChildAt(i10);
            if (((C0689zh) childAt.getLayoutParams()).f16444b == 0 && m924a(childAt)) {
                View view = childAt;
                max2 += m918a(childAt, i, max2, i2, 0, iArr);
                i4 = Math.max(i4, view.getMeasuredHeight() + m928d(view));
                i3 = View.combineMeasuredStates(i3, view.getMeasuredState());
            }
        }
        int i13 = this.f1000B + this.f1001C;
        int i14 = this.f1039z + this.f999A;
        if (m924a((View) this.f1015b)) {
            m918a(this.f1015b, i, max2 + i14, i2, i13, iArr);
            i9 = this.f1015b.getMeasuredWidth() + m927c((View) this.f1015b);
            int measuredHeight = this.f1015b.getMeasuredHeight() + m928d((View) this.f1015b);
            i7 = View.combineMeasuredStates(i3, this.f1015b.getMeasuredState());
            i8 = measuredHeight;
        } else {
            i7 = i3;
            i8 = 0;
        }
        if (m924a((View) this.f1016c)) {
            i9 = Math.max(i9, m918a(this.f1016c, i, max2 + i14, i2, i8 + i13, iArr));
            i8 += this.f1016c.getMeasuredHeight() + m928d((View) this.f1016c);
            i7 = View.combineMeasuredStates(i7, this.f1016c.getMeasuredState());
        }
        int max3 = Math.max(i4, i8);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        setMeasuredDimension(View.resolveSizeAndState(Math.max(max2 + i9 + paddingLeft + paddingRight, getSuggestedMinimumWidth()), i, -16777216 & i7), View.resolveSizeAndState(Math.max(max3 + paddingTop + paddingBottom, getSuggestedMinimumHeight()), i2, i7 << 16));
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        C0472rg rgVar;
        MenuItem findItem;
        if (!(parcelable instanceof C0692zk)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        C0692zk zkVar = (C0692zk) parcelable;
        super.onRestoreInstanceState(zkVar.f15201b);
        ActionMenuView actionMenuView = this.f1014a;
        if (actionMenuView != null) {
            rgVar = actionMenuView.f934a;
        } else {
            rgVar = null;
        }
        int i = zkVar.f16445c;
        if (!(i == 0 || this.f1032s == null || rgVar == null || (findItem = rgVar.findItem(i)) == null)) {
            findItem.expandActionView();
        }
        if (zkVar.f16446d) {
            removeCallbacks(this.f1013O);
            post(this.f1013O);
        }
    }

    public final void onRtlPropertiesChanged(int i) {
        int i2 = Build.VERSION.SDK_INT;
        super.onRtlPropertiesChanged(i);
        mo1105i();
        C0673ys ysVar = this.f1026m;
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        if (z != ysVar.f16418g) {
            ysVar.f16418g = z;
            if (!ysVar.f16419h) {
                ysVar.f16412a = ysVar.f16416e;
                ysVar.f16413b = ysVar.f16417f;
            } else if (!z) {
                int i3 = ysVar.f16414c;
                if (i3 == Integer.MIN_VALUE) {
                    i3 = ysVar.f16416e;
                }
                ysVar.f16412a = i3;
                int i4 = ysVar.f16415d;
                if (i4 == Integer.MIN_VALUE) {
                    i4 = ysVar.f16417f;
                }
                ysVar.f16413b = i4;
            } else {
                int i5 = ysVar.f16415d;
                if (i5 == Integer.MIN_VALUE) {
                    i5 = ysVar.f16416e;
                }
                ysVar.f16412a = i5;
                int i6 = ysVar.f16414c;
                if (i6 == Integer.MIN_VALUE) {
                    i6 = ysVar.f16417f;
                }
                ysVar.f16413b = i6;
            }
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        C0475rj rjVar;
        C0692zk zkVar = new C0692zk(super.onSaveInstanceState());
        C0688zg zgVar = this.f1032s;
        if (!(zgVar == null || (rjVar = zgVar.f16441a) == null)) {
            zkVar.f16445c = rjVar.f15780a;
        }
        zkVar.f16446d = mo1087an();
        return zkVar;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f1007I = false;
        }
        if (!this.f1007I) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.f1007I = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f1007I = false;
        }
        return true;
    }

    /* renamed from: a */
    public final void mo1083a(Drawable drawable) {
        if (drawable != null) {
            m931k();
            if (!m926b((View) this.f1037x)) {
                m922a((View) this.f1037x, true);
            }
        } else {
            ImageView imageView = this.f1037x;
            if (imageView != null && m926b((View) imageView)) {
                removeView(this.f1037x);
                this.f1029p.remove(this.f1037x);
            }
        }
        ImageView imageView2 = this.f1037x;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    /* renamed from: c */
    public final void mo1092c(int i) {
        mo1093c(i != 0 ? getContext().getText(i) : null);
    }

    /* renamed from: c */
    public final void mo1093c(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            m936p();
        }
        ImageButton imageButton = this.f1036w;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    /* renamed from: d */
    public final void mo1096d(int i) {
        mo1089b(C0436py.m15105b(getContext(), i));
    }

    /* renamed from: b */
    public final void mo1089b(Drawable drawable) {
        if (drawable != null) {
            m936p();
            if (!m926b((View) this.f1036w)) {
                m922a((View) this.f1036w, true);
            }
        } else {
            ImageButton imageButton = this.f1036w;
            if (imageButton != null && m926b((View) imageButton)) {
                removeView(this.f1036w);
                this.f1029p.remove(this.f1036w);
            }
        }
        ImageButton imageButton2 = this.f1036w;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    /* renamed from: a */
    public final void mo1084a(View.OnClickListener onClickListener) {
        m936p();
        this.f1036w.setOnClickListener(onClickListener);
    }

    /* renamed from: a */
    public final void mo1082a(int i) {
        if (this.f1022i != i) {
            this.f1022i = i;
            if (i != 0) {
                this.f1021h = new ContextThemeWrapper(getContext(), i);
            } else {
                this.f1021h = getContext();
            }
        }
    }

    /* renamed from: b */
    public void mo1090b(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f1016c;
            if (textView != null && m926b((View) textView)) {
                removeView(this.f1016c);
                this.f1029p.remove(this.f1016c);
            }
        } else {
            if (this.f1016c == null) {
                Context context = getContext();
                C0558ul ulVar = new C0558ul(context);
                this.f1016c = ulVar;
                ulVar.setSingleLine();
                this.f1016c.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.f1024k;
                if (i != 0) {
                    this.f1016c.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.f1006H;
                if (colorStateList != null) {
                    this.f1016c.setTextColor(colorStateList);
                }
            }
            if (!m926b((View) this.f1016c)) {
                m922a((View) this.f1016c, true);
            }
        }
        TextView textView2 = this.f1016c;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.f1028o = charSequence;
    }

    /* renamed from: b */
    public final void mo1088b(int i) {
        mo1085a(getContext().getText(i));
    }

    /* renamed from: a */
    public void mo1085a(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.f1015b;
            if (textView != null && m926b((View) textView)) {
                removeView(this.f1015b);
                this.f1029p.remove(this.f1015b);
            }
        } else {
            if (this.f1015b == null) {
                Context context = getContext();
                C0558ul ulVar = new C0558ul(context);
                this.f1015b = ulVar;
                ulVar.setSingleLine();
                this.f1015b.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.f1023j;
                if (i != 0) {
                    this.f1015b.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.f1005G;
                if (colorStateList != null) {
                    this.f1015b.setTextColor(colorStateList);
                }
            }
            if (!m926b((View) this.f1015b)) {
                m922a((View) this.f1015b, true);
            }
        }
        TextView textView2 = this.f1015b;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.f1027n = charSequence;
    }

    /* renamed from: a */
    private final boolean m924a(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r0.f936c;
     */
    /* renamed from: al */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo1086al() {
        /*
            r1 = this;
            android.support.v7.widget.ActionMenuView r0 = r1.f1014a
            if (r0 == 0) goto L_0x0010
            st r0 = r0.f936c
            if (r0 == 0) goto L_0x0010
            boolean r0 = r0.mo10070c()
            if (r0 == 0) goto L_0x0010
            r0 = 1
            return r0
        L_0x0010:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p002v7.widget.Toolbar.mo1086al():boolean");
    }
}
