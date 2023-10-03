package com.android.dialer.calllog.datasources.phonelookup;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.support.p002v7.appcompat.R$style;
import android.util.ArrayMap;
import android.util.ArraySet;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.NumberAttributes;
import com.android.dialer.calllog.datasources.CallLogDataSource;
import com.android.dialer.calllog.datasources.CallLogMutations;
import com.android.dialer.common.Assert;
import com.android.dialer.phonelookup.PhoneLookupInfo;
import com.android.dialer.phonelookup.composite.CompositePhoneLookup;
import com.android.dialer.phonelookup.database.PhoneLookupHistoryDatabaseHelper;
import com.android.dialer.phonelookup.database.contract.PhoneLookupHistoryContract;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;

public final class PhoneLookupDataSource implements CallLogDataSource {
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutorService;
    private final CompositePhoneLookup compositePhoneLookup;
    private final ListeningExecutorService lightweightExecutorService;
    private final PhoneLookupHistoryDatabaseHelper phoneLookupHistoryDatabaseHelper;
    private final Set<String> phoneLookupHistoryRowsToDelete = new ArraySet();
    private final Map<String, PhoneLookupInfo> phoneLookupHistoryRowsToUpdate = new ArrayMap();

    private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
        if (th != null) {
            try {
                autoCloseable.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            autoCloseable.close();
        }
    }

    PhoneLookupDataSource(Context context, CompositePhoneLookup compositePhoneLookup2, ListeningExecutorService listeningExecutorService, ListeningExecutorService listeningExecutorService2, PhoneLookupHistoryDatabaseHelper phoneLookupHistoryDatabaseHelper2) {
        this.appContext = context;
        this.compositePhoneLookup = compositePhoneLookup2;
        this.backgroundExecutorService = listeningExecutorService;
        this.lightweightExecutorService = listeningExecutorService2;
        this.phoneLookupHistoryDatabaseHelper = phoneLookupHistoryDatabaseHelper2;
    }

    static /* synthetic */ PhoneLookupInfo lambda$queryPhoneLookupHistoryForNumbers$8(Map map, Map map2, DialerPhoneNumber dialerPhoneNumber) {
        PhoneLookupInfo phoneLookupInfo = (PhoneLookupInfo) map2.get((String) map.get(dialerPhoneNumber));
        return phoneLookupInfo == null ? PhoneLookupInfo.getDefaultInstance() : phoneLookupInfo;
    }

    private void updateContentValues(ContentValues contentValues, PhoneLookupInfo phoneLookupInfo) {
        contentValues.put("number_attributes", ((NumberAttributes) R$style.fromPhoneLookupInfo(phoneLookupInfo).build()).toByteArray());
    }

    public ListenableFuture<Void> clearData() {
        return Futures.transform(Futures.allAsList((ListenableFuture<? extends V>[]) new ListenableFuture[]{this.compositePhoneLookup.clearData(), this.phoneLookupHistoryDatabaseHelper.delete()}), $$Lambda$PhoneLookupDataSource$D7HjFX4BLc17pftdDg2qh2N8D4U.INSTANCE, MoreExecutors.directExecutor());
    }

    public ListenableFuture<Void> fill(CallLogMutations callLogMutations) {
        Object[] objArr = {Integer.valueOf(callLogMutations.getInserts().size()), Integer.valueOf(callLogMutations.getUpdates().size()), Integer.valueOf(callLogMutations.getDeletes().size())};
        this.phoneLookupHistoryRowsToUpdate.clear();
        this.phoneLookupHistoryRowsToDelete.clear();
        ListenableFuture submit = this.backgroundExecutorService.submit(new Callable(callLogMutations) {
            private final /* synthetic */ CallLogMutations f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return PhoneLookupDataSource.this.lambda$fill$1$PhoneLookupDataSource(this.f$1);
            }
        });
        ListenableFuture transform = Futures.transform(submit, new Function() {
            public final Object apply(Object obj) {
                return PhoneLookupDataSource.this.lambda$fill$2$PhoneLookupDataSource((Map) obj);
            }
        }, this.backgroundExecutorService);
        CompositePhoneLookup compositePhoneLookup2 = this.compositePhoneLookup;
        Objects.requireNonNull(compositePhoneLookup2);
        ListenableFuture transformAsync = Futures.transformAsync(transform, new AsyncFunction() {
            public final ListenableFuture apply(Object obj) {
                return CompositePhoneLookup.this.getMostRecentInfo((ImmutableMap) obj);
            }
        }, this.lightweightExecutorService);
        return Futures.transform(Futures.whenAllSucceed(submit, transformAsync, transform).call(new Callable(submit, transform, transformAsync, callLogMutations) {
            private final /* synthetic */ ListenableFuture f$1;
            private final /* synthetic */ ListenableFuture f$2;
            private final /* synthetic */ ListenableFuture f$3;
            private final /* synthetic */ CallLogMutations f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final Object call() {
                return PhoneLookupDataSource.this.lambda$fill$3$PhoneLookupDataSource(this.f$1, this.f$2, this.f$3, this.f$4);
            }
        }, this.backgroundExecutorService), new Function(callLogMutations) {
            private final /* synthetic */ CallLogMutations f$1;

            {
                this.f$1 = r2;
            }

            public final Object apply(Object obj) {
                return PhoneLookupDataSource.this.lambda$fill$4$PhoneLookupDataSource(this.f$1, (ImmutableMap) obj);
            }
        }, this.lightweightExecutorService);
    }

    public String getLoggingName() {
        return "PhoneLookupDataSource";
    }

    public ListenableFuture<Boolean> isDirty() {
        ListenableFuture submit = this.backgroundExecutorService.submit(new Callable() {
            public final Object call() {
                return PhoneLookupDataSource.this.lambda$isDirty$0$PhoneLookupDataSource();
            }
        });
        CompositePhoneLookup compositePhoneLookup2 = this.compositePhoneLookup;
        Objects.requireNonNull(compositePhoneLookup2);
        return Futures.transformAsync(submit, new AsyncFunction() {
            public final ListenableFuture apply(Object obj) {
                return CompositePhoneLookup.this.isDirty((ImmutableSet) obj);
            }
        }, this.lightweightExecutorService);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007c, code lost:
        if (r10 != null) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00c7, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00c8, code lost:
        if (r10 != null) goto L_0x00ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ca, code lost:
        $closeResource(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00cd, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ java.util.Map lambda$fill$1$PhoneLookupDataSource(com.android.dialer.calllog.datasources.CallLogMutations r10) throws java.lang.Exception {
        /*
            r9 = this;
            android.content.Context r9 = r9.appContext
            android.util.ArrayMap r0 = new android.util.ArrayMap
            r0.<init>()
            android.util.ArrayMap r10 = r10.getInserts()
            java.util.Set r10 = r10.entrySet()
            java.util.Iterator r10 = r10.iterator()
        L_0x0013:
            boolean r1 = r10.hasNext()
            java.lang.String r2 = "number"
            if (r1 == 0) goto L_0x0058
            java.lang.Object r1 = r10.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r3 = r1.getKey()
            java.lang.Long r3 = (java.lang.Long) r3
            long r3 = r3.longValue()
            java.lang.Object r1 = r1.getValue()
            android.content.ContentValues r1 = (android.content.ContentValues) r1
            byte[] r1 = r1.getAsByteArray(r2)     // Catch:{ InvalidProtocolBufferException -> 0x0051 }
            com.android.dialer.DialerPhoneNumber r1 = com.android.dialer.DialerPhoneNumber.parseFrom(r1)     // Catch:{ InvalidProtocolBufferException -> 0x0051 }
            java.lang.Object r2 = r0.get(r1)
            java.util.Set r2 = (java.util.Set) r2
            if (r2 != 0) goto L_0x0049
            android.util.ArraySet r2 = new android.util.ArraySet
            r2.<init>()
            r0.put(r1, r2)
        L_0x0049:
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r2.add(r1)
            goto L_0x0013
        L_0x0051:
            r9 = move-exception
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            r10.<init>(r9)
            throw r10
        L_0x0058:
            android.content.ContentResolver r3 = r9.getContentResolver()
            android.net.Uri r4 = com.android.dialer.calllog.database.contract.AnnotatedCallLogContract.AnnotatedCallLog.CONTENT_URI
            java.lang.String r9 = "_id"
            java.lang.String[] r5 = new java.lang.String[]{r9, r2}
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r10 = r3.query(r4, r5, r6, r7, r8)
            r1 = 0
            if (r10 != 0) goto L_0x0082
            java.lang.String r9 = "PhoneLookupDataSource.collectIdAndNumberFromAnnotatedCallLogAndPendingInserts"
            java.lang.String r0 = "null cursor"
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x00c5 }
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r9, (java.lang.String) r0, (java.lang.Object[]) r2)     // Catch:{ all -> 0x00c5 }
            com.google.common.collect.ImmutableMap r0 = com.google.common.collect.ImmutableMap.m82of()     // Catch:{ all -> 0x00c5 }
            if (r10 == 0) goto L_0x00c4
        L_0x007e:
            $closeResource(r1, r10)
            goto L_0x00c4
        L_0x0082:
            boolean r3 = r10.moveToFirst()     // Catch:{ all -> 0x00c5 }
            if (r3 == 0) goto L_0x007e
            int r9 = r10.getColumnIndexOrThrow(r9)     // Catch:{ all -> 0x00c5 }
            int r2 = r10.getColumnIndexOrThrow(r2)     // Catch:{ all -> 0x00c5 }
        L_0x0090:
            long r3 = r10.getLong(r9)     // Catch:{ all -> 0x00c5 }
            byte[] r5 = r10.getBlob(r2)     // Catch:{ all -> 0x00c5 }
            if (r5 != 0) goto L_0x009b
            goto L_0x00b6
        L_0x009b:
            com.android.dialer.DialerPhoneNumber r5 = com.android.dialer.DialerPhoneNumber.parseFrom(r5)     // Catch:{ InvalidProtocolBufferException -> 0x00bd }
            java.lang.Object r6 = r0.get(r5)     // Catch:{ all -> 0x00c5 }
            java.util.Set r6 = (java.util.Set) r6     // Catch:{ all -> 0x00c5 }
            if (r6 != 0) goto L_0x00af
            android.util.ArraySet r6 = new android.util.ArraySet     // Catch:{ all -> 0x00c5 }
            r6.<init>()     // Catch:{ all -> 0x00c5 }
            r0.put(r5, r6)     // Catch:{ all -> 0x00c5 }
        L_0x00af:
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x00c5 }
            r6.add(r3)     // Catch:{ all -> 0x00c5 }
        L_0x00b6:
            boolean r3 = r10.moveToNext()     // Catch:{ all -> 0x00c5 }
            if (r3 != 0) goto L_0x0090
            goto L_0x007e
        L_0x00bd:
            r9 = move-exception
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00c5 }
            r0.<init>(r9)     // Catch:{ all -> 0x00c5 }
            throw r0     // Catch:{ all -> 0x00c5 }
        L_0x00c4:
            return r0
        L_0x00c5:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x00c7 }
        L_0x00c7:
            r0 = move-exception
            if (r10 == 0) goto L_0x00cd
            $closeResource(r9, r10)
        L_0x00cd:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.calllog.datasources.phonelookup.PhoneLookupDataSource.lambda$fill$1$PhoneLookupDataSource(com.android.dialer.calllog.datasources.CallLogMutations):java.util.Map");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a3, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a4, code lost:
        if (r3 != null) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a6, code lost:
        $closeResource(r10, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a9, code lost:
        throw r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ com.google.common.collect.ImmutableMap lambda$fill$2$PhoneLookupDataSource(java.util.Map r11) {
        /*
            r10 = this;
            android.content.Context r10 = r10.appContext
            java.util.Set r11 = r11.keySet()
            com.android.dialer.calllog.datasources.phonelookup.-$$Lambda$-mKsxw9doKrl6qslGhJrKqqpAtg r0 = com.android.dialer.calllog.datasources.phonelookup.$$Lambda$mKsxw9doKrl6qslGhJrKqqpAtg.INSTANCE
            java.util.Map r0 = com.google.common.collect.Collections2.asMap(r11, r0)
            java.util.Collection r1 = r0.values()
            r2 = 0
            java.lang.String[] r3 = new java.lang.String[r2]
            java.lang.Object[] r1 = r1.toArray(r3)
            r7 = r1
            java.lang.String[] r7 = (java.lang.String[]) r7
            int r1 = r7.length
            java.lang.String[] r1 = new java.lang.String[r1]
            java.lang.String r3 = "?"
            java.util.Arrays.fill(r1, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "normalized_number in ("
            r3.append(r4)
            java.lang.String r4 = ","
            java.lang.String r1 = android.text.TextUtils.join(r4, r1)
            r3.append(r1)
            java.lang.String r1 = ")"
            r3.append(r1)
            java.lang.String r6 = r3.toString()
            android.util.ArrayMap r1 = new android.util.ArrayMap
            r1.<init>()
            android.content.ContentResolver r3 = r10.getContentResolver()
            android.net.Uri r4 = com.android.dialer.phonelookup.database.contract.PhoneLookupHistoryContract.PhoneLookupHistory.CONTENT_URI
            java.lang.String r10 = "phone_lookup_info"
            java.lang.String r9 = "normalized_number"
            java.lang.String[] r5 = new java.lang.String[]{r9, r10}
            r8 = 0
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8)
            if (r3 != 0) goto L_0x0062
            java.lang.String r10 = "PhoneLookupDataSource.queryPhoneLookupHistoryForNumbers"
            java.lang.String r4 = "null cursor"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x00a1 }
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r10, (java.lang.String) r4, (java.lang.Object[]) r2)     // Catch:{ all -> 0x00a1 }
            goto L_0x008d
        L_0x0062:
            boolean r2 = r3.moveToFirst()     // Catch:{ all -> 0x00a1 }
            if (r2 == 0) goto L_0x008d
            int r2 = r3.getColumnIndexOrThrow(r9)     // Catch:{ all -> 0x00a1 }
            int r10 = r3.getColumnIndexOrThrow(r10)     // Catch:{ all -> 0x00a1 }
        L_0x0070:
            java.lang.String r4 = r3.getString(r2)     // Catch:{ all -> 0x00a1 }
            byte[] r5 = r3.getBlob(r10)     // Catch:{ InvalidProtocolBufferException -> 0x0086 }
            com.android.dialer.phonelookup.PhoneLookupInfo r5 = com.android.dialer.phonelookup.PhoneLookupInfo.parseFrom(r5)     // Catch:{ InvalidProtocolBufferException -> 0x0086 }
            r1.put(r4, r5)     // Catch:{ all -> 0x00a1 }
            boolean r4 = r3.moveToNext()     // Catch:{ all -> 0x00a1 }
            if (r4 != 0) goto L_0x0070
            goto L_0x008d
        L_0x0086:
            r10 = move-exception
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00a1 }
            r11.<init>(r10)     // Catch:{ all -> 0x00a1 }
            throw r11     // Catch:{ all -> 0x00a1 }
        L_0x008d:
            if (r3 == 0) goto L_0x0093
            r10 = 0
            $closeResource(r10, r3)
        L_0x0093:
            com.android.dialer.calllog.datasources.phonelookup.-$$Lambda$PhoneLookupDataSource$R5WGoAG5sxS3JsZvDX3FLzJrZyg r10 = new com.android.dialer.calllog.datasources.phonelookup.-$$Lambda$PhoneLookupDataSource$R5WGoAG5sxS3JsZvDX3FLzJrZyg
            r10.<init>(r0, r1)
            java.util.Map r10 = com.google.common.collect.Collections2.asMap(r11, r10)
            com.google.common.collect.ImmutableMap r10 = com.google.common.collect.ImmutableMap.copyOf(r10)
            return r10
        L_0x00a1:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x00a3 }
        L_0x00a3:
            r11 = move-exception
            if (r3 == 0) goto L_0x00a9
            $closeResource(r10, r3)
        L_0x00a9:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.calllog.datasources.phonelookup.PhoneLookupDataSource.lambda$fill$2$PhoneLookupDataSource(java.util.Map):com.google.common.collect.ImmutableMap");
    }

    public /* synthetic */ ImmutableMap lambda$fill$3$PhoneLookupDataSource(ListenableFuture listenableFuture, ListenableFuture listenableFuture2, ListenableFuture listenableFuture3, CallLogMutations callLogMutations) throws Exception {
        Collection collection;
        Map map = (Map) listenableFuture.get();
        ImmutableMap immutableMap = (ImmutableMap) listenableFuture2.get();
        ImmutableMap immutableMap2 = (ImmutableMap) listenableFuture3.get();
        ImmutableMap.Builder builder = ImmutableMap.builder();
        UnmodifiableIterator it = immutableMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            PhoneLookupInfo phoneLookupInfo = (PhoneLookupInfo) entry.getValue();
            for (Long put : (Set) map.get((DialerPhoneNumber) entry.getKey())) {
                builder.put(put, phoneLookupInfo);
            }
        }
        ImmutableMap build = builder.build();
        for (Map.Entry next : callLogMutations.getInserts().entrySet()) {
            long longValue = ((Long) next.getKey()).longValue();
            ContentValues contentValues = (ContentValues) next.getValue();
            PhoneLookupInfo phoneLookupInfo2 = (PhoneLookupInfo) build.get(Long.valueOf(longValue));
            if (phoneLookupInfo2 != null) {
                updateContentValues(contentValues, phoneLookupInfo2);
            }
        }
        Set<String> set = this.phoneLookupHistoryRowsToDelete;
        if (callLogMutations.getDeletes().isEmpty()) {
            collection = ImmutableSet.m86of();
        } else {
            ArrayMap arrayMap = new ArrayMap();
            for (Map.Entry entry2 : map.entrySet()) {
                Set set2 = (Set) entry2.getValue();
                String normalizedNumber = ((DialerPhoneNumber) entry2.getKey()).getNormalizedNumber();
                Set set3 = (Set) arrayMap.get(normalizedNumber);
                if (set3 == null) {
                    set3 = new ArraySet();
                    arrayMap.put(normalizedNumber, set3);
                }
                set3.addAll(set2);
            }
            ArraySet arraySet = new ArraySet();
            for (Map.Entry entry3 : arrayMap.entrySet()) {
                String str = (String) entry3.getKey();
                Set set4 = (Set) entry3.getValue();
                set4.removeAll(callLogMutations.getDeletes());
                if (set4.isEmpty()) {
                    arraySet.add(str);
                }
            }
            collection = arraySet;
        }
        set.addAll(collection);
        ImmutableMap.Builder builder2 = ImmutableMap.builder();
        UnmodifiableIterator it2 = immutableMap2.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry4 = (Map.Entry) it2.next();
            DialerPhoneNumber dialerPhoneNumber = (DialerPhoneNumber) entry4.getKey();
            PhoneLookupInfo phoneLookupInfo3 = (PhoneLookupInfo) entry4.getValue();
            if (!((PhoneLookupInfo) immutableMap.get(dialerPhoneNumber)).equals(phoneLookupInfo3)) {
                for (Long put2 : (Set) map.get(dialerPhoneNumber)) {
                    builder2.put(put2, phoneLookupInfo3);
                }
                this.phoneLookupHistoryRowsToUpdate.put(dialerPhoneNumber.getNormalizedNumber(), phoneLookupInfo3);
            }
        }
        return builder2.build();
    }

    public /* synthetic */ Void lambda$fill$4$PhoneLookupDataSource(CallLogMutations callLogMutations, ImmutableMap immutableMap) {
        UnmodifiableIterator it = immutableMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            long longValue = ((Long) entry.getKey()).longValue();
            PhoneLookupInfo phoneLookupInfo = (PhoneLookupInfo) entry.getValue();
            ContentValues contentValues = callLogMutations.getInserts().get(Long.valueOf(longValue));
            if (contentValues != null) {
                updateContentValues(contentValues, phoneLookupInfo);
            } else {
                ContentValues contentValues2 = callLogMutations.getUpdates().get(Long.valueOf(longValue));
                if (contentValues2 != null) {
                    updateContentValues(contentValues2, phoneLookupInfo);
                } else {
                    ContentValues contentValues3 = new ContentValues();
                    updateContentValues(contentValues3, phoneLookupInfo);
                    callLogMutations.getUpdates().put(Long.valueOf(longValue), contentValues3);
                }
            }
        }
        Object[] objArr = {Integer.valueOf(callLogMutations.getInserts().size()), Integer.valueOf(callLogMutations.getUpdates().size()), Integer.valueOf(callLogMutations.getDeletes().size())};
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0060, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0061, code lost:
        if (r1 != null) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0063, code lost:
        $closeResource(r7, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0066, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ com.google.common.collect.ImmutableSet lambda$isDirty$0$PhoneLookupDataSource() throws java.lang.Exception {
        /*
            r7 = this;
            android.content.Context r7 = r7.appContext
            com.google.common.collect.ImmutableSet$Builder r0 = com.google.common.collect.ImmutableSet.builder()
            android.content.ContentResolver r1 = r7.getContentResolver()
            android.net.Uri r2 = com.android.dialer.calllog.database.contract.AnnotatedCallLogContract.AnnotatedCallLog.DISTINCT_NUMBERS_CONTENT_URI
            java.lang.String r7 = "number"
            java.lang.String[] r3 = new java.lang.String[]{r7}
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6)
            r2 = 0
            if (r1 != 0) goto L_0x0030
            java.lang.String r7 = "PhoneLookupDataSource.queryDistinctDialerPhoneNumbersFromAnnotatedCallLog"
            java.lang.String r3 = "null cursor"
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x005e }
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r7, (java.lang.String) r3, (java.lang.Object[]) r4)     // Catch:{ all -> 0x005e }
            com.google.common.collect.ImmutableSet r7 = r0.build()     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x005d
            $closeResource(r2, r1)
            goto L_0x005d
        L_0x0030:
            boolean r3 = r1.moveToFirst()     // Catch:{ all -> 0x005e }
            if (r3 == 0) goto L_0x0056
            int r7 = r1.getColumnIndexOrThrow(r7)     // Catch:{ all -> 0x005e }
        L_0x003a:
            byte[] r3 = r1.getBlob(r7)     // Catch:{ all -> 0x005e }
            if (r3 != 0) goto L_0x0041
            goto L_0x0048
        L_0x0041:
            com.android.dialer.DialerPhoneNumber r3 = com.android.dialer.DialerPhoneNumber.parseFrom(r3)     // Catch:{ InvalidProtocolBufferException -> 0x004f }
            r0.add((java.lang.Object) r3)     // Catch:{ InvalidProtocolBufferException -> 0x004f }
        L_0x0048:
            boolean r3 = r1.moveToNext()     // Catch:{ all -> 0x005e }
            if (r3 != 0) goto L_0x003a
            goto L_0x0056
        L_0x004f:
            r7 = move-exception
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x005e }
            r0.<init>(r7)     // Catch:{ all -> 0x005e }
            throw r0     // Catch:{ all -> 0x005e }
        L_0x0056:
            $closeResource(r2, r1)
            com.google.common.collect.ImmutableSet r7 = r0.build()
        L_0x005d:
            return r7
        L_0x005e:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0060 }
        L_0x0060:
            r0 = move-exception
            if (r1 == 0) goto L_0x0066
            $closeResource(r7, r1)
        L_0x0066:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.calllog.datasources.phonelookup.PhoneLookupDataSource.lambda$isDirty$0$PhoneLookupDataSource():com.google.common.collect.ImmutableSet");
    }

    public /* synthetic */ Void lambda$onSuccessfulFill$5$PhoneLookupDataSource() throws Exception {
        Context context = this.appContext;
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        for (Map.Entry next : this.phoneLookupHistoryRowsToUpdate.entrySet()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("phone_lookup_info", ((PhoneLookupInfo) next.getValue()).toByteArray());
            contentValues.put("last_modified", Long.valueOf(currentTimeMillis));
            arrayList.add(ContentProviderOperation.newUpdate(PhoneLookupHistoryContract.PhoneLookupHistory.contentUriForNumber((String) next.getKey())).withValues(contentValues).build());
        }
        for (String contentUriForNumber : this.phoneLookupHistoryRowsToDelete) {
            arrayList.add(ContentProviderOperation.newDelete(PhoneLookupHistoryContract.PhoneLookupHistory.contentUriForNumber(contentUriForNumber)).build());
        }
        Assert.isNotNull(context.getContentResolver().applyBatch(PhoneLookupHistoryContract.AUTHORITY, arrayList));
        return null;
    }

    public /* synthetic */ ListenableFuture lambda$onSuccessfulFill$6$PhoneLookupDataSource(Void voidR) throws Exception {
        return this.compositePhoneLookup.onSuccessfulBulkUpdate();
    }

    public ListenableFuture<Void> onSuccessfulFill() {
        return Futures.transformAsync(this.backgroundExecutorService.submit(new Callable() {
            public final Object call() {
                return PhoneLookupDataSource.this.lambda$onSuccessfulFill$5$PhoneLookupDataSource();
            }
        }), new AsyncFunction() {
            public final ListenableFuture apply(Object obj) {
                return PhoneLookupDataSource.this.lambda$onSuccessfulFill$6$PhoneLookupDataSource((Void) obj);
            }
        }, this.lightweightExecutorService);
    }

    public void registerContentObservers() {
        this.compositePhoneLookup.registerContentObservers();
    }

    public void unregisterContentObservers() {
        this.compositePhoneLookup.unregisterContentObservers();
    }
}
