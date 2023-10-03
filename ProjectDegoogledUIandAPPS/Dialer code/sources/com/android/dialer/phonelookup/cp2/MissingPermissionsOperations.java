package com.android.dialer.phonelookup.cp2;

import android.content.Context;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.phonelookup.PhoneLookupInfo;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

final class MissingPermissionsOperations {
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutor;
    private final ListeningExecutorService lightweightExecutor;

    MissingPermissionsOperations(Context context, ListeningExecutorService listeningExecutorService, ListeningExecutorService listeningExecutorService2) {
        this.appContext = context;
        this.backgroundExecutor = listeningExecutorService;
        this.lightweightExecutor = listeningExecutorService2;
    }

    static /* synthetic */ ImmutableMap lambda$getMostRecentInfoForMissingPermissions$2(ImmutableMap immutableMap) throws Exception {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        UnmodifiableIterator it = immutableMap.keySet().iterator();
        while (it.hasNext()) {
            builder.put((DialerPhoneNumber) it.next(), PhoneLookupInfo.Cp2Info.getDefaultInstance());
        }
        return builder.build();
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<ImmutableMap<DialerPhoneNumber, PhoneLookupInfo.Cp2Info>> getMostRecentInfoForMissingPermissions(ImmutableMap<DialerPhoneNumber, PhoneLookupInfo.Cp2Info> immutableMap) {
        return this.lightweightExecutor.submit(new Callable() {
            public final Object call() {
                return MissingPermissionsOperations.lambda$getMostRecentInfoForMissingPermissions$2(ImmutableMap.this);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<Boolean> isDirtyForMissingPermissions(ImmutableSet<DialerPhoneNumber> immutableSet, Predicate<PhoneLookupInfo> predicate) {
        return this.backgroundExecutor.submit(new Callable(immutableSet, predicate) {
            private final /* synthetic */ ImmutableSet f$1;
            private final /* synthetic */ Predicate f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object call() {
                return MissingPermissionsOperations.this.mo6736x6a9c1365(this.f$1, this.f$2);
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009c, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x009d, code lost:
        if (r7 != null) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a3, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a4, code lost:
        r6.addSuppressed(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a7, code lost:
        throw r8;
     */
    /* renamed from: lambda$isDirtyForMissingPermissions$1$MissingPermissionsOperations */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ java.lang.Boolean mo6736x6a9c1365(com.google.common.collect.ImmutableSet r7, java.util.function.Predicate r8) throws java.lang.Exception {
        /*
            r6 = this;
            java.util.stream.Stream r7 = r7.stream()
            com.android.dialer.phonelookup.cp2.-$$Lambda$JJBv2GZdGuVLJ_PmYqbU-dcZzJ8 r0 = com.android.dialer.phonelookup.cp2.$$Lambda$JJBv2GZdGuVLJ_PmYqbUdcZzJ8.INSTANCE
            java.util.stream.Stream r7 = r7.map(r0)
            com.android.dialer.phonelookup.cp2.-$$Lambda$MissingPermissionsOperations$wE3FcWCj9nusG0Xp_7Bnf6ddzic r0 = com.android.dialer.phonelookup.cp2.C0548x30e3bce6.INSTANCE
            java.lang.Object[] r7 = r7.toArray(r0)
            java.lang.String[] r7 = (java.lang.String[]) r7
            com.android.dialer.common.database.Selection$Builder r0 = com.android.dialer.common.database.Selection.builder()
            java.lang.String r1 = "normalized_number"
            com.android.dialer.common.database.Selection$Column r1 = com.android.dialer.common.database.Selection.column(r1)
            if (r7 != 0) goto L_0x0023
            java.util.List r7 = java.util.Collections.emptyList()
            goto L_0x0027
        L_0x0023:
            java.util.List r7 = java.util.Arrays.asList(r7)
        L_0x0027:
            com.android.dialer.common.database.Selection r7 = r1.mo5865in(r7)
            r0.and(r7)
            com.android.dialer.common.database.Selection r7 = r0.build()
            android.content.Context r6 = r6.appContext
            android.content.ContentResolver r0 = r6.getContentResolver()
            android.net.Uri r1 = com.android.dialer.phonelookup.database.contract.PhoneLookupHistoryContract.PhoneLookupHistory.CONTENT_URI
            java.lang.String r6 = "phone_lookup_info"
            java.lang.String[] r2 = new java.lang.String[]{r6}
            java.lang.String r3 = r7.getSelection()
            java.lang.String[] r4 = r7.getSelectionArgs()
            r5 = 0
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5)
            r0 = 0
            if (r7 != 0) goto L_0x0063
            java.lang.String r6 = "MissingPermissionsOperations.isDirtyForMissingPermissions"
            java.lang.String r8 = "null cursor"
            java.lang.Object[] r1 = new java.lang.Object[r0]     // Catch:{ all -> 0x009a }
            com.android.dialer.common.LogUtil.m10w(r6, r8, r1)     // Catch:{ all -> 0x009a }
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x009a }
            if (r7 == 0) goto L_0x0062
            r7.close()
        L_0x0062:
            return r6
        L_0x0063:
            boolean r1 = r7.moveToFirst()     // Catch:{ all -> 0x009a }
            if (r1 == 0) goto L_0x0092
            int r6 = r7.getColumnIndexOrThrow(r6)     // Catch:{ all -> 0x009a }
        L_0x006d:
            byte[] r1 = r7.getBlob(r6)     // Catch:{ InvalidProtocolBufferException -> 0x008b }
            com.android.dialer.phonelookup.PhoneLookupInfo r1 = com.android.dialer.phonelookup.PhoneLookupInfo.parseFrom(r1)     // Catch:{ InvalidProtocolBufferException -> 0x008b }
            boolean r1 = r8.test(r1)     // Catch:{ all -> 0x009a }
            if (r1 == 0) goto L_0x0084
            r6 = 1
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch:{ all -> 0x009a }
            r7.close()
            return r6
        L_0x0084:
            boolean r1 = r7.moveToNext()     // Catch:{ all -> 0x009a }
            if (r1 != 0) goto L_0x006d
            goto L_0x0092
        L_0x008b:
            r6 = move-exception
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch:{ all -> 0x009a }
            r8.<init>(r6)     // Catch:{ all -> 0x009a }
            throw r8     // Catch:{ all -> 0x009a }
        L_0x0092:
            r7.close()
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r0)
            return r6
        L_0x009a:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x009c }
        L_0x009c:
            r8 = move-exception
            if (r7 == 0) goto L_0x00a7
            r7.close()     // Catch:{ all -> 0x00a3 }
            goto L_0x00a7
        L_0x00a3:
            r7 = move-exception
            r6.addSuppressed(r7)
        L_0x00a7:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonelookup.cp2.MissingPermissionsOperations.mo6736x6a9c1365(com.google.common.collect.ImmutableSet, java.util.function.Predicate):java.lang.Boolean");
    }
}
