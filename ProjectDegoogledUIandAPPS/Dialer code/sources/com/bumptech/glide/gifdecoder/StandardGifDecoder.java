package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.resource.gif.GifBitmapProvider;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;

public class StandardGifDecoder implements GifDecoder {
    private int[] act;
    private Bitmap.Config bitmapConfig = Bitmap.Config.ARGB_8888;
    private final GifDecoder.BitmapProvider bitmapProvider;
    private byte[] block;
    private int downsampledHeight;
    private int downsampledWidth;
    private int framePointer;
    private GifHeader header;
    private Boolean isFirstFrameTransparent;
    private byte[] mainPixels;
    private int[] mainScratch;
    private final int[] pct = new int[256];
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private int sampleSize;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;

    public StandardGifDecoder(GifDecoder.BitmapProvider bitmapProvider2, GifHeader gifHeader, ByteBuffer byteBuffer, int i) {
        this.bitmapProvider = bitmapProvider2;
        this.header = new GifHeader();
        setData(gifHeader, byteBuffer, i);
    }

    private Bitmap getNextBitmap() {
        Boolean bool = this.isFirstFrameTransparent;
        Bitmap obtain = ((GifBitmapProvider) this.bitmapProvider).obtain(this.downsampledWidth, this.downsampledHeight, (bool == null || bool.booleanValue()) ? Bitmap.Config.ARGB_8888 : this.bitmapConfig);
        obtain.setHasAlpha(true);
        return obtain;
    }

    private int readByte() {
        return this.rawData.get() & 255;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0044, code lost:
        if (r3.bgIndex == r1.transIndex) goto L_0x0051;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0068  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap setPixels(com.bumptech.glide.gifdecoder.GifFrame r37, com.bumptech.glide.gifdecoder.GifFrame r38) {
        /*
            r36 = this;
            r0 = r36
            r1 = r37
            r2 = r38
            int[] r10 = r0.mainScratch
            r11 = 0
            if (r2 != 0) goto L_0x001c
            android.graphics.Bitmap r3 = r0.previousImage
            if (r3 == 0) goto L_0x0016
            com.bumptech.glide.gifdecoder.GifDecoder$BitmapProvider r4 = r0.bitmapProvider
            com.bumptech.glide.load.resource.gif.GifBitmapProvider r4 = (com.bumptech.glide.load.resource.gif.GifBitmapProvider) r4
            r4.release((android.graphics.Bitmap) r3)
        L_0x0016:
            r3 = 0
            r0.previousImage = r3
            java.util.Arrays.fill(r10, r11)
        L_0x001c:
            r12 = 3
            if (r2 == 0) goto L_0x002a
            int r3 = r2.dispose
            if (r3 != r12) goto L_0x002a
            android.graphics.Bitmap r3 = r0.previousImage
            if (r3 != 0) goto L_0x002a
            java.util.Arrays.fill(r10, r11)
        L_0x002a:
            r13 = 2
            r14 = 1
            if (r2 == 0) goto L_0x0088
            int r3 = r2.dispose
            if (r3 <= 0) goto L_0x0088
            if (r3 != r13) goto L_0x0076
            boolean r3 = r1.transparency
            if (r3 != 0) goto L_0x0047
            com.bumptech.glide.gifdecoder.GifHeader r3 = r0.header
            int r4 = r3.bgColor
            int[] r5 = r1.lct
            if (r5 == 0) goto L_0x0052
            int r3 = r3.bgIndex
            int r5 = r1.transIndex
            if (r3 != r5) goto L_0x0052
            goto L_0x0051
        L_0x0047:
            int r3 = r0.framePointer
            if (r3 != 0) goto L_0x0051
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r14)
            r0.isFirstFrameTransparent = r3
        L_0x0051:
            r4 = r11
        L_0x0052:
            int r3 = r2.f55ih
            int r5 = r0.sampleSize
            int r3 = r3 / r5
            int r6 = r2.f58iy
            int r6 = r6 / r5
            int r7 = r2.f56iw
            int r7 = r7 / r5
            int r2 = r2.f57ix
            int r2 = r2 / r5
            int r5 = r0.downsampledWidth
            int r6 = r6 * r5
            int r6 = r6 + r2
            int r3 = r3 * r5
            int r3 = r3 + r6
        L_0x0066:
            if (r6 >= r3) goto L_0x0088
            int r2 = r6 + r7
            r5 = r6
        L_0x006b:
            if (r5 >= r2) goto L_0x0072
            r10[r5] = r4
            int r5 = r5 + 1
            goto L_0x006b
        L_0x0072:
            int r2 = r0.downsampledWidth
            int r6 = r6 + r2
            goto L_0x0066
        L_0x0076:
            if (r3 != r12) goto L_0x0088
            android.graphics.Bitmap r2 = r0.previousImage
            if (r2 == 0) goto L_0x0088
            r4 = 0
            int r8 = r0.downsampledWidth
            r6 = 0
            r7 = 0
            int r9 = r0.downsampledHeight
            r3 = r10
            r5 = r8
            r2.getPixels(r3, r4, r5, r6, r7, r8, r9)
        L_0x0088:
            if (r1 == 0) goto L_0x0091
            java.nio.ByteBuffer r2 = r0.rawData
            int r3 = r1.bufferFrameStart
            r2.position(r3)
        L_0x0091:
            if (r1 != 0) goto L_0x009a
            com.bumptech.glide.gifdecoder.GifHeader r2 = r0.header
            int r3 = r2.width
            int r2 = r2.height
            goto L_0x009e
        L_0x009a:
            int r3 = r1.f56iw
            int r2 = r1.f55ih
        L_0x009e:
            int r3 = r3 * r2
            byte[] r2 = r0.mainPixels
            if (r2 == 0) goto L_0x00a6
            int r2 = r2.length
            if (r2 >= r3) goto L_0x00b0
        L_0x00a6:
            com.bumptech.glide.gifdecoder.GifDecoder$BitmapProvider r2 = r0.bitmapProvider
            com.bumptech.glide.load.resource.gif.GifBitmapProvider r2 = (com.bumptech.glide.load.resource.gif.GifBitmapProvider) r2
            byte[] r2 = r2.obtainByteArray(r3)
            r0.mainPixels = r2
        L_0x00b0:
            byte[] r2 = r0.mainPixels
            short[] r4 = r0.prefix
            r5 = 4096(0x1000, float:5.74E-42)
            if (r4 != 0) goto L_0x00bc
            short[] r4 = new short[r5]
            r0.prefix = r4
        L_0x00bc:
            short[] r4 = r0.prefix
            byte[] r6 = r0.suffix
            if (r6 != 0) goto L_0x00c6
            byte[] r6 = new byte[r5]
            r0.suffix = r6
        L_0x00c6:
            byte[] r6 = r0.suffix
            byte[] r7 = r0.pixelStack
            if (r7 != 0) goto L_0x00d2
            r7 = 4097(0x1001, float:5.741E-42)
            byte[] r7 = new byte[r7]
            r0.pixelStack = r7
        L_0x00d2:
            byte[] r7 = r0.pixelStack
            int r8 = r36.readByte()
            int r9 = r14 << r8
            int r15 = r9 + 1
            int r16 = r9 + 2
            int r8 = r8 + r14
            int r17 = r14 << r8
            int r17 = r17 + -1
            r13 = r11
        L_0x00e4:
            if (r13 >= r9) goto L_0x00f0
            r4[r13] = r11
            byte r5 = (byte) r13
            r6[r13] = r5
            int r13 = r13 + 1
            r5 = 4096(0x1000, float:5.74E-42)
            goto L_0x00e4
        L_0x00f0:
            byte[] r5 = r0.block
            r14 = r0
            r28 = r8
            r13 = r11
            r19 = r13
            r20 = r19
            r22 = r20
            r23 = r22
            r24 = r23
            r26 = r24
            r29 = r26
            r27 = r16
            r30 = r17
            r25 = -1
        L_0x010a:
            r31 = 8
            if (r13 >= r3) goto L_0x01fe
            if (r20 != 0) goto L_0x0142
            int r12 = r36.readByte()
            if (r12 > 0) goto L_0x011d
            r33 = r8
            r35 = r10
            r34 = r13
            goto L_0x0133
        L_0x011d:
            java.nio.ByteBuffer r11 = r14.rawData
            r33 = r8
            byte[] r8 = r14.block
            r34 = r13
            int r13 = r11.remaining()
            int r13 = java.lang.Math.min(r12, r13)
            r35 = r10
            r10 = 0
            r11.get(r8, r10, r13)
        L_0x0133:
            if (r12 > 0) goto L_0x013d
            r8 = 3
            r14.status = r8
            r11 = r19
            r10 = 0
            goto L_0x0203
        L_0x013d:
            r20 = r12
            r24 = 0
            goto L_0x0148
        L_0x0142:
            r33 = r8
            r35 = r10
            r34 = r13
        L_0x0148:
            byte r8 = r5[r24]
            r8 = r8 & 255(0xff, float:3.57E-43)
            int r8 = r8 << r22
            int r23 = r23 + r8
            int r22 = r22 + 8
            r8 = 1
            int r24 = r24 + 1
            r8 = -1
            int r20 = r20 + -1
            r18 = r14
            r10 = r22
            r13 = r25
            r14 = r26
            r12 = r27
            r11 = r28
        L_0x0164:
            if (r10 < r11) goto L_0x01e9
            r8 = r23 & r30
            int r23 = r23 >> r11
            int r10 = r10 - r11
            if (r8 != r9) goto L_0x0176
            r12 = r16
            r30 = r17
            r11 = r33
            r8 = -1
            r13 = -1
            goto L_0x0164
        L_0x0176:
            if (r8 != r15) goto L_0x018e
            r22 = r10
            r28 = r11
            r27 = r12
            r25 = r13
            r26 = r14
            r14 = r18
            r8 = r33
            r13 = r34
            r10 = r35
            r11 = 0
            r12 = 3
            goto L_0x010a
        L_0x018e:
            r25 = r5
            r5 = -1
            if (r13 != r5) goto L_0x01a1
            byte r5 = r6[r8]
            r7[r29] = r5
            int r29 = r29 + 1
            r18 = r0
            r13 = r8
            r14 = r13
        L_0x019d:
            r5 = r25
            r8 = -1
            goto L_0x0164
        L_0x01a1:
            if (r8 < r12) goto L_0x01aa
            byte r5 = (byte) r14
            r7[r29] = r5
            int r29 = r29 + 1
            r5 = r13
            goto L_0x01ab
        L_0x01aa:
            r5 = r8
        L_0x01ab:
            if (r5 < r9) goto L_0x01b6
            byte r14 = r6[r5]
            r7[r29] = r14
            int r29 = r29 + 1
            short r5 = r4[r5]
            goto L_0x01ab
        L_0x01b6:
            byte r5 = r6[r5]
            r14 = r5 & 255(0xff, float:3.57E-43)
            byte r5 = (byte) r14
            r2[r19] = r5
        L_0x01bd:
            r21 = 1
            int r19 = r19 + 1
            int r34 = r34 + 1
            if (r29 <= 0) goto L_0x01cc
            int r29 = r29 + -1
            byte r22 = r7[r29]
            r2[r19] = r22
            goto L_0x01bd
        L_0x01cc:
            r22 = r7
            r7 = 4096(0x1000, float:5.74E-42)
            if (r12 >= r7) goto L_0x01e3
            short r13 = (short) r13
            r4[r12] = r13
            r6[r12] = r5
            int r12 = r12 + 1
            r5 = r12 & r30
            if (r5 != 0) goto L_0x01e3
            if (r12 >= r7) goto L_0x01e3
            int r11 = r11 + 1
            int r30 = r30 + r12
        L_0x01e3:
            r18 = r0
            r13 = r8
            r7 = r22
            goto L_0x019d
        L_0x01e9:
            r22 = r10
            r28 = r11
            r27 = r12
            r25 = r13
            r26 = r14
            r8 = r33
            r13 = r34
            r10 = r35
            r11 = 0
            r12 = 3
            r14 = r0
            goto L_0x010a
        L_0x01fe:
            r35 = r10
            r10 = r11
            r11 = r19
        L_0x0203:
            java.util.Arrays.fill(r2, r11, r3, r10)
            boolean r2 = r1.interlace
            if (r2 != 0) goto L_0x0276
            int r2 = r0.sampleSize
            r3 = 1
            if (r2 == r3) goto L_0x0211
            goto L_0x0276
        L_0x0211:
            int[] r2 = r0.mainScratch
            int r3 = r1.f55ih
            int r4 = r1.f58iy
            int r5 = r1.f56iw
            int r6 = r1.f57ix
            int r7 = r0.framePointer
            if (r7 != 0) goto L_0x0221
            r7 = 1
            goto L_0x0222
        L_0x0221:
            r7 = r10
        L_0x0222:
            int r8 = r0.downsampledWidth
            byte[] r9 = r0.mainPixels
            int[] r11 = r0.act
            r12 = r10
            r13 = -1
        L_0x022a:
            if (r12 >= r3) goto L_0x0260
            int r14 = r12 + r4
            int r14 = r14 * r8
            int r15 = r14 + r6
            int r10 = r15 + r5
            int r14 = r14 + r8
            if (r14 >= r10) goto L_0x0237
            goto L_0x0238
        L_0x0237:
            r14 = r10
        L_0x0238:
            int r10 = r1.f56iw
            int r10 = r10 * r12
        L_0x023b:
            if (r15 >= r14) goto L_0x0258
            r16 = r3
            byte r3 = r9[r10]
            r17 = r4
            r4 = r3 & 255(0xff, float:3.57E-43)
            if (r4 == r13) goto L_0x024f
            r4 = r11[r4]
            if (r4 == 0) goto L_0x024e
            r2[r15] = r4
            goto L_0x024f
        L_0x024e:
            r13 = r3
        L_0x024f:
            int r10 = r10 + 1
            int r15 = r15 + 1
            r3 = r16
            r4 = r17
            goto L_0x023b
        L_0x0258:
            r16 = r3
            r17 = r4
            int r12 = r12 + 1
            r10 = 0
            goto L_0x022a
        L_0x0260:
            java.lang.Boolean r2 = r0.isFirstFrameTransparent
            if (r2 != 0) goto L_0x026c
            if (r7 == 0) goto L_0x026c
            r2 = -1
            if (r13 == r2) goto L_0x026c
            r32 = 1
            goto L_0x026e
        L_0x026c:
            r32 = 0
        L_0x026e:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r32)
            r0.isFirstFrameTransparent = r2
            goto L_0x0412
        L_0x0276:
            int[] r2 = r0.mainScratch
            int r3 = r1.f55ih
            int r4 = r0.sampleSize
            int r3 = r3 / r4
            int r5 = r1.f58iy
            int r5 = r5 / r4
            int r6 = r1.f56iw
            int r6 = r6 / r4
            int r7 = r1.f57ix
            int r7 = r7 / r4
            int r4 = r0.framePointer
            r8 = 1
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r8)
            if (r4 != 0) goto L_0x0291
            r4 = 1
            goto L_0x0292
        L_0x0291:
            r4 = 0
        L_0x0292:
            int r8 = r0.sampleSize
            int r10 = r0.downsampledWidth
            int r11 = r0.downsampledHeight
            byte[] r12 = r0.mainPixels
            int[] r13 = r0.act
            java.lang.Boolean r14 = r0.isFirstFrameTransparent
            r38 = r9
            r15 = r14
            r17 = r31
            r9 = 0
            r14 = 0
            r16 = 1
        L_0x02a7:
            if (r14 >= r3) goto L_0x03fb
            r18 = r15
            boolean r15 = r1.interlace
            if (r15 == 0) goto L_0x02d4
            if (r9 < r3) goto L_0x02cb
            int r15 = r16 + 1
            r20 = r3
            r3 = 2
            if (r15 == r3) goto L_0x02c8
            r3 = 3
            if (r15 == r3) goto L_0x02c3
            r3 = 4
            if (r15 == r3) goto L_0x02bf
            goto L_0x02cf
        L_0x02bf:
            r9 = 1
            r17 = 2
            goto L_0x02cf
        L_0x02c3:
            r3 = 4
            r17 = r3
            r9 = 2
            goto L_0x02cf
        L_0x02c8:
            r3 = 4
            r9 = r3
            goto L_0x02cf
        L_0x02cb:
            r20 = r3
            r15 = r16
        L_0x02cf:
            int r3 = r9 + r17
            r16 = r15
            goto L_0x02d8
        L_0x02d4:
            r20 = r3
            r3 = r9
            r9 = r14
        L_0x02d8:
            int r9 = r9 + r5
            r15 = 1
            if (r8 != r15) goto L_0x02de
            r15 = 1
            goto L_0x02df
        L_0x02de:
            r15 = 0
        L_0x02df:
            if (r9 >= r11) goto L_0x03db
            int r9 = r9 * r10
            int r19 = r9 + r7
            r22 = r3
            int r3 = r19 + r6
            int r9 = r9 + r10
            if (r9 >= r3) goto L_0x02ec
            r3 = r9
        L_0x02ec:
            int r9 = r14 * r8
            r23 = r5
            int r5 = r1.f56iw
            int r9 = r9 * r5
            if (r15 == 0) goto L_0x031b
            r15 = r9
            r9 = r18
            r5 = r19
        L_0x02fa:
            r24 = r6
            if (r5 >= r3) goto L_0x0315
            byte r6 = r12[r15]
            r6 = r6 & 255(0xff, float:3.57E-43)
            r6 = r13[r6]
            if (r6 == 0) goto L_0x0309
            r2[r5] = r6
            goto L_0x030f
        L_0x0309:
            if (r4 == 0) goto L_0x030f
            if (r9 != 0) goto L_0x030f
            r9 = r38
        L_0x030f:
            int r15 = r15 + r8
            int r5 = r5 + 1
            r6 = r24
            goto L_0x02fa
        L_0x0315:
            r25 = r7
            r18 = r9
            goto L_0x03e3
        L_0x031b:
            r24 = r6
            int r5 = r3 - r19
            int r5 = r5 * r8
            int r5 = r5 + r9
            r15 = r9
            r9 = r18
            r6 = r19
        L_0x0326:
            if (r6 >= r3) goto L_0x03d3
            r19 = r3
            int r3 = r1.f56iw
            r25 = r7
            r30 = r10
            r7 = r15
            r18 = 0
            r26 = 0
            r27 = 0
            r28 = 0
            r29 = 0
        L_0x033b:
            int r10 = r0.sampleSize
            int r10 = r10 + r15
            if (r7 >= r10) goto L_0x0370
            byte[] r10 = r0.mainPixels
            r33 = r11
            int r11 = r10.length
            if (r7 >= r11) goto L_0x0372
            if (r7 >= r5) goto L_0x0372
            byte r10 = r10[r7]
            r10 = r10 & 255(0xff, float:3.57E-43)
            int[] r11 = r0.act
            r10 = r11[r10]
            if (r10 == 0) goto L_0x036b
            int r11 = r10 >> 24
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r18 = r18 + r11
            int r11 = r10 >> 16
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r26 = r26 + r11
            int r11 = r10 >> 8
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r27 = r27 + r11
            r10 = r10 & 255(0xff, float:3.57E-43)
            int r28 = r28 + r10
            int r29 = r29 + 1
        L_0x036b:
            int r7 = r7 + 1
            r11 = r33
            goto L_0x033b
        L_0x0370:
            r33 = r11
        L_0x0372:
            int r3 = r3 + r15
            r7 = r3
        L_0x0374:
            int r10 = r0.sampleSize
            int r10 = r10 + r3
            if (r7 >= r10) goto L_0x03a5
            byte[] r10 = r0.mainPixels
            int r11 = r10.length
            if (r7 >= r11) goto L_0x03a5
            if (r7 >= r5) goto L_0x03a5
            byte r10 = r10[r7]
            r10 = r10 & 255(0xff, float:3.57E-43)
            int[] r11 = r0.act
            r10 = r11[r10]
            if (r10 == 0) goto L_0x03a2
            int r11 = r10 >> 24
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r18 = r18 + r11
            int r11 = r10 >> 16
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r26 = r26 + r11
            int r11 = r10 >> 8
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r27 = r27 + r11
            r10 = r10 & 255(0xff, float:3.57E-43)
            int r28 = r28 + r10
            int r29 = r29 + 1
        L_0x03a2:
            int r7 = r7 + 1
            goto L_0x0374
        L_0x03a5:
            if (r29 != 0) goto L_0x03a9
            r11 = 0
            goto L_0x03bb
        L_0x03a9:
            int r18 = r18 / r29
            int r3 = r18 << 24
            int r26 = r26 / r29
            int r7 = r26 << 16
            r3 = r3 | r7
            int r27 = r27 / r29
            int r7 = r27 << 8
            r3 = r3 | r7
            int r28 = r28 / r29
            r11 = r3 | r28
        L_0x03bb:
            if (r11 == 0) goto L_0x03c0
            r2[r6] = r11
            goto L_0x03c6
        L_0x03c0:
            if (r4 == 0) goto L_0x03c6
            if (r9 != 0) goto L_0x03c6
            r9 = r38
        L_0x03c6:
            int r15 = r15 + r8
            int r6 = r6 + 1
            r3 = r19
            r7 = r25
            r10 = r30
            r11 = r33
            goto L_0x0326
        L_0x03d3:
            r25 = r7
            r30 = r10
            r33 = r11
            r15 = r9
            goto L_0x03e9
        L_0x03db:
            r22 = r3
            r23 = r5
            r24 = r6
            r25 = r7
        L_0x03e3:
            r30 = r10
            r33 = r11
            r15 = r18
        L_0x03e9:
            int r14 = r14 + 1
            r3 = r20
            r9 = r22
            r5 = r23
            r6 = r24
            r7 = r25
            r10 = r30
            r11 = r33
            goto L_0x02a7
        L_0x03fb:
            r18 = r15
            java.lang.Boolean r2 = r0.isFirstFrameTransparent
            if (r2 != 0) goto L_0x0412
            if (r18 != 0) goto L_0x0406
            r32 = 0
            goto L_0x040c
        L_0x0406:
            boolean r11 = r18.booleanValue()
            r32 = r11
        L_0x040c:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r32)
            r0.isFirstFrameTransparent = r2
        L_0x0412:
            boolean r2 = r0.savePrevious
            if (r2 == 0) goto L_0x0436
            int r1 = r1.dispose
            if (r1 == 0) goto L_0x041d
            r2 = 1
            if (r1 != r2) goto L_0x0436
        L_0x041d:
            android.graphics.Bitmap r1 = r0.previousImage
            if (r1 != 0) goto L_0x0427
            android.graphics.Bitmap r1 = r36.getNextBitmap()
            r0.previousImage = r1
        L_0x0427:
            android.graphics.Bitmap r1 = r0.previousImage
            r3 = 0
            int r7 = r0.downsampledWidth
            r5 = 0
            r6 = 0
            int r8 = r0.downsampledHeight
            r2 = r35
            r4 = r7
            r1.setPixels(r2, r3, r4, r5, r6, r7, r8)
        L_0x0436:
            android.graphics.Bitmap r8 = r36.getNextBitmap()
            r2 = 0
            int r6 = r0.downsampledWidth
            r4 = 0
            r5 = 0
            int r7 = r0.downsampledHeight
            r0 = r8
            r1 = r35
            r3 = r6
            r0.setPixels(r1, r2, r3, r4, r5, r6, r7)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.StandardGifDecoder.setPixels(com.bumptech.glide.gifdecoder.GifFrame, com.bumptech.glide.gifdecoder.GifFrame):android.graphics.Bitmap");
    }

    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    public void clear() {
        this.header = null;
        byte[] bArr = this.mainPixels;
        if (bArr != null) {
            ((GifBitmapProvider) this.bitmapProvider).release(bArr);
        }
        int[] iArr = this.mainScratch;
        if (iArr != null) {
            ((GifBitmapProvider) this.bitmapProvider).release(iArr);
        }
        Bitmap bitmap = this.previousImage;
        if (bitmap != null) {
            ((GifBitmapProvider) this.bitmapProvider).release(bitmap);
        }
        this.previousImage = null;
        this.rawData = null;
        this.isFirstFrameTransparent = null;
        byte[] bArr2 = this.block;
        if (bArr2 != null) {
            ((GifBitmapProvider) this.bitmapProvider).release(bArr2);
        }
    }

    public int getByteSize() {
        return (this.mainScratch.length * 4) + this.rawData.limit() + this.mainPixels.length;
    }

    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    public ByteBuffer getData() {
        return this.rawData;
    }

    public int getFrameCount() {
        return this.header.frameCount;
    }

    public int getNextDelay() {
        int i;
        GifHeader gifHeader = this.header;
        int i2 = gifHeader.frameCount;
        if (i2 <= 0 || (i = this.framePointer) < 0) {
            return 0;
        }
        if (i < 0 || i >= i2) {
            return -1;
        }
        return gifHeader.frames.get(i).delay;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f1, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.graphics.Bitmap getNextFrame() {
        /*
            r7 = this;
            monitor-enter(r7)
            com.bumptech.glide.gifdecoder.GifHeader r0 = r7.header     // Catch:{ all -> 0x00f2 }
            int r0 = r0.frameCount     // Catch:{ all -> 0x00f2 }
            r1 = 3
            r2 = 1
            if (r0 <= 0) goto L_0x000d
            int r0 = r7.framePointer     // Catch:{ all -> 0x00f2 }
            if (r0 >= 0) goto L_0x003d
        L_0x000d:
            java.lang.String r0 = "StandardGifDecoder"
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00f2 }
            if (r0 == 0) goto L_0x003b
            java.lang.String r0 = "StandardGifDecoder"
            com.bumptech.glide.gifdecoder.GifHeader r3 = r7.header     // Catch:{ all -> 0x00f2 }
            int r3 = r3.frameCount     // Catch:{ all -> 0x00f2 }
            int r4 = r7.framePointer     // Catch:{ all -> 0x00f2 }
            r5 = 72
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f2 }
            r6.<init>(r5)     // Catch:{ all -> 0x00f2 }
            java.lang.String r5 = "Unable to decode frame, frameCount="
            r6.append(r5)     // Catch:{ all -> 0x00f2 }
            r6.append(r3)     // Catch:{ all -> 0x00f2 }
            java.lang.String r3 = ", framePointer="
            r6.append(r3)     // Catch:{ all -> 0x00f2 }
            r6.append(r4)     // Catch:{ all -> 0x00f2 }
            java.lang.String r3 = r6.toString()     // Catch:{ all -> 0x00f2 }
            android.util.Log.d(r0, r3)     // Catch:{ all -> 0x00f2 }
        L_0x003b:
            r7.status = r2     // Catch:{ all -> 0x00f2 }
        L_0x003d:
            int r0 = r7.status     // Catch:{ all -> 0x00f2 }
            r3 = 0
            if (r0 == r2) goto L_0x00ce
            int r0 = r7.status     // Catch:{ all -> 0x00f2 }
            r4 = 2
            if (r0 != r4) goto L_0x0049
            goto L_0x00ce
        L_0x0049:
            r0 = 0
            r7.status = r0     // Catch:{ all -> 0x00f2 }
            byte[] r4 = r7.block     // Catch:{ all -> 0x00f2 }
            if (r4 != 0) goto L_0x005c
            com.bumptech.glide.gifdecoder.GifDecoder$BitmapProvider r4 = r7.bitmapProvider     // Catch:{ all -> 0x00f2 }
            r5 = 255(0xff, float:3.57E-43)
            com.bumptech.glide.load.resource.gif.GifBitmapProvider r4 = (com.bumptech.glide.load.resource.gif.GifBitmapProvider) r4
            byte[] r4 = r4.obtainByteArray(r5)     // Catch:{ all -> 0x00f2 }
            r7.block = r4     // Catch:{ all -> 0x00f2 }
        L_0x005c:
            com.bumptech.glide.gifdecoder.GifHeader r4 = r7.header     // Catch:{ all -> 0x00f2 }
            java.util.List<com.bumptech.glide.gifdecoder.GifFrame> r4 = r4.frames     // Catch:{ all -> 0x00f2 }
            int r5 = r7.framePointer     // Catch:{ all -> 0x00f2 }
            java.lang.Object r4 = r4.get(r5)     // Catch:{ all -> 0x00f2 }
            com.bumptech.glide.gifdecoder.GifFrame r4 = (com.bumptech.glide.gifdecoder.GifFrame) r4     // Catch:{ all -> 0x00f2 }
            int r5 = r7.framePointer     // Catch:{ all -> 0x00f2 }
            int r5 = r5 - r2
            if (r5 < 0) goto L_0x0078
            com.bumptech.glide.gifdecoder.GifHeader r6 = r7.header     // Catch:{ all -> 0x00f2 }
            java.util.List<com.bumptech.glide.gifdecoder.GifFrame> r6 = r6.frames     // Catch:{ all -> 0x00f2 }
            java.lang.Object r5 = r6.get(r5)     // Catch:{ all -> 0x00f2 }
            com.bumptech.glide.gifdecoder.GifFrame r5 = (com.bumptech.glide.gifdecoder.GifFrame) r5     // Catch:{ all -> 0x00f2 }
            goto L_0x0079
        L_0x0078:
            r5 = r3
        L_0x0079:
            int[] r6 = r4.lct     // Catch:{ all -> 0x00f2 }
            if (r6 == 0) goto L_0x0080
            int[] r6 = r4.lct     // Catch:{ all -> 0x00f2 }
            goto L_0x0084
        L_0x0080:
            com.bumptech.glide.gifdecoder.GifHeader r6 = r7.header     // Catch:{ all -> 0x00f2 }
            int[] r6 = r6.gct     // Catch:{ all -> 0x00f2 }
        L_0x0084:
            r7.act = r6     // Catch:{ all -> 0x00f2 }
            int[] r6 = r7.act     // Catch:{ all -> 0x00f2 }
            if (r6 != 0) goto L_0x00b0
            java.lang.String r0 = "StandardGifDecoder"
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00f2 }
            if (r0 == 0) goto L_0x00ac
            java.lang.String r0 = "StandardGifDecoder"
            int r1 = r7.framePointer     // Catch:{ all -> 0x00f2 }
            r4 = 49
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f2 }
            r5.<init>(r4)     // Catch:{ all -> 0x00f2 }
            java.lang.String r4 = "No valid color table found for frame #"
            r5.append(r4)     // Catch:{ all -> 0x00f2 }
            r5.append(r1)     // Catch:{ all -> 0x00f2 }
            java.lang.String r1 = r5.toString()     // Catch:{ all -> 0x00f2 }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00f2 }
        L_0x00ac:
            r7.status = r2     // Catch:{ all -> 0x00f2 }
            monitor-exit(r7)
            return r3
        L_0x00b0:
            boolean r1 = r4.transparency     // Catch:{ all -> 0x00f2 }
            if (r1 == 0) goto L_0x00c8
            int[] r1 = r7.act     // Catch:{ all -> 0x00f2 }
            int[] r2 = r7.pct     // Catch:{ all -> 0x00f2 }
            int[] r3 = r7.act     // Catch:{ all -> 0x00f2 }
            int r3 = r3.length     // Catch:{ all -> 0x00f2 }
            java.lang.System.arraycopy(r1, r0, r2, r0, r3)     // Catch:{ all -> 0x00f2 }
            int[] r1 = r7.pct     // Catch:{ all -> 0x00f2 }
            r7.act = r1     // Catch:{ all -> 0x00f2 }
            int[] r1 = r7.act     // Catch:{ all -> 0x00f2 }
            int r2 = r4.transIndex     // Catch:{ all -> 0x00f2 }
            r1[r2] = r0     // Catch:{ all -> 0x00f2 }
        L_0x00c8:
            android.graphics.Bitmap r0 = r7.setPixels(r4, r5)     // Catch:{ all -> 0x00f2 }
            monitor-exit(r7)
            return r0
        L_0x00ce:
            java.lang.String r0 = "StandardGifDecoder"
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00f2 }
            if (r0 == 0) goto L_0x00f0
            java.lang.String r0 = "StandardGifDecoder"
            int r1 = r7.status     // Catch:{ all -> 0x00f2 }
            r2 = 42
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f2 }
            r4.<init>(r2)     // Catch:{ all -> 0x00f2 }
            java.lang.String r2 = "Unable to decode frame, status="
            r4.append(r2)     // Catch:{ all -> 0x00f2 }
            r4.append(r1)     // Catch:{ all -> 0x00f2 }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x00f2 }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00f2 }
        L_0x00f0:
            monitor-exit(r7)
            return r3
        L_0x00f2:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.StandardGifDecoder.getNextFrame():android.graphics.Bitmap");
    }

    public void resetFrameIndex() {
        this.framePointer = -1;
    }

    public synchronized void setData(GifHeader gifHeader, ByteBuffer byteBuffer, int i) {
        if (i > 0) {
            int highestOneBit = Integer.highestOneBit(i);
            this.status = 0;
            this.header = gifHeader;
            this.framePointer = -1;
            this.rawData = byteBuffer.asReadOnlyBuffer();
            this.rawData.position(0);
            this.rawData.order(ByteOrder.LITTLE_ENDIAN);
            this.savePrevious = false;
            Iterator<GifFrame> it = gifHeader.frames.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().dispose == 3) {
                        this.savePrevious = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            this.sampleSize = highestOneBit;
            int i2 = gifHeader.width;
            this.downsampledWidth = i2 / highestOneBit;
            int i3 = gifHeader.height;
            this.downsampledHeight = i3 / highestOneBit;
            this.mainPixels = ((GifBitmapProvider) this.bitmapProvider).obtainByteArray(i2 * i3);
            this.mainScratch = ((GifBitmapProvider) this.bitmapProvider).obtainIntArray(this.downsampledWidth * this.downsampledHeight);
        } else {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Sample size must be >=0, not: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public void setDefaultBitmapConfig(Bitmap.Config config) {
        if (config == Bitmap.Config.ARGB_8888 || config == Bitmap.Config.RGB_565) {
            this.bitmapConfig = config;
            return;
        }
        String valueOf = String.valueOf(config);
        String valueOf2 = String.valueOf(Bitmap.Config.ARGB_8888);
        String valueOf3 = String.valueOf(Bitmap.Config.RGB_565);
        StringBuilder sb = new StringBuilder(valueOf3.length() + valueOf2.length() + valueOf.length() + 41);
        sb.append("Unsupported format: ");
        sb.append(valueOf);
        sb.append(", must be one of ");
        sb.append(valueOf2);
        throw new IllegalArgumentException(GeneratedOutlineSupport.outline12(sb, " or ", valueOf3));
    }
}
