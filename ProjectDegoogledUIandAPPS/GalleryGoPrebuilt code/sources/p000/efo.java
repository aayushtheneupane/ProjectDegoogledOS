package p000;

import java.io.File;

/* renamed from: efo */
/* compiled from: PG */
final /* synthetic */ class efo implements icf {

    /* renamed from: a */
    private final File f8164a;

    /* renamed from: b */
    private final boolean f8165b;

    public efo(File file, boolean z) {
        this.f8164a = file;
        this.f8165b = z;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        File file = this.f8164a;
        efq efq = (efq) obj;
        return ife.m12820a((Object) efr.m7378a(file.getPath(), efq.mo4776b(), efq.mo4775a(), this.f8165b));
    }
}
