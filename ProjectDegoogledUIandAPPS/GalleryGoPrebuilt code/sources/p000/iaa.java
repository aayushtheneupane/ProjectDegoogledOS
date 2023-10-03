package p000;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/* renamed from: iaa */
/* compiled from: PG */
final class iaa extends ife {

    /* renamed from: a */
    private final File f13710a;

    public /* synthetic */ iaa(File file) {
        this.f13710a = (File) ife.m12898e((Object) file);
    }

    /* renamed from: a */
    public final byte[] mo8317a() {
        hzy hzy = new hzy(hzy.f13703a);
        try {
            FileInputStream fileInputStream = new FileInputStream(this.f13710a);
            hzy.f13704b.addFirst(fileInputStream);
            byte[] a = hzt.m12552a((InputStream) fileInputStream, fileInputStream.getChannel().size());
            hzy.close();
            return a;
        } catch (Throwable th) {
            hzy.close();
            throw th;
        }
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f13710a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
        sb.append("Files.asByteSource(");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }
}
