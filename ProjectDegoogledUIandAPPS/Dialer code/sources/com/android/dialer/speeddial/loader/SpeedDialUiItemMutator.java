package com.android.dialer.speeddial.loader;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.ArraySet;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerFutureSerializer;
import com.android.dialer.contacts.displaypreference.ContactDisplayPreferences;
import com.android.dialer.contacts.hiresphoto.HighResolutionPhotoRequester;
import com.android.dialer.duo.DuoComponent;
import com.android.dialer.duo.stub.DuoStub;
import com.android.dialer.speeddial.database.SpeedDialEntry;
import com.android.dialer.speeddial.database.SpeedDialEntryDao;
import com.android.dialer.speeddial.database.SpeedDialEntryDatabaseHelper;
import com.android.dialer.speeddial.loader.SpeedDialUiItem;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

public final class SpeedDialUiItemMutator {
    private final Context appContext;
    private final ListeningExecutorService backgroundExecutor;
    private final ContactDisplayPreferences contactDisplayPreferences;
    private final DialerFutureSerializer dialerFutureSerializer = new DialerFutureSerializer();
    private final HighResolutionPhotoRequester highResolutionPhotoRequester;

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

    public SpeedDialUiItemMutator(Context context, ListeningExecutorService listeningExecutorService, ContactDisplayPreferences contactDisplayPreferences2, HighResolutionPhotoRequester highResolutionPhotoRequester2) {
        this.appContext = context;
        this.backgroundExecutor = listeningExecutorService;
        this.contactDisplayPreferences = contactDisplayPreferences2;
        this.highResolutionPhotoRequester = highResolutionPhotoRequester2;
    }

    private SpeedDialEntryDao getSpeedDialEntryDao() {
        return new SpeedDialEntryDatabaseHelper(this.appContext);
    }

    private boolean isPrimaryDisplayNameOrder() {
        return this.contactDisplayPreferences.getDisplayOrder() == ContactDisplayPreferences.DisplayOrder.PRIMARY;
    }

    static /* synthetic */ boolean lambda$loadSpeedDialUiItemsInternal$2(SpeedDialUiItem speedDialUiItem, SpeedDialUiItem speedDialUiItem2) {
        return speedDialUiItem2.contactId() == speedDialUiItem.contactId();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00af, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00b0, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00b2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00b3, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00b4, code lost:
        if (r11 != null) goto L_0x00b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00b6, code lost:
        $closeResource(r1, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b9, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        com.android.dialer.common.LogUtil.m8e("SpeedDialUiItemMutator.updateContactIdsAndLookupKeys", "null cursor", new java.lang.Object[0]);
        r7 = new java.util.ArrayList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x007c, code lost:
        if (r11 == null) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x007e, code lost:
        $closeResource((java.lang.Throwable) null, r11);
     */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x038a  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x03c5 A[LOOP:9: B:122:0x03bf->B:124:0x03c5, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x040a  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x040f  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x01a5 A[Catch:{ all -> 0x047e, all -> 0x0481 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.common.collect.ImmutableList<com.android.dialer.speeddial.loader.SpeedDialUiItem> loadSpeedDialUiItemsInternal() {
        /*
            r19 = this;
            r0 = r19
            java.lang.String r1 = "loadSpeedDialUiItemsInternal"
            android.os.Trace.beginSection(r1)
            com.android.dialer.common.Assert.isWorkerThread()
            java.lang.String r1 = "getAllEntries"
            android.os.Trace.beginSection(r1)
            com.android.dialer.speeddial.database.SpeedDialEntryDao r1 = r19.getSpeedDialEntryDao()
            android.os.Trace.endSection()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            com.android.dialer.speeddial.database.SpeedDialEntryDatabaseHelper r1 = (com.android.dialer.speeddial.database.SpeedDialEntryDatabaseHelper) r1
            com.google.common.collect.ImmutableList r6 = r1.getAllEntries()
            com.android.dialer.common.Assert.isWorkerThread()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Iterator r6 = r6.iterator()
        L_0x003c:
            boolean r8 = r6.hasNext()
            java.lang.String r9 = "null cursor"
            r10 = 0
            if (r8 == 0) goto L_0x00ba
            java.lang.Object r8 = r6.next()
            com.android.dialer.speeddial.database.SpeedDialEntry r8 = (com.android.dialer.speeddial.database.SpeedDialEntry) r8
            android.content.Context r11 = r0.appContext
            android.content.ContentResolver r12 = r11.getContentResolver()
            long r13 = r8.contactId()
            java.lang.String r11 = r8.lookupKey()
            android.net.Uri r13 = android.provider.ContactsContract.Contacts.getLookupUri(r13, r11)
            java.lang.String r11 = "_id"
            java.lang.String r14 = "lookup"
            java.lang.String[] r14 = new java.lang.String[]{r11, r14}
            r15 = 0
            r16 = 0
            r17 = 0
            android.database.Cursor r11 = r12.query(r13, r14, r15, r16, r17)
            if (r11 != 0) goto L_0x0083
            java.lang.String r6 = "SpeedDialUiItemMutator.updateContactIdsAndLookupKeys"
            java.lang.Object[] r7 = new java.lang.Object[r10]     // Catch:{ all -> 0x00af }
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r6, (java.lang.String) r9, (java.lang.Object[]) r7)     // Catch:{ all -> 0x00af }
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x00af }
            r7.<init>()     // Catch:{ all -> 0x00af }
            if (r11 == 0) goto L_0x00ba
            r6 = 0
            $closeResource(r6, r11)
            goto L_0x00ba
        L_0x0083:
            int r9 = r11.getCount()     // Catch:{ all -> 0x00af }
            if (r9 != 0) goto L_0x008d
            r7.add(r8)     // Catch:{ all -> 0x00af }
            goto L_0x00aa
        L_0x008d:
            r11.moveToFirst()     // Catch:{ all -> 0x00af }
            com.android.dialer.speeddial.database.SpeedDialEntry$Builder r8 = r8.toBuilder()     // Catch:{ all -> 0x00af }
            long r9 = r11.getLong(r10)     // Catch:{ all -> 0x00af }
            r8.setContactId(r9)     // Catch:{ all -> 0x00af }
            r9 = 1
            java.lang.String r9 = r11.getString(r9)     // Catch:{ all -> 0x00af }
            r8.setLookupKey(r9)     // Catch:{ all -> 0x00af }
            com.android.dialer.speeddial.database.SpeedDialEntry r8 = r8.build()     // Catch:{ all -> 0x00af }
            r7.add(r8)     // Catch:{ all -> 0x00af }
        L_0x00aa:
            r8 = 0
            $closeResource(r8, r11)
            goto L_0x003c
        L_0x00af:
            r0 = move-exception
            r1 = r0
            throw r1     // Catch:{ all -> 0x00b2 }
        L_0x00b2:
            r0 = move-exception
            r2 = r0
            if (r11 == 0) goto L_0x00b9
            $closeResource(r1, r11)
        L_0x00b9:
            throw r2
        L_0x00ba:
            java.lang.String r6 = "getSpeedDialUiItemsFromEntries"
            android.os.Trace.beginSection(r6)
            com.android.dialer.common.Assert.isWorkerThread()
            android.util.ArraySet r6 = new android.util.ArraySet
            r6.<init>()
            com.android.dialer.speeddial.loader.-$$Lambda$SpeedDialUiItemMutator$mw2dS3RuyCr8_WdE2tL5rYqlpEw r8 = new com.android.dialer.speeddial.loader.-$$Lambda$SpeedDialUiItemMutator$mw2dS3RuyCr8_WdE2tL5rYqlpEw
            r8.<init>(r6)
            r7.forEach(r8)
            boolean r8 = r6.isEmpty()
            java.lang.String r10 = "contact_id"
            if (r8 == 0) goto L_0x00e3
            android.os.Trace.endSection()
            android.util.ArrayMap r6 = new android.util.ArrayMap
            r6.<init>()
            r18 = r1
            goto L_0x01f0
        L_0x00e3:
            com.android.dialer.common.database.Selection$Builder r8 = com.android.dialer.common.database.Selection.builder()
            com.android.dialer.common.database.Selection$Column r11 = com.android.dialer.common.database.Selection.column(r10)
            com.android.dialer.common.database.Selection r6 = r11.mo5865in(r6)
            r8.and(r6)
            com.android.dialer.common.database.Selection r6 = r8.build()
            android.content.Context r8 = r0.appContext
            android.content.ContentResolver r11 = r8.getContentResolver()
            android.net.Uri r12 = android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            boolean r8 = r19.isPrimaryDisplayNameOrder()
            java.lang.String[] r13 = com.android.dialer.speeddial.loader.SpeedDialUiItem.getPhoneProjection(r8)
            java.lang.String r14 = r6.getSelection()
            java.lang.String[] r15 = r6.getSelectionArgs()
            r16 = 0
            android.database.Cursor r6 = r11.query(r12, r13, r14, r15, r16)
            android.util.ArrayMap r8 = new android.util.ArrayMap     // Catch:{ all -> 0x047e }
            r8.<init>()     // Catch:{ all -> 0x047e }
            r6.moveToFirst()     // Catch:{ all -> 0x047e }
        L_0x011c:
            boolean r11 = r6.isAfterLast()     // Catch:{ all -> 0x047e }
            if (r11 != 0) goto L_0x01d1
            android.content.Context r11 = r0.appContext     // Catch:{ all -> 0x047e }
            android.content.res.Resources r11 = r11.getResources()     // Catch:{ all -> 0x047e }
            android.content.Context r12 = r0.appContext     // Catch:{ all -> 0x047e }
            boolean r12 = com.android.dialer.util.CallUtil.isVideoEnabled(r12)     // Catch:{ all -> 0x047e }
            com.android.dialer.speeddial.loader.SpeedDialUiItem r11 = com.android.dialer.speeddial.loader.SpeedDialUiItem.fromCursor(r11, r6, r12)     // Catch:{ all -> 0x047e }
            java.util.Iterator r12 = r7.iterator()     // Catch:{ all -> 0x047e }
        L_0x0136:
            boolean r13 = r12.hasNext()     // Catch:{ all -> 0x047e }
            if (r13 == 0) goto L_0x011c
            java.lang.Object r13 = r12.next()     // Catch:{ all -> 0x047e }
            com.android.dialer.speeddial.database.SpeedDialEntry r13 = (com.android.dialer.speeddial.database.SpeedDialEntry) r13     // Catch:{ all -> 0x047e }
            long r14 = r13.contactId()     // Catch:{ all -> 0x047e }
            long r16 = r11.contactId()     // Catch:{ all -> 0x047e }
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 != 0) goto L_0x0136
            com.android.dialer.speeddial.loader.SpeedDialUiItem$Builder r14 = r11.toBuilder()     // Catch:{ all -> 0x047e }
            java.lang.Long r15 = r13.mo7325id()     // Catch:{ all -> 0x047e }
            r14.setSpeedDialEntryId(r15)     // Catch:{ all -> 0x047e }
            com.google.common.base.Optional r15 = r13.pinnedPosition()     // Catch:{ all -> 0x047e }
            r14.setPinnedPosition(r15)     // Catch:{ all -> 0x047e }
            com.android.dialer.speeddial.database.SpeedDialEntry$Channel r15 = r13.defaultChannel()     // Catch:{ all -> 0x047e }
            if (r15 == 0) goto L_0x01ae
            r16 = r12
            com.google.common.collect.ImmutableList r12 = r11.channels()     // Catch:{ all -> 0x047e }
            boolean r12 = r12.contains(r15)     // Catch:{ all -> 0x047e }
            if (r12 != 0) goto L_0x01a6
            com.google.common.collect.ImmutableList r12 = r11.channels()     // Catch:{ all -> 0x047e }
            r17 = r11
            int r11 = r15.technology()     // Catch:{ all -> 0x047e }
            r18 = r1
            r1 = 3
            if (r11 == r1) goto L_0x0182
            goto L_0x01a2
        L_0x0182:
            com.google.common.collect.UnmodifiableIterator r1 = r12.iterator()     // Catch:{ all -> 0x047e }
        L_0x0186:
            boolean r11 = r1.hasNext()     // Catch:{ all -> 0x047e }
            if (r11 == 0) goto L_0x01a2
            java.lang.Object r11 = r1.next()     // Catch:{ all -> 0x047e }
            com.android.dialer.speeddial.database.SpeedDialEntry$Channel r11 = (com.android.dialer.speeddial.database.SpeedDialEntry.Channel) r11     // Catch:{ all -> 0x047e }
            java.lang.String r11 = r11.number()     // Catch:{ all -> 0x047e }
            java.lang.String r12 = r15.number()     // Catch:{ all -> 0x047e }
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x047e }
            if (r11 == 0) goto L_0x0186
            r1 = 1
            goto L_0x01a3
        L_0x01a2:
            r1 = 0
        L_0x01a3:
            if (r1 == 0) goto L_0x01b4
            goto L_0x01aa
        L_0x01a6:
            r18 = r1
            r17 = r11
        L_0x01aa:
            r14.setDefaultChannel(r15)     // Catch:{ all -> 0x047e }
            goto L_0x01b4
        L_0x01ae:
            r18 = r1
            r17 = r11
            r16 = r12
        L_0x01b4:
            com.android.dialer.speeddial.loader.SpeedDialUiItem r1 = r14.build()     // Catch:{ all -> 0x047e }
            java.lang.Object r1 = r8.put(r13, r1)     // Catch:{ all -> 0x047e }
            if (r1 != 0) goto L_0x01c0
            r1 = 1
            goto L_0x01c1
        L_0x01c0:
            r1 = 0
        L_0x01c1:
            java.lang.String r11 = "Each SpeedDialEntry only has one correct SpeedDialUiItem"
            r12 = 0
            java.lang.Object[] r12 = new java.lang.Object[r12]     // Catch:{ all -> 0x047e }
            com.android.dialer.common.Assert.checkArgument(r1, r11, r12)     // Catch:{ all -> 0x047e }
            r12 = r16
            r11 = r17
            r1 = r18
            goto L_0x0136
        L_0x01d1:
            r18 = r1
            java.util.Iterator r1 = r7.iterator()     // Catch:{ all -> 0x047e }
        L_0x01d7:
            boolean r11 = r1.hasNext()     // Catch:{ all -> 0x047e }
            if (r11 == 0) goto L_0x01e8
            java.lang.Object r11 = r1.next()     // Catch:{ all -> 0x047e }
            com.android.dialer.speeddial.database.SpeedDialEntry r11 = (com.android.dialer.speeddial.database.SpeedDialEntry) r11     // Catch:{ all -> 0x047e }
            r12 = 0
            r8.putIfAbsent(r11, r12)     // Catch:{ all -> 0x047e }
            goto L_0x01d7
        L_0x01e8:
            r1 = 0
            android.os.Trace.endSection()     // Catch:{ all -> 0x047e }
            $closeResource(r1, r6)
            r6 = r8
        L_0x01f0:
            int r1 = r7.size()
            int r8 = r6.size()
            if (r1 != r8) goto L_0x01fc
            r1 = 1
            goto L_0x01fd
        L_0x01fc:
            r1 = 0
        L_0x01fd:
            java.lang.String r8 = "Updated entries are incomplete: "
            java.lang.StringBuilder r8 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r8)
            int r11 = r7.size()
            r8.append(r11)
            java.lang.String r11 = " != "
            r8.append(r11)
            int r11 = r6.size()
            r8.append(r11)
            java.lang.String r8 = r8.toString()
            r11 = 0
            java.lang.Object[] r11 = new java.lang.Object[r11]
            com.android.dialer.common.Assert.checkArgument(r1, r8, r11)
            java.lang.String r1 = "updateOrDeleteEntries"
            android.os.Trace.beginSection(r1)
            java.util.Iterator r1 = r7.iterator()
        L_0x0229:
            boolean r7 = r1.hasNext()
            if (r7 == 0) goto L_0x0270
            java.lang.Object r7 = r1.next()
            com.android.dialer.speeddial.database.SpeedDialEntry r7 = (com.android.dialer.speeddial.database.SpeedDialEntry) r7
            java.lang.Object r8 = r6.get(r7)
            com.android.dialer.speeddial.loader.SpeedDialUiItem r8 = (com.android.dialer.speeddial.loader.SpeedDialUiItem) r8
            if (r8 == 0) goto L_0x0268
            boolean r11 = r8.isStarred()
            if (r11 != 0) goto L_0x0244
            goto L_0x0268
        L_0x0244:
            com.android.dialer.speeddial.database.SpeedDialEntry$Builder r7 = r7.toBuilder()
            java.lang.String r11 = r8.lookupKey()
            r7.setLookupKey(r11)
            long r11 = r8.contactId()
            r7.setContactId(r11)
            com.android.dialer.speeddial.database.SpeedDialEntry$Channel r11 = r8.defaultChannel()
            r7.setDefaultChannel(r11)
            com.android.dialer.speeddial.database.SpeedDialEntry r7 = r7.build()
            r4.add(r7)
            r2.add(r8)
            goto L_0x0229
        L_0x0268:
            java.lang.Long r7 = r7.mo7325id()
            r5.add(r7)
            goto L_0x0229
        L_0x0270:
            android.os.Trace.endSection()
            java.lang.String r1 = "getStrequentContacts"
            android.os.Trace.beginSection(r1)
            com.android.dialer.common.Assert.isWorkerThread()
            android.util.ArraySet r1 = new android.util.ArraySet
            r1.<init>()
            android.net.Uri r6 = android.provider.ContactsContract.Contacts.CONTENT_STREQUENT_URI
            android.net.Uri$Builder r6 = r6.buildUpon()
            java.lang.String r7 = "strequent_phone_only"
            java.lang.String r8 = "true"
            android.net.Uri$Builder r6 = r6.appendQueryParameter(r7, r8)
            android.net.Uri r12 = r6.build()
            java.lang.String r6 = "starred"
            com.android.dialer.common.database.Selection$Column r6 = com.android.dialer.common.database.Selection.column(r6)
            r7 = 1
            java.lang.Integer r8 = java.lang.Integer.valueOf(r7)
            java.lang.String r11 = "="
            com.android.dialer.common.database.Selection r6 = r6.mo5867is(r11, r8)
            android.content.Context r8 = r0.appContext
            android.content.ContentResolver r11 = r8.getContentResolver()
            java.lang.String[] r13 = new java.lang.String[]{r10}
            java.lang.String r14 = r6.getSelection()
            java.lang.String[] r15 = r6.getSelectionArgs()
            r16 = 0
            android.database.Cursor r6 = r11.query(r12, r13, r14, r15, r16)
            if (r6 != 0) goto L_0x02d0
            java.lang.String r1 = "SpeedDialUiItemMutator.getStarredContacts"
            r8 = 0
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ all -> 0x0473 }
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r1, (java.lang.String) r9, (java.lang.Object[]) r8)     // Catch:{ all -> 0x0473 }
            android.os.Trace.endSection()     // Catch:{ all -> 0x0473 }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0473 }
            r1.<init>()     // Catch:{ all -> 0x0473 }
            if (r6 == 0) goto L_0x02e2
            goto L_0x02de
        L_0x02d0:
            int r8 = r6.getCount()     // Catch:{ all -> 0x0473 }
            if (r8 != 0) goto L_0x02e5
            android.os.Trace.endSection()     // Catch:{ all -> 0x0473 }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0473 }
            r1.<init>()     // Catch:{ all -> 0x0473 }
        L_0x02de:
            r8 = 0
            $closeResource(r8, r6)
        L_0x02e2:
            r6 = r1
            goto L_0x037b
        L_0x02e5:
            r6.moveToFirst()     // Catch:{ all -> 0x0473 }
        L_0x02e8:
            boolean r8 = r6.isAfterLast()     // Catch:{ all -> 0x0473 }
            if (r8 != 0) goto L_0x02fe
            r8 = 0
            long r11 = r6.getLong(r8)     // Catch:{ all -> 0x0473 }
            java.lang.String r8 = java.lang.Long.toString(r11)     // Catch:{ all -> 0x0473 }
            r1.add(r8)     // Catch:{ all -> 0x0473 }
            r6.moveToNext()     // Catch:{ all -> 0x0473 }
            goto L_0x02e8
        L_0x02fe:
            r8 = 0
            $closeResource(r8, r6)
            com.android.dialer.common.database.Selection$Builder r6 = com.android.dialer.common.database.Selection.builder()
            com.android.dialer.common.database.Selection$Column r8 = com.android.dialer.common.database.Selection.column(r10)
            com.android.dialer.common.database.Selection r1 = r8.mo5865in(r1)
            r6.and(r1)
            com.android.dialer.common.database.Selection r1 = r6.build()
            android.content.Context r6 = r0.appContext
            android.content.ContentResolver r10 = r6.getContentResolver()
            android.net.Uri r11 = android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            boolean r6 = r19.isPrimaryDisplayNameOrder()
            java.lang.String[] r12 = com.android.dialer.speeddial.loader.SpeedDialUiItem.getPhoneProjection(r6)
            java.lang.String r13 = r1.getSelection()
            java.lang.String[] r14 = r1.getSelectionArgs()
            r15 = 0
            android.database.Cursor r1 = r10.query(r11, r12, r13, r14, r15)
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x0468 }
            r6.<init>()     // Catch:{ all -> 0x0468 }
            if (r1 != 0) goto L_0x034c
            java.lang.String r6 = "SpeedDialUiItemMutator.getStrequentContacts"
            r8 = 0
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ all -> 0x0468 }
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r6, (java.lang.String) r9, (java.lang.Object[]) r8)     // Catch:{ all -> 0x0468 }
            android.os.Trace.endSection()     // Catch:{ all -> 0x0468 }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x0468 }
            r6.<init>()     // Catch:{ all -> 0x0468 }
            if (r1 == 0) goto L_0x037b
            goto L_0x0355
        L_0x034c:
            int r8 = r1.getCount()     // Catch:{ all -> 0x0468 }
            if (r8 != 0) goto L_0x0357
            android.os.Trace.endSection()     // Catch:{ all -> 0x0468 }
        L_0x0355:
            r8 = 0
            goto L_0x0378
        L_0x0357:
            r1.moveToFirst()     // Catch:{ all -> 0x0468 }
        L_0x035a:
            boolean r8 = r1.isAfterLast()     // Catch:{ all -> 0x0468 }
            if (r8 != 0) goto L_0x0374
            android.content.Context r8 = r0.appContext     // Catch:{ all -> 0x0468 }
            android.content.res.Resources r8 = r8.getResources()     // Catch:{ all -> 0x0468 }
            android.content.Context r9 = r0.appContext     // Catch:{ all -> 0x0468 }
            boolean r9 = com.android.dialer.util.CallUtil.isVideoEnabled(r9)     // Catch:{ all -> 0x0468 }
            com.android.dialer.speeddial.loader.SpeedDialUiItem r8 = com.android.dialer.speeddial.loader.SpeedDialUiItem.fromCursor(r8, r1, r9)     // Catch:{ all -> 0x0468 }
            r6.add(r8)     // Catch:{ all -> 0x0468 }
            goto L_0x035a
        L_0x0374:
            android.os.Trace.endSection()     // Catch:{ all -> 0x0468 }
            goto L_0x0355
        L_0x0378:
            $closeResource(r8, r1)
        L_0x037b:
            java.lang.String r1 = "addStarredContact"
            android.os.Trace.beginSection(r1)
            java.util.Iterator r1 = r6.iterator()
        L_0x0384:
            boolean r6 = r1.hasNext()
            if (r6 == 0) goto L_0x03aa
            java.lang.Object r6 = r1.next()
            com.android.dialer.speeddial.loader.SpeedDialUiItem r6 = (com.android.dialer.speeddial.loader.SpeedDialUiItem) r6
            java.util.stream.Stream r8 = r2.stream()
            com.android.dialer.speeddial.loader.-$$Lambda$SpeedDialUiItemMutator$Shie_2Zk-_vj-zUg9EOrG9itRd4 r9 = new com.android.dialer.speeddial.loader.-$$Lambda$SpeedDialUiItemMutator$Shie_2Zk-_vj-zUg9EOrG9itRd4
            r9.<init>()
            boolean r8 = r8.noneMatch(r9)
            if (r8 == 0) goto L_0x0384
            com.android.dialer.speeddial.database.SpeedDialEntry r8 = r6.buildSpeedDialEntry()
            r3.add(r8)
            r2.add(r6)
            goto L_0x0384
        L_0x03aa:
            android.os.Trace.endSection()
            java.lang.String r1 = "insertUpdateAndDelete"
            android.os.Trace.beginSection(r1)
            android.content.Context r1 = r0.appContext
            com.android.dialer.contacts.ContactsComponent r1 = com.android.dialer.contacts.ContactsComponent.get(r1)
            r1.highResolutionPhotoLoader()
            java.util.Iterator r1 = r3.iterator()
        L_0x03bf:
            boolean r6 = r1.hasNext()
            if (r6 == 0) goto L_0x03ec
            java.lang.Object r6 = r1.next()
            com.android.dialer.speeddial.database.SpeedDialEntry r6 = (com.android.dialer.speeddial.database.SpeedDialEntry) r6
            long r8 = r6.contactId()
            java.lang.String r6 = r6.lookupKey()
            android.net.Uri r6 = android.provider.ContactsContract.Contacts.getLookupUri(r8, r6)
            com.android.dialer.contacts.hiresphoto.HighResolutionPhotoRequester r8 = r0.highResolutionPhotoRequester
            com.android.dialer.contacts.hiresphoto.HighResolutionPhotoRequesterImpl r8 = (com.android.dialer.contacts.hiresphoto.HighResolutionPhotoRequesterImpl) r8
            com.google.common.util.concurrent.ListenableFuture r6 = r8.request(r6)
            com.android.dialer.common.concurrent.DefaultFutureCallback r8 = new com.android.dialer.common.concurrent.DefaultFutureCallback
            r8.<init>()
            java.util.concurrent.Executor r9 = com.google.common.util.concurrent.MoreExecutors.directExecutor()
            com.google.common.util.concurrent.Futures.addCallback(r6, r8, r9)
            goto L_0x03bf
        L_0x03ec:
            com.google.common.collect.ImmutableList r0 = com.google.common.collect.ImmutableList.copyOf(r3)
            com.google.common.collect.ImmutableList r1 = com.google.common.collect.ImmutableList.copyOf(r4)
            com.google.common.collect.ImmutableList r3 = com.google.common.collect.ImmutableList.copyOf(r5)
            r4 = r18
            com.google.common.collect.ImmutableMap r0 = r4.insertUpdateAndDelete(r0, r1, r3)
            android.os.Trace.endSection()
            android.os.Trace.endSection()
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x040f
            com.google.common.collect.ImmutableList r0 = com.google.common.collect.ImmutableList.copyOf(r2)
            goto L_0x0467
        L_0x040f:
            com.google.common.collect.ImmutableList$Builder r1 = com.google.common.collect.ImmutableList.builder()
            java.util.Iterator r2 = r2.iterator()
        L_0x0417:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0463
            java.lang.Object r3 = r2.next()
            com.android.dialer.speeddial.loader.SpeedDialUiItem r3 = (com.android.dialer.speeddial.loader.SpeedDialUiItem) r3
            com.android.dialer.speeddial.database.SpeedDialEntry r4 = r3.buildSpeedDialEntry()
            boolean r5 = r0.containsKey(r4)
            if (r5 == 0) goto L_0x0445
            java.lang.Object r4 = r0.get(r4)
            java.lang.Long r4 = (java.lang.Long) r4
            com.android.dialer.common.Assert.isNotNull(r4)
            com.android.dialer.speeddial.loader.SpeedDialUiItem$Builder r3 = r3.toBuilder()
            r3.setSpeedDialEntryId(r4)
            com.android.dialer.speeddial.loader.SpeedDialUiItem r3 = r3.build()
            r1.add((java.lang.Object) r3)
            goto L_0x0417
        L_0x0445:
            boolean r4 = r3.isStarred()
            java.lang.Long r5 = r3.speedDialEntryId()
            if (r5 == 0) goto L_0x0451
            r5 = r7
            goto L_0x0452
        L_0x0451:
            r5 = 0
        L_0x0452:
            if (r4 != r5) goto L_0x0456
            r4 = r7
            goto L_0x0457
        L_0x0456:
            r4 = 0
        L_0x0457:
            r5 = 0
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r6 = "Contact must be starred with a speed dial entry id, or not starred with no id (suggested contacts)"
            com.android.dialer.common.Assert.checkArgument(r4, r6, r5)
            r1.add((java.lang.Object) r3)
            goto L_0x0417
        L_0x0463:
            com.google.common.collect.ImmutableList r0 = r1.build()
        L_0x0467:
            return r0
        L_0x0468:
            r0 = move-exception
            r2 = r0
            throw r2     // Catch:{ all -> 0x046b }
        L_0x046b:
            r0 = move-exception
            r3 = r0
            if (r1 == 0) goto L_0x0472
            $closeResource(r2, r1)
        L_0x0472:
            throw r3
        L_0x0473:
            r0 = move-exception
            r1 = r0
            throw r1     // Catch:{ all -> 0x0476 }
        L_0x0476:
            r0 = move-exception
            r2 = r0
            if (r6 == 0) goto L_0x047d
            $closeResource(r1, r6)
        L_0x047d:
            throw r2
        L_0x047e:
            r0 = move-exception
            r1 = r0
            throw r1     // Catch:{ all -> 0x0481 }
        L_0x0481:
            r0 = move-exception
            r2 = r0
            if (r6 == 0) goto L_0x0488
            $closeResource(r1, r6)
        L_0x0488:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.speeddial.loader.SpeedDialUiItemMutator.loadSpeedDialUiItemsInternal():com.google.common.collect.ImmutableList");
    }

    public ImmutableList<SpeedDialUiItem> insertDuoChannels(Context context, ImmutableList<SpeedDialUiItem> immutableList) {
        Assert.isMainThread();
        ImmutableList.Builder builder = ImmutableList.builder();
        UnmodifiableIterator<SpeedDialUiItem> it = immutableList.iterator();
        while (it.hasNext()) {
            SpeedDialUiItem next = it.next();
            if (next.defaultChannel() == null) {
                Assert.isMainThread();
                Assert.checkArgument(next.isStarred());
                ImmutableList.Builder builder2 = ImmutableList.builder();
                SpeedDialEntry.Channel channel = next.channels().get(0);
                builder2.add((Object) channel);
                int i = 1;
                while (i < next.channels().size()) {
                    SpeedDialEntry.Channel channel2 = next.channels().get(i);
                    if (!channel.isVideoTechnology() && !channel2.isVideoTechnology()) {
                        ((DuoStub) DuoComponent.get(context).getDuo()).isReachable(context, channel.number());
                    }
                    builder2.add((Object) channel2);
                    i++;
                    channel = channel2;
                }
                if (!channel.isVideoTechnology()) {
                    ((DuoStub) DuoComponent.get(context).getDuo()).isReachable(context, channel.number());
                }
                SpeedDialUiItem.Builder builder3 = next.toBuilder();
                builder3.setChannels(builder2.build());
                builder.add((Object) builder3.build());
            } else {
                builder.add((Object) next);
            }
        }
        return builder.build();
    }

    public /* synthetic */ ImmutableList lambda$removeSpeedDialUiItem$0$SpeedDialUiItemMutator(SpeedDialUiItem speedDialUiItem) throws Exception {
        Assert.isWorkerThread();
        Assert.checkArgument(speedDialUiItem.isStarred());
        Assert.isWorkerThread();
        Assert.checkArgument(speedDialUiItem.isStarred());
        SpeedDialEntryDatabaseHelper speedDialEntryDatabaseHelper = (SpeedDialEntryDatabaseHelper) getSpeedDialEntryDao();
        UnmodifiableIterator<SpeedDialEntry> it = speedDialEntryDatabaseHelper.getAllEntries().iterator();
        SpeedDialEntry speedDialEntry = null;
        int i = 0;
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            SpeedDialEntry next = it.next();
            if (next.contactId() == speedDialUiItem.contactId()) {
                i++;
            }
            if (Objects.equals(next.mo7325id(), speedDialUiItem.speedDialEntryId())) {
                if (speedDialEntry != null) {
                    z = false;
                }
                Assert.checkArgument(z);
                speedDialEntry = next;
            }
        }
        speedDialEntryDatabaseHelper.delete(ImmutableList.m75of(speedDialEntry.mo7325id()));
        if (i == 1) {
            Assert.isWorkerThread();
            ContentValues contentValues = new ContentValues();
            contentValues.put("starred", 0);
            this.appContext.getContentResolver().update(ContactsContract.Contacts.CONTENT_URI, contentValues, "_id = ?", new String[]{Long.toString(speedDialUiItem.contactId())});
        }
        return loadSpeedDialUiItemsInternal();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x008e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x008f, code lost:
        if (r11 != null) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0091, code lost:
        $closeResource(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0094, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ com.google.common.collect.ImmutableList lambda$starContact$1$SpeedDialUiItemMutator(android.net.Uri r11) throws java.lang.Exception {
        /*
            r10 = this;
            com.android.dialer.common.Assert.isWorkerThread()
            android.content.Context r0 = r10.appContext
            android.content.ContentResolver r1 = r0.getContentResolver()
            boolean r0 = r10.isPrimaryDisplayNameOrder()
            java.lang.String[] r3 = com.android.dialer.speeddial.loader.SpeedDialUiItem.getPhoneProjection(r0)
            r4 = 0
            r5 = 0
            r6 = 0
            r2 = r11
            android.database.Cursor r11 = r1.query(r2, r3, r4, r5, r6)
            r0 = 0
            r1 = 0
            if (r11 != 0) goto L_0x0030
            java.lang.String r2 = "SpeedDialUiItemMutator.insertNewContactEntry"
            java.lang.String r3 = "Cursor was null"
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x008c }
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r2, (java.lang.String) r3, (java.lang.Object[]) r0)     // Catch:{ all -> 0x008c }
            com.google.common.collect.ImmutableList r10 = r10.loadSpeedDialUiItemsInternal()     // Catch:{ all -> 0x008c }
            if (r11 == 0) goto L_0x008b
            $closeResource(r1, r11)
            goto L_0x008b
        L_0x0030:
            boolean r2 = r11.moveToFirst()     // Catch:{ all -> 0x008c }
            java.lang.String r3 = "Cursor should never be empty"
            java.lang.Object[] r4 = new java.lang.Object[r0]     // Catch:{ all -> 0x008c }
            com.android.dialer.common.Assert.checkArgument(r2, r3, r4)     // Catch:{ all -> 0x008c }
            android.content.Context r2 = r10.appContext     // Catch:{ all -> 0x008c }
            android.content.res.Resources r2 = r2.getResources()     // Catch:{ all -> 0x008c }
            android.content.Context r3 = r10.appContext     // Catch:{ all -> 0x008c }
            boolean r3 = com.android.dialer.util.CallUtil.isVideoEnabled(r3)     // Catch:{ all -> 0x008c }
            com.android.dialer.speeddial.loader.SpeedDialUiItem r2 = com.android.dialer.speeddial.loader.SpeedDialUiItem.fromCursor(r2, r11, r3)     // Catch:{ all -> 0x008c }
            boolean r3 = r2.isStarred()     // Catch:{ all -> 0x008c }
            if (r3 != 0) goto L_0x0077
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch:{ all -> 0x008c }
            r3.<init>()     // Catch:{ all -> 0x008c }
            java.lang.String r4 = "starred"
            java.lang.String r5 = "1"
            r3.put(r4, r5)     // Catch:{ all -> 0x008c }
            android.content.Context r4 = r10.appContext     // Catch:{ all -> 0x008c }
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch:{ all -> 0x008c }
            android.net.Uri r5 = android.provider.ContactsContract.Contacts.CONTENT_URI     // Catch:{ all -> 0x008c }
            java.lang.String r6 = "_id = ?"
            r7 = 1
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ all -> 0x008c }
            long r8 = r2.contactId()     // Catch:{ all -> 0x008c }
            java.lang.String r8 = java.lang.Long.toString(r8)     // Catch:{ all -> 0x008c }
            r7[r0] = r8     // Catch:{ all -> 0x008c }
            r4.update(r5, r3, r6, r7)     // Catch:{ all -> 0x008c }
        L_0x0077:
            com.android.dialer.speeddial.database.SpeedDialEntryDao r0 = r10.getSpeedDialEntryDao()     // Catch:{ all -> 0x008c }
            com.android.dialer.speeddial.database.SpeedDialEntry r2 = r2.buildSpeedDialEntry()     // Catch:{ all -> 0x008c }
            com.android.dialer.speeddial.database.SpeedDialEntryDatabaseHelper r0 = (com.android.dialer.speeddial.database.SpeedDialEntryDatabaseHelper) r0
            r0.insert(r2)     // Catch:{ all -> 0x008c }
            $closeResource(r1, r11)
            com.google.common.collect.ImmutableList r10 = r10.loadSpeedDialUiItemsInternal()
        L_0x008b:
            return r10
        L_0x008c:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x008e }
        L_0x008e:
            r0 = move-exception
            if (r11 == 0) goto L_0x0094
            $closeResource(r10, r11)
        L_0x0094:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.speeddial.loader.SpeedDialUiItemMutator.lambda$starContact$1$SpeedDialUiItemMutator(android.net.Uri):com.google.common.collect.ImmutableList");
    }

    public ListenableFuture<ImmutableList<SpeedDialUiItem>> loadSpeedDialUiItems() {
        return this.dialerFutureSerializer.submit(new Callable() {
            public final Object call() {
                return SpeedDialUiItemMutator.this.loadSpeedDialUiItemsInternal();
            }
        }, this.backgroundExecutor);
    }

    public ListenableFuture<ImmutableList<SpeedDialUiItem>> removeSpeedDialUiItem(SpeedDialUiItem speedDialUiItem) {
        return this.dialerFutureSerializer.submit(new Callable(speedDialUiItem) {
            private final /* synthetic */ SpeedDialUiItem f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return SpeedDialUiItemMutator.this.lambda$removeSpeedDialUiItem$0$SpeedDialUiItemMutator(this.f$1);
            }
        }, this.backgroundExecutor);
    }

    public ListenableFuture<ImmutableList<SpeedDialUiItem>> starContact(Uri uri) {
        return this.dialerFutureSerializer.submit(new Callable(uri) {
            private final /* synthetic */ Uri f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return SpeedDialUiItemMutator.this.lambda$starContact$1$SpeedDialUiItemMutator(this.f$1);
            }
        }, this.backgroundExecutor);
    }

    public void updatePinnedPosition(List<SpeedDialUiItem> list) {
        Assert.isWorkerThread();
        if (list != null && !list.isEmpty()) {
            ImmutableList.Builder builder = ImmutableList.builder();
            int i = 0;
            for (int i2 = 0; i2 < list.size(); i2++) {
                SpeedDialUiItem speedDialUiItem = list.get(i2);
                if (speedDialUiItem.isStarred()) {
                    SpeedDialEntry.Builder builder2 = speedDialUiItem.buildSpeedDialEntry().toBuilder();
                    builder2.setPinnedPosition(Optional.m67of(Integer.valueOf(i2)));
                    builder.add((Object) builder2.build());
                }
            }
            ((SpeedDialEntryDatabaseHelper) getSpeedDialEntryDao()).update(builder.build());
            ArrayList arrayList = new ArrayList();
            ArraySet arraySet = new ArraySet();
            for (SpeedDialUiItem next : list) {
                if (arraySet.add(Long.valueOf(next.contactId()))) {
                    arrayList.add(next);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            while (i < arrayList.size()) {
                SpeedDialUiItem speedDialUiItem2 = (SpeedDialUiItem) arrayList.get(i);
                i++;
                if (!speedDialUiItem2.pinnedPosition().isPresent() || speedDialUiItem2.pinnedPosition().get().intValue() != i) {
                    Uri withAppendedPath = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(speedDialUiItem2.contactId()));
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("pinned", Integer.valueOf(i));
                    arrayList2.add(ContentProviderOperation.newUpdate(withAppendedPath).withValues(contentValues).build());
                }
            }
            if (!arrayList2.isEmpty()) {
                try {
                    this.appContext.getContentResolver().applyBatch("com.android.contacts", arrayList2);
                } catch (OperationApplicationException | RemoteException e) {
                    LogUtil.m7e("SpeedDialUiItemMutator.updatePinnedPosition", "Exception thrown when pinning contacts", e);
                }
            }
        }
    }
}
