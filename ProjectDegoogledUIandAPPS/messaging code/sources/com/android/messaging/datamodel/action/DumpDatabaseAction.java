package com.android.messaging.datamodel.action;

import android.os.Parcel;
import android.os.Parcelable;

public class DumpDatabaseAction extends Action implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0823m();

    private DumpDatabaseAction() {
    }

    /* renamed from: Fe */
    public static void m1364Fe() {
        new DumpDatabaseAction().start();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:20|21|22|23|24|(1:27)|28|57|58) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0057 */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0084 A[SYNTHETIC, Splitter:B:46:0x0084] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0089 A[SYNTHETIC, Splitter:B:50:0x0089] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00b2 A[SYNTHETIC, Splitter:B:62:0x00b2] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00b7 A[SYNTHETIC, Splitter:B:66:0x00b7] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00c0  */
    /* renamed from: ve */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object mo5956ve() {
        /*
            r13 = this;
            java.lang.String r13 = ", copy size: "
            java.lang.String r0 = "Dump complete; orig size: "
            java.lang.String r1 = "MessagingAppDataModel"
            com.android.messaging.f r2 = com.android.messaging.C0967f.get()
            android.content.Context r2 = r2.getApplicationContext()
            java.lang.String r3 = "bugle_db"
            java.io.File r2 = r2.getDatabasePath(r3)
            boolean r3 = r2.exists()
            if (r3 == 0) goto L_0x0025
            boolean r3 = r2.isFile()
            if (r3 == 0) goto L_0x0025
            long r3 = r2.length()
            goto L_0x0027
        L_0x0025:
            r3 = 0
        L_0x0027:
            r5 = 1
            java.lang.String r6 = "db_copy.db"
            java.io.File r6 = com.android.messaging.util.C1410N.m3551e(r6, r5)
            r7 = 0
            r8 = 0
            java.io.BufferedOutputStream r9 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x0079, all -> 0x0075 }
            java.io.FileOutputStream r10 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0079, all -> 0x0075 }
            r10.<init>(r6)     // Catch:{ IOException -> 0x0079, all -> 0x0075 }
            r9.<init>(r10)     // Catch:{ IOException -> 0x0079, all -> 0x0075 }
            java.io.BufferedInputStream r10 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0072, all -> 0x0070 }
            java.io.FileInputStream r11 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0072, all -> 0x0070 }
            r11.<init>(r2)     // Catch:{ IOException -> 0x0072, all -> 0x0070 }
            r10.<init>(r11)     // Catch:{ IOException -> 0x0072, all -> 0x0070 }
            r2 = 16384(0x4000, float:2.2959E-41)
            byte[] r2 = new byte[r2]     // Catch:{ IOException -> 0x006e, all -> 0x006b }
            r11 = r8
        L_0x0049:
            int r12 = r10.read(r2)     // Catch:{ IOException -> 0x0069 }
            if (r12 <= 0) goto L_0x0054
            r9.write(r2, r8, r12)     // Catch:{ IOException -> 0x0069 }
            int r11 = r11 + r12
            goto L_0x0049
        L_0x0054:
            r9.close()     // Catch:{ IOException -> 0x0057 }
        L_0x0057:
            r10.close()     // Catch:{ IOException -> 0x005a }
        L_0x005a:
            boolean r2 = r6.exists()
            if (r2 == 0) goto L_0x0063
            r6.setReadable(r5, r8)
        L_0x0063:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            goto L_0x009a
        L_0x0069:
            r2 = move-exception
            goto L_0x007d
        L_0x006b:
            r2 = move-exception
            r11 = r8
            goto L_0x00af
        L_0x006e:
            r2 = move-exception
            goto L_0x007c
        L_0x0070:
            r2 = move-exception
            goto L_0x0077
        L_0x0072:
            r2 = move-exception
            r10 = r7
            goto L_0x007c
        L_0x0075:
            r2 = move-exception
            r9 = r7
        L_0x0077:
            r11 = r8
            goto L_0x00b0
        L_0x0079:
            r2 = move-exception
            r9 = r7
            r10 = r9
        L_0x007c:
            r11 = r8
        L_0x007d:
            java.lang.String r12 = "Exception copying the database; destination may not be complete."
            com.android.messaging.util.C1430e.m3631w(r1, r12, r2)     // Catch:{ all -> 0x00ae }
            if (r9 == 0) goto L_0x0087
            r9.close()     // Catch:{ IOException -> 0x0087 }
        L_0x0087:
            if (r10 == 0) goto L_0x008c
            r10.close()     // Catch:{ IOException -> 0x008c }
        L_0x008c:
            boolean r2 = r6.exists()
            if (r2 == 0) goto L_0x0095
            r6.setReadable(r5, r8)
        L_0x0095:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
        L_0x009a:
            r2.append(r0)
            r2.append(r3)
            r2.append(r13)
            r2.append(r11)
            java.lang.String r13 = r2.toString()
            com.android.messaging.util.C1430e.m3625i(r1, r13)
            return r7
        L_0x00ae:
            r2 = move-exception
        L_0x00af:
            r7 = r10
        L_0x00b0:
            if (r9 == 0) goto L_0x00b5
            r9.close()     // Catch:{ IOException -> 0x00b5 }
        L_0x00b5:
            if (r7 == 0) goto L_0x00ba
            r7.close()     // Catch:{ IOException -> 0x00ba }
        L_0x00ba:
            boolean r7 = r6.exists()
            if (r7 == 0) goto L_0x00c3
            r6.setReadable(r5, r8)
        L_0x00c3:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            r5.append(r3)
            r5.append(r13)
            r5.append(r11)
            java.lang.String r13 = r5.toString()
            com.android.messaging.util.C1430e.m3625i(r1, r13)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.action.DumpDatabaseAction.mo5956ve():java.lang.Object");
    }

    public void writeToParcel(Parcel parcel, int i) {
        mo5946a(parcel, i);
    }

    /* synthetic */ DumpDatabaseAction(Parcel parcel, C0823m mVar) {
        super(parcel);
    }
}
