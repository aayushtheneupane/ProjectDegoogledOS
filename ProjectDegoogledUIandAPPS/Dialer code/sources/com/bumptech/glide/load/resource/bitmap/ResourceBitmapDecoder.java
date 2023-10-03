package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.drawable.ResourceDrawableDecoder;
import java.io.IOException;

public class ResourceBitmapDecoder implements ResourceDecoder<Uri, Bitmap> {
    private final BitmapPool bitmapPool;
    private final ResourceDrawableDecoder drawableDecoder;

    public ResourceBitmapDecoder(ResourceDrawableDecoder resourceDrawableDecoder, BitmapPool bitmapPool2) {
        this.drawableDecoder = resourceDrawableDecoder;
        this.bitmapPool = bitmapPool2;
    }

    public Resource decode(Object obj, int i, int i2, Options options) throws IOException {
        Resource<Drawable> decode = this.drawableDecoder.decode((Uri) obj, i, i2, options);
        if (decode == null) {
            return null;
        }
        return DrawableToBitmapConverter.convert(this.bitmapPool, decode.get(), i, i2);
    }

    public boolean handles(Object obj, Options options) throws IOException {
        return "android.resource".equals(((Uri) obj).getScheme());
    }
}
