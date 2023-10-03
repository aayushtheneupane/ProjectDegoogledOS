package p000;

import java.io.File;

/* renamed from: goc */
/* compiled from: PG */
final /* synthetic */ class goc implements hpr {

    /* renamed from: a */
    private final goe f11722a;

    public goc(goe goe) {
        this.f11722a = goe;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        File[] listFiles = ((File) obj).listFiles(new god(this.f11722a));
        if (listFiles == null) {
            return null;
        }
        for (File file : listFiles) {
            if (!file.delete()) {
                ((hvv) ((hvv) goe.f11724a.mo8178a()).mo8201a("com/google/apps/tiktok/cache/OrphanCacheAccountSynclet", "lambda$clean$1", 68, "OrphanCacheAccountSynclet.java")).mo8206a("Failed to remove orphaned cache file: %s", (Object) file);
            }
        }
        return null;
    }
}
