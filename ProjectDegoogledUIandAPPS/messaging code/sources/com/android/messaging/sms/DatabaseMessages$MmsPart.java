package com.android.messaging.sms;

import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.messaging.util.C1481w;
import p026b.p027a.p030b.p031a.C0632a;

public class DatabaseMessages$MmsPart implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C1021q();

    /* renamed from: EE */
    public static final int f1500EE;

    /* renamed from: GE */
    public static final int f1501GE;

    /* renamed from: JE */
    public static final int f1502JE;

    /* renamed from: KE */
    public static final int f1503KE;

    /* renamed from: Wu */
    public static final String[] f1504Wu = {"_id", "mid", "chset", "ct", "text"};

    /* renamed from: hE */
    private static int f1505hE;

    /* renamed from: iE */
    public static final int f1506iE;

    /* renamed from: jy */
    public long f1507jy;
    public int mCharset;
    public String mContentType;
    private int mHeight;
    public long mRowId;
    public long mSize;
    public String mText;
    public String mUri;
    private int mWidth;

    static {
        f1505hE = 0;
        int i = f1505hE;
        f1505hE = i + 1;
        f1506iE = i;
        int i2 = f1505hE;
        f1505hE = i2 + 1;
        f1501GE = i2;
        int i3 = f1505hE;
        f1505hE = i3 + 1;
        f1500EE = i3;
        int i4 = f1505hE;
        f1505hE = i4 + 1;
        f1502JE = i4;
        int i5 = f1505hE;
        f1505hE = i5 + 1;
        f1503KE = i5;
    }

    private DatabaseMessages$MmsPart() {
    }

    /* renamed from: a */
    public static DatabaseMessages$MmsPart m2340a(Cursor cursor, boolean z) {
        DatabaseMessages$MmsPart databaseMessages$MmsPart = new DatabaseMessages$MmsPart();
        databaseMessages$MmsPart.mo6801b(cursor, z);
        return databaseMessages$MmsPart;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: byte[]} */
    /* JADX WARNING: type inference failed for: r2v0, types: [android.graphics.Rect, java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r2v8, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: type inference failed for: r2v16 */
    /* JADX WARNING: type inference failed for: r2v17 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ad A[SYNTHETIC, Splitter:B:30:0x00ad] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ba A[SYNTHETIC, Splitter:B:35:0x00ba] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo6801b(android.database.Cursor r8, boolean r9) {
        /*
            r7 = this;
            int r0 = f1506iE
            long r0 = r8.getLong(r0)
            r7.mRowId = r0
            int r0 = f1501GE
            long r0 = r8.getLong(r0)
            r7.f1507jy = r0
            int r0 = f1502JE
            java.lang.String r0 = r8.getString(r0)
            r7.mContentType = r0
            int r0 = f1503KE
            java.lang.String r0 = r8.getString(r0)
            r7.mText = r0
            int r0 = f1500EE
            int r0 = r8.getInt(r0)
            r7.mCharset = r0
            r0 = 0
            r7.mWidth = r0
            r7.mHeight = r0
            r1 = 0
            r7.mSize = r1
            boolean r1 = r7.isMedia()
            r2 = 0
            r3 = 1
            java.lang.String r4 = "MessagingApp"
            if (r1 == 0) goto L_0x013b
            if (r9 == 0) goto L_0x01e3
            java.lang.String r9 = r7.mContentType
            boolean r9 = com.android.messaging.util.C1481w.isImageType(r9)
            if (r9 == 0) goto L_0x00c3
            java.lang.String r9 = "IOException caught while closing stream"
            android.content.ContentResolver r0 = p026b.p027a.p030b.p031a.C0632a.m1012Pk()
            android.net.Uri r1 = r7.getDataUri()
            java.io.InputStream r0 = r0.openInputStream(r1)     // Catch:{ FileNotFoundException -> 0x00a5 }
            android.graphics.BitmapFactory$Options r5 = new android.graphics.BitmapFactory$Options     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
            r5.<init>()     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
            r5.inJustDecodeBounds = r3     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
            android.graphics.BitmapFactory.decodeStream(r0, r2, r5)     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
            java.lang.String r2 = r5.outMimeType     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
            r7.mContentType = r2     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
            int r2 = r5.outWidth     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
            r7.mWidth = r2     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
            int r2 = r5.outHeight     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
            r7.mHeight = r2     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
            java.lang.String r2 = r7.mContentType     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
            if (r2 == 0) goto L_0x0096
            java.lang.String r1 = r1.getPath()     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
            android.webkit.MimeTypeMap r2 = android.webkit.MimeTypeMap.getSingleton()     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
            java.lang.String r5 = android.webkit.MimeTypeMap.getFileExtensionFromUrl(r1)     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
            if (r6 == 0) goto L_0x0090
            r6 = 46
            int r6 = r1.lastIndexOf(r6)     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
            if (r6 < 0) goto L_0x0090
            int r6 = r6 + r3
            java.lang.String r5 = r1.substring(r6)     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
        L_0x0090:
            java.lang.String r1 = r2.getMimeTypeFromExtension(r5)     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
            r7.mContentType = r1     // Catch:{ FileNotFoundException -> 0x009f, all -> 0x009d }
        L_0x0096:
            if (r0 == 0) goto L_0x012f
            r0.close()     // Catch:{ IOException -> 0x00b2 }
            goto L_0x012f
        L_0x009d:
            r7 = move-exception
            goto L_0x00b8
        L_0x009f:
            r1 = move-exception
            r2 = r0
            goto L_0x00a6
        L_0x00a2:
            r7 = move-exception
            r0 = r2
            goto L_0x00b8
        L_0x00a5:
            r1 = move-exception
        L_0x00a6:
            java.lang.String r0 = "DatabaseMessages.MmsPart.loadImage: file not found"
            com.android.messaging.util.C1430e.m3623e(r4, r0, r1)     // Catch:{ all -> 0x00a2 }
            if (r2 == 0) goto L_0x012f
            r2.close()     // Catch:{ IOException -> 0x00b2 }
            goto L_0x012f
        L_0x00b2:
            r0 = move-exception
            android.util.Log.e(r4, r9, r0)
            goto L_0x012f
        L_0x00b8:
            if (r0 == 0) goto L_0x00c2
            r0.close()     // Catch:{ IOException -> 0x00be }
            goto L_0x00c2
        L_0x00be:
            r8 = move-exception
            android.util.Log.e(r4, r9, r8)
        L_0x00c2:
            throw r7
        L_0x00c3:
            java.lang.String r9 = r7.mContentType
            boolean r9 = com.android.messaging.util.C1481w.m3830Ea(r9)
            if (r9 == 0) goto L_0x012f
            boolean r9 = com.android.messaging.util.C1452ha.m3728Qj()
            if (r9 != 0) goto L_0x00d2
            goto L_0x012f
        L_0x00d2:
            android.net.Uri r9 = r7.getDataUri()
            com.android.messaging.util.ea r0 = new com.android.messaging.util.ea
            r0.<init>()
            r0.mo8063v(r9)     // Catch:{ IOException -> 0x0112 }
            r1 = 12
            java.lang.String r1 = r0.extractMetadata(r1)     // Catch:{ IOException -> 0x0112 }
            r7.mContentType = r1     // Catch:{ IOException -> 0x0112 }
            r1 = -1
            android.graphics.Bitmap r1 = r0.getFrameAtTime(r1)     // Catch:{ IOException -> 0x0112 }
            if (r1 == 0) goto L_0x00fb
            int r2 = r1.getWidth()     // Catch:{ IOException -> 0x0112 }
            r7.mWidth = r2     // Catch:{ IOException -> 0x0112 }
            int r1 = r1.getHeight()     // Catch:{ IOException -> 0x0112 }
            r7.mHeight = r1     // Catch:{ IOException -> 0x0112 }
            goto L_0x0127
        L_0x00fb:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0112 }
            r1.<init>()     // Catch:{ IOException -> 0x0112 }
            java.lang.String r2 = "loadVideo: Got null bitmap from "
            r1.append(r2)     // Catch:{ IOException -> 0x0112 }
            r1.append(r9)     // Catch:{ IOException -> 0x0112 }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x0112 }
            com.android.messaging.util.C1430e.m3625i(r4, r1)     // Catch:{ IOException -> 0x0112 }
            goto L_0x0127
        L_0x0110:
            r7 = move-exception
            goto L_0x012b
        L_0x0112:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0110 }
            r2.<init>()     // Catch:{ all -> 0x0110 }
            java.lang.String r3 = "Error extracting metadata from "
            r2.append(r3)     // Catch:{ all -> 0x0110 }
            r2.append(r9)     // Catch:{ all -> 0x0110 }
            java.lang.String r9 = r2.toString()     // Catch:{ all -> 0x0110 }
            com.android.messaging.util.C1430e.m3626i(r4, r9, r1)     // Catch:{ all -> 0x0110 }
        L_0x0127:
            r0.release()
            goto L_0x012f
        L_0x012b:
            r0.release()
            throw r7
        L_0x012f:
            android.net.Uri r9 = r7.getDataUri()
            long r0 = com.android.messaging.sms.C1029y.m2447m(r9)
            r7.mSize = r0
            goto L_0x01e3
        L_0x013b:
            java.lang.String r9 = "DatabaseMessages.MmsPart: close file failed: "
            java.lang.String r1 = r7.mContentType
            java.lang.String r5 = "text/plain"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x015d
            java.lang.String r1 = r7.mContentType
            java.lang.String r5 = "application/smil"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x015d
            java.lang.String r1 = r7.mContentType
            java.lang.String r5 = "text/html"
            boolean r1 = r5.equals(r1)
            if (r1 == 0) goto L_0x015c
            goto L_0x015d
        L_0x015c:
            r3 = r0
        L_0x015d:
            if (r3 == 0) goto L_0x0170
            java.lang.String r9 = r7.mText
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            if (r9 != 0) goto L_0x01d2
            java.lang.String r9 = r7.mText
            int r0 = r7.mCharset
            byte[] r2 = android.support.p016v4.media.session.C0107q.m132c(r9, r0)
            goto L_0x01d2
        L_0x0170:
            android.content.ContentResolver r1 = p026b.p027a.p030b.p031a.C0632a.m1012Pk()
            android.net.Uri r3 = r7.getDataUri()
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream
            r5.<init>()
            java.io.InputStream r2 = r1.openInputStream(r3)     // Catch:{ IOException -> 0x01a0 }
            r1 = 256(0x100, float:3.59E-43)
            byte[] r1 = new byte[r1]     // Catch:{ IOException -> 0x01a0 }
            int r3 = r2.read(r1)     // Catch:{ IOException -> 0x01a0 }
        L_0x0189:
            if (r3 < 0) goto L_0x0193
            r5.write(r1, r0, r3)     // Catch:{ IOException -> 0x01a0 }
            int r3 = r2.read(r1)     // Catch:{ IOException -> 0x01a0 }
            goto L_0x0189
        L_0x0193:
            r2.close()     // Catch:{ IOException -> 0x0197 }
            goto L_0x01ce
        L_0x0197:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            goto L_0x01c1
        L_0x019e:
            r7 = move-exception
            goto L_0x01f6
        L_0x01a0:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x019e }
            r1.<init>()     // Catch:{ all -> 0x019e }
            java.lang.String r3 = "DatabaseMessages.MmsPart: loading text from file failed: "
            r1.append(r3)     // Catch:{ all -> 0x019e }
            r1.append(r0)     // Catch:{ all -> 0x019e }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x019e }
            com.android.messaging.util.C1430e.m3623e(r4, r1, r0)     // Catch:{ all -> 0x019e }
            if (r2 == 0) goto L_0x01ce
            r2.close()     // Catch:{ IOException -> 0x01bb }
            goto L_0x01ce
        L_0x01bb:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
        L_0x01c1:
            r1.append(r9)
            r1.append(r0)
            java.lang.String r9 = r1.toString()
            com.android.messaging.util.C1430e.m3623e(r4, r9, r0)
        L_0x01ce:
            byte[] r2 = r5.toByteArray()
        L_0x01d2:
            if (r2 == 0) goto L_0x01e3
            int r9 = r2.length
            if (r9 <= 0) goto L_0x01e3
            int r9 = r2.length
            long r0 = (long) r9
            r7.mSize = r0
            int r9 = r7.mCharset
            java.lang.String r9 = android.support.p016v4.media.session.C0107q.m125a((byte[]) r2, (int) r9)
            r7.mText = r9
        L_0x01e3:
            android.net.Uri r9 = android.provider.Telephony.Mms.CONTENT_URI
            int r0 = f1506iE
            java.lang.String r8 = r8.getString(r0)
            android.net.Uri r8 = android.net.Uri.withAppendedPath(r9, r8)
            java.lang.String r8 = r8.toString()
            r7.mUri = r8
            return
        L_0x01f6:
            if (r2 == 0) goto L_0x0200
            r2.close()     // Catch:{ IOException -> 0x01fc }
            goto L_0x0200
        L_0x01fc:
            r8 = move-exception
            p026b.p027a.p030b.p031a.C0632a.m1020a((java.lang.String) r9, (java.lang.Object) r8, (java.lang.String) r4, (java.lang.Throwable) r8)
        L_0x0200:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.sms.DatabaseMessages$MmsPart.mo6801b(android.database.Cursor, boolean):void");
    }

    public int describeContents() {
        return 0;
    }

    public Uri getDataUri() {
        StringBuilder Pa = C0632a.m1011Pa("content://mms/part/");
        Pa.append(this.mRowId);
        return Uri.parse(Pa.toString());
    }

    public boolean isMedia() {
        return C1481w.isImageType(this.mContentType) || C1481w.m3830Ea(this.mContentType) || C1481w.m3831za(this.mContentType) || C1481w.m3829Da(this.mContentType);
    }

    public boolean isText() {
        return "text/plain".equals(this.mContentType) || "text/html".equals(this.mContentType) || "application/vnd.wap.xhtml+xml".equals(this.mContentType);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUri);
        parcel.writeLong(this.mRowId);
        parcel.writeLong(this.f1507jy);
        parcel.writeString(this.mContentType);
        parcel.writeString(this.mText);
        parcel.writeInt(this.mCharset);
        parcel.writeInt(this.mWidth);
        parcel.writeInt(this.mHeight);
        parcel.writeLong(this.mSize);
    }

    /* synthetic */ DatabaseMessages$MmsPart(Parcel parcel, C1016l lVar) {
        this.mUri = parcel.readString();
        this.mRowId = parcel.readLong();
        this.f1507jy = parcel.readLong();
        this.mContentType = parcel.readString();
        this.mText = parcel.readString();
        this.mCharset = parcel.readInt();
        this.mWidth = parcel.readInt();
        this.mHeight = parcel.readInt();
        this.mSize = parcel.readLong();
    }
}
