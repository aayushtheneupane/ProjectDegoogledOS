package com.bumptech.glide.load.data.mediastore;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.p002v7.appcompat.R$style;
import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

class ThumbnailStreamOpener {
    private static final FileService DEFAULT_SERVICE = new FileService();
    private final ArrayPool byteArrayPool;
    private final ContentResolver contentResolver;
    private final List<ImageHeaderParser> parsers;
    private final ThumbnailQuery query;
    private final FileService service = DEFAULT_SERVICE;

    ThumbnailStreamOpener(List<ImageHeaderParser> list, ThumbnailQuery thumbnailQuery, ArrayPool arrayPool, ContentResolver contentResolver2) {
        this.query = thumbnailQuery;
        this.byteArrayPool = arrayPool;
        this.contentResolver = contentResolver2;
        this.parsers = list;
    }

    /* access modifiers changed from: package-private */
    public int getOrientation(Uri uri) {
        InputStream inputStream = null;
        try {
            InputStream openInputStream = this.contentResolver.openInputStream(uri);
            int orientation = R$style.getOrientation(this.parsers, openInputStream, this.byteArrayPool);
            if (openInputStream != null) {
                try {
                    openInputStream.close();
                } catch (IOException unused) {
                }
            }
            return orientation;
        } catch (IOException | NullPointerException e) {
            if (Log.isLoggable("ThumbStreamOpener", 3)) {
                String valueOf = String.valueOf(uri);
                StringBuilder sb = new StringBuilder(valueOf.length() + 20);
                sb.append("Failed to open uri: ");
                sb.append(valueOf);
                Log.d("ThumbStreamOpener", sb.toString(), e);
            }
            if (inputStream == null) {
                return -1;
            }
            try {
                inputStream.close();
                return -1;
            } catch (IOException unused2) {
                return -1;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused3) {
                }
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0029 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.InputStream open(android.net.Uri r8) throws java.io.FileNotFoundException {
        /*
            r7 = this;
            com.bumptech.glide.load.data.mediastore.ThumbnailQuery r0 = r7.query
            android.database.Cursor r0 = r0.query(r8)
            r1 = 0
            r2 = 0
            if (r0 == 0) goto L_0x001d
            boolean r3 = r0.moveToFirst()     // Catch:{ all -> 0x0018 }
            if (r3 == 0) goto L_0x001d
            java.lang.String r3 = r0.getString(r1)     // Catch:{ all -> 0x0018 }
            r0.close()
            goto L_0x0023
        L_0x0018:
            r7 = move-exception
            r0.close()
            throw r7
        L_0x001d:
            if (r0 == 0) goto L_0x0022
            r0.close()
        L_0x0022:
            r3 = r2
        L_0x0023:
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 == 0) goto L_0x002a
            return r2
        L_0x002a:
            com.bumptech.glide.load.data.mediastore.FileService r0 = r7.service
            java.io.File r0 = r0.get(r3)
            com.bumptech.glide.load.data.mediastore.FileService r3 = r7.service
            boolean r3 = r3.exists(r0)
            if (r3 == 0) goto L_0x0045
            r3 = 0
            com.bumptech.glide.load.data.mediastore.FileService r5 = r7.service
            long r5 = r5.length(r0)
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 >= 0) goto L_0x0045
            r1 = 1
        L_0x0045:
            if (r1 != 0) goto L_0x0048
            return r2
        L_0x0048:
            android.net.Uri r0 = android.net.Uri.fromFile(r0)
            android.content.ContentResolver r7 = r7.contentResolver     // Catch:{ NullPointerException -> 0x0053 }
            java.io.InputStream r7 = r7.openInputStream(r0)     // Catch:{ NullPointerException -> 0x0053 }
            return r7
        L_0x0053:
            r7 = move-exception
            java.io.FileNotFoundException r1 = new java.io.FileNotFoundException
            java.lang.String r8 = java.lang.String.valueOf(r8)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r2 = r8.length()
            int r2 = r2 + 21
            int r3 = r0.length()
            int r3 = r3 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r3)
            java.lang.String r3 = "NPE opening uri: "
            r2.append(r3)
            r2.append(r8)
            java.lang.String r8 = " -> "
            r2.append(r8)
            r2.append(r0)
            java.lang.String r8 = r2.toString()
            r1.<init>(r8)
            java.lang.Throwable r7 = r1.initCause(r7)
            java.io.FileNotFoundException r7 = (java.io.FileNotFoundException) r7
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.data.mediastore.ThumbnailStreamOpener.open(android.net.Uri):java.io.InputStream");
    }
}
