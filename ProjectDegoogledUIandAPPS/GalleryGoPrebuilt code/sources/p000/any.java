package p000;

import java.util.Collections;
import java.util.Iterator;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: any */
/* compiled from: PG */
public final class any implements anh {

    /* renamed from: a */
    public aom f1235a;

    /* renamed from: b */
    public String f1236b = null;

    /* renamed from: c */
    private Iterator f1237c = null;

    public any(aoa aoa, String str, String str2, aom aom) {
        boolean z;
        aod aod;
        String str3 = null;
        this.f1235a = aom;
        boolean z2 = str != null && str.length() > 0;
        if (str2 == null || str2.length() <= 0) {
            z = false;
        } else {
            z = true;
        }
        if (!z2 && !z) {
            aod = aoa.f1242a;
        } else if (z2 && z) {
            aoj a = C0643xp.m15938a(str, str2);
            aoj aoj = new aoj();
            for (int i = 0; i < a.mo1346a() - 1; i++) {
                aoj.mo1348a(a.mo1347a(i));
            }
            aod = C0637xj.m15894a(aoa.f1242a, a, false, (aop) null);
            this.f1236b = str;
            str3 = aoj.toString();
        } else if (z2) {
            aod = C0637xj.m15896a(aoa.f1242a, str, false);
        } else {
            throw new ang("Schema namespace URI is required", 101);
        }
        if (aod == null) {
            this.f1237c = Collections.EMPTY_LIST.iterator();
        } else if (!this.f1235a.mo1359a(256)) {
            this.f1237c = new anw(this, aod, str3, 1);
        } else {
            this.f1237c = new anx(this, aod, str3);
        }
    }

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final boolean hasNext() {
        return this.f1237c.hasNext();
    }

    public final Object next() {
        return this.f1237c.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException("The XMPIterator does not support remove().");
    }
}
