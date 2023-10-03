package p000;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;

/* renamed from: abt */
/* compiled from: PG */
public final class abt {

    /* renamed from: a */
    public Context f105a;

    /* renamed from: b */
    public Uri f106b;

    abt() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x006e A[LOOP:1: B:15:0x006b->B:17:0x006e, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x007f  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.abt mo121a(java.lang.String r10) {
        /*
            r9 = this;
            android.content.Context r0 = r9.f105a
            android.content.ContentResolver r1 = r0.getContentResolver()
            android.net.Uri r0 = r9.f106b
            java.lang.String r2 = android.provider.DocumentsContract.getDocumentId(r0)
            android.net.Uri r2 = android.provider.DocumentsContract.buildChildDocumentsUriUsingTree(r0, r2)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r7 = 0
            r3 = 1
            r8 = 0
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x0041, all -> 0x003f }
            java.lang.String r4 = "document_id"
            r3[r8] = r4     // Catch:{ Exception -> 0x0041, all -> 0x003f }
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0041, all -> 0x003f }
        L_0x0025:
            boolean r2 = r1.moveToNext()     // Catch:{ Exception -> 0x003d }
            if (r2 == 0) goto L_0x0039
            java.lang.String r2 = r1.getString(r8)     // Catch:{ Exception -> 0x003d }
            android.net.Uri r3 = r9.f106b     // Catch:{ Exception -> 0x003d }
            android.net.Uri r2 = android.provider.DocumentsContract.buildDocumentUriUsingTree(r3, r2)     // Catch:{ Exception -> 0x003d }
            r0.add(r2)     // Catch:{ Exception -> 0x003d }
            goto L_0x0025
        L_0x0039:
            m166a((java.lang.AutoCloseable) r1)
            goto L_0x005b
        L_0x003d:
            r2 = move-exception
            goto L_0x0044
        L_0x003f:
            r10 = move-exception
            goto L_0x0093
        L_0x0041:
            r1 = move-exception
            r2 = r1
            r1 = r7
        L_0x0044:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0090 }
            r3.<init>()     // Catch:{ all -> 0x0090 }
            java.lang.String r4 = "Failed query: "
            r3.append(r4)     // Catch:{ all -> 0x0090 }
            r3.append(r2)     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = "DocumentFile"
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0090 }
            android.util.Log.w(r2, r3)     // Catch:{ all -> 0x0090 }
            goto L_0x0039
        L_0x005b:
            int r1 = r0.size()
            android.net.Uri[] r1 = new android.net.Uri[r1]
            java.lang.Object[] r0 = r0.toArray(r1)
            android.net.Uri[] r0 = (android.net.Uri[]) r0
            int r1 = r0.length
            abt[] r2 = new p000.abt[r1]
            r3 = 0
        L_0x006b:
            int r4 = r0.length
            if (r3 >= r4) goto L_0x007c
            abt r4 = new abt
            android.content.Context r5 = r9.f105a
            r6 = r0[r3]
            r4.<init>(r5, r6)
            r2[r3] = r4
            int r3 = r3 + 1
            goto L_0x006b
        L_0x007c:
        L_0x007d:
            if (r8 >= r1) goto L_0x008f
            r0 = r2[r8]
            java.lang.String r3 = r0.mo122a()
            boolean r3 = r10.equals(r3)
            if (r3 != 0) goto L_0x008e
            int r8 = r8 + 1
            goto L_0x007d
        L_0x008e:
            return r0
        L_0x008f:
            return r7
        L_0x0090:
            r10 = move-exception
            r7 = r1
        L_0x0093:
            m166a((java.lang.AutoCloseable) r7)
            goto L_0x0098
        L_0x0097:
            throw r10
        L_0x0098:
            goto L_0x0097
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.abt.mo121a(java.lang.String):abt");
    }

    /* renamed from: a */
    public static abt m164a(Context context, Uri uri) {
        int i = Build.VERSION.SDK_INT;
        String treeDocumentId = DocumentsContract.getTreeDocumentId(uri);
        if (DocumentsContract.isDocumentUri(context, uri)) {
            treeDocumentId = DocumentsContract.getDocumentId(uri);
        }
        return new abt(context, DocumentsContract.buildDocumentUriUsingTree(uri, treeDocumentId));
    }

    public abt(Context context, Uri uri) {
        this.f105a = context;
        this.f106b = uri;
    }

    /* renamed from: a */
    private static void m166a(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }

    /* renamed from: b */
    public final abt mo123b(String str) {
        Uri a = m165a(this.f105a, this.f106b, "vnd.android.document/directory", str);
        if (a != null) {
            return new abt(this.f105a, a);
        }
        return null;
    }

    /* renamed from: a */
    public static Uri m165a(Context context, Uri uri, String str, String str2) {
        try {
            return DocumentsContract.createDocument(context.getContentResolver(), uri, str, str2);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public final String mo122a() {
        return C0652xy.m16065a(this.f105a, this.f106b, "_display_name");
    }
}
