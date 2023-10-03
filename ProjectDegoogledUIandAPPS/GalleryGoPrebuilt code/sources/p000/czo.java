package p000;

import java.io.File;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: czo */
/* compiled from: PG */
final /* synthetic */ class czo implements icf {

    /* renamed from: a */
    private final efr f6108a;

    /* renamed from: b */
    private final File f6109b;

    public czo(efr efr, File file) {
        this.f6108a = efr;
        this.f6109b = file;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        efr efr = this.f6108a;
        File file = this.f6109b;
        int i = czu.f6115a;
        if (((Boolean) obj).booleanValue()) {
            return ife.m12820a((Object) Optional.empty());
        }
        return gte.m10770a(efr.mo4787a(file, false), czj.f6103a, (Executor) idh.f13918a);
    }
}
