package p000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: aph */
/* compiled from: PG */
public final class aph {

    /* renamed from: a */
    public final axs f1328a = new axs(this.f1336i);

    /* renamed from: b */
    public final bdp f1329b = new bdp();

    /* renamed from: c */
    public final bdu f1330c = new bdu();

    /* renamed from: d */
    public final bdw f1331d = new bdw();

    /* renamed from: e */
    public final arn f1332e = new arn();

    /* renamed from: f */
    public final bck f1333f = new bck();

    /* renamed from: g */
    public final bds f1334g = new bds();

    /* renamed from: h */
    public final bdr f1335h = new bdr();

    /* renamed from: i */
    public final C0306lc f1336i = bfx.m2448a();

    /* renamed from: j */
    private final bdq f1337j = new bdq();

    public aph() {
        List asList = Arrays.asList(new String[]{"Gif", "Bitmap", "BitmapDrawable"});
        ArrayList arrayList = new ArrayList(asList.size());
        arrayList.addAll(asList);
        arrayList.add(0, "legacy_prepend_all");
        arrayList.add("legacy_append");
        this.f1330c.mo1846a((List) arrayList);
    }

    /* renamed from: a */
    public final void mo1407a(Class cls, aqk aqk) {
        this.f1329b.mo1840a(cls, aqk);
    }

    /* renamed from: a */
    public final void mo1408a(Class cls, arc arc) {
        this.f1331d.mo1849a(cls, arc);
    }

    /* renamed from: a */
    public final void mo1409a(Class cls, Class cls2, arb arb) {
        mo1412a("legacy_append", cls, cls2, arb);
    }

    /* renamed from: a */
    public final void mo1410a(Class cls, Class cls2, axp axp) {
        this.f1328a.mo1718a(cls, cls2, axp);
    }

    /* renamed from: a */
    public final void mo1412a(String str, Class cls, Class cls2, arb arb) {
        this.f1330c.mo1845a(str, arb, cls, cls2);
    }

    /* renamed from: a */
    public final List mo1403a() {
        List a = this.f1337j.mo1841a();
        if (!a.isEmpty()) {
            return a;
        }
        throw new apd();
    }

    /* renamed from: a */
    public final List mo1404a(Object obj) {
        List b = this.f1328a.mo1719b(obj.getClass());
        if (!b.isEmpty()) {
            int size = b.size();
            List emptyList = Collections.emptyList();
            boolean z = true;
            for (int i = 0; i < size; i++) {
                axo axo = (axo) b.get(i);
                if (axo.mo1698a(obj)) {
                    if (z) {
                        emptyList = new ArrayList(size - i);
                    }
                    emptyList.add(axo);
                    z = false;
                }
            }
            if (!emptyList.isEmpty()) {
                return emptyList;
            }
            throw new ape(obj, b);
        }
        throw new ape(obj);
    }

    /* renamed from: a */
    public final void mo1405a(aqm aqm) {
        this.f1337j.mo1842a(aqm);
    }

    /* renamed from: a */
    public final void mo1406a(arj arj) {
        this.f1332e.mo1531a(arj);
    }

    /* renamed from: a */
    public final void mo1411a(Class cls, Class cls2, bci bci) {
        this.f1333f.mo1809a(cls, cls2, bci);
    }
}
