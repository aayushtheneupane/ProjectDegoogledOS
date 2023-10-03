package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.p002v7.appcompat.R$style;
import android.util.DisplayMetrics;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public final class Downsampler {
    public static final Option<Boolean> ALLOW_HARDWARE_CONFIG = Option.memory("com.bumtpech.glide.load.resource.bitmap.Downsampler.AllowHardwareDecode");
    public static final Option<DecodeFormat> DECODE_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeFormat", DecodeFormat.DEFAULT);
    public static final Option<DownsampleStrategy> DOWNSAMPLE_STRATEGY = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.DownsampleStrategy", DownsampleStrategy.DEFAULT);
    private static final DecodeCallbacks EMPTY_CALLBACKS = new DecodeCallbacks() {
        public void onDecodeComplete(BitmapPool bitmapPool, Bitmap bitmap) {
        }

        public void onObtainBounds() {
        }
    };
    public static final Option<Boolean> FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.FixBitmapSize", false);
    private static final Set<String> NO_DOWNSAMPLE_PRE_N_MIME_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"image/vnd.wap.wbmp", "image/x-ico"})));
    private static final Queue<BitmapFactory.Options> OPTIONS_QUEUE = Util.createQueue(0);
    private static final Set<ImageHeaderParser.ImageType> TYPES_THAT_USE_POOL_PRE_KITKAT = Collections.unmodifiableSet(EnumSet.of(ImageHeaderParser.ImageType.JPEG, ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG));
    private final BitmapPool bitmapPool;
    private final ArrayPool byteArrayPool;
    private final DisplayMetrics displayMetrics;
    private final HardwareConfigState hardwareConfigState = HardwareConfigState.getInstance();
    private final List<ImageHeaderParser> parsers;

    public interface DecodeCallbacks {
        void onDecodeComplete(BitmapPool bitmapPool, Bitmap bitmap) throws IOException;

        void onObtainBounds();
    }

    public Downsampler(List<ImageHeaderParser> list, DisplayMetrics displayMetrics2, BitmapPool bitmapPool2, ArrayPool arrayPool) {
        this.parsers = list;
        R$style.checkNotNull(displayMetrics2, "Argument must not be null");
        this.displayMetrics = displayMetrics2;
        R$style.checkNotNull(bitmapPool2, "Argument must not be null");
        this.bitmapPool = bitmapPool2;
        R$style.checkNotNull(arrayPool, "Argument must not be null");
        this.byteArrayPool = arrayPool;
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:0x02f9  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x02fc  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0308  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x031f A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0338  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0368  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x03c5  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x03e5  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0482  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0487  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x02d7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap decodeFromWrappedStreams(java.io.InputStream r34, android.graphics.BitmapFactory.Options r35, com.bumptech.glide.load.resource.bitmap.DownsampleStrategy r36, com.bumptech.glide.load.DecodeFormat r37, boolean r38, int r39, int r40, boolean r41, com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeCallbacks r42) throws java.io.IOException {
        /*
            r33 = this;
            r1 = r33
            r2 = r34
            r10 = r35
            r0 = r36
            r11 = r37
            r12 = r39
            r13 = r40
            r14 = r42
            long r15 = com.bumptech.glide.util.LogTime.getLogTime()
            com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r3 = r1.bitmapPool
            int[] r3 = getDimensions(r2, r10, r14, r3)
            r4 = 0
            r9 = r3[r4]
            r5 = 1
            r8 = r3[r5]
            java.lang.String r7 = r10.outMimeType
            r3 = -1
            if (r9 == r3) goto L_0x002b
            if (r8 != r3) goto L_0x0028
            goto L_0x002b
        L_0x0028:
            r17 = r38
            goto L_0x002d
        L_0x002b:
            r17 = r4
        L_0x002d:
            java.util.List<com.bumptech.glide.load.ImageHeaderParser> r3 = r1.parsers
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r6 = r1.byteArrayPool
            int r6 = android.support.p002v7.appcompat.R$style.getOrientation(r3, r2, r6)
            int r3 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.getExifOrientationDegrees(r6)
            switch(r6) {
                case 2: goto L_0x003f;
                case 3: goto L_0x003f;
                case 4: goto L_0x003f;
                case 5: goto L_0x003f;
                case 6: goto L_0x003f;
                case 7: goto L_0x003f;
                case 8: goto L_0x003f;
                default: goto L_0x003c;
            }
        L_0x003c:
            r18 = r4
            goto L_0x0041
        L_0x003f:
            r18 = r5
        L_0x0041:
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r12 != r4) goto L_0x0047
            r5 = r9
            goto L_0x0048
        L_0x0047:
            r5 = r12
        L_0x0048:
            r38 = r6
            if (r13 != r4) goto L_0x004e
            r4 = r8
            goto L_0x004f
        L_0x004e:
            r4 = r13
        L_0x004f:
            java.util.List<com.bumptech.glide.load.ImageHeaderParser> r6 = r1.parsers
            r19 = r7
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r7 = r1.byteArrayPool
            com.bumptech.glide.load.ImageHeaderParser$ImageType r6 = android.support.p002v7.appcompat.R$style.getType(r6, r2, r7)
            com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r7 = r1.bitmapPool
            java.lang.String r13 = "]"
            java.lang.String r12 = ", target density: "
            r20 = 1065353216(0x3f800000, float:1.0)
            r21 = r15
            java.lang.String r15 = ", density: "
            java.lang.String r11 = "x"
            java.lang.String r1 = "Downsampler"
            if (r9 <= 0) goto L_0x024c
            if (r8 > 0) goto L_0x006f
            goto L_0x024c
        L_0x006f:
            r16 = r13
            r13 = 90
            if (r3 == r13) goto L_0x007f
            r13 = 270(0x10e, float:3.78E-43)
            if (r3 != r13) goto L_0x007a
            goto L_0x007f
        L_0x007a:
            float r3 = r0.getScaleFactor(r9, r8, r5, r4)
            goto L_0x0083
        L_0x007f:
            float r3 = r0.getScaleFactor(r8, r9, r5, r4)
        L_0x0083:
            r13 = 0
            int r13 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            r23 = r15
            java.lang.String r15 = "], target: ["
            if (r13 <= 0) goto L_0x0201
            com.bumptech.glide.load.resource.bitmap.DownsampleStrategy$SampleSizeRounding r13 = r0.getSampleSizeRounding(r9, r8, r5, r4)
            if (r13 == 0) goto L_0x01f9
            r24 = r12
            float r12 = (float) r9
            r25 = r15
            float r15 = r3 * r12
            r26 = r1
            double r0 = (double) r15
            int r0 = round(r0)
            float r1 = (float) r8
            float r15 = r3 * r1
            r28 = r4
            r27 = r5
            double r4 = (double) r15
            int r4 = round(r4)
            int r0 = r9 / r0
            int r4 = r8 / r4
            com.bumptech.glide.load.resource.bitmap.DownsampleStrategy$SampleSizeRounding r5 = com.bumptech.glide.load.resource.bitmap.DownsampleStrategy.SampleSizeRounding.MEMORY
            if (r13 != r5) goto L_0x00b9
            int r0 = java.lang.Math.max(r0, r4)
            goto L_0x00bd
        L_0x00b9:
            int r0 = java.lang.Math.min(r0, r4)
        L_0x00bd:
            int r4 = android.os.Build.VERSION.SDK_INT
            int r0 = java.lang.Integer.highestOneBit(r0)
            r4 = 1
            int r0 = java.lang.Math.max(r4, r0)
            com.bumptech.glide.load.resource.bitmap.DownsampleStrategy$SampleSizeRounding r4 = com.bumptech.glide.load.resource.bitmap.DownsampleStrategy.SampleSizeRounding.MEMORY
            if (r13 != r4) goto L_0x00d5
            float r4 = (float) r0
            float r5 = r20 / r3
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 >= 0) goto L_0x00d5
            int r0 = r0 << 1
        L_0x00d5:
            r10.inSampleSize = r0
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.JPEG
            if (r6 != r4) goto L_0x00f7
            r4 = 8
            int r4 = java.lang.Math.min(r0, r4)
            float r4 = (float) r4
            float r12 = r12 / r4
            double r5 = (double) r12
            double r5 = java.lang.Math.ceil(r5)
            int r5 = (int) r5
            float r1 = r1 / r4
            double r6 = (double) r1
            double r6 = java.lang.Math.ceil(r6)
            int r1 = (int) r6
            int r4 = r0 / 8
            if (r4 <= 0) goto L_0x013f
            int r5 = r5 / r4
            int r1 = r1 / r4
            goto L_0x013f
        L_0x00f7:
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.PNG
            if (r6 == r4) goto L_0x0130
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.PNG_A
            if (r6 != r4) goto L_0x0100
            goto L_0x0130
        L_0x0100:
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.WEBP
            if (r6 == r4) goto L_0x0122
            com.bumptech.glide.load.ImageHeaderParser$ImageType r4 = com.bumptech.glide.load.ImageHeaderParser.ImageType.WEBP_A
            if (r6 != r4) goto L_0x0109
            goto L_0x0122
        L_0x0109:
            int r1 = r9 % r0
            if (r1 != 0) goto L_0x0117
            int r1 = r8 % r0
            if (r1 == 0) goto L_0x0112
            goto L_0x0117
        L_0x0112:
            int r5 = r9 / r0
            int r1 = r8 / r0
            goto L_0x013f
        L_0x0117:
            int[] r1 = getDimensions(r2, r10, r14, r7)
            r4 = 0
            r5 = r1[r4]
            r4 = 1
            r1 = r1[r4]
            goto L_0x013f
        L_0x0122:
            int r4 = android.os.Build.VERSION.SDK_INT
            float r4 = (float) r0
            float r12 = r12 / r4
            int r5 = java.lang.Math.round(r12)
            float r1 = r1 / r4
            int r1 = java.lang.Math.round(r1)
            goto L_0x013f
        L_0x0130:
            float r4 = (float) r0
            float r12 = r12 / r4
            double r5 = (double) r12
            double r5 = java.lang.Math.floor(r5)
            int r5 = (int) r5
            float r1 = r1 / r4
            double r6 = (double) r1
            double r6 = java.lang.Math.floor(r6)
            int r1 = (int) r6
        L_0x013f:
            r4 = r1
            r12 = r27
            r13 = r28
            r1 = r36
            float r1 = r1.getScaleFactor(r5, r4, r12, r13)
            double r6 = (double) r1
            int r1 = android.os.Build.VERSION.SDK_INT
            int r1 = getDensityMultiplier(r6)
            double r14 = (double) r1
            double r14 = r14 * r6
            int r14 = round(r14)
            float r15 = (float) r14
            float r1 = (float) r1
            float r15 = r15 / r1
            double r1 = (double) r15
            double r1 = r6 / r1
            double r14 = (double) r14
            double r1 = r1 * r14
            int r1 = round(r1)
            r10.inTargetDensity = r1
            int r1 = getDensityMultiplier(r6)
            r10.inDensity = r1
            int r1 = r10.inTargetDensity
            if (r1 <= 0) goto L_0x0177
            int r2 = r10.inDensity
            if (r2 <= 0) goto L_0x0177
            if (r1 == r2) goto L_0x0177
            r1 = 1
            goto L_0x0178
        L_0x0177:
            r1 = 0
        L_0x0178:
            if (r1 == 0) goto L_0x017e
            r1 = 1
            r10.inScaled = r1
            goto L_0x0183
        L_0x017e:
            r1 = 0
            r10.inTargetDensity = r1
            r10.inDensity = r1
        L_0x0183:
            r1 = 2
            r2 = r1
            r1 = r26
            boolean r2 = android.util.Log.isLoggable(r1, r2)
            if (r2 == 0) goto L_0x01f3
            int r2 = r10.inTargetDensity
            int r14 = r10.inDensity
            r15 = 309(0x135, float:4.33E-43)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r15)
            java.lang.String r15 = "Calculate scaling, source: ["
            r10.append(r15)
            r10.append(r9)
            r10.append(r11)
            r10.append(r8)
            r15 = r25
            r10.append(r15)
            r10.append(r12)
            r10.append(r11)
            r10.append(r13)
            java.lang.String r15 = "], power of two scaled: ["
            r10.append(r15)
            r10.append(r5)
            r10.append(r11)
            r10.append(r4)
            java.lang.String r4 = "], exact scale factor: "
            r10.append(r4)
            r10.append(r3)
            java.lang.String r3 = ", power of 2 sample size: "
            r10.append(r3)
            r10.append(r0)
            java.lang.String r0 = ", adjusted scale factor: "
            r10.append(r0)
            r10.append(r6)
            r15 = r24
            r10.append(r15)
            r10.append(r2)
            r2 = r23
            r10.append(r2)
            r10.append(r14)
            java.lang.String r0 = r10.toString()
            android.util.Log.v(r1, r0)
            goto L_0x0287
        L_0x01f3:
            r2 = r23
            r15 = r24
            goto L_0x0287
        L_0x01f9:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Cannot round with null rounding"
            r0.<init>(r1)
            throw r0
        L_0x0201:
            r1 = r0
            r13 = r4
            r12 = r5
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = java.lang.String.valueOf(r36)
            int r2 = r1.length()
            int r2 = r2 + 118
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r2)
            java.lang.String r2 = "Cannot scale with factor: "
            r4.append(r2)
            r4.append(r3)
            java.lang.String r2 = " from: "
            r4.append(r2)
            r4.append(r1)
            java.lang.String r1 = ", source: ["
            r4.append(r1)
            r4.append(r9)
            r4.append(r11)
            r4.append(r8)
            r4.append(r15)
            r4.append(r12)
            r4.append(r11)
            r4.append(r13)
            r3 = r16
            r4.append(r3)
            java.lang.String r1 = r4.toString()
            r0.<init>(r1)
            throw r0
        L_0x024c:
            r3 = r13
            r2 = r15
            r13 = r4
            r15 = r12
            r12 = r5
            r0 = 3
            boolean r0 = android.util.Log.isLoggable(r1, r0)
            if (r0 == 0) goto L_0x0287
            java.lang.String r0 = java.lang.String.valueOf(r6)
            int r4 = r0.length()
            int r4 = r4 + 74
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r4)
            java.lang.String r4 = "Unable to determine dimensions for: "
            r5.append(r4)
            r5.append(r0)
            java.lang.String r0 = " with target ["
            r5.append(r0)
            r5.append(r12)
            r5.append(r11)
            r5.append(r13)
            r5.append(r3)
            java.lang.String r0 = r5.toString()
            android.util.Log.d(r1, r0)
        L_0x0287:
            r10 = r1
            r1 = r33
            com.bumptech.glide.load.resource.bitmap.HardwareConfigState r3 = r1.hardwareConfigState
            r14 = 1
            r16 = 0
            r4 = r12
            r5 = r13
            r7 = r38
            r6 = r35
            r30 = r7
            r29 = r19
            r7 = r37
            r31 = r8
            r8 = r17
            r32 = r9
            r9 = r18
            boolean r0 = r3.setHardwareConfigIfAllowed(r4, r5, r6, r7, r8, r9)
            if (r0 == 0) goto L_0x02af
            r6 = r34
            r3 = r35
            r4 = r11
            goto L_0x0313
        L_0x02af:
            com.bumptech.glide.load.DecodeFormat r0 = com.bumptech.glide.load.DecodeFormat.PREFER_ARGB_8888
            r3 = r37
            r4 = r11
            if (r3 == r0) goto L_0x030b
            com.bumptech.glide.load.DecodeFormat r0 = com.bumptech.glide.load.DecodeFormat.PREFER_ARGB_8888_DISALLOW_HARDWARE
            if (r3 == r0) goto L_0x030b
            int r0 = android.os.Build.VERSION.SDK_INT
            java.util.List<com.bumptech.glide.load.ImageHeaderParser> r0 = r1.parsers     // Catch:{ IOException -> 0x02cd }
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r5 = r1.byteArrayPool     // Catch:{ IOException -> 0x02cd }
            r6 = r34
            com.bumptech.glide.load.ImageHeaderParser$ImageType r0 = android.support.p002v7.appcompat.R$style.getType(r0, r6, r5)     // Catch:{ IOException -> 0x02cb }
            boolean r0 = r0.hasAlpha()     // Catch:{ IOException -> 0x02cb }
            goto L_0x02f7
        L_0x02cb:
            r0 = move-exception
            goto L_0x02d0
        L_0x02cd:
            r0 = move-exception
            r6 = r34
        L_0x02d0:
            r5 = 3
            boolean r5 = android.util.Log.isLoggable(r10, r5)
            if (r5 == 0) goto L_0x02f5
            java.lang.String r3 = java.lang.String.valueOf(r37)
            int r5 = r3.length()
            int r5 = r5 + 72
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r5)
            java.lang.String r5 = "Cannot determine whether the image has alpha or not from header, format "
            r7.append(r5)
            r7.append(r3)
            java.lang.String r3 = r7.toString()
            android.util.Log.d(r10, r3, r0)
        L_0x02f5:
            r0 = r16
        L_0x02f7:
            if (r0 == 0) goto L_0x02fc
            android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.ARGB_8888
            goto L_0x02fe
        L_0x02fc:
            android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.RGB_565
        L_0x02fe:
            r3 = r35
            r3.inPreferredConfig = r0
            android.graphics.Bitmap$Config r0 = r3.inPreferredConfig
            android.graphics.Bitmap$Config r5 = android.graphics.Bitmap.Config.RGB_565
            if (r0 != r5) goto L_0x0313
            r3.inDither = r14
            goto L_0x0313
        L_0x030b:
            r6 = r34
            r3 = r35
            android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.ARGB_8888
            r3.inPreferredConfig = r0
        L_0x0313:
            int r0 = android.os.Build.VERSION.SDK_INT
            int r0 = r3.inSampleSize
            int r0 = android.os.Build.VERSION.SDK_INT
            r5 = r32
            r7 = r31
            if (r5 < 0) goto L_0x0329
            if (r7 < 0) goto L_0x0329
            if (r41 == 0) goto L_0x0329
            r9 = r12
            r11 = r13
        L_0x0325:
            r24 = r15
            goto L_0x03b6
        L_0x0329:
            int r0 = r3.inTargetDensity
            if (r0 <= 0) goto L_0x0334
            int r8 = r3.inDensity
            if (r8 <= 0) goto L_0x0334
            if (r0 == r8) goto L_0x0334
            goto L_0x0336
        L_0x0334:
            r14 = r16
        L_0x0336:
            if (r14 == 0) goto L_0x0340
            int r0 = r3.inTargetDensity
            float r0 = (float) r0
            int r8 = r3.inDensity
            float r8 = (float) r8
            float r20 = r0 / r8
        L_0x0340:
            r0 = r20
            int r8 = r3.inSampleSize
            float r9 = (float) r5
            float r11 = (float) r8
            float r9 = r9 / r11
            double r12 = (double) r9
            double r12 = java.lang.Math.ceil(r12)
            int r9 = (int) r12
            float r12 = (float) r7
            float r12 = r12 / r11
            double r11 = (double) r12
            double r11 = java.lang.Math.ceil(r11)
            int r11 = (int) r11
            float r9 = (float) r9
            float r9 = r9 * r0
            int r9 = java.lang.Math.round(r9)
            float r11 = (float) r11
            float r11 = r11 * r0
            int r11 = java.lang.Math.round(r11)
            r12 = 2
            boolean r12 = android.util.Log.isLoggable(r10, r12)
            if (r12 == 0) goto L_0x0325
            int r12 = r3.inTargetDensity
            int r13 = r3.inDensity
            r14 = 192(0xc0, float:2.69E-43)
            r24 = r15
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>(r14)
            java.lang.String r14 = "Calculated target ["
            r15.append(r14)
            r15.append(r9)
            r15.append(r4)
            r15.append(r11)
            java.lang.String r14 = "] for source ["
            r15.append(r14)
            r15.append(r5)
            r15.append(r4)
            r15.append(r7)
            java.lang.String r14 = "], sampleSize: "
            r15.append(r14)
            r15.append(r8)
            java.lang.String r8 = ", targetDensity: "
            r15.append(r8)
            r15.append(r12)
            r15.append(r2)
            r15.append(r13)
            java.lang.String r8 = ", density multiplier: "
            r15.append(r8)
            r15.append(r0)
            java.lang.String r0 = r15.toString()
            android.util.Log.v(r10, r0)
        L_0x03b6:
            if (r9 <= 0) goto L_0x03d1
            if (r11 <= 0) goto L_0x03d1
            com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r0 = r1.bitmapPool
            int r8 = android.os.Build.VERSION.SDK_INT
            android.graphics.Bitmap$Config r8 = r3.inPreferredConfig
            android.graphics.Bitmap$Config r12 = android.graphics.Bitmap.Config.HARDWARE
            if (r8 != r12) goto L_0x03c5
            goto L_0x03d1
        L_0x03c5:
            android.graphics.Bitmap$Config r12 = r3.outConfig
            if (r12 != 0) goto L_0x03ca
            goto L_0x03cb
        L_0x03ca:
            r8 = r12
        L_0x03cb:
            android.graphics.Bitmap r0 = r0.getDirty(r9, r11, r8)
            r3.inBitmap = r0
        L_0x03d1:
            com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r0 = r1.bitmapPool
            r8 = r42
            android.graphics.Bitmap r0 = decodeStream(r6, r3, r8, r0)
            com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r6 = r1.bitmapPool
            r8.onDecodeComplete(r6, r0)
            r6 = 2
            boolean r6 = android.util.Log.isLoggable(r10, r6)
            if (r6 == 0) goto L_0x0482
            java.lang.String r6 = getBitmapString(r0)
            android.graphics.Bitmap r8 = r3.inBitmap
            java.lang.String r8 = getBitmapString(r8)
            int r9 = r3.inSampleSize
            int r11 = r3.inDensity
            int r3 = r3.inTargetDensity
            java.lang.Thread r12 = java.lang.Thread.currentThread()
            java.lang.String r12 = r12.getName()
            double r13 = com.bumptech.glide.util.LogTime.getElapsedMillis(r21)
            r15 = 208(0xd0, float:2.91E-43)
            int r15 = com.android.tools.p006r8.GeneratedOutlineSupport.outline1(r6, r15)
            r1 = r29
            int r15 = com.android.tools.p006r8.GeneratedOutlineSupport.outline1(r1, r15)
            int r15 = com.android.tools.p006r8.GeneratedOutlineSupport.outline1(r8, r15)
            int r15 = com.android.tools.p006r8.GeneratedOutlineSupport.outline1(r12, r15)
            r34 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r15)
            java.lang.String r15 = "Decoded "
            r0.append(r15)
            r0.append(r6)
            java.lang.String r6 = " from ["
            r0.append(r6)
            r0.append(r5)
            r0.append(r4)
            r0.append(r7)
            java.lang.String r5 = "] "
            r0.append(r5)
            r0.append(r1)
            java.lang.String r1 = " with inBitmap "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r1 = " for ["
            r0.append(r1)
            r1 = r39
            r5 = r24
            r0.append(r1)
            r0.append(r4)
            r1 = r40
            r0.append(r1)
            java.lang.String r1 = "], sample size: "
            r0.append(r1)
            r0.append(r9)
            r0.append(r2)
            r0.append(r11)
            r0.append(r5)
            r0.append(r3)
            java.lang.String r1 = ", thread: "
            r0.append(r1)
            r0.append(r12)
            java.lang.String r1 = ", duration: "
            r0.append(r1)
            r0.append(r13)
            java.lang.String r0 = r0.toString()
            android.util.Log.v(r10, r0)
            goto L_0x0484
        L_0x0482:
            r34 = r0
        L_0x0484:
            r0 = 0
            if (r34 == 0) goto L_0x04a5
            r1 = r33
            android.util.DisplayMetrics r0 = r1.displayMetrics
            int r0 = r0.densityDpi
            r2 = r34
            r2.setDensity(r0)
            com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r0 = r1.bitmapPool
            r3 = r30
            android.graphics.Bitmap r0 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImageExif(r0, r2, r3)
            boolean r3 = r2.equals(r0)
            if (r3 != 0) goto L_0x04a5
            com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r1 = r1.bitmapPool
            r1.put(r2)
        L_0x04a5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.Downsampler.decodeFromWrappedStreams(java.io.InputStream, android.graphics.BitmapFactory$Options, com.bumptech.glide.load.resource.bitmap.DownsampleStrategy, com.bumptech.glide.load.DecodeFormat, boolean, int, int, boolean, com.bumptech.glide.load.resource.bitmap.Downsampler$DecodeCallbacks):android.graphics.Bitmap");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:23|24) */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        throw r1;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Bitmap decodeStream(java.io.InputStream r6, android.graphics.BitmapFactory.Options r7, com.bumptech.glide.load.resource.bitmap.Downsampler.DecodeCallbacks r8, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool r9) throws java.io.IOException {
        /*
            java.lang.String r0 = "Downsampler"
            boolean r1 = r7.inJustDecodeBounds
            if (r1 == 0) goto L_0x000c
            r1 = 10485760(0xa00000, float:1.469368E-38)
            r6.mark(r1)
            goto L_0x000f
        L_0x000c:
            r8.onObtainBounds()
        L_0x000f:
            int r1 = r7.outWidth
            int r2 = r7.outHeight
            java.lang.String r3 = r7.outMimeType
            java.util.concurrent.locks.Lock r4 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.getBitmapDrawableLock()
            r4.lock()
            r4 = 0
            android.graphics.Bitmap r8 = android.graphics.BitmapFactory.decodeStream(r6, r4, r7)     // Catch:{ IllegalArgumentException -> 0x0032 }
            java.util.concurrent.locks.Lock r9 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.getBitmapDrawableLock()
            r9.unlock()
            boolean r7 = r7.inJustDecodeBounds
            if (r7 == 0) goto L_0x002f
            r6.reset()
        L_0x002f:
            return r8
        L_0x0030:
            r6 = move-exception
            goto L_0x005f
        L_0x0032:
            r5 = move-exception
            java.io.IOException r1 = newIoExceptionForInBitmapAssertion(r5, r1, r2, r3, r7)     // Catch:{ all -> 0x0030 }
            r2 = 3
            boolean r2 = android.util.Log.isLoggable(r0, r2)     // Catch:{ all -> 0x0030 }
            if (r2 == 0) goto L_0x0043
            java.lang.String r2 = "Failed to decode with inBitmap, trying again without Bitmap re-use"
            android.util.Log.d(r0, r2, r1)     // Catch:{ all -> 0x0030 }
        L_0x0043:
            android.graphics.Bitmap r0 = r7.inBitmap     // Catch:{ all -> 0x0030 }
            if (r0 == 0) goto L_0x005e
            r6.reset()     // Catch:{ IOException -> 0x005d }
            android.graphics.Bitmap r0 = r7.inBitmap     // Catch:{ IOException -> 0x005d }
            r9.put(r0)     // Catch:{ IOException -> 0x005d }
            r7.inBitmap = r4     // Catch:{ IOException -> 0x005d }
            android.graphics.Bitmap r6 = decodeStream(r6, r7, r8, r9)     // Catch:{ IOException -> 0x005d }
            java.util.concurrent.locks.Lock r7 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.getBitmapDrawableLock()
            r7.unlock()
            return r6
        L_0x005d:
            throw r1     // Catch:{ all -> 0x0030 }
        L_0x005e:
            throw r1     // Catch:{ all -> 0x0030 }
        L_0x005f:
            java.util.concurrent.locks.Lock r7 = com.bumptech.glide.load.resource.bitmap.TransformationUtils.getBitmapDrawableLock()
            r7.unlock()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.Downsampler.decodeStream(java.io.InputStream, android.graphics.BitmapFactory$Options, com.bumptech.glide.load.resource.bitmap.Downsampler$DecodeCallbacks, com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool):android.graphics.Bitmap");
    }

    @TargetApi(19)
    private static String getBitmapString(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int i = Build.VERSION.SDK_INT;
        int allocationByteCount = bitmap.getAllocationByteCount();
        StringBuilder sb = new StringBuilder(14);
        sb.append(" (");
        sb.append(allocationByteCount);
        sb.append(")");
        String sb2 = sb.toString();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        String valueOf = String.valueOf(bitmap.getConfig());
        StringBuilder sb3 = new StringBuilder(GeneratedOutlineSupport.outline1(sb2, valueOf.length() + 26));
        sb3.append("[");
        sb3.append(width);
        sb3.append("x");
        sb3.append(height);
        sb3.append("] ");
        sb3.append(valueOf);
        sb3.append(sb2);
        return sb3.toString();
    }

    private static synchronized BitmapFactory.Options getDefaultOptions() {
        BitmapFactory.Options poll;
        synchronized (Downsampler.class) {
            synchronized (OPTIONS_QUEUE) {
                poll = OPTIONS_QUEUE.poll();
            }
            if (poll == null) {
                poll = new BitmapFactory.Options();
                resetOptions(poll);
            }
        }
        return poll;
    }

    private static int getDensityMultiplier(double d) {
        if (d > 1.0d) {
            d = 1.0d / d;
        }
        return (int) Math.round(d * 2.147483647E9d);
    }

    private static int[] getDimensions(InputStream inputStream, BitmapFactory.Options options, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool2) throws IOException {
        options.inJustDecodeBounds = true;
        decodeStream(inputStream, options, decodeCallbacks, bitmapPool2);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    private static IOException newIoExceptionForInBitmapAssertion(IllegalArgumentException illegalArgumentException, int i, int i2, String str, BitmapFactory.Options options) {
        String bitmapString = getBitmapString(options.inBitmap);
        StringBuilder sb = new StringBuilder(GeneratedOutlineSupport.outline1(bitmapString, GeneratedOutlineSupport.outline1(str, 99)));
        sb.append("Exception decoding bitmap, outWidth: ");
        sb.append(i);
        sb.append(", outHeight: ");
        sb.append(i2);
        sb.append(", outMimeType: ");
        sb.append(str);
        sb.append(", inBitmap: ");
        sb.append(bitmapString);
        return new IOException(sb.toString(), illegalArgumentException);
    }

    private static void releaseOptions(BitmapFactory.Options options) {
        resetOptions(options);
        synchronized (OPTIONS_QUEUE) {
            OPTIONS_QUEUE.offer(options);
        }
    }

    private static void resetOptions(BitmapFactory.Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.inDensity = 0;
        options.inTargetDensity = 0;
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        options.inBitmap = null;
        options.inMutable = true;
    }

    private static int round(double d) {
        return (int) (d + 0.5d);
    }

    public Resource<Bitmap> decode(InputStream inputStream, int i, int i2, Options options) throws IOException {
        return decode(inputStream, i, i2, options, EMPTY_CALLBACKS);
    }

    public boolean handles(InputStream inputStream) {
        return true;
    }

    public boolean handles(ByteBuffer byteBuffer) {
        return true;
    }

    public Resource<Bitmap> decode(InputStream inputStream, int i, int i2, Options options, DecodeCallbacks decodeCallbacks) throws IOException {
        Options options2 = options;
        R$style.checkArgument(inputStream.markSupported(), "You must provide an InputStream that supports mark()");
        byte[] bArr = (byte[]) this.byteArrayPool.get(65536, byte[].class);
        BitmapFactory.Options defaultOptions = getDefaultOptions();
        defaultOptions.inTempStorage = bArr;
        DecodeFormat decodeFormat = (DecodeFormat) options2.get(DECODE_FORMAT);
        try {
            return BitmapResource.obtain(decodeFromWrappedStreams(inputStream, defaultOptions, (DownsampleStrategy) options2.get(DOWNSAMPLE_STRATEGY), decodeFormat, decodeFormat == DecodeFormat.PREFER_ARGB_8888_DISALLOW_HARDWARE ? false : options2.get(ALLOW_HARDWARE_CONFIG) != null && ((Boolean) options2.get(ALLOW_HARDWARE_CONFIG)).booleanValue(), i, i2, ((Boolean) options2.get(FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS)).booleanValue(), decodeCallbacks), this.bitmapPool);
        } finally {
            releaseOptions(defaultOptions);
            this.byteArrayPool.put(bArr);
        }
    }
}
