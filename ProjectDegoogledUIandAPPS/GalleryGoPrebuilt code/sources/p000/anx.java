package p000;

import java.util.Iterator;

/* renamed from: anx */
/* compiled from: PG */
final class anx extends anw {

    /* renamed from: b */
    private final String f1231b;

    /* renamed from: c */
    private final Iterator f1232c;

    /* renamed from: d */
    private int f1233d = 0;

    /* renamed from: e */
    private final /* synthetic */ any f1234e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public anx(any any, aod aod, String str) {
        super(any);
        this.f1234e = any;
        if (aod.mo1328i().mo1379j()) {
            any.f1236b = aod.f1246a;
        }
        this.f1231b = mo1294a(aod, str, 1);
        this.f1232c = aod.mo1325f();
    }

    public final boolean hasNext() {
        if (this.f1223a != null) {
            return true;
        }
        if (!this.f1232c.hasNext()) {
            return false;
        }
        aod aod = (aod) this.f1232c.next();
        this.f1233d++;
        String str = null;
        if (aod.mo1328i().mo1379j()) {
            this.f1234e.f1236b = aod.f1246a;
        } else if (aod.f1248c != null) {
            str = mo1294a(aod, this.f1231b, this.f1233d);
        }
        if (this.f1234e.f1235a.mo1356a() && aod.mo1324e()) {
            return hasNext();
        }
        this.f1223a = m1215a(aod, this.f1234e.f1236b, str);
        return true;
    }
}
