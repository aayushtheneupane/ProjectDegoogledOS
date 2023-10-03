package com.android.dialer.callcomposer.camera;

import android.content.Context;
import android.net.Uri;
import com.android.dialer.common.Assert;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.google.auto.value.AutoValue;

public class ImagePersistWorker implements DialerExecutor.Worker<Void, Result> {
    private final byte[] bytes;
    private final Context context;
    private int height;
    private final float heightPercent;
    private int width;

    @AutoValue
    static abstract class Result {

        static abstract class Builder {
            Builder() {
            }

            /* access modifiers changed from: package-private */
            public abstract Result build();

            /* access modifiers changed from: package-private */
            public abstract Builder setHeight(int i);

            /* access modifiers changed from: package-private */
            public abstract Builder setUri(Uri uri);

            /* access modifiers changed from: package-private */
            public abstract Builder setWidth(int i);
        }

        Result() {
        }

        /* access modifiers changed from: package-private */
        public abstract int getHeight();

        /* access modifiers changed from: package-private */
        public abstract Uri getUri();

        /* access modifiers changed from: package-private */
        public abstract int getWidth();
    }

    ImagePersistWorker(int i, int i2, float f, byte[] bArr, Context context2) {
        Assert.checkArgument(f >= 0.0f && f <= 1.0f);
        Assert.isNotNull(bArr);
        Assert.isNotNull(context2);
        this.width = i;
        this.height = i2;
        this.heightPercent = f;
        this.bytes = bArr;
        this.context = context2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeClippedBitmap(java.io.OutputStream r9) throws java.io.IOException {
        /*
            r8 = this;
            com.android.dialer.callcomposer.camera.exif.ExifInterface r0 = new com.android.dialer.callcomposer.camera.exif.ExifInterface
            r0.<init>()
            r1 = 0
            byte[] r2 = r8.bytes     // Catch:{ IOException -> 0x0018 }
            r0.readExif(r2)     // Catch:{ IOException -> 0x0018 }
            int r2 = com.android.dialer.callcomposer.camera.exif.ExifInterface.TAG_ORIENTATION     // Catch:{ IOException -> 0x0018 }
            java.lang.Integer r2 = r0.getTagIntValue(r2)     // Catch:{ IOException -> 0x0018 }
            if (r2 == 0) goto L_0x0018
            int r2 = r2.intValue()     // Catch:{ IOException -> 0x0018 }
            goto L_0x0019
        L_0x0018:
            r2 = r1
        L_0x0019:
            r3 = 270(0x10e, float:3.78E-43)
            r4 = 90
            r5 = 1
            switch(r2) {
                case 2: goto L_0x002a;
                case 3: goto L_0x0025;
                case 4: goto L_0x002a;
                case 5: goto L_0x0022;
                case 6: goto L_0x0022;
                case 7: goto L_0x0023;
                case 8: goto L_0x0023;
                default: goto L_0x0021;
            }
        L_0x0021:
            goto L_0x002a
        L_0x0022:
            r3 = r4
        L_0x0023:
            r2 = r5
            goto L_0x002c
        L_0x0025:
            r2 = 180(0xb4, float:2.52E-43)
            r3 = r2
            r2 = r1
            goto L_0x002c
        L_0x002a:
            r2 = r1
            r3 = r2
        L_0x002c:
            byte[] r6 = r8.bytes
            int r7 = r6.length
            android.graphics.Bitmap r6 = android.graphics.BitmapFactory.decodeByteArray(r6, r1, r7)
            if (r2 == 0) goto L_0x0059
            int r2 = r8.width
            int r7 = r6.getHeight()
            if (r2 != r7) goto L_0x003f
            r2 = r5
            goto L_0x0040
        L_0x003f:
            r2 = r1
        L_0x0040:
            com.android.dialer.common.Assert.checkState(r2)
            int r2 = r8.height
            int r7 = r6.getWidth()
            if (r2 != r7) goto L_0x004c
            r1 = r5
        L_0x004c:
            com.android.dialer.common.Assert.checkState(r1)
            int r1 = r8.height
            float r1 = (float) r1
            float r2 = r8.heightPercent
            float r1 = r1 * r2
            int r1 = (int) r1
            int r2 = r8.width
            goto L_0x007c
        L_0x0059:
            int r2 = r8.width
            int r7 = r6.getWidth()
            if (r2 != r7) goto L_0x0063
            r2 = r5
            goto L_0x0064
        L_0x0063:
            r2 = r1
        L_0x0064:
            com.android.dialer.common.Assert.checkState(r2)
            int r2 = r8.height
            int r7 = r6.getHeight()
            if (r2 != r7) goto L_0x0070
            r1 = r5
        L_0x0070:
            com.android.dialer.common.Assert.checkState(r1)
            int r1 = r8.width
            int r2 = r8.height
            float r2 = (float) r2
            float r5 = r8.heightPercent
            float r2 = r2 * r5
            int r2 = (int) r2
        L_0x007c:
            int r5 = r6.getHeight()
            int r5 = r5 - r2
            int r5 = r5 / 2
            int r7 = r6.getWidth()
            int r7 = r7 - r1
            int r7 = r7 / 2
            r8.width = r1
            r8.height = r2
            android.graphics.Bitmap r8 = android.graphics.Bitmap.createBitmap(r6, r7, r5, r1, r2)
            android.graphics.Bitmap r8 = com.android.dialer.callcomposer.util.BitmapResizer.resizeForEnrichedCalling(r8, r3)
            r0.clearExif()
            if (r8 == 0) goto L_0x00ac
            if (r9 == 0) goto L_0x00ac
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.JPEG
            r8.compress(r0, r4, r9)
            r9.flush()
            r8.recycle()
            r6.recycle()
            return
        L_0x00ac:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "Argument is null"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.callcomposer.camera.ImagePersistWorker.writeClippedBitmap(java.io.OutputStream):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0040, code lost:
        r3.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0043, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x003a, code lost:
        r4 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object doInBackground(java.lang.Object r4) throws java.lang.Throwable {
        /*
            r3 = this;
            java.lang.Void r4 = (java.lang.Void) r4
            android.content.Context r4 = r3.context
            java.io.File r4 = com.android.dialer.util.DialerUtils.createShareableFile(r4)
            java.io.FileOutputStream r0 = new java.io.FileOutputStream
            r0.<init>(r4)
            r3.writeClippedBitmap(r0)     // Catch:{ all -> 0x0038 }
            r0.close()
            com.android.dialer.callcomposer.camera.AutoValue_ImagePersistWorker_Result$Builder r0 = new com.android.dialer.callcomposer.camera.AutoValue_ImagePersistWorker_Result$Builder
            r0.<init>()
            android.content.Context r1 = r3.context
            com.android.dialer.constants.Constants r2 = com.android.dialer.constants.Constants.get()
            java.lang.String r2 = r2.getFileProviderAuthority()
            android.net.Uri r4 = android.support.p000v4.content.FileProvider.getUriForFile(r1, r2, r4)
            r0.setUri(r4)
            int r4 = r3.width
            r0.setWidth(r4)
            int r3 = r3.height
            r0.setHeight(r3)
            com.android.dialer.callcomposer.camera.ImagePersistWorker$Result r3 = r0.build()
            return r3
        L_0x0038:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x003a }
        L_0x003a:
            r4 = move-exception
            r0.close()     // Catch:{ all -> 0x003f }
            goto L_0x0043
        L_0x003f:
            r0 = move-exception
            r3.addSuppressed(r0)
        L_0x0043:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.callcomposer.camera.ImagePersistWorker.doInBackground(java.lang.Object):java.lang.Object");
    }
}
