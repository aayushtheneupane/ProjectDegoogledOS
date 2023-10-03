package com.android.dialer.phonelookup.blockednumber;

import android.content.Context;
import android.provider.BlockedNumberContract;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.blocking.FilteredNumberCompat;
import com.android.dialer.calllog.observer.MarkDirtyObserver;
import com.android.dialer.common.LogUtil;
import com.android.dialer.phonelookup.PhoneLookup;
import com.android.dialer.phonelookup.PhoneLookupInfo;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.concurrent.Callable;

public class SystemBlockedNumberPhoneLookup implements PhoneLookup<PhoneLookupInfo.SystemBlockedNumberInfo> {
    private final Context appContext;
    private final ListeningExecutorService executorService;
    private final MarkDirtyObserver markDirtyObserver;

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

    SystemBlockedNumberPhoneLookup(Context context, ListeningExecutorService listeningExecutorService, MarkDirtyObserver markDirtyObserver2) {
        this.appContext = context;
        this.executorService = listeningExecutorService;
        this.markDirtyObserver = markDirtyObserver2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x004b, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x004c, code lost:
        $closeResource(r12, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004f, code lost:
        throw r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0093, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0094, code lost:
        $closeResource(r13, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0097, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.common.collect.ImmutableMap<com.android.dialer.DialerPhoneNumber, com.android.dialer.phonelookup.PhoneLookupInfo.SystemBlockedNumberInfo> queryNumbers(com.google.common.collect.ImmutableSet<com.android.dialer.DialerPhoneNumber> r13) {
        /*
            r12 = this;
            com.android.dialer.common.Assert.isWorkerThread()
            com.android.dialer.phonenumberproto.PartitionedNumbers r0 = new com.android.dialer.phonenumberproto.PartitionedNumbers
            r0.<init>(r13)
            android.util.ArraySet r1 = new android.util.ArraySet
            r1.<init>()
            java.lang.String r2 = "e164_number"
            com.android.dialer.common.database.Selection$Column r3 = com.android.dialer.common.database.Selection.column(r2)
            com.google.common.collect.ImmutableSet r4 = r0.validE164Numbers()
            com.android.dialer.common.database.Selection r3 = r3.mo5865in(r4)
            android.content.Context r4 = r12.appContext
            android.content.ContentResolver r5 = r4.getContentResolver()
            android.net.Uri r6 = android.provider.BlockedNumberContract.BlockedNumbers.CONTENT_URI
            java.lang.String[] r7 = new java.lang.String[]{r2}
            java.lang.String r8 = r3.getSelection()
            java.lang.String[] r9 = r3.getSelectionArgs()
            r10 = 0
            android.database.Cursor r2 = r5.query(r6, r7, r8, r9, r10)
        L_0x0034:
            r3 = 0
            if (r2 == 0) goto L_0x0050
            boolean r4 = r2.moveToNext()     // Catch:{ all -> 0x0049 }
            if (r4 == 0) goto L_0x0050
            java.lang.String r3 = r2.getString(r3)     // Catch:{ all -> 0x0049 }
            com.google.common.collect.ImmutableSet r3 = r0.dialerPhoneNumbersForValidE164(r3)     // Catch:{ all -> 0x0049 }
            r1.addAll(r3)     // Catch:{ all -> 0x0049 }
            goto L_0x0034
        L_0x0049:
            r12 = move-exception
            throw r12     // Catch:{ all -> 0x004b }
        L_0x004b:
            r13 = move-exception
            $closeResource(r12, r2)
            throw r13
        L_0x0050:
            r4 = 0
            if (r2 == 0) goto L_0x0056
            $closeResource(r4, r2)
        L_0x0056:
            java.lang.String r2 = "original_number"
            com.android.dialer.common.database.Selection$Column r5 = com.android.dialer.common.database.Selection.column(r2)
            com.google.common.collect.ImmutableSet r6 = r0.invalidNumbers()
            com.android.dialer.common.database.Selection r5 = r5.mo5865in(r6)
            android.content.Context r12 = r12.appContext
            android.content.ContentResolver r6 = r12.getContentResolver()
            android.net.Uri r7 = android.provider.BlockedNumberContract.BlockedNumbers.CONTENT_URI
            java.lang.String[] r8 = new java.lang.String[]{r2}
            java.lang.String r9 = r5.getSelection()
            java.lang.String[] r10 = r5.getSelectionArgs()
            r11 = 0
            android.database.Cursor r12 = r6.query(r7, r8, r9, r10, r11)
        L_0x007d:
            if (r12 == 0) goto L_0x0098
            boolean r2 = r12.moveToNext()     // Catch:{ all -> 0x0091 }
            if (r2 == 0) goto L_0x0098
            java.lang.String r2 = r12.getString(r3)     // Catch:{ all -> 0x0091 }
            com.google.common.collect.ImmutableSet r2 = r0.dialerPhoneNumbersForInvalid(r2)     // Catch:{ all -> 0x0091 }
            r1.addAll(r2)     // Catch:{ all -> 0x0091 }
            goto L_0x007d
        L_0x0091:
            r13 = move-exception
            throw r13     // Catch:{ all -> 0x0093 }
        L_0x0093:
            r0 = move-exception
            $closeResource(r13, r12)
            throw r0
        L_0x0098:
            if (r12 == 0) goto L_0x009d
            $closeResource(r4, r12)
        L_0x009d:
            com.google.common.collect.ImmutableMap$Builder r12 = com.google.common.collect.ImmutableMap.builder()
            com.google.common.collect.UnmodifiableIterator r13 = r13.iterator()
        L_0x00a5:
            boolean r0 = r13.hasNext()
            if (r0 == 0) goto L_0x00cd
            java.lang.Object r0 = r13.next()
            com.android.dialer.DialerPhoneNumber r0 = (com.android.dialer.DialerPhoneNumber) r0
            com.android.dialer.phonelookup.PhoneLookupInfo$SystemBlockedNumberInfo$Builder r2 = com.android.dialer.phonelookup.PhoneLookupInfo.SystemBlockedNumberInfo.newBuilder()
            boolean r3 = r1.contains(r0)
            if (r3 == 0) goto L_0x00be
            com.android.dialer.phonelookup.PhoneLookupInfo$BlockedState r3 = com.android.dialer.phonelookup.PhoneLookupInfo.BlockedState.BLOCKED
            goto L_0x00c0
        L_0x00be:
            com.android.dialer.phonelookup.PhoneLookupInfo$BlockedState r3 = com.android.dialer.phonelookup.PhoneLookupInfo.BlockedState.NOT_BLOCKED
        L_0x00c0:
            r2.setBlockedState(r3)
            com.google.protobuf.GeneratedMessageLite r2 = r2.build()
            com.android.dialer.phonelookup.PhoneLookupInfo$SystemBlockedNumberInfo r2 = (com.android.dialer.phonelookup.PhoneLookupInfo.SystemBlockedNumberInfo) r2
            r12.put(r0, r2)
            goto L_0x00a5
        L_0x00cd:
            com.google.common.collect.ImmutableMap r12 = r12.build()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonelookup.blockednumber.SystemBlockedNumberPhoneLookup.queryNumbers(com.google.common.collect.ImmutableSet):com.google.common.collect.ImmutableMap");
    }

    public ListenableFuture<Void> clearData() {
        return Futures.immediateFuture(null);
    }

    public String getLoggingName() {
        return "SystemBlockedNumberPhoneLookup";
    }

    public ListenableFuture<ImmutableMap<DialerPhoneNumber, PhoneLookupInfo.SystemBlockedNumberInfo>> getMostRecentInfo(ImmutableMap<DialerPhoneNumber, PhoneLookupInfo.SystemBlockedNumberInfo> immutableMap) {
        LogUtil.enterBlock("SystemBlockedNumberPhoneLookup.getMostRecentPhoneLookupInfo");
        if (!FilteredNumberCompat.useNewFiltering(this.appContext)) {
            return Futures.immediateFuture(immutableMap);
        }
        return this.executorService.submit(new Callable(immutableMap) {
            private final /* synthetic */ ImmutableMap f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return SystemBlockedNumberPhoneLookup.this.lambda$getMostRecentInfo$1$SystemBlockedNumberPhoneLookup(this.f$1);
            }
        });
    }

    public Object getSubMessage(PhoneLookupInfo phoneLookupInfo) {
        return phoneLookupInfo.getSystemBlockedNumberInfo();
    }

    public ListenableFuture<Boolean> isDirty(ImmutableSet<DialerPhoneNumber> immutableSet) {
        return Futures.immediateFuture(false);
    }

    public /* synthetic */ ImmutableMap lambda$getMostRecentInfo$1$SystemBlockedNumberPhoneLookup(ImmutableMap immutableMap) throws Exception {
        return queryNumbers(immutableMap.keySet());
    }

    public /* synthetic */ PhoneLookupInfo.SystemBlockedNumberInfo lambda$lookup$0$SystemBlockedNumberPhoneLookup(DialerPhoneNumber dialerPhoneNumber) throws Exception {
        return queryNumbers(ImmutableSet.m87of(dialerPhoneNumber)).get(dialerPhoneNumber);
    }

    public ListenableFuture<PhoneLookupInfo.SystemBlockedNumberInfo> lookup(DialerPhoneNumber dialerPhoneNumber) {
        if (!FilteredNumberCompat.useNewFiltering(this.appContext)) {
            return Futures.immediateFuture(PhoneLookupInfo.SystemBlockedNumberInfo.getDefaultInstance());
        }
        return this.executorService.submit(new Callable(dialerPhoneNumber) {
            private final /* synthetic */ DialerPhoneNumber f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return SystemBlockedNumberPhoneLookup.this.lambda$lookup$0$SystemBlockedNumberPhoneLookup(this.f$1);
            }
        });
    }

    public ListenableFuture<Void> onSuccessfulBulkUpdate() {
        return Futures.immediateFuture(null);
    }

    public void registerContentObservers() {
        this.appContext.getContentResolver().registerContentObserver(BlockedNumberContract.BlockedNumbers.CONTENT_URI, true, this.markDirtyObserver);
    }

    public void setSubMessage(PhoneLookupInfo.Builder builder, Object obj) {
        builder.setSystemBlockedNumberInfo((PhoneLookupInfo.SystemBlockedNumberInfo) obj);
    }

    public void unregisterContentObservers() {
        this.appContext.getContentResolver().unregisterContentObserver(this.markDirtyObserver);
    }
}
