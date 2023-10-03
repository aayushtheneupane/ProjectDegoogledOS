package p000;

import android.support.p002v7.widget.RecyclerView;
import java.util.ArrayList;

/* renamed from: ed */
/* compiled from: PG */
public class C0116ed {

    /* renamed from: A */
    public int f7954A;

    /* renamed from: B */
    public int f7955B;

    /* renamed from: C */
    public int f7956C;

    /* renamed from: D */
    public int f7957D;

    /* renamed from: E */
    public float f7958E;

    /* renamed from: F */
    public float f7959F;

    /* renamed from: G */
    public Object f7960G;

    /* renamed from: H */
    public int f7961H;

    /* renamed from: I */
    public int f7962I;

    /* renamed from: J */
    public int f7963J;

    /* renamed from: K */
    public int f7964K;

    /* renamed from: L */
    public int f7965L;

    /* renamed from: M */
    public boolean f7966M;

    /* renamed from: N */
    public boolean f7967N;

    /* renamed from: O */
    public boolean f7968O;

    /* renamed from: P */
    public boolean f7969P;

    /* renamed from: Q */
    public boolean f7970Q;

    /* renamed from: R */
    public boolean f7971R;

    /* renamed from: S */
    public int f7972S;

    /* renamed from: T */
    public int f7973T;

    /* renamed from: U */
    public boolean f7974U;

    /* renamed from: V */
    public boolean f7975V;

    /* renamed from: W */
    public float f7976W;

    /* renamed from: X */
    public float f7977X;

    /* renamed from: Y */
    public C0116ed f7978Y;

    /* renamed from: Z */
    public C0116ed f7979Z;

    /* renamed from: a */
    public int f7980a = -1;

    /* renamed from: aa */
    public int f7981aa;

    /* renamed from: ab */
    public int f7982ab;

    /* renamed from: ac */
    private final C0115ec f7983ac = new C0115ec(this, 8);

    /* renamed from: ad */
    private final C0115ec f7984ad = new C0115ec(this, 9);

    /* renamed from: ae */
    private final C0115ec f7985ae = new C0115ec(this, 7);

    /* renamed from: af */
    private int f7986af;

    /* renamed from: ag */
    private int f7987ag;

    /* renamed from: b */
    public int f7988b = -1;

    /* renamed from: c */
    public int f7989c = 0;

    /* renamed from: d */
    public int f7990d = 0;

    /* renamed from: e */
    public int f7991e = 0;

    /* renamed from: f */
    public int f7992f = 0;

    /* renamed from: g */
    public int f7993g = 0;

    /* renamed from: h */
    public int f7994h = 0;

    /* renamed from: i */
    public final C0115ec f7995i = new C0115ec(this, 2);

    /* renamed from: j */
    public final C0115ec f7996j = new C0115ec(this, 3);

    /* renamed from: k */
    public final C0115ec f7997k = new C0115ec(this, 4);

    /* renamed from: l */
    public final C0115ec f7998l = new C0115ec(this, 5);

    /* renamed from: m */
    public final C0115ec f7999m = new C0115ec(this, 6);

    /* renamed from: n */
    public final ArrayList f8000n;

    /* renamed from: o */
    public C0116ed f8001o;

    /* renamed from: p */
    public int f8002p;

    /* renamed from: q */
    public int f8003q;

    /* renamed from: r */
    public float f8004r;

    /* renamed from: s */
    public int f8005s;

    /* renamed from: t */
    public int f8006t;

    /* renamed from: u */
    public int f8007u;

    /* renamed from: v */
    public int f8008v;

    /* renamed from: w */
    public int f8009w;

    /* renamed from: x */
    public int f8010x;

    /* renamed from: y */
    public int f8011y;

    /* renamed from: z */
    public int f8012z;

    public C0116ed() {
        ArrayList arrayList = new ArrayList();
        this.f8000n = arrayList;
        this.f8001o = null;
        this.f8002p = 0;
        this.f8003q = 0;
        this.f8004r = 0.0f;
        this.f8005s = -1;
        this.f8006t = 0;
        this.f8007u = 0;
        this.f7986af = 0;
        this.f7987ag = 0;
        this.f8008v = 0;
        this.f8009w = 0;
        this.f8010x = 0;
        this.f8011y = 0;
        this.f8012z = 0;
        this.f7958E = 0.5f;
        this.f7959F = 0.5f;
        this.f7981aa = 1;
        this.f7982ab = 1;
        this.f7961H = 0;
        this.f7972S = 0;
        this.f7973T = 0;
        this.f7976W = 0.0f;
        this.f7977X = 0.0f;
        this.f7978Y = null;
        this.f7979Z = null;
        arrayList.add(this.f7995i);
        this.f8000n.add(this.f7996j);
        this.f8000n.add(this.f7997k);
        this.f8000n.add(this.f7998l);
        this.f8000n.add(this.f7983ac);
        this.f8000n.add(this.f7984ad);
        this.f8000n.add(this.f7999m);
    }

    /* renamed from: b */
    public final boolean mo4698b() {
        return this.f8001o == null;
    }

    /* renamed from: c */
    public final int mo4699c() {
        if (this.f7961H == 8) {
            return 0;
        }
        return this.f8002p;
    }

    /* renamed from: e */
    public C0115ec mo4705e(int i) {
        switch (i - 1) {
            case 1:
                return this.f7995i;
            case RecyclerView.SCROLL_STATE_SETTLING:
                return this.f7996j;
            case 3:
                return this.f7997k;
            case 4:
                return this.f7998l;
            case 5:
                return this.f7999m;
            case 6:
                return this.f7985ae;
            case 7:
                return this.f7983ac;
            default:
                return this.f7984ad;
        }
    }

    /* renamed from: f */
    public final int mo4706f() {
        if (this.f7961H == 8) {
            return 0;
        }
        return this.f8003q;
    }

    /* renamed from: g */
    public final int mo4708g() {
        return this.f7986af + this.f8010x;
    }

    /* renamed from: h */
    public final int mo4710h() {
        return this.f7987ag + this.f8011y;
    }

    /* renamed from: i */
    public final int mo4711i() {
        return this.f8006t + this.f8002p;
    }

    /* renamed from: j */
    public final int mo4712j() {
        return this.f8007u + this.f8003q;
    }

    /* renamed from: k */
    public final boolean mo4713k() {
        return this.f8012z > 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:133:0x020e, code lost:
        if (r13 == -1) goto L_0x0213;
     */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x021c  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x021f  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0225  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x0237  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x031d  */
    /* JADX WARNING: Removed duplicated region for block: B:225:0x0518  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo4695a(p000.C0110dy r41) {
        /*
            r40 = this;
            r15 = r40
            r14 = r41
            ec r0 = r15.f7995i
            eb r13 = r14.mo4554a((java.lang.Object) r0)
            ec r0 = r15.f7997k
            eb r12 = r14.mo4554a((java.lang.Object) r0)
            ec r0 = r15.f7996j
            eb r11 = r14.mo4554a((java.lang.Object) r0)
            ec r0 = r15.f7998l
            eb r10 = r14.mo4554a((java.lang.Object) r0)
            ec r0 = r15.f7999m
            eb r9 = r14.mo4554a((java.lang.Object) r0)
            ed r0 = r15.f8001o
            r8 = 2
            r7 = 1
            r6 = 0
            if (r0 == 0) goto L_0x011b
            ec r1 = r15.f7995i
            ec r2 = r1.f7903b
            if (r2 != 0) goto L_0x0030
            goto L_0x0034
        L_0x0030:
            ec r2 = r2.f7903b
            if (r2 == r1) goto L_0x0040
        L_0x0034:
            ec r1 = r15.f7997k
            ec r2 = r1.f7903b
            if (r2 == 0) goto L_0x003e
            ec r2 = r2.f7903b
            if (r2 == r1) goto L_0x0040
        L_0x003e:
            r0 = 0
            goto L_0x0046
        L_0x0040:
            ee r0 = (p000.C0117ee) r0
            r0.mo4741a((p000.C0116ed) r15, (int) r6)
            r0 = 1
        L_0x0046:
            ec r1 = r15.f7996j
            ec r2 = r1.f7903b
            if (r2 != 0) goto L_0x004d
            goto L_0x0051
        L_0x004d:
            ec r2 = r2.f7903b
            if (r2 == r1) goto L_0x005f
        L_0x0051:
            ec r1 = r15.f7998l
            ec r2 = r1.f7903b
            if (r2 == 0) goto L_0x005d
            ec r2 = r2.f7903b
            if (r2 != r1) goto L_0x005c
            goto L_0x005f
        L_0x005c:
        L_0x005d:
            r1 = 0
            goto L_0x0067
        L_0x005f:
            ed r1 = r15.f8001o
            ee r1 = (p000.C0117ee) r1
            r1.mo4741a((p000.C0116ed) r15, (int) r7)
            r1 = 1
        L_0x0067:
            ed r2 = r15.f8001o
            int r3 = r2.f7981aa
            if (r3 == r8) goto L_0x006e
            goto L_0x00bc
        L_0x006e:
            if (r0 != 0) goto L_0x00bc
            ec r3 = r15.f7995i
            ec r4 = r3.f7903b
            if (r4 != 0) goto L_0x0077
            goto L_0x0080
        L_0x0077:
            ed r4 = r4.f7902a
            if (r4 != r2) goto L_0x0080
            if (r4 != r2) goto L_0x0094
            r3.f7909h = r8
            goto L_0x0094
        L_0x0080:
            ec r2 = r2.f7995i
            eb r2 = r14.mo4554a((java.lang.Object) r2)
            dv r3 = r41.mo4561b()
            eb r4 = r41.mo4563c()
            r3.mo4473a(r13, r2, r4, r6)
            r14.mo4556a((p000.C0107dv) r3)
        L_0x0094:
            ec r2 = r15.f7997k
            ec r3 = r2.f7903b
            if (r3 != 0) goto L_0x009b
            goto L_0x00a6
        L_0x009b:
            ed r3 = r3.f7902a
            ed r4 = r15.f8001o
            if (r3 != r4) goto L_0x00a6
            if (r3 != r4) goto L_0x00bc
            r2.f7909h = r8
            goto L_0x00bc
        L_0x00a6:
            ed r2 = r15.f8001o
            ec r2 = r2.f7997k
            eb r2 = r14.mo4554a((java.lang.Object) r2)
            dv r3 = r41.mo4561b()
            eb r4 = r41.mo4563c()
            r3.mo4473a(r2, r12, r4, r6)
            r14.mo4556a((p000.C0107dv) r3)
        L_0x00bc:
            ed r2 = r15.f8001o
            int r3 = r2.f7982ab
            if (r3 == r8) goto L_0x00c3
        L_0x00c2:
            goto L_0x0116
        L_0x00c3:
            if (r1 != 0) goto L_0x00c2
            ec r3 = r15.f7996j
            ec r4 = r3.f7903b
            if (r4 != 0) goto L_0x00cc
            goto L_0x00d5
        L_0x00cc:
            ed r4 = r4.f7902a
            if (r4 != r2) goto L_0x00d5
            if (r4 != r2) goto L_0x00e9
            r3.f7909h = r8
            goto L_0x00e9
        L_0x00d5:
            ec r2 = r2.f7996j
            eb r2 = r14.mo4554a((java.lang.Object) r2)
            dv r3 = r41.mo4561b()
            eb r4 = r41.mo4563c()
            r3.mo4473a(r11, r2, r4, r6)
            r14.mo4556a((p000.C0107dv) r3)
        L_0x00e9:
            ec r2 = r15.f7998l
            ec r3 = r2.f7903b
            if (r3 != 0) goto L_0x00f0
            goto L_0x00fe
        L_0x00f0:
            ed r3 = r3.f7902a
            ed r4 = r15.f8001o
            if (r3 != r4) goto L_0x00fe
            if (r3 == r4) goto L_0x00f9
            goto L_0x00c2
        L_0x00f9:
            r2.f7909h = r8
            goto L_0x0116
        L_0x00fe:
            ed r2 = r15.f8001o
            ec r2 = r2.f7998l
            eb r2 = r14.mo4554a((java.lang.Object) r2)
            dv r3 = r41.mo4561b()
            eb r4 = r41.mo4563c()
            r3.mo4473a(r2, r10, r4, r6)
            r14.mo4556a((p000.C0107dv) r3)
        L_0x0116:
            r16 = r0
            r17 = r1
            goto L_0x0120
        L_0x011b:
            r16 = 0
            r17 = 0
        L_0x0120:
            int r0 = r15.f8002p
            int r5 = r15.f7954A
            if (r0 < r5) goto L_0x0128
            r1 = r0
            goto L_0x0129
        L_0x0128:
            r1 = r5
        L_0x0129:
            int r2 = r15.f8003q
            int r3 = r15.f7955B
            if (r2 >= r3) goto L_0x0130
            goto L_0x0131
        L_0x0130:
            r3 = r2
        L_0x0131:
            int r4 = r15.f7981aa
            r8 = 3
            if (r4 == r8) goto L_0x0139
            r19 = 1
            goto L_0x013b
        L_0x0139:
            r19 = 0
        L_0x013b:
            int r6 = r15.f7982ab
            if (r6 == r8) goto L_0x0142
            r21 = 1
            goto L_0x0144
        L_0x0142:
            r21 = 0
        L_0x0144:
            if (r19 != 0) goto L_0x0159
            ec r7 = r15.f7995i
            if (r7 == 0) goto L_0x0159
            ec r8 = r15.f7997k
            if (r8 == 0) goto L_0x0159
            ec r7 = r7.f7903b
            if (r7 == 0) goto L_0x0157
            ec r7 = r8.f7903b
            if (r7 == 0) goto L_0x0157
            goto L_0x0159
        L_0x0157:
            r19 = 1
        L_0x0159:
            if (r21 == 0) goto L_0x015c
            goto L_0x017c
        L_0x015c:
            ec r7 = r15.f7996j
            if (r7 == 0) goto L_0x017c
            ec r8 = r15.f7998l
            if (r8 == 0) goto L_0x017c
            ec r7 = r7.f7903b
            if (r7 == 0) goto L_0x016c
            ec r8 = r8.f7903b
            if (r8 != 0) goto L_0x017c
        L_0x016c:
            int r8 = r15.f8012z
            if (r8 == 0) goto L_0x017a
            ec r8 = r15.f7999m
            if (r8 == 0) goto L_0x017c
            if (r7 == 0) goto L_0x017a
            ec r7 = r8.f7903b
            if (r7 != 0) goto L_0x017c
        L_0x017a:
            r21 = 1
        L_0x017c:
            int r7 = r15.f8005s
            float r8 = r15.f8004r
            r24 = 0
            r25 = r13
            int r24 = (r8 > r24 ? 1 : (r8 == r24 ? 0 : -1))
            if (r24 > 0) goto L_0x0193
            r13 = r7
            r26 = 0
            r7 = r3
            r3 = r19
            r19 = r8
            r8 = r1
            goto L_0x0205
        L_0x0193:
            int r13 = r15.f7961H
            r26 = r1
            r1 = 8
            if (r13 == r1) goto L_0x01fb
            r1 = 1065353216(0x3f800000, float:1.0)
            r13 = 3
            if (r4 != r13) goto L_0x01d0
            if (r6 != r13) goto L_0x01d0
            if (r19 == 0) goto L_0x01ac
            if (r21 == 0) goto L_0x01a7
            goto L_0x01ac
        L_0x01a7:
            r7 = r3
            r3 = r19
            r13 = 0
            goto L_0x01c9
        L_0x01ac:
            if (r19 != 0) goto L_0x01c5
            if (r21 == 0) goto L_0x01c5
            r0 = -1
            if (r7 != r0) goto L_0x01c0
            float r1 = r1 / r8
            r7 = r3
            r3 = r19
            r8 = r26
            r13 = 1
            r26 = 1
            r19 = r1
            goto L_0x0205
        L_0x01c0:
            r7 = r3
            r3 = r19
            r13 = 1
            goto L_0x01c9
        L_0x01c5:
            r13 = r7
            r7 = r3
            r3 = r19
        L_0x01c9:
            r19 = r8
            r8 = r26
            r26 = 1
            goto L_0x0205
        L_0x01d0:
            r13 = 3
            if (r4 != r13) goto L_0x01e2
            float r0 = (float) r2
            float r0 = r0 * r8
            int r0 = (int) r0
            r7 = r3
            r19 = r8
            r3 = 1
            r13 = 0
            r26 = 0
            r8 = r0
            goto L_0x0205
        L_0x01e2:
            if (r6 != r13) goto L_0x01fb
            r2 = -1
            if (r7 != r2) goto L_0x01e9
            float r8 = r1 / r8
        L_0x01e9:
            float r0 = (float) r0
            float r0 = r0 * r8
            int r0 = (int) r0
            r7 = r0
            r3 = r19
            r13 = 1
            r21 = 1
            r19 = r8
            r8 = r26
            r26 = 0
            goto L_0x0205
        L_0x01fb:
            r13 = r7
            r7 = r3
            r3 = r19
            r19 = r8
            r8 = r26
            r26 = 0
        L_0x0205:
            if (r26 != 0) goto L_0x020b
            r6 = -1
        L_0x0208:
            r24 = 0
            goto L_0x0215
        L_0x020b:
            if (r13 == 0) goto L_0x0212
            r6 = -1
            if (r13 != r6) goto L_0x0211
            goto L_0x0213
        L_0x0211:
            goto L_0x0208
        L_0x0212:
            r6 = -1
        L_0x0213:
            r24 = 1
        L_0x0215:
            r0 = 2
            if (r4 != r0) goto L_0x021f
            boolean r0 = r15 instanceof p000.C0117ee
            if (r0 == 0) goto L_0x021e
            r2 = 1
            goto L_0x0220
        L_0x021e:
        L_0x021f:
            r2 = 0
        L_0x0220:
            int r0 = r15.f7980a
            r4 = 2
            if (r0 != r4) goto L_0x0237
            r33 = r7
            r34 = r9
            r35 = r10
            r36 = r11
            r18 = r12
            r37 = r13
            r16 = r25
            r28 = 0
            goto L_0x0316
        L_0x0237:
            if (r24 != 0) goto L_0x0243
            r20 = -1
            r23 = 3
            r27 = 2
            r28 = 0
            goto L_0x02d4
        L_0x0243:
            ec r0 = r15.f7995i
            ec r1 = r0.f7903b
            if (r1 == 0) goto L_0x02cc
            ec r1 = r15.f7997k
            ec r1 = r1.f7903b
            if (r1 == 0) goto L_0x02cc
            eb r1 = r14.mo4554a((java.lang.Object) r0)
            ec r0 = r15.f7997k
            eb r8 = r14.mo4554a((java.lang.Object) r0)
            ec r0 = r15.f7995i
            ec r0 = r0.f7903b
            eb r2 = r14.mo4554a((java.lang.Object) r0)
            ec r0 = r15.f7997k
            ec r0 = r0.f7903b
            eb r5 = r14.mo4554a((java.lang.Object) r0)
            ec r0 = r15.f7995i
            int r0 = r0.mo4667a()
            r3 = 3
            r14.mo4560a(r1, r2, r0, r3)
            ec r0 = r15.f7997k
            int r0 = r0.mo4667a()
            int r0 = -r0
            r14.mo4562b(r8, r5, r0, r3)
            if (r16 != 0) goto L_0x02b4
            ec r0 = r15.f7995i
            int r16 = r0.mo4667a()
            float r0 = r15.f7958E
            ec r3 = r15.f7997k
            int r18 = r3.mo4667a()
            r24 = r0
            r0 = r41
            r23 = 3
            r3 = r16
            r27 = 2
            r4 = r24
            r20 = -1
            r28 = 0
            r6 = r8
            r8 = r7
            r7 = r18
            r0.mo4559a(r1, r2, r3, r4, r5, r6, r7)
            r33 = r8
            r34 = r9
            r35 = r10
            r36 = r11
            r18 = r12
            r37 = r13
            r16 = r25
            goto L_0x0316
        L_0x02b4:
            r8 = r7
            r20 = -1
            r23 = 3
            r27 = 2
            r28 = 0
            r33 = r8
            r34 = r9
            r35 = r10
            r36 = r11
            r18 = r12
            r37 = r13
            r16 = r25
            goto L_0x0316
        L_0x02cc:
            r20 = -1
            r23 = 3
            r27 = 2
            r28 = 0
        L_0x02d4:
            ec r4 = r15.f7995i
            ec r6 = r15.f7997k
            int r1 = r15.f8006t
            int r18 = r1 + r8
            float r0 = r15.f7958E
            r22 = r13
            int r13 = r15.f7989c
            int r14 = r15.f7991e
            r29 = r14
            int r14 = r15.f7992f
            r30 = r0
            r0 = r40
            r31 = r1
            r1 = r41
            r32 = r5
            r5 = r6
            r6 = r31
            r33 = r7
            r7 = r18
            r34 = r9
            r9 = r32
            r35 = r10
            r10 = r30
            r36 = r11
            r11 = r24
            r18 = r12
            r12 = r16
            r37 = r22
            r16 = r25
            r20 = r14
            r14 = r29
            r15 = r20
            r0.m7224a(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
        L_0x0316:
            r15 = r40
            int r0 = r15.f7988b
            r1 = 2
            if (r0 == r1) goto L_0x0518
            int r0 = r15.f7982ab
            if (r0 != r1) goto L_0x0327
            boolean r0 = r15 instanceof p000.C0117ee
            if (r0 == 0) goto L_0x0327
            r2 = 1
            goto L_0x0328
        L_0x0327:
            r2 = 0
        L_0x0328:
            if (r26 != 0) goto L_0x032f
            r14 = r37
            r11 = 0
            r13 = 1
            goto L_0x033b
        L_0x032f:
            r14 = r37
            r13 = 1
            if (r14 == r13) goto L_0x033a
            r0 = -1
            if (r14 != r0) goto L_0x0338
            goto L_0x033a
        L_0x0338:
            r11 = 0
            goto L_0x033b
        L_0x033a:
            r11 = 1
        L_0x033b:
            int r0 = r15.f8012z
            if (r0 <= 0) goto L_0x0400
            ec r1 = r15.f7998l
            r12 = 5
            r10 = r41
            r3 = r34
            r9 = r36
            r10.mo4564c(r3, r9, r0, r12)
            ec r0 = r15.f7999m
            ec r3 = r0.f7903b
            if (r3 == 0) goto L_0x0356
            int r1 = r15.f8012z
            r5 = r0
            r8 = r1
            goto L_0x0359
        L_0x0356:
            r5 = r1
            r8 = r33
        L_0x0359:
            if (r11 != 0) goto L_0x035d
            r7 = 3
            goto L_0x03b9
        L_0x035d:
            ec r0 = r15.f7996j
            ec r1 = r0.f7903b
            if (r1 == 0) goto L_0x03b8
            ec r1 = r15.f7998l
            ec r1 = r1.f7903b
            if (r1 == 0) goto L_0x03b8
            eb r1 = r10.mo4554a((java.lang.Object) r0)
            ec r0 = r15.f7998l
            eb r6 = r10.mo4554a((java.lang.Object) r0)
            ec r0 = r15.f7996j
            ec r0 = r0.f7903b
            eb r2 = r10.mo4554a((java.lang.Object) r0)
            ec r0 = r15.f7998l
            ec r0 = r0.f7903b
            eb r5 = r10.mo4554a((java.lang.Object) r0)
            ec r0 = r15.f7996j
            int r0 = r0.mo4667a()
            r8 = 3
            r10.mo4560a(r1, r2, r0, r8)
            ec r0 = r15.f7998l
            int r0 = r0.mo4667a()
            int r0 = -r0
            r10.mo4562b(r6, r5, r0, r8)
            if (r17 != 0) goto L_0x03b2
            ec r0 = r15.f7996j
            int r3 = r0.mo4667a()
            float r4 = r15.f7959F
            ec r0 = r15.f7998l
            int r7 = r0.mo4667a()
            r0 = r41
            r0.mo4559a(r1, r2, r3, r4, r5, r6, r7)
            r36 = r9
            r39 = r14
            goto L_0x0499
        L_0x03b2:
            r36 = r9
            r39 = r14
            goto L_0x0499
        L_0x03b8:
            r7 = 3
        L_0x03b9:
            ec r4 = r15.f7996j
            int r6 = r15.f8007u
            int r20 = r6 + r8
            int r3 = r15.f7955B
            float r1 = r15.f7959F
            int r0 = r15.f7990d
            r37 = r14
            int r14 = r15.f7993g
            r22 = r14
            int r14 = r15.f7994h
            r23 = r0
            r0 = r40
            r24 = r1
            r1 = r41
            r25 = r3
            r3 = r21
            r7 = r20
            r38 = r9
            r9 = r25
            r10 = r24
            r12 = r17
            r13 = r23
            r17 = r14
            r39 = r37
            r14 = r22
            r15 = r17
            r0.m7224a(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            r15 = r41
            r8 = r33
            r13 = r35
            r14 = r38
            r0 = 5
            r15.mo4564c(r13, r14, r8, r0)
            r36 = r14
            goto L_0x0499
        L_0x0400:
            r15 = r41
            r39 = r14
            r8 = r33
            r13 = r35
            r14 = r36
            if (r11 != 0) goto L_0x0410
            r12 = r40
            r10 = 3
            goto L_0x046c
        L_0x0410:
            r12 = r40
            ec r0 = r12.f7996j
            ec r1 = r0.f7903b
            if (r1 == 0) goto L_0x046b
            ec r1 = r12.f7998l
            ec r1 = r1.f7903b
            if (r1 == 0) goto L_0x046b
            eb r1 = r15.mo4554a((java.lang.Object) r0)
            ec r0 = r12.f7998l
            eb r6 = r15.mo4554a((java.lang.Object) r0)
            ec r0 = r12.f7996j
            ec r0 = r0.f7903b
            eb r2 = r15.mo4554a((java.lang.Object) r0)
            ec r0 = r12.f7998l
            ec r0 = r0.f7903b
            eb r5 = r15.mo4554a((java.lang.Object) r0)
            ec r0 = r12.f7996j
            int r0 = r0.mo4667a()
            r10 = 3
            r15.mo4560a(r1, r2, r0, r10)
            ec r0 = r12.f7998l
            int r0 = r0.mo4667a()
            int r0 = -r0
            r15.mo4562b(r6, r5, r0, r10)
            if (r17 != 0) goto L_0x0466
            ec r0 = r12.f7996j
            int r3 = r0.mo4667a()
            float r4 = r12.f7959F
            ec r0 = r12.f7998l
            int r7 = r0.mo4667a()
            r0 = r41
            r0.mo4559a(r1, r2, r3, r4, r5, r6, r7)
            r35 = r13
            r36 = r14
            goto L_0x0499
        L_0x0466:
            r35 = r13
            r36 = r14
            goto L_0x0499
        L_0x046b:
            r10 = 3
        L_0x046c:
            ec r4 = r12.f7996j
            ec r5 = r12.f7998l
            int r6 = r12.f8007u
            int r7 = r6 + r8
            int r9 = r12.f7955B
            float r3 = r12.f7959F
            int r1 = r12.f7990d
            int r0 = r12.f7993g
            int r15 = r12.f7994h
            r20 = r0
            r0 = r40
            r22 = r1
            r1 = r41
            r23 = r3
            r3 = r21
            r10 = r23
            r12 = r17
            r35 = r13
            r13 = r22
            r36 = r14
            r14 = r20
            r0.m7224a(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
        L_0x0499:
            if (r26 == 0) goto L_0x0515
            dv r0 = r41.mo4561b()
            r7 = r39
            if (r7 != 0) goto L_0x04b7
            r1 = r0
            r2 = r18
            r3 = r16
            r4 = r35
            r5 = r36
            r6 = r19
            r1.mo4474a(r2, r3, r4, r5, r6)
            r8 = r41
            r8.mo4556a((p000.C0107dv) r0)
            return
        L_0x04b7:
            r8 = r41
            r1 = 1
            if (r7 == r1) goto L_0x04ff
            r7 = r40
            int r1 = r7.f7991e
            if (r1 > 0) goto L_0x04c8
            r5 = r16
            r4 = r18
            r2 = 3
            goto L_0x04d1
        L_0x04c8:
            r5 = r16
            r4 = r18
            r2 = 3
            r8.mo4560a(r4, r5, r1, r2)
        L_0x04d1:
            int r1 = r7.f7993g
            if (r1 <= 0) goto L_0x04dd
            r9 = r35
            r6 = r36
            r8.mo4560a(r9, r6, r1, r2)
            goto L_0x04e1
        L_0x04dd:
            r9 = r35
            r6 = r36
        L_0x04e1:
            r1 = r0
            r2 = r4
            r3 = r5
            r4 = r9
            r5 = r6
            r6 = r19
            r1.mo4474a(r2, r3, r4, r5, r6)
            eb r1 = r41.mo4565d()
            eb r2 = r41.mo4565d()
            r3 = 4
            r1.f7815c = r3
            r2.f7815c = r3
            r0.mo4470a((p000.C0114eb) r1, (p000.C0114eb) r2)
            r8.mo4556a((p000.C0107dv) r0)
            return
        L_0x04ff:
            r7 = r40
            r5 = r16
            r4 = r18
            r9 = r35
            r6 = r36
            r1 = r0
            r2 = r9
            r3 = r6
            r6 = r19
            r1.mo4474a(r2, r3, r4, r5, r6)
            r8.mo4556a((p000.C0107dv) r0)
            return
        L_0x0515:
            r7 = r40
            return
        L_0x0518:
            r7 = r15
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0116ed.mo4695a(dy):void");
    }

    /* renamed from: a */
    private final void m7224a(C0110dy dyVar, boolean z, boolean z2, C0115ec ecVar, C0115ec ecVar2, int i, int i2, int i3, int i4, float f, boolean z3, boolean z4, int i5, int i6, int i7) {
        C0110dy dyVar2 = dyVar;
        C0115ec ecVar3 = ecVar;
        C0115ec ecVar4 = ecVar2;
        int i8 = i;
        int i9 = i2;
        int i10 = i4;
        int i11 = i6;
        int i12 = i7;
        C0114eb a = dyVar2.mo4554a((Object) ecVar3);
        C0114eb a2 = dyVar2.mo4554a((Object) ecVar4);
        C0114eb a3 = dyVar2.mo4554a((Object) ecVar3.f7903b);
        C0114eb a4 = dyVar2.mo4554a((Object) ecVar4.f7903b);
        int a5 = ecVar.mo4667a();
        int a6 = ecVar2.mo4667a();
        int i13 = this.f7961H;
        boolean z5 = true;
        boolean z6 = i13 == 8;
        int i14 = i13 != 8 ? i3 : 0;
        boolean z7 = z6 | z2;
        if (a3 == null) {
            if (a4 == null) {
                C0107dv b = dyVar.mo4561b();
                b.mo4469a(a, i8);
                dyVar2.mo4556a(b);
                if (z3) {
                    return;
                }
                if (z) {
                    dyVar2.mo4556a(C0110dy.m6886a(dyVar2, a2, a, i10, true));
                    return;
                } else if (!z7) {
                    C0107dv b2 = dyVar.mo4561b();
                    b2.mo4469a(a2, i9);
                    dyVar2.mo4556a(b2);
                    return;
                } else {
                    dyVar2.mo4556a(C0110dy.m6886a(dyVar2, a2, a, i14, false));
                    return;
                }
            }
        }
        if (a3 != null && a4 == null) {
            C0107dv b3 = dyVar.mo4561b();
            b3.mo4471a(a, a3, a5);
            dyVar2.mo4556a(b3);
            if (z) {
                dyVar2.mo4556a(C0110dy.m6886a(dyVar2, a2, a, i10, true));
            } else if (z3) {
            } else {
                if (!z7) {
                    C0107dv b4 = dyVar.mo4561b();
                    b4.mo4469a(a2, i9);
                    dyVar2.mo4556a(b4);
                    return;
                }
                C0107dv b5 = dyVar.mo4561b();
                b5.mo4471a(a2, a, i14);
                dyVar2.mo4556a(b5);
            }
        } else if (a3 == null) {
            C0107dv b6 = dyVar.mo4561b();
            b6.mo4471a(a2, a4, -a6);
            dyVar2.mo4556a(b6);
            if (z) {
                dyVar2.mo4556a(C0110dy.m6886a(dyVar2, a2, a, i10, true));
            } else if (z3) {
            } else {
                if (!z7) {
                    C0107dv b7 = dyVar.mo4561b();
                    b7.mo4469a(a, i8);
                    dyVar2.mo4556a(b7);
                    return;
                }
                C0107dv b8 = dyVar.mo4561b();
                b8.mo4471a(a2, a, i14);
                dyVar2.mo4556a(b8);
            }
        } else if (z7) {
            if (!z) {
                C0107dv b9 = dyVar.mo4561b();
                b9.mo4471a(a2, a, i14);
                dyVar2.mo4556a(b9);
            } else {
                dyVar2.mo4556a(C0110dy.m6886a(dyVar2, a2, a, i10, true));
            }
            int i15 = ecVar3.f7908g;
            C0115ec ecVar5 = ecVar2;
            if (i15 != ecVar5.f7908g) {
                if (i15 == 2) {
                    C0107dv b10 = dyVar.mo4561b();
                    b10.mo4471a(a, a3, a5);
                    dyVar2.mo4556a(b10);
                    C0114eb c = dyVar.mo4563c();
                    C0107dv b11 = dyVar.mo4561b();
                    b11.mo4475b(a2, a4, c, -a6);
                    dyVar2.mo4556a(b11);
                    return;
                }
                C0114eb c2 = dyVar.mo4563c();
                C0107dv b12 = dyVar.mo4561b();
                b12.mo4473a(a, a3, c2, a5);
                dyVar2.mo4556a(b12);
                C0107dv b13 = dyVar.mo4561b();
                b13.mo4471a(a2, a4, -a6);
                dyVar2.mo4556a(b13);
            } else if (a3 == a4) {
                dyVar2.mo4556a(C0110dy.m6885a(dyVar, a, a3, 0, 0.5f, a4, a2, 0, true));
            } else if (!z4) {
                dyVar2.mo4556a(C0110dy.m6891b(dyVar2, a, a3, a5, ecVar3.f7909h != 2));
                if (ecVar5.f7909h == 2) {
                    z5 = false;
                }
                dyVar2.mo4556a(C0110dy.m6892c(dyVar2, a2, a4, -a6, z5));
                dyVar2.mo4556a(C0110dy.m6885a(dyVar, a, a3, a5, f, a4, a2, a6, false));
            }
        } else if (z3) {
            dyVar2.mo4560a(a, a3, a5, 3);
            dyVar2.mo4562b(a2, a4, -a6, 3);
            dyVar2.mo4556a(C0110dy.m6885a(dyVar, a, a3, a5, f, a4, a2, a6, true));
        } else if (z4) {
        } else {
            if (i5 == 1) {
                if (i11 <= i14) {
                    i11 = i14;
                }
                if (i12 > 0) {
                    if (i12 >= i11) {
                        dyVar2.mo4562b(a2, a, i12, 3);
                    } else {
                        i11 = i12;
                    }
                }
                dyVar2.mo4564c(a2, a, i11, 3);
                dyVar2.mo4560a(a, a3, a5, 2);
                dyVar2.mo4562b(a2, a4, -a6, 2);
                dyVar.mo4559a(a, a3, a5, f, a4, a2, a6);
            } else if (i11 == 0 && i12 == 0) {
                C0107dv b14 = dyVar.mo4561b();
                b14.mo4471a(a, a3, a5);
                dyVar2.mo4556a(b14);
                C0107dv b15 = dyVar.mo4561b();
                b15.mo4471a(a2, a4, -a6);
                dyVar2.mo4556a(b15);
            } else {
                if (i12 > 0) {
                    dyVar2.mo4562b(a2, a, i12, 3);
                }
                dyVar2.mo4560a(a, a3, a5, 2);
                dyVar2.mo4562b(a2, a4, -a6, 2);
                dyVar.mo4559a(a, a3, a5, f, a4, a2, a6);
            }
        }
    }

    /* renamed from: e */
    public final int mo4704e() {
        int i = this.f8003q;
        if (this.f7982ab == 3) {
            if (this.f7990d == 1) {
                i = Math.max(this.f7993g, i);
            } else {
                i = this.f7993g;
                if (i <= 0) {
                    i = 0;
                } else {
                    this.f8003q = i;
                }
            }
            int i2 = this.f7994h;
            if (i2 > 0 && i2 < i) {
                return i2;
            }
        }
        return i;
    }

    /* renamed from: d */
    public final int mo4702d() {
        int i = this.f8002p;
        if (this.f7981aa == 3) {
            if (this.f7989c == 1) {
                i = Math.max(this.f7991e, i);
            } else {
                i = this.f7991e;
                if (i <= 0) {
                    i = 0;
                } else {
                    this.f8002p = i;
                }
            }
            int i2 = this.f7992f;
            if (i2 > 0 && i2 < i) {
                return i2;
            }
        }
        return i;
    }

    /* renamed from: a */
    public final void mo4693a(int i, C0116ed edVar, int i2, int i3, int i4) {
        mo4705e(i).mo4668a(edVar.mo4705e(i2), i3, i4, 2, 0, true);
    }

    /* renamed from: a */
    public void mo4690a() {
        this.f7995i.mo4669b();
        this.f7996j.mo4669b();
        this.f7997k.mo4669b();
        this.f7998l.mo4669b();
        this.f7999m.mo4669b();
        this.f7983ac.mo4669b();
        this.f7984ad.mo4669b();
        this.f7985ae.mo4669b();
        this.f8001o = null;
        this.f8002p = 0;
        this.f8003q = 0;
        this.f8004r = 0.0f;
        this.f8005s = -1;
        this.f8006t = 0;
        this.f8007u = 0;
        this.f7986af = 0;
        this.f7987ag = 0;
        this.f8008v = 0;
        this.f8009w = 0;
        this.f8010x = 0;
        this.f8011y = 0;
        this.f8012z = 0;
        this.f7954A = 0;
        this.f7955B = 0;
        this.f7956C = 0;
        this.f7957D = 0;
        this.f7958E = 0.5f;
        this.f7959F = 0.5f;
        this.f7981aa = 1;
        this.f7982ab = 1;
        this.f7960G = null;
        this.f7961H = 0;
        this.f7970Q = false;
        this.f7971R = false;
        this.f7972S = 0;
        this.f7973T = 0;
        this.f7974U = false;
        this.f7975V = false;
        this.f7976W = 0.0f;
        this.f7977X = 0.0f;
        this.f7980a = -1;
        this.f7988b = -1;
    }

    /* renamed from: a */
    public void mo4694a(C0108dw dwVar) {
        this.f7995i.mo4671d();
        this.f7996j.mo4671d();
        this.f7997k.mo4671d();
        this.f7998l.mo4671d();
        this.f7999m.mo4671d();
        this.f7985ae.mo4671d();
        this.f7983ac.mo4671d();
        this.f7984ad.mo4671d();
    }

    /* renamed from: b */
    public final void mo4696b(int i) {
        this.f8003q = i;
        int i2 = this.f7955B;
        if (i < i2) {
            this.f8003q = i2;
        }
    }

    /* renamed from: b */
    public final void mo4697b(int i, int i2) {
        this.f8006t = i;
        int i3 = i2 - i;
        this.f8002p = i3;
        int i4 = this.f7954A;
        if (i3 < i4) {
            this.f8002p = i4;
        }
    }

    /* renamed from: f */
    public final void mo4707f(int i) {
        this.f7981aa = i;
        if (i == 2) {
            mo4691a(this.f7956C);
        }
    }

    /* renamed from: d */
    public final void mo4703d(int i) {
        if (i < 0) {
            this.f7955B = 0;
        } else {
            this.f7955B = i;
        }
    }

    /* renamed from: c */
    public final void mo4700c(int i) {
        if (i < 0) {
            this.f7954A = 0;
        } else {
            this.f7954A = i;
        }
    }

    /* renamed from: a */
    public void mo4692a(int i, int i2) {
        this.f8010x = i;
        this.f8011y = i2;
    }

    /* renamed from: c */
    public final void mo4701c(int i, int i2) {
        this.f8007u = i;
        int i3 = i2 - i;
        this.f8003q = i3;
        int i4 = this.f7955B;
        if (i3 < i4) {
            this.f8003q = i4;
        }
    }

    /* renamed from: g */
    public final void mo4709g(int i) {
        this.f7982ab = i;
        if (i == 2) {
            mo4696b(this.f7957D);
        }
    }

    /* renamed from: a */
    public final void mo4691a(int i) {
        this.f8002p = i;
        int i2 = this.f7954A;
        if (i < i2) {
            this.f8002p = i2;
        }
    }

    public final String toString() {
        return "(" + this.f8006t + ", " + this.f8007u + ") - (" + this.f8002p + " x " + this.f8003q + ") wrap: (" + this.f7956C + " x " + this.f7957D + ")";
    }

    /* renamed from: l */
    public void mo4714l() {
        int i = this.f8006t;
        int i2 = this.f8007u;
        int i3 = this.f8002p;
        int i4 = this.f8003q;
        this.f7986af = i;
        this.f7987ag = i2;
        this.f8008v = (i3 + i) - i;
        this.f8009w = (i4 + i2) - i2;
    }

    /* renamed from: m */
    public void mo4715m() {
        int i;
        int i2;
        int b = C0110dy.m6890b(this.f7995i);
        int b2 = C0110dy.m6890b(this.f7996j);
        int b3 = C0110dy.m6890b(this.f7997k) - b;
        int b4 = C0110dy.m6890b(this.f7998l) - b2;
        this.f8006t = b;
        this.f8007u = b2;
        if (this.f7961H == 8) {
            this.f8002p = 0;
            this.f8003q = 0;
            return;
        }
        if (this.f7981aa == 1 && b3 < (i2 = this.f8002p)) {
            b3 = i2;
        }
        if (this.f7982ab == 1 && b4 < (i = this.f8003q)) {
            b4 = i;
        }
        this.f8002p = b3;
        this.f8003q = b4;
        int i3 = this.f7955B;
        if (b4 < i3) {
            this.f8003q = i3;
        }
        int i4 = this.f7954A;
        if (b3 < i4) {
            this.f8002p = i4;
        }
    }
}
