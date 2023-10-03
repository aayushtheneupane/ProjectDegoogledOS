package p000;

import java.io.File;

/* renamed from: gar */
/* compiled from: PG */
final class gar implements idw {

    /* renamed from: a */
    private final /* synthetic */ gay f10798a;

    public gar(gay gay) {
        this.f10798a = gay;
    }

    /* renamed from: a */
    public final void mo3868a(Throwable th) {
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo3867a(Object obj) {
        new File(this.f10798a.f10802b.getDatabasePath((String) obj).getPath().concat(".bak")).delete();
    }
}
