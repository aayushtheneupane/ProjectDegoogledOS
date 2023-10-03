package p000;

import java.io.File;
import java.io.FileOutputStream;

/* renamed from: fyi */
/* compiled from: PG */
public final class fyi extends fyk implements fxz, fyd {

    /* renamed from: a */
    private final FileOutputStream f10701a;

    /* renamed from: b */
    private final File f10702b;

    public fyi(FileOutputStream fileOutputStream, File file) {
        super(fileOutputStream);
        this.f10701a = fileOutputStream;
        this.f10702b = file;
    }

    /* renamed from: a */
    public final File mo6329a() {
        return this.f10702b;
    }

    public final void close() {
        new fyh(this).close();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final /* synthetic */ void mo6336c() {
        super.close();
    }

    /* renamed from: b */
    public final void mo6331b() {
        this.f10701a.getFD().sync();
    }
}
