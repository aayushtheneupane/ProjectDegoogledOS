package p000;

import android.net.Uri;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* renamed from: dhx */
/* compiled from: PG */
final /* synthetic */ class dhx implements icf {

    /* renamed from: a */
    private final dib f6574a;

    public dhx(dib dib) {
        this.f6574a = dib;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        dib dib = this.f6574a;
        ArrayList arrayList = new ArrayList();
        for (String str : (Iterable) obj) {
            arrayList.add(gte.m10772a(dib.f6589b.mo7042a(new Uri.Builder().scheme("content").authority(str).appendPath("processing").build(), dib.f6588a, (String) null, (String[]) null, (String) null).mo6895a((hpr) new dhy(str), (Executor) dib.f6590c), Exception.class, dhz.f6576a, (Executor) idh.f13918a));
        }
        return gte.m10770a(ife.m12819a((Iterable) arrayList), dia.f6587a, (Executor) idh.f13918a);
    }
}
