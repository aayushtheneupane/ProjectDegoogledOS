package p000;

import java.io.File;
import java.util.concurrent.Callable;

/* renamed from: czn */
/* compiled from: PG */
final /* synthetic */ class czn implements Callable {

    /* renamed from: a */
    private final File f6107a;

    public czn(File file) {
        this.f6107a = file;
    }

    public final Object call() {
        File file = this.f6107a;
        int i = czu.f6115a;
        boolean z = false;
        if (file.exists() && file.canWrite()) {
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
