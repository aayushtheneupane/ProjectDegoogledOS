package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: tb */
/* compiled from: PG */
public final class C0521tb implements C0620wt {

    /* renamed from: a */
    public final ArrayList f15904a = new ArrayList();

    /* renamed from: b */
    public final ArrayList f15905b = new ArrayList();

    /* renamed from: c */
    public int f15906c = 0;

    /* renamed from: d */
    private final C0306lc f15907d = new C0307ld(30);

    /* renamed from: e */
    private final C0518sz f15908e;

    /* renamed from: f */
    private final C0621wu f15909f;

    public C0521tb(C0518sz szVar) {
        this.f15908e = szVar;
        this.f15909f = new C0621wu(this);
    }

    /* renamed from: a */
    public final boolean mo10089a(int i) {
        return (i & this.f15906c) != 0;
    }

    /* renamed from: c */
    private final boolean m15417c(int i) {
        int size = this.f15905b.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0520ta taVar = (C0520ta) this.f15905b.get(i2);
            int i3 = taVar.f15900a;
            if (i3 == 8) {
                if (m15414b(taVar.f15903d, i2 + 1) == i) {
                    return true;
                }
            } else if (i3 == 1) {
                int i4 = taVar.f15901b;
                int i5 = taVar.f15903d + i4;
                while (i4 < i5) {
                    if (m15414b(i4, i2 + 1) == i) {
                        return true;
                    }
                    i4++;
                }
                continue;
            } else {
                continue;
            }
        }
        return false;
    }

    /* renamed from: c */
    public final void mo10092c() {
        int size = this.f15905b.size();
        for (int i = 0; i < size; i++) {
            ((C0633xf) this.f15908e).mo10533a((C0520ta) this.f15905b.get(i));
        }
        m15412a((List) this.f15905b);
        this.f15906c = 0;
    }

    /* renamed from: e */
    public final void mo10094e() {
        mo10092c();
        int size = this.f15904a.size();
        for (int i = 0; i < size; i++) {
            C0520ta taVar = (C0520ta) this.f15904a.get(i);
            int i2 = taVar.f15900a;
            if (i2 == 1) {
                ((C0633xf) this.f15908e).mo10533a(taVar);
                this.f15908e.mo10080b(taVar.f15901b, taVar.f15903d);
            } else if (i2 == 2) {
                ((C0633xf) this.f15908e).mo10533a(taVar);
                this.f15908e.mo10078a(taVar.f15901b, taVar.f15903d);
            } else if (i2 == 4) {
                ((C0633xf) this.f15908e).mo10533a(taVar);
                this.f15908e.mo10079a(taVar.f15901b, taVar.f15903d, taVar.f15902c);
            } else if (i2 == 8) {
                ((C0633xf) this.f15908e).mo10533a(taVar);
                this.f15908e.mo10081c(taVar.f15901b, taVar.f15903d);
            }
        }
        m15412a((List) this.f15904a);
        this.f15906c = 0;
    }

    /* renamed from: b */
    private final void m15415b(C0520ta taVar) {
        int i;
        int i2 = taVar.f15900a;
        if (i2 == 1 || i2 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int a = m15411a(taVar.f15901b, i2);
        int i3 = taVar.f15901b;
        int i4 = taVar.f15900a;
        if (i4 == 2) {
            i = 0;
        } else if (i4 == 4) {
            i = 1;
        } else {
            throw new IllegalArgumentException("op should be remove or update." + taVar);
        }
        int i5 = 1;
        for (int i6 = 1; i6 < taVar.f15903d; i6++) {
            int a2 = m15411a(taVar.f15901b + (i * i6), taVar.f15900a);
            int i7 = taVar.f15900a;
            if (i7 == 2 ? a2 != a : !(i7 == 4 && a2 == a + 1)) {
                C0520ta a3 = mo10086a(i7, a, i5, taVar.f15902c);
                m15413a(a3, i3);
                mo10088a(a3);
                if (taVar.f15900a == 4) {
                    i3 += i5;
                }
                a = a2;
                i5 = 1;
            } else {
                i5++;
            }
        }
        Object obj = taVar.f15902c;
        mo10088a(taVar);
        if (i5 > 0) {
            C0520ta a4 = mo10086a(taVar.f15900a, a, i5, obj);
            m15413a(a4, i3);
            mo10088a(a4);
        }
    }

    /* renamed from: a */
    private final void m15413a(C0520ta taVar, int i) {
        ((C0633xf) this.f15908e).mo10533a(taVar);
        int i2 = taVar.f15900a;
        if (i2 == 2) {
            this.f15908e.mo10078a(i, taVar.f15903d);
        } else if (i2 == 4) {
            this.f15908e.mo10079a(i, taVar.f15903d, taVar.f15902c);
        } else {
            throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final int mo10090b(int i) {
        return m15414b(i, 0);
    }

    /* renamed from: b */
    private final int m15414b(int i, int i2) {
        int size = this.f15905b.size();
        while (i2 < size) {
            C0520ta taVar = (C0520ta) this.f15905b.get(i2);
            int i3 = taVar.f15900a;
            if (i3 == 8) {
                int i4 = taVar.f15901b;
                if (i4 == i) {
                    i = taVar.f15903d;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (taVar.f15903d <= i) {
                        i++;
                    }
                }
            } else {
                int i5 = taVar.f15901b;
                if (i5 > i) {
                    continue;
                } else if (i3 == 2) {
                    int i6 = taVar.f15903d;
                    if (i < i5 + i6) {
                        return -1;
                    }
                    i -= i6;
                } else if (i3 == 1) {
                    i += taVar.f15903d;
                }
            }
            i2++;
        }
        return i;
    }

    /* renamed from: d */
    public final boolean mo10093d() {
        return this.f15904a.size() > 0;
    }

    /* renamed from: a */
    public final C0520ta mo10086a(int i, int i2, int i3, Object obj) {
        C0520ta taVar = (C0520ta) this.f15907d.mo1971a();
        if (taVar == null) {
            return new C0520ta(i, i2, i3, obj);
        }
        taVar.f15900a = i;
        taVar.f15901b = i2;
        taVar.f15903d = i3;
        taVar.f15902c = obj;
        return taVar;
    }

    /* renamed from: c */
    private final void m15416c(C0520ta taVar) {
        this.f15905b.add(taVar);
        int i = taVar.f15900a;
        if (i == 1) {
            this.f15908e.mo10080b(taVar.f15901b, taVar.f15903d);
        } else if (i == 2) {
            C0518sz szVar = this.f15908e;
            C0633xf xfVar = (C0633xf) szVar;
            xfVar.f16286a.offsetPositionRecordsForRemove(taVar.f15901b, taVar.f15903d, false);
            xfVar.f16286a.mItemsAddedOrRemoved = true;
        } else if (i == 4) {
            this.f15908e.mo10079a(taVar.f15901b, taVar.f15903d, taVar.f15902c);
        } else if (i == 8) {
            this.f15908e.mo10081c(taVar.f15901b, taVar.f15903d);
        } else {
            throw new IllegalArgumentException("Unknown update op type for " + taVar);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:171:0x0006 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0121  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo10091b() {
        /*
            r16 = this;
            r0 = r16
            wu r1 = r0.f15909f
            java.util.ArrayList r2 = r0.f15904a
        L_0x0006:
            int r3 = r2.size()
            r4 = -1
            int r3 = r3 + r4
            r5 = 0
            r6 = 0
        L_0x000e:
            r7 = 8
            r8 = 1
            if (r3 < 0) goto L_0x0025
            java.lang.Object r9 = r2.get(r3)
            ta r9 = (p000.C0520ta) r9
            int r9 = r9.f15900a
            if (r9 == r7) goto L_0x001f
            r6 = 1
            goto L_0x0022
        L_0x001f:
            if (r6 == 0) goto L_0x0022
            goto L_0x0027
        L_0x0022:
            int r3 = r3 + -1
            goto L_0x000e
        L_0x0025:
            r3 = -1
        L_0x0027:
            r6 = 4
            r9 = 2
            r10 = 0
            if (r3 == r4) goto L_0x01c2
            int r7 = r3 + 1
            java.lang.Object r11 = r2.get(r3)
            ta r11 = (p000.C0520ta) r11
            java.lang.Object r12 = r2.get(r7)
            ta r12 = (p000.C0520ta) r12
            int r13 = r12.f15900a
            if (r13 == r8) goto L_0x0197
            if (r13 == r9) goto L_0x00a7
            if (r13 == r6) goto L_0x0043
            goto L_0x0006
        L_0x0043:
            int r4 = r11.f15903d
            int r5 = r12.f15901b
            if (r4 >= r5) goto L_0x004f
            int r5 = r5 + -1
            r12.f15901b = r5
        L_0x004d:
            r4 = r10
            goto L_0x0065
        L_0x004f:
            int r9 = r12.f15903d
            int r5 = r5 + r9
            if (r4 >= r5) goto L_0x0063
            int r9 = r9 + -1
            r12.f15903d = r9
            wt r4 = r1.f16275a
            int r5 = r11.f15901b
            java.lang.Object r9 = r12.f15902c
            ta r4 = r4.mo10086a(r6, r5, r8, r9)
            goto L_0x0065
        L_0x0063:
            goto L_0x004d
        L_0x0065:
            int r5 = r11.f15901b
            int r8 = r12.f15901b
            if (r5 > r8) goto L_0x0070
            int r8 = r8 + 1
            r12.f15901b = r8
            goto L_0x0088
        L_0x0070:
            int r9 = r12.f15903d
            int r8 = r8 + r9
            if (r5 >= r8) goto L_0x0086
            int r8 = r8 - r5
            wt r9 = r1.f16275a
            int r5 = r5 + 1
            java.lang.Object r10 = r12.f15902c
            ta r10 = r9.mo10086a(r6, r5, r8, r10)
            int r5 = r12.f15903d
            int r5 = r5 - r8
            r12.f15903d = r5
            goto L_0x0088
        L_0x0086:
        L_0x0088:
            r2.set(r7, r11)
            int r5 = r12.f15903d
            if (r5 <= 0) goto L_0x0093
            r2.set(r3, r12)
            goto L_0x009b
        L_0x0093:
            r2.remove(r3)
            wt r5 = r1.f16275a
            r5.mo10088a(r12)
        L_0x009b:
            if (r4 == 0) goto L_0x00a0
            r2.add(r3, r4)
        L_0x00a0:
            if (r10 == 0) goto L_0x0006
            r2.add(r3, r10)
            goto L_0x0006
        L_0x00a7:
            int r4 = r11.f15901b
            int r6 = r11.f15903d
            if (r4 < r6) goto L_0x00bd
            int r13 = r12.f15901b
            int r14 = r6 + 1
            if (r13 != r14) goto L_0x00bb
            int r13 = r12.f15903d
            int r4 = r4 - r6
            if (r13 != r4) goto L_0x00ba
            r4 = 1
            goto L_0x00c8
        L_0x00ba:
        L_0x00bb:
            r4 = 1
            goto L_0x00cb
        L_0x00bd:
            int r13 = r12.f15901b
            if (r13 != r4) goto L_0x00ca
            int r13 = r12.f15903d
            int r4 = r6 - r4
            if (r13 != r4) goto L_0x00ca
            r4 = 0
        L_0x00c8:
            r5 = 1
            goto L_0x00cb
        L_0x00ca:
            r4 = 0
        L_0x00cb:
            int r13 = r12.f15901b
            if (r6 >= r13) goto L_0x00d4
            int r13 = r13 + -1
            r12.f15901b = r13
            goto L_0x00f1
        L_0x00d4:
            int r14 = r12.f15903d
            int r15 = r13 + r14
            if (r6 >= r15) goto L_0x00f0
            int r14 = r14 + -1
            r12.f15903d = r14
            r11.f15900a = r9
            r11.f15903d = r8
            int r3 = r12.f15903d
            if (r3 != 0) goto L_0x0006
            r2.remove(r7)
            wt r3 = r1.f16275a
            r3.mo10088a(r12)
            goto L_0x0006
        L_0x00f0:
        L_0x00f1:
            int r6 = r11.f15901b
            if (r6 > r13) goto L_0x00fa
            int r13 = r13 + 1
            r12.f15901b = r13
            goto L_0x0112
        L_0x00fa:
            int r8 = r12.f15903d
            int r13 = r13 + r8
            if (r6 >= r13) goto L_0x0110
            wt r8 = r1.f16275a
            int r14 = r6 + 1
            int r13 = r13 - r6
            ta r10 = r8.mo10086a(r9, r14, r13, r10)
            int r6 = r11.f15901b
            int r8 = r12.f15901b
            int r6 = r6 - r8
            r12.f15903d = r6
            goto L_0x0112
        L_0x0110:
        L_0x0112:
            if (r5 == 0) goto L_0x0121
            r2.set(r3, r12)
            r2.remove(r7)
            wt r3 = r1.f16275a
            r3.mo10088a(r11)
            goto L_0x0006
        L_0x0121:
            if (r4 != 0) goto L_0x0152
            if (r10 == 0) goto L_0x013b
            int r4 = r11.f15901b
            int r5 = r10.f15901b
            if (r4 < r5) goto L_0x0130
            int r5 = r10.f15903d
            int r4 = r4 - r5
            r11.f15901b = r4
        L_0x0130:
            int r4 = r11.f15903d
            int r5 = r10.f15901b
            if (r4 < r5) goto L_0x013b
            int r5 = r10.f15903d
            int r4 = r4 - r5
            r11.f15903d = r4
        L_0x013b:
            int r4 = r11.f15901b
            int r5 = r12.f15901b
            if (r4 < r5) goto L_0x0146
            int r5 = r12.f15903d
            int r4 = r4 - r5
            r11.f15901b = r4
        L_0x0146:
            int r4 = r11.f15903d
            int r5 = r12.f15901b
            if (r4 < r5) goto L_0x0180
            int r5 = r12.f15903d
            int r4 = r4 - r5
            r11.f15903d = r4
            goto L_0x0180
        L_0x0152:
            if (r10 == 0) goto L_0x016a
            int r4 = r11.f15901b
            int r5 = r10.f15901b
            if (r4 <= r5) goto L_0x015f
            int r5 = r10.f15903d
            int r4 = r4 - r5
            r11.f15901b = r4
        L_0x015f:
            int r4 = r11.f15903d
            int r5 = r10.f15901b
            if (r4 <= r5) goto L_0x016a
            int r5 = r10.f15903d
            int r4 = r4 - r5
            r11.f15903d = r4
        L_0x016a:
            int r4 = r11.f15901b
            int r5 = r12.f15901b
            if (r4 <= r5) goto L_0x0175
            int r5 = r12.f15903d
            int r4 = r4 - r5
            r11.f15901b = r4
        L_0x0175:
            int r4 = r11.f15903d
            int r5 = r12.f15901b
            if (r4 <= r5) goto L_0x0180
            int r5 = r12.f15903d
            int r4 = r4 - r5
            r11.f15903d = r4
        L_0x0180:
            r2.set(r3, r12)
            int r4 = r11.f15901b
            int r5 = r11.f15903d
            if (r4 == r5) goto L_0x018d
            r2.set(r7, r11)
            goto L_0x0190
        L_0x018d:
            r2.remove(r7)
        L_0x0190:
            if (r10 == 0) goto L_0x0006
            r2.add(r3, r10)
            goto L_0x0006
        L_0x0197:
            int r6 = r11.f15903d
            int r8 = r12.f15901b
            if (r6 >= r8) goto L_0x019e
            goto L_0x01a0
        L_0x019e:
            r4 = 0
        L_0x01a0:
            int r5 = r11.f15901b
            if (r5 >= r8) goto L_0x01a6
            int r4 = r4 + 1
        L_0x01a6:
            if (r8 > r5) goto L_0x01ad
            int r8 = r12.f15903d
            int r5 = r5 + r8
            r11.f15901b = r5
        L_0x01ad:
            int r5 = r12.f15901b
            if (r5 <= r6) goto L_0x01b2
            goto L_0x01b7
        L_0x01b2:
            int r8 = r12.f15903d
            int r6 = r6 + r8
            r11.f15903d = r6
        L_0x01b7:
            int r5 = r5 + r4
            r12.f15901b = r5
            r2.set(r3, r12)
            r2.set(r7, r11)
            goto L_0x0006
        L_0x01c2:
            java.util.ArrayList r1 = r0.f15904a
            int r1 = r1.size()
            r2 = 0
        L_0x01c9:
            if (r2 >= r1) goto L_0x029e
            java.util.ArrayList r3 = r0.f15904a
            java.lang.Object r3 = r3.get(r2)
            ta r3 = (p000.C0520ta) r3
            int r11 = r3.f15900a
            if (r11 == r8) goto L_0x0296
            if (r11 == r9) goto L_0x023e
            if (r11 == r6) goto L_0x01e4
            if (r11 == r7) goto L_0x01df
            goto L_0x0299
        L_0x01df:
            r0.m15416c((p000.C0520ta) r3)
            goto L_0x0299
        L_0x01e4:
            int r11 = r3.f15901b
            int r12 = r3.f15903d
            int r12 = r12 + r11
            r13 = r11
            r14 = 0
            r15 = -1
        L_0x01ec:
            if (r11 >= r12) goto L_0x0224
            sz r4 = r0.f15908e
            ym r4 = r4.mo10077a(r11)
            if (r4 != 0) goto L_0x020d
            boolean r4 = r0.m15417c((int) r11)
            if (r4 != 0) goto L_0x020d
            if (r15 == r8) goto L_0x01ff
            goto L_0x020b
        L_0x01ff:
            java.lang.Object r4 = r3.f15902c
            ta r4 = r0.mo10086a(r6, r13, r14, r4)
            r0.m15416c((p000.C0520ta) r4)
            r13 = r11
            r14 = 0
        L_0x020b:
            r15 = 0
            goto L_0x021e
        L_0x020d:
            if (r15 != 0) goto L_0x021b
            java.lang.Object r4 = r3.f15902c
            ta r4 = r0.mo10086a(r6, r13, r14, r4)
            r0.m15415b((p000.C0520ta) r4)
            r13 = r11
            r14 = 0
            goto L_0x021c
        L_0x021b:
        L_0x021c:
            r15 = 1
        L_0x021e:
            int r14 = r14 + r8
            int r11 = r11 + 1
            r4 = -1
            goto L_0x01ec
        L_0x0224:
            int r4 = r3.f15903d
            if (r14 == r4) goto L_0x0232
            java.lang.Object r4 = r3.f15902c
            r0.mo10088a((p000.C0520ta) r3)
            ta r3 = r0.mo10086a(r6, r13, r14, r4)
            goto L_0x0233
        L_0x0232:
        L_0x0233:
            if (r15 == 0) goto L_0x023a
            r0.m15416c((p000.C0520ta) r3)
            goto L_0x0299
        L_0x023a:
            r0.m15415b((p000.C0520ta) r3)
            goto L_0x0299
        L_0x023e:
            int r4 = r3.f15901b
            int r11 = r3.f15903d
            int r11 = r11 + r4
            r12 = r4
            r13 = 0
            r14 = -1
        L_0x0246:
            if (r12 >= r11) goto L_0x027f
            sz r15 = r0.f15908e
            ym r15 = r15.mo10077a(r12)
            if (r15 != 0) goto L_0x0265
            boolean r15 = r0.m15417c((int) r12)
            if (r15 != 0) goto L_0x0265
            if (r14 == r8) goto L_0x025a
            r14 = 0
            goto L_0x0263
        L_0x025a:
            ta r14 = r0.mo10086a(r9, r4, r13, r10)
            r0.m15416c((p000.C0520ta) r14)
            r14 = 1
        L_0x0263:
            r15 = 0
            goto L_0x0273
        L_0x0265:
            if (r14 != 0) goto L_0x0270
            ta r14 = r0.mo10086a(r9, r4, r13, r10)
            r0.m15415b((p000.C0520ta) r14)
            r14 = 1
            goto L_0x0271
        L_0x0270:
            r14 = 0
        L_0x0271:
            r15 = 1
        L_0x0273:
            if (r14 == 0) goto L_0x0279
            int r12 = r12 - r13
            int r11 = r11 - r13
            r13 = 1
            goto L_0x027b
        L_0x0279:
            int r13 = r13 + 1
        L_0x027b:
            int r12 = r12 + r8
            r14 = r15
            goto L_0x0246
        L_0x027f:
            int r11 = r3.f15903d
            if (r13 == r11) goto L_0x028b
            r0.mo10088a((p000.C0520ta) r3)
            ta r3 = r0.mo10086a(r9, r4, r13, r10)
            goto L_0x028c
        L_0x028b:
        L_0x028c:
            if (r14 == 0) goto L_0x0292
            r0.m15416c((p000.C0520ta) r3)
            goto L_0x0299
        L_0x0292:
            r0.m15415b((p000.C0520ta) r3)
            goto L_0x0299
        L_0x0296:
            r0.m15416c((p000.C0520ta) r3)
        L_0x0299:
            int r2 = r2 + 1
            r4 = -1
            goto L_0x01c9
        L_0x029e:
            java.util.ArrayList r1 = r0.f15904a
            r1.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0521tb.mo10091b():void");
    }

    /* renamed from: a */
    public final void mo10088a(C0520ta taVar) {
        taVar.f15902c = null;
        this.f15907d.mo1972a(taVar);
    }

    /* renamed from: a */
    private final void m15412a(List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            mo10088a((C0520ta) list.get(i));
        }
        list.clear();
    }

    /* renamed from: a */
    public final void mo10087a() {
        m15412a((List) this.f15904a);
        m15412a((List) this.f15905b);
        this.f15906c = 0;
    }

    /* renamed from: a */
    private final int m15411a(int i, int i2) {
        int size = this.f15905b.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            C0520ta taVar = (C0520ta) this.f15905b.get(size);
            int i3 = taVar.f15900a;
            if (i3 == 8) {
                int i4 = taVar.f15901b;
                int i5 = taVar.f15903d;
                int i6 = i4 < i5 ? i5 : i4;
                int i7 = i4 < i5 ? i4 : i5;
                if (i < i7 || i > i6) {
                    if (i < i4) {
                        if (i2 == 1) {
                            taVar.f15901b = i4 + 1;
                            taVar.f15903d = i5 + 1;
                        } else if (i2 == 2) {
                            taVar.f15901b = i4 - 1;
                            taVar.f15903d = i5 - 1;
                        }
                    }
                } else if (i7 != i4) {
                    if (i2 == 1) {
                        taVar.f15901b = i4 + 1;
                    } else if (i2 == 2) {
                        taVar.f15901b = i4 - 1;
                    }
                    i--;
                } else {
                    if (i2 == 1) {
                        taVar.f15903d = i5 + 1;
                    } else if (i2 == 2) {
                        taVar.f15903d = i5 - 1;
                    }
                    i++;
                }
            } else {
                int i8 = taVar.f15901b;
                if (i8 > i) {
                    if (i2 == 1) {
                        taVar.f15901b = i8 + 1;
                    } else if (i2 == 2) {
                        taVar.f15901b = i8 - 1;
                    }
                } else if (i3 == 1) {
                    i -= taVar.f15903d;
                } else if (i3 == 2) {
                    i += taVar.f15903d;
                }
            }
        }
        for (int size2 = this.f15905b.size() - 1; size2 >= 0; size2--) {
            C0520ta taVar2 = (C0520ta) this.f15905b.get(size2);
            if (taVar2.f15900a == 8) {
                int i9 = taVar2.f15903d;
                if (i9 == taVar2.f15901b || i9 < 0) {
                    this.f15905b.remove(size2);
                    mo10088a(taVar2);
                }
            } else if (taVar2.f15903d <= 0) {
                this.f15905b.remove(size2);
                mo10088a(taVar2);
            }
        }
        return i;
    }
}
