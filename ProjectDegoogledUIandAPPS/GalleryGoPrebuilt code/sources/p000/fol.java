package p000;

/* renamed from: fol */
/* compiled from: PG */
public final class fol {
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00d2, code lost:
        r14 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00d4, code lost:
        r14 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00d2 A[ExcHandler: Error (e java.lang.Error), Splitter:B:5:0x001b] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.pm.PackageStats m9321a(android.content.Context r14) {
        /*
            p000.fxk.m9836c()
            java.lang.Class<android.os.storage.StorageManager> r0 = android.os.storage.StorageManager.class
            java.lang.Object r0 = r14.getSystemService(r0)
            android.os.storage.StorageManager r0 = (android.os.storage.StorageManager) r0
            r1 = 0
            r2 = 0
            java.lang.String r3 = "PackageStatsO"
            if (r0 != 0) goto L_0x0019
            java.lang.Object[] r14 = new java.lang.Object[r2]
            java.lang.String r0 = "StorageManager is not available"
            p000.flw.m9203e(r3, r0, r14)
            return r1
        L_0x0019:
            java.lang.Class<android.app.usage.StorageStatsManager> r4 = android.app.usage.StorageStatsManager.class
            java.lang.Object r4 = r14.getSystemService(r4)     // Catch:{ RuntimeException -> 0x00d4, Error -> 0x00d2 }
            android.app.usage.StorageStatsManager r4 = (android.app.usage.StorageStatsManager) r4     // Catch:{ RuntimeException -> 0x00d4, Error -> 0x00d2 }
            java.lang.String r14 = r14.getPackageName()     // Catch:{ RuntimeException -> 0x00d4, Error -> 0x00d2 }
            android.content.pm.PackageStats r5 = new android.content.pm.PackageStats     // Catch:{ RuntimeException -> 0x00d4, Error -> 0x00d2 }
            r5.<init>(r14)     // Catch:{ RuntimeException -> 0x00d4, Error -> 0x00d2 }
            java.util.List r0 = r0.getStorageVolumes()     // Catch:{ RuntimeException -> 0x00d4, Error -> 0x00d2 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ RuntimeException -> 0x00d4, Error -> 0x00d2 }
        L_0x0032:
            boolean r6 = r0.hasNext()     // Catch:{ RuntimeException -> 0x00d4, Error -> 0x00d2 }
            if (r6 == 0) goto L_0x00d1
            java.lang.Object r6 = r0.next()     // Catch:{ RuntimeException -> 0x00d4, Error -> 0x00d2 }
            android.os.storage.StorageVolume r6 = (android.os.storage.StorageVolume) r6     // Catch:{ RuntimeException -> 0x00d4, Error -> 0x00d2 }
            java.lang.String r7 = r6.getState()     // Catch:{ RuntimeException -> 0x00d4, Error -> 0x00d2 }
            java.lang.String r8 = "mounted"
            boolean r7 = r7.equals(r8)     // Catch:{ RuntimeException -> 0x00d4, Error -> 0x00d2 }
            if (r7 == 0) goto L_0x0032
            java.lang.String r6 = r6.getUuid()     // Catch:{ RuntimeException -> 0x00d4, Error -> 0x00d2 }
            r7 = 1
            java.lang.String[] r8 = new java.lang.String[r7]     // Catch:{ IllegalArgumentException -> 0x0062 }
            r8[r2] = r6     // Catch:{ IllegalArgumentException -> 0x0062 }
            java.lang.String r9 = "UUID for %s"
            p000.flw.m9199b(r3, r9, r8)     // Catch:{ IllegalArgumentException -> 0x0062 }
            if (r6 != 0) goto L_0x005d
            java.util.UUID r6 = android.os.storage.StorageManager.UUID_DEFAULT     // Catch:{ IllegalArgumentException -> 0x0062 }
            goto L_0x006e
        L_0x005d:
            java.util.UUID r6 = java.util.UUID.fromString(r6)     // Catch:{ IllegalArgumentException -> 0x0062 }
            goto L_0x006e
        L_0x0062:
            r8 = move-exception
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ RuntimeException -> 0x00d4, Error -> 0x00d2 }
            r7[r2] = r6     // Catch:{ RuntimeException -> 0x00d4, Error -> 0x00d2 }
            java.lang.String r6 = "Invalid UUID format: '%s'"
            p000.flw.m9203e(r3, r6, r7)     // Catch:{ RuntimeException -> 0x00d4, Error -> 0x00d2 }
            r6 = r1
        L_0x006e:
            if (r6 == 0) goto L_0x0032
            android.os.UserHandle r7 = android.os.Process.myUserHandle()     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            android.app.usage.StorageStats r7 = r4.queryStatsForPackage(r6, r14, r7)     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            java.util.UUID r8 = android.os.storage.StorageManager.UUID_DEFAULT     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            boolean r6 = r8.equals(r6)     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            if (r6 != 0) goto L_0x00a1
            long r8 = r5.externalCodeSize     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            long r10 = r7.getAppBytes()     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            long r8 = r8 + r10
            r5.externalCodeSize = r8     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            long r8 = r5.externalDataSize     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            long r10 = r7.getDataBytes()     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            long r12 = r7.getCacheBytes()     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            long r10 = r10 - r12
            long r8 = r8 + r10
            r5.externalDataSize = r8     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            long r8 = r5.externalCacheSize     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            long r6 = r7.getCacheBytes()     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            long r8 = r8 + r6
            r5.externalCacheSize = r8     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            goto L_0x0032
        L_0x00a1:
            long r8 = r5.codeSize     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            long r10 = r7.getAppBytes()     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            long r8 = r8 + r10
            r5.codeSize = r8     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            long r8 = r5.dataSize     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            long r10 = r7.getDataBytes()     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            long r12 = r7.getCacheBytes()     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            long r10 = r10 - r12
            long r8 = r8 + r10
            r5.dataSize = r8     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            long r8 = r5.cacheSize     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            long r6 = r7.getCacheBytes()     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            long r8 = r8 + r6
            r5.cacheSize = r8     // Catch:{ NameNotFoundException -> 0x00c7, IOException -> 0x00c5, RuntimeException -> 0x00c3, Error -> 0x00d2 }
            goto L_0x0032
        L_0x00c3:
            r6 = move-exception
            goto L_0x00c8
        L_0x00c5:
            r6 = move-exception
            goto L_0x00c8
        L_0x00c7:
            r6 = move-exception
        L_0x00c8:
            java.lang.String r7 = "queryStatsForPackage() call failed"
            java.lang.Object[] r8 = new java.lang.Object[r2]     // Catch:{ RuntimeException -> 0x00d4, Error -> 0x00d2 }
            p000.flw.m9200c(r3, r7, r6, r8)     // Catch:{ RuntimeException -> 0x00d4, Error -> 0x00d2 }
            goto L_0x0032
        L_0x00d1:
            return r5
        L_0x00d2:
            r14 = move-exception
            goto L_0x00d5
        L_0x00d4:
            r14 = move-exception
        L_0x00d5:
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r2 = "StorageStatsManager is not available"
            p000.flw.m9198b(r3, r2, r14, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.fol.m9321a(android.content.Context):android.content.pm.PackageStats");
    }
}
