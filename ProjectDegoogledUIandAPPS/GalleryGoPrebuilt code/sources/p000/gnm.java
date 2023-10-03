package p000;

import java.io.File;
import java.util.concurrent.Callable;

/* renamed from: gnm */
/* compiled from: PG */
public final /* synthetic */ class gnm implements Callable {

    /* renamed from: a */
    private final gnn f11692a;

    /* renamed from: b */
    private final hfs f11693b;

    public gnm(gnn gnn, hfs hfs) {
        this.f11692a = gnn;
        this.f11693b = hfs;
    }

    public final Object call() {
        gnn gnn = this.f11692a;
        File file = new File(gnn.f11694a.mo7372a(this.f11693b), gnn.m10546a(gnn.f11695b));
        file.mkdirs();
        return file;
    }
}
