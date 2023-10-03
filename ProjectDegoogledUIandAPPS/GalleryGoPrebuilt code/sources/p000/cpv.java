package p000;

import android.view.ActionMode;
import android.view.Menu;
import com.google.android.apps.photosgo.R;
import java.util.Collection;
import java.util.Set;

/* renamed from: cpv */
/* compiled from: PG */
final class cpv extends cpw {

    /* renamed from: a */
    private C0147fh f5391a;

    /* renamed from: b */
    private cqh f5392b;

    /* renamed from: c */
    private cpi f5393c;

    /* renamed from: d */
    private cpx f5394d;

    /* renamed from: e */
    private final int f5395e;

    /* renamed from: f */
    private final cjr f5396f;

    /* renamed from: g */
    private final cjr f5397g;

    public cpv(C0147fh fhVar, cqh cqh, hlz hlz, cpi cpi, cpx cpx, cjr cjr, cjr cjr2) {
        this(fhVar, cqh, hlz, cpi, cpx, cjr, cjr2, R.string.select_items_text);
    }

    public cpv(C0147fh fhVar, cqh cqh, hlz hlz, cpi cpi, cpx cpx, cjr cjr, cjr cjr2, int i) {
        super(fhVar, cqh, hlz, cpi);
        this.f5391a = fhVar;
        this.f5392b = cqh;
        this.f5393c = cpi;
        this.f5394d = cpx;
        this.f5396f = cjr;
        this.f5397g = cjr2;
        this.f5395e = i;
    }

    /* renamed from: a */
    private final hto m5234a(boolean z) {
        Set set;
        cqh cqh = (cqh) ife.m12898e((Object) this.f5392b);
        if (this.f5396f.mo3175a() && z) {
            set = dwv.m6831a(cqh.f5420a, true);
        } else {
            set = dwv.m6831a(cqh.f5420a, false);
        }
        return hto.m12125a((Collection) set);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x014c, code lost:
        if (r9.size() <= 100) goto L_0x014e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0151, code lost:
        r9 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x017b, code lost:
        if (r0 <= 100) goto L_0x014e;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo3737a(android.view.ActionMode r8, int r9) {
        /*
            r7 = this;
            cqh r0 = r7.f5392b
            java.lang.Object r0 = p000.ife.m12898e((java.lang.Object) r0)
            cqh r0 = (p000.cqh) r0
            fh r1 = r7.f5391a
            java.lang.Object r1 = p000.ife.m12898e((java.lang.Object) r1)
            fh r1 = (p000.C0147fh) r1
            if (r9 == 0) goto L_0x01b7
            r2 = 100
            r3 = 1
            r4 = 0
            switch(r9) {
                case 2131361926: goto L_0x01a7;
                case 2131361934: goto L_0x011f;
                case 2131362088: goto L_0x0105;
                case 2131362226: goto L_0x0095;
                case 2131362234: goto L_0x003a;
                default: goto L_0x0019;
            }
        L_0x0019:
            java.lang.String r8 = p000.cun.m5449b(r9)
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            int r0 = r8.length()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            int r0 = r0 + 17
            r1.<init>(r0)
            java.lang.String r0 = "Unsupported item "
            r1.append(r0)
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            r9.<init>(r8)
            throw r9
        L_0x003a:
            java.util.Set r8 = r0.f5420a
            int r8 = r8.size()
            if (r8 <= r2) goto L_0x0062
            android.content.Context r8 = r1.mo2634k()
            r9 = 2
            java.lang.Object[] r9 = new java.lang.Object[r9]
            java.lang.String r0 = "num_shares"
            r9[r4] = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r2)
            r9[r3] = r0
            r0 = 2131886417(0x7f120151, float:1.9407412E38)
            java.lang.String r8 = p000.C0643xp.m15940a(r8, r0, r9)
            dwz r8 = p000.dwz.m6850a(r8)
            p000.ihg.m13041a((p000.hoi) r8, (p000.C0147fh) r1)
            return
        L_0x0062:
            ecx r8 = p000.ecx.f7947d
            iir r8 = r8.mo8793g()
            hto r9 = r7.m5234a(r4)
            boolean r0 = r8.f14319c
            if (r0 == 0) goto L_0x0075
            r8.mo8751b()
            r8.f14319c = r4
        L_0x0075:
            iix r0 = r8.f14318b
            ecx r0 = (p000.ecx) r0
            r0.mo4686a()
            ije r0 = r0.f7950b
            p000.igz.m12986a((java.lang.Iterable) r9, (java.util.List) r0)
            iix r8 = r8.mo8770g()
            ecx r8 = (p000.ecx) r8
            ecw r8 = p000.ecw.m7199a((p000.ecx) r8)
            gd r9 = r1.mo5659r()
            java.lang.String r0 = "share"
            r8.mo5429b(r9, r0)
            return
        L_0x0095:
            java.util.List r8 = r0.f5421b
            java.util.HashSet r9 = new java.util.HashSet
            r9.<init>()
            java.lang.Object r8 = p000.ife.m12898e((java.lang.Object) r8)
            java.util.List r8 = (java.util.List) r8
            java.util.Iterator r8 = r8.iterator()
        L_0x00a6:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L_0x00c5
            java.lang.Object r1 = r8.next()
            cpt r1 = (p000.cpt) r1
            boolean r2 = r1 instanceof p000.dwv
            if (r2 == 0) goto L_0x00a6
            r2 = r1
            dwv r2 = (p000.dwv) r2
            dwu r2 = r2.mo4478b()
            dwu r3 = p000.dwu.MEDIA
            if (r2 != r3) goto L_0x00a6
            r9.add(r1)
            goto L_0x00a6
        L_0x00c5:
            j$.util.Optional r8 = r0.f5422c
            boolean r8 = r8.isPresent()
            java.lang.String r1 = "MultiselectState must be set"
            p000.ife.m12876b((boolean) r8, (java.lang.Object) r1)
            j$.util.Optional r8 = r0.f5422c
            java.lang.Object r8 = r8.get()
            cqe r8 = (p000.cqe) r8
            int r8 = r8.f5417b
            int r8 = p000.cun.m5438a((int) r8)
            if (r8 != 0) goto L_0x00e1
            goto L_0x00e5
        L_0x00e1:
            r1 = 3
            if (r8 != r1) goto L_0x00e5
            return
        L_0x00e5:
            java.util.Iterator r8 = r9.iterator()
        L_0x00e9:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x0101
            java.lang.Object r9 = r8.next()
            cpt r9 = (p000.cpt) r9
            boolean r1 = r0.mo3767g()
            if (r1 == 0) goto L_0x0101
            java.util.Set r1 = r0.f5420a
            r1.add(r9)
            goto L_0x00e9
        L_0x0101:
            r0.mo3759b()
            return
        L_0x0105:
            hto r9 = r7.m5234a(r3)
            cpu r0 = new cpu
            r0.<init>(r8, r9, r1)
            cpx r8 = r7.f5394d
            java.lang.Object r8 = p000.ife.m12898e((java.lang.Object) r8)
            cpx r8 = (p000.cpx) r8
            java.lang.Runnable r0 = p000.hmq.m11748a((java.lang.Runnable) r0)
            r8.mo3728a(r9, r0)
            return
        L_0x011f:
            bqc r8 = p000.bqc.f3349d
            iir r8 = r8.mo8793g()
            hto r9 = r7.m5234a(r3)
            boolean r5 = r8.f14319c
            if (r5 == 0) goto L_0x0132
            r8.mo8751b()
            r8.f14319c = r4
        L_0x0132:
            iix r5 = r8.f14318b
            bqc r5 = (p000.bqc) r5
            r5.mo2663a()
            ije r5 = r5.f3352b
            p000.igz.m12986a((java.lang.Iterable) r9, (java.util.List) r5)
            java.util.Set r9 = r0.f5420a
            cjr r0 = r7.f5396f
            boolean r0 = r0.mo3175a()
            if (r0 != 0) goto L_0x0153
            int r9 = r9.size()
            if (r9 > r2) goto L_0x0150
        L_0x014e:
            r9 = 0
            goto L_0x017e
        L_0x0150:
        L_0x0151:
            r9 = 1
            goto L_0x017e
        L_0x0153:
            java.util.Set r9 = p000.dwv.m6830a(r9)
            java.util.Iterator r9 = r9.iterator()
            r0 = 0
        L_0x015c:
            boolean r5 = r9.hasNext()
            if (r5 == 0) goto L_0x017b
            java.lang.Object r5 = r9.next()
            cxi r5 = (p000.cxi) r5
            ije r6 = r5.f5929u
            int r6 = r6.size()
            if (r6 == 0) goto L_0x0178
            ije r5 = r5.f5929u
            int r5 = r5.size()
            int r0 = r0 + r5
            goto L_0x015c
        L_0x0178:
            int r0 = r0 + 1
            goto L_0x015c
        L_0x017b:
            if (r0 > r2) goto L_0x0151
            goto L_0x014e
        L_0x017e:
            boolean r0 = r8.f14319c
            if (r0 != 0) goto L_0x0183
            goto L_0x0188
        L_0x0183:
            r8.mo8751b()
            r8.f14319c = r4
        L_0x0188:
            iix r0 = r8.f14318b
            bqc r0 = (p000.bqc) r0
            int r2 = r0.f3351a
            r2 = r2 | r3
            r0.f3351a = r2
            r0.f3353c = r9
            iix r8 = r8.mo8770g()
            bqc r8 = (p000.bqc) r8
            bqd r8 = p000.bqd.m3356a((p000.bqc) r8)
            gd r9 = r1.mo5659r()
            java.lang.String r0 = "delete_fragment"
            r8.mo5429b(r9, r0)
            return
        L_0x01a7:
            hto r9 = r7.m5234a(r3)
            r8.finish()
            dxo r8 = p000.dxo.m6865a(r4, r9)
            p000.ihg.m13041a((p000.hoi) r8, (p000.C0147fh) r1)
            return
        L_0x01b7:
            r8 = 0
            goto L_0x01ba
        L_0x01b9:
            throw r8
        L_0x01ba:
            goto L_0x01b9
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.cpv.mo3737a(android.view.ActionMode, int):void");
    }

    public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        actionMode.getMenuInflater().inflate(R.menu.multiselect_contextual_menu, menu);
        menu.findItem(R.id.move_to_folder).setVisible(!((cpi) ife.m12898e((Object) this.f5393c)).equals(cpi.HOME_NO_MOVE));
        if (this.f5397g.mo3175a()) {
            menu.findItem(R.id.trash).setVisible(true);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo3736a() {
        this.f5391a = null;
        this.f5392b = null;
        this.f5393c = null;
        this.f5394d = null;
    }

    public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        ife.m12898e((Object) this.f5392b);
        if (this.f5392b.f5420a.isEmpty()) {
            actionMode.setTitle(this.f5395e);
            m5235a(menu, false);
        } else {
            actionMode.setTitle(String.valueOf(this.f5392b.f5420a.size()));
            m5235a(menu, true);
        }
        return true;
    }

    /* renamed from: a */
    private final void m5235a(Menu menu, boolean z) {
        menu.findItem(R.id.delete).setEnabled(z);
        menu.findItem(R.id.share).setEnabled(z);
        menu.findItem(R.id.move_to_folder).setEnabled(z);
        menu.findItem(R.id.copy_to_folder).setEnabled(z);
        if (this.f5397g.mo3175a()) {
            menu.findItem(R.id.trash).setEnabled(z);
        }
    }
}
