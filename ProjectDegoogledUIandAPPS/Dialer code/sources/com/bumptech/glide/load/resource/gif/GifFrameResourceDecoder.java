package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.gifdecoder.StandardGifDecoder;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import java.io.IOException;

public final class GifFrameResourceDecoder implements ResourceDecoder<GifDecoder, Bitmap> {
    private final BitmapPool bitmapPool;

    public GifFrameResourceDecoder(BitmapPool bitmapPool2) {
        this.bitmapPool = bitmapPool2;
    }

    public Resource decode(Object obj, int i, int i2, Options options) throws IOException {
        return BitmapResource.obtain(((StandardGifDecoder) obj).getNextFrame(), this.bitmapPool);
    }

    public boolean handles(Object obj, Options options) throws IOException {
        GifDecoder gifDecoder = (GifDecoder) obj;
        return true;
    }
}
