package p000;

import android.net.Uri;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: dba */
/* compiled from: PG */
final /* synthetic */ class dba implements hpr {

    /* renamed from: a */
    private final ebi f6166a;

    /* renamed from: b */
    private final iek f6167b;

    public dba(ebi ebi, iek iek) {
        this.f6166a = ebi;
        this.f6167b = iek;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        ebi ebi = this.f6166a;
        iek iek = this.f6167b;
        Uri uri = ((abt) obj).f106b;
        return dbf.m5840a(gpc.m10580a((Callable) new dbc(ebi, uri), (Executor) iek), uri);
    }
}
