package com.android.dialer.phonelookup.cnap;

import android.content.Context;
import android.telecom.Call;
import android.text.TextUtils;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.phonelookup.PhoneLookup;
import com.android.dialer.phonelookup.PhoneLookupInfo;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.concurrent.Callable;

public final class CnapPhoneLookup implements PhoneLookup<PhoneLookupInfo.CnapInfo> {
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutorService;

    CnapPhoneLookup(Context context, ListeningExecutorService listeningExecutorService) {
        this.appContext = context;
        this.backgroundExecutorService = listeningExecutorService;
    }

    public ListenableFuture<Void> clearData() {
        return Futures.immediateFuture(null);
    }

    public String getLoggingName() {
        return "CnapPhoneLookup";
    }

    public ListenableFuture<ImmutableMap<DialerPhoneNumber, PhoneLookupInfo.CnapInfo>> getMostRecentInfo(ImmutableMap<DialerPhoneNumber, PhoneLookupInfo.CnapInfo> immutableMap) {
        return Futures.immediateFuture(immutableMap);
    }

    public Object getSubMessage(PhoneLookupInfo phoneLookupInfo) {
        return phoneLookupInfo.getCnapInfo();
    }

    public ListenableFuture<Boolean> isDirty(ImmutableSet<DialerPhoneNumber> immutableSet) {
        return Futures.immediateFuture(false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0089, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008a, code lost:
        if (r7 != null) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0090, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0091, code lost:
        r6.addSuppressed(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0094, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ com.android.dialer.phonelookup.PhoneLookupInfo.CnapInfo lambda$lookup$0$CnapPhoneLookup(com.android.dialer.DialerPhoneNumber r7) throws java.lang.Exception {
        /*
            r6 = this;
            com.android.dialer.common.database.Selection$Builder r0 = com.android.dialer.common.database.Selection.builder()
            java.lang.String r1 = "normalized_number"
            com.android.dialer.common.database.Selection$Column r1 = com.android.dialer.common.database.Selection.column(r1)
            java.lang.String r7 = r7.getNormalizedNumber()
            java.lang.String r2 = "="
            com.android.dialer.common.database.Selection r7 = r1.mo5867is(r2, r7)
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
            java.lang.String r0 = "CnapPhoneLookup.lookup"
            r1 = 0
            if (r7 != 0) goto L_0x004c
            java.lang.String r6 = "null cursor"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0087 }
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r0, (java.lang.String) r6, (java.lang.Object[]) r1)     // Catch:{ all -> 0x0087 }
            com.android.dialer.phonelookup.PhoneLookupInfo$CnapInfo r6 = com.android.dialer.phonelookup.PhoneLookupInfo.CnapInfo.getDefaultInstance()     // Catch:{ all -> 0x0087 }
            if (r7 == 0) goto L_0x004b
            r7.close()
        L_0x004b:
            return r6
        L_0x004c:
            boolean r2 = r7.moveToFirst()     // Catch:{ all -> 0x0087 }
            if (r2 != 0) goto L_0x0061
            java.lang.String r6 = "empty cursor"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0087 }
            com.android.dialer.common.LogUtil.m9i(r0, r6, r1)     // Catch:{ all -> 0x0087 }
            com.android.dialer.phonelookup.PhoneLookupInfo$CnapInfo r6 = com.android.dialer.phonelookup.PhoneLookupInfo.CnapInfo.getDefaultInstance()     // Catch:{ all -> 0x0087 }
            r7.close()
            return r6
        L_0x0061:
            int r0 = r7.getCount()     // Catch:{ all -> 0x0087 }
            r2 = 1
            if (r0 != r2) goto L_0x0069
            r1 = r2
        L_0x0069:
            com.android.dialer.common.Assert.checkState(r1)     // Catch:{ all -> 0x0087 }
            int r6 = r7.getColumnIndexOrThrow(r6)     // Catch:{ all -> 0x0087 }
            byte[] r6 = r7.getBlob(r6)     // Catch:{ InvalidProtocolBufferException -> 0x0080 }
            com.android.dialer.phonelookup.PhoneLookupInfo r6 = com.android.dialer.phonelookup.PhoneLookupInfo.parseFrom(r6)     // Catch:{ InvalidProtocolBufferException -> 0x0080 }
            com.android.dialer.phonelookup.PhoneLookupInfo$CnapInfo r6 = r6.getCnapInfo()     // Catch:{ all -> 0x0087 }
            r7.close()
            return r6
        L_0x0080:
            r6 = move-exception
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0087 }
            r0.<init>(r6)     // Catch:{ all -> 0x0087 }
            throw r0     // Catch:{ all -> 0x0087 }
        L_0x0087:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0089 }
        L_0x0089:
            r0 = move-exception
            if (r7 == 0) goto L_0x0094
            r7.close()     // Catch:{ all -> 0x0090 }
            goto L_0x0094
        L_0x0090:
            r7 = move-exception
            r6.addSuppressed(r7)
        L_0x0094:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonelookup.cnap.CnapPhoneLookup.lambda$lookup$0$CnapPhoneLookup(com.android.dialer.DialerPhoneNumber):com.android.dialer.phonelookup.PhoneLookupInfo$CnapInfo");
    }

    public ListenableFuture<PhoneLookupInfo.CnapInfo> lookup(Context context, Call call) {
        PhoneLookupInfo.CnapInfo cnapInfo;
        String callerDisplayName = call.getDetails().getCallerDisplayName();
        if (TextUtils.isEmpty(callerDisplayName)) {
            cnapInfo = PhoneLookupInfo.CnapInfo.getDefaultInstance();
        } else {
            PhoneLookupInfo.CnapInfo.Builder newBuilder = PhoneLookupInfo.CnapInfo.newBuilder();
            newBuilder.setName(callerDisplayName);
            cnapInfo = (PhoneLookupInfo.CnapInfo) newBuilder.build();
        }
        return Futures.immediateFuture(cnapInfo);
    }

    public ListenableFuture<Void> onSuccessfulBulkUpdate() {
        return Futures.immediateFuture(null);
    }

    public void registerContentObservers() {
    }

    public void setSubMessage(PhoneLookupInfo.Builder builder, Object obj) {
        builder.setCnapInfo((PhoneLookupInfo.CnapInfo) obj);
    }

    public void unregisterContentObservers() {
    }

    public ListenableFuture<PhoneLookupInfo.CnapInfo> lookup(DialerPhoneNumber dialerPhoneNumber) {
        return this.backgroundExecutorService.submit(new Callable(dialerPhoneNumber) {
            private final /* synthetic */ DialerPhoneNumber f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return CnapPhoneLookup.this.lambda$lookup$0$CnapPhoneLookup(this.f$1);
            }
        });
    }
}
