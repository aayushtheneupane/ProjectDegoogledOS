package p000;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/* renamed from: awy */
/* compiled from: PG */
final class awy implements awx {
    /* renamed from: a */
    public final Class mo1701a() {
        return InputStream.class;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo1703a(Object obj) {
        ((InputStream) obj).close();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo1702a(File file) {
        return new FileInputStream(file);
    }
}
