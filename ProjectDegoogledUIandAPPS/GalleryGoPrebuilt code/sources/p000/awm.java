package p000;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* renamed from: awm */
/* compiled from: PG */
final class awm implements ari {

    /* renamed from: a */
    private final File f1798a;

    public awm(File file) {
        this.f1798a = file;
    }

    /* renamed from: a */
    public final Class mo1510a() {
        return ByteBuffer.class;
    }

    /* renamed from: b */
    public final void mo1516b() {
    }

    /* renamed from: c */
    public final void mo1517c() {
    }

    /* renamed from: d */
    public final int mo1518d() {
        return 1;
    }

    /* renamed from: a */
    public final void mo1514a(apb apb, arh arh) {
        try {
            arh.mo1525a((Object) bfd.m2405a(this.f1798a));
        } catch (IOException e) {
            arh.mo1524a((Exception) e);
        }
    }
}
