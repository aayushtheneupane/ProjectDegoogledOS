package p000;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: anw */
/* compiled from: PG */
class anw implements Iterator, p003j$.util.Iterator {

    /* renamed from: a */
    public aot f1223a = null;

    /* renamed from: b */
    private int f1224b = 0;

    /* renamed from: c */
    private aod f1225c;

    /* renamed from: d */
    private String f1226d;

    /* renamed from: e */
    private Iterator f1227e = null;

    /* renamed from: f */
    private int f1228f = 0;

    /* renamed from: g */
    private Iterator f1229g = Collections.EMPTY_LIST.iterator();

    /* renamed from: h */
    private final /* synthetic */ any f1230h;

    public anw(any any) {
        this.f1230h = any;
    }

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public anw(any any, aod aod, String str, int i) {
        this.f1230h = any;
        this.f1225c = aod;
        this.f1224b = 0;
        if (aod.mo1328i().mo1379j()) {
            any.f1236b = aod.f1246a;
        }
        this.f1226d = mo1294a(aod, str, i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo1294a(aod aod, String str, int i) {
        String str2;
        String str3;
        if (aod.f1248c == null || aod.mo1328i().mo1379j()) {
            return null;
        }
        if (!aod.f1248c.mo1328i().mo1375f()) {
            str3 = aod.f1246a;
            str2 = "/";
        } else {
            String valueOf = String.valueOf(i);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 2);
            sb.append("[");
            sb.append(valueOf);
            sb.append("]");
            str3 = sb.toString();
            str2 = "";
        }
        if (str == null || str.length() == 0) {
            return str3;
        }
        if (!this.f1230h.f1235a.mo1359a(1024)) {
            StringBuilder sb2 = new StringBuilder(str.length() + str2.length() + String.valueOf(str3).length());
            sb2.append(str);
            sb2.append(str2);
            sb2.append(str3);
            return sb2.toString();
        } else if (str3.startsWith("?")) {
            return str3.substring(1);
        } else {
            return str3;
        }
    }

    /* renamed from: a */
    protected static final aot m1215a(aod aod, String str, String str2) {
        String str3;
        if (!aod.mo1328i().mo1379j()) {
            str3 = aod.f1247b;
        } else {
            str3 = null;
        }
        return new anv(str, str2, str3);
    }

    public boolean hasNext() {
        if (this.f1223a != null) {
            return true;
        }
        int i = this.f1224b;
        if (i == 0) {
            this.f1224b = 1;
            if (this.f1225c.f1248c == null || (this.f1230h.f1235a.mo1356a() && this.f1225c.mo1324e())) {
                return hasNext();
            }
            this.f1223a = m1215a(this.f1225c, this.f1230h.f1236b, this.f1226d);
            return true;
        } else if (i != 1) {
            if (this.f1227e == null) {
                this.f1227e = this.f1225c.mo1327h();
            }
            return m1216a(this.f1227e);
        } else {
            if (this.f1227e == null) {
                this.f1227e = this.f1225c.mo1325f();
            }
            boolean a = m1216a(this.f1227e);
            if (a || !this.f1225c.mo1326g() || this.f1230h.f1235a.mo1359a(4096)) {
                return a;
            }
            this.f1224b = 2;
            this.f1227e = null;
            return hasNext();
        }
    }

    /* renamed from: a */
    private final boolean m1216a(Iterator it) {
        if (!this.f1229g.hasNext() && it.hasNext()) {
            int i = this.f1228f + 1;
            this.f1228f = i;
            this.f1229g = new anw(this.f1230h, (aod) it.next(), this.f1226d, i);
        }
        if (!this.f1229g.hasNext()) {
            return false;
        }
        this.f1223a = (aot) this.f1229g.next();
        return true;
    }

    public final Object next() {
        if (hasNext()) {
            aot aot = this.f1223a;
            this.f1223a = null;
            return aot;
        }
        throw new NoSuchElementException("There are no more nodes to return");
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
