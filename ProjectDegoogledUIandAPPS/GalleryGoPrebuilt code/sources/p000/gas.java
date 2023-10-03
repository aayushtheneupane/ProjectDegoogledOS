package p000;

import android.database.sqlite.SQLiteDatabase;
import java.io.File;

/* renamed from: gas */
/* compiled from: PG */
final class gas implements idw {

    /* renamed from: a */
    private final /* synthetic */ gay f10799a;

    public gas(gay gay) {
        this.f10799a = gay;
    }

    /* renamed from: a */
    public final void mo3868a(Throwable th) {
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo3867a(Object obj) {
        if (!new File(((SQLiteDatabase) obj).getPath()).exists()) {
            synchronized (this.f10799a.f10811k) {
                this.f10799a.mo6379b();
            }
        }
    }
}
