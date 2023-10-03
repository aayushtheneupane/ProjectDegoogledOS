package com.android.messaging.datamodel.p038b;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.messaging.datamodel.b.N */
public class C0851N implements C0883w {
    /* access modifiers changed from: private */

    /* renamed from: YC */
    public final List f1104YC = new ArrayList();
    /* access modifiers changed from: private */

    /* renamed from: ZC */
    public C0853P f1105ZC;
    private final Context mContext;
    private final C0852O mDescriptor;

    C0851N(Context context, C0852O o) {
        this.mDescriptor = o;
        this.mContext = context;
    }

    /* renamed from: fa */
    public C0882v mo6121fa() {
        return C0866f.get().mo6144ua(3);
    }

    public C0884x getDescriptor() {
        return this.mDescriptor;
    }

    public String getKey() {
        return this.mDescriptor.f1106JC.toString();
    }

    public int getRequestType() {
        return 3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x004c, code lost:
        com.android.messaging.util.C1430e.m3622e("MessagingApp", "Must not reach here. " + r3);
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0088, code lost:
        if (m1546a(r2, r5, r6, false, (java.util.List) null) == false) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r3 = m1546a(r2, r12.getEstimatedType(), r11, false, (java.util.List) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x004b, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x003d */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.messaging.datamodel.p038b.C0846I mo6120a(java.util.List r14) {
        /*
            r13 = this;
            com.android.messaging.util.C1424b.m3584Gj()
            com.android.messaging.datamodel.b.P r14 = r13.f1105ZC
            r0 = 1
            r1 = 0
            if (r14 != 0) goto L_0x000b
            r14 = r0
            goto L_0x000c
        L_0x000b:
            r14 = r1
        L_0x000c:
            com.android.messaging.util.C1424b.m3592ia(r14)
            java.util.List r14 = r13.f1104YC
            int r14 = r14.size()
            com.android.messaging.util.C1424b.equals((int) r1, (int) r14)
            java.util.concurrent.CountDownLatch r14 = new java.util.concurrent.CountDownLatch
            r14.<init>(r0)
            com.android.messaging.datamodel.b.O r2 = r13.mDescriptor
            android.net.Uri r2 = r2.f1106JC
            java.lang.String r9 = "MessagingApp"
            java.lang.String r10 = "Must not reach here. "
            com.android.messaging.util.C1424b.m3584Gj()
            com.android.vcard.VCardEntryCounter r11 = new com.android.vcard.VCardEntryCounter
            r11.<init>()
            com.android.vcard.VCardSourceDetector r12 = new com.android.vcard.VCardSourceDetector
            r12.<init>()
            r5 = 0
            r7 = 1
            r8 = 0
            r3 = r13
            r4 = r2
            r6 = r12
            boolean r3 = r3.m1546a(r4, r5, r6, r7, r8)     // Catch:{ VCardNestedException -> 0x003d }
            goto L_0x005f
        L_0x003d:
            int r5 = r12.getEstimatedType()     // Catch:{ VCardNestedException -> 0x004b }
            r7 = 0
            r8 = 0
            r3 = r13
            r4 = r2
            r6 = r11
            boolean r3 = r3.m1546a(r4, r5, r6, r7, r8)     // Catch:{ VCardNestedException -> 0x004b }
            goto L_0x005f
        L_0x004b:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r10)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.android.messaging.util.C1430e.m3622e(r9, r3)
            r3 = r1
        L_0x005f:
            if (r3 != 0) goto L_0x0062
            goto L_0x009e
        L_0x0062:
            com.android.messaging.util.C1424b.m3584Gj()
            int r3 = r12.getEstimatedType()
            if (r3 != 0) goto L_0x0071
            java.lang.String r3 = "default"
            int r3 = com.android.vcard.VCardConfig.getVCardTypeFromString(r3)
        L_0x0071:
            r5 = r3
            com.android.messaging.datamodel.b.k r6 = new com.android.messaging.datamodel.b.k
            r3 = 0
            r6.<init>(r5, r3)
            com.android.messaging.datamodel.b.M r3 = new com.android.messaging.datamodel.b.M
            r3.<init>(r13, r14)
            r6.mo6146a(r3)
            r7 = 0
            r8 = 0
            r3 = r13
            r4 = r2
            boolean r2 = r3.m1546a(r4, r5, r6, r7, r8)     // Catch:{ VCardNestedException -> 0x008b }
            if (r2 != 0) goto L_0x009f
            goto L_0x009e
        L_0x008b:
            r0 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r10)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.android.messaging.util.C1430e.m3622e(r9, r0)
        L_0x009e:
            r0 = r1
        L_0x009f:
            if (r0 == 0) goto L_0x00b5
            r0 = 10000(0x2710, double:4.9407E-320)
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS
            r14.await(r0, r2)
            com.android.messaging.datamodel.b.P r13 = r13.f1105ZC
            if (r13 == 0) goto L_0x00ad
            return r13
        L_0x00ad:
            com.android.vcard.exception.VCardException r13 = new com.android.vcard.exception.VCardException
            java.lang.String r14 = "Failure or timeout loading vcard"
            r13.<init>(r14)
            throw r13
        L_0x00b5:
            com.android.vcard.exception.VCardException r13 = new com.android.vcard.exception.VCardException
            java.lang.String r14 = "Invalid vcard"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.p038b.C0851N.mo6120a(java.util.List):com.android.messaging.datamodel.b.I");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:7|8|9|10|(1:12)|13|14|15|(1:49)) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0023, code lost:
        if ((r6 instanceof com.android.messaging.datamodel.p038b.C0871k) != false) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        ((com.android.messaging.datamodel.p038b.C0871k) r6).clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        r1 = r3.openInputStream(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r3 = new com.android.vcard.VCardParser_V30(r5);
        r3.addInterpreter(r6);
        r3.parse(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003a, code lost:
        if (r1 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0048, code lost:
        throw new com.android.vcard.exception.VCardException("vCard with unspported version.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0049, code lost:
        if (r1 != null) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004f, code lost:
        if (r8 != null) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0051, code lost:
        r8.add(r4.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0058, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0059, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x005c, code lost:
        if ((r3 instanceof com.android.vcard.exception.VCardNestedException) == false) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0063, code lost:
        throw ((com.android.vcard.exception.VCardNestedException) r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0064, code lost:
        if (r8 != null) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0066, code lost:
        r8.add(r4.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x006d, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x006e, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x006f, code lost:
        r5 = p026b.p027a.p030b.p031a.C0632a.m1011Pa("IOException was emitted: ");
        r5.append(r3.getMessage());
        com.android.messaging.util.C1430e.m3622e("MessagingApp", r5.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0085, code lost:
        if (r8 != null) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0087, code lost:
        r8.add(r4.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x008e, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001c, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0041 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x004e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0021 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0025 A[Catch:{ all -> 0x001c, IOException -> 0x006e, VCardNotSupportedException -> 0x0059, VCardException -> 0x004f }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[ExcHandler: VCardException (unused com.android.vcard.exception.VCardException), SYNTHETIC, Splitter:B:1:0x000a] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0059 A[ExcHandler: VCardNotSupportedException (r3v7 'e' com.android.vcard.exception.VCardNotSupportedException A[CUSTOM_DECLARE]), Splitter:B:1:0x000a] */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:17:0x003c=Splitter:B:17:0x003c, B:26:0x004e=Splitter:B:26:0x004e} */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m1546a(android.net.Uri r4, int r5, com.android.vcard.VCardInterpreter r6, boolean r7, java.util.List r8) {
        /*
            r3 = this;
            com.android.messaging.util.C1424b.m3584Gj()
            android.content.Context r3 = r3.mContext
            android.content.ContentResolver r3 = r3.getContentResolver()
            r0 = 0
            java.io.InputStream r1 = r3.openInputStream(r4)     // Catch:{ IOException -> 0x006e, VCardNotSupportedException -> 0x0059, VCardException -> 0x004f }
            com.android.vcard.VCardParser_V21 r2 = new com.android.vcard.VCardParser_V21     // Catch:{ IOException -> 0x006e, VCardNotSupportedException -> 0x0059, VCardException -> 0x004f }
            r2.<init>(r5)     // Catch:{ IOException -> 0x006e, VCardNotSupportedException -> 0x0059, VCardException -> 0x004f }
            r2.addInterpreter(r6)     // Catch:{ IOException -> 0x006e, VCardNotSupportedException -> 0x0059, VCardException -> 0x004f }
            r2.parse(r1)     // Catch:{ VCardVersionException -> 0x001e }
            if (r1 == 0) goto L_0x003f
            goto L_0x003c
        L_0x001c:
            r3 = move-exception
            goto L_0x0049
        L_0x001e:
            r1.close()     // Catch:{ IOException -> 0x0021 }
        L_0x0021:
            boolean r2 = r6 instanceof com.android.messaging.datamodel.p038b.C0871k     // Catch:{ all -> 0x001c }
            if (r2 == 0) goto L_0x002b
            r2 = r6
            com.android.messaging.datamodel.b.k r2 = (com.android.messaging.datamodel.p038b.C0871k) r2     // Catch:{ all -> 0x001c }
            r2.clear()     // Catch:{ all -> 0x001c }
        L_0x002b:
            java.io.InputStream r1 = r3.openInputStream(r4)     // Catch:{ all -> 0x001c }
            com.android.vcard.VCardParser_V30 r3 = new com.android.vcard.VCardParser_V30     // Catch:{ VCardVersionException -> 0x0041 }
            r3.<init>(r5)     // Catch:{ VCardVersionException -> 0x0041 }
            r3.addInterpreter(r6)     // Catch:{ VCardVersionException -> 0x0041 }
            r3.parse(r1)     // Catch:{ VCardVersionException -> 0x0041 }
            if (r1 == 0) goto L_0x003f
        L_0x003c:
            r1.close()     // Catch:{ IOException -> 0x003f, VCardNotSupportedException -> 0x0059, VCardException -> 0x004f }
        L_0x003f:
            r3 = 1
            return r3
        L_0x0041:
            com.android.vcard.exception.VCardException r3 = new com.android.vcard.exception.VCardException     // Catch:{ all -> 0x001c }
            java.lang.String r5 = "vCard with unspported version."
            r3.<init>(r5)     // Catch:{ all -> 0x001c }
            throw r3     // Catch:{ all -> 0x001c }
        L_0x0049:
            if (r1 == 0) goto L_0x004e
            r1.close()     // Catch:{ IOException -> 0x004e, VCardNotSupportedException -> 0x0059, VCardException -> 0x004f }
        L_0x004e:
            throw r3     // Catch:{ IOException -> 0x006e, VCardNotSupportedException -> 0x0059, VCardException -> 0x004f }
        L_0x004f:
            if (r8 == 0) goto L_0x0058
            java.lang.String r3 = r4.toString()
            r8.add(r3)
        L_0x0058:
            return r0
        L_0x0059:
            r3 = move-exception
            boolean r5 = r3 instanceof com.android.vcard.exception.VCardNestedException
            if (r5 == 0) goto L_0x0064
            if (r7 != 0) goto L_0x0061
            goto L_0x0064
        L_0x0061:
            com.android.vcard.exception.VCardNestedException r3 = (com.android.vcard.exception.VCardNestedException) r3
            throw r3
        L_0x0064:
            if (r8 == 0) goto L_0x006d
            java.lang.String r3 = r4.toString()
            r8.add(r3)
        L_0x006d:
            return r0
        L_0x006e:
            r3 = move-exception
            java.lang.String r5 = "IOException was emitted: "
            java.lang.StringBuilder r5 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r5)
            java.lang.String r3 = r3.getMessage()
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            java.lang.String r5 = "MessagingApp"
            com.android.messaging.util.C1430e.m3622e(r5, r3)
            if (r8 == 0) goto L_0x008e
            java.lang.String r3 = r4.toString()
            r8.add(r3)
        L_0x008e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.p038b.C0851N.m1546a(android.net.Uri, int, com.android.vcard.VCardInterpreter, boolean, java.util.List):boolean");
    }
}
