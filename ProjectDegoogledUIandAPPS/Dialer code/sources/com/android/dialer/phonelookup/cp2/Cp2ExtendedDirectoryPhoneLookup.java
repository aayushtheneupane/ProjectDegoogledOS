package com.android.dialer.phonelookup.cp2;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProvider;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.phonelookup.PhoneLookup;
import com.android.dialer.phonelookup.PhoneLookupInfo;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.android.dialer.util.PermissionsUtil;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class Cp2ExtendedDirectoryPhoneLookup implements PhoneLookup<PhoneLookupInfo.Cp2Info> {
    static final String CP2_EXTENDED_DIRECTORY_PHONE_LOOKUP_TIMEOUT_MILLIS = "cp2_extended_directory_phone_lookup_timout_millis";
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutorService;
    private final ConfigProvider configProvider;
    private final ListeningExecutorService lightweightExecutorService;
    private final MissingPermissionsOperations missingPermissionsOperations;
    private final ScheduledExecutorService scheduledExecutorService;

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

    Cp2ExtendedDirectoryPhoneLookup(Context context, ListeningExecutorService listeningExecutorService, ListeningExecutorService listeningExecutorService2, ScheduledExecutorService scheduledExecutorService2, ConfigProvider configProvider2, MissingPermissionsOperations missingPermissionsOperations2) {
        this.appContext = context;
        this.backgroundExecutorService = listeningExecutorService;
        this.lightweightExecutorService = listeningExecutorService2;
        this.scheduledExecutorService = scheduledExecutorService2;
        this.configProvider = configProvider2;
        this.missingPermissionsOperations = missingPermissionsOperations2;
    }

    static Uri getContentUriForContacts(String str, long j) {
        return ContactsContract.PhoneLookup.ENTERPRISE_CONTENT_FILTER_URI.buildUpon().appendPath(str).appendQueryParameter("sip", String.valueOf(PhoneNumberHelper.isUriNumber(str))).appendQueryParameter("directory", String.valueOf(j)).build();
    }

    static /* synthetic */ boolean lambda$isDirty$5(PhoneLookupInfo phoneLookupInfo) {
        return !phoneLookupInfo.getExtendedCp2Info().equals(PhoneLookupInfo.Cp2Info.getDefaultInstance());
    }

    static /* synthetic */ PhoneLookupInfo.Cp2Info lambda$queryCp2ForDirectoryContact$3(List list) {
        PhoneLookupInfo.Cp2Info.Builder newBuilder = PhoneLookupInfo.Cp2Info.newBuilder();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            newBuilder.addAllCp2ContactInfo(((PhoneLookupInfo.Cp2Info) it.next()).getCp2ContactInfoList());
        }
        return (PhoneLookupInfo.Cp2Info) newBuilder.build();
    }

    public ListenableFuture<Void> clearData() {
        return Futures.immediateFuture(null);
    }

    public String getLoggingName() {
        return "Cp2ExtendedDirectoryPhoneLookup";
    }

    public ListenableFuture<ImmutableMap<DialerPhoneNumber, PhoneLookupInfo.Cp2Info>> getMostRecentInfo(ImmutableMap<DialerPhoneNumber, PhoneLookupInfo.Cp2Info> immutableMap) {
        if (PermissionsUtil.hasContactsReadPermissions(this.appContext)) {
            return Futures.immediateFuture(immutableMap);
        }
        LogUtil.m10w("Cp2ExtendedDirectoryPhoneLookup.getMostRecentInfo", "missing permissions", new Object[0]);
        return this.missingPermissionsOperations.getMostRecentInfoForMissingPermissions(immutableMap);
    }

    public Object getSubMessage(PhoneLookupInfo phoneLookupInfo) {
        return phoneLookupInfo.getExtendedCp2Info();
    }

    public ListenableFuture<Boolean> isDirty(ImmutableSet<DialerPhoneNumber> immutableSet) {
        if (PermissionsUtil.hasContactsReadPermissions(this.appContext)) {
            return Futures.immediateFuture(false);
        }
        return this.missingPermissionsOperations.isDirtyForMissingPermissions(immutableSet, C0543x8facb7e5.INSTANCE);
    }

    public /* synthetic */ ListenableFuture lambda$lookup$0$Cp2ExtendedDirectoryPhoneLookup(DialerPhoneNumber dialerPhoneNumber, List list) throws Exception {
        if (list.isEmpty()) {
            return Futures.immediateFuture(PhoneLookupInfo.Cp2Info.getDefaultInstance());
        }
        String normalizedNumber = dialerPhoneNumber.getNormalizedNumber();
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(this.backgroundExecutorService.submit(new Callable(normalizedNumber, ((Long) it.next()).longValue()) {
                private final /* synthetic */ String f$1;
                private final /* synthetic */ long f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final Object call() {
                    return Cp2ExtendedDirectoryPhoneLookup.this.mo6732x3d6bd7c7(this.f$1, this.f$2);
                }
            }));
        }
        return Futures.transform(Futures.allAsList(arrayList), C0545xd4537907.INSTANCE, this.lightweightExecutorService);
    }

    public /* synthetic */ PhoneLookupInfo.Cp2Info lambda$lookup$1$Cp2ExtendedDirectoryPhoneLookup(TimeoutException timeoutException) {
        LogUtil.m10w("Cp2ExtendedDirectoryPhoneLookup.lookup", "Time out!", new Object[0]);
        ((LoggingBindingsStub) Logger.get(this.appContext)).logImpression(DialerImpression$Type.CP2_EXTENDED_DIRECTORY_PHONE_LOOKUP_TIMEOUT);
        return PhoneLookupInfo.Cp2Info.getDefaultInstance();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0071, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0072, code lost:
        if (r9 != null) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0074, code lost:
        $closeResource(r8, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0077, code lost:
        throw r10;
     */
    /* renamed from: lambda$queryCp2ForDirectoryContact$4$Cp2ExtendedDirectoryPhoneLookup */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ com.android.dialer.phonelookup.PhoneLookupInfo.Cp2Info mo6732x3d6bd7c7(java.lang.String r9, long r10) throws java.lang.Exception {
        /*
            r8 = this;
            com.android.dialer.phonelookup.PhoneLookupInfo$Cp2Info$Builder r0 = com.android.dialer.phonelookup.PhoneLookupInfo.Cp2Info.newBuilder()
            android.content.Context r1 = r8.appContext
            android.content.ContentResolver r2 = r1.getContentResolver()
            android.net.Uri r3 = getContentUriForContacts(r9, r10)
            java.lang.String[] r4 = com.android.dialer.phonelookup.cp2.Cp2Projections.getProjectionForPhoneLookupTable()
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)
            r1 = 0
            r2 = 1
            java.lang.String r3 = "Cp2ExtendedDirectoryPhoneLookup.queryCp2ForDirectoryContact"
            r4 = 0
            if (r9 != 0) goto L_0x0039
            java.lang.String r8 = "null cursor returned when querying directory %d"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x006f }
            java.lang.Long r10 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x006f }
            r2[r1] = r10     // Catch:{ all -> 0x006f }
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r3, (java.lang.String) r8, (java.lang.Object[]) r2)     // Catch:{ all -> 0x006f }
            com.google.protobuf.GeneratedMessageLite r8 = r0.build()     // Catch:{ all -> 0x006f }
            com.android.dialer.phonelookup.PhoneLookupInfo$Cp2Info r8 = (com.android.dialer.phonelookup.PhoneLookupInfo.Cp2Info) r8     // Catch:{ all -> 0x006f }
            if (r9 == 0) goto L_0x0038
            $closeResource(r4, r9)
        L_0x0038:
            return r8
        L_0x0039:
            boolean r5 = r9.moveToFirst()     // Catch:{ all -> 0x006f }
            if (r5 != 0) goto L_0x0056
            java.lang.String r8 = "empty cursor returned when querying directory %d"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x006f }
            java.lang.Long r10 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x006f }
            r2[r1] = r10     // Catch:{ all -> 0x006f }
            com.android.dialer.common.LogUtil.m9i(r3, r8, r2)     // Catch:{ all -> 0x006f }
            com.google.protobuf.GeneratedMessageLite r8 = r0.build()     // Catch:{ all -> 0x006f }
            com.android.dialer.phonelookup.PhoneLookupInfo$Cp2Info r8 = (com.android.dialer.phonelookup.PhoneLookupInfo.Cp2Info) r8     // Catch:{ all -> 0x006f }
            $closeResource(r4, r9)
            return r8
        L_0x0056:
            android.content.Context r1 = r8.appContext     // Catch:{ all -> 0x006f }
            com.android.dialer.phonelookup.PhoneLookupInfo$Cp2Info$Cp2ContactInfo r1 = com.android.dialer.phonelookup.cp2.Cp2Projections.buildCp2ContactInfoFromCursor(r1, r9, r10)     // Catch:{ all -> 0x006f }
            r0.addCp2ContactInfo(r1)     // Catch:{ all -> 0x006f }
            boolean r1 = r9.moveToNext()     // Catch:{ all -> 0x006f }
            if (r1 != 0) goto L_0x0056
            $closeResource(r4, r9)
            com.google.protobuf.GeneratedMessageLite r8 = r0.build()
            com.android.dialer.phonelookup.PhoneLookupInfo$Cp2Info r8 = (com.android.dialer.phonelookup.PhoneLookupInfo.Cp2Info) r8
            return r8
        L_0x006f:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0071 }
        L_0x0071:
            r10 = move-exception
            if (r9 == 0) goto L_0x0077
            $closeResource(r8, r9)
        L_0x0077:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonelookup.cp2.Cp2ExtendedDirectoryPhoneLookup.mo6732x3d6bd7c7(java.lang.String, long):com.android.dialer.phonelookup.PhoneLookupInfo$Cp2Info");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0070, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0071, code lost:
        if (r1 != null) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0073, code lost:
        $closeResource(r7, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0076, code lost:
        throw r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0059 A[Catch:{ all -> 0x0070 }] */
    /* renamed from: lambda$queryCp2ForExtendedDirectoryIds$2$Cp2ExtendedDirectoryPhoneLookup */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ java.util.List mo6733x27d98bf6() throws java.lang.Exception {
        /*
            r7 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.content.Context r7 = r7.appContext
            android.content.ContentResolver r1 = r7.getContentResolver()
            android.net.Uri r2 = android.provider.ContactsContract.Directory.ENTERPRISE_CONTENT_URI
            java.lang.String r7 = "_id"
            java.lang.String[] r3 = new java.lang.String[]{r7}
            r4 = 0
            r5 = 0
            java.lang.String r6 = "_id"
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6)
            java.lang.String r2 = "Cp2ExtendedDirectoryPhoneLookup.queryCp2ForExtendedDirectoryIds"
            r3 = 0
            r4 = 0
            if (r1 != 0) goto L_0x002e
            java.lang.String r7 = "null cursor"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x006e }
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r2, (java.lang.String) r7, (java.lang.Object[]) r3)     // Catch:{ all -> 0x006e }
            if (r1 == 0) goto L_0x002d
            $closeResource(r4, r1)
        L_0x002d:
            return r0
        L_0x002e:
            boolean r5 = r1.moveToFirst()     // Catch:{ all -> 0x006e }
            if (r5 != 0) goto L_0x003f
            java.lang.String r7 = "empty cursor"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x006e }
            com.android.dialer.common.LogUtil.m9i(r2, r7, r3)     // Catch:{ all -> 0x006e }
            $closeResource(r4, r1)
            return r0
        L_0x003f:
            int r7 = r1.getColumnIndexOrThrow(r7)     // Catch:{ all -> 0x006e }
        L_0x0043:
            long r5 = r1.getLong(r7)     // Catch:{ all -> 0x006e }
            boolean r2 = android.provider.ContactsContract.Directory.isRemoteDirectoryId(r5)     // Catch:{ all -> 0x006e }
            if (r2 != 0) goto L_0x0056
            boolean r2 = android.provider.ContactsContract.Directory.isEnterpriseDirectoryId(r5)     // Catch:{ all -> 0x006e }
            if (r2 == 0) goto L_0x0054
            goto L_0x0056
        L_0x0054:
            r2 = r3
            goto L_0x0057
        L_0x0056:
            r2 = 1
        L_0x0057:
            if (r2 == 0) goto L_0x0064
            long r5 = r1.getLong(r7)     // Catch:{ all -> 0x006e }
            java.lang.Long r2 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x006e }
            r0.add(r2)     // Catch:{ all -> 0x006e }
        L_0x0064:
            boolean r2 = r1.moveToNext()     // Catch:{ all -> 0x006e }
            if (r2 != 0) goto L_0x0043
            $closeResource(r4, r1)
            return r0
        L_0x006e:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0070 }
        L_0x0070:
            r0 = move-exception
            if (r1 == 0) goto L_0x0076
            $closeResource(r7, r1)
        L_0x0076:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonelookup.cp2.Cp2ExtendedDirectoryPhoneLookup.mo6733x27d98bf6():java.util.List");
    }

    public ListenableFuture<PhoneLookupInfo.Cp2Info> lookup(DialerPhoneNumber dialerPhoneNumber) {
        if (!PermissionsUtil.hasContactsReadPermissions(this.appContext)) {
            return Futures.immediateFuture(PhoneLookupInfo.Cp2Info.getDefaultInstance());
        }
        ListenableFuture<PhoneLookupInfo.Cp2Info> transformAsync = Futures.transformAsync(this.backgroundExecutorService.submit(new Callable() {
            public final Object call() {
                return Cp2ExtendedDirectoryPhoneLookup.this.mo6733x27d98bf6();
            }
        }), new AsyncFunction(dialerPhoneNumber) {
            private final /* synthetic */ DialerPhoneNumber f$1;

            {
                this.f$1 = r2;
            }

            public final ListenableFuture apply(Object obj) {
                return Cp2ExtendedDirectoryPhoneLookup.this.lambda$lookup$0$Cp2ExtendedDirectoryPhoneLookup(this.f$1, (List) obj);
            }
        }, this.lightweightExecutorService);
        long j = ((SharedPrefConfigProvider) this.configProvider).getLong(CP2_EXTENDED_DIRECTORY_PHONE_LOOKUP_TIMEOUT_MILLIS, Long.MAX_VALUE);
        return j == Long.MAX_VALUE ? transformAsync : Futures.catching(Futures.withTimeout(transformAsync, j, TimeUnit.MILLISECONDS, this.scheduledExecutorService), TimeoutException.class, new Function() {
            public final Object apply(Object obj) {
                return Cp2ExtendedDirectoryPhoneLookup.this.lambda$lookup$1$Cp2ExtendedDirectoryPhoneLookup((TimeoutException) obj);
            }
        }, this.lightweightExecutorService);
    }

    public ListenableFuture<Void> onSuccessfulBulkUpdate() {
        return Futures.immediateFuture(null);
    }

    public void registerContentObservers() {
    }

    public void setSubMessage(PhoneLookupInfo.Builder builder, Object obj) {
        builder.setExtendedCp2Info((PhoneLookupInfo.Cp2Info) obj);
    }

    public void unregisterContentObservers() {
    }
}
