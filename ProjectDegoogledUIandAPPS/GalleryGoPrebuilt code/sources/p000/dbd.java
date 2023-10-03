package p000;

import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.Callable;

/* renamed from: dbd */
/* compiled from: PG */
final /* synthetic */ class dbd implements Callable {

    /* renamed from: a */
    private final File f6175a;

    public dbd(File file) {
        this.f6175a = file;
    }

    public final Object call() {
        return new FileOutputStream(this.f6175a);
    }
}
