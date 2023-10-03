package com.android.keyguard.clock;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.android.internal.annotations.VisibleForTesting;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.function.Supplier;

public final class ClockOptionsProvider extends ContentProvider {
    private final Supplier<List<ClockInfo>> mClocksSupplier;

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public boolean onCreate() {
        return true;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public ClockOptionsProvider() {
        this($$Lambda$ClockOptionsProvider$VCFr6VBqrtOSuPKYuOzo6kUuyg.INSTANCE);
    }

    @VisibleForTesting
    ClockOptionsProvider(Supplier<List<ClockInfo>> supplier) {
        this.mClocksSupplier = supplier;
    }

    public String getType(Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() > 0) {
            return ("preview".equals(pathSegments.get(0)) || "thumbnail".equals(pathSegments.get(0))) ? "image/png" : "vnd.android.cursor.dir/clock_faces";
        }
        return "vnd.android.cursor.dir/clock_faces";
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (!"/list_options".equals(uri.getPath())) {
            return null;
        }
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"name", "title", "id", "thumbnail", "preview"});
        List list = this.mClocksSupplier.get();
        for (int i = 0; i < list.size(); i++) {
            ClockInfo clockInfo = (ClockInfo) list.get(i);
            matrixCursor.newRow().add("name", clockInfo.getName()).add("title", clockInfo.getTitle()).add("id", clockInfo.getId()).add("thumbnail", createThumbnailUri(clockInfo)).add("preview", createPreviewUri(clockInfo));
        }
        return matrixCursor;
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        ClockInfo clockInfo;
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() != 2 || (!"preview".equals(pathSegments.get(0)) && !"thumbnail".equals(pathSegments.get(0)))) {
            throw new FileNotFoundException("Invalid preview url");
        }
        String str2 = pathSegments.get(1);
        if (!TextUtils.isEmpty(str2)) {
            List list = this.mClocksSupplier.get();
            int i = 0;
            while (true) {
                if (i >= list.size()) {
                    clockInfo = null;
                    break;
                } else if (str2.equals(((ClockInfo) list.get(i)).getId())) {
                    clockInfo = (ClockInfo) list.get(i);
                    break;
                } else {
                    i++;
                }
            }
            if (clockInfo != null) {
                return openPipeHelper(uri, "image/png", (Bundle) null, "preview".equals(pathSegments.get(0)) ? clockInfo.getPreview() : clockInfo.getThumbnail(), new MyWriter());
            }
            throw new FileNotFoundException("Invalid preview url, id not found");
        }
        throw new FileNotFoundException("Invalid preview url, missing id");
    }

    private Uri createThumbnailUri(ClockInfo clockInfo) {
        return new Uri.Builder().scheme("content").authority("com.android.keyguard.clock").appendPath("thumbnail").appendPath(clockInfo.getId()).build();
    }

    private Uri createPreviewUri(ClockInfo clockInfo) {
        return new Uri.Builder().scheme("content").authority("com.android.keyguard.clock").appendPath("preview").appendPath(clockInfo.getId()).build();
    }

    private static class MyWriter implements ContentProvider.PipeDataWriter<Bitmap> {
        private MyWriter() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            r0.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x001b, code lost:
            throw r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
            r2 = move-exception;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void writeDataToPipe(android.os.ParcelFileDescriptor r1, android.net.Uri r2, java.lang.String r3, android.os.Bundle r4, android.graphics.Bitmap r5) {
            /*
                r0 = this;
                android.os.ParcelFileDescriptor$AutoCloseOutputStream r0 = new android.os.ParcelFileDescriptor$AutoCloseOutputStream     // Catch:{ Exception -> 0x001c }
                r0.<init>(r1)     // Catch:{ Exception -> 0x001c }
                android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ all -> 0x0010 }
                r2 = 100
                r5.compress(r1, r2, r0)     // Catch:{ all -> 0x0010 }
                r0.close()     // Catch:{ Exception -> 0x001c }
                goto L_0x0024
            L_0x0010:
                r1 = move-exception
                throw r1     // Catch:{ all -> 0x0012 }
            L_0x0012:
                r2 = move-exception
                r0.close()     // Catch:{ all -> 0x0017 }
                goto L_0x001b
            L_0x0017:
                r0 = move-exception
                r1.addSuppressed(r0)     // Catch:{ Exception -> 0x001c }
            L_0x001b:
                throw r2     // Catch:{ Exception -> 0x001c }
            L_0x001c:
                r0 = move-exception
                java.lang.String r1 = "ClockOptionsProvider"
                java.lang.String r2 = "fail to write to pipe"
                android.util.Log.w(r1, r2, r0)
            L_0x0024:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.keyguard.clock.ClockOptionsProvider.MyWriter.writeDataToPipe(android.os.ParcelFileDescriptor, android.net.Uri, java.lang.String, android.os.Bundle, android.graphics.Bitmap):void");
        }
    }
}
