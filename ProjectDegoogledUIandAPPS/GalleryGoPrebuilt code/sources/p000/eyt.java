package p000;

import android.content.Context;
import com.google.android.gms.common.api.internal.BasePendingResult;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;

@Deprecated
/* renamed from: eyt */
/* compiled from: PG */
public final class eyt implements eym, ezg {

    /* renamed from: a */
    private final Context f9203a;

    /* renamed from: b */
    private final ekv f9204b;

    /* renamed from: c */
    private final ezb f9205c;

    /* renamed from: d */
    public final Context mo5402d() {
        return this.f9203a;
    }

    /* renamed from: e */
    public final ekv mo5412e() {
        return this.f9204b;
    }

    /* JADX WARNING: type inference failed for: r13v0, types: [ent] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo5397a() {
        /*
            r33 = this;
            r1 = r33
            ekv r15 = r1.f9204b
            r14 = r15
            ena r14 = (p000.ena) r14
            java.util.concurrent.locks.Lock r0 = r14.f8602b
            r0.lock()
            r0 = r15
            ena r0 = (p000.ena) r0     // Catch:{ all -> 0x02e3 }
            int r0 = r0.f8605e     // Catch:{ all -> 0x02e3 }
            r2 = 2
            r3 = 0
            r4 = 1
            if (r0 < 0) goto L_0x0027
            r0 = r15
            ena r0 = (p000.ena) r0     // Catch:{ all -> 0x02e3 }
            java.lang.Integer r0 = r0.f8616p     // Catch:{ all -> 0x02e3 }
            if (r0 == 0) goto L_0x001f
            r0 = 1
            goto L_0x0021
        L_0x001f:
            r0 = 0
        L_0x0021:
            java.lang.String r5 = "Sign-in mode should have been set explicitly by auto-manage."
            p000.abj.m108a((boolean) r0, (java.lang.Object) r5)     // Catch:{ all -> 0x02e3 }
            goto L_0x004b
        L_0x0027:
            r0 = r15
            ena r0 = (p000.ena) r0     // Catch:{ all -> 0x02e3 }
            java.lang.Integer r0 = r0.f8616p     // Catch:{ all -> 0x02e3 }
            if (r0 != 0) goto L_0x0045
            r0 = r15
            ena r0 = (p000.ena) r0     // Catch:{ all -> 0x02e3 }
            java.util.Map r0 = r0.f8610j     // Catch:{ all -> 0x02e3 }
            java.util.Collection r0 = r0.values()     // Catch:{ all -> 0x02e3 }
            int r0 = p000.ena.m7836a((java.lang.Iterable) r0)     // Catch:{ all -> 0x02e3 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x02e3 }
            r5 = r15
            ena r5 = (p000.ena) r5     // Catch:{ all -> 0x02e3 }
            r5.f8616p = r0     // Catch:{ all -> 0x02e3 }
            goto L_0x004b
        L_0x0045:
            int r0 = r0.intValue()     // Catch:{ all -> 0x02e3 }
            if (r0 == r2) goto L_0x02d8
        L_0x004b:
            r0 = r15
            ena r0 = (p000.ena) r0     // Catch:{ all -> 0x02e3 }
            java.lang.Integer r0 = r0.f8616p     // Catch:{ all -> 0x02e3 }
            int r0 = r0.intValue()     // Catch:{ all -> 0x02e3 }
            r5 = r15
            ena r5 = (p000.ena) r5     // Catch:{ all -> 0x02e3 }
            java.util.concurrent.locks.Lock r5 = r5.f8602b     // Catch:{ all -> 0x02e3 }
            r5.lock()     // Catch:{ all -> 0x02e3 }
            r5 = 3
            if (r0 != r5) goto L_0x0061
        L_0x005f:
            r5 = 1
            goto L_0x0069
        L_0x0061:
            if (r0 == r4) goto L_0x005f
            if (r0 != r2) goto L_0x0068
            r5 = 1
            goto L_0x0069
        L_0x0068:
            r5 = 0
        L_0x0069:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x02cd }
            r7 = 33
            r6.<init>(r7)     // Catch:{ all -> 0x02cd }
            java.lang.String r7 = "Illegal sign-in mode: "
            r6.append(r7)     // Catch:{ all -> 0x02cd }
            r6.append(r0)     // Catch:{ all -> 0x02cd }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x02cd }
            p000.abj.m117b((boolean) r5, (java.lang.Object) r6)     // Catch:{ all -> 0x02cd }
            r5 = r15
            ena r5 = (p000.ena) r5     // Catch:{ all -> 0x02cd }
            java.lang.Integer r5 = r5.f8616p     // Catch:{ all -> 0x02cd }
            if (r5 == 0) goto L_0x00c8
            int r5 = r5.intValue()     // Catch:{ all -> 0x02cd }
            if (r5 != r0) goto L_0x008d
            goto L_0x00d1
        L_0x008d:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch:{ all -> 0x02cd }
            java.lang.String r0 = p000.ena.m7837b(r0)     // Catch:{ all -> 0x02cd }
            r3 = r15
            ena r3 = (p000.ena) r3     // Catch:{ all -> 0x02cd }
            java.lang.Integer r3 = r3.f8616p     // Catch:{ all -> 0x02cd }
            int r3 = r3.intValue()     // Catch:{ all -> 0x02cd }
            java.lang.String r3 = p000.ena.m7837b(r3)     // Catch:{ all -> 0x02cd }
            int r4 = r0.length()     // Catch:{ all -> 0x02cd }
            int r4 = r4 + 51
            int r5 = r3.length()     // Catch:{ all -> 0x02cd }
            int r4 = r4 + r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x02cd }
            r5.<init>(r4)     // Catch:{ all -> 0x02cd }
            java.lang.String r4 = "Cannot use sign-in mode: "
            r5.append(r4)     // Catch:{ all -> 0x02cd }
            r5.append(r0)     // Catch:{ all -> 0x02cd }
            java.lang.String r0 = ". Mode was already set to "
            r5.append(r0)     // Catch:{ all -> 0x02cd }
            r5.append(r3)     // Catch:{ all -> 0x02cd }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x02cd }
            r2.<init>(r0)     // Catch:{ all -> 0x02cd }
            throw r2     // Catch:{ all -> 0x02cd }
        L_0x00c8:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x02cd }
            r5 = r15
            ena r5 = (p000.ena) r5     // Catch:{ all -> 0x02cd }
            r5.f8616p = r0     // Catch:{ all -> 0x02cd }
        L_0x00d1:
            r0 = r15
            ena r0 = (p000.ena) r0     // Catch:{ all -> 0x02cd }
            enu r0 = r0.f8604d     // Catch:{ all -> 0x02cd }
            if (r0 != 0) goto L_0x02ac
            r0 = r15
            ena r0 = (p000.ena) r0     // Catch:{ all -> 0x02cd }
            java.util.Map r0 = r0.f8610j     // Catch:{ all -> 0x02cd }
            java.util.Collection r0 = r0.values()     // Catch:{ all -> 0x02cd }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x02cd }
        L_0x00e5:
            boolean r5 = r0.hasNext()     // Catch:{ all -> 0x02cd }
            if (r5 == 0) goto L_0x00fe
            java.lang.Object r5 = r0.next()     // Catch:{ all -> 0x02cd }
            ekm r5 = (p000.ekm) r5     // Catch:{ all -> 0x02cd }
            boolean r6 = r5.mo4934g()     // Catch:{ all -> 0x02cd }
            if (r6 != 0) goto L_0x00f8
            goto L_0x00fa
        L_0x00f8:
            r3 = 1
        L_0x00fa:
            r5.mo4938k()     // Catch:{ all -> 0x02cd }
            goto L_0x00e5
        L_0x00fe:
            r0 = r15
            ena r0 = (p000.ena) r0     // Catch:{ all -> 0x02cd }
            java.lang.Integer r0 = r0.f8616p     // Catch:{ all -> 0x02cd }
            int r0 = r0.intValue()     // Catch:{ all -> 0x02cd }
            if (r0 == r4) goto L_0x0259
            if (r0 == r2) goto L_0x010d
            goto L_0x025b
        L_0x010d:
            if (r3 == 0) goto L_0x025b
            r0 = r15
            ena r0 = (p000.ena) r0     // Catch:{ all -> 0x02cd }
            android.content.Context r0 = r0.f8606f     // Catch:{ all -> 0x02cd }
            r2 = r15
            ena r2 = (p000.ena) r2     // Catch:{ all -> 0x02cd }
            java.util.concurrent.locks.Lock r2 = r2.f8602b     // Catch:{ all -> 0x02cd }
            r3 = r15
            ena r3 = (p000.ena) r3     // Catch:{ all -> 0x02cd }
            android.os.Looper r3 = r3.f8607g     // Catch:{ all -> 0x02cd }
            r5 = r15
            ena r5 = (p000.ena) r5     // Catch:{ all -> 0x02cd }
            ejw r5 = r5.f8609i     // Catch:{ all -> 0x02cd }
            r6 = r15
            ena r6 = (p000.ena) r6     // Catch:{ all -> 0x02cd }
            java.util.Map r6 = r6.f8610j     // Catch:{ all -> 0x02cd }
            r7 = r15
            ena r7 = (p000.ena) r7     // Catch:{ all -> 0x02cd }
            epk r7 = r7.f8612l     // Catch:{ all -> 0x02cd }
            r8 = r15
            ena r8 = (p000.ena) r8     // Catch:{ all -> 0x02cd }
            java.util.Map r8 = r8.f8613m     // Catch:{ all -> 0x02cd }
            r9 = r15
            ena r9 = (p000.ena) r9     // Catch:{ all -> 0x02cd }
            eov r9 = r9.f8619s     // Catch:{ all -> 0x02cd }
            r10 = r15
            ena r10 = (p000.ena) r10     // Catch:{ all -> 0x02cd }
            java.util.ArrayList r10 = r10.f8615o     // Catch:{ all -> 0x02cd }
            kn r11 = new kn     // Catch:{ all -> 0x02cd }
            r11.<init>()     // Catch:{ all -> 0x02cd }
            kn r12 = new kn     // Catch:{ all -> 0x02cd }
            r12.<init>()     // Catch:{ all -> 0x02cd }
            java.util.Set r6 = r6.entrySet()     // Catch:{ all -> 0x02cd }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x02cd }
        L_0x014e:
            boolean r13 = r6.hasNext()     // Catch:{ all -> 0x02cd }
            if (r13 == 0) goto L_0x0181
            java.lang.Object r13 = r6.next()     // Catch:{ all -> 0x02cd }
            java.util.Map$Entry r13 = (java.util.Map.Entry) r13     // Catch:{ all -> 0x02cd }
            java.lang.Object r16 = r13.getValue()     // Catch:{ all -> 0x02cd }
            r4 = r16
            ekm r4 = (p000.ekm) r4     // Catch:{ all -> 0x02cd }
            r4.mo4938k()     // Catch:{ all -> 0x02cd }
            boolean r16 = r4.mo4934g()     // Catch:{ all -> 0x02cd }
            if (r16 != 0) goto L_0x0176
            java.lang.Object r13 = r13.getKey()     // Catch:{ all -> 0x02cd }
            eki r13 = (p000.eki) r13     // Catch:{ all -> 0x02cd }
            r12.put(r13, r4)     // Catch:{ all -> 0x02cd }
            r4 = 1
            goto L_0x014e
        L_0x0176:
            java.lang.Object r13 = r13.getKey()     // Catch:{ all -> 0x02cd }
            eki r13 = (p000.eki) r13     // Catch:{ all -> 0x02cd }
            r11.put(r13, r4)     // Catch:{ all -> 0x02cd }
            r4 = 1
            goto L_0x014e
        L_0x0181:
            boolean r4 = r11.isEmpty()     // Catch:{ all -> 0x02cd }
            r6 = 1
            r4 = r4 ^ r6
            java.lang.String r6 = "CompositeGoogleApiClient should not be used without any APIs that require sign-in."
            p000.abj.m108a((boolean) r4, (java.lang.Object) r6)     // Catch:{ all -> 0x02cd }
            kn r4 = new kn     // Catch:{ all -> 0x02cd }
            r4.<init>()     // Catch:{ all -> 0x02cd }
            kn r6 = new kn     // Catch:{ all -> 0x02cd }
            r6.<init>()     // Catch:{ all -> 0x02cd }
            java.util.Set r13 = r8.keySet()     // Catch:{ all -> 0x02cd }
            java.util.Iterator r13 = r13.iterator()     // Catch:{ all -> 0x02cd }
        L_0x019e:
            boolean r16 = r13.hasNext()     // Catch:{ all -> 0x02cd }
            if (r16 != 0) goto L_0x021b
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x02cd }
            r8.<init>()     // Catch:{ all -> 0x02cd }
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ all -> 0x02cd }
            r13.<init>()     // Catch:{ all -> 0x02cd }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ all -> 0x02cd }
        L_0x01b2:
            boolean r16 = r10.hasNext()     // Catch:{ all -> 0x02cd }
            if (r16 == 0) goto L_0x01ea
            java.lang.Object r16 = r10.next()     // Catch:{ all -> 0x02cd }
            r1 = r16
            ely r1 = (p000.ely) r1     // Catch:{ all -> 0x02cd }
            r16 = r10
            ekn r10 = r1.f8531a     // Catch:{ all -> 0x02cd }
            boolean r10 = r4.containsKey(r10)     // Catch:{ all -> 0x02cd }
            if (r10 != 0) goto L_0x01e2
            ekn r10 = r1.f8531a     // Catch:{ all -> 0x02cd }
            boolean r10 = r6.containsKey(r10)     // Catch:{ all -> 0x02cd }
            if (r10 == 0) goto L_0x01da
            r13.add(r1)     // Catch:{ all -> 0x02cd }
            r1 = r33
            r10 = r16
            goto L_0x01b2
        L_0x01da:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x02cd }
            java.lang.String r1 = "Each ClientCallbacks must have a corresponding API in the isOptionalMap"
            r0.<init>(r1)     // Catch:{ all -> 0x02cd }
            throw r0     // Catch:{ all -> 0x02cd }
        L_0x01e2:
            r8.add(r1)     // Catch:{ all -> 0x02cd }
            r1 = r33
            r10 = r16
            goto L_0x01b2
        L_0x01ea:
            emc r1 = new emc     // Catch:{ all -> 0x02cd }
            r18 = r15
            ena r18 = (p000.ena) r18     // Catch:{ all -> 0x02cd }
            r30 = 0
            r31 = 0
            r16 = r1
            r17 = r0
            r19 = r2
            r20 = r3
            r21 = r5
            r22 = r11
            r23 = r12
            r24 = r7
            r25 = r9
            r26 = r8
            r27 = r13
            r28 = r4
            r29 = r6
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31)     // Catch:{ all -> 0x02cd }
            r0 = r15
            ena r0 = (p000.ena) r0     // Catch:{ all -> 0x02cd }
            r0.f8604d = r1     // Catch:{ all -> 0x02cd }
            r32 = r14
            r1 = r15
            goto L_0x02af
        L_0x021b:
            java.lang.Object r1 = r13.next()     // Catch:{ all -> 0x02cd }
            ekn r1 = (p000.ekn) r1     // Catch:{ all -> 0x02cd }
            r16 = r0
            eki r0 = r1.mo4940a()     // Catch:{ all -> 0x02cd }
            boolean r17 = r11.containsKey(r0)     // Catch:{ all -> 0x02cd }
            if (r17 == 0) goto L_0x023c
            java.lang.Object r0 = r8.get(r1)     // Catch:{ all -> 0x02cd }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x02cd }
            r4.put(r1, r0)     // Catch:{ all -> 0x02cd }
            r1 = r33
            r0 = r16
            goto L_0x019e
        L_0x023c:
            boolean r0 = r12.containsKey(r0)     // Catch:{ all -> 0x02cd }
            if (r0 == 0) goto L_0x0251
            java.lang.Object r0 = r8.get(r1)     // Catch:{ all -> 0x02cd }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x02cd }
            r6.put(r1, r0)     // Catch:{ all -> 0x02cd }
            r1 = r33
            r0 = r16
            goto L_0x019e
        L_0x0251:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x02cd }
            java.lang.String r1 = "Each API in the isOptionalMap must have a corresponding client in the clients map."
            r0.<init>(r1)     // Catch:{ all -> 0x02cd }
            throw r0     // Catch:{ all -> 0x02cd }
        L_0x0259:
            if (r3 == 0) goto L_0x02a1
        L_0x025b:
            ene r0 = new ene     // Catch:{ all -> 0x02cd }
            r1 = r15
            ena r1 = (p000.ena) r1     // Catch:{ all -> 0x02cd }
            android.content.Context r3 = r1.f8606f     // Catch:{ all -> 0x02cd }
            r1 = r15
            ena r1 = (p000.ena) r1     // Catch:{ all -> 0x02cd }
            java.util.concurrent.locks.Lock r5 = r1.f8602b     // Catch:{ all -> 0x02cd }
            r1 = r15
            ena r1 = (p000.ena) r1     // Catch:{ all -> 0x02cd }
            android.os.Looper r6 = r1.f8607g     // Catch:{ all -> 0x02cd }
            r1 = r15
            ena r1 = (p000.ena) r1     // Catch:{ all -> 0x02cd }
            ejw r7 = r1.f8609i     // Catch:{ all -> 0x02cd }
            r1 = r15
            ena r1 = (p000.ena) r1     // Catch:{ all -> 0x02cd }
            java.util.Map r8 = r1.f8610j     // Catch:{ all -> 0x02cd }
            r1 = r15
            ena r1 = (p000.ena) r1     // Catch:{ all -> 0x02cd }
            epk r9 = r1.f8612l     // Catch:{ all -> 0x02cd }
            r1 = r15
            ena r1 = (p000.ena) r1     // Catch:{ all -> 0x02cd }
            java.util.Map r10 = r1.f8613m     // Catch:{ all -> 0x02cd }
            r1 = r15
            ena r1 = (p000.ena) r1     // Catch:{ all -> 0x02cd }
            eov r11 = r1.f8619s     // Catch:{ all -> 0x02cd }
            r1 = r15
            ena r1 = (p000.ena) r1     // Catch:{ all -> 0x02cd }
            java.util.ArrayList r12 = r1.f8615o     // Catch:{ all -> 0x02cd }
            r4 = r15
            ena r4 = (p000.ena) r4     // Catch:{ all -> 0x02cd }
            r1 = 0
            r16 = 0
            r2 = r0
            r13 = r15
            r32 = r14
            r14 = r1
            r1 = r15
            r15 = r16
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ all -> 0x02c9 }
            r15 = r1
            ena r15 = (p000.ena) r15     // Catch:{ all -> 0x02c9 }
            r15.f8604d = r0     // Catch:{ all -> 0x02c9 }
            goto L_0x02af
        L_0x02a1:
            r32 = r14
            r1 = r15
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x02c9 }
            java.lang.String r2 = "SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead."
            r0.<init>(r2)     // Catch:{ all -> 0x02c9 }
            throw r0     // Catch:{ all -> 0x02c9 }
        L_0x02ac:
            r32 = r14
            r1 = r15
        L_0x02af:
            r15 = r1
            ena r15 = (p000.ena) r15     // Catch:{ all -> 0x02c9 }
            r15.mo5031d()     // Catch:{ all -> 0x02c9 }
            r15 = r1
            ena r15 = (p000.ena) r15     // Catch:{ all -> 0x02c5 }
            java.util.concurrent.locks.Lock r0 = r15.f8602b     // Catch:{ all -> 0x02c5 }
            r0.unlock()     // Catch:{ all -> 0x02c5 }
            r15 = r32
            java.util.concurrent.locks.Lock r0 = r15.f8602b
            r0.unlock()
            return
        L_0x02c5:
            r0 = move-exception
            r15 = r32
            goto L_0x02e5
        L_0x02c9:
            r0 = move-exception
            r15 = r32
            goto L_0x02d0
        L_0x02cd:
            r0 = move-exception
            r1 = r15
            r15 = r14
        L_0x02d0:
            ena r1 = (p000.ena) r1     // Catch:{ all -> 0x02e1 }
            java.util.concurrent.locks.Lock r1 = r1.f8602b     // Catch:{ all -> 0x02e1 }
            r1.unlock()     // Catch:{ all -> 0x02e1 }
            throw r0     // Catch:{ all -> 0x02e1 }
        L_0x02d8:
            r15 = r14
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x02e1 }
            java.lang.String r1 = "Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead."
            r0.<init>(r1)     // Catch:{ all -> 0x02e1 }
            throw r0     // Catch:{ all -> 0x02e1 }
        L_0x02e1:
            r0 = move-exception
            goto L_0x02e5
        L_0x02e3:
            r0 = move-exception
            r15 = r14
        L_0x02e5:
            java.util.concurrent.locks.Lock r1 = r15.f8602b
            r1.unlock()
            goto L_0x02ec
        L_0x02eb:
            throw r0
        L_0x02ec:
            goto L_0x02eb
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.eyt.mo5397a():void");
    }

    /* renamed from: b */
    public final void mo5400b() {
        Lock lock;
        boolean b;
        ekv ekv = this.f9204b;
        ena ena = (ena) ekv;
        ena.f8602b.lock();
        try {
            eot eot = ((ena) ekv).f8618r;
            for (BasePendingResult basePendingResult : (BasePendingResult[]) eot.f8729c.toArray(eot.f8728b)) {
                basePendingResult.mo3509a((eos) null);
                synchronized (basePendingResult.f4984c) {
                    if (((ekv) basePendingResult.f4985d.get()) == null || !basePendingResult.f4987f) {
                        basePendingResult.mo3505a();
                    }
                    b = basePendingResult.mo3511b();
                }
                if (b) {
                    eot.f8729c.remove(basePendingResult);
                }
            }
            enu enu = ((ena) ekv).f8604d;
            if (enu != null) {
                enu.mo5003b();
            }
            eoa eoa = ((ena) ekv).f8614n;
            Iterator it = eoa.f8699a.iterator();
            if (!it.hasNext()) {
                eoa.f8699a.clear();
                for (elq elq : ((ena) ekv).f8608h) {
                    elq.mo3509a((eos) null);
                    elq.mo3505a();
                }
                ((ena) ekv).f8608h.clear();
                if (((ena) ekv).f8604d != null) {
                    ((ena) ekv).mo5033f();
                    ((ena) ekv).f8603c.mo5139a();
                    lock = ena.f8602b;
                } else {
                    lock = ena.f8602b;
                }
                lock.unlock();
                return;
            }
            eod eod = (eod) it.next();
            throw null;
        } catch (Throwable th) {
            ena.f8602b.unlock();
            throw th;
        }
    }

    /* renamed from: c */
    public final boolean mo5401c() {
        return this.f9204b.mo4950c();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: ekt} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo5398a(p000.eyk r5) {
        /*
            r4 = this;
            ekv r0 = r4.f9204b
            ezb r1 = r4.f9205c
            java.lang.Object r2 = r1.f9210a
            monitor-enter(r2)
            java.util.Map r3 = r1.f9211b     // Catch:{ all -> 0x002d }
            boolean r3 = r3.containsKey(r5)     // Catch:{ all -> 0x002d }
            if (r3 != 0) goto L_0x001b
            eyz r3 = new eyz     // Catch:{ all -> 0x002d }
            r3.<init>(r5)     // Catch:{ all -> 0x002d }
            java.util.Map r1 = r1.f9211b     // Catch:{ all -> 0x002d }
            r1.put(r5, r3)     // Catch:{ all -> 0x002d }
            monitor-exit(r2)     // Catch:{ all -> 0x002d }
            goto L_0x0025
        L_0x001b:
            java.util.Map r1 = r1.f9211b     // Catch:{ all -> 0x002d }
            java.lang.Object r5 = r1.get(r5)     // Catch:{ all -> 0x002d }
            r3 = r5
            ekt r3 = (p000.ekt) r3     // Catch:{ all -> 0x002d }
            monitor-exit(r2)     // Catch:{ all -> 0x002d }
        L_0x0025:
            ena r0 = (p000.ena) r0
            epx r5 = r0.f8603c
            r5.mo5140a((p000.ekt) r3)
            return
        L_0x002d:
            r5 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x002d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.eyt.mo5398a(eyk):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: eku} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo5399a(p000.eyl r5) {
        /*
            r4 = this;
            ekv r0 = r4.f9204b
            ezb r1 = r4.f9205c
            java.lang.Object r2 = r1.f9210a
            monitor-enter(r2)
            java.util.Map r3 = r1.f9212c     // Catch:{ all -> 0x002d }
            boolean r3 = r3.containsKey(r5)     // Catch:{ all -> 0x002d }
            if (r3 != 0) goto L_0x001b
            eza r3 = new eza     // Catch:{ all -> 0x002d }
            r3.<init>(r5)     // Catch:{ all -> 0x002d }
            java.util.Map r1 = r1.f9212c     // Catch:{ all -> 0x002d }
            r1.put(r5, r3)     // Catch:{ all -> 0x002d }
            monitor-exit(r2)     // Catch:{ all -> 0x002d }
            goto L_0x0025
        L_0x001b:
            java.util.Map r1 = r1.f9212c     // Catch:{ all -> 0x002d }
            java.lang.Object r5 = r1.get(r5)     // Catch:{ all -> 0x002d }
            r3 = r5
            eku r3 = (p000.eku) r3     // Catch:{ all -> 0x002d }
            monitor-exit(r2)     // Catch:{ all -> 0x002d }
        L_0x0025:
            ena r0 = (p000.ena) r0
            epx r5 = r0.f8603c
            r5.mo5141a((p000.eku) r3)
            return
        L_0x002d:
            r5 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x002d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.eyt.mo5399a(eyl):void");
    }

    public eyt(Context context, ekv ekv, ezb ezb) {
        this.f9203a = context;
        this.f9204b = ekv;
        this.f9205c = ezb;
    }
}
