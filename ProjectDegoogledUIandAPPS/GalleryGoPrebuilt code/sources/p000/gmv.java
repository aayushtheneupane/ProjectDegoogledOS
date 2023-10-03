package p000;

/* renamed from: gmv */
/* compiled from: PG */
final /* synthetic */ class gmv implements hpr {

    /* renamed from: a */
    private final gmy f11637a;

    public gmv(gmy gmy) {
        this.f11637a = gmy;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        gmy gmy = this.f11637a;
        Boolean bool = (Boolean) obj;
        if (bool.booleanValue()) {
            gmy.f11640a.set(new gna(gmy.f11641b.f11662a.getSharedPreferences("accounts", 0)));
        }
        return bool;
    }
}
