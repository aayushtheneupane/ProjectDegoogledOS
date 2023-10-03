package com.android.messaging.mmslib.p039a;

import android.content.ContentResolver;
import android.content.Context;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import p000a.p005b.C0027n;

/* renamed from: com.android.messaging.mmslib.a.o */
public class C0985o {

    /* renamed from: kD */
    private static C0027n f1413kD = new C0027n();
    /* access modifiers changed from: private */

    /* renamed from: iD */
    public C0982l f1414iD = null;

    /* renamed from: jD */
    private C0987q f1415jD = null;
    protected ByteArrayOutputStream mMessage = null;
    private C0976f mPdu = null;
    protected int mPosition = 0;
    private final ContentResolver mResolver;

    static {
        int i = 0;
        while (true) {
            String[] strArr = C0986p.f1416em;
            if (i < strArr.length) {
                f1413kD.put(strArr[i], Integer.valueOf(i));
                i++;
            } else {
                return;
            }
        }
    }

    public C0985o(Context context, C0976f fVar) {
        this.mPdu = fVar;
        this.mResolver = context.getContentResolver();
        this.f1415jD = fVar.f1404Zl;
        this.f1414iD = new C0982l(this, (C0981k) null);
        this.mMessage = new ByteArrayOutputStream();
        this.mPosition = 0;
    }

    /* renamed from: Fb */
    private int m2235Fb(int i) {
        switch (i) {
            case 129:
            case 130:
            case 151:
                C0975e[] D = this.f1415jD.mo6711D(i);
                if (D != null) {
                    for (C0975e e : D) {
                        C0975e e2 = m2237e(e);
                        if (e2 == null) {
                            return 1;
                        }
                        this.mMessage.write(i);
                        this.mPosition++;
                        mo6701d(e2);
                    }
                    break;
                } else {
                    return 2;
                }
            case 131:
            case 139:
            case 152:
                byte[] G = this.f1415jD.mo6714G(i);
                if (G != null) {
                    this.mMessage.write(i);
                    this.mPosition++;
                    mo6703k(G);
                    break;
                } else {
                    return 2;
                }
            case 133:
                long E = this.f1415jD.mo6712E(i);
                if (-1 != E) {
                    this.mMessage.write(i);
                    this.mPosition++;
                    mo6705v(E);
                    break;
                } else {
                    return 2;
                }
            case 134:
            case 143:
            case 144:
            case 145:
            case 149:
            case 155:
                int F = this.f1415jD.mo6713F(i);
                if (F != 0) {
                    this.mMessage.write(i);
                    this.mPosition++;
                    mo6708za(F);
                    break;
                } else {
                    return 2;
                }
            case 136:
            case 142:
                long E2 = this.f1415jD.mo6712E(i);
                if (-1 != E2) {
                    this.mMessage.write(i);
                    this.mPosition++;
                    this.f1414iD.mo6693ci();
                    C0984n mark = this.f1414iD.mark();
                    append(129);
                    mo6705v(E2);
                    int length = mark.getLength();
                    this.f1414iD.pop();
                    mo6707x((long) length);
                    this.f1414iD.copy();
                    break;
                } else {
                    return 2;
                }
            case 137:
                this.mMessage.write(i);
                this.mPosition++;
                C0975e C = this.f1415jD.mo6710C(i);
                if (C != null && !TextUtils.isEmpty(C.getString()) && !new String(C.mo6666fc()).equals("insert-address-token")) {
                    this.f1414iD.mo6693ci();
                    C0984n mark2 = this.f1414iD.mark();
                    append(128);
                    C0975e e3 = m2237e(C);
                    if (e3 != null) {
                        mo6701d(e3);
                        int length2 = mark2.getLength();
                        this.f1414iD.pop();
                        mo6707x((long) length2);
                        this.f1414iD.copy();
                        break;
                    } else {
                        return 1;
                    }
                } else {
                    append(1);
                    append(129);
                    break;
                }
                break;
            case 138:
                byte[] G2 = this.f1415jD.mo6714G(i);
                if (G2 != null) {
                    this.mMessage.write(i);
                    this.mPosition++;
                    if (!Arrays.equals(G2, "advertisement".getBytes())) {
                        if (!Arrays.equals(G2, "auto".getBytes())) {
                            if (!Arrays.equals(G2, "personal".getBytes())) {
                                if (!Arrays.equals(G2, "informational".getBytes())) {
                                    mo6703k(G2);
                                    break;
                                } else {
                                    mo6708za(130);
                                    break;
                                }
                            } else {
                                mo6708za(128);
                                break;
                            }
                        } else {
                            mo6708za(131);
                            break;
                        }
                    } else {
                        mo6708za(129);
                        break;
                    }
                } else {
                    return 2;
                }
            case 141:
                this.mMessage.write(i);
                this.mPosition++;
                int F2 = this.f1415jD.mo6713F(i);
                if (F2 != 0) {
                    mo6698Aa(F2);
                    break;
                } else {
                    mo6698Aa(18);
                    break;
                }
            case 150:
                C0975e C2 = this.f1415jD.mo6710C(i);
                if (C2 != null) {
                    this.mMessage.write(i);
                    this.mPosition++;
                    mo6701d(C2);
                    break;
                } else {
                    return 2;
                }
            default:
                return 3;
        }
        return 0;
    }

    /* renamed from: e */
    private C0975e m2237e(C0975e eVar) {
        try {
            String string = eVar.getString();
            char c = 5;
            if (string != null) {
                if (string.matches("[0-9]{1,3}\\.{1}[0-9]{1,3}\\.{1}[0-9]{1,3}\\.{1}[0-9]{1,3}")) {
                    c = 3;
                } else if (string.matches("\\+?[0-9|\\.|\\-]+")) {
                    c = 1;
                } else if (string.matches("[a-zA-Z| ]*\\<{0,1}[a-zA-Z| ]+@{1}[a-zA-Z| ]+\\.{1}[a-zA-Z| ]+\\>{0,1}")) {
                    c = 2;
                } else if (string.matches("[a-fA-F]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}")) {
                    c = 4;
                }
            }
            C0975e a = C0975e.m2210a(eVar);
            if (1 == c) {
                a.mo6668k("/TYPE=PLMN".getBytes());
            } else if (3 == c) {
                a.mo6668k("/TYPE=IPV4".getBytes());
            } else if (4 == c) {
                a.mo6668k("/TYPE=IPV6".getBytes());
            }
            return a;
        } catch (NullPointerException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: Aa */
    public void mo6698Aa(int i) {
        append((i | 128) & 255);
    }

    /* access modifiers changed from: protected */
    public void append(int i) {
        this.mMessage.write(i);
        this.mPosition++;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo6701d(C0975e eVar) {
        int Zh = eVar.mo6663Zh();
        byte[] fc = eVar.mo6666fc();
        if (fc != null) {
            this.f1414iD.mo6693ci();
            C0984n mark = this.f1414iD.mark();
            mo6698Aa(Zh);
            mo6703k(fc);
            int length = mark.getLength();
            this.f1414iD.pop();
            mo6707x((long) length);
            this.f1414iD.copy();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:170:0x035d, code lost:
        if (r7 != null) goto L_0x0367;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0361, code lost:
        if (r7 != null) goto L_0x0367;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x0365, code lost:
        if (r7 != null) goto L_0x0367;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a1, code lost:
        if (m2235Fb(131) != 0) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e1, code lost:
        if (m2235Fb(155) != 0) goto L_0x00e3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0358 A[SYNTHETIC, Splitter:B:164:0x0358] */
    /* renamed from: di */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] mo6702di() {
        /*
            r15 = this;
            com.android.messaging.mmslib.a.f r0 = r15.mPdu
            int r0 = r0.getMessageType()
            r1 = 150(0x96, float:2.1E-43)
            r2 = 151(0x97, float:2.12E-43)
            r3 = 128(0x80, float:1.794E-43)
            r4 = 137(0x89, float:1.92E-43)
            r5 = 130(0x82, float:1.82E-43)
            r6 = 152(0x98, float:2.13E-43)
            r7 = 141(0x8d, float:1.98E-43)
            r8 = 140(0x8c, float:1.96E-43)
            r9 = 0
            r10 = 133(0x85, float:1.86E-43)
            r11 = 1
            r12 = 0
            if (r0 == r3) goto L_0x0111
            if (r0 == r10) goto L_0x00e7
            r3 = 135(0x87, float:1.89E-43)
            if (r0 == r3) goto L_0x00a7
            r2 = 131(0x83, float:1.84E-43)
            if (r0 == r5) goto L_0x0057
            if (r0 == r2) goto L_0x002a
            return r12
        L_0x002a:
            java.io.ByteArrayOutputStream r0 = r15.mMessage
            if (r0 != 0) goto L_0x0037
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r15.mMessage = r0
            r15.mPosition = r9
        L_0x0037:
            r15.mo6708za(r8)
            r15.mo6708za(r2)
            int r0 = r15.m2235Fb(r6)
            if (r0 == 0) goto L_0x0044
            goto L_0x0053
        L_0x0044:
            int r0 = r15.m2235Fb(r7)
            if (r0 == 0) goto L_0x004b
            goto L_0x0053
        L_0x004b:
            r0 = 149(0x95, float:2.09E-43)
            int r0 = r15.m2235Fb(r0)
            if (r0 == 0) goto L_0x0054
        L_0x0053:
            r9 = r11
        L_0x0054:
            if (r9 == 0) goto L_0x037f
            return r12
        L_0x0057:
            java.io.ByteArrayOutputStream r0 = r15.mMessage
            if (r0 != 0) goto L_0x0064
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r15.mMessage = r0
            r15.mPosition = r9
        L_0x0064:
            r15.mo6708za(r8)
            r15.mo6708za(r5)
            int r0 = r15.m2235Fb(r6)
            if (r0 == 0) goto L_0x0071
            goto L_0x00a3
        L_0x0071:
            int r0 = r15.m2235Fb(r7)
            if (r0 == 0) goto L_0x0078
            goto L_0x00a3
        L_0x0078:
            int r0 = r15.m2235Fb(r4)
            if (r0 == 0) goto L_0x007f
            goto L_0x00a3
        L_0x007f:
            r15.m2235Fb(r1)
            r0 = 138(0x8a, float:1.93E-43)
            int r0 = r15.m2235Fb(r0)
            if (r0 == 0) goto L_0x008b
            goto L_0x00a3
        L_0x008b:
            r0 = 142(0x8e, float:1.99E-43)
            int r0 = r15.m2235Fb(r0)
            if (r0 == 0) goto L_0x0094
            goto L_0x00a3
        L_0x0094:
            r0 = 136(0x88, float:1.9E-43)
            int r0 = r15.m2235Fb(r0)
            if (r0 == 0) goto L_0x009d
            goto L_0x00a3
        L_0x009d:
            int r0 = r15.m2235Fb(r2)
            if (r0 == 0) goto L_0x00a4
        L_0x00a3:
            r9 = r11
        L_0x00a4:
            if (r9 == 0) goto L_0x037f
            return r12
        L_0x00a7:
            java.io.ByteArrayOutputStream r0 = r15.mMessage
            if (r0 != 0) goto L_0x00b4
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r15.mMessage = r0
            r15.mPosition = r9
        L_0x00b4:
            r15.mo6708za(r8)
            r15.mo6708za(r3)
            int r0 = r15.m2235Fb(r7)
            if (r0 == 0) goto L_0x00c1
            goto L_0x00e3
        L_0x00c1:
            r0 = 139(0x8b, float:1.95E-43)
            int r0 = r15.m2235Fb(r0)
            if (r0 == 0) goto L_0x00ca
            goto L_0x00e3
        L_0x00ca:
            int r0 = r15.m2235Fb(r2)
            if (r0 == 0) goto L_0x00d1
            goto L_0x00e3
        L_0x00d1:
            int r0 = r15.m2235Fb(r4)
            if (r0 == 0) goto L_0x00d8
            goto L_0x00e3
        L_0x00d8:
            r15.m2235Fb(r10)
            r0 = 155(0x9b, float:2.17E-43)
            int r0 = r15.m2235Fb(r0)
            if (r0 == 0) goto L_0x00e4
        L_0x00e3:
            r9 = r11
        L_0x00e4:
            if (r9 == 0) goto L_0x037f
            return r12
        L_0x00e7:
            java.io.ByteArrayOutputStream r0 = r15.mMessage
            if (r0 != 0) goto L_0x00f4
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r15.mMessage = r0
            r15.mPosition = r9
        L_0x00f4:
            r15.mo6708za(r8)
            r15.mo6708za(r10)
            int r0 = r15.m2235Fb(r6)
            if (r0 == 0) goto L_0x0101
            goto L_0x0107
        L_0x0101:
            int r0 = r15.m2235Fb(r7)
            if (r0 == 0) goto L_0x0109
        L_0x0107:
            r9 = r11
            goto L_0x010e
        L_0x0109:
            r0 = 145(0x91, float:2.03E-43)
            r15.m2235Fb(r0)
        L_0x010e:
            if (r9 == 0) goto L_0x037f
            return r12
        L_0x0111:
            java.io.ByteArrayOutputStream r0 = r15.mMessage
            if (r0 != 0) goto L_0x011e
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r15.mMessage = r0
            r15.mPosition = r9
        L_0x011e:
            r15.mo6708za(r8)
            r15.mo6708za(r3)
            r15.mo6708za(r6)
            com.android.messaging.mmslib.a.q r0 = r15.f1415jD
            byte[] r0 = r0.mo6714G(r6)
            if (r0 == 0) goto L_0x0386
            r15.mo6703k(r0)
            int r0 = r15.m2235Fb(r7)
            if (r0 == 0) goto L_0x013a
            goto L_0x037b
        L_0x013a:
            r15.m2235Fb(r10)
            int r0 = r15.m2235Fb(r4)
            if (r0 == 0) goto L_0x0145
            goto L_0x037b
        L_0x0145:
            int r0 = r15.m2235Fb(r2)
            if (r0 == r11) goto L_0x014d
            r0 = r11
            goto L_0x014e
        L_0x014d:
            r0 = r9
        L_0x014e:
            int r2 = r15.m2235Fb(r5)
            if (r2 == r11) goto L_0x0155
            r0 = r11
        L_0x0155:
            r2 = 129(0x81, float:1.81E-43)
            int r3 = r15.m2235Fb(r2)
            if (r3 == r11) goto L_0x015e
            r0 = r11
        L_0x015e:
            if (r0 != 0) goto L_0x0162
            goto L_0x037b
        L_0x0162:
            r15.m2235Fb(r1)
            r0 = 138(0x8a, float:1.93E-43)
            r15.m2235Fb(r0)
            r1 = 136(0x88, float:1.9E-43)
            r15.m2235Fb(r1)
            r1 = 143(0x8f, float:2.0E-43)
            r15.m2235Fb(r1)
            r1 = 134(0x86, float:1.88E-43)
            r15.m2235Fb(r1)
            r1 = 144(0x90, float:2.02E-43)
            r15.m2235Fb(r1)
            r1 = 132(0x84, float:1.85E-43)
            r15.mo6708za(r1)
            java.lang.String r3 = ">"
            java.lang.String r5 = "<"
            com.android.messaging.mmslib.a.l r6 = r15.f1414iD
            r6.mo6693ci()
            com.android.messaging.mmslib.a.l r6 = r15.f1414iD
            com.android.messaging.mmslib.a.n r6 = r6.mark()
            java.lang.String r7 = new java.lang.String
            com.android.messaging.mmslib.a.q r8 = r15.f1415jD
            byte[] r1 = r8.mo6714G(r1)
            r7.<init>(r1)
            a.b.n r1 = f1413kD
            java.lang.Object r1 = r1.get(r7)
            java.lang.Integer r1 = (java.lang.Integer) r1
            if (r1 != 0) goto L_0x01a9
            goto L_0x037b
        L_0x01a9:
            int r1 = r1.intValue()
            r15.mo6698Aa(r1)
            com.android.messaging.mmslib.a.f r1 = r15.mPdu
            com.android.messaging.mmslib.a.y r1 = (com.android.messaging.mmslib.p039a.C0995y) r1
            com.android.messaging.mmslib.a.j r1 = r1.getBody()
            if (r1 == 0) goto L_0x036b
            int r7 = r1.mo6690bi()
            if (r7 != 0) goto L_0x01c2
            goto L_0x036b
        L_0x01c2:
            r7 = 60
            r8 = 62
            com.android.messaging.mmslib.a.s r12 = r1.getPart(r9)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            byte[] r13 = r12.mo6738ic()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            if (r13 == 0) goto L_0x01ff
            r15.mo6708za(r0)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            byte r0 = r13[r9]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            if (r7 != r0) goto L_0x01e1
            int r0 = r13.length     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            int r0 = r0 - r11
            byte r0 = r13[r0]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            if (r8 != r0) goto L_0x01e1
            r15.mo6703k(r13)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            goto L_0x01ff
        L_0x01e1:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            r0.<init>()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            r0.append(r5)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            java.lang.String r7 = new java.lang.String     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            r7.<init>(r13)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            r0.append(r7)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            r0.append(r3)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            java.lang.String r0 = r0.toString()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            byte[] r0 = r0.getBytes()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            r15.mo6703k(r0)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
        L_0x01ff:
            r15.mo6708za(r4)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            byte[] r0 = r12.getContentType()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            r15.mo6703k(r0)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x020a }
            goto L_0x020e
        L_0x020a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x020e:
            int r0 = r6.getLength()
            com.android.messaging.mmslib.a.l r4 = r15.f1414iD
            r4.pop()
            long r6 = (long) r0
            r15.mo6707x(r6)
            com.android.messaging.mmslib.a.l r0 = r15.f1414iD
            r0.copy()
            int r0 = r1.mo6690bi()
            long r6 = (long) r0
            r15.mo6706w(r6)
            r4 = r2
            r2 = r9
        L_0x022a:
            r6 = 60
            if (r2 >= r0) goto L_0x037a
            com.android.messaging.mmslib.a.s r7 = r1.getPart(r2)
            com.android.messaging.mmslib.a.l r8 = r15.f1414iD
            r8.mo6693ci()
            com.android.messaging.mmslib.a.l r8 = r15.f1414iD
            com.android.messaging.mmslib.a.n r8 = r8.mark()
            com.android.messaging.mmslib.a.l r12 = r15.f1414iD
            r12.mo6693ci()
            com.android.messaging.mmslib.a.l r12 = r15.f1414iD
            com.android.messaging.mmslib.a.n r12 = r12.mark()
            byte[] r13 = r7.getContentType()
            if (r13 != 0) goto L_0x0250
            goto L_0x037b
        L_0x0250:
            a.b.n r11 = f1413kD
            java.lang.String r14 = new java.lang.String
            r14.<init>(r13)
            java.lang.Object r11 = r11.get(r14)
            java.lang.Integer r11 = (java.lang.Integer) r11
            if (r11 != 0) goto L_0x0263
            r15.mo6703k(r13)
            goto L_0x026a
        L_0x0263:
            int r11 = r11.intValue()
            r15.mo6698Aa(r11)
        L_0x026a:
            byte[] r11 = r7.getName()
            if (r11 != 0) goto L_0x0282
            byte[] r11 = r7.mo6741lc()
            if (r11 != 0) goto L_0x0282
            byte[] r11 = r7.mo6739jc()
            if (r11 != 0) goto L_0x0282
            java.lang.String r11 = "smil.xml"
            byte[] r11 = r11.getBytes()
        L_0x0282:
            r15.mo6708za(r10)
            r15.mo6703k(r11)
            int r11 = r7.getCharset()
            if (r11 == 0) goto L_0x0294
            r15.mo6708za(r4)
            r15.mo6698Aa(r11)
        L_0x0294:
            int r4 = r12.getLength()
            com.android.messaging.mmslib.a.l r11 = r15.f1414iD
            r11.pop()
            long r11 = (long) r4
            r15.mo6707x(r11)
            com.android.messaging.mmslib.a.l r4 = r15.f1414iD
            r4.copy()
            byte[] r4 = r7.mo6738ic()
            if (r4 == 0) goto L_0x02de
            r11 = 192(0xc0, float:2.69E-43)
            r15.mo6708za(r11)
            byte r11 = r4[r9]
            if (r6 != r11) goto L_0x02c2
            int r6 = r4.length
            r11 = 1
            int r6 = r6 - r11
            byte r6 = r4[r6]
            r12 = 62
            if (r12 != r6) goto L_0x02c3
            r15.mo6704n(r4)
            goto L_0x02e0
        L_0x02c2:
            r11 = 1
        L_0x02c3:
            java.lang.StringBuilder r6 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r5)
            java.lang.String r12 = new java.lang.String
            r12.<init>(r4)
            r6.append(r12)
            r6.append(r3)
            java.lang.String r4 = r6.toString()
            byte[] r4 = r4.getBytes()
            r15.mo6704n(r4)
            goto L_0x02e0
        L_0x02de:
            r4 = 1
            r11 = r4
        L_0x02e0:
            byte[] r4 = r7.mo6739jc()
            if (r4 == 0) goto L_0x02ee
            r6 = 142(0x8e, float:1.99E-43)
            r15.mo6708za(r6)
            r15.mo6703k(r4)
        L_0x02ee:
            int r4 = r8.getLength()
            byte[] r6 = r7.getData()
            if (r6 == 0) goto L_0x0305
            int r7 = r6.length
            java.io.ByteArrayOutputStream r12 = r15.mMessage
            r12.write(r6, r9, r7)
            int r12 = r15.mPosition
            int r12 = r12 + r7
            r15.mPosition = r12
            int r6 = r6.length
            goto L_0x032b
        L_0x0305:
            r6 = 1024(0x400, float:1.435E-42)
            byte[] r6 = new byte[r6]     // Catch:{ FileNotFoundException -> 0x0364, IOException -> 0x0360, RuntimeException -> 0x035c, all -> 0x0354 }
            android.content.ContentResolver r12 = r15.mResolver     // Catch:{ FileNotFoundException -> 0x0364, IOException -> 0x0360, RuntimeException -> 0x035c, all -> 0x0354 }
            android.net.Uri r7 = r7.getDataUri()     // Catch:{ FileNotFoundException -> 0x0364, IOException -> 0x0360, RuntimeException -> 0x035c, all -> 0x0354 }
            java.io.InputStream r7 = r12.openInputStream(r7)     // Catch:{ FileNotFoundException -> 0x0364, IOException -> 0x0360, RuntimeException -> 0x035c, all -> 0x0354 }
            r12 = r9
        L_0x0314:
            int r13 = r7.read(r6)     // Catch:{ FileNotFoundException -> 0x0365, IOException -> 0x0361, RuntimeException -> 0x035d, all -> 0x0352 }
            r14 = -1
            if (r13 == r14) goto L_0x0327
            java.io.ByteArrayOutputStream r14 = r15.mMessage     // Catch:{ FileNotFoundException -> 0x0365, IOException -> 0x0361, RuntimeException -> 0x035d, all -> 0x0352 }
            r14.write(r6, r9, r13)     // Catch:{ FileNotFoundException -> 0x0365, IOException -> 0x0361, RuntimeException -> 0x035d, all -> 0x0352 }
            int r14 = r15.mPosition     // Catch:{ FileNotFoundException -> 0x0365, IOException -> 0x0361, RuntimeException -> 0x035d, all -> 0x0352 }
            int r14 = r14 + r13
            r15.mPosition = r14     // Catch:{ FileNotFoundException -> 0x0365, IOException -> 0x0361, RuntimeException -> 0x035d, all -> 0x0352 }
            int r12 = r12 + r13
            goto L_0x0314
        L_0x0327:
            r7.close()     // Catch:{ IOException -> 0x032a }
        L_0x032a:
            r6 = r12
        L_0x032b:
            int r7 = r8.getLength()
            int r7 = r7 - r4
            if (r6 != r7) goto L_0x034a
            com.android.messaging.mmslib.a.l r7 = r15.f1414iD
            r7.pop()
            long r7 = (long) r4
            r15.mo6706w(r7)
            long r6 = (long) r6
            r15.mo6706w(r6)
            com.android.messaging.mmslib.a.l r4 = r15.f1414iD
            r4.copy()
            int r2 = r2 + 1
            r4 = 129(0x81, float:1.81E-43)
            goto L_0x022a
        L_0x034a:
            java.lang.RuntimeException r15 = new java.lang.RuntimeException
            java.lang.String r0 = "BUG: Length sanity check failed"
            r15.<init>(r0)
            throw r15
        L_0x0352:
            r15 = move-exception
            goto L_0x0356
        L_0x0354:
            r15 = move-exception
            r7 = 0
        L_0x0356:
            if (r7 == 0) goto L_0x035b
            r7.close()     // Catch:{ IOException -> 0x035b }
        L_0x035b:
            throw r15
        L_0x035c:
            r7 = 0
        L_0x035d:
            if (r7 == 0) goto L_0x037b
            goto L_0x0367
        L_0x0360:
            r7 = 0
        L_0x0361:
            if (r7 == 0) goto L_0x037b
            goto L_0x0367
        L_0x0364:
            r7 = 0
        L_0x0365:
            if (r7 == 0) goto L_0x037b
        L_0x0367:
            r7.close()     // Catch:{ IOException -> 0x037b }
            goto L_0x037b
        L_0x036b:
            r0 = 0
            r15.mo6706w(r0)
            com.android.messaging.mmslib.a.l r0 = r15.f1414iD
            r0.pop()
            com.android.messaging.mmslib.a.l r0 = r15.f1414iD
            r0.copy()
        L_0x037a:
            r11 = r9
        L_0x037b:
            if (r11 == 0) goto L_0x037f
            r15 = 0
            return r15
        L_0x037f:
            java.io.ByteArrayOutputStream r15 = r15.mMessage
            byte[] r15 = r15.toByteArray()
            return r15
        L_0x0386:
            java.lang.IllegalArgumentException r15 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Transaction-ID is null."
            r15.<init>(r0)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.mmslib.p039a.C0985o.mo6702di():byte[]");
    }

    /* access modifiers changed from: protected */
    /* renamed from: k */
    public void mo6703k(byte[] bArr) {
        if ((bArr[0] & 255) > Byte.MAX_VALUE) {
            append(127);
        }
        int length = bArr.length;
        this.mMessage.write(bArr, 0, length);
        this.mPosition += length;
        append(0);
    }

    /* access modifiers changed from: protected */
    /* renamed from: n */
    public void mo6704n(byte[] bArr) {
        append(34);
        int length = bArr.length;
        this.mMessage.write(bArr, 0, length);
        this.mPosition += length;
        append(0);
    }

    /* access modifiers changed from: protected */
    /* renamed from: v */
    public void mo6705v(long j) {
        long j2 = j;
        int i = 0;
        while (j2 != 0 && i < 8) {
            j2 >>>= 8;
            i++;
        }
        this.mMessage.write(i);
        this.mPosition++;
        int i2 = (i - 1) * 8;
        for (int i3 = 0; i3 < i; i3++) {
            append((int) ((j >>> i2) & 255));
            i2 -= 8;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: w */
    public void mo6706w(long j) {
        int i = 0;
        long j2 = 127;
        while (i < 5 && j >= j2) {
            j2 = (j2 << 7) | 127;
            i++;
        }
        while (i > 0) {
            append((int) ((((j >>> (i * 7)) & 127) | 128) & 255));
            i--;
        }
        append((int) (j & 127));
    }

    /* access modifiers changed from: protected */
    /* renamed from: x */
    public void mo6707x(long j) {
        if (j < 31) {
            this.mMessage.write((int) j);
            this.mPosition++;
            return;
        }
        append(31);
        mo6706w(j);
    }

    /* access modifiers changed from: protected */
    /* renamed from: za */
    public void mo6708za(int i) {
        this.mMessage.write(i);
        this.mPosition++;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo6699a(byte[] bArr, int i, int i2) {
        this.mMessage.write(bArr, i, i2);
        this.mPosition += i2;
    }
}
