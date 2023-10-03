package com.android.dialer.calllog.datasources.systemcalllog;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.CallLog;
import android.provider.VoicemailContract;
import com.android.dialer.calllog.database.AnnotatedCallLogDatabaseHelper;
import com.android.dialer.calllog.datasources.CallLogDataSource;
import com.android.dialer.calllog.datasources.CallLogMutations;
import com.android.dialer.calllog.observer.MarkDirtyObserver;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.duo.Duo;
import com.android.dialer.util.PermissionsUtil;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class SystemCallLogDataSource implements CallLogDataSource {
    static final String PREF_LAST_TIMESTAMP_PROCESSED = "systemCallLogLastTimestampProcessed";
    private static final String[] PROJECTION_O_AND_LATER;
    private static final String[] PROJECTION_PRE_O = {"_id", "date", "last_modified", "number", "presentation", "type", "countryiso", "duration", "data_usage", "transcription", "voicemail_uri", "is_read", "new", "geocoded_location", "subscription_component_name", "subscription_id", "features", "post_dial_digits"};
    private final AnnotatedCallLogDatabaseHelper annotatedCallLogDatabaseHelper;
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutorService;
    private final Duo duo;
    private boolean isCallLogContentObserverRegistered = false;
    private Long lastTimestampProcessed;
    private final MarkDirtyObserver markDirtyObserver;
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

    static {
        ArrayList arrayList = new ArrayList(Arrays.asList(PROJECTION_PRE_O));
        arrayList.add("transcription_state");
        PROJECTION_O_AND_LATER = (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    SystemCallLogDataSource(Context context, ListeningExecutorService listeningExecutorService, MarkDirtyObserver markDirtyObserver2, SharedPreferences sharedPreferences2, AnnotatedCallLogDatabaseHelper annotatedCallLogDatabaseHelper2, Duo duo2) {
        this.appContext = context;
        this.backgroundExecutorService = listeningExecutorService;
        this.markDirtyObserver = markDirtyObserver2;
        this.sharedPreferences = sharedPreferences2;
        this.annotatedCallLogDatabaseHelper = annotatedCallLogDatabaseHelper2;
        this.duo = duo2;
    }

    /* access modifiers changed from: private */
    public boolean isDirtyInternal() {
        Assert.isWorkerThread();
        return !this.sharedPreferences.contains(PREF_LAST_TIMESTAMP_PROCESSED);
    }

    static /* synthetic */ Void lambda$clearData$1(List list) {
        return null;
    }

    /* access modifiers changed from: private */
    public Void onSuccessfulFillInternal() {
        if (this.lastTimestampProcessed == null) {
            return null;
        }
        this.sharedPreferences.edit().putLong(PREF_LAST_TIMESTAMP_PROCESSED, this.lastTimestampProcessed.longValue()).apply();
        return null;
    }

    public ListenableFuture<Void> clearData() {
        return Futures.transform(Futures.allAsList((ListenableFuture<? extends V>[]) new ListenableFuture[]{this.backgroundExecutorService.submit(new Callable() {
            public final Object call() {
                return SystemCallLogDataSource.this.lambda$clearData$0$SystemCallLogDataSource();
            }
        }), this.annotatedCallLogDatabaseHelper.delete()}), $$Lambda$SystemCallLogDataSource$iX_5BlfV6ktmfIg6BAB0LA9aj0.INSTANCE, MoreExecutors.directExecutor());
    }

    public ListenableFuture<Void> fill(CallLogMutations callLogMutations) {
        return this.backgroundExecutorService.submit(new Callable(callLogMutations) {
            private final /* synthetic */ CallLogMutations f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return SystemCallLogDataSource.this.lambda$fill$2$SystemCallLogDataSource(this.f$1);
            }
        });
    }

    public String getLoggingName() {
        return "SystemCallLogDataSource";
    }

    public ListenableFuture<Boolean> isDirty() {
        if (this.isCallLogContentObserverRegistered || !PermissionsUtil.hasCallLogReadPermissions(this.appContext)) {
            return this.backgroundExecutorService.submit(new Callable() {
                public final Object call() {
                    return Boolean.valueOf(SystemCallLogDataSource.this.isDirtyInternal());
                }
            });
        }
        LogUtil.enterBlock("SystemCallLogDataSource.registerContentObservers");
        if (!PermissionsUtil.hasCallLogReadPermissions(this.appContext)) {
            LogUtil.m9i("SystemCallLogDataSource.registerContentObservers", "no call log permissions", new Object[0]);
        } else {
            this.appContext.getContentResolver().registerContentObserver(CallLog.Calls.CONTENT_URI_WITH_VOICEMAIL, true, this.markDirtyObserver);
            this.isCallLogContentObserverRegistered = true;
            if (!PermissionsUtil.hasAddVoicemailPermissions(this.appContext)) {
                LogUtil.m9i("SystemCallLogDataSource.registerContentObservers", "no add voicemail permissions", new Object[0]);
            } else {
                this.appContext.getContentResolver().registerContentObserver(VoicemailContract.Status.CONTENT_URI, true, this.markDirtyObserver);
            }
        }
        return Futures.immediateFuture(true);
    }

    public /* synthetic */ Void lambda$clearData$0$SystemCallLogDataSource() throws Exception {
        this.sharedPreferences.edit().remove(PREF_LAST_TIMESTAMP_PROCESSED).apply();
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00d2, code lost:
        if (r4 != null) goto L_0x00d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        com.android.dialer.common.LogUtil.m8e("SystemCallLogDataSource.getIdsFromSystemCallLog", r21, new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0386, code lost:
        if (r5 == null) goto L_0x03c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0388, code lost:
        $closeResource((java.lang.Throwable) null, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x038d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x038e, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x03bd, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x03be, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x03bf, code lost:
        if (r5 != null) goto L_0x03c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x03c1, code lost:
        $closeResource(r1, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x03c4, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0453, code lost:
        throw new java.lang.IllegalStateException("presentation is missing");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x045b, code lost:
        throw new java.lang.IllegalStateException("call type is missing");
     */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x03c5 A[EDGE_INSN: B:120:0x03c5->B:88:0x03c5 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00d6 A[Catch:{ all -> 0x045c, all -> 0x045f }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0319  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0401 A[LOOP:5: B:89:0x03fb->B:91:0x0401, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ java.lang.Void lambda$fill$2$SystemCallLogDataSource(com.android.dialer.calllog.datasources.CallLogMutations r58) throws java.lang.Exception {
        /*
            r57 = this;
            r0 = r57
            r1 = r58
            com.android.dialer.common.Assert.isWorkerThread()
            r2 = 0
            r0.lastTimestampProcessed = r2
            android.content.Context r3 = r0.appContext
            java.lang.String r4 = "android.permission.READ_CALL_LOG"
            boolean r3 = com.android.dialer.util.PermissionsUtil.hasPermission(r3, r4)
            java.lang.String r4 = "SystemCallLogDataSource.fill"
            r5 = 0
            if (r3 != 0) goto L_0x0020
            java.lang.Object[] r0 = new java.lang.Object[r5]
            java.lang.String r1 = "no call log permissions"
            com.android.dialer.common.LogUtil.m9i(r4, r1, r0)
            goto L_0x040f
        L_0x0020:
            boolean r3 = r58.isEmpty()
            com.android.dialer.common.Assert.checkArgument(r3)
            android.content.Context r3 = r0.appContext
            android.util.ArraySet r6 = new android.util.ArraySet
            r6.<init>()
            android.content.ContentResolver r7 = r3.getContentResolver()
            android.net.Uri r8 = com.android.dialer.calllog.database.contract.AnnotatedCallLogContract.AnnotatedCallLog.CONTENT_URI
            java.lang.String r3 = "_id"
            java.lang.String[] r9 = new java.lang.String[]{r3}
            r10 = 0
            r11 = 0
            r12 = 0
            android.database.Cursor r7 = r7.query(r8, r9, r10, r11, r12)
            java.lang.String r8 = "null cursor"
            if (r7 != 0) goto L_0x004f
            java.lang.String r9 = "SystemCallLogDataSource.getAnnotatedCallLogIds"
            java.lang.Object[] r10 = new java.lang.Object[r5]     // Catch:{ all -> 0x0467 }
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r9, (java.lang.String) r8, (java.lang.Object[]) r10)     // Catch:{ all -> 0x0467 }
            if (r7 == 0) goto L_0x006d
            goto L_0x006a
        L_0x004f:
            boolean r9 = r7.moveToFirst()     // Catch:{ all -> 0x0467 }
            if (r9 == 0) goto L_0x006a
            int r9 = r7.getColumnIndexOrThrow(r3)     // Catch:{ all -> 0x0467 }
        L_0x0059:
            long r10 = r7.getLong(r9)     // Catch:{ all -> 0x0467 }
            java.lang.Long r10 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x0467 }
            r6.add(r10)     // Catch:{ all -> 0x0467 }
            boolean r10 = r7.moveToNext()     // Catch:{ all -> 0x0467 }
            if (r10 != 0) goto L_0x0059
        L_0x006a:
            $closeResource(r2, r7)
        L_0x006d:
            r7 = 1
            java.lang.Object[] r9 = new java.lang.Object[r7]
            int r10 = r6.size()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r9[r5] = r10
            java.lang.String r10 = "found %d existing annotated call log ids"
            com.android.dialer.common.LogUtil.m9i(r4, r10, r9)
            android.content.Context r4 = r0.appContext
            java.lang.String r9 = "features"
            java.lang.String r10 = "geocoded_location"
            java.lang.String r11 = "new"
            java.lang.String r12 = "is_read"
            java.lang.String r13 = "voicemail_uri"
            java.lang.String r14 = "transcription"
            java.lang.String r15 = "data_usage"
            java.lang.String r2 = "duration"
            java.lang.String r5 = "presentation"
            java.lang.String r7 = "number"
            android.content.SharedPreferences r1 = r0.sharedPreferences
            r18 = r9
            r19 = r10
            r9 = 0
            r20 = r6
            java.lang.String r6 = "systemCallLogLastTimestampProcessed"
            long r9 = r1.getLong(r6, r9)
            com.android.dialer.phonenumberproto.DialerPhoneNumberUtil r1 = new com.android.dialer.phonenumberproto.DialerPhoneNumberUtil
            r1.<init>()
            android.content.ContentResolver r21 = r4.getContentResolver()
            android.net.Uri r22 = android.provider.CallLog.Calls.CONTENT_URI_WITH_VOICEMAIL
            int r4 = android.os.Build.VERSION.SDK_INT
            java.lang.String[] r23 = PROJECTION_O_AND_LATER
            r4 = 1
            java.lang.String[] r6 = new java.lang.String[r4]
            java.lang.String r4 = java.lang.String.valueOf(r9)
            r9 = 0
            r6[r9] = r4
            java.lang.String r24 = "last_modified > ? AND deleted = 0"
            java.lang.String r26 = "last_modified DESC LIMIT 1000"
            r25 = r6
            android.database.Cursor r4 = r21.query(r22, r23, r24, r25, r26)
            java.lang.String r6 = "SystemCallLogDataSource.handleInsertsAndUpdates"
            if (r4 != 0) goto L_0x00d6
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x045c }
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r6, (java.lang.String) r8, (java.lang.Object[]) r2)     // Catch:{ all -> 0x045c }
            if (r4 == 0) goto L_0x00e8
        L_0x00d4:
            r1 = 0
            goto L_0x00e5
        L_0x00d6:
            boolean r9 = r4.moveToFirst()     // Catch:{ all -> 0x045c }
            if (r9 != 0) goto L_0x00f2
            java.lang.String r1 = "no entries to insert/update"
            r2 = 0
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ all -> 0x045c }
            com.android.dialer.common.LogUtil.m9i(r6, r1, r5)     // Catch:{ all -> 0x045c }
            goto L_0x00d4
        L_0x00e5:
            $closeResource(r1, r4)
        L_0x00e8:
            r1 = r58
            r22 = r3
            r21 = r8
            r2 = r20
            goto L_0x0302
        L_0x00f2:
            java.lang.String r9 = "found %d entries to insert/update"
            r21 = r8
            r10 = 1
            java.lang.Object[] r8 = new java.lang.Object[r10]     // Catch:{ all -> 0x045c }
            int r10 = r4.getCount()     // Catch:{ all -> 0x045c }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x045c }
            r17 = 0
            r8[r17] = r10     // Catch:{ all -> 0x045c }
            com.android.dialer.common.LogUtil.m9i(r6, r9, r8)     // Catch:{ all -> 0x045c }
            int r6 = r4.getColumnIndexOrThrow(r3)     // Catch:{ all -> 0x045c }
            java.lang.String r8 = "date"
            int r8 = r4.getColumnIndexOrThrow(r8)     // Catch:{ all -> 0x045c }
            java.lang.String r9 = "last_modified"
            int r9 = r4.getColumnIndexOrThrow(r9)     // Catch:{ all -> 0x045c }
            int r10 = r4.getColumnIndexOrThrow(r7)     // Catch:{ all -> 0x045c }
            r22 = r3
            int r3 = r4.getColumnIndexOrThrow(r5)     // Catch:{ all -> 0x045c }
            r23 = r5
            java.lang.String r5 = "type"
            int r5 = r4.getColumnIndexOrThrow(r5)     // Catch:{ all -> 0x045c }
            r24 = r7
            java.lang.String r7 = "countryiso"
            int r7 = r4.getColumnIndexOrThrow(r7)     // Catch:{ all -> 0x045c }
            r25 = r1
            int r1 = r4.getColumnIndexOrThrow(r2)     // Catch:{ all -> 0x045c }
            r26 = r2
            int r2 = r4.getColumnIndexOrThrow(r15)     // Catch:{ all -> 0x045c }
            r27 = r15
            int r15 = r4.getColumnIndexOrThrow(r14)     // Catch:{ all -> 0x045c }
            r28 = r14
            int r14 = r4.getColumnIndexOrThrow(r13)     // Catch:{ all -> 0x045c }
            r29 = r13
            int r13 = r4.getColumnIndexOrThrow(r12)     // Catch:{ all -> 0x045c }
            r30 = r12
            int r12 = r4.getColumnIndexOrThrow(r11)     // Catch:{ all -> 0x045c }
            r31 = r11
            r11 = r19
            r19 = r12
            int r12 = r4.getColumnIndexOrThrow(r11)     // Catch:{ all -> 0x045c }
            r32 = r11
            java.lang.String r11 = "subscription_component_name"
            int r11 = r4.getColumnIndexOrThrow(r11)     // Catch:{ all -> 0x045c }
            r33 = r11
            java.lang.String r11 = "subscription_id"
            int r11 = r4.getColumnIndexOrThrow(r11)     // Catch:{ all -> 0x045c }
            r34 = r11
            r11 = r18
            r18 = r12
            int r12 = r4.getColumnIndexOrThrow(r11)     // Catch:{ all -> 0x045c }
            r35 = r11
            java.lang.String r11 = "post_dial_digits"
            int r11 = r4.getColumnIndexOrThrow(r11)     // Catch:{ all -> 0x045c }
            long r36 = r4.getLong(r9)     // Catch:{ all -> 0x045c }
            java.lang.Long r9 = java.lang.Long.valueOf(r36)     // Catch:{ all -> 0x045c }
            r0.lastTimestampProcessed = r9     // Catch:{ all -> 0x045c }
            r36 = r11
            r9 = r12
        L_0x018f:
            long r11 = r4.getLong(r6)     // Catch:{ all -> 0x045c }
            long r37 = r4.getLong(r8)     // Catch:{ all -> 0x045c }
            r39 = r6
            java.lang.String r6 = r4.getString(r10)     // Catch:{ all -> 0x045c }
            boolean r40 = r4.isNull(r5)     // Catch:{ all -> 0x045c }
            if (r40 != 0) goto L_0x0454
            int r40 = r4.getInt(r5)     // Catch:{ all -> 0x045c }
            if (r40 == 0) goto L_0x0454
            boolean r41 = r4.isNull(r3)     // Catch:{ all -> 0x045c }
            if (r41 != 0) goto L_0x044c
            int r41 = r4.getInt(r3)     // Catch:{ all -> 0x045c }
            if (r41 == 0) goto L_0x044c
            r42 = r3
            java.lang.String r3 = r4.getString(r7)     // Catch:{ all -> 0x045c }
            int r43 = r4.getInt(r1)     // Catch:{ all -> 0x045c }
            int r44 = r4.getInt(r2)     // Catch:{ all -> 0x045c }
            r45 = r1
            java.lang.String r1 = r4.getString(r15)     // Catch:{ all -> 0x045c }
            r46 = r2
            java.lang.String r2 = r4.getString(r14)     // Catch:{ all -> 0x045c }
            int r47 = r4.getInt(r13)     // Catch:{ all -> 0x045c }
            r48 = r5
            r5 = r19
            int r19 = r4.getInt(r5)     // Catch:{ all -> 0x045c }
            r49 = r5
            r5 = r18
            r18 = r7
            java.lang.String r7 = r4.getString(r5)     // Catch:{ all -> 0x045c }
            r50 = r5
            r5 = r33
            r33 = r8
            java.lang.String r8 = r4.getString(r5)     // Catch:{ all -> 0x045c }
            r51 = r5
            r5 = r34
            r34 = r10
            java.lang.String r10 = r4.getString(r5)     // Catch:{ all -> 0x045c }
            int r52 = r4.getInt(r9)     // Catch:{ all -> 0x045c }
            r53 = r5
            r5 = r36
            r36 = r9
            java.lang.String r9 = r4.getString(r5)     // Catch:{ all -> 0x045c }
            r54 = r5
            com.android.dialer.duo.Duo r5 = r0.duo     // Catch:{ all -> 0x045c }
            com.android.dialer.duo.stub.DuoStub r5 = (com.android.dialer.duo.stub.DuoStub) r5     // Catch:{ all -> 0x045c }
            r5.isDuoAccount((java.lang.String) r8)     // Catch:{ all -> 0x045c }
            android.content.ContentValues r5 = new android.content.ContentValues     // Catch:{ all -> 0x045c }
            r5.<init>()     // Catch:{ all -> 0x045c }
            r55 = r13
            java.lang.String r13 = "timestamp"
            r56 = r14
            java.lang.Long r14 = java.lang.Long.valueOf(r37)     // Catch:{ all -> 0x045c }
            r5.put(r13, r14)     // Catch:{ all -> 0x045c }
            boolean r13 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x045c }
            if (r13 != 0) goto L_0x0258
            if (r9 != 0) goto L_0x022e
        L_0x022a:
            r9 = r6
            r6 = r25
            goto L_0x023e
        L_0x022e:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x045c }
            r13.<init>()     // Catch:{ all -> 0x045c }
            r13.append(r6)     // Catch:{ all -> 0x045c }
            r13.append(r9)     // Catch:{ all -> 0x045c }
            java.lang.String r6 = r13.toString()     // Catch:{ all -> 0x045c }
            goto L_0x022a
        L_0x023e:
            com.android.dialer.DialerPhoneNumber r13 = r6.parse(r9, r3)     // Catch:{ all -> 0x045c }
            byte[] r13 = r13.toByteArray()     // Catch:{ all -> 0x045c }
            r14 = r24
            r5.put(r14, r13)     // Catch:{ all -> 0x045c }
            java.lang.String r3 = android.telephony.PhoneNumberUtils.formatNumber(r9, r3)     // Catch:{ all -> 0x045c }
            if (r3 != 0) goto L_0x0252
            r3 = r9
        L_0x0252:
            java.lang.String r9 = "formatted_number"
            r5.put(r9, r3)     // Catch:{ all -> 0x045c }
            goto L_0x0267
        L_0x0258:
            r14 = r24
            r6 = r25
            com.android.dialer.DialerPhoneNumber r3 = com.android.dialer.DialerPhoneNumber.getDefaultInstance()     // Catch:{ all -> 0x045c }
            byte[] r3 = r3.toByteArray()     // Catch:{ all -> 0x045c }
            r5.put(r14, r3)     // Catch:{ all -> 0x045c }
        L_0x0267:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r41)     // Catch:{ all -> 0x045c }
            r9 = r23
            r5.put(r9, r3)     // Catch:{ all -> 0x045c }
            java.lang.String r3 = "call_type"
            java.lang.Integer r13 = java.lang.Integer.valueOf(r40)     // Catch:{ all -> 0x045c }
            r5.put(r3, r13)     // Catch:{ all -> 0x045c }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r47)     // Catch:{ all -> 0x045c }
            r13 = r30
            r5.put(r13, r3)     // Catch:{ all -> 0x045c }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r19)     // Catch:{ all -> 0x045c }
            r25 = r6
            r6 = r31
            r5.put(r6, r3)     // Catch:{ all -> 0x045c }
            r3 = r32
            r5.put(r3, r7)     // Catch:{ all -> 0x045c }
            java.lang.String r7 = "phone_account_component_name"
            r5.put(r7, r8)     // Catch:{ all -> 0x045c }
            java.lang.String r7 = "phone_account_id"
            r5.put(r7, r10)     // Catch:{ all -> 0x045c }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r52)     // Catch:{ all -> 0x045c }
            r8 = r35
            r5.put(r8, r7)     // Catch:{ all -> 0x045c }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r43)     // Catch:{ all -> 0x045c }
            r10 = r26
            r5.put(r10, r7)     // Catch:{ all -> 0x045c }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r44)     // Catch:{ all -> 0x045c }
            r19 = r3
            r3 = r27
            r5.put(r3, r7)     // Catch:{ all -> 0x045c }
            r7 = r28
            r5.put(r7, r1)     // Catch:{ all -> 0x045c }
            r1 = r29
            r5.put(r1, r2)     // Catch:{ all -> 0x045c }
            java.lang.String r2 = "call_mapping_id"
            r29 = r1
            java.lang.String r1 = java.lang.String.valueOf(r37)     // Catch:{ all -> 0x045c }
            r5.put(r2, r1)     // Catch:{ all -> 0x045c }
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x045c }
            java.lang.String r1 = "transcription_state"
            int r2 = r4.getColumnIndexOrThrow(r1)     // Catch:{ all -> 0x045c }
            int r2 = r4.getInt(r2)     // Catch:{ all -> 0x045c }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x045c }
            r5.put(r1, r2)     // Catch:{ all -> 0x045c }
            java.lang.Long r1 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x045c }
            r2 = r20
            boolean r1 = r2.contains(r1)     // Catch:{ all -> 0x045c }
            if (r1 == 0) goto L_0x02f3
            r1 = r58
            r1.update(r11, r5)     // Catch:{ all -> 0x045c }
            goto L_0x02f8
        L_0x02f3:
            r1 = r58
            r1.insert(r11, r5)     // Catch:{ all -> 0x045c }
        L_0x02f8:
            boolean r5 = r4.moveToNext()     // Catch:{ all -> 0x045c }
            if (r5 != 0) goto L_0x0412
            r5 = 0
            $closeResource(r5, r4)
        L_0x0302:
            android.content.Context r0 = r0.appContext
            android.util.ArraySet r3 = new android.util.ArraySet
            r3.<init>()
            r4 = 999(0x3e7, float:1.4E-42)
            java.lang.Iterable r4 = com.google.common.collect.Collections2.partition(r2, r4)
            java.util.Iterator r4 = r4.iterator()
        L_0x0313:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x03c5
            java.lang.Object r5 = r4.next()
            java.util.List r5 = (java.util.List) r5
            int r6 = r5.size()
            java.lang.String[] r6 = new java.lang.String[r6]
            java.lang.String r7 = "?"
            java.util.Arrays.fill(r6, r7)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "_id in ("
            r7.append(r8)
            java.lang.String r8 = ","
            java.lang.String r6 = android.text.TextUtils.join(r8, r6)
            r7.append(r6)
            java.lang.String r6 = ")"
            r7.append(r6)
            java.lang.String r11 = r7.toString()
            int r6 = r5.size()
            java.lang.String[] r12 = new java.lang.String[r6]
            java.util.Iterator r5 = r5.iterator()
            r6 = 0
        L_0x0351:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto L_0x036b
            java.lang.Object r7 = r5.next()
            java.lang.Long r7 = (java.lang.Long) r7
            long r7 = r7.longValue()
            int r9 = r6 + 1
            java.lang.String r7 = java.lang.String.valueOf(r7)
            r12[r6] = r7
            r6 = r9
            goto L_0x0351
        L_0x036b:
            android.content.ContentResolver r8 = r0.getContentResolver()
            android.net.Uri r9 = android.provider.CallLog.Calls.CONTENT_URI_WITH_VOICEMAIL
            java.lang.String[] r10 = new java.lang.String[]{r22}
            r13 = 0
            android.database.Cursor r5 = r8.query(r9, r10, r11, r12, r13)
            if (r5 != 0) goto L_0x0390
            java.lang.String r0 = "SystemCallLogDataSource.getIdsFromSystemCallLog"
            r4 = 0
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch:{ all -> 0x038d }
            r11 = r21
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r0, (java.lang.String) r11, (java.lang.Object[]) r6)     // Catch:{ all -> 0x038d }
            if (r5 == 0) goto L_0x03c5
            r0 = 0
            $closeResource(r0, r5)
            goto L_0x03c5
        L_0x038d:
            r0 = move-exception
            r1 = r0
            goto L_0x03bc
        L_0x0390:
            r11 = r21
            boolean r6 = r5.moveToFirst()     // Catch:{ all -> 0x038d }
            if (r6 == 0) goto L_0x03b0
            r12 = r22
            int r6 = r5.getColumnIndexOrThrow(r12)     // Catch:{ all -> 0x038d }
        L_0x039e:
            long r7 = r5.getLong(r6)     // Catch:{ all -> 0x038d }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x038d }
            r3.add(r7)     // Catch:{ all -> 0x038d }
            boolean r7 = r5.moveToNext()     // Catch:{ all -> 0x038d }
            if (r7 != 0) goto L_0x039e
            goto L_0x03b2
        L_0x03b0:
            r12 = r22
        L_0x03b2:
            r6 = 0
            $closeResource(r6, r5)
            r21 = r11
            r22 = r12
            goto L_0x0313
        L_0x03bc:
            throw r1     // Catch:{ all -> 0x03bd }
        L_0x03bd:
            r0 = move-exception
            r2 = r0
            if (r5 == 0) goto L_0x03c4
            $closeResource(r1, r5)
        L_0x03c4:
            throw r2
        L_0x03c5:
            r5 = 1
            java.lang.Object[] r0 = new java.lang.Object[r5]
            int r4 = r3.size()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r17 = 0
            r0[r17] = r4
            java.lang.String r4 = "SystemCallLogDataSource.handleDeletes"
            java.lang.String r6 = "found %d matching entries in system call log"
            com.android.dialer.common.LogUtil.m9i(r4, r6, r0)
            android.util.ArraySet r0 = new android.util.ArraySet
            r0.<init>()
            r0.addAll(r2)
            r0.removeAll(r3)
            java.lang.Object[] r2 = new java.lang.Object[r5]
            int r3 = r0.size()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2[r17] = r3
            java.lang.String r3 = "found %d call log entries to remove"
            com.android.dialer.common.LogUtil.m9i(r4, r3, r2)
            java.util.Iterator r0 = r0.iterator()
        L_0x03fb:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x040f
            java.lang.Object r2 = r0.next()
            java.lang.Long r2 = (java.lang.Long) r2
            long r2 = r2.longValue()
            r1.delete(r2)
            goto L_0x03fb
        L_0x040f:
            r16 = 0
            return r16
        L_0x0412:
            r16 = 0
            r17 = 0
            r20 = r2
            r27 = r3
            r31 = r6
            r28 = r7
            r35 = r8
            r23 = r9
            r26 = r10
            r30 = r13
            r24 = r14
            r7 = r18
            r32 = r19
            r8 = r33
            r10 = r34
            r9 = r36
            r6 = r39
            r3 = r42
            r1 = r45
            r2 = r46
            r5 = r48
            r19 = r49
            r18 = r50
            r33 = r51
            r34 = r53
            r36 = r54
            r13 = r55
            r14 = r56
            goto L_0x018f
        L_0x044c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x045c }
            java.lang.String r1 = "presentation is missing"
            r0.<init>(r1)     // Catch:{ all -> 0x045c }
            throw r0     // Catch:{ all -> 0x045c }
        L_0x0454:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x045c }
            java.lang.String r1 = "call type is missing"
            r0.<init>(r1)     // Catch:{ all -> 0x045c }
            throw r0     // Catch:{ all -> 0x045c }
        L_0x045c:
            r0 = move-exception
            r1 = r0
            throw r1     // Catch:{ all -> 0x045f }
        L_0x045f:
            r0 = move-exception
            r2 = r0
            if (r4 == 0) goto L_0x0466
            $closeResource(r1, r4)
        L_0x0466:
            throw r2
        L_0x0467:
            r0 = move-exception
            r1 = r0
            throw r1     // Catch:{ all -> 0x046a }
        L_0x046a:
            r0 = move-exception
            r2 = r0
            if (r7 == 0) goto L_0x0471
            $closeResource(r1, r7)
        L_0x0471:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.calllog.datasources.systemcalllog.SystemCallLogDataSource.lambda$fill$2$SystemCallLogDataSource(com.android.dialer.calllog.datasources.CallLogMutations):java.lang.Void");
    }

    public ListenableFuture<Void> onSuccessfulFill() {
        return this.backgroundExecutorService.submit(new Callable() {
            public final Object call() {
                return SystemCallLogDataSource.this.onSuccessfulFillInternal();
            }
        });
    }

    public void registerContentObservers() {
        LogUtil.enterBlock("SystemCallLogDataSource.registerContentObservers");
        if (!PermissionsUtil.hasCallLogReadPermissions(this.appContext)) {
            LogUtil.m9i("SystemCallLogDataSource.registerContentObservers", "no call log permissions", new Object[0]);
            return;
        }
        this.appContext.getContentResolver().registerContentObserver(CallLog.Calls.CONTENT_URI_WITH_VOICEMAIL, true, this.markDirtyObserver);
        this.isCallLogContentObserverRegistered = true;
        if (!PermissionsUtil.hasAddVoicemailPermissions(this.appContext)) {
            LogUtil.m9i("SystemCallLogDataSource.registerContentObservers", "no add voicemail permissions", new Object[0]);
        } else {
            this.appContext.getContentResolver().registerContentObserver(VoicemailContract.Status.CONTENT_URI, true, this.markDirtyObserver);
        }
    }

    public void unregisterContentObservers() {
        this.appContext.getContentResolver().unregisterContentObserver(this.markDirtyObserver);
        this.isCallLogContentObserverRegistered = false;
    }
}
