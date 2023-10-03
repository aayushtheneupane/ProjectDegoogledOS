package p000;

/* renamed from: hve */
/* compiled from: PG */
final class hve extends htk {

    /* renamed from: a */
    public static final hve f13461a = new hve(new hut());

    /* renamed from: b */
    public final transient hut f13462b;

    /* renamed from: c */
    private final transient int f13463c;

    /* renamed from: d */
    private transient hto f13464d;

    /* renamed from: h */
    public final boolean mo7885h() {
        return false;
    }

    public final int size() {
        return this.f13463c;
    }

    public hve(hut hut) {
        this.f13462b = hut;
        long j = 0;
        for (int i = 0; i < hut.f13431c; i++) {
            j += (long) hut.mo8109c(i);
        }
        this.f13463c = ife.m12862b(j);
    }

    /* renamed from: a */
    public final int mo7769a(Object obj) {
        return this.f13462b.mo8106b(obj);
    }

    /* renamed from: i */
    public final hto mo7794e() {
        hto hto = this.f13464d;
        if (hto != null) {
            return hto;
        }
        hvc hvc = new hvc(this);
        this.f13464d = hvc;
        return hvc;
    }

    /* renamed from: a */
    public final hun mo7987a(int i) {
        return this.f13462b.mo8110d(i);
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new hvd(this);
    }
}
