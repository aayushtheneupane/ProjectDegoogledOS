package p000;

/* renamed from: gof */
/* compiled from: PG */
final /* synthetic */ class gof implements Runnable {

    /* renamed from: a */
    private final gog f11728a;

    public gof(gog gog) {
        this.f11728a = gog;
    }

    public final void run() {
        gog gog = this.f11728a;
        for (String str : gog.f11730b.databaseList()) {
            if (str.startsWith("SqliteKeyValueCache:") && str.endsWith(":Singleton") && !str.endsWith("-wal") && !str.endsWith("-shm") && !((hsu) gog.f11731c).keySet().contains(str) && !gog.f11730b.deleteDatabase(str)) {
                ((hvv) ((hvv) gog.f11729a.mo8178a()).mo8201a("com/google/apps/tiktok/cache/OrphanCacheSingletonSynclet", "lambda$sync$0", 48, "OrphanCacheSingletonSynclet.java")).mo8206a("Failed to remove orphaned cache file: %s", (Object) str);
            }
        }
    }
}
