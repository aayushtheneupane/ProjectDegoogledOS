package p000;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

/* renamed from: efn */
/* compiled from: PG */
final /* synthetic */ class efn implements Callable {

    /* renamed from: a */
    private final efr f8161a;

    /* renamed from: b */
    private final File f8162b;

    /* renamed from: c */
    private final boolean f8163c;

    public efn(efr efr, File file, boolean z) {
        this.f8161a = efr;
        this.f8162b = file;
        this.f8163c = z;
    }

    public final Object call() {
        efr efr = this.f8161a;
        File file = this.f8162b;
        boolean z = this.f8163c;
        String path = file.getPath();
        eft a = efr.f8169b.mo4788a(file);
        if (a != null) {
            efm efm = (efm) a;
            return efr.m7378a(path, efm.f8160b, efm.f8159a, z);
        }
        throw new IOException("No writable document file path found.");
    }
}
