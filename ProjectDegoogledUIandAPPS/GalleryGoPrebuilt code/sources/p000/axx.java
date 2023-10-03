package p000;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: axx */
/* compiled from: PG */
public final class axx {

    /* renamed from: a */
    private static final axo f1848a = new axv();

    /* renamed from: b */
    private final List f1849b = new ArrayList();

    /* renamed from: c */
    private final Set f1850c = new HashSet();

    /* renamed from: d */
    private final C0306lc f1851d;

    public axx(C0306lc lcVar) {
        this.f1851d = lcVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized void mo1724a(Class cls, Class cls2, axp axp) {
        axw axw = new axw(cls, cls2, axp);
        List list = this.f1849b;
        list.add(list.size(), axw);
    }

    /* renamed from: a */
    private final axo m1874a(axw axw) {
        return (axo) cns.m4632a((Object) axw.f1846b.mo1696a(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized List mo1723a(Class cls) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            for (axw axw : this.f1849b) {
                if (!this.f1850c.contains(axw) && axw.mo1721a(cls)) {
                    this.f1850c.add(axw);
                    arrayList.add(m1874a(axw));
                    this.f1850c.remove(axw);
                }
            }
        } catch (Throwable th) {
            this.f1850c.clear();
            throw th;
        }
        return arrayList;
    }

    /* renamed from: a */
    public final synchronized axo mo1722a(Class cls, Class cls2) {
        try {
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (axw axw : this.f1849b) {
                if (this.f1850c.contains(axw)) {
                    z = true;
                } else if (axw.mo1721a(cls) && axw.f1845a.isAssignableFrom(cls2)) {
                    this.f1850c.add(axw);
                    arrayList.add(m1874a(axw));
                    this.f1850c.remove(axw);
                }
            }
            if (arrayList.size() > 1) {
                return new axu(arrayList, this.f1851d);
            } else if (arrayList.size() == 1) {
                return (axo) arrayList.get(0);
            } else if (z) {
                return f1848a;
            } else {
                throw new ape(cls, cls2);
            }
        } catch (Throwable th) {
            this.f1850c.clear();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final synchronized List mo1725b(Class cls) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        List list = this.f1849b;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            axw axw = (axw) list.get(i);
            if (!arrayList.contains(axw.f1845a) && axw.mo1721a(cls)) {
                arrayList.add(axw.f1845a);
            }
        }
        return arrayList;
    }
}
