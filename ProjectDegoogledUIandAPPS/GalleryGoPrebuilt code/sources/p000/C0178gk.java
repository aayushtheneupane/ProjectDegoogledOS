package p000;

import android.os.Bundle;
import android.util.SparseArray;

/* renamed from: gk */
/* compiled from: PG */
final class C0178gk {

    /* renamed from: a */
    public final C0162fw f11533a;

    /* renamed from: b */
    public final C0147fh f11534b;

    public C0178gk(C0162fw fwVar, C0147fh fhVar) {
        this.f11533a = fwVar;
        this.f11534b = fhVar;
    }

    public C0178gk(C0162fw fwVar, C0147fh fhVar, C0177gj gjVar) {
        this.f11533a = fwVar;
        this.f11534b = fhVar;
        fhVar.f9589h = null;
        fhVar.f9603v = 0;
        fhVar.f9600s = false;
        fhVar.f9597p = false;
        C0147fh fhVar2 = fhVar.f9593l;
        fhVar.f9594m = fhVar2 != null ? fhVar2.f9591j : null;
        fhVar.f9593l = null;
        Bundle bundle = gjVar.f11463m;
        if (bundle == null) {
            fhVar.f9588g = new Bundle();
        } else {
            fhVar.f9588g = bundle;
        }
    }

    public C0178gk(C0162fw fwVar, ClassLoader classLoader, C0159ft ftVar, C0177gj gjVar) {
        this.f11533a = fwVar;
        this.f11534b = ftVar.mo6175c(classLoader, gjVar.f11451a);
        Bundle bundle = gjVar.f11460j;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        this.f11534b.mo5646f(gjVar.f11460j);
        C0147fh fhVar = this.f11534b;
        fhVar.f9591j = gjVar.f11452b;
        fhVar.f9599r = gjVar.f11453c;
        fhVar.f9601t = true;
        fhVar.f9562A = gjVar.f11454d;
        fhVar.f9563B = gjVar.f11455e;
        fhVar.f9564C = gjVar.f11456f;
        fhVar.f9567F = gjVar.f11457g;
        fhVar.f9598q = gjVar.f11458h;
        fhVar.f9566E = gjVar.f11459i;
        fhVar.f9565D = gjVar.f11461k;
        fhVar.f9582U = C0573v.values()[gjVar.f11462l];
        Bundle bundle2 = gjVar.f11463m;
        if (bundle2 != null) {
            this.f11534b.f9588g = bundle2;
        } else {
            this.f11534b.f9588g = new Bundle();
        }
        if (C0171gd.m10054a(2)) {
            "Instantiated fragment " + this.f11534b;
        }
    }

    /* JADX WARNING: type inference failed for: r5v15, types: [android.view.View] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo6791a(p000.C0156fq r5) {
        /*
            r4 = this;
            fh r0 = r4.f11534b
            boolean r0 = r0.f9599r
            if (r0 != 0) goto L_0x0107
            r0 = 3
            boolean r0 = p000.C0171gd.m10054a((int) r0)
            if (r0 == 0) goto L_0x001f
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "moveto CREATE_VIEW: "
            r0.append(r1)
            fh r1 = r4.f11534b
            r0.append(r1)
            r0.toString()
        L_0x001f:
            fh r0 = r4.f11534b
            android.view.ViewGroup r1 = r0.f9572K
            if (r1 != 0) goto L_0x009c
            int r0 = r0.f9563B
            if (r0 == 0) goto L_0x009b
            r1 = -1
            if (r0 == r1) goto L_0x007d
            android.view.View r5 = r5.mo5559a(r0)
            r1 = r5
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            if (r1 != 0) goto L_0x009d
            fh r5 = r4.f11534b
            boolean r0 = r5.f9601t
            if (r0 == 0) goto L_0x003c
            goto L_0x009d
        L_0x003c:
            android.content.res.Resources r5 = r5.mo5657p()     // Catch:{ NotFoundException -> 0x0049 }
            fh r0 = r4.f11534b     // Catch:{ NotFoundException -> 0x0049 }
            int r0 = r0.f9563B     // Catch:{ NotFoundException -> 0x0049 }
            java.lang.String r5 = r5.getResourceName(r0)     // Catch:{ NotFoundException -> 0x0049 }
            goto L_0x004c
        L_0x0049:
            r5 = move-exception
            java.lang.String r5 = "unknown"
        L_0x004c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "No view found for id 0x"
            r1.append(r2)
            fh r2 = r4.f11534b
            int r2 = r2.f9563B
            java.lang.String r2 = java.lang.Integer.toHexString(r2)
            r1.append(r2)
            java.lang.String r2 = " ("
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = ") for fragment "
            r1.append(r5)
            fh r5 = r4.f11534b
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r0.<init>(r5)
            throw r0
        L_0x007d:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Cannot create fragment "
            r0.append(r1)
            fh r1 = r4.f11534b
            r0.append(r1)
            java.lang.String r1 = " for a container view with no id"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        L_0x009b:
            r1 = 0
        L_0x009c:
        L_0x009d:
            fh r5 = r4.f11534b
            r5.f9572K = r1
            android.os.Bundle r0 = r5.f9588g
            android.view.LayoutInflater r0 = r5.mo5647g(r0)
            fh r2 = r4.f11534b
            android.os.Bundle r2 = r2.f9588g
            r5.mo5643b(r0, r1, r2)
            fh r5 = r4.f11534b
            android.view.View r5 = r5.f9573L
            if (r5 == 0) goto L_0x0107
            r0 = 0
            r5.setSaveFromParentEnabled(r0)
            fh r5 = r4.f11534b
            android.view.View r2 = r5.f9573L
            r3 = 2131362002(0x7f0a00d2, float:1.8343772E38)
            r2.setTag(r3, r5)
            if (r1 == 0) goto L_0x00cb
            fh r5 = r4.f11534b
            android.view.View r5 = r5.f9573L
            r1.addView(r5)
        L_0x00cb:
            fh r5 = r4.f11534b
            boolean r1 = r5.f9565D
            if (r1 != 0) goto L_0x00d2
            goto L_0x00d9
        L_0x00d2:
            android.view.View r5 = r5.f9573L
            r1 = 8
            r5.setVisibility(r1)
        L_0x00d9:
            fh r5 = r4.f11534b
            android.view.View r5 = r5.f9573L
            p000.C0340mj.m14724o(r5)
            fh r5 = r4.f11534b
            android.view.View r1 = r5.f9573L
            android.os.Bundle r2 = r5.f9588g
            r5.mo2632a((android.view.View) r1, (android.os.Bundle) r2)
            fw r5 = r4.f11533a
            fh r1 = r4.f11534b
            android.view.View r2 = r1.f9573L
            android.os.Bundle r3 = r1.f9588g
            r5.mo6231a(r1, r2, r3, r0)
            fh r5 = r4.f11534b
            android.view.View r1 = r5.f9573L
            int r1 = r1.getVisibility()
            if (r1 != 0) goto L_0x0105
            fh r1 = r4.f11534b
            android.view.ViewGroup r1 = r1.f9572K
            if (r1 == 0) goto L_0x0105
            r0 = 1
        L_0x0105:
            r5.f9577P = r0
        L_0x0107:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0178gk.mo6791a(fq):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo6792a(ClassLoader classLoader) {
        Bundle bundle = this.f11534b.f9588g;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
            C0147fh fhVar = this.f11534b;
            fhVar.f9589h = fhVar.f9588g.getSparseParcelableArray("android:view_state");
            C0147fh fhVar2 = this.f11534b;
            fhVar2.f9594m = fhVar2.f9588g.getString("android:target_state");
            C0147fh fhVar3 = this.f11534b;
            if (fhVar3.f9594m != null) {
                fhVar3.f9595n = fhVar3.f9588g.getInt("android:target_req_state", 0);
            }
            C0147fh fhVar4 = this.f11534b;
            Boolean bool = fhVar4.f9590i;
            fhVar4.f9575N = fhVar4.f9588g.getBoolean("android:user_visible_hint", true);
            C0147fh fhVar5 = this.f11534b;
            if (!fhVar5.f9575N) {
                fhVar5.f9574M = true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo6790a() {
        if (this.f11534b.f9573L != null) {
            SparseArray sparseArray = new SparseArray();
            this.f11534b.f9573L.saveHierarchyState(sparseArray);
            if (sparseArray.size() > 0) {
                this.f11534b.f9589h = sparseArray;
            }
        }
    }
}
