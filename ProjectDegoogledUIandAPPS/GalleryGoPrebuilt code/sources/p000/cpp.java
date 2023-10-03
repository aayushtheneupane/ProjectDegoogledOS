package p000;

import android.view.ActionMode;
import com.google.android.apps.photosgo.gridview.ActionModeHelper$2;
import p003j$.util.Optional;

/* renamed from: cpp */
/* compiled from: PG */
public final class cpp {

    /* renamed from: a */
    public final grx f5371a;

    /* renamed from: b */
    public final gry f5372b;

    /* renamed from: c */
    public Optional f5373c = Optional.empty();

    /* renamed from: d */
    private final cnh f5374d;

    /* renamed from: e */
    private final cqh f5375e;

    /* renamed from: f */
    private final hlz f5376f;

    /* renamed from: g */
    private Optional f5377g = Optional.empty();

    /* renamed from: h */
    private final C0147fh f5378h;

    /* renamed from: i */
    private final C0654y f5379i = new cpo(this);

    /* renamed from: j */
    private final cpx f5380j;

    /* renamed from: k */
    private final cjr f5381k;

    /* renamed from: l */
    private final cjr f5382l;

    public cpp(C0147fh fhVar, cnh cnh, hlz hlz, cqh cqh, egp egp, grx grx, cjr cjr, cjr cjr2) {
        this.f5378h = fhVar;
        this.f5374d = cnh;
        this.f5376f = hlz;
        this.f5375e = cqh;
        this.f5371a = grx;
        this.f5381k = cjr;
        this.f5382l = cjr2;
        C0600w ad = fhVar.mo5ad();
        ad.mo64a(this.f5379i);
        ad.mo64a(egp);
        this.f5372b = new ActionModeHelper$2(new cpk(this), new cpl());
        this.f5380j = new cpm(this, grx, egp);
    }

    /* renamed from: b */
    public final void mo3732b() {
        if (this.f5377g.isPresent()) {
            ((ActionMode) this.f5377g.get()).finish();
            this.f5377g = Optional.empty();
        }
    }

    /* renamed from: a */
    public final void mo3730a() {
        this.f5373c = Optional.empty();
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [android.view.ActionMode$Callback] */
    /* JADX WARNING: type inference failed for: r5v4, types: [cpv] */
    /* JADX WARNING: type inference failed for: r5v5, types: [cpy] */
    /* JADX WARNING: type inference failed for: r5v6, types: [cpr] */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r2v2, types: [cpv] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo3731a(p000.cpi r14) {
        /*
            r13 = this;
            fh r0 = r13.f5378h
            fj r0 = r0.mo5653m()
            if (r0 == 0) goto L_0x0091
            j$.util.Optional r1 = r13.f5377g
            boolean r1 = r1.isPresent()
            if (r1 != 0) goto L_0x0085
            cpi r1 = p000.cpi.DEFAULT
            int r1 = r14.ordinal()
            switch(r1) {
                case 0: goto L_0x0065;
                case 1: goto L_0x0055;
                case 2: goto L_0x0065;
                case 3: goto L_0x0065;
                case 4: goto L_0x0044;
                case 5: goto L_0x0037;
                case 6: goto L_0x001f;
                default: goto L_0x0019;
            }
        L_0x0019:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            throw r0
        L_0x001f:
            cpv r1 = new cpv
            fh r3 = r13.f5378h
            cqh r4 = r13.f5375e
            hlz r5 = r13.f5376f
            cpx r7 = r13.f5380j
            cjr r8 = r13.f5381k
            cjr r9 = r13.f5382l
            r10 = 2131886396(0x7f12013c, float:1.940737E38)
            r2 = r1
            r6 = r14
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            goto L_0x007a
        L_0x0037:
            cpz r1 = new cpz
            fh r2 = r13.f5378h
            cqh r3 = r13.f5375e
            hlz r4 = r13.f5376f
            r1.<init>(r2, r3, r4, r14)
            goto L_0x007a
        L_0x0044:
            cpr r1 = new cpr
            fh r6 = r13.f5378h
            cqh r7 = r13.f5375e
            hlz r8 = r13.f5376f
            cnh r10 = r13.f5374d
            r5 = r1
            r9 = r14
            r5.<init>(r6, r7, r8, r9, r10)
            goto L_0x007a
        L_0x0055:
            cpy r1 = new cpy
            fh r6 = r13.f5378h
            cqh r7 = r13.f5375e
            hlz r8 = r13.f5376f
            cnh r10 = r13.f5374d
            r5 = r1
            r9 = r14
            r5.<init>(r6, r7, r8, r9, r10)
            goto L_0x007a
        L_0x0065:
            cpv r1 = new cpv
            fh r6 = r13.f5378h
            cqh r7 = r13.f5375e
            hlz r8 = r13.f5376f
            cpx r10 = r13.f5380j
            cjr r11 = r13.f5381k
            cjr r12 = r13.f5382l
            r5 = r1
            r9 = r14
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)
        L_0x007a:
            android.view.ActionMode r0 = r0.startActionMode(r1)
            j$.util.Optional r0 = p003j$.util.Optional.ofNullable(r0)
            r13.f5377g = r0
            return
        L_0x0085:
            j$.util.Optional r0 = r13.f5377g
            java.lang.Object r0 = r0.get()
            android.view.ActionMode r0 = (android.view.ActionMode) r0
            r0.invalidate()
            return
        L_0x0091:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.cpp.mo3731a(cpi):void");
    }
}
