package com.android.systemui.statusbar;

import android.graphics.Bitmap;
import android.graphics.Point;

/* compiled from: MediaArtworkProcessor.kt */
public final class MediaArtworkProcessor {
    private Bitmap mArtworkCache;
    private int mDownSample = 6;
    private final Point mTmpSize = new Point();

    /* JADX WARNING: Removed duplicated region for block: B:61:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0138  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.graphics.Bitmap processArtwork(android.content.Context r11, android.graphics.Bitmap r12, float r13) {
        /*
            r10 = this;
            java.lang.String r0 = "inBitmap"
            java.lang.String r1 = "context"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r1)
            java.lang.String r1 = "artwork"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r1)
            android.graphics.Bitmap r1 = r10.mArtworkCache
            if (r1 == 0) goto L_0x0011
            return r1
        L_0x0011:
            android.renderscript.RenderScript r1 = android.renderscript.RenderScript.create(r11)
            android.renderscript.Element r2 = android.renderscript.Element.U8_4(r1)
            android.renderscript.ScriptIntrinsicBlur r2 = android.renderscript.ScriptIntrinsicBlur.create(r1, r2)
            r3 = 1084227584(0x40a00000, float:5.0)
            int r3 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
            r4 = 2
            r5 = 1065353216(0x3f800000, float:1.0)
            r6 = 0
            if (r3 >= 0) goto L_0x002a
            r10.mDownSample = r4     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            goto L_0x002d
        L_0x002a:
            r3 = 6
            r10.mDownSample = r3     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
        L_0x002d:
            android.view.Display r11 = r11.getDisplay()     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            android.graphics.Point r3 = r10.mTmpSize     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            r11.getSize(r3)     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            android.graphics.Rect r11 = new android.graphics.Rect     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            int r3 = r12.getWidth()     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            int r7 = r12.getHeight()     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            r8 = 0
            r11.<init>(r8, r8, r3, r7)     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            android.graphics.Point r3 = r10.mTmpSize     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            int r3 = r3.x     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            int r7 = r10.mDownSample     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            int r3 = r3 / r7
            android.graphics.Point r7 = r10.mTmpSize     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            int r7 = r7.y     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            int r10 = r10.mDownSample     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            int r7 = r7 / r10
            int r10 = java.lang.Math.max(r3, r7)     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            android.util.MathUtils.fitRect(r11, r10)     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            int r10 = r11.width()     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            int r11 = r11.height()     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            r3 = 1
            android.graphics.Bitmap r10 = android.graphics.Bitmap.createScaledBitmap(r12, r10, r11, r3)     // Catch:{ IllegalArgumentException -> 0x0103, all -> 0x00fe }
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r0)     // Catch:{ IllegalArgumentException -> 0x00fb, all -> 0x00f8 }
            android.graphics.Bitmap$Config r11 = r10.getConfig()     // Catch:{ IllegalArgumentException -> 0x00fb, all -> 0x00f8 }
            android.graphics.Bitmap$Config r7 = android.graphics.Bitmap.Config.ARGB_8888     // Catch:{ IllegalArgumentException -> 0x00fb, all -> 0x00f8 }
            if (r11 == r7) goto L_0x008b
            android.graphics.Bitmap$Config r11 = android.graphics.Bitmap.Config.ARGB_8888     // Catch:{ IllegalArgumentException -> 0x00fb, all -> 0x00f8 }
            android.graphics.Bitmap r11 = r10.copy(r11, r8)     // Catch:{ IllegalArgumentException -> 0x00fb, all -> 0x00f8 }
            r10.recycle()     // Catch:{ IllegalArgumentException -> 0x0083, all -> 0x007c }
            r10 = r11
            goto L_0x008b
        L_0x007c:
            r10 = move-exception
            r1 = r6
            r9 = r11
            r11 = r10
            r10 = r9
            goto L_0x0128
        L_0x0083:
            r10 = move-exception
            r1 = r6
            r3 = r1
            r9 = r11
            r11 = r10
            r10 = r9
            goto L_0x0108
        L_0x008b:
            int r11 = (r13 > r5 ? 1 : (r13 == r5 ? 0 : -1))
            if (r11 < 0) goto L_0x00be
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r0)     // Catch:{ IllegalArgumentException -> 0x00fb, all -> 0x00f8 }
            int r0 = r10.getWidth()     // Catch:{ IllegalArgumentException -> 0x00fb, all -> 0x00f8 }
            int r3 = r10.getHeight()     // Catch:{ IllegalArgumentException -> 0x00fb, all -> 0x00f8 }
            android.graphics.Bitmap$Config r7 = android.graphics.Bitmap.Config.ARGB_8888     // Catch:{ IllegalArgumentException -> 0x00fb, all -> 0x00f8 }
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r0, r3, r7)     // Catch:{ IllegalArgumentException -> 0x00fb, all -> 0x00f8 }
            android.renderscript.Allocation$MipmapControl r3 = android.renderscript.Allocation.MipmapControl.MIPMAP_NONE     // Catch:{ IllegalArgumentException -> 0x00fb, all -> 0x00f8 }
            android.renderscript.Allocation r3 = android.renderscript.Allocation.createFromBitmap(r1, r10, r3, r4)     // Catch:{ IllegalArgumentException -> 0x00fb, all -> 0x00f8 }
            android.renderscript.Allocation r1 = android.renderscript.Allocation.createFromBitmap(r1, r0)     // Catch:{ IllegalArgumentException -> 0x00bb, all -> 0x00b7 }
            r2.setRadius(r13)     // Catch:{ IllegalArgumentException -> 0x00f6 }
            r2.setInput(r3)     // Catch:{ IllegalArgumentException -> 0x00f6 }
            r2.forEach(r1)     // Catch:{ IllegalArgumentException -> 0x00f6 }
            r1.copyTo(r0)     // Catch:{ IllegalArgumentException -> 0x00f6 }
            goto L_0x00c6
        L_0x00b7:
            r11 = move-exception
            r1 = r6
            goto L_0x0127
        L_0x00bb:
            r11 = move-exception
            r1 = r6
            goto L_0x0108
        L_0x00be:
            android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.ARGB_8888     // Catch:{ IllegalArgumentException -> 0x00fb, all -> 0x00f8 }
            android.graphics.Bitmap r0 = r10.copy(r0, r3)     // Catch:{ IllegalArgumentException -> 0x00fb, all -> 0x00f8 }
            r1 = r6
            r3 = r1
        L_0x00c6:
            androidx.palette.graphics.Palette$Swatch r12 = com.android.systemui.statusbar.notification.MediaNotificationProcessor.findBackgroundSwatch((android.graphics.Bitmap) r12)     // Catch:{ IllegalArgumentException -> 0x00f6 }
            android.graphics.Canvas r4 = new android.graphics.Canvas     // Catch:{ IllegalArgumentException -> 0x00f6 }
            r4.<init>(r0)     // Catch:{ IllegalArgumentException -> 0x00f6 }
            java.lang.String r7 = "swatch"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r12, r7)     // Catch:{ IllegalArgumentException -> 0x00f6 }
            int r12 = r12.getRgb()     // Catch:{ IllegalArgumentException -> 0x00f6 }
            r7 = 178(0xb2, float:2.5E-43)
            int r12 = com.android.internal.graphics.ColorUtils.setAlphaComponent(r12, r7)     // Catch:{ IllegalArgumentException -> 0x00f6 }
            r4.drawColor(r12)     // Catch:{ IllegalArgumentException -> 0x00f6 }
            if (r11 < 0) goto L_0x00ed
            if (r3 == 0) goto L_0x00e8
            r3.destroy()
        L_0x00e8:
            if (r1 == 0) goto L_0x00ed
            r1.destroy()
        L_0x00ed:
            if (r10 == 0) goto L_0x00f2
            r10.recycle()
        L_0x00f2:
            r2.destroy()
            return r0
        L_0x00f6:
            r11 = move-exception
            goto L_0x0108
        L_0x00f8:
            r11 = move-exception
            r1 = r6
            goto L_0x0128
        L_0x00fb:
            r11 = move-exception
            r1 = r6
            goto L_0x0107
        L_0x00fe:
            r10 = move-exception
            r11 = r10
            r10 = r6
            r1 = r10
            goto L_0x0128
        L_0x0103:
            r10 = move-exception
            r11 = r10
            r10 = r6
            r1 = r10
        L_0x0107:
            r3 = r1
        L_0x0108:
            java.lang.String r12 = "MediaArtworkProcessor"
            java.lang.String r0 = "error while processing artwork"
            android.util.Log.e(r12, r0, r11)     // Catch:{ all -> 0x0126 }
            int r11 = (r13 > r5 ? 1 : (r13 == r5 ? 0 : -1))
            if (r11 < 0) goto L_0x011d
            if (r3 == 0) goto L_0x0118
            r3.destroy()
        L_0x0118:
            if (r1 == 0) goto L_0x011d
            r1.destroy()
        L_0x011d:
            if (r10 == 0) goto L_0x0122
            r10.recycle()
        L_0x0122:
            r2.destroy()
            return r6
        L_0x0126:
            r11 = move-exception
        L_0x0127:
            r6 = r3
        L_0x0128:
            int r12 = (r13 > r5 ? 1 : (r13 == r5 ? 0 : -1))
            if (r12 < 0) goto L_0x0136
            if (r6 == 0) goto L_0x0131
            r6.destroy()
        L_0x0131:
            if (r1 == 0) goto L_0x0136
            r1.destroy()
        L_0x0136:
            if (r10 == 0) goto L_0x013b
            r10.recycle()
        L_0x013b:
            r2.destroy()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.MediaArtworkProcessor.processArtwork(android.content.Context, android.graphics.Bitmap, float):android.graphics.Bitmap");
    }

    public final void clearCache() {
        Bitmap bitmap = this.mArtworkCache;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.mArtworkCache = null;
    }
}
