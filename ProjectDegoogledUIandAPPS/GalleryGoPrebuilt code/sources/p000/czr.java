package p000;

import java.io.File;
import java.util.concurrent.Callable;

/* renamed from: czr */
/* compiled from: PG */
final /* synthetic */ class czr implements Callable {

    /* renamed from: a */
    private final File f6113a;

    public czr(File file) {
        this.f6113a = file;
    }

    public final Object call() {
        File file = this.f6113a;
        int i = czu.f6115a;
        boolean z = true;
        if (file.exists() && !file.delete()) {
            z = false;
        }
        return Boolean.valueOf(z);
    }
}
