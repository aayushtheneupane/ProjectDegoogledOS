package p000;

/* renamed from: hrs */
/* compiled from: PG */
final class hrs extends hrh {

    /* renamed from: a */
    private final Object f13318a;

    /* renamed from: b */
    private int f13319b;

    /* renamed from: c */
    private final /* synthetic */ hru f13320c;

    public hrs(hru hru, int i) {
        this.f13320c = hru;
        this.f13318a = hru.f13325d[i];
        this.f13319b = i;
    }

    public final Object getKey() {
        return this.f13318a;
    }

    public final Object getValue() {
        m11981a();
        int i = this.f13319b;
        if (i != -1) {
            return this.f13320c.f13326e[i];
        }
        return null;
    }

    public final Object setValue(Object obj) {
        m11981a();
        int i = this.f13319b;
        if (i != -1) {
            Object[] objArr = this.f13320c.f13326e;
            Object obj2 = objArr[i];
            objArr[i] = obj;
            return obj2;
        }
        this.f13320c.put(this.f13318a, obj);
        return null;
    }

    /* renamed from: a */
    private final void m11981a() {
        int i = this.f13319b;
        if (i != -1) {
            hru hru = this.f13320c;
            if (i < hru.f13328g && ife.m12891c(this.f13318a, hru.f13325d[i])) {
                return;
            }
        }
        this.f13319b = this.f13320c.mo7833a(this.f13318a);
    }
}
