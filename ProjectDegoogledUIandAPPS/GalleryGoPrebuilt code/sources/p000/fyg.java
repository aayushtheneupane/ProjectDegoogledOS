package p000;

import java.io.File;
import java.io.FileInputStream;

/* renamed from: fyg */
/* compiled from: PG */
public final class fyg extends fyj implements fxz {

    /* renamed from: a */
    private final FileInputStream f10698a;

    /* renamed from: b */
    private final File f10699b;

    public fyg(FileInputStream fileInputStream, File file) {
        super(fileInputStream);
        this.f10698a = fileInputStream;
        this.f10699b = file;
    }

    /* renamed from: a */
    public final File mo6329a() {
        return this.f10699b;
    }

    public final void close() {
        new fyf(this).close();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final /* synthetic */ void mo6333b() {
        super.close();
    }
}
