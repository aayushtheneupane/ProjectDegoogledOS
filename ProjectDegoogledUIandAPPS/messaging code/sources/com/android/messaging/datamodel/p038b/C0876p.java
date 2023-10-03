package com.android.messaging.datamodel.p038b;

import android.content.Context;

/* renamed from: com.android.messaging.datamodel.b.p */
public class C0876p extends C0848K {
    private final String mPath;

    /* renamed from: xC */
    private final boolean f1122xC;

    public C0876p(Context context, C0877q qVar) {
        super(context, qVar);
        this.mPath = qVar.path;
        this.f1122xC = qVar.f1123HC;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001c  */
    /* renamed from: Mh */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap mo6087Mh() {
        /*
            r9 = this;
            boolean r0 = r9.f1122xC
            r1 = 0
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x008e
            android.media.ExifInterface r0 = new android.media.ExifInterface     // Catch:{ IOException -> 0x0019 }
            java.lang.String r4 = r9.mPath     // Catch:{ IOException -> 0x0019 }
            r0.<init>(r4)     // Catch:{ IOException -> 0x0019 }
            boolean r4 = r0.hasThumbnail()     // Catch:{ IOException -> 0x0019 }
            if (r4 == 0) goto L_0x0019
            byte[] r0 = r0.getThumbnail()     // Catch:{ IOException -> 0x0019 }
            goto L_0x001a
        L_0x0019:
            r0 = r1
        L_0x001a:
            if (r0 == 0) goto L_0x008e
            android.graphics.BitmapFactory$Options r4 = com.android.messaging.datamodel.p038b.C0845H.m1524a(r3, r3, r3)
            r4.inJustDecodeBounds = r2
            int r5 = r0.length
            android.graphics.BitmapFactory.decodeByteArray(r0, r3, r5, r4)
            com.android.messaging.util.U r5 = com.android.messaging.util.C1416U.get()
            com.android.messaging.datamodel.b.t r6 = r9.mDescriptor
            int r7 = r6.desiredWidth
            int r6 = r6.f1128yC
            int r5 = r5.mo8047a((android.graphics.BitmapFactory.Options) r4, (int) r7, (int) r6)
            r4.inSampleSize = r5
            r4.inJustDecodeBounds = r3
            android.content.Context r5 = r9.mContext     // Catch:{ IOException -> 0x0086 }
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch:{ IOException -> 0x0086 }
            com.android.messaging.datamodel.b.t r6 = r9.mDescriptor     // Catch:{ IOException -> 0x0086 }
            com.android.messaging.datamodel.b.L r6 = (com.android.messaging.datamodel.p038b.C0849L) r6     // Catch:{ IOException -> 0x0086 }
            android.net.Uri r6 = r6.uri     // Catch:{ IOException -> 0x0086 }
            java.io.InputStream r5 = r5.openInputStream(r6)     // Catch:{ IOException -> 0x0086 }
            int r5 = com.android.messaging.util.C1416U.m3561a(r5)     // Catch:{ IOException -> 0x0086 }
            r9.mOrientation = r5     // Catch:{ IOException -> 0x0086 }
            int r5 = r9.mOrientation     // Catch:{ IOException -> 0x0086 }
            com.android.messaging.util.exif.c r5 = com.android.messaging.util.exif.C1435d.m3656Ua(r5)     // Catch:{ IOException -> 0x0086 }
            boolean r5 = r5.f2251lL     // Catch:{ IOException -> 0x0086 }
            if (r5 == 0) goto L_0x0062
            com.android.messaging.datamodel.b.t r5 = r9.mDescriptor     // Catch:{ IOException -> 0x0086 }
            int r6 = r4.outHeight     // Catch:{ IOException -> 0x0086 }
            int r7 = r4.outWidth     // Catch:{ IOException -> 0x0086 }
            r5.mo6083u(r6, r7)     // Catch:{ IOException -> 0x0086 }
            goto L_0x006b
        L_0x0062:
            com.android.messaging.datamodel.b.t r5 = r9.mDescriptor     // Catch:{ IOException -> 0x0086 }
            int r6 = r4.outWidth     // Catch:{ IOException -> 0x0086 }
            int r7 = r4.outHeight     // Catch:{ IOException -> 0x0086 }
            r5.mo6083u(r6, r7)     // Catch:{ IOException -> 0x0086 }
        L_0x006b:
            com.android.messaging.datamodel.b.G r5 = r9.mo6160Hh()     // Catch:{ IOException -> 0x0086 }
            if (r5 != 0) goto L_0x0077
            int r5 = r0.length     // Catch:{ IOException -> 0x0086 }
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeByteArray(r0, r3, r5, r4)     // Catch:{ IOException -> 0x0086 }
            return r9
        L_0x0077:
            int r6 = r4.outWidth     // Catch:{ IOException -> 0x0086 }
            int r7 = r4.inSampleSize     // Catch:{ IOException -> 0x0086 }
            int r6 = r6 / r7
            int r7 = r4.outHeight     // Catch:{ IOException -> 0x0086 }
            int r8 = r4.inSampleSize     // Catch:{ IOException -> 0x0086 }
            int r7 = r7 / r8
            android.graphics.Bitmap r9 = r5.mo6090a((byte[]) r0, (android.graphics.BitmapFactory.Options) r4, (int) r6, (int) r7)     // Catch:{ IOException -> 0x0086 }
            return r9
        L_0x0086:
            r0 = move-exception
            java.lang.String r4 = "MessagingAppImage"
            java.lang.String r5 = "FileImageRequest: failed to load thumbnail from Exif"
            com.android.messaging.util.C1430e.m3623e(r4, r5, r0)
        L_0x008e:
            com.android.messaging.datamodel.b.t r0 = r9.mDescriptor
            int r4 = r0.f1129zC
            r5 = -1
            if (r4 == r5) goto L_0x009c
            int r0 = r0.f1124AC
            if (r0 != r5) goto L_0x009a
            goto L_0x009c
        L_0x009a:
            r0 = r3
            goto L_0x009d
        L_0x009c:
            r0 = r2
        L_0x009d:
            boolean r4 = r9.mo6141Kh()
            if (r4 == 0) goto L_0x00ba
            android.graphics.Bitmap r1 = r9.mo6140Gh()
            if (r1 == 0) goto L_0x014c
            if (r0 == 0) goto L_0x014c
            com.android.messaging.datamodel.b.t r9 = r9.mDescriptor
            int r0 = r1.getWidth()
            int r2 = r1.getHeight()
            r9.mo6083u(r0, r2)
            goto L_0x014c
        L_0x00ba:
            java.io.InputStream r4 = r9.mo6085Jh()
            int r4 = com.android.messaging.util.C1416U.m3561a(r4)
            r9.mOrientation = r4
            android.graphics.BitmapFactory$Options r4 = com.android.messaging.datamodel.p038b.C0845H.m1524a(r3, r3, r3)
            if (r0 == 0) goto L_0x0101
            java.io.InputStream r0 = r9.mo6085Jh()
            if (r0 == 0) goto L_0x00fb
            r4.inJustDecodeBounds = r2     // Catch:{ all -> 0x00f6 }
            android.graphics.BitmapFactory.decodeStream(r0, r1, r4)     // Catch:{ all -> 0x00f6 }
            int r5 = r9.mOrientation     // Catch:{ all -> 0x00f6 }
            com.android.messaging.util.exif.c r5 = com.android.messaging.util.exif.C1435d.m3656Ua(r5)     // Catch:{ all -> 0x00f6 }
            boolean r5 = r5.f2251lL     // Catch:{ all -> 0x00f6 }
            if (r5 == 0) goto L_0x00e9
            com.android.messaging.datamodel.b.t r5 = r9.mDescriptor     // Catch:{ all -> 0x00f6 }
            int r6 = r4.outHeight     // Catch:{ all -> 0x00f6 }
            int r7 = r4.outWidth     // Catch:{ all -> 0x00f6 }
            r5.mo6083u(r6, r7)     // Catch:{ all -> 0x00f6 }
            goto L_0x00f2
        L_0x00e9:
            com.android.messaging.datamodel.b.t r5 = r9.mDescriptor     // Catch:{ all -> 0x00f6 }
            int r6 = r4.outWidth     // Catch:{ all -> 0x00f6 }
            int r7 = r4.outHeight     // Catch:{ all -> 0x00f6 }
            r5.mo6083u(r6, r7)     // Catch:{ all -> 0x00f6 }
        L_0x00f2:
            r0.close()
            goto L_0x010b
        L_0x00f6:
            r9 = move-exception
            r0.close()
            throw r9
        L_0x00fb:
            java.io.FileNotFoundException r9 = new java.io.FileNotFoundException
            r9.<init>()
            throw r9
        L_0x0101:
            com.android.messaging.datamodel.b.t r0 = r9.mDescriptor
            int r5 = r0.f1129zC
            r4.outWidth = r5
            int r0 = r0.f1124AC
            r4.outHeight = r0
        L_0x010b:
            com.android.messaging.util.U r0 = com.android.messaging.util.C1416U.get()
            com.android.messaging.datamodel.b.t r5 = r9.mDescriptor
            int r6 = r5.desiredWidth
            int r5 = r5.f1128yC
            int r0 = r0.mo8047a((android.graphics.BitmapFactory.Options) r4, (int) r6, (int) r5)
            r4.inSampleSize = r0
            int r0 = r4.inSampleSize
            if (r0 <= 0) goto L_0x0121
            r0 = r2
            goto L_0x0122
        L_0x0121:
            r0 = r3
        L_0x0122:
            com.android.messaging.util.C1424b.m3592ia(r0)
            java.io.InputStream r0 = r9.mo6085Jh()
            if (r0 == 0) goto L_0x0152
            r4.inJustDecodeBounds = r3     // Catch:{ all -> 0x014d }
            com.android.messaging.datamodel.b.G r9 = r9.mo6160Hh()     // Catch:{ all -> 0x014d }
            if (r9 != 0) goto L_0x013b
            android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r0, r1, r4)     // Catch:{ all -> 0x014d }
        L_0x0137:
            r0.close()
            goto L_0x014c
        L_0x013b:
            int r1 = r4.outWidth     // Catch:{ all -> 0x014d }
            int r3 = r4.inSampleSize     // Catch:{ all -> 0x014d }
            int r1 = r1 + r3
            int r1 = r1 - r2
            int r1 = r1 / r3
            int r5 = r4.outHeight     // Catch:{ all -> 0x014d }
            int r5 = r5 + r3
            int r5 = r5 - r2
            int r5 = r5 / r3
            android.graphics.Bitmap r1 = r9.mo6089a((java.io.InputStream) r0, (android.graphics.BitmapFactory.Options) r4, (int) r1, (int) r5)     // Catch:{ all -> 0x014d }
            goto L_0x0137
        L_0x014c:
            return r1
        L_0x014d:
            r9 = move-exception
            r0.close()
            throw r9
        L_0x0152:
            java.io.FileNotFoundException r9 = new java.io.FileNotFoundException
            r9.<init>()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.p038b.C0876p.mo6087Mh():android.graphics.Bitmap");
    }
}
