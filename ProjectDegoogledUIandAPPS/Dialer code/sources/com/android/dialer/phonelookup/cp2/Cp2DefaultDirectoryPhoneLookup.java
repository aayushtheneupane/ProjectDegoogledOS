package com.android.dialer.phonelookup.cp2;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.p000v4.util.ArrayMap;
import android.support.p000v4.util.ArraySet;
import android.text.TextUtils;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProvider;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.phonelookup.PhoneLookup;
import com.android.dialer.phonelookup.PhoneLookupInfo;
import com.android.dialer.phonenumberproto.PartitionedNumbers;
import com.android.dialer.util.PermissionsUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public final class Cp2DefaultDirectoryPhoneLookup implements PhoneLookup<PhoneLookupInfo.Cp2Info> {
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutorService;
    private final ConfigProvider configProvider;
    private Long currentLastTimestampProcessed;
    private final ListeningExecutorService lightweightExecutorService;
    private final MissingPermissionsOperations missingPermissionsOperations;
    private final SharedPreferences sharedPreferences;

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

    Cp2DefaultDirectoryPhoneLookup(Context context, SharedPreferences sharedPreferences2, ListeningExecutorService listeningExecutorService, ListeningExecutorService listeningExecutorService2, ConfigProvider configProvider2, MissingPermissionsOperations missingPermissionsOperations2) {
        this.appContext = context;
        this.sharedPreferences = sharedPreferences2;
        this.backgroundExecutorService = listeningExecutorService;
        this.lightweightExecutorService = listeningExecutorService2;
        this.configProvider = configProvider2;
        this.missingPermissionsOperations = missingPermissionsOperations2;
    }

    private static void addInfo(Map<DialerPhoneNumber, Set<PhoneLookupInfo.Cp2Info.Cp2ContactInfo>> map, Set<DialerPhoneNumber> set, Set<PhoneLookupInfo.Cp2Info.Cp2ContactInfo> set2) {
        for (DialerPhoneNumber next : set) {
            Set set3 = map.get(next);
            if (set3 == null) {
                set3 = new ArraySet();
                map.put(next, set3);
            }
            set3.addAll(set2);
        }
    }

    private Set<DialerPhoneNumber> findDeletedPhoneNumbersIn(ImmutableMap<DialerPhoneNumber, PhoneLookupInfo.Cp2Info> immutableMap, Cursor cursor) {
        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("contact_id");
        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("contact_deleted_timestamp");
        ArraySet arraySet = new ArraySet();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            arraySet.addAll(findDialerPhoneNumbersContainingContactId(immutableMap, cursor.getLong(columnIndexOrThrow)));
            long j = cursor.getLong(columnIndexOrThrow2);
            Long l = this.currentLastTimestampProcessed;
            if (l == null || l.longValue() < j) {
                this.currentLastTimestampProcessed = Long.valueOf(j);
            }
        }
        return arraySet;
    }

    private static Set<DialerPhoneNumber> findDialerPhoneNumbersContainingContactId(Map<DialerPhoneNumber, PhoneLookupInfo.Cp2Info> map, long j) {
        ArraySet arraySet = new ArraySet();
        for (Map.Entry next : map.entrySet()) {
            for (PhoneLookupInfo.Cp2Info.Cp2ContactInfo contactId : ((PhoneLookupInfo.Cp2Info) next.getValue()).getCp2ContactInfoList()) {
                if (contactId.getContactId() == j) {
                    arraySet.add((DialerPhoneNumber) next.getKey());
                }
            }
        }
        boolean z = arraySet.size() > 0;
        Assert.checkArgument(z, "Couldn't find DialerPhoneNumber for contact ID: " + j, new Object[0]);
        return arraySet;
    }

    private long getMaxSupportedInvalidNumbers() {
        return ((SharedPrefConfigProvider) this.configProvider).getLong("cp2_phone_lookup_max_invalid_numbers", 5);
    }

    static /* synthetic */ Map lambda$buildMapForUpdatedOrAddedContacts$24(ListenableFuture listenableFuture, ListenableFuture listenableFuture2, PartitionedNumbers partitionedNumbers, Set set) throws Exception {
        List list = (List) listenableFuture2.get();
        ArrayMap arrayMap = new ArrayMap();
        for (Map.Entry entry : ((Map) listenableFuture.get()).entrySet()) {
            ImmutableSet<DialerPhoneNumber> dialerPhoneNumbersForValidE164 = partitionedNumbers.dialerPhoneNumbersForValidE164((String) entry.getKey());
            addInfo(arrayMap, dialerPhoneNumbersForValidE164, (Set) entry.getValue());
            set.removeAll(dialerPhoneNumbersForValidE164);
        }
        UnmodifiableIterator<String> it = partitionedNumbers.invalidNumbers().iterator();
        int i = 0;
        while (it.hasNext()) {
            ImmutableSet<DialerPhoneNumber> dialerPhoneNumbersForInvalid = partitionedNumbers.dialerPhoneNumbersForInvalid(it.next());
            addInfo(arrayMap, dialerPhoneNumbersForInvalid, (Set) list.get(i));
            set.removeAll(dialerPhoneNumbersForInvalid);
            i++;
        }
        Iterator it2 = set.iterator();
        while (it2.hasNext()) {
            arrayMap.put((DialerPhoneNumber) it2.next(), ImmutableSet.m86of());
        }
        new Object[1][0] = Integer.valueOf(set.size());
        return arrayMap;
    }

    static /* synthetic */ boolean lambda$getMostRecentInfo$17(ArraySet arraySet, DialerPhoneNumber dialerPhoneNumber) {
        return !(arraySet.indexOf(dialerPhoneNumber) >= 0);
    }

    static /* synthetic */ ImmutableMap lambda$getMostRecentInfo$18(ImmutableMap immutableMap, Set set, ArraySet arraySet, Map map) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        UnmodifiableIterator it = immutableMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            DialerPhoneNumber dialerPhoneNumber = (DialerPhoneNumber) entry.getKey();
            PhoneLookupInfo.Cp2Info.Builder newBuilder = PhoneLookupInfo.Cp2Info.newBuilder((PhoneLookupInfo.Cp2Info) entry.getValue());
            if (map.containsKey(dialerPhoneNumber)) {
                newBuilder.clear();
                newBuilder.addAllCp2ContactInfo((Iterable) map.get(dialerPhoneNumber));
            } else if (set.contains(dialerPhoneNumber)) {
                newBuilder.clear();
            } else if (arraySet.contains(dialerPhoneNumber) && !dialerPhoneNumber.getNormalizedNumber().isEmpty()) {
                newBuilder.setIsIncomplete(true);
            }
            builder.put(dialerPhoneNumber, (PhoneLookupInfo.Cp2Info) newBuilder.build());
        }
        return builder.build();
    }

    static /* synthetic */ boolean lambda$isDirty$1(PhoneLookupInfo phoneLookupInfo) {
        return !phoneLookupInfo.getDefaultCp2Info().equals(PhoneLookupInfo.Cp2Info.getDefaultInstance());
    }

    static /* synthetic */ Set lambda$queryPhoneTableForContactIds$9(List list) {
        ArraySet arraySet = new ArraySet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arraySet.addAll((Set) it.next());
        }
        return arraySet;
    }

    private Cursor queryContactsTableForContacts(Set<Long> set, long j) {
        String outline12 = GeneratedOutlineSupport.outline12(GeneratedOutlineSupport.outline13("contact_last_updated_timestamp > ? AND _id IN ("), questionMarks(set.size()), ")");
        int i = 1;
        String[] strArr = new String[(set.size() + 1)];
        strArr[0] = Long.toString(j);
        for (Long longValue : set) {
            strArr[i] = Long.toString(longValue.longValue());
            i++;
        }
        return this.appContext.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[]{"_id", "contact_last_updated_timestamp"}, outline12, strArr, (String) null);
    }

    private Cursor queryPhoneLookup(String[] strArr, String str) {
        return this.appContext.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(str)), strArr, (String) null, (String[]) null, (String) null);
    }

    private Cursor queryPhoneTableBasedOnE164(String[] strArr, Set<String> set) {
        return this.appContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, strArr, GeneratedOutlineSupport.outline12(GeneratedOutlineSupport.outline13("data4 IN ("), questionMarks(set.size()), ")"), (String[]) set.toArray(new String[set.size()]), (String) null);
    }

    private static String questionMarks(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 != 0) {
                sb.append(", ");
            }
            sb.append("?");
        }
        return sb.toString();
    }

    public ListenableFuture<Void> clearData() {
        return this.backgroundExecutorService.submit(new Callable() {
            public final Object call() {
                return Cp2DefaultDirectoryPhoneLookup.this.lambda$clearData$23$Cp2DefaultDirectoryPhoneLookup();
            }
        });
    }

    public String getLoggingName() {
        return "Cp2DefaultDirectoryPhoneLookup";
    }

    public ListenableFuture<ImmutableMap<DialerPhoneNumber, PhoneLookupInfo.Cp2Info>> getMostRecentInfo(ImmutableMap<DialerPhoneNumber, PhoneLookupInfo.Cp2Info> immutableMap) {
        this.currentLastTimestampProcessed = null;
        if (PermissionsUtil.hasContactsReadPermissions(this.appContext)) {
            return Futures.transformAsync(this.backgroundExecutorService.submit(new Callable() {
                public final Object call() {
                    return Cp2DefaultDirectoryPhoneLookup.this.lambda$getMostRecentInfo$16$Cp2DefaultDirectoryPhoneLookup();
                }
            }), new AsyncFunction(immutableMap) {
                private final /* synthetic */ ImmutableMap f$1;

                {
                    this.f$1 = r2;
                }

                public final ListenableFuture apply(Object obj) {
                    return Cp2DefaultDirectoryPhoneLookup.this.lambda$getMostRecentInfo$20$Cp2DefaultDirectoryPhoneLookup(this.f$1, (Long) obj);
                }
            }, this.lightweightExecutorService);
        }
        LogUtil.m10w("Cp2DefaultDirectoryPhoneLookup.getMostRecentInfo", "missing permissions", new Object[0]);
        return this.missingPermissionsOperations.getMostRecentInfoForMissingPermissions(immutableMap);
    }

    public Object getSubMessage(PhoneLookupInfo phoneLookupInfo) {
        return phoneLookupInfo.getDefaultCp2Info();
    }

    public ListenableFuture<Boolean> isDirty(ImmutableSet<DialerPhoneNumber> immutableSet) {
        if (!PermissionsUtil.hasContactsReadPermissions(this.appContext)) {
            LogUtil.m10w("Cp2DefaultDirectoryPhoneLookup.isDirty", "missing permissions", new Object[0]);
            return this.missingPermissionsOperations.isDirtyForMissingPermissions(immutableSet, C0529x153fe746.INSTANCE);
        }
        PartitionedNumbers partitionedNumbers = new PartitionedNumbers(immutableSet);
        if (((long) partitionedNumbers.invalidNumbers().size()) <= getMaxSupportedInvalidNumbers()) {
            return Futures.transformAsync(this.backgroundExecutorService.submit(new Callable() {
                public final Object call() {
                    return Cp2DefaultDirectoryPhoneLookup.this.lambda$isDirty$2$Cp2DefaultDirectoryPhoneLookup();
                }
            }), new AsyncFunction(immutableSet) {
                private final /* synthetic */ ImmutableSet f$1;

                {
                    this.f$1 = r2;
                }

                public final ListenableFuture apply(Object obj) {
                    return Cp2DefaultDirectoryPhoneLookup.this.lambda$isDirty$8$Cp2DefaultDirectoryPhoneLookup(this.f$1, (Long) obj);
                }
            }, MoreExecutors.directExecutor());
        }
        new Object[1][0] = Integer.valueOf(partitionedNumbers.invalidNumbers().size());
        return Futures.immediateFuture(true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0048, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0049, code lost:
        if (r7 != null) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004b, code lost:
        $closeResource(r6, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004e, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ java.lang.Boolean lambda$anyContactsDeletedSince$15$Cp2DefaultDirectoryPhoneLookup(long r7) throws java.lang.Exception {
        /*
            r6 = this;
            android.content.Context r6 = r6.appContext
            android.content.ContentResolver r0 = r6.getContentResolver()
            android.net.Uri r1 = android.provider.ContactsContract.DeletedContacts.CONTENT_URI
            java.lang.String r6 = "contact_deleted_timestamp"
            java.lang.String[] r2 = new java.lang.String[]{r6}
            r6 = 1
            java.lang.String[] r4 = new java.lang.String[r6]
            java.lang.String r7 = java.lang.Long.toString(r7)
            r8 = 0
            r4[r8] = r7
            java.lang.String r3 = "contact_deleted_timestamp > ?"
            java.lang.String r5 = "contact_deleted_timestamp limit 1"
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5)
            r0 = 0
            if (r7 != 0) goto L_0x0036
            java.lang.String r6 = "Cp2DefaultDirectoryPhoneLookup.anyContactsDeletedSince"
            java.lang.String r1 = "null cursor"
            java.lang.Object[] r2 = new java.lang.Object[r8]     // Catch:{ all -> 0x0046 }
            com.android.dialer.common.LogUtil.m10w(r6, r1, r2)     // Catch:{ all -> 0x0046 }
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r8)     // Catch:{ all -> 0x0046 }
            if (r7 == 0) goto L_0x0035
            $closeResource(r0, r7)
        L_0x0035:
            return r6
        L_0x0036:
            int r1 = r7.getCount()     // Catch:{ all -> 0x0046 }
            if (r1 <= 0) goto L_0x003d
            goto L_0x003e
        L_0x003d:
            r6 = r8
        L_0x003e:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch:{ all -> 0x0046 }
            $closeResource(r0, r7)
            return r6
        L_0x0046:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0048 }
        L_0x0048:
            r8 = move-exception
            if (r7 == 0) goto L_0x004e
            $closeResource(r6, r7)
        L_0x004e:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonelookup.cp2.Cp2DefaultDirectoryPhoneLookup.lambda$anyContactsDeletedSince$15$Cp2DefaultDirectoryPhoneLookup(long):java.lang.Boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0051, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0052, code lost:
        if (r7 != null) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0054, code lost:
        $closeResource(r6, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0057, code lost:
        throw r0;
     */
    /* renamed from: lambda$batchQueryForValidNumbers$26$Cp2DefaultDirectoryPhoneLookup */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ java.util.Map mo6707x2d4592df(java.util.Set r7) throws java.lang.Exception {
        /*
            r6 = this;
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            boolean r1 = r7.isEmpty()
            if (r1 == 0) goto L_0x000c
            return r0
        L_0x000c:
            java.lang.String[] r1 = com.android.dialer.phonelookup.cp2.Cp2Projections.getProjectionForPhoneTable()
            android.database.Cursor r7 = r6.queryPhoneTableBasedOnE164(r1, r7)
            r1 = 0
            if (r7 != 0) goto L_0x0022
            java.lang.String r6 = "Cp2DefaultDirectoryPhoneLookup.batchQueryForValidNumbers"
            java.lang.String r2 = "null cursor"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x004f }
            com.android.dialer.common.LogUtil.m10w(r6, r2, r3)     // Catch:{ all -> 0x004f }
            goto L_0x0049
        L_0x0022:
            boolean r2 = r7.moveToNext()     // Catch:{ all -> 0x004f }
            if (r2 == 0) goto L_0x0049
            r2 = 6
            java.lang.String r2 = r7.getString(r2)     // Catch:{ all -> 0x004f }
            java.lang.Object r3 = r0.get(r2)     // Catch:{ all -> 0x004f }
            java.util.Set r3 = (java.util.Set) r3     // Catch:{ all -> 0x004f }
            if (r3 != 0) goto L_0x003d
            android.support.v4.util.ArraySet r3 = new android.support.v4.util.ArraySet     // Catch:{ all -> 0x004f }
            r3.<init>()     // Catch:{ all -> 0x004f }
            r0.put(r2, r3)     // Catch:{ all -> 0x004f }
        L_0x003d:
            android.content.Context r2 = r6.appContext     // Catch:{ all -> 0x004f }
            r4 = 0
            com.android.dialer.phonelookup.PhoneLookupInfo$Cp2Info$Cp2ContactInfo r2 = com.android.dialer.phonelookup.cp2.Cp2Projections.buildCp2ContactInfoFromCursor(r2, r7, r4)     // Catch:{ all -> 0x004f }
            r3.add(r2)     // Catch:{ all -> 0x004f }
            goto L_0x0022
        L_0x0049:
            if (r7 == 0) goto L_0x004e
            $closeResource(r1, r7)
        L_0x004e:
            return r0
        L_0x004f:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0051 }
        L_0x0051:
            r0 = move-exception
            if (r7 == 0) goto L_0x0057
            $closeResource(r6, r7)
        L_0x0057:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonelookup.cp2.Cp2DefaultDirectoryPhoneLookup.mo6707x2d4592df(java.util.Set):java.util.Map");
    }

    /* renamed from: lambda$buildMapForUpdatedOrAddedContacts$25$Cp2DefaultDirectoryPhoneLookup */
    public /* synthetic */ ListenableFuture mo6708x9bb9d987(Set set) throws Exception {
        if (set.isEmpty()) {
            return Futures.immediateFuture(new ArrayMap());
        }
        PartitionedNumbers partitionedNumbers = new PartitionedNumbers(ImmutableSet.copyOf(set));
        ListenableFuture submit = this.backgroundExecutorService.submit(new Callable(partitionedNumbers.validE164Numbers()) {
            private final /* synthetic */ Set f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return Cp2DefaultDirectoryPhoneLookup.this.mo6707x2d4592df(this.f$1);
            }
        });
        ArrayList arrayList = new ArrayList();
        UnmodifiableIterator<String> it = partitionedNumbers.invalidNumbers().iterator();
        while (it.hasNext()) {
            arrayList.add(this.backgroundExecutorService.submit(new Callable(it.next()) {
                private final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final Object call() {
                    return Cp2DefaultDirectoryPhoneLookup.this.mo6716x1ba8605b(this.f$1);
                }
            }));
        }
        ListenableFuture allAsList = Futures.allAsList(arrayList);
        return Futures.whenAllSucceed(submit, allAsList).call(new Callable(allAsList, partitionedNumbers, set) {
            private final /* synthetic */ ListenableFuture f$1;
            private final /* synthetic */ PartitionedNumbers f$2;
            private final /* synthetic */ Set f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final Object call() {
                return Cp2DefaultDirectoryPhoneLookup.lambda$buildMapForUpdatedOrAddedContacts$24(ListenableFuture.this, this.f$1, this.f$2, this.f$3);
            }
        }, this.lightweightExecutorService);
    }

    public /* synthetic */ Void lambda$clearData$23$Cp2DefaultDirectoryPhoneLookup() throws Exception {
        this.sharedPreferences.edit().remove("cp2DefaultDirectoryPhoneLookupLastTimestampProcessed").apply();
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0019, code lost:
        if (r0 != null) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        $closeResource(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001e, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ java.lang.Boolean lambda$contactsUpdated$13$Cp2DefaultDirectoryPhoneLookup(java.util.Set r1, long r2) throws java.lang.Exception {
        /*
            r0 = this;
            android.database.Cursor r0 = r0.queryContactsTableForContacts(r1, r2)
            int r1 = r0.getCount()     // Catch:{ all -> 0x0016 }
            if (r1 <= 0) goto L_0x000c
            r1 = 1
            goto L_0x000d
        L_0x000c:
            r1 = 0
        L_0x000d:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x0016 }
            r2 = 0
            $closeResource(r2, r0)
            return r1
        L_0x0016:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0018 }
        L_0x0018:
            r2 = move-exception
            if (r0 == 0) goto L_0x001e
            $closeResource(r1, r0)
        L_0x001e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonelookup.cp2.Cp2DefaultDirectoryPhoneLookup.lambda$contactsUpdated$13$Cp2DefaultDirectoryPhoneLookup(java.util.Set, long):java.lang.Boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b7, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00b8, code lost:
        if (r12 != null) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ba, code lost:
        $closeResource(r10, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00bd, code lost:
        throw r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ java.util.Set lambda$findNumbersToUpdate$22$Cp2DefaultDirectoryPhoneLookup(java.util.Map r11, java.util.Set r12, long r13) throws java.lang.Exception {
        /*
            r10 = this;
            android.support.v4.util.ArraySet r0 = new android.support.v4.util.ArraySet
            r0.<init>()
            android.support.v4.util.ArraySet r1 = new android.support.v4.util.ArraySet
            r1.<init>()
            java.util.Set r2 = r11.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x0012:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x006c
            java.lang.Object r3 = r2.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            com.android.dialer.DialerPhoneNumber r4 = (com.android.dialer.DialerPhoneNumber) r4
            java.lang.Object r3 = r3.getValue()
            com.android.dialer.phonelookup.PhoneLookupInfo$Cp2Info r3 = (com.android.dialer.phonelookup.PhoneLookupInfo.Cp2Info) r3
            boolean r5 = r12.contains(r4)
            if (r5 == 0) goto L_0x0034
            r0.add(r4)
            goto L_0x0012
        L_0x0034:
            int r5 = r3.getCp2ContactInfoCount()
            if (r5 != 0) goto L_0x003e
            r0.add(r4)
            goto L_0x0012
        L_0x003e:
            java.util.List r3 = r3.getCp2ContactInfoList()
            java.util.Iterator r3 = r3.iterator()
        L_0x0046:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x0012
            java.lang.Object r5 = r3.next()
            com.android.dialer.phonelookup.PhoneLookupInfo$Cp2Info$Cp2ContactInfo r5 = (com.android.dialer.phonelookup.PhoneLookupInfo.Cp2Info.Cp2ContactInfo) r5
            long r6 = r5.getContactId()
            r8 = 0
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 != 0) goto L_0x0060
            r0.add(r4)
            goto L_0x0046
        L_0x0060:
            long r5 = r5.getContactId()
            java.lang.Long r5 = java.lang.Long.valueOf(r5)
            r1.add(r5)
            goto L_0x0046
        L_0x006c:
            boolean r12 = r1.isEmpty()
            if (r12 != 0) goto L_0x00be
            android.database.Cursor r12 = r10.queryContactsTableForContacts(r1, r13)
            r13 = 0
            java.lang.String r14 = "_id"
            int r14 = r12.getColumnIndex(r14)     // Catch:{ all -> 0x00b5 }
            java.lang.String r1 = "contact_last_updated_timestamp"
            int r1 = r12.getColumnIndex(r1)     // Catch:{ all -> 0x00b5 }
            r2 = -1
            r12.moveToPosition(r2)     // Catch:{ all -> 0x00b5 }
        L_0x0087:
            boolean r2 = r12.moveToNext()     // Catch:{ all -> 0x00b5 }
            if (r2 == 0) goto L_0x00b1
            long r2 = r12.getLong(r14)     // Catch:{ all -> 0x00b5 }
            java.util.Set r2 = findDialerPhoneNumbersContainingContactId(r11, r2)     // Catch:{ all -> 0x00b5 }
            r0.addAll(r2)     // Catch:{ all -> 0x00b5 }
            long r2 = r12.getLong(r1)     // Catch:{ all -> 0x00b5 }
            java.lang.Long r4 = r10.currentLastTimestampProcessed     // Catch:{ all -> 0x00b5 }
            if (r4 == 0) goto L_0x00aa
            java.lang.Long r4 = r10.currentLastTimestampProcessed     // Catch:{ all -> 0x00b5 }
            long r4 = r4.longValue()     // Catch:{ all -> 0x00b5 }
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x0087
        L_0x00aa:
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x00b5 }
            r10.currentLastTimestampProcessed = r2     // Catch:{ all -> 0x00b5 }
            goto L_0x0087
        L_0x00b1:
            $closeResource(r13, r12)
            goto L_0x00be
        L_0x00b5:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x00b7 }
        L_0x00b7:
            r11 = move-exception
            if (r12 == 0) goto L_0x00bd
            $closeResource(r10, r12)
        L_0x00bd:
            throw r11
        L_0x00be:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonelookup.cp2.Cp2DefaultDirectoryPhoneLookup.lambda$findNumbersToUpdate$22$Cp2DefaultDirectoryPhoneLookup(java.util.Map, java.util.Set, long):java.util.Set");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x009a, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x009b, code lost:
        if (r12 != null) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x009d, code lost:
        $closeResource(r10, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00a0, code lost:
        throw r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ java.util.Set lambda$getDeletedPhoneNumbers$28$Cp2DefaultDirectoryPhoneLookup(com.google.common.collect.ImmutableMap r11, long r12) throws java.lang.Exception {
        /*
            r10 = this;
            android.support.v4.util.ArraySet r0 = new android.support.v4.util.ArraySet
            r0.<init>()
            com.google.common.collect.ImmutableCollection r1 = r11.values()
            com.google.common.collect.UnmodifiableIterator r1 = r1.iterator()
        L_0x000d:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0039
            java.lang.Object r2 = r1.next()
            com.android.dialer.phonelookup.PhoneLookupInfo$Cp2Info r2 = (com.android.dialer.phonelookup.PhoneLookupInfo.Cp2Info) r2
            java.util.List r2 = r2.getCp2ContactInfoList()
            java.util.Iterator r2 = r2.iterator()
        L_0x0021:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x000d
            java.lang.Object r3 = r2.next()
            com.android.dialer.phonelookup.PhoneLookupInfo$Cp2Info$Cp2ContactInfo r3 = (com.android.dialer.phonelookup.PhoneLookupInfo.Cp2Info.Cp2ContactInfo) r3
            long r3 = r3.getContactId()
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r0.add(r3)
            goto L_0x0021
        L_0x0039:
            java.lang.String r1 = "contact_deleted_timestamp > ? AND contact_id IN ("
            java.lang.StringBuilder r1 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r1)
            int r2 = r0.size()
            java.lang.String r2 = questionMarks(r2)
            java.lang.String r3 = ")"
            java.lang.String r7 = com.android.tools.p006r8.GeneratedOutlineSupport.outline12(r1, r2, r3)
            int r1 = r0.size()
            r2 = 1
            int r1 = r1 + r2
            java.lang.String[] r8 = new java.lang.String[r1]
            java.lang.String r12 = java.lang.Long.toString(r12)
            r13 = 0
            r8[r13] = r12
            java.util.Iterator r12 = r0.iterator()
        L_0x0060:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x007a
            java.lang.Object r13 = r12.next()
            java.lang.Long r13 = (java.lang.Long) r13
            int r0 = r2 + 1
            long r3 = r13.longValue()
            java.lang.String r13 = java.lang.Long.toString(r3)
            r8[r2] = r13
            r2 = r0
            goto L_0x0060
        L_0x007a:
            android.content.Context r12 = r10.appContext
            android.content.ContentResolver r4 = r12.getContentResolver()
            android.net.Uri r5 = android.provider.ContactsContract.DeletedContacts.CONTENT_URI
            java.lang.String r12 = "contact_id"
            java.lang.String r13 = "contact_deleted_timestamp"
            java.lang.String[] r6 = new java.lang.String[]{r12, r13}
            r9 = 0
            android.database.Cursor r12 = r4.query(r5, r6, r7, r8, r9)
            r13 = 0
            java.util.Set r10 = r10.findDeletedPhoneNumbersIn(r11, r12)     // Catch:{ all -> 0x0098 }
            $closeResource(r13, r12)
            return r10
        L_0x0098:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x009a }
        L_0x009a:
            r11 = move-exception
            if (r12 == 0) goto L_0x00a0
            $closeResource(r10, r12)
        L_0x00a0:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonelookup.cp2.Cp2DefaultDirectoryPhoneLookup.lambda$getDeletedPhoneNumbers$28$Cp2DefaultDirectoryPhoneLookup(com.google.common.collect.ImmutableMap, long):java.util.Set");
    }

    public /* synthetic */ Long lambda$getMostRecentInfo$16$Cp2DefaultDirectoryPhoneLookup() throws Exception {
        return Long.valueOf(this.sharedPreferences.getLong("cp2DefaultDirectoryPhoneLookupLastTimestampProcessed", 0));
    }

    public /* synthetic */ ListenableFuture lambda$getMostRecentInfo$19$Cp2DefaultDirectoryPhoneLookup(ImmutableMap immutableMap, Long l, Set set) throws Exception {
        ArraySet arraySet = new ArraySet();
        PartitionedNumbers partitionedNumbers = new PartitionedNumbers(immutableMap.keySet());
        int size = partitionedNumbers.invalidNumbers().size();
        ((LoggingBindingsStub) Logger.get(this.appContext)).logAnnotatedCallLogMetrics(size);
        if (((long) size) > getMaxSupportedInvalidNumbers()) {
            UnmodifiableIterator<String> it = partitionedNumbers.invalidNumbers().iterator();
            while (it.hasNext()) {
                arraySet.addAll(partitionedNumbers.dialerPhoneNumbersForInvalid(it.next()));
            }
        }
        return Futures.transform(Futures.transformAsync(this.backgroundExecutorService.submit(new Callable(!arraySet.isEmpty() ? Collections2.filterKeys(immutableMap, new Predicate() {
            public final boolean apply(Object obj) {
                return Cp2DefaultDirectoryPhoneLookup.lambda$getMostRecentInfo$17(ArraySet.this, (DialerPhoneNumber) obj);
            }
        }) : immutableMap, set, l.longValue()) {
            private final /* synthetic */ Map f$1;
            private final /* synthetic */ Set f$2;
            private final /* synthetic */ long f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final Object call() {
                return Cp2DefaultDirectoryPhoneLookup.this.lambda$findNumbersToUpdate$22$Cp2DefaultDirectoryPhoneLookup(this.f$1, this.f$2, this.f$3);
            }
        }), new AsyncFunction() {
            public final ListenableFuture apply(Object obj) {
                return Cp2DefaultDirectoryPhoneLookup.this.mo6708x9bb9d987((Set) obj);
            }
        }, this.lightweightExecutorService), new Function(set, arraySet) {
            private final /* synthetic */ Set f$1;
            private final /* synthetic */ ArraySet f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object apply(Object obj) {
                return Cp2DefaultDirectoryPhoneLookup.lambda$getMostRecentInfo$18(ImmutableMap.this, this.f$1, this.f$2, (Map) obj);
            }
        }, this.lightweightExecutorService);
    }

    public /* synthetic */ ListenableFuture lambda$getMostRecentInfo$20$Cp2DefaultDirectoryPhoneLookup(ImmutableMap immutableMap, Long l) throws Exception {
        return Futures.transformAsync(this.backgroundExecutorService.submit(new Callable(immutableMap, l.longValue()) {
            private final /* synthetic */ ImmutableMap f$1;
            private final /* synthetic */ long f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object call() {
                return Cp2DefaultDirectoryPhoneLookup.this.lambda$getDeletedPhoneNumbers$28$Cp2DefaultDirectoryPhoneLookup(this.f$1, this.f$2);
            }
        }), new AsyncFunction(immutableMap, l) {
            private final /* synthetic */ ImmutableMap f$1;
            private final /* synthetic */ Long f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final ListenableFuture apply(Object obj) {
                return Cp2DefaultDirectoryPhoneLookup.this.lambda$getMostRecentInfo$19$Cp2DefaultDirectoryPhoneLookup(this.f$1, this.f$2, (Set) obj);
            }
        }, this.lightweightExecutorService);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003d, code lost:
        if (r6 != null) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003f, code lost:
        $closeResource(r5, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0042, code lost:
        throw r0;
     */
    /* renamed from: lambda$individualQueryForInvalidNumber$27$Cp2DefaultDirectoryPhoneLookup */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ java.util.Set mo6716x1ba8605b(java.lang.String r6) throws java.lang.Exception {
        /*
            r5 = this;
            android.support.v4.util.ArraySet r0 = new android.support.v4.util.ArraySet
            r0.<init>()
            boolean r1 = r6.isEmpty()
            if (r1 == 0) goto L_0x000c
            return r0
        L_0x000c:
            java.lang.String[] r1 = com.android.dialer.phonelookup.cp2.Cp2Projections.getProjectionForPhoneLookupTable()
            android.database.Cursor r6 = r5.queryPhoneLookup(r1, r6)
            r1 = 0
            if (r6 != 0) goto L_0x0022
            java.lang.String r5 = "Cp2DefaultDirectoryPhoneLookup.individualQueryForInvalidNumber"
            java.lang.String r2 = "null cursor"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x003a }
            com.android.dialer.common.LogUtil.m10w(r5, r2, r3)     // Catch:{ all -> 0x003a }
            goto L_0x0034
        L_0x0022:
            boolean r2 = r6.moveToNext()     // Catch:{ all -> 0x003a }
            if (r2 == 0) goto L_0x0034
            android.content.Context r2 = r5.appContext     // Catch:{ all -> 0x003a }
            r3 = 0
            com.android.dialer.phonelookup.PhoneLookupInfo$Cp2Info$Cp2ContactInfo r2 = com.android.dialer.phonelookup.cp2.Cp2Projections.buildCp2ContactInfoFromCursor(r2, r6, r3)     // Catch:{ all -> 0x003a }
            r0.add(r2)     // Catch:{ all -> 0x003a }
            goto L_0x0022
        L_0x0034:
            if (r6 == 0) goto L_0x0039
            $closeResource(r1, r6)
        L_0x0039:
            return r0
        L_0x003a:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x003c }
        L_0x003c:
            r0 = move-exception
            if (r6 == 0) goto L_0x0042
            $closeResource(r5, r6)
        L_0x0042:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonelookup.cp2.Cp2DefaultDirectoryPhoneLookup.mo6716x1ba8605b(java.lang.String):java.util.Set");
    }

    public /* synthetic */ Long lambda$isDirty$2$Cp2DefaultDirectoryPhoneLookup() throws Exception {
        return Long.valueOf(this.sharedPreferences.getLong("cp2DefaultDirectoryPhoneLookupLastTimestampProcessed", 0));
    }

    public /* synthetic */ ListenableFuture lambda$isDirty$3$Cp2DefaultDirectoryPhoneLookup(Long l, Set set) throws Exception {
        return this.backgroundExecutorService.submit(new Callable(set, l.longValue()) {
            private final /* synthetic */ Set f$1;
            private final /* synthetic */ long f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object call() {
                return Cp2DefaultDirectoryPhoneLookup.this.lambda$contactsUpdated$13$Cp2DefaultDirectoryPhoneLookup(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ ListenableFuture lambda$isDirty$4$Cp2DefaultDirectoryPhoneLookup(Long l, Set set) throws Exception {
        return this.backgroundExecutorService.submit(new Callable(set, l.longValue()) {
            private final /* synthetic */ Set f$1;
            private final /* synthetic */ long f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object call() {
                return Cp2DefaultDirectoryPhoneLookup.this.lambda$contactsUpdated$13$Cp2DefaultDirectoryPhoneLookup(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ ListenableFuture lambda$isDirty$5$Cp2DefaultDirectoryPhoneLookup(Long l, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            return Futures.immediateFuture(true);
        }
        return Futures.transformAsync(this.backgroundExecutorService.submit(new Callable() {
            public final Object call() {
                return Cp2DefaultDirectoryPhoneLookup.this.mo6727xd8824da2();
            }
        }), new AsyncFunction(l) {
            private final /* synthetic */ Long f$1;

            {
                this.f$1 = r2;
            }

            public final ListenableFuture apply(Object obj) {
                return Cp2DefaultDirectoryPhoneLookup.this.lambda$isDirty$4$Cp2DefaultDirectoryPhoneLookup(this.f$1, (Set) obj);
            }
        }, MoreExecutors.directExecutor());
    }

    public /* synthetic */ ListenableFuture lambda$isDirty$6$Cp2DefaultDirectoryPhoneLookup(ImmutableSet immutableSet, Long l, Boolean bool) throws Exception {
        ListenableFuture listenableFuture;
        boolean z = false;
        if (bool.booleanValue()) {
            return Futures.immediateFuture(false);
        }
        PartitionedNumbers partitionedNumbers = new PartitionedNumbers(immutableSet);
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.backgroundExecutorService.submit(new Callable(partitionedNumbers.validE164Numbers()) {
            private final /* synthetic */ Set f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return Cp2DefaultDirectoryPhoneLookup.this.mo6729x22c0949d(this.f$1);
            }
        }));
        if (((long) partitionedNumbers.invalidNumbers().size()) <= getMaxSupportedInvalidNumbers()) {
            z = true;
        }
        Assert.checkState(z);
        UnmodifiableIterator<String> it = partitionedNumbers.invalidNumbers().iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (TextUtils.isEmpty(next)) {
                listenableFuture = Futures.immediateFuture(new ArraySet());
            } else {
                listenableFuture = this.backgroundExecutorService.submit(new Callable(next) {
                    private final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final Object call() {
                        return Cp2DefaultDirectoryPhoneLookup.this.mo6728x78ee2f45(this.f$1);
                    }
                });
            }
            arrayList.add(listenableFuture);
        }
        return Futures.transformAsync(Futures.transformAsync(Futures.transform(Futures.allAsList(arrayList), C0520x13abc48b.INSTANCE, this.lightweightExecutorService), new AsyncFunction(l) {
            private final /* synthetic */ Long f$1;

            {
                this.f$1 = r2;
            }

            public final ListenableFuture apply(Object obj) {
                return Cp2DefaultDirectoryPhoneLookup.this.lambda$isDirty$3$Cp2DefaultDirectoryPhoneLookup(this.f$1, (Set) obj);
            }
        }, MoreExecutors.directExecutor()), new AsyncFunction(l) {
            private final /* synthetic */ Long f$1;

            {
                this.f$1 = r2;
            }

            public final ListenableFuture apply(Object obj) {
                return Cp2DefaultDirectoryPhoneLookup.this.lambda$isDirty$5$Cp2DefaultDirectoryPhoneLookup(this.f$1, (Boolean) obj);
            }
        }, MoreExecutors.directExecutor());
    }

    public /* synthetic */ ListenableFuture lambda$isDirty$7$Cp2DefaultDirectoryPhoneLookup(Long l, ImmutableSet immutableSet, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            return Futures.immediateFuture(true);
        }
        return Futures.transformAsync(this.backgroundExecutorService.submit(new Callable(l.longValue()) {
            private final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return Cp2DefaultDirectoryPhoneLookup.this.lambda$noContactsModifiedSince$14$Cp2DefaultDirectoryPhoneLookup(this.f$1);
            }
        }), new AsyncFunction(immutableSet, l) {
            private final /* synthetic */ ImmutableSet f$1;
            private final /* synthetic */ Long f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final ListenableFuture apply(Object obj) {
                return Cp2DefaultDirectoryPhoneLookup.this.lambda$isDirty$6$Cp2DefaultDirectoryPhoneLookup(this.f$1, this.f$2, (Boolean) obj);
            }
        }, MoreExecutors.directExecutor());
    }

    public /* synthetic */ ListenableFuture lambda$isDirty$8$Cp2DefaultDirectoryPhoneLookup(ImmutableSet immutableSet, Long l) throws Exception {
        return Futures.transformAsync(this.backgroundExecutorService.submit(new Callable(l.longValue()) {
            private final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return Cp2DefaultDirectoryPhoneLookup.this.lambda$anyContactsDeletedSince$15$Cp2DefaultDirectoryPhoneLookup(this.f$1);
            }
        }), new AsyncFunction(l, immutableSet) {
            private final /* synthetic */ Long f$1;
            private final /* synthetic */ ImmutableSet f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final ListenableFuture apply(Object obj) {
                return Cp2DefaultDirectoryPhoneLookup.this.lambda$isDirty$7$Cp2DefaultDirectoryPhoneLookup(this.f$1, this.f$2, (Boolean) obj);
            }
        }, MoreExecutors.directExecutor());
    }

    public /* synthetic */ PhoneLookupInfo.Cp2Info lambda$lookup$0$Cp2DefaultDirectoryPhoneLookup(DialerPhoneNumber dialerPhoneNumber) throws Exception {
        if (TextUtils.isEmpty(dialerPhoneNumber.getNormalizedNumber())) {
            return PhoneLookupInfo.Cp2Info.getDefaultInstance();
        }
        ArraySet arraySet = new ArraySet();
        PartitionedNumbers partitionedNumbers = new PartitionedNumbers(ImmutableSet.m87of(dialerPhoneNumber));
        Cursor cursor = null;
        try {
            if (!partitionedNumbers.validE164Numbers().isEmpty()) {
                cursor = queryPhoneTableBasedOnE164(Cp2Projections.getProjectionForPhoneTable(), partitionedNumbers.validE164Numbers());
            } else {
                cursor = queryPhoneLookup(Cp2Projections.getProjectionForPhoneLookupTable(), (String) Collections2.getOnlyElement(partitionedNumbers.invalidNumbers()));
            }
            if (cursor == null) {
                LogUtil.m10w("Cp2DefaultDirectoryPhoneLookup.lookupInternal", "null cursor", new Object[0]);
                PhoneLookupInfo.Cp2Info defaultInstance = PhoneLookupInfo.Cp2Info.getDefaultInstance();
            }
            while (cursor.moveToNext()) {
                arraySet.add(Cp2Projections.buildCp2ContactInfoFromCursor(this.appContext, cursor, 0));
            }
            cursor.close();
            PhoneLookupInfo.Cp2Info.Builder newBuilder = PhoneLookupInfo.Cp2Info.newBuilder();
            newBuilder.addAllCp2ContactInfo(arraySet);
            return (PhoneLookupInfo.Cp2Info) newBuilder.build();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0048, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0049, code lost:
        if (r7 != null) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004b, code lost:
        $closeResource(r6, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004e, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ java.lang.Boolean lambda$noContactsModifiedSince$14$Cp2DefaultDirectoryPhoneLookup(long r7) throws java.lang.Exception {
        /*
            r6 = this;
            android.content.Context r6 = r6.appContext
            android.content.ContentResolver r0 = r6.getContentResolver()
            android.net.Uri r1 = android.provider.ContactsContract.Contacts.CONTENT_URI
            java.lang.String r6 = "_id"
            java.lang.String[] r2 = new java.lang.String[]{r6}
            r6 = 1
            java.lang.String[] r4 = new java.lang.String[r6]
            java.lang.String r7 = java.lang.Long.toString(r7)
            r8 = 0
            r4[r8] = r7
            java.lang.String r3 = "contact_last_updated_timestamp > ?"
            java.lang.String r5 = "_id limit 1"
            android.database.Cursor r7 = r0.query(r1, r2, r3, r4, r5)
            r0 = 0
            if (r7 != 0) goto L_0x0036
            java.lang.String r6 = "Cp2DefaultDirectoryPhoneLookup.noContactsModifiedSince"
            java.lang.String r1 = "null cursor"
            java.lang.Object[] r2 = new java.lang.Object[r8]     // Catch:{ all -> 0x0046 }
            com.android.dialer.common.LogUtil.m10w(r6, r1, r2)     // Catch:{ all -> 0x0046 }
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r8)     // Catch:{ all -> 0x0046 }
            if (r7 == 0) goto L_0x0035
            $closeResource(r0, r7)
        L_0x0035:
            return r6
        L_0x0036:
            int r1 = r7.getCount()     // Catch:{ all -> 0x0046 }
            if (r1 != 0) goto L_0x003d
            goto L_0x003e
        L_0x003d:
            r6 = r8
        L_0x003e:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch:{ all -> 0x0046 }
            $closeResource(r0, r7)
            return r6
        L_0x0046:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0048 }
        L_0x0048:
            r8 = move-exception
            if (r7 == 0) goto L_0x004e
            $closeResource(r6, r7)
        L_0x004e:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonelookup.cp2.Cp2DefaultDirectoryPhoneLookup.lambda$noContactsModifiedSince$14$Cp2DefaultDirectoryPhoneLookup(long):java.lang.Boolean");
    }

    public /* synthetic */ Void lambda$onSuccessfulBulkUpdate$21$Cp2DefaultDirectoryPhoneLookup() throws Exception {
        if (this.currentLastTimestampProcessed == null) {
            return null;
        }
        this.sharedPreferences.edit().putLong("cp2DefaultDirectoryPhoneLookupLastTimestampProcessed", this.currentLastTimestampProcessed.longValue()).apply();
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0077, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0078, code lost:
        if (r1 != null) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007a, code lost:
        $closeResource(r7, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007d, code lost:
        throw r0;
     */
    /* renamed from: lambda$queryPhoneLookupHistoryForContactIds$10$Cp2DefaultDirectoryPhoneLookup */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ java.util.Set mo6727xd8824da2() throws java.lang.Exception {
        /*
            r7 = this;
            android.support.v4.util.ArraySet r0 = new android.support.v4.util.ArraySet
            r0.<init>()
            android.content.Context r7 = r7.appContext
            android.content.ContentResolver r1 = r7.getContentResolver()
            android.net.Uri r2 = com.android.dialer.phonelookup.database.contract.PhoneLookupHistoryContract.PhoneLookupHistory.CONTENT_URI
            java.lang.String r7 = "phone_lookup_info"
            java.lang.String[] r3 = new java.lang.String[]{r7}
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6)
            r2 = 0
            if (r1 != 0) goto L_0x002d
            java.lang.String r7 = "Cp2DefaultDirectoryPhoneLookup.queryPhoneLookupHistoryForContactIds"
            java.lang.String r3 = "null cursor"
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0075 }
            com.android.dialer.common.LogUtil.m10w(r7, r3, r4)     // Catch:{ all -> 0x0075 }
            if (r1 == 0) goto L_0x002c
            $closeResource(r2, r1)
        L_0x002c:
            return r0
        L_0x002d:
            boolean r3 = r1.moveToFirst()     // Catch:{ all -> 0x0075 }
            if (r3 == 0) goto L_0x0071
            int r7 = r1.getColumnIndexOrThrow(r7)     // Catch:{ all -> 0x0075 }
        L_0x0037:
            byte[] r3 = r1.getBlob(r7)     // Catch:{ InvalidProtocolBufferException -> 0x006a }
            com.android.dialer.phonelookup.PhoneLookupInfo r3 = com.android.dialer.phonelookup.PhoneLookupInfo.parseFrom(r3)     // Catch:{ InvalidProtocolBufferException -> 0x006a }
            com.android.dialer.phonelookup.PhoneLookupInfo$Cp2Info r3 = r3.getDefaultCp2Info()     // Catch:{ all -> 0x0075 }
            java.util.List r3 = r3.getCp2ContactInfoList()     // Catch:{ all -> 0x0075 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0075 }
        L_0x004b:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x0075 }
            if (r4 == 0) goto L_0x0063
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x0075 }
            com.android.dialer.phonelookup.PhoneLookupInfo$Cp2Info$Cp2ContactInfo r4 = (com.android.dialer.phonelookup.PhoneLookupInfo.Cp2Info.Cp2ContactInfo) r4     // Catch:{ all -> 0x0075 }
            long r4 = r4.getContactId()     // Catch:{ all -> 0x0075 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0075 }
            r0.add(r4)     // Catch:{ all -> 0x0075 }
            goto L_0x004b
        L_0x0063:
            boolean r3 = r1.moveToNext()     // Catch:{ all -> 0x0075 }
            if (r3 != 0) goto L_0x0037
            goto L_0x0071
        L_0x006a:
            r7 = move-exception
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0075 }
            r0.<init>(r7)     // Catch:{ all -> 0x0075 }
            throw r0     // Catch:{ all -> 0x0075 }
        L_0x0071:
            $closeResource(r2, r1)
            return r0
        L_0x0075:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0077 }
        L_0x0077:
            r0 = move-exception
            if (r1 == 0) goto L_0x007d
            $closeResource(r7, r1)
        L_0x007d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonelookup.cp2.Cp2DefaultDirectoryPhoneLookup.mo6727xd8824da2():java.util.Set");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003b, code lost:
        if (r4 != null) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003d, code lost:
        $closeResource(r5, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0040, code lost:
        throw r0;
     */
    /* renamed from: lambda$queryPhoneLookupTableForContactIdsBasedOnRawNumber$12$Cp2DefaultDirectoryPhoneLookup */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ java.util.Set mo6728x78ee2f45(java.lang.String r5) throws java.lang.Exception {
        /*
            r4 = this;
            android.support.v4.util.ArraySet r0 = new android.support.v4.util.ArraySet
            r0.<init>()
            java.lang.String r1 = "contact_id"
            java.lang.String[] r1 = new java.lang.String[]{r1}
            android.database.Cursor r4 = r4.queryPhoneLookup(r1, r5)
            r5 = 0
            r1 = 0
            if (r4 != 0) goto L_0x0022
            java.lang.String r2 = "Cp2DefaultDirectoryPhoneLookup.queryPhoneLookupTableForContactIdsBasedOnRawNumber"
            java.lang.String r3 = "null cursor"
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x0038 }
            com.android.dialer.common.LogUtil.m10w(r2, r3, r5)     // Catch:{ all -> 0x0038 }
            if (r4 == 0) goto L_0x0021
            $closeResource(r1, r4)
        L_0x0021:
            return r0
        L_0x0022:
            boolean r2 = r4.moveToNext()     // Catch:{ all -> 0x0038 }
            if (r2 == 0) goto L_0x0034
            long r2 = r4.getLong(r5)     // Catch:{ all -> 0x0038 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x0038 }
            r0.add(r2)     // Catch:{ all -> 0x0038 }
            goto L_0x0022
        L_0x0034:
            $closeResource(r1, r4)
            return r0
        L_0x0038:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x003a }
        L_0x003a:
            r0 = move-exception
            if (r4 == 0) goto L_0x0040
            $closeResource(r5, r4)
        L_0x0040:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonelookup.cp2.Cp2DefaultDirectoryPhoneLookup.mo6728x78ee2f45(java.lang.String):java.util.Set");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0041, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0042, code lost:
        if (r4 != null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0044, code lost:
        $closeResource(r5, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0047, code lost:
        throw r0;
     */
    /* renamed from: lambda$queryPhoneTableForContactIdsBasedOnE164$11$Cp2DefaultDirectoryPhoneLookup */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ java.util.Set mo6729x22c0949d(java.util.Set r5) throws java.lang.Exception {
        /*
            r4 = this;
            android.support.v4.util.ArraySet r0 = new android.support.v4.util.ArraySet
            r0.<init>()
            boolean r1 = r5.isEmpty()
            if (r1 == 0) goto L_0x000c
            return r0
        L_0x000c:
            java.lang.String r1 = "contact_id"
            java.lang.String[] r1 = new java.lang.String[]{r1}
            android.database.Cursor r4 = r4.queryPhoneTableBasedOnE164(r1, r5)
            r5 = 0
            r1 = 0
            if (r4 != 0) goto L_0x0029
            java.lang.String r2 = "Cp2DefaultDirectoryPhoneLookup.queryPhoneTableForContactIdsBasedOnE164"
            java.lang.String r3 = "null cursor"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x003f }
            com.android.dialer.common.LogUtil.m10w(r2, r3, r1)     // Catch:{ all -> 0x003f }
            if (r4 == 0) goto L_0x0028
            $closeResource(r5, r4)
        L_0x0028:
            return r0
        L_0x0029:
            boolean r2 = r4.moveToNext()     // Catch:{ all -> 0x003f }
            if (r2 == 0) goto L_0x003b
            long r2 = r4.getLong(r1)     // Catch:{ all -> 0x003f }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x003f }
            r0.add(r2)     // Catch:{ all -> 0x003f }
            goto L_0x0029
        L_0x003b:
            $closeResource(r5, r4)
            return r0
        L_0x003f:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0041 }
        L_0x0041:
            r0 = move-exception
            if (r4 == 0) goto L_0x0047
            $closeResource(r5, r4)
        L_0x0047:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonelookup.cp2.Cp2DefaultDirectoryPhoneLookup.mo6729x22c0949d(java.util.Set):java.util.Set");
    }

    public ListenableFuture<PhoneLookupInfo.Cp2Info> lookup(DialerPhoneNumber dialerPhoneNumber) {
        if (!PermissionsUtil.hasContactsReadPermissions(this.appContext)) {
            return Futures.immediateFuture(PhoneLookupInfo.Cp2Info.getDefaultInstance());
        }
        return this.backgroundExecutorService.submit(new Callable(dialerPhoneNumber) {
            private final /* synthetic */ DialerPhoneNumber f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return Cp2DefaultDirectoryPhoneLookup.this.lambda$lookup$0$Cp2DefaultDirectoryPhoneLookup(this.f$1);
            }
        });
    }

    public ListenableFuture<Void> onSuccessfulBulkUpdate() {
        return this.backgroundExecutorService.submit(new Callable() {
            public final Object call() {
                return Cp2DefaultDirectoryPhoneLookup.this.lambda$onSuccessfulBulkUpdate$21$Cp2DefaultDirectoryPhoneLookup();
            }
        });
    }

    public void registerContentObservers() {
    }

    public void setSubMessage(PhoneLookupInfo.Builder builder, Object obj) {
        builder.setDefaultCp2Info((PhoneLookupInfo.Cp2Info) obj);
    }

    public void unregisterContentObservers() {
    }
}
