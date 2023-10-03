package p000;

import android.support.p002v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: eip */
/* compiled from: PG */
public final class eip {

    /* renamed from: a */
    public final eik f8366a;

    /* renamed from: b */
    private boolean[] f8367b;

    public eip(eik eik) {
        this.f8366a = eik;
    }

    /* renamed from: a */
    private final void m7580a(List list, eim eim, int i) {
        eim.f8359m = i;
        this.f8366a.mo3466a(eim);
        list.add(eim);
    }

    /* JADX WARNING: Removed duplicated region for block: B:75:0x02a7  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x02ac  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x02d1  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x030a  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo4854a(p000.ein r29, int r30, int r31) {
        /*
            r28 = this;
            r0 = r28
            r1 = r29
            r2 = r30
            r3 = r31
            eik r4 = r0.f8366a
            boolean r4 = r4.mo3476f()
            int r5 = android.view.View.MeasureSpec.getMode(r30)
            int r6 = android.view.View.MeasureSpec.getSize(r30)
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r1.f8362a = r7
            if (r4 == 0) goto L_0x0026
            eik r8 = r0.f8366a
            int r8 = r8.getPaddingStart()
            goto L_0x002c
        L_0x0026:
            eik r8 = r0.f8366a
            int r8 = r8.getPaddingTop()
        L_0x002c:
            if (r4 != 0) goto L_0x0035
            eik r9 = r0.f8366a
            int r9 = r9.getPaddingBottom()
            goto L_0x003b
        L_0x0035:
            eik r9 = r0.f8366a
            int r9 = r9.getPaddingEnd()
        L_0x003b:
            if (r4 != 0) goto L_0x0044
            eik r10 = r0.f8366a
            int r10 = r10.getPaddingStart()
            goto L_0x004a
        L_0x0044:
            eik r10 = r0.f8366a
            int r10 = r10.getPaddingTop()
        L_0x004a:
            if (r4 != 0) goto L_0x0053
            eik r11 = r0.f8366a
            int r11 = r11.getPaddingEnd()
            goto L_0x0059
        L_0x0053:
            eik r11 = r0.f8366a
            int r11 = r11.getPaddingBottom()
        L_0x0059:
            eim r12 = new eim
            r12.<init>()
            r13 = 0
            r12.f8361o = r13
            int r8 = r8 + r9
            r12.f8351e = r8
            eik r9 = r0.f8366a
            int r9 = r9.mo3461a()
            r1 = 0
            r14 = 0
            r15 = 0
            r16 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x006f:
            if (r15 >= r9) goto L_0x032d
            r18 = r1
            eik r1 = r0.f8366a
            android.view.View r1 = r1.mo3471b(r15)
            if (r1 != 0) goto L_0x008b
            boolean r1 = m7581a((int) r15, (int) r9, (p000.eim) r12)
            if (r1 == 0) goto L_0x0085
            r0.m7580a((java.util.List) r7, (p000.eim) r12, (int) r13)
            goto L_0x0086
        L_0x0085:
        L_0x0086:
            r20 = r8
            r19 = r14
            goto L_0x00ae
        L_0x008b:
            r19 = r14
            int r14 = r1.getVisibility()
            r2 = 8
            r20 = r8
            r8 = 1
            if (r14 != r2) goto L_0x00bd
            int r1 = r12.f8355i
            int r1 = r1 + r8
            r12.f8355i = r1
            int r1 = r12.f8354h
            int r1 = r1 + r8
            r12.f8354h = r1
            boolean r1 = m7581a((int) r15, (int) r9, (p000.eim) r12)
            if (r1 == 0) goto L_0x00ad
            r0.m7580a((java.util.List) r7, (p000.eim) r12, (int) r13)
            goto L_0x00ae
        L_0x00ad:
        L_0x00ae:
            r1 = r9
            r23 = r10
            r14 = r19
            r8 = r20
            r10 = r6
            r19 = r11
            r11 = r15
            r15 = r30
            goto L_0x0319
        L_0x00bd:
            android.view.ViewGroup$LayoutParams r2 = r1.getLayoutParams()
            eil r2 = (p000.eil) r2
            int r14 = r2.mo4832f()
            r8 = 4
            if (r14 != r8) goto L_0x00d3
            java.util.List r8 = r12.f8360n
            java.lang.Integer r14 = java.lang.Integer.valueOf(r15)
            r8.add(r14)
        L_0x00d3:
            if (r4 != 0) goto L_0x00da
            int r8 = r2.mo4828b()
            goto L_0x00de
        L_0x00da:
            int r8 = r2.mo4827a()
        L_0x00de:
            float r14 = r2.mo4838l()
            r22 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r14 = (r14 > r22 ? 1 : (r14 == r22 ? 0 : -1))
            if (r14 == 0) goto L_0x00f7
            r14 = 1073741824(0x40000000, float:2.0)
            if (r5 != r14) goto L_0x00f7
            float r8 = (float) r6
            float r14 = r2.mo4838l()
            float r8 = r8 * r14
            int r8 = java.lang.Math.round(r8)
        L_0x00f7:
            if (r4 != 0) goto L_0x0135
            eik r14 = r0.f8366a
            r22 = r9
            r9 = 0
            int r17 = m7587c(r2, r9)
            int r23 = m7575a((p000.eil) r2, (boolean) r9)
            r24 = r6
            int r6 = m7589e(r2, r9)
            int r25 = r10 + r11
            int r25 = r25 + r17
            int r25 = r25 + r23
            int r9 = r25 + r13
            int r6 = r14.mo3463a((int) r3, (int) r9, (int) r6)
            eik r9 = r0.f8366a
            r14 = 0
            int r17 = m7588d(r2, r14)
            int r17 = r20 + r17
            int r23 = m7584b((p000.eil) r2, (boolean) r14)
            int r14 = r17 + r23
            r17 = r15
            r15 = r30
            int r8 = r9.mo3470b(r15, r14, r8)
            r1.measure(r6, r8)
            r23 = r10
            goto L_0x016e
        L_0x0135:
            r24 = r6
            r22 = r9
            r17 = r15
            r15 = r30
            eik r6 = r0.f8366a
            r9 = 1
            int r14 = m7588d(r2, r9)
            int r14 = r20 + r14
            int r21 = m7584b((p000.eil) r2, (boolean) r9)
            int r14 = r14 + r21
            int r8 = r6.mo3463a((int) r15, (int) r14, (int) r8)
            eik r6 = r0.f8366a
            int r14 = r10 + r11
            int r21 = m7587c(r2, r9)
            int r14 = r14 + r21
            int r21 = m7575a((p000.eil) r2, (boolean) r9)
            int r14 = r14 + r21
            int r14 = r14 + r13
            r23 = r10
            int r10 = m7589e(r2, r9)
            int r6 = r6.mo3470b(r3, r14, r10)
            r1.measure(r8, r6)
        L_0x016e:
            m7578a((android.view.View) r1)
            int r6 = r1.getMeasuredState()
            r9 = r19
            int r14 = android.view.View.combineMeasuredStates(r9, r6)
            int r6 = r12.f8351e
            int r9 = m7574a((android.view.View) r1, (boolean) r4)
            int r10 = m7588d(r2, r4)
            int r9 = r9 + r10
            int r10 = m7584b((p000.eil) r2, (boolean) r4)
            int r9 = r9 + r10
            int r10 = r7.size()
            r19 = r11
            eik r11 = r0.f8366a
            int r11 = r11.mo3472c()
            if (r11 == 0) goto L_0x0261
            boolean r11 = r2.mo4837k()
            r26 = r14
            r14 = -1
            if (r11 != 0) goto L_0x01e0
            if (r5 != 0) goto L_0x01af
            r11 = r17
            r14 = r18
            r8 = r20
            r10 = r24
            r6 = 1
            goto L_0x026c
        L_0x01af:
            eik r11 = r0.f8366a
            int r11 = r11.mo3481i()
            if (r11 != r14) goto L_0x01b8
            goto L_0x01c8
        L_0x01b8:
            r21 = 1
            int r10 = r10 + 1
            if (r11 > r10) goto L_0x01c8
            r11 = r17
            r14 = r18
            r8 = r20
            r10 = r24
            goto L_0x01dd
        L_0x01c8:
            eik r10 = r0.f8366a
            r11 = r17
            r14 = r18
            int r10 = r10.mo3462a(r11, r14)
            if (r10 <= 0) goto L_0x01d5
            int r9 = r9 + r10
        L_0x01d5:
            int r6 = r6 + r9
            r10 = r24
            if (r10 >= r6) goto L_0x01db
            goto L_0x01e4
        L_0x01db:
            r8 = r20
        L_0x01dd:
            r6 = 1
            goto L_0x026c
        L_0x01e0:
            r11 = r17
            r10 = r24
        L_0x01e4:
            int r6 = r12.mo4843a()
            if (r6 <= 0) goto L_0x01f1
            r0.m7580a((java.util.List) r7, (p000.eim) r12, (int) r13)
            int r6 = r12.f8353g
            int r13 = r13 + r6
            goto L_0x01f2
        L_0x01f1:
        L_0x01f2:
            if (r4 != 0) goto L_0x0222
            int r6 = r2.mo4827a()
            r9 = -1
            if (r6 != r9) goto L_0x024f
            eik r6 = r0.f8366a
            int r9 = r6.getPaddingLeft()
            eik r12 = r0.f8366a
            int r12 = r12.getPaddingRight()
            int r9 = r9 + r12
            int r12 = r2.mo4839m()
            int r9 = r9 + r12
            int r12 = r2.mo4841o()
            int r9 = r9 + r12
            int r9 = r9 + r13
            int r12 = r2.mo4827a()
            int r6 = r6.mo3463a((int) r3, (int) r9, (int) r12)
            r1.measure(r6, r8)
            m7578a((android.view.View) r1)
            goto L_0x024f
        L_0x0222:
            int r6 = r2.mo4828b()
            r9 = -1
            if (r6 != r9) goto L_0x024f
            eik r6 = r0.f8366a
            int r9 = r6.getPaddingTop()
            eik r12 = r0.f8366a
            int r12 = r12.getPaddingBottom()
            int r9 = r9 + r12
            int r12 = r2.mo4840n()
            int r9 = r9 + r12
            int r12 = r2.mo4842p()
            int r9 = r9 + r12
            int r9 = r9 + r13
            int r12 = r2.mo4828b()
            int r6 = r6.mo3470b(r3, r9, r12)
            r1.measure(r8, r6)
            m7578a((android.view.View) r1)
        L_0x024f:
            eim r12 = new eim
            r12.<init>()
            r6 = 1
            r12.f8354h = r6
            r8 = r20
            r12.f8351e = r8
            r12.f8361o = r11
            r6 = 0
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x0275
        L_0x0261:
            r26 = r14
            r11 = r17
            r14 = r18
            r8 = r20
            r10 = r24
            r6 = 1
        L_0x026c:
            int r9 = r12.f8354h
            int r9 = r9 + r6
            r12.f8354h = r9
            int r6 = r14 + 1
            r9 = r16
        L_0x0275:
            int r14 = r12.f8351e
            int r16 = m7574a((android.view.View) r1, (boolean) r4)
            int r17 = m7588d(r2, r4)
            int r16 = r16 + r17
            int r17 = m7584b((p000.eil) r2, (boolean) r4)
            int r16 = r16 + r17
            int r14 = r14 + r16
            r12.f8351e = r14
            float r14 = r12.f8356j
            float r16 = r2.mo4830d()
            float r14 = r14 + r16
            r12.f8356j = r14
            float r14 = r12.f8357k
            float r16 = r2.mo4831e()
            float r14 = r14 + r16
            r12.f8357k = r14
            eik r14 = r0.f8366a
            r14.mo3465a((int) r11, (int) r6, (p000.eim) r12)
            if (r4 == 0) goto L_0x02ac
            int r14 = r1.getMeasuredHeight()
            goto L_0x02b0
        L_0x02ac:
            int r14 = r1.getMeasuredWidth()
        L_0x02b0:
            int r16 = m7587c(r2, r4)
            int r14 = r14 + r16
            int r16 = m7575a((p000.eil) r2, (boolean) r4)
            int r14 = r14 + r16
            eik r3 = r0.f8366a
            int r3 = r3.mo3483k()
            int r14 = r14 + r3
            int r3 = java.lang.Math.max(r9, r14)
            int r9 = r12.f8353g
            int r9 = java.lang.Math.max(r9, r3)
            r12.f8353g = r9
            if (r4 == 0) goto L_0x0302
            eik r9 = r0.f8366a
            int r9 = r9.mo3472c()
            r14 = 2
            if (r9 == r14) goto L_0x02ec
            int r9 = r12.f8358l
            int r1 = r1.getBaseline()
            int r2 = r2.mo4840n()
            int r1 = r1 + r2
            int r1 = java.lang.Math.max(r9, r1)
            r12.f8358l = r1
            goto L_0x0302
        L_0x02ec:
            int r9 = r12.f8358l
            int r14 = r1.getMeasuredHeight()
            int r1 = r1.getBaseline()
            int r14 = r14 - r1
            int r1 = r2.mo4842p()
            int r14 = r14 + r1
            int r1 = java.lang.Math.max(r9, r14)
            r12.f8358l = r1
        L_0x0302:
            r1 = r22
            boolean r2 = m7581a((int) r11, (int) r1, (p000.eim) r12)
            if (r2 == 0) goto L_0x0311
            r0.m7580a((java.util.List) r7, (p000.eim) r12, (int) r13)
            int r2 = r12.f8353g
            int r13 = r13 + r2
            goto L_0x0312
        L_0x0311:
        L_0x0312:
            r16 = r3
            r18 = r6
            r14 = r26
        L_0x0319:
            int r2 = r11 + 1
            r3 = r31
            r9 = r1
            r6 = r10
            r1 = r18
            r11 = r19
            r10 = r23
            r27 = r15
            r15 = r2
            r2 = r27
            goto L_0x006f
        L_0x032d:
            r9 = r14
            r1 = r29
            r1.f8363b = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.eip.mo4854a(ein, int, int):void");
    }

    /* renamed from: a */
    private static final void m7578a(View view) {
        boolean z;
        eil eil = (eil) view.getLayoutParams();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        boolean z2 = true;
        if (measuredWidth < eil.mo4833g()) {
            measuredWidth = eil.mo4833g();
            z = true;
        } else if (measuredWidth > eil.mo4835i()) {
            measuredWidth = eil.mo4835i();
            z = true;
        } else {
            z = false;
        }
        if (measuredHeight < eil.mo4834h()) {
            measuredHeight = eil.mo4834h();
        } else if (measuredHeight > eil.mo4836j()) {
            measuredHeight = eil.mo4836j();
        } else {
            z2 = z;
        }
        if (z2) {
            view.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
        }
    }

    /* renamed from: a */
    private static final List m7576a(List list, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        eim eim = new eim();
        eim.f8353g = (i - i2) / 2;
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            if (i3 == 0) {
                arrayList.add(eim);
            }
            arrayList.add((eim) list.get(i3));
            if (i3 == list.size() - 1) {
                arrayList.add(eim);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public final List mo4848a(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            eio eio = new eio((byte[]) null);
            eio.f8365b = ((eil) this.f8366a.mo3464a(i2).getLayoutParams()).mo4829c();
            eio.f8364a = i2;
            arrayList.add(eio);
        }
        return arrayList;
    }

    /* renamed from: a */
    public final void mo4851a(int i, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int b = this.f8366a.mo3469b();
        if (b == 0 || b == 1) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            i4 = mode;
            i5 = size;
        } else if (b == 2 || b == 3) {
            i4 = View.MeasureSpec.getMode(i);
            i5 = View.MeasureSpec.getSize(i);
        } else {
            throw new IllegalArgumentException("Invalid flex direction: " + b);
        }
        List<eim> j = this.f8366a.mo3482j();
        if (i4 == 1073741824) {
            int h = this.f8366a.mo3480h() + i3;
            int i7 = 0;
            if (j.size() == 1) {
                ((eim) j.get(0)).f8353g = i5 - i3;
            } else if (j.size() >= 2) {
                int d = this.f8366a.mo3474d();
                if (d == 1) {
                    eim eim = new eim();
                    eim.f8353g = i5 - h;
                    j.add(0, eim);
                } else if (d == 2) {
                    this.f8366a.mo3467a(m7576a((List) j, i5, h));
                } else if (d != 3) {
                    if (d != 4) {
                        if (d == 5 && h < i5) {
                            float size2 = ((float) (i5 - h)) / ((float) j.size());
                            int size3 = j.size();
                            float f = 0.0f;
                            while (i7 < size3) {
                                eim eim2 = (eim) j.get(i7);
                                float f2 = ((float) eim2.f8353g) + size2;
                                if (i7 == j.size() - 1) {
                                    f2 += f;
                                    f = 0.0f;
                                }
                                int round = Math.round(f2);
                                f += f2 - ((float) round);
                                if (f > 1.0f) {
                                    round++;
                                    f -= 4.0f;
                                } else if (f < -1.0f) {
                                    round--;
                                    f += 1.0f;
                                }
                                eim2.f8353g = round;
                                i7++;
                            }
                        }
                    } else if (h < i5) {
                        int size4 = j.size();
                        int i8 = (i5 - h) / (size4 + size4);
                        ArrayList arrayList = new ArrayList();
                        eim eim3 = new eim();
                        eim3.f8353g = i8;
                        for (eim add : j) {
                            arrayList.add(eim3);
                            arrayList.add(add);
                            arrayList.add(eim3);
                        }
                        this.f8366a.mo3467a((List) arrayList);
                    } else {
                        this.f8366a.mo3467a(m7576a((List) j, i5, h));
                    }
                } else if (h < i5) {
                    float size5 = ((float) (i5 - h)) / ((float) (j.size() - 1));
                    ArrayList arrayList2 = new ArrayList();
                    int size6 = j.size();
                    float f3 = 0.0f;
                    while (i7 < size6) {
                        arrayList2.add((eim) j.get(i7));
                        if (i7 != j.size() - 1) {
                            eim eim4 = new eim();
                            if (i7 == j.size() - 2) {
                                int round2 = Math.round(f3 + size5);
                                eim4.f8353g = round2;
                                i6 = round2;
                                f3 = 0.0f;
                            } else {
                                i6 = Math.round(size5);
                                eim4.f8353g = i6;
                            }
                            f3 += size5 - ((float) i6);
                            if (f3 > 1.0f) {
                                eim4.f8353g = i6 + 1;
                                f3 -= 4.0f;
                            } else if (f3 < -1.0f) {
                                eim4.f8353g = i6 - 1;
                                f3 += 1.0f;
                            }
                            arrayList2.add(eim4);
                        }
                        i7++;
                    }
                    this.f8366a.mo3467a((List) arrayList2);
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo4850a(int i, int i2) {
        int i3;
        int i4;
        int a = this.f8366a.mo3461a();
        boolean[] zArr = this.f8367b;
        if (zArr != null) {
            int length = zArr.length;
            if (length < a) {
                int i5 = length + length;
                if (i5 >= a) {
                    a = i5;
                }
                this.f8367b = new boolean[a];
            } else {
                Arrays.fill(zArr, false);
            }
        } else {
            if (a < 10) {
                a = 10;
            }
            this.f8367b = new boolean[a];
        }
        if (this.f8366a.mo3461a() > 0) {
            int b = this.f8366a.mo3469b();
            int b2 = this.f8366a.mo3469b();
            if (b2 == 0 || b2 == 1) {
                int mode = View.MeasureSpec.getMode(i);
                int size = View.MeasureSpec.getSize(i);
                if (mode != 1073741824) {
                    size = this.f8366a.mo3477g();
                }
                i4 = this.f8366a.getPaddingLeft() + this.f8366a.getPaddingRight();
            } else if (b2 == 2 || b2 == 3) {
                int mode2 = View.MeasureSpec.getMode(i2);
                i3 = View.MeasureSpec.getSize(i2);
                if (mode2 != 1073741824) {
                    i3 = this.f8366a.mo3477g();
                }
                i4 = this.f8366a.getPaddingTop() + this.f8366a.getPaddingBottom();
            } else {
                throw new IllegalArgumentException("Invalid flex direction: " + b);
            }
            List j = this.f8366a.mo3482j();
            int size2 = j.size();
            for (int i6 = 0; i6 < size2; i6++) {
                eim eim = (eim) j.get(i6);
                if (eim.f8351e < i3) {
                    m7577a(i, i2, eim, i3, i4, false);
                } else {
                    m7585b(i, i2, eim, i3, i4, false);
                }
            }
        }
    }

    /* renamed from: a */
    private final void m7577a(int i, int i2, eim eim, int i3, int i4, boolean z) {
        int i5;
        int i6;
        double d;
        double d2;
        eim eim2 = eim;
        int i7 = i3;
        float f = eim2.f8356j;
        float f2 = 0.0f;
        if (f > 0.0f && i7 >= (i5 = eim2.f8351e)) {
            float f3 = ((float) (i7 - i5)) / f;
            eim2.f8351e = i4 + eim2.f8352f;
            int i8 = 0;
            if (!z) {
                eim2.f8353g = RecyclerView.UNDEFINED_DURATION;
            }
            boolean z2 = false;
            int i9 = 0;
            float f4 = 0.0f;
            while (i8 < eim2.f8354h) {
                int i10 = eim2.f8361o + i8;
                View b = this.f8366a.mo3471b(i10);
                if (b == null) {
                    int i11 = i2;
                } else if (b.getVisibility() != 8) {
                    eil eil = (eil) b.getLayoutParams();
                    int b2 = this.f8366a.mo3469b();
                    if (b2 == 0 || b2 == 1) {
                        int measuredWidth = b.getMeasuredWidth();
                        int measuredHeight = b.getMeasuredHeight();
                        if (this.f8367b[i10]) {
                            int i12 = i2;
                        } else if (eil.mo4830d() > 0.0f) {
                            float d3 = ((float) measuredWidth) + (eil.mo4830d() * f3);
                            if (i8 == eim2.f8354h - 1) {
                                d3 += f4;
                                f4 = 0.0f;
                            }
                            int round = Math.round(d3);
                            if (round > eil.mo4835i()) {
                                round = eil.mo4835i();
                                this.f8367b[i10] = true;
                                eim2.f8356j -= eil.mo4830d();
                                z2 = true;
                            } else {
                                f4 += d3 - ((float) round);
                                double d4 = (double) f4;
                                if (d4 > 1.0d) {
                                    round++;
                                    Double.isNaN(d4);
                                    d = d4 - 4.0d;
                                } else if (d4 < -1.0d) {
                                    round--;
                                    Double.isNaN(d4);
                                    d = d4 + 1.0d;
                                }
                                f4 = (float) d;
                            }
                            b.measure(View.MeasureSpec.makeMeasureSpec(round, 1073741824), m7583b(i2, eil, eim2.f8359m));
                            measuredWidth = b.getMeasuredWidth();
                            measuredHeight = b.getMeasuredHeight();
                        } else {
                            int i13 = i2;
                        }
                        int max = Math.max(i9, measuredHeight + eil.mo4840n() + eil.mo4842p() + this.f8366a.mo3483k());
                        eim2.f8351e += measuredWidth + eil.mo4839m() + eil.mo4841o();
                        i6 = max;
                    } else {
                        int measuredHeight2 = b.getMeasuredHeight();
                        int measuredWidth2 = b.getMeasuredWidth();
                        if (this.f8367b[i10]) {
                            int i14 = i;
                        } else if (eil.mo4830d() > f2) {
                            float d5 = ((float) measuredHeight2) + (eil.mo4830d() * f3);
                            if (i8 == eim2.f8354h - 1) {
                                d5 += f4;
                                f4 = 0.0f;
                            }
                            int round2 = Math.round(d5);
                            if (round2 > eil.mo4836j()) {
                                round2 = eil.mo4836j();
                                this.f8367b[i10] = true;
                                eim2.f8356j -= eil.mo4830d();
                                z2 = true;
                            } else {
                                f4 += d5 - ((float) round2);
                                double d6 = (double) f4;
                                if (d6 > 1.0d) {
                                    round2++;
                                    Double.isNaN(d6);
                                    d2 = d6 - 4.0d;
                                } else if (d6 < -1.0d) {
                                    round2--;
                                    Double.isNaN(d6);
                                    d2 = d6 + 1.0d;
                                }
                                f4 = (float) d2;
                            }
                            b.measure(m7573a(i, eil, eim2.f8359m), View.MeasureSpec.makeMeasureSpec(round2, 1073741824));
                            measuredWidth2 = b.getMeasuredWidth();
                            measuredHeight2 = b.getMeasuredHeight();
                        } else {
                            int i15 = i;
                        }
                        i6 = Math.max(i9, measuredWidth2 + eil.mo4839m() + eil.mo4841o() + this.f8366a.mo3483k());
                        eim2.f8351e += measuredHeight2 + eil.mo4840n() + eil.mo4842p();
                        int i16 = i2;
                    }
                    eim2.f8353g = Math.max(eim2.f8353g, i6);
                    i9 = i6;
                } else {
                    int i17 = i2;
                }
                i8++;
                f2 = 0.0f;
            }
            int i18 = i2;
            if (z2 && i5 != eim2.f8351e) {
                m7577a(i, i2, eim, i3, i4, true);
            }
        }
    }

    /* renamed from: b */
    private final int m7583b(int i, eil eil, int i2) {
        eik eik = this.f8366a;
        int b = eik.mo3470b(i, eik.getPaddingTop() + this.f8366a.getPaddingBottom() + eil.mo4840n() + eil.mo4842p() + i2, eil.mo4828b());
        int size = View.MeasureSpec.getSize(b);
        if (size > eil.mo4836j()) {
            return View.MeasureSpec.makeMeasureSpec(eil.mo4836j(), View.MeasureSpec.getMode(b));
        }
        if (size < eil.mo4834h()) {
            return View.MeasureSpec.makeMeasureSpec(eil.mo4834h(), View.MeasureSpec.getMode(b));
        }
        return b;
    }

    /* renamed from: a */
    private final int m7573a(int i, eil eil, int i2) {
        eik eik = this.f8366a;
        int a = eik.mo3463a(i, eik.getPaddingLeft() + this.f8366a.getPaddingRight() + eil.mo4839m() + eil.mo4841o() + i2, eil.mo4827a());
        int size = View.MeasureSpec.getSize(a);
        if (size > eil.mo4835i()) {
            return View.MeasureSpec.makeMeasureSpec(eil.mo4835i(), View.MeasureSpec.getMode(a));
        }
        if (size < eil.mo4833g()) {
            return View.MeasureSpec.makeMeasureSpec(eil.mo4833g(), View.MeasureSpec.getMode(a));
        }
        return a;
    }

    /* renamed from: a */
    private static final int m7575a(eil eil, boolean z) {
        if (z) {
            return eil.mo4842p();
        }
        return eil.mo4841o();
    }

    /* renamed from: b */
    private static final int m7584b(eil eil, boolean z) {
        if (z) {
            return eil.mo4841o();
        }
        return eil.mo4842p();
    }

    /* renamed from: c */
    private static final int m7587c(eil eil, boolean z) {
        if (z) {
            return eil.mo4840n();
        }
        return eil.mo4839m();
    }

    /* renamed from: d */
    private static final int m7588d(eil eil, boolean z) {
        if (z) {
            return eil.mo4839m();
        }
        return eil.mo4840n();
    }

    /* renamed from: e */
    private static final int m7589e(eil eil, boolean z) {
        if (z) {
            return eil.mo4828b();
        }
        return eil.mo4827a();
    }

    /* renamed from: a */
    private static final int m7574a(View view, boolean z) {
        if (z) {
            return view.getMeasuredWidth();
        }
        return view.getMeasuredHeight();
    }

    /* renamed from: a */
    private static final boolean m7581a(int i, int i2, eim eim) {
        return i == i2 + -1 && eim.mo4843a() != 0;
    }

    /* renamed from: a */
    public final void mo4852a(View view, eim eim, int i, int i2, int i3, int i4) {
        eil eil = (eil) view.getLayoutParams();
        int e = this.f8366a.mo3475e();
        if (eil.mo4832f() != -1) {
            e = eil.mo4832f();
        }
        int i5 = eim.f8353g;
        if (e != 0) {
            if (e != 1) {
                if (e == 2) {
                    int measuredHeight = (((i5 - view.getMeasuredHeight()) + eil.mo4840n()) - eil.mo4842p()) / 2;
                    if (this.f8366a.mo3472c() != 2) {
                        int i6 = i2 + measuredHeight;
                        view.layout(i, i6, i3, view.getMeasuredHeight() + i6);
                        return;
                    }
                    int i7 = i2 - measuredHeight;
                    view.layout(i, i7, i3, view.getMeasuredHeight() + i7);
                    return;
                } else if (e != 3) {
                    if (e != 4) {
                        return;
                    }
                } else if (this.f8366a.mo3472c() != 2) {
                    int max = Math.max(eim.f8358l - view.getBaseline(), eil.mo4840n());
                    view.layout(i, i2 + max, i3, i4 + max);
                    return;
                } else {
                    int max2 = Math.max((eim.f8358l - view.getMeasuredHeight()) + view.getBaseline(), eil.mo4842p());
                    view.layout(i, i2 - max2, i3, i4 - max2);
                    return;
                }
            } else if (this.f8366a.mo3472c() != 2) {
                int i8 = i2 + i5;
                view.layout(i, (i8 - view.getMeasuredHeight()) - eil.mo4842p(), i3, i8 - eil.mo4842p());
                return;
            } else {
                view.layout(i, (i2 - i5) + view.getMeasuredHeight() + eil.mo4840n(), i3, (i4 - i5) + view.getMeasuredHeight() + eil.mo4840n());
                return;
            }
        }
        if (this.f8366a.mo3472c() != 2) {
            view.layout(i, i2 + eil.mo4840n(), i3, i4 + eil.mo4840n());
        } else {
            view.layout(i, i2 - eil.mo4842p(), i3, i4 - eil.mo4842p());
        }
    }

    /* renamed from: a */
    public final void mo4853a(View view, eim eim, boolean z, int i, int i2, int i3, int i4) {
        eil eil = (eil) view.getLayoutParams();
        int e = this.f8366a.mo3475e();
        if (eil.mo4832f() != -1) {
            e = eil.mo4832f();
        }
        int i5 = eim.f8353g;
        if (e != 0) {
            if (e != 1) {
                if (e == 2) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                    int measuredWidth = (((i5 - view.getMeasuredWidth()) + C0350mt.m14758a(marginLayoutParams)) - C0350mt.m14769b(marginLayoutParams)) / 2;
                    if (!z) {
                        view.layout(i + measuredWidth, i2, i3 + measuredWidth, i4);
                        return;
                    } else {
                        view.layout(i - measuredWidth, i2, i3 - measuredWidth, i4);
                        return;
                    }
                } else if (!(e == 3 || e == 4)) {
                    return;
                }
            } else if (z) {
                view.layout((i - i5) + view.getMeasuredWidth() + eil.mo4839m(), i2, (i3 - i5) + view.getMeasuredWidth() + eil.mo4839m(), i4);
                return;
            } else {
                view.layout(((i + i5) - view.getMeasuredWidth()) - eil.mo4841o(), i2, ((i3 + i5) - view.getMeasuredWidth()) - eil.mo4841o(), i4);
                return;
            }
        }
        if (!z) {
            view.layout(i + eil.mo4839m(), i2, i3 + eil.mo4839m(), i4);
        } else {
            view.layout(i - eil.mo4841o(), i2, i3 - eil.mo4841o(), i4);
        }
    }

    /* renamed from: b */
    private final void m7585b(int i, int i2, eim eim, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        eim eim2 = eim;
        int i11 = i3;
        int i12 = eim2.f8351e;
        float f = eim2.f8357k;
        float f2 = 0.0f;
        if (f > 0.0f && i11 <= i12) {
            float f3 = ((float) (i12 - i11)) / f;
            eim2.f8351e = i4 + eim2.f8352f;
            int i13 = 0;
            if (!z) {
                eim2.f8353g = RecyclerView.UNDEFINED_DURATION;
            }
            boolean z2 = false;
            int i14 = 0;
            float f4 = 0.0f;
            while (i13 < eim2.f8354h) {
                int i15 = eim2.f8361o + i13;
                View b = this.f8366a.mo3471b(i15);
                if (b == null) {
                    int i16 = i2;
                    i6 = i12;
                    i5 = i13;
                } else if (b.getVisibility() != 8) {
                    eil eil = (eil) b.getLayoutParams();
                    int b2 = this.f8366a.mo3469b();
                    if (b2 == 0) {
                        i9 = i12;
                        i8 = i13;
                        int i17 = i;
                    } else if (b2 == 1) {
                        i9 = i12;
                        i8 = i13;
                        int i18 = i;
                    } else {
                        int measuredHeight = b.getMeasuredHeight();
                        int measuredWidth = b.getMeasuredWidth();
                        if (this.f8367b[i15]) {
                            i6 = i12;
                            i10 = i13;
                            int i19 = i;
                        } else if (eil.mo4831e() > f2) {
                            float e = ((float) measuredHeight) - (eil.mo4831e() * f3);
                            if (i13 == eim2.f8354h - 1) {
                                e += f4;
                                f4 = 0.0f;
                            }
                            int round = Math.round(e);
                            if (round < eil.mo4834h()) {
                                round = eil.mo4834h();
                                this.f8367b[i15] = true;
                                eim2.f8357k -= eil.mo4831e();
                                i6 = i12;
                                i10 = i13;
                                z2 = true;
                            } else {
                                f4 += e - ((float) round);
                                i6 = i12;
                                i10 = i13;
                                double d = (double) f4;
                                if (d > 1.0d) {
                                    round++;
                                    f4 -= 4.0f;
                                } else if (d < -1.0d) {
                                    round--;
                                    f4 += 1.0f;
                                }
                            }
                            b.measure(m7573a(i, eil, eim2.f8359m), View.MeasureSpec.makeMeasureSpec(round, 1073741824));
                            measuredWidth = b.getMeasuredWidth();
                            measuredHeight = b.getMeasuredHeight();
                        } else {
                            i6 = i12;
                            i10 = i13;
                            int i20 = i;
                        }
                        i7 = Math.max(i14, measuredWidth + eil.mo4839m() + eil.mo4841o() + this.f8366a.mo3483k());
                        eim2.f8351e += measuredHeight + eil.mo4840n() + eil.mo4842p();
                        int i21 = i2;
                        i5 = i10;
                        eim2.f8353g = Math.max(eim2.f8353g, i7);
                        i14 = i7;
                    }
                    int measuredWidth2 = b.getMeasuredWidth();
                    int measuredHeight2 = b.getMeasuredHeight();
                    if (this.f8367b[i15]) {
                        int i22 = i2;
                        i5 = i8;
                    } else if (eil.mo4831e() > 0.0f) {
                        float e2 = ((float) measuredWidth2) - (eil.mo4831e() * f3);
                        i5 = i8;
                        if (i5 == eim2.f8354h - 1) {
                            e2 += f4;
                            f4 = 0.0f;
                        }
                        int round2 = Math.round(e2);
                        if (round2 < eil.mo4833g()) {
                            round2 = eil.mo4833g();
                            z2 = true;
                            this.f8367b[i15] = true;
                            eim2.f8357k -= eil.mo4831e();
                        } else {
                            f4 += e2 - ((float) round2);
                            double d2 = (double) f4;
                            if (d2 > 1.0d) {
                                round2++;
                                f4 -= 4.0f;
                            } else if (d2 < -1.0d) {
                                round2--;
                                f4 += 1.0f;
                            }
                        }
                        b.measure(View.MeasureSpec.makeMeasureSpec(round2, 1073741824), m7583b(i2, eil, eim2.f8359m));
                        measuredWidth2 = b.getMeasuredWidth();
                        measuredHeight2 = b.getMeasuredHeight();
                    } else {
                        int i23 = i2;
                        i5 = i8;
                    }
                    int max = Math.max(i14, measuredHeight2 + eil.mo4840n() + eil.mo4842p() + this.f8366a.mo3483k());
                    eim2.f8351e += measuredWidth2 + eil.mo4839m() + eil.mo4841o();
                    i7 = max;
                    eim2.f8353g = Math.max(eim2.f8353g, i7);
                    i14 = i7;
                } else {
                    int i24 = i2;
                    i6 = i12;
                    i5 = i13;
                }
                i13 = i5 + 1;
                i12 = i6;
                f2 = 0.0f;
            }
            int i25 = i2;
            int i26 = i12;
            if (z2 && i26 != eim2.f8351e) {
                m7585b(i, i2, eim, i3, i4, true);
            }
        }
    }

    /* renamed from: a */
    public static final int[] m7582a(int i, List list, SparseIntArray sparseIntArray) {
        Collections.sort(list);
        sparseIntArray.clear();
        int[] iArr = new int[i];
        int size = list.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            eio eio = (eio) list.get(i3);
            int i4 = eio.f8364a;
            iArr[i2] = i4;
            sparseIntArray.append(i4, eio.f8365b);
            i2++;
        }
        return iArr;
    }

    /* renamed from: a */
    private final void m7579a(View view, int i) {
        eil eil = (eil) view.getLayoutParams();
        int m = eil.mo4839m();
        int o = eil.mo4841o();
        eik eik = this.f8366a;
        int min = Math.min(Math.max(((i - m) - o) - eik.mo3483k(), eil.mo4833g()), eil.mo4835i());
        view.measure(View.MeasureSpec.makeMeasureSpec(min, 1073741824), View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 1073741824));
    }

    /* renamed from: b */
    private final void m7586b(View view, int i) {
        eil eil = (eil) view.getLayoutParams();
        int n = eil.mo4840n();
        int p = eil.mo4842p();
        eik eik = this.f8366a;
        view.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(Math.min(Math.max(((i - n) - p) - eik.mo3483k(), eil.mo4834h()), eil.mo4836j()), 1073741824));
    }

    /* renamed from: a */
    public final void mo4849a() {
        View b;
        if (this.f8366a.mo3461a() > 0) {
            int b2 = this.f8366a.mo3469b();
            if (this.f8366a.mo3475e() == 4) {
                List j = this.f8366a.mo3482j();
                int size = j.size();
                for (int i = 0; i < size; i++) {
                    eim eim = (eim) j.get(i);
                    int i2 = eim.f8354h;
                    for (int i3 = 0; i3 < i2; i3++) {
                        int i4 = eim.f8361o + i3;
                        if (!(i3 >= this.f8366a.mo3461a() || (b = this.f8366a.mo3471b(i4)) == null || b.getVisibility() == 8)) {
                            eil eil = (eil) b.getLayoutParams();
                            if (eil.mo4832f() == -1 || eil.mo4832f() == 4) {
                                if (b2 == 0 || b2 == 1) {
                                    m7586b(b, eim.f8353g);
                                } else if (b2 == 2 || b2 == 3) {
                                    m7579a(b, eim.f8353g);
                                } else {
                                    throw new IllegalArgumentException("Invalid flex direction: " + b2);
                                }
                            }
                        }
                    }
                }
                return;
            }
            for (eim eim2 : this.f8366a.mo3482j()) {
                List list = eim2.f8360n;
                int size2 = list.size();
                for (int i5 = 0; i5 < size2; i5++) {
                    Integer num = (Integer) list.get(i5);
                    View b3 = this.f8366a.mo3471b(num.intValue());
                    if (b2 == 0 || b2 == 1) {
                        int i6 = eim2.f8353g;
                        num.intValue();
                        m7586b(b3, i6);
                    } else if (b2 == 2 || b2 == 3) {
                        int i7 = eim2.f8353g;
                        num.intValue();
                        m7579a(b3, i7);
                    } else {
                        throw new IllegalArgumentException("Invalid flex direction: " + b2);
                    }
                }
            }
        }
    }
}
