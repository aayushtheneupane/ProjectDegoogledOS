package p000;

import android.widget.FrameLayout;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.oneup.OneUpPagerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p003j$.util.Optional;

/* renamed from: dog */
/* compiled from: PG */
public final class dog {

    /* renamed from: e */
    private static final hpq f6932e = new doe();

    /* renamed from: f */
    private static final gvw f6933f = dnu.f6922a;

    /* renamed from: a */
    public final eav f6934a;

    /* renamed from: b */
    public final cjr f6935b;

    /* renamed from: c */
    public dof f6936c = dnt.f6921a;

    /* renamed from: d */
    public Optional f6937d = Optional.empty();

    /* renamed from: g */
    private final ebc f6938g;

    public dog(OneUpPagerView oneUpPagerView, hbl hbl, cjr cjr) {
        this.f6938g = oneUpPagerView;
        this.f6935b = cjr;
        oneUpPagerView.f7827c = 2;
        oneUpPagerView.f7826b.setItemViewCacheSize(5);
        ebc ebc = this.f6938g;
        int dimensionPixelOffset = hbl.getResources().getDimensionPixelOffset(R.dimen.oneup_page_gap) / 2;
        C0643xp xpVar = ebc.f7828d;
        if (xpVar != null) {
            ebc.f7826b.removeItemDecoration(xpVar);
        }
        ebc.f7828d = new eay(dimensionPixelOffset);
        ebc.f7826b.addItemDecoration(ebc.f7828d);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        int i = -dimensionPixelOffset;
        layoutParams.leftMargin = i;
        layoutParams.rightMargin = i;
        ebc.f7826b.setLayoutParams(layoutParams);
        ebc ebc2 = this.f6938g;
        dlv dlv = new dlv(hbl);
        hpq a = f6932e.mo7660a(dnv.f6923a);
        bpy bpy = new bpy(f6933f);
        gwb c = gwd.m10934c();
        c.mo7127a((gwe) dlv);
        c.f12172b = a;
        ife.m12876b(a != hpp.f13230a, (Object) "Equivalence.equals() should not be used with setEquivalence. See go/tiktok/dataservice/recyclerview.md for proper usage.");
        c.f12171a = bpy;
        gwd a2 = c.mo7126a();
        eav eav = new eav(ebc2, a2);
        ebc2.f7829e = eav;
        ebc2.f7826b.setAdapter(a2);
        this.f6934a = eav;
        eav.f7805e = Optional.ofNullable(new dnw(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo4298a(List list, Map map) {
        dik dik;
        boolean z;
        Optional a = mo4296a();
        int i = 0;
        if (!a.isPresent() && list.isEmpty()) {
            return false;
        }
        if (a.isPresent()) {
            if (this.f6935b.mo3175a()) {
                cxi cxi = (cxi) a.get();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    cxi cxi2 = (cxi) it.next();
                    if (cxi.f5929u.size() != 0) {
                        if (cxi2.f5929u.size() != 0) {
                            z = bmf.m3230a(cxi).equals(bmf.m3230a(cxi2));
                            continue;
                        } else {
                            z = flw.m9195a(cxi, cxi2);
                            continue;
                        }
                    } else if (cxi2.f5929u.size() != 0) {
                        z = flw.m9195a(cxi2, cxi);
                        continue;
                    } else {
                        z = cyc.m5645a(cxi, cxi2);
                        continue;
                    }
                    if (z) {
                        new Object[1][0] = Long.valueOf(cxi.f5911c);
                    }
                }
                new Object[1][0] = Long.valueOf(cxi.f5911c);
                return true;
            }
            cxi cxi3 = (cxi) a.get();
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                if (cyc.m5645a((cxi) it2.next(), cxi3)) {
                }
            }
            return true;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it3 = list.iterator();
        while (it3.hasNext()) {
            cxi cxi4 = (cxi) it3.next();
            if ((cxi4.f5909a & 65536) != 0) {
                dik = (dik) map.get(cxi4.f5926r);
            } else {
                dik = null;
            }
            arrayList.add(mo4295a(cxi4, dik));
        }
        eav eav = this.f6934a;
        Optional a2 = eav.mo4634a();
        eav.f7803c = arrayList;
        eav.f7802b.mo7129a(eav.f7803c);
        if (a2.isPresent()) {
            while (true) {
                if (i < eav.f7803c.size()) {
                    if (((eaq) eav.f7803c.get(i)).mo4234a((eaq) a2.get())) {
                        eav.mo4636a(i, true);
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
        } else {
            eav.mo4636a(0, true);
        }
        return true;
    }

    /* renamed from: a */
    public final Optional mo4296a() {
        return this.f6934a.mo4634a().map(doa.f6928a);
    }

    /* renamed from: b */
    public final void mo4299b() {
        Optional a = this.f6934a.mo4634a();
        if (!this.f6937d.equals(a)) {
            this.f6936c.mo4261a(a.map(doc.f6930a), a.flatMap(dod.f6931a));
            this.f6937d = a;
        }
    }

    /* renamed from: a */
    public final dls mo4295a(cxi cxi, dik dik) {
        cxh cxh = cxh.UNKNOWN_MEDIA_TYPE;
        cxh a = cxh.m5592a(cxi.f5913e);
        if (a == null) {
            a = cxh.UNKNOWN_MEDIA_TYPE;
        }
        int ordinal = a.ordinal();
        if (ordinal == 0) {
            throw new IllegalArgumentException("Media of unknown type can't be transformed into a page.");
        } else if (ordinal == 1) {
            return new drl(cxi, dik, this.f6935b);
        } else {
            if (ordinal == 2) {
                return new dtw(cxi, dik, this.f6935b);
            }
            cxh a2 = cxh.m5592a(cxi.f5913e);
            if (a2 == null) {
                a2 = cxh.UNKNOWN_MEDIA_TYPE;
            }
            String name = a2.name();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 30);
            sb.append("Impossible state; ");
            sb.append(name);
            sb.append(" not handled");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0074  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo4297a(p000.cxi r6) {
        /*
            r5 = this;
            cjr r0 = r5.f6935b
            boolean r0 = r0.mo3175a()
            r1 = -1
            r2 = 0
            if (r0 != 0) goto L_0x0027
            eav r0 = r5.f6934a
            java.util.List r0 = r0.f7803c
            r3 = 0
        L_0x000f:
            int r4 = r0.size()
            if (r3 >= r4) goto L_0x0026
            java.lang.Object r4 = r0.get(r3)
            dls r4 = (p000.dls) r4
            cxi r4 = r4.f6796a
            boolean r4 = p000.cyc.m5645a((p000.cxi) r4, (p000.cxi) r6)
            if (r4 != 0) goto L_0x0063
            int r3 = r3 + 1
            goto L_0x000f
        L_0x0026:
            goto L_0x0062
        L_0x0027:
            eav r0 = r5.f6934a
            java.util.List r0 = r0.f7803c
            r3 = 0
        L_0x002c:
            int r4 = r0.size()
            if (r3 >= r4) goto L_0x0061
            java.lang.Object r4 = r0.get(r3)
            dls r4 = (p000.dls) r4
            cxi r4 = r4.f6796a
            boolean r4 = p000.flw.m9194a((p000.cxi) r4)
            if (r4 == 0) goto L_0x004f
            java.lang.Object r4 = r0.get(r3)
            dls r4 = (p000.dls) r4
            cxi r4 = r4.f6796a
            boolean r4 = p000.flw.m9195a((p000.cxi) r4, (p000.cxi) r6)
            if (r4 != 0) goto L_0x0063
            goto L_0x005e
        L_0x004f:
            java.lang.Object r4 = r0.get(r3)
            dls r4 = (p000.dls) r4
            cxi r4 = r4.f6796a
            boolean r4 = p000.cyc.m5645a((p000.cxi) r4, (p000.cxi) r6)
            if (r4 == 0) goto L_0x005e
            goto L_0x0063
        L_0x005e:
            int r3 = r3 + 1
            goto L_0x002c
        L_0x0061:
        L_0x0062:
            r3 = -1
        L_0x0063:
            if (r3 != r1) goto L_0x0074
            r0 = 1
            cxi[] r0 = new p000.cxi[r0]
            r0[r2] = r6
            java.util.ArrayList r6 = p000.ife.m12872b((java.lang.Object[]) r0)
            hsu r0 = p000.hvb.f13454a
            r5.mo4298a((java.util.List) r6, (java.util.Map) r0)
            return
        L_0x0074:
            eav r6 = r5.f6934a
            r6.mo4636a(r3, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.dog.mo4297a(cxi):void");
    }
}
