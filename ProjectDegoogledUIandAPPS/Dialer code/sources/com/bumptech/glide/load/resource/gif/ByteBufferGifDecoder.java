package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.gifdecoder.GifHeader;
import com.bumptech.glide.gifdecoder.GifHeaderParser;
import com.bumptech.glide.gifdecoder.StandardGifDecoder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.UnitTransformation;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Queue;

public class ByteBufferGifDecoder implements ResourceDecoder<ByteBuffer, GifDrawable> {
    private static final GifDecoderFactory GIF_DECODER_FACTORY = new GifDecoderFactory();
    private static final GifHeaderParserPool PARSER_POOL = new GifHeaderParserPool();
    private final Context context;
    private final GifDecoderFactory gifDecoderFactory;
    private final GifHeaderParserPool parserPool;
    private final List<ImageHeaderParser> parsers;
    private final GifBitmapProvider provider;

    static class GifDecoderFactory {
        GifDecoderFactory() {
        }

        /* access modifiers changed from: package-private */
        public GifDecoder build(GifDecoder.BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer, int i) {
            return new StandardGifDecoder(bitmapProvider, gifHeader, byteBuffer, i);
        }
    }

    static class GifHeaderParserPool {
        private final Queue<GifHeaderParser> pool = Util.createQueue(0);

        GifHeaderParserPool() {
        }

        /* access modifiers changed from: package-private */
        public synchronized GifHeaderParser obtain(ByteBuffer byteBuffer) {
            GifHeaderParser poll;
            poll = this.pool.poll();
            if (poll == null) {
                poll = new GifHeaderParser();
            }
            poll.setData(byteBuffer);
            return poll;
        }

        /* access modifiers changed from: package-private */
        public synchronized void release(GifHeaderParser gifHeaderParser) {
            gifHeaderParser.clear();
            this.pool.offer(gifHeaderParser);
        }
    }

    public ByteBufferGifDecoder(Context context2, List<ImageHeaderParser> list, BitmapPool bitmapPool, ArrayPool arrayPool) {
        this(context2, list, bitmapPool, arrayPool, PARSER_POOL, GIF_DECODER_FACTORY);
    }

    private static int getSampleSize(GifHeader gifHeader, int i, int i2) {
        int i3;
        int min = Math.min(gifHeader.getHeight() / i2, gifHeader.getWidth() / i);
        if (min == 0) {
            i3 = 0;
        } else {
            i3 = Integer.highestOneBit(min);
        }
        int max = Math.max(1, i3);
        if (Log.isLoggable("BufferGifDecoder", 2) && max > 1) {
            int width = gifHeader.getWidth();
            int height = gifHeader.getHeight();
            StringBuilder sb = new StringBuilder(125);
            sb.append("Downsampling GIF, sampleSize: ");
            sb.append(max);
            sb.append(", target dimens: [");
            sb.append(i);
            sb.append("x");
            sb.append(i2);
            sb.append("], actual dimens: [");
            sb.append(width);
            sb.append("x");
            sb.append(height);
            sb.append("]");
            Log.v("BufferGifDecoder", sb.toString());
        }
        return max;
    }

    ByteBufferGifDecoder(Context context2, List<ImageHeaderParser> list, BitmapPool bitmapPool, ArrayPool arrayPool, GifHeaderParserPool gifHeaderParserPool, GifDecoderFactory gifDecoderFactory2) {
        this.context = context2.getApplicationContext();
        this.parsers = list;
        this.gifDecoderFactory = gifDecoderFactory2;
        this.provider = new GifBitmapProvider(bitmapPool, arrayPool);
        this.parserPool = gifHeaderParserPool;
    }

    public GifDrawableResource decode(ByteBuffer byteBuffer, int i, int i2, Options options) {
        GifHeaderParser obtain = this.parserPool.obtain(byteBuffer);
        try {
            return decode(byteBuffer, i, i2, obtain, options);
        } finally {
            this.parserPool.release(obtain);
        }
    }

    public boolean handles(ByteBuffer byteBuffer, Options options) throws IOException {
        ImageHeaderParser.ImageType imageType;
        if (((Boolean) options.get(GifOptions.DISABLE_ANIMATION)).booleanValue()) {
            return false;
        }
        List<ImageHeaderParser> list = this.parsers;
        if (byteBuffer == null) {
            imageType = ImageHeaderParser.ImageType.UNKNOWN;
        } else {
            int size = list.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    imageType = ImageHeaderParser.ImageType.UNKNOWN;
                    break;
                }
                ImageHeaderParser.ImageType type = ((DefaultImageHeaderParser) list.get(i)).getType(byteBuffer);
                if (type != ImageHeaderParser.ImageType.UNKNOWN) {
                    imageType = type;
                    break;
                }
                i++;
            }
        }
        if (imageType == ImageHeaderParser.ImageType.GIF) {
            return true;
        }
        return false;
    }

    private GifDrawableResource decode(ByteBuffer byteBuffer, int i, int i2, GifHeaderParser gifHeaderParser, Options options) {
        long logTime = LogTime.getLogTime();
        try {
            GifHeader parseHeader = gifHeaderParser.parseHeader();
            if (parseHeader.getNumFrames() > 0) {
                if (parseHeader.getStatus() == 0) {
                    Bitmap.Config config = options.get(GifOptions.DECODE_FORMAT) == DecodeFormat.PREFER_RGB_565 ? Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_8888;
                    StandardGifDecoder standardGifDecoder = (StandardGifDecoder) this.gifDecoderFactory.build(this.provider, parseHeader, byteBuffer, getSampleSize(parseHeader, i, i2));
                    standardGifDecoder.setDefaultBitmapConfig(config);
                    standardGifDecoder.advance();
                    Bitmap nextFrame = standardGifDecoder.getNextFrame();
                    if (nextFrame == null) {
                        if (Log.isLoggable("BufferGifDecoder", 2)) {
                            double elapsedMillis = LogTime.getElapsedMillis(logTime);
                            StringBuilder sb = new StringBuilder(51);
                            sb.append("Decoded GIF from stream in ");
                            sb.append(elapsedMillis);
                            Log.v("BufferGifDecoder", sb.toString());
                        }
                        return null;
                    }
                    UnitTransformation unitTransformation = UnitTransformation.get();
                    Glide glide = Glide.get(this.context);
                    GifFrameLoader gifFrameLoader = r10;
                    GifFrameLoader gifFrameLoader2 = new GifFrameLoader(glide, standardGifDecoder, i, i2, unitTransformation, nextFrame);
                    GifDrawableResource gifDrawableResource = new GifDrawableResource(new GifDrawable(new GifDrawable.GifState(gifFrameLoader)));
                    if (Log.isLoggable("BufferGifDecoder", 2)) {
                        double elapsedMillis2 = LogTime.getElapsedMillis(logTime);
                        StringBuilder sb2 = new StringBuilder(51);
                        sb2.append("Decoded GIF from stream in ");
                        sb2.append(elapsedMillis2);
                        Log.v("BufferGifDecoder", sb2.toString());
                    }
                    return gifDrawableResource;
                }
            }
            return null;
        } finally {
            if (Log.isLoggable("BufferGifDecoder", 2)) {
                double elapsedMillis3 = LogTime.getElapsedMillis(logTime);
                StringBuilder sb3 = new StringBuilder(51);
                sb3.append("Decoded GIF from stream in ");
                sb3.append(elapsedMillis3);
                Log.v("BufferGifDecoder", sb3.toString());
            }
        }
    }
}
