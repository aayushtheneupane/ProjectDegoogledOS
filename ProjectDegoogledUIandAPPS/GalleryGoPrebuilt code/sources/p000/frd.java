package p000;

import android.system.Os;
import java.io.FileDescriptor;
import java.util.concurrent.Callable;

/* renamed from: frd */
/* compiled from: PG */
final /* synthetic */ class frd implements Callable {

    /* renamed from: a */
    private final FileDescriptor f10307a;

    public frd(FileDescriptor fileDescriptor) {
        this.f10307a = fileDescriptor;
    }

    public final Object call() {
        return Os.fstat(this.f10307a);
    }
}
