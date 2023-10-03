package p000;

/* renamed from: hlm */
/* compiled from: PG */
public final class hlm extends hln implements hll {

    /* renamed from: a */
    public static final hln f12987a = new hlm(new C0309lf(0)).mo7555c();

    private hlm(C0309lf lfVar) {
        super((hln) null, lfVar);
    }

    public /* synthetic */ hlm(hln hln, C0309lf lfVar) {
        super(hln, lfVar);
    }

    /* renamed from: a */
    public final hln mo7552a() {
        return mo7555c();
    }

    /* renamed from: a */
    public final void mo7553a(hok hok, Object obj) {
        ife.m12876b(!this.f12990c, (Object) "Can't mutate after handing to trace");
        ife.m12898e(obj);
        ife.m12876b(!mo7554a(hok), (Object) "Key already present");
        this.f12989b.put(hok, obj);
    }
}
