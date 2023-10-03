package p000;

import android.content.Context;
import android.graphics.Rect;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;

/* renamed from: vy */
/* compiled from: PG */
public class C0598vy extends C0607wg {

    /* renamed from: A */
    private final SparseIntArray f16171A = new SparseIntArray();

    /* renamed from: B */
    private final Rect f16172B = new Rect();

    /* renamed from: a */
    public int f16173a = -1;

    /* renamed from: b */
    public C0597vx f16174b = new C0595vv();

    /* renamed from: w */
    private boolean f16175w = false;

    /* renamed from: x */
    private int[] f16176x;

    /* renamed from: y */
    private View[] f16177y;

    /* renamed from: z */
    private final SparseIntArray f16178z = new SparseIntArray();

    public C0598vy(int i, byte[] bArr) {
        mo10425a(i);
    }

    /* renamed from: b */
    public boolean mo4532b() {
        return this.f16228h == null && !this.f16175w;
    }

    public C0598vy(int i) {
        super(1);
        mo10425a(i);
    }

    /* renamed from: i */
    private final void m15652i(int i) {
        int i2;
        int length;
        int[] iArr = this.f16176x;
        int i3 = this.f16173a;
        if (!(iArr != null && (length = iArr.length) == i3 + 1 && iArr[length - 1] == i)) {
            iArr = new int[(i3 + 1)];
        }
        int i4 = 0;
        iArr[0] = 0;
        int i5 = i / i3;
        int i6 = i % i3;
        int i7 = 0;
        for (int i8 = 1; i8 <= i3; i8++) {
            i4 += i6;
            if (i4 > 0 && i3 - i4 < i6) {
                i2 = i5 + 1;
                i4 -= i3;
            } else {
                i2 = i5;
            }
            i7 += i2;
            iArr[i8] = i7;
        }
        this.f16176x = iArr;
    }

    /* renamed from: a */
    public final boolean mo10432a(C0648xu xuVar) {
        return xuVar instanceof C0596vw;
    }

    /* renamed from: a */
    public final void mo10431a(C0664yj yjVar, C0604wd wdVar, C0646xs xsVar) {
        int i = this.f16173a;
        for (int i2 = 0; i2 < this.f16173a && wdVar.mo10455a(yjVar) && i > 0; i2++) {
            int i3 = wdVar.f16208d;
            xsVar.mo10410a(i3, Math.max(0, wdVar.f16211g));
            i -= this.f16174b.mo2711a(i3);
            wdVar.f16208d += wdVar.f16209e;
        }
    }

    /* renamed from: B */
    private final void m15645B() {
        View[] viewArr = this.f16177y;
        if (viewArr == null || viewArr.length != this.f16173a) {
            this.f16177y = new View[this.f16173a];
        }
    }

    /* renamed from: a */
    public final View mo10424a(C0656yb ybVar, C0664yj yjVar, int i, int i2, int i3) {
        int i4;
        mo10479l();
        int c = this.f16224d.mo10516c();
        int a = this.f16224d.mo10511a();
        if (i2 > i) {
            i4 = 1;
        } else {
            i4 = -1;
        }
        View view = null;
        View view2 = null;
        while (i != i2) {
            View h = mo10582h(i);
            int i5 = m15975i(h);
            if (i5 >= 0 && i5 < i3 && m15649b(ybVar, yjVar, i5) == 0) {
                if (!((C0648xu) h.getLayoutParams()).mo10594a()) {
                    if (this.f16224d.mo10519d(h) < a && this.f16224d.mo10517c(h) >= c) {
                        return h;
                    }
                    if (view == null) {
                        view = h;
                    }
                } else if (view2 == null) {
                    view2 = h;
                }
            }
            i += i4;
        }
        return view == null ? view2 : view;
    }

    /* renamed from: a */
    public final C0648xu mo2617a() {
        if (this.f16223c == 0) {
            return new C0596vw(-2, -1);
        }
        return new C0596vw(-1, -2);
    }

    /* renamed from: a */
    public final C0648xu mo2618a(Context context, AttributeSet attributeSet) {
        return new C0596vw(context, attributeSet);
    }

    /* renamed from: a */
    public final C0648xu mo2619a(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new C0596vw((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new C0596vw(layoutParams);
    }

    /* renamed from: b */
    public final int mo10434b(C0656yb ybVar, C0664yj yjVar) {
        if (this.f16223c == 1) {
            return this.f16173a;
        }
        if (yjVar.mo10626a() > 0) {
            return m15646a(ybVar, yjVar, yjVar.mo10626a() - 1) + 1;
        }
        return 0;
    }

    /* renamed from: a */
    public final int mo10422a(C0656yb ybVar, C0664yj yjVar) {
        if (this.f16223c == 0) {
            return this.f16173a;
        }
        if (yjVar.mo10626a() > 0) {
            return m15646a(ybVar, yjVar, yjVar.mo10626a() - 1) + 1;
        }
        return 0;
    }

    /* renamed from: d */
    private final int m15651d(int i, int i2) {
        if (this.f16223c == 1 && mo10478k()) {
            int[] iArr = this.f16176x;
            int i3 = this.f16173a - i;
            return iArr[i3] - iArr[i3 - i2];
        }
        int[] iArr2 = this.f16176x;
        return iArr2[i2 + i] - iArr2[i];
    }

    /* renamed from: a */
    private final int m15646a(C0656yb ybVar, C0664yj yjVar, int i) {
        if (!yjVar.f16364g) {
            return this.f16174b.mo10419c(i, this.f16173a);
        }
        int a = ybVar.mo10603a(i);
        if (a != -1) {
            return this.f16174b.mo10419c(a, this.f16173a);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i);
        return 0;
    }

    /* renamed from: b */
    private final int m15649b(C0656yb ybVar, C0664yj yjVar, int i) {
        if (!yjVar.f16364g) {
            return this.f16174b.mo10417b(i, this.f16173a);
        }
        int i2 = this.f16171A.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int a = ybVar.mo10603a(i);
        if (a != -1) {
            return this.f16174b.mo10417b(a, this.f16173a);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 0;
    }

    /* renamed from: c */
    private final int m15650c(C0656yb ybVar, C0664yj yjVar, int i) {
        if (!yjVar.f16364g) {
            return this.f16174b.mo2711a(i);
        }
        int i2 = this.f16178z.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int a = ybVar.mo10603a(i);
        if (a != -1) {
            return this.f16174b.mo2711a(a);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i);
        return 1;
    }

    /* renamed from: a */
    public final void mo10429a(C0656yb ybVar, C0664yj yjVar, C0604wd wdVar, C0603wc wcVar) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean z;
        View a;
        C0656yb ybVar2 = ybVar;
        C0664yj yjVar2 = yjVar;
        C0604wd wdVar2 = wdVar;
        C0603wc wcVar2 = wcVar;
        int g = this.f16224d.mo10524g();
        int i10 = mo10585r() > 0 ? this.f16176x[this.f16173a] : 0;
        if (g != 1073741824) {
            m15644A();
        }
        int i11 = wdVar2.f16209e;
        int i12 = this.f16173a;
        if (i11 != 1) {
            i12 = m15649b(ybVar2, yjVar2, wdVar2.f16208d) + m15650c(ybVar2, yjVar2, wdVar2.f16208d);
        }
        int i13 = 0;
        while (i13 < this.f16173a && wdVar2.mo10455a(yjVar2) && i12 > 0) {
            int i14 = wdVar2.f16208d;
            int c = m15650c(ybVar2, yjVar2, i14);
            if (c <= this.f16173a) {
                i12 -= c;
                if (i12 < 0 || (a = wdVar2.mo10453a(ybVar2)) == null) {
                    break;
                }
                this.f16177y[i13] = a;
                i13++;
            } else {
                throw new IllegalArgumentException("Item at position " + i14 + " requires " + c + " spans but GridLayoutManager has only " + this.f16173a + " spans.");
            }
        }
        if (i13 != 0) {
            if (i11 != 1) {
                i3 = i13 - 1;
                i2 = -1;
                i = -1;
            } else {
                i2 = i13;
                i3 = 0;
                i = 1;
            }
            int i15 = 0;
            while (i3 != i2) {
                View view = this.f16177y[i3];
                C0596vw vwVar = (C0596vw) view.getLayoutParams();
                int c2 = m15650c(ybVar2, yjVar2, m15975i(view));
                vwVar.f16167b = c2;
                vwVar.f16166a = i15;
                i15 += c2;
                i3 += i;
            }
            float f = 0.0f;
            int i16 = 0;
            for (int i17 = 0; i17 < i13; i17++) {
                View view2 = this.f16177y[i17];
                if (wdVar2.f16216l != null) {
                    if (i11 != 1) {
                        mo10568a(view2, 0);
                    } else {
                        mo10567a(view2);
                    }
                } else if (i11 != 1) {
                    mo10577b(view2, 0);
                } else {
                    mo10576b(view2);
                }
                Rect rect = this.f16172B;
                RecyclerView recyclerView = this.f16299j;
                if (recyclerView != null) {
                    rect.set(recyclerView.getItemDecorInsetsForChild(view2));
                    z = false;
                } else {
                    z = false;
                    rect.set(0, 0, 0, 0);
                }
                m15648a(view2, g, z);
                int a2 = this.f16224d.mo10512a(view2);
                if (a2 > i16) {
                    i16 = a2;
                }
                float b = ((float) this.f16224d.mo10515b(view2)) / ((float) ((C0596vw) view2.getLayoutParams()).f16167b);
                if (b > f) {
                    f = b;
                }
            }
            if (g != 1073741824) {
                m15652i(Math.max(Math.round(f * ((float) this.f16173a)), i10));
                i16 = 0;
                for (int i18 = 0; i18 < i13; i18++) {
                    View view3 = this.f16177y[i18];
                    m15648a(view3, 1073741824, true);
                    int a3 = this.f16224d.mo10512a(view3);
                    if (a3 > i16) {
                        i16 = a3;
                    }
                }
            }
            for (int i19 = 0; i19 < i13; i19++) {
                View view4 = this.f16177y[i19];
                if (this.f16224d.mo10512a(view4) != i16) {
                    C0596vw vwVar2 = (C0596vw) view4.getLayoutParams();
                    Rect rect2 = vwVar2.f16313d;
                    int i20 = rect2.top + rect2.bottom + vwVar2.topMargin + vwVar2.bottomMargin;
                    int i21 = rect2.left + rect2.right + vwVar2.leftMargin + vwVar2.rightMargin;
                    int d = m15651d(vwVar2.f16166a, vwVar2.f16167b);
                    if (this.f16223c == 1) {
                        i9 = m15965a(d, 1073741824, i21, vwVar2.width, false);
                        i8 = View.MeasureSpec.makeMeasureSpec(i16 - i20, 1073741824);
                    } else {
                        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i16 - i21, 1073741824);
                        i8 = m15965a(d, 1073741824, i20, vwVar2.height, false);
                        i9 = makeMeasureSpec;
                    }
                    m15647a(view4, i9, i8, true);
                }
            }
            wcVar2.f16201a = i16;
            if (this.f16223c == 1) {
                if (wdVar2.f16210f == -1) {
                    int i22 = wdVar2.f16206b;
                    i7 = i22 - i16;
                    i6 = i22;
                    i5 = 0;
                    i4 = 0;
                } else {
                    int i23 = wdVar2.f16206b;
                    i6 = i23 + i16;
                    i7 = i23;
                    i5 = 0;
                    i4 = 0;
                }
            } else if (wdVar2.f16210f == -1) {
                i4 = wdVar2.f16206b;
                i5 = i4 - i16;
                i7 = 0;
                i6 = 0;
            } else {
                int i24 = wdVar2.f16206b;
                i5 = i24;
                i6 = 0;
                i4 = i24 + i16;
                i7 = 0;
            }
            for (int i25 = 0; i25 < i13; i25++) {
                View view5 = this.f16177y[i25];
                C0596vw vwVar3 = (C0596vw) view5.getLayoutParams();
                if (this.f16223c != 1) {
                    i7 = mo10587t() + this.f16176x[vwVar3.f16166a];
                    i6 = this.f16224d.mo10515b(view5) + i7;
                } else if (mo10478k()) {
                    int s = mo10586s() + this.f16176x[this.f16173a - vwVar3.f16166a];
                    i4 = s;
                    i5 = s - this.f16224d.mo10515b(view5);
                } else {
                    i5 = mo10586s() + this.f16176x[vwVar3.f16166a];
                    i4 = this.f16224d.mo10515b(view5) + i5;
                }
                m15966a(view5, i5, i7, i4, i6);
                if (vwVar3.mo10594a() || vwVar3.mo10595b()) {
                    wcVar2.f16203c = true;
                }
                wcVar2.f16204d = view5.hasFocusable() | wcVar2.f16204d;
            }
            Arrays.fill(this.f16177y, (Object) null);
            return;
        }
        wcVar2.f16202b = true;
    }

    /* renamed from: a */
    private final void m15648a(View view, int i, boolean z) {
        int i2;
        int i3;
        C0596vw vwVar = (C0596vw) view.getLayoutParams();
        Rect rect = vwVar.f16313d;
        int i4 = rect.top + rect.bottom + vwVar.topMargin + vwVar.bottomMargin;
        int i5 = rect.left + rect.right + vwVar.leftMargin + vwVar.rightMargin;
        int d = m15651d(vwVar.f16166a, vwVar.f16167b);
        if (this.f16223c == 1) {
            i2 = m15965a(d, i, i5, vwVar.width, false);
            i3 = m15965a(this.f16224d.mo10518d(), this.f16309t, i4, vwVar.height, true);
        } else {
            int a = m15965a(d, i, i4, vwVar.height, false);
            int a2 = m15965a(this.f16224d.mo10518d(), this.f16308s, i5, vwVar.width, true);
            i3 = a;
            i2 = a2;
        }
        m15647a(view, i2, i3, z);
    }

    /* renamed from: a */
    private final void m15647a(View view, int i, int i2, boolean z) {
        C0648xu xuVar = (C0648xu) view.getLayoutParams();
        if (z) {
            if (this.f16304o && C0647xt.m15968b(view.getMeasuredWidth(), i, xuVar.width) && C0647xt.m15968b(view.getMeasuredHeight(), i2, xuVar.height)) {
                return;
            }
        } else if (!mo10573a(view, i, i2, xuVar)) {
            return;
        }
        view.measure(i, i2);
    }

    /* renamed from: a */
    public final void mo10428a(C0656yb ybVar, C0664yj yjVar, C0602wb wbVar, int i) {
        m15644A();
        if (yjVar.mo10626a() > 0 && !yjVar.f16364g) {
            int b = m15649b(ybVar, yjVar, wbVar.f16197b);
            if (i == 1) {
                while (b > 0) {
                    int i2 = wbVar.f16197b;
                    if (i2 <= 0) {
                        break;
                    }
                    int i3 = i2 - 1;
                    wbVar.f16197b = i3;
                    b = m15649b(ybVar, yjVar, i3);
                }
            } else {
                int a = yjVar.mo10626a() - 1;
                int i4 = wbVar.f16197b;
                while (i4 < a) {
                    int i5 = i4 + 1;
                    int b2 = m15649b(ybVar, yjVar, i5);
                    if (b2 <= b) {
                        break;
                    }
                    i4 = i5;
                    b = b2;
                }
                wbVar.f16197b = i4;
            }
        }
        m15645B();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0019, code lost:
        if (r0.f16298i.mo10318c(r3) == false) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0129, code lost:
        if (r13 != (r2 > r9)) goto L_0x012c;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View mo10423a(android.view.View r23, int r24, p000.C0656yb r25, p000.C0664yj r26) {
        /*
            r22 = this;
            r0 = r22
            r1 = r25
            r2 = r26
            android.support.v7.widget.RecyclerView r3 = r0.f16299j
            r4 = 0
            if (r3 == 0) goto L_0x001d
            r5 = r23
            android.view.View r3 = r3.findContainingItemView(r5)
            if (r3 == 0) goto L_0x001f
            uq r6 = r0.f16298i
            boolean r6 = r6.mo10318c((android.view.View) r3)
            if (r6 != 0) goto L_0x001c
            goto L_0x0020
        L_0x001c:
            goto L_0x001f
        L_0x001d:
            r5 = r23
        L_0x001f:
            r3 = r4
        L_0x0020:
            if (r3 == 0) goto L_0x017a
            android.view.ViewGroup$LayoutParams r6 = r3.getLayoutParams()
            vw r6 = (p000.C0596vw) r6
            int r7 = r6.f16166a
            int r6 = r6.f16167b
            int r6 = r6 + r7
            android.view.View r5 = super.mo10423a((android.view.View) r23, (int) r24, (p000.C0656yb) r25, (p000.C0664yj) r26)
            if (r5 == 0) goto L_0x0179
            r5 = r24
            int r5 = r0.mo10471e((int) r5)
            r8 = 1
            if (r5 == r8) goto L_0x003e
            r5 = 0
            goto L_0x0040
        L_0x003e:
            r5 = 1
        L_0x0040:
            boolean r10 = r0.f16225e
            r11 = -1
            if (r5 != r10) goto L_0x004d
            int r5 = r22.mo10585r()
            r10 = r5
            r5 = 0
            r12 = 1
            goto L_0x0054
        L_0x004d:
            int r5 = r22.mo10585r()
            int r5 = r5 + r11
            r10 = -1
            r12 = -1
        L_0x0054:
            int r13 = r0.f16223c
            if (r13 != r8) goto L_0x0062
            boolean r13 = r22.mo10478k()
            if (r13 == 0) goto L_0x0061
            r13 = 1
            goto L_0x0063
        L_0x0061:
        L_0x0062:
            r13 = 0
        L_0x0063:
            int r14 = r0.m15646a((p000.C0656yb) r1, (p000.C0664yj) r2, (int) r5)
            r11 = r5
            r9 = -1
            r15 = -1
            r16 = 0
            r17 = 0
            r5 = r4
        L_0x006f:
            if (r11 == r10) goto L_0x0175
            int r8 = r0.m15646a((p000.C0656yb) r1, (p000.C0664yj) r2, (int) r11)
            android.view.View r1 = r0.mo10582h((int) r11)
            if (r1 == r3) goto L_0x0175
            boolean r18 = r1.hasFocusable()
            if (r18 == 0) goto L_0x0095
            if (r8 == r14) goto L_0x0095
            if (r4 == 0) goto L_0x0087
            goto L_0x0175
        L_0x0087:
            r18 = r3
            r21 = r10
            r19 = r14
        L_0x008d:
            r0 = r16
            r14 = r17
            r20 = 1
            goto L_0x012c
        L_0x0095:
            android.view.ViewGroup$LayoutParams r8 = r1.getLayoutParams()
            vw r8 = (p000.C0596vw) r8
            int r2 = r8.f16166a
            r18 = r3
            int r3 = r8.f16167b
            int r3 = r3 + r2
            boolean r19 = r1.hasFocusable()
            if (r19 != 0) goto L_0x00a9
            goto L_0x00ae
        L_0x00a9:
            if (r2 != r7) goto L_0x00ae
            if (r3 != r6) goto L_0x00ae
            return r1
        L_0x00ae:
            boolean r19 = r1.hasFocusable()
            if (r19 != 0) goto L_0x00b5
            goto L_0x00b7
        L_0x00b5:
            if (r4 == 0) goto L_0x0131
        L_0x00b7:
            boolean r19 = r1.hasFocusable()
            if (r19 == 0) goto L_0x00be
            goto L_0x00cc
        L_0x00be:
            if (r5 != 0) goto L_0x00cc
            r21 = r10
            r19 = r14
            r0 = r16
            r14 = r17
            r20 = 1
            goto L_0x013b
        L_0x00cc:
            int r19 = java.lang.Math.max(r2, r7)
            int r20 = java.lang.Math.min(r3, r6)
            r21 = r10
            int r10 = r20 - r19
            boolean r19 = r1.hasFocusable()
            if (r19 != 0) goto L_0x0117
            r19 = r14
            if (r4 == 0) goto L_0x00e3
            goto L_0x008d
        L_0x00e3:
            zr r14 = r0.f16300k
            boolean r14 = r14.mo10750a(r1)
            if (r14 == 0) goto L_0x00f6
            zr r14 = r0.f16301l
            boolean r14 = r14.mo10750a(r1)
            if (r14 == 0) goto L_0x00f5
            r14 = 1
            goto L_0x00f7
        L_0x00f5:
        L_0x00f6:
            r14 = 0
        L_0x00f7:
            r20 = 1
            r14 = r14 ^ 1
            if (r14 == 0) goto L_0x0112
            r14 = r17
            if (r10 <= r14) goto L_0x0104
            r0 = r16
            goto L_0x013b
        L_0x0104:
            if (r10 != r14) goto L_0x0114
            if (r2 > r15) goto L_0x010a
            r10 = 0
            goto L_0x010c
        L_0x010a:
            r10 = 1
        L_0x010c:
            if (r13 == r10) goto L_0x010f
            goto L_0x0114
        L_0x010f:
            r0 = r16
            goto L_0x013b
        L_0x0112:
            r14 = r17
        L_0x0114:
            r0 = r16
            goto L_0x012c
        L_0x0117:
            r19 = r14
            r14 = r17
            r20 = 1
            r0 = r16
            if (r10 > r0) goto L_0x013b
            if (r10 != r0) goto L_0x012b
            if (r2 > r9) goto L_0x0127
            r10 = 0
            goto L_0x0129
        L_0x0127:
            r10 = 1
        L_0x0129:
            if (r13 == r10) goto L_0x013b
        L_0x012b:
        L_0x012c:
            r16 = r0
            r17 = r14
            goto L_0x0164
        L_0x0131:
            r21 = r10
            r19 = r14
            r0 = r16
            r14 = r17
            r20 = 1
        L_0x013b:
            boolean r10 = r1.hasFocusable()
            if (r10 == 0) goto L_0x0153
            int r0 = r8.f16166a
            int r3 = java.lang.Math.min(r3, r6)
            int r2 = java.lang.Math.max(r2, r7)
            int r16 = r3 - r2
            r9 = r0
            r4 = r1
            r17 = r14
            goto L_0x0164
        L_0x0153:
            int r5 = r8.f16166a
            int r3 = java.lang.Math.min(r3, r6)
            int r2 = java.lang.Math.max(r2, r7)
            int r17 = r3 - r2
            r16 = r0
            r15 = r5
            r5 = r1
        L_0x0164:
            int r11 = r11 + r12
            r0 = r22
            r1 = r25
            r2 = r26
            r3 = r18
            r14 = r19
            r10 = r21
            r8 = 1
            goto L_0x006f
        L_0x0175:
            if (r4 != 0) goto L_0x0178
            return r5
        L_0x0178:
            return r4
        L_0x0179:
        L_0x017a:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0598vy.mo10423a(android.view.View, int, yb, yj):android.view.View");
    }

    /* renamed from: a */
    public final void mo10427a(C0656yb ybVar, C0664yj yjVar, View view, C0354mx mxVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof C0596vw)) {
            super.mo10570a(view, mxVar);
            return;
        }
        C0596vw vwVar = (C0596vw) layoutParams;
        int a = m15646a(ybVar, yjVar, vwVar.mo10596c());
        if (this.f16223c == 0) {
            mxVar.mo9429b((Object) C0353mw.m14775a(vwVar.f16166a, vwVar.f16167b, a, 1, false, false));
            return;
        }
        mxVar.mo9429b((Object) C0353mw.m14775a(a, 1, vwVar.f16166a, vwVar.f16167b, false, false));
    }

    /* renamed from: c */
    public final void mo10435c() {
        this.f16174b.mo10416a();
        this.f16174b.mo10418b();
    }

    /* renamed from: d */
    public final void mo10436d() {
        this.f16174b.mo10416a();
        this.f16174b.mo10418b();
    }

    /* renamed from: e */
    public final void mo10437e() {
        this.f16174b.mo10416a();
        this.f16174b.mo10418b();
    }

    /* renamed from: f */
    public final void mo10438f() {
        this.f16174b.mo10416a();
        this.f16174b.mo10418b();
    }

    /* renamed from: g */
    public final void mo10439g() {
        this.f16174b.mo10416a();
        this.f16174b.mo10418b();
    }

    /* renamed from: c */
    public void mo4533c(C0656yb ybVar, C0664yj yjVar) {
        if (yjVar.f16364g) {
            int r = mo10585r();
            for (int i = 0; i < r; i++) {
                C0596vw vwVar = (C0596vw) mo10582h(i).getLayoutParams();
                int c = vwVar.mo10596c();
                this.f16178z.put(c, vwVar.f16167b);
                this.f16171A.put(c, vwVar.f16166a);
            }
        }
        super.mo4533c(ybVar, yjVar);
        this.f16178z.clear();
        this.f16171A.clear();
    }

    /* renamed from: a */
    public final void mo10430a(C0664yj yjVar) {
        super.mo10430a(yjVar);
        this.f16175w = false;
    }

    /* renamed from: a */
    public final int mo10421a(int i, C0656yb ybVar, C0664yj yjVar) {
        m15644A();
        m15645B();
        return super.mo10421a(i, ybVar, yjVar);
    }

    /* renamed from: b */
    public final int mo10433b(int i, C0656yb ybVar, C0664yj yjVar) {
        m15644A();
        m15645B();
        return super.mo10433b(i, ybVar, yjVar);
    }

    /* renamed from: a */
    public final void mo10426a(Rect rect, int i, int i2) {
        int i3;
        int i4;
        if (this.f16176x == null) {
            super.mo10426a(rect, i, i2);
        }
        int s = mo10586s() + mo10588u();
        int t = mo10587t() + mo10589v();
        if (this.f16223c == 1) {
            i4 = m15964a(i2, rect.height() + t, mo10593z());
            int[] iArr = this.f16176x;
            i3 = m15964a(i, iArr[iArr.length - 1] + s, mo10592y());
        } else {
            i3 = m15964a(i, rect.width() + s, mo10592y());
            int[] iArr2 = this.f16176x;
            i4 = m15964a(i2, iArr2[iArr2.length - 1] + t, mo10593z());
        }
        mo10579c(i3, i4);
    }

    /* renamed from: a */
    public final void mo10425a(int i) {
        if (i != this.f16173a) {
            this.f16175w = true;
            if (i > 0) {
                this.f16173a = i;
                this.f16174b.mo10416a();
                mo10583p();
                return;
            }
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + i);
        }
    }

    /* renamed from: A */
    private final void m15644A() {
        int i;
        if (this.f16223c == 1) {
            i = (this.f16310u - mo10588u()) - mo10586s();
        } else {
            i = (this.f16311v - mo10589v()) - mo10587t();
        }
        m15652i(i);
    }
}
