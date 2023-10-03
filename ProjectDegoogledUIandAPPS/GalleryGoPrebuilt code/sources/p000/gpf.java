package p000;

import android.content.Intent;
import android.content.ServiceConnection;

/* renamed from: gpf */
/* compiled from: PG */
public final /* synthetic */ class gpf implements abc {

    /* renamed from: a */
    private final gph f11783a;

    public gpf(gph gph) {
        this.f11783a = gph;
    }

    /* renamed from: a */
    public final Object mo71a(aba aba) {
        gph gph = this.f11783a;
        gpg gpg = new gpg(aba);
        dzl dzl = ((dzi) gph).f7712a;
        dzk dzk = new dzk(dzl.f7720b, dzl.f7719a, gpg);
        dzl.f7724f = new hnj(hmu.m11754a(), dzk);
        ServiceConnection serviceConnection = dzl.f7724f;
        dzk.f7715a = serviceConnection;
        ServiceConnection serviceConnection2 = (ServiceConnection) ife.m12898e((Object) serviceConnection);
        if (!dzl.f7719a.bindService(new Intent("com.google.android.apps.photos.backup.apiservice.PHOTOS_BACKUP_SERVICE").setPackage("com.google.android.apps.photos"), serviceConnection2, 1)) {
            gpg.mo6902a((Throwable) new IllegalStateException("No Photos backup service to bind to"));
        }
        return serviceConnection2;
    }
}
