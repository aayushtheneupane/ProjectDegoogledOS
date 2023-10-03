package p000;

import java.io.File;
import java.io.IOException;

/* renamed from: iab */
/* compiled from: PG */
public final class iab {

    /* renamed from: a */
    public static final hyy f13711a = new hzz();

    /* renamed from: b */
    public static ife m12562b(File file) {
        return new iaa(file);
    }

    /* renamed from: a */
    public static void m12561a(File file) {
        ife.m12898e((Object) file);
        File parentFile = file.getCanonicalFile().getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
            if (!parentFile.isDirectory()) {
                String valueOf = String.valueOf(file);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 39);
                sb.append("Unable to create parent directories of ");
                sb.append(valueOf);
                throw new IOException(sb.toString());
            }
        }
    }
}
