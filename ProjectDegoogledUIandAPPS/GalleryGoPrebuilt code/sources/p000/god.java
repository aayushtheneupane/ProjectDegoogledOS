package p000;

import java.io.File;
import java.io.FilenameFilter;

/* renamed from: god */
/* compiled from: PG */
final /* synthetic */ class god implements FilenameFilter {

    /* renamed from: a */
    private final goe f11723a;

    public god(goe goe) {
        this.f11723a = goe;
    }

    public final boolean accept(File file, String str) {
        goe goe = this.f11723a;
        if (str.endsWith("-wal") || str.endsWith("-shm")) {
            str = str.substring(0, str.length() - 4);
        } else if (str.endsWith("-journal")) {
            str = str.substring(0, str.length() - 8);
        }
        if (!str.startsWith("SqliteKeyValueCache:") || !str.endsWith(".db") || ((hsu) goe.f11725b).keySet().contains(str)) {
            return false;
        }
        return true;
    }
}
