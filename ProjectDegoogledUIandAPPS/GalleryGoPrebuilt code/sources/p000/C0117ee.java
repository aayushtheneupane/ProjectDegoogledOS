package p000;

import java.util.Arrays;

/* renamed from: ee */
/* compiled from: PG */
public final class C0117ee extends C0121ei {

    /* renamed from: ac */
    public int f8078ac = 2;

    /* renamed from: ad */
    public boolean f8079ad = false;

    /* renamed from: ae */
    public boolean f8080ae = false;

    /* renamed from: ag */
    private final C0110dy f8081ag = new C0110dy();

    /* renamed from: ah */
    private C0120eh f8082ah;

    /* renamed from: ai */
    private int f8083ai;

    /* renamed from: aj */
    private int f8084aj;

    /* renamed from: ak */
    private int f8085ak = 0;

    /* renamed from: al */
    private int f8086al = 0;

    /* renamed from: am */
    private C0116ed[] f8087am = new C0116ed[4];

    /* renamed from: an */
    private C0116ed[] f8088an = new C0116ed[4];

    /* renamed from: ao */
    private C0116ed[] f8089ao = new C0116ed[4];

    /* renamed from: ap */
    private final boolean[] f8090ap = new boolean[3];

    /* renamed from: aq */
    private final C0116ed[] f8091aq = new C0116ed[4];

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo4741a(C0116ed edVar, int i) {
        int i2 = 0;
        if (i == 0) {
            while (true) {
                C0115ec ecVar = edVar.f7995i;
                C0115ec ecVar2 = ecVar.f7903b;
                if (ecVar2 == null) {
                    break;
                }
                C0116ed edVar2 = ecVar2.f7902a;
                C0115ec ecVar3 = edVar2.f7997k.f7903b;
                if (ecVar3 == null || ecVar3 != ecVar || edVar2 == edVar) {
                    break;
                }
                edVar = edVar2;
            }
            while (true) {
                int i3 = this.f8085ak;
                if (i2 >= i3) {
                    C0116ed[] edVarArr = this.f8089ao;
                    int length = edVarArr.length;
                    if (i3 + 1 >= length) {
                        this.f8089ao = (C0116ed[]) Arrays.copyOf(edVarArr, length + length);
                    }
                    C0116ed[] edVarArr2 = this.f8089ao;
                    int i4 = this.f8085ak;
                    edVarArr2[i4] = edVar;
                    this.f8085ak = i4 + 1;
                    return;
                } else if (this.f8089ao[i2] != edVar) {
                    i2++;
                } else {
                    return;
                }
            }
        } else {
            while (true) {
                C0115ec ecVar4 = edVar.f7996j;
                C0115ec ecVar5 = ecVar4.f7903b;
                if (ecVar5 == null) {
                    break;
                }
                C0116ed edVar3 = ecVar5.f7902a;
                C0115ec ecVar6 = edVar3.f7998l.f7903b;
                if (ecVar6 == null || ecVar6 != ecVar4 || edVar3 == edVar) {
                    break;
                }
                edVar = edVar3;
            }
            while (true) {
                int i5 = this.f8086al;
                if (i2 >= i5) {
                    C0116ed[] edVarArr3 = this.f8088an;
                    int length2 = edVarArr3.length;
                    if (i5 + 1 >= length2) {
                        this.f8088an = (C0116ed[]) Arrays.copyOf(edVarArr3, length2 + length2);
                    }
                    C0116ed[] edVarArr4 = this.f8088an;
                    int i6 = this.f8086al;
                    edVarArr4[i6] = edVar;
                    this.f8086al = i6 + 1;
                    return;
                } else if (this.f8088an[i2] != edVar) {
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:224:0x06e9, code lost:
        if (r5.f7961H == 8) goto L_0x06eb;
     */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean m7319d(p000.C0110dy r21) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r20.mo4695a((p000.C0110dy) r21)
            java.util.ArrayList r2 = r0.f8330af
            int r2 = r2.size()
            int r3 = r0.f8078ac
            r4 = 4
            r5 = 8
            r6 = 0
            r7 = 1
            r8 = 2
            if (r3 != r8) goto L_0x0018
            goto L_0x001c
        L_0x0018:
            if (r3 == r4) goto L_0x001c
            r3 = 1
            goto L_0x0072
        L_0x001c:
            java.util.ArrayList r3 = r0.f8330af
            int r3 = r3.size()
            r9 = 0
        L_0x0023:
            r10 = 3
            r11 = -1
            if (r9 >= r3) goto L_0x0043
            java.util.ArrayList r12 = r0.f8330af
            java.lang.Object r12 = r12.get(r9)
            ed r12 = (p000.C0116ed) r12
            r12.f7980a = r11
            r12.f7988b = r11
            int r11 = r12.f7981aa
            if (r11 == r10) goto L_0x003c
            int r11 = r12.f7982ab
            if (r11 == r10) goto L_0x003c
            goto L_0x0040
        L_0x003c:
            r12.f7980a = r7
            r12.f7988b = r7
        L_0x0040:
            int r9 = r9 + 1
            goto L_0x0023
        L_0x0043:
            r9 = 0
            r12 = 0
            r13 = 0
        L_0x0046:
            if (r9 == 0) goto L_0x0144
            r9 = 0
            r10 = 0
            r12 = 0
        L_0x004b:
            if (r9 >= r3) goto L_0x006b
            java.util.ArrayList r13 = r0.f8330af
            java.lang.Object r13 = r13.get(r9)
            ed r13 = (p000.C0116ed) r13
            int r14 = r13.f7980a
            if (r14 != r7) goto L_0x005a
            goto L_0x005c
        L_0x005a:
            if (r14 != r11) goto L_0x005e
        L_0x005c:
            int r10 = r10 + 1
        L_0x005e:
            int r13 = r13.f7988b
            if (r13 != r7) goto L_0x0063
        L_0x0062:
            goto L_0x0066
        L_0x0063:
            if (r13 != r11) goto L_0x0068
            goto L_0x0062
        L_0x0066:
            int r12 = r12 + 1
        L_0x0068:
            int r9 = r9 + 1
            goto L_0x004b
        L_0x006b:
            if (r10 == 0) goto L_0x006f
        L_0x006d:
            r3 = 0
            goto L_0x0072
        L_0x006f:
            if (r12 == 0) goto L_0x0142
            goto L_0x006d
        L_0x0072:
            if (r6 >= r2) goto L_0x0132
            java.util.ArrayList r9 = r0.f8330af
            java.lang.Object r9 = r9.get(r6)
            ed r9 = (p000.C0116ed) r9
            boolean r10 = r9 instanceof p000.C0117ee
            if (r10 == 0) goto L_0x009f
            int r10 = r9.f7981aa
            int r11 = r9.f7982ab
            if (r10 != r8) goto L_0x0089
            r9.mo4707f(r7)
        L_0x0089:
            if (r11 != r8) goto L_0x008e
            r9.mo4709g(r7)
        L_0x008e:
            r9.mo4695a((p000.C0110dy) r1)
            if (r10 != r8) goto L_0x0096
            r9.mo4707f(r8)
        L_0x0096:
            if (r11 == r8) goto L_0x009a
            goto L_0x012e
        L_0x009a:
            r9.mo4709g(r8)
            goto L_0x012e
        L_0x009f:
            if (r3 == 0) goto L_0x012b
            int r10 = r0.f7981aa
            if (r10 == r8) goto L_0x00d9
            int r10 = r9.f7981aa
            if (r10 != r4) goto L_0x00d9
            ec r10 = r9.f7995i
            eb r11 = r1.mo4554a((java.lang.Object) r10)
            r10.f7906e = r11
            ec r10 = r9.f7997k
            eb r11 = r1.mo4554a((java.lang.Object) r10)
            r10.f7906e = r11
            ec r10 = r9.f7995i
            int r10 = r10.f7904c
            int r11 = r20.mo4699c()
            ec r12 = r9.f7997k
            int r12 = r12.f7904c
            int r11 = r11 - r12
            ec r12 = r9.f7995i
            eb r12 = r12.f7906e
            r1.mo4558a((p000.C0114eb) r12, (int) r10)
            ec r12 = r9.f7997k
            eb r12 = r12.f7906e
            r1.mo4558a((p000.C0114eb) r12, (int) r11)
            r9.mo4697b(r10, r11)
            r9.f7980a = r8
        L_0x00d9:
            int r10 = r0.f7982ab
            if (r10 == r8) goto L_0x012b
            int r10 = r9.f7982ab
            if (r10 != r4) goto L_0x012b
            ec r10 = r9.f7996j
            eb r11 = r1.mo4554a((java.lang.Object) r10)
            r10.f7906e = r11
            ec r10 = r9.f7998l
            eb r11 = r1.mo4554a((java.lang.Object) r10)
            r10.f7906e = r11
            ec r10 = r9.f7996j
            int r10 = r10.f7904c
            int r11 = r20.mo4706f()
            ec r12 = r9.f7998l
            int r12 = r12.f7904c
            int r11 = r11 - r12
            ec r12 = r9.f7996j
            eb r12 = r12.f7906e
            r1.mo4558a((p000.C0114eb) r12, (int) r10)
            ec r12 = r9.f7998l
            eb r12 = r12.f7906e
            r1.mo4558a((p000.C0114eb) r12, (int) r11)
            int r12 = r9.f8012z
            if (r12 > 0) goto L_0x0114
            int r12 = r9.f7961H
            if (r12 != r5) goto L_0x0126
        L_0x0114:
            ec r12 = r9.f7999m
            eb r13 = r1.mo4554a((java.lang.Object) r12)
            r12.f7906e = r13
            ec r12 = r9.f7999m
            eb r12 = r12.f7906e
            int r13 = r9.f8012z
            int r13 = r13 + r10
            r1.mo4558a((p000.C0114eb) r12, (int) r13)
        L_0x0126:
            r9.mo4701c(r10, r11)
            r9.f7988b = r8
        L_0x012b:
            r9.mo4695a((p000.C0110dy) r1)
        L_0x012e:
            int r6 = r6 + 1
            goto L_0x0072
        L_0x0132:
            int r2 = r0.f8085ak
            if (r2 > 0) goto L_0x0137
            goto L_0x013a
        L_0x0137:
            r20.m7316b(r21)
        L_0x013a:
            int r2 = r0.f8086al
            if (r2 <= 0) goto L_0x0141
            r20.m7318c(r21)
        L_0x0141:
            return r7
        L_0x0142:
            return r6
        L_0x0144:
            r14 = 0
            r15 = 0
            r16 = 0
        L_0x0148:
            if (r14 >= r3) goto L_0x072b
            java.util.ArrayList r5 = r0.f8330af
            java.lang.Object r5 = r5.get(r14)
            ed r5 = (p000.C0116ed) r5
            int r6 = r5.f7980a
            r17 = 1056964608(0x3f000000, float:0.5)
            if (r6 != r11) goto L_0x0381
            int r6 = r0.f7981aa
            if (r6 == r8) goto L_0x037e
            int r11 = r5.f7981aa
            if (r11 != r10) goto L_0x0164
            r5.f7980a = r7
            goto L_0x0381
        L_0x0164:
            if (r6 == r8) goto L_0x019b
            if (r11 == r4) goto L_0x0169
            goto L_0x019b
        L_0x0169:
            ec r6 = r5.f7995i
            eb r11 = r1.mo4554a((java.lang.Object) r6)
            r6.f7906e = r11
            ec r6 = r5.f7997k
            eb r11 = r1.mo4554a((java.lang.Object) r6)
            r6.f7906e = r11
            ec r6 = r5.f7995i
            int r6 = r6.f7904c
            int r11 = r20.mo4699c()
            ec r4 = r5.f7997k
            int r4 = r4.f7904c
            int r11 = r11 - r4
            ec r4 = r5.f7995i
            eb r4 = r4.f7906e
            r1.mo4558a((p000.C0114eb) r4, (int) r6)
            ec r4 = r5.f7997k
            eb r4 = r4.f7906e
            r1.mo4558a((p000.C0114eb) r4, (int) r11)
            r5.mo4697b(r6, r11)
            r5.f7980a = r8
            goto L_0x0381
        L_0x019b:
            ec r4 = r5.f7995i
            ec r6 = r4.f7903b
            if (r6 == 0) goto L_0x020a
            ec r11 = r5.f7997k
            ec r11 = r11.f7903b
            if (r11 == 0) goto L_0x020a
            ed r6 = r6.f7902a
            if (r6 != r0) goto L_0x0206
            ed r6 = r11.f7902a
            if (r6 == r0) goto L_0x01b0
            goto L_0x0206
        L_0x01b0:
            int r4 = r4.mo4667a()
            ec r6 = r5.f7997k
            int r6 = r6.mo4667a()
            int r11 = r0.f7981aa
            if (r11 != r10) goto L_0x01c4
            int r11 = r20.mo4699c()
            int r11 = r11 - r6
            goto L_0x01e1
        L_0x01c4:
            int r11 = r5.mo4699c()
            int r18 = r20.mo4699c()
            int r18 = r18 - r4
            int r18 = r18 - r6
            int r6 = r18 - r11
            float r6 = (float) r6
            float r11 = r5.f7958E
            float r6 = r6 * r11
            float r6 = r6 + r17
            int r6 = (int) r6
            int r4 = r4 + r6
            int r6 = r5.mo4699c()
            int r11 = r4 + r6
        L_0x01e1:
            ec r6 = r5.f7995i
            eb r10 = r1.mo4554a((java.lang.Object) r6)
            r6.f7906e = r10
            ec r6 = r5.f7997k
            eb r10 = r1.mo4554a((java.lang.Object) r6)
            r6.f7906e = r10
            ec r6 = r5.f7995i
            eb r6 = r6.f7906e
            r1.mo4558a((p000.C0114eb) r6, (int) r4)
            ec r6 = r5.f7997k
            eb r6 = r6.f7906e
            r1.mo4558a((p000.C0114eb) r6, (int) r11)
            r5.f7980a = r8
            r5.mo4697b(r4, r11)
            goto L_0x0381
        L_0x0206:
            r5.f7980a = r7
            goto L_0x0381
        L_0x020a:
            if (r6 == 0) goto L_0x023f
            ed r10 = r6.f7902a
            if (r10 == r0) goto L_0x0211
            goto L_0x023f
        L_0x0211:
            int r4 = r4.mo4667a()
            int r6 = r5.mo4699c()
            int r6 = r6 + r4
            ec r10 = r5.f7995i
            eb r11 = r1.mo4554a((java.lang.Object) r10)
            r10.f7906e = r11
            ec r10 = r5.f7997k
            eb r11 = r1.mo4554a((java.lang.Object) r10)
            r10.f7906e = r11
            ec r10 = r5.f7995i
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r4)
            ec r10 = r5.f7997k
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r6)
            r5.f7980a = r8
            r5.mo4697b(r4, r6)
            goto L_0x0381
        L_0x023f:
            ec r10 = r5.f7997k
            ec r10 = r10.f7903b
            if (r10 == 0) goto L_0x027e
            ed r11 = r10.f7902a
            if (r11 == r0) goto L_0x024a
            goto L_0x027e
        L_0x024a:
            eb r6 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r6
            ec r4 = r5.f7997k
            eb r6 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r6
            int r4 = r20.mo4699c()
            ec r6 = r5.f7997k
            int r6 = r6.mo4667a()
            int r4 = r4 - r6
            int r6 = r5.mo4699c()
            int r6 = r4 - r6
            ec r10 = r5.f7995i
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r6)
            ec r10 = r5.f7997k
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r4)
            r5.f7980a = r8
            r5.mo4697b(r6, r4)
            goto L_0x0381
        L_0x027e:
            if (r6 == 0) goto L_0x02bd
            ed r11 = r6.f7902a
            int r11 = r11.f7980a
            if (r11 != r8) goto L_0x02bd
            eb r6 = r6.f7906e
            eb r10 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r10
            ec r4 = r5.f7997k
            eb r10 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r10
            float r4 = r6.f7816d
            ec r6 = r5.f7995i
            int r6 = r6.mo4667a()
            float r6 = (float) r6
            float r4 = r4 + r6
            float r4 = r4 + r17
            int r4 = (int) r4
            int r6 = r5.mo4699c()
            int r6 = r6 + r4
            ec r10 = r5.f7995i
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r4)
            ec r10 = r5.f7997k
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r6)
            r5.f7980a = r8
            r5.mo4697b(r4, r6)
            goto L_0x0381
        L_0x02bd:
            if (r10 == 0) goto L_0x02fd
            ed r11 = r10.f7902a
            int r11 = r11.f7980a
            if (r11 != r8) goto L_0x02fd
            eb r6 = r10.f7906e
            eb r10 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r10
            ec r4 = r5.f7997k
            eb r10 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r10
            float r4 = r6.f7816d
            ec r6 = r5.f7997k
            int r6 = r6.mo4667a()
            float r6 = (float) r6
            float r4 = r4 - r6
            float r4 = r4 + r17
            int r4 = (int) r4
            int r6 = r5.mo4699c()
            int r6 = r4 - r6
            ec r10 = r5.f7995i
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r6)
            ec r10 = r5.f7997k
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r4)
            r5.f7980a = r8
            r5.mo4697b(r6, r4)
            goto L_0x0381
        L_0x02fd:
            if (r6 != 0) goto L_0x0381
            if (r10 != 0) goto L_0x0381
            boolean r6 = r5 instanceof p000.C0118ef
            if (r6 == 0) goto L_0x0358
            r6 = r5
            ef r6 = (p000.C0118ef) r6
            int r10 = r6.f8136af
            if (r10 != r7) goto L_0x0381
            eb r10 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r10
            ec r4 = r5.f7997k
            eb r10 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r10
            int r4 = r6.f8134ad
            r10 = -1
            if (r4 == r10) goto L_0x0321
            float r4 = (float) r4
            goto L_0x0337
        L_0x0321:
            int r4 = r6.f8135ae
            if (r4 != r10) goto L_0x032f
            int r4 = r20.mo4699c()
            float r4 = (float) r4
            float r6 = r6.f8133ac
            float r4 = r4 * r6
            goto L_0x0337
        L_0x032f:
            int r4 = r20.mo4699c()
            int r6 = r6.f8135ae
            int r4 = r4 - r6
            float r4 = (float) r4
        L_0x0337:
            float r4 = r4 + r17
            int r4 = (int) r4
            ec r6 = r5.f7995i
            eb r6 = r6.f7906e
            r1.mo4558a((p000.C0114eb) r6, (int) r4)
            ec r6 = r5.f7997k
            eb r6 = r6.f7906e
            r1.mo4558a((p000.C0114eb) r6, (int) r4)
            r5.f7980a = r8
            r5.f7988b = r8
            r5.mo4697b(r4, r4)
            int r4 = r20.mo4706f()
            r6 = 0
            r5.mo4701c(r6, r4)
            goto L_0x0381
        L_0x0358:
            eb r6 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r6
            ec r4 = r5.f7997k
            eb r6 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r6
            int r4 = r5.f8006t
            int r6 = r5.mo4699c()
            ec r10 = r5.f7995i
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r4)
            ec r10 = r5.f7997k
            eb r10 = r10.f7906e
            int r4 = r4 + r6
            r1.mo4558a((p000.C0114eb) r10, (int) r4)
            r5.f7980a = r8
            goto L_0x0381
        L_0x037e:
            r5.f7980a = r7
        L_0x0381:
            int r4 = r5.f7988b
            r6 = -1
            if (r4 != r6) goto L_0x070e
            int r4 = r0.f7982ab
            if (r4 == r8) goto L_0x0707
            int r6 = r5.f7982ab
            r10 = 3
            if (r6 != r10) goto L_0x0397
            r5.f7988b = r7
            r4 = 1
            r6 = 0
            r10 = 8
            goto L_0x0712
        L_0x0397:
            if (r4 == r8) goto L_0x03ef
            r4 = 4
            if (r6 == r4) goto L_0x039d
            goto L_0x03ef
        L_0x039d:
            ec r6 = r5.f7996j
            eb r10 = r1.mo4554a((java.lang.Object) r6)
            r6.f7906e = r10
            ec r6 = r5.f7998l
            eb r10 = r1.mo4554a((java.lang.Object) r6)
            r6.f7906e = r10
            ec r6 = r5.f7996j
            int r6 = r6.f7904c
            int r10 = r20.mo4706f()
            ec r11 = r5.f7998l
            int r11 = r11.f7904c
            int r10 = r10 - r11
            ec r11 = r5.f7996j
            eb r11 = r11.f7906e
            r1.mo4558a((p000.C0114eb) r11, (int) r6)
            ec r11 = r5.f7998l
            eb r11 = r11.f7906e
            r1.mo4558a((p000.C0114eb) r11, (int) r10)
            int r11 = r5.f8012z
            if (r11 > 0) goto L_0x03d2
            int r11 = r5.f7961H
            r4 = 8
            if (r11 != r4) goto L_0x03e4
        L_0x03d2:
            ec r4 = r5.f7999m
            eb r11 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r11
            ec r4 = r5.f7999m
            eb r4 = r4.f7906e
            int r11 = r5.f8012z
            int r11 = r11 + r6
            r1.mo4558a((p000.C0114eb) r4, (int) r11)
        L_0x03e4:
            r5.mo4701c(r6, r10)
            r5.f7988b = r8
            r4 = 1
            r6 = 0
            r10 = 8
            goto L_0x0712
        L_0x03ef:
            ec r4 = r5.f7996j
            ec r6 = r4.f7903b
            if (r6 == 0) goto L_0x0485
            ec r10 = r5.f7998l
            ec r10 = r10.f7903b
            if (r10 == 0) goto L_0x0485
            ed r6 = r6.f7902a
            if (r6 != r0) goto L_0x047d
            ed r6 = r10.f7902a
            if (r6 == r0) goto L_0x0405
            goto L_0x047d
        L_0x0405:
            int r4 = r4.mo4667a()
            ec r6 = r5.f7998l
            int r6 = r6.mo4667a()
            int r10 = r0.f7982ab
            r11 = 3
            if (r10 != r11) goto L_0x041a
            int r6 = r5.mo4706f()
            int r6 = r6 + r4
            goto L_0x0437
        L_0x041a:
            int r10 = r5.mo4706f()
            float r11 = (float) r4
            int r19 = r20.mo4706f()
            int r19 = r19 - r4
            int r19 = r19 - r6
            int r4 = r19 - r10
            float r4 = (float) r4
            float r6 = r5.f7959F
            float r4 = r4 * r6
            float r11 = r11 + r4
            float r11 = r11 + r17
            int r4 = (int) r11
            int r6 = r5.mo4706f()
            int r6 = r6 + r4
        L_0x0437:
            ec r10 = r5.f7996j
            eb r11 = r1.mo4554a((java.lang.Object) r10)
            r10.f7906e = r11
            ec r10 = r5.f7998l
            eb r11 = r1.mo4554a((java.lang.Object) r10)
            r10.f7906e = r11
            ec r10 = r5.f7996j
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r4)
            ec r10 = r5.f7998l
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r6)
            int r10 = r5.f8012z
            if (r10 > 0) goto L_0x045f
            int r10 = r5.f7961H
            r11 = 8
            if (r10 != r11) goto L_0x0471
        L_0x045f:
            ec r10 = r5.f7999m
            eb r11 = r1.mo4554a((java.lang.Object) r10)
            r10.f7906e = r11
            ec r10 = r5.f7999m
            eb r10 = r10.f7906e
            int r11 = r5.f8012z
            int r11 = r11 + r4
            r1.mo4558a((p000.C0114eb) r10, (int) r11)
        L_0x0471:
            r5.f7988b = r8
            r5.mo4701c(r4, r6)
            r4 = 1
            r6 = 0
            r10 = 8
            goto L_0x0712
        L_0x047d:
            r5.f7988b = r7
            r4 = 1
            r6 = 0
            r10 = 8
            goto L_0x0712
        L_0x0485:
            if (r6 == 0) goto L_0x04dc
            ed r10 = r6.f7902a
            if (r10 == r0) goto L_0x048c
            goto L_0x04dc
        L_0x048c:
            int r4 = r4.mo4667a()
            int r6 = r5.mo4706f()
            int r6 = r6 + r4
            ec r10 = r5.f7996j
            eb r11 = r1.mo4554a((java.lang.Object) r10)
            r10.f7906e = r11
            ec r10 = r5.f7998l
            eb r11 = r1.mo4554a((java.lang.Object) r10)
            r10.f7906e = r11
            ec r10 = r5.f7996j
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r4)
            ec r10 = r5.f7998l
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r6)
            int r10 = r5.f8012z
            if (r10 <= 0) goto L_0x04b8
            goto L_0x04be
        L_0x04b8:
            int r10 = r5.f7961H
            r11 = 8
            if (r10 != r11) goto L_0x04d0
        L_0x04be:
            ec r10 = r5.f7999m
            eb r11 = r1.mo4554a((java.lang.Object) r10)
            r10.f7906e = r11
            ec r10 = r5.f7999m
            eb r10 = r10.f7906e
            int r11 = r5.f8012z
            int r11 = r11 + r4
            r1.mo4558a((p000.C0114eb) r10, (int) r11)
        L_0x04d0:
            r5.f7988b = r8
            r5.mo4701c(r4, r6)
            r4 = 1
            r6 = 0
            r10 = 8
            goto L_0x0712
        L_0x04dc:
            ec r10 = r5.f7998l
            ec r10 = r10.f7903b
            if (r10 == 0) goto L_0x053d
            ed r11 = r10.f7902a
            if (r11 == r0) goto L_0x04e7
            goto L_0x053d
        L_0x04e7:
            eb r6 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r6
            ec r4 = r5.f7998l
            eb r6 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r6
            int r4 = r20.mo4706f()
            ec r6 = r5.f7998l
            int r6 = r6.mo4667a()
            int r4 = r4 - r6
            int r6 = r5.mo4706f()
            int r6 = r4 - r6
            ec r10 = r5.f7996j
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r6)
            ec r10 = r5.f7998l
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r4)
            int r10 = r5.f8012z
            if (r10 <= 0) goto L_0x0519
            goto L_0x051f
        L_0x0519:
            int r10 = r5.f7961H
            r11 = 8
            if (r10 != r11) goto L_0x0531
        L_0x051f:
            ec r10 = r5.f7999m
            eb r11 = r1.mo4554a((java.lang.Object) r10)
            r10.f7906e = r11
            ec r10 = r5.f7999m
            eb r10 = r10.f7906e
            int r11 = r5.f8012z
            int r11 = r11 + r6
            r1.mo4558a((p000.C0114eb) r10, (int) r11)
        L_0x0531:
            r5.f7988b = r8
            r5.mo4701c(r6, r4)
            r4 = 1
            r6 = 0
            r10 = 8
            goto L_0x0712
        L_0x053d:
            if (r6 == 0) goto L_0x059e
            ed r11 = r6.f7902a
            int r11 = r11.f7988b
            if (r11 != r8) goto L_0x059e
            eb r6 = r6.f7906e
            eb r10 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r10
            ec r4 = r5.f7998l
            eb r10 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r10
            float r4 = r6.f7816d
            ec r6 = r5.f7996j
            int r6 = r6.mo4667a()
            float r6 = (float) r6
            float r4 = r4 + r6
            float r4 = r4 + r17
            int r4 = (int) r4
            int r6 = r5.mo4706f()
            int r6 = r6 + r4
            ec r10 = r5.f7996j
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r4)
            ec r10 = r5.f7998l
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r6)
            int r10 = r5.f8012z
            if (r10 <= 0) goto L_0x057a
            goto L_0x0580
        L_0x057a:
            int r10 = r5.f7961H
            r11 = 8
            if (r10 != r11) goto L_0x0592
        L_0x0580:
            ec r10 = r5.f7999m
            eb r11 = r1.mo4554a((java.lang.Object) r10)
            r10.f7906e = r11
            ec r10 = r5.f7999m
            eb r10 = r10.f7906e
            int r11 = r5.f8012z
            int r11 = r11 + r4
            r1.mo4558a((p000.C0114eb) r10, (int) r11)
        L_0x0592:
            r5.f7988b = r8
            r5.mo4701c(r4, r6)
            r4 = 1
            r6 = 0
            r10 = 8
            goto L_0x0712
        L_0x059e:
            if (r10 == 0) goto L_0x0600
            ed r11 = r10.f7902a
            int r11 = r11.f7988b
            if (r11 != r8) goto L_0x0600
            eb r6 = r10.f7906e
            eb r10 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r10
            ec r4 = r5.f7998l
            eb r10 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r10
            float r4 = r6.f7816d
            ec r6 = r5.f7998l
            int r6 = r6.mo4667a()
            float r6 = (float) r6
            float r4 = r4 - r6
            float r4 = r4 + r17
            int r4 = (int) r4
            int r6 = r5.mo4706f()
            int r6 = r4 - r6
            ec r10 = r5.f7996j
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r6)
            ec r10 = r5.f7998l
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r4)
            int r10 = r5.f8012z
            if (r10 <= 0) goto L_0x05dc
            goto L_0x05e2
        L_0x05dc:
            int r10 = r5.f7961H
            r11 = 8
            if (r10 != r11) goto L_0x05f4
        L_0x05e2:
            ec r10 = r5.f7999m
            eb r11 = r1.mo4554a((java.lang.Object) r10)
            r10.f7906e = r11
            ec r10 = r5.f7999m
            eb r10 = r10.f7906e
            int r11 = r5.f8012z
            int r11 = r11 + r6
            r1.mo4558a((p000.C0114eb) r10, (int) r11)
        L_0x05f4:
            r5.f7988b = r8
            r5.mo4701c(r6, r4)
            r4 = 1
            r6 = 0
            r10 = 8
            goto L_0x0712
        L_0x0600:
            ec r11 = r5.f7999m
            ec r11 = r11.f7903b
            if (r11 == 0) goto L_0x0655
            ed r7 = r11.f7902a
            int r7 = r7.f7988b
            if (r7 != r8) goto L_0x0655
            eb r6 = r11.f7906e
            eb r7 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r7
            ec r4 = r5.f7998l
            eb r7 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r7
            float r4 = r6.f7816d
            int r6 = r5.f8012z
            float r6 = (float) r6
            float r4 = r4 - r6
            float r4 = r4 + r17
            int r4 = (int) r4
            int r6 = r5.mo4706f()
            int r6 = r6 + r4
            ec r7 = r5.f7996j
            eb r7 = r7.f7906e
            r1.mo4558a((p000.C0114eb) r7, (int) r4)
            ec r7 = r5.f7998l
            eb r7 = r7.f7906e
            r1.mo4558a((p000.C0114eb) r7, (int) r6)
            ec r7 = r5.f7999m
            eb r10 = r1.mo4554a((java.lang.Object) r7)
            r7.f7906e = r10
            ec r7 = r5.f7999m
            eb r7 = r7.f7906e
            int r10 = r5.f8012z
            int r10 = r10 + r4
            r1.mo4558a((p000.C0114eb) r7, (int) r10)
            r5.f7988b = r8
            r5.mo4701c(r4, r6)
            r4 = 1
            r6 = 0
            r10 = 8
            goto L_0x0712
        L_0x0655:
            if (r11 != 0) goto L_0x0702
            if (r6 != 0) goto L_0x0702
            if (r10 != 0) goto L_0x0702
            boolean r6 = r5 instanceof p000.C0118ef
            if (r6 == 0) goto L_0x06ba
            r6 = r5
            ef r6 = (p000.C0118ef) r6
            int r7 = r6.f8136af
            if (r7 != 0) goto L_0x06b5
            eb r7 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r7
            ec r4 = r5.f7998l
            eb r7 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r7
            int r4 = r6.f8134ad
            r7 = -1
            if (r4 == r7) goto L_0x067b
            float r4 = (float) r4
            goto L_0x0691
        L_0x067b:
            int r4 = r6.f8135ae
            if (r4 != r7) goto L_0x0689
            int r4 = r20.mo4706f()
            float r4 = (float) r4
            float r6 = r6.f8133ac
            float r4 = r4 * r6
            goto L_0x0691
        L_0x0689:
            int r4 = r20.mo4706f()
            int r6 = r6.f8135ae
            int r4 = r4 - r6
            float r4 = (float) r4
        L_0x0691:
            float r4 = r4 + r17
            int r4 = (int) r4
            ec r6 = r5.f7996j
            eb r6 = r6.f7906e
            r1.mo4558a((p000.C0114eb) r6, (int) r4)
            ec r6 = r5.f7998l
            eb r6 = r6.f7906e
            r1.mo4558a((p000.C0114eb) r6, (int) r4)
            r5.f7988b = r8
            r5.f7980a = r8
            r5.mo4701c(r4, r4)
            int r4 = r20.mo4699c()
            r6 = 0
            r5.mo4697b(r6, r4)
            r4 = 1
            r10 = 8
            goto L_0x0712
        L_0x06b5:
            r6 = 0
            r4 = 1
            r10 = 8
            goto L_0x0712
        L_0x06ba:
            r6 = 0
            eb r7 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r7
            ec r4 = r5.f7998l
            eb r7 = r1.mo4554a((java.lang.Object) r4)
            r4.f7906e = r7
            int r4 = r5.f8007u
            int r7 = r5.mo4706f()
            ec r10 = r5.f7996j
            eb r10 = r10.f7906e
            r1.mo4558a((p000.C0114eb) r10, (int) r4)
            ec r10 = r5.f7998l
            eb r10 = r10.f7906e
            int r7 = r7 + r4
            r1.mo4558a((p000.C0114eb) r10, (int) r7)
            int r7 = r5.f8012z
            if (r7 <= 0) goto L_0x06e5
            r10 = 8
            goto L_0x06eb
        L_0x06e5:
            int r7 = r5.f7961H
            r10 = 8
            if (r7 != r10) goto L_0x06fd
        L_0x06eb:
            ec r7 = r5.f7999m
            eb r11 = r1.mo4554a((java.lang.Object) r7)
            r7.f7906e = r11
            ec r7 = r5.f7999m
            eb r7 = r7.f7906e
            int r11 = r5.f8012z
            int r4 = r4 + r11
            r1.mo4558a((p000.C0114eb) r7, (int) r4)
        L_0x06fd:
            r5.f7988b = r8
            r4 = 1
            goto L_0x0712
        L_0x0702:
            r6 = 0
            r10 = 8
            r4 = 1
            goto L_0x0712
        L_0x0707:
            r6 = 0
            r10 = 8
            r4 = 1
            r5.f7988b = r4
            goto L_0x0712
        L_0x070e:
            r4 = 1
            r6 = 0
            r10 = 8
        L_0x0712:
            int r7 = r5.f7988b
            r11 = -1
            if (r7 != r11) goto L_0x0719
            int r15 = r15 + 1
        L_0x0719:
            int r5 = r5.f7980a
            if (r5 == r11) goto L_0x071e
            goto L_0x0722
        L_0x071e:
            r5 = r16
            int r16 = r5 + 1
        L_0x0722:
            int r14 = r14 + 1
            r4 = 4
            r5 = 8
            r7 = 1
            r10 = 3
            goto L_0x0148
        L_0x072b:
            r5 = r16
            r4 = 1
            r10 = 8
            if (r15 != 0) goto L_0x0736
            if (r5 != 0) goto L_0x0736
        L_0x0734:
            r9 = 1
            goto L_0x073b
        L_0x0736:
            if (r12 != r15) goto L_0x073b
            if (r13 != r5) goto L_0x073b
            goto L_0x0734
        L_0x073b:
            r13 = r5
            r12 = r15
            r4 = 4
            r5 = 8
            r7 = 1
            r10 = 3
            goto L_0x0046
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0117ee.m7319d(dy):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:239:0x04e6  */
    /* JADX WARNING: Removed duplicated region for block: B:244:0x04f6  */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x0526  */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x0528 A[SYNTHETIC] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m7316b(p000.C0110dy r31) {
        /*
            r30 = this;
            r6 = r30
            r15 = r31
            r14 = 0
            r13 = 0
        L_0x0006:
            int r0 = r6.f8085ak
            if (r13 >= r0) goto L_0x05a9
            ed[] r0 = r6.f8089ao
            r12 = r0[r13]
            ed[] r2 = r6.f8091aq
            r4 = 0
            boolean[] r5 = r6.f8090ap
            r0 = r30
            r1 = r31
            r3 = r12
            int r0 = r0.m7314a(r1, r2, r3, r4, r5)
            ed[] r1 = r6.f8091aq
            r2 = 2
            r1 = r1[r2]
            if (r1 == 0) goto L_0x05a0
            boolean[] r3 = r6.f8090ap
            r4 = 1
            boolean r5 = r3[r4]
            if (r5 != 0) goto L_0x0578
            int r5 = r12.f7972S
            int r7 = r6.f7981aa
            int r8 = r6.f8078ac
            r9 = 8
            r10 = 0
            r11 = 3
            r16 = 0
            if (r8 == r2) goto L_0x003a
            if (r8 != r9) goto L_0x0178
        L_0x003a:
            boolean r3 = r3[r14]
            if (r3 == 0) goto L_0x0178
            boolean r3 = r12.f7974U
            if (r3 == 0) goto L_0x0178
            if (r5 == r2) goto L_0x0178
            if (r7 == r2) goto L_0x0178
            if (r5 == 0) goto L_0x004b
            goto L_0x0178
        L_0x004b:
            r1 = r12
            r2 = r16
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x0054:
            if (r1 == 0) goto L_0x00ab
            int r2 = r1.f7961H
            if (r2 == r9) goto L_0x0087
            int r4 = r4 + 1
            int r2 = r1.f7981aa
            if (r2 == r11) goto L_0x0083
            int r2 = r1.mo4699c()
            int r5 = r5 + r2
            ec r2 = r1.f7995i
            ec r7 = r2.f7903b
            if (r7 == 0) goto L_0x0071
            int r2 = r2.mo4667a()
            goto L_0x0073
        L_0x0071:
            r2 = 0
        L_0x0073:
            int r5 = r5 + r2
            ec r2 = r1.f7997k
            ec r7 = r2.f7903b
            if (r7 == 0) goto L_0x007f
            int r2 = r2.mo4667a()
            goto L_0x0081
        L_0x007f:
            r2 = 0
        L_0x0081:
            int r5 = r5 + r2
            goto L_0x0088
        L_0x0083:
            float r2 = r1.f7976W
            float r3 = r3 + r2
            goto L_0x0088
        L_0x0087:
        L_0x0088:
            ec r2 = r1.f7997k
            ec r2 = r2.f7903b
            if (r2 == 0) goto L_0x0091
            ed r2 = r2.f7902a
            goto L_0x0093
        L_0x0091:
            r2 = r16
        L_0x0093:
            if (r2 != 0) goto L_0x0096
            goto L_0x00a2
        L_0x0096:
            ec r7 = r2.f7995i
            ec r7 = r7.f7903b
            if (r7 == 0) goto L_0x00a0
            ed r7 = r7.f7902a
            if (r7 == r1) goto L_0x00a2
        L_0x00a0:
            r2 = r16
        L_0x00a2:
            r29 = r2
            r2 = r1
            r1 = r29
            goto L_0x0054
        L_0x00ab:
            if (r2 == 0) goto L_0x00c6
            ec r1 = r2.f7997k
            ec r1 = r1.f7903b
            if (r1 == 0) goto L_0x00b8
            ed r2 = r1.f7902a
            int r2 = r2.f8006t
            goto L_0x00ba
        L_0x00b8:
            r2 = 0
        L_0x00ba:
            if (r1 != 0) goto L_0x00bd
            goto L_0x00c7
        L_0x00bd:
            ed r1 = r1.f7902a
            if (r1 != r6) goto L_0x00c7
            int r2 = r30.mo4711i()
            goto L_0x00c7
        L_0x00c6:
            r2 = 0
        L_0x00c7:
            float r1 = (float) r2
            float r2 = (float) r5
            float r1 = r1 - r2
            int r4 = r4 + 1
            float r2 = (float) r4
            float r2 = r1 / r2
            if (r0 == 0) goto L_0x00d7
            float r2 = (float) r0
            float r2 = r1 / r2
            r4 = r2
            r2 = 0
            goto L_0x00d8
        L_0x00d7:
            r4 = r2
        L_0x00d8:
            if (r12 == 0) goto L_0x0174
            ec r5 = r12.f7995i
            ec r7 = r5.f7903b
            if (r7 == 0) goto L_0x00e5
            int r5 = r5.mo4667a()
            goto L_0x00e7
        L_0x00e5:
            r5 = 0
        L_0x00e7:
            ec r7 = r12.f7997k
            ec r8 = r7.f7903b
            if (r8 == 0) goto L_0x00f2
            int r7 = r7.mo4667a()
            goto L_0x00f4
        L_0x00f2:
            r7 = 0
        L_0x00f4:
            int r8 = r12.f7961H
            r17 = 1056964608(0x3f000000, float:0.5)
            if (r8 == r9) goto L_0x0135
            float r5 = (float) r5
            float r2 = r2 + r5
            ec r8 = r12.f7995i
            eb r8 = r8.f7906e
            float r9 = r2 + r17
            int r9 = (int) r9
            r15.mo4558a((p000.C0114eb) r8, (int) r9)
            int r8 = r12.f7981aa
            if (r8 == r11) goto L_0x0111
            int r5 = r12.mo4699c()
            float r5 = (float) r5
            float r2 = r2 + r5
            goto L_0x0124
        L_0x0111:
            int r8 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r8 == 0) goto L_0x011f
            float r8 = r12.f7976W
            float r8 = r8 * r1
            float r8 = r8 / r3
            float r8 = r8 - r5
            float r5 = (float) r7
            float r8 = r8 - r5
            float r2 = r2 + r8
            goto L_0x0124
        L_0x011f:
            float r5 = r4 - r5
            float r8 = (float) r7
            float r5 = r5 - r8
            float r2 = r2 + r5
        L_0x0124:
            ec r5 = r12.f7997k
            eb r5 = r5.f7906e
            float r8 = r2 + r17
            int r8 = (int) r8
            r15.mo4558a((p000.C0114eb) r5, (int) r8)
            if (r0 == 0) goto L_0x0131
            goto L_0x0132
        L_0x0131:
            float r2 = r2 + r4
        L_0x0132:
            float r5 = (float) r7
            float r2 = r2 + r5
            goto L_0x014c
        L_0x0135:
            r5 = 1073741824(0x40000000, float:2.0)
            float r5 = r4 / r5
            float r5 = r2 - r5
            float r5 = r5 + r17
            int r5 = (int) r5
            ec r7 = r12.f7995i
            eb r7 = r7.f7906e
            r15.mo4558a((p000.C0114eb) r7, (int) r5)
            ec r7 = r12.f7997k
            eb r7 = r7.f7906e
            r15.mo4558a((p000.C0114eb) r7, (int) r5)
        L_0x014c:
            ec r5 = r12.f7997k
            ec r5 = r5.f7903b
            if (r5 == 0) goto L_0x0155
            ed r5 = r5.f7902a
            goto L_0x0158
        L_0x0155:
            r5 = r16
        L_0x0158:
            if (r5 == 0) goto L_0x0167
            ec r7 = r5.f7995i
            ec r7 = r7.f7903b
            if (r7 == 0) goto L_0x0167
            ed r7 = r7.f7902a
            if (r7 == r12) goto L_0x0167
            r12 = r16
            goto L_0x0168
        L_0x0167:
            r12 = r5
        L_0x0168:
            if (r12 != r6) goto L_0x0170
            r12 = r16
            r9 = 8
            goto L_0x00d8
        L_0x0170:
            r9 = 8
            goto L_0x00d8
        L_0x0174:
            r0 = r13
            r6 = 0
            goto L_0x05a2
        L_0x0178:
            if (r0 != 0) goto L_0x0180
            r0 = r1
            r2 = r16
            r3 = r2
            goto L_0x03d8
        L_0x0180:
            if (r5 == r2) goto L_0x03d3
            r3 = r16
        L_0x0185:
            if (r1 == 0) goto L_0x0228
            int r5 = r1.f7981aa
            if (r5 == r11) goto L_0x01e4
            ec r5 = r1.f7995i
            int r5 = r5.mo4667a()
            if (r3 == 0) goto L_0x019b
            ec r3 = r3.f7997k
            int r3 = r3.mo4667a()
            int r5 = r5 + r3
            goto L_0x019c
        L_0x019b:
        L_0x019c:
            ec r3 = r1.f7995i
            ec r7 = r3.f7903b
            ed r8 = r7.f7902a
            int r8 = r8.f7981aa
            if (r8 == r11) goto L_0x01a8
            r8 = 3
            goto L_0x01aa
        L_0x01a8:
            r8 = 2
        L_0x01aa:
            eb r3 = r3.f7906e
            eb r7 = r7.f7906e
            r15.mo4560a(r3, r7, r5, r8)
            ec r3 = r1.f7997k
            int r3 = r3.mo4667a()
            ec r5 = r1.f7997k
            ec r5 = r5.f7903b
            ed r5 = r5.f7902a
            ec r5 = r5.f7995i
            ec r7 = r5.f7903b
            if (r7 != 0) goto L_0x01c4
            goto L_0x01cd
        L_0x01c4:
            ed r7 = r7.f7902a
            if (r7 != r1) goto L_0x01cd
            int r5 = r5.mo4667a()
            int r3 = r3 + r5
        L_0x01cd:
            ec r5 = r1.f7997k
            ec r7 = r5.f7903b
            ed r8 = r7.f7902a
            int r8 = r8.f7981aa
            if (r8 == r11) goto L_0x01d9
            r8 = 3
            goto L_0x01db
        L_0x01d9:
            r8 = 2
        L_0x01db:
            eb r5 = r5.f7906e
            eb r7 = r7.f7906e
            int r3 = -r3
            r15.mo4562b(r5, r7, r3, r8)
            goto L_0x021e
        L_0x01e4:
            float r3 = r1.f7976W
            float r10 = r10 + r3
            ec r3 = r1.f7997k
            ec r5 = r3.f7903b
            if (r5 == 0) goto L_0x0206
            int r3 = r3.mo4667a()
            ed[] r5 = r6.f8091aq
            r5 = r5[r11]
            if (r1 != r5) goto L_0x01f8
            goto L_0x0207
        L_0x01f8:
            ec r5 = r1.f7997k
            ec r5 = r5.f7903b
            ed r5 = r5.f7902a
            ec r5 = r5.f7995i
            int r5 = r5.mo4667a()
            int r3 = r3 + r5
            goto L_0x0207
        L_0x0206:
            r3 = 0
        L_0x0207:
            ec r5 = r1.f7997k
            eb r5 = r5.f7906e
            ec r7 = r1.f7995i
            eb r7 = r7.f7906e
            r15.mo4560a(r5, r7, r14, r4)
            ec r5 = r1.f7997k
            eb r7 = r5.f7906e
            ec r5 = r5.f7903b
            eb r5 = r5.f7906e
            int r3 = -r3
            r15.mo4562b(r7, r5, r3, r4)
        L_0x021e:
            ed r3 = r1.f7978Y
            r29 = r3
            r3 = r1
            r1 = r29
            goto L_0x0185
        L_0x0228:
            if (r0 != r4) goto L_0x02a6
            ed[] r0 = r6.f8087am
            r0 = r0[r14]
            ec r1 = r0.f7995i
            int r1 = r1.mo4667a()
            ec r3 = r0.f7995i
            ec r3 = r3.f7903b
            if (r3 != 0) goto L_0x023c
            goto L_0x0241
        L_0x023c:
            int r3 = r3.mo4667a()
            int r1 = r1 + r3
        L_0x0241:
            ec r3 = r0.f7997k
            int r3 = r3.mo4667a()
            ec r5 = r0.f7997k
            ec r5 = r5.f7903b
            if (r5 != 0) goto L_0x024e
            goto L_0x0253
        L_0x024e:
            int r5 = r5.mo4667a()
            int r3 = r3 + r5
        L_0x0253:
            ec r5 = r12.f7997k
            ec r5 = r5.f7903b
            eb r5 = r5.f7906e
            ed[] r7 = r6.f8091aq
            r8 = r7[r11]
            if (r0 != r8) goto L_0x0267
            r5 = r7[r4]
            ec r5 = r5.f7997k
            ec r5 = r5.f7903b
            eb r5 = r5.f7906e
        L_0x0267:
            int r7 = r0.f7989c
            if (r7 != r4) goto L_0x0291
            ec r0 = r12.f7995i
            eb r7 = r0.f7906e
            ec r0 = r0.f7903b
            eb r0 = r0.f7906e
            r15.mo4560a(r7, r0, r1, r4)
            ec r0 = r12.f7997k
            eb r0 = r0.f7906e
            int r1 = -r3
            r15.mo4562b(r0, r5, r1, r4)
            ec r0 = r12.f7997k
            eb r0 = r0.f7906e
            ec r1 = r12.f7995i
            eb r1 = r1.f7906e
            int r3 = r12.mo4699c()
            r15.mo4564c(r0, r1, r3, r2)
            r0 = r13
            r6 = 0
            goto L_0x05a2
        L_0x0291:
            ec r2 = r0.f7995i
            eb r7 = r2.f7906e
            ec r2 = r2.f7903b
            eb r2 = r2.f7906e
            r15.mo4564c(r7, r2, r1, r4)
            ec r0 = r0.f7997k
            eb r0 = r0.f7906e
            int r1 = -r3
            r15.mo4564c(r0, r5, r1, r4)
            goto L_0x03cf
        L_0x02a6:
            r1 = 0
        L_0x02a8:
            int r3 = r0 + -1
            if (r1 >= r3) goto L_0x03cf
            ed[] r5 = r6.f8087am
            r7 = r5[r1]
            int r1 = r1 + 1
            r5 = r5[r1]
            ec r8 = r7.f7995i
            eb r9 = r8.f7906e
            ec r14 = r7.f7997k
            eb r14 = r14.f7906e
            ec r2 = r5.f7995i
            eb r2 = r2.f7906e
            ec r4 = r5.f7997k
            eb r4 = r4.f7906e
            r28 = r0
            ed[] r0 = r6.f8091aq
            r16 = r4
            r4 = r0[r11]
            if (r5 == r4) goto L_0x02d1
            r0 = r16
            goto L_0x02d9
        L_0x02d1:
            r4 = 1
            r0 = r0[r4]
            ec r0 = r0.f7997k
            eb r0 = r0.f7906e
        L_0x02d9:
            int r4 = r8.mo4667a()
            ec r8 = r7.f7995i
            ec r8 = r8.f7903b
            if (r8 != 0) goto L_0x02e4
        L_0x02e3:
            goto L_0x02f5
        L_0x02e4:
            ed r8 = r8.f7902a
            ec r8 = r8.f7997k
            ec r11 = r8.f7903b
            if (r11 == 0) goto L_0x02e3
            ed r11 = r11.f7902a
            if (r11 != r7) goto L_0x02f5
            int r8 = r8.mo4667a()
            int r4 = r4 + r8
        L_0x02f5:
            ec r8 = r7.f7995i
            ec r8 = r8.f7903b
            eb r8 = r8.f7906e
            r11 = 2
            r15.mo4560a(r9, r8, r4, r11)
            ec r4 = r7.f7997k
            int r4 = r4.mo4667a()
            ec r8 = r7.f7997k
            ec r8 = r8.f7903b
            if (r8 == 0) goto L_0x031e
            ed r8 = r7.f7978Y
            if (r8 == 0) goto L_0x031e
            ec r8 = r8.f7995i
            ec r11 = r8.f7903b
            if (r11 == 0) goto L_0x031a
            int r8 = r8.mo4667a()
            goto L_0x031c
        L_0x031a:
            r8 = 0
        L_0x031c:
            int r4 = r4 + r8
            goto L_0x031f
        L_0x031e:
        L_0x031f:
            ec r8 = r7.f7997k
            ec r8 = r8.f7903b
            eb r8 = r8.f7906e
            int r4 = -r4
            r11 = 2
            r15.mo4562b(r14, r8, r4, r11)
            if (r1 != r3) goto L_0x0388
            ec r3 = r5.f7995i
            int r3 = r3.mo4667a()
            ec r4 = r5.f7995i
            ec r4 = r4.f7903b
            if (r4 != 0) goto L_0x0339
        L_0x0338:
            goto L_0x034a
        L_0x0339:
            ed r4 = r4.f7902a
            ec r4 = r4.f7997k
            ec r8 = r4.f7903b
            if (r8 == 0) goto L_0x0338
            ed r8 = r8.f7902a
            if (r8 != r5) goto L_0x034a
            int r4 = r4.mo4667a()
            int r3 = r3 + r4
        L_0x034a:
            ec r4 = r5.f7995i
            ec r4 = r4.f7903b
            eb r4 = r4.f7906e
            r8 = 2
            r15.mo4560a(r2, r4, r3, r8)
            ec r3 = r5.f7997k
            ed[] r4 = r6.f8091aq
            r8 = 3
            r11 = r4[r8]
            if (r5 == r11) goto L_0x035e
            goto L_0x0364
        L_0x035e:
            r3 = 1
            r4 = r4[r3]
            ec r3 = r4.f7997k
        L_0x0364:
            int r4 = r3.mo4667a()
            ec r8 = r3.f7903b
            if (r8 == 0) goto L_0x037e
            ed r8 = r8.f7902a
            ec r8 = r8.f7995i
            ec r11 = r8.f7903b
            if (r11 != 0) goto L_0x0375
            goto L_0x037e
        L_0x0375:
            ed r11 = r11.f7902a
            if (r11 != r5) goto L_0x037e
            int r8 = r8.mo4667a()
            int r4 = r4 + r8
        L_0x037e:
            ec r3 = r3.f7903b
            eb r3 = r3.f7906e
            int r4 = -r4
            r8 = 2
            r15.mo4562b(r0, r3, r4, r8)
            goto L_0x0389
        L_0x0388:
            r8 = 2
        L_0x0389:
            int r3 = r12.f7992f
            if (r3 <= 0) goto L_0x0390
            r15.mo4562b(r14, r9, r3, r8)
        L_0x0390:
            dv r3 = r31.mo4561b()
            float r4 = r7.f7976W
            float r8 = r5.f7976W
            ec r11 = r7.f7995i
            int r21 = r11.mo4667a()
            ec r7 = r7.f7997k
            int r23 = r7.mo4667a()
            ec r7 = r5.f7995i
            int r25 = r7.mo4667a()
            ec r5 = r5.f7997k
            int r27 = r5.mo4667a()
            r16 = r3
            r17 = r4
            r18 = r10
            r19 = r8
            r20 = r9
            r22 = r14
            r24 = r2
            r26 = r0
            r16.mo4466a(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            r15.mo4556a((p000.C0107dv) r3)
            r0 = r28
            r2 = 2
            r4 = 1
            r11 = 3
            r14 = 0
            goto L_0x02a8
        L_0x03cf:
            r0 = r13
            r6 = 0
            goto L_0x05a2
        L_0x03d3:
            r0 = r1
            r2 = r16
            r3 = r2
            r14 = 0
        L_0x03d8:
            if (r0 == 0) goto L_0x0534
            ed r4 = r0.f7978Y
            if (r4 != 0) goto L_0x03e6
            ed[] r2 = r6.f8091aq
            r7 = 1
            r2 = r2[r7]
            r17 = 1
            goto L_0x03e8
        L_0x03e6:
            r17 = r14
        L_0x03e8:
            r7 = 2
            if (r5 != r7) goto L_0x0444
            ec r7 = r0.f7995i
            int r8 = r7.mo4667a()
            if (r3 == 0) goto L_0x03fb
            ec r3 = r3.f7997k
            int r3 = r3.mo4667a()
            int r8 = r8 + r3
            goto L_0x03fc
        L_0x03fb:
        L_0x03fc:
            if (r1 != r0) goto L_0x0400
            r3 = 1
            goto L_0x0402
        L_0x0400:
            r3 = 3
        L_0x0402:
            eb r9 = r7.f7906e
            ec r10 = r7.f7903b
            eb r10 = r10.f7906e
            r15.mo4560a(r9, r10, r8, r3)
            int r3 = r0.f7981aa
            r8 = 3
            if (r3 != r8) goto L_0x0442
            ec r3 = r0.f7997k
            int r8 = r0.f7989c
            r9 = 1
            if (r8 != r9) goto L_0x042b
            int r8 = r0.f7991e
            int r9 = r0.mo4699c()
            int r8 = java.lang.Math.max(r8, r9)
            eb r3 = r3.f7906e
            eb r7 = r7.f7906e
            r11 = 3
            r15.mo4564c(r3, r7, r8, r11)
            goto L_0x04a0
        L_0x042b:
            r11 = 3
            eb r8 = r7.f7906e
            ec r9 = r7.f7903b
            eb r9 = r9.f7906e
            int r10 = r7.f7904c
            r15.mo4560a(r8, r9, r10, r11)
            eb r3 = r3.f7906e
            eb r7 = r7.f7906e
            int r8 = r0.f7991e
            r15.mo4562b(r3, r7, r8, r11)
            goto L_0x04a0
        L_0x0442:
            r11 = 3
            goto L_0x04a0
        L_0x0444:
            r11 = 3
            r7 = 5
            if (r5 == 0) goto L_0x0475
            if (r17 == 0) goto L_0x0475
            if (r3 != 0) goto L_0x044d
            goto L_0x0475
        L_0x044d:
            ec r3 = r0.f7997k
            ec r8 = r3.f7903b
            if (r8 != 0) goto L_0x0461
            eb r3 = r3.f7906e
            int r7 = r0.mo4708g()
            int r8 = r0.f8008v
            int r7 = r7 + r8
            r15.mo4558a((p000.C0114eb) r3, (int) r7)
            goto L_0x04a0
        L_0x0461:
            int r3 = r3.mo4667a()
            ec r8 = r0.f7997k
            eb r8 = r8.f7906e
            ec r9 = r2.f7997k
            ec r9 = r9.f7903b
            eb r9 = r9.f7906e
            int r3 = -r3
            r15.mo4564c(r8, r9, r3, r7)
            goto L_0x04a0
        L_0x0475:
            if (r5 == 0) goto L_0x04a7
            if (r17 != 0) goto L_0x04a7
            if (r3 == 0) goto L_0x047c
            goto L_0x04a7
        L_0x047c:
            ec r3 = r0.f7995i
            ec r8 = r3.f7903b
            if (r8 != 0) goto L_0x048d
            eb r3 = r3.f7906e
            int r7 = r0.mo4708g()
            r15.mo4558a((p000.C0114eb) r3, (int) r7)
            goto L_0x04a0
        L_0x048d:
            int r3 = r3.mo4667a()
            ec r8 = r0.f7995i
            eb r8 = r8.f7906e
            ec r9 = r12.f7995i
            ec r9 = r9.f7903b
            eb r9 = r9.f7906e
            r15.mo4564c(r8, r9, r3, r7)
        L_0x04a0:
            r18 = r0
            r3 = r12
            r0 = r13
            r6 = 0
            goto L_0x0523
        L_0x04a7:
            ec r7 = r0.f7995i
            ec r8 = r0.f7997k
            int r10 = r7.mo4667a()
            int r14 = r8.mo4667a()
            eb r9 = r7.f7906e
            ec r11 = r7.f7903b
            eb r11 = r11.f7906e
            r18 = r0
            r0 = 1
            r15.mo4560a(r9, r11, r10, r0)
            eb r9 = r8.f7906e
            ec r11 = r8.f7903b
            eb r11 = r11.f7906e
            int r6 = -r14
            r15.mo4562b(r9, r11, r6, r0)
            ec r0 = r7.f7903b
            if (r0 == 0) goto L_0x04d0
            eb r0 = r0.f7906e
            goto L_0x04d3
        L_0x04d0:
            r0 = r16
        L_0x04d3:
            if (r3 != 0) goto L_0x04e2
            ec r0 = r12.f7995i
            ec r0 = r0.f7903b
            if (r0 == 0) goto L_0x04de
            eb r0 = r0.f7906e
            goto L_0x04e3
        L_0x04de:
            r9 = r16
            goto L_0x04e4
        L_0x04e2:
        L_0x04e3:
            r9 = r0
        L_0x04e4:
            if (r4 != 0) goto L_0x04f3
            ec r0 = r2.f7997k
            ec r0 = r0.f7903b
            if (r0 == 0) goto L_0x04ef
            ed r4 = r0.f7902a
            goto L_0x04f4
        L_0x04ef:
            r4 = r16
            goto L_0x04f4
        L_0x04f3:
        L_0x04f4:
            if (r4 == 0) goto L_0x0520
            ec r0 = r4.f7995i
            eb r0 = r0.f7906e
            if (r17 == 0) goto L_0x0508
            ec r0 = r2.f7997k
            ec r0 = r0.f7903b
            if (r0 == 0) goto L_0x0505
            eb r0 = r0.f7906e
            goto L_0x0508
        L_0x0505:
            r0 = r16
        L_0x0508:
            if (r9 == 0) goto L_0x0520
            if (r0 == 0) goto L_0x0520
            eb r3 = r7.f7906e
            r11 = 1056964608(0x3f000000, float:0.5)
            eb r6 = r8.f7906e
            r7 = r31
            r8 = r3
            r3 = 3
            r3 = r12
            r12 = r0
            r0 = r13
            r13 = r6
            r6 = 0
            r7.mo4559a(r8, r9, r10, r11, r12, r13, r14)
            goto L_0x0523
        L_0x0520:
            r3 = r12
            r0 = r13
            r6 = 0
        L_0x0523:
            if (r17 != 0) goto L_0x0526
            goto L_0x0528
        L_0x0526:
            r4 = r16
        L_0x0528:
            r6 = r30
            r13 = r0
            r12 = r3
            r0 = r4
            r14 = r17
            r3 = r18
            goto L_0x03d8
        L_0x0534:
            r3 = r12
            r0 = r13
            r6 = 0
            r4 = 2
            if (r5 != r4) goto L_0x05a2
            ec r1 = r1.f7995i
            ec r4 = r2.f7997k
            int r10 = r1.mo4667a()
            int r14 = r4.mo4667a()
            ec r5 = r3.f7995i
            ec r5 = r5.f7903b
            if (r5 == 0) goto L_0x0550
            eb r5 = r5.f7906e
            r9 = r5
            goto L_0x0553
        L_0x0550:
            r9 = r16
        L_0x0553:
            ec r2 = r2.f7997k
            ec r2 = r2.f7903b
            if (r2 == 0) goto L_0x055d
            eb r2 = r2.f7906e
            r12 = r2
            goto L_0x0560
        L_0x055d:
            r12 = r16
        L_0x0560:
            if (r9 != 0) goto L_0x0563
        L_0x0562:
            goto L_0x05a2
        L_0x0563:
            if (r12 == 0) goto L_0x0562
            eb r2 = r4.f7906e
            int r5 = -r14
            r7 = 1
            r15.mo4562b(r2, r12, r5, r7)
            eb r8 = r1.f7906e
            float r11 = r3.f7958E
            eb r13 = r4.f7906e
            r7 = r31
            r7.mo4559a(r8, r9, r10, r11, r12, r13, r14)
            goto L_0x05a2
        L_0x0578:
            r3 = r12
            r0 = r13
            r6 = 0
            int r2 = r3.mo4708g()
        L_0x057f:
            if (r1 == 0) goto L_0x05a2
            ec r3 = r1.f7995i
            eb r3 = r3.f7906e
            r15.mo4558a((p000.C0114eb) r3, (int) r2)
            ed r3 = r1.f7978Y
            ec r4 = r1.f7995i
            int r4 = r4.mo4667a()
            int r5 = r1.mo4699c()
            int r4 = r4 + r5
            ec r1 = r1.f7997k
            int r1 = r1.mo4667a()
            int r4 = r4 + r1
            int r2 = r2 + r4
            r1 = r3
            goto L_0x057f
        L_0x05a0:
            r0 = r13
            r6 = 0
        L_0x05a2:
            int r13 = r0 + 1
            r14 = 0
            r6 = r30
            goto L_0x0006
        L_0x05a9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0117ee.m7316b(dy):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:253:0x052f  */
    /* JADX WARNING: Removed duplicated region for block: B:258:0x053f  */
    /* JADX WARNING: Removed duplicated region for block: B:269:0x056f  */
    /* JADX WARNING: Removed duplicated region for block: B:315:0x0571 A[SYNTHETIC] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m7318c(p000.C0110dy r31) {
        /*
            r30 = this;
            r6 = r30
            r15 = r31
            r14 = 0
            r13 = 0
        L_0x0006:
            int r0 = r6.f8086al
            if (r13 >= r0) goto L_0x05ca
            ed[] r0 = r6.f8088an
            r12 = r0[r13]
            ed[] r2 = r6.f8091aq
            r4 = 1
            boolean[] r5 = r6.f8090ap
            r0 = r30
            r1 = r31
            r3 = r12
            int r0 = r0.m7314a(r1, r2, r3, r4, r5)
            ed[] r1 = r6.f8091aq
            r2 = 2
            r1 = r1[r2]
            if (r1 == 0) goto L_0x05c1
            boolean[] r3 = r6.f8090ap
            r4 = 1
            boolean r5 = r3[r4]
            if (r5 == 0) goto L_0x0053
            int r0 = r12.mo4710h()
        L_0x002e:
            if (r1 == 0) goto L_0x004f
            ec r2 = r1.f7996j
            eb r2 = r2.f7906e
            r15.mo4558a((p000.C0114eb) r2, (int) r0)
            ed r2 = r1.f7979Z
            ec r3 = r1.f7996j
            int r3 = r3.mo4667a()
            int r4 = r1.mo4706f()
            int r3 = r3 + r4
            ec r1 = r1.f7998l
            int r1 = r1.mo4667a()
            int r3 = r3 + r1
            int r0 = r0 + r3
            r1 = r2
            goto L_0x002e
        L_0x004f:
            r0 = r13
            r6 = 0
            goto L_0x05c3
        L_0x0053:
            int r5 = r12.f7973T
            int r7 = r6.f7982ab
            int r8 = r6.f8078ac
            r9 = 8
            r10 = 0
            r11 = 3
            r16 = 0
            if (r8 == r2) goto L_0x0063
            if (r8 != r9) goto L_0x01a1
        L_0x0063:
            boolean r3 = r3[r14]
            if (r3 == 0) goto L_0x01a1
            boolean r3 = r12.f7975V
            if (r3 == 0) goto L_0x01a1
            if (r5 == r2) goto L_0x01a1
            if (r7 == r2) goto L_0x01a1
            if (r5 == 0) goto L_0x0074
            goto L_0x01a1
        L_0x0074:
            r1 = r12
            r2 = r16
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x007d:
            if (r1 == 0) goto L_0x00d4
            int r2 = r1.f7961H
            if (r2 == r9) goto L_0x00b0
            int r4 = r4 + 1
            int r2 = r1.f7982ab
            if (r2 == r11) goto L_0x00ac
            int r2 = r1.mo4706f()
            int r5 = r5 + r2
            ec r2 = r1.f7996j
            ec r7 = r2.f7903b
            if (r7 == 0) goto L_0x009a
            int r2 = r2.mo4667a()
            goto L_0x009c
        L_0x009a:
            r2 = 0
        L_0x009c:
            int r5 = r5 + r2
            ec r2 = r1.f7998l
            ec r7 = r2.f7903b
            if (r7 == 0) goto L_0x00a8
            int r2 = r2.mo4667a()
            goto L_0x00aa
        L_0x00a8:
            r2 = 0
        L_0x00aa:
            int r5 = r5 + r2
            goto L_0x00b1
        L_0x00ac:
            float r2 = r1.f7977X
            float r3 = r3 + r2
            goto L_0x00b1
        L_0x00b0:
        L_0x00b1:
            ec r2 = r1.f7998l
            ec r2 = r2.f7903b
            if (r2 == 0) goto L_0x00ba
            ed r2 = r2.f7902a
            goto L_0x00bc
        L_0x00ba:
            r2 = r16
        L_0x00bc:
            if (r2 != 0) goto L_0x00bf
            goto L_0x00cb
        L_0x00bf:
            ec r7 = r2.f7996j
            ec r7 = r7.f7903b
            if (r7 == 0) goto L_0x00c9
            ed r7 = r7.f7902a
            if (r7 == r1) goto L_0x00cb
        L_0x00c9:
            r2 = r16
        L_0x00cb:
            r29 = r2
            r2 = r1
            r1 = r29
            goto L_0x007d
        L_0x00d4:
            if (r2 == 0) goto L_0x00ef
            ec r1 = r2.f7998l
            ec r1 = r1.f7903b
            if (r1 == 0) goto L_0x00e1
            ed r2 = r1.f7902a
            int r2 = r2.f8006t
            goto L_0x00e3
        L_0x00e1:
            r2 = 0
        L_0x00e3:
            if (r1 != 0) goto L_0x00e6
            goto L_0x00f0
        L_0x00e6:
            ed r1 = r1.f7902a
            if (r1 != r6) goto L_0x00f0
            int r2 = r30.mo4712j()
            goto L_0x00f0
        L_0x00ef:
            r2 = 0
        L_0x00f0:
            float r1 = (float) r2
            float r2 = (float) r5
            float r1 = r1 - r2
            int r4 = r4 + 1
            float r2 = (float) r4
            float r2 = r1 / r2
            if (r0 == 0) goto L_0x0100
            float r2 = (float) r0
            float r2 = r1 / r2
            r4 = r2
            r2 = 0
            goto L_0x0101
        L_0x0100:
            r4 = r2
        L_0x0101:
            if (r12 == 0) goto L_0x019d
            ec r5 = r12.f7996j
            ec r7 = r5.f7903b
            if (r7 == 0) goto L_0x010e
            int r5 = r5.mo4667a()
            goto L_0x0110
        L_0x010e:
            r5 = 0
        L_0x0110:
            ec r7 = r12.f7998l
            ec r8 = r7.f7903b
            if (r8 == 0) goto L_0x011b
            int r7 = r7.mo4667a()
            goto L_0x011d
        L_0x011b:
            r7 = 0
        L_0x011d:
            int r8 = r12.f7961H
            r17 = 1056964608(0x3f000000, float:0.5)
            if (r8 == r9) goto L_0x015e
            float r5 = (float) r5
            float r2 = r2 + r5
            ec r8 = r12.f7996j
            eb r8 = r8.f7906e
            float r9 = r2 + r17
            int r9 = (int) r9
            r15.mo4558a((p000.C0114eb) r8, (int) r9)
            int r8 = r12.f7982ab
            if (r8 == r11) goto L_0x013a
            int r5 = r12.mo4706f()
            float r5 = (float) r5
            float r2 = r2 + r5
            goto L_0x014d
        L_0x013a:
            int r8 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r8 == 0) goto L_0x0148
            float r8 = r12.f7977X
            float r8 = r8 * r1
            float r8 = r8 / r3
            float r8 = r8 - r5
            float r5 = (float) r7
            float r8 = r8 - r5
            float r2 = r2 + r8
            goto L_0x014d
        L_0x0148:
            float r5 = r4 - r5
            float r8 = (float) r7
            float r5 = r5 - r8
            float r2 = r2 + r5
        L_0x014d:
            ec r5 = r12.f7998l
            eb r5 = r5.f7906e
            float r8 = r2 + r17
            int r8 = (int) r8
            r15.mo4558a((p000.C0114eb) r5, (int) r8)
            if (r0 == 0) goto L_0x015a
            goto L_0x015b
        L_0x015a:
            float r2 = r2 + r4
        L_0x015b:
            float r5 = (float) r7
            float r2 = r2 + r5
            goto L_0x0175
        L_0x015e:
            r5 = 1073741824(0x40000000, float:2.0)
            float r5 = r4 / r5
            float r5 = r2 - r5
            float r5 = r5 + r17
            int r5 = (int) r5
            ec r7 = r12.f7996j
            eb r7 = r7.f7906e
            r15.mo4558a((p000.C0114eb) r7, (int) r5)
            ec r7 = r12.f7998l
            eb r7 = r7.f7906e
            r15.mo4558a((p000.C0114eb) r7, (int) r5)
        L_0x0175:
            ec r5 = r12.f7998l
            ec r5 = r5.f7903b
            if (r5 == 0) goto L_0x017e
            ed r5 = r5.f7902a
            goto L_0x0181
        L_0x017e:
            r5 = r16
        L_0x0181:
            if (r5 == 0) goto L_0x0190
            ec r7 = r5.f7996j
            ec r7 = r7.f7903b
            if (r7 == 0) goto L_0x0190
            ed r7 = r7.f7902a
            if (r7 == r12) goto L_0x0190
            r12 = r16
            goto L_0x0191
        L_0x0190:
            r12 = r5
        L_0x0191:
            if (r12 != r6) goto L_0x0199
            r12 = r16
            r9 = 8
            goto L_0x0101
        L_0x0199:
            r9 = 8
            goto L_0x0101
        L_0x019d:
            r0 = r13
            r6 = 0
            goto L_0x05c3
        L_0x01a1:
            if (r0 != 0) goto L_0x01a9
            r0 = r1
            r2 = r16
            r3 = r2
            goto L_0x0401
        L_0x01a9:
            if (r5 == r2) goto L_0x03fc
            r3 = r16
        L_0x01ae:
            if (r1 == 0) goto L_0x0251
            int r5 = r1.f7982ab
            if (r5 == r11) goto L_0x020d
            ec r5 = r1.f7996j
            int r5 = r5.mo4667a()
            if (r3 == 0) goto L_0x01c4
            ec r3 = r3.f7998l
            int r3 = r3.mo4667a()
            int r5 = r5 + r3
            goto L_0x01c5
        L_0x01c4:
        L_0x01c5:
            ec r3 = r1.f7996j
            ec r7 = r3.f7903b
            ed r8 = r7.f7902a
            int r8 = r8.f7982ab
            if (r8 == r11) goto L_0x01d1
            r8 = 3
            goto L_0x01d3
        L_0x01d1:
            r8 = 2
        L_0x01d3:
            eb r3 = r3.f7906e
            eb r7 = r7.f7906e
            r15.mo4560a(r3, r7, r5, r8)
            ec r3 = r1.f7998l
            int r3 = r3.mo4667a()
            ec r5 = r1.f7998l
            ec r5 = r5.f7903b
            ed r5 = r5.f7902a
            ec r5 = r5.f7996j
            ec r7 = r5.f7903b
            if (r7 != 0) goto L_0x01ed
            goto L_0x01f6
        L_0x01ed:
            ed r7 = r7.f7902a
            if (r7 != r1) goto L_0x01f6
            int r5 = r5.mo4667a()
            int r3 = r3 + r5
        L_0x01f6:
            ec r5 = r1.f7998l
            ec r7 = r5.f7903b
            ed r8 = r7.f7902a
            int r8 = r8.f7982ab
            if (r8 == r11) goto L_0x0202
            r8 = 3
            goto L_0x0204
        L_0x0202:
            r8 = 2
        L_0x0204:
            eb r5 = r5.f7906e
            eb r7 = r7.f7906e
            int r3 = -r3
            r15.mo4562b(r5, r7, r3, r8)
            goto L_0x0247
        L_0x020d:
            float r3 = r1.f7977X
            float r10 = r10 + r3
            ec r3 = r1.f7998l
            ec r5 = r3.f7903b
            if (r5 == 0) goto L_0x022f
            int r3 = r3.mo4667a()
            ed[] r5 = r6.f8091aq
            r5 = r5[r11]
            if (r1 != r5) goto L_0x0221
            goto L_0x0230
        L_0x0221:
            ec r5 = r1.f7998l
            ec r5 = r5.f7903b
            ed r5 = r5.f7902a
            ec r5 = r5.f7996j
            int r5 = r5.mo4667a()
            int r3 = r3 + r5
            goto L_0x0230
        L_0x022f:
            r3 = 0
        L_0x0230:
            ec r5 = r1.f7998l
            eb r5 = r5.f7906e
            ec r7 = r1.f7996j
            eb r7 = r7.f7906e
            r15.mo4560a(r5, r7, r14, r4)
            ec r5 = r1.f7998l
            eb r7 = r5.f7906e
            ec r5 = r5.f7903b
            eb r5 = r5.f7906e
            int r3 = -r3
            r15.mo4562b(r7, r5, r3, r4)
        L_0x0247:
            ed r3 = r1.f7979Z
            r29 = r3
            r3 = r1
            r1 = r29
            goto L_0x01ae
        L_0x0251:
            if (r0 != r4) goto L_0x02cf
            ed[] r0 = r6.f8087am
            r0 = r0[r14]
            ec r1 = r0.f7996j
            int r1 = r1.mo4667a()
            ec r3 = r0.f7996j
            ec r3 = r3.f7903b
            if (r3 != 0) goto L_0x0265
            goto L_0x026a
        L_0x0265:
            int r3 = r3.mo4667a()
            int r1 = r1 + r3
        L_0x026a:
            ec r3 = r0.f7998l
            int r3 = r3.mo4667a()
            ec r5 = r0.f7998l
            ec r5 = r5.f7903b
            if (r5 != 0) goto L_0x0277
            goto L_0x027c
        L_0x0277:
            int r5 = r5.mo4667a()
            int r3 = r3 + r5
        L_0x027c:
            ec r5 = r12.f7998l
            ec r5 = r5.f7903b
            eb r5 = r5.f7906e
            ed[] r7 = r6.f8091aq
            r8 = r7[r11]
            if (r0 != r8) goto L_0x0290
            r5 = r7[r4]
            ec r5 = r5.f7998l
            ec r5 = r5.f7903b
            eb r5 = r5.f7906e
        L_0x0290:
            int r7 = r0.f7990d
            if (r7 != r4) goto L_0x02ba
            ec r0 = r12.f7996j
            eb r7 = r0.f7906e
            ec r0 = r0.f7903b
            eb r0 = r0.f7906e
            r15.mo4560a(r7, r0, r1, r4)
            ec r0 = r12.f7998l
            eb r0 = r0.f7906e
            int r1 = -r3
            r15.mo4562b(r0, r5, r1, r4)
            ec r0 = r12.f7998l
            eb r0 = r0.f7906e
            ec r1 = r12.f7996j
            eb r1 = r1.f7906e
            int r3 = r12.mo4706f()
            r15.mo4564c(r0, r1, r3, r2)
            r0 = r13
            r6 = 0
            goto L_0x05c3
        L_0x02ba:
            ec r2 = r0.f7996j
            eb r7 = r2.f7906e
            ec r2 = r2.f7903b
            eb r2 = r2.f7906e
            r15.mo4564c(r7, r2, r1, r4)
            ec r0 = r0.f7998l
            eb r0 = r0.f7906e
            int r1 = -r3
            r15.mo4564c(r0, r5, r1, r4)
            goto L_0x03f8
        L_0x02cf:
            r1 = 0
        L_0x02d1:
            int r3 = r0 + -1
            if (r1 >= r3) goto L_0x03f8
            ed[] r5 = r6.f8087am
            r7 = r5[r1]
            int r1 = r1 + 1
            r5 = r5[r1]
            ec r8 = r7.f7996j
            eb r9 = r8.f7906e
            ec r14 = r7.f7998l
            eb r14 = r14.f7906e
            ec r2 = r5.f7996j
            eb r2 = r2.f7906e
            ec r4 = r5.f7998l
            eb r4 = r4.f7906e
            r28 = r0
            ed[] r0 = r6.f8091aq
            r16 = r4
            r4 = r0[r11]
            if (r5 == r4) goto L_0x02fa
            r0 = r16
            goto L_0x0302
        L_0x02fa:
            r4 = 1
            r0 = r0[r4]
            ec r0 = r0.f7998l
            eb r0 = r0.f7906e
        L_0x0302:
            int r4 = r8.mo4667a()
            ec r8 = r7.f7996j
            ec r8 = r8.f7903b
            if (r8 != 0) goto L_0x030d
        L_0x030c:
            goto L_0x031e
        L_0x030d:
            ed r8 = r8.f7902a
            ec r8 = r8.f7998l
            ec r11 = r8.f7903b
            if (r11 == 0) goto L_0x030c
            ed r11 = r11.f7902a
            if (r11 != r7) goto L_0x031e
            int r8 = r8.mo4667a()
            int r4 = r4 + r8
        L_0x031e:
            ec r8 = r7.f7996j
            ec r8 = r8.f7903b
            eb r8 = r8.f7906e
            r11 = 2
            r15.mo4560a(r9, r8, r4, r11)
            ec r4 = r7.f7998l
            int r4 = r4.mo4667a()
            ec r8 = r7.f7998l
            ec r8 = r8.f7903b
            if (r8 == 0) goto L_0x0347
            ed r8 = r7.f7979Z
            if (r8 == 0) goto L_0x0347
            ec r8 = r8.f7996j
            ec r11 = r8.f7903b
            if (r11 == 0) goto L_0x0343
            int r8 = r8.mo4667a()
            goto L_0x0345
        L_0x0343:
            r8 = 0
        L_0x0345:
            int r4 = r4 + r8
            goto L_0x0348
        L_0x0347:
        L_0x0348:
            ec r8 = r7.f7998l
            ec r8 = r8.f7903b
            eb r8 = r8.f7906e
            int r4 = -r4
            r11 = 2
            r15.mo4562b(r14, r8, r4, r11)
            if (r1 != r3) goto L_0x03b1
            ec r3 = r5.f7996j
            int r3 = r3.mo4667a()
            ec r4 = r5.f7996j
            ec r4 = r4.f7903b
            if (r4 != 0) goto L_0x0362
        L_0x0361:
            goto L_0x0373
        L_0x0362:
            ed r4 = r4.f7902a
            ec r4 = r4.f7998l
            ec r8 = r4.f7903b
            if (r8 == 0) goto L_0x0361
            ed r8 = r8.f7902a
            if (r8 != r5) goto L_0x0373
            int r4 = r4.mo4667a()
            int r3 = r3 + r4
        L_0x0373:
            ec r4 = r5.f7996j
            ec r4 = r4.f7903b
            eb r4 = r4.f7906e
            r8 = 2
            r15.mo4560a(r2, r4, r3, r8)
            ec r3 = r5.f7998l
            ed[] r4 = r6.f8091aq
            r8 = 3
            r11 = r4[r8]
            if (r5 == r11) goto L_0x0387
            goto L_0x038d
        L_0x0387:
            r3 = 1
            r4 = r4[r3]
            ec r3 = r4.f7998l
        L_0x038d:
            int r4 = r3.mo4667a()
            ec r8 = r3.f7903b
            if (r8 == 0) goto L_0x03a7
            ed r8 = r8.f7902a
            ec r8 = r8.f7996j
            ec r11 = r8.f7903b
            if (r11 != 0) goto L_0x039e
            goto L_0x03a7
        L_0x039e:
            ed r11 = r11.f7902a
            if (r11 != r5) goto L_0x03a7
            int r8 = r8.mo4667a()
            int r4 = r4 + r8
        L_0x03a7:
            ec r3 = r3.f7903b
            eb r3 = r3.f7906e
            int r4 = -r4
            r8 = 2
            r15.mo4562b(r0, r3, r4, r8)
            goto L_0x03b2
        L_0x03b1:
            r8 = 2
        L_0x03b2:
            int r3 = r12.f7994h
            if (r3 <= 0) goto L_0x03b9
            r15.mo4562b(r14, r9, r3, r8)
        L_0x03b9:
            dv r3 = r31.mo4561b()
            float r4 = r7.f7977X
            float r8 = r5.f7977X
            ec r11 = r7.f7996j
            int r21 = r11.mo4667a()
            ec r7 = r7.f7998l
            int r23 = r7.mo4667a()
            ec r7 = r5.f7996j
            int r25 = r7.mo4667a()
            ec r5 = r5.f7998l
            int r27 = r5.mo4667a()
            r16 = r3
            r17 = r4
            r18 = r10
            r19 = r8
            r20 = r9
            r22 = r14
            r24 = r2
            r26 = r0
            r16.mo4466a(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            r15.mo4556a((p000.C0107dv) r3)
            r0 = r28
            r2 = 2
            r4 = 1
            r11 = 3
            r14 = 0
            goto L_0x02d1
        L_0x03f8:
            r0 = r13
            r6 = 0
            goto L_0x05c3
        L_0x03fc:
            r0 = r1
            r2 = r16
            r3 = r2
            r14 = 0
        L_0x0401:
            if (r0 == 0) goto L_0x057d
            ed r4 = r0.f7979Z
            if (r4 != 0) goto L_0x040f
            ed[] r2 = r6.f8091aq
            r7 = 1
            r2 = r2[r7]
            r17 = 1
            goto L_0x0411
        L_0x040f:
            r17 = r14
        L_0x0411:
            r7 = 2
            if (r5 != r7) goto L_0x048d
            ec r7 = r0.f7996j
            int r8 = r7.mo4667a()
            if (r3 == 0) goto L_0x0424
            ec r3 = r3.f7998l
            int r3 = r3.mo4667a()
            int r8 = r8 + r3
            goto L_0x0425
        L_0x0424:
        L_0x0425:
            if (r1 != r0) goto L_0x0429
            r3 = 1
            goto L_0x042b
        L_0x0429:
            r3 = 3
        L_0x042b:
            ec r9 = r7.f7903b
            if (r9 == 0) goto L_0x0434
            eb r10 = r7.f7906e
            eb r9 = r9.f7906e
            goto L_0x044d
        L_0x0434:
            ec r9 = r0.f7999m
            ec r10 = r9.f7903b
            if (r10 == 0) goto L_0x0449
            eb r9 = r9.f7906e
            eb r10 = r10.f7906e
            int r11 = r7.mo4667a()
            int r8 = r8 - r11
            r29 = r10
            r10 = r9
            r9 = r29
            goto L_0x044d
        L_0x0449:
            r9 = r16
            r10 = r9
        L_0x044d:
            if (r10 == 0) goto L_0x0454
            if (r9 == 0) goto L_0x0454
            r15.mo4560a(r10, r9, r8, r3)
        L_0x0454:
            int r3 = r0.f7982ab
            r8 = 3
            if (r3 != r8) goto L_0x048b
            ec r3 = r0.f7998l
            int r8 = r0.f7990d
            r9 = 1
            if (r8 != r9) goto L_0x0474
            int r8 = r0.f7993g
            int r9 = r0.mo4706f()
            int r8 = java.lang.Math.max(r8, r9)
            eb r3 = r3.f7906e
            eb r7 = r7.f7906e
            r11 = 3
            r15.mo4564c(r3, r7, r8, r11)
            goto L_0x04e9
        L_0x0474:
            r11 = 3
            eb r8 = r7.f7906e
            ec r9 = r7.f7903b
            eb r9 = r9.f7906e
            int r10 = r7.f7904c
            r15.mo4560a(r8, r9, r10, r11)
            eb r3 = r3.f7906e
            eb r7 = r7.f7906e
            int r8 = r0.f7993g
            r15.mo4562b(r3, r7, r8, r11)
            goto L_0x04e9
        L_0x048b:
            r11 = 3
            goto L_0x04e9
        L_0x048d:
            r11 = 3
            r7 = 5
            if (r5 == 0) goto L_0x04be
            if (r17 == 0) goto L_0x04be
            if (r3 != 0) goto L_0x0496
            goto L_0x04be
        L_0x0496:
            ec r3 = r0.f7998l
            ec r8 = r3.f7903b
            if (r8 != 0) goto L_0x04aa
            eb r3 = r3.f7906e
            int r7 = r0.mo4710h()
            int r8 = r0.f8009w
            int r7 = r7 + r8
            r15.mo4558a((p000.C0114eb) r3, (int) r7)
            goto L_0x04e9
        L_0x04aa:
            int r3 = r3.mo4667a()
            ec r8 = r0.f7998l
            eb r8 = r8.f7906e
            ec r9 = r2.f7998l
            ec r9 = r9.f7903b
            eb r9 = r9.f7906e
            int r3 = -r3
            r15.mo4564c(r8, r9, r3, r7)
            goto L_0x04e9
        L_0x04be:
            if (r5 == 0) goto L_0x04f0
            if (r17 != 0) goto L_0x04f0
            if (r3 == 0) goto L_0x04c5
            goto L_0x04f0
        L_0x04c5:
            ec r3 = r0.f7996j
            ec r8 = r3.f7903b
            if (r8 != 0) goto L_0x04d6
            eb r3 = r3.f7906e
            int r7 = r0.mo4710h()
            r15.mo4558a((p000.C0114eb) r3, (int) r7)
            goto L_0x04e9
        L_0x04d6:
            int r3 = r3.mo4667a()
            ec r8 = r0.f7996j
            eb r8 = r8.f7906e
            ec r9 = r12.f7996j
            ec r9 = r9.f7903b
            eb r9 = r9.f7906e
            r15.mo4564c(r8, r9, r3, r7)
        L_0x04e9:
            r18 = r0
            r3 = r12
            r0 = r13
            r6 = 0
            goto L_0x056c
        L_0x04f0:
            ec r7 = r0.f7996j
            ec r8 = r0.f7998l
            int r10 = r7.mo4667a()
            int r14 = r8.mo4667a()
            eb r9 = r7.f7906e
            ec r11 = r7.f7903b
            eb r11 = r11.f7906e
            r18 = r0
            r0 = 1
            r15.mo4560a(r9, r11, r10, r0)
            eb r9 = r8.f7906e
            ec r11 = r8.f7903b
            eb r11 = r11.f7906e
            int r6 = -r14
            r15.mo4562b(r9, r11, r6, r0)
            ec r0 = r7.f7903b
            if (r0 == 0) goto L_0x0519
            eb r0 = r0.f7906e
            goto L_0x051c
        L_0x0519:
            r0 = r16
        L_0x051c:
            if (r3 != 0) goto L_0x052b
            ec r0 = r12.f7996j
            ec r0 = r0.f7903b
            if (r0 == 0) goto L_0x0527
            eb r0 = r0.f7906e
            goto L_0x052c
        L_0x0527:
            r9 = r16
            goto L_0x052d
        L_0x052b:
        L_0x052c:
            r9 = r0
        L_0x052d:
            if (r4 != 0) goto L_0x053c
            ec r0 = r2.f7998l
            ec r0 = r0.f7903b
            if (r0 == 0) goto L_0x0538
            ed r4 = r0.f7902a
            goto L_0x053d
        L_0x0538:
            r4 = r16
            goto L_0x053d
        L_0x053c:
        L_0x053d:
            if (r4 == 0) goto L_0x0569
            ec r0 = r4.f7996j
            eb r0 = r0.f7906e
            if (r17 == 0) goto L_0x0551
            ec r0 = r2.f7998l
            ec r0 = r0.f7903b
            if (r0 == 0) goto L_0x054e
            eb r0 = r0.f7906e
            goto L_0x0551
        L_0x054e:
            r0 = r16
        L_0x0551:
            if (r9 == 0) goto L_0x0569
            if (r0 == 0) goto L_0x0569
            eb r3 = r7.f7906e
            r11 = 1056964608(0x3f000000, float:0.5)
            eb r6 = r8.f7906e
            r7 = r31
            r8 = r3
            r3 = 3
            r3 = r12
            r12 = r0
            r0 = r13
            r13 = r6
            r6 = 0
            r7.mo4559a(r8, r9, r10, r11, r12, r13, r14)
            goto L_0x056c
        L_0x0569:
            r3 = r12
            r0 = r13
            r6 = 0
        L_0x056c:
            if (r17 != 0) goto L_0x056f
            goto L_0x0571
        L_0x056f:
            r4 = r16
        L_0x0571:
            r6 = r30
            r13 = r0
            r12 = r3
            r0 = r4
            r14 = r17
            r3 = r18
            goto L_0x0401
        L_0x057d:
            r3 = r12
            r0 = r13
            r6 = 0
            r4 = 2
            if (r5 != r4) goto L_0x05c3
            ec r1 = r1.f7996j
            ec r4 = r2.f7998l
            int r10 = r1.mo4667a()
            int r14 = r4.mo4667a()
            ec r5 = r3.f7996j
            ec r5 = r5.f7903b
            if (r5 == 0) goto L_0x0599
            eb r5 = r5.f7906e
            r9 = r5
            goto L_0x059c
        L_0x0599:
            r9 = r16
        L_0x059c:
            ec r2 = r2.f7998l
            ec r2 = r2.f7903b
            if (r2 == 0) goto L_0x05a6
            eb r2 = r2.f7906e
            r12 = r2
            goto L_0x05a9
        L_0x05a6:
            r12 = r16
        L_0x05a9:
            if (r9 != 0) goto L_0x05ac
        L_0x05ab:
            goto L_0x05c3
        L_0x05ac:
            if (r12 == 0) goto L_0x05ab
            eb r2 = r4.f7906e
            int r5 = -r14
            r7 = 1
            r15.mo4562b(r2, r12, r5, r7)
            eb r8 = r1.f7906e
            float r11 = r3.f7959F
            eb r13 = r4.f7906e
            r7 = r31
            r7.mo4559a(r8, r9, r10, r11, r12, r13, r14)
            goto L_0x05c3
        L_0x05c1:
            r0 = r13
            r6 = 0
        L_0x05c3:
            int r13 = r0 + 1
            r14 = 0
            r6 = r30
            goto L_0x0006
        L_0x05ca:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0117ee.m7318c(dy):void");
    }

    /* renamed from: a */
    private final int m7314a(C0110dy dyVar, C0116ed[] edVarArr, C0116ed edVar, int i, boolean[] zArr) {
        int i2;
        int i3;
        char c;
        char c2;
        C0110dy dyVar2 = dyVar;
        C0116ed edVar2 = edVar;
        zArr[0] = true;
        zArr[1] = false;
        C0116ed edVar3 = null;
        edVarArr[0] = null;
        edVarArr[2] = null;
        edVarArr[1] = null;
        edVarArr[3] = null;
        float f = 0.0f;
        int i4 = 5;
        if (i == 0) {
            C0115ec ecVar = edVar2.f7995i.f7903b;
            boolean z = ecVar == null || ecVar.f7902a == this;
            edVar2.f7978Y = null;
            C0116ed edVar4 = edVar2.f7961H == 8 ? null : edVar2;
            C0116ed edVar5 = edVar2;
            C0116ed edVar6 = null;
            C0116ed edVar7 = edVar4;
            i2 = 0;
            while (edVar5.f7997k.f7903b != null) {
                edVar5.f7978Y = edVar3;
                if (edVar5.f7961H == 8) {
                    C0115ec ecVar2 = edVar5.f7995i;
                    dyVar2.mo4564c(ecVar2.f7906e, ecVar2.f7903b.f7906e, 0, 5);
                    dyVar2.mo4564c(edVar5.f7997k.f7906e, edVar5.f7995i.f7906e, 0, 5);
                } else {
                    if (edVar4 == null) {
                        edVar4 = edVar5;
                    }
                    if (!(edVar7 == null || edVar7 == edVar5)) {
                        edVar7.f7978Y = edVar5;
                    }
                    edVar7 = edVar5;
                }
                if (edVar5.f7961H != 8 && edVar5.f7981aa == 3) {
                    if (edVar5.f7982ab == 3) {
                        zArr[0] = false;
                    }
                    if (edVar5.f8004r <= f) {
                        zArr[0] = false;
                        int i5 = i2 + 1;
                        C0116ed[] edVarArr2 = this.f8087am;
                        int length = edVarArr2.length;
                        if (i5 >= length) {
                            this.f8087am = (C0116ed[]) Arrays.copyOf(edVarArr2, length + length);
                        }
                        this.f8087am[i2] = edVar5;
                        i2 = i5;
                    }
                }
                C0116ed edVar8 = edVar5.f7997k.f7903b.f7902a;
                C0115ec ecVar3 = edVar8.f7995i.f7903b;
                if (ecVar3 == null || ecVar3.f7902a != edVar5 || edVar8 == edVar5) {
                    break;
                }
                edVar6 = edVar8;
                edVar5 = edVar6;
                edVar3 = null;
                f = 0.0f;
            }
            C0115ec ecVar4 = edVar5.f7997k.f7903b;
            if (!(ecVar4 == null || ecVar4.f7902a == this)) {
                z = false;
            }
            if (edVar2.f7995i.f7903b == null || edVar6.f7997k.f7903b == null) {
                c2 = 1;
                zArr[1] = true;
            } else {
                c2 = 1;
            }
            edVar2.f7974U = z;
            edVar6.f7978Y = null;
            edVarArr[0] = edVar2;
            edVarArr[2] = edVar4;
            edVarArr[c2] = edVar6;
            edVarArr[3] = edVar7;
        } else {
            C0115ec ecVar5 = edVar2.f7996j.f7903b;
            boolean z2 = ecVar5 == null || ecVar5.f7902a == this;
            edVar2.f7979Z = null;
            C0116ed edVar9 = edVar2;
            C0116ed edVar10 = edVar2.f7961H == 8 ? null : edVar2;
            C0116ed edVar11 = edVar10;
            C0116ed edVar12 = null;
            int i6 = 0;
            while (true) {
                if (edVar9.f7998l.f7903b == null) {
                    i3 = i6;
                    break;
                }
                edVar9.f7979Z = null;
                if (edVar9.f7961H == 8) {
                    C0115ec ecVar6 = edVar9.f7996j;
                    dyVar2.mo4564c(ecVar6.f7906e, ecVar6.f7903b.f7906e, 0, i4);
                    dyVar2.mo4564c(edVar9.f7998l.f7906e, edVar9.f7996j.f7906e, 0, i4);
                } else {
                    if (edVar10 == null) {
                        edVar10 = edVar9;
                    }
                    if (!(edVar11 == null || edVar11 == edVar9)) {
                        edVar11.f7979Z = edVar9;
                    }
                    edVar11 = edVar9;
                }
                if (edVar9.f7961H != 8) {
                    if (edVar9.f7982ab == 3) {
                        if (edVar9.f7981aa == 3) {
                            zArr[0] = false;
                        }
                        if (edVar9.f8004r <= 0.0f) {
                            zArr[0] = false;
                            int i7 = i6 + 1;
                            C0116ed[] edVarArr3 = this.f8087am;
                            int length2 = edVarArr3.length;
                            if (i7 >= length2) {
                                this.f8087am = (C0116ed[]) Arrays.copyOf(edVarArr3, length2 + length2);
                            }
                            this.f8087am[i6] = edVar9;
                            i6 = i7;
                        }
                    }
                }
                C0116ed edVar13 = edVar9.f7998l.f7903b.f7902a;
                C0115ec ecVar7 = edVar13.f7996j.f7903b;
                if (ecVar7 == null || ecVar7.f7902a != edVar9 || edVar13 == edVar9) {
                    i3 = i6;
                } else {
                    edVar12 = edVar13;
                    edVar9 = edVar12;
                    i4 = 5;
                }
            }
            i3 = i6;
            C0115ec ecVar8 = edVar9.f7998l.f7903b;
            if (!(ecVar8 == null || ecVar8.f7902a == this)) {
                z2 = false;
            }
            if (edVar2.f7996j.f7903b == null || edVar12.f7998l.f7903b == null) {
                c = 1;
                zArr[1] = true;
            } else {
                c = 1;
            }
            edVar2.f7975V = z2;
            edVar12.f7979Z = null;
            edVarArr[0] = edVar2;
            edVarArr[2] = edVar10;
            edVarArr[c] = edVar12;
            edVarArr[3] = edVar11;
        }
        return i2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m7315a(p000.C0116ed r11, boolean[] r12) {
        /*
            r10 = this;
            int r0 = r11.f7981aa
            r1 = 0
            r2 = 3
            r3 = 0
            if (r0 != r2) goto L_0x0016
            int r0 = r11.f7982ab
            if (r0 != r2) goto L_0x0016
            float r0 = r11.f8004r
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 > 0) goto L_0x0012
            goto L_0x0016
        L_0x0012:
            r12[r3] = r3
            return
        L_0x0016:
            int r0 = r11.mo4702d()
            int r4 = r11.f7981aa
            if (r4 != r2) goto L_0x002d
            int r4 = r11.f7982ab
            if (r4 == r2) goto L_0x002d
            float r4 = r11.f8004r
            int r1 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r1 > 0) goto L_0x0029
            goto L_0x002d
        L_0x0029:
            r12[r3] = r3
            return
        L_0x002d:
            r1 = 1
            r11.f7970Q = r1
            boolean r4 = r11 instanceof p000.C0118ef
            if (r4 != 0) goto L_0x014c
            ec r4 = r11.f7997k
            boolean r4 = r4.mo4670c()
            if (r4 != 0) goto L_0x004e
            ec r4 = r11.f7995i
            boolean r4 = r4.mo4670c()
            if (r4 == 0) goto L_0x0046
            goto L_0x004e
        L_0x0046:
            int r12 = r11.f8006t
            int r3 = r0 + r12
            r4 = r0
            goto L_0x0164
        L_0x004e:
            ec r4 = r11.f7997k
            ec r5 = r4.f7903b
            if (r5 == 0) goto L_0x006b
            ec r6 = r11.f7995i
            ec r6 = r6.f7903b
            if (r6 == 0) goto L_0x006b
            if (r5 == r6) goto L_0x0067
            ed r7 = r5.f7902a
            ed r6 = r6.f7902a
            if (r7 != r6) goto L_0x006b
            ed r6 = r11.f8001o
            if (r7 != r6) goto L_0x0067
            goto L_0x006b
        L_0x0067:
            r12[r3] = r3
            return
        L_0x006b:
            r6 = 0
            if (r5 == 0) goto L_0x0084
            ed r5 = r5.f7902a
            int r4 = r4.mo4667a()
            int r4 = r4 + r0
            boolean r7 = r5.mo4698b()
            if (r7 == 0) goto L_0x007c
            goto L_0x0086
        L_0x007c:
            boolean r7 = r5.f7970Q
            if (r7 != 0) goto L_0x0086
            r10.m7315a((p000.C0116ed) r5, (boolean[]) r12)
            goto L_0x0086
        L_0x0084:
            r4 = r0
            r5 = r6
        L_0x0086:
            ec r7 = r11.f7995i
            ec r8 = r7.f7903b
            if (r8 == 0) goto L_0x00a4
            ed r6 = r8.f7902a
            int r7 = r7.mo4667a()
            int r0 = r0 + r7
            boolean r7 = r6.mo4698b()
            if (r7 == 0) goto L_0x009a
        L_0x0099:
            goto L_0x00a5
        L_0x009a:
            boolean r7 = r6.f7970Q
            if (r7 == 0) goto L_0x009f
            goto L_0x0099
        L_0x009f:
            r10.m7315a((p000.C0116ed) r6, (boolean[]) r12)
            goto L_0x00a6
        L_0x00a4:
        L_0x00a5:
        L_0x00a6:
            ec r12 = r11.f7997k
            ec r12 = r12.f7903b
            r7 = 2
            r8 = 4
            if (r12 == 0) goto L_0x00f8
            boolean r12 = r5.mo4698b()
            if (r12 != 0) goto L_0x00f8
            ec r12 = r11.f7997k
            ec r12 = r12.f7903b
            int r12 = r12.f7907f
            if (r12 != r8) goto L_0x00c5
            int r12 = r5.f7964K
            int r9 = r5.mo4702d()
            int r12 = r12 - r9
            int r4 = r4 + r12
            goto L_0x00cb
        L_0x00c5:
            if (r12 != r7) goto L_0x00cb
            int r12 = r5.f7964K
            int r4 = r4 + r12
        L_0x00cb:
            boolean r12 = r5.f7967N
            if (r12 != 0) goto L_0x00e4
            ec r12 = r5.f7995i
            ec r12 = r12.f7903b
            if (r12 == 0) goto L_0x00e2
            ec r12 = r5.f7997k
            ec r12 = r12.f7903b
            if (r12 == 0) goto L_0x00e2
            int r12 = r5.f7981aa
            if (r12 != r2) goto L_0x00e0
            goto L_0x00e2
        L_0x00e0:
            goto L_0x00e4
        L_0x00e2:
            r12 = 0
            goto L_0x00e5
        L_0x00e4:
            r12 = 1
        L_0x00e5:
            r11.f7967N = r12
            if (r12 == 0) goto L_0x00f8
            ec r12 = r5.f7995i
            ec r12 = r12.f7903b
            if (r12 == 0) goto L_0x00f3
            ed r12 = r12.f7902a
            if (r12 == r11) goto L_0x00f8
        L_0x00f3:
            int r12 = r5.f7964K
            int r12 = r4 - r12
            int r4 = r4 + r12
        L_0x00f8:
            ec r12 = r11.f7995i
            ec r12 = r12.f7903b
            if (r12 == 0) goto L_0x0149
            boolean r12 = r6.mo4698b()
            if (r12 != 0) goto L_0x0149
            ec r12 = r11.f7995i
            ec r12 = r12.f7903b
            int r12 = r12.f7907f
            if (r12 != r7) goto L_0x0115
            int r12 = r6.f7963J
            int r5 = r6.mo4702d()
            int r12 = r12 - r5
            int r0 = r0 + r12
            goto L_0x011b
        L_0x0115:
            if (r12 != r8) goto L_0x011b
            int r12 = r6.f7963J
            int r0 = r0 + r12
        L_0x011b:
            boolean r12 = r6.f7966M
            if (r12 != 0) goto L_0x0131
            ec r12 = r6.f7995i
            ec r12 = r12.f7903b
            if (r12 == 0) goto L_0x0132
            ec r12 = r6.f7997k
            ec r12 = r12.f7903b
            if (r12 == 0) goto L_0x0132
            int r12 = r6.f7981aa
            if (r12 != r2) goto L_0x0130
            goto L_0x0132
        L_0x0130:
        L_0x0131:
            r3 = 1
        L_0x0132:
            r11.f7966M = r3
            if (r3 == 0) goto L_0x0149
            ec r12 = r6.f7997k
            ec r12 = r12.f7903b
            if (r12 != 0) goto L_0x013d
            goto L_0x0142
        L_0x013d:
            ed r12 = r12.f7902a
            if (r12 != r11) goto L_0x0142
            goto L_0x0149
        L_0x0142:
            int r12 = r6.f7963J
            int r12 = r0 - r12
            int r3 = r0 + r12
            goto L_0x0164
        L_0x0149:
            r3 = r0
            goto L_0x0164
        L_0x014c:
            r12 = r11
            ef r12 = (p000.C0118ef) r12
            int r2 = r12.f8136af
            if (r2 != r1) goto L_0x0162
            int r0 = r12.f8134ad
            r1 = -1
            if (r0 != r1) goto L_0x015f
            int r12 = r12.f8135ae
            if (r12 != r1) goto L_0x015d
            goto L_0x0160
        L_0x015d:
            r4 = r12
            goto L_0x0164
        L_0x015f:
            r3 = r0
        L_0x0160:
            r4 = 0
            goto L_0x0164
        L_0x0162:
            r3 = r0
            r4 = r3
        L_0x0164:
            int r12 = r11.f7961H
            r0 = 8
            if (r12 != r0) goto L_0x0170
            int r12 = r11.f8002p
            int r3 = r3 - r12
            int r4 = r4 - r12
            goto L_0x0171
        L_0x0170:
        L_0x0171:
            r11.f7963J = r3
            r11.f7964K = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0117ee.m7315a(ed, boolean[]):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v21, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0104, code lost:
        r12 = r7.f7996j.f7903b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x010e, code lost:
        r12 = r7.f7998l.f7903b;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void m7317b(p000.C0116ed r11, boolean[] r12) {
        /*
            r10 = this;
            int r0 = r11.f7982ab
            r1 = 3
            r2 = 0
            if (r0 != r1) goto L_0x0016
            int r0 = r11.f7981aa
            if (r0 == r1) goto L_0x0016
            float r0 = r11.f8004r
            r3 = 0
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 > 0) goto L_0x0012
            goto L_0x0016
        L_0x0012:
            r12[r2] = r2
            return
        L_0x0016:
            int r0 = r11.mo4704e()
            r3 = 1
            r11.f7971R = r3
            boolean r4 = r11 instanceof p000.C0118ef
            r5 = 8
            if (r4 != 0) goto L_0x0192
            ec r4 = r11.f7999m
            ec r6 = r4.f7903b
            if (r6 != 0) goto L_0x003d
            ec r6 = r11.f7996j
            ec r6 = r6.f7903b
            if (r6 != 0) goto L_0x003d
            ec r6 = r11.f7998l
            ec r6 = r6.f7903b
            if (r6 != 0) goto L_0x003d
            int r12 = r11.f8007u
            int r2 = r0 + r12
            r4 = r2
            r2 = r0
            goto L_0x01aa
        L_0x003d:
            ec r6 = r11.f7998l
            ec r6 = r6.f7903b
            if (r6 == 0) goto L_0x005a
            ec r7 = r11.f7996j
            ec r7 = r7.f7903b
            if (r7 == 0) goto L_0x005a
            if (r6 == r7) goto L_0x0056
            ed r6 = r6.f7902a
            ed r7 = r7.f7902a
            if (r6 != r7) goto L_0x005a
            ed r7 = r11.f8001o
            if (r6 != r7) goto L_0x0056
            goto L_0x005a
        L_0x0056:
            r12[r2] = r2
            return
        L_0x005a:
            boolean r4 = r4.mo4670c()
            if (r4 == 0) goto L_0x008f
            ec r1 = r11.f7999m
            ec r1 = r1.f7903b
            ed r1 = r1.f7902a
            boolean r2 = r1.f7971R
            if (r2 == 0) goto L_0x006b
            goto L_0x006e
        L_0x006b:
            r10.m7317b(r1, r12)
        L_0x006e:
            int r12 = r1.f7962I
            int r2 = r1.f8003q
            int r12 = r12 - r2
            int r12 = r12 + r0
            int r12 = java.lang.Math.max(r12, r0)
            int r2 = r1.f7965L
            int r1 = r1.f8003q
            int r2 = r2 - r1
            int r2 = r2 + r0
            int r0 = java.lang.Math.max(r2, r0)
            int r1 = r11.f7961H
            if (r1 != r5) goto L_0x008a
            int r1 = r11.f8003q
            int r12 = r12 - r1
            int r0 = r0 - r1
        L_0x008a:
            r11.f7962I = r12
            r11.f7965L = r0
            return
        L_0x008f:
            ec r4 = r11.f7996j
            boolean r4 = r4.mo4670c()
            r6 = 0
            if (r4 == 0) goto L_0x00b2
            ec r4 = r11.f7996j
            ec r7 = r4.f7903b
            ed r7 = r7.f7902a
            int r4 = r4.mo4667a()
            int r4 = r4 + r0
            boolean r8 = r7.mo4698b()
            if (r8 == 0) goto L_0x00aa
            goto L_0x00b4
        L_0x00aa:
            boolean r8 = r7.f7971R
            if (r8 != 0) goto L_0x00b4
            r10.m7317b(r7, r12)
            goto L_0x00b4
        L_0x00b2:
            r4 = r0
            r7 = r6
        L_0x00b4:
            ec r8 = r11.f7998l
            boolean r8 = r8.mo4670c()
            if (r8 == 0) goto L_0x00da
            ec r6 = r11.f7998l
            ec r8 = r6.f7903b
            ed r8 = r8.f7902a
            int r6 = r6.mo4667a()
            int r0 = r0 + r6
            boolean r6 = r8.mo4698b()
            if (r6 == 0) goto L_0x00ce
        L_0x00cd:
            goto L_0x00d8
        L_0x00ce:
            boolean r6 = r8.f7971R
            if (r6 == 0) goto L_0x00d3
            goto L_0x00cd
        L_0x00d3:
            r10.m7317b(r8, r12)
        L_0x00d8:
            r6 = r8
            goto L_0x00dc
        L_0x00da:
        L_0x00dc:
            ec r12 = r11.f7996j
            ec r12 = r12.f7903b
            r8 = 5
            if (r12 == 0) goto L_0x0135
            boolean r12 = r7.mo4698b()
            if (r12 != 0) goto L_0x0135
            ec r12 = r11.f7996j
            ec r12 = r12.f7903b
            int r12 = r12.f7907f
            if (r12 != r1) goto L_0x00fa
            int r12 = r7.f7962I
            int r9 = r7.mo4704e()
            int r12 = r12 - r9
            int r4 = r4 + r12
            goto L_0x0100
        L_0x00fa:
            if (r12 != r8) goto L_0x0100
            int r12 = r7.f7962I
            int r4 = r4 + r12
        L_0x0100:
            boolean r12 = r7.f7968O
            if (r12 != 0) goto L_0x0121
            ec r12 = r7.f7996j
            ec r12 = r12.f7903b
            if (r12 == 0) goto L_0x011f
            ed r12 = r12.f7902a
            if (r12 == r11) goto L_0x011f
            ec r12 = r7.f7998l
            ec r12 = r12.f7903b
            if (r12 == 0) goto L_0x011f
            ed r12 = r12.f7902a
            if (r12 == r11) goto L_0x011f
            int r12 = r7.f7982ab
            if (r12 != r1) goto L_0x011d
            goto L_0x011f
        L_0x011d:
            goto L_0x0121
        L_0x011f:
            r12 = 0
            goto L_0x0122
        L_0x0121:
            r12 = 1
        L_0x0122:
            r11.f7968O = r12
            if (r12 == 0) goto L_0x0135
            ec r12 = r7.f7998l
            ec r12 = r12.f7903b
            if (r12 == 0) goto L_0x0130
            ed r12 = r12.f7902a
            if (r12 == r11) goto L_0x0135
        L_0x0130:
            int r12 = r7.f7962I
            int r12 = r4 - r12
            int r4 = r4 + r12
        L_0x0135:
            ec r12 = r11.f7998l
            ec r12 = r12.f7903b
            if (r12 == 0) goto L_0x018f
            boolean r12 = r6.mo4698b()
            if (r12 != 0) goto L_0x018f
            ec r12 = r11.f7998l
            ec r12 = r12.f7903b
            int r12 = r12.f7907f
            if (r12 != r8) goto L_0x0152
            int r12 = r6.f7965L
            int r7 = r6.mo4704e()
            int r12 = r12 - r7
            int r0 = r0 + r12
            goto L_0x0158
        L_0x0152:
            if (r12 != r1) goto L_0x0158
            int r12 = r6.f7965L
            int r0 = r0 + r12
        L_0x0158:
            boolean r12 = r6.f7969P
            if (r12 != 0) goto L_0x0176
            ec r12 = r6.f7996j
            ec r12 = r12.f7903b
            if (r12 == 0) goto L_0x0177
            ed r12 = r12.f7902a
            if (r12 == r11) goto L_0x0177
            ec r12 = r6.f7998l
            ec r12 = r12.f7903b
            if (r12 == 0) goto L_0x0177
            ed r12 = r12.f7902a
            if (r12 == r11) goto L_0x0177
            int r12 = r6.f7982ab
            if (r12 != r1) goto L_0x0175
            goto L_0x0177
        L_0x0175:
        L_0x0176:
            r2 = 1
        L_0x0177:
            r11.f7969P = r2
            if (r2 == 0) goto L_0x018f
            ec r12 = r6.f7996j
            ec r12 = r12.f7903b
            if (r12 != 0) goto L_0x0182
            goto L_0x0187
        L_0x0182:
            ed r12 = r12.f7902a
            if (r12 != r11) goto L_0x0187
            goto L_0x018f
        L_0x0187:
            int r12 = r6.f7965L
            int r12 = r0 - r12
            int r2 = r0 + r12
            goto L_0x01aa
        L_0x018f:
            r2 = r0
            goto L_0x01aa
        L_0x0192:
            r12 = r11
            ef r12 = (p000.C0118ef) r12
            int r1 = r12.f8136af
            if (r1 != 0) goto L_0x01a8
            int r0 = r12.f8134ad
            r1 = -1
            if (r0 != r1) goto L_0x01a6
            int r12 = r12.f8135ae
            if (r12 != r1) goto L_0x01a3
            goto L_0x01a4
        L_0x01a3:
            r2 = r12
        L_0x01a4:
            r4 = 0
            goto L_0x01aa
        L_0x01a6:
            r4 = r0
            goto L_0x01aa
        L_0x01a8:
            r2 = r0
            r4 = r2
        L_0x01aa:
            int r12 = r11.f7961H
            if (r12 != r5) goto L_0x01b4
            int r12 = r11.f8003q
            int r4 = r4 - r12
            int r2 = r2 - r12
            goto L_0x01b5
        L_0x01b4:
        L_0x01b5:
            r11.f7962I = r4
            r11.f7965L = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0117ee.m7317b(ed, boolean[]):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:124:0x02cd A[Catch:{ Exception -> 0x02d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x02fe A[Catch:{ Exception -> 0x02d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x03ae A[Catch:{ Exception -> 0x03e4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x0413  */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x0454  */
    /* JADX WARNING: Removed duplicated region for block: B:240:0x0498  */
    /* JADX WARNING: Removed duplicated region for block: B:254:0x04f5  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x0508  */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x0523  */
    /* JADX WARNING: Removed duplicated region for block: B:262:0x0530  */
    /* JADX WARNING: Removed duplicated region for block: B:263:0x0537  */
    /* renamed from: n */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo4742n() {
        /*
            r31 = this;
            r1 = r31
            int r2 = r1.f8006t
            int r3 = r1.f8007u
            int r0 = r31.mo4699c()
            r4 = 0
            int r5 = java.lang.Math.max(r4, r0)
            int r0 = r31.mo4706f()
            int r6 = java.lang.Math.max(r4, r0)
            r1.f8079ad = r4
            r1.f8080ae = r4
            ed r0 = r1.f8001o
            r7 = 0
            r8 = 2
            if (r0 != 0) goto L_0x0027
            r1.f8006t = r4
            r1.f8007u = r4
            goto L_0x00ac
        L_0x0027:
            eh r0 = r1.f8082ah
            if (r0 == 0) goto L_0x002c
            goto L_0x0033
        L_0x002c:
            eh r0 = new eh
            r0.<init>(r1)
            r1.f8082ah = r0
        L_0x0033:
            eh r0 = r1.f8082ah
            int r9 = r1.f8006t
            r0.f8254a = r9
            int r9 = r1.f8007u
            r0.f8255b = r9
            int r9 = r31.mo4699c()
            r0.f8256c = r9
            int r9 = r31.mo4706f()
            r0.f8257d = r9
            java.util.ArrayList r9 = r0.f8258e
            int r9 = r9.size()
            r10 = 0
        L_0x0050:
            if (r10 >= r9) goto L_0x0089
            java.util.ArrayList r11 = r0.f8258e
            java.lang.Object r11 = r11.get(r10)
            eg r11 = (p000.C0119eg) r11
            ec r12 = r11.f8182a
            int r12 = r12.f7907f
            ec r12 = r1.mo4705e(r12)
            r11.f8182a = r12
            ec r12 = r11.f8182a
            if (r12 == 0) goto L_0x007d
            ec r13 = r12.f7903b
            r11.f8183b = r13
            int r12 = r12.mo4667a()
            r11.f8184c = r12
            ec r12 = r11.f8182a
            int r13 = r12.f7908g
            r11.f8186e = r13
            int r12 = r12.f7905d
            r11.f8185d = r12
            goto L_0x0086
        L_0x007d:
            r11.f8183b = r7
            r11.f8184c = r4
            r11.f8186e = r8
            r11.f8185d = r4
        L_0x0086:
            int r10 = r10 + 1
            goto L_0x0050
        L_0x0089:
            r1.f8006t = r4
            r1.f8007u = r4
            java.util.ArrayList r0 = r1.f8000n
            int r0 = r0.size()
            r9 = 0
        L_0x0095:
            if (r9 >= r0) goto L_0x00a5
            java.util.ArrayList r10 = r1.f8000n
            java.lang.Object r10 = r10.get(r9)
            ec r10 = (p000.C0115ec) r10
            r10.mo4669b()
            int r9 = r9 + 1
            goto L_0x0095
        L_0x00a5:
            dy r0 = r1.f8081ag
            dw r0 = r0.f7626f
            r1.mo4694a((p000.C0108dw) r0)
        L_0x00ac:
            int r9 = r1.f7982ab
            int r10 = r1.f7981aa
            int r0 = r1.f8078ac
            r12 = 1
            if (r0 == r8) goto L_0x00bb
        L_0x00b5:
            r23 = r2
            r24 = r3
            goto L_0x023b
        L_0x00bb:
            if (r9 != r8) goto L_0x00be
            goto L_0x00c2
        L_0x00be:
            if (r10 == r8) goto L_0x00c2
            goto L_0x00b5
        L_0x00c2:
            java.util.ArrayList r0 = r1.f8330af
            boolean[] r13 = r1.f8090ap
            int r14 = r0.size()
            r13[r4] = r12
            r7 = 0
            r8 = 0
            r12 = 0
            r15 = 0
            r18 = 0
            r19 = 0
            r20 = 0
        L_0x00d6:
            if (r15 >= r14) goto L_0x019f
            java.lang.Object r21 = r0.get(r15)
            r11 = r21
            ed r11 = (p000.C0116ed) r11
            boolean r21 = r11.mo4698b()
            if (r21 == 0) goto L_0x00ee
            r23 = r2
            r24 = r3
            r22 = r13
            goto L_0x018d
        L_0x00ee:
            boolean r4 = r11.f7970Q
            if (r4 != 0) goto L_0x00f5
            r1.m7315a((p000.C0116ed) r11, (boolean[]) r13)
        L_0x00f5:
            boolean r4 = r11.f7971R
            if (r4 != 0) goto L_0x00fc
            r1.m7317b(r11, r13)
        L_0x00fc:
            r4 = 0
            boolean r22 = r13[r4]
            if (r22 == 0) goto L_0x0199
            int r4 = r11.f7963J
            r22 = r13
            int r13 = r11.f7964K
            int r4 = r4 + r13
            int r13 = r11.mo4699c()
            int r4 = r4 - r13
            int r13 = r11.f7962I
            r23 = r4
            int r4 = r11.f7965L
            int r13 = r13 + r4
            int r4 = r11.mo4706f()
            int r13 = r13 - r4
            int r4 = r11.f7981aa
            r24 = r13
            r13 = 4
            if (r4 != r13) goto L_0x0130
            int r4 = r11.mo4699c()
            ec r13 = r11.f7995i
            int r13 = r13.f7904c
            int r4 = r4 + r13
            ec r13 = r11.f7997k
            int r13 = r13.f7904c
            int r4 = r4 + r13
            goto L_0x0132
        L_0x0130:
            r4 = r23
        L_0x0132:
            int r13 = r11.f7982ab
            r23 = r4
            r4 = 4
            if (r13 != r4) goto L_0x0148
            int r4 = r11.mo4706f()
            ec r13 = r11.f7996j
            int r13 = r13.f7904c
            int r4 = r4 + r13
            ec r13 = r11.f7998l
            int r13 = r13.f7904c
            int r4 = r4 + r13
            goto L_0x014a
        L_0x0148:
            r4 = r24
        L_0x014a:
            int r13 = r11.f7961H
            r24 = r4
            r4 = 8
            if (r13 == r4) goto L_0x0159
            r30 = r24
            r24 = r3
            r3 = r30
            goto L_0x015c
        L_0x0159:
            r24 = r3
            r3 = 0
        L_0x015c:
            if (r13 == r4) goto L_0x0161
            r4 = r23
            goto L_0x0163
        L_0x0161:
            r4 = 0
        L_0x0163:
            int r13 = r11.f7963J
            int r7 = java.lang.Math.max(r7, r13)
            int r13 = r11.f7964K
            int r12 = java.lang.Math.max(r12, r13)
            int r13 = r11.f7965L
            r23 = r2
            r2 = r19
            int r19 = java.lang.Math.max(r2, r13)
            int r2 = r11.f7962I
            r11 = r18
            int r18 = java.lang.Math.max(r11, r2)
            int r8 = java.lang.Math.max(r8, r4)
            r4 = r20
            int r20 = java.lang.Math.max(r4, r3)
        L_0x018d:
            int r15 = r15 + 1
            r13 = r22
            r2 = r23
            r3 = r24
            r4 = 0
            goto L_0x00d6
        L_0x0199:
            r23 = r2
            r24 = r3
            r4 = 0
            goto L_0x01e3
        L_0x019f:
            r23 = r2
            r24 = r3
            r11 = r18
            r2 = r19
            r4 = r20
            int r3 = java.lang.Math.max(r7, r12)
            int r7 = r1.f7954A
            int r3 = java.lang.Math.max(r3, r8)
            int r3 = java.lang.Math.max(r7, r3)
            r1.f8083ai = r3
            int r2 = java.lang.Math.max(r11, r2)
            int r3 = r1.f7955B
            int r2 = java.lang.Math.max(r2, r4)
            int r2 = java.lang.Math.max(r3, r2)
            r1.f8084aj = r2
            r2 = 0
        L_0x01ca:
            if (r2 >= r14) goto L_0x01e2
            java.lang.Object r3 = r0.get(r2)
            ed r3 = (p000.C0116ed) r3
            r4 = 0
            r3.f7970Q = r4
            r3.f7971R = r4
            r3.f7966M = r4
            r3.f7967N = r4
            r3.f7968O = r4
            r3.f7969P = r4
            int r2 = r2 + 1
            goto L_0x01ca
        L_0x01e2:
            r4 = 0
        L_0x01e3:
            boolean[] r0 = r1.f8090ap
            boolean r0 = r0[r4]
            if (r5 > 0) goto L_0x01ea
            goto L_0x01f8
        L_0x01ea:
            if (r6 <= 0) goto L_0x01f8
            int r2 = r1.f8083ai
            if (r2 > r5) goto L_0x01f7
            int r2 = r1.f8084aj
            if (r2 <= r6) goto L_0x01f8
            r0 = 0
            goto L_0x01f8
        L_0x01f7:
            r0 = 0
        L_0x01f8:
            if (r0 == 0) goto L_0x023a
            int r2 = r1.f7981aa
            r3 = 2
            if (r2 != r3) goto L_0x021a
            r2 = 1
            r1.f7981aa = r2
            if (r5 > 0) goto L_0x0205
            goto L_0x020f
        L_0x0205:
            int r3 = r1.f8083ai
            if (r5 >= r3) goto L_0x020f
            r1.f8079ad = r2
            r1.mo4691a((int) r5)
            goto L_0x021a
        L_0x020f:
            int r2 = r1.f7954A
            int r3 = r1.f8083ai
            int r2 = java.lang.Math.max(r2, r3)
            r1.mo4691a((int) r2)
        L_0x021a:
            int r2 = r1.f7982ab
            r3 = 2
            if (r2 != r3) goto L_0x023a
            r2 = 1
            r1.f7982ab = r2
            if (r6 > 0) goto L_0x0225
            goto L_0x022f
        L_0x0225:
            int r3 = r1.f8084aj
            if (r6 >= r3) goto L_0x022f
            r1.f8080ae = r2
            r1.mo4696b(r6)
            goto L_0x023a
        L_0x022f:
            int r2 = r1.f7955B
            int r3 = r1.f8084aj
            int r2 = java.lang.Math.max(r2, r3)
            r1.mo4696b(r2)
        L_0x023a:
            r4 = r0
        L_0x023b:
            r2 = 0
            r1.f8085ak = r2
            r1.f8086al = r2
            java.util.ArrayList r0 = r1.f8330af
            int r2 = r0.size()
            r0 = 0
        L_0x0247:
            if (r0 >= r2) goto L_0x025e
            java.util.ArrayList r3 = r1.f8330af
            java.lang.Object r3 = r3.get(r0)
            ed r3 = (p000.C0116ed) r3
            boolean r7 = r3 instanceof p000.C0121ei
            if (r7 != 0) goto L_0x0256
            goto L_0x025b
        L_0x0256:
            ei r3 = (p000.C0121ei) r3
            r3.mo4742n()
        L_0x025b:
            int r0 = r0 + 1
            goto L_0x0247
        L_0x025e:
            r0 = 0
            r3 = 1
        L_0x0260:
            if (r3 == 0) goto L_0x0584
            r7 = 1
            int r8 = r0 + 1
            dy r0 = r1.f8081ag     // Catch:{ Exception -> 0x0402 }
            r0.mo4555a()     // Catch:{ Exception -> 0x0402 }
            dy r0 = r1.f8081ag     // Catch:{ Exception -> 0x0402 }
            boolean r3 = r1.m7319d(r0)     // Catch:{ Exception -> 0x0402 }
            if (r3 == 0) goto L_0x03f5
            dy r7 = r1.f8081ag     // Catch:{ Exception -> 0x03e6 }
            dx r11 = r7.f7621a     // Catch:{ Exception -> 0x03e6 }
            r11.mo4534a(r7)     // Catch:{ Exception -> 0x03e6 }
            r7.mo4557a((p000.C0109dx) r11)     // Catch:{ Exception -> 0x03e6 }
            r0 = 0
        L_0x027d:
            int r12 = r7.f7624d     // Catch:{ Exception -> 0x03e6 }
            if (r0 >= r12) goto L_0x0289
            boolean[] r12 = r7.f7623c     // Catch:{ Exception -> 0x03e6 }
            r13 = 0
            r12[r0] = r13     // Catch:{ Exception -> 0x03e6 }
            int r0 = r0 + 1
            goto L_0x027d
        L_0x0289:
            r0 = 0
            r12 = 0
        L_0x028b:
            if (r12 != 0) goto L_0x03c8
            java.util.ArrayList r13 = r11.f7549a     // Catch:{ Exception -> 0x03e6 }
            int r13 = r13.size()     // Catch:{ Exception -> 0x03e6 }
            r18 = r3
            r3 = 0
            r14 = 0
            r15 = 0
        L_0x0298:
            r19 = 0
            if (r14 >= r13) goto L_0x02da
            r20 = r12
            java.util.ArrayList r12 = r11.f7549a     // Catch:{ Exception -> 0x02d7 }
            java.lang.Object r12 = r12.get(r14)     // Catch:{ Exception -> 0x02d7 }
            eb r12 = (p000.C0114eb) r12     // Catch:{ Exception -> 0x02d7 }
            r22 = 5
            r22 = r13
            r13 = 5
        L_0x02ab:
            if (r13 >= 0) goto L_0x02b5
            int r14 = r14 + 1
            r12 = r20
            r13 = r22
            goto L_0x0298
        L_0x02b5:
            r25 = r14
            float[] r14 = r12.f7817e     // Catch:{ Exception -> 0x02d7 }
            r14 = r14[r13]     // Catch:{ Exception -> 0x02d7 }
            if (r3 == 0) goto L_0x02be
        L_0x02bd:
            goto L_0x02c8
        L_0x02be:
            int r26 = (r14 > r19 ? 1 : (r14 == r19 ? 0 : -1))
            if (r26 < 0) goto L_0x02c3
            goto L_0x02bd
        L_0x02c3:
            if (r13 >= r15) goto L_0x02c6
            goto L_0x02bd
        L_0x02c6:
            r3 = r12
            r15 = r13
        L_0x02c8:
            int r14 = (r14 > r19 ? 1 : (r14 == r19 ? 0 : -1))
            if (r14 > 0) goto L_0x02cd
        L_0x02cc:
            goto L_0x02d2
        L_0x02cd:
            if (r13 > r15) goto L_0x02d0
            goto L_0x02cc
        L_0x02d0:
            r15 = r13
            r3 = 0
        L_0x02d2:
            int r13 = r13 + -1
            r14 = r25
            goto L_0x02ab
        L_0x02d7:
            r0 = move-exception
            goto L_0x03e9
        L_0x02da:
            r20 = r12
            if (r3 == 0) goto L_0x02f6
            boolean[] r12 = r7.f7623c     // Catch:{ Exception -> 0x02d7 }
            int r13 = r3.f7813a     // Catch:{ Exception -> 0x02d7 }
            boolean r14 = r12[r13]     // Catch:{ Exception -> 0x02d7 }
            if (r14 == 0) goto L_0x02e9
            r3 = r0
            r0 = 0
            goto L_0x02fc
        L_0x02e9:
            r14 = 1
            r12[r13] = r14     // Catch:{ Exception -> 0x02d7 }
            int r0 = r0 + 1
            int r12 = r7.f7624d     // Catch:{ Exception -> 0x02d7 }
            if (r0 < r12) goto L_0x02f6
            r20 = 1
            goto L_0x02f7
        L_0x02f6:
        L_0x02f7:
            r30 = r3
            r3 = r0
            r0 = r30
        L_0x02fc:
            if (r0 == 0) goto L_0x03ae
            r12 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r12 = 0
            r14 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r15 = -1
        L_0x0306:
            int r13 = r7.f7625e     // Catch:{ Exception -> 0x02d7 }
            if (r12 >= r13) goto L_0x0372
            dv[] r13 = r7.f7622b     // Catch:{ Exception -> 0x02d7 }
            r13 = r13[r12]     // Catch:{ Exception -> 0x02d7 }
            r25 = r3
            eb r3 = r13.f7438a     // Catch:{ Exception -> 0x02d7 }
            int r3 = r3.f7820h     // Catch:{ Exception -> 0x02d7 }
            r26 = r4
            r4 = 1
            if (r3 == r4) goto L_0x035d
            du r3 = r13.f7441d     // Catch:{ Exception -> 0x035a }
            int r4 = r3.f7377f     // Catch:{ Exception -> 0x035a }
            r27 = r6
            r6 = -1
            r28 = r5
            if (r4 != r6) goto L_0x0327
            r29 = r9
            goto L_0x0364
        L_0x0327:
            r5 = 0
        L_0x0328:
            if (r4 == r6) goto L_0x0361
            int r6 = r3.f7372a     // Catch:{ Exception -> 0x0357 }
            if (r5 >= r6) goto L_0x0361
            int[] r6 = r3.f7374c     // Catch:{ Exception -> 0x0357 }
            r6 = r6[r4]     // Catch:{ Exception -> 0x0357 }
            r29 = r9
            int r9 = r0.f7813a     // Catch:{ Exception -> 0x03e4 }
            if (r6 == r9) goto L_0x0342
            int[] r6 = r3.f7375d     // Catch:{ Exception -> 0x03e4 }
            r4 = r6[r4]     // Catch:{ Exception -> 0x03e4 }
            int r5 = r5 + 1
            r9 = r29
            r6 = -1
            goto L_0x0328
        L_0x0342:
            du r3 = r13.f7441d     // Catch:{ Exception -> 0x03e4 }
            float r3 = r3.mo4450b((p000.C0114eb) r0)     // Catch:{ Exception -> 0x03e4 }
            int r4 = (r3 > r19 ? 1 : (r3 == r19 ? 0 : -1))
            if (r4 >= 0) goto L_0x0363
            float r4 = r13.f7439b     // Catch:{ Exception -> 0x03e4 }
            float r4 = -r4
            float r4 = r4 / r3
            int r3 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
            if (r3 >= 0) goto L_0x0363
            r14 = r4
            r15 = r12
            goto L_0x0364
        L_0x0357:
            r0 = move-exception
            goto L_0x03ef
        L_0x035a:
            r0 = move-exception
            goto L_0x03eb
        L_0x035d:
            r28 = r5
            r27 = r6
        L_0x0361:
            r29 = r9
        L_0x0363:
        L_0x0364:
            int r12 = r12 + 1
            r3 = r25
            r4 = r26
            r6 = r27
            r5 = r28
            r9 = r29
            goto L_0x0306
        L_0x0372:
            r25 = r3
            r26 = r4
            r28 = r5
            r27 = r6
            r29 = r9
            if (r15 < 0) goto L_0x03b8
            dv[] r3 = r7.f7622b     // Catch:{ Exception -> 0x03e4 }
            r3 = r3[r15]     // Catch:{ Exception -> 0x03e4 }
            eb r4 = r3.f7438a     // Catch:{ Exception -> 0x03e4 }
            r5 = -1
            r4.f7814b = r5     // Catch:{ Exception -> 0x03e4 }
            r3.mo4468a((p000.C0114eb) r0)     // Catch:{ Exception -> 0x03e4 }
            eb r0 = r3.f7438a     // Catch:{ Exception -> 0x03e4 }
            r0.f7814b = r15     // Catch:{ Exception -> 0x03e4 }
            r4 = 0
        L_0x038f:
            int r0 = r7.f7625e     // Catch:{ Exception -> 0x03e4 }
            if (r4 >= r0) goto L_0x039d
            dv[] r0 = r7.f7622b     // Catch:{ Exception -> 0x03e4 }
            r0 = r0[r4]     // Catch:{ Exception -> 0x03e4 }
            r0.mo4467a((p000.C0107dv) r3)     // Catch:{ Exception -> 0x03e4 }
            int r4 = r4 + 1
            goto L_0x038f
        L_0x039d:
            r11.mo4534a(r7)     // Catch:{ Exception -> 0x03e4 }
            r7.mo4557a((p000.C0109dx) r11)     // Catch:{ Exception -> 0x03a4 }
            goto L_0x03ab
        L_0x03a4:
            r0 = move-exception
            r3 = r0
            p000.ifn.m12932a(r3)     // Catch:{ Exception -> 0x03e4 }
        L_0x03ab:
            r12 = r20
            goto L_0x03b9
        L_0x03ae:
            r25 = r3
            r26 = r4
            r28 = r5
            r27 = r6
            r29 = r9
        L_0x03b8:
            r12 = 1
        L_0x03b9:
            r3 = r18
            r0 = r25
            r4 = r26
            r6 = r27
            r5 = r28
            r9 = r29
            goto L_0x028b
        L_0x03c8:
            r18 = r3
            r26 = r4
            r28 = r5
            r27 = r6
            r29 = r9
            r4 = 0
        L_0x03d3:
            int r0 = r7.f7625e     // Catch:{ Exception -> 0x03e4 }
            if (r4 >= r0) goto L_0x03ff
            dv[] r0 = r7.f7622b     // Catch:{ Exception -> 0x03e4 }
            r0 = r0[r4]     // Catch:{ Exception -> 0x03e4 }
            eb r3 = r0.f7438a     // Catch:{ Exception -> 0x03e4 }
            float r0 = r0.f7439b     // Catch:{ Exception -> 0x03e4 }
            r3.f7816d = r0     // Catch:{ Exception -> 0x03e4 }
            int r4 = r4 + 1
            goto L_0x03d3
        L_0x03e4:
            r0 = move-exception
            goto L_0x03f1
        L_0x03e6:
            r0 = move-exception
            r18 = r3
        L_0x03e9:
            r26 = r4
        L_0x03eb:
            r28 = r5
            r27 = r6
        L_0x03ef:
            r29 = r9
        L_0x03f1:
            r3 = r18
            goto L_0x040b
        L_0x03f5:
            r18 = r3
            r26 = r4
            r28 = r5
            r27 = r6
            r29 = r9
        L_0x03ff:
            r3 = r18
            goto L_0x0410
        L_0x0402:
            r0 = move-exception
            r26 = r4
            r28 = r5
            r27 = r6
            r29 = r9
        L_0x040b:
            p000.ifn.m12932a(r0)
        L_0x0410:
            r0 = 3
            if (r3 == 0) goto L_0x0454
            boolean[] r3 = r1.f8090ap
            r4 = 0
            r5 = 2
            r3[r5] = r4
            r31.mo4715m()
            java.util.ArrayList r5 = r1.f8330af
            int r5 = r5.size()
            r6 = 0
        L_0x0423:
            if (r6 >= r5) goto L_0x048d
            java.util.ArrayList r7 = r1.f8330af
            java.lang.Object r7 = r7.get(r6)
            ed r7 = (p000.C0116ed) r7
            r7.mo4715m()
            int r9 = r7.f7981aa
            if (r9 != r0) goto L_0x0440
            int r9 = r7.mo4699c()
            int r11 = r7.f7956C
            if (r9 >= r11) goto L_0x0440
            r9 = 2
            r11 = 1
            r3[r9] = r11
        L_0x0440:
            int r9 = r7.f7982ab
            if (r9 == r0) goto L_0x0445
            goto L_0x0451
        L_0x0445:
            int r9 = r7.mo4706f()
            int r7 = r7.f7957D
            if (r9 >= r7) goto L_0x0451
            r7 = 2
            r9 = 1
            r3[r7] = r9
        L_0x0451:
            int r6 = r6 + 1
            goto L_0x0423
        L_0x0454:
            r4 = 0
            r31.mo4715m()
            r3 = 0
        L_0x0459:
            if (r3 >= r2) goto L_0x048d
            java.util.ArrayList r5 = r1.f8330af
            java.lang.Object r5 = r5.get(r3)
            ed r5 = (p000.C0116ed) r5
            int r6 = r5.f7981aa
            if (r6 != r0) goto L_0x0476
            int r6 = r5.mo4699c()
            int r7 = r5.f7956C
            if (r6 >= r7) goto L_0x0476
            boolean[] r0 = r1.f8090ap
            r3 = 2
            r5 = 1
            r0[r3] = r5
            goto L_0x048d
        L_0x0476:
            int r6 = r5.f7982ab
            if (r6 == r0) goto L_0x047b
            goto L_0x048a
        L_0x047b:
            int r6 = r5.mo4706f()
            int r5 = r5.f7957D
            if (r6 >= r5) goto L_0x048a
            boolean[] r0 = r1.f8090ap
            r3 = 2
            r5 = 1
            r0[r3] = r5
            goto L_0x048d
        L_0x048a:
            int r3 = r3 + 1
            goto L_0x0459
        L_0x048d:
            r3 = 8
            if (r8 >= r3) goto L_0x04f5
            boolean[] r0 = r1.f8090ap
            r5 = 2
            boolean r0 = r0[r5]
            if (r0 == 0) goto L_0x04f5
            r0 = 0
            r5 = 0
            r6 = 0
        L_0x049b:
            if (r0 >= r2) goto L_0x04be
            java.util.ArrayList r7 = r1.f8330af
            java.lang.Object r7 = r7.get(r0)
            ed r7 = (p000.C0116ed) r7
            int r9 = r7.f8006t
            int r11 = r7.mo4699c()
            int r9 = r9 + r11
            int r5 = java.lang.Math.max(r5, r9)
            int r9 = r7.f8007u
            int r7 = r7.mo4706f()
            int r9 = r9 + r7
            int r6 = java.lang.Math.max(r6, r9)
            int r0 = r0 + 1
            goto L_0x049b
        L_0x04be:
            int r0 = r1.f7954A
            int r0 = java.lang.Math.max(r0, r5)
            int r5 = r1.f7955B
            int r5 = java.lang.Math.max(r5, r6)
            r6 = 2
            if (r10 == r6) goto L_0x04cf
        L_0x04cd:
            r0 = 0
            goto L_0x04e0
        L_0x04cf:
            int r7 = r31.mo4699c()
            if (r7 >= r0) goto L_0x04df
            r1.mo4691a((int) r0)
            r1.f7981aa = r6
            r0 = 1
            r26 = 1
            goto L_0x04e0
        L_0x04df:
            goto L_0x04cd
        L_0x04e0:
            r7 = r29
            if (r7 == r6) goto L_0x04e5
        L_0x04e4:
            goto L_0x04f8
        L_0x04e5:
            int r9 = r31.mo4706f()
            if (r9 >= r5) goto L_0x04e4
            r1.mo4696b(r5)
            r1.f7982ab = r6
            r0 = 1
            r26 = 1
            goto L_0x04f8
        L_0x04f5:
            r7 = r29
            r0 = 0
        L_0x04f8:
            int r5 = r1.f7954A
            int r6 = r31.mo4699c()
            int r5 = java.lang.Math.max(r5, r6)
            int r6 = r31.mo4699c()
            if (r5 <= r6) goto L_0x0512
            r1.mo4691a((int) r5)
            r5 = 1
            r1.f7981aa = r5
            r0 = 1
            r26 = 1
            goto L_0x0513
        L_0x0512:
        L_0x0513:
            int r5 = r1.f7955B
            int r6 = r31.mo4706f()
            int r5 = java.lang.Math.max(r5, r6)
            int r6 = r31.mo4706f()
            if (r5 <= r6) goto L_0x052d
            r1.mo4696b(r5)
            r5 = 1
            r1.f7982ab = r5
            r0 = 1
            r26 = 1
            goto L_0x052e
        L_0x052d:
        L_0x052e:
            if (r26 == 0) goto L_0x0537
            r11 = r27
            r6 = r28
            r5 = 1
            r9 = 2
            goto L_0x057a
        L_0x0537:
            int r5 = r1.f7981aa
            r6 = 2
            if (r5 == r6) goto L_0x053f
            r6 = r28
            goto L_0x0559
        L_0x053f:
            if (r28 <= 0) goto L_0x0556
            int r5 = r31.mo4699c()
            r6 = r28
            if (r5 <= r6) goto L_0x0558
            r5 = 1
            r1.f8079ad = r5
            r1.f7981aa = r5
            r1.mo4691a((int) r6)
            r0 = 1
            r26 = 1
            goto L_0x0559
        L_0x0556:
            r6 = r28
        L_0x0558:
        L_0x0559:
            int r5 = r1.f7982ab
            r9 = 2
            if (r5 != r9) goto L_0x0577
            if (r27 <= 0) goto L_0x0577
            int r5 = r31.mo4706f()
            r11 = r27
            if (r5 <= r11) goto L_0x0575
            r5 = 1
            r1.f8080ae = r5
            r1.f7982ab = r5
            r1.mo4696b(r11)
            r0 = 1
            r26 = 1
            goto L_0x057a
        L_0x0575:
            r5 = 1
            goto L_0x057a
        L_0x0577:
            r11 = r27
            r5 = 1
        L_0x057a:
            r3 = r0
            r5 = r6
            r9 = r7
            r0 = r8
            r6 = r11
            r4 = r26
            goto L_0x0260
        L_0x0584:
            r26 = r4
            r7 = r9
            r4 = 0
            ed r0 = r1.f8001o
            if (r0 == 0) goto L_0x05e6
            int r0 = r1.f7954A
            int r2 = r31.mo4699c()
            int r0 = java.lang.Math.max(r0, r2)
            int r2 = r1.f7955B
            int r3 = r31.mo4706f()
            int r2 = java.lang.Math.max(r2, r3)
            eh r3 = r1.f8082ah
            int r5 = r3.f8254a
            r1.f8006t = r5
            int r5 = r3.f8255b
            r1.f8007u = r5
            int r5 = r3.f8256c
            r1.mo4691a((int) r5)
            int r5 = r3.f8257d
            r1.mo4696b(r5)
            java.util.ArrayList r5 = r3.f8258e
            int r5 = r5.size()
        L_0x05ba:
            if (r4 >= r5) goto L_0x05df
            java.util.ArrayList r6 = r3.f8258e
            java.lang.Object r6 = r6.get(r4)
            eg r6 = (p000.C0119eg) r6
            ec r8 = r6.f8182a
            int r8 = r8.f7907f
            ec r11 = r1.mo4705e(r8)
            ec r12 = r6.f8183b
            int r13 = r6.f8184c
            r14 = -1
            int r15 = r6.f8186e
            int r6 = r6.f8185d
            r17 = 0
            r16 = r6
            r11.mo4668a(r12, r13, r14, r15, r16, r17)
            int r4 = r4 + 1
            goto L_0x05ba
        L_0x05df:
            r1.mo4691a((int) r0)
            r1.mo4696b(r2)
            goto L_0x05ee
        L_0x05e6:
            r2 = r23
            r1.f8006t = r2
            r2 = r24
            r1.f8007u = r2
        L_0x05ee:
            if (r26 != 0) goto L_0x05f1
            goto L_0x05f5
        L_0x05f1:
            r1.f7981aa = r10
            r1.f7982ab = r7
        L_0x05f5:
            dy r0 = r1.f8081ag
            dw r0 = r0.f7626f
            r1.mo4694a((p000.C0108dw) r0)
            ed r0 = r1.f8001o
            r2 = r1
        L_0x05ff:
            if (r0 == 0) goto L_0x060a
            ed r3 = r0.f8001o
            boolean r4 = r0 instanceof p000.C0117ee
            if (r4 == 0) goto L_0x0608
            r2 = r0
        L_0x0608:
            r0 = r3
            goto L_0x05ff
        L_0x060a:
            if (r1 != r2) goto L_0x060f
            r31.mo4714l()
        L_0x060f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0117ee.mo4742n():void");
    }

    /* renamed from: a */
    public final void mo4690a() {
        this.f8081ag.mo4555a();
        super.mo4690a();
    }
}
