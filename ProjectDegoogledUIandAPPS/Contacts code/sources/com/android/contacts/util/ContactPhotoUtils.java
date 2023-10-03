package com.android.contacts.util;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.android.contacts.R;
import com.google.common.p004io.Closeables;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ContactPhotoUtils {
    public static Uri generateTempImageUri(Context context) {
        return FileProvider.getUriForFile(context, context.getResources().getString(R.string.photo_file_provider_authority), new File(pathForTempPhoto(context, generateTempPhotoFileName())));
    }

    public static Uri generateTempCroppedImageUri(Context context) {
        return FileProvider.getUriForFile(context, context.getResources().getString(R.string.photo_file_provider_authority), new File(pathForTempPhoto(context, generateTempCroppedPhotoFileName())));
    }

    private static String pathForTempPhoto(Context context, String str) {
        File cacheDir = context.getCacheDir();
        cacheDir.mkdirs();
        return new File(cacheDir, str).getAbsolutePath();
    }

    private static String generateTempPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss", Locale.US);
        return "ContactPhoto-" + simpleDateFormat.format(date) + ".jpg";
    }

    private static String generateTempCroppedPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss", Locale.US);
        return "ContactPhoto-" + simpleDateFormat.format(date) + "-cropped.jpg";
    }

    public static Bitmap getBitmapFromUri(Context context, Uri uri) throws FileNotFoundException {
        InputStream openInputStream = context.getContentResolver().openInputStream(uri);
        try {
            return BitmapFactory.decodeStream(openInputStream);
        } finally {
            Closeables.closeQuietly(openInputStream);
        }
    }

    public static byte[] compressBitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bitmap.getWidth() * bitmap.getHeight() * 4);
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            Log.w("ContactPhotoUtils", "Unable to serialize photo: " + e.toString());
            return null;
        }
    }

    public static void addCropExtras(Intent intent, int i) {
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", i);
        intent.putExtra("outputY", i);
    }

    public static void addPhotoPickerExtras(Intent intent, Uri uri) {
        intent.putExtra("output", uri);
        intent.addFlags(3);
        intent.setClipData(ClipData.newRawUri("output", uri));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0074, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0075, code lost:
        if (r3 != null) goto L_0x0077;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        $closeResource(r4, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x007a, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x007d, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x007e, code lost:
        if (r9 != null) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        $closeResource(r3, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0083, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean savePhotoFromUriToUri(android.content.Context r7, android.net.Uri r8, android.net.Uri r9, boolean r10) {
        /*
            java.lang.String r0 = "ContactPhotoUtils"
            r1 = 0
            if (r8 == 0) goto L_0x00bd
            if (r9 == 0) goto L_0x00bd
            boolean r2 = isFilePathAndNotStorage(r8)
            if (r2 == 0) goto L_0x000f
            goto L_0x00bd
        L_0x000f:
            r2 = 0
            android.content.ContentResolver r3 = r7.getContentResolver()     // Catch:{ IOException -> 0x0088, NullPointerException -> 0x0086 }
            java.lang.String r4 = "rw"
            android.content.res.AssetFileDescriptor r9 = r3.openAssetFileDescriptor(r9, r4)     // Catch:{ IOException -> 0x0088, NullPointerException -> 0x0086 }
            java.io.FileOutputStream r9 = r9.createOutputStream()     // Catch:{ IOException -> 0x0088, NullPointerException -> 0x0086 }
            android.content.ContentResolver r3 = r7.getContentResolver()     // Catch:{ all -> 0x007b }
            java.io.InputStream r3 = r3.openInputStream(r8)     // Catch:{ all -> 0x007b }
            r4 = 16384(0x4000, float:2.2959E-41)
            byte[] r4 = new byte[r4]     // Catch:{ all -> 0x0072 }
            r5 = 0
        L_0x002b:
            int r6 = r3.read(r4)     // Catch:{ all -> 0x0072 }
            if (r6 <= 0) goto L_0x0036
            r9.write(r4, r1, r6)     // Catch:{ all -> 0x0072 }
            int r5 = r5 + r6
            goto L_0x002b
        L_0x0036:
            r4 = 2
            boolean r4 = android.util.Log.isLoggable(r0, r4)     // Catch:{ all -> 0x0072 }
            if (r4 == 0) goto L_0x005d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0072 }
            r4.<init>()     // Catch:{ all -> 0x0072 }
            java.lang.String r6 = "Wrote "
            r4.append(r6)     // Catch:{ all -> 0x0072 }
            r4.append(r5)     // Catch:{ all -> 0x0072 }
            java.lang.String r5 = " bytes for photo "
            r4.append(r5)     // Catch:{ all -> 0x0072 }
            java.lang.String r5 = r8.toString()     // Catch:{ all -> 0x0072 }
            r4.append(r5)     // Catch:{ all -> 0x0072 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0072 }
            android.util.Log.v(r0, r4)     // Catch:{ all -> 0x0072 }
        L_0x005d:
            if (r3 == 0) goto L_0x0062
            $closeResource(r2, r3)     // Catch:{ all -> 0x007b }
        L_0x0062:
            if (r9 == 0) goto L_0x0067
            $closeResource(r2, r9)     // Catch:{ IOException -> 0x0088, NullPointerException -> 0x0086 }
        L_0x0067:
            if (r10 == 0) goto L_0x0070
            android.content.ContentResolver r7 = r7.getContentResolver()
            r7.delete(r8, r2, r2)
        L_0x0070:
            r7 = 1
            return r7
        L_0x0072:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0074 }
        L_0x0074:
            r5 = move-exception
            if (r3 == 0) goto L_0x007a
            $closeResource(r4, r3)     // Catch:{ all -> 0x007b }
        L_0x007a:
            throw r5     // Catch:{ all -> 0x007b }
        L_0x007b:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x007d }
        L_0x007d:
            r4 = move-exception
            if (r9 == 0) goto L_0x0083
            $closeResource(r3, r9)     // Catch:{ IOException -> 0x0088, NullPointerException -> 0x0086 }
        L_0x0083:
            throw r4     // Catch:{ IOException -> 0x0088, NullPointerException -> 0x0086 }
        L_0x0084:
            r9 = move-exception
            goto L_0x00b3
        L_0x0086:
            r9 = move-exception
            goto L_0x0089
        L_0x0088:
            r9 = move-exception
        L_0x0089:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0084 }
            r3.<init>()     // Catch:{ all -> 0x0084 }
            java.lang.String r4 = "Failed to write photo: "
            r3.append(r4)     // Catch:{ all -> 0x0084 }
            java.lang.String r4 = r8.toString()     // Catch:{ all -> 0x0084 }
            r3.append(r4)     // Catch:{ all -> 0x0084 }
            java.lang.String r4 = " because: "
            r3.append(r4)     // Catch:{ all -> 0x0084 }
            r3.append(r9)     // Catch:{ all -> 0x0084 }
            java.lang.String r9 = r3.toString()     // Catch:{ all -> 0x0084 }
            android.util.Log.e(r0, r9)     // Catch:{ all -> 0x0084 }
            if (r10 == 0) goto L_0x00b2
            android.content.ContentResolver r7 = r7.getContentResolver()
            r7.delete(r8, r2, r2)
        L_0x00b2:
            return r1
        L_0x00b3:
            if (r10 == 0) goto L_0x00bc
            android.content.ContentResolver r7 = r7.getContentResolver()
            r7.delete(r8, r2, r2)
        L_0x00bc:
            throw r9
        L_0x00bd:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.util.ContactPhotoUtils.savePhotoFromUriToUri(android.content.Context, android.net.Uri, android.net.Uri, boolean):boolean");
    }

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

    private static boolean isFilePathAndNotStorage(Uri uri) {
        if ("file".equals(uri.getScheme())) {
            try {
                return !new File(uri.getPath()).getCanonicalFile().getCanonicalPath().startsWith("/storage/");
            } catch (IOException unused) {
            }
        }
        return false;
    }
}
