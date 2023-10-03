package p000;

/* renamed from: crx */
/* compiled from: PG */
public final class crx {

    /* renamed from: a */
    public static final String[] f5527a = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

    /* renamed from: b */
    public int f5528b = 1;

    /* renamed from: c */
    private final gus f5529c;

    public crx(gus gus) {
        this.f5529c = gus;
    }

    /* renamed from: a */
    public final void mo3792a(C0147fh fhVar) {
        if (!dvg.m6745a(fhVar.mo2634k(), f5527a)) {
            int i = this.f5528b;
            if (i == 0) {
                throw null;
            } else if (i == 1) {
                mo3791a(2);
                fhVar.mo5640a(f5527a);
            } else if (i == 3) {
                mo3791a(4);
            }
        } else {
            mo3791a(3);
        }
    }

    /* renamed from: a */
    public final void mo3791a(int i) {
        int i2 = this.f5528b;
        if (i2 == 0) {
            throw null;
        } else if (i2 != i) {
            this.f5528b = i;
            this.f5529c.mo7096a(ife.m12820a((Object) null), (Object) "HOME_FRAGMENT_DATA_SERVICE");
        }
    }
}
