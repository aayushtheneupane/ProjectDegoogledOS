package p000;

import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: fep */
/* compiled from: PG */
public final class fep {

    /* renamed from: a */
    public final fcu f9383a;

    /* renamed from: b */
    public final feu f9384b;

    /* renamed from: c */
    public final Set f9385c = new HashSet();

    /* renamed from: d */
    public final Set f9386d = new HashSet();

    /* renamed from: e */
    public final List f9387e = new ArrayList();

    /* renamed from: f */
    public final Map f9388f = new HashMap();

    /* renamed from: g */
    public final List f9389g = new ArrayList();

    /* renamed from: h */
    public final List f9390h = new ArrayList();

    /* renamed from: i */
    public final Map f9391i = new HashMap();

    /* renamed from: j */
    public final fdo f9392j = new fem(this);

    /* renamed from: k */
    public final boolean f9393k = true;

    /* renamed from: l */
    public final boolean f9394l = true;

    /* renamed from: m */
    public Runnable f9395m = null;

    /* renamed from: n */
    private final fei f9396n;

    public fep(fcu fcu, fei fei) {
        this.f9383a = fcu;
        this.f9384b = new feu(fcu);
        this.f9396n = fei;
    }

    /* renamed from: c */
    private final feo m8712c(fdr fdr) {
        feo feo = new feo(this.f9389g.size(), this.f9385c.size());
        fdx a = feo.mo5568a(fdr, -1);
        ArrayList arrayList = new ArrayList(1);
        fej.m8700a(fdr, (List) arrayList);
        fdh fdh = a.f9343d;
        if (fdh == null) {
            fdh = fdh.f9308e;
        }
        feo.mo5569a(new ffd(1, arrayList, fdh.f9312c));
        this.f9389g.add(feo);
        this.f9388f.put(a, feo);
        return feo;
    }

    /* renamed from: a */
    public final feo mo5570a(List list, int i) {
        fdx fdx = (fdx) ife.m12907g((Iterable) list);
        feo feo = (feo) this.f9388f.get(fdx);
        if (feo != null) {
            return feo;
        }
        feo feo2 = new feo(this.f9389g.size(), i);
        this.f9389g.add(feo2);
        this.f9388f.put(fdx, feo2);
        return feo2;
    }

    /* renamed from: a */
    public static boolean m8711a(fdr fdr) {
        fdx a = fdr.mo5534a(ffa.f9433a);
        iih iih = ffz.f9495a;
        Map map = iix.f14324A;
        a.mo8786b(iih);
        if (a.f14321j.mo8726a((iil) iih.f14244d)) {
            return true;
        }
        View a2 = fdz.m8659a(fdr, ffa.f9433a);
        while (a2 != null) {
            if (!fdz.m8662a(a2)) {
                ViewParent parent = a2.getParent();
                if (!(parent instanceof View)) {
                    return false;
                }
                a2 = (View) parent;
            } else {
                String valueOf = String.valueOf(fdr);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 180);
                sb.append("Unexpected visual element (");
                sb.append(valueOf);
                sb.append(") without parent detected. All visual elements except the root view must have a parent visual element. See also: go/gil-android/impressions#requirements.");
                Log.e("GIL", sb.toString());
                return false;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ab  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.feo mo5572b(p000.fdr r7) {
        /*
            r6 = this;
            ffa r0 = p000.ffa.f9433a
            int r0 = r7.mo5547h(r0)
            r1 = -2
            r2 = -1
            r3 = 0
            if (r0 != r2) goto L_0x00c9
            ffa r0 = p000.ffa.f9433a
            boolean r0 = r7.mo5540b(r0)
            p000.ife.m12896d((boolean) r0)
            java.util.List r0 = r6.f9390h
            r0.add(r7)
            ffa r0 = p000.ffa.f9433a
            fdq r0 = r7.mo5544e(r0)
            boolean r4 = r0.mo5523a()
            if (r4 == 0) goto L_0x002a
            feo r7 = r6.m8712c(r7)
            return r7
        L_0x002a:
            fdr r0 = r0.mo5524b()
            if (r0 != 0) goto L_0x0041
            boolean r0 = m8711a(r7)
            if (r0 != 0) goto L_0x003c
            ffa r0 = p000.ffa.f9433a
            r7.mo5535a((int) r1, (p000.ffa) r0)
            return r3
        L_0x003c:
            feo r7 = r6.m8712c(r7)
            return r7
        L_0x0041:
            ffa r1 = p000.ffa.f9433a
            int r1 = r0.mo5547h(r1)
            if (r1 != r2) goto L_0x00b6
            ffa r1 = p000.ffa.f9433a
            boolean r1 = r0.mo5542c(r1)
            if (r1 == 0) goto L_0x00b6
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r1.add(r3)
            p000.fej.m8700a((p000.fdr) r0, (java.util.List) r1)
            int r0 = r1.size()
            r3 = 0
            r4 = 1
            if (r0 <= r4) goto L_0x0066
            r0 = 1
            goto L_0x0068
        L_0x0066:
            r0 = 0
        L_0x0068:
            p000.ife.m12896d((boolean) r0)
            java.lang.Object r0 = r1.get(r4)
            fdx r0 = (p000.fdx) r0
            fdh r0 = r0.f9343d
            if (r0 == 0) goto L_0x0076
            goto L_0x0078
        L_0x0076:
            fdh r0 = p000.fdh.f9308e
        L_0x0078:
            int r5 = r0.f9310a
            r5 = r5 & r4
            if (r5 == 0) goto L_0x008c
            ial r0 = r0.f9311b
            if (r0 == 0) goto L_0x0082
            goto L_0x0084
        L_0x0082:
            ial r0 = p000.ial.f13725d
        L_0x0084:
            int r0 = r0.f13727a
            r0 = r0 & 2
            if (r0 != 0) goto L_0x008c
            r0 = 0
            goto L_0x008e
        L_0x008c:
            r0 = 1
        L_0x008e:
            java.lang.String r5 = "Cannot mix client and server impressed nodes in the same tree."
            p000.ife.m12876b((boolean) r0, (java.lang.Object) r5)
            java.util.Set r0 = r6.f9385c
            int r0 = r0.size()
            feo r0 = r6.mo5570a(r1, r0)
            fdx r7 = r0.mo5568a(r7, r2)
            r1.set(r3, r7)
            ffd r2 = new ffd
            fdh r7 = r7.f9343d
            if (r7 == 0) goto L_0x00ab
            goto L_0x00ad
        L_0x00ab:
            fdh r7 = p000.fdh.f9308e
        L_0x00ad:
            int r7 = r7.f9312c
            r2.<init>(r4, r1, r7)
            r0.mo5569a(r2)
            return r0
        L_0x00b6:
            feo r1 = r6.mo5572b(r0)
            if (r1 == 0) goto L_0x00c8
            ffa r2 = p000.ffa.f9433a
            fdh r0 = r0.mo5543d(r2)
            int r0 = r0.f9312c
            r1.mo5568a(r7, r0)
            return r1
        L_0x00c8:
            return r3
        L_0x00c9:
            if (r0 == r1) goto L_0x00d4
            java.util.List r7 = r6.f9389g
            java.lang.Object r7 = r7.get(r0)
            feo r7 = (p000.feo) r7
            return r7
        L_0x00d4:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.fep.mo5572b(fdr):feo");
    }

    /* renamed from: a */
    public final void mo5571a() {
        if (this.f9395m == null) {
            Runnable a = this.f9396n.mo5566a("AutoGIL", new fek(this));
            this.f9395m = a;
            fxk.m9824a(a);
        }
    }
}
