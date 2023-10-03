package com.android.contacts.vcard;

import android.app.Notification;
import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;
import com.android.contactsbind.FeedbackHelper;
import com.android.vcard.VCardEntry;
import com.android.vcard.VCardEntryConstructor;
import com.android.vcard.VCardEntryHandler;
import com.android.vcard.VCardInterpreter;
import com.android.vcard.VCardParser;
import com.android.vcard.VCardParser_V21;
import com.android.vcard.VCardParser_V30;
import com.android.vcard.exception.VCardException;
import com.android.vcard.exception.VCardNotSupportedException;
import com.android.vcard.exception.VCardVersionException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ImportProcessor extends ProcessorBase implements VCardEntryHandler {
    private volatile boolean mCanceled;
    private int mCurrentCount = 0;
    private volatile boolean mDone;
    private final List<Uri> mFailedUris = new ArrayList();
    private final ImportRequest mImportRequest;
    private final int mJobId;
    private final VCardImportExportListener mListener;
    private final ContentResolver mResolver;
    private final VCardService mService;
    private int mTotalCount = 0;
    private VCardParser mVCardParser;

    public final int getType() {
        return 1;
    }

    public void onEnd() {
    }

    public void onStart() {
    }

    public ImportProcessor(VCardService vCardService, VCardImportExportListener vCardImportExportListener, ImportRequest importRequest, int i) {
        this.mService = vCardService;
        this.mResolver = this.mService.getContentResolver();
        this.mListener = vCardImportExportListener;
        this.mImportRequest = importRequest;
        this.mJobId = i;
    }

    public void onEntryCreated(VCardEntry vCardEntry) {
        Notification onImportParsed;
        this.mCurrentCount++;
        VCardImportExportListener vCardImportExportListener = this.mListener;
        if (vCardImportExportListener != null && (onImportParsed = vCardImportExportListener.onImportParsed(this.mImportRequest, this.mJobId, vCardEntry, this.mCurrentCount, this.mTotalCount)) != null) {
            this.mService.startForeground(this.mJobId, onImportParsed);
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r5 = this;
            r0 = 1
            r5.runInternal()     // Catch:{ OutOfMemoryError -> 0x0023, RuntimeException -> 0x0021 }
            boolean r1 = r5.isCancelled()     // Catch:{ OutOfMemoryError -> 0x0023, RuntimeException -> 0x0021 }
            if (r1 == 0) goto L_0x0017
            com.android.contacts.vcard.VCardImportExportListener r1 = r5.mListener     // Catch:{ OutOfMemoryError -> 0x0023, RuntimeException -> 0x0021 }
            if (r1 == 0) goto L_0x0017
            com.android.contacts.vcard.VCardImportExportListener r1 = r5.mListener     // Catch:{ OutOfMemoryError -> 0x0023, RuntimeException -> 0x0021 }
            com.android.contacts.vcard.ImportRequest r2 = r5.mImportRequest     // Catch:{ OutOfMemoryError -> 0x0023, RuntimeException -> 0x0021 }
            int r3 = r5.mJobId     // Catch:{ OutOfMemoryError -> 0x0023, RuntimeException -> 0x0021 }
            r1.onImportCanceled(r2, r3)     // Catch:{ OutOfMemoryError -> 0x0023, RuntimeException -> 0x0021 }
        L_0x0017:
            monitor-enter(r5)
            r5.mDone = r0     // Catch:{ all -> 0x001c }
            monitor-exit(r5)     // Catch:{ all -> 0x001c }
            goto L_0x0031
        L_0x001c:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x001c }
            throw r0
        L_0x001f:
            r1 = move-exception
            goto L_0x0035
        L_0x0021:
            r1 = move-exception
            goto L_0x0024
        L_0x0023:
            r1 = move-exception
        L_0x0024:
            com.android.contacts.vcard.VCardService r2 = r5.mService     // Catch:{ all -> 0x001f }
            java.lang.String r3 = "VCardImport"
            java.lang.String r4 = "Vcard import failed"
            com.android.contactsbind.FeedbackHelper.sendFeedback(r2, r3, r4, r1)     // Catch:{ all -> 0x001f }
            monitor-enter(r5)
            r5.mDone = r0     // Catch:{ all -> 0x0032 }
            monitor-exit(r5)     // Catch:{ all -> 0x0032 }
        L_0x0031:
            return
        L_0x0032:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0032 }
            throw r0
        L_0x0035:
            monitor-enter(r5)
            r5.mDone = r0     // Catch:{ all -> 0x003a }
            monitor-exit(r5)     // Catch:{ all -> 0x003a }
            throw r1
        L_0x003a:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x003a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.vcard.ImportProcessor.run():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b9 A[SYNTHETIC, Splitter:B:30:0x00b9] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c0 A[SYNTHETIC, Splitter:B:37:0x00c0] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0136  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void runInternal() {
        /*
            r14 = this;
            r0 = 1
            java.lang.Object[] r1 = new java.lang.Object[r0]
            int r2 = r14.mJobId
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3 = 0
            r1[r3] = r2
            java.lang.String r2 = "vCard import (id: %d) has started."
            java.lang.String r1 = java.lang.String.format(r2, r1)
            java.lang.String r2 = "VCardImport"
            android.util.Log.i(r2, r1)
            com.android.contacts.vcard.ImportRequest r1 = r14.mImportRequest
            boolean r4 = r14.isCancelled()
            java.lang.String r5 = ")"
            if (r4 == 0) goto L_0x003b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "Canceled before actually handling parameter ("
            r0.append(r3)
            android.net.Uri r1 = r1.uri
            r0.append(r1)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            android.util.Log.i(r2, r0)
            return
        L_0x003b:
            int r4 = r1.vcardVersion
            if (r4 != 0) goto L_0x0047
            r4 = 2
            int[] r4 = new int[r4]
            r4 = {1, 2} // fill-array
            r12 = r4
            goto L_0x004c
        L_0x0047:
            int[] r6 = new int[r0]
            r6[r3] = r4
            r12 = r6
        L_0x004c:
            android.net.Uri r4 = r1.uri
            android.accounts.Account r6 = r1.account
            int r9 = r1.estimatedVCardType
            java.lang.String r10 = r1.estimatedCharset
            int r7 = r1.entryCount
            int r8 = r14.mTotalCount
            int r8 = r8 + r7
            r14.mTotalCount = r8
            com.android.vcard.VCardEntryConstructor r11 = new com.android.vcard.VCardEntryConstructor
            r11.<init>(r9, r6, r10)
            com.android.vcard.VCardEntryCommitter r6 = new com.android.vcard.VCardEntryCommitter
            android.content.ContentResolver r7 = r14.mResolver
            r6.<init>(r7)
            r11.addEntryHandler(r6)
            r11.addEntryHandler(r14)
            r13 = 0
            if (r4 == 0) goto L_0x008e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00bd, all -> 0x00b6 }
            r1.<init>()     // Catch:{ IOException -> 0x00bd, all -> 0x00b6 }
            java.lang.String r7 = "start importing one vCard (Uri: "
            r1.append(r7)     // Catch:{ IOException -> 0x00bd, all -> 0x00b6 }
            r1.append(r4)     // Catch:{ IOException -> 0x00bd, all -> 0x00b6 }
            r1.append(r5)     // Catch:{ IOException -> 0x00bd, all -> 0x00b6 }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x00bd, all -> 0x00b6 }
            android.util.Log.i(r2, r1)     // Catch:{ IOException -> 0x00bd, all -> 0x00b6 }
            android.content.ContentResolver r1 = r14.mResolver     // Catch:{ IOException -> 0x00bd, all -> 0x00b6 }
            java.io.InputStream r1 = r1.openInputStream(r4)     // Catch:{ IOException -> 0x00bd, all -> 0x00b6 }
            goto L_0x00a1
        L_0x008e:
            byte[] r7 = r1.data     // Catch:{ IOException -> 0x00bd, all -> 0x00b6 }
            if (r7 == 0) goto L_0x00a0
            java.lang.String r7 = "start importing one vCard (byte[])"
            android.util.Log.i(r2, r7)     // Catch:{ IOException -> 0x00bd, all -> 0x00b6 }
            java.io.ByteArrayInputStream r7 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x00bd, all -> 0x00b6 }
            byte[] r1 = r1.data     // Catch:{ IOException -> 0x00bd, all -> 0x00b6 }
            r7.<init>(r1)     // Catch:{ IOException -> 0x00bd, all -> 0x00b6 }
            r1 = r7
            goto L_0x00a1
        L_0x00a0:
            r1 = r13
        L_0x00a1:
            if (r1 == 0) goto L_0x00af
            r7 = r14
            r8 = r1
            boolean r7 = r7.readOneVCard(r8, r9, r10, r11, r12)     // Catch:{ IOException -> 0x00ad, all -> 0x00aa }
            goto L_0x00b0
        L_0x00aa:
            r0 = move-exception
            r13 = r1
            goto L_0x00b7
        L_0x00ad:
            goto L_0x00be
        L_0x00af:
            r7 = 0
        L_0x00b0:
            if (r1 == 0) goto L_0x00c4
            r1.close()     // Catch:{ Exception -> 0x00c4 }
            goto L_0x00c4
        L_0x00b6:
            r0 = move-exception
        L_0x00b7:
            if (r13 == 0) goto L_0x00bc
            r13.close()     // Catch:{ Exception -> 0x00bc }
        L_0x00bc:
            throw r0
        L_0x00bd:
            r1 = r13
        L_0x00be:
            if (r1 == 0) goto L_0x00c3
            r1.close()     // Catch:{ Exception -> 0x00c3 }
        L_0x00c3:
            r7 = 0
        L_0x00c4:
            com.android.contacts.vcard.VCardService r1 = r14.mService
            int r8 = r14.mJobId
            r1.handleFinishImportNotification(r8, r7)
            if (r7 == 0) goto L_0x0136
            boolean r1 = r14.isCancelled()
            if (r1 == 0) goto L_0x00eb
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "vCard import has been canceled (uri: "
            r0.append(r1)
            r0.append(r4)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            android.util.Log.i(r2, r0)
            goto L_0x014f
        L_0x00eb:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r5 = "Successfully finished importing one vCard file: "
            r1.append(r5)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            android.util.Log.i(r2, r1)
            java.util.ArrayList r1 = r6.getCreatedUris()
            com.android.contacts.vcard.VCardImportExportListener r4 = r14.mListener
            if (r4 == 0) goto L_0x014f
            if (r1 == 0) goto L_0x011f
            int r4 = r1.size()
            if (r4 != r0) goto L_0x011f
            com.android.contacts.vcard.VCardImportExportListener r0 = r14.mListener
            com.android.contacts.vcard.ImportRequest r2 = r14.mImportRequest
            int r4 = r14.mJobId
            java.lang.Object r1 = r1.get(r3)
            android.net.Uri r1 = (android.net.Uri) r1
            r0.onImportFinished(r2, r4, r1)
            goto L_0x014f
        L_0x011f:
            if (r1 == 0) goto L_0x0127
            int r0 = r1.size()
            if (r0 != 0) goto L_0x012c
        L_0x0127:
            java.lang.String r0 = "Created Uris is null or 0 length though the creation itself is successful."
            android.util.Log.w(r2, r0)
        L_0x012c:
            com.android.contacts.vcard.VCardImportExportListener r0 = r14.mListener
            com.android.contacts.vcard.ImportRequest r1 = r14.mImportRequest
            int r2 = r14.mJobId
            r0.onImportFinished(r1, r2, r13)
            goto L_0x014f
        L_0x0136:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Failed to read one vCard file: "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            android.util.Log.w(r2, r0)
            java.util.List<android.net.Uri> r0 = r14.mFailedUris
            r0.add(r4)
        L_0x014f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.vcard.ImportProcessor.runInternal():void");
    }

    private boolean readOneVCard(InputStream inputStream, int i, String str, VCardInterpreter vCardInterpreter, int[] iArr) {
        VCardParser vCardParser;
        int length = iArr.length;
        int i2 = 0;
        while (i2 < length) {
            int i3 = iArr[i2];
            if (i2 > 0) {
                try {
                    if (vCardInterpreter instanceof VCardEntryConstructor) {
                        ((VCardEntryConstructor) vCardInterpreter).clear();
                    }
                } catch (IOException e) {
                    e = e;
                } catch (VCardNotSupportedException e2) {
                    e = e2;
                } catch (VCardVersionException unused) {
                    if (i2 == length - 1) {
                        Log.e("VCardImport", "Appropriate version for this vCard is not found.");
                    }
                    i2 = inputStream != null ? i2 + 1 : i2 + 1;
                } catch (VCardException e3) {
                    try {
                        Log.e("VCardImport", e3.toString());
                    } finally {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException unused2) {
                            }
                        }
                    }
                }
            }
            synchronized (this) {
                if (i3 == 2) {
                    vCardParser = new VCardParser_V30(i);
                } else {
                    vCardParser = new VCardParser_V21(i);
                }
                this.mVCardParser = vCardParser;
                if (isCancelled()) {
                    Log.i("VCardImport", "ImportProcessor already recieves cancel request, so send cancel request to vCard parser too.");
                    this.mVCardParser.cancel();
                }
            }
            this.mVCardParser.parse(inputStream, vCardInterpreter);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused3) {
                }
            }
            return true;
        }
        return false;
        FeedbackHelper.sendFeedback(this.mService, "VCardImport", "Failed to read vcard", e);
        if (inputStream == null) {
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public synchronized boolean cancel(boolean r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r2 = r1.mDone     // Catch:{ all -> 0x0020 }
            if (r2 != 0) goto L_0x001d
            boolean r2 = r1.mCanceled     // Catch:{ all -> 0x0020 }
            if (r2 == 0) goto L_0x000a
            goto L_0x001d
        L_0x000a:
            r2 = 1
            r1.mCanceled = r2     // Catch:{ all -> 0x0020 }
            monitor-enter(r1)     // Catch:{ all -> 0x0020 }
            com.android.vcard.VCardParser r0 = r1.mVCardParser     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x0017
            com.android.vcard.VCardParser r0 = r1.mVCardParser     // Catch:{ all -> 0x001a }
            r0.cancel()     // Catch:{ all -> 0x001a }
        L_0x0017:
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            monitor-exit(r1)
            return r2
        L_0x001a:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            throw r2     // Catch:{ all -> 0x0020 }
        L_0x001d:
            r2 = 0
            monitor-exit(r1)
            return r2
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.vcard.ImportProcessor.cancel(boolean):boolean");
    }

    public synchronized boolean isCancelled() {
        return this.mCanceled;
    }

    public synchronized boolean isDone() {
        return this.mDone;
    }
}
