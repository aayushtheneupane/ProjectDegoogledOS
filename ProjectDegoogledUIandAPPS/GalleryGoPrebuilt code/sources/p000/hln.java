package p000;

import java.util.Iterator;
import java.util.Set;

/* renamed from: hln */
/* compiled from: PG */
public class hln {

    /* renamed from: a */
    private final hln f12988a;

    /* renamed from: b */
    public final C0309lf f12989b;

    /* renamed from: c */
    public boolean f12990c = false;

    public /* synthetic */ hln(hln hln, C0309lf lfVar) {
        if (hln != null) {
            ife.m12890c(hln.f12990c);
        }
        this.f12988a = hln;
        this.f12989b = lfVar;
    }

    /* renamed from: a */
    private final boolean mo7552a() {
        return this == hlm.f12987a;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r0 = r3.f12988a;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo7554a(p000.hok r4) {
        /*
            r3 = this;
            lf r0 = r3.f12989b
            boolean r0 = r0.containsKey(r4)
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0016
            hln r0 = r3.f12988a
            if (r0 == 0) goto L_0x0017
            boolean r4 = r0.mo7554a((p000.hok) r4)
            if (r4 == 0) goto L_0x0015
            goto L_0x0016
        L_0x0015:
            return r1
        L_0x0016:
            r1 = 1
        L_0x0017:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.hln.mo7554a(hok):boolean");
    }

    /* renamed from: a */
    static hln m11701a(hln hln, hln hln2) {
        if (!hln.mo7552a()) {
            return !hln2.mo7552a() ? m11702a((Set) hto.m12121a((Object) hln, (Object) hln2)) : hln;
        }
        return hln2;
    }

    /* renamed from: a */
    static hln m11702a(Set set) {
        if (set.isEmpty()) {
            return hlm.f12987a;
        }
        if (set.size() == 1) {
            return (hln) set.iterator().next();
        }
        Iterator it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            hln hln = (hln) it.next();
            do {
                i += hln.f12989b.f15193b;
                hln = hln.f12988a;
            } while (hln != null);
        }
        if (i == 0) {
            return hlm.f12987a;
        }
        C0309lf lfVar = new C0309lf(i);
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            hln hln2 = (hln) it2.next();
            do {
                int i2 = 0;
                while (true) {
                    C0309lf lfVar2 = hln2.f12989b;
                    if (i2 >= lfVar2.f15193b) {
                        break;
                    }
                    ife.m12849a(lfVar.put((hok) lfVar2.mo9320b(i2), hln2.f12989b.mo9321c(i2)) == null, "Duplicate bindings: %s", hln2.f12989b.mo9320b(i2));
                    i2++;
                }
                hln2 = hln2.f12988a;
            } while (hln2 != null);
        }
        return new hlm((hln) null, lfVar).mo7555c();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final hln mo7555c() {
        if (!this.f12990c) {
            this.f12990c = true;
            return (this.f12988a == null || !this.f12989b.isEmpty()) ? this : this.f12988a;
        }
        throw new IllegalStateException("Already frozen");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000d, code lost:
        r1 = r2.f12988a;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Object m11705b(p000.hok r3) {
        /*
            r2 = this;
            boolean r0 = r2.f12990c
            p000.ife.m12896d((boolean) r0)
            lf r0 = r2.f12989b
            java.lang.Object r0 = r0.get(r3)
            if (r0 != 0) goto L_0x0016
            hln r1 = r2.f12988a
            if (r1 == 0) goto L_0x0016
            java.lang.Object r3 = r1.m11705b(r3)
            return r3
        L_0x0016:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.hln.m11705b(hok):java.lang.Object");
    }

    /* renamed from: a */
    public static hlk m11700a(hok hok, hln hln, hnf hnf) {
        ife.m12898e((Object) hnf);
        Object b = hln.m11705b(hok);
        if (b != null) {
            return new hlk(1, ife.m12898e(b), false);
        }
        return hlk.m11693a(3);
    }

    /* renamed from: b */
    public static hll m11704b() {
        return new hlm(hlm.f12987a, new C0309lf());
    }
}
